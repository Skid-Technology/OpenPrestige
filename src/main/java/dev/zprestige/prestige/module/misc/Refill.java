package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import org.lwjgl.glfw.GLFW;

public class Refill extends Module {
    public Refill() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private KeybindSetting f;
    private IntSetting c;
    private IntSetting d;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player != null && b.world != null) {
            RenderHelper27 var10000 = this.e;
            float var10001 = ((Integer)this.d.getValue()).intValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                if (this.d(new Object[]{75267366531328L})) {
                    for (int var15 = 0; var15 < 9; var15++) {
                        ItemStack var16 = b.player.getInventory().getStack(var15);
                        if (!var16.isEmpty()
                            && var16.getMaxCount() > 1
                            && var16.getCount() < var16.getMaxCount()
                            && var16.getCount() <= (Integer)this.c.getValue()) {
                            int var17 = this.a(new Object[]{var16, 57840989203711L});
                            if (var17 != -1) {
                                int var18 = 36 + var15;
                                Object[] var10005 = new Object[]{null, null, SlotActionType.PICKUP, 92701205799711L};
                                var10005[1] = 0;
                                var10005[0] = var17;
                                InventoryUtil.e(var10005);
                                var10005 = new Object[]{null, null, SlotActionType.PICKUP, 92701205799711L};
                                var10005[1] = 0;
                                var10005[0] = var18;
                                InventoryUtil.e(var10005);
                                var10005 = new Object[]{null, null, SlotActionType.PICKUP, 92701205799711L};
                                var10005[1] = 0;
                                var10005[0] = var17;
                                InventoryUtil.e(var10005);
                                this.e.a_void_____________________(new Object[]{93540861878173L});
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean d(Object[] var1) {
        if (((String)this.a.getValue()).equals("Always")) {
            return b.currentScreen instanceof InventoryScreen;
        } else {
            int var5 = (Integer)this.f.getValue();
            return var5 != -1 && GLFW.glfwGetKey(b.getWindow().getHandle(), var5) == 1;
        }
    }

    private int a(Object[] var1) {
        ItemStack var4 = (ItemStack)var1[0];
        Event.b();

        for (int var6 = 9; var6 < 36; var6++) {
            ItemStack var7 = b.player.getInventory().getStack(var6);
            if (!var7.isEmpty() && var7.getItem() == var4.getItem()) {
                return var6;
            }
        }

        return -1;
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
