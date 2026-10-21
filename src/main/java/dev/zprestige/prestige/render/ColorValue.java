package dev.zprestige.prestige.render;

import dev.zprestige.prestige.math.MathUtil4;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.joml.Vector4f;

public class ColorValue {
    public final float a;
    public final float b;
    public final float c;
    public final float d;

    public ColorValue(Vector4f var1, long var2) {
        super();
        var2 = 0L;
        var2 = 98963274059567L ^ var2;
        long var4 = var2 ^ 12796092348509L;
        float var10001 = var1.x;
        Object[] var10004 = new Object[]{null, var4};
        var10004[0] = var10001;
        this.a = MathUtil4.a_float____________(var10004);
        var10001 = var1.y;
        var10004 = new Object[]{null, var4};
        var10004[0] = var10001;
        this.b = MathUtil4.a_float____________(var10004);
        var10001 = var1.z;
        var10004 = new Object[]{null, var4};
        var10004[0] = var10001;
        this.c = MathUtil4.a_float____________(var10004);
        var10001 = var1.w;
        var10004 = new Object[]{null, var4};
        var10004[0] = var10001;
        this.d = MathUtil4.a_float____________(var10004);
    }

    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else {
            return !(var1 instanceof ColorValue var5)
                ? false
                : Float.compare(var5.a, this.a) == 0
                    && Float.compare(var5.b, this.b) == 0
                    && Float.compare(var5.c, this.c) == 0
                    && Float.compare(var5.d, this.d) == 0;
        }
    }

    @Override
    public int hashCode() {
        int var3 = Float.floatToIntBits(this.a);
        var3 = 31 * var3 + Float.floatToIntBits(this.b);
        var3 = 31 * var3 + Float.floatToIntBits(this.c);
        return 31 * var3 + Float.floatToIntBits(this.d);
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
