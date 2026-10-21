package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Hand;

public class MacePot extends Module implements RenderContract3 {
    public MacePot() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private BoolSetting d;
    private FloatSetting c;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);
    private RenderHelper27 f = new RenderHelper27(26293358099357L);
    private RenderHelper27 g = new RenderHelper27(26293358099357L);
    private RenderHelper27 h = new RenderHelper27(26293358099357L);
    private Integer i = null;
    private Integer j = null;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public boolean b(Object[] var1) {
        return Boolean.FALSE.equals(this.d.getValue());
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 136651866339394L;
        long var6 = var2 ^ 43040229079061L;
        long var8 = var2 ^ 29247161167908L;
        long var10 = var2 ^ 65321317039L;
        long var12 = var2 ^ 71104996207826L;
        long var14 = var2 ^ 85547089512629L;
        long var16 = var2 ^ 60322152390054L;
        if (b.currentScreen == null && b.isWindowFocused()) {
            RenderHelper27 var10000 = this.e;
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, var14};
            var10004[0] = var10001;
            if (!var10000.a(var10004)) {
                return null;
            } else {
                boolean var19 = b.player.hasStatusEffect(StatusEffects.SPEED);
                boolean var20 = b.player.hasStatusEffect(StatusEffects.STRENGTH);
                if (b.player.isOnGround()) {
                    if (!var19) {
                        var10000 = this.g;
                        var10004 = new Object[]{null, var14};
                        var10004[0] = 300.0F;
                        if (var10000.a(var10004)) {
                            Object[] var10005 = new Object[]{null, null, StatusEffects.SPEED, var12};
                            var10005[1] = 9;
                            var10005[0] = 0;
                            Integer var21 = InventoryUtil.e_Integer(var10005);
                            if (var21 != null) {
                                this.e.a_void_____________________(new Object[]{var8});
                                this.g.a_void_____________________(new Object[]{var8});
                                int var32 = var21;
                                var10004 = new Object[]{null, var10};
                                var10004[0] = var32;
                                return this.b_RenderBatch__________(var10004);
                            }

                            var10005 = new Object[]{null, null, StatusEffects.SPEED, var12};
                            var10005[1] = 36;
                            var10005[0] = 0;
                            Integer var22 = InventoryUtil.e_Integer(var10005);
                            if (var22 != null) {
                                int var31 = var22;
                                var10004 = new Object[]{null, var4};
                                var10004[0] = var31;
                                this.j(var10004);
                                this.e.a_void_____________________(new Object[]{var8});
                                return null;
                            }
                        }
                    }

                    if (!var20) {
                        var10000 = this.h;
                        var10004 = new Object[]{null, var14};
                        var10004[0] = 300.0F;
                        if (var10000.a(var10004)) {
                            Object[] var41 = new Object[]{null, null, StatusEffects.STRENGTH, var12};
                            var41[1] = 9;
                            var41[0] = 0;
                            Integer var23 = InventoryUtil.e_Integer(var41);
                            if (var23 != null) {
                                this.e.a_void_____________________(new Object[]{var8});
                                this.h.a_void_____________________(new Object[]{var8});
                                int var30 = var23;
                                var10004 = new Object[]{null, var10};
                                var10004[0] = var30;
                                return this.b_RenderBatch__________(var10004);
                            }

                            var41 = new Object[]{null, null, StatusEffects.STRENGTH, var12};
                            var41[1] = 36;
                            var41[0] = 0;
                            Integer var24 = InventoryUtil.e_Integer(var41);
                            if (var24 != null) {
                                int var29 = var24;
                                var10004 = new Object[]{null, var4};
                                var10004[0] = var29;
                                this.j(var10004);
                                this.e.a_void_____________________(new Object[]{var8});
                                return null;
                            }
                        }
                    }
                }

                if (var19 && var20 && this.i != null && this.j != null) {
                    int var27 = this.i;
                    int var28 = this.j;
                    var10004 = new Object[]{null, null, var16};
                    var10004[1] = var28;
                    var10004[0] = var27;
                    InventoryUtil.f(var10004);
                    this.i = null;
                    if ((Boolean)this.d.getValue()) {
                        this.toggle(new Object[]{var6});
                    }

                    return null;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    private void j(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        var3 = 48077845032872L ^ var3;
        long var5 = var3 ^ 85112855881678L;
        long var7 = var3 ^ 108120121548791L;
        long var9 = var3 ^ 106848507890252L;
        if (this.i == null && this.j == null) {
            this.i = var2;
            this.j = InventoryUtil.a_int_____(new Object[]{var7});
        }

        this.f.a_void_____________________(new Object[]{var5});
        int var10001 = InventoryUtil.a_int_____(new Object[]{var7});
        Object[] var10004 = new Object[]{null, null, var9};
        var10004[1] = var10001;
        var10004[0] = var2;
        InventoryUtil.f(var10004);
    }

    private RenderBatch b_RenderBatch__________(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        var2 = 48077845032872L ^ var2;
        long var5 = var2 ^ 93268990726716L;
        float var7 = (Float)this.c.getValue() - 1.0F + RenderUtil5.a.nextFloat();
        Runnable var10001 = () -> {
            float var5x = b.player.getPitch();
            b.player.setPitch((Float)this.c.getValue() - 1.0F + RenderUtil5.a.nextFloat());
            InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
            b.player.setPitch(var5x);
        };
        Object[] var10005 = new Object[]{null, null, null, var5};
        var10005[2] = true;
        var10005[1] = var10001;
        var10005[0] = var4;
        InventoryUtil.b_void__(var10005);
        return new RenderBatch(b.player.getYaw(), var7);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
        this.i = null;
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
