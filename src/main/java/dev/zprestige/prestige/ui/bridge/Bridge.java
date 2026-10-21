package dev.zprestige.prestige.ui.bridge;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reflection bridge to the running Prestige client ({@code dev.zprestige.prestige.client.Prestige}).
 * Nothing here touches Minecraft classes, so it can be exercised offline against the client jar.
 * When the client is not present every call degrades to a no-op and {@link #available()} is false,
 * leaving the UI on its manifest (tsv) fallback.
 */
public final class Bridge {
    private Bridge() {}

    private static boolean tried, bound;
    private static Object manager;
    private static final List<LiveModule> MODULES = new ArrayList<>();

    public static boolean available() { return bound; }
    public static List<LiveModule> modules() { return MODULES; }

    public static synchronized boolean bind() {
        if (tried) return bound;
        tried = true;
        try {
            Class<?> prestige = load("dev.zprestige.prestige.client.Prestige");
            if (prestige == null) return false;
            Field f = field(prestige, "moduleManager");
            if (f == null) return false;
            Object mgr = f.get(null);
            if (mgr == null) return false;
            List<?> list = moduleList(mgr);
            if (list == null) { System.out.println("[prestige-ui] bridge: manager has no module-list accessor"); return false; }
            if (list.isEmpty()) list = buildModules(prestige, mgr);
            for (Object o : list) {
                LiveModule m = LiveModule.of(o);
                if (m != null) MODULES.add(m);
            }
            manager = mgr;
            bound = !MODULES.isEmpty();
            if (bound) dev.zprestige.prestige.engine.EngineState.markReady("bridge bound " + MODULES.size() + " modules");
            String dump = System.getProperty("prestige.dump");   // dev: force class init of instrumented classes (DumpHook)
            if (dump != null) DumpHook.init(dump, prestige.getClassLoader());
            System.out.println("[prestige-ui] bridge " + (bound ? "bound: " + MODULES.size() + " modules" : "found no modules"));
            return bound;
        } catch (Throwable t) {
            System.out.println("[prestige-ui] bridge bind failed: " + t);
            return false;
        }
    }

    /**
     * The native module normally registers the module objects; without it the manager list is empty. Construct every module class
     * listed in the live manifest (settings are injected by {@link Bootstrap#pre} from the hooked constructors) and register them.
     */
    private static List<?> buildModules(Class<?> prestige, Object mgr) {
        ClassLoader cl = prestige.getClassLoader();
        List<Object> built = new ArrayList<>();
        int fail = 0;
        for (Bootstrap.Mod mod : Bootstrap.mods().values()) {
            try {
                Class<?> c = Bootstrap.loadModuleClass(mod.clazz, cl);
                java.lang.reflect.Constructor<?> k = c.getDeclaredConstructor();
                k.setAccessible(true);
                Object m = k.newInstance();
                Bootstrap.post(m);
                built.add(m);
            } catch (Throwable t) {
                fail++;
                Throwable r = t instanceof java.lang.reflect.InvocationTargetException && t.getCause() != null ? t.getCause() : t;
                System.out.println("[prestige-ui] bridge: module " + mod.clazz + " (" + mod.name + ") failed: " + r);
                if (fail <= 3) r.printStackTrace(System.out);
            }
        }
        System.out.println("[prestige-ui] bridge: built " + built.size() + " modules from manifest, failed " + fail);
        if (!built.isEmpty()) {
            for (Method m : mgr.getClass().getDeclaredMethods())
                if (m.getName().equals("setModules") && m.getParameterCount() == 1 && m.getParameterTypes()[0] == List.class) {
                    try { m.setAccessible(true); m.invoke(mgr, built); break; } catch (Throwable ignored) {}
                }
        }
        return built;
    }

    public static LiveModule byName(String name) {
        for (LiveModule m : MODULES) if (m.name.equalsIgnoreCase(name)) return m;
        return null;
    }

    // ------------------------------------------------------------------ reflection helpers
    static Class<?> load(String name) {
        ClassLoader[] cs = {Thread.currentThread().getContextClassLoader(), Bridge.class.getClassLoader(), ClassLoader.getSystemClassLoader()};
        for (ClassLoader cl : cs) {
            if (cl == null) continue;
            try { return Class.forName(name, false, cl); } catch (Throwable ignored) {}
        }
        try { return Class.forName(name); } catch (Throwable ignored) { return null; }
    }

    static Field field(Class<?> c, String name) {
        for (Class<?> k = c; k != null && k != Object.class; k = k.getSuperclass())
            for (Field f : k.getDeclaredFields())
                if (f.getName().equals(name)) { f.setAccessible(true); return f; }
        return null;
    }

    static Field fieldOfType(Class<?> c, String type, String preferred) {
        Field fallback = null;
        for (Class<?> k = c; k != null && k != Object.class; k = k.getSuperclass())
            for (Field f : k.getDeclaredFields()) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                if (!f.getType().getName().equals(type)) continue;
                if (f.getName().equals(preferred)) { f.setAccessible(true); return f; }
                if (fallback == null) { f.setAccessible(true); fallback = f; }
            }
        return fallback;
    }

    /** Method by name + parameter count + return type (null return type = any). */
    static Method method(Class<?> c, String name, int args, Class<?> ret) {
        for (Method m : c.getMethods()) {
            if (!m.getName().equals(name) || m.getParameterCount() != args) continue;
            if (ret != null && m.getReturnType() != ret) continue;
            m.setAccessible(true);
            return m;
        }
        return null;
    }

    static Object call(Object o, Method m, Object... args) {
        if (o == null || m == null) return null;
        try { return m.invoke(o, args); } catch (Throwable ignored) { return null; }
    }

    static List<?> moduleList(Object mgr) {
        Object v = call(mgr, method(mgr.getClass(), "getModules", 0, List.class));
        return v instanceof List<?> l ? l : null;
    }

    static boolean isFalse(Boolean b) { return b == null || !b; }

    // ------------------------------------------------------------------ live module
    public static final class LiveModule {
        public final Object handle;
        public final String name, desc, category;
        private final Object enabledSetting;   // BoolSetting enabledSetting
        private final Object keybindSetting;   // KeybindSetting keybind
        private final Method enabledGet;       // Module.isEnabled()Z
        private final List<LiveSetting> settings = new ArrayList<>();

        private LiveModule(Object o, Object enabled, Object keybind, Method get) {
            handle = o; enabledSetting = enabled; keybindSetting = keybind; enabledGet = get;
            name = str(call(o, method(o.getClass(), "getName", 0, String.class)));
            desc = str(call(o, method(o.getClass(), "getDescription", 0, String.class)));
            Object cat = call(o, method(o.getClass(), "getCategory", 0, load("dev.zprestige.prestige.Category")));
            category = cat == null ? "Misc" : String.valueOf(cat);
            for (Object s : list(o)) {
                LiveSetting ls = LiveSetting.of(s);
                if (ls != null) settings.add(ls);
            }
        }

        static LiveModule of(Object o) {
            if (o == null) return null;
            try {
                Object enabled = get(fieldOfType(o.getClass(), "dev.zprestige.prestige.BoolSetting", "enabledSetting"), o);
                Object keybind = get(fieldOfType(o.getClass(), "dev.zprestige.prestige.KeybindSetting", "keybind"), o);
                Method get = method(o.getClass(), "isEnabled", 0, boolean.class);
                return new LiveModule(o, enabled, keybind, get);
            } catch (Throwable t) { return null; }
        }

        private static List<?> list(Object o) {
            // SettingHolder.settings (ArrayList of the module's own settings; Enabled/Bind are the module's core settings)
            Object v = get(field(o.getClass(), "settings"), o);
            return v instanceof List<?> l ? l : Collections.emptyList();
        }

        static Object get(Field f, Object o) {
            if (f == null) return null;
            try { return f.get(o); } catch (Throwable ignored) { return null; }
        }

        public boolean enabled() {
            Object v = call(handle, enabledGet);
            if (v instanceof Boolean b) return b;
            return enabledSetting != null && Boolean.TRUE.equals(LiveSetting.of(enabledSetting).value());
        }

        /**
         * Enable/disable like the client does: Module.toggle flips the setting, registers/unregisters the module's @Subscribe
         * listeners on the EventBus and runs onEnable/onDisable (queued until a world exists). Writing only the Enabled setting
         * (what this used to do) left the module without listeners, i.e. inert.
         */
        public void setEnabled(boolean value) {
            if (value == enabled()) return;
            Method toggle = method(handle.getClass(), "toggle", 1, void.class);
            if (toggle != null) {
                try { toggle.setAccessible(true); toggle.invoke(handle, (Object) new Object[]{0L}); return; }
                catch (Throwable t) { System.out.println("[prestige-ui] toggle failed for " + name + ": " + (t.getCause() != null ? t.getCause() : t)); }
            }
            if (enabledSetting != null) LiveSetting.of(enabledSetting).set(value);
        }

        public LiveSetting enabledSettingForTest() { return enabledSetting == null ? null : LiveSetting.of(enabledSetting); }

        /** Module keybind row (KeybindSetting field "keybind"), or null. */
        public LiveSetting keybind() { return keybindSetting == null ? null : LiveSetting.of(keybindSetting); }

        public List<LiveSetting> settings() { return settings; }

        public LiveSetting setting(String name) {
            for (LiveSetting s : settings) if (s.name.equalsIgnoreCase(name)) return s;
            return null;
        }
    }

    // ------------------------------------------------------------------ live setting
    public static final class LiveSetting {
        public final Object handle;
        public final String type;   // simple class name: BoolSetting/ColorSetting/FloatSetting/IntSetting/RangeSetting/EnumSetting/MultiSetting/KeybindSetting/TextSetting
        public final String name, desc;

        private LiveSetting(Object o) {
            handle = o;
            type = o.getClass().getSimpleName();
            name = str(call(o, method(o.getClass(), "getName", 0, String.class)));
            String d = str(call(o, method(o.getClass(), "getDescription", 0, String.class)));
            desc = d;
        }

        static LiveSetting of(Object o) { return o == null ? null : new LiveSetting(o); }

        public Object value() { return call(handle, method(handle.getClass(), "getValue", 0, Object.class)); }

        public void set(Object v) {
            for (Method m : handle.getClass().getMethods())
                if (m.getName().equals("setValue") && m.getParameterCount() == 1 && m.getReturnType() == void.class
                        && m.getParameterTypes()[0].isAssignableFrom(v.getClass())) {
                    try { m.setAccessible(true); m.invoke(handle, v); } catch (Throwable ignored) {}
                    return;
                }
        }

        public float[] bounds() {
            Method a = method(handle.getClass(), "getMin", 0, float.class);
            Method b = method(handle.getClass(), "getMax", 0, float.class);
            if (a != null && b != null) return new float[]{(Float) call(handle, a), (Float) call(handle, b)};
            Method ai = method(handle.getClass(), "getMin", 0, int.class);
            Method bi = method(handle.getClass(), "getMax", 0, int.class);
            if (ai != null && bi != null) return new float[]{(Integer) call(handle, ai), (Integer) call(handle, bi)};
            return new float[]{0f, 0f};
        }

        public String[] options() {
            Object v = call(handle, method(handle.getClass(), "getOptions", 0, String[].class));
            return v instanceof String[] a ? a : new String[0];
        }

        public boolean[] flags() {
            Object v = call(handle, method(handle.getClass(), "getSelected", 0, boolean[].class));
            return v instanceof boolean[] a ? a : new boolean[0];
        }

        public void setFlag(String option, boolean on) {
            Method m = method(handle.getClass(), "setSelected", 2, void.class);
            if (m != null) call(handle, m, option, on);
        }

        public void setRangeLow(float v) { call(handle, method(handle.getClass(), "setLow", 1, void.class), v); }
        public void setRangeHigh(float v) { call(handle, method(handle.getClass(), "setHigh", 1, void.class), v); }
        public float rangeLow() { Object v = call(handle, method(handle.getClass(), "getLow", 0, float.class)); return v instanceof Float f ? f : 0f; }
        public float rangeHigh() { Object v = call(handle, method(handle.getClass(), "getHigh", 0, float.class)); return v instanceof Float f ? f : 0f; }

        public boolean toggleMode() { Boolean b = (Boolean) call(handle, method(handle.getClass(), "isToggleMode", 0, boolean.class)); return b != null && b; }
        public void setToggleMode(boolean v) { call(handle, method(handle.getClass(), "setToggleMode", 1, void.class), v); }
    }

    private static String str(Object o) { return o == null ? "" : String.valueOf(o); }
}
