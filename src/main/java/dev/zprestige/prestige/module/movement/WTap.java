package dev.zprestige.prestige.module.movement;

import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public class WTap extends Module {
    public WTap() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private RangeSetting c;
    private RangeSetting d;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);
    private RenderHelper27 f = new RenderHelper27(26293358099357L);
    private boolean i = false;
    private boolean g = false;
    private static final long l = 5341673954434089047L;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Subscribe
    public void a(EntityEvent var1) {
        if (!this.i && !this.g) {
            Entity var9 = var1.a(new Object[0]);
            if (var9 != null) {
                if (var9 instanceof PlayerEntity) {
                    if (b.options.forwardKey.isPressed() && b.player.isSprinting() && RenderUtil5.a.nextFloat() * 100.0F <= (Float)this.a.getValue()) {
                        this.d.reroll(new Object[]{57083216809697L});
                        this.e.a_void_____________________(new Object[]{93540861878173L});
                        this.i = true;
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (GLFW.glfwGetKey(b.getWindow().getHandle(), (int)l) != 1) {
            this.g = false;
            this.i = false;
        } else {
            if (this.i) {
                RenderHelper27 var10000 = this.e;
                float var10001 = (Float)this.d.getValue();
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = var10001;
                if (var10000.a(var10004)) {
                    b.options.forwardKey.setPressed(false);
                    this.f.a_void_____________________(new Object[]{93540861878173L});
                    this.c.reroll(new Object[]{57083216809697L});
                    this.g = true;
                    this.i = false;
                }
            }

            if (this.g) {
                RenderHelper27 var2 = this.f;
                float var3 = (Float)this.c.getValue();
                Object[] var4 = new Object[]{null, 2505922520844L};
                var4[0] = var3;
                if (var2.a(var4)) {
                    if (b.currentScreen == null) {
                        b.options.forwardKey.setPressed(true);
                    }

                    this.g = false;
                }
            }
        }
    }

    public boolean d(Object[] var1) {
        return this.i;
    }

    public boolean e(Object[] var1) {
        return this.g;
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
