package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent12;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;

public class BreachSwap extends Module {
    public BreachSwap() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private int c = -1;
    private int d = 0;
    private static final long l = 5554404204296011784L;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.d == 0) {
            if (this.c != -1) {
                int var10000 = this.c;
                Object[] var10003 = new Object[]{null, 37392877673079L};
                var10003[0] = var10000;
                InventoryUtil.a_void________(var10003);
                this.c = -1;
            }
        } else {
            this.d--;
        }
    }

    @Subscribe
    public void a(TickEvent12 var1) {
        if (RenderUtil5.a.nextFloat() * 100.0F > (Float)this.a.getValue()) {
            this.d = 1;
        }

        if (this.d <= 0) {
            if (!(b.player.fallDistance > 1.5)) {
                if (this.c == -1 && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                    this.c = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                    int var13 = this.a(new Object[]{2715653746745L});
                    if (var13 != -1) {
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var13;
                        InventoryUtil.a_void________(var10003);
                        this.d = 1;
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (RenderUtil5.a.nextFloat() * 100.0F > (Float)this.a.getValue()) {
            this.d = 1;
        }

        if (this.d <= 0) {
            if (!(b.player.fallDistance > 1.5)) {
                if (this.c == -1 && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                    this.c = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                    int var13 = this.a(new Object[]{2715653746745L});
                    if (var13 != -1) {
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var13;
                        InventoryUtil.a_void________(var10003);
                        this.d = 1;
                    }
                }
            }
        }
    }

    public int a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 115104592900024L ^ var2;
        long var4 = var2 ^ 130817381454573L;
        long var6 = var2 ^ 21641841522365L;
        Event.b();
        ArrayList var9 = new ArrayList();

        for (int var10 = 0; var10 <= (int)l; var10++) {
            if (InventoryUtil.o(new Object[]{b.player.getInventory().getStack(var10), var6})) {
                var9.add(var10);
            }
        }

        int var16 = -1;
        int var11 = -1;

        for (int var13 : (Iterable<Integer>)(var9)) {
            Object[] var10004 = new Object[]{null, var4};
            var10004[0] = var13;
            int var14 = this.c(var10004);
            if (var14 > var16) {
                var16 = var14;
                var11 = var13;
            }
        }

        return var11;
    }

    public int c(Object[] var1) {
        int var4 = (Integer)var1[0];
        Event.b();
        ItemStack var6 = b.player.getInventory().getStack(var4);
        if (var6 != null) {
            float var7 = EnchantmentHelper.getLevel(
                b.world.getRegistryManager().getOptional(Enchantments.BREACH.getRegistryRef()).get().getEntry(Enchantments.BREACH.getValue()).get(), var6
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
