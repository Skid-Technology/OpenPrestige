package dev.zprestige.prestige.inventory;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.module.combat.AnchorExploder;
import dev.zprestige.prestige.module.combat.AnchorMacro;
import dev.zprestige.prestige.module.combat.AnchorPearlCatch;
import dev.zprestige.prestige.module.combat.AnchorPlacer;
import dev.zprestige.prestige.module.combat.AutoAnchor;
import dev.zprestige.prestige.module.combat.DoubleAnchor;
import dev.zprestige.prestige.module.combat.SafeAnchor;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BowItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.EggItem;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ExperienceBottleItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.TridentItem;
import net.minecraft.item.WindChargeItem;
import net.minecraft.util.hit.BlockHitResult;

public final class ArmorUtil implements RenderContract2 {
    private ArmorUtil() {
    }

    public static boolean a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 87650301936136L ^ var1;
        long var3 = var1 ^ 105190636902736L;
        long var5 = var1 ^ 58724177745006L;
        if (b.player != null && b.world != null) {
            if (!b(new Object[]{var3})) {
                return false;
            } else if (b.player.getMainHandStack().getItem() != Items.RESPAWN_ANCHOR && b.player.getOffHandStack().getItem() != Items.RESPAWN_ANCHOR) {
                if (b.crosshairTarget instanceof BlockHitResult var8) {
                    return b.world.getBlockState(var8.getBlockPos()).getBlock() != Blocks.RESPAWN_ANCHOR
                        ? false
                        : !c(new Object[]{b.player.getMainHandStack(), var5}) && !c(new Object[]{b.player.getOffHandStack(), var5});
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private static boolean b(Object[] var0) {
        return AutoAnchor.B || AnchorExploder.o || AnchorMacro.p || AnchorPearlCatch.C || AnchorPlacer.k || DoubleAnchor.s || SafeAnchor.d(new Object[0]);
    }

    private static boolean c(Object[] var0) {
        ItemStack var3 = (ItemStack)var0[0];
        Event.b();
        Item var5 = var3.getItem();
        return var3.get(DataComponentTypes.FOOD) != null
            || var5 instanceof BowItem
            || var5 instanceof CrossbowItem
            || var5 instanceof EnderPearlItem
            || var5 instanceof SnowballItem
            || var5 instanceof EggItem
            || var5 instanceof TridentItem
            || var5 instanceof WindChargeItem
            || var5 instanceof PotionItem
            || var5 instanceof ExperienceBottleItem
            || var5 instanceof ShieldItem
            || var5 instanceof BucketItem
            || var5 instanceof FishingRodItem;
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
