package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper35;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.item.Items;
import org.lwjgl.glfw.GLFW;

public class InstantShield extends Module {
    public InstantShield() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private KeybindSetting f;
    private BoolSetting d;
    private boolean i = false;
    private int a = -1;
    private int c = -1;
    private int e = -1;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player != null && b.world != null) {
            if (!b.player.isAlive()) {
                this.l(new Object[0]);
                this.m(new Object[]{27905760393643L});
            } else {
                if (this.c != -1
                    && b.player.getInventory().getStack(this.c).getItem() != Items.SHIELD
                    && (Prestige.u == null || !Prestige.u.a_boolean____________________________________(new Object[]{89789443909602L}))) {
                    this.l(new Object[0]);
                }

                boolean var21 = (Integer)this.f.getValue() != -1 && this.f.isPressed(new Object[]{28844609523469L});
                boolean var22 = var21 && !this.i;
                boolean var23 = !var21 && this.i;
                this.i = var21;
                if (b.currentScreen == null) {
                    if (this.f.isToggleMode()) {
                        if (var22 && this.a == -1) {
                            this.j(new Object[]{101409313690721L});
                        } else if (var23 && this.a != -1) {
                            this.k(new Object[]{136057425675881L});
                        }
                    } else if (var22) {
                        if (this.a != -1) {
                            this.k(new Object[]{136057425675881L});
                        } else {
                            this.j(new Object[]{101409313690721L});
                        }
                    }
                }

                if (b.currentScreen == null && this.e != -1 && this.c != -1 && InventoryUtil.a_int_____(new Object[]{134339616728996L}) != this.c) {
                    int var10000 = this.e;
                    int var10001 = this.c;
                    Object[] var10004 = new Object[]{null, null, 133411734417951L};
                    var10004[1] = var10001;
                    var10004[0] = var10000;
                    InventoryUtil.f(var10004);
                    this.l(new Object[0]);
                }

                if (b.currentScreen == null && this.d(new Object[]{129923587519811L})) {
                    b.options.useKey.setPressed(true);
                } else {
                    this.m(new Object[]{27905760393643L});
                }
            }
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 52249015264320L ^ var2;
        long var4 = var2 ^ 89868647590486L;
        long var6 = var2 ^ 10516105942917L;
        long var8 = var2 ^ 11992872632894L;
        if (b.player.getMainHandStack().getItem() != Items.SHIELD) {
            for (int var11 = 0; var11 < 9; var11++) {
                if (b.player.getInventory().getStack(var11).getItem() == Items.SHIELD) {
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
                    if (b.player.getInventory().getStack(var14).getItem() == Items.SHIELD) {
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
        var2 = 52249015264320L ^ var2;
        long var4 = var2 ^ 129988397938782L;
        long var6 = var2 ^ 49946886770742L;
        if (this.e != -1) {
            int var10000 = this.e;
            int var10001 = this.c;
            Object[] var10004 = new Object[]{null, null, var6};
            var10004[1] = var10001;
            var10004[0] = var10000;
            InventoryUtil.f(var10004);
        } else {
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

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 52249015264320L ^ var2;
        long var4 = var2 ^ 94922939943404L;
        return b.player.getMainHandStack().getItem() == Items.SHIELD
            ? true
            : b.player.getOffHandStack().getItem() == Items.SHIELD && !InventoryUtil2.e(new Object[]{var4});
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 52249015264320L ^ var2;
        long var4 = var2 ^ 82743220821503L;
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
        long var4 = var2 ^ 68998707433166L;
        this.m(new Object[]{var4});
        this.l(new Object[0]);
        this.i = false;
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
