package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderHelper7 {
    public Framebuffer a;
    public Framebuffer b;
    public FontRenderer c;
    public FontRenderer d;
    private int e;
    private int f;
    private static final MinecraftClient g = MinecraftClient.getInstance();
    private static final int h;
    private static final int i;
    private static final int j;
    private final float k;
    private final float l;
    private boolean m;
    private final VertexBuffer n;
    private long o;
    private static final long t = Long.MIN_VALUE;

    public int a(Object[] var1) {
        return this.e;
    }

    public int b(Object[] var1) {
        return this.f;
    }

    public RenderHelper7(float var1, float var2, long var3) {
        super();
        var3 = 0L;
        var3 = 117519987309236L ^ var3;
        long var5 = var3 ^ 64673413241473L;
        long var7 = var3 ^ 87901495498316L;
        long var9 = var3 ^ 17519793599063L;
        long var11 = var3 ^ 38108902190762L;
        this.a = Framebuffer.a(new Object[]{var5});
        this.b = Framebuffer.a(new Object[]{var5});
        Object[] var10004 = new Object[]{null, var11};
        var10004[0] = 3553;
        this.c = FontRenderer.a(var10004);
        var10004 = new Object[]{null, var11};
        var10004[0] = 3553;
        this.d = FontRenderer.a(var10004);
        this.m = false;
        this.o = t;
        this.e = g.getWindow().getFramebufferWidth();
        this.f = g.getWindow().getFramebufferHeight();
        this.k = var1;
        this.l = var2;
        this.a_void________________________(new Object[]{var9});
        VertexBuffer var10001 = new VertexBuffer();
        Shader var10002 = RenderHelper22.d;
        Consumer var10003 = lpL12_0 -> {
            GL13.glActiveTexture(33984);
            int var6 = RenderUtil.b(new Object[]{110551307634167L});
            GL30.glBindTexture(3553, var6);
            GL20.glUniform2f(h, 1.0F, 0.0F);
            GL20.glUniform1f(i, this.k);
            GL20.glUniform1f(j, this.l);
        };
        int var19 = this.b.a;
        Object[] var10007 = new Object[]{null, null, null, var7};
        var10007[2] = var19;
        var10007[1] = var10003;
        var10007[0] = var10002;
        var10001 = var10001.a(var10007);
        var10002 = RenderHelper22.d;
        var10003 = lpL11_0 -> {
            GL13.glActiveTexture(33984);
            GL30.glBindTexture(3553, this.d.a);
            GL20.glUniform2f(h, 0.0F, 1.0F);
            GL20.glUniform1f(i, this.k);
            GL20.glUniform1f(j, this.l);
        };
        int var20 = this.a.a;
        var10007 = new Object[]{null, null, null, var7};
        var10007[2] = var20;
        var10007[1] = var10003;
        var10007[0] = var10002;
        this.n = var10001.a(var10007);
    }

    private void a_void________________________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 117519987309236L ^ var2;
        long var4 = var2 ^ 35527023105165L;
        long var6 = var2 ^ 84656432960849L;
        long var8 = var2 ^ 78776404403785L;
        long var10 = var2 ^ 45786829980009L;
        if (this.m) {
            this.c.close();
            this.d.close();
        }

        Object[] var10004 = new Object[]{null, var8};
        var10004[0] = 3553;
        this.c = FontRenderer.a(var10004);
        FontRenderer var10000 = this.c;
        int var10002 = this.e;
        int var10003 = this.f;
        Object[] var10006 = new Object[]{null, null, null, var4};
        var10006[2] = var10003;
        var10006[1] = var10002;
        var10006[0] = 32849;
        var10000.c(var10006);
        var10000 = this.c;
        var10004 = new Object[]{null, var6};
        var10004[0] = 9729;
        var10000.b_void_______(var10004);
        var10000 = this.c;
        var10004 = new Object[]{null, var10};
        var10004[0] = 33071;
        var10000.e(var10004);
        var10004 = new Object[]{null, var8};
        var10004[0] = 3553;
        this.d = FontRenderer.a(var10004);
        var10000 = this.d;
        var10002 = this.e;
        var10003 = this.f;
        var10006 = new Object[]{null, null, null, var4};
        var10006[2] = var10003;
        var10006[1] = var10002;
        var10006[0] = 32849;
        var10000.c(var10006);
        var10000 = this.d;
        var10004 = new Object[]{null, var6};
        var10004[0] = 9729;
        var10000.b_void_______(var10004);
        var10000 = this.d;
        var10004 = new Object[]{null, var10};
        var10004[0] = 33071;
        var10000.e(var10004);
        GL30.glBindFramebuffer(36160, this.a.a);
        GL30.glFramebufferTexture2D(36160, 36064, 3553, this.c.a, 0);
        GL30.glBindFramebuffer(36160, this.b.a);
        GL30.glFramebufferTexture2D(36160, 36064, 3553, this.d.a, 0);
        GL30.glBindFramebuffer(36160, 0);
        this.m = true;
    }

    private boolean a_boolean___________________________________________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 117519987309236L ^ var2;
        long var4 = var2 ^ 66642561940597L;
        if (g.getWindow() == null) {
            return false;
        } else {
            int var7 = g.getWindow().getFramebufferWidth();
            int var8 = g.getWindow().getFramebufferHeight();
            if (var7 <= 0 || var8 <= 0) {
                return false;
            } else if (this.e == var7 && this.f == var8) {
                return false;
            } else {
                this.e = var7;
                this.f = var8;
                this.a_void________________________(new Object[]{var4});
                return true;
            }
        }
    }

    public void b_void_________(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = (Long)var1[1];
        var4 = 117519987309236L ^ var4;
        long var6 = var4 ^ 19784286000452L;
        long var8 = var4 ^ 95451703888586L;
        Event.b();
        boolean var11 = this.a_boolean___________________________________________(new Object[]{var6});
        if (var11 || this.o != var2) {
            Object[] var10004 = new Object[]{null, var8};
            var10004[0] = var2;
            this.c(var10004);
        }
    }

    private void c(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = (Long)var1[1];
        var4 = 117519987309236L ^ var4;
        long var6 = var4 ^ 15354003995179L;
        this.o = var2;
        this.n.a_void______________________________(new Object[]{var6});
    }

    static {
        Object var16 = new String[]{"width", "direction", "texSampler", "sigma"};
        h = RenderHelper22.d.d(new Object[]{((Object[])var16)[1], 0L});
        i = RenderHelper22.d.d(new Object[]{((Object[])var16)[0], 0L});
        j = RenderHelper22.d.d(new Object[]{((Object[])var16)[3], 0L});
        Shader var10000 = RenderHelper22.d;
        Object var10001 = ((Object[])var16)[2];
        Object[] var10005 = new Object[]{null, null, 0L};
        var10005[1] = 0;
        var10005[0] = var10001;
        var10000.a_void_____________________________(var10005);
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
