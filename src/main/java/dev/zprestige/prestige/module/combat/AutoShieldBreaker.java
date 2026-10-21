package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.util.TpsTimer;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;

public class AutoShieldBreaker extends Module implements RenderContract3 {
    private EnumSetting a;
    private BoolSetting d;
    private FloatSetting c;
    private BoolSetting e;
    private RangeSetting f;
    private RangeSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private FloatSetting j;
    private int k = -1;
    private int l = -1;
    private boolean m = false;
    private RenderHelper27 n = new RenderHelper27(26293358099357L);
    private RenderHelper27 o = new RenderHelper27(26293358099357L);
    private RenderHelper27 p = new RenderHelper27(26293358099357L);
    private static final long x = 3000L;

    public AutoShieldBreaker() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.d.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL296_0 -> ((String)this.a.getValue()).equals("Automatic"), 73196168581295L});
        this.g.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL292_0 -> ((String)this.a.getValue()).equals("Automatic"), 37043306203287L});
        this.h.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL291_0 -> ((String)this.a.getValue()).equals("Automatic"), 73196168581295L});
        this.i.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL290_0 -> ((String)this.a.getValue()).equals("Automatic"), 73196168581295L});
        this.j
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL289_0 -> ((String)this.a.getValue()).equals("Automatic") && (Boolean)this.i.getValue(), 35046756052036L}
            );
        this.c.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL295_0 -> ((String)this.a.getValue()).equals("Automatic"), 35046756052036L});
        this.f.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL293_0 -> ((String)this.a.getValue()).equals("Automatic"), 37043306203287L});
        this.e.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL294_0 -> ((String)this.a.getValue()).equals("Automatic"), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword);
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (!b.player.isAlive()) {
            this.k = -1;
            this.l = -1;
            this.m = false;
        } else if (!(b.player.fallDistance > 2.0)) {
            if (this.m) {
                RenderHelper27 var10000 = this.p;
                float var10001 = this.f.getRolled(new Object[0]) + 400.0F;
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = var10001;
                if (var10000.a(var10004)) {
                    this.m = false;
                }
            }

            if (this.k != -1 && !this.m && b.currentScreen == null && !InventoryUtil2.e(new Object[]{17574514088175L})) {
                if ((Boolean)this.h.getValue()) {
                    if (this.l != -1) {
                        int var2 = this.l;
                        int var4 = this.k;
                        Object[] var5 = new Object[]{null, null, 133411734417951L};
                        var5[1] = var4;
                        var5[0] = var2;
                        InventoryUtil.f(var5);
                    } else {
                        int var3 = this.k;
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var3;
                        InventoryUtil.a_void________(var10003);
                    }
                }

                this.k = -1;
                this.l = -1;
            }

            if (b.targetedEntity != null) {
                if (b.targetedEntity instanceof PlayerEntity) {
                    this.d_boolean__(new Object[]{(PlayerEntity)b.targetedEntity, 23325805542517L});
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (((String)this.a.getValue()).equals("On Hit")) {
            if (b.targetedEntity != null && b.currentScreen == null && b.isWindowFocused()) {
                if (b.targetedEntity instanceof PlayerEntity var17) {
                    if (!Prestige.g.a(new Object[]{var17.getName().getString(), 24848952222090L})) {
                        if (Prestige.i.a_boolean_(new Object[]{var17, 54363558999103L})) {
                            if (this.a_boolean________________(new Object[]{var17, 61043913486438L})) {
                                int var19 = this.d(new Object[]{91840059170392L});
                                if (var19 != -1) {
                                    if (this.k == -1) {
                                        this.k = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                        this.l = -1;
                                    }

                                    Object[] var10003 = new Object[]{null, 37392877673079L};
                                    var10003[0] = var19;
                                    InventoryUtil.a_void________(var10003);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private int d(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() instanceof AxeItem) {
                return var5;
            }
        }

        return -1;
    }

    private int e(Object[] var1) {
        Event.b();

        for (int var5 = 9; var5 <= 35; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() instanceof AxeItem) {
                return var5;
            }
        }

        return -1;
    }

    private boolean a_boolean________________(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        return var2.isUsingItem() && var2.getActiveItem().getItem() == Items.SHIELD;
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 139616768334726L;
        long var6 = var2 ^ 81856115773718L;
        long var8 = var2 ^ 48633155378565L;
        long var10 = var2 ^ 99765532791756L;
        if (!(Boolean)this.i.getValue()) {
            return null;
        } else if (b.player.fallDistance > 2.0) {
            return null;
        } else {
            float var10000 = (Float)this.j.getValue();
            Object[] var10003 = new Object[]{null, var8};
            var10003[0] = var10000;
            PlayerEntity var13 = CombatUtil.b(var10003);
            if (var13 != null) {
                if (b.player.distanceTo(var13) > 3.0F || !b.player.canSee(var13)) {
                    return null;
                }

                if (!Prestige.i.a_boolean_(new Object[]{var13, var4})) {
                    return null;
                }

                RenderBatch var14 = WorldUtil.a_RenderBatch_(new Object[]{var13.getEyePos(), var6});
                float var15 = MathHelper.wrapDegrees(var14.a(new Object[0]) - b.player.getYaw());
                if (Math.abs(var15) > (Float)this.j.getValue()) {
                    return null;
                }

                if (this.d_boolean__(new Object[]{var13, var10})) {
                    return var14;
                }
            }

            return null;
        }
    }

    public boolean d_boolean__(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 98027917846872L ^ var3;
        long var5 = var3 ^ 99542470669479L;
        long var7 = var3 ^ 137853719650578L;
        long var9 = var3 ^ 27497399503536L;
        long var11 = var3 ^ 121028492791642L;
        long var13 = var3 ^ 21383556365186L;
        long var15 = var3 ^ 135884001617227L;
        long var17 = var3 ^ 140718861360076L;
        long var19 = var3 ^ 59646094127753L;
        long var21 = var3 ^ 60370766762950L;
        long var23 = var3 ^ 88630113527819L;
        long var25 = var3 ^ 34730170420085L;
        long var27 = var3 ^ 74676391480770L;
        long var29 = var3 ^ 22188002490132L;
        long var31 = var3 ^ 32475790358336L;
        long var33 = var3 ^ 72405603546274L;
        long var35 = var3 ^ 58580240377650L;
        if (b.player.fallDistance > 2.0) {
            return false;
        } else if (b.currentScreen == null && b.isWindowFocused()) {
            if (InventoryUtil2.e(new Object[]{var27})) {
                return false;
            } else {
                if (!b.player.isUsingItem()) {
                    Object[] var10004 = new Object[]{null, null, var29};
                    var10004[1] = 100;
                    var10004[0] = 0;
                    if (RenderUtil5.a_int__________________________(var10004) <= (Float)this.c.getValue() && this.n.b(new Object[]{this.g, var31})) {
                        if (!((String)this.a.getValue()).equals("Automatic")) {
                            return false;
                        }

                        if (Prestige.g.a(new Object[]{var2.getName().getString(), var5})) {
                            return false;
                        }

                        if (!Prestige.i.a_boolean_(new Object[]{var2, var7})) {
                            return false;
                        }

                        if (this.a_boolean________________(new Object[]{var2, var15})) {
                            if (!(b.player.getMainHandStack().getItem() instanceof AxeItem)) {
                                if (this.m) {
                                    return false;
                                }

                                int var38 = this.d(new Object[]{var25});
                                if (var38 != -1) {
                                    this.k = InventoryUtil.a_int_____(new Object[]{var19});
                                    this.l = -1;
                                    Object[] var41 = new Object[]{null, var11};
                                    var41[0] = var38;
                                    InventoryUtil.a_void________(var41);
                                    this.m = true;
                                    this.p.a_void_____________________(new Object[]{var9});
                                } else if ((Boolean)this.e.getValue()) {
                                    int var39 = this.e(new Object[]{var21});
                                    if (var39 != -1) {
                                        this.k = InventoryUtil.a_int_____(new Object[]{var19});
                                        this.l = var39;
                                        int var10001 = this.k;
                                        var10004 = new Object[]{null, null, var35};
                                        var10004[1] = var10001;
                                        var10004[0] = var39;
                                        InventoryUtil.f(var10004);
                                        this.m = true;
                                        this.p.a_void_____________________(new Object[]{var9});
                                    }
                                }

                                this.o.a_void_____________________(new Object[]{var9});
                                this.f.reroll(new Object[]{var17});
                                return false;
                            }

                            if (!this.o.b(new Object[]{this.f, var31})) {
                                return false;
                            }

                            InventoryUtil2.a_void_________(new Object[]{var2, var23});
                            if ((Boolean)this.d.getValue()) {
                                InventoryUtil2.a_void_________(new Object[]{var2, var23});
                            }

                            if (ModuleNotificationsElement.a.e_boolean(new Object[]{var33})) {
                                TpsTimer var10000 = Prestige.v;
                                long var10003 = x;
                                Object[] var10007 = new Object[]{null, null, null, NotificationType.INFO, var13};
                                var10007[2] = var10003;
                                var10007[1] = "Broke target's shield!";
                                var10007[0] = "AutoShieldBreaker";
                                var10000.a(var10007);
                            }

                            this.m = false;
                            this.n.a_void_____________________(new Object[]{var9});
                            this.g.reroll(new Object[]{var17});
                            return true;
                        }

                        return false;
                    }
                }

                return false;
            }
        } else {
            return false;
        }
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
        this.k = -1;
        this.l = -1;
        this.m = false;
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
