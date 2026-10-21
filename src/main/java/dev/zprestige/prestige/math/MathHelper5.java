package dev.zprestige.prestige.math;

import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.Framebuffer;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.MinecraftClient;
import org.joml.Vector4f;

public class MathHelper5 {
    public Framebuffer a;
    public FontRenderer b;
    private int c;
    private int d;
    private static final MinecraftClient e = MinecraftClient.getInstance();
    private Vector4f f;
    private Vector4f g;
    private float h;
    private float i;
    private Color j;
    private boolean k;
    private long l;

    private void c(Object[] var1) {
        long var2 = (Long)var1[0];
        this.l = var2;
    }

    public void d(Object[] var1) {
        Vector4f var2 = (Vector4f)var1[0];
        this.f = var2;
    }

    public Vector4f a(Object[] var1) {
        return this.f;
    }

    public void e(Object[] var1) {
        float var2 = (Float)var1[0];
        this.h = var2;
    }

    public float a_float___________(Object[] var1) {
        return this.h;
    }

    public void f(Object[] var1) {
        float var2 = (Float)var1[0];
        this.i = var2;
    }

    public float b(Object[] var1) {
        return this.i;
    }

    public void g(Object[] var1) {
        Vector4f var2 = (Vector4f)var1[0];
        this.g = var2;
    }

    public Vector4f b_Vector4f(Object[] var1) {
        return this.g;
    }

    public void h(Object[] var1) {
        Color var2 = (Color)var1[0];
        this.j = var2;
    }

    public Color a_Color_(Object[] var1) {
        return this.j;
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
