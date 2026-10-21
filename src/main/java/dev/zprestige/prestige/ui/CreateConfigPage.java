package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

/**
 * Body of the Configs page "Create config" tab (dword_180708A14 == 2): the part of sub_180131B70 around
 * clean.c lines 1690-2330, plus the helpers sub_180122650 (text input), sub_180121320 (visibility dropdown),
 * sub_180122120 (tags dropdown), sub_18013EF10 (checklist row), sub_18013F270 (popup frame) and
 * sub_1801237F0 (config card, preview variant), and the click / char handling from sub_180145320.
 * ConfigsPage owns the tab strip and calls {@link #draw()} when its tab == 2.
 */
public final class CreateConfigPage {
    private CreateConfigPage() {}

    // ---- state (original globals) ----
    /** qword_1806FC1F8 / qword_1806FC2E0 */
    public static final StringBuilder name = new StringBuilder(), desc = new StringBuilder();
    /** byte_180708A4D / byte_18070893E: which text field has focus. */
    public static boolean nameFocus, descFocus;
    /** byte_18070897F / byte_18070893D: visibility / tags popup open. */
    public static boolean visOpen, tagsOpen;
    /** dword_1807089C8: 0 Personal, 1 Public. */
    public static int visibility;
    /** xmmword_180744CF0 / qword_180744CD0: tag names and their selected bits. */
    public static final String[] TAGS = {"Crystal", "Sword", "Legit", "Blatant"};
    public static final boolean[] tagSel = new boolean[TAGS.length];
    /** xmmword_180745620: current user name shown as the preview author. */
    public static String username = System.getProperty("user", "skid solutions");
    /** byte_1807089AE: set when the Create config button was clicked (consumed by the config upload code). */
    public static boolean createRequested;
    /** Tab entrance animation (flt_1806FD728), 1 = settled. ConfigsPage may drive it on tab switch. */
    public static float tabEnter = 1f;

    // animation globals
    private static float aVisHover, aVisOpen, aCreateHover;      // dword_180745588 / 180745570 / 180745568
    private static int hoverVis = -1, hoverTag = -1;             // dword_1804CCE1C / dword_1804CCE94

    private static final String[] VIS = {"Personal", "Public"};
    private static final float[] WHITE = {1f, 1f, 1f, 1f};

    // ---- layout (sub_18012C410, 37 floats) ----
    private static float[] layout() {
        float[] L = new float[37];
        float v2 = Ui.S.sideW + 30f, v3 = v2 + 24f, v4 = (v2 + 480f) + 24f;
        L[0] = 24f; L[1] = v2; L[2] = 131f; L[3] = 480f; L[4] = 396f; L[5] = 155f; L[6] = 183f; L[7] = 215f;
        L[8] = 231f; L[9] = v3; L[10] = 253f; L[11] = 432f; L[12] = 36f; L[13] = 305f; L[14] = v3; L[15] = 327f;
        L[16] = 432f; L[17] = 36f; L[18] = 379f; L[19] = v3; L[20] = 401f; L[21] = 208f; L[22] = 36f;
        L[23] = (v3 + 208f) + 16f; L[24] = 401f; L[25] = 208f; L[26] = 36f; L[27] = v3; L[28] = 461f;
        L[29] = 432f; L[30] = 42f; L[31] = 30f; L[32] = 6f; L[33] = 4f; L[34] = v4; L[35] = 131f; L[36] = 1070f - v4;
        return L;
    }

    private static boolean active() { return ConfigsPage.tab == 2; }
    /** sub_18014F670 */
    private static boolean hit(float x, float y, float w, float h) {
        float mx = (int) Input.mx, my = (int) Input.my;
        return mx >= x && x + w >= mx && my >= y && y + h >= my;
    }

    // =====================================================================================================
    // draw
    // =====================================================================================================
    public static void draw() {
        float[] L = layout();
        float dt = Ui.S.dt;
        float v375 = Math.min(easeOutCubic(tabEnter), easeOutCubic(ModulesPage.P.enterB));
        float v475 = L[1], v476 = L[0] + v475, v477 = L[3] - L[0] * 2f;
        pushTransform(((L[34] + v475) + L[36]) * 0.5f, L[4] * 0.5f + L[2], 0f, v375 * 0.035f + 0.965f);
        // card
        fillRect(L[1] + 1f, L[2] + 5f, L[3] - 2f, L[4], 13f, new float[]{0f, 0f, 0f, 0.2f});
        float[] sb = Theme.sidebar();
        fillRect(L[1], L[2], L[3], L[4], 12f, new float[]{sb[0], sb[1], sb[2], 0.62f});
        fillRect(L[1] + 6f, L[2] + 0.5f, L[3] - 12f, 1f, 0f, new float[]{1f, 1f, 1f, 0.08f});
        strokeRect(L[1], L[2], L[3], L[4], 12f, 1f, Theme.e26C0());
        textBox("Create a new config", v476, L[5], v477, 24f, 10, Theme.text(), false, false);
        textBox("Snapshots your current settings and saves them as a config.", v476, L[6], v477, 18f, 4, Theme.muted(), false, false);
        line(v476, L[7], v477 + v476, L[7], 1f, Theme.e26C0());

        // --- name ---
        textBox("Config name", v476, L[8], v477 * 0.5f, 16f, 5, Theme.text(), false, false);
        counter(name.length(), 20, v477 + v476, L[8]);
        boolean nameHov = hit(L[9], L[10], L[11], L[12]);
        textInput(L[9], L[10], L[11], name.toString(), nameFocus, nameHov, L[12]);
        if (name.length() == 0)
            textBox("e.g. Crystal PvP config", L[9] + 8f, L[10] + 2f, L[11] - 16f, L[12] - 4f, 4, Theme.muted(), false, true);

        // --- description ---
        textBox("Description", v476, L[13], v477 * 0.5f, 16f, 5, Theme.text(), false, false);
        counter(desc.length(), 50, v477 + v476, L[13]);
        boolean descHov = hit(L[14], L[15], L[16], L[17]);
        textInput(L[14], L[15], L[16], desc.toString(), descFocus, descHov, L[17]);
        if (desc.length() == 0)
            textBox("What the config does", L[14] + 8f, L[15] + 2f, L[16] - 16f, L[17] - 4f, 4, Theme.muted(), false, true);

        // --- visibility / tags ---
        textBox("Visibility", v476, L[18], L[21], 16f, 5, Theme.text(), false, false);
        textBox("Tags", L[23], L[18], L[25], 16f, 5, Theme.text(), false, false);
        boolean visHov = hit(L[19], L[20], L[21], L[22]);
        boolean tagHov = hit(L[23], L[24], L[25], L[26]);
        aVisHover = smooth(aVisHover, visHov ? 1f : 0f, dt, 12f);
        aVisOpen = smooth(aVisOpen, visOpen ? 1f : 0f, dt, 14f);
        visDropdown(L[19], L[20], L[21], VIS[visibility], aVisHover, aVisOpen, L[22]);
        tagsDropdown(L[23], L[24], L[25], tagHov, L[26]);

        // --- create button ---
        boolean cbHot = !(visOpen || tagsOpen || !hit(L[27], L[28], L[29], L[30]));
        aCreateHover = smooth(aCreateHover, cbHot ? 1f : 0f, dt, 12f);
        float h = aCreateHover;
        if (h > 0.01f) {
            for (int i = 3; i >= 1; i--) {
                float g = i * 3f;
                float[] a = Theme.accent();
                rect(L[27] - g, L[28] - g, L[29] + g + g, L[30] + g + g, g + 8f, true, new float[]{a[0], a[1], a[2], (0.1f / i) * h});
            }
        }
        float[] bf = lerp(Theme.e2080(), Theme.accent(), h * 0.45f + 0.55f);
        rect(L[27], L[28], L[29], L[30], 8f, true, bf);
        textBox("Create config", L[27], L[28], L[29], L[30], 5, WHITE, true, true);

        // --- preview ---
        textBox("Preview", L[34], L[35], L[36], 18f, 5, Theme.text(), false, false);
        textBox("How it looks on the site", L[34], L[35] + 20f, L[36], 16f, 4, Theme.muted(), false, false);
        String pn = name.length() == 0 ? "Config name" : name.toString();
        String pd = desc.length() == 0 ? "Your description will appear here." : desc.toString();
        float cy = L[35] + 48f;
        previewCard(L[34], cy, L[36], pn, pd, 0, username, "Just now");

        // --- checklist ---
        float[] yy = {cy + 200f};
        checkRow(L, yy, name.length() >= 3, "Name needs 3-20 chars");
        checkRow(L, yy, desc.length() >= 3, "Description needs 3-50 chars");
        circleFilled(L[34] + 7f, yy[0] + 10f, 2.5f, Theme.accent());
        textBoxEllipsis(visibility == 1 ? "Everyone can see it" : "Only you can see it", L[34] + 22f, yy[0], L[36] - 22f, 20f, 4, Theme.muted(), false, true);
        popTransform();

        // --- popups (drawn on top, no transform) ---
        if (visOpen) {
            float y = (L[22] + L[20]) + L[32];
            popupFrame(L[19], y, L[21], 2f * L[31] + L[33] * 2f);
            float iy = L[33] + y;
            for (int i = 0; i < 2; i++) {
                if (hoverVis == i) rect(L[19] + 4f, iy, L[21] - 8f, L[31], 5f, true, a(Theme.accent(), 0.16f));
                boolean sel = visibility == i;
                textBox(VIS[i], L[19] + 8f, iy, L[21] - 16f, L[31], 4, sel ? Theme.accent() : Theme.text(), false, true);
                if (sel) circleFilled((L[19] + L[21]) - 8f, L[31] * 0.5f + iy, 2.5f, Theme.accent());
                iy += L[31];
            }
        }
        if (tagsOpen) {
            float y = (L[26] + L[24]) + L[32];
            popupFrame(L[23], y, L[25], TAGS.length * L[31] + L[33] * 2f);
            float iy = L[33] + y;
            for (int i = 0; i < TAGS.length; i++) {
                if (hoverTag == i) rect(L[23] + 4f, iy, L[25] - 8f, L[31], 5f, true, a(Theme.accent(), 0.16f));
                float bx = L[23] + 8f, by = (L[31] - 14f) * 0.5f + iy;
                rect(bx, by, 14f, 14f, 3f, true, tagSel[i] ? Theme.accent() : Theme.e26C0());
                if (tagSel[i]) {
                    line(bx + 3f, by + 7f, (bx + 7f) - 1f, (by + 14f) - 3f, 1.5f, Theme.text());
                    line((bx + 7f) - 1f, (by + 14f) - 3f, (bx + 14f) - 2f, by + 2f, 1.5f, Theme.text());
                }
                float tx = (bx + 14f) + 8f;
                textBoxEllipsis(TAGS[i], tx, iy, ((L[23] + L[25]) - tx) - 8f, L[31], 4, Theme.text(), false, true);
                iy += L[31];
            }
        }
    }

    private static float[] a(float[] c, float al) { return new float[]{c[0], c[1], c[2], al}; }

    /** "n/max" counter, right aligned; muted until the limit is hit. */
    private static void counter(int n, int max, float right, float y) {
        String s = n + "/" + max;
        float w = textSize(s, 4)[0];
        textBox(s, right - w, y, w, 16f, 4, n < max ? Theme.muted() : Theme.e3AC0(), false, false);
    }

    /** sub_180122650 */
    static void textInput(float x, float y, float w, String s, boolean focused, boolean hover, float h) {
        if (focused) {
            fillRect(x, y, w, h, 4f, Theme.e1D60());
            strokeRect(x, y, w, h, 4f, 1f, Theme.accent());
        } else {
            fillRect(x, y, w, h, 4f, hover ? Theme.hover() : Theme.surface());
            strokeRect(x, y, w, h, 4f, 1f, Theme.e26C0());
        }
        float x0 = x + 8f, maxW = w - 16f;
        float tw = textSize(s, 4)[0];
        if (focused && tw > maxW) {
            pushClip(x + 2f, y, w - 4f, h);
            textBox(s, (maxW - tw) + x0, y + 2f, maxW, h - 4f, 4, Theme.text(), false, true);
            popClip();
        } else {
            textBoxEllipsis(s, x0, y + 2f, maxW, h - 4f, 4, Theme.text(), false, true);
            if (!focused) return;
        }
        float cx = Math.min((w + x) - 8f, tw + x0);
        line(cx, y + 4f, cx, (y + h) - 4f, 1.5f, Theme.accent());
    }

    /** sub_180121320 */
    static void visDropdown(float x, float y, float w, String label, float hov, float open, float h) {
        float g = (((hov * 0.01f + 1f) * w) - w) * 0.5f;
        float gx = x - g, gw = (g + g) + w;
        fillRect(gx, y, gw, h, 4f, lerp(Theme.surface(), Theme.hover(), hov));
        strokeRect(gx, y, gw, h, 4f, 1f, lerp(Theme.e26C0(), Theme.accent(), Math.max(hov, open)));
        textBoxEllipsis(label, (x + 8f) - g, y + 2f, (w - 36f) + (g + g), h - 4f, 3, Theme.text(), false, true);
        float[] c = lerp(Theme.muted(), Theme.accent(), hov);
        float cx = ((x + w) - 12f) + g, cy = h * 0.5f + y;
        float ang = open * 3.1415901f, co = (float) Math.cos(ang), si = (float) Math.sin(ang);
        // v-shaped chevron (-3,-2) (0,2) (3,-2) rotated by open*PI about (cx, cy)
        float px = -2f * si + cx, py = 2f * co + cy;
        float lx = (-3f * co + 2f * si) + cx, ly = (-3f * si - 2f * co) + cy;
        float rx = (3f * co + 2f * si) + cx, ry = (3f * si - 2f * co) + cy;
        line(lx, ly, px, py, 1.5f, c);
        line(px, py, rx, ry, 1.5f, c);
    }

    /** sub_180122120 */
    static void tagsDropdown(float x, float y, float w, boolean hov, float h) {
        fillRect(x, y, w, h, 4f, hov ? Theme.hover() : Theme.surface());
        strokeRect(x, y, w, h, 4f, 1f, hov ? Theme.accent() : Theme.e26C0());
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (int i = 0; i < TAGS.length; i++) if (tagSel[i]) { if (n++ > 0) sb.append(", "); sb.append(TAGS[i]); }
        if (n == 0) sb.append("None");
        float tw = (w - 20f) - 16f;
        String s = ellipsize(sb.toString(), 3, tw);
        if (tw > 0f) {
            float th = textSize(s, 3)[1];
            text(s, x + 8f, (((h - 4f) - th) * 0.5f) + (y + 2f), 3, Theme.text());
        }
        float cx = (w + x) - 12f, cy = h * 0.5f + y;
        line(cx - 3f, cy - 2f, cx, cy + 2f, 1.5f, Theme.muted());
        line(cx, cy + 2f, cx + 3f, cy - 2f, 1.5f, Theme.muted());
    }

    /** sub_18013F270 */
    private static void popupFrame(float x, float y, float w, float h) {
        fillRect(x + 1f, y + 4f, w - 2f, h, 9f, new float[]{0f, 0f, 0f, 0.35f});
        float[] bg = Theme.bg();
        fillRect(x, y, w, h, 8f, new float[]{bg[0], bg[1], bg[2], 1f});
        fillRect(x, y, w, h, 8f, Theme.surface());
        strokeRect(x, y, w, h, 8f, 1f, Theme.e29E0());
    }

    /** sub_18013EF10: one validation row; advances y by 26. */
    private static void checkRow(float[] L, float[] yy, boolean ok, String label) {
        float y = yy[0] + 3f, x = L[34];
        fillRect(x, y, 14f, 14f, 3f, ok ? Theme.e2E90() : Theme.e1D60());
        strokeRect(x, y, 14f, 14f, 3f, 1f, ok ? Theme.e2E90() : Theme.e26C0());
        if (ok) {
            line(x + 3f, y + 7f, x + 6f, y + 11f, 1.5f, WHITE);
            line(x + 6f, y + 11f, x + 12f, y + 3f, 1.5f, WHITE);
        }
        textBox(label, x + 22f, yy[0], L[36] - 22f, 20f, 4, ok ? Theme.text() : Theme.muted(), false, true);
        yy[0] += 26f;
    }

    // ---- config card (sub_1801237F0), preview variant: no hover, Download + Edit buttons ----
    private static void previewCard(float x, float y, float w, String nm, String ds, int count, String author, String time) {
        pushTransform(w * 0.5f + x, y + 90f, 0f, 1f);
        float[] gs = {18f, 10f, 4f}, go = {14f, 7f, 3f}, ga = {0.06f, 0.12f, 0.22f};
        for (int i = 0; i < 3; i++) {
            float s = gs[i];
            fillRect(x - s, (y - s * 0.35f) + go[i], (s + s) + w, (s * 0.8f) + 180f, s + 10f, new float[]{0f, 0f, 0f, ga[i]});
        }
        float[] sb = Theme.sidebar();
        fillRect(x, y, w, 180f, 10f, new float[]{sb[0], sb[1], sb[2], 0.62f});
        fillRect(x + 6f, y + 0.5f, w - 12f, 1f, 0f, new float[]{1f, 1f, 1f, 0.08f});
        strokeRect(x, y, w, 180f, 10f, 1f, Theme.e26C0());
        String cs = Integer.toString(count);
        float bw = textSize(cs, 4)[0] + 24f;
        float bx = ((w + x) - 16f) - bw;
        textBoxEllipsis(nm, x + 16f, y + 16f, (bx - (x + 16f)) - 8f, 24f, 5, Theme.text(), false, false);
        fillRect(bx, y + 16f, bw, 22f, 4f, a(Theme.accent(), 0.15f));
        float[] cz = textSize(cs, 4);
        text(cs, ((bw - cz[0]) * 0.5f) + bx, ((22f - cz[1]) * 0.5f) + (y + 16f), 4, Theme.accent());
        ModulesPage.wrapped(ds, x + 16f, (y + 16f) + 28f, w - 32f, 4, Theme.muted(), 2);
        float half = w * 0.5f - 16f;
        float[] ac = Theme.e2E90();   // "mine" colour (a1+160 set for the preview)
        textEllipsis("by " + author, x + 16f, (y + 16f) + 72f, half, 4, ac);
        float tw = Math.min(textSize(time, 4)[0], half);
        textEllipsis(time, ((w + x) - 16f) - tw, (y + 16f) + 72f, half, 4, Theme.muted());
        // tags row
        float tx = x + 16f, ty = (y + 16f) + 92f, right = (w + x) - 16f;
        boolean any = false;
        for (int i = 0; i < TAGS.length; i++) {
            if (!tagSel[i]) continue;
            any = true;
            float pw = textSize(TAGS[i], 4)[0] + 12f;
            if (pw + tx > right) break;
            fillRect(tx, ty, pw, 20f, 4f, a(Theme.accent(), 0.2f));
            text(TAGS[i], tx + 6f, ty + 2f, 4, Theme.accent());
            tx += pw + 6f;
        }
        if (!any) {
            float pw = textSize("No tags", 4)[0] + 12f;
            fillRect(tx, ty, pw, 20f, 4f, a(Theme.muted(), 0.2f));
            text("No tags", tx + 6f, ty + 2f, 4, Theme.muted());
        }
        // buttons
        float by = ((y + 180f) - 16f) - 32f, bh = 32f, bx0 = x + 16f, bwid = ((w - 32f) - 8f) * 0.5f;
        float[] ap = Theme.accent();
        fillRect(bx0, by, bwid, bh, 6f, new float[]{ap[0] * 0.85f, ap[1] * 0.85f, ap[2] * 0.85f, 1f});
        textBox("Download", bx0, by, bwid, bh, 4, WHITE, true, true);
        float bx1 = (bx0 + bwid) + 8f;
        fillRect(bx1, by, bwid, bh, 6f, new float[]{sb[0], sb[1], sb[2], 0.55f});
        strokeRect(bx1, by, bwid, bh, 6f, 1f, Theme.e26C0());
        float[] es = textSize("Edit", 4);
        text("Edit", ((bwid - es[0]) * 0.5f) + bx1, ((bh - es[1]) * 0.5f) + by, 4, Theme.text());
        popTransform();   // inlined pop at the end of sub_1801237F0 (xmmword_180745410 -= 24)
    }

    // =====================================================================================================
    // input (sub_180145320)
    // =====================================================================================================
    public static boolean click(int button) {
        if (!active() || button != 0) return false;
        float[] L = layout();
        boolean popupHandled = false;
        if (visOpen) {
            float y = (L[22] + L[20]) + L[32];
            if (hit(L[19], y, L[21], L[31] * 2f + L[33] * 2f)) {
                int idx = (int) Math.floor(((int) Input.my - (L[33] + y)) / L[31]);
                if (idx >= 0 && idx <= 1) visibility = idx;
                visOpen = false;
                return true;
            }
            if (hit(L[19], L[20], L[21], L[22])) { visOpen = false; return true; }
            visOpen = false;
            popupHandled = true;
        } else if (tagsOpen) {
            float y = (L[26] + L[24]) + L[32];
            if (hit(L[23], y, L[25], TAGS.length * L[31] + L[33] * 2f)) {
                int idx = (int) Math.floor(((int) Input.my - (L[33] + y)) / L[31]);
                if (idx >= 0 && idx < TAGS.length) tagSel[idx] = !tagSel[idx];
                return true;
            }
            tagsOpen = false;
            if (hit(L[23], L[24], L[25], L[26])) return true;
            popupHandled = true;
        }
        if (hit(L[9], L[10], L[11], L[12])) { nameFocus = true; descFocus = false; return true; }
        if (hit(L[14], L[15], L[16], L[17])) { descFocus = true; nameFocus = false; return true; }
        if (hit(L[19], L[20], L[21], L[22])) { visOpen = true; tagsOpen = false; nameFocus = descFocus = false; return true; }
        if (hit(L[23], L[24], L[25], L[26])) { tagsOpen = true; visOpen = false; nameFocus = descFocus = false; return true; }
        if (!popupHandled && hit(L[27], L[28], L[29], L[30])) { createRequested = true; return true; }
        nameFocus = descFocus = false;
        return false;
    }

    public static boolean scroll(double dy) { return false; }

    /** GLFW key press: Backspace edits the focused field (the original handles it as WM_CHAR 8). */
    public static boolean key(int glfwKey) {
        if (!active()) return false;
        if (glfwKey == 259) return typed(8);
        return false;
    }

    /** WM_CHAR handling: backspace, printable ASCII except ':' ';' '=' (mask 0x2C000000FFFFFFFF), length caps 20 / 50. */
    public static boolean typed(int cp) {
        if (!active()) return false;
        StringBuilder sb; int max;
        if (nameFocus) { sb = name; max = 20; }
        else if (descFocus) { sb = desc; max = 50; }
        else return false;
        if (cp == 8) {
            if (sb.length() > 0) { sb.setLength(sb.length() - 1); return true; }
            return false;
        }
        if (cp <= 0x3D && ((0x2C000000FFFFFFFFL >>> cp) & 1L) != 0) return false;
        if (cp >= 0x7F || sb.length() >= max) return false;
        sb.append((char) cp);
        return true;
    }

    /** Popup hover tracking (mouse-move handler). */
    public static void update(float dt) {
        hoverVis = -1; hoverTag = -1;
        if (!active()) return;
        float[] L = layout();
        if (visOpen) {
            float y = (L[22] + L[20]) + L[32] + L[33];
            for (int i = 0; i < 2; i++, y += L[31])
                if (hit(L[19], y, L[21], L[31])) { hoverVis = i; break; }
        }
        if (tagsOpen) {
            float y = (L[26] + L[24]) + L[32] + L[33];
            for (int i = 0; i < TAGS.length; i++, y += L[31])
                if (hit(L[23], y, L[25], L[31])) { hoverTag = i; break; }
        }
    }
}
