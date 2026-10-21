package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class GhostPlace extends Module {
    private BoolSetting d;
    private BoolSetting a;
    private long c;
    private static final long l = -1L;

    public GhostPlace() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.c = l;
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (b.player != null && b.world != null) {
            if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
                if (!Prestige.o.d_boolean______(new Object[0])) {
                    if (b.options.useKey.isPressed()) {
                        Hand var13 = this.a(new Object[]{5530615103354L});
                        if (var13 != null) {
                            if (d(new Object[]{b.crosshairTarget, 52554676280732L})) {
                                BlockHitResult var14 = this.a_class_3965________(new Object[]{10873155518664L});
                                if (var14 != null && var14.getType() == Type.BLOCK) {
                                    InventoryUtil2.b(new Object[]{var14, var13, 41898663564720L});
                                    this.c = b.world.getTime();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (b.player != null && b.world != null) {
            if (d(new Object[]{b.crosshairTarget, 52554676280732L})) {
                if (this.a(new Object[]{5530615103354L}) != null) {
                    if (this.c == b.world.getTime()) {
                        var1.a_void_____(new Object[0]);
                    } else if (b.options.useKey.isPressed()) {
                        BlockHitResult var11 = this.a_class_3965________(new Object[]{10873155518664L});
                        if (var11 != null && var11.getType() == Type.BLOCK) {
                            var1.a_void_____(new Object[0]);
                        }
                    }
                }
            }
        }
    }

    private Hand a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 51780909037643L ^ var2;
        long var4 = var2 ^ 34460687958252L;
        if (this.a_boolean_____________________(new Object[]{b.player.getMainHandStack(), var4})) {
            return Hand.MAIN_HAND;
        } else {
            return this.a.getValue() && this.a_boolean_____________________(new Object[]{b.player.getOffHandStack(), var4}) ? Hand.OFF_HAND : null;
        }
    }

    private boolean a_boolean_____________________(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        return this.d.getValue() ? var2.getItem() == Items.COBWEB : var2.getItem() instanceof BlockItem;
    }

    private static boolean d(Object[] var0) {
        HitResult var1 = (HitResult)var0[0];
        long var2 = (Long)var0[1];
        var2 = 51780909037643L ^ var2;
        long var4 = var2 ^ 24523735270493L;
        long var6 = var2 ^ 54588914831848L;
        if (!(var1 instanceof EntityHitResult var9 && var9.getEntity() instanceof LivingEntity var10)) {
            return false;
        } else {
            return Prestige.g.a(new Object[]{var10.getName().getString(), var4}) ? false : Prestige.i.a_boolean_(new Object[]{var10, var6});
        }
    }

    private BlockHitResult a_class_3965________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 51780909037643L ^ var2;
        long var4 = var2 ^ 61465696013829L;
        Vec3d var6 = b.player.getEyePos();
        RenderBatch var7 = Prestige.o.b_RenderBatch____________(new Object[0]);
        Vec3d var8 = var7 != null ? b.player.getRotationVector(var7.b(new Object[0]), var7.a(new Object[0])) : b.player.getRotationVec(1.0F);
        Vec3d var9 = var6.add(var8.multiply(InventoryUtil2.b_float_____(new Object[]{var4})));
        return b.world.raycast(new RaycastContext(var6, var9, ShapeType.OUTLINE, FluidHandling.NONE, b.player));
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
