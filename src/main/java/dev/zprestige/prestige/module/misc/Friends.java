package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.StringEvent2;
import dev.zprestige.prestige.event.TickEvent28;
import dev.zprestige.prestige.type.EventPhase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;

public class Friends extends Module {
    public Friends() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;

    @Subscribe
    public void a(StringEvent2 var1) {
        if ((Boolean)this.a.getValue()) {
            for (Object var6 : Prestige.g.a()) {
                if (var1.b_String_(new Object[0]).contains((CharSequence)var6)) {
                    var1.b(new Object[]{var6});
                    var1.c(new Object[]{"" + Formatting.AQUA + var6 + Formatting.RESET});
                    var1.a_void_____(new Object[0]);
                }
            }
        }
    }

    @Subscribe
    public void a(EntityEvent var1) {
        if ((Boolean)this.d.getValue()) {
            Entity var7 = var1.a(new Object[0]);
            if (var7 instanceof PlayerEntity && Prestige.g.a(new Object[]{var7.getName().getString(), 24848952222090L})) {
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(TickEvent28 var1) {
        if ((Boolean)this.d.getValue()) {
            HitResult var7 = b.crosshairTarget;
            if (var7 != null) {
                if (var7.getType() == Type.ENTITY) {
                    Entity var8 = ((EntityHitResult)var7).getEntity();
                    if (var8 instanceof PlayerEntity && Prestige.g.a(new Object[]{var8.getName().getString(), 24848952222090L})) {
                        var1.a_void_____(new Object[0]);
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(BoolEvent var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
            if ((Boolean)this.d.getValue()) {
                HitResult var7 = b.crosshairTarget;
                if (var7 != null) {
                    if (var7.getType() == Type.ENTITY) {
                        Entity var8 = ((EntityHitResult)var7).getEntity();
                        if (var8 instanceof PlayerEntity && Prestige.g.a(new Object[]{var8.getName().getString(), 24848952222090L})) {
                            b.player.handSwinging = false;
                            var1.a_void_____(new Object[0]);
                        }
                    }
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
