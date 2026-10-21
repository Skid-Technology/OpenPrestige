package dev.zprestige.prestige.math;

import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.type.AnimationMode;
import dev.zprestige.prestige.world.PlacementCheck;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.util.math.Vec3d;

public class HitEffectHelper implements RenderContract2 {
    private final RenderHelper27 a;
    public Vec3d b;
    public Vec3d c;
    public Vec3d d;
    public Vec3d e;
    public final PlacementCheck f;
    public final AnimationMode g;

    public HitEffectHelper(Vec3d var1, Vec3d var2, AnimationMode var3, long var4) {
        super();
        var4 = 0L;
        var4 = 26183465440188L ^ var4;
        long var6 = var4 ^ 71503997410734L;
        long var8 = var4 ^ 4340496562606L;
        long var10 = var4 ^ 114862446739809L;
        this.a = new RenderHelper27(var6);
        this.b = var1;
        this.c = var2;
        this.e = var2;
        this.d = new Vec3d((Math.random() * 0.5 - 0.25) * 0.01, Math.random() * 0.25 * 0.01, (Math.random() * 0.5 - 0.25) * 0.01);
        this.g = var3;
        this.f = this.a(new Object[]{var10});
        this.a.a_void_____________________(new Object[]{var8});
    }

    private PlacementCheck a(Object[] var1) {
        return (PlacementCheck)(switch (this.g) {
            case LEGACY -> new MathUtil();
            case POP -> new MathUtil2();
        });
    }

    public void a_void__________(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        long var3 = (Long)var1[1];
        var3 = 26183465440188L ^ var3;
        long var5 = var3 ^ 74231399566631L;
        this.e = this.c;
        PlacementCheck var10000 = this.f;
        Object[] var10005 = new Object[]{null, null, var5};
        var10005[1] = var2;
        var10005[0] = this;
        var10000.a(var10005);
    }

    public float a_float__________(Object[] var1) {
        float var2 = (Float)var1[0];
        long var3 = (Long)var1[1];
        var3 = 26183465440188L ^ var3;
        long var5 = var3 ^ 44892599887962L;
        return Math.min(1.0F, this.a.a_float__________________(new Object[]{var5}) / var2);
    }

    public Vec3d a_class_243_(Object[] var1) {
        return this.e;
    }

    public Vec3d b(Object[] var1) {
        return this.c;
    }

    public Vec3d c(Object[] var1) {
        return this.d;
    }

    public RenderHelper27 a_RenderHelper27(Object[] var1) {
        return this.a;
    }

    public AnimationMode a_AnimationMode(Object[] var1) {
        return this.g;
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
