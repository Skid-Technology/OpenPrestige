package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Keybinds page (page 10). Port of clean.c ~1273-1490 (draw), 180145320.c (click / key capture / wheel) and the key chip
 * sub_18011F140. Key codes are Windows virtual-key codes like in the original (+216 of the "Bind" setting); 0 = None.
 */
public final class KeybindsPage {
    private KeybindsPage() {}

    /** One bound module (original list element: name, description, key label, module index, setting index). */
    public static final class Bound {
        public String name, desc;
        public int vk;              // Windows VK code (or 9995..9999 for mouse buttons); 0 = none (hidden from the list)
        public boolean toggle;      // Hold / Toggle mode (not shown on this page, kept for the lead)
        public Bound(String name, String desc, int vk, boolean toggle) { this.name = name; this.desc = desc; this.vk = vk; this.toggle = toggle; }
    }

    /** The lead fills this from the real modules (only entries with vk != 0 are listed). */
    public static final List<Bound> BOUND = new ArrayList<>();

    /** dword_1804CCE5C: hovered row (-1 none). dword_1804CCE90 / byte_18070893F: row being captured / capture active. */
    public static int hoverRow = -1, captureRow = -1;
    public static boolean capturing;
    /** flt_180708994 current scroll, dword_1807089E8 target. */
    public static float scroll, scrollTarget;
    /** Icon texture id drawn inside the key chip (sub_18011E040 on qword_180744B80+504); 0 = none. */
    public static long chipIcon = 0;

    private static List<Bound> visible() {
        List<Bound> l = new ArrayList<>();
        for (Bound b : BOUND) if (!vkName(b.vk).equals("None")) l.add(b);
        return l;
    }

    private static float v108() { return Ui.S.sideW + 30f; }
    private static float width() { return (1100f - Ui.S.sideW) - 60f; }

    // ------------------------------------------------------------------------------------------ draw
    public static void draw() {
        float v108 = v108(), v107 = Ui.S.sideW, v551 = 1000f - v108, v619 = width();
        textBox("Keybinds", v108, 30f, v551, 35f, 8, Theme.text(), false, false);
        textBox("Rebind any module", v108, 65f, v551, 20f, 3, Theme.muted(), false, false);
        float dt = Ui.S.dt;
        scroll = lerp(scroll, scrollTarget, dt * 8f);
        if (STEADY) scroll = scrollTarget;
        List<Bound> list = visible();
        pushClip(v107, 105f, 1100f - v107, 580f);
        float v618 = 110f - scroll;
        if (list.isEmpty()) {
            textBox("No keybinds set. Configure keybinds in module settings.", v108, 230f, v619, 50f, 3, Theme.muted(), true, true);
        } else {
            float v884 = easeOutCubic(ModulesPage.P.enterB);
            float chipAlpha = ModulesPage.P.enterA;                       // low float of qword_1804CCEA0
            float v622 = v108 + 15f, v623 = v622 + 250f + 10f;
            float v877 = v619 * 0.5f + v108;
            float v625 = (v619 + v108) - 210f, v626 = (v619 + v108) - 120f;
            for (int i = 0; i < list.size(); i++) {
                Bound b = list.get(i);
                boolean rowHot = i == hoverRow;
                float v629 = ThemesPage.stagger(v884, i, 0.04f, 0.5f);
                float v630 = (1f - v629) * 20f + v618;
                pushTransform(v877, v630 + 30f, 0f, v629 * 0.04f + 0.96f);
                fillRect(v108, v630, v619, 60f, 8f, Theme.surface());
                strokeRect(v108, v630, v619, 60f, 8f, 1f, Theme.e2850());
                textBoxEllipsis(b.name, v622, v630 + 12f, 235f, 20f, 5, Theme.text(), false, false);
                if (captureRow == i && capturing) {
                    textBox("Press key", v623, v630 + 14f, 100f, 20f, 4, Theme.accent(), true, true);
                } else {
                    chip(v623, v630 + 14f, vkName(b.vk), chipAlpha);
                }
                textBoxEllipsis(b.desc, v622, v630 + 34f, 235f, 14f, 4, Theme.muted(), false, false);
                boolean editHot = rowHot && inBtn(v625, v618);
                fillRect(v625, v630 + 15f, 80f, 30f, 4f, editHot ? Theme.hover() : Theme.surface());
                strokeRect(v625, v630 + 15f, 80f, 30f, 4f, 1f, editHot ? Theme.accent() : Theme.e26C0());
                textBox("Edit", v625, v630 + 20f, 80f, 20f, 4, Theme.text(), true, true);
                boolean delHot = rowHot && inBtn(v626, v618);
                fillRect(v626, v630 + 15f, 80f, 30f, 4f, delHot ? Theme.hover() : Theme.surface());
                strokeRect(v626, v630 + 15f, 80f, 30f, 4f, 1f, delHot ? Theme.e29E0() : Theme.e26C0());
                textBox("Delete", v626, v630 + 20f, 80f, 20f, 4, delHot ? DEL_HOT : Theme.text(), true, true);
                popTransform();
                v618 += 70f;
            }
        }
        popClip();
    }

    /** xmmword_180476130 (hot "Delete" label colour). */
    static final float[] DEL_HOT = {1f, 0.5f, 0.5f, 1f};

    /** Mouse inside the button column [bx, bx+80] and the 15..45 band of the row whose top is rowY. */
    private static boolean inBtn(float bx, float rowY) {
        return Input.mx >= bx && Input.mx <= bx + 80f && Input.my >= rowY + 15f && Input.my <= rowY + 45f;
    }

    /** sub_18011F140: key chip (rounded box + icon + label). */
    static void chip(float x, float y, String label, float alpha) {
        if (label.equals("None") || alpha <= 0f) return;
        float w = textSize(label, 4)[0] + 22f + 5f;
        float[] s = Theme.surface();
        fillRect(x, y, w, 22f, 4f, new float[]{s[0], s[1], s[2], s[3] * alpha});
        float[] b = Theme.e26C0();
        strokeRect(x, y, w, 22f, 4f, 1f, new float[]{b[0], b[1], b[2], b[3] * alpha});
        float x1 = x + 5f;
        float[] m = Theme.muted();
        float[] mc = {m[0], m[1], m[2], m[3] * alpha};
        if (chipIcon != 0) image(chipIcon, x1, y + 4f, 14f, 14f, mc);
        float tx = (x1 + 14f) + 3f;
        textBox(label, tx, y + 2f, (w - 5f) - (tx - x), 18f, 4, mc, false, true);
    }

    // ------------------------------------------------------------------------------------------ input
    /** Per-frame: hover row (mouse-move handler in 180145320.c) + scroll clamp. */
    public static void update(float dt) {
        hoverRow = -1;
        if (Ui.S.page != 10) return;
        int n = visible().size();
        float v108 = v108(), w = width();
        float y = 110f - scroll;
        for (int i = 0; i < n; i++, y += 70f) {
            if (Input.mx >= v108 && Input.mx <= v108 + w && Input.my >= y && Input.my <= y + 60f) { hoverRow = i; break; }
        }
        scrollTarget = Math.max(0f, Math.min(scrollTarget, maxScroll(n)));
    }

    private static float maxScroll(int n) { return Math.max(0f, (n * 70f - 10f) - 575f); }

    /** Left click: Edit starts capture, Delete clears the key (sets it to None). */
    public static boolean click(int button) {
        if (button != 0) return false;
        List<Bound> list = visible();
        if (hoverRow < 0 || hoverRow >= list.size()) return false;
        float v108 = v108(), w = width();
        float rowY = hoverRow * 70f + (110f - scroll);
        float edit = (w + v108) - 210f, del = (w + v108) - 120f;
        if (Input.mx >= edit && Input.mx <= edit + 80f && Input.my >= rowY + 15f && Input.my <= rowY + 45f) {
            captureRow = hoverRow; capturing = true;
            return true;
        }
        if (Input.mx >= del && Input.mx <= del + 80f && Input.my >= rowY + 15f && Input.my <= rowY + 45f) {
            list.get(hoverRow).vk = 0;
            captureRow = -1; capturing = false;
            return true;
        }
        return false;
    }

    /** Wheel: target -= delta/120*40 (one notch = 40 px). */
    public static boolean scroll(double dy) {
        scrollTarget -= (float) dy * 40f;
        scrollTarget = Math.max(0f, Math.min(scrollTarget, maxScroll(visible().size())));
        return true;
    }

    /** Key press (GLFW key code): while capturing, Esc / Delete clear the bind, any other key binds it. */
    public static boolean key(int glfwKey) {
        if (!capturing || captureRow < 0) return false;
        List<Bound> list = visible();
        if (captureRow < list.size()) {
            Bound b = list.get(captureRow);
            if (glfwKey == 256 || glfwKey == 261) b.vk = 0;            // VK 27 / 46
            else { int vk = glfwToVk(glfwKey); if (vk != 0) b.vk = vk; else return true; }
        }
        capturing = false; captureRow = -1;
        return true;
    }

    public static boolean typed(int codepoint) { return capturing; }

    /** Called when the page is left (the original resets the capture state on page change). */
    public static void reset() { capturing = false; captureRow = -1; hoverRow = -1; }

    // ------------------------------------------------------------------------------------------ key names
    /** sub_1800E0EC0: VK code -> label. */
    public static String vkName(int k) {
        switch (k) {
            case 9999: return "LMB"; case 9998: return "RMB"; case 9997: return "MMB"; case 9996: return "MB4"; case 9995: return "MB5";
            case 32: return "Space"; case 16: return "Shift"; case 17: return "Ctrl"; case 18: return "Alt";
            case 8: return "Backspace"; case 9: return "Tab"; case 13: return "Enter"; case 19: return "Pause"; case 20: return "Caps Lock";
            case 27: return "Esc";
            case 33: return "Page Up"; case 34: return "Page Down"; case 35: return "End"; case 36: return "Home";
            case 37: return "Left"; case 38: return "Up"; case 39: return "Right"; case 40: return "Down";
            case 45: return "Insert"; case 46: return "Delete";
            case 91: return "Left Windows"; case 92: return "Right Windows"; case 93: return "Application";
            case 106: return "Num *"; case 107: return "Num +"; case 109: return "Num -"; case 110: return "Num Del"; case 111: return "Num /";
            case 144: return "Num Lock"; case 145: return "Scroll Lock";
            case 160: return "Shift"; case 161: return "Right Shift"; case 162: return "Ctrl"; case 163: return "Right Ctrl";
            case 164: return "Alt"; case 165: return "Right Alt";
            case 186: return ";"; case 187: return "="; case 188: return ","; case 189: return "-"; case 190: return "."; case 191: return "/";
            case 192: return "`"; case 219: return "["; case 220: return "\\"; case 221: return "]"; case 222: return "'";
            default: break;
        }
        if (k >= 48 && k <= 57) return String.valueOf((char) k);
        if (k >= 65 && k <= 90) return String.valueOf((char) k);
        if (k >= 96 && k <= 105) return "Num " + (k - 96);
        if (k >= 112 && k <= 135) return "F" + (k - 111);
        return "None";
    }

    /** GLFW key code -> Windows VK code (0 = unmapped). */
    public static int glfwToVk(int g) {
        if (g >= 48 && g <= 57) return g;
        if (g >= 65 && g <= 90) return g;
        if (g >= 290 && g <= 313) return 112 + (g - 290);          // F1..F24
        if (g >= 320 && g <= 329) return 96 + (g - 320);           // keypad 0-9
        switch (g) {
            case 32: return 32; case 39: return 222; case 44: return 188; case 45: return 189; case 46: return 190; case 47: return 191;
            case 59: return 186; case 61: return 187; case 91: return 219; case 92: return 220; case 93: return 221; case 96: return 192;
            case 257: return 13; case 258: return 9; case 259: return 8; case 260: return 45; case 261: return 46;
            case 262: return 39; case 263: return 37; case 264: return 40; case 265: return 38;
            case 266: return 33; case 267: return 34; case 268: return 36; case 269: return 35;
            case 280: return 20; case 281: return 145; case 282: return 144; case 284: return 19;
            case 330: return 110; case 331: return 111; case 332: return 106; case 333: return 109; case 334: return 107; case 335: return 13;
            case 340: return 160; case 341: return 162; case 342: return 164; case 343: return 91;
            case 344: return 161; case 345: return 163; case 346: return 165; case 347: return 92; case 348: return 93;
            default: return 0;
        }
    }
}
