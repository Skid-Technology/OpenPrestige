package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

/** Port of the Themes page (page 7) and sub_180127280 (theme card). */
public final class ThemesPage {
    private ThemesPage() {}

    public static float scroll;                 // 'result' scroll offset
    public static float enterB = Float.parseFloat(System.getProperty("enterB", "1"));
    public static int hover = -1;               // dword_1804CCECC
    public static float[] hoverA = new float[64], selA = new float[64], pressA = new float[64];

    static float stagger(float a1, int idx, float a3, float a4) {   // sub_180131AE0
        float v4 = idx * a3;
        if (a4 <= v4) v4 = a4;
        float v6 = 1f - v4;
        if (v6 <= 0.01f) v6 = 0.01f;
        float v7 = (a1 - v4) / v6;
        float v8 = v7 > 1f ? 1f : (v7 >= 0f ? v7 : 0f);
        return 1f - (float) Math.pow(1f - v8, 3.0);
    }

    public static void draw() {
        float v108 = Ui.S.sideW + 30f;
        float v107 = Ui.S.sideW;
        float v883 = 1100f - Ui.S.sideW;
        float v336 = 1000f - v108;
        textBox("Themes", v108, 30f, v336, 35f, 8, Theme.text(), false, false);
        textBox("Menu accent and colors", v108, 65f, v336, 20f, 3, Theme.muted(), false, false);
        pushClip(v107, 105f, v883, 580f);
        float v337 = 110f - scroll;
        float v338 = Lib.easeOutCubic(enterB);
        for (int jj = 0; jj < Theme.NAMES.length; jj++) {
            float x = (jj % 3) * 260f + v108;
            float y = (jj / 3) * 140f + v337;
            if (y + 120f >= 30f && y <= 700f) {
                float v342 = stagger(v338, jj, 0.05f, 0.6f);
                float y2 = (1f - v342) * 26f + y;
                boolean sel = jj == Theme.active, hov = jj == hover;
                pushTransform(x + 120f, y2 + 60f, 0f, v342 * 0.05f + 0.95f);
                card(jj, x, y2, sel, hov);
                popTransform();
            }
        }
        popClip();
    }

    /** sub_180127280 */
    static void card(int idx, float a2, float a3, boolean sel, boolean hov) {
        float dt = Ui.S.dt;
        hoverA[idx] = Lib.lerp(hoverA[idx], hov ? 1f : 0f, dt * 12f);
        selA[idx] = Lib.lerp(selA[idx], sel ? 1f : 0f, dt * 12f);
        pressA[idx] = Lib.lerp(pressA[idx], hov && ModulesPage.P.mouseDown ? 1f : 0f, dt * 30f);
        if (Lib.STEADY) { hoverA[idx] = hov ? 1f : 0f; selA[idx] = sel ? 1f : 0f; pressA[idx] = 0f; }
        float h = hoverA[idx], s = selA[idx], p = pressA[idx];
        float v19 = ((h * 0.02f + 1f) - p * 0.03f);
        float v20 = ((v19 * 240f) - 240f) * 0.5f, v21 = ((v19 * 120f) - 120f) * 0.5f;
        float v22 = a2 - v20, v23 = a3 - v21;
        float v24 = (v20 + v20) + 240f, v25 = (v21 + v21) + 120f;
        fillRect(v22 + 1f, (s + s) + (v23 + 5f), v24 - 2f, v25, 11f, new float[]{0f, 0f, 0f, s * 0.12f + 0.2f});
        if (s > 0.01f) {
            fillRect(v22 - 10f, v23 - 6f, v24 + 20f, v25 + 16f, 18f, new float[]{0f, 0f, 0f, s * 0.1f});
            float[] a = Theme.accent();
            fillRect(v22 - 3f, v23 - 3f, v24 + 6f, v25 + 6f, 13f, new float[]{a[0], a[1], a[2], s * 0.05f});
        }
        float[] c30 = Theme.sidebar(), c31 = Theme.accent();
        float v34 = Math.max(s * 1.5f, h) * 0.1f;
        float al = Math.max(h, s) * 0.1f + c30[3];
        float[] fill = {(c31[0] - c30[0]) * v34 + c30[0], (c31[1] - c30[1]) * v34 + c30[1], (c31[2] - c30[2]) * v34 + c30[2], al};
        fillRect(v22, v23, v24, v25, 10f, fill);
        fillRect(v22 + 6f, v23 + 0.5f, v24 - 12f, 1f, 0f, new float[]{1f, 1f, 1f, 0.0863f});
        float[] bd = lerp(Theme.e26C0(), Theme.e2850(), h);
        bd = lerp(bd, Theme.e29E0(), s);
        strokeRect(v22, v23, v24, v25, 10f, 1f, bd);
        float v54 = a3, v55 = a3 + 12f;
        float[][] sw = {Theme.slotOf(idx, 4), Theme.slotOf(idx, 6), Theme.slotOf(idx, 9), Theme.slotOf(idx, 10), Theme.slotOf(idx, 14)};
        for (int i = 0; i < 5; i++) {
            float xx = i * 43.2f + (a2 + 12f);
            if (i == 0) fillRectFlags(xx, v55, 43.2f, 36f, 6f, 80, sw[i]);
            else if (i == 4) fillRectFlags(xx, v55, 43.2f, 36f, 6f, 160, sw[i]);
            else fillRect(xx, v55, 43.2f, 36f, 0f, sw[i]);
        }
        // "GLASS" tag
        float[] gs = textSize("GLASS", 4);
        float v66 = gs[0] + 10f;
        float v67 = ((a2 + 240f) - 12f) - v66 - 6f;
        float v68 = v55 + 11f;
        float[] acc = Theme.slotOf(idx, 9);
        fillRect(v67, v68, v66, 14f, 3f, new float[]{acc[0], acc[1], acc[2], 0.22f});
        textBox("GLASS", v67, v68, v66, 14f, 4, Theme.slotOf(idx, 27), true, true);
        textBoxEllipsis(Theme.NAMES[idx], a2 + 12f, (v54 + 36f) + 20f, 216f, 22f, 5, Theme.text(), false, false);
        ModulesPage.wrapped(Theme.DESCS[idx], a2 + 12f, (v54 + 36f) + 42f, 216f, 4, Theme.muted(), 2);
        if (s > 0.01f) {
            float v76 = (a2 + 240f) - 28f, v77 = v54 + 52f;
            float v79 = (float) ((Math.pow(s - 1f, 3.0) * 4f + 1f) + Math.pow(s - 1f, 2.0) * 3f) * 10f;
            float[] g = Theme.e2210();
            float pulse = (((float) Math.sin((Ui.S.time * 1.2f) * 6.2831855f + v76 * 0.05f) * 0.5f + 0.5f) * 0.25f + 0.75f) * (s * 0.4f);
            circleFilled(v76, v77, v79 + 4f, new float[]{g[0], g[1], g[2], pulse});
            float[] a = Theme.accent();
            circleFilled(v76, v77, v79, new float[]{a[0], a[1], a[2], a[3] * s});
            float v88 = Math.min(s * 1.4f, 1f);
            float v89 = (float) Math.pow(1f - v88, 3.0);
            float ca = 1f - v89;
            float v90 = (1f - v89) * 13.46f;
            float v91 = Math.min(v90 / 4.24f, 1f);
            float[] wc = {1f, 1f, 1f, ca};
            line(v76 - 4f, v77, (v76 - 4f) + v91 * 3f, v91 * 3f + v77, 2f, wc);
            if (v90 > 4.24f) {
                float v95 = Math.min((v90 - 4.24f) / 9.22f, 1f);
                float v96 = v77 + 3f, v97 = v76 - 1f;
                line(v97, v96, v95 * 6f + v97, v96 - v95 * 7f, 2f, wc);
            }
        }
    }
}
