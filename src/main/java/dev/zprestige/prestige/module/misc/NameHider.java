package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.TextSetting;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.StringEvent2;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public class NameHider extends Module {
    private EnumSetting a;
    private TextSetting c;
    private BoolSetting d;
    private FloatSetting e;
    private RenderHelper27 f = new RenderHelper27(26293358099357L);
    private String h = "";

    public NameHider() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.e
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL362_0 -> (Boolean)this.d.getValue() && ((String)this.a.getValue()).equals("Random"), 35046756052036L}
            );
        this.d.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL363_0 -> ((String)this.a.getValue()).equals("Random"), 73196168581295L});
        this.c.setVisibility_TextSetting(new Object[]{(Predicate<String>)lpL364_0 -> ((String)this.a.getValue()).equals("Custom"), 115047363157683L});
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if ((Boolean)this.d.getValue()) {
            RenderHelper27 var10000 = this.f;
            float var10001 = (Float)this.e.getValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                this.h = this.d(new Object[]{128049266673L});
                this.f.a_void_____________________(new Object[]{93540861878173L});
            }
        }

        if (((String)this.a.getValue()).equals("Custom")) {
            this.h = this.d(new Object[]{128049266673L});
        }
    }

    @Subscribe
    public void a(StringEvent2 var1) {
        Event.b();
        String var5 = b.getSession().getUsername();
        if (var1.b_String_(new Object[0]).contains(var5)) {
            var1.b(new Object[]{b.getSession().getUsername()});
            var1.c(new Object[]{this.h});
            var1.a_void_____(new Object[0]);
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 136609732465399L;
        this.h = this.d(new Object[]{var4});
    }

    private String d(Object[] var1) {
        if (((String)this.a.getValue()).equals("Custom")) {
            return ((String)this.c.getValue()).toString();
        } else {
            StringBuilder var5 = new StringBuilder();
            String var6 = "abcdefghijklmnopqrstuvwxyz0123456789";

            for (int var7 = 0; var7 < b.player.getDisplayName().getString().length(); var7++) {
                char var8 = var6.charAt(RenderUtil5.a.nextInt(var6.length()));
                if (Character.isAlphabetic(var8) && RenderUtil5.a.nextFloat() > 0.5F) {
                    var8 = Character.toUpperCase(var8);
                }

                var5.append(var8);
            }

            return var5.toString();
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
