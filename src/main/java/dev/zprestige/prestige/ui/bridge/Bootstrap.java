package dev.zprestige.prestige.ui.bridge;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Color;
import java.io.InputStream;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Runs at the start of every Prestige module constructor. Recreates the settings
 * and metadata that the native module normally injects, so constructors that read
 * those fields before assigning them work in the native-free build.
 */
public final class Bootstrap {
    public static final class Setting {
        public String field, setClass, name, desc, value;
        public Map<String, String> props = Collections.emptyMap();
    }
    public static final class Mod {
        @com.google.gson.annotations.SerializedName("class")
        public String clazz;
        public String name, desc, category;
        public boolean enabled;
        public List<Setting> settings = Collections.emptyList();
    }
    public static final class Manifest { public List<Mod> modules = Collections.emptyList(); }

    static Map<String, Mod> mods;
    static final Map<Class<?>, Map<String, Field>> FIELD_CACHE = new HashMap<>();

    public static synchronized Map<String, Mod> mods() {
        if (mods != null) return mods;
        mods = new LinkedHashMap<>();
        try (InputStream in = Bootstrap.class.getResourceAsStream("/prestige_ui/manifest_live.json")) {
            if (in == null) return mods;
            Manifest m = new Gson().fromJson(new String(in.readAllBytes(), StandardCharsets.UTF_8), Manifest.class);
            if (m != null && m.modules != null)
                for (Mod x : m.modules) mods.put(x.clazz, x);
        } catch (Throwable ignored) {}
        return mods;
    }

    /** Manifest entry for a module class. The source route renames case-colliding classes with a trailing '_' (ea -> ea_). */
    static Mod modFor(Class<?> c) {
        String n = c.getName();
        Mod m = mods().get(n);
        while (m == null && n.endsWith("_")) { n = n.substring(0, n.length() - 1); m = mods().get(n); }
        return m;
    }

    /** Loads the module class named in the manifest, also accepting the '_'-suffixed twin the source route uses on case-insensitive file systems. */
    static Class<?> loadModuleClass(String name, ClassLoader cl) throws ClassNotFoundException {
        Throwable first = null;
        String n = name;
        for (int i = 0; i < 3; i++, n += "_") {
            try {
                Class<?> c = Class.forName(n, true, cl);
                if (c.getName().equals(n)) return c;
            } catch (LinkageError | ClassNotFoundException e) { if (first == null) first = e; }
        }
        if (first instanceof ClassNotFoundException cnf) throw cnf;
        throw new ClassNotFoundException(name, first);
    }

    public static void pre(Object module) {
        try {
            Mod mod = modFor(module.getClass());
            if (mod == null) return;
            ClassLoader cl = module.getClass().getClassLoader();
            for (Setting s : mod.settings) {
                try {
                    Object setting = make(cl, s);
                    if (setting != null) set(module, s.field, setting);
                } catch (Throwable ignored) {}
            }
            setMeta(module, mod);
        } catch (Throwable ignored) {}
    }

    /** Post-construction pass: metadata + settings list completeness. */
    public static void post(Object module) {
        try {
            Mod mod = modFor(module.getClass());
            if (mod == null) return;
            setMeta(module, mod);
            Field listField = null;
            Class<?> c = module.getClass();
            while (c != null) {
                if (c.getName().equals("dev.zprestige.prestige.SettingHolder"))
                    for (Field f : c.getDeclaredFields())
                        if (java.util.List.class.isAssignableFrom(f.getType())) { listField = f; break; }
                c = c.getSuperclass();
            }
            if (listField == null) return;
            listField.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) listField.get(module);
            if (list == null) return;
            for (Setting s : mod.settings) {
                Object v = get(module, s.field);
                if (v != null && !list.contains(v)) list.add(v);
            }
        } catch (Throwable ignored) {}
    }

    static Object get(Object obj, String field) {
        Class<?> c = obj.getClass();
        while (c != null) {
            for (Field f : c.getDeclaredFields())
                if (f.getName().equals(field)) { try { f.setAccessible(true); return f.get(obj); } catch (Throwable ignored) { return null; } }
            c = c.getSuperclass();
        }
        return null;
    }

    static Object make(ClassLoader cl, Setting s) throws Exception {
        if (s.setClass == null) return null;
        Class<?> sc = Class.forName(s.setClass, true, cl);
        String sn = sc.getSimpleName();
        Object obj;
        switch (sn) {
            case "BoolSetting":
                obj = ctor(sc, String.class, boolean.class).newInstance(s.name, Boolean.parseBoolean(s.value));
                break;
            case "FloatSetting":
                obj = ctor(sc, String.class, float.class, float.class, float.class)
                        .newInstance(s.name, f(s.value), f(s.props.get("min")), f(s.props.get("max")));
                break;
            case "IntSetting":
                obj = ctor(sc, String.class, int.class, int.class, int.class)
                        .newInstance(s.name, i(s.value), i(s.props.get("min")), i(s.props.get("max")));
                break;
            case "RangeSetting":
                obj = ctor(sc, String.class, float.class, float.class, float.class, float.class)
                        .newInstance(s.name, f(s.value), f(s.props.get("high")), f(s.props.get("min")), f(s.props.get("max")));
                break;
            case "EnumSetting":
                obj = ctor(sc, String.class, String.class, String[].class)
                        .newInstance(s.name, s.value, arr(s.props.get("options")));
                break;
            case "MultiSetting":
                String[] opts = arr(s.props.get("options"));
                boolean[] sel = boolArr(s.props.get("selected"), opts.length);
                obj = ctor(sc, String.class, String[].class, boolean[].class).newInstance(s.name, opts, sel);
                break;
            case "TextSetting":
                obj = ctor(sc, String.class, String.class, String.class)
                        .newInstance(s.name, s.value, s.props.getOrDefault("placeholder", ""));
                break;
            case "ColorSetting":
                obj = ctor(sc, String.class, Color.class).newInstance(s.name, color(s.value));
                break;
            case "KeybindSetting":
                obj = ctor(sc, String.class, int.class).newInstance(s.name, i(s.value));
                break;
            default:
                return null;
        }
        if (s.desc != null && !s.desc.isEmpty()) {
            try { sc.getMethod("setDescription", String.class).invoke(obj, s.desc); } catch (Throwable ignored) {}
        }
        return obj;
    }

    static void setMeta(Object m, Mod mod) {
        Class<?> c = m.getClass();
        while (c != null) {
            for (Field f : c.getDeclaredFields()) {
                try {
                    if (Modifier.isStatic(f.getModifiers())) continue;
                    if (f.getType() == String.class && f.getName().equals("name")) { f.setAccessible(true); f.set(m, mod.name); }
                    else if (f.getType() == String.class && f.getName().equals("description")) { f.setAccessible(true); f.set(m, mod.desc); }
                    else if (f.getType().getSimpleName().equals("Category") && f.getName().equals("category")) {
                        Object cat = category(m.getClass().getClassLoader(), mod.category);
                        if (cat != null) { f.setAccessible(true); f.set(m, cat); }
                    }
                } catch (Throwable ignored) {}
            }
            c = c.getSuperclass();
        }
    }

    static Object category(ClassLoader cl, String name) {
        try {
            Class<?> e = Class.forName("dev.zprestige.prestige.Category", true, cl);
            for (Object v : e.getEnumConstants())
                if (String.valueOf(v).equalsIgnoreCase(name)) return v;
        } catch (Throwable ignored) {}
        return null;
    }

    static void set(Object obj, String field, Object value) throws Exception {
        Class<?> c = obj.getClass();
        while (c != null) {
            for (Field f : c.getDeclaredFields()) {
                if (f.getName().equals(field) && f.getType().isAssignableFrom(value.getClass())) {
                    f.setAccessible(true);
                    f.set(obj, value);
                    return;
                }
            }
            c = c.getSuperclass();
        }
    }

    static Constructor<?> ctor(Class<?> c, Class<?>... p) throws Exception {
        Constructor<?> k = c.getDeclaredConstructor(p);
        k.setAccessible(true);
        return k;
    }

    static float f(String s) { try { return Float.parseFloat(s); } catch (Exception e) { return 0f; } }
    static int i(String s) { try { return Integer.parseInt(s); } catch (Exception e) { return 0; } }

    static String[] arr(String s) {
        if (s == null || s.isEmpty() || s.equals("[]")) return new String[0];
        String t = s;
        if (t.startsWith("[")) t = t.substring(1);
        if (t.endsWith("]")) t = t.substring(0, t.length() - 1);
        if (t.isEmpty()) return new String[0];
        String[] out = t.split(",");
        for (int k = 0; k < out.length; k++) out[k] = out[k].trim();
        return out;
    }

    static boolean[] boolArr(String s, int n) {
        boolean[] out = new boolean[Math.max(n, 0)];
        String[] p = arr(s);
        for (int k = 0; k < out.length && k < p.length; k++) out[k] = Boolean.parseBoolean(p[k]);
        return out;
    }

    static Color color(String s) {
        try {
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("r=(\\d+),g=(\\d+),b=(\\d+)(?:,a=(\\d+))?").matcher(s);
            if (m.find()) {
                int a = m.group(4) == null ? 255 : Integer.parseInt(m.group(4));
                return new Color(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)), a);
            }
        } catch (Throwable ignored) {}
        return Color.WHITE;
    }
}
