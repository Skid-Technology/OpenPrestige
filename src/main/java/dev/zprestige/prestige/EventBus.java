package dev.zprestige.prestige;

import dev.zprestige.prestige.event.Event;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private volatile Map listeners = new HashMap();
    private final Object lock = new Object();
    private static final MethodType c;
    private static final Set d;
    public final CopyOnWriteArrayList e = new CopyOnWriteArrayList();
    private static final String g = "[Prestige/EventBus] listener threw (logged once per type): ";

    public void register(Object[] var1) {
        Object var4 = var1[0];
        long var2 = (Long)var1[1];
        var2 = 114818700636565L ^ var2;
        long var5 = var2 ^ 56241223455158L;
        if (var4 != null) {
            if (this.e.isEmpty()) {
                Class var8 = var4.getClass();
                ArrayList var9 = new ArrayList();

                for (Method var13 : var8.getDeclaredMethods()) {
                    if (var13.isAnnotationPresent(Subscribe.class)) {
                        Class[] var14 = var13.getParameterTypes();
                        if (var14.length == 1) {
                            try {
                                var13.setAccessible(true);
                                MethodHandle var15 = MethodHandles.lookup().unreflect(var13).bindTo(var4).asType(c);
                                var9.add(new ListenerEntry(var13, var4, var14[0], var15));
                            } catch (Throwable var21) {
                            }
                        }
                    }
                }

                if (!var9.isEmpty()) {
                    synchronized (this.lock) {
                        if (!d(new Object[]{this.listeners, var4, var5})) {
                            HashMap var25 = new HashMap(this.listeners);

                            for (ListenerEntry var27 : (Iterable<ListenerEntry>)(var9)) {
                                ListenerEntry[] var28 = (ListenerEntry[])var25.get(var27.a_Class(new Object[0]));
                                ListenerEntry[] var29;
                                if (var28 == null) {
                                    var29 = new ListenerEntry[]{var27};
                                } else {
                                    var29 = new ListenerEntry[var28.length + 1];
                                    System.arraycopy(var28, 0, var29, 0, var28.length);
                                    var29[var28.length] = var27;
                                }

                                var25.put(var27.a_Class(new Object[0]), var29);
                            }

                            this.listeners = var25;
                        }
                    }
                }
            }
        }
    }

    public void unregister(Object[] var1) {
        Object var4 = var1[0];
        if (var4 != null) {
            synchronized (this.lock) {
                HashMap var7 = new HashMap(this.listeners);
                boolean var8 = false;

                for (Entry var10 : (Iterable<Entry>)(new ArrayList(var7.entrySet()))) {
                    ListenerEntry[] var11 = (ListenerEntry[])var10.getValue();
                    int var12 = 0;

                    for (ListenerEntry var16 : var11) {
                        if (var16.a_Object(new Object[0]) != var4) {
                            var12++;
                        }
                    }

                    if (var12 != var11.length) {
                        var8 = true;
                        if (var12 == 0) {
                            var7.remove(var10.getKey());
                        } else {
                            ListenerEntry[] var21 = new ListenerEntry[var12];
                            int var22 = 0;

                            for (ListenerEntry var18 : var11) {
                                if (var18.a_Object(new Object[0]) != var4) {
                                    var21[var22++] = var18;
                                }
                            }

                            var7.put((Class)var10.getKey(), var21);
                        }
                    }
                }

                if (var8) {
                    this.listeners = var7;
                }
            }
        }
    }

    public boolean post(Object[] var1) {
        Event var4 = (Event)var1[0];
        long var2 = (Long)var1[1];
        var2 = 114818700636565L ^ var2;
        long var5 = var2 ^ 52036292005796L;
        Event.b();
        ListenerEntry[] var8 = (ListenerEntry[])this.listeners.get(var4.getClass());
        if (var8 == null) {
            return var4.a_boolean___(new Object[0]);
        } else {
            for (ListenerEntry var12 : var8) {
                try {
                    var12.a_MethodHandle(new Object[0]).invokeExact((Event)var4);
                } catch (Throwable var14) {
                    c(new Object[]{var12, var14, var5});
                }
            }

            return var4.a_boolean___(new Object[0]);
        }
    }

    public boolean hasListeners(Object[] var1) {
        Class var4 = (Class)var1[0];
        Event.b();
        ListenerEntry[] var6 = (ListenerEntry[])this.listeners.get(var4);
        return var6 != null && var6.length > 0;
    }

    public boolean hasListenerOfType(Object[] var1) {
        Class var5 = (Class)var1[0];
        Class var4 = (Class)var1[1];
        Event.b();
        ListenerEntry[] var7 = (ListenerEntry[])this.listeners.get(var5);
        if (var7 == null) {
            return false;
        } else {
            for (ListenerEntry var11 : var7) {
                if (var4.isInstance(var11.a_Object(new Object[0]))) {
                    return true;
                }
            }

            return false;
        }
    }

    private static boolean d(Object[] var0) {
        Map var1 = (Map)var0[0];
        Object var4 = var0[1];
        Event.b();

        for (ListenerEntry[] var7 : (Iterable<ListenerEntry[]>)(var1.values())) {
            for (ListenerEntry var11 : var7) {
                if (var11.a_Object(new Object[0]) == var4) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void c(Object[] var0) {
        ListenerEntry var4 = (ListenerEntry)var0[0];
        Throwable var3 = (Throwable)var0[1];
        Event.b();
        String var6 = var4.a_Object(new Object[0]).getClass().getName() + "#" + var4.a(new Object[0]).getName();
        if (d.add(var6 + ":" + var3.getClass().getName())) {
            System.err.println(g + var6);
            var3.printStackTrace();
        }
    }

    static {
        c = MethodType.methodType(void.class, Event.class);
        d = ConcurrentHashMap.newKeySet();
    }

    private static Field a(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field b(Class var0, String var1, Class var2) {
        Field var3 = a(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = b(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
        label33:
        for (Method var8 : var0.getDeclaredMethods()) {
            if (var8.getName().equals(var1) && var8.getReturnType() == var2) {
                Class[] var9 = var8.getParameterTypes();
                if (var9.length == var3) {
                    for (int var10 = 0; var10 < var3; var10++) {
                        if (var9[var10] != var4[var10]) {
                            continue label33;
                        }
                    }

                    return var8;
                }
            }
        }

        return null;
    }

    private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = a(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = b(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }

    private static Object[] b() {
        return new Object[0];
    }

    private static Object[] a(Object var0, Object var1) {
        return new Object[]{var0, var1};
    }

    private static Object[] a(Object var0) {
        return new Object[]{var0};
    }

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
