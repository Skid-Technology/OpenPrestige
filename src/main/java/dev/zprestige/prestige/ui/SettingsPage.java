package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.HashMap;

/**
 * Settings page (page 6, clean.c ~520-786) plus the generic colour picker sub_1801204C0.
 * Layout = sub_18012C310, picker hit rects = sub_180145250, input = the page-6 parts of the WndProc (180145320).
 */
public final class SettingsPage {
    private SettingsPage() {}

    // ---- colour state (flt_1804CCE58 / CE44 / CE78 / dword_1804CCEC8; defaults = #8576FC) ----
    public static float hue = Float.intBitsToFloat(0x4376ab85);
    public static float sat = Float.intBitsToFloat(0x3f083127);
    public static float val = Float.intBitsToFloat(0x3f7ced91);
    public static float alpha = 1f;
    /** byte_18070897E: hex field being edited; hexBuf = unk_1806FC2C0. */
    public static boolean hexEditing;
    public static StringBuilder hexBuf = new StringBuilder();
    // drag flags byte_180708A21 / 1807089AF / 1807089DC
    static boolean dragSV, dragHue, dragAlpha;
    // key bind: dword_1804CCE84 (Windows VK code, default 0xA1 = right shift), listening byte_180708A08, hover byte_1807089AC
    public static int menuKey = 0xA1;
    public static boolean listening, keyHover;
    static float hoverA, listenA;      // dword_180745560 / dword_18074556C

    // ------------------------------------------------------------------ layout (sub_18012C310)
    static final class L {
        float[] v = new float[23];
    }
    static float[] layout() {
        float v2 = Ui.S.sideW + 30f;
        float v3 = (v2 + 260f) + 24f, v4 = 1070f - v3, v5 = v3 + 20f, v6 = v4 - 40f;
        return new float[]{20f, v2, 100f, 260f, 358f, 118f, 142f, v2 + 20f, 180f, v3, 100f, v4,
                146f, 118f, 142f, v5, 170f, v6, 56f, ((v6 + v5) - 120f) - 14f, 184f, 120f, 28f};
    }

    // ------------------------------------------------------------------ page
    public static void draw() {
        float[] l = layout();
        float v108 = Ui.S.sideW + 30f;
        float v285 = 1000f - v108;
        textBox("Settings", v108, 30f, v285, 35f, 8, Theme.text(), false, false);
        textBox("Appearance and keybinds", v108, 65f, v285, 18f, 4, Theme.e38F0(), false, false);
        float v291 = Lib.easeOutCubic(ThemesPage.enterB);
        float a1 = (1f - ThemesPage.stagger(v291, 0, 0.06f, 0.3f)) * 20f;
        float a2 = (1f - ThemesPage.stagger(v291, 1, 0.06f, 0.3f)) * 20f;
        // left card: main colour
        card(l[1], l[2] + a1, l[3], l[4]);
        float tx = l[0] + l[1], tw = l[3] - l[0] * 2f;
        textBox("Main color", tx, l[5] + a1, tw, 22f, 5, Theme.text(), false, false);
        ModulesPage.wrapped("Accent for trails, effects and HUD", tx, l[6] + a1, tw, 4, Theme.e38F0(), 2);
        String hex = hexEditing ? hexBuf.toString() : hexString(hsv(hue, sat, val, alpha));
        colorPicker(l[7], l[8] + a1, hue, sat, val, alpha, hex, hexEditing, false, 1f, l[7], l[8]);
        // right card: menu bind
        card(l[9], l[10] + a2, l[11], l[12]);
        float rx = l[9] + l[0], rw = l[11] - l[0] * 2f;
        textBox("Menu Bind", rx, l[13] + a2, rw, 22f, 5, Theme.text(), false, false);
        textBox("Key to open/close this menu", rx, l[14] + a2, rw, 18f, 4, Theme.e38F0(), false, false);
        float v313 = l[16] + a2, bx = l[15], bw = l[17], bh = l[18];
        fillRect(bx, v313, bw, bh, 8f, Theme.surface());
        strokeRect(bx, v313, bw, bh, 8f, 1f, Theme.e26C0());
        textBox("Toggle menu", bx + 14f, v313, (bw - l[21]) - 42f, bh, 3, Theme.text(), false, true);
        float dt = Ui.S.dt;
        hoverA = Lib.smooth(hoverA, keyHover ? 1f : 0f, dt, 14f);
        listenA = Lib.smooth(listenA, listening ? 1f : 0f, dt, 10f);
        float kx = l[19], kw = l[21], kh = l[22], ky = l[20] + a2;
        if (listenA > 0.01f) {
            float v323 = (((float) Math.sin((Ui.S.time * 1.4f) * 6.2831855f) * 0.5f + 0.5f) * 0.45f) + 0.55f;
            for (int i = 4; i >= 1; i--) {
                float g = i * 3f;
                float[] ac = Theme.accent();
                fillRect(kx - g, ky - g, (g + g) + kw, (g + g) + kh, g + 4f,
                        new float[]{ac[0], ac[1], ac[2], ((0.16f / i) * listenA) * v323});
            }
        }
        fillRect(kx, ky, kw, kh, 4f, Theme.e1D60());
        float[] bd = lerp(lerp(Theme.e26C0(), Theme.e2850(), hoverA), Theme.e29E0(), listenA);
        strokeRect(kx, ky, kw, kh, 4f, 1f, bd);
        String kn = listening ? "Press a key" : keyName(menuKey);
        float[] tc = lerp(Theme.text(), Theme.accent(), listenA);
        textBoxEllipsis(kn, kx + 8f, ky, kw - 16f, kh, 4, tc, true, true);
    }

    /** sub_18013ECF0: glass card. */
    static void card(float x, float y, float w, float h) {
        fillRect(x + 1f, y + 5f, w - 2f, h, 13f, new float[]{0f, 0f, 0f, 0.2f});
        float[] sb = Theme.sidebar();
        fillRect(x, y, w, h, 12f, new float[]{sb[0], sb[1], sb[2], 0.78f});
        fillRect(x + 8f, y + 0.5f, w - 16f, 1f, 0f, new float[]{1f, 1f, 1f, 0.085f});
        strokeRect(x, y, w, h, 12f, 1f, Theme.e2850());
    }

    // ------------------------------------------------------------------ colour conversions
    /** sub_1800E04F0: HSV(h in degrees) -> RGBA. */
    public static float[] hsv(float h, float s, float v, float a) {
        float c = s * v, m = v - c;
        float x = (1f - Math.abs((h / 60f) % 2f - 1f)) * c;
        float r, g, b;
        if (h < 60f) { r = c; g = x; b = 0f; }
        else if (h < 120f) { r = x; g = c; b = 0f; }
        else if (h < 180f) { r = 0f; g = c; b = x; }
        else if (h < 240f) { r = 0f; g = x; b = c; }
        else if (h < 300f) { r = x; g = 0f; b = c; }
        else { r = c; g = 0f; b = x; }
        return new float[]{r + m, g + m, b + m, a};
    }

    /** sub_1800E0620: RGB -> {h, s, v}. */
    public static float[] rgbToHsv(float r, float g, float b) {
        float mx = Math.max(Math.max(g, b), r), mn = Math.min(Math.min(g, b), r);
        float d = mx - mn, h, s;
        if (mx == 0f) return new float[]{0f, 0f, mx};
        s = d / mx;
        if (d == 0f) h = 0f;
        else {
            float t;
            if (mx == r) t = ((g - b) / d) % 6f;
            else if (mx == g) t = ((b - r) / d) + 2f;
            else t = ((r - g) / d) + 4f;
            h = t * 60f;
            if (h < 0f) h += 360f;
        }
        return new float[]{h, s, mx};
    }

    /** sub_1800E0730: "#RRGGBB" or "#RRGGBBAA". */
    public static String hexString(float[] c) {
        int r = (int) (c[0] * 255f + 0.5f), g = (int) (c[1] * 255f + 0.5f), b = (int) (c[2] * 255f + 0.5f), a = (int) (c[3] * 255f + 0.5f);
        if (a >= 255) return String.format("#%02X%02X%02X", r, g, b);
        return String.format("#%02X%02X%02X%02X", r, g, b, a);
    }

    /** sub_1800E0830: parse "#rrggbb[aa]" -> rgba or null. */
    public static float[] parseHex(String s) {
        if (s.isEmpty()) return null;
        if (s.charAt(0) == '#') s = s.substring(1);
        if (s.length() != 6 && s.length() != 8) return null;
        try {
            int r = Integer.parseInt(s.substring(0, 2), 16), g = Integer.parseInt(s.substring(2, 4), 16), b = Integer.parseInt(s.substring(4, 6), 16);
            int a = s.length() == 8 ? Integer.parseInt(s.substring(6, 8), 16) : 255;
            return new float[]{r / 255f, g / 255f, b / 255f, a / 255f};
        } catch (NumberFormatException e) { return null; }
    }

    // ------------------------------------------------------------------ colour picker (sub_1801204C0)
    /** per-picker smoothed values (sub_18011EB70 map, keyed by rounded (keyX, keyY)): [0]=sat,[1]=val,[2]=hue,[3]=alpha */
    static final HashMap<Long, float[]> ANIM = new HashMap<>();

    static float approach(float cur, float target, float dt) {
        return dt > 0f ? (target - cur) * (1f - (float) Math.exp(dt * -22f)) + cur : cur;
    }

    /**
     * Generic colour picker. (x,y) = top-left of the 220x258 panel; hue 0..360, sat/val/alpha 0..1; text = hex field
     * string; editing = field focused; scaleAnim = open animation (dword_180708A58) used when useScale;
     * (keyX,keyY) identifies the picker for the animation state (pass <0 to use x,y).
     */
    public static void colorPicker(float x, float y, float hue, float sat, float val, float alpha, String text,
                                   boolean editing, boolean useScale, float scaleAnim, float keyX, float keyY) {
        if (keyX < 0f) keyX = x;
        if (keyY < 0f) keyY = y;
        long key = ((long) (int) (keyY + 0.5f)) | ((long) (int) (keyX + 0.5f) << 32);
        float[] st = ANIM.get(key);
        if (st == null) { st = new float[]{sat, val, hue, alpha}; ANIM.put(key, st); }
        float dt = Ui.S.dt;
        if (Lib.STEADY) { st[0] = sat; st[1] = val; st[2] = hue; st[3] = alpha; }
        else {
            st[0] = approach(st[0], sat, dt); st[1] = approach(st[1], val, dt);
            st[2] = approach(st[2], hue, dt); st[3] = approach(st[3], alpha, dt);
        }
        boolean pushed = false;
        if (useScale && scaleAnim < 0.9995f) {
            float v29 = scaleAnim - 1f;
            float p2 = (float) Math.pow(v29, 2.0), p3 = (float) Math.pow(v29, 3.0);
            pushTransform(x + 110f, y + 129f, 0f, (((p3 * 3f) + 1f) + (p2 + p2)) * 0.06f + 0.94f);
            pushed = true;
        }
        final float[] white = {1f, 1f, 1f, 1f};
        fillRect(x, y, 220f, 258f, 8f, Theme.surface());
        strokeRect(x, y, 220f, 258f, 8f, 1f, Theme.e26C0());
        float v32 = x + 12f, v33 = y + 12f;
        fillRect(v32, v33, 170f, 170f, 4f, white);
        // saturation / value square
        for (int i = 0; i < 170; i += 2) {
            float v37 = 170f - i, v38 = i / 170f, v39 = i + v32;
            float w = v37 <= 2f ? v37 : 2f;
            for (int j = 0; j < 170; j += 2) {
                float[] c = hsv(hue, v38, 1f - j / 170f, 1f);
                float h = 170f - j;
                if (h > 2f) h = 2f;
                fillRect(v39, j + v33, w, h, 0f, c);
            }
        }
        strokeRect(v32, v33, 170f, 170f, 4f, 1f, Theme.e26C0());
        float hx = (st[0] * 170f) + v32, hy = ((1f - st[1]) * 170f) + v33;
        float[] g = Theme.e2210();
        float[] fillC = {g[0], g[1], g[2], 0.35f};
        if (dl != null) {
            float[] p = pt(hx, hy);
            int cc = col(fillC);
            if ((cc & 0xFF000000) != 0) dl.addCircleFilled(p[0], p[1], 9f * scale, cc, 0);
            int cw = col(white);
            if ((cw & 0xFF000000) != 0) dl.addCircle(p[0], p[1], (7f - 0.5f) * scale, cw, 0, 2f * scale);
            int cb = col(new float[]{0f, 0f, 0f, 1f});
            if ((cb & 0xFF000000) != 0) dl.addCircle(p[0], p[1], (5f - 0.5f) * scale, cb, 0, 1f * scale);
        }
        // hue bar
        float v55 = (v32 + 170f) + 8f;
        for (int j = 0; j < 48; j++) {
            float[] c = hsv((j / 48f) * 360f, 1f, 1f, 1f);
            float v57 = j * 3.5416667f;
            float h = 170f - v57;
            if (h > 4.541667f) h = 4.541667f;
            fillRect(v55, v33 + v57, 18f, h, 0f, c);
        }
        strokeRect(v55, v33, 18f, 170f, 4f, 1f, Theme.e26C0());
        strokeRect(v55 - 2f, (((st[2] / 360f) * 170f) + v33) - 3f, 22f, 6f, 2f, 1f, white);
        // alpha bar
        float v60 = (v33 + 170f) + 10f;
        fillRect(v32, v60, 196f, 16f, 4f, new float[]{0.4f, 0.4f, 0.4f, 1f});
        float[] chk = {0.6f, 0.6f, 0.6f, 1f};
        for (int k = 0; k < 196; k += 8) {
            for (int m = 0; m < 16; m += 8) {
                if ((((k >>> 3) + (m >>> 3)) & 1) == 0) {
                    float v63 = k + v32, v64 = m + v60;
                    float v65 = v63 + 8f; if ((v32 + 196f) <= v65) v65 = v32 + 196f;
                    float v66 = v64 + 8f; if ((v60 + 16f) <= v66) v66 = v60 + 16f;
                    fillRect(v63, v64, v65 - v63, v66 - v64, 0f, chk);
                }
            }
        }
        float[] rgb = hsv(hue, sat, val, 1f);
        for (int n = 0; n < 48; n++) {
            float v68 = n * 4.0833335f;
            float v69 = 196f - v68;
            if (v69 > 5.0833335f) v69 = 5.0833335f;
            fillRect(v68 + v32, v60, v69, 16f, 0f, new float[]{rgb[0], rgb[1], rgb[2], (n + 0.5f) / 48f});
        }
        strokeRect(v32, v60, 196f, 16f, 4f, 1f, Theme.e26C0());
        strokeRect(((st[3] * 196f) + v32) - 3f, v60 - 2f, 6f, 20f, 2f, 1f, white);
        // preview + hex field
        float v71 = (v60 + 16f) + 10f;
        fillRect(v32, v71, 28f, 28f, 4f, new float[]{0.4f, 0.4f, 0.4f, 1f});
        fillRect(v32, v71, 28f, 28f, 4f, hsv(hue, sat, val, alpha));
        strokeRect(v32, v71, 28f, 28f, 4f, 1f, Theme.e26C0());
        float v72 = (v32 + 28f) + 8f;
        if (editing) {
            fillRect(v72, v71, 160f, 28f, 4f, Theme.e1D60());
            strokeRect(v72, v71, 160f, 28f, 4f, 1f, Theme.accent());
        } else {
            fillRect(v72, v71, 160f, 28f, 4f, Theme.hover());
            strokeRect(v72, v71, 160f, 28f, 4f, 1f, Theme.e26C0());
        }
        float v74 = v72 + 8f;
        float tw = textSize(text, 4)[0];
        if (editing && tw > 144f) {
            pushClip(v72 + 2f, v71, 156f, 28f);
            textBox(text, (144f - tw) + v74, v71 + 4f, 144f, 20f, 4, Theme.text(), false, true);
            popClip();
        } else {
            textBoxEllipsis(text, v74, v71 + 4f, 144f, 20f, 4, Theme.text(), false, true);
        }
        if (editing) {
            float v78 = Math.min((v72 + 160f) - 8f, tw + v74);
            float[] tcol = Theme.text();
            float blink = ((float) Math.sin((Ui.S.time * 1.1f) * 6.2831855f + 0f) * 0.5f + 0.5f) * 0.65f + 0.35f;
            line(v78, v71 + 6f, v78, (v71 + 28f) - 6f, 1f, new float[]{tcol[0], tcol[1], tcol[2], tcol[3] * blink});
        }
        if (pushed) popTransform();
    }

    // ------------------------------------------------------------------ key names (sub_1800E0EC0, Windows VK codes)
    public static String keyName(int vk) {
        switch (vk) {
            case 9999: return "LMB"; case 9998: return "RMB"; case 9997: return "MMB"; case 9996: return "MB4"; case 9995: return "MB5";
            case 32: return "Space"; case 16: return "Shift"; case 17: return "Ctrl"; case 18: return "Alt";
            case 160: return "Left Shift"; case 161: return "Right Shift"; case 162: return "Ctrl"; case 163: return "Right Ctrl";
            case 164: return "Alt"; case 165: return "Right Alt";
            case 8: return "Backspace"; case 9: return "Tab"; case 13: return "Enter"; case 20: return "Caps Lock";
            case 33: return "Page Up"; case 34: return "Page Down"; case 35: return "End"; case 36: return "Home";
            case 37: return "Left"; case 38: return "Up"; case 39: return "Right"; case 40: return "Down";
            case 45: return "Insert"; case 46: return "Delete";
            case 186: return ";"; case 187: return "="; case 188: return ","; case 189: return "-"; case 190: return "."; case 191: return "/";
            case 192: return "`"; case 219: return "["; case 220: return "\\"; case 221: return "]"; case 222: return "'";
            default:
        }
        if ((vk >= 65 && vk <= 90) || (vk >= 48 && vk <= 57)) return String.valueOf((char) vk);
        if (vk >= 112 && vk <= 135) return "F" + (vk - 111);
        if (vk >= 96 && vk <= 105) return "Num " + (vk - 96);
        return "None";
    }

    /** GLFW key -> Windows VK (0 = unsupported). */
    static int toVk(int k) {
        if ((k >= 65 && k <= 90) || (k >= 48 && k <= 57) || k == 32) return k;
        if (k >= 290 && k <= 314) return 112 + (k - 290);
        switch (k) {
            case 340: return 160; case 344: return 161; case 341: return 162; case 345: return 163; case 342: return 164; case 346: return 165;
            case 258: return 9; case 257: return 13; case 259: return 8; case 280: return 20;
            case 265: return 38; case 264: return 40; case 263: return 37; case 262: return 39;
            case 260: return 45; case 261: return 46; case 268: return 36; case 269: return 35; case 266: return 33; case 267: return 34;
            case 59: return 186; case 61: return 187; case 44: return 188; case 45: return 189; case 46: return 190; case 47: return 191;
            case 96: return 192; case 91: return 219; case 92: return 220; case 93: return 221; case 39: return 222;
            default: return 0;
        }
    }

    // ------------------------------------------------------------------ input
    static boolean inRect(float x, float y, float w, float h) {
        return Input.mx >= x && Input.mx <= x + w && Input.my >= y && Input.my <= y + h;
    }

    static void applySV() {
        float[] l = layout();
        float px = l[7] + 12f, py = l[8] + 12f;
        sat = clamp01((Input.mx - px) / 170f);
        val = clamp01(1f - ((Input.my - py) / 170f));
    }
    static void applyHue() { float[] l = layout(); hue = clampN((Input.my - (l[8] + 12f)) / 170f * 360f, 360f); }
    static void applyAlpha() { float[] l = layout(); alpha = clamp01((Input.mx - (l[7] + 12f)) / 196f); }
    static float clamp01(float v) { return v < 0f ? 0f : (v > 1f ? 1f : v); }
    static float clampN(float v, float n) { return v < 0f ? 0f : (v > n ? n : v); }

    /** Left click (canvas coords; Input.mx/my already set). */
    public static boolean click(int button) {
        if (button != 0) return false;
        float[] l = layout();
        float px = l[7], py = l[8];
        hexEditing = false;
        if (inRect(px + 12f, py + 12f, 170f, 170f)) { dragSV = true; applySV(); return true; }
        if (inRect(px + 12f + 178f, py + 12f, 18f, 170f)) { dragHue = true; applyHue(); return true; }
        if (inRect(px + 12f, py + 12f + 180f, 196f, 16f)) { dragAlpha = true; applyAlpha(); return true; }
        if (inRect(px + 12f + 36f, py + 12f + 170f + 10f + 16f + 10f, 160f, 28f)) {
            hexEditing = true;
            hexBuf = new StringBuilder(hexString(hsv(hue, sat, val, alpha)));
            return true;
        }
        if (keyHover) listening = true;
        return true;
    }

    public static boolean scroll(double dy) { return false; }

    /** Key press (GLFW key code). */
    public static boolean key(int glfwKey) {
        if (hexEditing) {
            if (glfwKey == 259) { if (hexBuf.length() > 0) hexBuf.setLength(hexBuf.length() - 1); return true; }
            if (glfwKey == 257 || glfwKey == 335) {
                float[] c = parseHex(hexBuf.toString());
                if (c != null) {
                    float[] h = rgbToHsv(c[0], c[1], c[2]);
                    hue = h[0]; sat = h[1]; val = h[2]; alpha = c[3];
                }
                hexEditing = false;
                return true;
            }
            if (glfwKey == 256) { hexEditing = false; return true; }
            return false;
        }
        if (listening) {
            if (glfwKey != 256 && glfwKey != 261) {
                int vk = toVk(glfwKey);
                if (vk != 0) menuKey = vk;
            }
            listening = false;
            return true;
        }
        return false;
    }

    /** Typed character. */
    public static boolean typed(int cp) {
        if (!hexEditing) return false;
        boolean ok = (cp >= '0' && cp <= '9') || (cp >= 'a' && cp <= 'f') || (cp >= 'A' && cp <= 'F') || cp == '#';
        if (ok && hexBuf.length() < 9) hexBuf.append((char) cp);
        return true;
    }

    /** Per-frame hover / drag update. */
    public static void update(float dt) {
        if (Ui.S.page != 6) return;
        if (!Input.down) { dragSV = dragHue = dragAlpha = false; }
        if (dragSV) applySV();
        if (dragHue) applyHue();
        if (dragAlpha) applyAlpha();
        float[] l = layout();
        keyHover = inRect(l[19], l[20], l[21], l[22]);
    }
}
