package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.inventory.InventoryUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.AirBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class AutoTool extends Module {
    public AutoTool() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private int a = -1;

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (b.crosshairTarget instanceof BlockHitResult var11) {
            BlockPos var12 = var11.getBlockPos();
            if (!b.world.getBlockState(var12).isAir()) {
                int var13 = this.a(new Object[]{var12, 93044856298765L});
                if (var13 != -1 && b.options.attackKey.isPressed()) {
                    this.a = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                    Object[] var14 = new Object[]{null, 37392877673079L};
                    var14[0] = var13;
                    InventoryUtil.a_void________(var14);
                } else {
                    int var10000 = this.a;
                    Object[] var10003 = new Object[]{null, 37392877673079L};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                }
            }
        }
    }

    private int a(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        Event.b();
        int var6 = -1;
        float var7 = 1.0F;

        for (int var8 = 0; var8 <= 8; var8++) {
            ItemStack var9 = b.player.getInventory().getStack(var8);
            if (!var9.isEmpty() && var9.getMaxDamage() - var9.getDamage() > 10) {
                float var10 = EnchantmentHelper.getLevel(
                    b.world.getRegistryManager().getOptional(Enchantments.EFFICIENCY.getRegistryRef()).get().getEntry(Enchantments.EFFICIENCY.getValue()).get(),
                    var9
                );
                float var11 = var9.getMiningSpeedMultiplier(b.world.getBlockState(var4));
                if (b.world.getBlockState(var4).getBlock() instanceof AirBlock) {
                    return -1;
                }

                if (var10 + var11 > var7) {
                    var7 = var10 + var11;
                    var6 = var8;
                }
            }
        }

        return var6;
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
