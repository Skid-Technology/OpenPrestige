package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.VectorFloatData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.math.MathUtil4;
import dev.zprestige.prestige.type.GlPrimitive;
import dev.zprestige.prestige.world.WorldContract;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Consumer;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL20;

public class RoundedOutlinePosColorShader extends ShaderBase {
    private static final RoundedOutlinePosColorShader a = new RoundedOutlinePosColorShader(0L);
    private static final Map b;
    private static final Matrix4f c = new Matrix4f();
    private static final ThreadLocal d = ThreadLocal.withInitial(Vector4f::new);
    private final int e;
    private final int f;

    private RoundedOutlinePosColorShader(long var1) {
        super("shaders/rounded_pos_color.vert", "shaders/rounded_outline_pos_color.frag", (10094525613653L));
        var1 = 0L;
        var1 = 6996815971196L ^ var1;
        long var3 = var1 ^ 92390406821603L;
        long var5 = var1 ^ 16987705443625L;
        this.e = this.a_Shader(new Object[0]).d(new Object[]{"roundness", var3});
        this.f = this.a_Shader(new Object[0]).d(new Object[]{"outlineThickness", var3});
    }

    public static void c(Object[] var0) {
        RenderHelper8 var6 = (RenderHelper8)var0[0];
        MatrixCameraData var12 = (MatrixCameraData)var0[1];
        Matrix4f var10 = (Matrix4f)var0[2];
        float var11 = (Float)var0[3];
        float var1 = (Float)var0[4];
        float var7 = (Float)var0[5];
        float var5 = (Float)var0[6];
        Color var3 = (Color)var0[7];
        float var2 = (Float)var0[8];
        float var4 = (Float)var0[9];
        long var8 = (Long)var0[10];
        var8 = 6996815971196L ^ var8;
        long var13 = var8 ^ 11309767004819L;
        RoundedOutlinePosColorShader var10000 = a;
        int var10008 = var3.getRGB();
        Vector4f var10009 = ((Vector4f)d.get()).set(var2);
        Object[] var10013 = new Object[]{null, null, null, null, null, null, null, null, null, null, var13};
        var10013[9] = var4;
        var10013[8] = var10009;
        var10013[7] = var10008;
        var10013[6] = var5;
        var10013[5] = var7;
        var10013[4] = var1;
        var10013[3] = var11;
        var10013[2] = var10;
        var10013[1] = var12;
        var10013[0] = var6;
        var10000.e(var10013);
    }

    private void e(Object[] var1) {
        Matrix4f var11 = (Matrix4f)var1[2];
        float var10 = (Float)var1[3];
        float var6 = (Float)var1[4];
        float var12 = (Float)var1[5];
        float var2 = (Float)var1[6];
        int var4 = (Integer)var1[7];
        Vector4f var5 = (Vector4f)var1[8];
        float var13 = (Float)var1[9];
        long var7 = (Long)var1[10];
        var7 = 6996815971196L ^ var7;
        long var14 = var7 ^ 14044989008541L;
        long var16 = var7 ^ 123555432777313L;
        long var18 = var7 ^ 64518496702926L;
        Object[] var10005 = new Object[]{null, null, var18};
        var10005[1] = var13;
        var10005[0] = var5;
        DrawMode var20 = this.a(var10005);
        float var21 = var5.x;
        float var22 = var5.y;
        float var23 = var5.z;
        float var24 = var5.w;
        RenderState.a_RenderHelper30(new Object[]{var14}).b(new Object[]{new RenderHelper13(var20, (lpL140_0, lpL140_1) -> {
            Event.b();
            Object[] var10004 = new Object[]{null, 91022737183023L};
            var10004[0] = var4;
            int var26x = RenderState.b_int_(var10004);
            float var27 = Math.max(Math.max(var21, var22), Math.max(var23, var24));
            float var28 = 2.0F * var13 + var27 + 2.0F;
            ((dev.zprestige.prestige.world.WorldContract)lpL140_1).k(new Object[]{111161505170643L});
            if (var28 * 2.0F >= Math.min(var12, var2)) {
                float var10002 = ((java.lang.Float)lpL140_0);
                Object[] var10016 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, 82698704610984L};
                var10016[13] = var2;
                var10016[12] = var12;
                var10016[11] = 0.0F;
                var10016[10] = 0.0F;
                var10016[9] = var2;
                var10016[8] = var12;
                var10016[7] = var6;
                var10016[6] = var10;
                var10016[5] = var2;
                var10016[4] = var12;
                var10016[3] = var26x;
                var10016[2] = var10002;
                var10016[1] = var11;
                var10016[0] = ((dev.zprestige.prestige.world.WorldContract)lpL140_1);
                f(var10016);
            } else {
                float var29 = ((java.lang.Float)lpL140_0);
                float var10011 = var2 - var28;
                Object[] var37 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, 82698704610984L};
                var37[13] = var2;
                var37[12] = var12;
                var37[11] = var10011;
                var37[10] = 0.0F;
                var37[9] = var28;
                var37[8] = var12;
                var37[7] = var6;
                var37[6] = var10;
                var37[5] = var2;
                var37[4] = var12;
                var37[3] = var26x;
                var37[2] = var29;
                var37[1] = var11;
                var37[0] = ((dev.zprestige.prestige.world.WorldContract)lpL140_1);
                f(var37);
                var29 = ((java.lang.Float)lpL140_0);
                float var10007 = var6 + var2 - var28;
                var37 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, 82698704610984L};
                var37[13] = var28;
                var37[12] = var12;
                var37[11] = 0.0F;
                var37[10] = 0.0F;
                var37[9] = var28;
                var37[8] = var12;
                var37[7] = var10007;
                var37[6] = var10;
                var37[5] = var2;
                var37[4] = var12;
                var37[3] = var26x;
                var37[2] = var29;
                var37[1] = var11;
                var37[0] = ((dev.zprestige.prestige.world.WorldContract)lpL140_1);
                f(var37);
                var29 = ((java.lang.Float)lpL140_0);
                var10007 = var6 + var28;
                float var10009 = var2 - 2.0F * var28;
                float var10013 = var2 - var28;
                var37 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, 82698704610984L};
                var37[13] = var10013;
                var37[12] = var28;
                var37[11] = var28;
                var37[10] = 0.0F;
                var37[9] = var10009;
                var37[8] = var28;
                var37[7] = var10007;
                var37[6] = var10;
                var37[5] = var2;
                var37[4] = var12;
                var37[3] = var26x;
                var37[2] = var29;
                var37[1] = var11;
                var37[0] = ((dev.zprestige.prestige.world.WorldContract)lpL140_1);
                f(var37);
                var29 = ((java.lang.Float)lpL140_0);
                float var10006 = var10 + var12 - var28;
                var10007 = var6 + var28;
                var10009 = var2 - 2.0F * var28;
                float var10010 = var12 - var28;
                var10013 = var2 - var28;
                var37 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, 82698704610984L};
                var37[13] = var10013;
                var37[12] = var12;
                var37[11] = var28;
                var37[10] = var10010;
                var37[9] = var10009;
                var37[8] = var28;
                var37[7] = var10007;
                var37[6] = var10006;
                var37[5] = var2;
                var37[4] = var12;
                var37[3] = var26x;
                var37[2] = var29;
                var37[1] = var11;
                var37[0] = ((dev.zprestige.prestige.world.WorldContract)lpL140_1);
                f(var37);
            }

            ((dev.zprestige.prestige.world.WorldContract)lpL140_1).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}).h(new Object[]{7414847813752L});
        }), var16});
    }

    private static void f(Object[] var0) {
        WorldContract var16 = (WorldContract)var0[0];
        Matrix4f var3 = (Matrix4f)var0[1];
        float var10 = (Float)var0[2];
        int var4 = (Integer)var0[3];
        float var15 = (Float)var0[4];
        float var12 = (Float)var0[5];
        float var8 = (Float)var0[6];
        float var9 = (Float)var0[7];
        float var11 = (Float)var0[8];
        float var2 = (Float)var0[9];
        float var13 = (Float)var0[10];
        float var7 = (Float)var0[11];
        float var1 = (Float)var0[12];
        float var14 = (Float)var0[13];
        long var5 = (Long)var0[14];
        var5 = 6996815971196L ^ var5;
        long var17 = var5 ^ 50056269571033L;
        long var19 = var5 ^ 43153706285312L;
        long var21 = var5 ^ 41995370973687L;
        float var10003 = var9 + var2;
        Object[] var10007 = new Object[]{null, null, null, null, var19};
        var10007[3] = var10;
        var10007[2] = var10003;
        var10007[1] = var8;
        var10007[0] = var3;
        WorldContract var10000 = var16.d(var10007)
            .b_WorldContract(new Object[]{new float[]{var13, var7}, var21})
            .b_WorldContract(new Object[]{new float[]{var15, var12}, var21});
        Object[] var10004 = new Object[]{null, var17};
        var10004[0] = var4;
        var10000 = var10000.e(var10004);
        float var10002 = var8 + var11;
        var10003 = var9 + var2;
        var10007 = new Object[]{null, null, null, null, var19};
        var10007[3] = var10;
        var10007[2] = var10003;
        var10007[1] = var10002;
        var10007[0] = var3;
        var10000 = var10000.d(var10007)
            .b_WorldContract(new Object[]{new float[]{var1, var7}, var21})
            .b_WorldContract(new Object[]{new float[]{var15, var12}, var21});
        var10004 = new Object[]{null, var17};
        var10004[0] = var4;
        var10000 = var10000.e(var10004);
        var10002 = var8 + var11;
        var10007 = new Object[]{null, null, null, null, var19};
        var10007[3] = var10;
        var10007[2] = var9;
        var10007[1] = var10002;
        var10007[0] = var3;
        var10000 = var10000.d(var10007)
            .b_WorldContract(new Object[]{new float[]{var1, var14}, var21})
            .b_WorldContract(new Object[]{new float[]{var15, var12}, var21});
        var10004 = new Object[]{null, var17};
        var10004[0] = var4;
        var10000 = var10000.e(var10004);
        var10007 = new Object[]{null, null, null, null, var19};
        var10007[3] = var10;
        var10007[2] = var9;
        var10007[1] = var8;
        var10007[0] = var3;
        var10000 = var10000.d(var10007)
            .b_WorldContract(new Object[]{new float[]{var13, var14}, var21})
            .b_WorldContract(new Object[]{new float[]{var15, var12}, var21});
        var10004 = new Object[]{null, var17};
        var10004[0] = var4;
        var10000.e(var10004);
    }

    public DrawMode a(Object[] var1) {
        Vector4f var5 = (Vector4f)var1[0];
        float var2 = (Float)var1[1];
        long var3 = (Long)var1[2];
        var3 = 6996815971196L ^ var3;
        long var6 = var3 ^ 139298265287696L;
        long var8 = var3 ^ 45915609480518L;
        Event.b();
        VectorFloatData var11 = new VectorFloatData(MathUtil4.a_Vector4f(new Object[]{var5, var6}), var2);
        DrawMode var12 = (DrawMode)b.get(var11);
        if (var12 == null) {
            VertexFormat var10002 = VertexAttribute.e;
            Uniform[] var10005 = new Uniform[]{Uniform.a, Uniform.a_ShaderData(new Object[]{this.a_Shader(new Object[0])}), null, null, null, null};
            Object[] var10011 = new Object[]{null, true};
            var10011[0] = 2884;
            var10005[2] = Uniform.a_IntBoolData2(var10011);
            var10011 = new Object[]{null, true};
            var10011[0] = true;
            var10005[3] = Uniform.a_BoolData2(var10011);
            var10011 = new Object[]{null, 515};
            var10011[0] = 515;
            var10005[4] = Uniform.a_IntData2(var10011);
            var10005[5] = Uniform.a_Uniform(new Object[]{(Consumer<RenderPass>)lpL139_0 -> {
                GL20.glUniform4f(this.e, var11.bF().x, var11.bF().y, var11.bF().z, var11.bF().w);
                GL20.glUniform1f(this.f, var11.bG());
            }});
            var12 = new DrawMode(var10002, 4, false, var10005, var8);
            b.put(var11, var12);
        }

        return var12;
    }

    static {
        Object[] var10003 = new Object[]{null, 0L};
        var10003[0] = 128;
        b = MathUtil4.a(var10003);
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
