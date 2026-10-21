package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public final class TrajectoriesHelper extends PersistentProjectileEntity {
    public boolean a;

    public TrajectoriesHelper(World var1, LivingEntity var2, ItemStack var3, long var4) {
        super(EntityType.ARROW, var2, var1, var3, var3);
    }

    @Override
    public void tick() {
        Event.b();
        HitResult var4 = ProjectileUtil.getCollision(this, lpL398_0 -> this.canHit(((net.minecraft.entity.Entity)lpL398_0)));
        if (var4.getType() != Type.MISS) {
            this.onCollision(var4);
        }

        this.tickBlockCollision();
        Vec3d var5 = this.getVelocity();
        double var6 = this.getX() + var5.x;
        double var8 = this.getY() + var5.y;
        double var10 = this.getZ() + var5.z;
        this.updateRotation();
        float var12 = this.isTouchingWater() ? 0.8F : 0.99F;
        this.setVelocity(var5.multiply(var12));
        if (!this.hasNoGravity()) {
            Vec3d var13 = this.getVelocity();
            this.setVelocity(var13.x, var13.y - 0.03, var13.z);
        }

        this.setPosition(var6, var8, var10);
    }

    @Override
    public void onBlockHit(BlockHitResult blockHitResult) {
        this.a = true;
    }

    @Override
    public ItemStack asItemStack() {
        return null;
    }

    @Override
    public ItemStack getDefaultItemStack() {
        return null;
    }

    @Override
    public void onEntityHit(EntityHitResult entityHitResult) {
        this.a = true;
    }

    @Override
    public void setVelocity(double x, double y, double z, float power, float uncertainty) {
        Vec3d var11 = new Vec3d(x, y, z).normalize().multiply(power);
        this.setVelocity(var11);
        this.setYaw((float)(MathHelper.atan2(var11.x, var11.z) * 180.0F / (float)Math.PI));
        this.setPitch((float)(MathHelper.atan2(var11.y, var11.horizontalLength()) * 180.0F / (float)Math.PI));
        this.lastYaw = this.getYaw();
        this.lastPitch = this.getPitch();
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
