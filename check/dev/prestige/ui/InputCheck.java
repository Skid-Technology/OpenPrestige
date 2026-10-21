package dev.prestige.ui;

import imgui.*;
import java.io.FileInputStream;
import java.util.List;

/**
 * Headless state-transition check of the popup / gamemode / colour-picker logic (Input owns all state).
 * Run: java -cp <classes>;<imgui jars> dev.prestige.ui.InputCheck <resources/prestige_ui dir>
 * (needs the imgui-java binding + windows natives for text metrics; no window / GL).
 */
public final class InputCheck {
    static int fails, checks;
    static void check(boolean ok, String what) { checks++; if (!ok) { fails++; System.out.println("FAIL: " + what); } }

    static void frames(int n) { for (int i = 0; i < n; i++) { Input.update(1f / 60f); frame(); } }
    static void frame() {
        ImGui.newFrame();
        Lib.dl = ImGui.getForegroundDrawList();
        Ui.frame();
        ImGui.render();
    }
    static void move(float x, float y) { Input.mx = x; Input.my = y; Input.update(1f / 60f); }
    static void click(float x, float y, int btn) { move(x, y); Input.mouseDown(btn); Input.mouseUp(btn); }

    static DetailPage.Setting find(int type) {
        for (DetailPage.Setting s : Input.vis()) if (s.type == type) return s;
        return null;
    }
    static float[] box(DetailPage.Setting s, float w) {
        float[] p = Input.panel();
        int vi = Input.vis().indexOf(s);
        return new float[]{(p[0] + p[2]) - 20f - w + w / 2f, Input.rowY(vi) + 13f + 14f};
    }

    public static void main(String[] a) throws Exception {
        String dir = a[0];
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);
        io.setDisplaySize(1920f, 1009f);
        io.setDeltaTime(1f / 60f);
        String[][] F = {{"DMSans-Medium", "26"}, {"DMSans-Medium", "29"}, {"DMSans-Regular", "20"}, {"DMSans-Regular", "17"}, {"DMSans-Regular", "14"},
            {"DMSans-Medium", "18"}, {"DMSerifDisplay-Regular", "24"}, {"DMSerifDisplay-Italic", "24"}, {"DMSerifDisplay-Regular", "34"},
            {"DMSerifDisplay-Italic", "34"}, {"DMSans-Medium", "20"}};
        for (int i = 0; i < F.length; i++) {
            ImFontConfig cfg = new ImFontConfig();
            Lib.FONT[i] = io.getFonts().addFontFromFileTTF(dir + "/fonts/" + F[i][0] + ".ttf", Float.parseFloat(F[i][1]), cfg);
            cfg.destroy();
        }
        io.getFonts().build();
        Lib.ox = 0; Lib.oy = 0;
        Data.load(new FileInputStream(dir + "/modules.tsv"), new FileInputStream(dir + "/settings.tsv"));
        Ui.initEntries(null);
        Input.active = true;
        Ui.S.page = 0;
        frame(); // prime ImGui frame state
        ImGui.render();

        // ---- gamemode view
        ModulesPage.Mod aim = ModulesPage.P.mods.stream().filter(m -> m.category.equals("Combat")).findFirst().get();
        Input.setGamemodes(aim.name, 1 << 2);                     // Sword
        frames(3);
        float[] gb = Input.panel();
        click(gb[4] + gb[6] / 2, gb[5] + gb[7] / 2, 0);
        check(ModulesPage.P.gamemodes && ModulesPage.P.gamemode == 0, "gamemodes button toggles view");
        check(Input.entries().isEmpty(), "Crystal chip: no module has bit 0");
        frames(60);
        float[][] sr = Input.stripRects();
        click(sr[2][0] + sr[2][2] / 2, sr[2][1] + sr[2][3] / 2, 0);
        check(ModulesPage.P.gamemode == 2, "Sword chip selected");
        check(Input.entries().size() == 1 && Input.mod(Input.entries().get(0)[0], Input.entries().get(0)[1]) == aim, "gamemode mask filters entries");
        frames(60);                                               // draws strip + cards through ModulesPage
        click(gb[4] + gb[6] / 2, gb[5] + gb[7] / 2, 0);
        check(!ModulesPage.P.gamemodes, "gamemodes button toggles back");
        frames(10);

        // ---- open ESP detail (enum, multi, colour rows)
        ModulesPage.Mod esp = ModulesPage.P.mods.stream().filter(m -> m.name.equals("ESP")).findFirst().get();
        Input.openDetail(esp);
        // the tsv carries no options for multi-selects (the live client supplies them): give the row some
        for (int i = 0; i < DetailPage.D.settings.size(); i++) if (DetailPage.D.settings.get(i).type == 7) {
            DetailPage.Setting n = new DetailPage.Setting();
            n.name = DetailPage.D.settings.get(i).name; n.type = 7; n.opts = new String[]{"Players", "Mobs", "Items", "Crystals"};
            DetailPage.D.settings.set(i, n);
        }
        frames(80);
        DetailPage.Setting en = find(6), ms = find(7), cp = find(2);
        check(en != null && ms != null && cp != null, "ESP has enum+multi+colour rows");

        // enum: left click cycles
        String before = en.sval;
        float[] b = box(en, 140f);
        click(b[0], b[1], 0);
        check(!en.sval.equals(before) && !Input.ddOpen, "enum left click cycles, no popup");
        // enum: right click opens, popup shown, hover pick
        move(b[0], b[1]);
        Input.mouseDown(1); Input.mouseUp(1);
        check(Input.ddOpen && Input.ddSetting == en, "enum right click opens list");
        frames(40);
        check(Input.ddShown == en && Input.ddA > 0.9f, "enum popup state visible for drawing");
        float px = Input.panel()[0] + Input.panel()[2] - 160f + 70f, py = Input.popupY(Input.vis().indexOf(en), en.opts.length * 28f) + 2 * 28f + 14f;
        move(px, py);
        check(Input.ddHover == 2, "enum hover over option 2 (" + Input.ddHover + ")");
        Input.mouseDown(0); Input.mouseUp(0);
        check(en.sval.equals(en.opts[2]) && !Input.ddOpen, "enum pick sets value and closes");
        frames(60);
        check(Input.ddShown == null, "enum popup state released after fade");

        // multi: left click opens, click option toggles, outside closes
        b = box(ms, 140f);
        click(b[0], b[1], 0);
        check(Input.msOpen && Input.msSetting == ms, "multi left click opens popup");
        frames(40);
        boolean[] fl = Input.multiFlags(ms);
        boolean f1 = fl[1];
        py = Input.popupY(Input.vis().indexOf(ms), ms.opts.length * 28f) + 28f + 14f;
        click(px, py, 0);
        check(fl[1] != f1 && Input.msOpen, "multi option toggles, popup stays");
        click(300f, 400f, 0);
        check(!Input.msOpen, "click outside closes multi");
        move(b[0], b[1]); Input.mouseDown(1); Input.mouseUp(1);
        check(Input.msOpen, "multi right click opens popup");
        frames(30);
        Input.escape();
        check(!Input.msOpen, "ESC closes multi");
        frames(40);

        // colour: left click opens picker, drag in SV square changes colour, ESC closes
        float[] cs = box(cp, 56f);
        click(cs[0], cs[1], 0);
        check(Input.cpOpen && Input.cpSetting == cp, "colour swatch click opens picker");
        frames(40);
        float[] g = Input.cpGeom(cp);
        float[] oldc = cp.color.clone();
        move(g[0] + 30, g[1] + 40);
        Input.mouseDown(0);
        move(g[0] + 90, g[1] + 100);
        Input.mouseUp(0);
        check(Input.cpOpen && !java.util.Arrays.equals(oldc, cp.color), "SV drag changes colour");
        frames(20);
        Input.escape();
        check(!Input.cpOpen, "ESC closes picker");
        frames(60);
        check(Input.cpShown == null, "picker state released");

        // right click on colour row opens too; only one popup at a time
        move(cs[0], cs[1]); Input.mouseDown(1); Input.mouseUp(1);
        check(Input.cpOpen && !Input.ddOpen && !Input.msOpen, "colour right click opens picker");
        b = box(en, 140f);
        move(b[0], b[1]); Input.mouseDown(1); Input.mouseUp(1);
        check(!Input.cpOpen || Input.ddOpen, "opening enum while picker open: popups are exclusive or click closed first");
        frames(30);

        // search state is Input's
        Input.closeDetail();
        Ui.S.searchFocused = true; Ui.S.search = "esp";
        frames(40);
        check(!Input.results.isEmpty() && Input.searchAlpha > 0.9f, "search results come from Input");

        System.out.println(checks + " checks, " + fails + " failures");
        ImGui.destroyContext();
        if (fails > 0) System.exit(1);
    }
}
