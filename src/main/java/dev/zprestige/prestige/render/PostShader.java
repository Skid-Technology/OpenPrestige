package dev.zprestige.prestige.render;

import it.unimi.dsi.fastutil.Pair;
import dev.zprestige.prestige.data.IntData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.type.GlPrimitive;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Consumer;
import org.lwjgl.opengl.GL11;

public abstract class PostShader extends ShaderBase {
    public VertexBuffer c;
    public int d;
    public int e;
    public boolean f;
    public Framebuffer g;
    public FontRenderer h;
    private static final int i = 128;
    public final Map j;
    private Consumer k;
    private boolean l;

    public PostShader(String var1, String var2, long var3) {
        super(var1, var2, false, (115399614053456L));
        var3 = 0L;
        var3 = 131965980497184L ^ var3;
        long var5 = var3 ^ 37624295549282L;
        long var7 = var3 ^ 18627950766448L;
        this.d = -1;
        this.e = -1;
        this.f = false;
        this.g = Framebuffer.a(new Object[]{var5});
        this.j = new java.util.HashMap();   // reconstructed: the ZKM ref was lost
        this.h = FontRenderer.a(new Object[]{3553, 0L});   // reconstructed: colour attachment texture (GL_TEXTURE_2D), sized on first use in g()
    }

    public void a_void_______________(Object[] var1) {
        RenderHelper8 var3 = (RenderHelper8)var1[0];
        long var4 = (Long)var1[2];
        var4 = 131965980497184L ^ var4;
        long var6 = var4 ^ 98043818924559L;
        long var8 = var4 ^ 130329971676169L;
        long var10 = var4 ^ 10211965472900L;
        this.g(new Object[]{var8});
        this.f(new Object[]{var10});
        var3.a_Stream(new Object[]{var6})
            .filter(lpL126_0 -> ((DrawMode)((Pair)lpL126_0).key()).b(new Object[0]))
            .forEach(lpL125_0 -> ((TextRenderer)((it.unimi.dsi.fastutil.Pair)lpL125_0).value()).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}));
    }

    public void b_void______(Object[] var1) {
        long var4 = (Long)var1[2];
        var4 = 131965980497184L ^ var4;
        long var6 = var4 ^ 124987963754920L;
        long var8 = var4 ^ 83279209821324L;
        if (this.f && this.c != null) {
            if (this.l) {
                this.l = false;
                boolean var11 = GL11.glGetBoolean(2884);
                boolean var12 = GL11.glGetBoolean(3042);
                boolean var13 = GL11.glGetBoolean(2929);
                GL11.glDisable(2884);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glDisable(2929);
                this.c.a_void______________________________(new Object[]{var6});
                if (var13) {
                    GL11.glEnable(2929);
                }

                if (var11) {
                    GL11.glEnable(2884);
                }

                if (!var12) {
                    GL11.glDisable(3042);
                }

                Framebuffer var10000 = this.g;
                Object[] var10007 = new Object[]{null, null, null, null, var8};
                var10007[3] = 0.0F;
                var10007[2] = 0.0F;
                var10007[1] = 0.0F;
                var10007[0] = 0.0F;
                var10000.e(var10007);
            }
        }
    }

    public final TextRenderer a_TextRenderer(Object[] var1) {
        RenderHelper8 var5 = (RenderHelper8)var1[0];
        int var2 = (Integer)var1[1];
        long var3 = (Long)var1[2];
        var3 = 131965980497184L ^ var3;
        long var6 = var3 ^ 118756937571420L;
        this.l = true;
        Object[] var10005 = new Object[]{null, null, var6};
        var10005[1] = var2;
        var10005[0] = var5;
        return this.b(var10005);
    }

    public abstract TextRenderer b(Object[] var1);

    public abstract VertexBuffer a(Object[] var1);

    public abstract void c(Object[] var1);

    public final void d(Object[] var1) {
        Consumer var2 = (Consumer)var1[0];
        this.k = var2;
    }

    public final void e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 131965980497184L ^ var2;
        long var4 = var2 ^ 48655475447922L;
        Event.b();
        IntData var7 = IntData.a(this.a_Shader(new Object[0]).a(new Object[0]));
        if (this.k != null) {
            this.k.accept(var7);
        } else {
            this.c(new Object[]{var7, var4});
        }
    }

    public void f(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 131965980497184L ^ var2;
        long var4 = var2 ^ 13766546752517L;
        long var6 = var2 ^ 96329862459142L;
        long var8 = var2 ^ 134913156825019L;
        long var10 = var2 ^ 67982258152471L;
        long var12 = var2 ^ 55828201241406L;
        if (a.getWindow() != null) {
            int var15 = a.getWindow().getFramebufferWidth();
            int var16 = a.getWindow().getFramebufferHeight();
            if (var15 > 0 && var16 > 0) {
                if (this.d != var15 || this.e != var16) {
                    this.d = var15;
                    this.e = var16;
                    FontRenderer var10001 = this.h;
                    int var10002 = this.d;
                    int var10003 = this.e;
                    Object[] var10006 = new Object[]{null, null, var8};
                    var10006[1] = var10003;
                    var10006[0] = var10002;
                    this.h = var10001.c_FontRenderer(var10006);
                    FontRenderer var10000 = this.h;
                    Object[] var10004 = new Object[]{null, var6};
                    var10004[0] = 9729;
                    var10000.b_void_______(var10004);
                    var10000 = this.h;
                    var10004 = new Object[]{null, var12};
                    var10004[0] = 33071;
                    var10000.e(var10004);
                    Framebuffer var19 = this.g;
                    Object[] var10005 = new Object[]{null, this.h, var4};
                    var10005[0] = 36064;
                    var19.b(var10005);
                    Framebuffer var20 = this.g;
                    Object[] var10007 = new Object[]{null, null, null, null, var10};
                    var10007[3] = 0.0F;
                    var10007[2] = 0.0F;
                    var10007[1] = 0.0F;
                    var10007[0] = 0.0F;
                    var20.e(var10007);
                }
            }
        }
    }

    public void g(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 131965980497184L ^ var2;
        long var4 = var2 ^ 126736450207880L;
        long var6 = var2 ^ 75783473736279L;
        long var8 = var2 ^ 44383856683915L;
        long var10 = var2 ^ 86612443836555L;
        long var12 = var2 ^ 72658991472794L;
        long var14 = var2 ^ 84696270847923L;
        if (!this.f) {
            this.d = a.getWindow().getFramebufferWidth();
            this.e = a.getWindow().getFramebufferHeight();
            FontRenderer var10000 = this.h;
            int var10002 = this.d;
            int var10003 = this.e;
            Object[] var10006 = new Object[]{null, null, null, var6};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = 33323;
            var10000.c(var10006);
            var10000 = this.h;
            Object[] var10004 = new Object[]{null, var8};
            var10004[0] = 9729;
            var10000.b_void_______(var10004);
            var10000 = this.h;
            var10004 = new Object[]{null, var14};
            var10004[0] = 33071;
            var10000.e(var10004);
            Framebuffer var19 = this.g;
            Object[] var10005 = new Object[]{null, this.h, var4};
            var10005[0] = 36064;
            var19.b(var10005);
            Framebuffer var20 = this.g;
            Object[] var10007 = new Object[]{null, null, null, null, var12};
            var10007[3] = 0.0F;
            var10007[2] = 0.0F;
            var10007[1] = 0.0F;
            var10007[0] = 0.0F;
            var20.e(var10007);
            this.c = this.a(new Object[]{var10});
            this.f = true;
        }
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
