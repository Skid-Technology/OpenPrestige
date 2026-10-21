package dev.zprestige.prestige.math;

import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.world.PlacementCheck;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MathUtil extends PlacementCheck {
    @Override
    public void a(Object[] var1) {
        HitEffectHelper var3 = (HitEffectHelper)var1[0];
        boolean var2 = (Boolean)var1[1];
        long var4 = (Long)var1[2];
        long var6 = var4 ^ 114389671997888L;
        long var8 = var4 ^ 8606540417602L;
        if (!var2) {
            this.b(new Object[]{var3, var8});
        } else {
            BlockState var11 = RenderContract2.b.world.getBlockState(BlockPos.ofFloored(var3.c.x, var3.c.y, var3.c.z + var3.d.z));
            if (!this.a_boolean______(new Object[]{var11, var6})) {
                var3.d = new Vec3d(var3.d.x, var3.d.y, var3.d.z * -0.8);
            }

            BlockState var12 = RenderContract2.b.world.getBlockState(BlockPos.ofFloored(var3.c.x, var3.c.y + var3.d.y, var3.c.z));
            if (!this.a_boolean______(new Object[]{var12, var6})) {
                var3.d = new Vec3d(var3.d.x * 0.999F, var3.d.y * -0.6, var3.d.z * 0.999F);
            }

            BlockState var13 = RenderContract2.b.world.getBlockState(BlockPos.ofFloored(var3.c.x + var3.d.x, var3.c.y, var3.c.z));
            if (!this.a_boolean______(new Object[]{var13, var6})) {
                var3.d = new Vec3d(var3.d.x * -0.8, var3.d.y, var3.d.z);
            }

            this.b(new Object[]{var3, var8});
        }
    }

    private void b(Object[] var1) {
        HitEffectHelper var2 = (HitEffectHelper)var1[0];
        var2.c = var2.c.add(var2.d);
        var2.d = new Vec3d(var2.d.x / 0.999998F, var2.d.y - 1.5E-6, var2.d.z / 0.999998F);
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

    private static MatchException b(MatchException var0) {
        return var0;
    }
}
