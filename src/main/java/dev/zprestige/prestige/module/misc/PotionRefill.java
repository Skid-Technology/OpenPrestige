package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class PotionRefill extends Module {
    public PotionRefill() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private RangeSetting a;
    private RenderHelper27 c = new RenderHelper27(26293358099357L);

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.UHC);
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen instanceof InventoryScreen) {
            if (this.a(new Object[]{94901030271103L}) != null && this.c.b(new Object[]{this.a, 89714527855213L})) {
                Object[] var10005 = new Object[]{null, null, this.d.getValue() ? StatusEffects.INSTANT_HEALTH : null, 16638224401259L};
                var10005[1] = 35;
                var10005[0] = 9;
                Integer var17 = InventoryUtil.e_Integer(var10005);
                if (var17 == null) {
                    return;
                }

                int var10000 = var17;
                var10005 = new Object[]{null, null, SlotActionType.QUICK_MOVE, 92701205799711L};
                var10005[1] = 0;
                var10005[0] = var10000;
                InventoryUtil.e(var10005);
                this.a.reroll(new Object[]{57083216809697L});
                this.c.a_void_____________________(new Object[]{93540861878173L});
            }
        } else {
            this.a.reroll(new Object[]{57083216809697L});
            this.c.a_void_____________________(new Object[]{93540861878173L});
        }
    }

    private Integer a(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() == Items.AIR) {
                return var5;
            }
        }

        return null;
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
