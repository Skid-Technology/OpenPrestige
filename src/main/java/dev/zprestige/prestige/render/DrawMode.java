package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.VertexIntData;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;

public class DrawMode {
    public VertexFormat a;
    public int b;
    public boolean c;
    public Uniform[] d;
    public static final boolean e = !DrawMode.class.desiredAssertionStatus();

    public DrawMode(VertexFormat var1, int var2, boolean var3, Uniform[] var4, long var5) {
        super();
        var5 = 0L;
        var5 = 99824927598897L ^ var5;
        long var7 = var5 ^ 106708735179943L;
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        if (!e && !a_boolean_____________________________________(new Object[]{var4, var7})) {
            throw new AssertionError("duplicate states that shouldnt be duplicated!");
        }
    }

    public int a(Object[] var1) {
        return this.b;
    }

    private static boolean a_boolean_____________________________________(Object[] var0) {
        Uniform[] var1 = (Uniform[])var0[0];
        HashSet var4 = new HashSet(var1.length);

        for (Uniform var8 : var1) {
            if (!var8.a_boolean() && !var4.add(var8.getClass())) {
                return false;
            }
        }

        return true;
    }

    public void a_void________________(Object[] var1) {
        MatrixCameraData var6 = (MatrixCameraData)var1[0];
        RenderHelper31 var3 = (RenderHelper31)var1[1];
        GpuBuffer var7 = (GpuBuffer)var1[2];
        VertexIntData var2 = (VertexIntData)var1[3];
        long var4 = (Long)var1[4];
        var4 = 99824927598897L ^ var4;
        long var8 = var4 ^ 92214760685375L;
        long var10 = var4 ^ 28523593297410L;
        long var12 = var4 ^ 25551188328945L;
        long var14 = var4 ^ 64111063703542L;
        long var16 = var4 ^ 102301186008704L;
        if (!e && !var2.bf().equals(this.a)) {
            throw new AssertionError("format mismatch!");
        } else {
            DrawContext var19 = RenderPass.a(new Object[]{var8});

            for (Uniform var23 : this.d) {
                var23.a(var19);
            }

            RenderPass var26 = var19.a_RenderPass(new Object[]{var14});
            var26.a_void__________________________(new Object[]{var12});
            var26.b(new Object[]{var6, var16});

            for (Uniform var24 : this.d) {
                var24.a(var26);
            }

            int var10003 = this.b;
            int var10004 = var2.bh();
            Object[] var10007 = new Object[]{null, null, null, null, var10};
            var10007[3] = var10004;
            var10007[2] = var10003;
            var10007[1] = var7;
            var10007[0] = var3;
            var26.d(var10007);

            for (Uniform var33 : this.d) {
                var33.a();
            }
        }
    }

    public boolean b(Object[] var1) {
        return this.c;
    }

    public VertexFormat a_VertexFormat(Object[] var1) {
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
