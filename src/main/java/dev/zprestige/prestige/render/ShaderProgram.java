package dev.zprestige.prestige.render;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
    public static final boolean a = !ShaderProgram.class.desiredAssertionStatus();

    public static Shader a(Object[] var0) {
        String var1 = (String)var0[0];
        String var3 = (String)var0[1];
        boolean var2 = (Boolean)var0[2];
        long var4 = (Long)var0[3];
        var4 = 46786204162257L ^ var4;
        long var6 = var4 ^ 134337004921602L;
        long var8 = var4 ^ 86108710405506L;
        Object[] var10005 = new Object[]{null, null, null, var6};
        var10005[2] = var2;
        var10005[1] = var1;
        var10005[0] = 35633;
        int var10 = a_int____________________________(var10005);
        var10005 = new Object[]{null, null, null, var6};
        var10005[2] = var2;
        var10005[1] = var3;
        var10005[0] = 35632;
        int var11 = a_int____________________________(var10005);
        int var12 = GL20.glCreateProgram();
        GL20.glAttachShader(var12, var10);
        GL20.glAttachShader(var12, var11);
        GL20.glLinkProgram(var12);
        if (GL20.glGetProgrami(var12, 35714) == 0) {
            String var13 = GL20.glGetProgramInfoLog(var12);
            System.err.println(var13);
            throw new RuntimeException("Program link failed. See error above");
        } else {
            GL20.glDeleteShader(var10);
            GL20.glDeleteShader(var11);
            return new Shader(var12, var8);
        }
    }

    private static int a_int____________________________(Object[] var0) {
        int var1 = (Integer)var0[0];
        String var5 = (String)var0[1];
        boolean var4 = (Boolean)var0[2];
        if (var4) {
            throw new UnsupportedOperationException("SPIR-V shader loading was removed for GL 3.3 compatibility. Provide GLSL source instead: " + var5);
        } else {
            int var6 = GL20.glCreateShader(var1);

            String var7;
            try (InputStream var8 = ShaderProgram.class.getClassLoader().getResourceAsStream(var5)) {
                if (!a && var8 == null) {
                    throw new AssertionError();
                }

                var7 = new String(var8.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException var13) {
                throw new RuntimeException(var13);
            }

            GL20.glShaderSource(var6, var7);
            GL20.glCompileShader(var6);
            if (GL20.glGetShaderi(var6, 35713) == 0) {
                String var14 = GL20.glGetShaderInfoLog(var6);
                System.err.println(var14);
                throw new RuntimeException("Shader compile failed. See logs above for error");
            } else {
                return var6;
            }
        }
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
