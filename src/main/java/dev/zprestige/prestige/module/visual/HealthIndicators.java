package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.PlayerStringEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;

public class HealthIndicators extends Module {
    public HealthIndicators() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    @Subscribe
    public void a(PlayerStringEvent var1) {
        float var8 = (float)Math.ceil(this.b_float_____________(new Object[]{var1.a(new Object[0]), 128129148455246L}));
        Object[] var10004 = new Object[]{null, 79027181785611L};
        var10004[0] = var8;
        String var10000 = String.valueOf(this.a(var10004));
        String var10 = String.valueOf(Formatting.RESET);
        String var12 = var10000;
        String var9 = " | " + var12 + var8 + var10 + " | ";
        var1.b(new Object[]{var9});
        var1.a_void_____(new Object[0]);
    }

    private Formatting a(Object[] var1) {
        float var2 = (Float)var1[0];
        if (var2 <= 5.0F) {
            return Formatting.RED;
        } else if (var2 <= 10.0F) {
            return Formatting.GOLD;
        } else if (var2 <= 15.0F) {
            return Formatting.YELLOW;
        } else {
            return var2 <= 20.0F ? Formatting.GREEN : Formatting.DARK_GREEN;
        }
    }

    private float b_float_____________(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        return var4.getHealth() + var4.getAbsorptionAmount();
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
