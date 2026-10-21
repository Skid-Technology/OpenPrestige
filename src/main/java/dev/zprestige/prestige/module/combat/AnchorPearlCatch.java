package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.VecBoolData;
import dev.zprestige.prestige.data.VecData;
import dev.zprestige.prestige.data.VecIntData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent10;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RenderUtil6;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import dev.zprestige.prestige.type.AnchorState;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.RaycastUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import org.joml.Matrix4f;

public class AnchorPearlCatch extends Module implements RenderContract3 {
    private FloatSetting a;
    private FloatSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private EnumSetting h;
    private BoolSetting i;
    private FloatSetting j;
    private BoolSetting k;
    private RangeSetting l;
    private RangeSetting m;
    private RangeSetting n;
    private BoolSetting o;
    private ColorSetting p;
    private ColorSetting q;
    private ColorSetting r;
    private static final int[][] s = new int[][]{{-1, 0, 0}, {1, 0, 0}, {0, 0, -1}, {0, 0, 1}, {-1, 0, -1}, {-1, 0, 1}, {1, 0, -1}, {1, 0, 1}};
    private static final Direction[] t = Direction.values();
    private static final float u = 6.0F;
    private static final float v = 200.0F;
    private static final int w = 3;
    private static final int x = 100;
    private static final int y = 160;
    private static final int z = 30;
    private static final double A = 2.5;
    private static final int B = 120;
    public static boolean C;
    private AnchorState D = AnchorState.IDLE;
    private RenderHelper27 E = new RenderHelper27(26293358099357L);
    private RenderHelper27 F = new RenderHelper27(26293358099357L);
    private RenderHelper27 G = new RenderHelper27(26293358099357L);
    private RenderHelper27 H = new RenderHelper27(26293358099357L);
    private RenderHelper27 I = new RenderHelper27(26293358099357L);
    private HashSet J = new HashSet();
    private UUID K;
    private UUID L;
    private Vec3d M;
    private int N = -1;
    private BlockPos O;
    private BlockPos P;
    private Vec3d Q;
    private BlockPos R;
    private BlockPos S;
    private Vec3d T;
    private int U = -1;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;

    public AnchorPearlCatch() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.p.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL48_0 -> (Boolean)this.o.getValue(), 119980880492450L});
        this.r.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL46_0 -> (Boolean)this.o.getValue(), 119980880492450L});
        this.h.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL50_0 -> (Boolean)this.g.getValue(), 90319707064974L});
        this.j.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL49_0 -> (Boolean)this.i.getValue(), 35046756052036L});
        this.q
            .setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL47_0 -> (Boolean)this.o.getValue() && (Boolean)this.g.getValue(), 119980880492450L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        this.z(new Object[0]);
        this.J.clear();
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 51011343502304L;
        long var6 = var2 ^ 82893747353842L;
        long var8 = var2 ^ 53560089228642L;
        long var10 = var2 ^ 140372736859409L;
        if (this.U != -1 && (Boolean)this.k.getValue() && Prestige.o.d_boolean______(new Object[0])) {
            Renderer2D var10000 = Prestige.o;
            int var10001 = this.U;
            Object[] var10004 = new Object[]{null, var6};
            var10004[0] = var10001;
            var10000.i(var10004);
            RenderUtil6.a_void____________________________(new Object[]{this, var4});
            this.U = -1;
        }

        this.A(new Object[]{var8});
        this.z(new Object[0]);
        Prestige.o.f(new Object[]{this, var10});
        C = false;
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.z(new Object[0]);
        this.J.clear();
        C = false;
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (this.d(new Object[]{44483758100801L})) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent10 var1) {
        if (this.d(new Object[]{44483758100801L})) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.d(new Object[]{44483758100801L})) {
            var1.a_void_____(new Object[0]);
        }
    }

    private boolean d(Object[] var1) {
        return this.D == AnchorState.PLACE_ANCHOR || this.D == AnchorState.SAFE_COVER || this.D == AnchorState.CHARGE_ANCHOR;
    }

    private VecIntData a_VecIntData(Object[] var1) {
        EnderPearlEntity var4 = (EnderPearlEntity)var1[0];
        double var6 = var4.getX();
        double var8 = var4.getY();
        double var10 = var4.getZ();
        Event.b();
        Vec3d var12 = var4.getVelocity();
        double var13 = var12.x;
        double var15 = var12.y;
        double var17 = var12.z;

        for (int var19 = 1; var19 <= 200; var19++) {
            Vec3d var20 = new Vec3d(var6, var8, var10);
            var6 += var13;
            var8 += var15;
            var10 += var17;
            var13 *= 0.99;
            var17 *= 0.99;
            var15 = var15 * 0.99 - 0.03;
            BlockHitResult var21 = b.world.raycast(new RaycastContext(var20, new Vec3d(var6, var8, var10), ShapeType.OUTLINE, FluidHandling.NONE, var4));
            if (var21 != null && var21.getType() == Type.BLOCK) {
                return new VecIntData(var21.getPos(), var19);
            }

            if (var8 < -65.0) {
                break;
            }
        }

        return null;
    }

    private EnderPearlEntity a_class_1684(Object[] var1) {
        if (this.K != null && b.world != null) {
            for (Entity var6 : b.world.getEntities()) {
                if (var6 instanceof EnderPearlEntity var7 && var6.getUuid().equals(this.K)) {
                    return var7;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private PlayerEntity a_class_1657_(Object[] var1) {
        if (this.L != null && b.world != null) {
            for (Entity var6 : b.world.getEntities()) {
                if (var6 instanceof PlayerEntity var7 && var6.getUuid().equals(this.L)) {
                    return var7;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private double a_double_(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        Event.b();
        double var7 = 10.0;
        double var9 = Math.sqrt(var4.squaredDistanceTo(var5)) / var7;
        if (var9 > 1.0) {
            return 0.0;
        } else {
            int var11 = 0;

            for (double var15 : new double[]{0.2, 1.0, 1.7}) {
                Vec3d var17 = var4.add(0.0, var15, 0.0);
                BlockHitResult var18 = b.world.raycast(new RaycastContext(var5, var17, ShapeType.COLLIDER, FluidHandling.NONE, b.player));
                if (var18 == null || var18.getType() == Type.MISS) {
                    var11++;
                }
            }

            double var19 = (1.0 - var9) * (var11 / 3.0);
            return (var19 * var19 + var19) / 2.0 * 7.0 * var7 + 1.0;
        }
    }

    private VecBoolData a_VecBoolData(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        long var2 = (Long)var1[1];
        var2 = 55495940703451L ^ var2;
        long var5 = var2 ^ 103392261267729L;
        long var7 = var2 ^ 81689807741877L;
        long var9 = var2 ^ 70347993463805L;
        long var11 = var2 ^ 50860481262513L;
        long var13 = var2 ^ 94916777813686L;
        long var15 = var2 ^ 87963028854718L;
        long var17 = var2 ^ 115638071237212L;
        long var19 = var2 ^ 93240739490053L;
        BlockPos var22 = BlockPos.ofFloored(var4);
        Vec3d var23 = b.player.getEyePos();
        Event.b();
        float var24 = b.player.getHealth() + b.player.getAbsorptionAmount();
        Block var25 = ((String)this.h.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE : Blocks.OBSIDIAN;
        boolean var26 = InventoryUtil.a_Integer(new Object[]{var25.asItem(), var9}) != null;
        short var10000 = 26708;
        BlockPos var27 = var22;
        long var10001 = 7715639541459957327L ^ var2;
        BlockPos var28 = var22.up();
        VecBoolData var29 = null;
        double var30 = -Double.MAX_VALUE;

        for (int var32 = -3; var32 <= 3; var32++) {
            for (int var33 = -1; var33 <= 2; var33++) {
                for (int var34 = -3; var34 <= 3; var34++) {
                    BlockPos var35 = var22.add(var32, var33, var34);
                    if (!var35.equals(var27) && !var35.equals(var28)) {
                        Vec3d var36 = var35.toCenterPos();
                        if (!(var23.squaredDistanceTo(var36) > 19.360000000000003)) {
                            boolean var37 = WorldUtil2.b(new Object[]{var35, Blocks.RESPAWN_ANCHOR, var13});
                            BlockPos var38 = null;
                            Vec3d var39 = null;
                            if (var37) {
                                var35 = var35.toImmutable();
                            } else {
                                if (WorldUtil2.e(new Object[]{var35, var5}) || WorldUtil2.g(new Object[]{var35, var7})) {
                                    continue;
                                }

                                VecData var40 = this.a_VecData(new Object[]{var35, var23, var11});
                                if (var40 == null) {
                                    continue;
                                }

                                var38 = var40.j();
                                var39 = var40.k();
                            }

                            double var60 = this.a_double_(new Object[]{var4, var36, var15});
                            if (!(var60 < ((Float)this.c.getValue()).floatValue())) {
                                double var42 = Prestige.f.k(new Object[]{b.player, var36, var17});
                                if (!(Boolean)this.e.getValue() || !(var42 >= var24 * 2.0F)) {
                                    BlockPos var44 = null;
                                    BlockPos var45 = null;
                                    Vec3d var46 = null;
                                    double var47 = var42;
                                    if ((Boolean)this.g.getValue() && var26 && var42 > 6.0) {
                                        double var49 = var42;

                                        for (int[] var54 : s) {
                                            BlockPos var55 = var35.add(var54[0], var54[1], var54[2]);
                                            if (!var55.equals(var27)
                                                && !var55.equals(var28)
                                                && !WorldUtil2.e(new Object[]{var55, var5})
                                                && !WorldUtil2.g(new Object[]{var55, var7})) {
                                                VecData var56 = this.a_VecData(new Object[]{var55, var23, var11});
                                                if (var56 != null) {
                                                    double var57 = Prestige.f.l(new Object[]{b.player, var36, var55, var25.getDefaultState(), var19});
                                                    if (!(var57 >= var49) && !(var42 - var57 < 2.0)) {
                                                        var49 = var57;
                                                        var44 = var55;
                                                        var45 = var56.j();
                                                        var46 = var56.k();
                                                    }
                                                }
                                            }
                                        }

                                        if (var44 != null) {
                                            var47 = var49;
                                        }
                                    }

                                    if (!(var47 > ((Float)this.d.getValue()).floatValue()) && (!(Boolean)this.e.getValue() || !(var47 >= var24 - 1.0F))) {
                                        double var61 = var60 - 0.5 * var4.distanceTo(var36) + (var37 ? 3.0 : 0.0);
                                        if (var61 > var30) {
                                            var30 = var61;
                                            var29 = new VecBoolData(var35, var38, var39, var44, var45, var46, var37);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return var29;
    }

    private VecData a_VecData(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        Event.b();
        BlockPos var7 = null;
        Vec3d var8 = null;
        double var9 = Double.MAX_VALUE;

        for (Direction var14 : t) {
            BlockPos var15 = var4.offset(var14);
            if (!b.world.getBlockState(var15).isReplaceable()) {
                Direction var16 = var14.getOpposite();
                Vec3d var17 = var15.toCenterPos().add(var16.getOffsetX() * 0.5, var16.getOffsetY() * 0.5, var16.getOffsetZ() * 0.5);
                double var18 = var5.squaredDistanceTo(var17);
                if (!(var18 > 18.0625) && !(var18 >= var9)) {
                    var9 = var18;
                    var7 = var15;
                    var8 = var17;
                }
            }
        }

        return var7 == null ? null : new VecData(var7, var8);
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 11300586223115L;
        long var6 = var2 ^ 91475489002186L;
        long var8 = var2 ^ 128481067272063L;
        long var10 = var2 ^ 18386640137437L;
        long var12 = var2 ^ 139806472782422L;
        long var14 = var2 ^ 126053932203376L;
        long var16 = var2 ^ 130023359200673L;
        long var18 = var2 ^ 9081094564810L;
        if (InventoryUtil.a_Integer(new Object[]{Items.RESPAWN_ANCHOR, var12}) != null) {
            if (InventoryUtil.a_Integer(new Object[]{Items.GLOWSTONE, var12}) != null) {
                if (this.e_int(new Object[]{var18}) != -1) {
                    double var21 = ((Float)this.a.getValue()).floatValue();

                    for (Entity var24 : b.world.getEntities()) {
                        if (var24 instanceof EnderPearlEntity var25 && !this.J.contains(var25.getUuid())) {
                            Vec3d var26 = new Vec3d(var25.getX(), var25.getY(), var25.getZ());
                            if (!(b.player.getEyePos().squaredDistanceTo(var26) > var21 * var21)
                                && var25.getOwner() instanceof PlayerEntity var27
                                && !var27.equals(b.player)
                                && Prestige.i.a_boolean_(new Object[]{var27, var8})
                                && !Prestige.g.a(new Object[]{var27.getName().getString(), var6})) {
                                VecIntData var31 = this.a_VecIntData(new Object[]{var25, var4});
                                if (var31 != null && var31.b() <= 100) {
                                    VecBoolData var29 = this.a_VecBoolData(new Object[]{var31.a(), var14});
                                    if (var29 != null) {
                                        this.K = var25.getUuid();
                                        this.L = var27.getUuid();
                                        this.M = var31.a();
                                        this.N = var31.b();
                                        this.O = var29.c();
                                        this.P = var29.d();
                                        this.Q = var29.e();
                                        this.R = var29.f();
                                        this.S = var29.g();
                                        this.T = var29.h();
                                        this.X = 0;
                                        this.W = 0;
                                        this.Y = 0;
                                        this.Z = 0;
                                        this.V = 0;
                                        this.H.a_void_____________________(new Object[]{var10});
                                        this.E.a_void_____________________(new Object[]{var10});
                                        this.l.reroll(new Object[]{var16});
                                        this.F.a_void_____________________(new Object[]{var10});
                                        this.m.reroll(new Object[]{var16});
                                        this.G.a_void_____________________(new Object[]{var10});
                                        this.n.reroll(new Object[]{var16});
                                        this.D = AnchorState.WAIT;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 22391772937175L;
        long var6 = var2 ^ 80417194319668L;
        long var8 = var2 ^ 117373783149219L;
        long var10 = var2 ^ 84933598205266L;
        long var12 = var2 ^ 112333111235568L;
        long var14 = var2 ^ 135458199582131L;
        long var16 = var2 ^ 6507056684030L;
        long var18 = var2 ^ 57563686535375L;
        Event.b();
        PlayerEntity var21 = this.a_class_1657_(new Object[]{var16});
        if (var21 != null && !Prestige.i.a_boolean_(new Object[]{var21, var8})) {
            Object[] var28 = new Object[]{null, null, var10};
            var28[1] = true;
            var28[0] = null;
            this.x(var28);
        } else if (++this.X > 120) {
            Object[] var27 = new Object[]{null, null, var10};
            var27[1] = true;
            var27[0] = null;
            this.x(var27);
        } else {
            EnderPearlEntity var22 = this.a_class_1684(new Object[]{var18});
            if (var22 != null) {
                VecIntData var23 = this.a_VecIntData(new Object[]{var22, var4});
                if (var23 == null) {
                    Object[] var26 = new Object[]{null, null, var10};
                    var26[1] = true;
                    var26[0] = null;
                    this.x(var26);
                } else {
                    this.N = var23.b();
                    if (var23.a().distanceTo(this.M) > 2.5) {
                        Object[] var25 = new Object[]{null, null, var10};
                        var25[1] = false;
                        var25[0] = null;
                        this.x(var25);
                    } else {
                        if (var23.b() <= this.d_int(new Object[]{var14})) {
                            this.l(new Object[]{var6});
                        }
                    }
                }
            } else {
                if (var21 != null && PositionUtil.a(new Object[]{var21, var12}).distanceTo(this.M) <= 3.0) {
                    this.l(new Object[]{var6});
                } else {
                    Object[] var10005 = new Object[]{null, null, var10};
                    var10005[1] = true;
                    var10005[0] = null;
                    this.x(var10005);
                }
            }
        }
    }

    private int d_int(Object[] var1) {
        Event.b();
        int var5 = 4 + Math.max(1, Math.round(this.l.getRolled(new Object[0]) / 50.0F));
        if ((Boolean)this.g.getValue() && this.R != null) {
            var5 += 3;
        }

        var5 += Math.max(1, Math.round(this.m.getRolled(new Object[0]) / 50.0F));
        var5 += 3;
        return var5 + 2;
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 129708724976366L;
        long var6 = var2 ^ 121553697339068L;
        long var8 = var2 ^ 20898228579218L;
        long var10 = var2 ^ 127334162188590L;
        Event.b();
        this.W = 0;
        this.Y = 0;
        this.Z = 0;
        this.V = 0;
        this.H.a_void_____________________(new Object[]{var4});
        this.F.a_void_____________________(new Object[]{var4});
        this.m.reroll(new Object[]{var8});
        if (WorldUtil2.b(new Object[]{this.O, Blocks.RESPAWN_ANCHOR, var10})) {
            this.D = this.g.getValue() && this.R != null
                ? AnchorState.SAFE_COVER
                : (WorldUtil2.a_int______________________________(new Object[]{this.O, var6}) > 0 ? AnchorState.DETONATE : AnchorState.CHARGE_ANCHOR);
            if (this.D == AnchorState.DETONATE) {
                this.G.a_void_____________________(new Object[]{var4});
                this.n.reroll(new Object[]{var8});
            }
        } else {
            this.D = AnchorState.PLACE_ANCHOR;
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player == null || b.world == null) {
            C = false;
        } else if (this.D == AnchorState.WAIT) {
            this.k(new Object[]{116310162602567L});
            C = this.D == AnchorState.WAIT && this.N >= 0 && this.N <= this.d_int(new Object[]{35748756048687L}) + 8;
        } else if (b.currentScreen != null || !b.isWindowFocused() || b.player.isUsingItem()) {
            C = false;
        } else if (!AutoObbyCrystal.R && !AutoAnchor.B && !AnchorMacro.p) {
            if (this.D != AnchorState.IDLE) {
                if (++this.W > 160) {
                    Object[] var3 = new Object[]{null, null, 24801603922894L};
                    var3[1] = true;
                    var3[0] = "timed out";
                    this.x(var3);
                    return;
                }

                if (this.D != AnchorState.DETONATE) {
                    RenderHelper27 var10000 = this.H;
                    Object[] var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = 200.0F;
                    if (var10000.a(var10004)) {
                        Object[] var2 = new Object[]{null, null, 24801603922894L};
                        var2[1] = true;
                        var2[0] = "stalled";
                        this.x(var2);
                        return;
                    }
                }

                if (this.D == AnchorState.DETONATE) {
                    this.m(new Object[]{135810372551516L});
                }
            } else {
                this.A(new Object[]{25384289206791L});
                this.j(new Object[]{131747906484123L});
            }

            if (this.D != AnchorState.IDLE && !(Boolean)this.i.getValue()) {
                if (Prestige.o.d_boolean______(new Object[0])) {
                    return;
                }

                this.n(new Object[]{49466004368406L});
            }

            C = this.D != AnchorState.IDLE;
        } else {
            if (this.D != AnchorState.IDLE) {
                Object[] var10005 = new Object[]{null, null, 24801603922894L};
                var10005[1] = true;
                var10005[0] = null;
                this.x(var10005);
            }

            C = false;
        }
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 31812202944538L;
        long var6 = var2 ^ 127985057180395L;
        long var8 = var2 ^ 118746247503344L;
        long var10 = var2 ^ 29522446969818L;
        long var12 = var2 ^ 134240512117094L;
        long var14 = var2 ^ 125783940892121L;
        long var16 = var2 ^ 4485980315405L;
        long var18 = var2 ^ 6671112094412L;
        long var20 = var2 ^ 104949205494857L;
        long var22 = var2 ^ 8364944053320L;
        long var24 = var2 ^ 26007270777573L;
        long var26 = var2 ^ 66749685785796L;
        long var28 = var2 ^ 41879554570708L;
        if (WorldUtil2.b(new Object[]{this.O, Blocks.RESPAWN_ANCHOR, var10})
            && WorldUtil2.a_int______________________________(new Object[]{this.O, var22}) <= 0) {
            this.D = AnchorState.CHARGE_ANCHOR;
            this.V = 0;
            this.Y = 0;
            this.H.a_void_____________________(new Object[]{var4});
            this.F.a_void_____________________(new Object[]{var4});
            this.m.reroll(new Object[]{var12});
        } else {
            EnderPearlEntity var31 = this.a_class_1684(new Object[]{var28});
            if (var31 == null) {
                this.N = 0;
            } else {
                VecIntData var32 = this.a_VecIntData(new Object[]{var31, var18});
                this.N = var32 != null ? var32.b() : 0;
            }

            if (++this.Y > 30 && !this.e(new Object[]{var26})) {
                Object[] var36 = new Object[]{null, null, var20};
                var36[1] = true;
                var36[0] = "the pearl never landed";
                this.x(var36);
            } else {
                if (InventoryUtil.l(new Object[]{Items.GLOWSTONE, var14}) && !Prestige.o.d_boolean______(new Object[0])) {
                    int var34 = this.e_int(new Object[]{var16});
                    if (var34 != -1) {
                        Object[] var10003 = new Object[]{null, var8};
                        var10003[0] = var34;
                        InventoryUtil.a_void________(var10003);
                        Prestige.o.g(new Object[]{true});
                    }
                }

                if (this.e(new Object[]{var26})) {
                    PlayerEntity var35 = this.a_class_1657_(new Object[]{var24});
                    if (var35 != null && PositionUtil.a(new Object[]{var35, var6}).distanceTo(this.O.toCenterPos()) > 4.5 && ++this.Z > 10) {
                        Object[] var10005 = new Object[]{null, null, var20};
                        var10005[1] = true;
                        var10005[0] = "they got away from the anchor";
                        this.x(var10005);
                        return;
                    }
                }
            }
        }
    }

    private boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 9746452980435L;
        long var6 = var2 ^ 44919710505419L;
        if (this.K == null) {
            return true;
        } else {
            EnderPearlEntity var9 = this.a_class_1684(new Object[]{var6});
            if (var9 == null) {
                return true;
            } else {
                VecIntData var10 = this.a_VecIntData(new Object[]{var9, var4});
                return var10 == null || var10.b() <= 1;
            }
        }
    }

    private boolean f(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 3189381078738L;
        long var6 = var2 ^ 106820266972892L;
        long var8 = var2 ^ 81591988441341L;
        if (!this.e(new Object[]{var8})) {
            return false;
        } else {
            PlayerEntity var11 = this.a_class_1657_(new Object[]{var6});
            if (var11 != null) {
                if (PositionUtil.a(new Object[]{var11, var4}).distanceTo(this.O.toCenterPos()) > 4.0) {
                    return false;
                }

                if ((Boolean)this.f.getValue() && var11.hurtTime > 0) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 53031967559301L;
        long var6 = var2 ^ 7477342050734L;
        long var8 = var2 ^ 139217056066258L;
        long var10 = var2 ^ 97874213094519L;
        long var12 = var2 ^ 122088347739286L;
        long var14 = var2 ^ 19997521145790L;
        long var16 = var2 ^ 51598816251400L;
        long var18 = var2 ^ 116380450414193L;
        long var20 = var2 ^ 101816702374277L;
        long var22 = var2 ^ 18365272238834L;
        if (!(Boolean)this.i.getValue()) {
            return null;
        } else if (b.player == null || b.world == null || b.currentScreen != null || !b.isWindowFocused() || b.player.isUsingItem()) {
            return null;
        } else if (this.D == AnchorState.IDLE || this.O == null) {
            return null;
        } else if (!AutoObbyCrystal.R && !AutoAnchor.B && !AnchorMacro.p) {
            if (Prestige.o.d_boolean______(new Object[0])) {
                return null;
            } else {
                boolean var25 = this.D == AnchorState.WAIT;
                if (!var25 || this.N >= 0 && this.N <= this.d_int(new Object[]{var12}) + 8) {
                    Vec3d var26 = var25 ? this.a_class_243__(new Object[]{var18}) : this.b_class_243(new Object[]{var14});
                    if (var26 == null) {
                        if (var25) {
                            return null;
                        } else {
                            this.h(new Object[]{var22});
                            return null;
                        }
                    } else if (!var25 && b.player.getEyePos().distanceTo(var26) > 4.4) {
                        Object[] var29 = new Object[]{null, null, var10};
                        var29[1] = true;
                        var29[0] = "the anchor left our reach";
                        this.x(var29);
                        return null;
                    } else {
                        RenderBatch var27 = Prestige.o.a_RenderBatch(new Object[]{var26, var20});
                        var27 = WorldUtil.d_RenderBatch(new Object[]{var27, var26, var6});
                        float var10000 = var27.a(new Object[0]) - b.player.getYaw();
                        Object[] var10003 = new Object[]{null, var16};
                        var10003[0] = var10000;
                        if (Math.abs(WorldUtil.b_float_____________________(var10003)) >= (Float)this.j.getValue()) {
                            if (var25) {
                                return null;
                            } else {
                                Object[] var10005 = new Object[]{null, null, var10};
                                var10005[1] = true;
                                var10005[0] = "outside silent aim FOV";
                                this.x(var10005);
                                return null;
                            }
                        } else if (!var25 && !var27.a_boolean________________________________________(new Object[0]) && this.g(new Object[]{var4})) {
                            this.o(new Object[]{var27, var8});
                            return this.D != AnchorState.IDLE && this.D != AnchorState.WAIT ? var27 : null;
                        } else {
                            return var27;
                        }
                    }
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    private Vec3d a_class_243__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 25059374759154L;
        long var6 = var2 ^ 78129950136654L;
        if (!WorldUtil2.b(new Object[]{this.O, Blocks.RESPAWN_ANCHOR, var6})) {
            return this.Q;
        } else {
            return this.g.getValue() && this.R != null && this.T != null ? this.T : RaycastUtil.b(new Object[]{this.O, var4});
        }
    }

    private void n(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 106884231268849L;
        long var6 = var2 ^ 52310086118822L;
        long var8 = var2 ^ 8841299346179L;
        long var10 = var2 ^ 73936017647818L;
        long var12 = var2 ^ 30219515827810L;
        long var14 = var2 ^ 67011777133794L;
        long var16 = var2 ^ 72281694055814L;
        if (this.g(new Object[]{var4})) {
            Vec3d var19 = this.b_class_243(new Object[]{var10});
            if (var19 != null && b.player.getEyePos().distanceTo(var19) > 4.4) {
                Object[] var10005 = new Object[]{null, null, var8};
                var10005[1] = true;
                var10005[0] = "the anchor left our reach";
                this.x(var10005);
            } else if (var19 == null) {
                this.h(new Object[]{var16});
            } else {
                RenderBatch var20 = WorldUtil.a_RenderBatch_(new Object[]{var19, var12});
                WorldUtil.a_void________________________________(new Object[]{var20, var14});
                this.o(new Object[]{var20, var6});
            }
        }
    }

    private boolean g(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 31132434106250L;

        return switch (this.D) {
            case PLACE_ANCHOR -> true;
            case SAFE_COVER -> this.E.b(new Object[]{this.l, var4});
            case CHARGE_ANCHOR -> this.F.b(new Object[]{this.m, var4});
            case DETONATE -> this.G.b(new Object[]{this.n, var4});
            default -> false;
        };
    }

    private Vec3d b_class_243(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 119994698583357L;

        return switch (this.D) {
            case PLACE_ANCHOR -> this.Q;
            case SAFE_COVER -> this.T;
            case CHARGE_ANCHOR, DETONATE -> RaycastUtil.b(new Object[]{this.O, var4});
            default -> null;
        };
    }

    private boolean h(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 136128013691486L;
        if (++this.V >= 3) {
            Object[] var10005 = new Object[]{null, null, var4};
            var10005[1] = true;
            var10005[0] = "lost the click line";
            this.x(var10005);
            return true;
        } else {
            return false;
        }
    }

    private void o(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 55495940703451L ^ var3;
        long var5 = var3 ^ 61769269446856L;
        long var7 = var3 ^ 29599222396589L;
        long var9 = var3 ^ 81959125273204L;
        long var11 = var3 ^ 76359550527191L;
        switch (this.D) {
            case PLACE_ANCHOR:
                this.p(new Object[]{var2, var7});
                break;
            case SAFE_COVER:
                this.r(new Object[]{var2, var9});
                break;
            case CHARGE_ANCHOR:
                this.t(new Object[]{var2, var11});
                break;
            case DETONATE:
                this.v(new Object[]{var2, var5});
        }
    }

    private void p(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 55495940703451L ^ var2;
        long var5 = var2 ^ 139568295210075L;
        long var7 = var2 ^ 19172812309200L;
        long var9 = var2 ^ 10990255122865L;
        long var11 = var2 ^ 132871915364251L;
        long var13 = var2 ^ 118373233873600L;
        long var15 = var2 ^ 26484172143911L;
        long var17 = var2 ^ 89964705616994L;
        long var19 = var2 ^ 123711374137404L;
        long var21 = var2 ^ 67561877854216L;
        long var23 = var2 ^ 58276865719177L;
        long var25 = var2 ^ 131019556291918L;
        long var27 = var2 ^ 8884709148034L;
        long var29 = var2 ^ 128392066872973L;
        long var31 = var2 ^ 84827517191119L;
        if (WorldUtil2.b(new Object[]{this.O, Blocks.RESPAWN_ANCHOR, var11})) {
            this.q(new Object[]{var25});
        } else if (WorldUtil2.e(new Object[]{this.O, var19})) {
            Object[] var10005 = new Object[]{null, null, var21};
            var10005[1] = true;
            var10005[0] = "the anchor spot got blocked";
            this.x(var10005);
        } else {
            Integer var34 = InventoryUtil.a_Integer(new Object[]{Items.RESPAWN_ANCHOR, var7});
            if (var34 == null) {
                this.y(new Object[]{"No respawn anchor in hotbar.", var27});
            } else {
                BlockHitResult var35 = this.a_class_3965_(new Object[]{var4, var23});
                if (var35 != null && var35.getBlockPos().equals(this.P) && var35.getBlockPos().offset(var35.getSide()).equals(this.O)) {
                    this.V = 0;
                    if (this.U == -1) {
                        this.U = RenderUtil6.a(new Object[]{this, var31});
                    }

                    if (InventoryUtil.a_int_____(new Object[]{var17}) != var34) {
                        int var10000 = var34;
                        Object[] var10003 = new Object[]{null, var9};
                        var10003[0] = var10000;
                        InventoryUtil.a_void________(var10003);
                        Prestige.o.g(new Object[]{true});
                        this.H.a_void_____________________(new Object[]{var5});
                    } else {
                        this.B(new Object[]{var35, var13});
                        this.E.a_void_____________________(new Object[]{var5});
                        this.l.reroll(new Object[]{var15});
                        this.q(new Object[]{var25});
                        this.H.a_void_____________________(new Object[]{var5});
                    }
                } else {
                    this.h(new Object[]{var29});
                }
            }
        }
    }

    private void q(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 81287101386936L;
        if ((Boolean)this.g.getValue() && this.R != null) {
            this.D = AnchorState.SAFE_COVER;
        } else {
            this.s(new Object[]{var4});
        }

        this.V = 0;
    }

    private void r(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 55495940703451L ^ var3;
        long var5 = var3 ^ 36158510474469L;
        long var7 = var3 ^ 51190725791874L;
        long var9 = var3 ^ 71541387984393L;
        long var11 = var3 ^ 111469975799632L;
        long var13 = var3 ^ 98543158039912L;
        long var15 = var3 ^ 65738385714713L;
        long var17 = var3 ^ 102114368597492L;
        long var19 = var3 ^ 1870625810619L;
        long var21 = var3 ^ 40289345931860L;
        long var23 = var3 ^ 32192686800662L;
        if (this.R != null && this.S != null && this.T != null) {
            if (WorldUtil2.e(new Object[]{this.R, var5})) {
                this.s(new Object[]{var17});
            } else {
                Block var26 = ((String)this.h.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE : Blocks.OBSIDIAN;
                Integer var27 = InventoryUtil.a_Integer(new Object[]{var26.asItem(), var9});
                if (var27 == null) {
                    this.s(new Object[]{var17});
                } else {
                    BlockHitResult var28 = this.a_class_3965_(new Object[]{var2, var11});
                    if (var28 != null && var28.getBlockPos().equals(this.S) && var28.getBlockPos().offset(var28.getSide()).equals(this.R)) {
                        this.V = 0;
                        if (this.U == -1) {
                            this.U = RenderUtil6.a(new Object[]{this, var23});
                        }

                        if (InventoryUtil.a_int_____(new Object[]{var19}) != var27) {
                            int var10000 = var27;
                            Object[] var10003 = new Object[]{null, var13};
                            var10003[0] = var10000;
                            InventoryUtil.a_void________(var10003);
                            Prestige.o.g(new Object[]{true});
                            this.H.a_void_____________________(new Object[]{var7});
                        } else {
                            this.B(new Object[]{var28, var15});
                            this.V = 0;
                            this.s(new Object[]{var17});
                            this.H.a_void_____________________(new Object[]{var7});
                        }
                    } else {
                        this.h(new Object[]{var21});
                    }
                }
            }
        } else {
            this.s(new Object[]{var17});
        }
    }

    private void s(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 70543637838253L;
        long var6 = var2 ^ 42679853197521L;
        this.D = AnchorState.CHARGE_ANCHOR;
        this.V = 0;
        this.F.a_void_____________________(new Object[]{var4});
        this.m.reroll(new Object[]{var6});
        this.H.a_void_____________________(new Object[]{var4});
    }

    private void t(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 55495940703451L ^ var2;
        long var5 = var2 ^ 126168308975624L;
        long var7 = var2 ^ 36793011993633L;
        long var9 = var2 ^ 86763849274026L;
        long var11 = var2 ^ 117209374501875L;
        long var13 = var2 ^ 63787996704883L;
        long var15 = var2 ^ 95002839497163L;
        long var17 = var2 ^ 43164078460897L;
        long var19 = var2 ^ 57387848221370L;
        long var21 = var2 ^ 96267081242104L;
        long var23 = var2 ^ 15718616826904L;
        long var25 = var2 ^ 47677748881143L;
        long var27 = var2 ^ 20612601287605L;
        if (!WorldUtil2.b(new Object[]{this.O, Blocks.RESPAWN_ANCHOR, var17})) {
            this.h(new Object[]{var25});
        } else if (WorldUtil2.a_int______________________________(new Object[]{this.O, var13}) > 0) {
            this.u(new Object[]{var5});
        } else {
            Integer var30 = InventoryUtil.a_Integer(new Object[]{Items.GLOWSTONE, var9});
            if (var30 == null) {
                this.y(new Object[]{"No glowstone in hotbar.", var21});
            } else {
                BlockHitResult var31 = this.a_class_3965_(new Object[]{var4, var11});
                if (var31 != null && var31.getBlockPos().equals(this.O)) {
                    this.V = 0;
                    if (this.U == -1) {
                        this.U = RenderUtil6.a(new Object[]{this, var27});
                    }

                    if (InventoryUtil.a_int_____(new Object[]{var23}) != var30) {
                        int var10000 = var30;
                        Object[] var10003 = new Object[]{null, var15};
                        var10003[0] = var10000;
                        InventoryUtil.a_void________(var10003);
                        Prestige.o.g(new Object[]{true});
                        this.H.a_void_____________________(new Object[]{var7});
                    } else {
                        this.B(new Object[]{var31, var19});
                        this.u(new Object[]{var5});
                    }
                } else {
                    this.h(new Object[]{var25});
                }
            }
        }
    }

    private void u(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 107410496745714L;
        long var6 = var2 ^ 8013897572750L;
        this.D = AnchorState.DETONATE;
        this.V = 0;
        this.Y = 0;
        this.Z = 0;
        this.G.a_void_____________________(new Object[]{var4});
        this.n.reroll(new Object[]{var6});
        this.H.a_void_____________________(new Object[]{var4});
    }

    private void v(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 55495940703451L ^ var3;
        long var5 = var3 ^ 115212540778598L;
        long var7 = var3 ^ 101333761233470L;
        long var9 = var3 ^ 47521703352276L;
        long var11 = var3 ^ 99089109688830L;
        long var13 = var3 ^ 64665293144898L;
        long var15 = var3 ^ 80739864521893L;
        long var17 = var3 ^ 111468994665748L;
        long var19 = var3 ^ 55667520196605L;
        long var21 = var3 ^ 75673978443049L;
        long var23 = var3 ^ 4664942623430L;
        long var25 = var3 ^ 34858588251757L;
        long var27 = var3 ^ 26143459558892L;
        long var29 = var3 ^ 78464159653484L;
        long var31 = var3 ^ 47085685652455L;
        long var33 = var3 ^ 94574239697128L;
        if (!WorldUtil2.b(new Object[]{this.O, Blocks.RESPAWN_ANCHOR, var11})) {
            this.h(new Object[]{var33});
        } else if (WorldUtil2.a_int______________________________(new Object[]{this.O, var29}) <= 0) {
            this.D = AnchorState.CHARGE_ANCHOR;
            this.V = 0;
            this.H.a_void_____________________(new Object[]{var7});
            this.F.a_void_____________________(new Object[]{var7});
            this.m.reroll(new Object[]{var13});
        } else if (this.f(new Object[]{var23})) {
            double var36 = Prestige.f.k(new Object[]{b.player, this.O.toCenterPos(), var17});
            float var38 = b.player.getHealth() + b.player.getAbsorptionAmount();
            if (var36 > ((Float)this.d.getValue()).floatValue()) {
                Object[] var42 = new Object[]{null, null, var25};
                var42[1] = true;
                var42[0] = "self damage too high";
                this.x(var42);
            } else if ((Boolean)this.e.getValue() && var36 >= var38 - 1.0F) {
                Object[] var10005 = new Object[]{null, null, var25};
                var10005[1] = true;
                var10005[0] = "would have killed us";
                this.x(var10005);
            } else if (InventoryUtil.l(new Object[]{Items.GLOWSTONE, var19})) {
                int var41 = this.U != -1 ? this.U : this.e_int(new Object[]{var21});
                if (var41 == -1) {
                    this.y(new Object[]{"No non-glowstone slot to detonate with.", var31});
                } else {
                    Object[] var10003 = new Object[]{null, var9};
                    var10003[0] = var41;
                    InventoryUtil.a_void________(var10003);
                    Prestige.o.g(new Object[]{true});
                    this.H.a_void_____________________(new Object[]{var7});
                }
            } else {
                BlockHitResult var39 = this.a_class_3965_(new Object[]{var2, var27});
                if (var39 != null && var39.getBlockPos().equals(this.O)) {
                    this.V = 0;
                    this.B(new Object[]{var39, var15});
                    this.w(new Object[]{var5});
                } else {
                    this.h(new Object[]{var33});
                }
            }
        }
    }

    private void w(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 75439754253081L;
        if (this.K != null) {
            this.J.add(this.K);
        }

        this.A(new Object[]{var4});
        this.z(new Object[0]);
    }

    private void x(Object[] var1) {
        String var5 = (String)var1[0];
        boolean var2 = (Boolean)var1[1];
        long var3 = (Long)var1[2];
        var3 = 55495940703451L ^ var3;
        long var6 = var3 ^ 72448090584337L;
        long var8 = var3 ^ 125226629705864L;
        long var10 = var3 ^ 57043752135954L;
        long var12 = var3 ^ 42540602525721L;
        if (var5 != null) {
            RenderHelper27 var10000 = this.I;
            Object[] var10004 = new Object[]{null, var12};
            var10004[0] = 3000.0F;
            if (var10000.a(var10004)) {
                ModuleNotificationsElement.s(new Object[]{this, "Anchor Pearl Catch: " + var5, NotificationType.INFO, var6});
                this.I.a_void_____________________(new Object[]{var8});
            }
        }

        if (var2 && this.K != null) {
            this.J.add(this.K);
        }

        this.A(new Object[]{var10});
        this.z(new Object[0]);
    }

    private void y(Object[] var1) {
        String var2 = (String)var1[0];
        long var3 = (Long)var1[1];
        var3 = 55495940703451L ^ var3;
        long var5 = var3 ^ 128105903564955L;
        long var7 = var3 ^ 75326219857154L;
        long var9 = var3 ^ 7834012031313L;
        long var11 = var3 ^ 21810277291411L;
        RenderHelper27 var10000 = this.I;
        Object[] var10004 = new Object[]{null, var11};
        var10004[0] = 3000.0F;
        if (var10000.a(var10004)) {
            ModuleNotificationsElement.s(new Object[]{this, var2, NotificationType.ERROR, var5});
            this.I.a_void_____________________(new Object[]{var7});
        }

        Object[] var10005 = new Object[]{null, null, var9};
        var10005[1] = true;
        var10005[0] = null;
        this.x(var10005);
    }

    private void z(Object[] var1) {
        this.D = AnchorState.IDLE;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = -1;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.V = 0;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        C = false;
    }

    private void A(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 55495940703451L ^ var2;
        long var4 = var2 ^ 49109876669017L;
        long var6 = var2 ^ 8165528657067L;
        if (this.U != -1) {
            if (b.player == null) {
                RenderUtil6.a_void____________________________(new Object[]{this, var4});
                this.U = -1;
            } else if (!RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                this.U = -1;
            } else if (!Prestige.o.d_boolean______(new Object[0])) {
                if ((Boolean)this.k.getValue()) {
                    int var10000 = this.U;
                    Object[] var10003 = new Object[]{null, var6};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                    Prestige.o.g(new Object[]{true});
                }

                RenderUtil6.a_void____________________________(new Object[]{this, var4});
                this.U = -1;
            }
        }
    }

    private void B(Object[] var1) {
        BlockHitResult var4 = (BlockHitResult)var1[0];
        boolean var5 = b.interactionManager.interactBlock(b.player, Hand.MAIN_HAND, var4).isAccepted();
        Prestige.o.g(new Object[]{true});
        if (var5) {
            b.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private BlockHitResult a_class_3965_(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 55495940703451L ^ var3;
        long var5 = var3 ^ 10139559460270L;
        Event.b();
        return WorldUtil.a_class_239_(new Object[]{var2, var5}) instanceof BlockHitResult var9 ? var9 : null;
    }

    private int e_int(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() != Items.GLOWSTONE) {
                return var5;
            }
        }

        return -1;
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.o.getValue() && b.player != null && b.world != null) {
            if (this.D != AnchorState.IDLE) {
                if (this.D == AnchorState.WAIT && this.M != null) {
                    this.C(new Object[]{var1, BlockPos.ofFloored(this.M), (Color)this.r.getValue(), 22045344565916L});
                }

                if (this.O != null && this.D != AnchorState.WAIT) {
                    this.C(new Object[]{var1, this.O, (Color)this.p.getValue(), 22045344565916L});
                }

                if (this.D == AnchorState.SAFE_COVER && this.R != null) {
                    this.C(new Object[]{var1, this.R, (Color)this.q.getValue(), 22045344565916L});
                }
            }
        }
    }

    private void C(Object[] var1) {
        TickEvent27 var2 = (TickEvent27)var1[0];
        BlockPos var3 = (BlockPos)var1[1];
        Color var4 = (Color)var1[2];
        long var5 = (Long)var1[3];
        var5 = 55495940703451L ^ var5;
        long var7 = var5 ^ 81687652922313L;
        MatrixCameraData var10000 = var2.b;
        RenderHelper8 var10001 = var2.a;
        float var10002 = var3.getX();
        float var10003 = var3.getY();
        float var10004 = var3.getZ();
        float var10005 = var3.getX() + 1.0F;
        float var10006 = var3.getY() + 1.0F;
        float var10007 = var3.getZ() + 1.0F;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var4, var7};
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

    @Subscribe
    public void a(TickEvent25 var1) {
        if ((Boolean)this.o.getValue()) {
            if (b.currentScreen == null || b.currentScreen instanceof ChatScreen) {
                if (b.world != null && b.player != null) {
                    if (this.D != AnchorState.IDLE && this.O != null) {
                        String var21 = this.N > 0 ? String.format("%.1fs", this.N / 20.0F) : "!";
                        Vec3d var22 = this.D == AnchorState.WAIT && this.M != null
                            ? new Vec3d(this.M.x, this.M.y + 0.8, this.M.z)
                            : new Vec3d(this.O.getX() + 0.5, this.O.getY() + 1.3, this.O.getZ() + 0.5);
                        Vec3d var23 = MatrixStack.a_class_243_______________(new Object[]{var22, 73023576373275L});
                        if (!(var23.z < 0.0) && !(var23.z >= 1.0)) {
                            Font var24 = Prestige.c.a(new Object[]{1236438218049L});
                            short var10000 = 24450;
                            float var25 = var24.c_float___(new Object[]{78913221670390L});
                            String var26 = "Anchor";
                            float var27 = var24.a_float______________(new Object[]{var26, 47859522476307L});
                            float var28 = var24.a_float______________(new Object[]{var21, 47859522476307L});
                            float var29 = 3.0F;
                            float var30 = 0.5F;
                            float var31 = 4.0F;
                            float var32 = 3.0F;
                            float var33 = 5.0F;
                            float var34 = Math.max(var27, var28);
                            float var35 = var25 + var29 + var30 + var29 + var25;
                            float var36 = var34 + 2.0F * var31;
                            float var37 = var35 + 2.0F * var32;
                            float var38 = (float)var23.x;
                            float var39 = (float)var23.y;
                            float var40 = var38 - var36 / 2.0F;
                            float var41 = var39 - var37 / 2.0F;
                            Matrix4f var42 = new Matrix4f();
                            Color var43 = new Color(14, 14, 14, 200);
                            Color var44 = new Color(255, 255, 255, 50);
                            Color var45 = new Color(255, 255, 255, 255);
                            Color var46 = (Color)this.p.getValue();
                            RenderHelper8 var50 = var1.a;
                            MatrixCameraData var10001 = var1.b;
                            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 35553070502242L};
                            var10011[8] = var33;
                            var10011[7] = 5;
                            var10011[6] = var37;
                            var10011[5] = var36;
                            var10011[4] = var41;
                            var10011[3] = var40;
                            var10011[2] = var42;
                            var10011[1] = var10001;
                            var10011[0] = var50;
                            RenderTarget.e(var10011);
                            RenderHelper8 var51 = var1.a;
                            var10001 = var1.b;
                            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
                            var10011[8] = var33;
                            var10011[7] = var43;
                            var10011[6] = var37;
                            var10011[5] = var36;
                            var10011[4] = var41;
                            var10011[3] = var40;
                            var10011[2] = var42;
                            var10011[1] = var10001;
                            var10011[0] = var51;
                            RoundedPosColorShader.c(var10011);
                            float var10003 = var38 - var27 / 2.0F;
                            float var10004 = var41 + var32;
                            Object[] var10008 = new Object[]{null, null, null, null, var46, 36494305523424L};
                            var10008[3] = var10004;
                            var10008[2] = var10003;
                            var10008[1] = var26;
                            var10008[0] = var42;
                            var24.v(var10008);
                            float var47 = var40 + var31;
                            float var48 = var40 + var36 - var31;
                            float var49 = var41 + var32 + var25 + var29;
                            var10004 = var49 + var30;
                            var10008 = new Object[]{null, null, null, null, null, var44, 43912076835818L};
                            var10008[4] = var10004;
                            var10008[3] = var48;
                            var10008[2] = var49;
                            var10008[1] = var47;
                            var10008[0] = var42;
                            RenderState.l(var10008);
                            var10003 = var38 - var28 / 2.0F;
                            var10004 = var49 + var30 + var29;
                            var10008 = new Object[]{null, null, null, null, var45, 36494305523424L};
                            var10008[3] = var10004;
                            var10008[2] = var10003;
                            var10008[1] = var21;
                            var10008[0] = var42;
                            var24.v(var10008);
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
