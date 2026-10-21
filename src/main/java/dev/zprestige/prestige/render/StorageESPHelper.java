package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.BoolData2;
import dev.zprestige.prestige.data.IntBoolData2;
import dev.zprestige.prestige.data.IntData;
import dev.zprestige.prestige.data.IntData2;
import dev.zprestige.prestige.data.IntSupplierData;
import dev.zprestige.prestige.data.ShaderData;
import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

public class StorageESPHelper implements RenderContract2 {
    private static final Shader a;
    private Framebuffer c;
    private FontRenderer d;
    private int e;
    private int f;
    private boolean g;
    private DrawMode h;
    private VertexBuffer i;
    private int j;
    private float k;
    private float l;

    public StorageESPHelper(long var1) {
        super();
        var1 = 0L;
        var1 = 94496914895056L ^ var1;
        long var3 = var1 ^ 113952482025887L;
        long var5 = var1 ^ 140577391187380L;
        this.c = Framebuffer.a(new Object[]{var3});
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = 3553;
        this.d = FontRenderer.a(var10004);
        this.g = false;
        this.j = 5;
        this.k = 15.0F;
        this.l = 0.3F;
    }

    private void a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 94496914895056L ^ var2;
        long var4 = var2 ^ 36892299146217L;
        long var6 = var2 ^ 24349217315126L;
        long var8 = var2 ^ 134777776117994L;
        long var10 = var2 ^ 14284416467846L;
        long var12 = var2 ^ 96460746655831L;
        long var14 = var2 ^ 18540100490235L;
        long var16 = var2 ^ 34971407994066L;
        long var18 = var2 ^ 76849730449349L;
        if (b.getWindow() != null) {
            int var21 = b.getWindow().getFramebufferWidth();
            int var22 = b.getWindow().getFramebufferHeight();
            if (var21 > 0 && var22 > 0) {
                if (!this.g) {
                    this.e = var21;
                    this.f = var22;
                    FontRenderer var10000 = this.d;
                    int var10002 = this.e;
                    int var10003 = this.f;
                    Object[] var10006 = new Object[]{null, null, null, var6};
                    var10006[2] = var10003;
                    var10006[1] = var10002;
                    var10006[0] = 32856;
                    var10000.c(var10006);
                    var10000 = this.d;
                    Object[] var10004 = new Object[]{null, var8};
                    var10004[0] = 9729;
                    var10000.b_void_______(var10004);
                    var10000 = this.d;
                    var10004 = new Object[]{null, var16};
                    var10004[0] = 33071;
                    var10000.e(var10004);
                    Framebuffer var26 = this.c;
                    Object[] var10005 = new Object[]{null, this.d, var4};
                    var10005[0] = 36064;
                    var26.b(var10005);
                    Framebuffer var27 = this.c;
                    Object[] var10007 = new Object[]{null, null, null, null, var14};
                    var10007[3] = 0.0F;
                    var10007[2] = 0.0F;
                    var10007[1] = 0.0F;
                    var10007[0] = 0.0F;
                    var27.e(var10007);
                    this.h = new DrawMode(
                        VertexAttribute.b,
                        4,
                        false,
                        new Uniform[]{
                            new IntSupplierData(this.c.a),
                            new ShaderData(RenderHelper22.a),
                            new IntBoolData2(2884, false),
                            new BoolData2(true, true),
                            new IntData2(519, 515),
                            new IntBoolData2(2929, false)
                        },
                        var10
                    );
                    this.i = new VertexBuffer()
                        .b(
                            new Object[]{
                                a,
                                (Consumer<RenderPass>)lpL361_0 -> {
                                    GL13.glActiveTexture(33984);
                                    GL30.glBindTexture(3553, this.d.a);
                                    GL30.glTexParameteri(3553, 33085, 4);
                                    GL30.glTexParameteri(3553, 10241, 9985);
                                    GL30.glGenerateMipmap(3553);
                                    GL30.glTexParameteri(3553, 10242, 33071);
                                    GL30.glTexParameteri(3553, 10243, 33071);
                                    IntData.a(a.a(new Object[0]))
                                        .a("texSampler", 0)
                                        .a("quality", this.j)
                                        .a("radius", this.k)
                                        .a("fill", this.l)
                                        .a("oneTexel", 1.0F / this.e, 1.0F / this.f);
                                },
                                (java.util.function.IntSupplier)RenderUtil::a,
                                var18
                            }
                        );
                    this.g = true;
                } else if (this.e != var21 || this.f != var22) {
                    this.e = var21;
                    this.f = var22;
                    FontRenderer var10001 = this.d;
                    int var32 = this.e;
                    int var33 = this.f;
                    Object[] var38 = new Object[]{null, null, var12};
                    var38[1] = var33;
                    var38[0] = var32;
                    this.d = var10001.c_FontRenderer(var38);
                    FontRenderer var28 = this.d;
                    Object[] var35 = new Object[]{null, var8};
                    var35[0] = 9729;
                    var28.b_void_______(var35);
                    var28 = this.d;
                    var35 = new Object[]{null, var16};
                    var35[0] = 33071;
                    var28.e(var35);
                    Framebuffer var30 = this.c;
                    Object[] var37 = new Object[]{null, this.d, var4};
                    var37[0] = 36064;
                    var30.b(var37);
                    Framebuffer var31 = this.c;
                    Object[] var39 = new Object[]{null, null, null, null, var14};
                    var39[3] = 0.0F;
                    var39[2] = 0.0F;
                    var39[1] = 0.0F;
                    var39[0] = 0.0F;
                    var31.e(var39);
                }
            }
        }
    }

    public DrawMode a_DrawMode_(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 94496914895056L ^ var2;
        long var4 = var2 ^ 40675518871174L;
        this.a(new Object[]{var4});
        return this.h;
    }

    public void b(Object[] var1) {
        int var6 = (Integer)var1[0];
        float var5 = (Float)var1[1];
        float var2 = (Float)var1[2];
        long var3 = (Long)var1[3];
        var3 = 94496914895056L ^ var3;
        long var7 = var3 ^ 97383328989331L;
        long var9 = var3 ^ 43621117271708L;
        long var11 = var3 ^ 32696003371960L;
        Event.b();
        this.a(new Object[]{var7});
        if (this.i != null) {
            this.j = var6;
            this.k = var5;
            this.l = var2;
            boolean var14 = GL30.glGetBoolean(2884);
            boolean var15 = GL30.glGetBoolean(3042);
            boolean var16 = GL30.glGetBoolean(2929);
            GL30.glDisable(2884);
            GL30.glEnable(3042);
            GL30.glBlendFunc(770, 771);
            GL30.glDisable(2929);
            this.i.a_void______________________________(new Object[]{var9});
            if (var16) {
                GL30.glEnable(2929);
            }

            if (var14) {
                GL30.glEnable(2884);
            }

            if (!var15) {
                GL30.glDisable(3042);
            }

            Framebuffer var10000 = this.c;
            Object[] var10007 = new Object[]{null, null, null, null, var11};
            var10007[3] = 0.0F;
            var10007[2] = 0.0F;
            var10007[1] = 0.0F;
            var10007[0] = 0.0F;
            var10000.e(var10007);
        }
    }

    static {
        Object[] var10005 = new Object[]{null, null, null, 0L};
        var10005[2] = false;
        var10005[1] = "shaders/storage_outline.frag";
        var10005[0] = "shaders/post.vert";
        a = ShaderProgram.a(var10005);
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
