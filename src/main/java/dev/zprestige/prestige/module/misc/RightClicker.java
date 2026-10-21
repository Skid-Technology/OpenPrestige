package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.AimManager;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public class RightClicker extends Module {
    public RightClicker() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private FloatSetting c;
    private RenderHelper27 d = new RenderHelper27(26293358099357L);
    private float e = 200.0F;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 55416263999342L;
        this.j(new Object[]{var4});
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen == null && b.player != null && !b.player.isUsingItem() && !Prestige.j.a(new Object[]{46247009548246L})) {
            RenderHelper27 var10000 = this.d;
            float var10001 = this.e;
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                AimManager var2 = Prestige.j;
                Object[] var10005 = new Object[]{null, null, 60507167928612L};
                var10005[1] = 0.0F;
                var10005[0] = 1;
                var2.a_void_(var10005);
                this.j(new Object[]{86061019419240L});
                this.d.a_void_____________________(new Object[]{93540861878173L});
            }
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11956094678555L ^ var2;
        long var4 = var2 ^ 103630454996286L;
        float var10002 = Math.max((Float)this.a.getValue(), 1.0F);
        float var10003 = (Float)this.c.getValue();
        Object[] var10006 = new Object[]{null, null, var4};
        var10006[1] = var10003;
        var10006[0] = var10002;
        this.e = 1000.0F / RenderUtil5.a(var10006);
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
