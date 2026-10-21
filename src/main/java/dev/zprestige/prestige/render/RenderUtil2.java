package dev.zprestige.prestige.render;

import dev.zprestige.prestige.client.Prestige;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.util.InputUtil;

public final class RenderUtil2 implements RenderContract2 {
    public static int a = 0;
    private static boolean c = false;

    private RenderUtil2() {
    }

    public static void a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 93541403458849L ^ var1;
        long var3 = var1 ^ 81162841569345L;
        if (!c) {
            c = true;
            Prestige.eventBus.register(new Object[]{new RenderUtil3(), var3});
        }

        b.options.jumpKey.setPressed(true);
        a = 2;
    }

    public static void b(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 93541403458849L ^ var1;
        long var3 = var1 ^ 139211699224092L;
        if (a > 0) {
            a = 0;
            c(new Object[]{var3});
        }
    }

    public static void c(Object[] var0) {
        if (!InputUtil.isKeyPressed(b.getWindow(), b.options.jumpKey.getDefaultKey().getCode())) {
            b.options.jumpKey.setPressed(false);
        }
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
