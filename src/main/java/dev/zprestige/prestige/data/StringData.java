package dev.zprestige.prestige.data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public record StringData(Class<?> bi, String bj, Class<?> bk, Class<?>[] bl, Class<?> bm) {
    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (var1 != null && this.getClass() == var1.getClass()) {
            StringData var5 = (StringData)var1;
            if (!this.bi.equals(var5.bi)) {
                return false;
            } else if (!this.bm.equals(var5.bm)) {
                return false;
            } else if (!this.bj.equals(var5.bj)) {
                return false;
            } else if (!this.bk.equals(var5.bk)) {
                return false;
            } else if (this.bl.length != var5.bl.length) {
                return false;
            } else {
                for (int var6 = 0; var6 < this.bl.length; var6++) {
                    if (!this.bl[var6].equals(var5.bl[var6])) {
                        return false;
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int var3 = this.bi.hashCode();
        var3 = 31 * var3 + this.bj.hashCode();
        var3 = 31 * var3 + this.bk.hashCode();
        var3 = 31 * var3 + this.bm.hashCode();
        return 31 * var3 + Arrays.hashCode((Object[])this.bl);
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
