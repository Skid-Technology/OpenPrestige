package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.MatrixCameraData;
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

public class RoundedPosColorShader extends ShaderBase {
    private static final RoundedPosColorShader a = new RoundedPosColorShader(0L);
    private static final Map b;
    private final int c;

    private RoundedPosColorShader(long var1) {
        super("shaders/rounded_pos_color.vert", "shaders/rounded_pos_color.frag", (86459433955547L));
        var1 = 0L;
        var1 = 122901707857854L ^ var1;
        long var3 = var1 ^ 134214443711663L;
        long var5 = var1 ^ 36718471982949L;
        this.c = this.a_Shader(new Object[0]).d(new Object[]{"roundness", var3});
    }

    public static void c(Object[] var0) {
        RenderHelper8 var6 = (RenderHelper8)var0[0];
        MatrixCameraData var5 = (MatrixCameraData)var0[1];
        Matrix4f var9 = (Matrix4f)var0[2];
        float var7 = (Float)var0[3];
        float var10 = (Float)var0[4];
        float var2 = (Float)var0[5];
        float var8 = (Float)var0[6];
        Color var11 = (Color)var0[7];
        float var1 = (Float)var0[8];
        long var3 = (Long)var0[9];
        var3 = 122901707857854L ^ var3;
        long var12 = var3 ^ 129088124588045L;
        RoundedPosColorShader var10000 = a;
        int var10008 = var11.getRGB();
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, new Vector4f(var1), var12};
        var10012[7] = var10008;
        var10012[6] = var8;
        var10012[5] = var2;
        var10012[4] = var10;
        var10012[3] = var7;
        var10012[2] = var9;
        var10012[1] = var5;
        var10012[0] = var6;
        var10000.e(var10012);
    }

    private void e(Object[] var1) {
        Matrix4f var4 = (Matrix4f)var1[2];
        float var7 = (Float)var1[3];
        float var6 = (Float)var1[4];
        float var8 = (Float)var1[5];
        float var5 = (Float)var1[6];
        int var11 = (Integer)var1[7];
        Vector4f var2 = (Vector4f)var1[8];
        long var9 = (Long)var1[9];
        var9 = 122901707857854L ^ var9;
        long var13 = var9 ^ 34638110780916L;
        long var15 = var9 ^ 108962103099656L;
        long var17 = var9 ^ 131252492588175L;
        DrawMode var19 = this.a(new Object[]{var2, var17});
        RenderState.a_RenderHelper30(new Object[]{var13})
            .b(
                new Object[]{
                    new RenderHelper13(
                        var19,
                        (lpL142_0, lpL142_1) -> {
                            Object[] var10003 = new Object[]{null, 91022737183023L};
                            var10003[0] = var11;
                            int var24 = RenderState.b_int_(var10003);
                            WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL142_1).k(new Object[]{111161505170643L});
                            float var33 = var6 + var5;
                            float var10004 = ((java.lang.Float)lpL142_0);
                            Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var33;
                            var10007[1] = var7;
                            var10007[0] = var4;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var8, var5}, 118034685045795L});
                            Object[] var35 = new Object[]{null, 106573078251021L};
                            var35[0] = var24;
                            var10000 = var10000.e(var35);
                            float var10002 = var7 + var8;
                            float var34 = var6 + var5;
                            var10004 = ((java.lang.Float)lpL142_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var34;
                            var10007[1] = var10002;
                            var10007[0] = var4;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var8, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var8, var5}, 118034685045795L});
                            Object[] var37 = new Object[]{null, 106573078251021L};
                            var37[0] = var24;
                            var10000 = var10000.e(var37);
                            var10002 = var7 + var8;
                            var10004 = ((java.lang.Float)lpL142_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var6;
                            var10007[1] = var10002;
                            var10007[0] = var4;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var8, var5}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var8, var5}, 118034685045795L});
                            Object[] var39 = new Object[]{null, 106573078251021L};
                            var39[0] = var24;
                            var10000 = var10000.e(var39);
                            var10004 = ((java.lang.Float)lpL142_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var6;
                            var10007[1] = var7;
                            var10007[0] = var4;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, var5}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var8, var5}, 118034685045795L});
                            Object[] var41 = new Object[]{null, 106573078251021L};
                            var41[0] = var24;
                            var10000.e(var41).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}).h(new Object[]{7414847813752L});
                        }
                    ),
                    var15
                }
            );
    }

    public DrawMode a(Object[] var1) {
        Vector4f var2 = (Vector4f)var1[0];
        long var3 = (Long)var1[1];
        var3 = 122901707857854L ^ var3;
        long var5 = var3 ^ 80943213351674L;
        long var7 = var3 ^ 34000696763308L;
        Vector4f var10 = MathUtil4.a_Vector4f(new Object[]{var2, var5});
        Event.b();
        DrawMode var11 = (DrawMode)b.get(var10);
        if (var11 == null) {
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
            var10005[5] = Uniform.a_Uniform(new Object[]{(Consumer<RenderPass>)lpL141_0 -> GL20.glUniform4f(this.c, var10.x, var10.y, var10.z, var10.w)});
            var11 = new DrawMode(var10002, 4, false, var10005, var7);
            b.put(var10, var11);
        }

        return var11;
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
