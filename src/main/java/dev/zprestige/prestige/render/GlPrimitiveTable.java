package dev.zprestige.prestige.render;

import dev.zprestige.prestige.type.GlPrimitive;
import dev.zprestige.prestige.world.WorldContract;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// $VF: synthetic class
public class GlPrimitiveTable {
    public static final boolean a;
    public static final int[] b = new int[GlPrimitive.values().length];

    static {
        try {
            b[GlPrimitive.TRIANGLES.ordinal()] = 1;
        } catch (NoSuchFieldError var5) {
        }

        try {
            b[GlPrimitive.QUADS.ordinal()] = 2;
        } catch (NoSuchFieldError var4) {
        }

        try {
            b[GlPrimitive.TRIANGLES_FAN.ordinal()] = 3;
        } catch (NoSuchFieldError var3) {
        }

        try {
            b[GlPrimitive.DEBUG_LINE_STRIP.ordinal()] = 4;
        } catch (NoSuchFieldError var2) {
        }

        try {
            b[GlPrimitive.QUAD_STRIP.ordinal()] = 5;
        } catch (NoSuchFieldError var1) {
        }

        try {
            b[GlPrimitive.LINES.ordinal()] = 0;
        } catch (NoSuchFieldError var0) {
        }

        a = !WorldContract.class.desiredAssertionStatus();
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

    private static NoSuchFieldError a(NoSuchFieldError var0) {
        return var0;
    }
}
