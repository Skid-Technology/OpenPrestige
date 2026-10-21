package dev.zprestige.prestige.world;

import com.mojang.authlib.GameProfile;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class PositionUtil implements RenderContract2 {
    public static Vec3d a(Object[] var0) {
        Entity var3 = (Entity)var0[0];
        return var3.getEntityPos();
    }

    public static Vec3d b(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 135654198816533L ^ var1;
        long var3 = var1 ^ 49962382593028L;
        return a(new Object[]{b.player, var3});
    }

    public static String a_String________(Object[] var0) {
        GameProfile var3 = (GameProfile)var0[0];
        return var3.name();
    }

    public static Vec3d c(Object[] var0) {
        Entity var1 = (Entity)var0[0];
        return new Vec3d(var1.lastX, var1.lastY, var1.lastZ);
    }

    public static boolean a_boolean________________________________________________(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 135654198816533L ^ var1;
        long var3 = var1 ^ 121904208108296L;
        return b_boolean_________(new Object[]{b.player, var3});
    }

    public static boolean b_boolean_________(Object[] var0) {
        PlayerEntity var1 = (PlayerEntity)var0[0];
        return var1.isGliding();
    }

    public static double a_double___________(Object[] var0) {
        PlayerEntity var1 = (PlayerEntity)var0[0];
        long var2 = (Long)var0[1];
        var2 = 135654198816533L ^ var2;
        long var4 = var2 ^ 39318658454649L;
        Vec3d var6 = a(new Object[]{var1, var4});
        Vec3d var7 = var6.add(0.0, -256.0, 0.0);
        BlockHitResult var8 = b.world.raycast(new RaycastContext(var6, var7, ShapeType.OUTLINE, FluidHandling.NONE, var1));
        return var8.getType() == Type.BLOCK ? var1.getY() - var8.getPos().y : 256.0;
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
