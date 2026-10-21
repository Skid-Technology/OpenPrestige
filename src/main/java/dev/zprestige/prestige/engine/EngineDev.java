package dev.zprestige.prestige.engine;

import dev.zprestige.prestige.ui.DevCmd;
import dev.zprestige.prestige.ui.bridge.Bridge;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.ScreenshotRecorder;

/**
 * Dev/test driver for the game hooks. Inactive unless -Dprestige.engine.cmd=&lt;file&gt; is set (gradle: -PengineCmd=&lt;file&gt;).
 * The file is polled every client tick; its lines are queued and executed one per tick:
 * <pre>
 *   wait &lt;ticks&gt;                      report                     listeners [OwnerSimpleName]
 *   loadtargets                       enable|disable|toggle &lt;module&gt;
 *   set &lt;module&gt;|&lt;setting&gt;|&lt;value&gt;   get &lt;module&gt;|&lt;setting&gt;     modstate &lt;module&gt;
 *   newworld &lt;name&gt;                  world &lt;name&gt;               server &lt;command&gt;   (integrated server command, no cheats needed)
 *   shot &lt;file&gt;                      screen                     exit
 * </pre>
 */
public final class EngineDev implements ClientModInitializer {
    private static final String CMD = System.getProperty("prestige.engine.cmd");
    private static final List<String> QUEUE = new ArrayList<>();
    private static int waitTicks, newWorldStage;
    private static String newWorldName;
    private static long reportEvery = Long.getLong("prestige.engine.report", 0L) * 20L, ticks;

    @Override public void onInitializeClient() {
        if (CMD == null && reportEvery == 0) return;
        ClientTickEvents.END_CLIENT_TICK.register(EngineDev::tick);
        System.out.println("[Engine] dev driver active: " + CMD);
    }

    private static void log(String s) { System.out.println("[Engine] " + s); }

    static void tick(MinecraftClient mc) {
        try {
            ticks++;
            if (reportEvery > 0 && ticks % reportEvery == 0) log(EngineState.details());
            if (CMD == null) return;
            Path p = Path.of(CMD);
            if (Files.exists(p)) { QUEUE.addAll(Files.readAllLines(p)); Files.delete(p); }
            if (newWorldStage > 0) { newWorld(mc); return; }
            if (waitTicks > 0) { waitTicks--; return; }
            if (QUEUE.isEmpty()) return;
            String l = QUEUE.remove(0).trim();
            if (l.isEmpty() || l.startsWith("#")) return;
            run(mc, l);
        } catch (Throwable t) {
            log("command failed: " + t);
            t.printStackTrace(System.out);
        }
    }

    private static void run(MinecraftClient mc, String line) throws Exception {
        String cmd = line.contains(" ") ? line.substring(0, line.indexOf(' ')) : line;
        String arg = line.length() > cmd.length() ? line.substring(cmd.length() + 1).trim() : "";
        switch (cmd) {
            case "wait" -> waitTicks = Integer.parseInt(arg);
            case "report" -> log(EngineState.details());
            case "listeners" -> {
                String text = DevCmd.listeners();
                if (!arg.isEmpty()) {
                    StringBuilder sb = new StringBuilder("LISTENERS owned by " + arg + ":");
                    for (String s : text.split("\n")) if (s.contains(arg) && s.startsWith("  ")) sb.append("\n").append(s);
                    text = sb.toString();
                }
                log(text);
            }
            case "loadtargets" -> {
                ClassLoader cl = EngineDev.class.getClassLoader();
                List<String> missing = new ArrayList<>();
                var in = EngineDev.class.getClassLoader().getResourceAsStream("prestige-engine/targets.named.list");
                for (String t : new String(in.readAllBytes()).split("\\R")) {
                    if (t.isBlank()) continue;
                    try { Class.forName(t.trim(), false, cl); } catch (Throwable e) { missing.add(t + " (" + e + ")"); }
                }
                log("loadtargets: transformed=" + EngineState.transformed.size() + " failed=" + missing);
            }
            case "enable", "disable", "toggle" -> {
                Bridge.LiveModule m = Bridge.byName(arg);
                if (m == null) { log("no such module: " + arg); return; }
                boolean target = cmd.equals("toggle") ? !m.enabled() : cmd.equals("enable");
                m.setEnabled(target);
                log("module " + m.name + " enabled=" + m.enabled());
            }
            case "set" -> {
                String[] a = arg.split("\\|", 3);
                Bridge.LiveModule m = Bridge.byName(a[0]);
                Bridge.LiveSetting s = m == null ? null : m.setting(a[1]);
                if (s == null) { log("no such setting: " + arg); return; }
                Object cur = s.value(), v;
                if (cur instanceof Boolean) v = Boolean.parseBoolean(a[2]);
                else if (cur instanceof Integer) v = Integer.parseInt(a[2]);
                else if (cur instanceof Float) v = Float.parseFloat(a[2]);
                else if (cur instanceof Double) v = Double.parseDouble(a[2]);
                else v = a[2];
                s.set(v);
                log("set " + a[0] + "." + a[1] + " " + cur + " -> " + s.value() + " (" + s.type + ")");
            }
            case "get" -> {
                String[] a = arg.split("\\|", 2);
                Bridge.LiveModule m = Bridge.byName(a[0]);
                if (m == null) { log("no such module: " + a[0]); return; }
                if (a.length == 1) {
                    StringBuilder sb = new StringBuilder(m.name + " enabled=" + m.enabled() + " settings:");
                    for (Bridge.LiveSetting s : m.settings()) sb.append("\n    ").append(s.name).append(" = ").append(s.value()).append("  [").append(s.type).append("]");
                    log(sb.toString());
                } else {
                    Bridge.LiveSetting s = m.setting(a[1]);
                    log(a[0] + "." + a[1] + " = " + (s == null ? "<none>" : s.value()));
                }
            }
            case "modstate" -> {
                Bridge.LiveModule m = Bridge.byName(arg);
                if (m == null) { log("no such module: " + arg); return; }
                String owner = m.handle.getClass().getSimpleName();
                int n = 0;
                for (String s : DevCmd.listeners().split("\n")) if (s.startsWith("  ") && s.contains(owner)) n++;
                log("modstate " + m.name + " class=" + owner + " enabled=" + m.enabled() + " eventTypesWithListener=" + n
                        + " world=" + (mc.world != null));
            }
            case "server" -> {
                var server = mc.getServer();
                if (server == null) { log("no integrated server"); return; }
                server.execute(() -> server.getCommandManager().parseAndExecute(server.getCommandSource(), arg));
                log("server command: " + arg);
            }
            case "newworld" -> { newWorldName = arg.isEmpty() ? "engine-test" : arg; newWorldStage = 1; }
            case "world" -> {
                mc.createIntegratedServerLoader().start(arg, () -> log("world load cancelled/failed"));
                log("loading world " + arg);
            }
            case "screen" -> log("screen=" + (mc.currentScreen == null ? null : mc.currentScreen.getClass().getName()) + " world=" + (mc.world != null)
                    + " player=" + (mc.player != null));
            case "shot" -> {
                Path out = Path.of(arg).toAbsolutePath();
                Files.createDirectories(out.getParent());
                ScreenshotRecorder.takeScreenshot(mc.getFramebuffer(), img -> {
                    try { img.writeTo(out); log("shot written " + out); } catch (Exception e) { log("shot failed " + e); } finally { img.close(); }
                });
            }
            case "exit" -> { log("final: " + EngineState.details()); mc.scheduleStop(); }
            default -> log("unknown command: " + line);
        }
    }

    /** Creates a world through the vanilla Create World screen (default settings) and waits until the player exists. */
    private static void newWorld(MinecraftClient mc) {
        Screen s = mc.currentScreen;
        switch (newWorldStage) {
            case 1 -> { CreateWorldScreen.show(mc, () -> {}); newWorldStage = 2; waitTicks = 0; newWorldDelay = 40; }
            case 2 -> {
                if (newWorldDelay-- > 0) return;
                if (s instanceof CreateWorldScreen) {
                    for (var c : s.children()) {
                        if (c instanceof ButtonWidget b && b.getMessage().getString().toLowerCase().contains("create new world")) {
                            try { ((net.minecraft.client.gui.widget.TextFieldWidget) findName(s)).setText(newWorldName); } catch (Throwable t) { log("name field: " + t); }
                            b.onPress(null);
                            log("create world pressed: " + newWorldName);
                            newWorldStage = 3;
                            newWorldDelay = 20 * 120;
                            return;
                        }
                    }
                    log("create button not found; screen children=" + s.children().size());
                    newWorldStage = 0;
                } else { log("expected CreateWorldScreen, got " + (s == null ? null : s.getClass().getName())); newWorldStage = 0; }
            }
            case 3 -> {
                if (mc.world != null && mc.player != null) { log("world ready (player in world)"); newWorldStage = 0; }
                else if (newWorldDelay-- < 0) { log("world creation timed out"); newWorldStage = 0; }
            }
            default -> newWorldStage = 0;
        }
    }

    private static int newWorldDelay;

    private static Object findName(Screen s) {
        // the world name field is the first TextFieldWidget in the tree of the create-world screen
        List<Object> todo = new ArrayList<>(s.children());
        for (int i = 0; i < todo.size(); i++) {
            Object o = todo.get(i);
            if (o instanceof net.minecraft.client.gui.widget.TextFieldWidget t) return t;
            if (o instanceof net.minecraft.client.gui.ParentElement pe) todo.addAll(pe.children());
        }
        throw new IllegalStateException("world name field not found");
    }
}
