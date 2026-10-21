package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

public class TotemHit extends Module {
    public TotemHit() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private static final long l = -2304674206878531576L;

    @Subscribe
    public void a(EntityEvent var1) {
        Event.b();
        Entity var9 = var1.a(new Object[0]);
        if (var9 != null) {
            if (var9 instanceof PlayerEntity) {
                if (b.player.getMainHandStack().getItem() == Items.TOTEM_OF_UNDYING) {
                    Integer var10 = null;

                    for (int var11 = 0; var11 <= (int)l; var11++) {
                        if (InventoryUtil.a(new Object[]{b.player.getInventory().getStack(var11), 127680976181311L})) {
                            var10 = var11;
                            break;
                        }
                    }

                    if (var10 == null) {
                        return;
                    }

                    var1.a_void_____(new Object[0]);
                    int var10000 = var10;
                    Runnable var10001 = () -> InventoryUtil2.a_void_________(new Object[]{var1.a(new Object[0]), 31382773185830L});
                    Object[] var10005 = new Object[]{null, null, null, 53805837885570L};
                    var10005[2] = true;
                    var10005[1] = var10001;
                    var10005[0] = var10000;
                    InventoryUtil.b_void__(var10005);
                }
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
