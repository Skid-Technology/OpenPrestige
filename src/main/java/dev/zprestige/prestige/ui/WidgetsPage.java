package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Shared overlay widgets of the ClickGUI (ported from sub_180131B70 tail + sub_180122120/122650/180122BD0/18012C580 and
 * the input handler sub_180145320): enum / multi-select popups, multi-select and text-field row widgets, gamemode chip
 * strip, search box + results overlay, tooltip chip, modal dialog and toast.  draw() is called last each frame.
 */
public final class WidgetsPage {
    private WidgetsPage() {}

    // ------------------------------------------------------------------ state
    // WidgetsPage owns NO interaction state: open popups, hover, search, text editing and gamemode chips all live in Input.
    // Only draw-side animation helpers and the modal dialog / toast / tooltip live here.
    private static final Map<DetailPage.Setting, float[]> MCHK = new WeakHashMap<>();
    private static final Map<String, Float> appear = new HashMap<>();

    /** Selected-bits of a multi-select setting (a1+184 bitset); shares Input's flag store so writes reach the client. */
    public static boolean[] multi(DetailPage.Setting s) {
        return Input.multiFlags(s);
    }

    // toast / tooltip / dialog
    public static float toastTime;             // dword_180708A50 (seconds left)
    public static String toastMsg = "";        // qword_1806FC320
    public static String tipText = ""; public static float tipX, tipY, tipW; public static long tipUntil; // 1806FC248/268/26C/270/274
    private static float tipAnim;
    public static int dlgMode;                 // dword_180744BA0 (0 none, 1 confirm, 2 alert)
    public static int dlgSel;                  // dword_180744BE8
    public static String dlgTitle = "", dlgMsg = "";
    public static Runnable dlgOk;
    private static float dlgAnim;              // dword_180708A48

    static void dlgAnimSet(float v) { dlgAnim = v; }
    public static void toast(String msg) { toastMsg = msg; toastTime = 2.4f; }
    public static void tooltip(String text, float ax, float ay, float aw, long ms) { tipText = text; tipX = ax; tipY = ay; tipW = aw; tipUntil = System.currentTimeMillis() + ms; }
    public static void dialog(int mode, String title, String msg, Runnable onOk) { dlgMode = mode; dlgTitle = title; dlgMsg = msg; dlgOk = onOk; dlgSel = 0; }

    // ------------------------------------------------------------------ helpers
    private static float[] a(float[] c, float al) { return new float[]{c[0], c[1], c[2], c[3] * al}; }
    private static float clamp01(float v) { return v < 0f ? 0f : Math.min(v, 1f); }
    /** sub_1800E0BB0 */
    private static float backK(float t, float k) { float v = t - 1f; return (float) Math.pow(v, 3.0) * (k + 1f) + 1f + (float) Math.pow(v, 2.0) * k; }

    private static final float PANEL_BOTTOM = 152f + 533f, LIST_TOP = 252f;

    /** y of a settings row (Input geometry, sub_18012E980) or NaN when it is not a visible row. */
    static float rowY(DetailPage.Setting s) {
        int vi = Input.vis().indexOf(s);
        return vi < 0 ? Float.NaN : Input.rowY(vi);
    }
    /** x of the option-list popups: (px+pw)-160 (910 with the default sidebar, independent of its width). */
    static float popupX() { float[] p = Input.panel(); return (p[0] + p[2]) - 160f; }

    // ------------------------------------------------------------------ per-frame update (draw animations only)
    public static void update(float dt) {
        Ui.State s = Ui.S;
        // search result "appear" animations over Input.results
        boolean idle = !s.searchFocused && Input.searchAlpha < 0.01f;
        if (idle) appear.clear();
        else {
            Map<String, Float> next = new HashMap<>();
            for (Input.Result r : Input.results) {
                String k = key(r);
                Float v = appear.get(k);
                next.put(k, v == null ? 0f : Lib.smooth(v, 1f, dt, 12f));
            }
            appear.clear(); appear.putAll(next);
        }
        if (toastTime > 0f) toastTime = Math.max(0f, toastTime - dt);
        boolean tipOn = !tipText.isEmpty() && System.currentTimeMillis() < tipUntil;
        tipAnim = Lib.smooth(tipAnim, tipOn ? 1f : 0f, dt, 14f);
        dlgAnim = Lib.smooth(dlgAnim, dlgMode != 0 ? 1f : 0f, dt, 14f);
        if (dlgMode != 0) { float[] b = dlgButtons(); dlgSel = -1; for (int i = 0; i < b.length / 4; i++) if (Input.in(b[i*4], b[i*4+1], b[i*4+2], b[i*4+3])) dlgSel = i == 0 && dlgMode == 1 ? 1 : 0; }
        // multi check anims
        DetailPage.Setting ms = Input.msShown;
        if (ms != null) {
            float[] c = chk(ms); boolean[] sel = multi(ms);
            for (int i = 0; i < c.length && i < sel.length; i++) c[i] = Lib.smooth(c[i], sel[i] ? 1f : 0f, dt, 14f);
        }
    }

    private static float[] chk(DetailPage.Setting s) {
        float[] c = MCHK.get(s);
        if (c == null || c.length != s.opts.length) { c = new float[s.opts.length]; boolean[] b = multi(s); for (int i = 0; i < c.length; i++) c[i] = i < b.length && b[i] ? 1f : 0f; MCHK.put(s, c); }
        return c;
    }

    private static long resultIcon(Input.Result r) {
        Ui.State s = Ui.S;
        if (r.type == 0 || (r.type == 2 && r.page < 6)) return s.cats != null && r.page >= 0 && r.page < s.cats.length ? s.cats[r.page].icon : 0;
        int g = r.page - 6;
        if (r.type == 1) { if (s.general != null) for (Ui.Entry e : s.general) if (e.name.equalsIgnoreCase("configs")) return e.icon; return 0; }
        return s.general != null && g >= 0 && g < s.general.length ? s.general[g].icon : 0;
    }

    private static String key(Input.Result r) { return r.type + "|" + r.title + "|" + r.label; }

    // ------------------------------------------------------------------ input (modal dialog only; everything else is Input)
    /** Returns true when the click was swallowed by the modal dialog. */
    public static boolean click(int button) {
        if (dlgMode == 0) return false;
        if (button == 0) {
            float[] b = dlgButtons();
            for (int i = 0; i < b.length / 4; i++) if (Input.in(b[i*4], b[i*4+1], b[i*4+2], b[i*4+3])) {
                boolean ok = !(dlgMode == 1 && i == 0);
                dlgMode = 0;
                if (ok && dlgOk != null) dlgOk.run();
                return true;
            }
        }
        return true;
    }
    public static boolean scroll(double dy) { return dlgMode != 0; }
    /** GLFW key press while a dialog is up: ESC dismisses, everything else is swallowed. */
    public static boolean key(int k) {
        if (dlgMode == 0) return false;
        if (k == 256) dlgMode = 0;
        return true;
    }
    public static boolean typed(int cp) { return dlgMode != 0; }
    public static boolean escape() { if (dlgMode != 0) { dlgMode = 0; return true; } return false; }

    // ------------------------------------------------------------------ draw
    public static void draw() {
        Ui.State s = Ui.S;
        // popups live under the content clip (sideW,105,1100-sideW,580)
        pushClip(s.sideW, 105f, 1100f - s.sideW, 580f);
        if (s.page < 6) {
            DetailPage.Setting dd = Input.ddShown, ms = Input.msShown;
            if (dd != null && dd.opts.length > 0) {
                float ry = rowY(dd);
                if (!Float.isNaN(ry)) dropdownPopup(dd.opts, dd.sval, popupX(), Input.popupY(Input.vis().indexOf(dd), dd.opts.length * 28f), Input.ddA, Input.ddHover);
            }
            if (ms != null && ms.opts.length > 0) {
                float ry = rowY(ms);
                if (!Float.isNaN(ry)) multiSelectPopup(ms.opts, multi(ms), chk(ms), popupX(), Input.popupY(Input.vis().indexOf(ms), ms.opts.length * 28f), Input.msA, Input.msHover);
            }
            colorPickerPopup();
        }
        popClip();
        dialogDraw(dlgAnim);
        tooltipDraw(tipText, tipX, tipY, tipW, tipAnim);
        searchOverlay(Input.searchAlpha);
        if (toastTime > 0f) toastDraw(toastMsg, toastTime);
        strokeRect(0f, 0f, 1100f, 700f, 25f, 1f, Theme.e26C0());
    }

    /** Colour picker popup of a dN module row (generic picker sub_1801204C0 at the row popup position). */
    static void colorPickerPopup() {
        DetailPage.Setting st = Input.cpShown;
        if (st == null || Input.vis().indexOf(st) < 0) return;
        float[] g = Input.cpGeom(st);
        float old = gAlpha;
        gAlpha = clamp01(Input.cpA) * old;
        SettingsPage.colorPicker(g[6], g[7], Input.cpHue, Input.cpSat, Input.cpVal, Input.cpAlpha, Input.cpHex.toString(),
                Input.cpHexEdit, true, Input.cpB, g[6], g[7]);
        gAlpha = old;
    }

    // ------------------------------------------------------------------ popups
    private static void popupFrame(float x, float y, float h, float anim, float sc, float yo) {
        pushTransform(x + 70f, y, 0f, sc);
        fillRect(x + 2f, (y + 2f) - yo, 140f, h, 6f, new float[]{0f, 0f, 0f, anim * 0.3f});
        fillRect(x, y - yo, 140f, h, 6f, a(Theme.surface(), anim));
        strokeRect(x, y - yo, 140f, h, 6f, 1f, a(Theme.accent(), anim));
    }
    private static float stag(float anim, int i) {
        float st = i * 0.04f, den = Math.max(1f - st, 0.001f);
        return easeOutCubic(clamp01((anim - st) / den));
    }
    private static void hoverBar(float x, float y, int i, int n, float t) {
        float yy = y, hh = 28f; int fl = 256;
        if (i == 0) { yy = y + 1f; hh = 27f; fl = 304; }
        if (i == n - 1) { fl |= 0xC0; hh -= 1f; }
        fillRectFlags(x + 1f, yy, 138f, hh, 5f, fl, a(Theme.hover(), t));
    }

    /** Open enum list (clean.c 3521-3680). x = popup left (910), y = popup top, anim = dword_180708A60, hover = option idx. */
    public static void dropdownPopup(String[] opts, String cur, float x, float y, float anim, int hover) {
        if (opts.length == 0) return;
        float h = opts.length * 28f, k = backK(anim, 2.4f), sc = k * 0.08f + 0.92f, yo = (1f - k) * 8f;
        pushClip(Ui.S.sideW, LIST_TOP, 1100f - Ui.S.sideW, PANEL_BOTTOM - LIST_TOP);
        popupFrame(x, y, h, anim, sc, yo);
        for (int i = 0; i < opts.length; i++) {
            float t = stag(anim, i), iy = (i * 28f + y) - yo + (1f - t) * 6f;
            boolean selv = opts[i].equals(cur), hov = hover == i;
            if (hov) hoverBar(x, iy, i, opts.length, t);
            float[] c;
            if (selv) { circleFilled(x + 10f, iy + 14f, 3f, a(Theme.accent(), t)); c = Theme.accent(); }
            else c = hov ? Theme.text() : Theme.muted();
            textBoxEllipsis(opts[i], x + 20f, iy + 4f, 112f, 20f, 4, a(c, t), false, true);
        }
        popTransform(); popClip();
    }

    /** Open multi-select list (clean.c 3686-3915). chk = per-option check animations. */
    public static void multiSelectPopup(String[] opts, boolean[] sel, float[] chk, float x, float y, float anim, int hover) {
        if (opts.length == 0) return;
        float h = opts.length * 28f, k = backK(anim, 2.4f), sc = k * 0.08f + 0.92f, yo = (1f - k) * 8f;
        pushClip(Ui.S.sideW, LIST_TOP, 1100f - Ui.S.sideW, PANEL_BOTTOM - LIST_TOP);
        popupFrame(x, y, h, anim, sc, yo);
        for (int i = 0; i < opts.length; i++) {
            float t = stag(anim, i), iy = (i * 28f + y) - yo + (1f - t) * 6f;
            boolean on = sel[i], hov = hover == i;
            if (hov) hoverBar(x, iy, i, opts.length, t);
            float bx = x + 10f, by = (iy + 14f) - 6f, ca = chk[i];
            fillRect(bx, by, 12f, 12f, 2f, a(lerp(Theme.e2B70(), Theme.accent(), ca), t));
            if (ca > 0.02f) {
                float[] w = {1f, 1f, 1f, ca * t};
                float v = Math.min(1f, ca + ca);
                line(bx + 3f, by + 6f, (v + v) + (bx + 3f), (v * 3f) + (by + 6f), 1.5f, w);
                if (ca > 0.5f) { float u = Math.min(1f, (ca - 0.5f) + (ca - 0.5f)); line(bx + 5f, by + 9f, (u * 4f) + (bx + 5f), (by + 9f) - (u * 6f), 1.5f, w); }
            }
            float[] c = on ? Theme.accent() : hov ? Theme.text() : Theme.muted();
            textBoxEllipsis(opts[i], x + 28f, iy + 4f, 104f, 20f, 4, a(c, t), false, true);
        }
        popTransform(); popClip();
    }

    // ------------------------------------------------------------------ row widgets
    /** Setting row, type 7 (sub_180122120). x = (rowRight) - 160, y = rowY + 13. */
    public static void multiSelectRow(DetailPage.Setting s, float x, float y, boolean hot) {
        multiSelectRow(x, y, 140f, s.opts, multi(s), hot, 28f);
    }
    public static void multiSelectRow(float x, float y, float w, String[] opts, boolean[] sel, boolean hot, float h) {
        fillRect(x, y, w, h, 4f, hot ? Theme.hover() : Theme.surface());
        strokeRect(x, y, w, h, 4f, 1f, hot ? Theme.accent() : Theme.e26C0());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < opts.length; i++) if (i < sel.length && sel[i]) { if (sb.length() > 0) sb.append(", "); sb.append(opts[i]); }
        if (sb.length() == 0) sb.append("None");
        float v22 = (w - 20f) - 16f, v21 = y + 2f;
        if (v22 > 0f) {
            String t = ellipsize(sb.toString(), 3, v22);
            float[] sz = textSize(t, 3);
            text(t, x + 8f, (((h - 4f) - sz[1]) * 0.5f) + v21, 3, Theme.text());
        }
        float v28 = (w + x) - 12f, v29 = h * 0.5f + y, v30 = v29 - 2f, v31 = v29 + 2f;
        line(v28 - 3f, v30, v28, v31, 1.5f, Theme.muted());
        line(v28, v31, v28 + 3f, v30, 1.5f, Theme.muted());
    }

    /** Setting row, type 8 (sub_180122650). x = (rowRight-20) - 240, y = rowY + 13. */
    public static void textFieldRow(DetailPage.Setting s, float x, float y, boolean hot) {
        boolean ed = Input.textEdit && Input.textSetting == s;
        textFieldRow(x, y, 240f, ed ? Input.textBuf.toString() : (s.sval == null ? "" : s.sval), ed, hot, 28f);
    }
    public static void textFieldRow(float x, float y, float w, String text, boolean ed, boolean hot, float h) {
        float[] bd;
        if (ed) { fillRect(x, y, w, h, 4f, Theme.e1D60()); bd = Theme.accent(); }
        else { fillRect(x, y, w, h, 4f, hot ? Theme.hover() : Theme.surface()); bd = Theme.e26C0(); }
        strokeRect(x, y, w, h, 4f, 1f, bd);
        float v13 = x + 8f, v14 = w - 16f, tw = textSize(text, 4)[0];
        if (ed && tw > v14) {
            pushClip(x + 2f, y, w - 4f, h);
            textBox(text, (v14 - tw) + v13, y + 2f, v14, h - 4f, 4, Theme.text(), false, true);
            popClip();
        } else textBoxEllipsis(text, v13, y + 2f, v14, h - 4f, 4, Theme.text(), false, true);
        if (ed) {
            float cx = Math.min((w + x) - 8f, tw + v13);
            line(cx, y + 4f, cx, (y + h) - 4f, 1.5f, Theme.accent());
        }
    }

    // ------------------------------------------------------------------ gamemode chips
    static float[][] chipRects() {
        float sideW = Ui.S.sideW, x0 = sideW + 30f, bx = x0 + 4f, bw = textSize("Gamemodes", 3)[0] + 28f;
        float x = (bw + bx) + 12f, y = ((36f - 22f) * 0.5f) + 98f;
        float[][] r = new float[6][];
        for (int i = 0; i < 6; i++) {
            float w = textSize(ModulesPage.GAMEMODES[i], 4)[0] + 18f;
            r[i] = new float[]{x, y, w, 22f};
            x += w + 5f;
        }
        return r;
    }

    /** Chip strip of the modules header (clean.c 2966-3025). gm = flt_1807089A8, detail = dword_180708A28. */
    public static void gamemodeStrip(float gm, float detail, float[] sel, float[] hov) {
        float v160 = (1f - detail) * gm;
        if (v160 <= 0.01f) return;
        float[] ac = Theme.accent();
        float[][] r = chipRects();
        for (int i = 0; i < 6; i++) {
            float x = r[i][0] - ((i * 15f + 40f) * (1f - gm)), y = r[i][1], w = r[i][2], h = r[i][3];
            float s = sel[i], hv = hov[i];
            float[] f = {lerp(ac[0] * 0.3f, ac[0], s), lerp(ac[1] * 0.3f, ac[1], s), lerp(ac[2] * 0.3f, ac[2], s), lerp((hv * 0.08f) + 0.12f, 0.25f, s) * v160};
            rect(x, y, w, h, h * 0.5f, true, f);
            if (s > 0.01f) rect(x, y, w, h, h * 0.5f, false, new float[]{ac[0], ac[1], ac[2], (s * 0.4f) * v160});
            float al = Math.min(1f, lerp(0.65f, 0.95f, s) + hv * 0.15f);
            textBox(ModulesPage.GAMEMODES[i], x, y, w, h, 4, new float[]{lerp(0.6f, 0.93f, s), lerp(0.6f, 0.91f, s), lerp(0.68f, 1f, s), v160 * al}, true, true);
        }
    }

    // ------------------------------------------------------------------ search
    /** Sidebar search box incl. focus glow, pulsing icon, overflow clip and caret (clean.c 435-518). */
    public static void searchBox() {
        Ui.State s = Ui.S;
        rect(760f, 42f, 310f, 36f, 8f, true, Theme.e31C0());
        float f = s.searchFocus;
        rect(760f, 42f, 310f, 36f, 8f, false, lerp(Theme.border(), Theme.accent(), f * 0.6f));
        if (f > 0.01f) for (int ii = 2; ii >= 1; ii--) rect(760f - ii, 42f - ii, ii + ii + 310f, ii + ii + 36f, ii + 8f, true, a(Theme.accent(), (0.055f / ii) * f));
        searchIcon(774f, 51f, 18f, lerp(Theme.muted(), Theme.accent(), f * 0.8f), s.searchFocused);
        String q = s.search == null ? "" : s.search;
        if (q.isEmpty()) textBox("Search modules", 800f, 51f, 250f, 18f, 3, Theme.muted(), false, false);
        else {
            float tw = textSize(q, 3)[0];
            if (s.searchFocused && tw > 250f) { pushClip(800f, 44f, 250f, 32f); textBox(q, (250f - tw) + 800f, 51f, 250f, 18f, 3, Theme.text(), false, false); popClip(); }
            else textBoxEllipsis(q, 800f, 51f, 250f, 18f, 3, Theme.text(), false, false);
        }
        if (s.searchFocused && Input.caretOn) {
            float cx = Math.min(1050f, textSize(q, 3)[0] + 802.5f);
            line(cx, 48f, cx, 72f, 1.5f, Theme.text());
        }
    }

    /** sub_18011E540: search icon, pulsing when focused. */
    static void searchIcon(float x, float y, float size, float[] c, boolean focused) {
        float v10 = size * 0.35f, cx = v10 + x + 2f, cy = v10 + y + 2f, k = 1f;
        if (focused) {
            k = (((float) Math.sin((Ui.S.time * 1.6f) * 6.2831855f) * 0.5f + 0.5f) * 0.05f) + 1f;
            circleFilled(cx, cy, (k * v10) + 3f, a(Theme.e2210(), 0.35f));
        }
        float r = k * v10;
        if (dl != null && (col(c) & 0xFF000000) != 0 && r >= 0.5f) { float[] p = pt(cx, cy); dl.addCircle(p[0], p[1], r * scale, col(c), 0, 1.5f * scale); }
        line(r * 0.7f + cx, r * 0.7f + cy, (x + size) - 2f, (y + size) - 2f, 1.5f, c);
    }

    /** Results overlay under the search box (clean.c 3977-4480). */
    public static void searchOverlay(float sa) {
        if (sa <= 0.005f) return;
        float x = 740f, y = 86f, w = 350f, h = Input.searchPopupH, an = easeOutCubic(sa), y0 = y - (1f - an) * 8f;
        for (int i = 3; i >= 1; i--) fillRect(x - (i + i), y0 - (i + i), (i * 4f) + w, (i * 4f) + h, i + 16f, new float[]{0f, 0f, 0f, an * 0.12f});
        fillRect(x, y0, w, h, 14f, a(Theme.surface(), an));
        strokeRect(x, y0, w, h, 14f, 1f, a(Theme.e26C0(), an));
        float ix = x + 8f, iy = y0 + 12f, iw = w - 16f, ih = h - 24f;
        pushClip(ix, iy, iw, ih);
        java.util.List<Input.Result> results = Input.results;
        if (results.isEmpty()) {
            float[] hint = a(Theme.e38F0(), an), mut = a(Theme.muted(), an), txt = a(Theme.text(), an);
            float cx = (iw * 0.5f) + ix, cy = ((ih * 0.5f) + iy) - 22f;
            for (int i = 0; i < 24; ) {
                float a0 = ((float) i++ / 24f) * 6.2831855f, a1 = ((float) i / 24f) * 6.2831855f;
                line((float) Math.cos(a0) * 11f + cx, (float) Math.sin(a0) * 11f + cy, (float) Math.cos(a1) * 11f + cx, (float) Math.sin(a1) * 11f + cy, 2f, hint);
            }
            line((float) Math.cos(0.785f) * 11f + cx, (float) Math.sin(0.785f) * 11f + cy, (float) Math.cos(0.785f) * 18f + cx, (float) Math.sin(0.785f) * 18f + cy, 2.5f, hint);
            String q = Ui.S.search == null ? "" : Ui.S.search;
            float my = (ih * 0.5f) + iy;
            if (!q.isEmpty()) {
                textBoxEllipsis("No results for \"" + q + "\"", ix, my + 4f, iw, 22f, 3, txt, true, true);
                textBox("Try a different search", ix, my + 28f, iw, 18f, 4, mut, true, true);
            } else {
                textBox("Type to search", ix, my + 4f, iw, 22f, 3, txt, true, true);
                textBox("Cracked by skid solutions", ix, my + 28f, iw, 18f, 4, mut, true, true);
            }
        } else {
            float cy = iy - Input.searchScrollCur;
            int prev = 0, pc = -1; boolean first = true;
            float rx = ix + 4f, rw = iw - 8f, a14 = an * 0.14f, a90 = an * 0.9f;
            float tx = (rx + 12f) + 22f + 12f, tw = (((rw - (tx - rx)) - 12f) - 150f) - 10f, right = rx + rw;
            for (int i = 0; i < results.size(); i++) {
                Input.Result r = results.get(i);
                if (first || r.type != prev) {
                    String lab = r.type == 0 ? "MODULES" : r.type == 1 ? "CONFIG TABS" : "CATEGORIES";
                    textBox(lab, ix + 14f, cy + 4f, iw - 28f, 22f, 4, a(Theme.accent(), a90), false, false);
                    cy += 26f; prev = r.type; pc = -1; first = false;
                }
                if (r.type == 0 && r.page != pc) {
                    String cn = r.page >= 0 && r.page < Ui.S.cats.length ? Ui.S.cats[r.page].name : "Other";
                    float[] bc = Theme.border();
                    line(ix + 14f, cy + 2f, (iw + ix) - 14f, cy + 2f, 1f, new float[]{bc[0], bc[1], bc[2], bc[3] * (an * 0.6f)});
                    textBox(cn, (ix + 14f) + 4f, cy + 4f, (iw - 28f) - 4f, 18f, 4, a(Theme.muted(), a90), false, false);
                    cy += 22f; pc = r.page;
                }
                boolean sel = Input.searchSel == i;
                Float ap = appear.get(key(r));
                float av = ap == null ? 1f : ap;
                float st = i * 0.03f, den = Math.max(1f - st, 0.001f);
                float v821 = clamp01((an - st) / den);
                float v823 = 1f - (float) Math.pow(1f - Math.min(av, v821), 3.0);
                float iy2 = cy - (1f - v823) * 6f;
                if (sel) {
                    float[] ac = Theme.accent();
                    fillRect(rx, iy2 + 2f, rw, 42f, 8f, new float[]{ac[0], ac[1], ac[2], ac[3] * (an * 0.18f) * v823});
                    strokeRect(rx, iy2 + 2f, rw, 42f, 8f, 1f, new float[]{ac[0], ac[1], ac[2], ac[3] * (an * 0.35f) * v823});
                }
                float yy = iy2 + 12f, ic = rx + 12f, al = v823 * an;
                float[] icc = a(sel ? Theme.accent() : Theme.muted(), al);
                long icon = resultIcon(r);
                if (icon != 0) image(icon, ic, yy, 22f, 22f, icc); else strokeRect(ic, yy, 22f, 22f, 4f, 1f, icc);
                textBoxEllipsis(r.title, tx, iy2 + 6f, tw, 20f, 5, a(Theme.text(), al), false, false);
                textBoxEllipsis(r.label, tx, iy2 + 26f, tw, 16f, 4, a(Theme.muted(), al), false, false);
                String lab = r.type == 0 ? "Module" : r.type == 1 ? "Config" : "Category";
                float bw = textSize(lab, 4)[0] + 20f, bxp = (right - bw) - 12f;
                fillRect(bxp, iy2 + 13f, bw, 20f, 10f, a(Theme.accent(), v823 * a14));
                textBox(lab, bxp, iy2 + 13f, bw, 20f, 4, a(Theme.accent(), a90 * v823), true, true);
                cy += 46f;
            }
        }
        popClip();
    }

    // ------------------------------------------------------------------ toast / tooltip / dialog
    /** Bottom-right toast (clean.c 4488-4580). */
    public static void toastDraw(String msg, float left) {
        float v846 = left >= 0.4f ? (left <= 2f ? 1f : (2.4f - left) / 0.4f) : left / 0.4f;
        float w = Math.min(1052f, textSize(msg, 3)[0] + 36f), x = (1100f - w) - 24f;
        float e = ((float) Math.pow(v846 - 1f, 3.0) * 3.4f + 1f);
        e = e + (float) Math.pow(v846 - 1f, 2.0) * 2.4f;
        float y = (1f - e) * 22f + 638f;
        pushTransform(w * 0.5f + x, y + 19f, 0f, e * 0.14f + 0.86f);
        for (int i = 3; i >= 1; i--) fillRect(x - (i + i), (y - (i + i)) + 2f, w + i * 4f, i * 4f + 38f, i + 10f, new float[]{0f, 0f, 0f, v846 * 0.15f});
        float pulse = (((float) Math.sin((Ui.S.time * 1.6f) * 6.2831855f) * 0.5f + 0.5f) * 0.5f) + 0.5f;
        for (int i = 3; i >= 1; i--) { float v = i * 3f; fillRect(x - v, y - v, w + v + v, v + v + 38f, v + 10f, a(Theme.accent(), ((0.08f / i) * v846) * pulse)); }
        float[] sb = Theme.sidebar();
        fillRect(x, y, w, 38f, 10f, new float[]{sb[0], sb[1], sb[2], v846 * 0.95f});
        rect(x, y, w, 38f, 10f, false, a(Theme.e2850(), v846));
        float r = pulse * 1.5f + 4f;
        circleFilled(x + 14f, y + 19f, r + 3f, new float[]{Theme.e2210()[0], Theme.e2210()[1], Theme.e2210()[2], (v846 * 0.6f) * pulse});
        circleFilled(x + 14f, y + 19f, r, a(Theme.accent(), v846));
        textBoxEllipsis(msg, x + 26f, y, w - 36f, 38f, 3, a(Theme.text(), v846), false, true);
        popTransform();
    }

    /** Small tooltip chip above an element (sub_18012C580). */
    public static void tooltipDraw(String text, float ax, float ay, float aw, float an) {
        if (an < 0.005f || text.isEmpty()) return;
        float w = Math.max(aw, textSize(text, 4)[0] + 24f), x = ((aw * 0.5f) + ax) - (w * 0.5f);
        float y = (ay - 28f) - 8f;
        if (y < 38f) y = (ay + 24f) + 8f;
        float e = (float) Math.pow(an - 1f, 3.0) * 3.4f + 1f + (float) Math.pow(an - 1f, 2.0) * 2.4f;
        pushTransform((w * 0.5f) + x, y + 14f, 0f, e * 0.1f + 0.9f);
        float y2 = y - (1f - e) * 6f;
        fillRect(x - 2f, y2 + 2f, w + 4f, 31f, 8f, new float[]{0f, 0f, 0f, an * 0.28f});
        fillRect(x, y2, w, 28f, 8f, a(Theme.hover(), an * 0.98f));
        strokeRect(x, y2, w, 28f, 8f, 1f, a(Theme.accent(), an));
        textBox(text, x + 12f, y2, w - 24f, 28f, 4, a(Theme.text(), an), true, true);
        popTransform();
    }

    private static float dlgH() { return dlgMode == 1 ? 180f : 140f; }
    /** Button rects (x,y,w,h) in order: [cancel,]confirm. */
    private static float[] dlgButtons() {
        float h = dlgH(), y = ((700f - h) * 0.5f + h) - 55f;
        if (dlgMode == 1) return new float[]{370f, y, 175f, 35f, 555f, y, 175f, 35f};
        if (dlgMode == 2) return new float[]{370f, y, 360f, 35f};
        return new float[0];
    }
    private static void accentButton(float x, float y, float w, float h, boolean hot) {
        float[] ac = Theme.accent(), c;
        if (hot) {
            for (int i = 3; i >= 1; i--) { float v = i * 3f; fillRect(x - v, y - v, v + v + w, v + v + h, v + 6f, new float[]{ac[0], ac[1], ac[2], 0.1f / i}); }
            c = ac;
        } else c = new float[]{ac[0] * 0.85f, ac[1] * 0.85f, ac[2] * 0.85f, 1f};
        fillRect(x, y, w, h, 6f, c);
    }

    /** Modal confirm/alert dialog (sub_180122BD0). */
    public static void dialogDraw(float an) {
        if (an < 0.005f && dlgMode == 0) return;
        fillRect(0f, 0f, 1100f, 700f, 0f, new float[]{0.02f, 0.02f, 0.05f, an * 0.62f});
        if (dlgMode == 0) return;
        float h = dlgH(), top = (700f - h) * 0.5f;
        float e = (float) Math.pow(an - 1f, 3.0) * 3.4f + 1f + (float) Math.pow(an - 1f, 2.0) * 2.4f;
        pushTransform(550f, h * 0.5f + top, 0f, e * 0.12f + 0.88f);
        for (int i = 0; i < 3; i++) { float k = i * 5f + 2f; fillRect(350f - k, (i * 3f + 4f) + top, k + k + 400f, k + h, k + 12f, new float[]{0f, 0f, 0f, (0.18f - i * 0.05f) * an}); }
        float[] sb = Theme.sidebar();
        fillRect(350f, top, 400f, h, 12f, new float[]{sb[0], sb[1], sb[2], an * 0.97f});
        fillRect(358f, top + 0.5f, 384f, 1f, 0f, new float[]{1f, 1f, 1f, an * 0.1f});
        strokeRect(350f, top, 400f, h, 12f, 1f, a(Theme.e2850(), an));
        textBoxEllipsis(dlgTitle, 370f, top + 20f, 360f, 30f, 5, Theme.text(), false, false);
        float lh = textSize("Ag", 3)[1] + 2f;
        int maxLines = lh <= 0f ? 1 : Math.max(1, (int) ((h - 110f) / lh));
        ModulesPage.wrapped(dlgMsg, 370f, top + 55f, 360f, 3, Theme.muted(), maxLines);
        float[] b = dlgButtons(), white = {1f, 1f, 1f, 1f};
        if (dlgMode == 1) {
            boolean cs = dlgSel == 1;
            fillRect(370f, b[1], 175f, 35f, 6f, new float[]{sb[0], sb[1], sb[2], cs ? 0.85f : 0.65f});
            strokeRect(370f, b[1], 175f, 35f, 6f, 1f, cs ? Theme.e2850() : Theme.e26C0());
            textBox("Cancel", 370f, b[1], 175f, 35f, 3, Theme.text(), true, true);
            accentButton(555f, b[1], 175f, 35f, dlgSel == 0);
            textBox("Confirm", 555f, b[1], 175f, 35f, 3, white, true, true);
        } else {
            accentButton(370f, b[1], 360f, 35f, dlgSel == 0);
            textBox("OK", 370f, b[1], 360f, 35f, 3, white, true, true);
        }
        popTransform();
    }
}
