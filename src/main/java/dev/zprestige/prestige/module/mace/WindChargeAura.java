package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.module.combat.AutoObbyCrystal;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class WindChargeAura extends Module implements RenderContract3 {
    public WindChargeAura() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private BoolSetting d;
    private FloatSetting c;
    private BoolSetting e;
    private RenderHelper27 f = new RenderHelper27(26293358099357L);

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 121908361326646L;
        long var6 = var2 ^ 7477342050734L;
        long var8 = var2 ^ 92985031432050L;
        long var10 = var2 ^ 91260814807711L;
        long var12 = var2 ^ 29247161167908L;
        long var14 = var2 ^ 85547089512629L;
        long var16 = var2 ^ 101816702374277L;
        if (b.player.isUsingItem()) {
            return null;
        } else if ((Boolean)this.e.getValue() && b.player.isOnGround()) {
            return null;
        } else if (AutoObbyCrystal.R) {
            return null;
        } else if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else {
            RenderHelper27 var10000 = this.f;
            Object[] var10004 = new Object[]{null, var14};
            var10004[0] = 100.0F;
            boolean var19 = !var10000.a(var10004);
            Entity var20 = this.a_class_1297___(new Object[]{var4});
            if (var20 == null) {
                return null;
            } else {
                Vec3d var21 = var20.getBoundingBox().getCenter();
                RenderBatch var22 = Prestige.o.a_RenderBatch(new Object[]{var21, var16});
                if ((Boolean)this.d.getValue() && !var22.a_boolean________________________________________(new Object[0])) {
                    var22 = WorldUtil.d_RenderBatch(new Object[]{var22, var21, var6});
                }

                if (var22.a(new Object[0]) - b.player.getYaw() > (Float)this.c.getValue() * 2.0F
                    || var22.a(new Object[0]) - b.player.getYaw() < -(Float)this.c.getValue() * 2.0F) {
                    return null;
                } else if (!b.player.canSee(var20)) {
                    return null;
                } else if (this.a_double________(new Object[]{var20, var8}) > ((Float)this.a.getValue()).floatValue()) {
                    return null;
                } else if (var19) {
                    return null;
                } else if (var22.a_boolean________________________________________(new Object[0])) {
                    return var22;
                } else {
                    InventoryUtil2.a_void_________(new Object[]{var20, var10});
                    this.f.a_void_____________________(new Object[]{var12});
                    return var22;
                }
            }
        }
    }

    private Entity a_class_1297___(Object[] var1) {
        Event.b();
        WindChargeEntity var5 = null;
        double var6 = Double.MAX_VALUE;

        for (Entity var9 : b.world.getEntities()) {
            if (var9 instanceof WindChargeEntity var10 && var10.isAlive() && var10.getOwner() != b.player) {
                Vec3d var11 = var10.getBoundingBox().getCenter();
                double var12 = b.player.squaredDistanceTo(var11.x, var11.y, var11.z);
                if (!(Math.sqrt(var12) > 5.0) && var12 < var6) {
                    var5 = var10;
                    var6 = var12;
                }
            }
        }

        return var5;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
    }

    private double a_double________(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        Vec3d var5 = b.player.getEyePos();
        Box var6 = var4.getBoundingBox();
        double var7 = MathHelper.clamp(var5.x, var6.minX, var6.maxX);
        double var9 = MathHelper.clamp(var5.y, var6.minY, var6.maxY);
        double var11 = MathHelper.clamp(var5.z, var6.minZ, var6.maxZ);
        double var13 = var5.x - var7;
        double var15 = var5.y - var9;
        double var17 = var5.z - var11;
        return Math.sqrt(var13 * var13 + var15 * var15 + var17 * var17);
    }

    private static Field g(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field h(Class var0, String var1, Class var2) {
        Field var3 = g(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = h(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method g(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method h(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = g(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = h(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
