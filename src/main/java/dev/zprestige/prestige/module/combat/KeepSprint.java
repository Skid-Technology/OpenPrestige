package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.module.movement.WTap;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import org.lwjgl.glfw.GLFW;

public class KeepSprint extends Module {
    public KeepSprint() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private RangeSetting a;
    private boolean i = false;
    private int c = -1;

    private boolean d(Object[] var1) {
        return GLFW.glfwGetKey(b.getWindow().getHandle(), 87) == 1;
    }

    @Subscribe
    public void a(EntityEvent var1) {
        if (b.player != null) {
            Entity var7 = var1.a(new Object[0]);
            if (var7 != null) {
                if (b.player.isSprinting() && this.d(new Object[]{121227298295364L})) {
                    this.i = true;
                }
            }
        }
    }

    private boolean e(Object[] var1) {
        Event.b();

        for (Module var6 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
            if (var6.getClass().equals(WTap.class)) {
                if (!var6.isEnabled()) {
                    return false;
                }

                WTap var7 = (WTap)var6;
                return var7.d(new Object[0]) || var7.e(new Object[0]);
            }
        }

        return false;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.e(new Object[]{43084567827504L})) {
            this.i = false;
            this.c = -1;
        } else if (this.c > 0) {
            this.c--;
        } else {
            if (this.c == 0) {
                this.c = -1;
                if (b.player != null && this.d(new Object[]{121227298295364L})) {
                    b.options.forwardKey.setPressed(true);
                    b.player.setSprinting(true);
                }
            }

            if (this.i) {
                this.i = false;
                if (b.player != null) {
                    if (!b.options.sneakKey.isPressed() && !b.player.isSneaking()) {
                        if (this.d(new Object[]{121227298295364L})) {
                            if (!b.player.horizontalCollision) {
                                if (!b.player.isUsingItem()) {
                                    if (b.player.getHungerManager().getFoodLevel() > 6) {
                                        b.options.forwardKey.setPressed(false);
                                        int var11 = Math.round(this.a.getLow());
                                        int var12 = Math.round(this.a.getHigh());
                                        int var10001;
                                        if (var11 == var12) {
                                            var10001 = var11;
                                        } else {
                                            Object[] var10005 = new Object[]{null, null, 97028099236409L};
                                            var10005[1] = var12;
                                            var10005[0] = var11;
                                            var10001 = RenderUtil5.a_int__________________________(var10005);
                                        }

                                        this.c = var10001;
                                    }
                                }
                            }
                        }
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
