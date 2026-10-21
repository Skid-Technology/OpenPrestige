package dev.zprestige.prestige.render;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntSupplier;

public class DrawContext {
    public IntSupplier a = () -> 0;
    public OptionalInt b = OptionalInt.empty();
    public OptionalDouble c = OptionalDouble.empty();
    public Shader d;

    public DrawContext(long var1) {
    }

    public DrawContext a(Object[] var1) {
        int var2 = (Integer)var1[0];
        this.a = () -> var2;
        return this;
    }

    public DrawContext b(Object[] var1) {
        IntSupplier var2 = (IntSupplier)var1[0];
        this.a = var2;
        return this;
    }

    public DrawContext c(Object[] var1) {
        OptionalInt var2 = (OptionalInt)var1[0];
        this.b = var2;
        return this;
    }

    public DrawContext d(Object[] var1) {
        OptionalDouble var2 = (OptionalDouble)var1[0];
        this.c = var2;
        return this;
    }

    public DrawContext e(Object[] var1) {
        Shader var2 = (Shader)var1[0];
        this.d = var2;
        return this;
    }

    public RenderPass a_RenderPass(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 12708709774606L ^ var2;
        long var4 = var2 ^ 123057129550079L;
        return new RenderPass(this, var4);
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
