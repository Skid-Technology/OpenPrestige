package dev.zprestige.prestige.math;

import dev.zprestige.prestige.client.Prestige;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class MathHelper4 {
    public float a;
    public float b;
    public float c;
    public float d;
    public String e;

    public MathHelper4(float var1, float var2, float var3, float var4, String var5) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
    }

    public static Color b(Object[] var0) {
        Color var4 = (Color)var0[0];
        float var1 = (Float)var0[1];
        return new Color(var4.getRed() / 255.0F, var4.getGreen() / 255.0F, var4.getBlue() / 255.0F, net.minecraft.util.math.MathHelper.clamp(var1, 0.0F, 1.0F));
    }

    public static Color d(Object[] var0) {
        return (Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue();
    }

    public static float a(Object[] var0) {
        float var2 = (Float)var0[0];
        float var1 = (Float)var0[1];
        float var5 = (Float)var0[2];
        return var2 < var1 ? var1 : Math.min(var2, var5);
    }

    public static float b_float______(Object[] var0) {
        float var2 = (Float)var0[0];
        float var1 = (Float)var0[1];
        float var5 = (Float)var0[2];
        long var3 = (Long)var0[3];
        var3 = 29597793068458L ^ var3;
        long var6 = var3 ^ 82621437909205L;
        float var10001 = var2 - var1;
        Object[] var10007 = new Object[]{null, null, null, var6};
        var10007[2] = 1.0F;
        var10007[1] = 0.0F;
        var10007[0] = var5;
        return var2 - var10001 * a(var10007);
    }

    public static float c(Object[] var0) {
        float var4 = (Float)var0[0];
        int var1 = (Integer)var0[1];
        if (var1 < 0) {
            throw new IllegalArgumentException();
        } else {
            BigDecimal var5 = BigDecimal.valueOf((double)var4);
            var5 = var5.setScale(var1, RoundingMode.FLOOR);
            return var5.floatValue();
        }
    }

    public static String a_String_____(Object[] var0) {
        int var1 = (Integer)var0[0];
        HashMap var4 = new HashMap();
        var4.put(9999, "LMB");
        var4.put(9998, "RMB");
        var4.put(9997, "MMB");
        var4.put(9996, "MB4");
        var4.put(9995, "MM5");
        var4.put(345, "R-Ctrl");
        var4.put(344, "Right Shift");
        var4.put(342, "L-Alt");
        var4.put(341, "L-Ctrl");
        var4.put(340, "Shift");
        var4.put(258, "Tab");
        return (String)var4.get(var1);
    }

    public void d_void(Object[] var1) {
    }

    public void e(Object[] var1) {
    }

    public void f(Object[] var1) {
    }

    public void g(Object[] var1) {
    }

    public void h(Object[] var1) {
    }

    public void i(Object[] var1) {
        this.e = null;
    }

    public float f_float(Object[] var1) {
        return this.a;
    }

    public void j(Object[] var1) {
        float var2 = (Float)var1[0];
        this.a = var2;
    }

    public float g_float(Object[] var1) {
        return this.b;
    }

    public void k(Object[] var1) {
        float var2 = (Float)var1[0];
        this.b = var2;
    }

    public float h_float(Object[] var1) {
        return this.c;
    }

    public void l(Object[] var1) {
        float var2 = (Float)var1[0];
        this.c = var2;
    }

    public float i_float(Object[] var1) {
        return this.d;
    }

    public void m(Object[] var1) {
        float var2 = (Float)var1[0];
        this.d = var2;
    }

    public String b_String__(Object[] var1) {
        return this.e;
    }

    public void n(Object[] var1) {
        String var2 = (String)var1[0];
        this.e = var2;
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
