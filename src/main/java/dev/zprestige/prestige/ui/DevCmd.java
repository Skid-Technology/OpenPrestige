package dev.zprestige.prestige.ui;

import dev.zprestige.prestige.ui.bridge.Bridge;
import java.util.List;

/** Dev/test helpers for the Overlay command file (only reachable with -Dprestige.ui.cmd=...). */
public final class DevCmd {
    private DevCmd() {}

    /** Switches to the module's category page and returns the canvas centre of its toggle switch, or null. */
    public static float[] toggleCenter(String name) {
        for (ModulesPage.Mod m : ModulesPage.P.mods) {
            if (!m.name.equals(name)) continue;
            int cat = Input.catOf(m);
            if (cat < 0) return null;
            Ui.S.page = cat; ModulesPage.P.cat = cat;
            List<int[]> e = Input.entries();
            Input.Layout L = Input.layout(e);
            int idx = Input.modIdx(m);
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i)[0] != cat || e.get(i)[1] != idx) continue;
                float[] r = L.r[i];
                return new float[]{(r[0] + r[2]) - 60f + 22f, r[1] + (ModulesPage.P.grid ? 14f : 30f) + 12f};
            }
        }
        return null;
    }

    /** One-line state: UI model vs the real client module (read back through reflection). */
    public static String state(String name) {
        if (name.equals("hudsmoke")) return hudSmoke();
        if (name.equals("cfgtest")) return LocalConfigs.selfTest();
        if (name.equals("engine")) return dev.zprestige.prestige.engine.EngineState.details();
        if (name.equals("listeners")) return listeners();
        for (ModulesPage.Mod m : ModulesPage.P.mods) {
            if (!m.name.equals(name)) continue;
            Bridge.LiveModule l = m.live;
            if (l == null) return name + " ui=" + m.enabled + " live=<none>";
            Bridge.LiveSetting es = l.enabledSettingForTest();
            return name + " ui=" + m.enabled + " live.a()=" + l.enabled() + " enabledSetting.value=" + (es == null ? null : es.value());
        }
        return name + " <unknown module>";
    }

    /** Event types that currently have listeners on the client EventBus (module lifecycle check): type -> listener owner classes. */
    public static String listeners() {
        try {
            Object bus = Class.forName("dev.zprestige.prestige.client.Prestige").getField("eventBus").get(null);
            java.lang.reflect.Field f = bus.getClass().getDeclaredField("listeners");
            f.setAccessible(true);
            java.util.Map<?, ?> map = (java.util.Map<?, ?>) f.get(bus);
            int total = 0;
            StringBuilder sb = new StringBuilder();
            java.util.TreeMap<String, String> sorted = new java.util.TreeMap<>();
            for (java.util.Map.Entry<?, ?> e : map.entrySet()) {
                Object[] arr = (Object[]) e.getValue();
                total += arr.length;
                java.util.TreeSet<String> owners = new java.util.TreeSet<>();
                for (Object le : arr) {
                    java.lang.reflect.Method m = le.getClass().getDeclaredMethod("a_Object", Object[].class);
                    m.setAccessible(true);
                    owners.add(m.invoke(le, (Object) new Object[0]).getClass().getSimpleName());
                }
                sorted.put(((Class<?>) e.getKey()).getSimpleName(), owners.toString());
            }
            sb.append("LISTENERS types=").append(map.size()).append(" entries=").append(total);
            sorted.forEach((k, v) -> sb.append(System.lineSeparator()).append("  ").append(k).append(" <- ").append(v));
            return sb.toString();
        } catch (Throwable t) {
            Throwable r = t; while (r.getCause() != null) r = r.getCause();
            return "LISTENERS failed " + r;
        }
    }

    /** Dev smoke test (command "state hudsmoke"): runs the HUD q() render hooks and the two recovered Runnables of the client through reflection. */
    private static String hudSmoke() {
        StringBuilder sb = new StringBuilder("HUDSMOKE");
        try {
            Class<?> pc = Class.forName("dev.zprestige.prestige.client.Prestige");
            Object editor = pc.getField("l").get(null);
            java.lang.reflect.Field fa = editor.getClass().getDeclaredField("a");
            fa.setAccessible(true);
            List<?> els = (List<?>) fa.get(editor);
            sb.append(" elements=").append(els.size());
            org.joml.Matrix4f mat = new org.joml.Matrix4f();
            for (Object el : els) {
                String n = el.getClass().getSimpleName();
                try {
                    Class<?> hc = Class.forName("dev.zprestige.prestige.hud.HudElement");
                    for (String f : new String[]{"e", "f", "g", "h"}) { java.lang.reflect.Field ff = hc.getDeclaredField(f); ff.setAccessible(true); ff.setFloat(el, f.equals("g") ? 80f : f.equals("h") ? 24f : 20f); }
                    java.lang.reflect.Method q = hc.getDeclaredMethod("q", Object[].class);
                    q.setAccessible(true);
                    q.invoke(el, (Object) new Object[]{mat, 0L});
                    sb.append(" [").append(n).append(".q ok]");
                } catch (Throwable t) {
                    Throwable r = t; while (r.getCause() != null) r = r.getCause();
                    sb.append(" [").append(n).append(".q FAIL ").append(r).append("]");
                }
            }
            // RenderHelper24 (dE.run): with the player present it selects a hotbar slot, otherwise returns
            Object rot = pc.getField("o").get(null);
            Class<?> rc = Class.forName("dev.zprestige.prestige.render.RenderHelper24");
            Object r24 = rc.getConstructors()[0].newInstance(rot, 0);
            ((Runnable) r24).run();
            sb.append(" [RenderHelper24.run ok]");
            // CobwebPlacerHelper (av.run) on the live CobwebPlacer module
            Bridge.LiveModule cw = Bridge.byName("Cobweb Placer");
            if (cw != null) {
                Object r = Class.forName("dev.zprestige.prestige.world.CobwebPlacerHelper").getConstructors()[0].newInstance(cw.handle);
                ((Runnable) r).run();
                sb.append(" [CobwebPlacerHelper.run ok]");
            } else sb.append(" [CobwebPlacerHelper: module not found]");
        } catch (Throwable t) {
            Throwable r = t; while (r.getCause() != null) r = r.getCause();
            sb.append(" ABORT ").append(r);
        }
        return sb.toString();
    }
}
