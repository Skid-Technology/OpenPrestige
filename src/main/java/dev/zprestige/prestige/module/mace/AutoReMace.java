package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.module.combat.AutoElytra;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.player.PlayerEntity;

public class AutoReMace extends Module {
    public AutoReMace() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private FloatSetting c;
    private RenderHelper27 d = new RenderHelper27(26293358099357L);
    private boolean i;
    private PlayerEntity e;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (this.i) {
            if (this.e != null && this.e.isAlive() && !(b.player.distanceTo(this.e) > 100.0F)) {
                label67: {
                    RenderHelper27 var10000 = this.d;
                    Object[] var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = 1000.0F;
                    if (var10000.a(var10004)) {
                        if (b.player.isOnGround()) {
                            break label67;
                        }

                        var10000 = this.d;
                        var10004 = new Object[]{null, 2505922520844L};
                        var10004[0] = 5000.0F;
                        if (var10000.a(var10004)) {
                            break label67;
                        }
                    }

                    var10000 = this.d;
                    var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = 100.0F;
                    if (!var10000.a(var10004)) {
                        return;
                    }

                    var10000 = this.d;
                    var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = 600.0F;
                    if (!var10000.a(var10004)) {
                        if (!AutoElytra.v.isEnabled()) {
                            AutoElytra.v.toggle(new Object[]{115103427876780L});
                        }

                        RenderBatch var18 = WorldUtil.a_RenderBatch_(
                            new Object[]{
                                PositionUtil.a(new Object[]{this.e, 67736483458412L}).add(0.0, ((Float)this.c.getValue()).floatValue(), 0.0), 6563042098863L
                            }
                        );
                        float var23 = (Float)this.a.getValue() / 2.0F;
                        Object[] var28 = new Object[]{null, null, null, null, 40447786406815L};
                        var28[3] = 0.5F;
                        var28[2] = 0.0F;
                        var28[1] = var23;
                        var28[0] = var18;
                        WorldUtil.c(var28);
                        return;
                    }

                    var10000 = this.d;
                    var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = 1400.0F;
                    if (!var10000.a(var10004)) {
                        RenderBatch var17 = WorldUtil.a_RenderBatch_(new Object[]{this.e.getEyePos(), 6563042098863L});
                        float var10001 = (Float)this.a.getValue() / 2.0F;
                        Object[] var10006 = new Object[]{null, null, null, null, 40447786406815L};
                        var10006[3] = 0.5F;
                        var10006[2] = 0.0F;
                        var10006[1] = var10001;
                        var10006[0] = var17;
                        WorldUtil.c(var10006);
                        return;
                    }

                    if (PositionUtil.a_double___________(new Object[]{b.player, 111298775242240L}) < 1.0) {
                        this.i = false;
                    }

                    return;
                }

                this.i = false;
            } else {
                this.i = false;
            }
        }
    }

    @Subscribe
    public void a(EntityEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerEntity var10 && InventoryUtil.o(new Object[]{b.player.getMainHandStack(), 133561865349436L})) {
            this.e = var10;
            this.i = true;
            this.d.a_void_____________________(new Object[]{93540861878173L});
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
