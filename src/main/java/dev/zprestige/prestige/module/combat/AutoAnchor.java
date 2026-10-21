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
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.VecBoolData2;
import dev.zprestige.prestige.data.VecBoolIntData;
import dev.zprestige.prestige.data.VecData2;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent10;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper5;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderUtil6;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.type.AutoAnchorState;
import dev.zprestige.prestige.type.Easing;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.RaycastUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;

public class AutoAnchor extends Module implements RenderContract3 {
    private FloatSetting a;
    private FloatSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private EnumSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private RangeSetting j;
    private RangeSetting k;
    private RangeSetting l;
    private BoolSetting m;
    private BoolSetting n;
    private ColorSetting o;
    private ColorSetting p;
    private ColorSetting q;
    private static final float r = 12.0F;
    private static final float s = 60.0F;
    private static final float t = 200.0F;
    private static final float u = 6.0F;
    private static final float v = 3.0F;
    private static final float w = 1.0F;
    private static final float x = 3.0F;
    private static final Direction[] z = Direction.values();
    private static final int[][] A = new int[][]{{-1, 0, 0}, {1, 0, 0}, {0, 0, -1}, {0, 0, 1}, {-1, 0, -1}, {-1, 0, 1}, {1, 0, -1}, {1, 0, 1}, {0, 1, 0}};
    public static boolean B;
    private AutoAnchorState C = AutoAnchorState.IDLE;
    private RenderHelper27 D = new RenderHelper27(26293358099357L);
    private RenderHelper27 E = new RenderHelper27(26293358099357L);
    private RenderHelper27 F = new RenderHelper27(26293358099357L);
    private RenderHelper27 G = new RenderHelper27(26293358099357L);
    private int H;
    private static final int I = 3;
    private HashMap J = new HashMap();
    private long K;
    private long M;
    private PlayerEntity N;
    private BlockPos O;
    private BlockPos P;
    private Vec3d Q;
    private Vec3d R;
    private Vec3d S;
    private float T;
    private int U;
    private volatile long V;
    private long W;
    private int X;
    private PlayerEntity Y;
    private BlockPos Z;
    private BlockPos aa;
    private Vec3d ab;
    private BlockPos ac;
    private BlockPos ad;
    private Vec3d ae;
    private BlockPos af;
    private int ag = -1;
    private Vec3d ah;
    private List ai = new ArrayList();
    private static final float aj = 180.0F;
    private static final double ak = 900.0;
    private static final float al = 0.6F;
    private static final float am = 0.4F;
    private RenderHelper5 an = new RenderHelper5(180.0F, false, Easing.SINE_IN_OUT, 88790582046503L);
    private RenderHelper5 ao = new RenderHelper5(180.0F, false, Easing.SINE_IN_OUT, 88790582046503L);
    private RenderHelper5 ap = new RenderHelper5(180.0F, false, Easing.SINE_IN_OUT, 88790582046503L);
    private BlockPos aq;
    private BlockPos ar;
    private BlockPos as;
    private RenderHelper27 at = new RenderHelper27(26293358099357L);

    public AutoAnchor() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.q.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL61_0 -> (Boolean)this.n.getValue(), 119980880492450L});
        this.p
            .setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL62_0 -> (Boolean)this.n.getValue() && (Boolean)this.f.getValue(), 119980880492450L});
        this.o.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL63_0 -> (Boolean)this.n.getValue(), 119980880492450L});
        this.g.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL64_0 -> (Boolean)this.f.getValue(), 90319707064974L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 14673990835481L;
        long var6 = var2 ^ 91082959635357L;
        this.A(new Object[]{var4});
        Prestige.o.e(new Object[]{this, var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 51011343502304L;
        long var6 = var2 ^ 82893747353842L;
        long var8 = var2 ^ 140372736859409L;
        long var10 = var2 ^ 95502412072826L;
        long var12 = var2 ^ 23510651249799L;
        if (this.ag != -1 && (Boolean)this.i.getValue() && Prestige.o.d_boolean______(new Object[0])) {
            Renderer2D var10000 = Prestige.o;
            int var10001 = this.ag;
            Object[] var10004 = new Object[]{null, var6};
            var10004[0] = var10001;
            var10000.i(var10004);
            RenderUtil6.a_void____________________________(new Object[]{this, var4});
            this.ag = -1;
        }

        this.y(new Object[]{var12});
        this.A(new Object[]{var10});
        Prestige.o.f(new Object[]{this, var8});
    }

    @Override
    public int a() {
        return 20;
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.A(new Object[]{124776060678175L});
        this.ai.clear();
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (this.C != AutoAnchorState.IDLE) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent10 var1) {
        if (this.C != AutoAnchorState.IDLE) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.C != AutoAnchorState.IDLE) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE && b.player != null) {
            this.ah = b.player.getEyePos();
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof BlockUpdateS2CPacket var5) {
            this.V++;
            if (var5.getState() != null && var5.getState().getBlock() != Blocks.RESPAWN_ANCHOR) {
                this.ai.remove(var5.getPos());
            }
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.n.getValue() && b.player != null && b.world != null) {
            float var13 = b_float________(new Object[]{60036511093915L});
            boolean var14 = this.Y != null;
            if (var14) {
                this.aq = this.Y.getBlockPos();
            }

            RenderHelper5 var10000 = this.an;
            Object[] var10004 = new Object[]{null, null, 80996539082704L};
            var10004[1] = var14;
            var10004[0] = var10000;
            float var15 = c(var10004);
            if (var15 > 0.01F && this.aq != null) {
                Color var23 = (Color)this.q.getValue();
                Object[] var10005 = new Object[]{null, null, null, 78311498633207L};
                var10005[2] = var13;
                var10005[1] = var15;
                var10005[0] = var23;
                Color var16 = a_Color__(var10005);
                if (var16.getAlpha() > 0) {
                    this.j(new Object[]{var1, this.aq, var16, 119087043519647L});
                }
            }

            boolean var21 = this.Z != null;
            if (var21) {
                this.ar = this.Z;
            }

            var10000 = this.ao;
            var10004 = new Object[]{null, null, 80996539082704L};
            var10004[1] = var21;
            var10004[0] = var10000;
            float var17 = c(var10004);
            if (var17 > 0.01F && this.ar != null) {
                Color var25 = (Color)this.o.getValue();
                Object[] var30 = new Object[]{null, null, null, 78311498633207L};
                var30[2] = var13;
                var30[1] = var17;
                var30[0] = var25;
                Color var18 = a_Color__(var30);
                if (var18.getAlpha() > 0) {
                    this.j(new Object[]{var1, this.ar, var18, 119087043519647L});
                }
            }

            boolean var22 = this.ac != null && this.C == AutoAnchorState.SAFE_COVER;
            if (var22) {
                this.as = this.ac;
            }

            var10000 = this.ap;
            var10004 = new Object[]{null, null, 80996539082704L};
            var10004[1] = var22;
            var10004[0] = var10000;
            float var19 = c(var10004);
            if (var19 > 0.01F && this.as != null) {
                Color var27 = (Color)this.p.getValue();
                Object[] var31 = new Object[]{null, null, null, 78311498633207L};
                var31[2] = var13;
                var31[1] = var19;
                var31[0] = var27;
                Color var20 = a_Color__(var31);
                if (var20.getAlpha() > 0) {
                    this.j(new Object[]{var1, this.as, var20, 119087043519647L});
                }
            }
        }
    }

    private static float b_float________(Object[] var0) {
        return 0.5F + 0.5F * (float)Math.sin(System.currentTimeMillis() / 900.0 * (Math.PI * 2));
    }

    private static float c(Object[] var0) {
        RenderHelper5 var1 = (RenderHelper5)var0[0];
        boolean var2 = (Boolean)var0[1];
        long var3 = (Long)var0[2];
        var3 = 105273939794480L ^ var3;
        long var5 = var3 ^ 119037965226482L;
        long var7 = var3 ^ 97423852277569L;
        if (var1.a(new Object[0]) != var2) {
            Object[] var10004 = new Object[]{null, var5};
            var10004[0] = var2;
            var1.a_void_______________________(var10004);
        }

        return var1.a_float___________________(new Object[]{var7});
    }

    private static int a_int________(Object[] var0) {
        int var1 = (Integer)var0[0];
        float var2 = (Float)var0[1];
        float var3 = (Float)var0[2];
        int var6 = Math.round(var1 * var2 * (0.6F + 0.4F * var3));
        return Math.max(0, Math.min(255, var6));
    }

    private static Color a_Color__(Object[] var0) {
        Color var3 = (Color)var0[0];
        float var1 = (Float)var0[1];
        float var2 = (Float)var0[2];
        long var4 = (Long)var0[3];
        var4 = 105273939794480L ^ var4;
        long var6 = var4 ^ 82951863138869L;
        int var10002 = var3.getRed();
        int var10003 = var3.getGreen();
        int var10004 = var3.getBlue();
        int var10005 = var3.getAlpha();
        Object[] var10010 = new Object[]{null, null, null, var6};
        var10010[2] = var2;
        var10010[1] = var1;
        var10010[0] = var10005;
        return new Color(var10002, var10003, var10004, a_int________(var10010));
    }

    private void j(Object[] var1) {
        TickEvent27 var6 = (TickEvent27)var1[0];
        BlockPos var4 = (BlockPos)var1[1];
        Color var5 = (Color)var1[2];
        long var2 = (Long)var1[3];
        var2 = 105273939794480L ^ var2;
        long var7 = var2 ^ 105344602161953L;
        MatrixCameraData var10000 = var6.b;
        RenderHelper8 var10001 = var6.a;
        float var10002 = var4.getX();
        float var10003 = var4.getY();
        float var10004 = var4.getZ();
        float var10005 = var4.getX() + 1.0F;
        float var10006 = var4.getY() + 1.0F;
        float var10007 = var4.getZ() + 1.0F;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var5, var7};
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
    public void a(TickEvent19 var1) {
        Event.b();
        this.K++;
        if (!this.J.isEmpty()) {
            this.J.entrySet().removeIf(lpL60_0 -> this.K - (Long)((java.util.Map.Entry)lpL60_0).getValue() > 40L);
        }

        if (b.player == null || b.world == null || b.currentScreen != null || !b.isWindowFocused() || b.player.isUsingItem()) {
            B = false;
        } else if (!AutoObbyCrystal.R && !AnchorPearlCatch.C) {
            if (this.C != AutoAnchorState.IDLE && (!this.h(new Object[]{25720726446400L}) || this.i(new Object[]{13353245394690L}))) {
                this.z(new Object[]{57197509800820L});
            }

            if (this.C != AutoAnchorState.IDLE) {
                RenderHelper27 var10000 = this.G;
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = 200.0F;
                if (var10000.a(var10004)) {
                    this.z(new Object[]{57197509800820L});
                }
            }

            if (this.C == AutoAnchorState.IDLE) {
                this.y(new Object[]{55810207585250L});
                this.u(new Object[]{75289287165817L});
            } else if (!(Boolean)this.h.getValue()) {
                if (Prestige.o.d_boolean______(new Object[0])) {
                    return;
                }

                this.k(new Object[]{52894314514730L});
            }

            B = this.C != AutoAnchorState.IDLE;
        } else {
            if (this.C != AutoAnchorState.IDLE) {
                this.z(new Object[]{57197509800820L});
            }

            this.y(new Object[]{55810207585250L});
            B = false;
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 116221305777013L;
        long var6 = var2 ^ 7477342050734L;
        long var8 = var2 ^ 15115887906165L;
        long var10 = var2 ^ 51598816251400L;
        long var12 = var2 ^ 135857772043469L;
        long var14 = var2 ^ 101816702374277L;
        long var16 = var2 ^ 19152179247881L;
        long var18 = var2 ^ 60850652382606L;
        long var20 = var2 ^ 36681481683100L;
        if (!(Boolean)this.h.getValue()) {
            return null;
        } else if (b.player == null || b.world == null || b.currentScreen != null || !b.isWindowFocused() || b.player.isUsingItem()) {
            return null;
        } else if (this.C == AutoAnchorState.IDLE || this.Z == null || this.Y == null) {
            return null;
        } else if (AutoObbyCrystal.R) {
            return null;
        } else if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else if (!this.d(new Object[]{var4})) {
            return null;
        } else {
            Vec3d var23 = this.a_class_243____(new Object[]{var8});
            if (this.a_boolean__________(new Object[]{var23, var18})) {
                this.z(new Object[]{var12});
                return null;
            } else if (var23 == null) {
                this.e(new Object[]{var20});
                return null;
            } else {
                RenderBatch var24 = Prestige.o.a_RenderBatch(new Object[]{var23, var14});
                var24 = WorldUtil.d_RenderBatch(new Object[]{var24, var23, var6});
                float var10000 = var24.a(new Object[0]) - b.player.getYaw();
                Object[] var10003 = new Object[]{null, var10};
                var10003[0] = var10000;
                if (Math.abs(WorldUtil.b_float_____________________(var10003)) >= 60.0F) {
                    this.z(new Object[]{var12});
                    return null;
                } else if (var24.a_boolean________________________________________(new Object[0])) {
                    return var24;
                } else {
                    this.l(new Object[]{var24, var16});
                    return this.C == AutoAnchorState.IDLE ? null : var24;
                }
            }
        }
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 80938587477974L;
        long var6 = var2 ^ 50098298237398L;
        long var8 = var2 ^ 100745980026990L;
        long var10 = var2 ^ 116949926292917L;
        long var12 = var2 ^ 85572466657077L;
        long var14 = var2 ^ 54168877467562L;
        long var16 = var2 ^ 25808456464685L;
        long var18 = var2 ^ 1595727920191L;
        if (this.d(new Object[]{var4})) {
            Vec3d var21 = this.a_class_243____(new Object[]{var6});
            if (this.a_boolean__________(new Object[]{var21, var16})) {
                this.z(new Object[]{var8});
            } else if (var21 == null) {
                this.e(new Object[]{var18});
            } else {
                RenderBatch var22 = WorldUtil.a_RenderBatch_(new Object[]{var21, var10});
                WorldUtil.a_void________________________________(new Object[]{var22, var12});
                this.l(new Object[]{var22, var14});
            }
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 44117291891857L;

        return switch (this.C) {
            case PLACE_ANCHOR -> true;
            case SAFE_COVER -> this.D.b(new Object[]{this.j, var4});
            case CHARGE_ANCHOR -> this.E.b(new Object[]{this.k, var4});
            case DETONATE -> this.F.b(new Object[]{this.l, var4});
            default -> false;
        };
    }

    private Vec3d a_class_243____(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 34543641538845L;

        return switch (this.C) {
            case PLACE_ANCHOR -> this.ab;
            case SAFE_COVER -> this.ae;
            case CHARGE_ANCHOR, DETONATE -> RaycastUtil.b(new Object[]{this.Z, var4});
            default -> null;
        };
    }

    private boolean a_boolean__________(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        return var2 == null ? false : b.player.getEyePos().distanceTo(var2) > 4.4 || this.ah != null && this.ah.distanceTo(var2) > 4.4;
    }

    private boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 5968785606241L;
        if (++this.H >= 3) {
            this.z(new Object[]{var4});
            return true;
        } else {
            return false;
        }
    }

    private void l(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 105273939794480L ^ var2;
        long var5 = var2 ^ 130344084500645L;
        long var7 = var2 ^ 125922209398472L;
        long var9 = var2 ^ 47219774531500L;
        long var11 = var2 ^ 80455321689799L;
        switch (this.C) {
            case PLACE_ANCHOR:
                this.m(new Object[]{var4, var7});
                break;
            case SAFE_COVER:
                this.o(new Object[]{var4, var5});
                break;
            case CHARGE_ANCHOR:
                this.q(new Object[]{var4, var11});
                break;
            case DETONATE:
                this.s(new Object[]{var4, var9});
        }
    }

    private void m(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 105273939794480L ^ var2;
        long var5 = var2 ^ 133539542513125L;
        long var7 = var2 ^ 25202247080814L;
        long var9 = var2 ^ 2433635833593L;
        long var11 = var2 ^ 15826955956239L;
        long var13 = var2 ^ 139927769974309L;
        long var15 = var2 ^ 34692832879769L;
        long var17 = var2 ^ 79223979126183L;
        long var19 = var2 ^ 94872942202332L;
        long var21 = var2 ^ 73328663971165L;
        long var23 = var2 ^ 130945278820738L;
        long var25 = var2 ^ 68849546613720L;
        long var27 = var2 ^ 51007182803346L;
        long var29 = var2 ^ 26810803181836L;
        long var31 = var2 ^ 82178500287089L;
        if (WorldUtil2.b(new Object[]{this.Z, Blocks.RESPAWN_ANCHOR, var13})) {
            this.n(new Object[]{var17});
        } else if (WorldUtil2.e(new Object[]{this.Z, var23})) {
            this.z(new Object[]{var29});
        } else {
            Integer var34 = InventoryUtil.a_Integer(new Object[]{Items.RESPAWN_ANCHOR, var7});
            if (var34 == null) {
                this.x(new Object[]{"No respawn anchor in hotbar.", var27});
            } else {
                BlockHitResult var35 = this.a_class_3965__(new Object[]{var4, var9});
                if (var35 != null && var35.getBlockPos().equals(this.aa) && var35.getBlockPos().offset(var35.getSide()).equals(this.Z)) {
                    this.H = 0;
                    if (this.ag == -1) {
                        this.ag = RenderUtil6.a(new Object[]{this, var31});
                    }

                    if (InventoryUtil.a_int_____(new Object[]{var19}) != var34) {
                        int var10000 = var34;
                        Object[] var10003 = new Object[]{null, var11};
                        var10003[0] = var10000;
                        InventoryUtil.a_void________(var10003);
                        Prestige.o.g(new Object[]{true});
                        this.G.a_void_____________________(new Object[]{var5});
                    } else {
                        this.t(new Object[]{var35, var25});
                        this.ai.add(this.Z);
                        this.D.a_void_____________________(new Object[]{var5});
                        this.j.reroll(new Object[]{var15});
                        this.n(new Object[]{var17});
                        this.G.a_void_____________________(new Object[]{var5});
                    }
                } else {
                    this.e(new Object[]{var21});
                }
            }
        }
    }

    private void n(Object[] var1) {
        if ((Boolean)this.f.getValue() && this.ac != null) {
            this.C = AutoAnchorState.SAFE_COVER;
        } else {
            this.C = AutoAnchorState.CHARGE_ANCHOR;
        }

        this.H = 0;
    }

    private void o(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 105273939794480L ^ var3;
        long var5 = var3 ^ 126523403259887L;
        long var7 = var3 ^ 137982479383432L;
        long var9 = var3 ^ 20758896678147L;
        long var11 = var3 ^ 64411030085045L;
        long var13 = var3 ^ 6854963517588L;
        long var15 = var3 ^ 11474345131618L;
        long var17 = var3 ^ 30267065461492L;
        long var19 = var3 ^ 90450942837681L;
        long var21 = var3 ^ 53629237344462L;
        long var23 = var3 ^ 86548825822236L;
        long var25 = var3 ^ 77772137581360L;
        if (this.ac != null && this.ad != null && this.ae != null) {
            if (WorldUtil2.e(new Object[]{this.ac, var5})) {
                this.p(new Object[]{var21});
            } else {
                Block var28 = ((String)this.g.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE : Blocks.OBSIDIAN;
                Integer var29 = InventoryUtil.a_Integer(new Object[]{var28.asItem(), var9});
                if (var29 == null) {
                    this.p(new Object[]{var21});
                } else {
                    BlockHitResult var30 = this.a_class_3965__(new Object[]{var2, var13});
                    if (var30 != null && var30.getBlockPos().equals(this.ad) && var30.getBlockPos().offset(var30.getSide()).equals(this.ac)) {
                        this.H = 0;
                        if (this.ag == -1) {
                            this.ag = RenderUtil6.a(new Object[]{this, var23});
                        }

                        if (InventoryUtil.a_int_____(new Object[]{var19}) != var29) {
                            int var10000 = var29;
                            Object[] var10003 = new Object[]{null, var15};
                            var10003[0] = var10000;
                            InventoryUtil.a_void________(var10003);
                            Prestige.o.g(new Object[]{true});
                            this.G.a_void_____________________(new Object[]{var7});
                        } else {
                            this.t(new Object[]{var30, var11});
                            this.E.a_void_____________________(new Object[]{var7});
                            this.k.reroll(new Object[]{var17});
                            this.C = AutoAnchorState.CHARGE_ANCHOR;
                            this.H = 0;
                            this.G.a_void_____________________(new Object[]{var7});
                        }
                    } else {
                        this.e(new Object[]{var25});
                    }
                }
            }
        } else {
            this.p(new Object[]{var21});
        }
    }

    private void p(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 19821085727094L;
        this.C = AutoAnchorState.CHARGE_ANCHOR;
        this.H = 0;
        this.G.a_void_____________________(new Object[]{var4});
    }

    private void q(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 105273939794480L ^ var2;
        long var5 = var2 ^ 47291063539242L;
        long var7 = var2 ^ 73525053314538L;
        long var9 = var2 ^ 49757744397153L;
        long var11 = var2 ^ 63343883597558L;
        long var13 = var2 ^ 59167373496320L;
        long var15 = var2 ^ 75769721644586L;
        long var17 = var2 ^ 83138824798388L;
        long var19 = var2 ^ 39751930021014L;
        long var21 = var2 ^ 120824649821651L;
        long var23 = var2 ^ 133160405181778L;
        long var25 = var2 ^ 5723892560855L;
        long var27 = var2 ^ 23978589745565L;
        long var29 = var2 ^ 124345041074814L;
        if (!WorldUtil2.b(new Object[]{this.Z, Blocks.RESPAWN_ANCHOR, var15})) {
            this.e(new Object[]{var23});
        } else {
            int var32 = this.c_int(new Object[]{this.Z, var5});
            if (var32 > 0) {
                this.r(new Object[]{var17});
            } else {
                Integer var33 = InventoryUtil.a_Integer(new Object[]{Items.GLOWSTONE, var9});
                if (var33 == null) {
                    this.x(new Object[]{"No glowstone in hotbar.", var27});
                } else {
                    BlockHitResult var34 = this.a_class_3965__(new Object[]{var4, var11});
                    if (var34 != null && var34.getBlockPos().equals(this.Z)) {
                        this.H = 0;
                        if (this.ag == -1) {
                            this.ag = RenderUtil6.a(new Object[]{this, var29});
                        }

                        if (InventoryUtil.a_int_____(new Object[]{var21}) != var33) {
                            int var10000 = var33;
                            Object[] var10003 = new Object[]{null, var13};
                            var10003[0] = var10000;
                            InventoryUtil.a_void________(var10003);
                            Prestige.o.g(new Object[]{true});
                            this.G.a_void_____________________(new Object[]{var7});
                        } else {
                            this.t(new Object[]{var34, var25});
                            this.F.a_void_____________________(new Object[]{var7});
                            this.l.reroll(new Object[]{var19});
                            this.r(new Object[]{var17});
                        }
                    } else {
                        this.e(new Object[]{var23});
                    }
                }
            }
        }
    }

    private void r(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 95646127804270L;
        this.C = AutoAnchorState.DETONATE;
        this.H = 0;
        this.G.a_void_____________________(new Object[]{var4});
    }

    private void s(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 105273939794480L ^ var2;
        long var5 = var2 ^ 80112082399041L;
        long var7 = var2 ^ 36286594477185L;
        long var9 = var2 ^ 94634126223723L;
        long var11 = var2 ^ 99240161546141L;
        long var13 = var2 ^ 43120404881217L;
        long var15 = var2 ^ 26699103331243L;
        long var17 = var2 ^ 87446096261442L;
        long var19 = var2 ^ 29435853773881L;
        long var21 = var2 ^ 84070775762545L;
        long var23 = var2 ^ 113159262735036L;
        long var25 = var2 ^ 129814051637494L;
        long var27 = var2 ^ 70466874769512L;
        long var29 = var2 ^ 119540392259527L;
        if (!WorldUtil2.b(new Object[]{this.Z, Blocks.RESPAWN_ANCHOR, var13})) {
            this.e(new Object[]{var19});
        } else {
            int var32 = this.c_int(new Object[]{this.Z, var5});
            if (var32 <= 0) {
                this.p(new Object[]{var29});
            } else {
                if (this.Y != null && this.Y.hurtTime > 2) {
                    this.G.a_void_____________________(new Object[]{var7});
                    if (++this.X <= 12) {
                        return;
                    }
                }

                this.X = 0;
                double var33 = Prestige.f.k(new Object[]{b.player, this.Z.toCenterPos(), var15});
                float var35 = b.player.getHealth() + b.player.getAbsorptionAmount();
                if (var33 > ((Float)this.d.getValue()).floatValue()) {
                    this.J.put(this.Z.toImmutable(), this.K);
                    this.z(new Object[]{var27});
                } else if ((Boolean)this.e.getValue() && var33 >= var35 - 1.0F) {
                    this.z(new Object[]{var27});
                } else if (InventoryUtil.l(new Object[]{Items.GLOWSTONE, var17})) {
                    int var38 = this.ag != -1 ? this.ag : this.d_int_(new Object[]{var21});
                    if (var38 == -1) {
                        this.x(new Object[]{"No non-glowstone slot to detonate with.", var25});
                    } else {
                        Object[] var10003 = new Object[]{null, var9};
                        var10003[0] = var38;
                        InventoryUtil.a_void________(var10003);
                        Prestige.o.g(new Object[]{true});
                        this.G.a_void_____________________(new Object[]{var7});
                    }
                } else {
                    BlockHitResult var36 = this.a_class_3965__(new Object[]{var4, var11});
                    if (var36 != null && var36.getBlockPos().equals(this.Z)) {
                        this.H = 0;
                        this.t(new Object[]{var36, var23});
                        this.z(new Object[]{var27});
                    } else {
                        this.e(new Object[]{var19});
                    }
                }
            }
        }
    }

    private int d_int_(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() != Items.GLOWSTONE) {
                return var5;
            }
        }

        return -1;
    }

    private void t(Object[] var1) {
        BlockHitResult var4 = (BlockHitResult)var1[0];
        boolean var5 = b.interactionManager.interactBlock(b.player, Hand.MAIN_HAND, var4).isAccepted();
        Prestige.o.g(new Object[]{true});
        if (var5) {
            b.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private BlockHitResult a_class_3965__(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 105273939794480L ^ var2;
        long var5 = var2 ^ 92715299821451L;
        Event.b();
        return WorldUtil.a_class_239_(new Object[]{var4, var5}) instanceof BlockHitResult var9 ? var9 : null;
    }

    private void u(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 104741225612570L;
        long var6 = var2 ^ 86121097354001L;
        long var8 = var2 ^ 100004932027201L;
        long var10 = var2 ^ 42916798185748L;
        long var12 = var2 ^ 86674405491412L;
        long var14 = var2 ^ 16112572092981L;
        long var16 = var2 ^ 44177499018152L;
        long var18 = var2 ^ 22741235671889L;
        Event.b();
        PlayerEntity var21 = CombatUtil.a_class_1657(new Object[]{var18});
        if (var21 != null && var21.isAlive() && !(b.player.distanceTo(var21) > (Float)this.a.getValue())) {
            if (!this.j_boolean(new Object[]{var21, var8})) {
                this.Y = var21;
                long var22 = this.V;
                VecBoolData2 var24 = this.a_VecBoolData2(new Object[]{var4});
                if (var24 == null) {
                    if (var22 == this.V) {
                        Object[] var10005 = new Object[]{null, null, var14};
                        var10005[1] = var22;
                        var10005[0] = var21;
                        this.v(var10005);
                    }

                    this.Y = null;
                } else {
                    this.w(new Object[]{var6});
                    this.Z = var24.l();
                    this.aa = var24.m();
                    this.ab = var24.n();
                    this.ac = var24.p();
                    this.ad = var24.q();
                    this.ae = var24.r();
                    this.af = this.Y.getBlockPos();
                    if (var24.o()) {
                        int var25 = this.c_int(new Object[]{this.Z, var10});
                        this.C = this.f.getValue() && this.ac != null
                            ? AutoAnchorState.SAFE_COVER
                            : (var25 > 0 ? AutoAnchorState.DETONATE : AutoAnchorState.CHARGE_ANCHOR);
                    } else {
                        this.C = AutoAnchorState.PLACE_ANCHOR;
                    }

                    this.H = 0;
                    this.G.a_void_____________________(new Object[]{var12});
                    this.D.a_void_____________________(new Object[]{var12});
                    this.j.reroll(new Object[]{var16});
                    this.E.a_void_____________________(new Object[]{var12});
                    this.k.reroll(new Object[]{var16});
                    this.F.a_void_____________________(new Object[]{var12});
                    this.l.reroll(new Object[]{var16});
                }
            }
        } else {
            this.w(new Object[]{var6});
        }
    }

    private Vec3d b_class_243_(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 64524393394298L;
        Event.b();
        Vec3d var7 = PositionUtil.a(new Object[]{this.Y, var4});
        Vec3d var8 = this.Y.getVelocity();
        if (var8.lengthSquared() < 1.0E-6) {
            return var7;
        } else {
            int var9 = 0;
            PlayerListEntry var10 = b.player.networkHandler.getPlayerListEntry(this.Y.getUuid());
            if (var10 != null) {
                var9 = Math.max(0, var10.getLatency());
            }

            float var11 = MathHelper.clamp(3.0F + var9 / 50.0F * 1.0F, 0.0F, 20.0F);
            Vec3d var12 = new Vec3d(var8.x * var11, 0.0, var8.z * var11);
            double var13 = 3.0;
            if (var12.lengthSquared() > var13 * var13) {
                var12 = var12.lengthSquared() > 1.0E-6 ? var12.normalize().multiply(var13) : Vec3d.ZERO;
            }

            return var7.add(var12);
        }
    }

    private VecBoolData2 a_VecBoolData2(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 134760382939301L;
        long var6 = var2 ^ 28426246359938L;
        long var8 = var2 ^ 42642442235004L;
        long var10 = var2 ^ 112787538791506L;
        long var12 = var2 ^ 42470544289551L;
        long var14 = var2 ^ 37073073927029L;
        long var16 = var2 ^ 33823999311052L;
        long var18 = var2 ^ 131256388375252L;
        long var20 = var2 ^ 21768429511699L;
        long var22 = var2 ^ 70662803597721L;
        long var24 = var2 ^ 92391049997117L;
        long var26 = var2 ^ 78599337804183L;
        long var28 = var2 ^ 134535939335122L;
        long var30 = var2 ^ 74430976984389L;
        long var32 = var2 ^ 27733170906009L;
        long var34 = var2 ^ 81921558716813L;
        Vec3d var37 = b.player.getEyePos();
        Vec3d var38 = PositionUtil.a(new Object[]{this.Y, var12});
        Event.b();
        Vec3d var39 = this.b_class_243_(new Object[]{var30});
        Vec3d var40 = var39.subtract(var38);
        float var41 = b.player.getHealth() + b.player.getAbsorptionAmount();
        List var42 = this.b_List_(new Object[]{var37, var38, var39, var32});
        var42.sort((lpL59_0, lpL59_1) -> {
            Event.b();
            int var5 = Double.compare(((dev.zprestige.prestige.data.VecBoolIntData)lpL59_1).ad(), ((dev.zprestige.prestige.data.VecBoolIntData)lpL59_0).ad());
            return var5 != 0 ? var5 : Integer.compare(((dev.zprestige.prestige.data.VecBoolIntData)lpL59_0).aa(), ((dev.zprestige.prestige.data.VecBoolIntData)lpL59_1).aa());
        });
        VecBoolData2 var43 = null;
        double var44 = -Double.MAX_VALUE;
        int var46 = Integer.MAX_VALUE;
        Block var47 = ((String)this.g.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE : Blocks.OBSIDIAN;
        BlockState var48 = var47.getDefaultState();
        boolean var49 = InventoryUtil.a_Integer(new Object[]{var47.asItem(), var14}) != null;

        for (VecBoolIntData var51 : (Iterable<VecBoolIntData>)(var42)) {
            if (var43 != null) {
                double var10000 = var51.ad();
                int var10001 = var51.aa();
                Object[] var10006 = new Object[]{null, null, null, null, var10};
                var10006[3] = var46;
                var10006[2] = var44;
                var10006[1] = var10001;
                var10006[0] = var10000;
                if (!f(var10006)) {
                    break;
                }
            }

            Vec3d var52 = var51.s().toCenterPos();
            Vec3d var53 = RaycastUtil.b(new Object[]{var51.s(), var6});
            if (!var51.v() || var53 != null) {
                Vec3d var54 = var53 != null ? var53 : var52.add(0.0, 0.5, 0.0);
                if (!(var37.squaredDistanceTo(var54) > 19.360000000000003)) {
                    if ((Boolean)this.h.getValue()) {
                        RenderBatch var55 = WorldUtil.a_RenderBatch_(new Object[]{var54, var16});
                        float var79 = var55.a(new Object[0]) - b.player.getYaw();
                        Object[] var10003 = new Object[]{null, var28};
                        var10003[0] = var79;
                        if (Math.abs(WorldUtil.b_float_____________________(var10003)) >= 60.0F) {
                            continue;
                        }
                    }

                    double var77 = this.b_double__(new Object[]{var51, var52, var40, var20});
                    if (!(var77 < ((Float)this.c.getValue()).floatValue())) {
                        Object[] var10005 = new Object[]{null, null, null, var8};
                        var10005[2] = 0.0;
                        var10005[1] = var77;
                        var10005[0] = var51;
                        double var57 = c_double__(var10005);
                        if (var43 != null) {
                            int var80 = var51.aa();
                            Object[] var83 = new Object[]{null, null, null, null, var10};
                            var83[3] = var46;
                            var83[2] = var44;
                            var83[1] = var80;
                            var83[0] = var57;
                            if (!f(var83)) {
                                continue;
                            }
                        }

                        double var59 = Prestige.f.k(new Object[]{b.player, var52, var18});
                        if (!(Boolean)this.e.getValue() || !(var59 >= var41 * 2.0F)) {
                            BlockPos var61 = null;
                            BlockPos var62 = null;
                            Vec3d var63 = null;
                            double var64 = var59;
                            if ((Boolean)this.f.getValue() && var49 && var59 > 6.0) {
                                double var66 = var59;

                                for (int[] var71 : A) {
                                    BlockPos var72 = var51.s().add(var71[0], var71[1], var71[2]);
                                    if (!WorldUtil2.e(new Object[]{var72, var22}) && !WorldUtil2.g(new Object[]{var72, var24})) {
                                        VecData2 var73 = this.a_VecData2(new Object[]{var72, var37, var4});
                                        if (var73 != null) {
                                            double var74 = Prestige.f.l(new Object[]{b.player, var52, var72, var48, var34});
                                            if (!(var74 >= var66) && !(var59 - var74 < 2.0)) {
                                                var66 = var74;
                                                var61 = var72;
                                                var62 = var73.ae();
                                                var63 = var73.af();
                                            }
                                        }
                                    }
                                }

                                if (var61 != null) {
                                    var64 = var66;
                                }
                            }

                            if (!(var64 > ((Float)this.d.getValue()).floatValue()) && (!(Boolean)this.e.getValue() || !(var64 >= var41 - 1.0F))) {
                                var10005 = new Object[]{null, null, null, var8};
                                var10005[2] = var64;
                                var10005[1] = var77;
                                var10005[0] = var51;
                                double var78 = c_double__(var10005);
                                int var81 = var51.aa();
                                Object[] var84 = new Object[]{null, null, null, null, var26};
                                var84[3] = var46;
                                var84[2] = var44;
                                var84[1] = var81;
                                var84[0] = var78;
                                if (g(var84)) {
                                    var44 = var78;
                                    var46 = var51.aa();
                                    var43 = new VecBoolData2(var51.s(), var51.t(), var51.u(), var51.v(), var61, var62, var63);
                                }
                            }
                        }
                    }
                }
            }
        }

        return var43;
    }

    private List b_List_(Object[] var1) {
        Vec3d var5 = (Vec3d)var1[0];
        Vec3d var6 = (Vec3d)var1[1];
        Vec3d var2 = (Vec3d)var1[2];
        long var3 = (Long)var1[3];
        var3 = 105273939794480L ^ var3;
        long var7 = var3 ^ 66071742953740L;
        long var9 = var3 ^ 111983794513575L;
        long var11 = var3 ^ 7437428674608L;
        long var13 = var3 ^ 107148944467863L;
        long var15 = var3 ^ 20343812182676L;
        long var17 = var3 ^ 113457489971932L;
        long var19 = var3 ^ 57914704996921L;
        long var21 = var3 ^ 107173927651936L;
        long var23 = var3 ^ 16049262630807L;
        Event.b();
        ArrayList var26 = new ArrayList();
        BlockPos var27 = this.Y.getBlockPos();
        Mutable var28 = new Mutable();
        boolean var29 = InventoryUtil.a_Integer(new Object[]{Items.RESPAWN_ANCHOR, var17}) != null;
        boolean var30 = InventoryUtil.a_Integer(new Object[]{Items.GLOWSTONE, var17}) != null;
        int var31 = 0;
        if (this.d_int_(new Object[]{var9}) == -1) {
            return var26;
        } else {
            for (int var32 = -5; var32 <= 5; var32++) {
                for (int var33 = -1; var33 <= 1; var33++) {
                    for (int var34 = -5; var34 <= 5; var31++) {
                        var28.set(var27.getX() + var32, var27.getY() + var33, var27.getZ() + var34);
                        Vec3d var35 = new Vec3d(var28.getX() + 0.5, var28.getY() + 0.5, var28.getZ() + 0.5);
                        double var36 = var5.squaredDistanceTo(var35);
                        label130:
                        if (!(var36 > 21.159999999999997) && (this.J.isEmpty() || !this.J.containsKey(var28))) {
                            boolean var38 = WorldUtil2.b(new Object[]{var28, Blocks.RESPAWN_ANCHOR, var23});
                            BlockPos var40 = null;
                            Vec3d var41 = null;
                            BlockPos var39;
                            if (var38) {
                                var39 = var28.toImmutable();
                            } else {
                                if (!var29
                                    || !var30
                                    || (Boolean)this.m.getValue()
                                    || WorldUtil2.e(new Object[]{var28, var11})
                                    || WorldUtil2.g(new Object[]{var28, var15})) {
                                    break label130;
                                }

                                var39 = var28.toImmutable();
                                VecData2 var42 = this.a_VecData2(new Object[]{var39, var5, var7});
                                if (var42 == null) {
                                    break label130;
                                }

                                var40 = var42.ae();
                                var41 = var42.af();
                            }

                            int var56 = var38 ? this.c_int(new Object[]{var39, var13}) : 0;
                            if (!var38 || var56 > 0 || var30) {
                                double var43 = a_double__(new Object[]{var6, var35, var19});
                                double var45 = a_double__(new Object[]{var2, var35, var19});
                                double var47 = Math.max(var43, var45);
                                if (!(var47 < ((Float)this.c.getValue()).floatValue())) {
                                    double var49 = Math.sqrt(var36);
                                    boolean var51 = var39.getY() == var27.getY();
                                    boolean var52 = var51 && Math.max(Math.abs(var39.getX() - var27.getX()), Math.abs(var39.getZ() - var27.getZ())) == 1;
                                    Object[] var10009 = new Object[]{null, null, null, null, null, null, null, var21};
                                    var10009[6] = var56;
                                    var10009[5] = var38;
                                    var10009[4] = var52;
                                    var10009[3] = var51;
                                    var10009[2] = var49;
                                    var10009[1] = 0.0;
                                    var10009[0] = var47;
                                    double var53 = d_double(var10009);
                                    var26.add(new VecBoolIntData(var39, var40, var41, var38, var56, var49, var51, var52, var31, var43, var45, var53));
                                }
                            }
                        }

                        var34++;
                    }
                }
            }

            return var26;
        }
    }

    private VecData2 a_VecData2(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        BlockPos var7 = null;
        Vec3d var8 = null;
        double var9 = Double.MAX_VALUE;
        Direction[] var11 = z;
        Event.b();

        for (Direction var14 : var11) {
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

        return var7 == null ? null : new VecData2(var7, var8);
    }

    private static double a_double__(Object[] var0) {
        Vec3d var1 = (Vec3d)var0[0];
        Vec3d var2 = (Vec3d)var0[1];
        Event.b();
        double var6 = var1.distanceTo(var2);
        if (var6 > 10.0) {
            return 0.0;
        } else {
            double var8 = 1.0 - var6 / 10.0;
            double var10 = ((var8 * var8 + var8) * 0.5 * 7.0 * 10.0 + 1.0) * 1.5;
            return Math.nextUp((float)var10);
        }
    }

    private double b_double__(Object[] var1) {
        VecBoolIntData var4 = (VecBoolIntData)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        Vec3d var6 = (Vec3d)var1[2];
        long var2 = (Long)var1[3];
        var2 = 105273939794480L ^ var2;
        long var7 = var2 ^ 64949135895799L;
        if (var6.lengthSquared() < 1.0E-6) {
            return Prestige.f.k(new Object[]{this.Y, var5, var7});
        } else if (var4.ac() > var4.ab()) {
            double var13 = Prestige.f.k(new Object[]{this.Y, var5.subtract(var6), var7});
            return var4.ab() <= var13 ? var13 : Math.max(var13, Prestige.f.k(new Object[]{this.Y, var5, var7}));
        } else {
            double var10 = Prestige.f.k(new Object[]{this.Y, var5, var7});
            return var4.ac() <= var10 ? var10 : Math.max(var10, Prestige.f.k(new Object[]{this.Y, var5.subtract(var6), var7}));
        }
    }

    private static double c_double__(Object[] var0) {
        VecBoolIntData var1 = (VecBoolIntData)var0[0];
        double var2 = (Double)var0[1];
        double var4 = (Double)var0[2];
        long var6 = (Long)var0[3];
        var6 = 105273939794480L ^ var6;
        long var8 = var6 ^ 103938371143045L;
        double var10002 = var1.x();
        boolean var10003 = var1.y();
        boolean var10004 = var1.z();
        boolean var10005 = var1.v();
        int var10006 = var1.w();
        Object[] var10009 = new Object[]{null, null, null, null, null, null, null, var8};
        var10009[6] = var10006;
        var10009[5] = var10005;
        var10009[4] = var10004;
        var10009[3] = var10003;
        var10009[2] = var10002;
        var10009[1] = var4;
        var10009[0] = var2;
        return d_double(var10009);
    }

    private static double d_double(Object[] var0) {
        double var2 = (Double)var0[0];
        double var4 = (Double)var0[1];
        double var6 = (Double)var0[2];
        boolean var1 = (Boolean)var0[3];
        boolean var10 = (Boolean)var0[4];
        boolean var11 = (Boolean)var0[5];
        int var12 = (Integer)var0[6];
        Event.b();
        double var14 = var2 - 0.6 * var4 - 0.15 * var6 - 0.15 * var6 * var6;
        if (var1) {
            var14 += 4.0;
        }

        if (var10) {
            var14 += 3.5;
        }

        if (var11) {
            double var16 = Math.max(0.0, 1.0 - var6 / 4.4);
            var14 += 5.0 * var16;
            if (var12 > 0) {
                var14 += 6.0 * var16;
            }
        }

        return var14;
    }

    private static boolean f(Object[] var0) {
        double var6 = (Double)var0[0];
        int var8 = (Integer)var0[1];
        double var1 = (Double)var0[2];
        int var3 = (Integer)var0[3];
        return var6 > var1 || var6 == var1 && var8 < var3;
    }

    private static boolean g(Object[] var0) {
        double var5 = (Double)var0[0];
        int var8 = (Integer)var0[1];
        double var3 = (Double)var0[2];
        int var7 = (Integer)var0[3];
        return var5 > var3 || var5 == var3 && var8 < var7;
    }

    private int c_int(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        Event.b();
        BlockState var6 = b.world.getBlockState(var4);
        return !var6.isOf(Blocks.RESPAWN_ANCHOR) ? 0 : var6.get(Properties.CHARGES);
    }

    private boolean h(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 133779332337487L;
        return this.Y != null && this.Y.isAlive() && b.player.distanceTo(this.Y) <= 12.0F && Prestige.i.a_boolean_(new Object[]{this.Y, var4});
    }

    private boolean i(Object[] var1) {
        if (this.af != null && this.Y != null) {
            BlockPos var5 = this.Y.getBlockPos();
            int var6 = Math.abs(var5.getX() - this.af.getX());
            int var7 = Math.abs(var5.getY() - this.af.getY());
            int var8 = Math.abs(var5.getZ() - this.af.getZ());
            return Math.max(var6, Math.max(var7, var8)) > 1;
        } else {
            return false;
        }
    }

    private boolean j_boolean(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 105273939794480L ^ var2;
        long var5 = var2 ^ 38580354050388L;
        long var7 = var2 ^ 74007614008979L;
        if (this.V != this.W) {
            return false;
        } else if (this.K < this.M && this.N == var4) {
            if (!b.player.getBlockPos().equals(this.O) || !var4.getBlockPos().equals(this.P)) {
                return false;
            } else if (this.Q != null && b.player.getEyePos().equals(this.Q)) {
                if (this.R == null || !PositionUtil.a(new Object[]{var4, var5}).equals(this.R)) {
                    return false;
                } else if (this.S == null || !var4.getVelocity().equals(this.S)) {
                    return false;
                } else {
                    return Float.compare(b.player.getYaw(), this.T) != 0 ? false : this.U == this.e_int_(new Object[]{var7});
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void v(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        long var5 = (Long)var1[2];
        var5 = 105273939794480L ^ var5;
        long var7 = var5 ^ 131138339059744L;
        long var9 = var5 ^ 25411372908519L;
        this.N = var2;
        this.O = b.player.getBlockPos().toImmutable();
        this.P = var2.getBlockPos().toImmutable();
        this.Q = b.player.getEyePos();
        this.R = PositionUtil.a(new Object[]{var2, var7});
        this.S = var2.getVelocity();
        this.T = b.player.getYaw();
        this.U = this.e_int_(new Object[]{var9});
        this.W = var3;
        this.M = this.K + 3L;
    }

    private int e_int_(Object[] var1) {
        int var5 = Float.floatToIntBits((Float)this.a.getValue());
        var5 = 31 * var5 + Float.floatToIntBits((Float)this.c.getValue());
        var5 = 31 * var5 + Float.floatToIntBits((Float)this.d.getValue());
        var5 = 31 * var5 + Boolean.hashCode((Boolean)this.e.getValue());
        var5 = 31 * var5 + Boolean.hashCode((Boolean)this.f.getValue());
        var5 = 31 * var5 + ((String)this.g.getValue()).hashCode();
        var5 = 31 * var5 + Boolean.hashCode((Boolean)this.h.getValue());
        var5 = 31 * var5 + Boolean.hashCode((Boolean)this.m.getValue());
        Event.b();
        var5 = 31 * var5 + Float.floatToIntBits(b.player.getHealth() + b.player.getAbsorptionAmount());

        for (int var6 = 0; var6 < 9; var6++) {
            ItemStack var7 = b.player.getInventory().getStack(var6);
            var5 = 31 * var5 + var7.getItem().hashCode();
            var5 = 31 * var5 + var7.getCount();
        }

        return var5;
    }

    private void w(Object[] var1) {
        this.M = 0L;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = 0.0F;
        this.U = 0;
        this.W = this.V;
    }

    private void x(Object[] var1) {
        String var2 = (String)var1[0];
        long var3 = (Long)var1[1];
        var3 = 105273939794480L ^ var3;
        long var5 = var3 ^ 62321389419486L;
        long var7 = var3 ^ 9547413765703L;
        long var9 = var3 ^ 105530424264406L;
        long var11 = var3 ^ 116241522774702L;
        RenderHelper27 var10000 = this.at;
        Object[] var10004 = new Object[]{null, var9};
        var10004[0] = 3000.0F;
        if (var10000.a(var10004)) {
            ModuleNotificationsElement.s(new Object[]{this, var2, NotificationType.ERROR, var5});
            this.at.a_void_____________________(new Object[]{var7});
        }

        this.z(new Object[]{var11});
    }

    private void y(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 110750006736215L;
        long var6 = var2 ^ 87405157733285L;
        if (this.ag != -1) {
            if (b.player == null) {
                RenderUtil6.a_void____________________________(new Object[]{this, var4});
                this.ag = -1;
            } else if (!RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                this.ag = -1;
            } else if (!Prestige.o.d_boolean______(new Object[0])) {
                if ((Boolean)this.i.getValue()) {
                    int var10000 = this.ag;
                    Object[] var10003 = new Object[]{null, var6};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                    Prestige.o.g(new Object[]{true});
                }

                RenderUtil6.a_void____________________________(new Object[]{this, var4});
                this.ag = -1;
            }
        }
    }

    private void z(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 68372978806556L;
        long var6 = var2 ^ 98380404554406L;
        this.y(new Object[]{var6});
        this.w(new Object[]{var4});
        this.C = AutoAnchorState.IDLE;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.H = 0;
        this.X = 0;
        B = false;
    }

    private void A(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 105273939794480L ^ var2;
        long var4 = var2 ^ 135590885993591L;
        long var6 = var2 ^ 136160711341490L;
        long var8 = var2 ^ 42513662119453L;
        this.w(new Object[]{var4});
        this.C = AutoAnchorState.IDLE;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = -1;
        this.H = 0;
        this.D.a_void_____________________(new Object[]{var6});
        this.E.a_void_____________________(new Object[]{var6});
        this.F.a_void_____________________(new Object[]{var6});
        this.G.a_void_____________________(new Object[]{var6});
        B = false;
        this.an.b(new Object[]{var8});
        this.ao.b(new Object[]{var8});
        this.ap.b(new Object[]{var8});
        this.aq = null;
        this.ar = null;
        this.as = null;
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
