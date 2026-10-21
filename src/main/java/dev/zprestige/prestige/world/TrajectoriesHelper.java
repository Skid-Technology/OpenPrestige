package dev.zprestige.prestige.world;

import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public final class TrajectoriesHelper extends PersistentProjectileEntity {
    public boolean a = false;
    public boolean b = false;

    public TrajectoriesHelper(EntityType var1, LivingEntity var2, World var3, ItemStack var4) {
        super(var1, var2, var3, var4, var4);
    }

    @Override
    public void tick() {
        Event.b();
        Vec3d var6 = this.getVelocity();
        if (this.lastPitch == 0.0F && this.lastYaw == 0.0F) {
            this.setYaw((float)(MathHelper.atan2(var6.x, var6.z) * 180.0F / (float)Math.PI));
            this.setPitch((float)(MathHelper.atan2(var6.y, var6.horizontalLength()) * 180.0F / (float)Math.PI));
            this.lastYaw = this.getYaw();
            this.lastPitch = this.getPitch();
        }

        BlockPos var7 = this.getBlockPos();
        World var8 = this.getEntityWorld();
        BlockState var9 = var8.getBlockState(var7);
        if (!var9.isAir()) {
            VoxelShape var10 = var9.getCollisionShape(var8, var7);
            if (!var10.isEmpty()) {
                for (Box var12 : var10.getBoundingBoxes()) {
                    if (var12.offset(var7).contains(PositionUtil.a(new Object[]{this, 67736483458412L}))) {
                        this.setOnGround(true);
                        break;
                    }
                }
            }
        }

        if (this.shake > 0) {
            this.shake--;
        }

        if (this.isTouchingWaterOrRain() || var9.isOf(Blocks.POWDER_SNOW)) {
            this.extinguish();
        }

        if (this.isOnGround()) {
            this.b = false;
        } else {
            this.inGroundTime = 0;
            Vec3d var28 = PositionUtil.a(new Object[]{this, 67736483458412L});
            Vec3d var30 = var28.add(var6);
            BlockHitResult var31 = var8.raycast(new RaycastContext(var28, var30, ShapeType.COLLIDER, FluidHandling.NONE, this));
            if (var31.getType() != Type.MISS) {
                var30 = var31.getPos();
            }

            EntityHitResult var13 = this.getEntityCollision(var28, var30);
            if (var13 != null) {
                this.onCollision(var13);
                this.velocityDirty = true;
            }
        }

        var6 = this.getVelocity();
        double var29 = var6.x;
        double var32 = var6.y;
        double var14 = var6.z;
        double var16 = this.getX() + var29;
        double var18 = this.getY() + var32;
        double var20 = this.getZ() + var14;
        double var22 = var6.horizontalLength();
        this.setYaw((float)(MathHelper.atan2(var29, var14) * 180.0F / (float)Math.PI));
        this.setPitch((float)(MathHelper.atan2(var32, var22) * 180.0F / (float)Math.PI));
        this.setPitch(updateRotation(this.lastPitch, this.getPitch()));
        this.setYaw(updateRotation(this.lastYaw, this.getYaw()));
        float var24 = 0.99F;
        if (this.isTouchingWater()) {
            var24 = this.getDragInWater();
        }

        this.setVelocity(var6.multiply(var24));
        if (!this.hasNoGravity()) {
            Vec3d var26 = this.getVelocity();
            this.setVelocity(var26.x, var26.y - 0.05F, var26.z);
        }

        this.setPosition(var16, var18, var20);
        this.tickBlockCollision();
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

    @Override
    public void onEntityHit(EntityHitResult entityHitResult) {
        this.b = false;
        this.a = true;
    }

    @Override
    public void onBlockHit(BlockHitResult blockHitResult) {
        this.b = false;
    }

    @Override
    public ItemStack asItemStack() {
        return null;
    }

    @Override
    public ItemStack getDefaultItemStack() {
        return null;
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
