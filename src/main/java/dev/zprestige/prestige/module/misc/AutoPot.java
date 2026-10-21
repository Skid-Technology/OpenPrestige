package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Hand;

public class AutoPot extends Module {
    public AutoPot() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private FloatSetting c;
    private FloatSetting d;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.UHC);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            boolean var15 = ((String)this.a.getValue()).equals("Auto");
            if (var15 && b.player.getHealth() > (Float)this.c.getValue()) {
                this.e.a_void_____________________(new Object[]{93540861878173L});
            } else {
                RenderHelper27 var10000 = this.e;
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = 300.0F;
                if (var10000.a(var10004)) {
                    if (var15) {
                        Object[] var10005 = new Object[]{null, null, StatusEffects.INSTANT_HEALTH, 16638224401259L};
                        var10005[1] = 9;
                        var10005[0] = 0;
                        Integer var16 = InventoryUtil.e_Integer(var10005);
                        if (var16 == null) {
                            return;
                        }

                        int var18 = var16;
                        Runnable var10001 = () -> {
                            float var5 = b.player.getPitch();
                            b.player.setPitch((Float)this.d.getValue() - 1.0F + RenderUtil5.a.nextFloat());
                            InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                            b.player.setPitch(var5);
                        };
                        var10005 = new Object[]{null, null, null, 53805837885570L};
                        var10005[2] = true;
                        var10005[1] = var10001;
                        var10005[0] = var18;
                        InventoryUtil.b_void__(var10005);
                        this.e.a_void_____________________(new Object[]{93540861878173L});
                    } else {
                        float var17 = b.player.getPitch();
                        b.player.setPitch((Float)this.d.getValue() - 1.0F + RenderUtil5.a.nextFloat());
                        InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                        b.player.setPitch(var17);
                        this.e.a_void_____________________(new Object[]{93540861878173L});
                    }
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
