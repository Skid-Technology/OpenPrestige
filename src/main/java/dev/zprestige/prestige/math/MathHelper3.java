package dev.zprestige.prestige.math;

import dev.zprestige.prestige.reflect.FieldAccessor;
import dev.zprestige.prestige.reflect.MethodValue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.util.math.Vec3d;

public class MathHelper3 {
    private static final MethodValue a = FieldAccessor.a_MethodValue(new Object[]{Vec3d.class, "x", double.class, 0L});
    private static final MethodValue b = FieldAccessor.a_MethodValue(new Object[]{Vec3d.class, "y", double.class, 0L});
    private static final MethodValue c = FieldAccessor.a_MethodValue(new Object[]{Vec3d.class, "z", double.class, 0L});
    private final Vec3d d;

    public MathHelper3(Vec3d var1) {
        this.d = var1;
    }

    public void d(Object[] var1) {
        double var2 = (Double)var1[0];
        double var4 = (Double)var1[1];
        double var6 = (Double)var1[2];
        long var8 = (Long)var1[3];
        var8 = 113192846192854L ^ var8;
        long var10 = var8 ^ 124005428690047L;
        MethodValue var10000 = a;
        Vec3d var10001 = this.d;
        Object[] var10005 = new Object[]{null, null, var10};
        var10005[1] = var2;
        var10005[0] = var10001;
        var10000.a_void______________(var10005);
        var10000 = b;
        var10001 = this.d;
        var10005 = new Object[]{null, null, var10};
        var10005[1] = var4;
        var10005[0] = var10001;
        var10000.a_void______________(var10005);
        var10000 = c;
        var10001 = this.d;
        var10005 = new Object[]{null, null, var10};
        var10005[1] = var6;
        var10005[0] = var10001;
        var10000.a_void______________(var10005);
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
