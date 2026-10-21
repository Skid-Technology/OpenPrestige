package dev.zprestige.prestige.math;

import dev.zprestige.prestige.util.LruCache;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import org.joml.Vector4f;

public final class MathUtil4 {
    private static final String b = "This is a utility class and cannot be instantiated";

    private MathUtil4(long var1) {
        throw new UnsupportedOperationException(b);
    }

    public static Map a(Object[] var0) {
        int var3 = (Integer)var0[0];
        return new LruCache(16, 0.75F, true, var3);
    }

    public static float a_float____________(Object[] var0) {
        float var3 = (Float)var0[0];
        return Math.round(var3 * 4.0F) * 0.25F;
    }

    public static Vector4f a_Vector4f(Object[] var0) {
        Vector4f var1 = (Vector4f)var0[0];
        long var2 = (Long)var0[1];
        var2 = 3124556471920L ^ var2;
        long var4 = var2 ^ 20773240911943L;
        float var10002 = var1.x;
        Object[] var10005 = new Object[]{null, var4};
        var10005[0] = var10002;
        var10002 = a_float____________(var10005);
        float var10003 = var1.y;
        Object[] var10006 = new Object[]{null, var4};
        var10006[0] = var10003;
        var10003 = a_float____________(var10006);
        float var10004 = var1.z;
        Object[] var10007 = new Object[]{null, var4};
        var10007[0] = var10004;
        var10004 = a_float____________(var10007);
        float var10 = var1.w;
        Object[] var10008 = new Object[]{null, var4};
        var10008[0] = var10;
        return new Vector4f(var10002, var10003, var10004, a_float____________(var10008));
    }

    public static int a_int______(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 >>> 24;
    }

    public static int b(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 >> 16 & 0xFF;
    }

    public static int c(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 >> 8 & 0xFF;
    }

    public static int d(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 & 0xFF;
    }

    public static float b_float_______(Object[] var0) {
        int var3 = (Integer)var0[0];
        long var1 = (Long)var0[1];
        var1 = 3124556471920L ^ var1;
        long var4 = var1 ^ 6652825324971L;
        Object[] var10003 = new Object[]{null, var4};
        var10003[0] = var3;
        return f(new Object[]{a_int______(var10003)});
    }

    public static float c_float_(Object[] var0) {
        int var3 = (Integer)var0[0];
        long var1 = (Long)var0[1];
        var1 = 3124556471920L ^ var1;
        long var4 = var1 ^ 59046493756333L;
        Object[] var10003 = new Object[]{null, var4};
        var10003[0] = var3;
        return f(new Object[]{b(var10003)});
    }

    public static float d_float(Object[] var0) {
        int var3 = (Integer)var0[0];
        long var1 = (Long)var0[1];
        var1 = 3124556471920L ^ var1;
        long var4 = var1 ^ 29288782198406L;
        Object[] var10003 = new Object[]{null, var4};
        var10003[0] = var3;
        return f(new Object[]{c(var10003)});
    }

    public static float e(Object[] var0) {
        int var1 = (Integer)var0[0];
        long var2 = (Long)var0[1];
        var2 = 3124556471920L ^ var2;
        long var4 = var2 ^ 42599639253721L;
        Object[] var10003 = new Object[]{null, var4};
        var10003[0] = var1;
        return f(new Object[]{d(var10003)});
    }

    private static float f(Object[] var0) {
        int var1 = (Integer)var0[0];
        return var1 / 255.0F;
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
