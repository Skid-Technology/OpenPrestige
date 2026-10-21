package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

/** sub_18012CC00 / sub_18012CA00: three animated ambient glow orbs behind the window. */
public final class Glow {
    private Glow() {}
    public static int SEGMENTS = Integer.getInteger("glowSeg", 0);

    public static void draw(float time) {
        if (Boolean.getBoolean("noGlow")) return;
        float[] c1 = Theme.slot(33), c2 = Theme.slot(34), c3 = Theme.slot(35);
        if (!(c1[3] > 0.001f || c2[3] > 0.001f || c3[3] > 0.001f)) return;
        pushClip(0f, 0f, 1100f, 700f);
        float v35 = time;
        float v36 = (float) Math.sin(v35 * 0.09f + 2.4f) * 18f;
        float v37 = (float) Math.sin(v35 * 0.15f) * 10f;
        float v38 = (float) Math.sin(v35 * 0.1f + 0.7f) * 14f;
        float v39 = (float) Math.sin(v35 * 0.12f + 1.9f) * 9f;
        float v40 = ((float) Math.sin(v35 * 0.18f * 6.2831855f) * 0.5f + 0.5f) * 0.1f + 0.9f;
        float v41 = (float) Math.sin(v35 * 0.13f + 1.2f) * 8f + 280f;
        float v42 = (float) Math.sin(v35 * 0.11f);
        orb(v42 * 12f + 550f, v41, 520f, c1, v40);
        float v43 = (float) Math.sin(v35 * 0.22f * 6.2831855f + 1.3f);
        orb(v36 + 120f, v37 + 504.00003f, 460f, c2, (((v43 * 0.5f + 0.5f) * 0.1f) + 0.9f) * 1.5f);
        float v44 = (float) Math.sin(v35 * 0.2f * 6.2831855f + 2.7f);
        orb(v38 + 1140f, v39 - 50f, 310f, c3, ((v44 * 0.5f + 0.5f) * 0.1f) + 0.9f);
        popClip();
    }

    /** sub_18012CA00 */
    static void orb(float cx, float cy, float radius, float[] col, float scale) {
        float v7 = Math.min(0.95f, scale * col[3]);
        if (v7 < 0.001f) return;
        float v10 = 1f - (float) Math.pow(1f - v7, 0.017857144f);
        for (int k = 55; k >= 0; k--) {
            float v13 = k / 55f;
            float v15 = v13 * v10;
            float r = (1f - v13) * radius;
            if (v15 >= 0.00025f) circleFilledSeg(cx, cy, r, new float[]{col[0], col[1], col[2], v15}, SEGMENTS);
        }
    }

    static void circleFilledSeg(float cx, float cy, float r, float[] c, int seg) {
        int cc = col(c);
        if ((cc & 0xFF000000) != 0 && r >= 0.5f) dl.addCircleFilled(ox + cx * scale, oy + cy * scale, r * scale, cc, seg);
    }
}
