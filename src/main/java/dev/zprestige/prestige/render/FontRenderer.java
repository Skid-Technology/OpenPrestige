package dev.zprestige.prestige.render;

import java.lang.ref.Cleaner.Cleanable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import net.minecraft.client.texture.GlTexture;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

public class FontRenderer implements AutoCloseable {
    public final int a;
    private final Cleanable b;
    private final int c;
    private volatile boolean d = false;
    private int e;

    public FontRenderer(int var1, int var2, long var3) {
        this(var1, var2, true, (74681995002117L));
        var3 = 0L;
        var3 = 21455033727438L ^ var3;
        long var5 = var3 ^ 88438248405195L;
    }

    private FontRenderer(int var1, int var2, boolean var3, long var4) {
        this.c = var1;
        this.a = var2;
        this.b = var3 ? RenderUtil.a.register(this, () -> RenderUtil.f(new Object[]{(Runnable)() -> GL11.glDeleteTextures(var2), 31795785076293L})) : null;
    }

    public static FontRenderer a(Object[] var0) {
        int var1 = (Integer)var0[0];
        long var2 = (Long)var0[1];
        var2 = 21455033727438L ^ var2;
        long var4 = var2 ^ 5035895914153L;
        return new FontRenderer(var1, GL11.glGenTextures(), var4);
    }

    public static FontRenderer b(Object[] var0) {
        int var1 = (Integer)var0[0];
        int var2 = (Integer)var0[1];
        long var3 = (Long)var0[2];
        var3 = 21455033727438L ^ var3;
        long var5 = var3 ^ 47619771965430L;
        return new FontRenderer(var1, var2, false, var5);
    }

    private int a_int____________________(Object[] var1) {
        int var4 = (Integer)var1[0];
        switch (var4) {
            case 3553:
                return 32873;
            case 32879:
                return 32874;
            default:
                return 32873;
        }
    }

    private int b_int(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 21455033727438L ^ var2;
        long var4 = var2 ^ 119734031557110L;
        int var10001 = this.c;
        Object[] var10004 = new Object[]{null, var4};
        var10004[0] = var10001;
        int var6 = GL11.glGetInteger(this.a_int____________________(var10004));
        GL11.glBindTexture(this.c, this.a);
        return var6;
    }

    private void a_void_________________(Object[] var1) {
        int var4 = (Integer)var1[0];
        GL11.glBindTexture(this.c, var4);
    }

    public void b_void_______(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        var2 = 21455033727438L ^ var2;
        long var5 = var2 ^ 89322139281255L;
        long var7 = var2 ^ 104455106328518L;
        int var9 = this.b_int(new Object[]{var7});
        GL11.glTexParameteri(this.c, 10241, var4);
        GL11.glTexParameteri(this.c, 10240, var4);
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var9;
        this.a_void_________________(var10004);
    }

    public void c(Object[] var1) {
        int var5 = (Integer)var1[0];
        int var4 = (Integer)var1[1];
        int var6 = (Integer)var1[2];
        long var2 = (Long)var1[3];
        var2 = 21455033727438L ^ var2;
        long var7 = var2 ^ 67678843381435L;
        long var9 = var2 ^ 56835344905754L;
        if (this.d) {
            throw new IllegalStateException("already inited - this is an immutable texture!");
        } else {
            this.d = true;
            this.e = var5;

            char var12 = switch (var5) {
                case 32849 -> 'ᤇ';
                default -> 'ᤈ';
                case 33321 -> 'ᤃ';
                case 33323 -> '舧';
            };
            int var13 = this.b_int(new Object[]{var9});
            GL11.glTexImage2D(this.c, 0, var5, var4, var6, 0, var12, 5121, (ByteBuffer)null);
            GL11.glTexParameteri(this.c, 33084, 0);
            GL11.glTexParameteri(this.c, 33085, 0);
            Object[] var10004 = new Object[]{null, var7};
            var10004[0] = var13;
            this.a_void_________________(var10004);
        }
    }

    public void d(Object[] var1) {
        int var9 = (Integer)var1[0];
        int var2 = (Integer)var1[1];
        int var10 = (Integer)var1[2];
        int var6 = (Integer)var1[3];
        int var5 = (Integer)var1[4];
        int var3 = (Integer)var1[5];
        ByteBuffer var4 = (ByteBuffer)var1[6];
        long var7 = (Long)var1[7];
        var7 = 21455033727438L ^ var7;
        long var11 = var7 ^ 19326230909074L;
        long var13 = var7 ^ 34836660142131L;
        int var15 = this.b_int(new Object[]{var13});
        GL11.glTexSubImage2D(this.c, 0, var9, var2, var10, var6, var5, var3, var4);
        Object[] var10004 = new Object[]{null, var11};
        var10004[0] = var15;
        this.a_void_________________(var10004);
    }

    public void e(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        var3 = 21455033727438L ^ var3;
        long var5 = var3 ^ 57616470389599L;
        long var7 = var3 ^ 64159674803198L;
        int var9 = this.b_int(new Object[]{var7});
        GL11.glTexParameteri(this.c, 10242, var2);
        GL11.glTexParameteri(this.c, 10243, var2);
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var9;
        this.a_void_________________(var10004);
    }

    public FontRenderer c_FontRenderer(Object[] var1) {
        int var2 = (Integer)var1[0];
        int var5 = (Integer)var1[1];
        long var3 = (Long)var1[2];
        var3 = 21455033727438L ^ var3;
        long var6 = var3 ^ 90286006020271L;
        long var8 = var3 ^ 59335264655979L;
        if (!this.d) {
            throw new IllegalStateException("not inited");
        } else {
            this.close();
            int var10000 = this.c;
            Object[] var10003 = new Object[]{null, var8};
            var10003[0] = var10000;
            FontRenderer var11 = a(var10003);
            int var10001 = this.e;
            Object[] var10006 = new Object[]{null, null, null, var6};
            var10006[2] = var5;
            var10006[1] = var2;
            var10006[0] = var10001;
            var11.c(var10006);
            return var11;
        }
    }

    @Override
    public void close() {
        if (this.b != null) {
            this.b.clean();
        }
    }

    public static FontRenderer d_FontRenderer(Object[] var0) {
        Identifier var3 = (Identifier)var0[0];
        long var1 = (Long)var0[1];
        var1 = 21455033727438L ^ var1;
        long var4 = var1 ^ 124713232668769L;
        int var6 = ((GlTexture)RenderContract2.b.getTextureManager().getTexture(var3).getGlTexture()).getGlId();
        Object[] var10004 = new Object[]{null, null, var4};
        var10004[1] = var6;
        var10004[0] = 3553;
        return b(var10004);
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
