package dev.zprestige.prestige.engine;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Shared state of the game-hook engine. Loaded by the Mixin config plugin (very early) and referenced from the gate that is
 * injected into every ClassTransform callback, so it must stay free of Minecraft and Prestige-client types.
 */
public final class EngineState {
    private EngineState() {}

    /** The transformers are registered and the Mixin plugin is installed. */
    public static volatile boolean installed;
    /** Callbacks only run once the client is initialised (Prestige.init finished, modules registered). */
    public static volatile boolean ready;
    /** Target classes that were transformed (dotted runtime names). */
    public static final Set<String> transformed = ConcurrentHashMap.newKeySet();
    /** Callback hit counters keyed by "MixinClass.method". */
    public static final ConcurrentHashMap<String, LongAdder> callbacks = new ConcurrentHashMap<>();
    /** Events posted on the client EventBus by event class simple name (see EventBusStatsMixin). */
    public static final ConcurrentHashMap<String, LongAdder> events = new ConcurrentHashMap<>();

    /** Called from the injected gate of every callback that passed the readiness check. */
    public static void onCallback(String name) {
        LongAdder a = callbacks.get(name);
        if (a == null) {
            a = callbacks.computeIfAbsent(name, k -> {
                System.out.println("[Engine] FIRST_CALLBACK=" + k);
                return new LongAdder();
            });
        }
        a.increment();
    }

    public static void onEvent(Class<?> type) {
        String n = type.getSimpleName();
        LongAdder a = events.get(n);
        if (a == null) a = events.computeIfAbsent(n, k -> new LongAdder());
        a.increment();
    }

    public static void markReady(String why) {
        if (!ready) {
            ready = true;
            System.out.println("[Engine] READY (" + why + ") targets transformed=" + transformed.size());
        }
    }

    public static String summary() {
        StringBuilder sb = new StringBuilder("ENGINE installed=").append(installed).append(" ready=").append(ready)
                .append(" transformed=").append(transformed.size()).append(" callbacksSeen=").append(callbacks.size())
                .append(" eventTypesSeen=").append(events.size());
        long cb = 0, ev = 0;
        for (LongAdder a : callbacks.values()) cb += a.sum();
        for (LongAdder a : events.values()) ev += a.sum();
        sb.append(" callbackTotal=").append(cb).append(" eventTotal=").append(ev);
        return sb.toString();
    }

    public static String details() {
        StringBuilder sb = new StringBuilder(summary());
        Map<String, Long> c = new TreeMap<>();
        callbacks.forEach((k, v) -> c.put(k, v.sum()));
        sb.append("\n  callbacks: ").append(c);
        Map<String, Long> e = new TreeMap<>();
        events.forEach((k, v) -> e.put(k, v.sum()));
        sb.append("\n  events: ").append(e);
        return sb.toString();
    }
}
