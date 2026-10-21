package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Gamemode;
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
import java.util.Set;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.lwjgl.glfw.GLFW;

public class CartRefill extends Module {
    public CartRefill() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private KeybindSetting f;
    private IntSetting c;
    private IntSetting d;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Cart);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player != null && b.world != null) {
            RenderHelper27 var10000 = this.e;
            float var10001 = ((Integer)this.d.getValue()).intValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                if (this.d(new Object[]{84297800312317L})) {
                    int var15 = (Integer)this.c.getValue() - 1;
                    ItemStack var16 = b.player.getInventory().getStack(var15);
                    if (var16.isEmpty()) {
                        int var17 = this.a(new Object[]{131533992057245L});
                        if (var17 != -1) {
                            var10004 = new Object[]{null, null, 133411734417951L};
                            var10004[1] = var15;
                            var10004[0] = var17;
                            InventoryUtil.f(var10004);
                            this.e.a_void_____________________(new Object[]{93540861878173L});
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
        Event.b();

        for (int var5 = 9; var5 < 36; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (!var6.isEmpty() && var6.getItem() == Items.TNT_MINECART) {
                return var5;
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
