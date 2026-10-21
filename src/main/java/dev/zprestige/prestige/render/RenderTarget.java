package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.math.MathUtil4;
import dev.zprestige.prestige.type.GlPrimitive;
import dev.zprestige.prestige.world.WorldContract;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Consumer;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

public class RenderTarget extends ShaderBase {
    private static final RenderTarget a = new RenderTarget(0L);
    private static final Map b;
    private static final Map c;
    private final int d;
    private final int e;

    private RenderTarget(long var1) {
        super("shaders/rounded_texture.vert", "shaders/rounded_texture_blurred.frag", (16076376986889L));
        var1 = 0L;
        var1 = 82121221110725L ^ var1;
        long var3 = var1 ^ 34477175487750L;
        long var5 = var1 ^ 74969804123852L;
        long var7 = var1 ^ 23882391755052L;
        this.d = this.a_Shader(new Object[0]).d(new Object[]{"roundness", var3});
        this.e = this.a_Shader(new Object[0]).d(new Object[]{"invBlurTexSize", var3});
        Shader var10000 = this.a_Shader(new Object[0]);
        Object[] var10005 = new Object[]{null, null, var7};
        var10005[1] = 0;
        var10005[0] = "texSampler";
        var10000.a_void_____________________________(var10005);
    }

    public static void d(Object[] var0) {
        RenderHelper8 var10 = (RenderHelper8)var0[0];
        MatrixCameraData var2 = (MatrixCameraData)var0[1];
        Matrix4f var5 = (Matrix4f)var0[2];
        float var4 = (Float)var0[3];
        float var7 = (Float)var0[4];
        float var11 = (Float)var0[5];
        float var3 = (Float)var0[6];
        int var6 = (Integer)var0[7];
        Vector4f var1 = (Vector4f)var0[8];
        long var8 = (Long)var0[9];
        var8 = 82121221110725L ^ var8;
        long var12 = var8 ^ 57232308482566L;
        RenderHelper7 var14 = (RenderHelper7)b.computeIfAbsent(var6, lpL124_0 -> new RenderHelper7(var6, var6 * 2, 80247927805987L));
        RenderTarget var10000 = a;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, var1, var12};
        var10012[7] = var3;
        var10012[6] = var11;
        var10012[5] = var7;
        var10012[4] = var4;
        var10012[3] = var14;
        var10012[2] = var5;
        var10012[1] = var2;
        var10012[0] = var10;
        var10000.g(var10012);
    }

    public static void e(Object[] var0) {
        RenderHelper8 var8 = (RenderHelper8)var0[0];
        MatrixCameraData var5 = (MatrixCameraData)var0[1];
        Matrix4f var11 = (Matrix4f)var0[2];
        float var1 = (Float)var0[3];
        float var7 = (Float)var0[4];
        float var9 = (Float)var0[5];
        float var2 = (Float)var0[6];
        int var6 = (Integer)var0[7];
        float var10 = (Float)var0[8];
        long var3 = (Long)var0[9];
        var3 = 82121221110725L ^ var3;
        long var12 = var3 ^ 105119791449421L;
        RenderHelper7 var14 = (RenderHelper7)b.computeIfAbsent(var6, lpL123_0 -> new RenderHelper7(var6, var6 * 2, 80247927805987L));
        RenderTarget var10000 = a;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, new Vector4f(var10), var12};
        var10012[7] = var2;
        var10012[6] = var9;
        var10012[5] = var7;
        var10012[4] = var1;
        var10012[3] = var14;
        var10012[2] = var11;
        var10012[1] = var5;
        var10012[0] = var8;
        var10000.g(var10012);
    }

    private void g(Object[] var1) {
        Matrix4f var6 = (Matrix4f)var1[2];
        RenderHelper7 var9 = (RenderHelper7)var1[3];
        float var8 = (Float)var1[4];
        float var4 = (Float)var1[5];
        float var12 = (Float)var1[6];
        float var10 = (Float)var1[7];
        Vector4f var5 = (Vector4f)var1[8];
        long var2 = (Long)var1[9];
        var2 = 82121221110725L ^ var2;
        long var13 = var2 ^ 30505240229135L;
        long var15 = var2 ^ 113421114350067L;
        long var17 = var2 ^ 52783725153125L;
        DrawMode var19 = this.a(new Object[]{var9, var5, var17});
        RenderState.a_RenderHelper30(new Object[]{var13})
            .b(
                new Object[]{
                    new RenderHelper13(
                        var19,
                        (lpL122_0, lpL122_1) -> {
                            WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL122_1).k(new Object[]{111161505170643L});
                            float var10003 = var4 + var10;
                            float var10004 = ((java.lang.Float)lpL122_0);
                            Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var10003;
                            var10007[1] = var8;
                            var10007[0] = var6;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var12, var10}, 118034685045795L});
                            float var10002 = var8 + var12;
                            var10003 = var4 + var10;
                            var10004 = ((java.lang.Float)lpL122_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var10003;
                            var10007[1] = var10002;
                            var10007[0] = var6;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var12, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var12, var10}, 118034685045795L});
                            var10002 = var8 + var12;
                            var10004 = ((java.lang.Float)lpL122_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var4;
                            var10007[1] = var10002;
                            var10007[0] = var6;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var12, var10}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var12, var10}, 118034685045795L});
                            var10004 = ((java.lang.Float)lpL122_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var4;
                            var10007[1] = var8;
                            var10007[0] = var6;
                            var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, var10}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var12, var10}, 118034685045795L})
                                .i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L})
                                .h(new Object[]{7414847813752L});
                        }
                    ),
                    var15
                }
            );
    }

    private DrawMode a(Object[] var1) {
        RenderHelper7 var4 = (RenderHelper7)var1[0];
        Vector4f var5 = (Vector4f)var1[1];
        long var2 = (Long)var1[2];
        var2 = 82121221110725L ^ var2;
        long var6 = var2 ^ 52267581780368L;
        long var8 = var2 ^ 133019812511942L;
        Vector4f var11 = MathUtil4.a_Vector4f(new Object[]{var5, var6});
        Map var12 = (Map)c.computeIfAbsent(var4, lpL121_0 -> {
            Object[] var10003 = new Object[]{null, 61743098197066L};
            var10003[0] = 128;
            return MathUtil4.a(var10003);
        });
        Event.b();
        DrawMode var13 = (DrawMode)var12.get(var11);
        if (var13 == null) {
            VertexFormat var10002 = VertexAttribute.g;
            Uniform[] var10005 = new Uniform[]{Uniform.b_Uniform(new Object[]{(Consumer<DrawContext>)lpL120_0 -> {
                long var10001 = RenderUtil.a_long__(new Object[0]);
                Object[] var10004 = new Object[]{null, 632507136497L};
                var10004[0] = var10001;
                var4.b_void_________(var10004);
            }}), Uniform.a, Uniform.a_ShaderData(new Object[]{this.a_Shader(new Object[0])}), null, null, null, null};
            Object[] var10011 = new Object[]{null, true};
            var10011[0] = 2884;
            var10005[3] = Uniform.a_IntBoolData2(var10011);
            var10011 = new Object[]{null, true};
            var10011[0] = true;
            var10005[4] = Uniform.a_BoolData2(var10011);
            var10011 = new Object[]{null, 515};
            var10011[0] = 515;
            var10005[5] = Uniform.a_IntData2(var10011);
            var10005[6] = Uniform.a_Uniform(new Object[]{(Consumer<RenderPass>)lpL119_0 -> {
                GL13.glActiveTexture(33984);
                GL11.glBindTexture(3553, var4.c.a);
                GL20.glUniform2f(this.e, 1.0F / var4.a(new Object[0]), 1.0F / var4.b(new Object[0]));
                GL20.glUniform4f(this.d, var11.x, var11.y, var11.z, var11.w);
            }});
            var13 = new DrawMode(var10002, 4, false, var10005, var8);
            var12.put(var11, var13);
        }

        return var13;
    }

    static {
        Object[] var10003 = new Object[]{null, 0L};
        var10003[0] = 4;
        b = MathUtil4.a(var10003);
        var10003 = new Object[]{null, 0L};
        var10003[0] = 4;
        c = MathUtil4.a(var10003);
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
