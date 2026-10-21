package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.TextSetting;
import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class AutoText extends Module {
    public AutoText() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private TextSetting a;

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 22594282158762L;
        Event.b();
        String var7 = ((String)this.a.getValue()).toString();
        if (var7.startsWith("/")) {
            Objects.requireNonNull(b.getNetworkHandler()).sendChatCommand(var7.substring(1));
        } else {
            Objects.requireNonNull(b.getNetworkHandler()).sendChatMessage(var7);
        }

        this.toggle(new Object[]{var4});
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
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
