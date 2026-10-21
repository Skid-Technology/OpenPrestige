package dev.zprestige.prestige.world;

import dev.zprestige.prestige.render.GlPrimitiveTable;
import dev.zprestige.prestige.type.GlPrimitive;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface WorldContract {
    String k = "generateIndices called with no offset being present and indices in this buffer : don't know where to begin generating..?";

    WorldContract a_WorldContract(Object[] var1);

    WorldContract b_WorldContract(Object[] var1);

    WorldContract c_WorldContract(Object[] var1);

    WorldContract d(Object[] var1);

    WorldContract e(Object[] var1);

    WorldContract f(Object[] var1);

    WorldContract g(Object[] var1);

    int a_int_____________________________(Object[] var1);

    int b_int__(Object[] var1);

    WorldContract h(Object[] var1);

    void a(Object[] var1);

    void b(Object[] var1);

    int c(Object[] var1);

    default WorldContract i_WorldContract(Object[] var1) {
        GlPrimitive var2 = (GlPrimitive)var1[0];
        long var3 = (Long)var1[1];
        var3 = 52947842122458L ^ var3;
        long var5 = var3 ^ 105131950759054L;
        long var7 = var3 ^ 107567671243298L;
        long var9 = var3 ^ 115719617908232L;
        long var11 = var3 ^ 112736010691983L;
        if (!GlPrimitiveTable.a && this.c(new Object[0]) == 0 && this.b_int__(new Object[]{var11}) != 0) {
            throw new AssertionError(k);
        } else {
            int var13 = this.a_int_____________________________(new Object[]{var9}) - this.c(new Object[0]);
            switch (GlPrimitiveTable.b[var2.ordinal()]) {
                case 1:
                    int var33 = var13 / 3 * 3 * 4;
                    Object[] var38 = new Object[]{null, var5};
                    var38[0] = var33;
                    this.a(var38);

                    for (int var23 = 0; var23 < var13; var23 += 3) {
                        this.g(new Object[]{new int[]{var23, var23 + 1, var23 + 2}, var7});
                    }

                    boolean var28 = false;
                    break;
                case 2:
                    int var32 = var13 / 4 * 6 * 4;
                    Object[] var37 = new Object[]{null, var5};
                    var37[0] = var32;
                    this.a(var37);

                    for (int var22 = 0; var22 < var13; var22 += 4) {
                        this.g(new Object[]{new int[]{var22, var22 + 1, var22 + 2, var22 + 2, var22 + 3, var22}, var7});
                    }

                    boolean var27 = false;
                    break;
                case 3:
                    int var31 = (var13 - 2) * 3 * 4;
                    Object[] var36 = new Object[]{null, var5};
                    var36[0] = var31;
                    this.a(var36);

                    for (int var21 = 1; var21 < var13 - 1; var21++) {
                        this.g(new Object[]{new int[]{0, var21, var21 + 1}, var7});
                    }

                    boolean var26 = false;
                    break;
                case 4:
                    int var30 = (var13 - 1) * 2 * 4;
                    Object[] var35 = new Object[]{null, var5};
                    var35[0] = var30;
                    this.a(var35);

                    for (int var20 = 0; var20 < var13 - 1; var20++) {
                        this.g(new Object[]{new int[]{var20, var20 + 1}, var7});
                    }

                    boolean var25 = false;
                    break;
                case 5:
                    int var19 = var13 / 2 - 1;
                    int var29 = var19 * 6 * 4;
                    Object[] var34 = new Object[]{null, var5};
                    var34[0] = var29;
                    this.a(var34);

                    for (int var16 = 0; var16 < var19; var16++) {
                        int var17 = var16 * 2;
                        this.g(new Object[]{new int[]{var17, var17 + 1, var17 + 3, var17 + 3, var17 + 2, var17}, var7});
                    }

                    boolean var24 = false;
                    break;
                case 6:
                    int var10001 = var13 / 2 * 2 * 4;
                    Object[] var10004 = new Object[]{null, var5};
                    var10004[0] = var10001;
                    this.a(var10004);

                    for (int var15 = 0; var15 < var13; var15 += 2) {
                        this.g(new Object[]{new int[]{var15, var15 + 1}, var7});
                    }

                    boolean var10000 = false;
                    break;
                default:
                    throw new MatchException(null, null);
            }

            return this;
        }
    }

    WorldContract j(Object[] var1);

    default WorldContract k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 52947842122458L ^ var2;
        long var4 = var2 ^ 16456975169252L;
        long var6 = var2 ^ 132732316017325L;
        int var10001 = this.a_int_____________________________(new Object[]{var4});
        Object[] var10004 = new Object[]{null, var6};
        var10004[0] = var10001;
        return this.j(var10004);
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
