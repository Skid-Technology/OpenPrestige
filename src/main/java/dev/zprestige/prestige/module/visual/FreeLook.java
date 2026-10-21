package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.DoubleEvent2;
import dev.zprestige.prestige.event.FloatEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.option.Perspective;

public class FreeLook extends Module {
    public FreeLook() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private float a = 0.0F;
    private float c = 0.0F;
    private Perspective e = null;

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Subscribe
    public void a(DoubleEvent2 var1) {
        this.a = (float)(this.a + var1.a(new Object[0]) * 0.1F);
        this.c = (float)(this.c + var1.b(new Object[0]) * 0.1F);
        var1.a_void_____(new Object[0]);
    }

    @Subscribe
    public void a(FloatEvent var1) {
        var1.b(new Object[]{this.a});
        var1.c(new Object[]{this.c});
        var1.a_void_____(new Object[0]);
    }

    @Override
    public void onEnable(Object[] var1) {
        this.a = b.player.getYaw();
        this.c = b.player.getPitch();
        this.e = b.options.getPerspective();
        if ((Boolean)this.d.getValue()) {
            b.options.setPerspective(Perspective.THIRD_PERSON_BACK);
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        b.options.setPerspective(this.e);
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
