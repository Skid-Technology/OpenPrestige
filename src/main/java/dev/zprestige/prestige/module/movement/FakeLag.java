package dev.zprestige.prestige.module.movement;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.module.combat.AutoElytra;
import dev.zprestige.prestige.network.NetworkUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.util.math.Vec3d;

public class FakeLag extends Module {
    private EnumSetting a;
    private RangeSetting c;
    private FloatSetting d;
    private RangeSetting e;
    private RangeSetting f;
    private MultiSetting g;
    private BoolSetting h;
    private Queue i = new ConcurrentLinkedQueue();
    private RenderHelper27 j = new RenderHelper27(26293358099357L);
    private RenderHelper27 k = new RenderHelper27(26293358099357L);
    private Vec3d l = Vec3d.ZERO;
    private boolean m;
    private boolean n;
    private boolean o;
    private float p = 0.0F;

    public FakeLag() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.e
            .setVisibility_RangeSetting(
                new Object[]{
                    (Predicate<Float>)lpL197_0 -> ((String)this.a.getValue()).equals("Timed")
                        || ((String)this.a.getValue()).equals("Dynamic")
                        || ((String)this.a.getValue()).equals("Knockback"),
                    37043306203287L
                }
            );
        this.g.setVisibility_MultiSetting(new Object[]{(Predicate<String>)lpL195_0 -> !((String)this.a.getValue()).equals("Manual"), 18435058659860L});
        this.c
            .setVisibility_RangeSetting(
                new Object[]{
                    (Predicate<Float>)lpL199_0 -> ((String)this.a.getValue()).equals("Ranged") || ((String)this.a.getValue()).equals("Dynamic"),
                    37043306203287L
                }
            );
        this.d
            .setVisibility_FloatSetting(
                new Object[]{
                    (Predicate<Float>)lpL198_0 -> ((String)this.a.getValue()).equals("Ranged") || ((String)this.a.getValue()).equals("Dynamic"),
                    35046756052036L
                }
            );
        this.f
            .setVisibility_RangeSetting(
                new Object[]{
                    (Predicate<Float>)lpL196_0 -> ((String)this.a.getValue()).equals("Ranged") || ((String)this.a.getValue()).equals("Dynamic"),
                    37043306203287L
                }
            );
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (b.world != null && b.player != null) {
            if (!((String)this.a.getValue()).equals("Manual")
                && var1.a(new Object[0]) instanceof ExplosionS2CPacket
                && this.g.isSelected(new Object[]{"On Explosion", 47850572908778L})) {
                if (!(
                    b.player
                            .squaredDistanceTo(
                                ((ExplosionS2CPacket)var1.a(new Object[0])).center().getX(),
                                ((ExplosionS2CPacket)var1.a(new Object[0])).center().getY(),
                                ((ExplosionS2CPacket)var1.a(new Object[0])).center().getZ()
                            )
                        > 100.0
                )) {
                    this.j(new Object[]{47590801564788L});
                }
            } else {
                if (((String)this.a.getValue()).equals("Knockback")
                    && var1.a(new Object[0]) instanceof EntityVelocityUpdateS2CPacket var13
                    && var13.getEntityId() == b.player.getId()) {
                    this.o = true;
                    this.k.a_void_____________________(new Object[]{93540861878173L});
                    this.e.reroll(new Object[]{57083216809697L});
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if (b.world != null && b.player != null) {
            if (((String)this.a.getValue()).equals("Manual")
                || (
                        !(var1.a(new Object[0]) instanceof PlayerInteractEntityC2SPacket)
                            || !this.g.isSelected(new Object[]{"On Interact Entity", 47850572908778L})
                    )
                    && (!(var1.a(new Object[0]) instanceof HandSwingC2SPacket) || !this.g.isSelected(new Object[]{"On Hand Swing", 47850572908778L}))
                    && (
                        !(var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket)
                            || !this.g.isSelected(new Object[]{"On Interact Block", 47850572908778L})
                    )
                    && (
                        !(var1.a(new Object[0]) instanceof PlayerInteractItemC2SPacket)
                            || !this.g.isSelected(new Object[]{"On Interact Item", 47850572908778L})
                    )
                    && (!(var1.a(new Object[0]) instanceof ClickSlotC2SPacket) || !this.g.isSelected(new Object[]{"On Click Slot", 47850572908778L}))) {
                if (!this.m && (!((String)this.a.getValue()).equals("Knockback") || !this.k.b(new Object[]{this.e, 89714527855213L}))) {
                    if (AutoElytra.u) {
                        this.j(new Object[]{47590801564788L});
                        return;
                    }

                    this.i.add(var1.a(new Object[0]));
                    var1.a_void_____(new Object[0]);
                }
            } else {
                this.j(new Object[]{47590801564788L});
            }
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        Event.b();
        this.n = false;
        if (((String)this.a.getValue()).equals("Knockback") && this.k.b(new Object[]{this.e, 89714527855213L})) {
            this.o = false;
            this.j(new Object[]{47590801564788L});
        }

        if (((String)this.a.getValue()).equals("Ranged") || ((String)this.a.getValue()).equals("Dynamic")) {
            float var10000 = (Float)this.d.getValue();
            Object[] var10003 = new Object[]{null, 109615191536188L};
            var10003[0] = var10000;
            PlayerEntity var15 = CombatUtil.b(var10003);
            if (var15 != null) {
                double var16 = (float)Math.sqrt(b.player.squaredDistanceTo(var15));
                if (var16 < this.c.getHigh() && var16 > this.c.getLow() && var16 <= this.p) {
                    if (this.j.b(new Object[]{this.f, 89714527855213L})) {
                        this.j.a_void_____________________(new Object[]{93540861878173L});
                        this.f.reroll(new Object[]{57083216809697L});
                        this.j(new Object[]{47590801564788L});
                        this.n = true;
                    }
                } else if (((String)this.a.getValue()).equals("Ranged") || this.j.b(new Object[]{this.e, 89714527855213L}) || b.player.isUsingItem()) {
                    this.j(new Object[]{47590801564788L});
                }

                this.p = (float)var16;
            } else if (((String)this.a.getValue()).equals("Ranged") || this.j.b(new Object[]{this.e, 89714527855213L}) || b.player.isUsingItem()) {
                this.j(new Object[]{47590801564788L});
            }
        }

        if (((String)this.a.getValue()).equals("Timed") && (this.j.b(new Object[]{this.e, 89714527855213L}) || b.player.isUsingItem())) {
            this.j(new Object[]{47590801564788L});
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.h.getValue()) {
            RenderHelper27 var13 = this.o ? this.k : this.j;
            float var14 = ((String)this.a.getValue()).equals("Manual") ? 1000.0F : (this.n ? this.f.getRolled(new Object[0]) : this.e.getRolled(new Object[0]));
            if (!(var13.a_float__________________(new Object[]{45633363943154L}) / var14 / 2.0F < 0.1F)) {
                MatrixCameraData var10000 = var1.b;
                RenderHelper8 var10001 = var1.a;
                float var10002 = (float)this.l.x;
                float var10003 = (float)this.l.y;
                float var10004 = (float)this.l.z;
                float var10005 = Math.min(0.5F, var13.a_float__________________(new Object[]{45633363943154L}) / var14 / 2.0F);
                Color var10008 = MathHelper4.d(new Object[]{122780339483702L}).brighter();
                float var10009 = Math.min(1.0F, var13.a_float__________________(new Object[]{45633363943154L}) / var14);
                Object[] var10012 = new Object[]{null, null, 20220661505083L};
                var10012[1] = var10009;
                var10012[0] = var10008;
                var10012 = new Object[]{
                    null, null, null, null, null, null, null, null, MathHelper4.b(var10012), MathHelper4.d(new Object[]{122780339483702L}), 84681243398314L
                };
                var10012[7] = 0.1F;
                var10012[6] = 0.005F;
                var10012[5] = var10005;
                var10012[4] = var10004;
                var10012[3] = var10003;
                var10012[2] = var10002;
                var10012[1] = var10001;
                var10012[0] = var10000;
                RenderState.f(var10012);
            }
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 45286455980699L;
        long var6 = var2 ^ 26013233570683L;
        this.j.a_void_____________________(new Object[]{var4});
        this.l = PositionUtil.b(new Object[]{var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 14193982919441L;
        this.j(new Object[]{var4});
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 41890196805669L ^ var2;
        long var4 = var2 ^ 97044371252172L;
        long var6 = var2 ^ 67643141364063L;
        long var8 = var2 ^ 68974796796592L;
        long var10 = var2 ^ 113087995867692L;
        if (b.player != null && b.world != null) {
            this.m = true;

            while (b.getNetworkHandler() != null && !this.i.isEmpty()) {
                NetworkUtil.a(new Object[]{(Packet)this.i.poll(), var6});
            }

            this.m = false;
            this.j.a_void_____________________(new Object[]{var4});
            this.e.reroll(new Object[]{var8});
            this.l = PositionUtil.b(new Object[]{var10});
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
