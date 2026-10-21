package dev.zprestige.prestige.module.spear;

import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.hit.EntityHitResult;

public class SpearMaceDoubleTap extends Module {
    public SpearMaceDoubleTap() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private RenderHelper27 c = new RenderHelper27(26293358099357L);
    private boolean i = false;
    private static final long l = 5793104364755222536L;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Spear, Gamemode.Mace);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.i) {
            RenderHelper27 var10000 = this.c;
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = 1000.0F;
            if (!var10000.a(var10004)) {
                if (!(b.crosshairTarget instanceof EntityHitResult)) {
                    return;
                }

                InventoryUtil2.a_void_________(new Object[]{((EntityHitResult)b.crosshairTarget).getEntity(), 31382773185830L});
            }
        }

        this.i = false;
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (var1.a(new Object[0]) instanceof EntityStatusS2CPacket) {
            byte var11 = ((EntityStatusS2CPacket)var1.a(new Object[0])).getStatus();
            if (var11 == 2) {
                int var12 = this.a(new Object[]{107124566716120L});
                if (var12 != -1 && b.player.fallDistance > ((Float)this.a.getValue()).floatValue()) {
                    b.player.stopUsingItem();
                    Object[] var10003 = new Object[]{null, 37392877673079L};
                    var10003[0] = var12;
                    InventoryUtil.a_void________(var10003);
                    this.c.a_void_____________________(new Object[]{93540861878173L});
                    this.i = true;
                }
            }
        }
    }

    public int a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 19636870262656L ^ var2;
        long var4 = var2 ^ 49152418905324L;
        long var6 = var2 ^ 63965945501436L;
        long var8 = var2 ^ 10757728757860L;
        Event.b();
        ArrayList var11 = new ArrayList();

        for (int var12 = 0; var12 <= (int)l; var12++) {
            if (InventoryUtil.o(new Object[]{b.player.getInventory().getStack(var12), var8})) {
                var11.add(var12);
            }
        }

        int var18 = -1;
        int var13 = -1;

        for (int var15 : (Iterable<Integer>)(var11)) {
            int var10000;
            if (b.player.fallDistance <= 7.0) {
                Object[] var10004 = new Object[]{null, var4};
                var10004[0] = var15;
                var10000 = this.d(var10004);
            } else {
                Object[] var19 = new Object[]{null, var6};
                var19[0] = var15;
                var10000 = this.c(var19);
            }

            int var16 = var10000;
            if (var16 > var18) {
                var18 = var16;
                var13 = var15;
            }
        }

        return var13;
    }

    public int c(Object[] var1) {
        int var4 = (Integer)var1[0];
        Event.b();
        ItemStack var6 = b.player.getInventory().getStack(var4);
        if (var6 != null) {
            float var7 = EnchantmentHelper.getLevel(
                b.world.getRegistryManager().getOptional(Enchantments.DENSITY.getRegistryRef()).get().getEntry(Enchantments.DENSITY.getValue()).get(), var6
            );
            return (int)var7;
        } else {
            return 0;
        }
    }

    public int d(Object[] var1) {
        int var4 = (Integer)var1[0];
        Event.b();
        ItemStack var6 = b.player.getInventory().getStack(var4);
        if (var6 != null) {
            float var7 = EnchantmentHelper.getLevel(
                b.world.getRegistryManager().getOptional(Enchantments.BREACH.getRegistryRef()).get().getEntry(Enchantments.DENSITY.getValue()).get(), var6
            );
            return (int)var7;
        } else {
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
