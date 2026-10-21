package dev.zprestige.prestige.ui.bridge;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Jar route only (client/work/client-runtime.jar): the ZKM reference tables of the client classes that Strip/IndyResolve could not fold still contain the
 * OBFUSCATED class names (dev.zprestige.prestige.fT ...), looked up with Class.forName at run time.  tools/RecoverBodies.java redirects every
 * Class.forName call of the client jar to this class, which maps the old name to the renamed one (docs/mapping.tsv + docs/classmap.tsv, resource
 * prestige_ui/old_names.tsv written by client/tools/rename.sh).  Unknown names pass through unchanged.
 */
public final class OldNames {
    private static final Map<String, String> MAP = new HashMap<>();

    static {
        try (InputStream in = OldNames.class.getResourceAsStream("/prestige_ui/old_names.tsv")) {
            if (in != null) {
                BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                for (String l; (l = r.readLine()) != null; ) {
                    int t = l.indexOf('\t');
                    if (t > 0) MAP.put(l.substring(0, t), l.substring(t + 1));
                }
            }
        } catch (Exception e) {
            System.out.println("[prestige-ui] OldNames: " + e);
        }
    }

    private OldNames() {}

    public static String map(String name) {
        if (name == null) return null;
        String n = MAP.get(name);
        if (n != null) return n;
        if (name.startsWith("[")) {                       // array descriptor form accepted by Class.forName: [Ldev.zprestige.prestige.X;
            int l = name.indexOf('L');
            if (l > 0 && name.endsWith(";")) {
                String e = MAP.get(name.substring(l + 1, name.length() - 1));
                if (e != null) return name.substring(0, l + 1) + e + ";";
            }
        }
        return name;
    }

    public static Class<?> forName(String name) throws ClassNotFoundException {
        return Class.forName(map(name));
    }

    public static Class<?> forName(String name, boolean init, ClassLoader cl) throws ClassNotFoundException {
        return Class.forName(map(name), init, cl);
    }
}
