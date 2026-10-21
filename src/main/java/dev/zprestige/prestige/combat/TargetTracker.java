package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;

public class TargetTracker implements RenderContract2 {
    private PlayerEntity a;
    private long c;

    public TargetTracker(long var1) {
        super();
        var1 = 0L;
        var1 = 108076624727597L ^ var1;
        long var3 = var1 ^ 134580352215820L;
        this.a = null;
        this.c = 0L;
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.a != null && (b.world == null || !this.a.isAlive() || this.a.distanceTo(b.player) > 15.0F || System.currentTimeMillis() - this.c > 5000L)) {
            this.a = null;
        }
    }

    @Subscribe
    public void a(EntityEvent var1) {
        Event.b();
        Entity var9 = var1.a(new Object[0]);
        if (var9 instanceof PlayerEntity var10) {
            this.a_void____(new Object[]{var10, 64033097141446L});
        } else if (var9 instanceof EndCrystalEntity) {
            for (PlayerEntity var12 : b.world.getPlayers()) {
                if (var12 != null
                    && var12 != b.player
                    && !Prestige.g.a(new Object[]{var12.getName().getString(), 24848952222090L})
                    && var12.distanceTo(var9) <= 5.0F) {
                    this.a_void____(new Object[]{var12, 64033097141446L});
                }
            }
        }
    }

    public PlayerEntity a(Object[] var1) {
        return this.a;
    }

    public void a_void____(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 108076624727597L ^ var3;
        long var5 = var3 ^ 86787500893025L;
        if (!Prestige.g.a(new Object[]{var2.getName().getString(), var5})) {
            this.a = var2;
            this.c = System.currentTimeMillis();
        }
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
