package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Port of the Socials page (page 9, clean.c 824-1267) plus its input handling (sub_180145320: hover 2807-2890,
 * click 3925-4075, wheel 5780, typing 1598, Esc/Enter 1080).
 *
 * Data: {@link #players} (qword_180745280, "All Players" tab) and {@link #friends} (qword_180745298, "Friends" tab)
 * are plain name lists; the lead feeds them. {@link #search} is qword_1806FC280 (the page's own search string).
 */
public final class SocialsPage {
    private SocialsPage() {}

    /** qword_180745280: players currently in the server (not yet friends). */
    public static final List<String> players = new ArrayList<>();
    /** qword_180745298: friends. */
    public static final List<String> friends = new ArrayList<>();
    /** qword_1806FC280: search text. */
    public static String search = "";
    /** dword_180708A34: 0 = All Players, 1 = Friends. */
    public static int tab = 0;
    /** byte_180708A0A: search field focused. */
    public static boolean searchFocused = false;
    /** dword_1807089A0: scroll target; flt_180708A54: smoothed scroll. */
    public static float scrollTarget = 0f, scrollA = 0f;
    /** dword_1804CCEB4 / dword_1804CCEC0: hovered raw list index in players / friends (-1 none). */
    public static int hoverPlayer = -1, hoverFriend = -1;
    /**
     * The original click handler hit-tests the header/tabs/search at y 110..145 and the rows 10px lower than where they
     * are drawn (draw: y 100..135, rows from 155), while hover in draw uses the drawn positions. 10 = faithful
     * to the original; set to 0 if clicks should line up exactly with what is drawn.
     */
    public static float clickYOffset = 10f;
    /** Fired when a friend is added / removed (sub_180108D50 / sub_180108E60 in the original); may be null. */
    public static java.util.function.Consumer<String> onAddFriend, onRemoveFriend;
    /** Force the caret blink phase (null = real clock like GetTickCount()/500 &amp; 1). */
    public static Boolean caretOn = null;

    // ------------------------------------------------------------------------------------------ helpers
    /** sub_18013F400 / sub_18014F270: empty search matches everything, else case-insensitive substring. */
    static boolean matches(String name) {
        if (search.isEmpty()) return true;
        return name.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT));
    }

    private static boolean in(float mx, float my, float x, float y, float w, float h) {
        return mx >= x && w + x >= mx && my >= y && h + y >= my;
    }

    private static final float[] WHITE = {1f, 1f, 1f, 1f};              // xmmword_180476370
    private static final float[] REMOVE_FILL = {0.8f, 0.2f, 0.2f, 0.3f}; // xmmword_180475BA0
    private static final float[] REMOVE_LINE = {0.9f, 0.3f, 0.3f, 1f};   // xmmword_180476080
    private static final float[] REMOVE_TEXT = {1f, 0.5f, 0.5f, 1f};     // xmmword_180476130

    // ------------------------------------------------------------------------------------------ draw
    public static void draw() {
        float sideW = Ui.S.sideW;                    // flt_1804CCE68
        float v108 = sideW + 30f;
        float v551 = 1000f - v108;
        int mx = (int) Input.mx, my = (int) Input.my;   // (int)qword_180708A40

        textBox("Socials", v108, 30f, v551, 35f, 8, Theme.text(), false, false);
        textBox("Friends and requests", v108, 65f, v551, 20f, 3, Theme.muted(), false, false);

        // ---- tabs
        boolean t0 = tab == 0;
        boolean h0 = mx >= v108 && v108 + 150f >= mx && my >= 100f && my <= 135f;
        float[] f0 = t0 ? Theme.accent() : (h0 ? Theme.hover() : Theme.surface());
        rect(v108, 100f, 150f, 35f, 6f, true, f0);
        rect(v108, 100f, 150f, 35f, 6f, false, t0 ? Theme.accent() : Theme.e26C0());
        textBox("All Players", v108, 100f, 150f, 35f, 3, t0 ? WHITE : Theme.text(), true, true);

        float v558 = (v108 + 150f) + 10f;
        boolean t1 = tab == 1;
        boolean h1 = mx >= v558 && v558 + 150f >= mx && my >= 100f && my <= 135f;
        float[] f1 = t1 ? Theme.accent() : (h1 ? Theme.hover() : Theme.surface());
        rect(v558, 100f, 150f, 35f, 6f, true, f1);
        rect(v558, 100f, 150f, 35f, 6f, false, t1 ? Theme.accent() : Theme.e26C0());
        textBox("Friends", v558, 100f, 150f, 35f, 3, t1 ? WHITE : Theme.text(), true, true);

        scrollA = Lib.lerp(scrollA, scrollTarget, Ui.S.dt * 8f);   // flt_180708A54 = lerp(.., dword_1807089A0, dt*8)

        // ---- search field
        float v565 = (v558 + 150f) + 20f;
        boolean hs = mx >= v565 && v565 + 250f >= mx && my >= 100f && my <= 135f;
        float[] sf = searchFocused ? Theme.e1D60() : (hs ? Theme.hover() : Theme.surface());
        rect(v565, 100f, 250f, 35f, 6f, true, sf);
        rect(v565, 100f, 250f, 35f, 6f, false, searchFocused ? Theme.accent() : Theme.e26C0());
        Ui.searchIcon(v565 + 10f, 110f, 15f, Theme.muted());   // sub_18011E540(.., 15, muted)
        boolean empty = search.isEmpty();
        float[] tc = empty ? Theme.muted() : Theme.text();
        String shown = empty ? "Search" : search;
        float v574 = v565 + 35f;
        float tw = textSize(shown, 4)[0];
        if (!searchFocused || empty || tw <= 205f) {
            textBoxEllipsis(shown, v574, 100f, 205f, 35f, 4, tc, false, true);
        } else {
            pushClip(v574, 102f, 205f, 31f);
            textBox(shown, (205f - tw) + v574, 100f, 205f, 35f, 4, tc, false, true);
            popClip();
        }
        boolean blink = caretOn != null ? caretOn : ((System.currentTimeMillis() / 500L) & 1L) == 0L;   // GetTickCount()/500
        if (searchFocused && blink) {
            float v575 = Math.min(v574 + 205f, textSize(search, 4)[0] + v574);
            line(v575, 110f, v575, 125f, 1.5f, Theme.accent());
        }

        // ---- list
        pushClip(sideW, 155f, 1100f - sideW, 530f);
        float v576 = 155f - scrollA;
        float v577 = (1100f - sideW) - 60f;
        float v578 = (v577 - 12f) * 0.5f;                       // card width
        float ease = Lib.easeOutCubic(ThemesPage.enterB);       // sub_1800E0410(qword_1804CCEA0.y)
        if (tab != 0) {
            if (friends.isEmpty()) {
                textBox("No friends added yet", v108, v576 + 100f, v577, 50f, 3, Theme.muted(), true, true);
            } else {
                int c = 0;
                for (int k = 0; k < friends.size(); k++) {
                    String name = friends.get(k);
                    if (!matches(name)) continue;
                    c = row(name, k == hoverFriend, c, v108, v576, v578, ease, mx, my, true);
                }
            }
        } else if (players.isEmpty()) {
            textBox("No players in server", v108, v576 + 100f, v577, 50f, 3, Theme.muted(), true, true);
        } else {
            int c = 0;
            for (int k = 0; k < players.size(); k++) {
                String name = players.get(k);
                if (!matches(name)) continue;
                c = row(name, k == hoverPlayer, c, v108, v576, v578, ease, mx, my, false);
            }
        }
        popClip();
    }

    /** One player / friend card (loop body at clean.c 1079-1160 / 1180-1261). Returns the next visible counter. */
    private static int row(String name, boolean hov, int c, float v108, float v576, float w, float ease, int mx, int my, boolean friend) {
        float x = (w + 12f) * (c % 2) + v108;
        float st = ThemesPage.stagger(ease, c, 0.04f, 0.5f);    // sub_180131AE0(v, idx, 0.04, 0.5)
        float y = (1f - st) * 20f + ((c / 2) * 70f + v576);
        pushTransform(w * 0.5f + x, y + 30f, 0f, st * 0.04f + 0.96f);
        rect(x, y, w, 60f, 8f, true, hov ? Theme.hover() : Theme.surface());
        rect(x, y, w, 60f, 8f, false, Theme.e2850());
        avatar(x + 15f, y + 16f, 28f);
        float bw = friend ? 115f : 95f;
        float bx = ((x + w) - bw) - 12f;
        float by = y + 15f;
        textBoxEllipsis(name, x + 55f, y + 20f, (bx - (x + 55f)) - 8f, 20f, 5, Theme.text(), false, false);
        boolean bh = hov && mx >= bx && bx + bw >= mx && my >= by && by + 30f >= my;
        float[] fill, line, txt;
        if (friend) {
            fill = bh ? REMOVE_FILL : Theme.e1D60();
            line = bh ? REMOVE_LINE : Theme.e26C0();
            txt = bh ? REMOVE_TEXT : Theme.text();
        } else {
            fill = bh ? Theme.accent() : Theme.e1D60();
            line = bh ? Theme.accent() : Theme.e26C0();
            txt = bh ? WHITE : Theme.text();
        }
        rect(bx, by, bw, 30f, 4f, true, fill);
        rect(bx, by, bw, 30f, 4f, false, line);
        textBox(friend ? "Remove friend" : "Add friend", bx, by + 5f, bw, 20f, 4, txt, true, true);
        popTransform();
        return c + 1;
    }

    /** sub_18011E7C0: generic avatar (pulsing glow, ring, head, shoulders). */
    static void avatar(float x, float y, float size) {
        if (dl == null) return;
        float r = size * 0.5f;
        float cx = r + x, cy = r + y;
        float[] g = Theme.e2210();
        float pulse = (float) Math.sin((Ui.S.time * 0.7f) * 6.2831855f + x * 0.09f);
        float ga = g[3] * ((((pulse * 0.5f + 0.5f) * 0.5f) + 0.5f) * 0.35f);
        circle48(cx, cy, r + 3f, new float[]{g[0], g[1], g[2], ga}, true, 0f);
        circle48(cx, cy, r, Theme.accent(), false, 2f);
        float[] m = Theme.muted();
        circle48(cx, cy - r * 0.2f, r * 0.28f, m, false, 1.5f);
        float v21 = r * 0.25f, v23 = r * 0.5f, v24 = v21 + cy, v25 = r * 0.6f + cy;
        line(cx - v23, v25, cx - v21, v24, 1.5f, m);
        line(cx - v21, v24, v21 + cx, v24, 1.5f, m);
        line(v21 + cx, v24, v23 + cx, v25, 1.5f, m);
    }

    /** AddCircleFilled / AddCircle with 48 segments (sub_1800A16D0+A1160 / sub_1800A2C10). */
    private static void circle48(float cx, float cy, float r, float[] c, boolean filled, float thick) {
        int cc = col(c);
        float[] p = pt(cx, cy);
        if ((cc & 0xFF000000) == 0 || r < 0.5f) return;
        if (filled) dl.addCircleFilled(p[0], p[1], r * scale, cc, 48);
        else dl.addCircle(p[0], p[1], r * scale, cc, 48, thick * scale);
    }

    // ------------------------------------------------------------------------------------------ input
    /** Per-frame hover (sub_180145320 2807-2890): raw list index, positions from 165 - scroll (sic). */
    public static void update(float dt) {
        if (Ui.S.page != 9) { return; }
        float sideW = Ui.S.sideW;
        float v243 = sideW + 30f;
        float v244 = (155f + clickYOffset) - scrollA;
        float w = (((1100f - sideW) - 60f) - 12f) * 0.5f;
        hoverPlayer = -1; hoverFriend = -1;
        int mx = (int) Input.mx, my = (int) Input.my;
        List<String> l = tab != 0 ? friends : players;
        for (int i = 0; i < l.size(); i++) {
            float x = (i & 1) * (w + 12f) + v243, y = (i >> 1) * 70f + v244;
            if (in(mx, my, x, y, w, 60f)) {
                if (tab != 0) hoverFriend = i; else hoverPlayer = i;
                break;
            }
        }
        // note: update() runs before draw; the smoothing of scrollA itself is done in draw() like the original
    }

    /** Left click (sub_180145320 3925-4075). Canvas coords already in Input.mx/my. */
    public static boolean click(int button) {
        if (button != 0) return false;
        float sideW = Ui.S.sideW;
        float v566 = sideW + 30f;
        float yo = clickYOffset;
        float v567 = (155f + yo) - scrollA;
        float v568 = (((1100f - sideW) - 60f) - 12f) * 0.5f;
        int mx = (int) Input.mx, my = (int) Input.my;
        boolean used = false;
        if (in(mx, my, v566, 100f + yo, 150f, 35f)) { tab = 0; scrollTarget = 0f; used = true; }
        else if (mx >= v566 + 160f && v566 + 310f >= mx && my >= 100f + yo && my <= 135f + yo) { tab = 1; scrollTarget = 0f; used = true; }
        float v570 = (v566 + 300f) + 30f;
        if (in(mx, my, v570, 100f + yo, 250f, 35f)) { searchFocused = true; used = true; }
        if (tab == 0) {
            int i = hoverPlayer;
            if (i != -1 && i < players.size()) {
                float bx = ((((v568 + 12f) * (i % 2) + v566) + v568) - 95f) - 12f;
                float by = ((i / 2) * 70f + v567) + 15f;
                if (in(mx, my, bx, by, 95f, 30f)) {
                    String name = players.get(i);
                    if (!friends.contains(name)) {
                        friends.add(name);
                        players.remove(i);
                        if (onAddFriend != null) onAddFriend.accept(name);
                    }
                    return true;
                }
            }
        } else {
            int i = hoverFriend;
            if (i != -1 && i < friends.size()) {
                float bx = ((((v568 + 12f) * (i % 2) + v566) + v568) - 115f) - 12f;
                float by = ((i / 2) * 70f + v567) + 15f;
                if (in(mx, my, bx, by, 115f, 30f)) {
                    String name = friends.get(i);
                    players.add(name);
                    friends.remove(i);
                    hoverFriend = -1;
                    if (onRemoveFriend != null) onRemoveFriend.accept(name);
                    return true;
                }
            }
        }
        return used;
    }

    /** Wheel (sub_180145320 5780): target -= notches * 20, clamped to the filtered list height. */
    public static boolean scroll(double dy) {
        scrollTarget -= (float) dy * 20f;
        int n = 0;
        for (String s : tab != 0 ? friends : players) if (matches(s)) n++;
        float max = (((n + 1) >> 1) * 70f - 10f) - 530f;
        if (max < 0f) max = 0f;
        if (Math.min(scrollTarget, max) < 0f) scrollTarget = 0f;
        else if (max <= scrollTarget) scrollTarget = max;
        return true;
    }

    /** Esc / Enter unfocus the search field (sub_180145320 1080). Backspace deletes. */
    public static boolean key(int glfwKey) {
        if (!searchFocused) return false;
        if (glfwKey == 256 || glfwKey == 257 || glfwKey == 335) { searchFocused = false; return true; }
        if (glfwKey == 259) { backspace(); return true; }
        return false;
    }

    /** Typed char (sub_180145320 1598): printable ASCII 32..126 appended, 8 = backspace. */
    public static boolean typed(int cp) {
        if (!searchFocused) return false;
        if (cp == 8) backspace();
        else if (cp >= 32 && cp <= 126) search += (char) cp;
        else return false;
        return true;
    }

    private static void backspace() { if (!search.isEmpty()) search = search.substring(0, search.length() - 1); }
}
