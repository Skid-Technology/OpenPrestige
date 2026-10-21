package dev.zprestige.prestige.type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.DoubleUnaryOperator;

public enum Easing {
    LINEAR(lpL433_0 -> lpL433_0),
    SINE_IN(lpL432_0 -> 1.0 - Math.cos(lpL432_0 * Math.PI / 2.0)),
    SINE_OUT(lpL431_0 -> Math.sin(lpL431_0 * Math.PI / 2.0)),
    SINE_IN_OUT(lpL430_0 -> -(Math.cos(Math.PI * lpL430_0) - 1.0) / 2.0),
    CUBIC_IN(lpL429_0 -> lpL429_0 * lpL429_0 * lpL429_0),
    CUBIC_OUT(lpL428_0 -> 1.0 - Math.pow(1.0 - lpL428_0, 3.0)),
    CUBIC_IN_OUT(lpL427_0 -> lpL427_0 < 0.5 ? 4.0 * lpL427_0 * lpL427_0 * lpL427_0 : 1.0 - Math.pow(-2.0 * lpL427_0 + 2.0, 3.0) / 2.0),
    QUAD_IN(lpL426_0 -> lpL426_0 * lpL426_0),
    QUAD_OUT(lpL425_0 -> 1.0 - (1.0 - lpL425_0) * (1.0 - lpL425_0)),
    QUAD_IN_OUT(lpL424_0 -> lpL424_0 < 0.5 ? 2.0 * lpL424_0 * lpL424_0 : 1.0 - Math.pow(-2.0 * lpL424_0 + 2.0, 2.0) / 2.0),
    QUART_IN(lpL423_0 -> lpL423_0 * lpL423_0 * lpL423_0 * lpL423_0),
    QUART_OUT(lpL422_0 -> 1.0 - Math.pow(1.0 - lpL422_0, 4.0)),
    QUART_IN_OUT(lpL421_0 -> lpL421_0 < 0.5 ? 8.0 * lpL421_0 * lpL421_0 * lpL421_0 * lpL421_0 : 1.0 - Math.pow(-2.0 * lpL421_0 + 2.0, 4.0) / 2.0),
    QUINT_IN(lpL420_0 -> lpL420_0 * lpL420_0 * lpL420_0 * lpL420_0 * lpL420_0),
    QUINT_OUT(lpL419_0 -> 1.0 - Math.pow(1.0 - lpL419_0, 5.0)),
    QUINT_IN_OUT(lpL418_0 -> lpL418_0 < 0.5 ? 16.0 * lpL418_0 * lpL418_0 * lpL418_0 * lpL418_0 * lpL418_0 : 1.0 - Math.pow(-2.0 * lpL418_0 + 2.0, 5.0) / 2.0),
    CIRC_IN(lpL417_0 -> 1.0 - Math.sqrt(1.0 - Math.pow(lpL417_0, 2.0))),
    CIRC_OUT(lpL416_0 -> Math.sqrt(1.0 - Math.pow(lpL416_0 - 1.0, 2.0))),
    CIRC_IN_OUT(
        lpL415_0 -> lpL415_0 < 0.5
            ? (1.0 - Math.sqrt(1.0 - Math.pow(2.0 * lpL415_0, 2.0))) / 2.0
            : (Math.sqrt(1.0 - Math.pow(-2.0 * lpL415_0 + 2.0, 2.0)) + 1.0) / 2.0
    ),
    EXPO_IN(lpL414_0 -> lpL414_0 == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * lpL414_0 - 10.0)),
    EXPO_OUT(lpL413_0 -> lpL413_0 == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * lpL413_0)),
    EXPO_IN_OUT(
        lpL412_0 -> lpL412_0 == 0.0
            ? 0.0
            : (lpL412_0 == 1.0 ? 1.0 : (lpL412_0 < 0.5 ? Math.pow(2.0, 20.0 * lpL412_0 - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * lpL412_0 + 10.0)) / 2.0))
    ),
    ELASTIC_IN(
        lpL411_0 -> lpL411_0 == 0.0
            ? 0.0
            : (lpL411_0 == 1.0 ? 1.0 : -Math.pow(2.0, 10.0 * lpL411_0 - 10.0) * Math.sin((lpL411_0 * 10.0 - 10.75) * (Math.PI * 2.0 / 3.0)))
    ),
    ELASTIC_OUT(
        lpL410_0 -> lpL410_0 == 0.0
            ? 0.0
            : (lpL410_0 == 1.0 ? 1.0 : Math.pow(2.0, -10.0 * lpL410_0) * Math.sin((lpL410_0 * 10.0 - 0.75) * (Math.PI * 2.0 / 3.0)) + 1.0)
    ),
    ELASTIC_IN_OUT(
        lpL409_0 -> lpL409_0 == 0.0
            ? 0.0
            : (
                lpL409_0 == 1.0
                    ? 1.0
                    : (
                        lpL409_0 < 0.5
                            ? -(Math.pow(2.0, 20.0 * lpL409_0 - 10.0) * Math.sin((20.0 * lpL409_0 - 11.125) * (Math.PI * 4.0 / 9.0))) / 2.0
                            : Math.pow(2.0, -20.0 * lpL409_0 + 10.0) * Math.sin((20.0 * lpL409_0 - 11.125) * (Math.PI * 4.0 / 9.0)) / 2.0 + 1.0
                    )
            )
    ),
    BACK_IN(lpL408_0 -> 2.70158 * lpL408_0 * lpL408_0 * lpL408_0 - 1.70158 * lpL408_0 * lpL408_0),
    BACK_OUT(lpL407_0 -> 1.0 + 2.70158 * Math.pow(lpL407_0 - 1.0, 3.0) + 1.70158 * Math.pow(lpL407_0 - 1.0, 2.0)),
    BACK_IN_OUT(
        lpL406_0 -> lpL406_0 < 0.5
            ? Math.pow(2.0 * lpL406_0, 2.0) * (7.189819 * lpL406_0 - 2.5949095) / 2.0
            : (Math.pow(2.0 * lpL406_0 - 2.0, 2.0) * (3.5949095 * (lpL406_0 * 2.0 - 2.0) + 2.5949095) + 2.0) / 2.0
    );

    private final DoubleUnaryOperator a;

    private Easing(DoubleUnaryOperator var3) {
        this.a = var3;
    }

    public double a(Object[] var1) {
        double var2 = (Double)var1[0];
        return this.a.applyAsDouble(var2);
    }

    static {
        Object[] var10001 = new Object[]{0L};
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
