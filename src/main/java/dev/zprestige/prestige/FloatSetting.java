package dev.zprestige.prestige;

import dev.zprestige.prestige.annotation.Chainable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public class FloatSetting extends Setting {
    // covariant getValue (added by fix_typed_values.py)
    @Override
    public Float getValue() {
        return (Float)super.getValue();
    }

    private final float min;
    private final float max;

    public FloatSetting(String var1, float var2, float var3, float var4) {
        super(var1, var2);
        this.min = var3;
        this.max = var4;
    }

    @Chainable
    public float getMin() {
        return this.min;
    }

    @Chainable
    public float getMax() {
        return this.max;
    }

    public FloatSetting setVisibility_FloatSetting(Object[] var1) {
        Predicate var2 = (Predicate)var1[0];
        long var3 = (Long)var1[1];
        var3 = 123628267853707L ^ var3;
        long var5 = var3 ^ 130072979389281L;
        super.setVisibility(new Object[]{var2, var5});
        return this;
    }

    @Chainable
    public FloatSetting setDescription_FloatSetting(String var1) {
        super.setDescription(var1);
        return this;
    }

    @Override
    public Setting setVisibility(Object[] var1) {
        Predicate var2 = (Predicate)var1[0];
        long var3 = (Long)var1[1];
        long var5 = var3 ^ 6857104452842L;
        return this.setVisibility_FloatSetting(new Object[]{var2, var5});
    }

    @Chainable
    @Override
    public Setting setDescription(String var1) {
        return this.setDescription_FloatSetting(var1);
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
