package dev.zprestige.prestige.network;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.render.RenderContract2;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

public class PingManager implements RenderContract2 {
    private final Object2IntMap a;
    private final HashSet c;
    private static final long e = 5903295852427870243L;

    public PingManager(long var1) {
        super();
        var1 = 0L;
        var1 = 140135262182266L ^ var1;
        long var3 = var1 ^ 50219082031318L;
        this.a = new Object2IntOpenHashMap();
        this.c = new HashSet();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof EntityStatusS2CPacket var5 && var5.getStatus() == (int)e && var5.getEntity(b.world) instanceof PlayerEntity var8) {
            int var9 = this.a.getOrDefault(var8.getUuid(), 0);
            this.a.put(var8.getUuid(), ++var9);
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (!this.a.isEmpty()) {
            this.c.clear();

            for (PlayerEntity var6 : b.world.getPlayers()) {
                UUID var7 = var6.getUuid();
                this.c.add(var7);
                if (var6.deathTime > 0 || var6.getHealth() <= 0.0F || var6.isDead()) {
                    this.a.removeInt(var7);
                }
            }

            if (!this.a.isEmpty()) {
                this.a.keySet().retainAll(this.c);
            }
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.a.clear();
    }

    public int a(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        return this.a.getOrDefault(var4.getUuid(), 0);
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
