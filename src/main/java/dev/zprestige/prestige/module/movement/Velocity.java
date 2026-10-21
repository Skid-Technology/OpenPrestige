package dev.zprestige.prestige.module.movement;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.DoubleEvent3;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class Velocity extends Module {
    public Velocity() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private BoolSetting d;
    private RangeSetting c;
    private RangeSetting e;
    private ArrayList f = new ArrayList();

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        new ArrayList<>(this.f).forEach(lpL356_0 -> {
            if (b.player != null && b.player.isOnGround()) {
                this.f.remove(((net.minecraft.network.packet.s2c.play.ExplosionS2CPacket)lpL356_0));
            }
        });
    }

    @Subscribe
    public void a(DoubleEvent3 var1) {
        if ((this.f.isEmpty() || !(Boolean)this.d.getValue()) && RenderUtil5.a.nextFloat() * 100.0F <= (Float)this.a.getValue()) {
            float var7 = this.c.randomInRange(new Object[]{877569900989L}) / 100.0F;
            float var8 = this.e.randomInRange(new Object[]{877569900989L}) / 100.0F;
            var1.b(new Object[]{var1.a(new Object[0]) * var8});
            var1.c(new Object[]{var1.b_double_(new Object[0]) * var7});
            var1.d(new Object[]{var1.c_double_(new Object[0]) * var8});
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof ExplosionS2CPacket var5
            && var5.playerKnockback().isPresent()
            && var5.playerKnockback().get().lengthSquared() > 0.0) {
            this.f.add(var5);
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        this.f.clear();
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.f.clear();
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
