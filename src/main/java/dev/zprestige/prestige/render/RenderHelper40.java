package dev.zprestige.prestige.render;

import dev.zprestige.prestige.reflect.MethodAccessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.render.ProjectionMatrix2;
import org.joml.Matrix4f;

public class RenderHelper40 {
    private static final MethodAccessor a;
    private final ProjectionMatrix2 b;

    public RenderHelper40(ProjectionMatrix2 var1) {
        this.b = var1;
    }

    public Matrix4f a(Object[] var1) {
        float var2 = (Float)var1[0];
        float var3 = (Float)var1[1];
        long var4 = (Long)var1[2];
        var4 = 20438541473024L ^ var4;
        long var6 = var4 ^ 40941517329865L;

        try {
            return (Matrix4f)a.b(new Object[]{this.b, new Object[]{var2, var3}, var6});
        } catch (Throwable var9) {
            throw new RuntimeException(var9);
        }
    }

    static {
        Object var0 = "getMatrix";
        a = MethodAccessor.e(new Object[]{ProjectionMatrix2.class, var0, Matrix4f.class, new Class[]{float.class, float.class}, 0L});
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
