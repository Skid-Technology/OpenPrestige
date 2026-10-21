package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PlayerEvent;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent6;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;
import net.minecraft.world.GameMode;

public class AntiBot extends Module {
    private MultiSetting a;
    private IntSetting c;
    private IntSetting d;
    private IntSetting e;
    private static final UUID f = new UUID(0L, 0L);
    private Map g = new IdentityHashMap();

    public AntiBot() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.e.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL55_0 -> this.a.isSelected(new Object[]{"Ping", 47850572908778L}), 98265949679296L});
        this.d
            .setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL56_0 -> this.a.isSelected(new Object[]{"TabList", 47850572908778L}), 98265949679296L});
        this.c
            .setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL57_0 -> this.a.isSelected(new Object[]{"Packet", 47850572908778L}), 98265949679296L});
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (this.a.isSelected(new Object[]{"Packet", 47850572908778L}) && var1.a(new Object[0]) instanceof EntityPositionS2CPacket && b.world != null) {
            Entity var9 = b.world.getEntityById(((EntityPositionS2CPacket)var1.a(new Object[0])).entityId());
            if (var9 == null) {
                return;
            }

            if (var9 instanceof PlayerEntity var10 && !Prestige.i.a_Set(new Object[0]).contains(var9)) {
                int var11 = (Integer)this.c.getValue();
                int var12 = (Integer)this.g.getOrDefault(var10, 0) + 1;
                if (var12 >= var11) {
                    Prestige.i.a(new Object[]{var10, 98777068804914L});
                    this.g.remove(var10);
                } else {
                    this.g.put(var10, var12);
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.g.clear();
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (!this.g.isEmpty()) {
            this.g.keySet().removeIf(lpL54_0 -> !((net.minecraft.entity.player.PlayerEntity)lpL54_0).isAlive());
        }
    }

    @Subscribe
    public void a(TickEvent6 var1) {
        var1.a_void_____(new Object[0]);
    }

    @Subscribe
    public void a(PlayerEvent var1) {
        PlayerEntity var7 = var1.a(new Object[0]);
        Event.b();
        UUID var8 = var7.getUuid();
        if (!this.a.isSelected(new Object[]{"UUID", 47850572908778L}) || !var8.equals(f) && var8.version() == 4) {
            PlayerListEntry var9 = b.getNetworkHandler() != null ? b.getNetworkHandler().getPlayerListEntry(var8) : null;
            if (this.a.isSelected(new Object[]{"TabList", 47850572908778L}) && var7.age * 50L >= ((Integer)this.d.getValue()).intValue() && var9 == null) {
                var1.a_void_____(new Object[0]);
            } else {
                if (this.a.isSelected(new Object[]{"Ping", 47850572908778L}) && var9 != null) {
                    GameMode var10 = var9.getGameMode();
                    boolean var11 = var10 == GameMode.SURVIVAL || var10 == GameMode.ADVENTURE;
                    if (var11 && var9.getLatency() == 0 && var7.age * 50L >= ((Integer)this.e.getValue()).intValue()) {
                        var1.a_void_____(new Object[0]);
                    }
                }
            }
        } else {
            var1.a_void_____(new Object[0]);
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
