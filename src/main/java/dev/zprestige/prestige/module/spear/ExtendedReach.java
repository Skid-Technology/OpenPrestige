package dev.zprestige.prestige.module.spear;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent15;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.world.WorldUtil;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;

public class ExtendedReach extends Module {
    private FloatSetting a;
    private MultiSetting c;
    private BoolSetting d;
    public static ExtendedReach e;
    private int f = -1;
    private int g = 0;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Spear);
    }

    public ExtendedReach() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        e = this;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.g == 0) {
            if (this.f != -1) {
                int var10000 = this.f;
                Object[] var10003 = new Object[]{null, 37392877673079L};
                var10003[0] = var10000;
                InventoryUtil.a_void________(var10003);
                this.f = -1;
            }
        } else {
            this.g--;
        }
    }

    @Subscribe
    public void a(TickEvent15 var1) {
        this.j(new Object[]{78860863667161L});
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        this.j(new Object[]{78860863667161L});
    }

    public void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 26766769286092L ^ var2;
        long var4 = var2 ^ 34708039823124L;
        long var6 = var2 ^ 75225366092527L;
        long var8 = var2 ^ 35622498021749L;
        long var10 = var2 ^ 138414233307746L;
        long var12 = var2 ^ 41572182259633L;
        if (a_class_1297____(new Object[]{var6}) != null) {
            if (RenderUtil5.a.nextFloat() * 100.0F > (Float)this.a.getValue()) {
                this.g = 1;
            }

            if (this.g <= 0) {
                if (!(b.player.fallDistance > 1.5)) {
                    if (this.f == -1) {
                        if (this.d(new Object[]{var4})) {
                            this.f = InventoryUtil.a_int_____(new Object[]{var12});
                            int var15 = a(new Object[]{var8});
                            if (var15 != -1) {
                                Object[] var10003 = new Object[]{null, var10};
                                var10003[0] = var15;
                                InventoryUtil.a_void________(var10003);
                                this.g = 1;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 26766769286092L ^ var2;
        long var4 = var2 ^ 48413586181362L;
        long var6 = var2 ^ 68343422288032L;
        long var8 = var2 ^ 127187768777767L;
        long var10 = var2 ^ 36633557319153L;
        return this.c.isSelected(new Object[]{"Sword", var8}) && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), var4})
            || this.c.isSelected(new Object[]{"Axe", var8}) && InventoryUtil.b(new Object[]{b.player.getMainHandStack(), var6})
            || this.c.isSelected(new Object[]{"Mace", var8}) && InventoryUtil.o(new Object[]{b.player.getMainHandStack(), var10});
    }

    private static int a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 26766769286092L ^ var1;
        long var3 = var1 ^ 43224967540481L;
        Event.b();

        for (int var6 = 0; var6 < 9; var6++) {
            ItemStack var7 = b.player.getInventory().getStack(var6);
            if (var7.isIn(ItemTags.SPEARS)) {
                boolean var8 = c(new Object[]{var7, Enchantments.LUNGE, var3}) == 0;
                if (var8) {
                    return var6;
                }
            }
        }

        return -1;
    }

    public static int c(Object[] var0) {
        ItemStack var4 = (ItemStack)var0[0];
        RegistryKey var1 = (RegistryKey)var0[1];
        long var2 = (Long)var0[2];
        var2 = 26766769286092L ^ var2;
        long var5 = var2 ^ 15191465325281L;
        long var7 = var2 ^ 62451506062620L;
        if (var4.isEmpty()) {
            return 0;
        } else {
            Object2IntArrayMap var10 = new Object2IntArrayMap();
            k(new Object[]{var4, var10, var5});
            return d_int__________(new Object[]{var10, var1, var7});
        }
    }

    public static int d_int__________(Object[] var0) {
        Object2IntMap var1 = (Object2IntMap)var0[0];
        RegistryKey var2 = (RegistryKey)var0[1];
        Event.b();

        for (Entry var7 : (Iterable<Entry>)(Object2IntMaps.fastIterable(var1))) {
            if (((RegistryEntry)var7.getKey()).matchesKey(var2)) {
                return var7.getIntValue();
            }
        }

        return 0;
    }

    public static void k(Object[] var0) {
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

    public static Entity a_class_1297____(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 26766769286092L ^ var1;
        long var3 = var1 ^ 136703853985878L;
        long var5 = var1 ^ 22070413222523L;
        if (!e.isEnabled()) {
            return null;
        } else if (a(new Object[]{var3}) == -1) {
            return null;
        } else if (b.player.getAttackCooldownProgress(0.0F) < 1.0F) {
            return null;
        } else if (b.crosshairTarget instanceof EntityHitResult) {
            return null;
        } else {
            RenderBatch var10000 = new RenderBatch(b.player.getYaw(), b.player.getPitch());
            Object[] var10004 = new Object[]{null, null, var5};
            var10004[1] = 4.75F;
            var10004[0] = var10000;
            HitResult var8 = WorldUtil.b(var10004);
            if (var8.getType() != Type.MISS) {
                return null;
            } else {
                Vec3d var9 = var8.getPos();
                Entity var10 = null;

                for (Entity var12 : b.world.getEntities()) {
                    if (var12 != b.player
                        && !(var12.distanceTo(b.player) > 6.0F)
                        && b.player.canSee(var12)
                        && var12.getBoundingBox().intersects(b.player.getEyePos(), var9)) {
                        var10 = var12;
                        break;
                    }
                }

                return var10;
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
