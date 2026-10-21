package dev.zprestige.prestige.render;

import java.lang.ref.Cleaner.Cleanable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GpuBuffer implements AutoCloseable {
    private static final Logger a = LoggerFactory.getLogger(GpuBuffer.class);
    private final int b;
    private final AtomicLong c;
    private final Cleanable d;
    private int e = 35044;

    public GpuBuffer(int var1, AtomicLong var2, long var3) {
        this.b = var1;
        this.c = var2;
        this.d = RenderUtil.a.register(this, () -> RenderUtil.f(new Object[]{(Runnable)() -> GL15.glDeleteBuffers(var1), 31795785076293L}));
    }

    public static GpuBuffer a() {
        return new GpuBuffer(GL15.glGenBuffers(), new AtomicLong(0L), 126766348694803L);
    }

    public void a(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        int var7 = GL11.glGetInteger(34964);
        GL15.glBindBuffer(34962, this.b);
        GL15.glBufferData(34962, var3, var2);
        GL15.glBindBuffer(34962, var7);
        this.e = var2;
        this.c.set(var3);
    }

    public ByteBuffer a_ByteBuffer(Object[] var1) {
        long var3 = (Long)var1[0];
        long var5 = (Long)var1[1];
        int var2 = (Integer)var1[2];
        int var9 = GL11.glGetInteger(34964);
        GL15.glBindBuffer(34962, this.b);
        ByteBuffer var10 = GL30.glMapBufferRange(34962, var3, var5, var2);
        GL15.glBindBuffer(34962, var9);
        return var10;
    }

    public void d(Object[] var1) {
        int var4 = GL11.glGetInteger(34964);
        GL15.glBindBuffer(34962, this.b);
        GL15.glUnmapBuffer(34962);
        GL15.glBindBuffer(34962, var4);
    }

    @Override
    public void close() {
        this.d.clean();
    }

    public int a_int_____________________(Object[] var1) {
        return this.b;
    }

    public int b(Object[] var1) {
        return this.e;
    }

    public AtomicLong a_AtomicLong(Object[] var1) {
        return this.c;
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
