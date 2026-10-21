package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SelfDestruct extends Module {
    public SelfDestruct() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 22594282158762L;
        long var6 = var2 ^ 53654995788662L;
        Event.b();
        Prestige.w = true;
        b.setScreen(null);

        for (Module var10 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
            if (var10.isEnabled()) {
                var10.toggle(new Object[]{var4});
            }

            var10.dispose(new Object[]{var6});
            var10.getKeybind(new Object[0]).setValue(-1);
        }

        Prestige.moduleManager.setModules(new ArrayList());
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
