package dev.zprestige.prestige.event;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.type.EventPhase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Event {
    private final EventPhase a;
    private boolean b = false;
    private static String[] c;

    public Event(EventPhase var1) {
        this.a = var1;
    }

    public Event() {
        this(EventPhase.NONE);
    }

    public boolean a_boolean___(Object[] var1) {
        return this.b;
    }

    public EventPhase a_EventPhase(Object[] var1) {
        return this.a;
    }

    public void a_void_____(Object[] var1) {
        this.b = true;
    }

    public boolean b_boolean__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 40579119770306L ^ var2;
        long var4 = var2 ^ 79390605381451L;
        return Prestige.eventBus == null ? false : Prestige.eventBus.post(new Object[]{this, var4});
    }

    public static void b(String[] var0) {
        c = var0;
    }

    public static String[] b() {
        return c;
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

    private static MatchException b(MatchException var0) {
        return var0;
    }
}
