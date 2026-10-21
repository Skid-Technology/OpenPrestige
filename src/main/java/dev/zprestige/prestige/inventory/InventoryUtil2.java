package dev.zprestige.prestige.inventory;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

public class InventoryUtil2 implements RenderContract2 {
    public static boolean a(Object[] var0) {
        BlockHitResult var1 = (BlockHitResult)var0[0];
        long var2 = (Long)var0[1];
        var2 = 19061682513592L ^ var2;
        long var4 = var2 ^ 106127797897354L;
        return b(new Object[]{var1, Hand.MAIN_HAND, var4});
    }

    public static boolean b(Object[] var0) {
        BlockHitResult var1 = (BlockHitResult)var0[0];
        Hand var4 = (Hand)var0[1];
        Event.b();
        ActionResult var6 = b.interactionManager.interactBlock(b.player, var4, var1);
        if (!b.player.isSpectator()) {
            Prestige.o.g(new Object[]{true});
        }

        if (var6.isAccepted()) {
            b.player.swingHand(var4);
            return true;
        } else {
            return false;
        }
    }

    public static void a_void_________(Object[] var0) {
        Entity var1 = (Entity)var0[0];
        b.interactionManager.attackEntity(b.player, var1);
        b.player.swingHand(Hand.MAIN_HAND);
        Prestige.o.g(new Object[]{true});
    }

    public static boolean c(Object[] var0) {
        Hand var1 = (Hand)var0[0];
        Event.b();
        ActionResult var5 = b.interactionManager.interactItem(b.player, var1);
        if (!b.player.isSpectator()) {
            Prestige.o.g(new Object[]{true});
        }

        if (var5.isAccepted()) {
            b.player.swingHand(Hand.MAIN_HAND);
            return true;
        } else {
            return false;
        }
    }

    public static void b_void___(Object[] var0) {
        b.player.startGliding();
    }

    public static float a_float_________(Object[] var0) {
        boolean var1 = (Boolean)var0[0];
        return var1 ? 5.0F : 4.5F;
    }

    public static float b_float_____(Object[] var0) {
        return a_float_________(new Object[]{b.player.isCreative()});
    }

    public static boolean d(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 19061682513592L ^ var1;
        long var3 = var1 ^ 87606581186488L;
        return b.player.isUsingItem()
            && (b.player.getMainHandStack().getItem() == Items.SHIELD || b.player.getOffHandStack().getItem() == Items.SHIELD && !e(new Object[]{var3}));
    }

    public static boolean e(Object[] var0) {
        return b.player.getMainHandStack().get(DataComponentTypes.FOOD) != null
            ? true
            : b.player.getMainHandStack().getItem() instanceof PotionItem
                || b.player.getMainHandStack().getItem() == Items.SNOWBALL
                || b.player.getMainHandStack().getItem() == Items.EGG
                || b.player.getMainHandStack().getItem() == Items.ENDER_PEARL
                || b.player.getMainHandStack().getItem() == Items.EXPERIENCE_BOTTLE
                || b.player.getMainHandStack().getItem() == Items.SPLASH_POTION
                || b.player.getMainHandStack().getItem() == Items.LINGERING_POTION;
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
