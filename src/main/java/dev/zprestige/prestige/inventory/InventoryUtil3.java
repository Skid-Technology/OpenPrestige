package dev.zprestige.prestige.inventory;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;

public class InventoryUtil3 implements RenderContract2 {
    public static boolean a(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        long var2 = (Long)var0[1];
        var2 = 53464839490873L ^ var2;
        long var4 = var2 ^ 122684777618761L;
        Event.b();
        int var7 = 0;

        for (Entity var9 : b.world.getEntities()) {
            if (var9 instanceof ItemEntity var10
                && !(var10.getY() < var1.getY())
                && b(new Object[]{var10.getStack().getItem(), var4})
                && !(Math.sqrt(var10.squaredDistanceTo(var1.toCenterPos())) > 15.0)) {
                var7++;
            }
        }

        return var7 > 3;
    }

    public static boolean b(Object[] var0) {
        Item var1 = (Item)var0[0];
        return var1.equals(Items.DIAMOND_HELMET)
            || var1.equals(Items.DIAMOND_CHESTPLATE)
            || var1.equals(Items.DIAMOND_LEGGINGS)
            || var1.equals(Items.DIAMOND_BOOTS)
            || var1.equals(Items.NETHERITE_HELMET)
            || var1.equals(Items.NETHERITE_CHESTPLATE)
            || var1.equals(Items.NETHERITE_LEGGINGS)
            || var1.equals(Items.NETHERITE_BOOTS)
            || var1.equals(Items.DIAMOND_PICKAXE)
            || var1.equals(Items.NETHERITE_PICKAXE)
            || var1.equals(Items.DIAMOND_SWORD)
            || var1.equals(Items.NETHERITE_SWORD)
            || var1.equals(Items.DIAMOND_AXE)
            || var1.equals(Items.NETHERITE_AXE)
            || var1.equals(Items.TOTEM_OF_UNDYING)
            || var1.equals(Items.GOLDEN_APPLE)
            || var1.equals(Items.ENCHANTED_GOLDEN_APPLE)
            || var1.equals(Items.ENDER_PEARL)
            || var1.equals(Items.EXPERIENCE_BOTTLE)
            || var1.equals(Items.END_CRYSTAL)
            || var1.equals(Items.RESPAWN_ANCHOR)
            || var1.equals(Items.GLOWSTONE);
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
