package dev.zprestige.prestige.render;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RenderUtil4 {
    private static final Map a = new HashMap();

    private RenderUtil4() {
    }

    public static int a(Object[] var0) {
        int var3 = (Integer)var0[0];
        return !a.containsKey(var3) ? var3 : (Integer)a.getOrDefault(var3, var3);
    }

    static {
        for (int var13 = 0; var13 < 26; var13++) {
            a.put(65 + var13, 65 + var13);
        }

        for (int var14 = 0; var14 < 10; var14++) {
            a.put(48 + var14, 48 + var14);
        }

        a.put(188, 44);
        a.put(190, 46);
        a.put(191, 47);
        a.put(186, 59);
        a.put(222, 39);
        a.put(219, 91);
        a.put(221, 93);
        a.put(220, 92);
        a.put(189, 45);
        a.put(187, 61);
        a.put(192, 96);
        a.put(32, 32);
        a.put(13, 257);
        a.put(9, 258);
        a.put(8, 259);
        a.put(46, 261);
        a.put(45, 260);
        a.put(39, 262);
        a.put(37, 263);
        a.put(40, 264);
        a.put(38, 265);
        a.put(33, 266);
        a.put(34, 267);
        a.put(36, 268);
        a.put(35, 269);
        a.put(27, 256);
        a.put(44, 283);
        a.put(145, 281);
        a.put(19, 284);
        a.put(20, 280);
        a.put(144, 282);
        a.put(93, 348);

        for (int var15 = 0; var15 < 24; var15++) {
            a.put(112 + var15, 290 + var15);
        }

        a.put(160, 340);
        a.put(161, 344);
        a.put(162, 341);
        a.put(163, 345);
        a.put(164, 342);
        a.put(165, 346);
        a.put(91, 343);
        a.put(92, 347);
        a.put(16, 340);
        a.put(17, 341);
        a.put(18, 342);

        for (int var16 = 0; var16 < 10; var16++) {
            a.put(96 + var16, 320 + var16);
        }

        a.put(106, 332);
        a.put(107, 334);
        a.put(109, 333);
        a.put(110, 330);
        a.put(111, 331);
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
