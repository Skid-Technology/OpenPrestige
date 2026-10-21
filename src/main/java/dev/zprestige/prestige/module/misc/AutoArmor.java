package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.AutoArmorHelper;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import org.lwjgl.glfw.GLFW;

public class AutoArmor extends Module {
    public AutoArmor() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private KeybindSetting f;
    private BoolSetting d;
    private IntSetting c;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);
    private static final EquipmentSlot[] g = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player != null && b.world != null) {
            RenderHelper27 var10000 = this.e;
            float var10001 = ((Integer)this.c.getValue()).intValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                if (this.d(new Object[]{51031871974225L})) {
                    for (EquipmentSlot var16 : g) {
                        if (this.a(new Object[]{var16, 99166558571491L})) {
                            this.e.a_void_____________________(new Object[]{93540861878173L});
                            return;
                        }
                    }
                }
            }
        }
    }

    private boolean a(Object[] var1) {
        EquipmentSlot var4 = (EquipmentSlot)var1[0];
        long var2 = (Long)var1[1];
        var2 = 125155694694942L ^ var2;
        long var5 = var2 ^ 48703979815350L;
        long var7 = var2 ^ 140371236960994L;
        long var9 = var2 ^ 32465603899819L;
        long var11 = var2 ^ 87340381176099L;
        long var13 = var2 ^ 99259568632757L;
        Event.b();
        ItemStack var16 = b.player.getEquippedStack(var4);
        if (var4 == EquipmentSlot.CHEST && !var16.isEmpty() && var16.getItem() == Items.ELYTRA) {
            return false;
        } else if (!var16.isEmpty() && e(new Object[]{var16, Enchantments.BINDING_CURSE, var11})) {
            return false;
        } else {
            int var17 = var16.isEmpty() ? -1 : c(new Object[]{var16, var9});
            int var18 = -1;
            int var19 = var17;

            for (int var20 = 0; var20 < 36; var20++) {
                ItemStack var21 = b.player.getInventory().getStack(var20);
                if (!var21.isEmpty()
                    && a_class_1304(new Object[]{var21.getItem(), var5}) == var4
                    && (
                        !(Boolean)this.d.getValue()
                            || !e(new Object[]{var21, Enchantments.BINDING_CURSE, var11}) && !e(new Object[]{var21, Enchantments.VANISHING_CURSE, var11})
                    )) {
                    int var22 = c(new Object[]{var21, var9});
                    if (var22 > var19) {
                        var19 = var22;
                        var18 = var20 < 9 ? 36 + var20 : var20;
                    }
                }
            }

            if (var18 == -1) {
                return false;
            } else {
                int var24 = a_int_____________(new Object[]{var4, var13});
                Object[] var10005 = new Object[]{null, null, SlotActionType.PICKUP, var7};
                var10005[1] = 0;
                var10005[0] = var18;
                InventoryUtil.e(var10005);
                var10005 = new Object[]{null, null, SlotActionType.PICKUP, var7};
                var10005[1] = 0;
                var10005[0] = var24;
                InventoryUtil.e(var10005);
                var10005 = new Object[]{null, null, SlotActionType.PICKUP, var7};
                var10005[1] = 0;
                var10005[0] = var18;
                InventoryUtil.e(var10005);
                return true;
            }
        }
    }

    private boolean d(Object[] var1) {
        Event.b();
        String var5 = (String)this.a.getValue();

        return switch (var5) {
            case "Inventory" -> b.currentScreen instanceof InventoryScreen;
            case "Keybind" -> {
                int var7 = (Integer)this.f.getValue();
                yield var7 != -1 && GLFW.glfwGetKey(b.getWindow().getHandle(), var7) == 1;
            }
            default -> true;
        };
    }

    private static int a_int_____________(Object[] var0) {
        EquipmentSlot var1 = (EquipmentSlot)var0[0];

        return switch (AutoArmorHelper.a[var1.ordinal()]) {
            case 1 -> 5;
            case 2 -> 6;
            case 3 -> 7;
            case 4 -> 8;
            default -> -1;
        };
    }

    private static EquipmentSlot a_class_1304(Object[] var0) {
        Item var3 = (Item)var0[0];
        if (var3 == Items.NETHERITE_HELMET
            || var3 == Items.DIAMOND_HELMET
            || var3 == Items.IRON_HELMET
            || var3 == Items.GOLDEN_HELMET
            || var3 == Items.CHAINMAIL_HELMET
            || var3 == Items.LEATHER_HELMET
            || var3 == Items.TURTLE_HELMET) {
            return EquipmentSlot.HEAD;
        } else if (var3 == Items.NETHERITE_CHESTPLATE
            || var3 == Items.DIAMOND_CHESTPLATE
            || var3 == Items.IRON_CHESTPLATE
            || var3 == Items.GOLDEN_CHESTPLATE
            || var3 == Items.CHAINMAIL_CHESTPLATE
            || var3 == Items.LEATHER_CHESTPLATE) {
            return EquipmentSlot.CHEST;
        } else if (var3 == Items.NETHERITE_LEGGINGS
            || var3 == Items.DIAMOND_LEGGINGS
            || var3 == Items.IRON_LEGGINGS
            || var3 == Items.GOLDEN_LEGGINGS
            || var3 == Items.CHAINMAIL_LEGGINGS
            || var3 == Items.LEATHER_LEGGINGS) {
            return EquipmentSlot.LEGS;
        } else {
            return var3 != Items.NETHERITE_BOOTS
                    && var3 != Items.DIAMOND_BOOTS
                    && var3 != Items.IRON_BOOTS
                    && var3 != Items.GOLDEN_BOOTS
                    && var3 != Items.CHAINMAIL_BOOTS
                    && var3 != Items.LEATHER_BOOTS
                ? null
                : EquipmentSlot.FEET;
        }
    }

    private static int c(Object[] var0) {
        ItemStack var1 = (ItemStack)var0[0];
        long var2 = (Long)var0[1];
        var2 = 125155694694942L ^ var2;
        long var4 = var2 ^ 89313523196562L;
        long var6 = var2 ^ 57879653837780L;
        int var8 = d_int________(new Object[]{var1.getItem(), var4}) * 100;
        var8 += e_int______(new Object[]{var1, Enchantments.PROTECTION, var6}) * 5;
        var8 += e_int______(new Object[]{var1, Enchantments.BLAST_PROTECTION, var6}) * 3;
        var8 += e_int______(new Object[]{var1, Enchantments.PROJECTILE_PROTECTION, var6}) * 3;
        var8 += e_int______(new Object[]{var1, Enchantments.FIRE_PROTECTION, var6}) * 2;
        var8 += e_int______(new Object[]{var1, Enchantments.THORNS, var6}) * 2;
        var8 += e_int______(new Object[]{var1, Enchantments.MENDING, var6}) * 3;
        var8 += e_int______(new Object[]{var1, Enchantments.UNBREAKING, var6}) * 1;
        var8 += e_int______(new Object[]{var1, Enchantments.FEATHER_FALLING, var6}) * 3;
        var8 += e_int______(new Object[]{var1, Enchantments.DEPTH_STRIDER, var6}) * 1;
        var8 += e_int______(new Object[]{var1, Enchantments.SOUL_SPEED, var6}) * 1;
        var8 += e_int______(new Object[]{var1, Enchantments.AQUA_AFFINITY, var6}) * 1;
        var8 += e_int______(new Object[]{var1, Enchantments.RESPIRATION, var6}) * 1;
        return var8 + e_int______(new Object[]{var1, Enchantments.SWIFT_SNEAK, var6}) * 2;
    }

    private static int d_int________(Object[] var0) {
        Item var3 = (Item)var0[0];
        if (var3 == Items.NETHERITE_HELMET || var3 == Items.NETHERITE_CHESTPLATE || var3 == Items.NETHERITE_LEGGINGS || var3 == Items.NETHERITE_BOOTS) {
            return 60;
        } else if (var3 == Items.DIAMOND_HELMET || var3 == Items.DIAMOND_CHESTPLATE || var3 == Items.DIAMOND_LEGGINGS || var3 == Items.DIAMOND_BOOTS) {
            return 50;
        } else if (var3 == Items.IRON_HELMET
            || var3 == Items.IRON_CHESTPLATE
            || var3 == Items.IRON_LEGGINGS
            || var3 == Items.IRON_BOOTS
            || var3 == Items.TURTLE_HELMET) {
            return 30;
        } else if (var3 == Items.CHAINMAIL_HELMET || var3 == Items.CHAINMAIL_CHESTPLATE || var3 == Items.CHAINMAIL_LEGGINGS || var3 == Items.CHAINMAIL_BOOTS) {
            return 20;
        } else if (var3 == Items.GOLDEN_HELMET || var3 == Items.GOLDEN_CHESTPLATE || var3 == Items.GOLDEN_LEGGINGS || var3 == Items.GOLDEN_BOOTS) {
            return 15;
        } else {
            return var3 != Items.LEATHER_HELMET && var3 != Items.LEATHER_CHESTPLATE && var3 != Items.LEATHER_LEGGINGS && var3 != Items.LEATHER_BOOTS ? 0 : 10;
        }
    }

    private static boolean e(Object[] var0) {
        ItemStack var4 = (ItemStack)var0[0];
        RegistryKey var3 = (RegistryKey)var0[1];
        long var1 = (Long)var0[2];
        var1 = 125155694694942L ^ var1;
        long var5 = var1 ^ 112482250677084L;
        return e_int______(new Object[]{var4, var3, var5}) > 0;
    }

    private static int e_int______(Object[] var0) {
        ItemStack var4 = (ItemStack)var0[0];
        RegistryKey var3 = (RegistryKey)var0[1];
        if (var4.isEmpty()) {
            return 0;
        } else {
            for (Entry var7 : var4.getEnchantments().getEnchantmentEntries()) {
                if (((RegistryEntry)var7.getKey()).matchesKey(var3)) {
                    return var7.getIntValue();
                }
            }

            return 0;
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
