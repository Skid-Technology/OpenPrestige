package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.lwjgl.glfw.GLFW;

public class ChestStealer extends Module {
    public ChestStealer() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private RangeSetting a;
    private BoolSetting d;
    private KeybindSetting f;
    private RenderHelper27 c = new RenderHelper27(26293358099357L);

    @Subscribe
    public void a(TickEvent24 var1) {
        if ((Integer)this.f.getValue() != -1 && GLFW.glfwGetKey(b.getWindow().getHandle(), (Integer)this.f.getValue()) == 1) {
            RenderHelper27 var10000 = this.c;
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                if (b.player.currentScreenHandler instanceof GenericContainerScreenHandler var15) {
                    for (int var16 = 0; var16 < var15.getInventory().size(); var16++) {
                        Slot var17 = var15.getSlot(var16);
                        if (var17.hasStack()) {
                            Object[] var10005 = new Object[]{null, null, SlotActionType.QUICK_MOVE, 114001958067495L};
                            var10005[1] = 0;
                            var10005[0] = var16;
                            InventoryUtil.g(var10005);
                            this.c.a_void_____________________(new Object[]{93540861878173L});
                            this.a.reroll(new Object[]{57083216809697L});
                            return;
                        }
                    }

                    if (this.a(new Object[]{var15, 126118319313640L}) && (Boolean)this.d.getValue()) {
                        b.player.closeHandledScreen();
                    }
                }
            }
        }
    }

    private boolean a(Object[] var1) {
        GenericContainerScreenHandler var4 = (GenericContainerScreenHandler)var1[0];
        int var6 = var4.getInventory().size() == 90 ? 54 : 27;

        for (int var7 = 0; var7 < var6; var7++) {
            if (var4.getSlot(var7).hasStack()) {
                return false;
            }
        }

        return true;
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
