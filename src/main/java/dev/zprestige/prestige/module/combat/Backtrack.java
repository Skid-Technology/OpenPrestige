package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.data.LongData3;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TrackedPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.BundleS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitiesDestroyS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityPositionSyncS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Backtrack extends Module {
    public Backtrack() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private RangeSetting a;
    private RangeSetting c;
    private BoolSetting d;
    private Map e = new ConcurrentHashMap();
    private ConcurrentLinkedQueue f = new ConcurrentLinkedQueue();
    private volatile long g;
    private volatile int h = -1;
    private volatile int i = -1;
    private Vec3d j;
    private boolean k;
    private volatile Vec3d l = Vec3d.ZERO;
    private volatile Vec3d m = Vec3d.ZERO;
    private volatile Vec3d n = Vec3d.ZERO;
    private int o;

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        Object[] var10005 = new Object[]{null, 118606147722755L};
        var10005[0] = false;
        this.k(var10005);
        this.l(new Object[]{88686438666488L});
        if (b.world == null || b.player == null) {
            Object[] var35 = new Object[]{null, 118606147722755L};
            var35[0] = true;
            this.k(var35);
            this.h = -1;
        } else if (b.player.age < 10) {
            Object[] var34 = new Object[]{null, 125085024114902L};
            var34[0] = true;
            this.n(var34);
        } else {
            Packet var21 = var1.a(new Object[0]);
            if (!(var21 instanceof PlayerRespawnS2CPacket) && !(var21 instanceof GameStateChangeS2CPacket) && !(var21 instanceof DisconnectS2CPacket)) {
                ArrayList var22 = new ArrayList();
                if (var21 instanceof BundleS2CPacket var23) {
                    for (Packet var25 : var23.getPackets()) {
                        var22.add(var25);
                    }
                } else {
                    var22.add(var21);
                }

                Vec3d var27 = null;

                for (Packet var29 : (Iterable<Packet>)(var22)) {
                    Vec3d var26 = this.a(new Object[]{var29, 43901125296446L});
                    if (var26 != null) {
                        var27 = var26;
                    }

                    if (this.a_boolean_________________(new Object[]{var29, 83149107607277L})) {
                        Object[] var30 = new Object[]{null, 118606147722755L};
                        var30[0] = true;
                        this.k(var30);
                        return;
                    }
                }

                if (this.h == -1) {
                    Object[] var33 = new Object[]{null, 118606147722755L};
                    var33[0] = true;
                    this.k(var33);
                } else if (!this.e(new Object[]{55962480142592L})) {
                    Object[] var32 = new Object[]{null, 118606147722755L};
                    var32[0] = true;
                    this.k(var32);
                } else {
                    if (var27 != null) {
                        this.k = this.d(new Object[]{var27, 637617902806L});
                        this.j = var27;
                    }

                    if (this.k) {
                        var1.a_void_____(new Object[0]);
                        this.j(new Object[]{var21, 73185779439232L});
                    } else {
                        Object[] var31 = new Object[]{null, 118606147722755L};
                        var31[0] = true;
                        this.k(var31);
                    }
                }
            } else {
                Object[] var10004 = new Object[]{null, 125085024114902L};
                var10004[0] = true;
                this.n(var10004);
            }
        }
    }

    @Subscribe
    public void a(EntityEvent var1) {
        Event.b();
        Entity var9 = var1.a(new Object[0]);
        if (var9 != null) {
            if (var9 instanceof PlayerEntity && this.f(new Object[]{var9, 122803427193588L})) {
                if (this.e(new Object[]{55962480142592L})) {
                    int var10 = var9.getId();
                    if (var10 != this.h && var10 != this.i) {
                        this.i = var10;
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        this.l = this.m;
        if (this.h != -1 && this.o > 0) {
            this.m = this.m.add(this.n.subtract(this.m).multiply(1.0 / this.o));
            this.o--;
        }

        if (b.world != null && b.player != null) {
            if (!b.player.isAlive()) {
                Object[] var10004 = new Object[]{null, 125085024114902L};
                var10004[0] = true;
                this.n(var10004);
            }
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        Object[] var10004 = new Object[]{null, 125085024114902L};
        var10004[0] = false;
        this.n(var10004);
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if (this.h != -1 && b.world != null) {
            if (this.m.getY() != 0.0) {
                Entity var14 = b.world.getEntityById(this.h);
                if (var14 != null) {
                    float var15 = MatrixStack.a_float_______________(new Object[]{601921721859L});
                    Vec3d var16 = this.l.add(this.m.subtract(this.l).multiply(var15));
                    MatrixCameraData var10000 = var1.b;
                    RenderHelper8 var10001 = var1.a;
                    float var10002 = (float)(var16.getX() - 0.25);
                    float var10003 = (float)var16.getY();
                    float var10004 = (float)(var16.getZ() - 0.25);
                    float var10005 = (float)(var16.getX() + 0.25);
                    float var10006 = (float)(var16.getY() + 2.0);
                    float var10007 = (float)(var16.getZ() + 0.25);
                    Color var10008 = MathHelper4.d(new Object[]{122780339483702L});
                    Object[] var10012 = new Object[]{null, null, 20220661505083L};
                    var10012[1] = 0.3F;
                    var10012[0] = var10008;
                    Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.b(var10012), 108390846498585L};
                    var10011[7] = var10007;
                    var10011[6] = var10006;
                    var10011[5] = var10005;
                    var10011[4] = var10004;
                    var10011[3] = var10003;
                    var10011[2] = var10002;
                    var10011[1] = var10001;
                    var10011[0] = var10000;
                    RenderState.h(var10011);
                    var10000 = var1.b;
                    var10001 = var1.a;
                    var10002 = (float)(var16.getX() - 0.25);
                    var10003 = (float)var16.getY();
                    var10004 = (float)(var16.getZ() - 0.25);
                    var10005 = (float)(var16.getX() + 0.25);
                    var10006 = (float)(var16.getY() + 2.0);
                    var10007 = (float)(var16.getZ() + 0.25);
                    var10008 = MathHelper4.d(new Object[]{122780339483702L});
                    var10012 = new Object[]{null, null, 20220661505083L};
                    var10012[1] = 1.0F;
                    var10012[0] = var10008;
                    var10011 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.b(var10012), 119015971454350L};
                    var10011[7] = var10007;
                    var10011[6] = var10006;
                    var10011[5] = var10005;
                    var10011[4] = var10004;
                    var10011[3] = var10003;
                    var10011[2] = var10002;
                    var10011[1] = var10001;
                    var10011[0] = var10000;
                    RenderState.g(var10011);
                }
            }
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 15258385839568L;
        Object[] var10004 = new Object[]{null, var4};
        var10004[0] = false;
        this.n(var10004);
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 94987294404531L;
        Object[] var10004 = new Object[]{null, var4};
        var10004[0] = true;
        this.n(var10004);
    }

    private Vec3d a(Object[] var1) {
        Packet var4 = (Packet)var1[0];
        long var2 = (Long)var1[1];
        var2 = 7286369456822L ^ var2;
        long var5 = var2 ^ 28973116157516L;
        long var7 = var2 ^ 81994662907275L;
        long var9 = var2 ^ 96044739017963L;
        Event.b();
        Integer var12 = null;
        if (var4 instanceof EntityS2CPacket var13) {
            Entity var16 = var13.getEntity(b.world);
            if (var16 != null && var13.isPositionChanged()) {
                var12 = var16.getId();
                TrackedPosition var17 = (TrackedPosition)this.e
                    .computeIfAbsent(var12, lpL314_0 -> this.a_class_7422(new Object[]{PositionUtil.a(new Object[]{var16, 67736483458412L}), 14173057341269L}));
                var17.setPos(var17.withDelta(var13.getDeltaX(), var13.getDeltaY(), var13.getDeltaZ()));
            }
        } else if (var4 instanceof EntityPositionS2CPacket var14) {
            var12 = var14.entityId();
            int var10001 = var12;
            Object[] var10005 = new Object[]{null, var14.change().position(), var5};
            var10005[0] = var10001;
            this.m(var10005);
        } else if (var4 instanceof EntitiesDestroyS2CPacket var15) {
            for (int var23 : var15.getEntityIds()) {
                this.e.remove(var23);
                if (var23 == this.h) {
                    this.h = -1;
                    this.j = null;
                    Object[] var10004 = new Object[]{null, var7};
                    var10004[0] = true;
                    this.k(var10004);
                }
            }

            return null;
        }

        if (var4 instanceof EntityPositionSyncS2CPacket var19) {
            var12 = var19.id();
            int var24 = var12;
            Object[] var25 = new Object[]{null, var19.values().position(), var5};
            var25[0] = var24;
            this.m(var25);
        }

        if (var12 != null && var12 == this.h) {
            TrackedPosition var20 = (TrackedPosition)this.e.get(var12);
            if (var20 == null) {
                return null;
            } else {
                Vec3d var21 = var20.withDelta(0L, 0L, 0L);
                this.o(new Object[]{var21, var9});
                return var21;
            }
        } else {
            return null;
        }
    }

    private boolean a_boolean_________________(Object[] var1) {
        Packet var4 = (Packet)var1[0];
        if (var4 instanceof PlayerPositionLookS2CPacket) {
            return true;
        } else {
            return var4 instanceof EntityVelocityUpdateS2CPacket var6 && b.player != null ? var6.getEntityId() == b.player.getId() : false;
        }
    }

    private boolean d(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        long var3 = (Long)var1[1];
        var3 = 7286369456822L ^ var3;
        long var5 = var3 ^ 70745482860988L;
        if (this.j == null) {
            return false;
        } else {
            double var8 = this.a_double____(new Object[]{this.j, var5});
            double var10 = this.a_double____(new Object[]{var2, var5});
            return var10 > var8 + 0.001;
        }
    }

    private double a_double____(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        Vec3d var5 = b.player.getEyePos();
        Vec3d var6 = var4.add(0.0, 0.9, 0.0);
        Vec3d var7 = var5.add(var6.subtract(var5).normalize().multiply(256.0));
        Box var8 = new Box(var4.x - 0.3, var4.y, var4.z - 0.3, var4.x + 0.3, var4.y + 1.8, var4.z + 0.3).expand(0.1);
        Optional<Vec3d> var9 = var8.raycast(var5, var7);
        return var9.map(var5::distanceTo).orElse(0.0);
    }

    private void j(Object[] var1) {
        Packet var4 = (Packet)var1[0];
        long var2 = (Long)var1[1];
        var2 = 7286369456822L ^ var2;
        long var5 = var2 ^ 75745140032907L;
        Event.b();
        long var8 = System.currentTimeMillis() + (long)this.c.randomInRange(new Object[]{var5});
        if (var8 <= this.g) {
            var8 = this.g + 1L;
        }

        this.g = var8;
        this.f.add(new LongData3(var4, var8));
    }

    private void k(Object[] var1) {
        boolean var4 = (Boolean)var1[0];
        if (!this.f.isEmpty()) {
            ClientPlayNetworkHandler var6 = b.getNetworkHandler();
            if (var6 == null) {
                this.f.clear();
                this.g = 0L;
            } else {
                long var7 = System.currentTimeMillis();

                LongData3 var9;
                while ((var9 = (LongData3)this.f.peek()) != null && (var4 || var7 >= var9.at())) {
                    this.f.poll();

                    try {
                        ((net.minecraft.network.packet.Packet<net.minecraft.network.listener.ClientPlayPacketListener>)var9.as()).apply(var6);
                    } catch (Exception var10) {
                    }
                }

                if (this.f.isEmpty()) {
                    this.g = 0L;
                }
            }
        }
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 7286369456822L ^ var2;
        long var4 = var2 ^ 118282188369698L;
        long var6 = var2 ^ 67996640304205L;
        if (this.i != -1) {
            this.h = this.i;
            this.i = -1;
            this.j = null;
            Vec3d var9 = null;
            TrackedPosition var10 = (TrackedPosition)this.e.get(this.h);
            if (var10 != null) {
                var9 = var10.withDelta(0L, 0L, 0L);
            } else if (b.world != null) {
                Entity var11 = b.world.getEntityById(this.h);
                if (var11 != null) {
                    var9 = PositionUtil.a(new Object[]{var11, var4});
                }
            }

            this.p(new Object[]{var9 == null ? Vec3d.ZERO : var9});
            Object[] var10004 = new Object[]{null, var6};
            var10004[0] = true;
            this.k(var10004);
        }
    }

    private void m(Object[] var1) {
        int var2 = (Integer)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        ((TrackedPosition)this.e.computeIfAbsent(var2, lpL313_0 -> new TrackedPosition())).setPos(var5);
    }

    private TrackedPosition a_class_7422(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        TrackedPosition var5 = new TrackedPosition();
        var5.setPos(var4);
        return var5;
    }

    private void n(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        long var3 = (Long)var1[1];
        var3 = 7286369456822L ^ var3;
        long var5 = var3 ^ 31597945869411L;
        if (var2) {
            Object[] var10004 = new Object[]{null, var5};
            var10004[0] = true;
            this.k(var10004);
        } else {
            this.f.clear();
            this.g = 0L;
        }

        this.e.clear();
        this.h = -1;
        this.i = -1;
        this.j = null;
        this.k = false;
        this.p(new Object[]{Vec3d.ZERO});
    }

    private void o(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        this.n = var4;
        this.o = 3;
    }

    private void p(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        this.l = var2;
        this.m = var2;
        this.n = var2;
        this.o = 0;
    }

    private boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 7286369456822L ^ var2;
        long var4 = var2 ^ 70805521408905L;
        long var6 = var2 ^ 90460232118235L;
        long var8 = var2 ^ 84924141097610L;
        if (!(Boolean)this.d.getValue()) {
            return true;
        } else {
            ItemStack var11 = b.player.getMainHandStack();
            return InventoryUtil.a(new Object[]{var11, var4}) || InventoryUtil.b(new Object[]{var11, var6}) || InventoryUtil.o(new Object[]{var11, var8});
        }
    }

    private boolean f(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        Event.b();
        double var6 = b.player.distanceTo(var2);
        return var6 >= this.a.getMin() && var6 <= this.a.getMax();
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

    private static Exception a(Exception var0) {
        return var0;
    }
}
