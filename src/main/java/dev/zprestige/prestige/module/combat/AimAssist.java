package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class AimAssist extends Module {
    private EnumSetting a;
    private EnumSetting c;
    private FloatSetting d;
    private FloatSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private BoolSetting k;
    private BoolSetting l;
    private BoolSetting m;
    private FloatSetting n;
    private FloatSetting o;
    private FloatSetting p;
    private BoolSetting q;
    private FloatSetting r;
    private BoolSetting s;
    private FloatSetting t;
    private BoolSetting u;
    private BoolSetting v;
    private Entity w;
    private RenderHelper27 x = new RenderHelper27(26293358099357L);
    private float y = 0.0F;
    private double[] z = new double[]{0.0, 0.0};
    private float A = 0.0F;
    private float B = 0.0F;

    public AimAssist() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.j.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL79_0 -> ((String)this.a.getValue()).equals("Players"), 73196168581295L});
        this.l.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL77_0 -> ((String)this.a.getValue()).equals("Players"), 73196168581295L});
        this.t
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL73_0 -> (Boolean)this.s.getValue() && ((String)this.a.getValue()).equals("Players"), 35046756052036L}
            );
        this.k.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL78_0 -> ((String)this.a.getValue()).equals("Crystals"), 73196168581295L});
        this.u.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL72_0 -> ((String)this.a.getValue()).equals("Crystals"), 73196168581295L});
        this.s.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL74_0 -> ((String)this.a.getValue()).equals("Players"), 73196168581295L});
        this.m.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL76_0 -> ((String)this.a.getValue()).equals("Crystals"), 73196168581295L});
        this.r.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL75_0 -> (Boolean)this.q.getValue(), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword);
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (!AutoObbyCrystal.R) {
                if (!(Boolean)this.i.getValue() || b.crosshairTarget == null || b.crosshairTarget.getType() != Type.BLOCK || !b.options.attackKey.isPressed()) {
                    if (((String)this.a.getValue()).equals("Players")) {
                        if ((Boolean)this.j.getValue()
                            && !InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})
                            && !(InventoryUtil.a_class_1792(new Object[]{36931329843449L}) instanceof AxeItem)) {
                            return;
                        }

                        if ((Boolean)this.l.getValue() && !b.options.attackKey.isPressed()) {
                            return;
                        }
                    }

                    if (((String)this.a.getValue()).equals("Crystals")) {
                        if ((Boolean)this.k.getValue() && !InventoryUtil.l(new Object[]{Items.END_CRYSTAL, 65539821781598L})) {
                            return;
                        }

                        if ((Boolean)this.m.getValue() && !b.options.useKey.isPressed()) {
                            return;
                        }
                    }

                    Entity var27 = this.w;
                    if (this.w != null) {
                        if (!this.w.isAlive()) {
                            this.w = null;
                        } else {
                            Vec3d var28 = this.w.getEyePos();
                            if (Math.sqrt(b.player.squaredDistanceTo(var28.x, var28.y, var28.z)) > ((Float)this.e.getValue()).floatValue()) {
                                this.w = null;
                            } else {
                                if ((Float)this.d.getValue() < 360.0F) {
                                    RenderBatch var29 = WorldUtil.a_RenderBatch_(new Object[]{var28, 6563042098863L});
                                    RenderBatch var10000 = new RenderBatch(b.player.getYaw(), b.player.getPitch());
                                    float var10002 = (Float)this.d.getValue();
                                    Object[] var10005 = new Object[]{null, null, null, 5238195483605L};
                                    var10005[2] = var10002;
                                    var10005[1] = var29;
                                    var10005[0] = var10000;
                                    if (!WorldUtil.f(var10005)) {
                                        this.w = null;
                                    }
                                }

                                if (this.w != null && !(Boolean)this.g.getValue() && !b.player.canSee(this.w)) {
                                    this.w = null;
                                }
                            }
                        }
                    }

                    if (!(Boolean)this.f.getValue() || this.w == null) {
                        this.w = this.a(new Object[]{116016010037847L});
                    }

                    if (this.w == null || !this.w.equals(var27)) {
                        this.x.a_void_____________________(new Object[]{93540861878173L});
                        float var10001 = (Float)this.n.getValue();
                        Object[] var10006 = new Object[]{null, null, 29524726012237L};
                        var10006[1] = 1.25F;
                        var10006[0] = 0.75F;
                        this.y = var10001 * RenderUtil5.a(var10006);
                        this.A = 0.0F;
                        this.B = 0.0F;
                    }

                    if ((Boolean)this.h.getValue()) {
                        double var44 = this.z[0];
                        double var30 = this.z[1];
                        this.z[0] = b.mouse.getX();
                        this.z[1] = b.mouse.getY();
                        if (var44 == this.z[0] && var30 == this.z[1]) {
                            return;
                        }
                    }

                    RenderHelper27 var52 = this.x;
                    float var55 = this.y;
                    Object[] var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = var55;
                    if (var52.a(var10004)) {
                        if (!(Boolean)this.v.getValue()
                            || this.w == null
                            || b.crosshairTarget == null
                            || b.crosshairTarget.getType() != Type.ENTITY
                            || !((EntityHitResult)b.crosshairTarget).getEntity().equals(this.w)) {
                            if (this.w != null) {
                                Vec3d var45 = this.w.getEyePos();
                                if (this.w instanceof EndCrystalEntity) {
                                    var45 = PositionUtil.a(new Object[]{this.w, 67736483458412L});
                                }

                                Vec3d var46 = new Vec3d(this.w.getX() - this.w.lastX, this.w.getY() - this.w.lastY, this.w.getZ() - this.w.lastZ)
                                    .multiply(((Float)this.t.getValue()).floatValue());
                                if ((Boolean)this.s.getValue() && ((String)this.a.getValue()).equals("Players")) {
                                    var45 = var45.add(var46);
                                }

                                RenderBatch var47 = WorldUtil.a_RenderBatch_(new Object[]{var45, 6563042098863L});
                                float var31 = var47.a(new Object[0]) - b.player.getYaw();
                                float var32 = var47.b(new Object[0]) - b.player.getPitch();

                                while (var31 > 180.0F) {
                                    var31 -= 360.0F;
                                }

                                while (var31 < -180.0F) {
                                    var31 += 360.0F;
                                }

                                if (!((Float)this.d.getValue() < 360.0F)
                                    || !(Math.abs(var31) > (Float)this.d.getValue() / 2.0F) && !(Math.abs(var32) > (Float)this.d.getValue() / 2.0F)) {
                                    float var33 = Math.max(b.getCurrentFps(), 1);
                                    float var34 = (Float)this.o.getValue() * 2.0F / var33;
                                    float var58 = (Float)this.p.getValue() / 100.0F;
                                    Object[] var61 = new Object[]{null, null, 29524726012237L};
                                    var61[1] = var58;
                                    var61[0] = 0.0F;
                                    float var35 = 1.0F - RenderUtil5.a(var61) * 0.3F;
                                    var34 *= var35;
                                    float var36 = Math.min((Float)this.o.getValue() / 100.0F * 0.15F, 1.0F);
                                    if ((Boolean)this.q.getValue()) {
                                        float var37 = (Float)this.r.getValue() / 100.0F;
                                        float var38 = Math.max(0.02F, 1.0F - var37);
                                        var36 = Math.max(0.005F, var38 * 0.18F);
                                        var34 *= var38;
                                    }

                                    this.A = this.A + (var31 - this.A) * var36;
                                    this.B = this.B + (var32 - this.B) * var36;
                                    boolean var49 = ((String)this.a.getValue()).equals("Crystals") && (Boolean)this.u.getValue();
                                    if (!var49) {
                                        float var50 = Math.min(Math.abs(this.A), var34) * Math.signum(this.A);
                                        if (Math.abs(var31) <= var34) {
                                            b.player.setYaw(var47.a(new Object[0]));
                                        } else {
                                            b.player.setYaw(b.player.getYaw() + var50);
                                        }
                                    }

                                    Box var51 = this.w.getBoundingBox();
                                    float var53 = (float)(var51.minX + (var51.maxX - var51.minX) / 2.0);
                                    var55 = (float)(var51.minY + var46.getY());
                                    var58 = (float)(var51.minZ + (var51.maxZ - var51.minZ) / 2.0);
                                    var61 = new Object[]{null, null, null, 5229932684601L};
                                    var61[2] = var58;
                                    var61[1] = var55;
                                    var61[0] = var53;
                                    RenderBatch var39 = WorldUtil.b_RenderBatch_____________(var61);
                                    float var54 = (float)(var51.minX + (var51.maxX - var51.minX) / 2.0);
                                    var55 = (float)(var51.maxY + var46.getY());
                                    var58 = (float)(var51.minZ + (var51.maxZ - var51.minZ) / 2.0);
                                    var61 = new Object[]{null, null, null, 5229932684601L};
                                    var61[2] = var58;
                                    var61[1] = var55;
                                    var61[0] = var54;
                                    RenderBatch var40 = WorldUtil.b_RenderBatch_____________(var61);
                                    float var41 = var39.b(new Object[0]);
                                    float var42 = var40.b(new Object[0]);
                                    if (b.player.getPitch() > var41 || b.player.getPitch() < var42) {
                                        float var43 = Math.min(Math.abs(this.B), var34) * Math.signum(this.B);
                                        if (Math.abs(var32) <= var34) {
                                            b.player.setPitch(var47.b(new Object[0]));
                                        } else {
                                            b.player.setPitch(b.player.getPitch() + var43);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private Entity a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 60361130766012L ^ var2;
        long var4 = var2 ^ 81331214648673L;
        long var6 = var2 ^ 121017117675732L;
        long var8 = var2 ^ 132678000133337L;
        long var10 = var2 ^ 87815776288296L;
        long var12 = var2 ^ 100752988510014L;
        long var14 = var2 ^ 100744071430610L;
        String var17 = (String)this.c.getValue();
        Entity var18 = null;
        double var19 = Double.MAX_VALUE;
        Event.b();
        RenderBatch var21 = new RenderBatch(b.player.getYaw(), b.player.getPitch());

        for (Entity var23 : b.world.getEntities()) {
            if (var23.isAlive()) {
                boolean var24 = CombatUtil.a(new Object[]{var23, var10});
                if ((var24 || var23 instanceof EndCrystalEntity)
                    && (!var24 || ((String)this.a.getValue()).equals("Players"))
                    && (!(var23 instanceof EndCrystalEntity) || ((String)this.a.getValue()).equals("Crystals"))
                    && Prestige.i.a_boolean_(new Object[]{var23, var6})
                    && (!(var23 instanceof PlayerEntity) || !Prestige.g.a(new Object[]{var23.getName().getString(), var4}))) {
                    Vec3d var25 = var23.getEyePos();
                    double var26 = b.player.squaredDistanceTo(var25.x, var25.y, var25.z);
                    if (!(Math.sqrt(var26) > ((Float)this.e.getValue()).floatValue())) {
                        float var10000 = (float)var25.x;
                        float var10001 = (float)var25.y;
                        float var10002 = (float)var25.z;
                        Object[] var10005 = new Object[]{null, null, null, var14};
                        var10005[2] = var10002;
                        var10005[1] = var10001;
                        var10005[0] = var10000;
                        RenderBatch var28 = WorldUtil.b_RenderBatch_____________(var10005);
                        if ((Float)this.d.getValue() < 360.0F) {
                            var10002 = (Float)this.d.getValue();
                            var10005 = new Object[]{null, null, null, var12};
                            var10005[2] = var10002;
                            var10005[1] = var28;
                            var10005[0] = var21;
                            if (!WorldUtil.f(var10005)) {
                                continue;
                            }
                        }

                        if ((Boolean)this.g.getValue() || b.player.canSee(var23)) {
                            Object[] var10007 = new Object[]{null, null, var28, var17, var8};
                            var10007[1] = var26;
                            var10007[0] = var23;
                            double var29 = this.a_double(var10007);
                            if (var29 < var19) {
                                var18 = var23;
                                var19 = var29;
                            }
                        }
                    }
                }
            }
        }

        return var18;
    }

    private double a_double(Object[] var1) {
        Entity var3 = (Entity)var1[0];
        double var4 = (Double)var1[1];
        RenderBatch var2 = (RenderBatch)var1[2];
        String var6 = (String)var1[3];
        long var7 = (Long)var1[4];
        var7 = 60361130766012L ^ var7;
        long var9 = var7 ^ 106486827641636L;
        Event.b();
        switch (var6) {
            case "Crosshair":
                return WorldUtil.a(new Object[]{var2, var9});
            case "Lowest HP":
                if (var3 instanceof PlayerEntity var16) {
                    return (var16.getHealth() + var16.getAbsorptionAmount()) * 1000.0 + var4;
                }

                return var4;
            case "Lowest Armor":
                if (var3 instanceof PlayerEntity var14) {
                    return var14.getArmor() * 1000.0 + var4;
                }

                return var4;
            case "Closest":
            default:
                return var4;
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
