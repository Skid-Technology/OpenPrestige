package dev.zprestige.prestige.module.spear;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.network.NetworkUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.PiercingWeaponComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class InstantLunge extends Module {
    public InstantLunge() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private RangeSetting a;
    private int c = -1;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Spear);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        int var21 = this.a(new Object[]{126020033099551L});
        if (this.c != -1) {
            if (this.e.b(new Object[]{this.a, 89714527855213L})) {
                int var10000 = this.c;
                Object[] var24 = new Object[]{null, 37392877673079L};
                var24[0] = var10000;
                InventoryUtil.a_void________(var24);
                this.c = -1;
                this.toggle(new Object[]{115103427876780L});
            }
        } else if (var21 != -1) {
            if (!b.player.isUsingItem()) {
                if (b.player.getHungerManager().getFoodLevel() >= 6) {
                    if (InventoryUtil.a_int_____(new Object[]{134339616728996L}) == var21) {
                        NetworkUtil.a(new Object[]{new PlayerActionC2SPacket(Action.STAB, BlockPos.ORIGIN, Direction.DOWN), 53690043409678L});
                        this.toggle(new Object[]{115103427876780L});
                    } else {
                        this.c = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var21;
                        InventoryUtil.a_void________(var10003);
                        ItemStack var22 = b.player.getMainHandStack();
                        PiercingWeaponComponent var23 = var22.get(DataComponentTypes.PIERCING_WEAPON);
                        if (var23 != null && !b.interactionManager.isFlyingLocked()) {
                            b.interactionManager.attackWithPiercingWeapon(var23);
                            b.player.swingHand(Hand.MAIN_HAND);
                        }

                        if (!(Boolean)this.d.getValue()) {
                            this.c = -1;
                            this.toggle(new Object[]{115103427876780L});
                        } else {
                            this.e.a_void_____________________(new Object[]{93540861878173L});
                            this.a.reroll(new Object[]{57083216809697L});
                        }
                    }
                }
            }
        }
    }

    private int a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 47745533966094L ^ var2;
        long var4 = var2 ^ 678305512337L;
        Event.b();

        for (int var7 = 0; var7 < 9; var7++) {
            ItemStack var8 = b.player.getInventory().getStack(var7);
            if (var8.isIn(ItemTags.SPEARS)) {
                boolean var9 = c(new Object[]{var8, Enchantments.LUNGE, var4}) > 0;
                if (var9) {
                    return var7;
                }
            }
        }

        return -1;
    }

    public static int c(Object[] var0) {
        ItemStack var4 = (ItemStack)var0[0];
        RegistryKey var3 = (RegistryKey)var0[1];
        long var1 = (Long)var0[2];
        var1 = 47745533966094L ^ var1;
        long var5 = var1 ^ 24961913191706L;
        long var7 = var1 ^ 39617031938216L;
        if (var4.isEmpty()) {
            return 0;
        } else {
            Object2IntArrayMap var10 = new Object2IntArrayMap();
            j(new Object[]{var4, var10, var5});
            return d(new Object[]{var10, var3, var7});
        }
    }

    public static int d(Object[] var0) {
        Object2IntMap var2 = (Object2IntMap)var0[0];
        RegistryKey var1 = (RegistryKey)var0[1];
        Event.b();

        for (Entry var7 : (Iterable<Entry>)(Object2IntMaps.fastIterable(var2))) {
            if (((RegistryEntry)var7.getKey()).matchesKey(var1)) {
                return var7.getIntValue();
            }
        }

        return 0;
    }

    public static void j(Object[] var0) {
        ItemStack var2 = (ItemStack)var0[0];
        Object2IntMap var1 = (Object2IntMap)var0[1];
        Event.b();
        var1.clear();
        if (!var2.isEmpty()) {
            for (Entry var8 : var2.getItem() == Items.ENCHANTED_BOOK
                ? var2.getOrDefault(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT).getEnchantmentEntries()
                : var2.getEnchantments().getEnchantmentEntries()) {
                var1.put((RegistryEntry)var8.getKey(), var8.getIntValue());
            }
        }
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
