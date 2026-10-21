package dev.zprestige.prestige.ui;

import imgui.ImDrawList;
import imgui.ImFont;
import imgui.ImGui;
import imgui.ImVec2;

/**
 * Java mirror of the original client's draw wrappers (ImDrawList helpers in java-product.dll,
 * sub_180119xxx..sub_18011Exxx). Names are the semantic names I gave the IDA functions.
 * All colours are float[4] (r,g,b,a) like the original; global alpha is applied at conversion.
 */
public final class Lib {
    public static ImDrawList dl;
    /** dword_180744E9C / dword_180744E98: window origin added to every position. */
    public static float ox, oy;
    /** dword_1806FC238: global alpha multiplier. */
    public static float gAlpha = 1f;
    /** UI scale (1 = native 1100x700 canvas). */
    public static float scale = 1f;

    /** qword_1807453B0[] / dword_180371DD0[]: fonts 0..10 and their pixel sizes. */
    public static final ImFont[] FONT = new ImFont[11];
    public static final float[] FONT_SIZE = {26, 29, 20, 17, 14, 18, 24, 24, 34, 34, 20};
    /** Pixel size each font is actually baked at (FONT_SIZE * scale, rounded). */
    public static float[] BAKED = FONT_SIZE.clone();

    private Lib() {}

    /** sub_180119F30: float rgba -> ImU32 (IM_COL32 layout), global alpha applied. */
    public static int col(float[] c) {
        float a = gAlpha * c[3];
        float v1 = a >= 0f ? Math.min(1f, a) : 0f;
        return (int) (c[0] * 255f + 0.5f) | ((int) (c[1] * 255f + 0.5f) << 8) | ((int) (c[2] * 255f + 0.5f) << 16) | ((int) (v1 * 255f + 0.5f) << 24);
    }

    public static float[] withAlpha(float[] c, float a) { return new float[]{c[0], c[1], c[2], c[3] * a}; }

    public static float[] lerp(float[] a, float[] b, float t) {
        return new float[]{a[0] + (b[0] - a[0]) * t, a[1] + (b[1] - a[1]) * t, a[2] + (b[2] - a[2]) * t, a[3] + (b[3] - a[3]) * t};
    }

    // ---- math helpers (sub_1800E0400 / E0C40 / E0410 / E0A70) ----
    public static float lerp(float a, float b, float t) { return (b - a) * t + a; }
    /** sub_1800E0C40: exponential smoothing. */
    public static final boolean STEADY = Boolean.getBoolean("steady");
    public static float smooth(float cur, float target, float dt, float speed) {
        if (STEADY) return target;
        if (dt > 0f) return (1f - (float) Math.exp(-speed * dt)) * (target - cur) + cur;
        return cur;
    }
    /** sub_1800E0410 */
    public static float easeOutCubic(float t) { return 1f - (float) Math.pow(1f - t, 3.0); }
    /** sub_1800E0AE0 (ease out back, 1.70158) */
    public static float easeOutBack(float a) { float v = a - 1f; return (float) Math.pow(v, 3.0) * 2.70158f + 1f + (float) Math.pow(v, 2.0) * 1.70158f; }
    /** sub_1800E0B50 (ease in-out quad) */
    public static float easeInOutQuad(float a) {
        float v = a + a;
        if (a >= 0.5f) return 1f - (float) Math.pow(2.0 - v, 2.0) * 0.5f;
        return v * a;
    }
    /** sub_1800E0A70 */
    public static float easeInOutCubic(float t) {
        if (t >= 0.5f) return 1f - (float) Math.pow(2.0 - (t + t), 3.0) * 0.5f;
        return t * 4f * t * t;
    }

    /** sub_18011A1F0 / A290: transform stack entries {px, py, cos, sin, scale}. */
    private static final java.util.ArrayList<float[]> XF = new java.util.ArrayList<>();
    public static void pushTransform(float px, float py, float angle, float scale) {
        XF.add(new float[]{px, py, (float) Math.cos(angle), (float) Math.sin(angle), scale});
    }
    public static void popTransform() { if (!XF.isEmpty()) XF.remove(XF.size() - 1); }
    public static boolean hasTransform() { return !XF.isEmpty(); }
    /** sub_180119E50: local point -> screen point. */
    static float[] pt(float x, float y) {
        if (XF.isEmpty()) return new float[]{ox + x * scale, oy + y * scale};
        float[] t = XF.get(XF.size() - 1);
        float v5 = (x - t[0]) * t[4], v6 = (y - t[1]) * t[4];
        return new float[]{ox + ((t[2] * v5 - t[3] * v6) + t[0]) * scale, oy + ((t[3] * v5 + t[2] * v6) + t[1]) * scale};
    }

    // ---- primitives ----
    /** sub_18011A490 / sub_18011DED0(filled=true) */
    public static void fillRect(float x, float y, float w, float h, float rounding, float[] c) {
        if (dl == null) return;
        float[] a = pt(x, y), b = pt(x + w, y + h);
        if (rounding > 0.5f || XF.isEmpty()) { dl.addRectFilled(a[0], a[1], b[0], b[1], col(c), rounding > 0.5f ? rounding * scale : 0f, 0); return; }
        float[] q2 = pt(x + w, y), q4 = pt(x, y + h);
        dl.addQuadFilled(a[0], a[1], q2[0], q2[1], b[0], b[1], q4[0], q4[1], col(c));
    }
    /** sub_18011A640 / sub_18011DED0(filled=false) */
    public static void strokeRect(float x, float y, float w, float h, float rounding, float thickness, float[] c) {
        if (dl == null) return;
        float[] a = pt(x, y), b = pt(x + w, y + h);
        dl.addRect(a[0], a[1], b[0], b[1], col(c), rounding * scale, 0, thickness * scale);
    }
    /** AddRectFilled with explicit ImDrawFlags (sub_1800A2790 direct). */
    public static void fillRectFlags(float x, float y, float w, float h, float rounding, int flags, float[] c) {
        if (dl == null) return;
        float[] a = pt(x, y), b = pt(x + w, y + h);
        dl.addRectFilled(a[0], a[1], b[0], b[1], col(c), rounding * scale, flags);
    }
    /** sub_18011DED0 */
    public static void rect(float x, float y, float w, float h, float rounding, boolean filled, float[] c) {
        if (filled) fillRect(x, y, w, h, rounding, c); else strokeRect(x, y, w, h, rounding, 1f, c);
    }
    /** sub_18011A6E0 */
    public static void line(float x1, float y1, float x2, float y2, float thickness, float[] c) {
        if (dl == null) return;
        float[] a = pt(x1, y1), b = pt(x2, y2);
        dl.addLine(a[0], a[1], b[0], b[1], col(c), thickness * scale);
    }
    /** sub_180119FF0 */
    public static void pushClip(float x, float y, float w, float h) {
        if (dl == null) return;
        dl.pushClipRect(ox + x * scale, oy + y * scale, ox + (x + w) * scale, oy + (y + h) * scale, true);
    }
    /** sub_18011A170 */
    public static void popClip() { if (dl != null) dl.popClipRect(); }

    /** sub_1800ABE50 via sub_18011AB50/AAF0: text size. */
    public static float[] textSize(String s, int font) {
        ImFont f = FONT[font];
        ImGui.pushFont(f, BAKED[font]);
        ImVec2 v = new ImVec2();
        ImGui.calcTextSize(v, s);
        ImGui.popFont();
        float k = FONT_SIZE[font] / BAKED[font];
        return new float[]{v.x * k, v.y * k};
    }

    /** sub_18011AC80 / ABF0: draw text with the original's drop shadow. */
    public static void text(String s, float x, float y, int font, float[] c) {
        if (dl == null || s == null || s.isEmpty()) return;
        ImFont f = FONT[font];
        float size = FONT_SIZE[font];
        boolean groupA = font <= 1 || (font - 6) <= 3 && font >= 6;
        boolean groupB = (font >= 2 && font <= 5) || font == 10;
        float a = gAlpha * c[3];
        if (a > 0.05f && (groupA || groupB)) {
            int sa = (int) ((groupA ? 140f : 70f) * a);
            float[] ps = pt(x + 1f, y + 1f);
            if (sa != 0) dl.addText(f, (int) BAKED[font], ps[0], ps[1], sa << 24, s);
        }
        int cc = col(c);
        float[] pp = pt(x, y);
        if ((cc & 0xFF000000) != 0) dl.addText(f, (int) BAKED[font], pp[0], pp[1], cc, s);
    }

    /** sub_18011AF00: text inside a box, optionally centred. */
    public static void textBox(String s, float x, float y, float w, float h, int font, float[] c, boolean hc, boolean vc) {
        if (s == null || s.isEmpty()) return;
        float[] sz = textSize(s, font);
        if (hc) x = (w - sz[0]) * 0.5f + x;
        if (vc) y = (h - sz[1]) * 0.5f + y;
        text(s, x, y, font, c);
    }

    /** sub_18011BF10 / BE40 / B810: text truncated with an ellipsis to maxW. */
    public static String ellipsize(String s, int font, float maxW) {
        if (s.isEmpty()) return s;
        float w = textSize(s, font)[0];
        if (maxW >= w) return s;
        // sub_18011B510: trim until "..." fits (verified against captured frames later)
        String t = s;
        while (!t.isEmpty() && textSize(t + "...", font)[0] > maxW) t = t.substring(0, t.length() - 1);
        return t + "...";
    }
    public static void textEllipsis(String s, float x, float y, float maxW, int font, float[] c) {
        if (maxW <= 0f) return;
        text(ellipsize(s, font, maxW), x, y, font, c);
    }
    public static void textBoxEllipsis(String s, float x, float y, float w, float h, int font, float[] c, boolean hc, boolean vc) {
        if (h <= 0f && false) return;
        if (w <= 0f) return;
        textBox(ellipsize(s, font, w), x, y, w, h, font, c, hc, vc);
    }

    /** sub_18011E040: AddImage(tex, min, max, uv(0,0)-(1,1), col). */
    public static void image(long tex, float x, float y, float w, float h, float[] c) {
        if (dl == null || tex == 0) return;
        float[] a = pt(x, y), b = pt(x + w, y + h);
        dl.addImage(tex, a[0], a[1], b[0], b[1], 0f, 0f, 1f, 1f, col(c));
    }

    /** sub_18011A770 (soft filled circle, AddCircleFilled with 48 segments via PathArcTo). */
    public static void circleFilled(float cx, float cy, float r, float[] c) {
        if (dl == null) return;
        int cc = col(c);
        float[] a = pt(cx, cy);
        if ((cc & 0xFF000000) != 0 && r >= 0.5f) dl.addCircleFilled(a[0], a[1], r * scale, cc, 0);
    }
}
