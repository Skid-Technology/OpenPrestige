package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.TickEvent12;
import dev.zprestige.prestige.event.TickEvent28;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult.Type;

public class HitFix extends Module implements RenderContract3 {
    public HitFix() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;
    private BoolSetting c;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);
    private boolean i = false;
    private boolean f = false;
    private PlayerEntity g = null;

    private boolean a_boolean______________________(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 45690498302318L ^ var3;
        long var5 = var3 ^ 3071957506038L;
        long var7 = var3 ^ 21796793555401L;
        if (var2 == null) {
            return false;
        } else if ((Boolean)this.d.getValue() && !InventoryUtil.a(new Object[]{b.player.getMainHandStack(), var5})) {
            return false;
        } else {
            return this.c.getValue() && var2.hurtTime != 0
                ? true
                : (Boolean)this.a.getValue() && PositionUtil.a_double___________(new Object[]{var2, var7}) > 0.3F;
        }
    }

    @Subscribe
    public void a(BoolEvent var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
                if (this.f) {
                    var1.b(new Object[]{true});
                } else if (b.crosshairTarget != null && b.crosshairTarget.getType() == Type.ENTITY) {
                    if (b.crosshairTarget instanceof EntityHitResult) {
                        if (((EntityHitResult)b.crosshairTarget).getEntity() instanceof PlayerEntity var9) {
                            if (this.a_boolean______________________(new Object[]{var9, 104887668870823L})) {
                                this.i = true;
                                this.g = var9;
                                this.e.a_void_____________________(new Object[]{93540861878173L});
                                var1.a_void_____(new Object[0]);
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(EntityEvent var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (b.crosshairTarget != null && b.crosshairTarget.getType() == Type.ENTITY) {
                if (b.crosshairTarget instanceof EntityHitResult) {
                    if (((EntityHitResult)b.crosshairTarget).getEntity() instanceof PlayerEntity var9) {
                        if (this.i || this.a_boolean______________________(new Object[]{var9, 104887668870823L})) {
                            this.i = true;
                            this.g = var9;
                            this.e.a_void_____________________(new Object[]{93540861878173L});
                            var1.a_void_____(new Object[0]);
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent28 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (b.crosshairTarget != null && b.crosshairTarget.getType() == Type.ENTITY) {
                if (b.crosshairTarget instanceof EntityHitResult) {
                    if (((EntityHitResult)b.crosshairTarget).getEntity() instanceof PlayerEntity var9) {
                        if (this.i || this.a_boolean______________________(new Object[]{var9, 104887668870823L})) {
                            this.i = true;
                            this.g = var9;
                            this.e.a_void_____________________(new Object[]{93540861878173L});
                            var1.a_void_____(new Object[0]);
                        }
                    }
                }
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 36160164621105L;
        long var6 = var2 ^ 91260814807711L;
        long var8 = var2 ^ 18621355024670L;
        long var10 = var2 ^ 85547089512629L;
        long var12 = var2 ^ 81856115773718L;
        long var14 = var2 ^ 71343916077473L;
        if (!this.i) {
            return null;
        } else if (b.player.isUsingItem()) {
            return null;
        } else {
            PlayerEntity var17 = this.g != null && this.g.isAlive() ? this.g : CombatUtil.a_class_1657(new Object[]{var14});
            if (var17 != null && !(b.player.distanceTo(var17) > 3.0F) && b.player.canSee(var17)) {
                if (this.a_boolean______________________(new Object[]{var17, var8})) {
                    return null;
                } else {
                    RenderHelper27 var10000 = this.e;
                    Object[] var10004 = new Object[]{null, var10};
                    var10004[0] = 1000.0F;
                    if (var10000.a(var10004)) {
                        this.i = false;
                        this.g = null;
                        return null;
                    } else {
                        RenderBatch var18 = WorldUtil.a_RenderBatch_(new Object[]{var17.getEyePos(), var12});
                        this.i = false;
                        this.g = null;
                        new TickEvent12().b_boolean__(new Object[]{var4});
                        this.f = true;
                        InventoryUtil2.a_void_________(new Object[]{var17, var6});
                        this.f = false;
                        return var18;
                    }
                }
            } else {
                this.i = false;
                this.g = null;
                return null;
            }
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        this.i = false;
        this.g = null;
        this.f = false;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        this.i = false;
        this.g = null;
        this.f = false;
        Prestige.o.f(new Object[]{this, var4});
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
