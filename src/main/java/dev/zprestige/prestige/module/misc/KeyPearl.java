package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class KeyPearl extends Module {
    public KeyPearl() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private RangeSetting a;
    private RenderHelper27 c = new RenderHelper27(26293358099357L);
    private int d = 0;
    private boolean i = false;
    private static final long l = 4112858187273601032L;

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 95564356895798L;
        this.i = false;
        this.j(new Object[]{var4});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            RenderHelper27 var10000 = this.c;
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                if (this.i) {
                    int var20 = this.d;
                    Object[] var21 = new Object[]{null, 37392877673079L};
                    var21[0] = var20;
                    InventoryUtil.a_void________(var21);
                    this.toggle(new Object[]{115103427876780L});
                } else if (b.player.getMainHandStack().getItem() != Items.ENDER_PEARL) {
                    int var19 = this.a(new Object[]{35036311790109L});
                    if (var19 == -1) {
                        this.toggle(new Object[]{115103427876780L});
                    } else {
                        this.d = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var19;
                        InventoryUtil.a_void________(var10003);
                        this.j(new Object[]{47044677658928L});
                    }
                } else {
                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                    this.i = true;
                    this.j(new Object[]{47044677658928L});
                }
            }
        }
    }

    private int a(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= (int)l; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() == Items.ENDER_PEARL) {
                return var5;
            }
        }

        return -1;
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 27011225142556L ^ var2;
        long var4 = var2 ^ 113573278659505L;
        long var6 = var2 ^ 1867302724301L;
        this.a.reroll(new Object[]{var6});
        this.c.a_void_____________________(new Object[]{var4});
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
