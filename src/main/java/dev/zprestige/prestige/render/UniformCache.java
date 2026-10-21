package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.BoolData2;
import dev.zprestige.prestige.data.IntBoolData2;
import dev.zprestige.prestige.data.IntData2;
import dev.zprestige.prestige.data.ShaderData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.math.MathUtil4;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import org.joml.Vector4f;

public class UniformCache {
    public static final DrawMode a = new DrawMode(
        VertexAttribute.b,
        4,
        false,
        new Uniform[]{Uniform.a, new ShaderData(RenderHelper22.a), new IntBoolData2(2884, false), new BoolData2(true, true), new IntData2(515, 515)},
        0L
    );
    public static final DrawMode b = new DrawMode(
        VertexAttribute.b,
        4,
        false,
        new Uniform[]{
            Uniform.a,
            new ShaderData(RenderHelper22.a),
            new IntBoolData2(2884, false),
            new BoolData2(true, true),
            new IntData2(515, 515),
            new IntBoolData2(2929, false)
        },
        0L
    );
    public static final DrawMode c = new DrawMode(
        VertexAttribute.b,
        4,
        false,
        new Uniform[]{
            Uniform.a,
            new ShaderData(RenderHelper22.a),
            new IntBoolData2(2884, true),
            new BoolData2(true, true),
            new IntData2(515, 515),
            new IntBoolData2(2929, true)
        },
        0L
    );
    public static final DrawMode d = new DrawMode(
        VertexAttribute.b,
        1,
        false,
        new Uniform[]{Uniform.a, new ShaderData(RenderHelper22.a), new IntBoolData2(2884, true), new BoolData2(true, true), new IntData2(515, 515)},
        0L
    );
    public static final DrawMode e = new DrawMode(
        VertexAttribute.b,
        1,
        false,
        new Uniform[]{
            Uniform.a,
            new ShaderData(RenderHelper22.a),
            new IntBoolData2(2884, true),
            new BoolData2(true, true),
            new IntData2(515, 515),
            new IntBoolData2(2929, false)
        },
        0L
    );
    private static final Map f;
    static final int g;

    public static DrawMode a(Object[] var0) {
        Vector4f var1 = (Vector4f)var0[0];
        long var2 = (Long)var0[1];
        var2 = 35742708978647L ^ var2;
        long var4 = var2 ^ 21954929016625L;
        long var6 = var2 ^ 5585743699746L;
        Event.b();
        ColorValue var9 = new ColorValue(var1, var6);
        DrawMode var10 = (DrawMode)f.get(var9);
        if (var10 == null) {
            var10 = new DrawMode(
                VertexAttribute.e,
                4,
                false,
                new Uniform[]{
                    Uniform.a,
                    new ShaderData(RenderHelper22.c),
                    new IntBoolData2(2884, true),
                    new BoolData2(true, true),
                    new IntData2(515, 515),
                    new IntBoolData2(2929, false),
                    new ColorUniform(var9)
                },
                var4
            );
            f.put(var9, var10);
        }

        return var10;
    }

    static {
        Object var11 = "roundness";
        Object[] var10003 = new Object[]{null, 0L};
        var10003[0] = 128;
        f = MathUtil4.a(var10003);
        g = RenderHelper22.c.d(new Object[]{var11, 0L});
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
