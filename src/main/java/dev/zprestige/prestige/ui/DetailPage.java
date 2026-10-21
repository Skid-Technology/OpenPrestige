package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Port of sub_18012EC00 (module detail panel) and sub_1801252E0 (setting row + widgets). */
public final class DetailPage {
    private DetailPage() {}

    public static final class Setting {
        public String name = "", desc = "";
        /** 0 keybind, 1 bool, 2 colour, 3 float slider, 4 int slider, 5 range, 6 enum, 7 multi, 8 text */
        public int type;
        public boolean visible = true;
        public boolean bval;            // bool (a1+68)
        public int key;                 // keybind (a1+216)
        public boolean toggleMode;      // keybind mode flag (a1+220)
        public float fval, fmin, fmax;  // float slider (a1+72/76/80)
        public int ival, imin, imax;    // int slider (a1+84/88/92)
        public float lo, hi, rmin, rmax; // range (a1+96..108)
        public String sval = "";        // enum / text current
        public String[] opts = new String[0];
        public float[] color = {1, 1, 1, 1};
        public dev.zprestige.prestige.ui.bridge.Bridge.LiveSetting live;   // running client setting, or null in fallback mode
        // animation state (static maps in the original, keyed by module+setting)
        public float a36, a40, hoverA, tipA, toggleT = -1f;
    }

    public static final class St {
        public ModulesPage.Mod mod;
        public List<Setting> settings = new ArrayList<>();
        public float scroll;               // dword_1807089C4
        public int hoverRow = Integer.getInteger("hoverRow", -1);          // dword_1804CCE48
        public String title;
    }
    public static final St D = new St();

    static float clamp01(float v) { return v < 0f ? 0f : Math.min(v, 1f); }

    /** Panel rect from sub_18012E5B0: x, y, w, h and row anchors. */
    public static void draw() {
        ModulesPage.St p = ModulesPage.P;
        float detail = p.detailT;
        if (detail <= 0.001f || D.mod == null) return;
        float px = Ui.S.sideW + 30f, py = 152f, pw = (1100f - Ui.S.sideW) - 60f, ph = 533f;
        float titleY = py + 20f, descY = titleY + 32f, lineY = descY + 40f, listTop = lineY + 8f;
        float v9 = Lib.easeInOutQuad(detail);
        float v10 = (1f - Lib.easeInOutCubic(detail)) * 40f;
        float v11 = clamp01(Math.min(1f, (detail - 0.06f) / 0.94f));
        float v13 = ((3f - (v11 + v11)) * (v11 * v11)) * v9;
        float v14 = clamp01(Math.min(1f, (detail - 0.14f) / 0.86f));
        float v15 = ((3f - (v14 + v14)) * (v14 * v14)) * v9;
        float v16 = v15;

        pushClip(px, py, pw, ph);
        float x = px + v10;
        fillRect(px + 2f + v10, py + 6f, pw - 4f, ph - 6f, 12f, new float[]{0f, 0f, 0f, v9 * 0.28f});
        float[] sb = Theme.sidebar();
        fillRect(x, py, pw, ph, 12f, new float[]{sb[0], sb[1], sb[2], v9 * 0.78f});
        fillRect(px + 8f + v10, py + 0.5f, pw - 16f, 1f, 0f, new float[]{1f, 1f, 1f, v9 * 0.08f});
        float[] b = Theme.e2850();
        strokeRect(x, py, pw, ph, 12f, 1f, new float[]{b[0], b[1], b[2], b[3] * v9});

        float textX = (px + 20f) + v10;
        float maxW = (pw - 40f) - 64f;
        String title = ellipsize(D.mod.name, 10, maxW);
        text(title, textX, titleY, 10, withAlpha(Theme.text(), v13));
        // optional badge (module tag) would go here (sub_18011F140)
        float tx = ((px + pw) - 20f - 44f) + v10, ty = titleY + 1f;
        boolean hot = false;
        D.mod.toggleT = Lib.lerp(D.mod.toggleT < 0f ? (D.mod.enabled ? 1f : 0f) : D.mod.toggleT, D.mod.enabled ? 1f : 0f, Ui.S.dt * 10f);
        ModulesPage.toggle(tx, ty, D.mod.enabled, hot, D.mod.toggleT, v13);
        ModulesPage.wrapped(D.mod.desc, textX, descY, pw - 40f, 4, withAlpha(Theme.e38F0(), v16), 2);
        fillRect(textX, lineY, pw - 40f, 1.5f, 0f, new float[]{0f, 0f, 0f, v15 * 0.45f});
        fillRect(textX, lineY + 1.5f, pw - 40f, 2f, 0f, new float[]{0f, 0f, 0f, v15 * 0.22f});
        fillRect(textX, lineY + 3.5f, pw - 40f, 3f, 0f, new float[]{0f, 0f, 0f, v15 * 0.1f});
        popClip();

        // ---- settings list ----
        pushClip(px, listTop, pw, (py + ph) - listTop);
        float oldA = gAlpha;
        gAlpha = clamp01(v9 * gAlpha);
        List<Setting> vis = new ArrayList<>();
        for (Setting s : D.settings) if (s.visible && !s.name.equals("Enabled")) vis.add(s);
        float y = (listTop + 14f) - D.scroll;
        for (int i = 0; i < vis.size(); i++) {
            Setting s = vis.get(i);
            boolean rowHot = D.hoverRow == i;
            s.hoverA = Lib.smooth(s.hoverA, rowHot ? 1f : 0f, Ui.S.dt, 12f);
            if (s.hoverA > 0.005f) {
                float v66 = (3f - (s.hoverA + s.hoverA)) * (s.hoverA * s.hoverA);
                if (v66 * 48f > 0.5f) {
                    float[] a = Theme.accent();
                    fillRect(x + 14f, ((53f - v66 * 48f) * 0.5f) + y, 2f, v66 * 48f, 0f, new float[]{a[0], a[1], a[2], v66 * 0.45f});
                }
            }
            if (Boolean.getBoolean("debugRows")) System.out.println("row " + s.name + " y=" + (y + Lib.oy) + " type=" + s.type);
            row(s, x, y, pw, rowHot);
            if (i != vis.size() - 1) fillRect(x + 20f, (y + 54f) - 1f, pw - 40f, 1f, 0f, new float[]{0f, 0f, 0f, 0.35f});
            y += 54f;
        }
        gAlpha = oldA;
        popClip();
    }

    // ------------------------------------------------------------------ rows

    static void row(Setting s, float a2, float a3, float a4, boolean hot) {
        float v12 = a4 + a2, v13 = v12 - 20f, v14 = a2 + 20f;
        float ctrl = 160f;
        switch (s.type) {
            case 0: ctrl = 248f; break;
            case 1: ctrl = 64f; break;
            case 2: ctrl = 76f; break;
            case 3: case 4: case 5: ctrl = 336f; break;
            case 8: ctrl = 260f; break;
            default: break;
        }
        float v16 = ((a4 - ctrl) - 12f) - 20f;
        boolean hasDesc = s.desc != null && !s.desc.isEmpty();
        float v19 = hasDesc ? a3 + 2f : a3 + 14f;
        if (!s.name.isEmpty() && v16 > 0f) {
            String nm = ellipsize(s.name, 10, v16);
            text(nm, v14, v19, 10, Theme.text());
        }
        if (hasDesc) textBoxEllipsis(s.desc, v14, a3 + 30f, v16, 16f, 4, Theme.e38F0(), false, false);

        switch (s.type) {
            case 0: keybind(s, v13, a3, hot); break;
            case 1: {
                s.toggleT = Lib.lerp(s.toggleT < 0f ? (s.bval ? 1f : 0f) : s.toggleT, s.bval ? 1f : 0f, Ui.S.dt * 10f);
                settingToggle(v12 - 64f, a3 + 16f, s.bval, hot, s.toggleT);
                break;
            }
            case 2: colorSwatch(v12 - 76f, a3 + 13f, 28f, s.color, hot, Input.cpShown == s ? Input.cpA : 0f); break;
            case 3: valueBox(v13, a3, String.format(Locale.ROOT, "%.2f", s.fval)); slider(v13 - 308f, a3 + 17f, 200f, 20f, s.fval, s.fmin, s.fmax, hot, false); break;
            case 4: valueBox(v13, a3, String.format(Locale.ROOT, "%d", s.ival)); slider(v13 - 308f, a3 + 17f, 200f, 20f, s.ival, s.imin, s.imax, hot, false); break;
            case 5: rangeRow(s, v13, a3, hot); break;
            case 6: dropdown(v12 - 160f, a3 + 13f, 140f, s.sval, 0f, Input.ddShown == s ? Input.ddA : 0f, 28f); break;
            case 7: WidgetsPage.multiSelectRow(s, v12 - 160f, a3 + 13f, hot); break;
            case 8: WidgetsPage.textFieldRow(s, v13 - 240f, a3 + 13f, hot); break;
            default: break;
        }
    }

    static void valueBox(float v13, float a3, String text) {
        fillRect(v13 - 96f, a3 + 15f, 96f, 24f, 4f, Theme.surface());
        strokeRect(v13 - 96f, a3 + 15f, 96f, 24f, 4f, 1f, Theme.e26C0());
        int font = textSize(text, 3)[0] > 80f ? 4 : 3;
        float[] sz = textSize(text, font);
        text(text, ((96f - sz[0]) * 0.5f) + (v13 - 96f), ((24f - sz[1]) * 0.5f) + (a3 + 15f), font, Theme.text());
    }

    static void rangeRow(Setting s, float v13, float a3, boolean hot) {
        String txt = String.format(Locale.ROOT, "%.1f – %.1f", s.lo, s.hi);
        if (textSize(txt, 4)[0] > 80f) txt = String.format(Locale.ROOT, "%.0f – %.0f", s.lo, s.hi);
        fillRect(v13 - 96f, a3 + 15f, 96f, 24f, 4f, Theme.surface());
        strokeRect(v13 - 96f, a3 + 15f, 96f, 24f, 4f, 1f, Theme.e26C0());
        float[] sz = textSize(txt, 4);
        text(txt, ((96f - sz[0]) * 0.5f) + (v13 - 96f), ((24f - sz[1]) * 0.5f) + (a3 + 15f), 4, Theme.text());
        float v73 = v13 - 308f;
        float v74 = (a3 + 17f) + 10f;
        fillRect(v73, v74 - 3f, 200f, 6f, 3f, new float[]{1f, 1f, 1f, 0.1f});
        float span = s.rmax - s.rmin;
        float a = Math.min(1f, (s.lo - s.rmin) / span); if (a < 0f) a = 0f;
        float bb = Math.min(1f, (s.hi - s.rmin) / span); if (bb < 0f) bb = 0f;
        float x1 = a * 200f + v73, x2 = bb * 200f + v73;
        fillRect(x1, v74 - 3f, x2 - x1, 6f, 3f, Theme.accent());
        knob(x1, v74, hot, false);
        knob(x2, v74, hot, false);
    }

    /** sub_18011F8A0 / knob of sub_18011F500. */
    static void knob(float cx, float cy, boolean hot, boolean drag) {
        float r = drag ? 8f : (hot ? 7f : 6f);
        float[] g = Theme.e2210();
        if (drag || hot) {
            for (int i = 0; i < 3; ) {
                float rr = i + (r + 1f);
                i++;
                circleFilled(cx, cy, rr, new float[]{g[0], g[1], g[2], 0.25f / i});
            }
        }
        circleFilled(cx, cy, r, Theme.accent());
        circleFilled(cx, cy, r - 3f, Theme.accent());
    }

    /** sub_18011F500 */
    static void slider(float x, float y, float w, float h, float val, float min, float max, boolean hot, boolean drag) {
        float cy = h * 0.5f + y;
        fillRect(x, cy - 3f, w, 6f, 3f, new float[]{1f, 1f, 1f, 0.1f});
        float t = Math.min(1f, (val - min) / (max - min)); if (t < 0f) t = 0f;
        float fw = t * w;
        fillRect(x, cy - 3f, fw, 6f, 3f, Theme.accent());
        knob(fw + x, cy, hot, drag);
    }

    /** sub_18011FB70: settings toggle (44x22). */
    static void settingToggle(float x, float y, boolean on, boolean hot, float t) {
        float v8 = t;
        float v10 = (float) (Math.pow(v8 - 1f, 3.0) * 2.70158f) + 1f;
        v10 = v10 + (float) (Math.pow(v8 - 1f, 2.0) * 1.70158f);
        float[] c = lerp(Theme.e2B70(), Theme.e2D00(), v8);
        if (hot) { c[0] = Math.min(c[0] * 1.15f, 1f); c[1] = Math.min(c[1] * 1.15f, 1f); c[2] = Math.min(c[2] * 1.15f, 1f); }
        fillRect(x, y, 44f, 22f, 11f, c);
        if (v8 > 0.1f) {
            float[] g = Theme.e2210();
            fillRect(x - 1f, y - 1f, 46f, 24f, 12f, new float[]{g[0], g[1], g[2], v8 * 0.2f});
        }
        strokeRect(x, y, 44f, 22f, 11f, 1f, Theme.e26C0());
        float x0 = ((x + 11f) + 2f);
        float cx = ((((x + 44f) - 11f) - 2f) - x0) * v10 + x0;
        circleFilled(cx, y + 11f, (hot ? 1.15f : 1f) * 8f, new float[]{1f, 1f, 1f, 1f});
    }

    /** sub_18011FF20: colour swatch with chevron. */
    static void colorSwatch(float a1, float a2, float a3, float[] col, boolean hot, float a6) {
        float v8 = a6 * 0.02f + 1f;
        float v9 = ((a3 * v8) - a3) * 0.5f;
        float v10 = ((v8 * (a3 + 28f)) - (a3 + 28f)) * 0.5f;
        float v11 = a2 - v9, v12 = a1 - v10;
        float v13 = (v9 + v9) + a3, v14 = (v10 + v10) + (a3 + 28f);
        fillRect(v12, v11, v14, v13, 4f, lerp(Theme.surface(), Theme.hover(), a6));
        strokeRect(v12, v11, v14, v13, 4f, 1f, lerp(Theme.e26C0(), Theme.accent(), a6));
        float v26 = a3 - 8f, v27 = a1 + 4f, v28 = a2 + 4f;
        fillRect(v27, v28, v26, v26, 3f, new float[]{0.3f, 0.3f, 0.3f, 1f});
        int v30 = (int) v26;
        for (int v29 = 0; v29 < v30; v29 += 4) {
            for (int i = 0; i < v30; i += 4) {
                if ((((v29 >>> 2) + (i >>> 2)) & 1) == 0) {
                    float v32 = v29 + v27, v33 = Math.min(v32 + 4f, v27 + v26);
                    float v34 = i + v28, v35 = Math.min(v34 + 4f, v28 + v26);
                    fillRect(v32, v34, v33 - v32, v35 - v34, 0f, new float[]{0.5f, 0.5f, 0.5f, 1f});
                }
            }
        }
        fillRect(v27, v28, v26, v26, 1f, col);
        strokeRect(v27, v28, v26, v26, 1f, 1f, Theme.e26C0());
        float[] cc = hot ? Theme.accent() : Theme.muted();
        float v38 = a3 * 0.5f + a2;
        float v39 = (a1 + (a3 + 28f)) - 10f;
        line(v39 - 3f, v38 - 2f, v39, v38 + 2f, 1.5f, cc);
        line(v39 + 3f, v38 - 2f, v39, v38 + 2f, 1.5f, cc);
    }

    /** sub_180121320: dropdown (closed). */
    static void dropdown(float a1, float a2, float a3, String label, float hover, float open, float a8) {
        float v11 = ((((hover * 0.01f) + 1f) * a3) - a3) * 0.5f;
        float v12 = v11 + v11, v13 = a1 - v11, v14 = v12 + a3;
        fillRect(v13, a2, v14, a8, 4f, lerp(Theme.surface(), Theme.hover(), hover));
        float v21 = Math.max(hover, open);
        strokeRect(v13, a2, v14, a8, 4f, 1f, lerp(Theme.e26C0(), Theme.accent(), v21));
        textBoxEllipsis(label, (a1 + 8f) - v11, a2 + 2f, (a3 - 36f) + v12, a8 - 4f, 3, Theme.text(), false, true);
        float[] cc = lerp(Theme.muted(), Theme.accent(), hover);
        float v33 = ((a1 + a3) - 12f) + v11;
        float v34 = a8 * 0.5f + a2;
        float c = (float) Math.cos(open * 3.1415901f), sn = (float) Math.sin(open * 3.1415901f);
        float v42 = ((c + c) + (sn * 0f)) + v34;
        float v43 = ((c * 0f) - (sn + sn)) + v33;
        float v39 = sn * -2f;
        float v40 = (c * 3f) - v39, v41 = (sn * 3f) + (c * -2f);
        line(((c * -3f) - v39) + v33, ((c * -2f) - (sn * 3f)) + v34, v43, v42, 1.5f, cc);
        line(v43, v42, v40 + v33, v41 + v34, 1.5f, cc);
    }

    /** keybind row (case 0): Hold/Toggle pill + key box. */
    static void keybind(Setting s, float v13, float a3, boolean hot) {
        String label = s.key == 0 ? "None" : keyName(s.key);
        float v94 = a3 + 13f, v95 = v13 - 228f;
        boolean disabled = s.key == 0;
        modePill(v95, v94, 96f, 28f, !s.toggleMode ? false : true, 0f, 0f, disabled, false);
        float bx = v13 - 120f;
        fillRect(bx, v94, 120f, 28f, 4f, Theme.surface());
        strokeRect(bx, v94, 120f, 28f, 4f, 1f, Theme.e26C0());
        textBoxEllipsis(label, bx + 8f, v94 + 2f, 104f, 24f, 4, Theme.text(), true, true);
    }

    /** sub_180121860: Hold / Toggle pill. */
    static void modePill(float a1, float a2, float a3, float a4, boolean toggleSel, float hl, float hr, boolean dim, boolean pressed) {
        float v13 = a3 * 0.5f, v14 = a4 * 0.5f;
        float v15 = dim ? 0.4f : 1f;
        float[] sf = Theme.surface();
        fillRect(a1, a2, a3, a4, v14, new float[]{sf[0], sf[1], sf[2], sf[3] * v15});
        if (!dim) {
            float[] ac = Theme.accent();
            if (toggleSel) { if (!pressed) fillRect(a1 + v13, a2, v13, a4, v14, ac); }
            else fillRect(a1, a2, v13, a4, v14, ac);
        }
        float[] bd = Theme.e26C0();
        float[] bdd = new float[]{bd[0], bd[1], bd[2], bd[3] * v15};
        line(a1 + v13, a2 + 3f, a1 + v13, (a2 + a4) - 3f, 1f, bdd);
        strokeRect(a1, a2, a3, a4, v14, 1f, bdd);
        float[] holdC, togC;
        if (toggleSel) { holdC = Theme.muted().clone(); togC = Theme.text().clone(); }
        else { holdC = Theme.text().clone(); togC = Theme.muted().clone(); }
        holdC[3] *= v15; togC[3] *= (pressed ? v15 * 0.4f : v15);
        float[] hs = textSize("Hold", 4);
        text("Hold", ((v13 - hs[0]) * 0.5f) + a1, ((a4 - hs[1]) * 0.5f) + a2, 4, holdC);
        float[] ts = textSize("Toggle", 4);
        text("Toggle", ((v13 - ts[0]) * 0.5f) + (a1 + v13), ((a4 - ts[1]) * 0.5f) + a2, 4, togC);
    }

    static String keyName(int k) {
        if (k >= 65 && k <= 90) return String.valueOf((char) k);
        if (k >= 48 && k <= 57) return String.valueOf((char) k);
        switch (k) {
            case 32: return "Space";
            case 9999: return "LMB"; case 9998: return "RMB"; case 9997: return "MMB";
            default: return "Key " + k;
        }
    }
}
