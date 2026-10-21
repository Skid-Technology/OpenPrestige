package dev.zprestige.prestige.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.zprestige.prestige.client.Prestige;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Local (offline) backend of the Configs page. Every config is one JSON file in {@code <gameDir>/prestige/configs/}:
 * the card metadata plus the client's own snapshot ({@code ConfigManager.a()}), which is applied again with
 * {@code ConfigManager.a(String)}. "Public configs" lists the configs saved with Public visibility, "Personal configs"
 * lists all of them; Create saves the current module/HUD state, Update overwrites it, Delete removes the file.
 */
public final class LocalConfigs {
    private LocalConfigs() {}

    /** Cards per page of the Public tab. */
    static final int PAGE = 9;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /** On-disk format. */
    static final class Entry {
        int id;
        String name = "", desc = "", author = "", date = "";
        List<String> tags = new ArrayList<>();
        int visibility;           // 0 personal, 1 public
        int downloads;
        JsonObject data;          // snapshot produced by ConfigManager.a()
    }

    private static boolean ready;
    private static final List<Entry> ALL = new ArrayList<>();
    private static int lastTab = -1, lastPage = -1;
    private static String lastQuery = null;

    static Path dir() {
        Path base;
        try { base = net.fabricmc.loader.api.FabricLoader.getInstance().getGameDir(); } catch (Throwable t) { base = Path.of("."); }
        return base.resolve("prestige").resolve("configs");
    }

    /** Wires the hooks of {@link ConfigsPage}; called once from the first frame of the page. */
    static void init() {
        if (ready) return;
        ready = true;
        ConfigsPage.loading = false;
        ConfigsPage.currentUser = CreateConfigPage.username;
        ConfigsPage.onDownload = LocalConfigs::download;
        ConfigsPage.onEdit = c -> {};
        ConfigsPage.onUpdate = c -> WidgetsPage.dialog(1, "Update Config",
                "Overwrite \"" + c.name + "\" with your current module settings?", () -> update(c));
        ConfigsPage.onDelete = c -> WidgetsPage.dialog(1, "Delete Config",
                "Delete \"" + c.name + "\"? This cannot be undone.", () -> delete(c));
        ConfigsPage.onPageChange = LocalConfigs::rebuild;
        ConfigsPage.onSearch = () -> { ConfigsPage.pageIndex = 0; rebuild(); };
        ConfigsPage.onTabChange = () -> { ConfigsPage.pageIndex = 0; rebuild(); };
        reload();
    }

    /** Per-frame: runs the init, reacts to search/tab changes and to the Create button. */
    static void tick() {
        init();
        if (CreateConfigPage.createRequested) { CreateConfigPage.createRequested = false; create(); }
        if (ConfigsPage.tab != lastTab || ConfigsPage.pageIndex != lastPage || !ConfigsPage.query.equals(lastQuery)) rebuild();
    }

    // ------------------------------------------------------------------ disk

    static void reload() {
        ALL.clear();
        Path d = dir();
        try {
            Files.createDirectories(d);
            try (Stream<Path> s = Files.list(d)) {
                for (Path p : (Iterable<Path>) s.filter(f -> f.getFileName().toString().endsWith(".json"))::iterator) {
                    try {
                        Entry e = GSON.fromJson(Files.readString(p, StandardCharsets.UTF_8), Entry.class);
                        if (e == null || e.name == null || e.name.isBlank()) continue;
                        if (e.tags == null) e.tags = new ArrayList<>();
                        ALL.add(e);
                    } catch (Throwable t) { System.out.println("[prestige-ui] config " + p.getFileName() + ": " + t); }
                }
            }
        } catch (IOException e) { System.out.println("[prestige-ui] configs dir: " + e); }
        ALL.sort(Comparator.comparingInt((Entry e) -> e.id).reversed());
        rebuild();
    }

    private static Path file(Entry e) { return dir().resolve(e.id + "_" + e.name.replaceAll("[^A-Za-z0-9_.-]", "_") + ".json"); }

    private static void write(Entry e) {
        try {
            Files.createDirectories(dir());
            // an id only ever has one file (the name part can change)
            deleteFiles(e.id);
            Files.writeString(file(e), GSON.toJson(e), StandardCharsets.UTF_8);
        } catch (IOException ex) { System.out.println("[prestige-ui] config save: " + ex); WidgetsPage.toast("Could not save config"); }
    }

    private static void deleteFiles(int id) {
        try (Stream<Path> s = Files.list(dir())) {
            s.filter(f -> f.getFileName().toString().startsWith(id + "_")).forEach(f -> { try { Files.deleteIfExists(f); } catch (IOException ignored) {} });
        } catch (IOException ignored) {}
    }

    // ------------------------------------------------------------------ view

    static void rebuild() {
        lastTab = ConfigsPage.tab; lastPage = ConfigsPage.pageIndex; lastQuery = ConfigsPage.query;
        String q = ConfigsPage.query.trim().toLowerCase(Locale.ROOT);
        List<Entry> match = new ArrayList<>();
        for (Entry e : ALL) {
            if (ConfigsPage.tab == 0 && e.visibility != 1) continue;
            if (!q.isEmpty() && !(e.name.toLowerCase(Locale.ROOT).contains(q) || e.desc.toLowerCase(Locale.ROOT).contains(q)
                    || e.author.toLowerCase(Locale.ROOT).contains(q) || e.tags.stream().anyMatch(t -> t.toLowerCase(Locale.ROOT).contains(q)))) continue;
            match.add(e);
        }
        List<ConfigsPage.Cfg> out = ConfigsPage.tab == 0 ? ConfigsPage.publicCfgs : ConfigsPage.personalCfgs;
        out.clear();
        int from = 0, to = match.size();
        if (ConfigsPage.tab == 0) {
            int pages = Math.max(1, (match.size() + PAGE - 1) / PAGE);
            ConfigsPage.pageTotal = pages - 1;
            ConfigsPage.pageIndex = Math.max(0, Math.min(ConfigsPage.pageIndex, pages - 1));
            lastPage = ConfigsPage.pageIndex;
            from = ConfigsPage.pageIndex * PAGE; to = Math.min(match.size(), from + PAGE);
        }
        for (Entry e : match.subList(from, to)) {
            ConfigsPage.Cfg c = new ConfigsPage.Cfg(e.id, e.name, e.downloads, e.desc, e.author, e.date, true, e.tags.toArray(new String[0]));
            out.add(c);
        }
        ConfigsPage.selected = -1;
    }

    private static Entry find(ConfigsPage.Cfg c) {
        for (Entry e : ALL) if (e.id == c.id) return e;
        return null;
    }

    // ------------------------------------------------------------------ actions

    private static String snapshot() {
        if (Prestige.e == null) return null;
        try { return Prestige.e.a(); } catch (Throwable t) { System.out.println("[prestige-ui] snapshot: " + t); return null; }
    }

    static void create() {
        String name = CreateConfigPage.name.toString().trim();
        if (name.isEmpty()) { WidgetsPage.dialog(2, "Create Config", "Please enter a name for the config.", null); return; }
        for (Entry e : ALL) if (e.name.equalsIgnoreCase(name)) {
            WidgetsPage.dialog(2, "Create Config", "A config called \"" + e.name + "\" already exists. Use Update on its card instead.", null);
            return;
        }
        String snap = snapshot();
        if (snap == null) { WidgetsPage.dialog(2, "Create Config", "The client is not ready yet, try again in a moment.", null); return; }
        Entry e = new Entry();
        int next = 1;
        for (Entry o : ALL) next = Math.max(next, o.id + 1);
        e.id = next;
        e.name = name;
        e.desc = CreateConfigPage.desc.toString().trim();
        e.author = CreateConfigPage.username;
        e.date = LocalDate.now().toString();
        e.visibility = CreateConfigPage.visibility;
        for (int i = 0; i < CreateConfigPage.TAGS.length; i++) if (CreateConfigPage.tagSel[i]) e.tags.add(CreateConfigPage.TAGS[i]);
        e.data = GSON.fromJson(snap, JsonObject.class);
        write(e);
        ALL.add(0, e);
        CreateConfigPage.name.setLength(0);
        CreateConfigPage.desc.setLength(0);
        java.util.Arrays.fill(CreateConfigPage.tagSel, false);
        CreateConfigPage.visibility = 0;
        ConfigsPage.query = "";
        ConfigsPage.setTab(1);
        rebuild();
        WidgetsPage.toast("Config created");
    }

    static void download(ConfigsPage.Cfg c) {
        Entry e = find(c);
        if (e == null || e.data == null) { WidgetsPage.toast("Config is empty"); return; }
        if (Prestige.e == null) { WidgetsPage.toast("Client is not ready"); return; }
        try {
            Prestige.e.a(e.data.toString());
            String report = Prestige.e.b();
            e.downloads++;
            c.downloads = e.downloads;
            write(e);
            // b() is the client's load summary ("<applied>|<warnings>|<errors>")
            System.out.println("[prestige-ui] config '" + e.name + "' loaded " + report);
            WidgetsPage.toast("Loaded " + e.name);
        } catch (Throwable t) {
            System.out.println("[prestige-ui] config load: " + t);
            WidgetsPage.toast("Failed to load config");
        }
    }

    static void update(ConfigsPage.Cfg c) {
        Entry e = find(c);
        String snap = snapshot();
        if (e == null || snap == null) { WidgetsPage.toast("Could not update config"); return; }
        e.data = GSON.fromJson(snap, JsonObject.class);
        e.date = LocalDate.now().toString();
        write(e);
        rebuild();
        WidgetsPage.toast("Config updated");
    }

    static void delete(ConfigsPage.Cfg c) {
        Entry e = find(c);
        if (e == null) return;
        deleteFiles(e.id);
        ALL.remove(e);
        rebuild();
        WidgetsPage.toast("Config deleted");
    }

    /** Dev: create -> download -> update -> delete round trip (DevCmd "state cfgtest"). */
    static String selfTest() {
        init();
        StringBuilder r = new StringBuilder("CFGTEST");
        CreateConfigPage.name.setLength(0); CreateConfigPage.name.append("selftest");
        CreateConfigPage.desc.setLength(0); CreateConfigPage.desc.append("round trip");
        CreateConfigPage.visibility = 1;
        int before = ALL.size();
        create();
        r.append(" created=").append(ALL.size() - before);
        Entry e = ALL.isEmpty() ? null : ALL.get(0);
        if (e == null || !e.name.equals("selftest")) return r.append(" FAIL(no entry)").toString();
        r.append(" file=").append(Files.exists(file(e))).append(" dataKeys=").append(e.data == null ? -1 : e.data.size());
        r.append(" publicCards=").append(ConfigsPage.publicCfgs.size());
        ConfigsPage.Cfg c = ConfigsPage.personalCfgs.stream().filter(x -> x.id == e.id).findFirst().orElse(null);
        if (c == null) return r.append(" FAIL(no card)").toString();
        download(c);
        r.append(" downloads=").append(e.downloads);
        update(c);
        delete(c);
        r.append(" afterDelete=").append(ALL.size() - before).append(" file=").append(Files.exists(file(e)));
        return r.toString();
    }
}
