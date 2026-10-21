package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.BoolIntData;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.math.MathHelper6;
import dev.zprestige.prestige.type.BlendMode;
import dev.zprestige.prestige.type.GlPrimitive;
import dev.zprestige.prestige.world.WorldContract;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

public class RoundedTextureShader extends ShaderBase {
    private static final RoundedTextureShader a = new RoundedTextureShader(0L);
    private static final Map b = new HashMap();
    private final int c;
    private final int d;

    private RoundedTextureShader(long var1) {
        super("shaders/rounded_texture.vert", "shaders/rounded_texture.frag", (23865181315070L));
        var1 = 0L;
        var1 = 133747743752646L ^ var1;
        long var3 = var1 ^ 60902806195698L;
        long var5 = var1 ^ 118816401399352L;
        long var7 = var1 ^ 67653529446872L;
        this.c = this.a_Shader(new Object[0]).d(new Object[]{"tintMode", var3});
        this.d = this.a_Shader(new Object[0]).d(new Object[]{"roundness", var3});
        Shader var10000 = this.a_Shader(new Object[0]);
        Object[] var10005 = new Object[]{null, null, var7};
        var10005[1] = 0;
        var10005[0] = "texSampler";
        var10000.a_void_____________________________(var10005);
    }

    public static void c(Object[] var0) {
        RenderHelper8 var8 = (RenderHelper8)var0[0];
        MatrixCameraData var3 = (MatrixCameraData)var0[1];
        Matrix4f var2 = (Matrix4f)var0[2];
        FontRenderer var1 = (FontRenderer)var0[3];
        float var4 = (Float)var0[4];
        float var11 = (Float)var0[5];
        float var9 = (Float)var0[6];
        float var5 = (Float)var0[7];
        Color var10 = (Color)var0[8];
        long var6 = (Long)var0[9];
        var6 = 133747743752646L ^ var6;
        long var12 = var6 ^ 13819602248046L;
        RoundedTextureShader var10000 = a;
        Object[] var10014 = new Object[]{null, null, null, null, null, null, null, null, new Vector4f(0.0F), var10, BlendMode.MULTIPLY, var12};
        var10014[7] = var5;
        var10014[6] = var9;
        var10014[5] = var11;
        var10014[4] = var4;
        var10014[3] = var1;
        var10014[2] = var2;
        var10014[1] = var3;
        var10014[0] = var8;
        var10000.y(var10014);
    }

    public static void d(Object[] var0) {
        RenderHelper8 var3 = (RenderHelper8)var0[0];
        MatrixCameraData var9 = (MatrixCameraData)var0[1];
        FontRenderer var11 = (FontRenderer)var0[2];
        float var7 = (Float)var0[3];
        float var10 = (Float)var0[4];
        float var8 = (Float)var0[5];
        float var5 = (Float)var0[6];
        Matrix4f var6 = (Matrix4f)var0[7];
        Color var4 = (Color)var0[8];
        long var1 = (Long)var0[9];
        var1 = 133747743752646L ^ var1;
        long var12 = var1 ^ 36611569935087L;
        RoundedTextureShader var10000 = a;
        Object[] var10014 = new Object[]{null, null, null, null, null, null, null, null, new Vector4f(0.0F), var4, BlendMode.MULTIPLY, var12};
        var10014[7] = var5;
        var10014[6] = var8;
        var10014[5] = var10;
        var10014[4] = var7;
        var10014[3] = var11;
        var10014[2] = var6;
        var10014[1] = var9;
        var10014[0] = var3;
        var10000.y(var10014);
    }

    public static void m(Object[] var0) {
        RenderHelper8 var4 = (RenderHelper8)var0[0];
        MatrixCameraData var6 = (MatrixCameraData)var0[1];
        Matrix4f var1 = (Matrix4f)var0[2];
        FontRenderer var13 = (FontRenderer)var0[3];
        float var3 = (Float)var0[4];
        float var11 = (Float)var0[5];
        float var12 = (Float)var0[6];
        float var2 = (Float)var0[7];
        float var10 = (Float)var0[8];
        float var14 = (Float)var0[9];
        Color var5 = (Color)var0[10];
        float var9 = (Float)var0[11];
        long var7 = (Long)var0[12];
        var7 = 133747743752646L ^ var7;
        long var15 = var7 ^ 94244386372130L;
        RoundedTextureShader var10000 = a;
        int var10011 = var5.getRGB();
        Object[] var10016 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, new Vector4f(var9), BlendMode.MULTIPLY, var15};
        var10016[10] = var10011;
        var10016[9] = var14;
        var10016[8] = var10;
        var10016[7] = var2;
        var10016[6] = var12;
        var10016[5] = var11;
        var10016[4] = var3;
        var10016[3] = var13;
        var10016[2] = var1;
        var10016[1] = var6;
        var10016[0] = var4;
        var10000.G(var10016);
    }

    public static void o(Object[] var0) {
        RenderHelper8 var12 = (RenderHelper8)var0[0];
        MatrixCameraData var11 = (MatrixCameraData)var0[1];
        Matrix4f var9 = (Matrix4f)var0[2];
        FontRenderer var16 = (FontRenderer)var0[3];
        float var7 = (Float)var0[4];
        float var13 = (Float)var0[5];
        float var4 = (Float)var0[6];
        float var3 = (Float)var0[7];
        float var17 = (Float)var0[8];
        float var10 = (Float)var0[9];
        Color var18 = (Color)var0[10];
        Color var15 = (Color)var0[11];
        Color var8 = (Color)var0[12];
        Color var2 = (Color)var0[13];
        float var1 = (Float)var0[14];
        boolean var14 = (Boolean)var0[15];
        long var5 = (Long)var0[16];
        var5 = 133747743752646L ^ var5;
        long var19 = var5 ^ 46609086249295L;
        RoundedTextureShader var10000 = a;
        int var10011 = var18.getRGB();
        int var10012 = var15.getRGB();
        int var10013 = var8.getRGB();
        int var10014 = var2.getRGB();
        Vector4f var10015 = new Vector4f(var1);
        Object[] var10020 = new Object[]{
            null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, BlendMode.MULTIPLY, var19
        };
        var10020[15] = var14;
        var10020[14] = var10015;
        var10020[13] = var10014;
        var10020[12] = var10013;
        var10020[11] = var10012;
        var10020[10] = var10011;
        var10020[9] = var10;
        var10020[8] = var17;
        var10020[7] = var3;
        var10020[6] = var4;
        var10020[5] = var13;
        var10020[4] = var7;
        var10020[3] = var16;
        var10020[2] = var9;
        var10020[1] = var11;
        var10020[0] = var12;
        var10000.H(var10020);
    }

    private void y(Object[] var1) {
        Matrix4f var3 = (Matrix4f)var1[2];
        FontRenderer var2 = (FontRenderer)var1[3];
        float var12 = (Float)var1[4];
        float var11 = (Float)var1[5];
        float var14 = (Float)var1[6];
        float var10 = (Float)var1[7];
        Vector4f var6 = (Vector4f)var1[8];
        Color var9 = (Color)var1[9];
        BlendMode var7 = (BlendMode)var1[10];
        long var4 = (Long)var1[11];
        var4 = 133747743752646L ^ var4;
        long var15 = var4 ^ 56610810757369L;
        long var17 = var4 ^ 126483276391244L;
        Object[] var10007 = new Object[]{null, null, null, null, var15};
        var10007[3] = true;
        var10007[2] = var7;
        var10007[1] = var6;
        var10007[0] = var2;
        DrawMode var19 = this.a(var10007);
        ((RenderHelper30)RenderState.a.get())
            .b(
                new Object[]{
                    new RenderHelper13(
                        var19,
                        (lpL148_0, lpL148_1) -> {
                            int var22 = var9.getRGB();
                            int var23 = var22 >> 24 & 0xFF;
                            int var24 = var22 >> 16 & 0xFF;
                            int var25 = var22 >> 8 & 0xFF;
                            int var26 = var22 & 0xFF;
                            int var27 = var23 << 24 | var26 << 16 | var25 << 8 | var24;
                            WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL148_1).k(new Object[]{111161505170643L});
                            float var10003 = var11 + var10;
                            float var10004 = ((java.lang.Float)lpL148_0);
                            Object[] var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var10003;
                            var10007x[1] = var12;
                            var10007x[0] = var3;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{0.0F, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var14, var10}, 118034685045795L});
                            Object[] var37 = new Object[]{null, 106573078251021L};
                            var37[0] = var27;
                            var10000 = var10000.e(var37);
                            float var10002 = var12 + var14;
                            var10003 = var11 + var10;
                            var10004 = ((java.lang.Float)lpL148_0);
                            var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var10003;
                            var10007x[1] = var10002;
                            var10007x[0] = var3;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{var14, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var14, var10}, 118034685045795L});
                            Object[] var39 = new Object[]{null, 106573078251021L};
                            var39[0] = var27;
                            var10000 = var10000.e(var39);
                            var10002 = var12 + var14;
                            var10004 = ((java.lang.Float)lpL148_0);
                            var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var11;
                            var10007x[1] = var10002;
                            var10007x[0] = var3;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{var14, var10}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var14, var10}, 118034685045795L});
                            Object[] var41 = new Object[]{null, 106573078251021L};
                            var41[0] = var27;
                            var10000 = var10000.e(var41);
                            var10004 = ((java.lang.Float)lpL148_0);
                            var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var11;
                            var10007x[1] = var12;
                            var10007x[0] = var3;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{0.0F, var10}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var14, var10}, 118034685045795L});
                            Object[] var43 = new Object[]{null, 106573078251021L};
                            var43[0] = var27;
                            var10000.e(var43).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}).h(new Object[]{7414847813752L});
                        }
                    ),
                    var17
                }
            );
    }

    private void z(Object[] var1) {
        Matrix4f var10 = (Matrix4f)var1[2];
        FontRenderer var5 = (FontRenderer)var1[3];
        float var11 = (Float)var1[4];
        float var2 = (Float)var1[5];
        float var13 = (Float)var1[6];
        float var8 = (Float)var1[7];
        float var4 = (Float)var1[8];
        float var7 = (Float)var1[9];
        float var3 = (Float)var1[10];
        float var14 = (Float)var1[11];
        Vector4f var9 = (Vector4f)var1[12];
        Color var18 = (Color)var1[13];
        BlendMode var15 = (BlendMode)var1[14];
        long var16 = (Long)var1[15];
        var16 = 133747743752646L ^ var16;
        long var19 = var16 ^ 93978664291017L;
        long var21 = var16 ^ 23143588604284L;
        Object[] var10007 = new Object[]{null, null, null, null, var19};
        var10007[3] = true;
        var10007[2] = var15;
        var10007[1] = var9;
        var10007[0] = var5;
        DrawMode var23 = this.a(var10007);
        ((RenderHelper30)RenderState.a.get())
            .b(
                new Object[]{
                    new RenderHelper13(
                        var23,
                        (lpL147_0, lpL147_1) -> {
                            int var26 = var18.getRGB();
                            int var27 = var26 >> 24 & 0xFF;
                            int var28 = var26 >> 16 & 0xFF;
                            int var29 = var26 >> 8 & 0xFF;
                            int var30 = var26 & 0xFF;
                            int var31 = var27 << 24 | var30 << 16 | var29 << 8 | var28;
                            WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL147_1).k(new Object[]{111161505170643L});
                            float var10003 = var2 + var8;
                            float var10004 = ((java.lang.Float)lpL147_0);
                            Object[] var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var10003;
                            var10007x[1] = var11;
                            var10007x[0] = var10;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{var4, var7}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var13, var8}, 118034685045795L});
                            Object[] var41 = new Object[]{null, 106573078251021L};
                            var41[0] = var31;
                            var10000 = var10000.e(var41);
                            float var10002 = var11 + var13;
                            var10003 = var2 + var8;
                            var10004 = ((java.lang.Float)lpL147_0);
                            var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var10003;
                            var10007x[1] = var10002;
                            var10007x[0] = var10;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{var4 + var3, var7}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var13, var8}, 118034685045795L});
                            Object[] var43 = new Object[]{null, 106573078251021L};
                            var43[0] = var31;
                            var10000 = var10000.e(var43);
                            var10002 = var11 + var13;
                            var10004 = ((java.lang.Float)lpL147_0);
                            var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var2;
                            var10007x[1] = var10002;
                            var10007x[0] = var10;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{var4 + var3, var7 + var14}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var13, var8}, 118034685045795L});
                            Object[] var45 = new Object[]{null, 106573078251021L};
                            var45[0] = var31;
                            var10000 = var10000.e(var45);
                            var10004 = ((java.lang.Float)lpL147_0);
                            var10007x = new Object[]{null, null, null, null, 116910503435476L};
                            var10007x[3] = var10004;
                            var10007x[2] = var2;
                            var10007x[1] = var11;
                            var10007x[0] = var10;
                            var10000 = var10000.d(var10007x)
                                .b_WorldContract(new Object[]{new float[]{var4, var7 + var14}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var13, var8}, 118034685045795L});
                            Object[] var47 = new Object[]{null, 106573078251021L};
                            var47[0] = var31;
                            var10000.e(var47).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}).h(new Object[]{7414847813752L});
                        }
                    ),
                    var21
                }
            );
    }

    public static void E(Object[] var0) {
        RenderHelper8 var7 = (RenderHelper8)var0[0];
        MatrixCameraData var15 = (MatrixCameraData)var0[1];
        Matrix4f var6 = (Matrix4f)var0[2];
        FontRenderer var4 = (FontRenderer)var0[3];
        float var9 = (Float)var0[4];
        float var2 = (Float)var0[5];
        float var11 = (Float)var0[6];
        float var16 = (Float)var0[7];
        float var8 = (Float)var0[8];
        float var3 = (Float)var0[9];
        float var12 = (Float)var0[10];
        float var1 = (Float)var0[11];
        Vector4f var5 = (Vector4f)var0[12];
        Color var10 = (Color)var0[13];
        long var13 = (Long)var0[14];
        var13 = 133747743752646L ^ var13;
        long var17 = var13 ^ 73228212321128L;
        RoundedTextureShader var10000 = a;
        Object[] var10018 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var5, var10, BlendMode.MULTIPLY, var17};
        var10018[11] = var1;
        var10018[10] = var12;
        var10018[9] = var3;
        var10018[8] = var8;
        var10018[7] = var16;
        var10018[6] = var11;
        var10018[5] = var2;
        var10018[4] = var9;
        var10018[3] = var4;
        var10018[2] = var6;
        var10018[1] = var15;
        var10018[0] = var7;
        var10000.z(var10018);
    }

    private DrawMode a(Object[] var1) {
        FontRenderer var4 = (FontRenderer)var1[0];
        Vector4f var3 = (Vector4f)var1[1];
        BlendMode var5 = (BlendMode)var1[2];
        boolean var2 = (Boolean)var1[3];
        MathHelper6 var8 = new MathHelper6(var4, var3, var5, var2);
        return (DrawMode)b.computeIfAbsent(var8, lpL145_0 -> {
            VertexFormat var10002 = VertexAttribute.e;
            Uniform[] var10005 = new Uniform[]{Uniform.a, Uniform.a_ShaderData(new Object[]{this.a_Shader(new Object[0])}), null, null, null, null};
            Object[] var10011 = new Object[]{null, var2};
            var10011[0] = 2929;
            var10005[2] = Uniform.a_IntBoolData2(var10011);
            var10005[3] = Uniform.b(new Object[]{true});
            var10005[4] = new BoolIntData(true, true, 770, 771, 1, 771);
            var10005[5] = Uniform.a_Uniform(new Object[]{(Consumer<RenderPass>)lpL146_0 -> {
                GL13.glActiveTexture(33984);
                GL11.glBindTexture(3553, var4.a);
                GL20.glUniform1i(this.c, var5.ordinal());
                GL20.glUniform4f(this.d, var3.x, var3.y, var3.z, var3.w);
            }});
            return new DrawMode(var10002, 4, false, var10005, 138006566364233L);
        });
    }

    private void G(Object[] var1) {
        RenderHelper8 var4 = (RenderHelper8)var1[0];
        Matrix4f var13 = (Matrix4f)var1[2];
        FontRenderer var11 = (FontRenderer)var1[3];
        float var2 = (Float)var1[4];
        float var3 = (Float)var1[5];
        float var7 = (Float)var1[6];
        float var10 = (Float)var1[7];
        float var12 = (Float)var1[8];
        float var8 = (Float)var1[9];
        int var15 = (Integer)var1[10];
        Vector4f var9 = (Vector4f)var1[11];
        BlendMode var16 = (BlendMode)var1[12];
        long var5 = (Long)var1[13];
        var5 = 133747743752646L ^ var5;
        long var17 = var5 ^ 62422432068521L;
        long var19 = var5 ^ 56497257084492L;
        long var21 = var5 ^ 28305482178466L;
        long var23 = var5 ^ 83541911797404L;
        long var25 = var5 ^ 104511460019013L;
        long var27 = var5 ^ 68141019647704L;
        float var29 = Math.abs(var10 - var2);
        float var30 = Math.abs(var12 - var3);
        int var31 = var15 >> 24 & 0xFF;
        int var32 = var15 >> 16 & 0xFF;
        int var33 = var15 >> 8 & 0xFF;
        int var34 = var15 & 0xFF;
        int var35 = var31 << 24 | var34 << 16 | var33 << 8 | var32;
        Object[] var10008 = new Object[]{null, null, null, null, null, var27};
        var10008[4] = true;
        var10008[3] = var16;
        var10008[2] = var9;
        var10008[1] = var11;
        var10008[0] = var4;
        TextRenderer var10000 = (TextRenderer)this.a_TextRenderer_(var10008).k(new Object[]{var17});
        Object[] var10007 = new Object[]{null, null, null, null, var23};
        var10007[3] = var8;
        var10007[2] = var12;
        var10007[1] = var2;
        var10007[0] = var13;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{0.0F, 0.0F}, var19})
            .b_TextRenderer(new Object[]{new float[]{var29, var30}, var19});
        Object[] var10004 = new Object[]{null, var21};
        var10004[0] = var35;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var23};
        var10007[3] = var8;
        var10007[2] = var12;
        var10007[1] = var10;
        var10007[0] = var13;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{var29, 0.0F}, var19})
            .b_TextRenderer(new Object[]{new float[]{var29, var30}, var19});
        var10004 = new Object[]{null, var21};
        var10004[0] = var35;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var23};
        var10007[3] = var7;
        var10007[2] = var3;
        var10007[1] = var10;
        var10007[0] = var13;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{var29, var30}, var19})
            .b_TextRenderer(new Object[]{new float[]{var29, var30}, var19});
        var10004 = new Object[]{null, var21};
        var10004[0] = var35;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var23};
        var10007[3] = var7;
        var10007[2] = var3;
        var10007[1] = var2;
        var10007[0] = var13;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{0.0F, var30}, var19})
            .b_TextRenderer(new Object[]{new float[]{var29, var30}, var19});
        var10004 = new Object[]{null, var21};
        var10004[0] = var35;
        ((TextRenderer)var10000.e_TextRenderer(var10004).i_WorldContract(new Object[]{GlPrimitive.QUADS, var25})).h_TextRenderer(new Object[0]);
    }

    private void H(Object[] var1) {
        RenderHelper8 var9 = (RenderHelper8)var1[0];
        Matrix4f var8 = (Matrix4f)var1[2];
        FontRenderer var11 = (FontRenderer)var1[3];
        float var6 = (Float)var1[4];
        float var12 = (Float)var1[5];
        float var4 = (Float)var1[6];
        float var13 = (Float)var1[7];
        float var2 = (Float)var1[8];
        float var18 = (Float)var1[9];
        int var17 = (Integer)var1[10];
        int var7 = (Integer)var1[11];
        int var20 = (Integer)var1[12];
        int var14 = (Integer)var1[13];
        Vector4f var5 = (Vector4f)var1[14];
        boolean var19 = (Boolean)var1[15];
        BlendMode var10 = (BlendMode)var1[16];
        long var15 = (Long)var1[17];
        var15 = 133747743752646L ^ var15;
        long var21 = var15 ^ 129646513965262L;
        long var23 = var15 ^ 138874520767787L;
        long var25 = var15 ^ 92986423583941L;
        long var27 = var15 ^ 7522256881147L;
        long var29 = var15 ^ 19933160927266L;
        long var31 = var15 ^ 72810458553650L;
        long var33 = var15 ^ 124060764714431L;
        float var35 = Math.abs(var13 - var6);
        float var36 = Math.abs(var2 - var12);
        Object[] var10003 = new Object[]{null, var31};
        var10003[0] = var17;
        int var37 = RenderState.b_int_(var10003);
        var10003 = new Object[]{null, var31};
        var10003[0] = var7;
        int var38 = RenderState.b_int_(var10003);
        var10003 = new Object[]{null, var31};
        var10003[0] = var20;
        int var39 = RenderState.b_int_(var10003);
        var10003 = new Object[]{null, var31};
        var10003[0] = var14;
        int var40 = RenderState.b_int_(var10003);
        Object[] var10008 = new Object[]{null, null, null, null, null, var33};
        var10008[4] = var19;
        var10008[3] = var10;
        var10008[2] = var5;
        var10008[1] = var11;
        var10008[0] = var9;
        TextRenderer var10000 = (TextRenderer)this.a_TextRenderer_(var10008).k(new Object[]{var21});
        Object[] var10007 = new Object[]{null, null, null, null, var27};
        var10007[3] = var18;
        var10007[2] = var2;
        var10007[1] = var6;
        var10007[0] = var8;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{0.0F, 0.0F}, var23})
            .b_TextRenderer(new Object[]{new float[]{var35, var36}, var23});
        Object[] var10004 = new Object[]{null, var25};
        var10004[0] = var37;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var27};
        var10007[3] = var18;
        var10007[2] = var2;
        var10007[1] = var13;
        var10007[0] = var8;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{var35, 0.0F}, var23})
            .b_TextRenderer(new Object[]{new float[]{var35, var36}, var23});
        var10004 = new Object[]{null, var25};
        var10004[0] = var38;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var27};
        var10007[3] = var4;
        var10007[2] = var12;
        var10007[1] = var13;
        var10007[0] = var8;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{var35, var36}, var23})
            .b_TextRenderer(new Object[]{new float[]{var35, var36}, var23});
        var10004 = new Object[]{null, var25};
        var10004[0] = var39;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var27};
        var10007[3] = var4;
        var10007[2] = var12;
        var10007[1] = var6;
        var10007[0] = var8;
        var10000 = var10000.d_TextRenderer(var10007)
            .b_TextRenderer(new Object[]{new float[]{0.0F, var36}, var23})
            .b_TextRenderer(new Object[]{new float[]{var35, var36}, var23});
        var10004 = new Object[]{null, var25};
        var10004[0] = var40;
        ((TextRenderer)var10000.e_TextRenderer(var10004).i_WorldContract(new Object[]{GlPrimitive.QUADS, var29})).h_TextRenderer(new Object[0]);
    }

    private static int a_int___________________________(Object[] var0) {
        int var3 = (Integer)var0[0];
        int var4 = var3 >> 24 & 0xFF;
        int var5 = var3 >> 16 & 0xFF;
        int var6 = var3 >> 8 & 0xFF;
        int var7 = var3 & 0xFF;
        return var4 << 24 | var7 << 16 | var6 << 8 | var5;
    }

    private TextRenderer a_TextRenderer_(Object[] var1) {
        RenderHelper8 var7 = (RenderHelper8)var1[0];
        FontRenderer var6 = (FontRenderer)var1[1];
        Vector4f var4 = (Vector4f)var1[2];
        BlendMode var5 = (BlendMode)var1[3];
        boolean var8 = (Boolean)var1[4];
        long var2 = (Long)var1[5];
        var2 = 133747743752646L ^ var2;
        long var9 = var2 ^ 41335267254465L;
        MathHelper6 var11 = new MathHelper6(var6, var4, var5, var8);
        DrawMode var12 = (DrawMode)b.computeIfAbsent(var11, lpL143_0 -> {
            VertexFormat var10002 = VertexAttribute.e;
            Uniform[] var10005 = new Uniform[]{Uniform.a, Uniform.a_ShaderData(new Object[]{this.a_Shader(new Object[0])}), null, null, null, null};
            Object[] var10011 = new Object[]{null, var8};
            var10011[0] = 2929;
            var10005[2] = Uniform.a_IntBoolData2(var10011);
            var10011 = new Object[]{null, true};
            var10011[0] = false;
            var10005[3] = Uniform.a(var10011);
            var10005[4] = new BoolIntData(true, true, 770, 771, 1, 771);
            var10005[5] = Uniform.a_Uniform(new Object[]{(Consumer<RenderPass>)lpL144_0 -> {
                GL13.glActiveTexture(33984);
                GL11.glBindTexture(3553, var6.a);
                GL20.glUniform1i(this.c, var5.ordinal());
                GL20.glUniform4f(this.d, var4.x, var4.y, var4.z, var4.w);
            }});
            return new DrawMode(var10002, 4, false, var10005, 138006566364233L);
        });
        return var7.a(new Object[]{var12, var9});
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
