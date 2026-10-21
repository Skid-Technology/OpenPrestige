package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.type.Easing;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class RenderHelper5 {
    private final Supplier a;
    private final boolean b;
    private final Supplier c;
    private long d;
    private boolean e;
    private static final long g = 0L;

    public RenderHelper5(Supplier var1, boolean var2, Supplier var3, long var4) {
        this.d = g;
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.e = var2;
    }

    public RenderHelper5(float var1, boolean var2, Easing var3, long var4) {
        this(() -> var1, var2, () -> var3, (33527026285589L));
        var4 = 0L;
        var4 = 40258606618875L ^ var4;
        long var6 = var4 ^ 64748537226478L;
    }

    public RenderHelper5(Supplier var1, boolean var2, Easing var3, long var4) {
        this(var1, var2, () -> var3, (139086976318679L));
        var4 = 0L;
        var4 = 40258606618875L ^ var4;
        long var6 = var4 ^ 99930368812076L;
    }

    public RenderHelper5(float var1, boolean var2, Supplier var3, long var4) {
        this(() -> var1, var2, var3, (107597476211729L));
        var4 = 0L;
        var4 = 40258606618875L ^ var4;
        long var6 = var4 ^ 76169410737386L;
    }

    public boolean a(Object[] var1) {
        return this.e;
    }

    public void a_void_______________________(Object[] var1) {
        boolean var4 = (Boolean)var1[0];
        long var2 = (Long)var1[1];
        var2 = 40258606618875L ^ var2;
        long var5 = var2 ^ 36133467499546L;
        if (!var4) {
            this.d = System.currentTimeMillis() - (long)((1.0 - this.a_double__________(new Object[]{var5})) * ((Float)this.a.get()).floatValue());
        } else {
            this.d = System.currentTimeMillis() - (long)(this.a_double__________(new Object[]{var5}) * ((Float)this.a.get()).floatValue());
        }

        this.e = var4;
    }

    public float a_float___________________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 40258606618875L ^ var2;
        long var4 = var2 ^ 45632900503376L;
        if (this.e) {
            Easing var7 = (Easing)this.c.get();
            double var8 = Math.min(1.0, Math.max(0.0, (double)(System.currentTimeMillis() - this.d) / ((Float)this.a.get()).floatValue()));
            Object[] var9 = new Object[]{null, var4};
            var9[0] = var8;
            return (float)var7.a(var9);
        } else {
            Easing var10000 = (Easing)this.c.get();
            double var10001 = Math.min(1.0, Math.max(0.0, 1.0 - (double)(System.currentTimeMillis() - this.d) / ((Float)this.a.get()).floatValue()));
            Object[] var10004 = new Object[]{null, var4};
            var10004[0] = var10001;
            return (float)var10000.a(var10004);
        }
    }

    public void b(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 40258606618875L ^ var2;
        long var4 = var2 ^ 91143086842938L;
        Event.b();
        this.e = this.b;
        if (this.b) {
            this.d = System.currentTimeMillis() - (long)((1.0 - this.a_double__________(new Object[]{var4})) * ((Float)this.a.get()).floatValue());
        } else {
            this.d = System.currentTimeMillis() - (long)(this.a_double__________(new Object[]{var4}) * ((Float)this.a.get()).floatValue());
        }
    }

    private double a_double__________(Object[] var1) {
        return !this.e
            ? Math.min(1.0, Math.max(0.0, 1.0 - (double)(System.currentTimeMillis() - this.d) / ((Float)this.a.get()).floatValue()))
            : Math.min(1.0, Math.max(0.0, (double)(System.currentTimeMillis() - this.d) / ((Float)this.a.get()).floatValue()));
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
