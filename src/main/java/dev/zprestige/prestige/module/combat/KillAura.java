package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent12;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.network.PacketManager;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class KillAura extends Module implements RenderContract3 {
    public static KillAura a;
    private FloatSetting c;
    private BoolSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private EnumSetting h;
    private RangeSetting i;
    private FloatSetting j;
    private FloatSetting k;
    private FloatSetting l;
    private EnumSetting m;
    private MultiSetting n;
    private BoolSetting o;
    private FloatSetting p;
    private ColorSetting q;
    private RenderHelper27 r = new RenderHelper27(26293358099357L);
    private RenderHelper27 s = new RenderHelper27(26293358099357L);
    private Entity t;
    private static final String[] u = new String[]{"Head", "Neck", "Chest", "Waist", "Thighs", "Legs"};
    private static final double[] v = new double[]{0.95, 0.85, 0.7, 0.55, 0.35, 0.1};

    public KillAura() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        a = this;
        this.i.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL161_0 -> ((String)this.h.getValue()).equals("Range"), 37043306203287L});
        this.q.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL159_0 -> (Boolean)this.o.getValue(), 119980880492450L});
        this.p.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL160_0 -> (Boolean)this.o.getValue(), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 67472954705639L;
        long var6 = var2 ^ 36160164621105L;
        long var8 = var2 ^ 65603549542278L;
        long var10 = var2 ^ 29247161167908L;
        long var12 = var2 ^ 82777235594348L;
        long var14 = var2 ^ 72189596612731L;
        long var16 = var2 ^ 81856115773718L;
        long var18 = var2 ^ 72467876329892L;
        long var20 = var2 ^ 136278178789514L;
        long var22 = var2 ^ 114164831697321L;
        long var24 = var2 ^ 7477342050734L;
        long var26 = var2 ^ 91260814807711L;
        long var28 = var2 ^ 87165761547780L;
        long var30 = var2 ^ 139097133863433L;
        long var32 = var2 ^ 93805536655092L;
        long var34 = var2 ^ 127186431202576L;
        long var36 = var2 ^ 85547089512629L;
        long var38 = var2 ^ 101816702374277L;
        if (b.player.isUsingItem()) {
            return null;
        } else if (AutoObbyCrystal.R) {
            return null;
        } else if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else if (!InventoryUtil.a(new Object[]{b.player.getMainHandStack(), var8})
            && !(b.player.getMainHandStack().getItem() instanceof AxeItem)
            && (Boolean)this.g.getValue()) {
            return null;
        } else {
            String var42 = (String)this.h.getValue();

            float var41 = switch (var42) {
                case "Vanilla" -> 1.0F;
                case "Range" -> this.i.randomInRange(new Object[]{var28}) / 100.0F;
                default -> this.i.getLow() / 100.0F;
            };
            RenderHelper27 var10000 = this.r;
            Object[] var10004 = new Object[]{null, var36};
            var10004[0] = 100.0F;
            boolean var48 = !var10000.a(var10004) || b.player.getAttackCooldownProgress(0.0F) < var41 || !b.player.isOnGround() && b.player.fallDistance <= 0.0;
            if (this.t != null) {
                if (!this.t.isAlive()) {
                    this.t = null;
                    return null;
                }

                Vec3d var49 = this.t.getEyePos();
                if (Math.sqrt(b.player.squaredDistanceTo(var49.x, var49.y, var49.z)) > 5.0) {
                    this.t = null;
                }

                RenderBatch var44 = WorldUtil.a_RenderBatch_(new Object[]{var49, var16});
                RenderBatch var53 = new RenderBatch(b.player.getYaw(), b.player.getPitch());
                float var10002 = (Float)this.k.getValue() * 2.0F;
                Object[] var10005 = new Object[]{null, null, null, var12};
                var10005[2] = var10002;
                var10005[1] = var44;
                var10005[0] = var53;
                if (!WorldUtil.f(var10005)) {
                    this.t = null;
                }
            }

            if (!(Boolean)this.d.getValue() || this.t == null) {
                boolean var55 = (Boolean)this.f.getValue();
                float var10003 = (Float)this.l.getValue();
                Object[] var10006 = new Object[]{null, null, var4};
                var10006[1] = var10003;
                var10006[0] = var55;
                this.t = this.a_class_1297_(var10006);
            }

            if (this.t == null) {
                return null;
            } else {
                Vec3d var50 = this.a_class_243__________(new Object[]{this.t, var14});
                RenderBatch var51 = Prestige.o.a_RenderBatch(new Object[]{var50, var38});
                if ((Boolean)this.e.getValue() && !var51.a_boolean________________________________________(new Object[0])) {
                    var51 = WorldUtil.d_RenderBatch(new Object[]{var51, var50, var24});
                }

                if (var51.a(new Object[0]) - b.player.getYaw() > (Float)this.k.getValue() * 2.0F
                    || var51.a(new Object[0]) - b.player.getYaw() < -(Float)this.k.getValue() * 2.0F) {
                    return null;
                } else if (!b.player.canSee(this.t)) {
                    return null;
                } else if (WorldUtil2.a(new Object[]{this.t, var22})) {
                    return null;
                } else if (this.a_double_______(new Object[]{this.t, var20}) > ((Float)this.c.getValue()).floatValue()) {
                    return null;
                } else if (var48) {
                    return null;
                } else if (var51.a_boolean________________________________________(new Object[0])) {
                    return var51;
                } else {
                    if ((Float)this.j.getValue() > 0.0F) {
                        var10004 = new Object[]{null, null, var32};
                        var10004[1] = 100.0F;
                        var10004[0] = 0.0F;
                        if (RenderUtil5.a(var10004) < (Float)this.j.getValue()) {
                            this.r.a_void_____________________(new Object[]{var10});
                            return null;
                        }
                    }

                    new TickEvent12().b_boolean__(new Object[]{var6});
                    if (this.t instanceof PlayerEntity var45 && CombatUtil.b_boolean(new Object[]{var45, var30})) {
                        String var52 = (String)this.m.getValue();
                        if (var52.equals("Skip")) {
                            return null;
                        }

                        if (var52.equals("Prefer Axe") && !(b.player.getMainHandStack().getItem() instanceof AxeItem)) {
                            int var47 = this.d(new Object[]{var18});
                            if (var47 != -1) {
                                PacketManager var54 = Prestige.t;
                                var10004 = new Object[]{null, var34};
                                var10004[0] = var47;
                                var54.a(var10004);
                            }
                        }
                    }

                    InventoryUtil2.a_void_________(new Object[]{this.t, var26});
                    this.r.a_void_____________________(new Object[]{var10});
                    return var51;
                }
            }
        }
    }

    private int d(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() instanceof AxeItem) {
                return var5;
            }
        }

        return -1;
    }

    private Entity a_class_1297_(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        float var5 = (Float)var1[1];
        long var3 = (Long)var1[2];
        var3 = 61843846160931L ^ var3;
        long var6 = var3 ^ 101644536818423L;
        long var8 = var3 ^ 135901068246850L;
        long var10 = var3 ^ 99332469330366L;
        long var12 = var3 ^ 85950663047336L;
        long var14 = var3 ^ 85941729193540L;
        Entity var17 = null;
        Event.b();
        double var18 = Double.MAX_VALUE;
        RenderBatch var20 = new RenderBatch(b.player.getYaw(), b.player.getPitch());

        for (Entity var22 : b.world.getEntities()) {
            if (var22 != b.player
                && var22.isAlive()
                && (!var2 || CombatUtil.a(new Object[]{var22, var10}))
                && Prestige.i.a_boolean_(new Object[]{var22, var8})
                && (!(var22 instanceof PlayerEntity) || !Prestige.g.a(new Object[]{var22.getName().getString(), var6}))) {
                Vec3d var23 = var22.getEyePos();
                double var24 = b.player.squaredDistanceTo(var23.x, var23.y, var23.z);
                if (!(Math.sqrt(var24) > 5.0)) {
                    if (var5 < 360.0F) {
                        float var10000 = (float)var23.x;
                        float var10001 = (float)var23.y;
                        float var10002 = (float)var23.z;
                        Object[] var10005 = new Object[]{null, null, null, var14};
                        var10005[2] = var10002;
                        var10005[1] = var10001;
                        var10005[0] = var10000;
                        RenderBatch var26 = WorldUtil.b_RenderBatch_____________(var10005);
                        var10005 = new Object[]{null, null, null, var12};
                        var10005[2] = var5;
                        var10005[1] = var26;
                        var10005[0] = var20;
                        if (!WorldUtil.f(var10005)) {
                            continue;
                        }
                    }

                    if (var24 < var18) {
                        var17 = var22;
                        var18 = var24;
                    }
                }
            }
        }

        return var17;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
    }

    private double a_double_______(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        Vec3d var5 = b.player.getEyePos();
        Box var6 = var4.getBoundingBox();
        double var7 = MathHelper.clamp(var5.x, var6.minX, var6.maxX);
        double var9 = MathHelper.clamp(var5.y, var6.minY, var6.maxY);
        double var11 = MathHelper.clamp(var5.z, var6.minZ, var6.maxZ);
        double var13 = var5.x - var7;
        double var15 = var5.y - var9;
        double var17 = var5.z - var11;
        return Math.sqrt(var13 * var13 + var15 * var15 + var17 * var17);
    }

    private Vec3d a_class_243__________(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 61843846160931L ^ var2;
        long var5 = var2 ^ 32535654448395L;
        Event.b();
        Vec3d var8 = b.player.getEyePos();
        Box var9 = var4.getBoundingBox();
        double var10 = var9.maxY - var9.minY;
        if (var10 <= 0.0) {
            return var4.getEyePos();
        } else {
            double var12 = MathHelper.clamp(var8.x, var9.minX, var9.maxX);
            double var14 = MathHelper.clamp(var8.z, var9.minZ, var9.maxZ);
            double var16 = (var8.x - var12) * (var8.x - var12) + (var8.z - var14) * (var8.z - var14);
            double var18 = Double.POSITIVE_INFINITY;
            Vec3d var20 = null;

            for (int var21 = 0; var21 < u.length; var21++) {
                if (this.n.isSelected(new Object[]{u[var21], var5})) {
                    double var22 = var9.minY + v[var21] * var10;
                    double var24 = var8.y - var22;
                    double var26 = var16 + var24 * var24;
                    if (var26 < var18) {
                        var18 = var26;
                        var20 = new Vec3d(var12, var22, var14);
                    }
                }
            }

            return var20 != null ? var20 : var4.getEyePos();
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerActionC2SPacket var7 && var7.getAction() == Action.RELEASE_USE_ITEM) {
            this.s.a_void_____________________(new Object[]{93540861878173L});
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.o.getValue()) {
            Entity var13 = this.t;
            if (var13 != null && var13.isAlive()) {
                Vec3d var14 = PositionUtil.c(new Object[]{var13, 91425777529078L});
                float var15 = MatrixStack.a_float_______________(new Object[]{601921721859L});
                double var16 = var13.getEyeY() - var13.getY();
                double var18 = var14.x + (var13.getX() - var14.x) * var15;
                double var20 = var14.y + (var13.getY() - var14.y) * var15 + var16;
                double var22 = var14.z + (var13.getZ() - var14.z) * var15;
                float var24 = (Float)this.p.getValue();
                float var25 = (float)(var18 - var24);
                float var26 = (float)(var20 - var24);
                float var27 = (float)(var22 - var24);
                float var28 = (float)(var18 + var24);
                float var29 = (float)(var20 + var24);
                float var30 = (float)(var22 + var24);
                Color var31 = (Color)this.q.getValue();
                Color var32 = new Color(var31.getRed(), var31.getGreen(), var31.getBlue(), Math.min(255, var31.getAlpha() + 55));
                MatrixCameraData var10000 = var1.b;
                RenderHelper8 var10001 = var1.a;
                Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var31, 108390846498585L};
                var10011[7] = var30;
                var10011[6] = var29;
                var10011[5] = var28;
                var10011[4] = var27;
                var10011[3] = var26;
                var10011[2] = var25;
                var10011[1] = var10001;
                var10011[0] = var10000;
                RenderState.h(var10011);
                var10000 = var1.b;
                var10001 = var1.a;
                var10011 = new Object[]{null, null, null, null, null, null, null, null, var32, 119015971454350L};
                var10011[7] = var30;
                var10011[6] = var29;
                var10011[5] = var28;
                var10011[4] = var27;
                var10011[3] = var26;
                var10011[2] = var25;
                var10011[1] = var10001;
                var10011[0] = var10000;
                RenderState.g(var10011);
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
