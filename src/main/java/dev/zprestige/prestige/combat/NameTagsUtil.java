package dev.zprestige.prestige.combat;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class NameTagsUtil {
    public static final float a = 0.65F;
    public static final float b = 0.75F;
    public static final float c = 0.9F;
    public static final float d = 1.0F;
    public static final float e = 1.0F;
    public static final float f = 1.0F;
    public static final float g = 4.0F;
    public static final float h = 3.0F;
    public static final float i = 1.5F;
    public static final int j = 5;
    public static final float k = 5.5F;
    public static final float l = 3.0F;
    public static final float m = 3.0F;
    public static final float n = 1.0F;
    public static final float o = 0.5F;
    public static final float p = 4.0F;
    public static final float q = 1.0F;
    public static final Color r = new Color(14, 14, 14, 200);
    public static final Color s = Color.WHITE;
    public static final Color t = new Color(200, 200, 200, 220);
    public static final Color u = new Color(160, 160, 168, 200);
    public static final Color v = new Color(255, 255, 255, 40);

    private NameTagsUtil() {
    }

    public static float a(Object[] var0) {
        float var1 = (Float)var0[0];
        float var2 = (Float)var0[1];
        return var1 * var2 + 6.0F;
    }

    public static float b(Object[] var0) {
        float var1 = (Float)var0[0];
        return 3.0F + var1 * 1.0F + 1.0F + 4.0F + 3.0F;
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
