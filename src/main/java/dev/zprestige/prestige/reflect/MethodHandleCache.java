package dev.zprestige.prestige.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.render.Camera;

public class MethodHandleCache {
    private static final MethodAccessor a = MethodAccessor.e(
        new Object[]{Camera.class, "setPos", void.class, new Class[]{double.class, double.class, double.class}, 0L}
    );
    private static final MethodAccessor b;
    private final Camera c;

    public MethodHandleCache(Camera var1) {
        this.c = var1;
    }

    public void a(Object[] var1) {
        double var2 = (Double)var1[0];
        double var4 = (Double)var1[1];
        double var6 = (Double)var1[2];
        long var8 = (Long)var1[3];
        var8 = 31533240469600L ^ var8;
        long var10 = var8 ^ 50275459632695L;

        try {
            a.b(new Object[]{this.c, new Object[]{var2, var4, var6}, var10});
        } catch (Throwable var13) {
            throw new RuntimeException(var13);
        }
    }

    public void b(Object[] var1) {
        float var2 = (Float)var1[0];
        float var5 = (Float)var1[1];
        long var3 = (Long)var1[2];
        var3 = 31533240469600L ^ var3;
        long var6 = var3 ^ 55335610542964L;

        try {
            b.b(new Object[]{this.c, new Object[]{var2, var5}, var6});
        } catch (Throwable var9) {
            throw new RuntimeException(var9);
        }
    }

    static {
        Object var0 = new String[]{"setPos", "setRotation"};
        b = MethodAccessor.e(new Object[]{Camera.class, ((Object[])var0)[1], void.class, new Class[]{float.class, float.class}, 0L});
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
