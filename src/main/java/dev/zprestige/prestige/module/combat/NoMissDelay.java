package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent28;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.type.EventPhase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;

public class NoMissDelay extends Module {
    private IntSetting a;
    private BoolSetting d;
    private FloatSetting c;
    private boolean i = false;
    private static final long l = 1273304670500028516L;

    public NoMissDelay() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.c.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL368_0 -> (Boolean)this.d.getValue(), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Subscribe
    public void a(TickEvent28 var1) {
        Event.b();
        HitResult var11 = b.crosshairTarget;
        if (var11 != null) {
            if ((InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L}) || b.player.getMainHandStack().getItem() instanceof AxeItem)
                && var11.getType() == Type.MISS) {
                if ((Boolean)this.d.getValue() && !this.d(new Object[]{6568117175539L})) {
                    this.i = true;
                    return;
                }

                int var10002 = (int)l;
                Object[] var10005 = new Object[]{null, null, 97028099236409L};
                var10005[1] = var10002;
                var10005[0] = 1;
                this.i = RenderUtil5.a_int__________________________(var10005) <= (Integer)this.a.getValue();
                if (!this.i) {
                    var1.a_void_____(new Object[0]);
                }
            }
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 38732078045089L ^ var2;
        long var4 = var2 ^ 53166753329880L;
        long var6 = var2 ^ 26056511006573L;
        float var9 = (Float)this.c.getValue();
        float var10 = var9 * var9;
        Event.b();

        for (PlayerEntity var12 : b.world.getPlayers()) {
            if (var12 != b.player
                && var12.isAlive()
                && Prestige.i.a_boolean_(new Object[]{var12, var6})
                && !Prestige.g.a(new Object[]{var12.getName().getString(), var4})
                && b.player.squaredDistanceTo(var12) <= var10) {
                return true;
            }
        }

        return false;
    }

    @Subscribe
    public void a(BoolEvent var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.POST) {
            HitResult var7 = b.crosshairTarget;
            if (var7 != null) {
                if ((InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L}) || b.player.getMainHandStack().getItem() instanceof AxeItem)
                    && var7.getType() == Type.MISS
                    && !this.i) {
                    b.player.handSwinging = false;
                    var1.a_void_____(new Object[0]);
                }
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
