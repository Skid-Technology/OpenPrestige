package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper35;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.lwjgl.glfw.GLFW;

public class InstantCrossbow extends Module {
    public InstantCrossbow() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private int a = -1;
    private int c = -1;
    private int e = -1;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC, Gamemode.Crystal);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 54551600962049L;
        if (b.player != null && b.world != null) {
            this.j(new Object[]{var4});
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player != null && b.world != null) {
            if (!b.player.isAlive()) {
                this.l(new Object[0]);
                this.m(new Object[]{51298390111178L});
            } else {
                boolean var13 = Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{89789443909602L});
                if (this.c != -1 && !var13 && b.player.getInventory().getStack(this.c).getItem() != Items.CROSSBOW) {
                    this.l(new Object[0]);
                }

                if (b.currentScreen != null) {
                    this.m(new Object[]{51298390111178L});
                } else {
                    ItemStack var14 = b.player.getMainHandStack();
                    if (var14.getItem() != Items.CROSSBOW && var13 && this.e != -1) {
                        this.m(new Object[]{51298390111178L});
                    } else if (var14.getItem() != Items.CROSSBOW) {
                        this.m(new Object[]{51298390111178L});
                        if (this.e != -1 && this.c != -1) {
                            int var10000 = this.e;
                            int var10001 = this.c;
                            Object[] var10004 = new Object[]{null, null, 133411734417951L};
                            var10004[1] = var10001;
                            var10004[0] = var10000;
                            InventoryUtil.f(var10004);
                        }

                        this.l(new Object[0]);
                        if (this.isEnabled()) {
                            this.toggle(new Object[]{115103427876780L});
                        }
                    } else if (CrossbowItem.isCharged(var14)) {
                        this.m(new Object[]{51298390111178L});
                    } else {
                        int var15 = Math.max(1, CrossbowItem.getPullTime(var14, b.player));
                        if (b.player.isUsingItem() && b.player.getItemUseTime() >= var15) {
                            this.m(new Object[]{51298390111178L});
                        } else {
                            b.options.useKey.setPressed(true);
                        }
                    }
                }
            }
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 116859361725830L ^ var2;
        long var4 = var2 ^ 6546394538230L;
        long var6 = var2 ^ 103184032324901L;
        long var8 = var2 ^ 104042659072158L;
        if (b.player.getMainHandStack().getItem() != Items.CROSSBOW) {
            for (int var11 = 0; var11 < 9; var11++) {
                if (b.player.getInventory().getStack(var11).getItem() == Items.CROSSBOW) {
                    this.a = InventoryUtil.a_int_____(new Object[]{var6});
                    this.c = var11;
                    this.e = -1;
                    Object[] var10003 = new Object[]{null, var4};
                    var10003[0] = var11;
                    InventoryUtil.a_void________(var10003);
                    return;
                }
            }

            if ((Boolean)this.d.getValue()) {
                for (int var14 = 9; var14 < 36; var14++) {
                    if (b.player.getInventory().getStack(var14).getItem() == Items.CROSSBOW) {
                        int var12 = InventoryUtil.a_int_____(new Object[]{var6});
                        this.a = var12;
                        this.c = var12;
                        this.e = var14;
                        Object[] var10004 = new Object[]{null, null, var8};
                        var10004[1] = var12;
                        var10004[0] = var14;
                        InventoryUtil.f(var10004);
                        return;
                    }
                }
            }
        }
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 116859361725830L ^ var2;
        long var4 = var2 ^ 125562209891263L;
        long var6 = var2 ^ 45521701166039L;
        if (this.e != -1) {
            int var10000 = this.e;
            int var10001 = this.c;
            Object[] var10004 = new Object[]{null, null, var6};
            var10004[1] = var10001;
            var10004[0] = var10000;
            InventoryUtil.f(var10004);
        } else if (this.a != -1) {
            int var9 = this.a;
            Object[] var10003 = new Object[]{null, var4};
            var10003[0] = var9;
            InventoryUtil.a_void________(var10003);
        }

        this.l(new Object[0]);
    }

    private void l(Object[] var1) {
        this.a = -1;
        this.c = -1;
        this.e = -1;
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 116859361725830L ^ var2;
        long var4 = var2 ^ 62994112740952L;
        Event.b();
        int var7 = new RenderHelper35(b.options.useKey).a(new Object[]{var4}).getCode();
        boolean var8;
        if (var7 < 8) {
            var8 = GLFW.glfwGetMouseButton(b.getWindow().getHandle(), var7) == 1;
        } else {
            var8 = GLFW.glfwGetKey(b.getWindow().getHandle(), var7) == 1;
        }

        b.options.useKey.setPressed(var8);
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 9927914210479L;
        long var6 = var2 ^ 32816452178731L;
        if (b.player != null && b.world != null && b.player.isAlive()) {
            this.k(new Object[]{var6});
        } else {
            this.l(new Object[0]);
        }

        this.m(new Object[]{var4});
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
