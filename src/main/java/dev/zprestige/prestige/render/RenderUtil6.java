package dev.zprestige.prestige.render;

import dev.zprestige.prestige.inventory.InventoryUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class RenderUtil6 implements RenderContract2 {
    private static Object a;
    private static int b = -1;

    public static int a(Object[] var0) {
        Object var1 = var0[0];
        long var2 = (Long)var0[1];
        var2 = 111044566853839L ^ var2;
        long var4 = var2 ^ 131986150899554L;
        if (a == null) {
            a = var1;
            b = InventoryUtil.a_int_____(new Object[]{var4});
        } else if (a != var1) {
            a = var1;
        }

        return b;
    }

    public static boolean a_boolean____________________________________________(Object[] var0) {
        Object var1 = var0[0];
        return a == var1;
    }

    public static void a_void____________________________(Object[] var0) {
        Object var3 = var0[0];
        if (a == var3) {
            a = null;
            b = -1;
        }
    }

    public static int b(Object[] var0) {
        return b;
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
