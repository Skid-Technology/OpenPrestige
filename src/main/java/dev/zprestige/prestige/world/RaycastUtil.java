package dev.zprestige.prestige.world;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class RaycastUtil implements RenderContract2 {
    private static final double a = 4.25;
    private static final double c = 8.0;

    public static Vec3d a(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        long var2 = (Long)var0[1];
        var2 = 52575387219545L ^ var2;
        long var4 = var2 ^ 61195202805394L;
        long var6 = var2 ^ 35956316691861L;
        Event.b();
        Vec3d[] var9 = new Vec3d[]{
            new Vec3d(0.5, 1.0, 0.5),
            new Vec3d(0.5, 0.5, 0.0),
            new Vec3d(0.0, 0.5, 0.5),
            new Vec3d(1.0, 0.5, 0.5),
            new Vec3d(0.5, 0.5, 1.0),
            new Vec3d(1.0, 0.0, 1.0),
            new Vec3d(0.0, 0.0, 1.0),
            new Vec3d(1.0, 0.0, 0.0),
            new Vec3d(0.0, 0.0, 0.0),
            new Vec3d(1.0, 1.0, 1.0),
            new Vec3d(0.0, 1.0, 1.0),
            new Vec3d(1.0, 1.0, 0.0),
            new Vec3d(0.0, 1.0, 0.0)
        };
        Vec3d var10 = null;

        for (int var11 = 0; var11 < var9.length; var11++) {
            Vec3d var12 = var9[var11];
            if (var11 > 4) {
                break;
            }

            Vec3d var13 = new Vec3d(var1.getX() + var12.x, var1.getY() + var12.y, var1.getZ() + var12.z);
            if (!(Math.sqrt(b.player.getEyePos().squaredDistanceTo(var13)) > 4.25)) {
                HitResult var14 = a_class_239(new Object[]{var13, var4});
                if (var14 != null && var14.getPos().distanceTo(var13) < 0.1F) {
                    EntityHitResult var15 = ProjectileUtil.raycast(
                        b.player,
                        b.player.getCameraPosVec(1.0F),
                        var13,
                        b.player.getBoundingBox().stretch(var13.subtract(PositionUtil.b(new Object[]{var6}))).expand(1.0, 1.0, 1.0),
                        lpL35_0 -> !((net.minecraft.entity.Entity)lpL35_0).isSpectator() && ((net.minecraft.entity.Entity)lpL35_0).canHit(),
                        var13.distanceTo(b.player.getCameraPosVec(1.0F))
                    );
                    if (var15 == null && (var10 == null || b.player.getEyePos().distanceTo(var13) < b.player.getEyePos().distanceTo(var10))) {
                        if (var11 == 0) {
                            return var13;
                        }

                        var10 = var13;
                    }
                }
            }
        }

        if (var10 != null) {
            return var10;
        } else {
            for (Vec3d var22 : var9) {
                Vec3d var23 = new Vec3d(var1.getX() + var22.x, var1.getY() + var22.y, var1.getZ() + var22.z);
                if (!(Math.sqrt(b.player.getEyePos().squaredDistanceTo(var23)) > 4.25)) {
                    HitResult var16 = a_class_239(new Object[]{var23, var4});
                    if (var16 != null && var16.getPos().distanceTo(var23) < 0.1F) {
                        EntityHitResult var17 = ProjectileUtil.raycast(
                            b.player,
                            b.player.getCameraPosVec(1.0F),
                            var23,
                            b.player.getBoundingBox().stretch(var23.subtract(PositionUtil.b(new Object[]{var6}))).expand(1.0, 1.0, 1.0),
                            lpL34_0 -> !((net.minecraft.entity.Entity)lpL34_0).isSpectator() && ((net.minecraft.entity.Entity)lpL34_0).canHit(),
                            var23.distanceTo(b.player.getCameraPosVec(1.0F))
                        );
                        if (var17 == null && (var10 == null || b.player.getEyePos().distanceTo(var23) < b.player.getEyePos().distanceTo(var10))) {
                            var10 = var23;
                        }
                    }
                }
            }

            return var10;
        }
    }

    public static Vec3d b(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        long var2 = (Long)var0[1];
        var2 = 52575387219545L ^ var2;
        long var4 = var2 ^ 89751123006146L;
        long var6 = var2 ^ 77770766348741L;
        if (b.player.getEyePos().getY() < var1.getY() + 1.0) {
            return null;
        } else {
            Vec3d[] var9 = new Vec3d[]{
                new Vec3d(0.5, 1.0, 0.5), new Vec3d(0.1, 1.0, 0.1), new Vec3d(0.9, 1.0, 0.1), new Vec3d(0.1, 1.0, 0.9), new Vec3d(0.9, 1.0, 0.9)
            };
            Vec3d var10 = null;

            for (int var11 = 0; var11 < var9.length; var11++) {
                Vec3d var12 = var9[var11];
                Vec3d var13 = new Vec3d(var1.getX() + var12.x, var1.getY() + var12.y, var1.getZ() + var12.z);
                if (!(b.player.getEyePos().distanceTo(var13) > 4.25)) {
                    HitResult var14 = a_class_239(new Object[]{var13, var4});
                    if (var14 != null && var14.getPos().distanceTo(var13) < 0.1F) {
                        EntityHitResult var15 = ProjectileUtil.raycast(
                            b.player,
                            b.player.getCameraPosVec(1.0F),
                            var13,
                            b.player.getBoundingBox().stretch(var13.subtract(PositionUtil.b(new Object[]{var6}))).expand(1.0, 1.0, 1.0),
                            lpL33_0 -> !((net.minecraft.entity.Entity)lpL33_0).isSpectator() && ((net.minecraft.entity.Entity)lpL33_0).canHit(),
                            var13.distanceTo(b.player.getCameraPosVec(1.0F))
                        );
                        if (var15 == null) {
                            if (var11 == 0) {
                                return var13;
                            }

                            if (var10 == null || b.player.getEyePos().distanceTo(var13) < b.player.getEyePos().distanceTo(var10)) {
                                var10 = var13;
                            }
                        }
                    }
                }
            }

            return var10;
        }
    }

    private static HitResult a_class_239(Object[] var0) {
        Vec3d var3 = (Vec3d)var0[0];
        return b.world.raycast(new RaycastContext(b.player.getEyePos(), var3, ShapeType.COLLIDER, FluidHandling.NONE, b.player));
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
