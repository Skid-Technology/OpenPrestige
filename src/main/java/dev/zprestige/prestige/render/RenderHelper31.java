package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.BufferQuadIntData;
import dev.zprestige.prestige.data.IntBoolData;
import java.lang.ref.Cleaner.Cleanable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL30;

public final class RenderHelper31 implements AutoCloseable {
    private final int a;
    private final BufferQuadIntData[] b;
    private final Cleanable c;
    private int d;
    public static final boolean e = !RenderHelper31.class.desiredAssertionStatus();
    private static final String g = "too many bindings (";

    private RenderHelper31(int var1, BufferQuadIntData[] var2, long var3) {
        this.a = var1;
        this.b = new BufferQuadIntData[16];
        if (var2 != null) {
            System.arraycopy(var2, 0, this.b, 0, var2.length);
        }

        this.d = var2 == null ? 0 : var2.length;
        this.c = RenderUtil.a.register(this, () -> RenderUtil.f(new Object[]{(Runnable)() -> GL30.glDeleteVertexArrays(var1), 31795785076293L}));
    }

    public static RenderHelper31 a(Object[] var0) {
        GpuBuffer var4 = (GpuBuffer)var0[0];
        VertexFormat var1 = (VertexFormat)var0[1];
        long var2 = (Long)var0[2];
        var2 = 4009735877496L ^ var2;
        long var5 = var2 ^ 135213029856523L;
        long var7 = var2 ^ 17806318104826L;
        return b(new Object[]{a_List_(new Object[]{var4, var1, var5}), var7});
    }

    @NotNull
    public static List a_List_(Object[] var0) {
        GpuBuffer var4 = (GpuBuffer)var0[0];
        VertexFormat var1 = (VertexFormat)var0[1];
        long var2 = (Long)var0[2];
        var2 = 4009735877496L ^ var2;
        long var5 = var2 ^ 127089460246871L;
        ArrayList var7 = new ArrayList(var1.a.length);

        for (int var8 = 0; var8 < var1.a.length; var8++) {
            Object[] var10006 = new Object[]{null, null, var5};
            var10006[1] = var8;
            var10006[0] = var4;
            var7.add(var1.a(var10006));
        }

        return var7;
    }

    public static RenderHelper31 b(Object[] var0) {
        List var3 = (List)var0[0];
        long var1 = (Long)var0[1];
        var1 = 4009735877496L ^ var1;
        long var4 = var1 ^ 57173462433439L;
        long var6 = var1 ^ 16218348393588L;
        int var8 = GL30.glGenVertexArrays();
        RenderHelper31 var9 = new RenderHelper31(var8, null, var4);
        if (var3 != null) {
            var9.a_void______________________(new Object[]{var3, var6});
        }

        return var9;
    }

    @Override
    public void close() {
        this.c.clean();
    }

    public void a_void______________________(Object[] var1) {
        List var4 = (List)var1[0];
        int var5 = var4.size();
        if (!e && var5 > 16) {
            throw new AssertionError(g + var5 + ")");
        } else {
            BufferQuadIntData[] var6 = (BufferQuadIntData[])var4.toArray(new BufferQuadIntData[0]);
            if (this.d != var5 || !Arrays.equals(this.b, 0, this.d, var6, 0, var6.length)) {
                int var7 = Math.max(this.d, var5);
                int var8 = Math.min(this.d, var5);
                GL30.glBindVertexArray(this.a);

                for (int var9 = var8; var9 < var7; var9++) {
                    if (var9 >= this.d) {
                        GL30.glEnableVertexAttribArray(var9);
                    } else {
                        GL30.glDisableVertexAttribArray(var9);
                    }
                }

                for (int var12 = 0; var12 < var5; var12++) {
                    BufferQuadIntData var10 = (BufferQuadIntData)var4.get(var12);
                    GL30.glBindBuffer(34962, var10.cc().a_int_____________________(new Object[0]));
                    IntBoolData var11 = var10.cd();
                    GL30.glVertexAttribPointer(var12, var11.cg(), var11.ch(), var11.cj(), var10.ce(), var10.cf());
                }

                GL30.glBindVertexArray(0);
                Arrays.fill(this.b, null);
                System.arraycopy(var6, 0, this.b, 0, var5);
                this.d = var5;
            }
        }
    }

    public int a_int________________________(Object[] var1) {
        return this.a;
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
