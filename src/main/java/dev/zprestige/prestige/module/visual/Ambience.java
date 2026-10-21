package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.ColorEvent;
import dev.zprestige.prestige.event.FloatColorEvent;
import dev.zprestige.prestige.event.LongEvent;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public class Ambience extends Module {
    private BoolSetting d;
    private FloatSetting a;
    private FloatSetting b;
    private ColorSetting c;
    private BoolSetting e;
    private BoolSetting f;
    private ColorSetting g;
    private BoolSetting h;
    private IntSetting i;

    public Ambience() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.g.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL81_0 -> (Boolean)this.f.getValue(), 119980880492450L});
        this.c.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL83_0 -> (Boolean)this.d.getValue(), 119980880492450L});
        this.i.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL80_0 -> (Boolean)this.h.getValue(), 98265949679296L});
        this.e.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL82_0 -> (Boolean)this.d.getValue(), 73196168581295L});
        this.a.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL85_0 -> (Boolean)this.d.getValue(), 35046756052036L});
        this.b.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL84_0 -> (Boolean)this.d.getValue(), 35046756052036L});
    }

    @Subscribe
    public void a(FloatColorEvent var1) {
        if ((Boolean)this.d.getValue()) {
            float var5 = Math.min((Float)this.a.getValue(), (Float)this.b.getValue());
            float var6 = Math.max((Float)this.a.getValue(), (Float)this.b.getValue());
            var1.c(new Object[]{var5});
            var1.d(new Object[]{var6});
            var1.b(new Object[]{(Color)this.c.getValue()});
            if ((Boolean)this.e.getValue()) {
                var1.e(new Object[]{var6});
            }

            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(ColorEvent var1) {
        if ((Boolean)this.f.getValue()) {
            Color var5 = (Color)this.g.getValue();
            var1.b(new Object[]{var5});
            var1.c(new Object[]{var5});
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(LongEvent var1) {
        if ((Boolean)this.h.getValue()) {
            var1.b(new Object[]{(long)((Integer)this.i.getValue()).intValue()});
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
