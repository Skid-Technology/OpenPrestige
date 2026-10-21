package dev.zprestige.prestige.render;

import java.lang.ref.Cleaner.Cleanable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public final class Shader implements AutoCloseable {
    private final int a;
    private final Cleanable b;
    private final int c;
    private final int d;

    public Shader(int var1, long var2) {
        this.a = var1;
        this.b = RenderUtil.a.register(this, () -> RenderUtil.f(new Object[]{(Runnable)() -> GL20.glDeleteProgram(var1), 31795785076293L}));
        this.c = GL20.glGetUniformLocation(this.a(new Object[0]), "projMat");
        this.d = GL20.glGetUniformLocation(this.a(new Object[0]), "viewMat");
    }

    public int a(Object[] var1) {
        return this.a;
    }

    @Override
    public void close() {
        this.b.clean();
    }

    public int b(Object[] var1) {
        return this.c;
    }

    public int c(Object[] var1) {
        return this.d;
    }

    public int d(Object[] var1) {
        String var2 = (String)var1[0];
        return GL20.glGetUniformLocation(this.a, var2);
    }

    public void a_void_____________________________(Object[] var1) {
        String var4 = (String)var1[0];
        int var5 = (Integer)var1[1];
        long var2 = (Long)var1[2];
        var2 = 87056892131211L ^ var2;
        long var6 = var2 ^ 76752568669089L;
        int var8 = GL11.glGetInteger(35725);
        GL20.glUseProgram(this.a);
        GL20.glUniform1i(this.d(new Object[]{var4, var6}), var5);
        GL20.glUseProgram(var8);
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
