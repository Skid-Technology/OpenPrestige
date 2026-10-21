package dev.zprestige.prestige.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Module / setting model loaded from the live manifest dump (modules.tsv, settings.tsv). */
public final class Data {
    private Data() {}

    static final Map<String, List<String[]>> SETTINGS = new HashMap<>();

    public static void load(InputStream modules, InputStream settings) throws IOException {
        ModulesPage.P.mods.clear();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(modules, StandardCharsets.UTF_8))) {
            for (String l; (l = r.readLine()) != null; ) {
                String[] f = l.split("\t", 3);
                if (f.length >= 2) ModulesPage.P.mods.add(new ModulesPage.Mod(f[0], f[1], f.length > 2 ? f[2] : ""));
            }
        }
        ModulesPage.P.mods.sort((a, b) -> a.name.compareTo(b.name));
        if (dev.zprestige.prestige.ui.bridge.Bridge.bind()) {
            for (ModulesPage.Mod m : ModulesPage.P.mods) {
                m.live = dev.zprestige.prestige.ui.bridge.Bridge.byName(m.name);
                if (m.live != null) m.enabled = m.live.enabled();
            }
        }
        SETTINGS.clear();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(settings, StandardCharsets.UTF_8))) {
            for (String l; (l = r.readLine()) != null; ) {
                String[] f = l.split("\t", -1);
                SETTINGS.computeIfAbsent(f[0], k -> new ArrayList<>()).add(f);
            }
        }
    }

    static String[] parseList(String v) {
        v = v.trim();
        if (v.startsWith("[")) v = v.substring(1);
        if (v.endsWith("]")) v = v.substring(0, v.length() - 1);
        return v.isEmpty() ? new String[0] : v.split(",\\s*");
    }

    /** Builds the setting list shown in the detail panel (Bind first, like the original). */
    public static List<DetailPage.Setting> settingsFor(String module) {
        ModulesPage.Mod mod = null;
        for (ModulesPage.Mod m : ModulesPage.P.mods) if (m.name.equals(module)) { mod = m; break; }
        List<DetailPage.Setting> out = new ArrayList<>();
        DetailPage.Setting bind = new DetailPage.Setting();
        bind.name = "Bind"; bind.desc = "Keybind for " + module; bind.type = 0;
        if (mod != null && mod.live != null) {
            bind.live = mod.live.keybind();
            if (bind.live != null) {
                Object k = bind.live.value();
                bind.key = k instanceof Number n ? n.intValue() : 0;
                bind.toggleMode = bind.live.toggleMode();
            }
        }
        out.add(bind);
        for (String[] f : SETTINGS.getOrDefault(module, List.of())) {
            DetailPage.Setting s = new DetailPage.Setting();
            s.name = f[2]; s.desc = f[3];
            try {
                switch (f[1]) {
                    case "dM": s.type = 1; s.bval = f[4].equals("true"); break;
                    case "dN": s.type = 2; break;
                    case "dO": s.type = 3; s.fval = Float.parseFloat(f[4]); s.fmin = Float.parseFloat(f[5]); s.fmax = Float.parseFloat(f[6]); break;
                    case "dP": s.type = 4; s.ival = Integer.parseInt(f[4]); s.imin = Integer.parseInt(f[5]); s.imax = Integer.parseInt(f[6]); break;
                    case "dQ": s.type = 5; s.rmin = Float.parseFloat(f[5]); s.rmax = Float.parseFloat(f[6]); s.lo = Float.parseFloat(f[7]); s.hi = Float.parseFloat(f[8]); break;
                    case "dR": s.type = 6; s.sval = f[4]; s.opts = parseList(f[5]); break;
                    case "dS": s.type = 7; break;
                    case "dT": s.type = 8; s.sval = f[4]; break;
                    default: s.type = 0;
                }
            } catch (RuntimeException e) { s.type = 1; }
            if (mod != null && mod.live != null) attach(s, mod.live.setting(s.name));
            out.add(s);
        }
        applyVisibility(out);
        return out;
    }

    /** Copies the running client value into the UI row and remembers the handle for write-back. */
    static void attach(DetailPage.Setting s, dev.zprestige.prestige.ui.bridge.Bridge.LiveSetting ls) {
        if (ls == null) return;
        s.live = ls;
        try {
            switch (s.type) {
                case 0: { Object k = ls.value(); s.key = k instanceof Number n ? n.intValue() : s.key; s.toggleMode = ls.toggleMode(); break; }
                case 1: s.bval = Boolean.TRUE.equals(ls.value()); break;
                case 2: {
                    if (ls.value() instanceof java.awt.Color c) s.color = new float[]{c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f};
                    break;
                }
                case 3: { Object v = ls.value(); if (v instanceof Number n) s.fval = n.floatValue(); float[] b = ls.bounds(); s.fmin = b[0]; s.fmax = b[1]; break; }
                case 4: { Object v = ls.value(); if (v instanceof Number n) s.ival = n.intValue(); float[] b = ls.bounds(); s.imin = (int) b[0]; s.imax = (int) b[1]; break; }
                case 5: { s.lo = ls.rangeLow(); s.hi = ls.rangeHigh(); float[] b = ls.bounds(); s.rmin = b[0]; s.rmax = b[1]; break; }
                case 6: { Object v = ls.value(); if (v != null) s.sval = String.valueOf(v); String[] o = ls.options(); if (o.length > 0) s.opts = o; break; }
                case 7: { String[] o = ls.options(); if (o.length > 0) s.opts = o; s.live = ls; break; }
                case 8: { Object v = ls.value(); if (v != null) s.sval = String.valueOf(v); break; }
                default: break;
            }
        } catch (Throwable ignored) {}
    }

    /** Writes the UI row value back into the running client setting. */
    public static void push(DetailPage.Setting s) {
        if (s == null || s.live == null) return;
        try {
            switch (s.type) {
                case 0: s.live.set(s.key); s.live.setToggleMode(s.toggleMode); break;
                case 1: s.live.set(s.bval); break;
                case 2: s.live.set(new java.awt.Color(clamp(s.color[0]), clamp(s.color[1]), clamp(s.color[2]), clamp(s.color[3]))); break;
                case 3: s.live.set(s.fval); break;
                case 4: s.live.set(s.ival); break;
                case 5: s.live.setRangeLow(s.lo); s.live.setRangeHigh(s.hi); break;
                case 6: s.live.set(s.sval); break;
                case 7: {
                    boolean[] f = Input.multiFlags(s);
                    String[] o = s.opts;
                    for (int i = 0; i < o.length && i < f.length; i++) s.live.setFlag(o[i], f[i]);
                    break;
                }
                case 8: s.live.set(s.sval); break;
                default: break;
            }
        } catch (Throwable ignored) {}
    }

    private static int clamp(float v) { return v <= 0f ? 0 : v >= 1f ? 255 : Math.round(v * 255f); }

    /** Seeds the multi-select flags of every row from the running client. */
    public static void seedMulti(List<DetailPage.Setting> list) {
        for (DetailPage.Setting s : list) {
            if (s.type != 7 || s.live == null) continue;
            boolean[] live = s.live.flags();
            boolean[] f = Input.multiFlags(s);
            for (int i = 0; i < f.length && i < live.length; i++) f[i] = live[i];
        }
    }

    /** "X Swap Back" only shows when "X Swap" is not None (pattern seen in recorded frames). */
    public static void applyVisibility(List<DetailPage.Setting> list) {
        for (DetailPage.Setting s : list) {
            if (s.name.endsWith(" Swap Back")) {
                String base = s.name.substring(0, s.name.length() - " Back".length());
                s.visible = false;
                for (DetailPage.Setting o : list) if (o.name.equals(base)) s.visible = !"None".equals(o.sval);
            }
        }
    }
}
