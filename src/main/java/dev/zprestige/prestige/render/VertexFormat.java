package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.BufferQuadIntData;
import dev.zprestige.prestige.data.IntBoolData;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class VertexFormat {
    public final IntBoolData[] a;
    public final int b;
    private final int[] c;
    private static final String e = "Invalid element index: ";

    public VertexFormat(IntBoolData[] var1, long var2) {
        this.a = var1;
        this.c = new int[var1.length];
        if (0 < var1.length) {
            int var4 = 0;
            for (int var5 = 0; var5 < var1.length; var5++) {
                this.c[var5] = var4;
                var4 += var1[var5].cg() * var1[var5].ci();
            }

            this.b = var4;
        } else {
            this.b = 0;
        }
    }

    public BufferQuadIntData a(Object[] var1) {
        GpuBuffer var2 = (GpuBuffer)var1[0];
        int var5 = (Integer)var1[1];
        if (var5 >= 0 && var5 < this.a.length) {
            IntBoolData var6 = this.a[var5];
            int var7 = this.c[var5];
            return new BufferQuadIntData(var2, var6, this.b, var7);
        } else {
            throw new IllegalArgumentException(e + var5);
        }
    }

    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (var1 != null && this.getClass() == var1.getClass()) {
            VertexFormat var5 = (VertexFormat)var1;
            return this.b == var5.b && Arrays.equals((Object[])this.a, (Object[])var5.a) && Arrays.equals(this.c, var5.c);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int var3 = Objects.hash(this.b);
        var3 = 31 * var3 + Arrays.hashCode((Object[])this.a);
        return 31 * var3 + Arrays.hashCode(this.c);
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

    private static IllegalArgumentException a(IllegalArgumentException var0) {
        return var0;
    }
}
