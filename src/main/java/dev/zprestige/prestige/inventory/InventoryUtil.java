package dev.zprestige.prestige.inventory;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.network.PacketQueue;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

public class InventoryUtil implements RenderContract2 {
    public static boolean a(Object[] var0) {
        ItemStack var1 = (ItemStack)var0[0];
        return var1.getItem() == Items.DIAMOND_SWORD
            || var1.getItem() == Items.NETHERITE_SWORD
            || var1.getItem() == Items.IRON_SWORD
            || var1.getItem() == Items.GOLDEN_SWORD
            || var1.getItem() == Items.STONE_SWORD
            || var1.getItem() == Items.WOODEN_SWORD;
    }

    public static boolean b(Object[] var0) {
        ItemStack var3 = (ItemStack)var0[0];
        return var3.getItem() == Items.DIAMOND_AXE
            || var3.getItem() == Items.NETHERITE_AXE
            || var3.getItem() == Items.IRON_AXE
            || var3.getItem() == Items.GOLDEN_AXE
            || var3.getItem() == Items.STONE_AXE
            || var3.getItem() == Items.WOODEN_AXE;
    }

    public static boolean c(Object[] var0) {
        ItemStack var3 = (ItemStack)var0[0];
        return var3.getItem() == Items.DIAMOND_PICKAXE
            || var3.getItem() == Items.NETHERITE_PICKAXE
            || var3.getItem() == Items.IRON_PICKAXE
            || var3.getItem() == Items.GOLDEN_PICKAXE
            || var3.getItem() == Items.STONE_PICKAXE
            || var3.getItem() == Items.WOODEN_PICKAXE;
    }

    public static boolean e(Object[] var0) {
        int var4 = (Integer)var0[0];
        int var5 = (Integer)var0[1];
        SlotActionType var3 = (SlotActionType)var0[2];
        if (!(b.currentScreen instanceof InventoryScreen)) {
            b.interactionManager.clickSlot(b.player.currentScreenHandler.syncId, var4, var5, var3, b.player);
            return false;
        } else {
            b.interactionManager.clickSlot(((InventoryScreen)b.currentScreen).getScreenHandler().syncId, var4, var5, var3, b.player);
            return true;
        }
    }

    public static boolean f(Object[] var0) {
        int var3 = (Integer)var0[0];
        int var4 = (Integer)var0[1];
        long var1 = (Long)var0[2];
        var1 = 91984986229325L ^ var1;
        long var5 = var1 ^ 139299028015949L;
        long var7 = var1 ^ 15339803618154L;
        Event.b();
        boolean var10 = b.currentScreen instanceof InventoryScreen;
        if (Prestige.u == null) {
            Object[] var12 = new Object[]{null, null, SlotActionType.SWAP, var5};
            var12[1] = var4;
            var12[0] = var3;
            e(var12);
            return var10;
        } else {
            PacketQueue var10000 = Prestige.u;
            Object[] var10005 = new Object[]{null, null, var7};
            var10005[1] = var4;
            var10005[0] = var3;
            var10000.a(var10005);
            return var10;
        }
    }

    public static boolean g(Object[] var0) {
        int var5 = (Integer)var0[0];
        int var4 = (Integer)var0[1];
        SlotActionType var3 = (SlotActionType)var0[2];
        if (!(b.player.currentScreenHandler instanceof GenericContainerScreenHandler)) {
            return false;
        } else {
            b.interactionManager.clickSlot(b.player.currentScreenHandler.syncId, var5, var4, var3, b.player);
            return true;
        }
    }

    public static boolean h(Object[] var0) {
        Item var1 = (Item)var0[0];
        long var2 = (Long)var0[1];
        var2 = 91984986229325L ^ var2;
        long var4 = var2 ^ 100351665601188L;
        long var6 = var2 ^ 74535592989125L;
        Event.b();
        Integer var9 = a_Integer(new Object[]{var1, var4});
        if (var9 == null) {
            return false;
        } else {
            int var10000 = var9;
            Object[] var10003 = new Object[]{null, var6};
            var10003[0] = var10000;
            a_void________(var10003);
            return true;
        }
    }

    public static boolean i(Object[] var0) {
        Block var1 = (Block)var0[0];
        long var2 = (Long)var0[1];
        var2 = 91984986229325L ^ var2;
        long var4 = var2 ^ 140376941298418L;
        return h(new Object[]{var1.asItem(), var4});
    }

    public static void a_void________(Object[] var0) {
        int var1 = (Integer)var0[0];
        if (var1 <= 8 && var1 >= 0) {
            b.player.getInventory().setSelectedSlot(var1);
        }
    }

    public static void b_void__(Object[] var0) {
        int var5 = (Integer)var0[0];
        Runnable var1 = (Runnable)var0[1];
        boolean var4 = (Boolean)var0[2];
        long var2 = (Long)var0[3];
        var2 = 91984986229325L ^ var2;
        long var6 = var2 ^ 71766697816248L;
        long var8 = var2 ^ 27939443117419L;
        int var11 = a_int_____(new Object[]{var8});
        Event.b();
        Object[] var10004 = new Object[]{null, var6};
        var10004[0] = var5;
        a_void________(var10004);
        var1.run();
        if (var4) {
            Object[] var10003 = new Object[]{null, var6};
            var10003[0] = var11;
            a_void________(var10003);
        }
    }

    public static boolean j(Object[] var0) {
        Item var5 = (Item)var0[0];
        Runnable var2 = (Runnable)var0[1];
        boolean var1 = (Boolean)var0[2];
        long var3 = (Long)var0[3];
        var3 = 91984986229325L ^ var3;
        long var6 = var3 ^ 12307063424163L;
        long var8 = var3 ^ 1452707992887L;
        Event.b();
        Integer var11 = a_Integer(new Object[]{var5, var8});
        if (var11 == null) {
            return false;
        } else {
            int var10000 = var11;
            Object[] var10005 = new Object[]{null, null, null, var6};
            var10005[2] = var1;
            var10005[1] = var2;
            var10005[0] = var10000;
            b_void__(var10005);
            return true;
        }
    }

    public static boolean k(Object[] var0) {
        Block var5 = (Block)var0[0];
        Runnable var1 = (Runnable)var0[1];
        boolean var4 = (Boolean)var0[2];
        long var2 = (Long)var0[3];
        var2 = 91984986229325L ^ var2;
        long var6 = var2 ^ 71279824906444L;
        Item var10000 = var5.asItem();
        Object[] var10005 = new Object[]{null, null, null, var6};
        var10005[2] = var4;
        var10005[1] = var1;
        var10005[0] = var10000;
        return j(var10005);
    }

    public static Integer a_Integer(Object[] var0) {
        Item var3 = (Item)var0[0];
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (var6.getItem() == var3) {
                return var5;
            }
        }

        return null;
    }

    public static Integer b_Integer(Object[] var0) {
        Item var1 = (Item)var0[0];
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (var6.getItem() != var1 && var6.getItem() != Items.AIR) {
                return var5;
            }
        }

        return null;
    }

    public static Integer c_Integer(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 91984986229325L ^ var1;
        long var3 = var1 ^ 66031891214505L;
        Event.b();

        for (int var6 = 0; var6 <= 8; var6++) {
            ItemStack var7 = b.player.getInventory().getStack(var6);
            if (o(new Object[]{var7, var3})) {
                return var6;
            }
        }

        return null;
    }

    public static Integer d(Object[] var0) {
        Block var1 = (Block)var0[0];
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (var6.getItem() == var1.asItem()) {
                return var5;
            }
        }

        return null;
    }

    public static List a_List(Object[] var0) {
        Item var1 = (Item)var0[0];
        boolean var4 = (Boolean)var0[1];
        Event.b();
        ArrayList var6 = new ArrayList();

        for (int var7 = var4 ? 0 : 9; var7 <= 35; var7++) {
            if (b.player.getInventory().getStack(var7).getItem() == var1) {
                var6.add(var7);
            }
        }

        return var6;
    }

    public static List b_List(Object[] var0) {
        Item var2 = (Item)var0[0];
        boolean var1 = (Boolean)var0[1];
        Event.b();
        ArrayList var6 = new ArrayList();

        for (int var7 = var1 ? 0 : 9; var7 <= 35; var7++) {
            if (b.player.getInventory().getStack(var7).getItem() == var2) {
                var6.add(var7);
            }
        }

        return var6;
    }

    public static boolean l(Object[] var0) {
        Item var3 = (Item)var0[0];
        return b.player.getMainHandStack().getItem() == var3;
    }

    public static Item a_class_1792(Object[] var0) {
        return b.player.getMainHandStack().getItem();
    }

    public static boolean m(Object[] var0) {
        Item var3 = (Item)var0[0];
        return b.player.getOffHandStack().getItem() == var3;
    }

    public static int a_int_____(Object[] var0) {
        return b.player.getInventory().getSelectedSlot();
    }

    public static Integer e_Integer(Object[] var0) {
        int var1 = (Integer)var0[0];
        int var5 = (Integer)var0[1];
        RegistryEntry var2 = (RegistryEntry)var0[2];
        long var3 = (Long)var0[3];
        var3 = 91984986229325L ^ var3;
        long var6 = var3 ^ 19397901641776L;
        Event.b();

        for (int var9 = var1; var9 < var5; var9++) {
            ItemStack var10 = b.player.getInventory().getStack(var9);
            if (var10.getItem() == Items.SPLASH_POTION && (var2 == null || n(new Object[]{var10, var2, var6}))) {
                return var9;
            }
        }

        return null;
    }

    public static boolean n(Object[] var0) {
        ItemStack var1 = (ItemStack)var0[0];
        RegistryEntry var2 = (RegistryEntry)var0[1];
        Event.b();
        boolean var6 = false;

        for (StatusEffectInstance var8 : var1.get(DataComponentTypes.POTION_CONTENTS).getEffects()) {
            if (var8.getEffectType() == var2) {
                var6 = true;
            }
        }

        return var6;
    }

    public static boolean o(Object[] var0) {
        ItemStack var1 = (ItemStack)var0[0];
        return var1.getItem().equals(Items.MACE);
    }

    public static boolean p(Object[] var0) {
        ItemStack var3 = (ItemStack)var0[0];
        return var3.getItem().equals(Items.WIND_CHARGE);
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
