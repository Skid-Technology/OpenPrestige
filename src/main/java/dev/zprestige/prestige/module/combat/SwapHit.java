package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;

public class SwapHit extends Module {
    public SwapHit() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private FloatSetting a;
    private FloatSetting c;
    private BoolSetting e;
    private BoolSetting f;
    private RenderHelper27 g = new RenderHelper27(26293358099357L);
    private RenderHelper27 h = new RenderHelper27(26293358099357L);
    private int i = -1;
    private int j = 0;

    @Override
    public void onEnable(Object[] var1) {
        this.i = -1;
        this.j = 0;
    }

    @Override
    public void onDisable(Object[] var1) {
        this.i = -1;
        this.j = 0;
    }

    private int a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 41753668174394L ^ var2;
        long var4 = var2 ^ 17418996245654L;
        Event.b();

        for (int var7 = 0; var7 <= 8; var7++) {
            if (InventoryUtil.a(new Object[]{b.player.getInventory().getStack(var7), var4})) {
                return var7;
            }
        }

        return -1;
    }

    private int d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 41753668174394L ^ var2;
        long var4 = var2 ^ 117644960216816L;
        Event.b();

        for (int var7 = 0; var7 <= 8; var7++) {
            if (InventoryUtil.b(new Object[]{b.player.getInventory().getStack(var7), var4})) {
                return var7;
            }
        }

        return -1;
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (b.player != null && b.world != null) {
            if (b.isWindowFocused() && b.currentScreen == null) {
                if (!InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                    if (!(Boolean)this.d.getValue() || b.player.getMainHandStack().getItem() == Items.TOTEM_OF_UNDYING) {
                        if (b.crosshairTarget instanceof EntityHitResult var19) {
                            if (var19.getEntity() instanceof PlayerEntity var21) {
                                int var22;
                                if ((Boolean)this.e.getValue() && CombatUtil.b_boolean(new Object[]{var21, 53913050824112L})) {
                                    if (!(Boolean)this.f.getValue()) {
                                        return;
                                    }

                                    int var25 = this.d(new Object[]{45107933241511L});
                                    if (var25 == -1) {
                                        return;
                                    }

                                    var22 = var25;
                                } else {
                                    int var23 = this.a(new Object[]{103415004855955L});
                                    if (var23 == -1) {
                                        return;
                                    }

                                    var22 = var23;
                                }

                                var1.a_void_____(new Object[0]);
                                this.i = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                Object[] var10003 = new Object[]{null, 37392877673079L};
                                var10003[0] = var22;
                                InventoryUtil.a_void________(var10003);
                                this.j = 1;
                                this.g.a_void_____________________(new Object[]{93540861878173L});
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.j != 0) {
            if (b.player != null && b.world != null) {
                if (this.j == 1) {
                    RenderHelper27 var10000 = this.g;
                    float var10001 = (Float)this.a.getValue();
                    Object[] var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = var10001;
                    if (!var10000.a(var10004)) {
                        return;
                    }

                    if (b.crosshairTarget instanceof EntityHitResult var13) {
                        Entity var15 = var13.getEntity();
                        if (var15 != null) {
                            InventoryUtil2.a_void_________(new Object[]{var15, 31382773185830L});
                        }
                    }

                    this.j = 2;
                    this.h.a_void_____________________(new Object[]{93540861878173L});
                } else if (this.j == 2) {
                    RenderHelper27 var16 = this.h;
                    float var18 = (Float)this.c.getValue();
                    Object[] var19 = new Object[]{null, 2505922520844L};
                    var19[0] = var18;
                    if (!var16.a(var19)) {
                        return;
                    }

                    if (this.i != -1) {
                        int var17 = this.i;
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var17;
                        InventoryUtil.a_void________(var10003);
                        this.i = -1;
                    }

                    this.j = 0;
                }
            } else {
                this.j = 0;
                this.i = -1;
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
