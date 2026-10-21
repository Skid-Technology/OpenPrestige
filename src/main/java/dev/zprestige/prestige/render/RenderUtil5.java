package dev.zprestige.prestige.render;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

public class RenderUtil5 {
    public static final Random a = new Random();

    public static float a(Object[] var0) {
        float var4 = (Float)var0[0];
        float var3 = (Float)var0[1];
        long var1 = (Long)var0[2];
        var1 = 16164445651919L ^ var1;
        long var5 = var1 ^ 60111729527913L;
        float var10000 = var4 + a.nextFloat() * (var3 - var4);
        Object[] var10005 = new Object[]{null, null, null, var5};
        var10005[2] = var3;
        var10005[1] = var4;
        var10005[0] = var10000;
        return b(var10005);
    }

    public static int a_int__________________________(Object[] var0) {
        int var1 = (Integer)var0[0];
        int var4 = (Integer)var0[1];
        return var1 + a.nextInt(var4 - var1 + 1);
    }

    private static float b(Object[] var0) {
        float var5 = (Float)var0[0];
        float var1 = (Float)var0[1];
        float var4 = (Float)var0[2];
        return Math.max(var1, Math.min(var4, var5));
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
}
