package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.IntData;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class ChamsOutlineShader extends PostShader {
    private final Color a;

    public ChamsOutlineShader(long var1) {
        super("shaders/post.vert", "shaders/chams_outline.frag", (14248148781822L));
        var1 = 0L;
        var1 = 57478881253633L ^ var1;
        long var3 = var1 ^ 62344664470527L;
        this.a = Color.WHITE;
    }

    @Override
    public TextRenderer b(Object[] var1) {
        RenderHelper8 var2 = (RenderHelper8)var1[0];
        int var3 = (Integer)var1[1];
        long var4 = (Long)var1[2];
        long var6 = var4 ^ 122531800221372L;
        return var2.a(
            new Object[]{
                this.j
                    .computeIfAbsent(
                        var3,
                        lpL131_0 -> {
                            VertexFormat var10002 = VertexAttribute.c;
                            Uniform[] var10005 = new Uniform[]{
                                Uniform.a_IntSupplierData(new Object[]{this.g.a}), Uniform.a_ShaderData(new Object[]{RenderHelper22.b}), null, null, null, null
                            };
                            Object[] var10012 = new Object[]{null, null, true};
                            var10012[1] = false;
                            var10012[0] = 2884;
                            var10005[2] = Uniform.b_IntBoolData2(var10012);
                            Object[] var10011 = new Object[]{null, true};
                            var10011[0] = true;
                            var10005[3] = Uniform.a_BoolData2(var10011);
                            var10011 = new Object[]{null, 515};
                            var10011[0] = 519;
                            var10005[4] = Uniform.a_IntData2(var10011);
                            var10005[5] = Uniform.a_Uniform(new Object[]{(Consumer<RenderPass>)lpL132_0 -> {
                                GL13.glActiveTexture(33984);
                                GL11.glBindTexture(3553, var3);
                            }});
                            return new DrawMode(var10002, 4, true, var10005, 138006566364233L);
                        }
                    ),
                var6
            }
        );
    }

    @Override
    public void c(Object[] var1) {
        IntData var2 = (IntData)var1[0];
        var2.a("color", this.a.getRed() / 255.0F, this.a.getGreen() / 255.0F, this.a.getBlue() / 255.0F, this.a.getAlpha() / 255.0F)
            .a("opacity", 0.5F)
            .a("rimWidth", 2.0F);
    }

    @Override
    public VertexBuffer a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 98867838856239L;
        return new VertexBuffer().b(new Object[]{this.a_Shader(new Object[0]), (Consumer<RenderPass>)lpL130_0 -> {
            GL13.glActiveTexture(33984);
            GL11.glBindTexture(3553, this.h.a);
            this.e(new Object[]{30083616553634L});
            IntData.a(this.a_Shader(new Object[0]).a(new Object[0])).a("oneTexel", 1.0F / this.d, 1.0F / this.e);
        }, (java.util.function.IntSupplier)RenderUtil::a, var4});
    }

    static {
        Shader var10000 = RenderHelper22.b;
        Object[] var10005 = new Object[]{null, null, 0L};
        var10005[1] = 0;
        var10005[0] = "texSampler";
        var10000.a_void_____________________________(var10005);
    }

    private static Field e(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field f(Class var0, String var1, Class var2) {
        Field var3 = e(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = f(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method e(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method f(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = e(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = f(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
