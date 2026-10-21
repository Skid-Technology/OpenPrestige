package dev.zprestige.prestige.input;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Native;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.IntEvent2;
import dev.zprestige.prestige.event.IntEvent4;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderUtil4;
import dev.zprestige.prestige.util.BindManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class MouseManager implements RenderContract2 {
    private static Double a = null;
    private static final Map c = Map.of(9999, 0, 9998, 1, 9997, 2, 9996, 3, 9995, 4);
    private long d;

    public MouseManager(long var1) {
        super();
        var1 = 0L;
        var1 = 21801194406431L ^ var1;
        long var3 = var1 ^ 70144848628720L;
        this.d = 0L;
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(IntEvent2 var1) {
        if (var1.b(new Object[0]) == 0) {
            int var10000 = var1.a(new Object[0]);
            int var10001 = (Integer)Prestige.moduleManager.getMenu(new Object[0]).getMenuKey(new Object[0]).getValue();
            Object[] var10004 = new Object[]{null, 58384228437483L};
            var10004[0] = var10001;
            if (var10000 == RenderUtil4.a(var10004)) {
                b.setScreen(Prestige.k);
                Prestige.k.a(new Object[0]);
            }
        }

        for (Module var12 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
            int var14 = var12.getKey(new Object[]{13012393635966L});
            Object[] var10003 = new Object[]{null, 58384228437483L};
            var10003[0] = var14;
            int var13 = RenderUtil4.a(var10003);
            if (var12.getKeybind(new Object[0]).isToggleMode()) {
                if (var12.isEnabled() && b.currentScreen != null) {
                    var12.toggle(new Object[]{115103427876780L});
                } else if (var1.b(new Object[0]) == 0) {
                    if (var12.isEnabled() && var13 == var1.a(new Object[0])) {
                        var12.toggle(new Object[]{115103427876780L});
                    }
                } else if (var1.b(new Object[0]) == 1 && b.currentScreen == null && !var12.isEnabled() && var13 == var1.a(new Object[0])) {
                    var12.toggle(new Object[]{115103427876780L});
                }
            } else if (b.currentScreen == null
                && var12.getKey(new Object[]{13012393635966L}) != -1
                && var1.b(new Object[0]) == 1
                && var13 == var1.a(new Object[0])) {
                var12.toggle(new Object[]{115103427876780L});
            }
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen == null) {
            long var5 = System.currentTimeMillis();
            if (var5 - this.d >= 30L) {
                this.d = var5;
                if (Native.j()) {
                    b.setScreen(Prestige.k);
                    Prestige.k.a(new Object[0]);
                }
            }

            if (a != null) {
                b.getWindow().setScaleFactor((int)Math.round(a));
                a = null;
            }
        }
    }

    @Subscribe
    public void a(IntEvent4 var1) {
        BindManager var10000 = Prestige.q;
        int var10001 = var1.a(new Object[0]);
        Object[] var10004 = new Object[]{null, 128352941745080L};
        var10004[0] = var10001;
        if (!var10000.a(var10004)) {
            for (Module var12 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
                if (var12.getKey(new Object[]{13012393635966L}) != -1
                    && c.containsKey(var12.getKey(new Object[]{13012393635966L}))
                    && (Integer)c.get(var12.getKey(new Object[]{13012393635966L})) == var1.a(new Object[0])) {
                    if (var12.getKeybind(new Object[0]).isToggleMode()) {
                        if (var1.b(new Object[0]) == 1 && b.currentScreen == null && !var12.isEnabled()) {
                            var12.toggle(new Object[]{115103427876780L});
                        } else if (var1.b(new Object[0]) == 0 && var12.isEnabled()) {
                            var12.toggle(new Object[]{115103427876780L});
                        }
                    } else if (b.currentScreen == null && var1.b(new Object[0]) == 1) {
                        var12.toggle(new Object[]{115103427876780L});
                    }
                }
            }
        }
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
