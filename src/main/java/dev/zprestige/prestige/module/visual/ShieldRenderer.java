package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.BoolPositionEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.LivingEvent;
import dev.zprestige.prestige.event.TickEvent19;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.math.Vec3d;

public class ShieldRenderer extends Module {
    public ShieldRenderer() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;
    private EnumSetting c;
    private FloatSetting e;
    private FloatSetting f;
    private FloatSetting g;
    private FloatSetting h;
    private FloatSetting i;
    private FloatSetting j;
    private ColorSetting k;
    private ColorSetting l;
    private static final Map m = new HashMap();
    private static final Map n = new HashMap();
    private static final long p = 5370769767999209572L;

    @Subscribe
    public void a(BoolPositionEvent var1) {
        var1.b = this.isEnabled();
        var1.a = (Boolean)this.d.getValue();
        var1.d = new Vec3d(((Float)this.e.getValue()).floatValue(), ((Float)this.f.getValue()).floatValue(), ((Float)this.g.getValue()).floatValue());
        var1.e = new Vec3d(((Float)this.h.getValue()).floatValue(), ((Float)this.i.getValue()).floatValue(), ((Float)this.j.getValue()).floatValue());
        var1.c = (Boolean)this.a.getValue();
        var1.f = ((String)this.c.getValue()).toString();
        var1.g = (Color)this.k.getValue();
        var1.h = (Color)this.l.getValue();
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();

        for (PlayerEntity var6 : b.world.getPlayers()) {
            UUID var7 = var6.getUuid();
            if (var6.isUsingItem() && var6.getStackInHand(var6.getActiveHand()).getItem() instanceof ShieldItem) {
                m.put(var7, (Integer)m.getOrDefault(var7, 0) + 1);
            } else {
                m.remove(var7);
            }
        }

        n.entrySet().removeIf(lpL391_0 -> (Integer)((java.util.Map.Entry)lpL391_0).getValue() <= 1);
        n.replaceAll((lpL390_0, lpL390_1) -> ((java.lang.Integer)lpL390_1) > 1 ? ((java.lang.Integer)lpL390_1) - 1 : ((java.lang.Integer)lpL390_1));
    }

    @Subscribe
    public void a(LivingEvent var1) {
        Event.b();
        if (var1.a instanceof PlayerEntity var5) {
            n.put(var5.getUuid(), (int)p);
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
