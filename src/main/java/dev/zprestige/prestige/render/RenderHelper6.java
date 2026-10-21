package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.VectorData;
import dev.zprestige.prestige.world.WorldContract;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.joml.Matrix4f;

public final class RenderHelper6 implements RenderContract4 {
    private final VectorData a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final float f;
    private final float g;
    private final float h;
    private final float i;
    private final int j;
    private final Matrix4f k;

    public RenderHelper6(
        VectorData var1, float var2, float var3, float var4, float var5, int var6, float var7, float var8, float var9, float var10, Matrix4f var11, long var12
    ) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
        this.f = var7;
        this.g = var8;
        this.h = var9;
        this.i = var10;
        this.k = var11;
        int var14 = var6 >>> 24 & 0xFF;
        int var15 = var6 >>> 16 & 0xFF;
        int var16 = var6 >>> 8 & 0xFF;
        int var17 = var6 & 0xFF;
        this.j = var14 << 24 | var17 << 16 | var16 << 8 | var15;
    }

    @Override
    public void a(Object[] var1) {
        WorldContract var2 = (WorldContract)var1[0];
        float var5 = (Float)var1[1];
        long var3 = (Long)var1[2];
        long var6 = var3 ^ 24167567963691L;
        long var8 = var3 ^ 82935697402127L;
        long var10 = var3 ^ 130143315315328L;
        long var12 = var3 ^ 39310700723775L;
        long var14 = var3 ^ 17653510059253L;
        long var16 = var3 ^ 29374964166188L;
        long var18 = var3 ^ 30482090942171L;
        var2.k(new Object[]{var6});
        if (this.k == null) {
            float var10001 = this.b;
            float var10002 = this.e;
            Object[] var10006 = new Object[]{null, null, null, var8};
            var10006[2] = var5;
            var10006[1] = var10002;
            var10006[0] = var10001;
            WorldContract var10000 = var2.c_WorldContract(var10006);
            int var33 = this.j;
            Object[] var10004 = new Object[]{null, var14};
            var10004[0] = var33;
            var10000 = var10000.e(var10004).b_WorldContract(new Object[]{new float[]{this.f, this.i}, var18});
            var10001 = this.b;
            var10002 = this.c;
            var10006 = new Object[]{null, null, null, var8};
            var10006[2] = var5;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var10000 = var10000.c_WorldContract(var10006);
            int var35 = this.j;
            var10004 = new Object[]{null, var14};
            var10004[0] = var35;
            var10000 = var10000.e(var10004).b_WorldContract(new Object[]{new float[]{this.f, this.g}, var18});
            var10001 = this.d;
            var10002 = this.c;
            var10006 = new Object[]{null, null, null, var8};
            var10006[2] = var5;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var10000 = var10000.c_WorldContract(var10006);
            int var37 = this.j;
            var10004 = new Object[]{null, var14};
            var10004[0] = var37;
            var10000 = var10000.e(var10004).b_WorldContract(new Object[]{new float[]{this.h, this.g}, var18});
            var10001 = this.d;
            var10002 = this.e;
            var10006 = new Object[]{null, null, null, var8};
            var10006[2] = var5;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var10000 = var10000.c_WorldContract(var10006);
            int var39 = this.j;
            var10004 = new Object[]{null, var14};
            var10004[0] = var39;
            var10000.e(var10004).b_WorldContract(new Object[]{new float[]{this.h, this.i}, var18});
        } else {
            Matrix4f var40 = this.k;
            float var51 = this.b;
            float var10003 = this.e;
            Object[] var10007 = new Object[]{null, null, null, null, var16};
            var10007[3] = var5;
            var10007[2] = var10003;
            var10007[1] = var51;
            var10007[0] = var40;
            WorldContract var26 = var2.d(var10007);
            int var41 = this.j;
            Object[] var61 = new Object[]{null, var14};
            var61[0] = var41;
            var26 = var26.e(var61).b_WorldContract(new Object[]{new float[]{this.f, this.i}, var18});
            var40 = this.k;
            var51 = this.b;
            var10003 = this.c;
            var10007 = new Object[]{null, null, null, null, var16};
            var10007[3] = var5;
            var10007[2] = var10003;
            var10007[1] = var51;
            var10007[0] = var40;
            var26 = var26.d(var10007);
            int var43 = this.j;
            var61 = new Object[]{null, var14};
            var61[0] = var43;
            var26 = var26.e(var61).b_WorldContract(new Object[]{new float[]{this.f, this.g}, var18});
            var40 = this.k;
            var51 = this.d;
            var10003 = this.c;
            var10007 = new Object[]{null, null, null, null, var16};
            var10007[3] = var5;
            var10007[2] = var10003;
            var10007[1] = var51;
            var10007[0] = var40;
            var26 = var26.d(var10007);
            int var45 = this.j;
            var61 = new Object[]{null, var14};
            var61[0] = var45;
            var26 = var26.e(var61).b_WorldContract(new Object[]{new float[]{this.h, this.g}, var18});
            var40 = this.k;
            var51 = this.d;
            var10003 = this.e;
            var10007 = new Object[]{null, null, null, null, var16};
            var10007[3] = var5;
            var10007[2] = var10003;
            var10007[1] = var51;
            var10007[0] = var40;
            var26 = var26.d(var10007);
            int var47 = this.j;
            var61 = new Object[]{null, var14};
            var61[0] = var47;
            var26.e(var61).b_WorldContract(new Object[]{new float[]{this.h, this.i}, var18});
        }

        var2.g(new Object[]{new int[]{0, 1, 2, 2, 3, 0}, var12}).h(new Object[]{var10});
    }

    @Override
    public VectorData a_VectorData_(Object[] var1) {
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
