package dev.zprestige.prestige.data;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL20;

public record IntData(int bz) {
    private static final Map a = new HashMap();

    public static IntData a(int var0) {
        return new IntData(var0);
    }

    private int a(String var1) {
        return (Integer)((Map)a.computeIfAbsent(this.bz, lpL401_0 -> new HashMap<>())).computeIfAbsent(var1, lpL400_0 -> GL20.glGetUniformLocation(this.bz, ((java.lang.String)lpL400_0)));
    }

    public IntData a(String var1, int var2) {
        GL20.glUniform1i(this.a(var1), var2);
        return this;
    }

    public IntData a(String var1, int var2, int var3) {
        GL20.glUniform2i(this.a(var1), var2, var3);
        return this;
    }

    public IntData a(String var1, int var2, int var3, int var4) {
        GL20.glUniform3i(this.a(var1), var2, var3, var4);
        return this;
    }

    public IntData a(String var1, int var2, int var3, int var4, int var5) {
        GL20.glUniform4i(this.a(var1), var2, var3, var4, var5);
        return this;
    }

    public IntData a(String var1, float var2) {
        GL20.glUniform1f(this.a(var1), var2);
        return this;
    }

    public IntData a(String var1, float var2, float var3) {
        GL20.glUniform2f(this.a(var1), var2, var3);
        return this;
    }

    public IntData a(String var1, float var2, float var3, float var4) {
        GL20.glUniform3f(this.a(var1), var2, var3, var4);
        return this;
    }

    public IntData a(String var1, float var2, float var3, float var4, float var5) {
        GL20.glUniform4f(this.a(var1), var2, var3, var4, var5);
        return this;
    }

    public IntData a(String var1, boolean var2, float[] var3) {
        GL20.glUniformMatrix4fv(this.a(var1), var2, var3);
        return this;
    }

    public IntData b(String var1, boolean var2, float[] var3) {
        GL20.glUniformMatrix3fv(this.a(var1), var2, var3);
        return this;
    }

    public IntData c(String var1, boolean var2, float[] var3) {
        GL20.glUniformMatrix2fv(this.a(var1), var2, var3);
        return this;
    }

    public IntData a(String var1, Color var2) {
        GL20.glUniform4f(this.a(var1), var2.getRed() / 255.0F, var2.getGreen() / 255.0F, var2.getBlue() / 255.0F, var2.getAlpha() / 255.0F);
        return this;
    }

    public IntData b(String var1, float var2, float var3, float var4, float var5) {
        GL20.glUniform4f(this.a(var1), var2, var3, var4, var5);
        return this;
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
