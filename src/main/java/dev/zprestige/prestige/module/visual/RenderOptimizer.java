package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.TickEvent;
import dev.zprestige.prestige.event.TickEvent18;
import dev.zprestige.prestige.event.TickEvent2;
import dev.zprestige.prestige.event.TickEvent20;
import dev.zprestige.prestige.event.TickEvent21;
import dev.zprestige.prestige.event.TickEvent23;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.event.TickEvent26;
import dev.zprestige.prestige.event.TickEvent31;
import dev.zprestige.prestige.event.TickEvent8;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RenderOptimizer extends Module {
    public RenderOptimizer() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;
    private BoolSetting c;
    private BoolSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private BoolSetting k;

    @Subscribe
    public void a(TickEvent20 var1) {
        if ((Boolean)this.d.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent var1) {
        if ((Boolean)this.a.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent21 var1) {
        if ((Boolean)this.c.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent8 var1) {
        if ((Boolean)this.e.getValue() && b.world != null) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent2 var1) {
        if ((Boolean)this.f.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent23 var1) {
        if ((Boolean)this.g.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent18 var1) {
        if ((Boolean)this.h.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent31 var1) {
        if ((Boolean)this.i.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if ((Boolean)this.j.getValue()) {
            b.inGameHud.clearTitle();
        }
    }

    @Subscribe
    public void a(TickEvent26 var1) {
        if ((Boolean)this.k.getValue()) {
            var1.a_void_____(new Object[0]);
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
