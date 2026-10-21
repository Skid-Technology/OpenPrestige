package dev.zprestige.prestige.render;

import java.lang.ref.Cleaner.Cleanable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Framebuffer implements AutoCloseable {
    public final int a;
    private final Cleanable b;
    private static final String d = "Framebuffer isn't complete, is: ";

    public Framebuffer(int var1, long var2) {
        this.a = var1;
        this.b = RenderUtil.a.register(this, () -> RenderUtil.f(new Object[]{(Runnable)() -> GL30.glDeleteFramebuffers(var1), 31795785076293L}));
    }

    public static Framebuffer a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 87225610055498L ^ var1;
        long var3 = var1 ^ 82851842664766L;
        return new Framebuffer(GL30.glGenFramebuffers(), var3);
    }

    public void a_void__________________(Object[] var1) {
        int var3 = (Integer)var1[0];
        int var2 = (Integer)var1[1];
        int var6 = GL11.glGetInteger(36006);
        GL30.glBindFramebuffer(36160, this.a);
        GL30.glFramebufferTexture2D(36160, var3, 3553, var2, 0);
        GL30.glBindFramebuffer(36160, var6);
    }

    public void b(Object[] var1) {
        int var3 = (Integer)var1[0];
        FontRenderer var2 = (FontRenderer)var1[1];
        long var4 = (Long)var1[2];
        var4 = 87225610055498L ^ var4;
        long var6 = var4 ^ 137836224116081L;
        int var10002 = var2.a;
        Object[] var10005 = new Object[]{null, null, var6};
        var10005[1] = var10002;
        var10005[0] = var3;
        this.a_void__________________(var10005);
    }

    public void e(Object[] var1) {
        float var3 = (Float)var1[0];
        float var7 = (Float)var1[1];
        float var4 = (Float)var1[2];
        float var2 = (Float)var1[3];
        int var8 = GL11.glGetInteger(36006);
        GL30.glBindFramebuffer(36009, this.a);
        GL30.glClearBufferfv(6144, 0, new float[]{var3, var7, var4, var2});
        GL30.glBindFramebuffer(36009, var8);
    }

    @Override
    public void close() throws Exception {
        this.b.clean();
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

    private static IllegalStateException a(IllegalStateException var0) {
        return var0;
    }
}
