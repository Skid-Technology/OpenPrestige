package dev.zprestige.prestige.render;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RenderHelper22 {
    public static final Shader a;
    public static final Shader b;
    public static final Shader c;
    public static final Shader d;
    public static final Shader e;

    static {
        Object var0 = new String[]{"shaders/post.vert", "shaders/pos_col.frag", "shaders/rounded_pos_color.vert", "shaders/pos_col_uv.vert",
            "shaders/pos_col_uv.vert", "shaders/pos_col.vert", "shaders/rounded_pos_color.frag", "shaders/msdf_text.frag",
            "shaders/pos_col_uv.frag", "shaders/gaussian.frag"};
        Object[] var10005 = new Object[]{null, null, null, 0L};
        var10005[2] = false;
        var10005[1] = "shaders/pos_col.frag";
        var10005[0] = "shaders/pos_col.vert";
        a = ShaderProgram.a(var10005);
        Object var10000 = ((Object[])var0)[4];
        Object var10001 = ((Object[])var0)[8];
        var10005 = new Object[]{null, null, null, 0L};
        var10005[2] = false;
        var10005[1] = var10001;
        var10005[0] = var10000;
        b = ShaderProgram.a(var10005);
        var10000 = ((Object[])var0)[2];
        var10001 = ((Object[])var0)[6];
        var10005 = new Object[]{null, null, null, 0L};
        var10005[2] = false;
        var10005[1] = var10001;
        var10005[0] = var10000;
        c = ShaderProgram.a(var10005);
        var10000 = ((Object[])var0)[0];
        var10001 = ((Object[])var0)[9];
        var10005 = new Object[]{null, null, null, 0L};
        var10005[2] = false;
        var10005[1] = var10001;
        var10005[0] = var10000;
        d = ShaderProgram.a(var10005);
        var10000 = ((Object[])var0)[3];
        var10001 = ((Object[])var0)[7];
        var10005 = new Object[]{null, null, null, 0L};
        var10005[2] = false;
        var10005[1] = var10001;
        var10005[0] = var10000;
        e = ShaderProgram.a(var10005);
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
