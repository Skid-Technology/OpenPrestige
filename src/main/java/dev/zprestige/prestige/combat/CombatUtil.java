package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;

public class CombatUtil implements RenderContract2 {
    public static boolean a(Object[] var0) {
        Entity var1 = (Entity)var0[0];
        long var2 = (Long)var0[1];
        var2 = 42632450102655L ^ var2;
        long var4 = var2 ^ 57986789991100L;
        if (var1 instanceof PlayerEntity) {
            return true;
        } else if (var1 instanceof LivingEntity && b.getNetworkHandler() != null) {
            String var7 = var1.getName().getString();

            for (PlayerListEntry var9 : b.getNetworkHandler().getPlayerList()) {
                if (PositionUtil.a_String________(new Object[]{var9.getProfile(), var4}).equals(var7)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static PlayerEntity a_class_1657(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 42632450102655L ^ var1;
        long var3 = var1 ^ 69502222699245L;
        long var5 = var1 ^ 27342419042136L;
        PlayerEntity var8 = null;
        Event.b();

        for (PlayerEntity var10 : b.world.getPlayers()) {
            if (var10.isAlive()
                && !var10.equals(b.player)
                && !Prestige.g.a(new Object[]{var10.getName().getString(), var3})
                && Prestige.i.a_boolean_(new Object[]{var10, var5})
                && (var8 == null || b.player.squaredDistanceTo(var10) < b.player.squaredDistanceTo(var8))) {
                var8 = var10;
            }
        }

        return var8;
    }

    public static PlayerEntity b(Object[] var0) {
        float var1 = (Float)var0[0];
        long var2 = (Long)var0[1];
        var2 = 42632450102655L ^ var2;
        long var4 = var2 ^ 92283722434249L;
        long var6 = var2 ^ 57728975127273L;
        long var8 = var2 ^ 127570764469116L;
        long var10 = var2 ^ 70986972797420L;
        Event.b();
        PlayerEntity var13 = null;

        for (PlayerEntity var15 : b.world.getPlayers()) {
            if (var15.isAlive()
                && !var15.equals(b.player)
                && !Prestige.g.a(new Object[]{var15.getName().getString(), var4})
                && Prestige.i.a_boolean_(new Object[]{var15, var8})
                && (var13 == null || b.player.squaredDistanceTo(var15) < b.player.squaredDistanceTo(var13))
                && !(WorldUtil.a(new Object[]{WorldUtil.a_RenderBatch_(new Object[]{var15.getEyePos(), var10}), var6}) > var1)) {
                var13 = var15;
            }
        }

        return var13;
    }

    public static Entity a_class_1297(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 42632450102655L ^ var1;
        long var3 = var1 ^ 20222013439035L;
        long var5 = var1 ^ 58876846827918L;
        Entity var8 = null;
        Event.b();

        for (Entity var10 : b.world.getEntities()) {
            if (var10.isAlive()
                && !var10.equals(b.player)
                && !Prestige.g.a(new Object[]{var10.getName().getString(), var3})
                && var10.isAttackable()
                && Prestige.i.a_boolean_(new Object[]{var10, var5})
                && (var8 == null || b.player.squaredDistanceTo(var10) < b.player.squaredDistanceTo(var8))) {
                var8 = var10;
            }
        }

        return var8;
    }

    public static boolean b_boolean(Object[] var0) {
        PlayerEntity var1 = (PlayerEntity)var0[0];
        long var2 = (Long)var0[1];
        var2 = 42632450102655L ^ var2;
        long var4 = var2 ^ 91326150591869L;
        return (
                var1.getMainHandStack().getItem() == Items.SHIELD
                    || var1.getOffHandStack().getItem() == Items.SHIELD && var1.getMainHandStack().get(DataComponentTypes.FOOD) == null
            )
            && var1.isUsingItem()
            && !c(new Object[]{var1, var4});
    }

    private static boolean c(Object[] var0) {
        PlayerEntity var3 = (PlayerEntity)var0[0];
        long var1 = (Long)var0[1];
        var1 = 42632450102655L ^ var1;
        long var4 = var1 ^ 105124625454497L;
        long var6 = var1 ^ 10499210163888L;
        if (b.player != null && var3 != null) {
            Vec3d var9 = PositionUtil.b(new Object[]{var6});
            Vec3d var10 = PositionUtil.a(new Object[]{var3, var4});
            Vec3d var11 = var9.subtract(var10).normalize();
            float var12 = var3.getYaw();
            float var13 = var3.getPitch();
            Vec3d var14 = new Vec3d(
                    -Math.sin(Math.toRadians(var12)) * Math.cos(Math.toRadians(var13)),
                    -Math.sin(Math.toRadians(var13)),
                    Math.cos(Math.toRadians(var12)) * Math.cos(Math.toRadians(var13))
                )
                .normalize();
            double var15 = var14.dotProduct(var11);
            return var15 < 0.0;
        } else {
            return false;
        }
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
