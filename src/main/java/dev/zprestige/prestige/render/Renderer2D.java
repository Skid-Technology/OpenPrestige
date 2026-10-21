package dev.zprestige.prestige.render;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.FloatEvent3;
import dev.zprestige.prestige.event.FloatPositionEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent4;
import dev.zprestige.prestige.module.movement.Freecam;
import dev.zprestige.prestige.network.NetworkHelper2;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.math.Vec3d;

public class Renderer2D implements RenderContract2 {
    private final CopyOnWriteArrayList a;
    private RenderBatch c;
    private RenderBatch d;
    private RenderBatch e;
    private static float f = 300.0F;
    private static float g = 90.0F;
    private RenderBatch h;
    boolean i;
    private Object j;
    private RenderBatch k;
    private Object l;
    final List m;

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        this.i = false;
        if (!this.m.isEmpty()) {
            ArrayList var7 = new ArrayList(this.m);
            this.m.clear();

            for (Runnable var9 : (Iterable<Runnable>)(var7)) {
                try {
                    var9.run();
                } catch (Throwable var12) {
                }
            }
        }

        if (this.a.isEmpty() && this.c == null && this.h == null) {
            this.d = null;
            this.e = null;
        } else {
            if (this.d == null) {
                this.d = new RenderBatch(b.player.getYaw(), b.player.getPitch());
            }

            boolean var13 = false;
            boolean var14 = this.c != null;

            for (RenderContract3 var10 : (Iterable<RenderContract3>)(this.a)) {
                RenderBatch var11 = var10.a(new Object[]{87456531541945L});
                if (var11 != null) {
                    this.c = var11;
                    var13 = true;
                }
            }

            if (!var13 && this.c != null) {
                float var16 = Math.abs(this.c.a(new Object[0]) - b.player.getYaw());
                if (var16 > f) {
                    float var18 = this.c.a(new Object[0]) - f * Math.signum(this.c.a(new Object[0]) - b.player.getYaw());
                    this.c = new RenderBatch(var18, this.c.b(new Object[0]));
                } else {
                    this.c = null;
                }
            }

            if (var14 && this.c == null) {
                float var17 = b.player.getYaw() + (float)(Math.random() * 0.06 - 0.03);
                float var19 = b.player.getPitch() + (float)(Math.random() * 0.06 - 0.03);
                this.h = new RenderBatch(var17, var19);
            } else if (this.c != null) {
                this.h = null;
            }

            this.d = this.c != null ? this.c : (this.h != null ? this.h : null);
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (this.c != null) {
            var1.b(new Object[]{this.c.a(new Object[0])});
            var1.c(new Object[]{this.c.b(new Object[0])});
        } else if (this.h != null) {
            var1.b(new Object[]{this.h.a(new Object[0])});
            var1.c(new Object[]{this.h.b(new Object[0])});
            this.h = null;
        }

        this.e = this.c;
        var1.a_void_____(new Object[0]);
    }

    @Subscribe
    public void a(FloatPositionEvent var1) {
        if (this.c != null) {
            var1.b(new Object[]{this.c.a(new Object[0])});
        }

        var1.a_void_____(new Object[0]);
    }

    @Subscribe
    public void a(FloatEvent3 var1) {
        if (this.c != null) {
            var1.a = this.c.a(new Object[0]);
            var1.b = this.c.b(new Object[0]);
        }

        var1.a_void_____(new Object[0]);
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractItemC2SPacket var11) {
            RenderBatch var14 = this.d(new Object[]{74534716520795L});
            if (var14 == null) {
                var14 = this.c != null ? this.c : this.h;
            }

            if (var14 != null) {
                NetworkHelper2 var13 = new NetworkHelper2(var11);
                float var10001 = var14.a(new Object[0]);
                Object[] var10004 = new Object[]{null, 51004495053360L};
                var10004[0] = var10001;
                var13.a(var10004);
                var10001 = var14.b(new Object[0]);
                var10004 = new Object[]{null, 93811211214329L};
                var10004[0] = var10001;
                var13.b(var10004);
            }
        }
    }

    public void a(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        this.c = var2;
    }

    public void b(Object[] var1) {
        this.c = null;
        this.h = null;
    }

    public RenderBatch a_RenderBatch(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        long var3 = (Long)var1[1];
        var3 = 117860352239008L ^ var3;
        long var5 = var3 ^ 51437924751915L;
        RenderBatch var8 = this.d != null ? this.d : new RenderBatch(b.player.getYaw(), b.player.getPitch());
        float var10002 = g;
        Object[] var10005 = new Object[]{null, null, null, var5};
        var10005[2] = var10002;
        var10005[1] = var2;
        var10005[0] = var8;
        return WorldUtil.c_RenderBatch(var10005);
    }

    public RenderBatch b_RenderBatch____________(Object[] var1) {
        return this.d;
    }

    public RenderBatch c(Object[] var1) {
        return this.c;
    }

    public synchronized boolean a_boolean_____________________________________________(Object[] var1) {
        Object var3 = var1[0];
        RenderBatch var2 = (RenderBatch)var1[1];
        long var4 = (Long)var1[2];
        var4 = 117860352239008L ^ var4;
        long var6 = var4 ^ 80391577534074L;
        if (var3 != null && var2 != null) {
            if (!this.c_boolean____(new Object[]{var6})) {
                this.d_void_(new Object[0]);
                return false;
            } else {
                if (this.l != b.world) {
                    this.d_void_(new Object[0]);
                }

                if (this.j != null && this.j != var3) {
                    return false;
                } else {
                    this.j = var3;
                    this.k = new RenderBatch(var2.a(new Object[0]), var2.b(new Object[0]));
                    this.l = b.world;
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public synchronized boolean b_boolean_______(Object[] var1) {
        Object var4 = var1[0];
        long var2 = (Long)var1[1];
        var2 = 117860352239008L ^ var2;
        long var5 = var2 ^ 120995730053419L;
        if (var4 != null && this.k != null) {
            if (this.l != b.world || !this.c_boolean____(new Object[]{var5})) {
                this.d_void_(new Object[0]);
            }

            return this.j == var4 && this.k != null;
        } else {
            return false;
        }
    }

    public synchronized RenderBatch d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 117860352239008L ^ var2;
        long var4 = var2 ^ 36350391803780L;
        if (this.k == null) {
            return null;
        } else {
            if (this.l != b.world || !this.c_boolean____(new Object[]{var4})) {
                this.d_void_(new Object[0]);
            }

            return this.k == null ? null : new RenderBatch(this.k.a(new Object[0]), this.k.b(new Object[0]));
        }
    }

    public synchronized void c_void___(Object[] var1) {
        Object var4 = var1[0];
        if (var4 != null && this.j == var4) {
            this.d_void_(new Object[0]);
        }
    }

    public boolean c_boolean____(Object[] var1) {
        if (b.player != null && b.world != null && !b.player.hasVehicle() && b.getCameraEntity() == b.player) {
            if (Prestige.moduleManager != null && Prestige.moduleManager.getModules() != null) {
                for (Module var6 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
                    if (var6 instanceof Freecam && var6.isEnabled()) {
                        return false;
                    }
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Subscribe
    public synchronized void a(TickEvent3 var1) {
        this.d_void_(new Object[0]);
    }

    @Subscribe
    public synchronized void a(TickEvent4 var1) {
        if (this.l != null && this.l != b.world) {
            this.d_void_(new Object[0]);
        }
    }

    private void d_void_(Object[] var1) {
        this.j = null;
        this.k = null;
        this.l = null;
    }

    public Renderer2D(long var1) {
        super();
        var1 = 0L;
        var1 = 117860352239008L ^ var1;
        long var3 = var1 ^ 118450842598666L;
        this.a = new CopyOnWriteArrayList();
        this.h = null;
        this.m = new CopyOnWriteArrayList();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    public void e(Object[] var1) {
        RenderContract3 var4 = (RenderContract3)var1[0];
        if (this.a.addIfAbsent(var4)) {
            this.a.sort(Comparator.<RenderContract3>comparingInt(RenderContract3::a).reversed());
        }
    }

    public void f(Object[] var1) {
        RenderContract3 var2 = (RenderContract3)var1[0];
        this.a.remove(var2);
    }

    public boolean d_boolean______(Object[] var1) {
        return this.i;
    }

    public void g(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        this.i = var2;
    }

    public void h(Object[] var1) {
        Runnable var4 = (Runnable)var1[0];
        if (var4 != null) {
            this.m.add(var4);
        }
    }

    public void i(Object[] var1) {
        int var2 = (Integer)var1[0];
        this.m.add(new RenderHelper24(this, var2));
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
