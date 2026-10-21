package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.type.NotificationType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;

public class MiddleClickFriend extends Module {
    public MiddleClickFriend() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private boolean i = false;

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.currentScreen == null) {
            if (!b.mouse.wasMiddleButtonClicked()) {
                this.i = false;
            }

            if (!this.i) {
                if (b.mouse.wasMiddleButtonClicked() && b.crosshairTarget instanceof EntityHitResult var9) {
                    if (var9.getEntity() instanceof PlayerEntity) {
                        PlayerEntity var10 = (PlayerEntity)var9.getEntity();
                        String var11 = var10.getName().getString();
                        if (Prestige.g.a(new Object[]{var11, 24848952222090L})) {
                            Prestige.g.b(var11);
                            ModuleNotificationsElement.s(new Object[]{this, "Removed friend: " + var11, NotificationType.INFO, 111134823389700L});
                        } else {
                            Prestige.g.a(var11);
                            ModuleNotificationsElement.s(new Object[]{this, "Added friend: " + var11, NotificationType.INFO, 111134823389700L});
                        }
                    }

                    this.i = true;
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
