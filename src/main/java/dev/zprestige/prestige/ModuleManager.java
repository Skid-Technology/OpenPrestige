package dev.zprestige.prestige;

import dev.zprestige.prestige.annotation.Chainable;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.render.RenderContract2;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ModuleManager implements RenderContract2 {
    private final MenuModule menu;
    private List modules;
    private final ConcurrentLinkedQueue enableQueue;
    private final ConcurrentLinkedQueue disableQueue;
    public static boolean f = true;

    public ModuleManager(long var1) {
        super();
        var1 = 0L;
        var1 = 22310984220293L ^ var1;
        long var3 = var1 ^ 31757000116027L;
        this.menu = new MenuModule();
        this.modules = new ArrayList();
        this.enableQueue = new ConcurrentLinkedQueue();
        this.disableQueue = new ConcurrentLinkedQueue();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void onTick(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            while (!this.enableQueue.isEmpty()) {
                Module var9 = (Module)this.enableQueue.poll();
                if (var9 != null && var9.isEnabled()) {
                    var9.onEnable(new Object[]{136490818940166L});
                }
            }

            while (!this.disableQueue.isEmpty()) {
                Module var10 = (Module)this.disableQueue.poll();
                if (var10 != null && !var10.isEnabled()) {
                    var10.onDisable(new Object[]{43569551062885L});
                }
            }
        }
    }

    public void queueEnable(Object[] var1) {
        Module var4 = (Module)var1[0];
        if (!this.enableQueue.contains(var4)) {
            this.enableQueue.offer(var4);
        }
    }

    public void queueDisable(Object[] var1) {
        Module var4 = (Module)var1[0];
        if (!this.disableQueue.contains(var4)) {
            this.disableQueue.offer(var4);
        }
    }

    @Chainable
    public List getModules() {
        return this.modules;
    }

    @Chainable
    public void setModules(List var1) {
        this.modules = var1;
    }

    public MenuModule getMenu(Object[] var1) {
        return this.menu;
    }

    @Chainable
    public void setMenuColor(Color var1) {
        this.menu.getMenuColor(new Object[0]).setValue(var1);
    }

    @Chainable
    public void setMenuKey(int var1) {
        this.menu.getMenuKey(new Object[0]).setValue(var1);
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
