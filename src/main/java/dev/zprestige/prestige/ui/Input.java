package dev.zprestige.prestige.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input layer: port of the original Windows message handler chain
 *   sub_1800DE010 (WndProc hook: mouse msgs get window-local coords, keys/wheel raw)  ->
 *   sub_180145320 (the big handler: WM_MOUSEMOVE hover pass, WM_LBUTTONDOWN/UP, WM_RBUTTONDOWN,
 *                  WM_MBUTTONDOWN, WM_XBUTTONDOWN, WM_MOUSEWHEEL, WM_KEYDOWN, WM_CHAR)
 * plus the per-frame state updates found at the top of the render function sub_180131B70.
 *
 * Helper functions ported inline (original names in comments):
 *   sub_18012D370 entries(), sub_18012DA40 layout(), sub_18012E5B0 panel geometry, sub_18012E980 rowY(),
 *   sub_18012EB60 popupY(), sub_18012D600 gamemode button/strip rects, sub_18012E020 view toggle rect,
 *   sub_180131960/131610 search popup rect / hit test, sub_18012FBA0 search result builder,
 *   sub_180131070 activateResult(), sub_18012E300 openDetail(), sub_18012E3A0 closeDetail(),
 *   sub_180122B30 keybind row geometry, sub_1800E04F0 / sub_1800E0620 hsv<->rgb, sub_1800E0830 hex parse.
 *
 * Event semantics follow the original: hover indices are only recomputed on a mouse-move (here: when mx/my
 * changed since the last processed position, detected in update() and at the start of every event).
 * Coordinates are canvas coordinates (1100x700 space), truncated to int like the WM_ lParam.
 *
 * Globals -> fields: see the table in the final report; each field carries its IDA name in a comment.
 */
public final class Input {
    private Input() {}

    // ------------------------------------------------------------------ public API (unchanged)
    public static float mx = -10000, my = -10000;   // qword_180708A40 (as float, truncated to int when processed)
    public static boolean down;                      // byte_18070897D (left button down)
    /** Set when the menu should close (ESC with nothing left to close / menu key): sub_1800DB680 toggling byte_1804CCE11. */
    public static boolean close;

    // ------------------------------------------------------------------ extra public state / hooks
    /** byte_1806FD750: input enabled (ClickGUI visible). */
    public static boolean active = true;
    /** dword_1804CCE84: menu key (VK), default 161 = RSHIFT. */
    public static int menuKey = 161;
    /** byte_180708A08: next key press becomes the menu key (set by the Settings page pill). */
    public static boolean capturingMenuKey;
    /** GetKeyState(VK_CONTROL) < 0 (host should keep this up to date). */
    public static boolean ctrlDown;
    public static java.util.function.Supplier<String> clipboard = () -> null;
    public static java.util.function.LongSupplier clock = System::currentTimeMillis;

    /** Sound ("pop", "pop2", "pop3", "hover", "dropdown", "woosh", "popup") and dirty notifications (sub_1800E86D0). */
    public interface Listener {
        default void sound(String name) {}
        /** sub_1800E86D0(page, module, setting): -1 means "none". setting index here = index in DetailPage.D.settings. */
        default void settingChanged(int cat, int mod, int setting) {}
        /** sub_1800DF800 toast text ("Assign a key first", ...). */
        default void toast(String msg) {}
    }
    public static Listener listener = new Listener() {
        @Override public void toast(String msg) { WidgetsPage.toast(msg); }
    };

    /** Gamemode membership (module +160 bitmask), keyed by module name; not part of modules.tsv. Bit n = Crystal,Mace,Sword,Spear,UHC,Cart. */
    public static final Map<String, Integer> gamemodeMask = new HashMap<>();
    /** Fill from outside (client): module name -> bitmask, bit n = Crystal, Mace, Sword, Spear, UHC, Cart. */
    public static void setGamemodes(String module, int mask) { gamemodeMask.put(module, mask); }
    public static void setGamemodes(Map<String, Integer> masks) { gamemodeMask.putAll(masks); }

    // ---- detail panel / open module (byte_1807089DE, dword_1804CCE8C / E38, flt_180708A04, dword_1807089C4)
    static boolean detailOpen;
    static int openCat = -1, openIdx = -1;
    static float scrollTarget;            // flt_18070899C  (dword_1807089CC = ModulesPage.P.scroll)
    static float detailScrollTarget;      // flt_180708A04  (dword_1807089C4 = DetailPage.D.scroll)
    static float themeScrollTarget;       // 'target'       (cur = ThemesPage.scroll)

    // ---- hover (all written by the mouse-move pass)
    public static int hoverSidebar = -1;  // dword_1804CCE9C (0..5 categories, 100+i general)
    public static boolean hoverHeader;    // byte_18070871E (logo area)
    public static boolean hoverGmBtn;     // byte_180708A20
    public static int hoverMode = -1;     // dword_1804CCE80 (gamemode strip item)
    public static int hoverView = -1;     // dword_1804CCEC4
    public static boolean hoverBack;      // byte_18070871C
    public static float backHoverA;       // dword_180708A1C

    // ---- gamemode strip (byte_180708A4F -> P.gamemodes, dword_180708A00 -> P.gamemode)
    public static final float[] modeSel = new float[6];    // qword_180744CB8
    public static final float[] modeHover = new float[6];  // qword_180744C48
    public static int gmClicks;                             // dword_180708A2C

    // ---- search (byte_180708A4C -> Ui.S.searchFocused, byte_1806FC300 -> Ui.S.search, dword_1807453AC -> Ui.S.searchFocus)
    public static final class Result {
        public int type;          // 0 module, 1 configs sub page, 2 page/category
        public String title, label;
        public int page, idx, sub; // +72, +76, +80
    }
    public static final List<Result> results = new ArrayList<>(); // qword_1807452C8
    public static int searchSel = -1;        // dword_1804CCE40
    public static float searchAlpha;         // dword_180708A38
    public static float searchScrollCur, searchScrollTarget; // dword_1807089A4 / flt_1807089F8
    public static float searchPopupH = 120;  // dword_1806FD72C
    public static float caretT;              // dword_1807453A8
    public static boolean caretOn;           // byte_1806FC144

    // ---- popups (names of the original flags)
    public static boolean ddOpen;  public static DetailPage.Setting ddSetting; public static int ddHover = -1;   // 897C, E50/E98, EB0
    public static float ddA, ddB;                                                                              // 180708A60 / A64
    public static boolean msOpen;  public static DetailPage.Setting msSetting; public static int msHover = -1;   // 893D, E18/E74, E94
    public static float msA, msB;                                                                              // 1807089D0 / D4
    private static final Map<DetailPage.Setting, boolean[]> MULTI = new java.util.WeakHashMap<>();            // option flags (+184)
    public static boolean cpOpen;  public static DetailPage.Setting cpSetting;                                 // A22, EB8/E60
    public static boolean cpDragSV, cpDragHue, cpDragAlpha, cpHexEdit;                                         // A21, AF, DC, A0B
    public static float cpHue, cpSat, cpVal, cpAlpha = 1f;                                                     // A3C, E7C, EBC, E2C
    public static final StringBuilder cpHex = new StringBuilder();                                             // qword_1806FC2C0
    public static float cpA, cpB;                                                                              // 180708A58 / A5C
    /** Setting whose popup is being drawn (kept after close until the close animation ends) - read by WidgetsPage.draw only. */
    public static DetailPage.Setting ddShown, msShown, cpShown;

    // ---- slider / range drag (E28/E3C, 89DF, 871D)
    public static DetailPage.Setting dragSetting;
    public static boolean rangeDragLo, rangeDragHi;
    static long lastSliderSound;             // dword_18074559C

    // ---- keybind capture (893F, E30/E64, A10, 9AD)
    public static boolean capturing; public static DetailPage.Setting capSetting;
    static int capPrevKey; static boolean capPrevToggle;

    // ---- text field edit (A09, E88/E4C, 2A0)
    public static boolean textEdit; public static DetailPage.Setting textSetting;
    public static final StringBuilder textBuf = new StringBuilder();

    // ---- misc
    public static boolean windowDragging;     // byte_18070893C
    public static boolean sidebarCollapsed;   // byte_1807089DD
    public static boolean enterAnimating;     // byte_1804CCEAC (enter anim of the page, qword_1804CCEA0 = P.enterA/B)
    public static int prevPage;               // dword_1804CCEA8
    public static int configsSubPage;         // dword_180708A14 (set by search result type 1)
    public static int themePrev = -1;         // dword_1804CCED8
    public static float themeFade = 1f;       // dword_1804CCEE0
    public static boolean themeFading;        // byte_1804CCEE4

    private static float X = -10000, Y = -10000;
    private static int lastMx = Integer.MIN_VALUE, lastMy = Integer.MIN_VALUE;

    // ============================================================== helpers
    static void snd(String n) { listener.sound(n); }
    static boolean incl(float x, float y, float w, float h) { return X >= x && x + w >= X && Y >= y && y + h >= Y; }
    static float clamp01(float v) { return v < 0f ? 0f : Math.min(v, 1f); }
    static float sideW() { return Ui.S.sideW; }
    static float px() { return sideW() + 30f; }
    static float pw() { return (1100f - sideW()) - 60f; }
    static float tw(String s, int font) { return Lib.textSize(s, font)[0]; }

    /** WM_ lParam coordinate conversion: unsigned 16 bit of the int part. */
    private static float toCoord(float v) { int i = (int) v; return (float) (i < 0 ? (i & 0xFFFF) : i); }

    private static void syncMouse() {
        X = toCoord(mx); Y = toCoord(my);
        int ix = (int) X, iy = (int) Y;
        ModulesPage.P.mx = ix; ModulesPage.P.my = iy;
        if (ix != lastMx || iy != lastMy) { lastMx = ix; lastMy = iy; if (active) onMouseMove(); }
    }

    // ---- model access
    static List<ModulesPage.Mod> modsOf(int cat) {
        List<ModulesPage.Mod> out = new ArrayList<>();
        if (cat < 0 || cat >= Ui.CATS.length) return out;
        for (ModulesPage.Mod m : ModulesPage.P.mods) if (m.category.equals(Ui.CATS[cat])) out.add(m);
        return out;
    }
    static ModulesPage.Mod mod(int cat, int idx) { List<ModulesPage.Mod> l = modsOf(cat); return idx >= 0 && idx < l.size() ? l.get(idx) : null; }
    static int catOf(ModulesPage.Mod m) { for (int i = 0; i < Ui.CATS.length; i++) if (Ui.CATS[i].equals(m.category)) return i; return -1; }
    static int modIdx(ModulesPage.Mod m) { return modsOf(catOf(m)).indexOf(m); }
    static boolean isEnabledName(DetailPage.Setting s) { return "Enabled".equals(s.name); }
    /** Rows shown in the panel (visible, not "Enabled"): original iterates the full vector with the same filter. */
    static List<DetailPage.Setting> vis() {
        List<DetailPage.Setting> o = new ArrayList<>();
        for (DetailPage.Setting s : DetailPage.D.settings) if (s.visible && !isEnabledName(s)) o.add(s);
        return o;
    }
    static int settingIndex(DetailPage.Setting s) { return DetailPage.D.settings.indexOf(s); }
    static void dirty(DetailPage.Setting s) {
        Data.push(s);
        listener.settingChanged(openCat, openIdx, s == null ? -1 : settingIndex(s));
    }
    static boolean sdet() { return detailOpen && openCat >= 0; }
    public static boolean[] multiFlags(DetailPage.Setting s) {
        return MULTI.computeIfAbsent(s, k -> new boolean[Math.max(0, k.opts.length)]);
    }

    // ============================================================== geometry
    /** sub_18012E5B0: [0..3] panel x,y,w,h; [4..7] back/gamemode button; [8..11] titleY,descY,lineY,listTop; [12] total; [13] max scroll. */
    static float[] panel() {
        float x = px(), w = pw(), y = 152f, h = 533f;
        float bw = tw("Gamemodes", 3) + 28f;
        float titleY = y + 20f, descY = titleY + 32f, lineY = descY + 40f, listTop = lineY + 8f;
        float n = 0f;
        for (DetailPage.Setting s : DetailPage.D.settings) if (s.visible && !isEnabledName(s)) n += 54f;
        float total = (n + 14f) + 30f;
        float mx_ = total - ((y + h) - listTop);
        return new float[]{x, y, w, h, x + 4f, (36f - 24f) * 0.5f + 98f, bw, 24f, titleY, descY, lineY, listTop, total, mx_ >= 0f ? mx_ : 0f};
    }
    /** sub_18012E980: y of visible row i. */
    static float rowY(int visIdx) { float[] p = panel(); return (p[11] + 14f) - DetailPage.D.scroll + visIdx * 54f; }
    /** sub_18012EB60: popup top for a row. */
    static float popupY(int visIdx, float popH) {
        float[] p = panel();
        float ry = rowY(visIdx);
        if (p[1] + p[3] < (ry + 54f) + popH) return Math.max(p[11], (ry - popH) - 6f);
        return ry + 54f;
    }
    /** sub_18012E020 view toggle rect (x, y, 34, 24). */
    static float[] viewRect() { return new float[]{(px() + pw()) - 68f - 4f, (36f - 24f) * 0.5f + 98f, 34f, 24f}; }
    /** sub_18012D600 strip rects: per mode {x,y,w,h}. */
    static float[][] stripRects() {
        float bx = px() + 4f, bw = tw("Gamemodes", 3) + 28f;
        float x = (bw + bx) + 12f, y = (36f - 22f) * 0.5f + 98f;
        float[][] r = new float[ModulesPage.GAMEMODES.length][];
        for (int i = 0; i < r.length; i++) {
            float w = tw(ModulesPage.GAMEMODES[i], 4) + 18f;
            r[i] = new float[]{x, y, w, 22f};
            x += w + 5f;
        }
        return r;
    }

    /** sub_18012D370: visible entries {cat, idx}. */
    static List<int[]> entries() {
        List<int[]> o = new ArrayList<>();
        ModulesPage.St p = ModulesPage.P;
        if (p.gamemodes) {
            int bit = 1 << p.gamemode;
            for (int c = 0; c < Ui.CATS.length; c++) {
                List<ModulesPage.Mod> l = modsOf(c);
                for (int m = 0; m < l.size(); m++) if ((gamemodeMask.getOrDefault(l.get(m).name, 0) & bit) != 0) o.add(new int[]{c, m});
            }
        } else {
            int c = Ui.S.page;
            if (c >= 0 && c < Ui.CATS.length) for (int m = 0; m < modsOf(c).size(); m++) o.add(new int[]{c, m});
        }
        return o;
    }

    static final class Layout { float[][] r; float[] hdr; float total, max; }
    /** sub_18012DA40 */
    static Layout layout(List<int[]> e) {
        Layout L = new Layout();
        int n = e.size();
        L.r = new float[n][]; L.hdr = new float[n];
        java.util.Arrays.fill(L.hdr, -1f);
        float v11 = px(), v12 = pw(), v13 = ModulesPage.P.grid ? 120f : 75f, v14 = ModulesPage.P.scroll, v15 = 152f - v14;
        boolean gm = ModulesPage.P.gamemodes;
        if (ModulesPage.P.grid) {
            float v19 = (v12 - 20f) / 3f;
            int i = 0;
            while (i < n) {
                if (gm && (i == 0 || e.get(i - 1)[0] != e.get(i)[0])) { L.hdr[i] = v15; v15 += 30f; }
                int j = i + 1, cnt = 1;
                while (j < n) {
                    if (cnt >= 3 || (gm && e.get(j - 1)[0] != e.get(j)[0])) break;
                    j++; cnt++;
                }
                for (int k = i; k < j; k++) L.r[k] = new float[]{(k - i) * (v19 + 10f) + v11, v15, v19, v13};
                v15 += v13 + 10f;
                i = j;
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (gm && (i == 0 || e.get(i - 1)[0] != e.get(i)[0])) { L.hdr[i] = v15; v15 += 30f; }
                L.r[i] = new float[]{v11, v15, v12, v13};
                v15 += v13 + 10f;
            }
        }
        L.total = (v15 + v14) - 152f;
        float m = L.total - 528f;
        L.max = m >= 0f ? m : 0f;
        return L;
    }
    static Layout layout() { return layout(entries()); }

    // ============================================================== per-frame update (top of sub_180131B70 + hover anims)
    public static void update(float dt) {
        Ui.State s = Ui.S;
        ModulesPage.St p = ModulesPage.P;
        s.dt = dt;
        syncMouse();
        p.mouseDown = down;
        s.time += dt;
        if (s.page >= 0 && s.page < 6) p.cat = s.page;
        // live client may toggle modules itself (keybind); mirror the running state
        if (dev.zprestige.prestige.ui.bridge.Bridge.available())
            for (ModulesPage.Mod m : p.mods) if (m.live != null) m.enabled = m.live.enabled();

        // theme colour fade (dword_1804CCEE0 / byte_1804CCEE4)
        if (themeFading) { themeFade += dt; if (themeFade >= 1f) { themeFade = 1f; themeFading = false; } }
        // scrolls
        ThemesPage.scroll = Lib.smooth(ThemesPage.scroll, themeScrollTarget, dt, 12f);   // 'result' <- flt_1807089FC
        p.scroll = Lib.smooth(p.scroll, scrollTarget, dt, 12f);                          // dword_1807089CC <- flt_18070899C
        // page enter animation (qword_1804CCEA0)
        if (enterAnimating) {
            p.enterA += dt * 0.6f; p.enterB += dt * 0.6f;
            if (p.enterA >= 1f && p.enterB >= 1f) { p.enterA = 1f; p.enterB = 1f; enterAnimating = false; }
        }
        ThemesPage.enterB = p.enterB;
        // detail panel open amount (dword_180708A28)
        p.detailT = Lib.smooth(p.detailT, detailOpen ? 1f : 0f, dt, 7f);
        if (!detailOpen && p.detailT < 0.01f) { p.detailT = 0f; openCat = -1; openIdx = -1; }
        DetailPage.D.scroll = Lib.smooth(DetailPage.D.scroll, detailScrollTarget, dt, 12f);   // dword_1807089C4
        backHoverA = Lib.smooth(backHoverA, hoverBack ? 1f : 0f, dt, 16f);                     // dword_180708A1C
        if (openCat >= 0 && DetailPage.D.mod != null) {
            float mxs = panel()[13];
            detailScrollTarget = clampTo(detailScrollTarget, mxs);
            DetailPage.D.scroll = clampTo(DetailPage.D.scroll, mxs);
        }
        ddA = Lib.smooth(ddA, ddOpen ? 1f : 0f, dt, 18f); ddB = Lib.smooth(ddB, ddOpen ? 1f : 0f, dt, 14f);
        msA = Lib.smooth(msA, msOpen ? 1f : 0f, dt, 18f); msB = Lib.smooth(msB, msOpen ? 1f : 0f, dt, 14f);
        cpA = Lib.smooth(cpA, cpOpen ? 1f : 0f, dt, 18f); cpB = Lib.smooth(cpB, cpOpen ? 1f : 0f, dt, 14f);
        if (ddOpen && ddSetting != null) ddShown = ddSetting; else if (ddA < 0.01f) ddShown = null;
        if (msOpen && msSetting != null) msShown = msSetting; else if (msA < 0.01f) msShown = null;
        if (cpOpen && cpSetting != null) cpShown = cpSetting; else if (cpA < 0.01f) cpShown = null;

        // sidebar rows: hover / selected triplets (qword_180744C98), t = dt*12 unclamped lerp
        for (int i = 0; i < s.cats.length; i++) {
            Ui.Entry e = s.cats[i];
            e.selected = s.page == i;
            e.hover = Lib.lerp(e.hover, hoverSidebar == i ? 1f : 0f, dt * 12f);
            e.sel1 = Lib.lerp(e.sel1, e.selected ? 1f : 0f, dt * 12f);
            e.sel2 = Lib.lerp(e.sel2, e.selected ? 1f : 0f, dt * 12f);
        }
        for (int i = 0; i < s.general.length; i++) {
            Ui.Entry e = s.general[i];
            e.selected = s.page == 6 + i;
            e.hover = Lib.lerp(e.hover, hoverSidebar == 100 + i ? 1f : 0f, dt * 12f);
            e.sel1 = Lib.lerp(e.sel1, e.selected ? 1f : 0f, dt * 12f);
            e.sel2 = Lib.lerp(e.sel2, e.selected ? 1f : 0f, dt * 12f);
        }
        s.collapse = Lib.smooth(s.collapse, sidebarCollapsed ? 1f : 0f, dt, 11f);     // dword_1807089C0
        s.flagGlow = Lib.smooth(s.flagGlow, hoverHeader ? 1f : 0f, dt, 14f);          // dword_1807089D8
        s.sideW = Lib.lerp(230f, 64f, Lib.easeInOutCubic(s.collapse));

        // gamemode / view toggle animations (inside the page<6 part of the render function)
        if (s.page < 6) {
            p.gamemodeAnim = Lib.lerp(p.gamemodeAnim, p.gamemodes ? 1f : 0f, dt * 8f);        // flt_1807089A8
            p.gmBtnHover = Lib.lerp(p.gmBtnHover, hoverGmBtn ? 1f : 0f, dt * 10f);            // flt_180708A68
            for (int i = 0; i < 6; i++) {
                modeSel[i] = Lib.lerp(modeSel[i], p.gamemode == i ? 1f : 0f, dt * 8f);
                modeHover[i] = Lib.lerp(modeHover[i], (hoverMode != i || p.gamemode == i) ? 0f : 1f, dt * 10f);
            }
            if (p.detailT < 0.999f) {
                p.viewAnim = Lib.lerp(p.viewAnim, p.grid ? 1f : 0f, dt * 10f);                 // flt_180708998
                for (int i = 0; i < 2; i++)
                    p.viewHover[i] = Lib.lerp(p.viewHover[i], (hoverView != i || (p.grid ? 1 : 0) == i) ? 0f : 1f, dt * 10f);
            }
            // list scroll clamp (render, before drawing cards)
            float lmax = layout().max;
            if (scrollTarget > lmax) scrollTarget = lmax;
            if (p.scroll > lmax) p.scroll = lmax;
        }

        // search: results, popup alpha, scrolls, focus
        buildResults();
        if (results.isEmpty()) searchSel = -1;
        else if (searchSel >= results.size()) searchSel = results.size() - 1;
        if (s.searchFocused) {
            searchAlpha = Lib.smooth(searchAlpha, 1f, dt, 16f);
        } else {
            searchAlpha = Lib.smooth(searchAlpha, 0f, dt, 16f);
            if (searchAlpha < 0.001f) { searchAlpha = 0f; searchSel = -1; }
        }
        searchScrollCur = Lib.smooth(searchScrollCur, searchScrollTarget, dt, 14f);
        s.searchFocus = Lib.smooth(s.searchFocus, s.searchFocused ? 1f : 0f, dt, 14f);
        if (s.searchFocused) {
            caretT += dt;
            if (caretT >= 0.53f) { caretT = 0f; caretOn = !caretOn; }
        }
        // popup height (sub_180131960 side effect)
        float target = searchPopupTarget();
        if (searchAlpha < 0.01f) searchPopupH = target;
        else { searchPopupH = Lib.smooth(searchPopupH, target, dt, 20f); if (Math.abs(searchPopupH - target) < 0.5f) searchPopupH = target; }

        SettingsPage.update(dt); KeybindsPage.update(dt); SocialsPage.update(dt);
        ConfigsPage.update(dt); CreateConfigPage.update(dt); WidgetsPage.update(dt);
    }
    private static float clampTo(float v, float mx_) { float m = Math.min(v, mx_); return m >= 0f ? Math.min(mx_, v) : 0f; }

    // ============================================================== search (sub_18012FBA0 / 180131960 / 131610 / 131070)
    static boolean matches(String name) { String q = Ui.S.search; return q.isEmpty() || name.toLowerCase().contains(q.toLowerCase()); }
    static void buildResults() {
        results.clear();
        if (Ui.S.search.isEmpty()) return;
        for (int c = 0; c < Ui.CATS.length; c++) {
            List<ModulesPage.Mod> l = modsOf(c);
            for (int m = 0; m < l.size(); m++) if (matches(l.get(m).name)) {
                Result r = new Result(); r.type = 0; r.title = l.get(m).name; r.label = Ui.CATS[c]; r.page = c; r.idx = m; r.sub = -1; results.add(r);
            }
        }
        String[] cf = {"Public configs", "Personal configs", "Create config"};
        for (int i = 0; i < 3; i++) if (matches(cf[i]) || matches("configs")) {
            Result r = new Result(); r.type = 1; r.title = cf[i]; r.label = "Configs"; r.page = 8; r.idx = -1; r.sub = i; results.add(r);
        }
        for (int i = 0; i < Ui.S.general.length; i++) if (matches(Ui.S.general[i].name)) {
            Result r = new Result(); r.type = 2; r.title = Ui.S.general[i].name; r.label = "Category"; r.page = i + 6; r.idx = -1; r.sub = -1; results.add(r);
        }
        for (int i = 0; i < Ui.S.cats.length; i++) if (matches(Ui.S.cats[i].name)) {
            Result r = new Result(); r.type = 2; r.title = Ui.S.cats[i].name; r.label = "Module Category"; r.page = i; r.idx = -1; r.sub = -1; results.add(r);
        }
    }
    static float searchPopupTarget() {
        float v6 = 0f; boolean first = true; int lastType = 0, lastCat = -1;
        for (Result r : results) {
            if (first || r.type != lastType) { v6 += 26f; lastType = r.type; lastCat = -1; first = false; }
            if (r.type == 0 && r.page != lastCat) { v6 += 22f; lastCat = r.page; }
            v6 += 46f;
        }
        float extra = results.isEmpty() ? 120f : 0f;
        return Math.min(440f, Math.max(90f, (v6 + 24f) + extra));
    }
    /** {x, y, w, h} of the results popup. */
    static float[] searchRect() { return new float[]{740f, 86f, 350f, searchPopupH}; }
    /** sub_180131610 */
    static int hitResult(float a1, float a2) {
        if (searchAlpha < 0.1f || results.isEmpty()) return -1;
        float v17 = (86f - (float) Math.pow(1f - searchAlpha, 3.0) * 8f) + 12f;
        if (a1 < 748f || a1 > 1082f || v17 > a2 || a2 > (searchPopupH - 24f) + v17) return -1;
        float v18 = v17 - searchScrollCur;
        boolean first = true; int lastType = 0, lastCat = -1;
        for (int i = 0; i < results.size(); i++) {
            Result r = results.get(i);
            if (first || r.type != lastType) { v18 += 26f; lastType = r.type; lastCat = -1; first = false; }
            if (r.type == 0 && r.page != lastCat) { v18 += 22f; lastCat = r.page; }
            if (a2 >= v18 && v18 + 46f > a2 && a1 >= 752f && a1 <= 1078f) return i;
            v18 += 46f;
        }
        return -1;
    }
    /** Scroll the results list so the keyboard-selected row is visible. */
    static void followSel() {
        if (searchSel < 0 || searchSel >= results.size()) return;
        float top = 0f; boolean first = true; int lastType = 0, lastCat = -1;
        for (int i = 0; i < results.size(); i++) {
            Result r = results.get(i);
            if (first || r.type != lastType) { top += 26f; lastType = r.type; lastCat = -1; first = false; }
            if (r.type == 0 && r.page != lastCat) { top += 22f; lastCat = r.page; }
            if (i == searchSel) break;
            top += 46f;
        }
        float vh = searchPopupTarget() - 24f;
        if (top < searchScrollTarget) searchScrollTarget = Math.max(0f, top - 26f);
        else if (top + 46f > searchScrollTarget + vh) searchScrollTarget = top + 46f - vh;
    }
    static void clearSearch() {
        Ui.S.searchFocused = false; Ui.S.search = ""; searchSel = -1; searchScrollCur = 0f; searchScrollTarget = 0f;
    }
    /** reset of all open popups done at the start of sub_180131070 */
    static void resetPopups() {
        ddOpen = false; msOpen = false; cpOpen = false; textEdit = false; textSetting = null;
        cpHexEdit = false;
    }
    static void deselectAll() { /* selection derived from Ui.S.page in update() */ }

    /** sub_180131070 */
    static void activateResult(Result r) {
        ModulesPage.St p = ModulesPage.P;
        resetPopups();
        if (r.type == 0) {
            switchPage(r.page);
            ModulesPage.Mod m = mod(r.page, r.idx);
            if (m != null) {
                openDetail(m);
                List<int[]> e = entries();
                int k = -1;
                for (int i = 0; i < e.size(); i++) if (e.get(i)[0] == r.page && e.get(i)[1] == r.idx) { k = i; break; }
                float v24 = 0f;
                if (k != -1) {
                    Layout L = layout(e);
                    float v30 = L.hdr[k] < 0f ? 0f : 30f;
                    float v31 = ((p.scroll + L.r[k][1]) - 152f) - v30;
                    if (Math.min(v31, L.max) >= 0f) v24 = Math.min(L.max, v31);
                }
                p.scroll = v24; scrollTarget = v24;
            }
        } else if (r.type == 1) {
            switchPage(r.page);
            p.scroll = 0f; scrollTarget = 0f;
            configsSubPage = r.sub;
        } else {
            switchPage(r.page);
            p.scroll = 0f; scrollTarget = 0f;
        }
    }

    // ============================================================== page switching / detail
    static void resetCardAnims() {            // sub_18013FB30 (map qword_180744CA8)
        for (ModulesPage.Mod m : ModulesPage.P.mods) { m.hoverLift = 0f; m.glow = 0f; m.press = 0f; }
    }
    static void startEnter() {                 // qword_1804CCEA0 = 0; byte_1804CCEAC = 1
        ModulesPage.P.enterA = 0f; ModulesPage.P.enterB = 0f; enterAnimating = true;
    }
    /** shared tail of sidebar / search page changes (sub_180131070 and the 0x201 sidebar code). */
    static void switchPage(int page) {
        Ui.State s = Ui.S;
        if (s.page != page) {
            prevPage = s.page;
            startEnter();
            resetCardAnims();
            if (openCat >= 0) { closeDetail(); ModulesPage.P.detailT = 0f; openCat = -1; openIdx = -1; }
            s.page = page;
            if (page < 6) ModulesPage.P.cat = page;
        }
    }

    /** Compatibility shims for WidgetsPage (cat < 0 means "page"). */
    static void selectPage(int page, int cat) { switchPage(page == -1 ? cat : page); }
    static boolean in(float x, float y, float w, float h) { return incl(x, y, w, h); }

    /** sub_18012E300 */
    static void openDetail(ModulesPage.Mod m) {
        int c = catOf(m), i = modIdx(m);
        if (c < 0 || i < 0) return;
        openCat = c; openIdx = i; detailOpen = true;
        DetailPage.D.mod = m;
        DetailPage.D.settings = Data.settingsFor(m.name);
        Data.seedMulti(DetailPage.D.settings);
        for (DetailPage.Setting st : DetailPage.D.settings) if (isEnabledName(st)) st.bval = m.enabled;
        DetailPage.D.scroll = 0f; detailScrollTarget = 0f;
    }
    /** sub_18012E3A0 */
    static void closeDetail() {
        detailOpen = false; hoverBack = false;
        if (capturing && capSetting != null) { capSetting.key = capPrevKey; capSetting.toggleMode = capPrevToggle; }
        capturing = false; capSetting = null; capPrevKey = 0; capPrevToggle = false;
        dragSetting = null; rangeDragLo = false; rangeDragHi = false;
        textEdit = false; textSetting = null;
        ddOpen = false; ddSetting = null; msOpen = false; msSetting = null;
        cpOpen = false; cpSetting = null; cpHexEdit = false; cpDragSV = false; cpDragHue = false; cpDragAlpha = false;
        DetailPage.D.hoverRow = -1;
    }
    /** toggling a module: byte +96, mirror into the "Enabled" setting (setting[0]+68). */
    static void toggleModule(ModulesPage.Mod m) {
        boolean was = m.enabled;
        m.enabled = !was;
        if (m.live != null) m.live.setEnabled(m.enabled);
        snd(was ? "pop3" : "pop2");
        if (DetailPage.D.mod == m) for (DetailPage.Setting st : DetailPage.D.settings) if (isEnabledName(st)) st.bval = m.enabled;
        listener.settingChanged(catOf(m), modIdx(m), 0);
    }
    public static int enabledCount(int cat) { int n = 0; for (ModulesPage.Mod m : modsOf(cat)) if (m.enabled) n++; return n; }

    // ============================================================== mouse move  (WM_MOUSEMOVE, sub_180145320 a2==0x200)
    static void onMouseMove() {
        Ui.State s = Ui.S; ModulesPage.St p = ModulesPage.P;
        float x = X, y = Y;
        if (searchAlpha > 0.1f) {
            if (!results.isEmpty()) {
                int r = hitResult(x, y);
                if (r != -1) { int old = searchSel; searchSel = r; if (old != r) snd("hover"); return; }
            }
            float[] sr = searchRect();
            if (x >= sr[0] && sr[0] + sr[2] >= x && y >= sr[1] && sr[1] + sr[3] >= y) { hoverSidebar = -1; p.hoverCard = -1; return; }
        }
        // header / sidebar hover
        boolean old871E = hoverHeader;
        if (x < 18f || (sideW() + 14f) - 4f < x || y < 18f || y > 60f) hoverHeader = false;
        else { hoverHeader = true; if (!old871E) snd("hover"); }
        float right = (sideW() + 14f) - 12f;
        int old9C = hoverSidebar; hoverSidebar = -1;
        float yy = 130f; int found = -1;
        for (int i = 0; i < s.cats.length; i++, yy += 44f)
            if (!(x < 26f || right < x || y < yy || yy + 40f < y)) { found = i; break; }
        if (found != -1) { if (old9C != found) snd("hover"); hoverSidebar = found; }
        else {
            float y2 = (yy + 30f) + 35f;
            for (int i = 0; i < s.general.length; i++, y2 += 44f)
                if (!(x < 26f || right < x || y < y2 || y2 + 40f < y)) {
                    if (old9C != i + 100) snd("hover");
                    hoverSidebar = i + 100; break;
                }
        }
        boolean mods = s.page < 6;
        // gamemodes button / strip
        boolean oldGm = hoverGmBtn; int oldMode = hoverMode;
        hoverGmBtn = false; hoverMode = -1;
        if (mods) {
            float[] pn = panel();
            if (incl(pn[4], pn[5], pn[6], pn[7])) hoverGmBtn = true;
            if (p.gamemodeAnim > 0.7f && !detailOpenNow()) {
                float[][] sr = stripRects();
                for (int i = 0; i < sr.length; i++) if (incl(sr[i][0], sr[i][1], sr[i][2], sr[i][3])) { hoverMode = i; break; }
            }
            if (oldGm != hoverGmBtn && hoverGmBtn) snd("hover");
            if (oldMode != hoverMode && hoverMode != -1) snd("hover");
        }
        // view toggle
        hoverView = -1;
        if (mods && !detailOpenNow()) {
            float[] v = viewRect();
            if (X >= v[0] && v[2] * 2f + v[0] > X && Y >= v[1] && v[1] + v[3] >= Y) {
                int k = (int) ((X - v[0]) / v[2]); if (k < 0) k = 0; if (k > 1) k = 1;
                hoverView = k;
            }
        }
        // cards / detail rows
        int oldCard = p.hoverCard, oldRow = DetailPage.D.hoverRow;
        p.hoverCard = -1; DetailPage.D.hoverRow = -1; hoverBack = false;
        if (mods) {
            if (detailOpenNow()) {
                p.hoverCard = openIdx;
                float[] pn = panel();
                hoverBack = x >= pn[4] - 4f && (pn[6] + pn[4]) + 4f >= x && y >= pn[5] - 4f && (pn[7] + pn[5]) + 4f >= y;
                if (!(y <= pn[11]) && !(x < pn[0]) && !(pn[2] + pn[0] < x)) {
                    float ry = (pn[11] + 14f) - DetailPage.D.scroll;
                    int vi = 0;
                    for (DetailPage.Setting st : DetailPage.D.settings) {
                        if (isEnabledName(st) || !st.visible) continue;
                        if (y >= ry && 54f + ry > y) { DetailPage.D.hoverRow = vi; if (oldRow != vi) snd("hover"); break; }
                        ry += 54f; vi++;
                    }
                }
            } else {
                List<int[]> e = entries(); Layout L = layout(e);
                for (int i = 0; i < e.size(); i++) {
                    float[] r = L.r[i];
                    if (x >= r[0] && r[0] + r[2] >= x && y >= r[1] && r[1] + r[3] >= y && y >= 30f && y <= 700f) {
                        p.hoverCard = i; if (oldCard != i) snd("hover"); break;
                    }
                }
            }
        }
        // slider drag (LABEL_1593)
        if (dragSetting != null && mods) dragUpdate();
        // colour picker drag / hover lists
        if (!cpUpdateDrag()) {
            int od = ddHover, om = msHover;
            ddHover = -1;
            if (ddOpen && ddSetting != null && mods && ddSetting.type == 6) ddHover = listHover(ddSetting, ddSetting.opts.length, null, od);
            msHover = -1;
            if (msOpen && msSetting != null && mods && msSetting.type == 7) msHover = listHover(msSetting, msSetting.opts.length, null, om);
        }
        // themes page
        ThemesPage.hover = -1;
        if (s.page == 7) {
            for (int j = 0; j < Theme.NAMES.length; j++) {
                float tx = (j % 3) * 260f + (sideW() + 30f), ty = (j / 3) * 140f + (110f - ThemesPage.scroll);
                if (x >= tx && tx + 240f >= x && y >= ty && ty + 120f >= y && y >= 105f && y <= 685f) {
                    if (ThemesPage.hover != j) snd("hover");
                    ThemesPage.hover = j; break;
                }
            }
        }
    }
    private static boolean detailOpenNow() { return openCat >= 0 && detailOpen; }   // sub_18012E270 (byte_1807089DE && indices valid)

    /** hover over option list popups (E50/E98 and E18/E74 blocks). */
    private static int listHover(DetailPage.Setting st, int n, Object unused, int old) {
        int vi = vis().indexOf(st);
        if (vi < 0) return -1;
        float[] pn = panel();
        float lx = (pn[2] + pn[0]) - 160f;
        float y1 = popupY(vi, n * 28f);
        float bottom = pn[3] + pn[1];
        for (int i = 0; i < n; i++) {
            float yi = i * 28f + y1;
            if (X >= lx && lx + 140f >= X && Y >= yi && yi + 28f >= Y && Y >= pn[11] && bottom >= Y) {
                if (old != i) snd("hover");
                return i;
            }
        }
        return -1;
    }

    private static void dragUpdate() {
        DetailPage.Setting st = dragSetting;
        long now = clock.getAsLong();
        float t = (X - (((1100f - sideW()) - 60f) + (sideW() + 30f) - 328f)) / 200f;
        if (st.type != 5) {
            float v = Math.min(1f, t); if (v < 0f) v = 0f;
            if (st.type == 3) {
                float old = st.fval;
                st.fval = (st.fmax - st.fmin) * v + st.fmin;
                if (Math.abs(st.fval - old) < 0.01f || now - lastSliderSound < 40) return;
            } else if (st.type == 4) {
                int old = st.ival;
                st.ival = (int) ((st.imax - st.imin) * v) + st.imin;
                if (st.ival == old || now - lastSliderSound < 40) return;
            } else return;
            snd("pop"); lastSliderSound = now;
            return;
        }
        // range: two knobs (type 5)
        float v = Math.min(1f, t); if (v < 0f) v = 0f;
        float lo = st.lo, hi = st.hi, rmax = st.rmax, rmin = st.rmin;
        float val = (rmax - rmin) * v + rmin;
        float a = lo, b = hi;
        if (rangeDragLo) {
            float nl = Math.max(rmin, Math.min(val, rmax));
            if (nl <= hi) { st.lo = nl; a = nl; b = hi; }
            else { st.hi = nl; rangeDragLo = false; rangeDragHi = true; st.lo = hi; a = hi; b = nl; }
        } else if (rangeDragHi) {
            float nh = Math.min(val, rmax); if (rmin > nh) nh = rmin;
            if (lo <= nh) { st.hi = nh; a = lo; b = nh; }
            else { st.hi = lo; st.lo = nh; rangeDragHi = false; rangeDragLo = true; a = nh; b = lo; }
        }
        if ((a != lo || b != hi) && now - lastSliderSound >= 40) { snd("pop"); lastSliderSound = now; }
    }

    // ---- colour picker geometry & drag
    /** {x0(v372), y0(v373), hueX(v375), alphaY(v377), hexY(v378), hexX(v379), panelX(v370), panelY(v371)} */
    static float[] cpGeom() { return cpGeom(cpSetting); }
    static float[] cpGeom(DetailPage.Setting st) {
        int vi = vis().indexOf(st);
        float[] pn = panel();
        float v370 = (((pn[0] + pn[2]) - 76f) - 220f) + 24f;
        float v371 = popupY(vi, 258f);
        float v372 = v370 + 12f, v373 = v371 + 12f, v374 = v372 + 170f, v375 = v374 + 8f;
        float v376 = v373 + 170f, v377 = v376 + 10f, v378 = (v377 + 16f) + 10f, v379 = (v372 + 28f) + 8f;
        return new float[]{v372, v373, v375, v377, v378, v379, v370, v371};
    }
    private static boolean cpValid() { return cpOpen && cpSetting != null && cpSetting.type == 2 && vis().contains(cpSetting); }
    private static void applyHsv() {
        cpSetting.color = hsv2rgb(cpHue, cpSat, cpVal, cpAlpha);
        setHex(cpSetting.color);
        Data.push(cpSetting);
    }
    private static boolean cpUpdateDrag() {
        if (!(cpValid()) ) return false;
        if (!(cpDragSV || cpDragHue || cpDragAlpha)) return false;
        float[] g = cpGeom();
        if (cpDragSV) {
            cpSat = clamp01((X - g[0]) / 170f);
            cpVal = clamp01(1f - ((Y - g[1]) / 170f));
            applyHsv(); return true;
        }
        if (cpDragHue) {
            float h = ((Y - g[1]) / 170f) * 360f;
            cpHue = h < 0f ? 0f : Math.min(h, 360f);
            applyHsv(); return true;
        }
        cpAlpha = clamp01((X - g[0]) / 196f);
        applyHsv(); return true;
    }
    /** sub_1800E04F0 */
    static float[] hsv2rgb(float h, float s, float v, float a) {
        float c = s * v, m = v - c;
        float xx = (float) ((1.0 - Math.abs((h / 60.0) % 2.0 - 1.0)) * c);
        float r, g, b;
        if (h < 60f) { r = c; g = xx; b = 0f; }
        else if (h < 120f) { r = xx; g = c; b = 0f; }
        else if (h < 180f) { r = 0f; g = c; b = xx; }
        else if (h < 240f) { r = 0f; g = xx; b = c; }
        else if (h < 300f) { r = xx; g = 0f; b = c; }
        else { r = c; g = 0f; b = xx; }
        return new float[]{r + m, g + m, b + m, a};
    }
    /** sub_1800E0620 -> {hue, sat, val} */
    static float[] rgb2hsv(float r, float g, float b) {
        float mxv = Math.max(Math.max(g, b), r), mn = Math.min(Math.min(g, b), r);
        float d = mxv - mn, hue, sat;
        if (mxv == 0f) { hue = 0f; sat = 0f; }
        else {
            sat = d / mxv;
            if (d == 0f) hue = 0f;
            else {
                float v12;
                if (mxv == r) v12 = (float) ((g - b) / d % 6.0);
                else if (mxv == g) v12 = ((b - r) / d) + 2f;
                else v12 = ((r - g) / d) + 4f;
                hue = v12 * 60f; if (hue < 0f) hue += 360f;
            }
        }
        return new float[]{hue, sat, mxv};
    }
    static void loadPicker(DetailPage.Setting st) {
        float[] c = st.color; float[] h = rgb2hsv(c[0], c[1], c[2]);
        cpHue = h[0]; cpSat = h[1]; cpVal = h[2]; cpAlpha = c[3];
        setHex(c);
    }
    /** sub_1800E0730 */
    static void setHex(float[] c) {
        int r = (int) (c[0] * 255f + 0.5f), g = (int) (c[1] * 255f + 0.5f), b = (int) (c[2] * 255f + 0.5f), a = (int) (c[3] * 255f + 0.5f);
        cpHex.setLength(0);
        cpHex.append(a >= 255 ? String.format("#%02X%02X%02X", r, g, b) : String.format("#%02X%02X%02X%02X", r, g, b, a));
    }
    /** sub_1800E0830 */
    static float[] parseHex(String t) {
        if (t.isEmpty()) return null;
        if (t.charAt(0) == '#') t = t.substring(1);
        if (t.length() != 6 && t.length() != 8) return null;
        try {
            int r = Integer.parseInt(t.substring(0, 2), 16), g = Integer.parseInt(t.substring(2, 4), 16), b = Integer.parseInt(t.substring(4, 6), 16);
            int a = t.length() == 8 ? Integer.parseInt(t.substring(6, 8), 16) : 255;
            return new float[]{r / 255f, g / 255f, b / 255f, a / 255f};
        } catch (NumberFormatException e) { return null; }
    }
    private static void closePicker() {
        cpOpen = false; cpSetting = null; cpHexEdit = false; cpDragSV = false; cpDragHue = false; cpDragAlpha = false;
    }

    // ============================================================== mouse buttons
    public static void mouseDown(int button) {
        if (!active) return;
        syncMouse();
        if (WidgetsPage.click(button)) return;
        switch (button) {
            case 0: lDown(); break;
            case 1: rDown(); break;
            case 2: assignMouseKey(9997); break;                 // WM_MBUTTONDOWN
            case 3: assignMouseKey(9996); break;                 // WM_XBUTTONDOWN, XBUTTON1
            case 4: assignMouseKey(9995); break;                 // XBUTTON2
            default: break;
        }
    }

    /** 0x207 / 0x20B */
    private static void assignMouseKey(int code) {
        if (!capturing || capSetting == null || Ui.S.page >= 6) return;
        capSetting.key = code;
        if (capPrevKey <= 0) capSetting.toggleMode = true;      // +220 = 0
        dirty(capSetting);
        endCapture(); snd("pop");
    }
    private static void endCapture() { capturing = false; capSetting = null; capPrevKey = 0; capPrevToggle = false; }

    private static void lDown() {
        Ui.State s = Ui.S; ModulesPage.St p = ModulesPage.P;
        down = true;
        float x = X, y = Y;
        // dialog (sub_180141670 && sub_180144FB0) handled by WidgetsPage.click above
        if (s.page < 6 && x >= 760f && x <= 1070f && y >= 42f && y <= 78f) {
            snd("pop");
            if (!s.searchFocused) { s.search = ""; searchSel = -1; searchScrollCur = 0f; searchScrollTarget = 0f; }
            s.searchFocused = true; textEdit = false; textSetting = null;
            return;
        }
        if (searchAlpha > 0.2f) {
            int r = hitResult(x, y);
            if (r >= 0 && r < results.size()) {
                snd("pop2");
                Result res = results.get(r);
                activateResult(res);
                clearSearch();
                return;
            }
            float[] sr = searchRect();
            if (x >= sr[0] && sr[0] + sr[2] >= x && y >= sr[1] && sr[1] + sr[3] >= y) return;
        }
        s.searchFocused = false;     // LABEL_730 (page 8 variant submits the config search in ConfigsPage)
        float v321 = sideW() + 14f;
        if (x >= 18f && v321 - 4f >= x && y >= 18f && y <= 60f) { sidebarCollapsed = !sidebarCollapsed; snd("pop"); return; }
        if (y < 30f && y != 0f && x > v321) { windowDragging = true; return; }
        float right = v321 - 12f;
        float yy = 130f;
        for (int i = 0; i < s.cats.length; i++, yy += 44f) {
            if (x < 26f || right < x || y < yy || yy + 40f < y) continue;
            if (s.page == i && !p.gamemodes) return;
            prevPage = s.page; startEnter(); resetCardAnims();
            if (openCat >= 0) { closeDetail(); p.detailT = 0f; openCat = -1; openIdx = -1; }
            textEdit = false; textSetting = null;
            s.page = i; p.cat = i;
            p.scroll = 0f; scrollTarget = 0f;
            p.gamemodes = false;
            snd("pop");
            return;
        }
        float y2 = (yy + 30f) + 35f;
        for (int i = 0; i < s.general.length; i++, y2 += 44f) {
            if (x < 26f || right < x || y < y2 || y2 + 40f < y) continue;
            prevPage = s.page; startEnter(); resetCardAnims();
            if (openCat >= 0) { closeDetail(); p.detailT = 0f; openCat = -1; openIdx = -1; }
            textEdit = false; textSetting = null;
            s.page = 6 + i;
            p.gamemodes = false;
            snd("pop");
            ThemesPage.scroll = 0f; themeScrollTarget = 0f;
            return;
        }
        // keybind capture + mouse click (LABEL_761)
        if (capturing && capSetting != null && s.page < 6) {
            boolean inBox = false;
            if (detailOpenNow() && vis().contains(capSetting) && capSetting.type == 0) {
                float[] g = keyGeom(vis().indexOf(capSetting));
                inBox = x >= g[8] && g[8] + 116f > x && y >= g[9] && g[9] + 28f > y;
            }
            if (inBox) { capSetting.key = 9999; capSetting.toggleMode = false; dirty(capSetting); snd("pop2"); }
            else { capSetting.key = capPrevKey; capSetting.toggleMode = capPrevToggle; dirty(capSetting); snd("pop3"); }
            endCapture();
            return;
        }
        if (s.page >= 6) { pageClick(0); return; }
        // dropdown option list
        if (ddOpen && ddSetting != null) {
            if (ddHover >= 0 && ddHover < ddSetting.opts.length) {
                ddSetting.sval = ddSetting.opts[ddHover];
                Data.applyVisibility(DetailPage.D.settings);
                dirty(ddSetting); snd("pop");
                ddOpen = false; ddSetting = null;
                return;
            }
            snd("dropdown"); ddOpen = false; ddSetting = null;
            return;
        }
        if (msOpen && msSetting != null) {
            if (msHover >= 0) {
                boolean[] f = multiFlags(msSetting);
                if (msHover < f.length) f[msHover] = !f[msHover];
                dirty(msSetting); snd("pop");
                return;
            }
            snd("dropdown"); msOpen = false; msSetting = null;
            return;
        }
        if (cpOpen && cpSetting != null) {
            if (!cpValid()) { closePicker(); return; }
            float[] g = cpGeom();
            if (x < g[6] || g[6] + 220f < x || y < g[7] || g[7] + 258f < y) { snd("dropdown"); closePicker(); return; }
            if (x >= g[0] && g[0] + 170f >= x && y >= g[1] && g[1] + 170f >= y) {
                cpDragSV = true; cpSat = clamp01((x - g[0]) / 170f); cpVal = clamp01(1f - ((y - g[1]) / 170f)); applyHsv(); snd("pop"); return;
            }
            if (x >= g[2] && g[2] + 18f >= x && y >= g[1] && g[1] + 170f >= y) {
                cpDragHue = true; float h = ((y - g[1]) / 170f) * 360f; cpHue = h < 0f ? 0f : Math.min(h, 360f); applyHsv(); snd("pop"); return;
            }
            if (x >= g[0] && g[0] + 196f >= x && y >= g[3] && g[3] + 16f >= y) {
                cpDragAlpha = true; cpAlpha = clamp01((x - g[0]) / 196f); applyHsv(); snd("pop"); return;
            }
            if (x >= g[5] && g[5] + 160f >= x && y >= g[4] && g[4] + 28f >= y) { cpHexEdit = true; snd("pop"); return; }
            cpHexEdit = false;
            return;
        }
        if (!detailOpenNow()) {
            float[] pn = panel();
            // gamemodes button
            if (incl(pn[4], pn[5], pn[6], pn[7])) {
                p.gamemodes = !p.gamemodes;
                if (p.gamemodes) p.gamemode = 0;
                if (openCat >= 0) { closeDetail(); p.detailT = 0f; openCat = -1; openIdx = -1; }
                resetCardAnims(); startEnter(); p.scroll = 0f; scrollTarget = 0f;
                snd("pop");
                return;
            }
            if (p.gamemodeAnim > 0.5f) {
                float[][] sr = stripRects();
                for (int i = 0; i < sr.length; i++) {
                    if (x >= sr[i][0] && sr[i][0] + sr[i][2] >= x && y >= sr[i][1] && sr[i][1] + sr[i][3] >= y) {
                        gmClicks++;
                        if (p.gamemode == i) return;
                        p.gamemode = i;
                        if (openCat >= 0) { closeDetail(); p.detailT = 0f; openCat = -1; openIdx = -1; }
                        resetCardAnims(); startEnter(); p.scroll = 0f; scrollTarget = 0f;
                        snd("pop");
                        return;
                    }
                }
            }
            // view toggle (sub_18012E020)
            float[] v = viewRect();
            if (x >= v[0] && v[2] * 2f + v[0] > x && y >= v[1] && v[1] + v[3] >= y) {
                int k = (int) ((x - v[0]) / v[2]); if (k < 0) k = 0; if (k > 1) k = 1;
                if (k == (p.grid ? 1 : 0)) return;
                p.grid = k == 1;
                if (openCat >= 0) { closeDetail(); p.detailT = 0f; openCat = -1; openIdx = -1; }
                resetCardAnims(); startEnter();
                float max = layout().max;
                scrollTarget = Math.min(scrollTarget, max) >= 0f ? Math.min(max, scrollTarget) : 0f;
                p.scroll = Math.min(p.scroll, max) >= 0f ? Math.min(max, p.scroll) : 0f;
                snd("pop");
                return;
            }
            // module cards
            List<int[]> e = entries(); Layout L = layout(e);
            for (int i = 0; i < e.size(); i++) {
                float[] r = L.r[i];
                if (x >= r[0] && r[0] + r[2] >= x && y >= r[1] && r[1] + r[3] >= y && y >= 30f) {
                    int[] en = e.get(i);
                    cardHoverSet(en);
                    float bx = (r[0] + r[2]) - 60f, by = r[1] + (p.grid ? 14f : 30f);
                    ModulesPage.Mod m = mod(en[0], en[1]);
                    if (m == null) return;
                    if (x < bx || bx + 44f < x || y < by || by + 24f < y) { snd("pop"); openDetail(m); snd("woosh"); }
                    else toggleModule(m);
                    return;
                }
            }
            return;
        }
        // ---- detail panel open
        float[] pn = panel();
        ModulesPage.Mod m = mod(openCat, openIdx);
        if (x >= pn[4] - 4f && (pn[6] + pn[4]) + 4f >= x && y >= pn[5] - 4f && (pn[7] + pn[5]) + 4f >= y) { closeDetail(); snd("pop3"); return; }
        float tx = ((pn[0] + pn[2]) - 20f) - 44f, ty = pn[8] + 1f;
        if (m != null && x >= tx && tx + 44f >= x && y >= ty && ty + 24f >= y) { toggleModule(m); return; }
        detailRowClick();
    }

    /** sub_18014F490: card hover set on click (switches page in gamemode view). */
    private static void cardHoverSet(int[] en) {
        Ui.State s = Ui.S;
        if (!ModulesPage.P.gamemodes || s.page == en[0]) { ModulesPage.P.hoverCard = en[1]; return; }
        s.page = en[0]; ModulesPage.P.cat = en[0];
        ModulesPage.P.hoverCard = en[1];
    }

    private static void pageClick(int button) {
        int pg = Ui.S.page;
        if (pg == 6) { SettingsPage.click(button); }
        else if (pg == 7) {
            if (ThemesPage.hover >= 0) { setTheme(ThemesPage.hover); snd("pop"); }
        } else if (pg == 8) { if (!ConfigsPage.click(button)) CreateConfigPage.click(button); }
        else if (pg == 9) SocialsPage.click(button);
        else if (pg == 10) KeybindsPage.click(button);
    }
    /** sub_1800E6350 */
    static void setTheme(int idx) {
        if (idx >= 0 && idx < Theme.NAMES.length && idx != Theme.active) {
            themePrev = Theme.active; themeFade = 0f; themeFading = true; Theme.active = idx;
        }
    }

    /** sub_180122B30: {pill x,y,..} -> returns [0]=pillX [1]=y [4]=holdX0 [5]=holdX1 [6]=togX0 [7]=togX1 [8]=boxX [9]=y. */
    private static float[] keyGeom(int vi) {
        float[] pn = panel();
        float v426 = (pn[0] + pn[2]) - 20f, ry = rowY(vi), y = ry + 13f;
        float x0 = v426 - 228f;
        return new float[]{x0, y, 96f, 28f, x0, x0 + 48f, x0 + 48f, x0 + 96f, v426 - 116f, y};
    }

    /** the row click switch at the end of the 0x201 handler (case 0..8). */
    private static void detailRowClick() {
        float[] pn = panel();
        float x = X, y = Y;
        if (y <= pn[11] || x < pn[0] || pn[0] + pn[2] < x) return;
        List<DetailPage.Setting> vs = vis();
        float ry = (pn[11] + 14f) - DetailPage.D.scroll;
        for (int vi = 0; vi < vs.size(); vi++, ry += 54f) {
            DetailPage.Setting st = vs.get(vi);
            if (y < ry || ry + 54f <= y) continue;
            float v13 = (pn[0] + pn[2]) - 20f;
            switch (st.type) {
                case 0: {
                    float[] g = keyGeom(vi);
                    boolean left = x >= g[4] && g[5] > x && y >= g[1] && g[1] + 28f > y;
                    boolean right = x >= g[6] && g[7] > x && y >= g[1] && g[1] + 28f > y;
                    if (left) {
                        if (st.key != 0) { if (!st.toggleMode) return; st.toggleMode = false; dirty(st); snd("pop"); }
                        else { listener.toast("Assign a key first"); snd("pop3"); }
                    } else if (right) {
                        if (st.key != 0) {
                            if (st.key != 9998 && st.key != 9999) { if (st.toggleMode) return; st.toggleMode = true; dirty(st); snd("pop"); }
                            else { listener.toast("Toggle not available for mouse buttons"); snd("pop3"); }
                        } else { listener.toast("Assign a key first"); snd("pop3"); }
                    } else if (x >= g[8] && g[8] + 116f > x && y >= g[9] && g[9] + 28f > y) {
                        capPrevKey = st.key; capPrevToggle = st.toggleMode; capSetting = st; capturing = true;
                        dirty(st); snd("pop");
                    }
                    return;
                }
                case 1: {
                    float ty = ry + 16f;
                    if (x < v13 - 44f || v13 < x || y < ty || ty + 22f < y) return;
                    st.bval = !st.bval; dirty(st); snd("pop");
                    return;
                }
                case 2: {
                    float cx = v13 - 56f, cy = ry + 13f;
                    if (x < cx || cx + 56f < x || y < cy || cy + 28f < y) return;
                    if (cpOpen && cpSetting == st) { snd("dropdown"); closePicker(); return; }
                    snd("dropdown");
                    ddOpen = false; msOpen = false;
                    cpSetting = st; cpOpen = true; cpHexEdit = false;
                    loadPicker(st);
                    dirty(st);
                    return;
                }
                case 3: case 4: {
                    float sx = v13 - 308f;
                    if (x < sx || sx + 200f < x || y < ry + 17f || ry + 37f < y) return;
                    dragSetting = st;
                    float t = Math.min(1f, (x - sx) / 200f); if (t < 0f) t = 0f;
                    if (st.type == 3) st.fval = (st.fmax - st.fmin) * t + st.fmin;
                    else st.ival = st.imin + (int) ((st.imax - st.imin) * t);
                    dirty(st); snd("pop");
                    return;
                }
                case 5: {
                    float sx = v13 - 308f, span = st.rmax - st.rmin;
                    float k1 = ((st.lo - st.rmin) / span) * 200f + sx, k2 = ((st.hi - st.rmin) / span) * 200f + sx;
                    if (y < ry + 17f || ry + 37f < y) return;
                    float d1 = Math.abs(x - k1), d2 = Math.abs(x - k2);
                    if (d1 > 15f && d2 > 15f) return;
                    dragSetting = st;
                    rangeDragLo = d2 > d1; rangeDragHi = d2 <= d1;
                    dirty(st); snd("pop");
                    return;
                }
                case 6: {
                    float bx = v13 - 140f, by = ry + 13f;
                    if (x < bx || bx + 140f < x || y < by || by + 28f < y || st.opts.length == 0) return;
                    int cur = 0;
                    for (int i = 0; i < st.opts.length; i++) if (st.opts[i].equals(st.sval)) { cur = i; break; }
                    st.sval = st.opts[(cur + 1) % st.opts.length];
                    Data.applyVisibility(DetailPage.D.settings);
                    dirty(st); snd("pop");
                    return;
                }
                case 7: {
                    float bx = v13 - 140f, by = ry + 13f;
                    if (x < bx || bx + 140f < x || y < by || by + 28f < y || st.opts.length == 0) return;
                    if (msOpen && msSetting == st) { snd("dropdown"); msOpen = false; msSetting = null; }
                    else { snd("dropdown"); ddOpen = false; ddSetting = null; cpOpen = false; cpSetting = null; cpHexEdit = false; msSetting = st; msOpen = true; }
                    dirty(st);
                    return;
                }
                case 8: {
                    float by = ry + 13f;
                    if (x < v13 - 240f || v13 < x || y < by || by + 28f < y) return;
                    textSetting = st; textEdit = true;
                    textBuf.setLength(0); textBuf.append(st.sval == null ? "" : st.sval);
                    dirty(st); snd("pop");
                    return;
                }
                default: return;
            }
        }
    }

    /** WM_RBUTTONDOWN (0x204): acts on the hovered row, not on coordinates. */
    private static void rDown() {
        Ui.State s = Ui.S; ModulesPage.St p = ModulesPage.P;
        int hr = DetailPage.D.hoverRow;
        if (s.page < 6 && p.hoverCard >= 0 && hr >= 0 && detailOpenNow()) {
            List<DetailPage.Setting> vs = vis();
            if (hr < vs.size()) {
                DetailPage.Setting st = vs.get(hr);
                if (st.type == 6 && st.opts.length > 0) {
                    if (ddOpen && ddSetting == st) { snd("dropdown"); ddOpen = false; ddSetting = null; }
                    else { snd("dropdown"); msOpen = false; msSetting = null; cpOpen = false; cpSetting = null; cpHexEdit = false; ddSetting = st; ddOpen = true; dirty(st); }
                    return;
                }
                if (st.type == 0 && !capturing) {
                    if (st.key != 0 && st.key != 9998 && st.key != 9999) { st.toggleMode = !st.toggleMode; dirty(st); snd("pop"); return; }
                } else if (st.type == 7 && st.opts.length > 0) {
                    if (msOpen && msSetting == st) { snd("dropdown"); msOpen = false; msSetting = null; }
                    else { snd("dropdown"); ddOpen = false; ddSetting = null; cpOpen = false; cpSetting = null; cpHexEdit = false; msSetting = st; msOpen = true; dirty(st); }
                    return;
                } else if (st.type == 2) {
                    if (cpOpen && cpSetting == st) { snd("dropdown"); cpOpen = false; cpSetting = null; }
                    else { snd("dropdown"); ddOpen = false; msOpen = false; cpSetting = st; cpOpen = true; loadPicker(st); }
                    return;
                }
            }
        }
        if (capturing && capSetting != null && s.page < 6) {
            boolean hold = !capSetting.toggleMode;
            capSetting.key = 9998; capSetting.toggleMode = false;
            dirty(capSetting);
            if (!hold) listener.toast("Mode set to Hold (Toggle not available for mouse buttons)");
            endCapture(); snd("pop");
            return;
        }
        if (s.page >= 6 || detailOpenNow() || p.hoverCard < 0) {
            if (s.page >= 6) pageClick(1);
            return;
        }
        List<int[]> e = entries();
        int k = p.hoverCard;
        if (k < e.size()) {
            int[] en = e.get(k);
            if (p.gamemodes && s.page != en[0]) { s.page = en[0]; p.cat = en[0]; }
            ModulesPage.Mod m = mod(en[0], en[1]);
            if (m != null) { openDetail(m); snd("pop"); snd("woosh"); }
        }
    }

    public static void mouseUp(int button) {
        if (!active) return;
        if (button != 0) return;
        syncMouse();
        down = false;                                   // byte_18070897D = 0
        windowDragging = false;                         // byte_18070893C
        if (dragSetting != null) {
            dirty(dragSetting);
            dragSetting = null; rangeDragLo = false; rangeDragHi = false;
        }
        if (cpDragSV || cpDragHue || cpDragAlpha) { dirty(cpSetting); cpDragSV = false; cpDragHue = false; cpDragAlpha = false; }
    }

    // ============================================================== wheel (0x20A)
    public static void scroll(double dy) {
        if (!active) return;
        syncMouse();
        if (WidgetsPage.scroll(dy)) return;
        Ui.State s = Ui.S; ModulesPage.St p = ModulesPage.P;
        float notch = (float) dy;      // SWORD1/120: one wheel notch = 1
        if (searchAlpha > 0.2f && !results.isEmpty()) {
            float h = searchPopupH;
            float total = 0f; boolean first = true; int lastType = 0;
            for (Result r : results) { if (first || r.type != lastType) { total += 26f; lastType = r.type; first = false; } total += 46f; }
            float mxs = Math.max(0f, total - (h - 24f));
            searchScrollTarget -= notch * 40f;
            searchScrollTarget = searchScrollTarget < 0f ? 0f : Math.min(searchScrollTarget, mxs);
            return;
        }
        if (s.page == 7) {
            themeScrollTarget -= notch * 40f;
            float m = ((float) ((Theme.NAMES.length + 2) / 3) * 140f - 20f) - 575f;
            if (m < 0f) m = 0f;
            themeScrollTarget = Math.min(themeScrollTarget, m) < 0f ? 0f : Math.min(m, themeScrollTarget);
            return;
        }
        if (s.page < 6) {
            if (detailOpenNow()) {
                detailScrollTarget -= notch * 40f;
                float m = panel()[13];
                detailScrollTarget = Math.min(detailScrollTarget, m) < 0f ? 0f : Math.min(m, detailScrollTarget);
                return;
            }
            scrollTarget -= notch * 40f;
            Layout L = layout();
            float k = Math.max(1f, L.total / 600f);
            scrollTarget -= ((k * 40f) * 0.1f) * notch;
            scrollTarget = Math.min(scrollTarget, L.max) >= 0f ? Math.min(L.max, scrollTarget) : 0f;
            return;
        }
        if (s.page == 6) SettingsPage.scroll(dy);
        else if (s.page == 8) { if (!ConfigsPage.scroll(dy)) CreateConfigPage.scroll(dy); }
        else if (s.page == 9) SocialsPage.scroll(dy);
        else if (s.page == 10) KeybindsPage.scroll(dy);
    }

    // ============================================================== keyboard
    /** GLFW key -> Windows virtual key (what the original stores in Setting.key / dword_1804CCE84). */
    public static int toVk(int g) {
        if (g >= 65 && g <= 90) return g;
        if (g >= 48 && g <= 57) return g;
        switch (g) {
            case 32: return 32; case 39: return 222; case 44: return 188; case 45: return 189; case 46: return 190; case 47: return 191;
            case 59: return 186; case 61: return 187; case 91: return 219; case 92: return 220; case 93: return 221; case 96: return 192;
            case 256: return 27; case 257: return 13; case 258: return 9; case 259: return 8; case 260: return 45; case 261: return 46;
            case 262: return 39; case 263: return 37; case 264: return 40; case 265: return 38; case 266: return 33; case 267: return 34;
            case 268: return 36; case 269: return 35; case 280: return 20; case 281: return 145; case 282: return 144; case 283: return 44; case 284: return 19;
            case 330: return 110; case 331: return 111; case 332: return 106; case 333: return 109; case 334: return 107; case 335: return 13;
            case 340: return 160; case 341: return 162; case 342: return 164; case 343: return 91; case 344: return 161; case 345: return 163; case 346: return 165; case 347: return 92; case 348: return 93;
            default: break;
        }
        if (g >= 290 && g <= 313) return 112 + (g - 290);
        if (g >= 320 && g <= 329) return 96 + (g - 320);
        return 0;
    }

    public static void key(int glfwKey) {
        if (!active) return;
        syncMouse();
        if (WidgetsPage.key(glfwKey)) return;
        int vk = toVk(glfwKey);
        if (vk == 0) return;
        int pg = Ui.S.page;
        if (vk != menuKey && !textEdit && !capturing && !capturingMenuKey) {
            if (pg == 6 && SettingsPage.key(glfwKey)) return;
            if (pg == 8 && (ConfigsPage.key(glfwKey) || CreateConfigPage.key(glfwKey))) return;
            if (pg == 9 && SocialsPage.key(glfwKey)) return;
            if (pg == 10 && KeybindsPage.key(glfwKey)) return;
        }
        keyDown(vk);
        if (vk == 8 || vk == 13 || vk == 27) onChar(vk);     // Windows posts WM_CHAR for these
    }

    public static void typed(int cp) {
        if (!active) return;
        syncMouse();
        if (WidgetsPage.typed(cp)) return;
        int pg = Ui.S.page;
        if (!textEdit && !cpHexEdit && !Ui.S.searchFocused) {
            if (pg == 6 && SettingsPage.typed(cp)) return;
            if (pg == 8 && (ConfigsPage.typed(cp) || CreateConfigPage.typed(cp))) return;
            if (pg == 9 && SocialsPage.typed(cp)) return;
            if (pg == 10 && KeybindsPage.typed(cp)) return;
        }
        onChar(cp);
    }

    /** ESC: same as the ESC key (keydown + WM_CHAR(27)); sets {@link #close} when nothing is left to close. */
    public static void escape() { if (WidgetsPage.escape()) return; keyDown(27); onChar(27); }

    private static void commitText() {
        if (textSetting != null && vis().contains(textSetting)) { textSetting.sval = textBuf.toString(); dirty(textSetting); }
    }

    /** WM_KEYDOWN / WM_SYSKEYDOWN part of sub_180145320. */
    static void keyDown(int v5) {
        Ui.State s = Ui.S;
        if (v5 == menuKey) { snd("pop3"); close = true; return; }
        if (s.searchFocused) {
            if (v5 == 40 && !results.isEmpty()) { searchSel = Math.min(results.size() - 1, searchSel + 1); if (searchSel < 0) searchSel = 0; followSel(); snd("hover"); return; }
            if (v5 == 38 && !results.isEmpty()) { searchSel = searchSel - 1 >= 0 ? searchSel - 1 : 0; followSel(); snd("hover"); return; }
            if (v5 == 13 && searchSel >= 0 && searchSel < results.size()) {
                snd("pop2"); activateResult(results.get(searchSel)); clearSearch(); return;
            }
            if (v5 == 27) { snd("pop3"); clearSearch(); return; }
        } else if (v5 == 27 && textEdit) {
            commitText(); textEdit = false; textSetting = null; snd("pop3"); return;
        }
        if (v5 == 86 && ctrlDown && textEdit && textSetting != null) {          // ctrl+V
            String c = clipboard.get();
            if (c != null) { for (char ch : c.toCharArray()) if (ch >= 32 && ch <= 126) textBuf.append(ch); commitText(); }
        }
        if (textEdit) {
            if (v5 == 8) { if (textBuf.length() == 0) return; textBuf.setLength(textBuf.length() - 1); commitText(); snd("pop"); return; }
            if (v5 == 13) { commitText(); snd("pop"); textEdit = false; textSetting = null; return; }
        }
        if (capturingMenuKey) {
            if (v5 != 27 && v5 != 46) { menuKey = v5; capturingMenuKey = false; listener.settingChanged(-1, -1, -1); snd("pop2"); return; }
            capturingMenuKey = false; snd("pop3"); return;
        }
        if (v5 != 27 && v5 != 46) {
            if (capturing && capSetting != null && s.page < 6) {
                capSetting.key = v5;
                if (capPrevKey <= 0) capSetting.toggleMode = true;
                dirty(capSetting); endCapture(); snd("pop2");
            }
            return;
        }
        if (capturing && capSetting != null) {                   // ESC / DEL while capturing: unbind
            capSetting.key = 0; dirty(capSetting); endCapture(); snd("pop3");
            return;
        }
        if (v5 == 27) {
            if (cpOpen) { snd("dropdown"); closePicker(); return; }
            if (ddOpen || msOpen) { snd("dropdown"); ddOpen = false; ddSetting = null; msOpen = false; msSetting = null; return; }
            if (detailOpen && s.page < 6) { closeDetail(); snd("pop3"); return; }
            close = true;                                            // sub_1800DB680
        }
    }

    /** WM_CHAR part of sub_180145320. */
    static void onChar(int c) {
        Ui.State s = Ui.S; ModulesPage.St p = ModulesPage.P;
        if (c != 8 && textEdit && textSetting != null) {
            if (c >= 32 && c <= 126) { textBuf.append((char) c); commitText(); snd("pop"); }
            return;
        }
        if (cpHexEdit && cpOpen && cpSetting != null) {
            if (c != 8) {
                if (c == 13 || c == 10) {
                    float[] col = parseHex(cpHex.toString());
                    if (col != null && s.page < 6) { cpSetting.color = col; loadPickerKeepHex(col); dirty(cpSetting); snd("pop"); }
                    cpHexEdit = false; return;
                }
                if (c == 27) { cpHexEdit = false; snd("pop"); return; }
                boolean ok = (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f') || c == '#';
                if (!ok || cpHex.length() >= 9) return;
                cpHex.append((char) c);
                if (cpHex.length() != 7 && cpHex.length() != 9) return;
                float[] col = parseHex(cpHex.toString());
                if (col != null && s.page < 6) { cpSetting.color = col; loadPickerKeepHex(col); dirty(cpSetting); snd("pop"); }
                return;
            }
            if (cpHex.length() > 0) { cpHex.setLength(cpHex.length() - 1); snd("pop"); }
            return;
        }
        if (s.searchFocused) {
            if (c == 8) {
                if (!s.search.isEmpty()) {
                    s.search = s.search.substring(0, s.search.length() - 1);
                    searchSel = -1; searchScrollTarget = 0f; snd("pop"); resetSearchPopups();
                }
                return;
            }
            if (c == 13) { s.searchFocused = false; return; }
            if (c >= 32 && c <= 126) {
                s.search = s.search + (char) c;
                scrollTarget = 0f; searchSel = 0; searchScrollTarget = 0f; resetSearchPopups(); snd("pop");
            }
        }
    }
    private static void loadPickerKeepHex(float[] col) {
        float[] h = rgb2hsv(col[0], col[1], col[2]);
        cpHue = h[0]; cpSat = h[1]; cpVal = h[2]; cpAlpha = col[3];
    }
    private static void resetSearchPopups() {      // 893D=0, A22=0, ids -1 (897C itself stays)
        msOpen = false; cpOpen = false; ddSetting = null; msSetting = null; cpSetting = null;
    }
}
