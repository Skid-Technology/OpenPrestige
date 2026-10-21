package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.Locale;

/**
 * Port of sub_180131B70 (the ClickGUI render function). Structure and constants follow the Hex-Rays output
 * line by line; animation globals live in {@link State}. Slices are added and verified against captured frames.
 */
public final class Ui {
    private Ui() {}

    /** One sidebar entry (element of qword_180744C30 / qword_180744B80). */
    public static final class Entry {
        public String name; public boolean selected; public long icon; public int count = -1;
        public float hover, sel1, sel2;           // qword_180744C98[idx] animation triplet
        public Entry(String n, long icon, int count) { this.name = n; this.icon = icon; this.count = count; }
    }

    /** Animation / UI globals (names = IDA data names). */
    public static final class State {
        public float dt = 1f / 60f;               // flt_1804CCE14
        public float collapse;                    // dword_1807089C0 (0 = expanded sidebar)
        public float sideW = 230f;                // flt_1804CCE68
        public float flagGlow;                    // dword_1807089D8
        public float time;                        // flt_180708978 (seconds)
        public long logoTex;                      // xmmword_180708980 (logo texture)
        public String version = "4.4.0";          // xmmword_180745660
        public boolean beta;                      // HIBYTE(word_180745680)
        public float searchFocus;                 // dword_1807453AC
        public boolean searchFocused;             // byte_180708A4C
        public String search = "";                // byte_1806FC300
        public int page;                          // dword_180708A30
        public Entry[] cats;                      // qword_180744C30
        public Entry[] general;                   // qword_180744B80
    }
    public static final State S = new State();
    public static final String[] CATS = {"Combat", "Mace", "Misc", "Movement", "Spear", "Visual"};

    /** Default sidebar entries; icons = texture ids {logo, 6 categories, 5 general}. */
    public static void initEntries(long[] icons) {
        String[] gn = {"Settings", "Theme", "Configs", "Socials", "Keybinds"};
        S.cats = new Entry[CATS.length];
        for (int i = 0; i < CATS.length; i++) {
            int c = 0;
            for (ModulesPage.Mod m : ModulesPage.P.mods) if (m.category.equals(CATS[i])) c++;
            S.cats[i] = new Entry(CATS[i], icons == null ? 0 : icons[1 + i], c);
        }
        S.general = new Entry[5];
        for (int i = 0; i < 5; i++) S.general[i] = new Entry(gn[i], icons == null ? 0 : icons[7 + i], -1);
        S.logoTex = icons == null ? 0 : icons[0];
    }

    public static void frame() {
        State s = S;
        // --- animation updates (steady state in the verification slice) ---
        s.sideW = lerp(230f, 64f, easeInOutCubic(s.collapse));

        // main panel background: sub_18011A490(0,0,1100,700,25,E18E0)
        fillRect(0f, 0f, 1100f, 700f, 25f, Theme.bg());
        Glow.draw(s.time);
        float v27 = s.sideW;
        float t = Math.max(0f, 1f - Math.min(1f, s.collapse * 1.8f));
        float textA = Math.max(0f, t);                      // v28
        for (int k = 0; k < 3; k++) {                       // soft shadow layers around the sidebar
            float e = k * 4f + 2f;
            fillRect(14f - e, (k + k + 4f) + 14f, (e + e) + v27, e + 672f, e + 16f, new float[]{0f, 0f, 0f, 0.12f - k * 0.035f});
        }
        fillRect(14f, 14f, v27, 672f, 16f, Theme.sidebar());
        strokeRect(14f, 14f, v27, 672f, 16f, 1f, Theme.border());
        float v30 = lerp(26f, (s.sideW * 0.5f + 14f) - 15f, s.collapse);
        if (s.flagGlow > 0.005f) {
            float[] c = Theme.e3750();
            fillRect(18f, 18f, v27 - 8f, 42f, 10f, new float[]{c[0], c[1], c[2], s.flagGlow * 0.55f * c[3]});
        }
        if (s.logoTex != 0) image(s.logoTex, v30, 25f, 30f, 30f, Theme.accent());
        if (textA > 0.005f) {
            float v33 = v30 + 30f + 8f;
            text("Prestige", v33, 26f, 6, withAlpha(Theme.text(), textA));
            float[] ps = textSize("Prestige", 6);
            text(" Client", ps[0] + v33, 26f, 7, withAlpha(Theme.secondary(), textA));
            String rel = s.beta ? "BETA RELEASE" : "RELEASE";
            if (!s.version.isEmpty()) rel = rel + " " + s.version;
            textEllipsis(rel, v33, ps[1] + 26f - 2f, ((s.sideW + 14f) - 12f) - v33, 4, withAlpha(Theme.e38F0(), textA));
            textBox("MODULES", 38f, 95f, s.sideW - 48f, 17f, 4, withAlpha(Theme.e38F0(), textA), false, false);
        }
        float y = 130f;
        for (int m = 0; m < s.cats.length; m++) {
            y = entryRow(s.cats[m], y, 18f, 11f, 44f, textA, true, v27);
        }
        float v876 = y + 5f;
        float x0 = s.sideW * 0.12f + 14f, x1 = s.sideW * 0.88f + 14f, span = x1 - x0;
        float[] bc = Theme.border();
        for (int i = 0; i < 24; ) {
            float t0 = (float) i++ / 24f, t1 = (float) i / 24f;
            float fade = Math.abs((t1 + t0) * 0.5f - 0.5f);
            line(span * t0 + x0, v876 + 10f, span * t1 + x0, v876 + 10f, 1f, new float[]{bc[0], bc[1], bc[2], bc[3] * (1f - (fade + fade))});
        }
        float v74 = v876 + 30f;
        if (textA > 0.005f) textBox("GENERAL", 38f, v74, s.sideW - 48f, 18f, 4, withAlpha(Theme.e38F0(), textA), false, false);
        y = v74 + 30f;
        for (int n = 0; n < s.general.length; n++) {
            y = entryRow(s.general[n], y, 20f, 10f, 44f, textA, false, v27);
        }
        if (s.page < 6) { WidgetsPage.searchBox(); ModulesPage.draw(CATS); }
        else if (s.page == 7) ThemesPage.draw();
        else if (s.page == 6) SettingsPage.draw();
        else if (s.page == 8) ConfigsPage.draw();
        else if (s.page == 9) SocialsPage.draw();
        else if (s.page == 10) KeybindsPage.draw();
        WidgetsPage.draw();   // popups / overlays drawn last (dropdowns, colour picker, tooltips)
    }

    /** Sidebar row (modules: icon 18px, general: icon 20px). Returns next y. */
    private static float entryRow(Entry e, float y, float iconSize, float iconOff, float step, float textA, boolean withCount, float sideW) {
        float v44 = e.hover * 3f + 26f, v45 = sideW - 24f;
        if (e.sel1 > 0.01f || e.hover > 0.01f) {
            float v46 = Math.max(e.hover * 0.55f, e.sel1);
            float[] c = lerp(Theme.e2080(), Theme.e3750(), e.sel1);
            c = new float[]{c[0], c[1], c[2], c[3] * v46};
            rect(v44, y, v45, 40f, 10f, true, c);
            if (e.sel1 > 0.05f) {
                float[] b = Theme.border();
                rect(v44, y, v45, 40f, 10f, false, new float[]{b[0], b[1], b[2], b[3] * e.sel1});
            }
        }
        float v50 = (e.hover * 0.1f + 1f) * iconSize;
        boolean on = e.selected || e.hover > 0.5f;
        float ix = (v44 + iconOff) - ((v50 - iconSize) * 0.5f);
        float iy = (y + iconOff) - ((v50 - iconSize) * 0.5f);
        // sub_18011E1C0: icon tinted accent (on) / muted (off)
        float[] ic = on ? Theme.accent() : Theme.muted();
        if (e.icon != 0) image(e.icon, ix, iy, v50, v50, ic);
        else rect(ix, iy, v50, v50, 4f, true, ic);
        float tm = Math.max(e.sel1, e.hover);
        float[] lc = lerp(Theme.muted(), Theme.text(), tm);
        if (textA > 0.005f) {
            textBoxEllipsis(e.name, v44 + 37f, y + 11f, (v45 - 37f) - (withCount ? 42f : 12f), 18f, 3, withAlpha(lc, textA), false, false);
            if (withCount && e.count >= 0) {
                String cs = String.format(Locale.ROOT, "%d", e.count);
                float[] sz = textSize(cs, 4);
                float cx = ((v45 + v44) - sz[0]) - 14f, cy = ((40f - sz[1]) * 0.5f) + y;
                float v63 = Math.max(e.sel1, e.hover * 0.5f);
                text(cs, cx, cy, 4, withAlpha(lerp(Theme.e38F0(), Theme.muted(), v63), textA));
            }
        }
        return y + step;
    }

    private static void searchBox(float textA) {
        State s = S;
        rect(760f, 42f, 310f, 36f, 8f, true, Theme.e31C0());
        float v98 = s.searchFocus * 0.6f;
        rect(760f, 42f, 310f, 36f, 8f, false, lerp(Theme.border(), Theme.accent(), v98));
        float v99 = s.searchFocus;
        searchIcon(774f, 51f, 18f, lerp(Theme.muted(), Theme.accent(), v99 * 0.8f));
        if (s.search.isEmpty()) {
            textBox("Search modules", 800f, 51f, 250f, 18f, 3, Theme.muted(), false, false);
        } else {
            textBoxEllipsis(s.search, 800f, 51f, 250f, 18f, 3, Theme.text(), false, false);
        }
    }

    /** sub_18011E540 (steady, no focus pulse). */
    static void searchIcon(float x, float y, float size, float[] c) {
        float v10 = size * 0.35f;
        float cx = v10 + x + 2f, cy = v10 + y + 2f;
        float r = 1f * v10;
        if (dl != null && (col(c) & 0xFF000000) != 0 && r >= 0.5f) dl.addCircle(ox + cx * scale, oy + cy * scale, r * scale, col(c), 0, 1.5f * scale);
        line(r * 0.7f + cx, r * 0.7f + cy, (x + size) - 2f, (y + size) - 2f, 1.5f, c);
    }
}
