package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.item.Items;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.lwjgl.glfw.GLFW;

public class CartStealer extends Module {
    public CartStealer() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private RangeSetting a;
    private BoolSetting d;
    private KeybindSetting f;
    private RenderHelper27 c = new RenderHelper27(26293358099357L);

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Cart);
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if ((Integer)this.f.getValue() == -1 || GLFW.glfwGetKey(b.getWindow().getHandle(), (Integer)this.f.getValue()) == 1) {
            if (this.c.b(new Object[]{this.a, 89714527855213L})) {
                if (b.player.currentScreenHandler instanceof ShulkerBoxScreenHandler var13) {
                    if (this.a(new Object[]{var13, 31428784206748L}) <= 1) {
                        if ((Boolean)this.d.getValue()) {
                            b.player.closeHandledScreen();
                        }
                    } else {
                        for (int var16 = 0; var16 < 27; var16++) {
                            Slot var15 = var13.getSlot(var16);
                            if (var15.hasStack() && var15.getStack().isOf(Items.TNT_MINECART)) {
                                b.interactionManager.clickSlot(var13.syncId, var16, 0, SlotActionType.QUICK_MOVE, b.player);
                                this.c.a_void_____________________(new Object[]{93540861878173L});
                                this.a.reroll(new Object[]{57083216809697L});
                                return;
                            }
                        }

                        if ((Boolean)this.d.getValue()) {
                            b.player.closeHandledScreen();
                        }
                    }
                }
            }
        }
    }

    private int a(Object[] var1) {
        ShulkerBoxScreenHandler var2 = (ShulkerBoxScreenHandler)var1[0];
        Event.b();
        int var6 = 0;

        for (int var7 = 27; var7 < 63; var7++) {
            if (!var2.getSlot(var7).hasStack()) {
                var6++;
            }
        }

        return var6;
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
