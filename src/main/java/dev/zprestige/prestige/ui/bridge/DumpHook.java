package dev.zprestige.prestige.ui.bridge;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Dev tool (client/tools/java/ClinitDump.java): instrumented client classes call {@link #log} for every array load in their
 * static initialiser, which prints the decoded constant tables the stripped decompile lost ("[dump] class idx type value").
 * Classes are forced to initialise with -Dprestige.dump=a,b,c (obfuscated simple names) after the bridge bound.
 */
public final class DumpHook {
    private DumpHook() {}
    private static final Set<String> SEEN = Collections.synchronizedSet(new HashSet<>());
    private static final IdentityHashMap<Object, Integer> IDS = new IdentityHashMap<>();

    public static void log(Object arr, int idx, String cls) {
        try {
            int id;
            synchronized (IDS) { id = IDS.computeIfAbsent(arr, k -> IDS.size()); }
            String key = cls + "#" + id + "#" + idx;
            if (!SEEN.add(key)) return;
            Object v = Array.get(arr, idx);
            String t = arr.getClass().getComponentType().getSimpleName();
            System.out.println("[dump] " + cls + "\tarr" + id + "\t" + Array.getLength(arr) + "\t" + idx + "\t" + t + "\t" + (v == null ? "null" : v.getClass().getSimpleName() + ":" + String.valueOf(v).replace("\n", "\n")));
        } catch (Throwable t) { System.out.println("[dump] error " + t); }
    }

    /** Forces initialisation of the listed classes (dev.zprestige.prestige.<name>). */
    public static void init(String list, ClassLoader cl) {
        for (String n : list.split(",")) {
            n = n.trim();
            if (n.isEmpty()) continue;
            try { Class.forName("dev.zprestige.prestige." + n, true, cl); System.out.println("[dump] init ok " + n); }
            catch (Throwable t) { System.out.println("[dump] init " + n + " failed: " + t + " / " + t.getCause()); }
        }
    }
}
