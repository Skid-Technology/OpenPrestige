package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.math.MathUtil4;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

public class RenderPass {
    private final IntSupplier a;
    private final OptionalInt b;
    private final OptionalDouble c;
    public final Shader d;
    private final int e;
    private final int f;

    public RenderPass(DrawContext var1, long var2) {
        this.a = Objects.requireNonNull(var1.a, "targetFramebuffer cannot be null");
        this.b = Objects.requireNonNull(var1.b, "clearColor cannot be null");
        this.c = Objects.requireNonNull(var1.c, "clearDepth cannot be null");
        this.d = Objects.requireNonNull(var1.d, "shaderProgram cannot be null");
        this.e = var1.d.b(new Object[0]);
        this.f = var1.d.c(new Object[0]);
    }

    public static DrawContext a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 112476627212957L ^ var1;
        long var3 = var1 ^ 115491843720741L;
        return new DrawContext(var3);
    }

    public void a_void__________________________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 112476627212957L ^ var2;
        long var4 = var2 ^ 2820097244064L;
        GL30.glBindFramebuffer(36160, this.a.getAsInt());
        this.e(new Object[]{var4});
        GL30.glUseProgram(this.d.a(new Object[0]));
    }

    public void b(Object[] var1) {
        MatrixCameraData var4 = (MatrixCameraData)var1[0];
        Event.b();
        this.d.a(new Object[0]);

        try (MemoryStack var7 = MemoryStack.stackPush()) {
            FloatBuffer var8 = var7.mallocFloat(16);
            FloatBuffer var9 = var7.mallocFloat(16);
            if (this.e >= 0) {
                var4.bA().get(var8);
                var8.rewind();
                GL30.glUniformMatrix4fv(this.e, false, var8);
            }

            if (this.f >= 0) {
                var4.bB().get(var9);
                var9.rewind();
                GL30.glUniformMatrix4fv(this.f, false, var9);
            }
        }
    }

    public void d(Object[] var1) {
        RenderHelper31 var3 = (RenderHelper31)var1[0];
        GpuBuffer var7 = (GpuBuffer)var1[1];
        int var4 = (Integer)var1[2];
        int var2 = (Integer)var1[3];
        GL30.glBindVertexArray(var3.a_int________________________(new Object[0]));
        GL30.glBindBuffer(34963, var7.a_int_____________________(new Object[0]));
        GL30.glDrawElements(var4, var2, 5125, 0L);
        GL30.glBindVertexArray(0);
        GL30.glBindBuffer(34963, 0);
    }

    private void e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 112476627212957L ^ var2;
        long var4 = var2 ^ 107379758519815L;
        long var6 = var2 ^ 116191617908441L;
        long var8 = var2 ^ 67522234459532L;
        long var10 = var2 ^ 41457372901639L;
        Event.b();
        short var13 = 0;
        if (this.b.isPresent()) {
            int var14 = this.b.getAsInt();
            Object[] var10003 = new Object[]{null, var6};
            var10003[0] = var14;
            float var10000 = MathUtil4.c_float_(var10003);
            Object[] var10004 = new Object[]{null, var10};
            var10004[0] = var14;
            float var10001 = MathUtil4.d_float(var10004);
            Object[] var10005 = new Object[]{null, var8};
            var10005[0] = var14;
            float var10002 = MathUtil4.e(var10005);
            Object[] var10006 = new Object[]{null, var4};
            var10006[0] = var14;
            GL30.glClearColor(var10000, var10001, var10002, MathUtil4.b_float_______(var10006));
            var13 = 16384;
        }

        if (this.c.isPresent()) {
            GL30.glClearDepth(this.c.getAsDouble());
            var13 |= 256;
        }

        if (var13 != 0) {
            GL30.glDisable(3089);
            GL30.glDepthMask(true);
            GL30.glColorMask(true, true, true, true);
            GL30.glClear(var13);
        }
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
