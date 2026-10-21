package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Port of the Configs page (page 8): header, tab strip (Public / Personal / Create), search field,
 * config card grid (sub_1801237F0), pagination, loading spinner. Region clean.c 1492-2790.
 * The body of tab 2 is drawn by CreateConfigPage.draw().
 */
public final class ConfigsPage {
    private ConfigsPage() {}

    /** One config entry (struct behind qword_180745268 / qword_1807452B0, 0xB0 bytes in the original). */
    public static final class Cfg {
        public int id;                 // +0
        public String name = "";       // +8
        public String desc = "";       // +40
        public String date = "";       // +72   shown right-aligned
        public String author = "";     // +104  shown as "by <author>"
        public final List<String> tags = new ArrayList<>();   // +136
        public boolean owned;          // +160  true -> Download + Edit buttons
        public int downloads;          // +164
        public Cfg() {}
        public Cfg(int id, String name, int downloads, String desc, String author, String date, boolean owned, String... tags) {
            this.id = id; this.name = name; this.downloads = downloads; this.desc = desc; this.author = author;
            this.date = date; this.owned = owned;
            for (String t : tags) this.tags.add(t);
        }
    }

    /** qword_180745268: public configs of the current page. */
    public static final List<Cfg> publicCfgs = new ArrayList<>();
    /** qword_1807452B0: the user's own configs. */
    public static final List<Cfg> personalCfgs = new ArrayList<>();

    /** Which tab is active: 0 public, 1 personal, 2 create (CreateConfigPage.draw() renders the create tab). */
    public static int tab = 0;                       // dword_180708A14

    // ---- state globals ----
    public static String query = "";                 // qword_1806FC218 (search text)
    public static boolean searchFocused;             // byte_180708A4E
    public static boolean loading;                   // byte_180708A23 (spinner instead of the list)
    public static int pageIndex = 0;                 // *dword_180708A18
    public static int pageTotal = 756;               // dword_18070896C
    public static float scrollCur;                   // flt_1807089F4
    public static float scrollTarget;                // dword_1807089F0
    public static float spin;                        // dword_180708A0C (degrees)
    public static int selected = -1;                 // dword_1804CCED0 (card in edit mode)
    public static int hoverCard = -1;                // dword_1804CCE54
    public static int hoverBtn = -1;                 // dword_1804CCE20
    public static int hoverArrow = -1;               // dword_1804CCE70 (0 prev, 1 next)
    public static String currentUser = "";           // xmmword_180745620 (own author name is drawn in accent)

    // tab strip animation
    public static float tabPos = 0f;                 // dword_180745584 (indicator position, in tabs)
    public static final float[] tabHover = new float[3];   // dword_180745578/7C/80
    public static float tabEnter = 1f;               // flt_1806FD728 (restarts on tab change)
    /** verification only: keep tabPos/tabHover as set by the fixture. */
    public static boolean freezeTabs;
    private static int lastTab = 0;                  // dword_1806FC23C

    // hooks for the host (network / dialogs)
    public interface Hook { void run(Cfg c); }
    public static Hook onDownload = c -> {};
    public static Hook onEdit = c -> {};
    public static Hook onUpdate = c -> {};           // opens "Update Config" confirm popup
    public static Hook onDelete = c -> {};           // opens "Delete Config" confirm popup
    public static Runnable onPageChange = () -> {};  // refetch after pageIndex changed
    public static Runnable onSearch = () -> {};      // Enter pressed in the search box
    public static Runnable onTabChange = () -> {};

    // per-card animation (maps keyed by config id)
    private static final Map<Integer, float[]> ANIM = new HashMap<>();   // {hover, press, flash, lastDownloads}

    static List<Cfg> list() { return tab == 0 ? publicCfgs : personalCfgs; }

    /** v875: enter animation shared with CreateConfigPage (min of tab-switch and page-enter easing). */
    public static float enter() {
        float a = Lib.easeOutCubic(tabEnter);
        float b = Lib.easeOutCubic(ThemesPage.enterB);
        return Math.min(a, b);
    }

    // ---- layout helpers ----
    static float x0() { return Ui.S.sideW + 30f; }                       // v108
    static float cardW() { return ((1100f - Ui.S.sideW - 60f) - 40f) / 3f; }   // v390
    static boolean inRect(float x, float y, float w, float h) { return Input.mx >= x && Input.mx <= x + w && Input.my >= y && Input.my <= y + h; }

    public static void draw() {
        Ui.State S = Ui.S;
        float dt = S.dt;
        float v108 = x0();
        hoverCard = -1; hoverBtn = -1;
        textBox("Configs", v108, 30f, 400f, 30f, 8, Theme.text(), false, false);

        String[] names = {"Public configs", "Personal configs", "Create config"};
        float v347 = v108 + 150f + 12f, v348 = v108 + 324f;
        float[] tx = {v108, v347, v348};
        for (int i = 0; i < 3; i++) {
            boolean in = inRect(tx[i], 75f, 150f, 36f) && Input.my >= 75f && Input.my <= 111f;
            if (!freezeTabs) tabHover[i] = Lib.smooth(tabHover[i], in ? 1f : 0f, dt, 10f);
        }
        if (!freezeTabs) tabPos = Lib.smooth(tabPos, (float) tab, dt, 16f);
        for (int i = 0; i < 3; i++) {
            float[] c = Lib.lerp(Theme.surface(), Theme.hover(), tabHover[i]);
            rect(tx[i], 75f, 150f, 36f, 8f, true, c);
        }
        rect(tabPos * 162f + v108, 75f, 150f, 36f, 8f, true, Theme.accent());
        for (int i = 0; i < 3; i++) {
            float t = 1f - Math.abs(i - tabPos);
            if (t < 0f) t = 0f;
            float[] c = Lib.lerp(Theme.muted(), Theme.text(), t);
            textBox(names[i], tx[i], 75f, 150f, 36f, 3, c, true, true);
        }

        float v362 = v348 + 150f + 24f;
        if (tab <= 1) drawSearch(v362);

        float v875 = enter();
        if (tab == 2) { CreateConfigPage.draw(); return; }
        if (loading) { drawSpinner(); return; }
        drawGrid(v108, v875);
        if (tab == 0 && pageTotal > 0) drawPager(v108);
    }

    // ------------------------------------------------------------------ search box
    static void drawSearch(float v362) {
        boolean hov = inRect(v362, 75f, 260f, 36f);
        float[] fill = searchFocused ? Theme.e1D60() : (hov ? Theme.hover() : Theme.surface());
        rect(v362, 75f, 260f, 36f, 8f, true, fill);
        rect(v362, 75f, 260f, 36f, 8f, false, searchFocused ? Theme.accent() : Theme.e26C0());
        Ui.searchIcon(v362 + 12f, 85.5f, 15f, Theme.muted());
        boolean empty = query.isEmpty();
        float[] col = empty ? Theme.muted() : Theme.text();
        String s = empty ? (tab == 0 ? "Search public configs" : "Search your configs") : query;
        float v373 = v362 + 36f;
        float tw = textSize(s, 4)[0];
        if (!searchFocused || empty || tw <= 214f) {
            textBoxEllipsis(s, v373, 75f, 214f, 36f, 4, col, false, true);
        } else {
            pushClip(v373, 77f, 214f, 32f);
            textBox(s, (214f - tw) + v373, 75f, 214f, 36f, 4, col, false, true);
            popClip();
        }
        if (searchFocused && ((System.currentTimeMillis() / 500L) & 1L) == 0L) {
            float qw = empty ? 0f : textSize(query, 4)[0];
            float cx = Math.min(v373 + 214f, qw + v373);
            line(cx, 85f, cx, 101f, 1.5f, Theme.accent());
        }
    }

    // ------------------------------------------------------------------ loading spinner
    static void drawSpinner() {
        float S0 = Ui.S.sideW;
        float cx = (1100f - S0) * 0.5f + S0;
        float k = (float) (Math.sin((Ui.S.time * 0.9f) * 6.2831855f + 0f) * 0.5 + 0.5) * 0.15f + 0.85f;
        float r = k * 15f;
        for (int i = 0; i < 8; i++) {
            float ang = (i * 45f + spin) * 0.017453279f;
            float px = (float) Math.cos(ang) * r + cx, py = (float) Math.sin(ang) * r + 350f;
            float[] g = Theme.e2210().clone();
            g[3] = (1f - i * 0.125f) * 0.35f;
            circleFilled(px, py, k * 4f, g);
            float[] a = Theme.accent();
            circleFilled(px, py, k + k, new float[]{a[0], a[1], a[2], 1f - i * 0.125f});
        }
        textBox("Fetching more configs", cx - 150f, 400f, 300f, 30f, 3, Theme.muted(), true, true);
    }

    // ------------------------------------------------------------------ card grid
    static void drawGrid(float v108, float v875) {
        Ui.State S = Ui.S;
        float v107 = S.sideW;
        boolean pub = tab == 0;
        float clipH = pub ? 514f : 554f;
        float scr = pub ? scrollCur : 0f;
        pushClip(v107, 131f, 1100f - v107, clipH);
        float v389 = (1100f - v107) - 60f;
        float w = (v389 - 40f) / 3f;
        float v391 = 131f - scr;
        List<Cfg> L = list();
        float stagMax = pub ? 0.45f : 0.45f;
        for (int i = 0; i < L.size(); i++) {
            float x = (i % 3) * (w + 20f) + v108;
            float y = (i / 3) * 200f + v391;
            boolean in = !(Input.mx < x || x + w < Input.mx || Input.my < y || y + 180f < Input.my);
            boolean sel = selected == i;
            int btn = -1;
            if (in) {
                hoverCard = i;
                btn = hitButton(L.get(i), i, x, y, w, sel);
                hoverBtn = btn;
            }
            float st = ThemesPage.stagger(v875, i, 0.05f, stagMax);
            pushTransform(w * 0.5f + x, (1f - st) * 22f + y + 90f, 0f, st * 0.04f + 0.96f);
            card(L.get(i), x, (1f - st) * 22f + y, w, in, btn, sel);
            popTransform();
        }
        if (L.isEmpty()) {
            float[] c = Theme.muted();
            if (!pub) textBoxEllipsis("No configs yet. Make one in the Create tab.", v108, 171f, v389, 30f, 3, c, true, true);
            else if (query.isEmpty()) textBoxEllipsis("No public configs available.", v108, 171f, v389, 30f, 3, c, true, true);
            else textBoxEllipsis("No public configs match \"" + query + "\".", v108, 171f, v389, 30f, 3, c, true, true);
        }
        popClip();
    }

    /** Which button of the card the mouse is over: 0..2 or -1 (same geometry as the original hit tests). */
    static int hitButton(Cfg c, int idx, float x, float y, float w, boolean sel) {
        float by = (y + 180f - 16f) - 32f;
        if (Input.my < by || by + 32f < Input.my) return -1;
        float mx = Input.mx;
        float bx = x + 16f;
        if (sel) {
            float cell = ((w - 32f) - 16f) / 3f;
            if (mx >= bx && bx + cell >= mx) return 0;
            if (mx >= bx + cell + 8f && bx + cell + 8f + cell >= mx) return 1;
            if (mx >= bx + (cell + 8f) * 2f && (x + w) - 16f >= mx) return 2;
        } else if (c.owned) {
            float cell = ((w - 32f) - 8f) * 0.5f;
            if (mx >= bx && bx + cell >= mx) return 0;
            if (mx >= bx + cell + 8f && (x + w) - 16f >= mx) return 1;
        } else if (mx >= bx && (x + w) - 16f >= mx) return 0;
        return -1;
    }

    static float[] anim(Cfg c) {
        return ANIM.computeIfAbsent(c.id, k -> new float[]{0f, 0f, 0f, c.downloads});
    }

    /** sub_1801237F0 */
    static void card(Cfg c, float x, float y, float w, boolean hovered, int btn, boolean sel) {
        float dt = Ui.S.dt;
        float[] an = anim(c);
        if (dt > 0f) an[0] += ((hovered ? 1f : 0f) - an[0]) * (1f - (float) Math.exp(dt * -12f));
        float pt = hovered && Input.down ? 1f : 0f;
        if (dt > 0f) an[1] += (pt - an[1]) * (1f - (float) Math.exp(dt * -30f));
        if (c.downloads > an[3]) { an[2] = 1f; }
        an[3] = c.downloads;
        if (dt > 0f) an[2] += (0f - an[2]) * (1f - (float) Math.exp(dt * -3f));
        if (Lib.STEADY) { an[0] = hovered ? 1f : 0f; an[1] = 0f; }
        float h = an[0], p = an[1], fl = an[2];

        pushTransform(w * 0.5f + x, y + 90f, 0f, (h * 0.015f + 1f) - p * 0.02f);
        float[] ss = {h * 8f + 18f, h * 5f + 10f, h * 2f + 4f};
        float[] so = {h * 10f + 14f, h * 6f + 7f, h * 3f + 3f};
        float[] sa = {h * 0.05f + 0.06f, h * 0.08f + 0.12f, h * 0.12f + 0.22f};
        for (int i = 0; i < 3; i++) {
            float s = ss[i];
            fillRect(x - s, (y - s * 0.35f) + so[i], (s + s) + w, s * 0.8f + 180f, s + 10f, new float[]{0f, 0f, 0f, sa[i]});
        }
        float[] sb = Theme.sidebar();
        fillRect(x, y, w, 180f, 10f, new float[]{sb[0], sb[1], sb[2], h * 0.1f + 0.62f});
        fillRect(x + 6f, y + 0.5f, w - 12f, 1f, 0f, new float[]{1f, 1f, 1f, h * 0.1f + 0.08f});
        strokeRect(x, y, w, 180f, 10f, 1f, Lib.lerp(Theme.e26C0(), Theme.e2850(), h));

        // downloads pill
        String dl = Integer.toString(c.downloads);
        float numW = textSize(dl, 4)[0] + 24f;
        float px = ((w + x) - 16f) - numW;
        textBoxEllipsis(c.name, x + 16f, y + 16f, (px - (x + 16f)) - 8f, 24f, 5, Theme.text(), false, false);
        float[] ac = Theme.accent();
        fillRect(px, y + 16f, numW, 22f, 4f, new float[]{ac[0], ac[1], ac[2], fl * 0.55f + 0.15f});
        if (fl > 0.02f) {
            float[] g = Theme.e2210();
            fillRect(px - 3f, y + 13f, numW + 6f, 28f, 6f, new float[]{g[0], g[1], g[2], fl * 0.35f});
        }
        if (!dl.isEmpty()) {
            float[] sz = textSize(dl, 4);
            text(dl, ((numW - sz[0]) * 0.5f) + px, ((22f - sz[1]) * 0.5f) + y + 16f, 4, ac);
        }

        // description
        ModulesPage.wrapped(c.desc, x + 16f, y + 16f + 28f, w - 32f, 4, Theme.muted(), 2);

        // author / date
        float[] acol;
        if (c.owned) acol = Theme.e2E90();
        else if (!currentUser.isEmpty() && currentUser.equals(c.author)) acol = Theme.accent();
        else acol = Theme.muted();
        float half = (w * 0.5f) - 16f;
        float ay = y + 16f + 72f;
        if (half > 0f) text(ellipsize("by " + c.author, 4, half), x + 16f, ay, 4, acol);
        float dwid = textSize(c.date, 4)[0];
        float dx = ((w + x) - 16f) - Math.min(dwid, half);
        if (!c.date.isEmpty() && half > 0f) text(ellipsize(c.date, 4, half), dx, ay, 4, Theme.muted());

        // tags
        float tx = x + 16f, ty = y + 16f + 92f;
        if (c.tags.isEmpty()) {
            float tw = textSize("No tags", 4)[0] + 12f;
            float[] m = Theme.muted();
            fillRect(tx, ty, tw, 20f, 4f, new float[]{m[0], m[1], m[2], 0.2f});
            text("No tags", tx + 6f, ty + 2f, 4, m);
        } else {
            float lim = (w + x) - 16f;
            for (String tg : c.tags) {
                float tw = textSize(tg, 4)[0] + 12f;
                if (tw + tx > lim) break;
                fillRect(tx, ty, tw, 20f, 4f, new float[]{ac[0], ac[1], ac[2], 0.2f});
                if (!tg.isEmpty()) text(tg, tx + 6f, ty + 2f, 4, ac);
                tx += tw + 6f;
            }
        }

        // buttons
        float by = (y + 180f) - 16f - 32f, bh = 32f;
        float bx = x + 16f, avail = w - 32f;
        float[] white = {1f, 1f, 1f, 1f};
        if (sel) {
            float cell = (avail - 16f) / 3f;
            secondary(bx, by, cell, bh, btn == 0);
            textBox("Back", bx, by, cell, bh, 4, Theme.text(), true, true);
            float ux = (bx + cell) + 8f;
            primary(ux, by, cell, bh, btn == 1);
            textBox("Update", ux, by, cell, bh, 4, white, true, true);
            float dx2 = (ux + cell) + 8f;
            if (btn == 2) {
                for (int i = 3; i >= 1; i--) {
                    float v = i * 3f;
                    fillRect(dx2 - v, by - v, (v + v) + cell, (v + v) + bh, v + 6f, new float[]{0.937f, 0.267f, 0.267f, 0.1f / i});
                }
            }
            fillRect(dx2, by, cell, bh, 6f, btn == 2 ? new float[]{0.937f, 0.267f, 0.267f, 1f} : new float[]{0.7496f, 0.2136f, 0.2136f, 1f});
            textBox("Delete", dx2, by, cell, bh, 4, white, true, true);
        } else if (c.owned) {
            float cell = (avail - 8f) * 0.5f;
            primary(bx, by, cell, bh, btn == 0);
            textBox("Download", bx, by, cell, bh, 4, white, true, true);
            float ex = (bx + cell) + 8f;
            secondary(ex, by, cell, bh, btn == 1);
            textBox("Edit", ex, by, cell, bh, 4, Theme.text(), true, true);
        } else {
            primary(bx, by, avail, bh, btn == 0);
            textBox("Download", bx, by, avail, bh, 4, white, true, true);
        }
        popTransform();
    }

    /** sub_1801235D0 */
    static void primary(float x, float y, float w, float h, boolean hot) {
        float[] a = Theme.accent();
        float[] fill;
        if (hot) {
            for (int i = 3; i >= 1; i--) {
                float v = i * 3f;
                fillRect(x - v, y - v, (v + v) + w, (v + v) + h, v + 6f, new float[]{a[0], a[1], a[2], 0.1f / i});
            }
            fill = new float[]{a[0], a[1], a[2], a[3]};
        } else {
            fill = new float[]{a[0] * 0.85f, a[1] * 0.85f, a[2] * 0.85f, 1f};
        }
        fillRect(x, y, w, h, 6f, fill);
    }

    /** sub_180125170 */
    static void secondary(float x, float y, float w, float h, boolean hot) {
        float[] s = Theme.sidebar();
        fillRect(x, y, w, h, 6f, new float[]{s[0], s[1], s[2], hot ? 0.85f : 0.55f});
        strokeRect(x, y, w, h, 6f, 1f, hot ? Theme.e2850() : Theme.e26C0());
    }

    // ------------------------------------------------------------------ pagination
    static void drawPager(float v108) {
        float v389 = (1100f - Ui.S.sideW) - 60f;
        float v419 = (v389 * 0.5f) + v108;
        String s = pageIndex + "/" + pageTotal;
        textBox(s, v419 - 30f, 660f, 60f, 25f, 3, Theme.text(), true, true);
        if (pageIndex > 0) {
            float x = (v419 - 50f) - 30f;
            arrow("<", x, hoverArrow == 0);
        }
        if (pageIndex < pageTotal) {
            arrow(">", v419 + 50f, hoverArrow == 1);
        }
    }

    static void arrow(String g, float x, boolean hot) {
        rect(x, 655f, 30f, 30f, 6f, true, hot ? Theme.hover() : Theme.surface());
        rect(x, 655f, 30f, 30f, 6f, false, hot ? Theme.accent() : Theme.e26C0());
        textBox(g, x, 655f, 30f, 30f, 1, hot ? Theme.accent() : Theme.muted(), true, true);
    }

    // ------------------------------------------------------------------ input
    /** Left/right click in canvas coords (Input.mx/my already set). Return true if consumed. */
    public static boolean click(int button) {
        if (button != 0) return false;
        float v108 = x0();
        if (Input.my >= 75f && Input.my <= 111f) {
            int t = -1;
            if (Input.mx >= v108 && v108 + 150f >= Input.mx) t = 0;
            else if (Input.mx >= v108 + 162f && v108 + 312f >= Input.mx) t = 1;
            else if (Input.mx >= v108 + 324f && v108 + 474f >= Input.mx) t = 2;
            if (t >= 0) { setTab(t); return true; }
            if (tab <= 1 && Input.mx >= v108 + 498f && v108 + 758f >= Input.mx) { searchFocused = true; return true; }
        }
        if (tab == 2) return false;
        if (tab <= 1) searchFocused = false;
        if (loading) return false;
        if (tab == 0) {
            float v419 = ((((1100f - Ui.S.sideW) - 60f) * 0.5f) + v108);
            float pv = v419 - 50f, nx = v419 + 50f;
            if (Input.mx >= pv - 30f && pv >= Input.mx && Input.my >= 655f && Input.my <= 685f && pageIndex > 0) {
                pageIndex--; resetList(); onPageChange.run(); return true;
            }
            if (Input.mx >= nx && nx + 30f >= Input.mx && Input.my >= 655f && Input.my <= 685f && pageIndex < pageTotal) {
                pageIndex++; resetList(); onPageChange.run(); return true;
            }
        }
        if (hoverCard >= 0 && hoverCard < list().size() && hoverBtn >= 0) {
            Cfg c = list().get(hoverCard);
            if (selected == hoverCard) {
                if (hoverBtn == 0) selected = -1;
                else if (hoverBtn == 1) onUpdate.run(c);
                else if (hoverBtn == 2 && c.owned) onDelete.run(c);
            } else if (hoverBtn == 0) onDownload.run(c);
            else if (hoverBtn == 1 && c.owned) { selected = hoverCard; onEdit.run(c); }
            return true;
        }
        return false;
    }

    static void setTab(int t) {
        tab = t;
        searchFocused = false;
        onTabChange.run();
    }

    static void resetList() { scrollTarget = 0f; hoverCard = -1; hoverBtn = -1; }

    /** Mouse wheel. Return true if consumed. */
    public static boolean scroll(double dy) {
        if (tab > 1) return false;
        scrollTarget -= (float) dy * 40f * 0.5f;
        int n = list().size();
        float max = ((n / 3) * 200f + 20f) - (569f - (tab != 0 ? 15f : 55f));
        if (max < 0f) max = 0f;
        if (scrollTarget < 0f) scrollTarget = 0f; else if (scrollTarget > max) scrollTarget = max;
        return true;
    }

    /** Key press (GLFW key code). Return true if consumed. */
    public static boolean key(int glfwKey) {
        if (tab > 1 || !searchFocused) return false;
        if (glfwKey == 259 && !query.isEmpty()) { query = query.substring(0, query.length() - 1); return true; }   // backspace
        if (glfwKey == 257 || glfwKey == 335) { onSearch.run(); return true; }                                   // enter
        if (glfwKey == 256) { searchFocused = false; return true; }                                              // escape
        return glfwKey == 259;
    }

    /** Typed character. Return true if consumed. */
    public static boolean typed(int codepoint) {
        if (tab > 1 || !searchFocused || codepoint < 32) return false;
        query += new String(Character.toChars(codepoint));
        return true;
    }

    /** Per-frame animation/hover update (called from Input.update before draw). */
    public static void update(float dt) {
        if (tab != lastTab) { lastTab = tab; tabEnter = 0f; }
        tabEnter = Math.min(1f, tabEnter + dt * 2.4f);
        if (loading) { spin += dt * 360f; if (spin >= 360f) spin -= 360f; }
        scrollCur = Lib.lerp(scrollCur, scrollTarget, dt * 4f);
        hoverArrow = -1;
        LocalConfigs.tick();
        if (tab == 0 && !loading && pageTotal > 0) {
            float v419 = ((((1100f - Ui.S.sideW) - 60f) * 0.5f) + x0());
            if (Input.my >= 655f && Input.my <= 685f) {
                if (pageIndex > 0 && Input.mx >= (v419 - 80f) && Input.mx <= v419 - 50f) hoverArrow = 0;
                else if (pageIndex < pageTotal && Input.mx >= v419 + 50f && Input.mx <= v419 + 80f) hoverArrow = 1;
            }
        }
    }
}
