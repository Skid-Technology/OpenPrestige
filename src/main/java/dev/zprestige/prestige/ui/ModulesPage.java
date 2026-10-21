package dev.zprestige.prestige.ui;

import static dev.zprestige.prestige.ui.Lib.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Port of the modules page section of sub_180131B70 (header, gamemodes strip, view toggle, module cards). */
public final class ModulesPage {
    private ModulesPage() {}

    public static final String[] GAMEMODES = {"Crystal", "Mace", "Sword", "Spear", "UHC", "Cart"};

    public static final class Mod {
        public String category, name, desc;
        public boolean enabled;
        public float toggleT = -1f;                       // qword_180744B60[...]
        public float hoverLift, glow, press;              // qword_180744CA8[...] floats [2],[3],[4]
        public dev.zprestige.prestige.ui.bridge.Bridge.LiveModule live;    // running client module, or null in fallback mode
        public Mod(String c, String n, String d) { category = c; name = n; desc = d; }
    }

    /** Page state (data names from the original). */
    public static final class St {
        public List<Mod> mods = new ArrayList<>();
        public float detailT;                             // dword_180708A28 (module detail panel open 0..1)
        public float gamemodeAnim;                        // flt_1807089A8
        public float gmBtnHover;                          // flt_180708A68
        public boolean gamemodes;                         // byte_180708A4F
        public int gamemode;                              // dword_180708A00
        public int cat;                                   // dword_180708A30 (selected category = page index)
        public boolean grid;                              // dword_180708A24 == 1
        public float viewAnim;                            // flt_180708998
        public float[] viewHover = new float[2];          // qword_180744C70
        public float scroll;                              // dword_1807089CC
        public float enterA = 1f, enterB = 1f;            // qword_1804CCEA0 (list entrance animation, 1 = done)
        public int hoverCard = -1;                        // dword_1804CCE6C
        public boolean mouseDown;                         // byte_18070897D
        public int mx = -10000, my = -10000;              // qword_180708A40 (window-local)
    }
    public static final St P = new St();

    static float sideW() { return Ui.S.sideW; }

    /** Cards of the current category (or gamemode) in display order. */
    static List<Mod> visible(String[] catNames) {
        List<Mod> out = new ArrayList<>();
        String c = catNames[P.cat];
        for (Mod m : P.mods) if (m.category.equals(c)) out.add(m);
        return out;
    }

    public static void draw(String[] catNames) {
        float v108 = sideW() + 30f;
        float v883 = 1100f - sideW();
        String title = P.gamemodes && P.gamemode <= 5 ? GAMEMODES[P.gamemode] : catNames[P.cat];
        boolean gm = P.gamemodes;
        List<int[]> ents = gm ? Input.entries() : null;     // gamemode view: modules of all categories whose mask has the chip bit
        List<Mod> list = visible(catNames);
        if (gm) { list = new ArrayList<>(); for (int[] e : ents) list.add(Input.mod(e[0], e[1])); list.removeIf(java.util.Objects::isNull); }

        // ---- header ----
        float titleMax = ((760f - v108) - textSize(" Modules", 8)[0]) - 12f;
        String t = ellipsize(title, 9, titleMax);
        float[] ts = textSize(t, 9);
        text(t, v108, 30f, 9, Theme.secondary());
        text(" Modules", ts[0] + v108, 30f, 8, Theme.text());
        int enabled = 0;
        for (Mod m : list) if (m.enabled) enabled++;
        text(String.format(Locale.ROOT, "%d modules · %d enabled", list.size(), enabled), v108, ts[1] + 30f + 4f, 3, Theme.muted());

        // ---- gamemodes button / strip ----
        float detail = P.detailT;
        float bx = v108 + 4f, by = 98f + (36f - 24f) * 0.5f, bw = textSize("Gamemodes", 3)[0] + 28f, bh = 24f;
        float g = (1f - detail) * P.gamemodeAnim;
        float[] c1 = lerp(Theme.e31C0(), Theme.e3750(), g);
        c1 = lerp(c1, Theme.e3750(), P.gmBtnHover * 0.4f);
        rect(bx, by, bw, bh, bh * 0.5f, true, c1);
        float hv = Math.max(g, P.gmBtnHover * 0.6f);
        rect(bx, by, bw, bh, bh * 0.5f, false, lerp(Theme.e26C0(), Theme.border(), hv));
        float[] tc = lerp(Theme.muted(), Theme.text(), Math.max(g, P.gmBtnHover * 0.7f));
        if (detail < 0.999f) textBox("Gamemodes", bx, by, bw, bh, 3, withAlpha(tc, 1f - detail), true, true);

        if (detail > 0.001f) {
            float[] tcb = lerp(Theme.muted(), Theme.text(), Math.max(g, P.gmBtnHover * 0.7f));
            float[] bc = {tcb[0], tcb[1], tcb[2], tcb[3] * detail};
            float tw = textSize("Back", 3)[0];
            float v154 = ((bw - (tw + 16f)) * 0.5f) + bx;
            chevron((v154 + 5f) - P.gmBtnHover * 3f, bh * 0.5f + by, 10f, bc, 0.5f);
            float v156 = v154 + 16f;
            textBox("Back", v156, by, bw - (v156 - bx), bh, 3, bc, false, true);
        }

        // gamemode chip strip (Crystal/Mace/Sword/Spear/UHC/Cart), fades out into the "Back" state while a module is open
        WidgetsPage.gamemodeStrip(P.gamemodeAnim, P.detailT, Input.modeSel, Input.modeHover);

        // ---- view toggle ----
        if (detail < 0.999f) {
            float oldA = gAlpha;
            gAlpha = (1f - detail) * gAlpha;
            float vx = ((v108 + (v883 - 60f)) - 68f) - 4f, vy = (36f - 24f) * 0.5f + 98f;
            rect(vx, vy, 68f, 24f, 12f, true, Theme.e31C0());
            for (int i = 0; i < 2; i++) {
                if (P.viewHover[i] > 0.01f) {
                    rect((i * 34f + vx) + 2f, vy + 2f, 30f, 20f, 10f, true, lerp(Theme.e31C0(), Theme.e3750(), P.viewHover[i] * 0.4f));
                }
            }
            rect(34f * P.viewAnim + vx + 2f, vy + 2f, 30f, 20f, 10f, true, Theme.e3750());
            float hmax = Math.max(P.viewHover[0], P.viewHover[1]);
            rect(vx, vy, 68f, 24f, 12f, false, lerp(Theme.e26C0(), Theme.border(), hmax * 0.6f));
            for (int i = 0; i < 2; i++) {
                float d = Math.abs(P.viewAnim - i);
                float s = d <= 1f ? 1f - d : 0f;
                float k = Math.max(s, P.viewHover[i] * 0.7f);
                float[] ic = lerp(Theme.muted(), Theme.text(), k);
                float cx = vx + 34f * i + 17f, cy = 12f * 1f + vy;
                float x0 = cx - 6f, y0 = cy - 6f;
                if (i == 0) {
                    fillRect(x0, y0, 12f, 2f, 0f, ic);
                    fillRect(x0, cy - 1f, 12f, 2f, 0f, ic);
                    fillRect(x0, cy + 4f, 12f, 2f, 0f, ic);
                } else {
                    rect(x0, y0, 5f, 5f, 1f, true, ic);
                    rect(cx + 1f, cy - 6f, 5f, 5f, 1f, true, ic);
                    rect(x0, cy + 1f, 5f, 5f, 1f, true, ic);
                    rect(cx + 1f, cy + 1f, 5f, 5f, 1f, true, ic);
                }
            }
            gAlpha = oldA;
        }

        // ---- cards ----
        pushClip(sideW(), 147f, v883, 538f);
        float cardH = P.grid ? 120f : 75f;
        float wFull = (1100f - sideW()) - 60f;
        float wCol = (wFull - 20f) / 3f;
        float y0 = 152f - P.scroll;
        float v208 = 1f - Lib.easeInOutQuad(P.detailT);   // 1 - sub_1800E0B50(detailT)
        float v885 = -Lib.easeInOutCubic(P.detailT) * 40f;
        if (gm && P.detailT < 0.999f) {
            Input.Layout L = Input.layout(ents);
            for (int i = 0; i < ents.size(); i++) {
                Mod m = Input.mod(ents.get(i)[0], ents.get(i)[1]);
                if (m == null) continue;
                float[] r = L.r[i];
                if (L.hdr[i] >= 0f) text(Ui.CATS[ents.get(i)[0]], v108 + v885, L.hdr[i] + 2f, 5, withAlpha(Theme.muted(), v208 * Lib.easeOutCubic(P.enterA)));
                card(m, i, r[0], r[1], r[2], r[3], v208, v885, v108);
            }
            list = new ArrayList<>();
        }
        for (int i = 0; i < list.size() && P.detailT < 0.999f; i++) {
            Mod m = list.get(i);
            float lx, ly, lw;
            if (P.grid) { lx = v108 + (i % 3) * (wCol + 10f); ly = y0 + (i / 3) * (cardH + 10f); lw = wCol; }
            else { lx = v108; ly = y0 + i * (cardH + 10f); lw = wFull; }
            card(m, i, lx, ly, lw, cardH, v208, v885, v108);
        }
        popClip();
        DetailPage.draw();
    }

    private static float clamp01(float v) { return v < 0f ? 0f : Math.min(v, 1f); }

    private static void card(Mod m, int i, float lx, float ly, float lw, float lh, float v208, float v885, float v108) {
        boolean hovered = P.hoverCard == i;
        // per-card animation (steady in verification frames)
        m.hoverLift = Lib.lerp(m.hoverLift, hovered ? 1f : 0f, Ui.S.dt * 12f);
        m.glow = Lib.lerp(m.glow, m.enabled ? 0.3f : 0f, Ui.S.dt * 8f);
        m.press = Lib.lerp(m.press, hovered && P.mouseDown ? 1f : 0f, Ui.S.dt * 30f);
        float stag = Math.min(i * 0.08f, 0.85f);
        float den = Math.max(1f - stag, 0.01f);
        float tA = clamp01((P.enterA - stag) / den), tB = clamp01((P.enterB - stag) / den);
        float v241 = Lib.easeOutCubic(tA) * v208;
        float tB2 = Math.min(tB * 1.1f, 1f);
        float v243 = Lib.easeOutBack(tB2);
        float x = ((1f - v243) * 50f + v885) + lx;
        float y = ly - ((m.hoverLift + m.hoverLift) - m.press * 3f);
        float w = lw, h = lh;
        float glowK = Math.min(m.glow / 0.3f, 1f);
        // drop shadow
        rect(x + 1f, y + 6f, w - 2f, h, 12f, true, new float[]{0f, 0f, 0f, v241 * 0.2f});
        // card fill: sidebar colour nudged toward accent
        float[] sb = Theme.sidebar(), ac = Theme.accent();
        float k = glowK * 0.07f;
        float[] fill = {(ac[0] - sb[0]) * k + sb[0], (ac[1] - sb[1]) * k + sb[1], (ac[2] - sb[2]) * k + sb[2], (m.hoverLift * 0.04f + sb[3]) * v241};
        rect(x, y, w, h, 10f, true, fill);
        fillRect(x + 6f, y + 0.5f, w - 12f, 1f, 0f, new float[]{1f, 1f, 1f, v241 * 0.07f});
        float[] bd = lerp(Theme.e26C0(), Theme.e2850(), Math.max(m.hoverLift, glowK));
        rect(x, y, w, h, 10f, false, withAlpha(bd, v241));
        if (m.glow > 0.01f) {
            float v259 = (3f - (glowK + glowK)) * (glowK * glowK);
            float v260 = v259 * v241;
            float v261 = (h - 24f) * v259;
            float v262 = ((h - 24f) * 0.5f + (y + 12f)) - v261 * 0.5f;
            if (v261 > 0.5f) {
                float v264 = x + 2f;
                for (int n = 3; n >= 1; n--) {
                    float e = n + n;
                    float[] a = Theme.accent();
                    rect(v264 - e, v262 - e, (e + e) + 2f, (e + e) + v261, e + 1f, true, new float[]{a[0], a[1], a[2], (0.16f / n) * v260});
                }
                float[] a = Theme.accent();
                rect(v264, v262, 2f, v261, 1f, true, new float[]{a[0], a[1], a[2], v260});
            }
        }
        float chevX, chevY;
        if (P.grid) {
            textBoxEllipsis(m.name, x + 20f, y + 14f, w - 84f, 24f, 10, withAlpha(Theme.text(), v241), false, false);
            wrapped(m.desc, x + 20f, y + 46f, w - 40f, 4, withAlpha(Theme.e38F0(), v241), 2);
            chevX = (x + w) - 28f; chevY = y + 96f;
        } else {
            float v272 = Math.min(w - 110f, 200f);
            String nm = ellipsize(m.name, 10, v272);
            textBox(nm, x + 20f, y + 18f, v272, 24f, 10, withAlpha(Theme.text(), v241), false, false);
            textBoxEllipsis(m.desc, x + 20f, y + 44f, w - 140f, 16f, 4, withAlpha(Theme.e38F0(), v241), false, false);
            chevX = (x + w) - 85f; chevY = y + 38f;
        }
        boolean chevHot = hovered && P.mx >= chevX - 15f && chevX + 15f >= P.mx && P.my >= chevY - 15f && chevY + 15f >= P.my;
        chevron(chevX, chevY, 10f, withAlpha(chevHot ? Theme.accent() : Theme.muted(), v241), -0.5f);
        float tx = (x + w) - 60f, ty = y + (P.grid ? 14f : 30f);
        boolean tHot = hovered && P.mx >= tx && tx + 44f >= P.mx && P.my >= ty && ty + 24f >= P.my;
        m.toggleT = Lib.lerp(m.toggleT < 0f ? (m.enabled ? 1f : 0f) : m.toggleT, m.enabled ? 1f : 0f, Ui.S.dt * 10f);
        toggle(tx, ty, m.enabled, tHot, m.toggleT, v241);
    }

    /** sub_18011C0E0: word-wrapped text limited to maxLines (ellipsis on the last). */
    static void wrapped(String s, float x, float y, float maxW, int font, float[] c, int maxLines) {
        if (s == null || s.isEmpty()) return;
        String[] words = s.split(" ");
        StringBuilder line = new StringBuilder();
        int lines = 0;
        float lh = FONT_SIZE[font] + 2f;
        for (int i = 0; i < words.length; i++) {
            String trial = line.length() == 0 ? words[i] : line + " " + words[i];
            if (textSize(trial, font)[0] > maxW && line.length() > 0) {
                text(line.toString(), x, y + lines * lh, font, c);
                lines++;
                line.setLength(0);
                if (lines >= maxLines) return;
                line.append(words[i]);
            } else {
                line.setLength(0); line.append(trial);
            }
        }
        if (line.length() > 0 && lines < maxLines) text(ellipsize(line.toString(), font, maxW), x, y + lines * lh, font, c);
    }

    /** sub_18011E300: chevron drawn as two lines rotated by a*PI. */
    static void chevron(float x, float y, float size, float[] c, float a) {
        float half = size * 0.5f;
        float cs = (float) Math.cos(a * 3.1415901f), sn = (float) Math.sin(a * 3.1415901f);
        float q = size * 0.25f, nq = -size * 0.25f;
        float v13 = cs * 0f - q * sn, v14 = q * cs + sn * 0f;
        float v15 = nq * sn, v16 = nq * cs;
        float v17 = cs * half - v15;
        float tipX = v13 + x, tipY = v14 + y;
        line(tipX, tipY, ((-half * cs) - v15) + x, ((-half * sn) + v16) + y, 2f, c);
        line(v17 + x, (sn * half + v16) + y, tipX, tipY, 2f, c);
    }

    /** sub_18011ED40: pill toggle switch. */
    static void toggle(float x, float y, boolean on, boolean hot, float t, float a) {
        if (a <= 0f) return;
        float v10 = t;
        float v12 = (float) (Math.pow(v10 - 1f, 3.0) * 2.70158f) + 1f;
        v12 = v12 + (float) (Math.pow(v10 - 1f, 2.0) * 1.70158f);
        float[] on1 = Theme.e2D00(), off1 = Theme.e2B70();
        float[] c = lerp(off1, on1, v10);
        if (hot) { c[0] = Math.min(c[0] * 1.15f, 1f); c[1] = Math.min(c[1] * 1.15f, 1f); c[2] = Math.min(c[2] * 1.15f, 1f); }
        c[3] = c[3] * a;
        fillRect(x, y, 44f, 24f, 12f, c);
        if (v10 > 0.1f) {
            float[] g = Theme.e2210().clone();
            g[3] = v10 * 0.3f * a;
            fillRect(x - 2f, y - 2f, 48f, 28f, 14f, g);
        }
        float[] bd = Theme.e26C0().clone();
        bd[3] = bd[3] * a;
        strokeRect(x, y, 44f, 24f, 12f, 1f, bd);
        float cy = y + 12f;
        float x0 = (x + 8f) + 4f;
        float cx = ((((x + 44f) - 8f) - 4f) - x0) * v12 + x0;
        circleFilled(cx, cy, (hot ? 1.1f : 1f) * 8f, new float[]{1f, 1f, 1f, a * 0.95f});
    }
}
