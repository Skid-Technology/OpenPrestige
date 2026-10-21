package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import org.lwjgl.glfw.GLFW;

public class AutoLoot extends Module {
    public AutoLoot() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private RangeSetting a;
    private IntSetting c;
    private IntSetting d;
    private KeybindSetting f;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen instanceof InventoryScreen) {
            RenderHelper27 var10000 = this.e;
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (!var10000.a(var10004)) {
                return;
            }

            if ((Integer)this.f.getValue() == -1) {
                return;
            }

            if (GLFW.glfwGetKey(b.getWindow().getHandle(), (Integer)this.f.getValue()) == 0) {
                return;
            }

            Item var19 = Items.TOTEM_OF_UNDYING;
            var10004 = new Object[]{null, null, 88471504819783L};
            var10004[1] = true;
            var10004[0] = var19;
            List var15 = InventoryUtil.b_List(var10004);
            Item var20 = Items.ENDER_PEARL;
            var10004 = new Object[]{null, null, 88471504819783L};
            var10004[1] = true;
            var10004[0] = var20;
            List var16 = InventoryUtil.b_List(var10004);
            if (!var15.isEmpty() && var15.size() > (Integer)this.c.getValue()) {
                int var18 = (Integer)var15.get(0) < 9 ? (Integer)var15.get(0) + 36 : (Integer)var15.get(0);
                Object[] var23 = new Object[]{null, null, SlotActionType.THROW, 92701205799711L};
                var23[1] = 1;
                var23[0] = var18;
                InventoryUtil.e(var23);
                this.a.reroll(new Object[]{57083216809697L});
                this.e.a_void_____________________(new Object[]{93540861878173L});
                return;
            }

            if (!var16.isEmpty() && var16.size() > (Integer)this.d.getValue()) {
                int var17 = (Integer)var16.get(0) < 9 ? (Integer)var16.get(0) + 36 : (Integer)var16.get(0);
                Object[] var10005 = new Object[]{null, null, SlotActionType.THROW, 92701205799711L};
                var10005[1] = 1;
                var10005[0] = var17;
                InventoryUtil.e(var10005);
                this.a.reroll(new Object[]{57083216809697L});
                this.e.a_void_____________________(new Object[]{93540861878173L});
                return;
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
