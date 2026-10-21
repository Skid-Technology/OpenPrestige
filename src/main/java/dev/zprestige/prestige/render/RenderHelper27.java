package dev.zprestige.prestige.render;

import dev.zprestige.prestige.RangeSetting;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RenderHelper27 {
    private long a = System.currentTimeMillis();

    public RenderHelper27(long var1) {
    }

    public boolean a(Object[] var1) {
        float var4 = (Float)var1[0];
        return (float)(System.currentTimeMillis() - this.a) > var4;
    }

    public float a_float__________________(Object[] var1) {
        return (float)(System.currentTimeMillis() - this.a);
    }

    public boolean b(Object[] var1) {
        RangeSetting var2 = (RangeSetting)var1[0];
        long var3 = (Long)var1[1];
        var3 = 72608926783452L ^ var3;
        long var5 = var3 ^ 19613694202557L;
        float var10001 = var2.getRolled(new Object[0]);
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var10001;
        return this.a(var10004);
    }

    public void a_void_____________________(Object[] var1) {
        this.a = System.currentTimeMillis();
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
