package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.combat.DamageUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.TickEvent10;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.math.AutoObbyCrystalHelper;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper5;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderUtil6;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.type.CrystalState;
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
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;

public class AutoObbyCrystal extends Module implements RenderContract3 {
    private FloatSetting a;
    private FloatSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private EnumSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private FloatSetting k;
    private RangeSetting l;
    private RangeSetting m;
    private RangeSetting n;
    private IntSetting o;
    private FloatSetting p;
    private BoolSetting q;
    private BoolSetting r;
    private FloatSetting s;
    private EnumSetting t;
    private FloatSetting u;
    private FloatSetting v;
    private FloatSetting w;
    private BoolSetting x;
    private BoolSetting y;
    private BoolSetting z;
    private IntSetting A;
    private BoolSetting B;
    private FloatSetting C;
    private FloatSetting D;
    private FloatSetting E;
    private BoolSetting F;
    private FloatSetting G;
    private FloatSetting H;
    private FloatSetting I;
    private BoolSetting J;
    private BoolSetting K;
    private BoolSetting L;
    private IntSetting M;
    private ColorSetting N;
    private ColorSetting O;
    private ColorSetting P;
    private ColorSetting Q;
    public static boolean R;
    private CrystalState S = CrystalState.IDLE;
    private RenderHelper27 T = new RenderHelper27(26293358099357L);
    private RenderHelper27 U = new RenderHelper27(26293358099357L);
    private RenderHelper27 V = new RenderHelper27(26293358099357L);
    private RenderHelper27 W = new RenderHelper27(26293358099357L);
    private boolean X;
    private boolean Y;
    private PlayerEntity Z;
    private BlockPos aa;
    private BlockPos ab;
    private Direction ac;
    private Vec3d ad;
    private BlockPos ae;
    private int af = -1;
    private boolean ag;
    private boolean ah;
    private int ai = -1;
    private List aj = new ArrayList();
    private Vec3d ak;
    private Entity al;
    private Vec3d am;
    private Vec3d an = Vec3d.ZERO;
    private long ao = Long.MIN_VALUE;
    private static final double ap = 0.5;
    private static final double aq = 0.02;
    private static final double ar = 27.5625;
    private static final float as = 180.0F;
    private static final double at = 900.0;
    private static final float au = 0.6F;
    private static final float av = 0.4F;
    private static final float aw = 0.02F;
    private RenderHelper5 ax = new RenderHelper5(180.0F, false, Easing.SINE_IN_OUT, 88790582046503L);
    private RenderHelper5 ay = new RenderHelper5(180.0F, false, Easing.SINE_IN_OUT, 88790582046503L);
    private LinkedHashMap az = new LinkedHashMap();
    private HashSet aA = new HashSet();
    private BlockPos aB;
    private BlockPos aC;
    private int aD;
    private RenderHelper27 aE = new RenderHelper27(26293358099357L);

    public AutoObbyCrystal() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.k.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL280_0 -> (Boolean)this.j.getValue(), 35046756052036L});
        this.s
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL272_0 -> (Boolean)this.j.getValue() && (Boolean)this.g.getValue(), 35046756052036L});
        this.n.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL277_0 -> (Boolean)this.j.getValue(), 37043306203287L});
        this.B.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL263_0 -> (Boolean)this.j.getValue(), 73196168581295L});
        this.D
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL261_0 -> (Boolean)this.j.getValue() && (Boolean)this.B.getValue(), 35046756052036L});
        this.J
            .setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL255_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue(), 73196168581295L});
        this.I.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL256_0 -> (Boolean)this.j.getValue(), 35046756052036L});
        this.C
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL262_0 -> (Boolean)this.j.getValue() && (Boolean)this.B.getValue(), 35046756052036L});
        this.p.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL275_0 -> (Boolean)this.j.getValue(), 35046756052036L});
        this.q.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL274_0 -> (Boolean)this.j.getValue(), 73196168581295L});
        this.Q
            .setVisibility_ColorSetting(
                new Object[]{
                    (Predicate<Color>)lpL248_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue() && (Boolean)this.L.getValue(), 119980880492450L
                }
            );
        this.O
            .setVisibility_ColorSetting(
                new Object[]{
                    (Predicate<Color>)lpL250_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue() && (Boolean)this.K.getValue(), 119980880492450L
                }
            );
        this.P
            .setVisibility_ColorSetting(
                new Object[]{
                    (Predicate<Color>)lpL249_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue() && (Boolean)this.J.getValue(), 119980880492450L
                }
            );
        this.v.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL269_0 -> (Boolean)this.j.getValue(), 35046756052036L});
        this.E
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL260_0 -> (Boolean)this.j.getValue() && (Boolean)this.B.getValue(), 35046756052036L});
        this.G
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL258_0 -> (Boolean)this.j.getValue() && (Boolean)this.F.getValue(), 35046756052036L});
        this.m.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL278_0 -> (Boolean)this.j.getValue(), 37043306203287L});
        this.w
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL268_0 -> (Boolean)this.j.getValue() && (Float)this.v.getValue() > 0.0F, 35046756052036L}
            );
        this.H
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL257_0 -> (Boolean)this.j.getValue() && (Boolean)this.F.getValue(), 35046756052036L});
        this.o.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL276_0 -> (Boolean)this.j.getValue(), 98265949679296L});
        this.r.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL273_0 -> (Boolean)this.j.getValue(), 73196168581295L});
        this.t.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL271_0 -> (Boolean)this.j.getValue(), 90319707064974L});
        this.z.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL265_0 -> (Boolean)this.j.getValue(), 73196168581295L});
        this.L
            .setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL253_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue(), 73196168581295L});
        this.u
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL270_0 -> (Boolean)this.j.getValue() && !((String)this.t.getValue()).equals("None"), 35046756052036L}
            );
        this.N
            .setVisibility_ColorSetting(
                new Object[]{
                    (Predicate<Color>)lpL251_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue() && (Boolean)this.K.getValue(), 119980880492450L
                }
            );
        this.l.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL279_0 -> (Boolean)this.j.getValue(), 37043306203287L});
        this.M
            .setVisibility_IntSetting(
                new Object[]{
                    (Predicate<Integer>)lpL252_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue() && (Boolean)this.L.getValue(), 98265949679296L
                }
            );
        this.y.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL266_0 -> (Boolean)this.j.getValue(), 73196168581295L});
        this.A.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL264_0 -> (Boolean)this.j.getValue(), 98265949679296L});
        this.x.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL267_0 -> (Boolean)this.j.getValue(), 73196168581295L});
        this.K
            .setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL254_0 -> (Boolean)this.j.getValue() && (Boolean)this.i.getValue(), 73196168581295L});
        this.F.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL259_0 -> (Boolean)this.j.getValue(), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 83517202222702L;
        long var6 = var2 ^ 91082959635357L;
        this.y(new Object[]{var4});
        Prestige.o.e(new Object[]{this, var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 51011343502304L;
        long var6 = var2 ^ 18099804951565L;
        long var8 = var2 ^ 82893747353842L;
        long var10 = var2 ^ 140372736859409L;
        long var12 = var2 ^ 64021474395393L;
        if (this.af != -1 && (Boolean)this.h.getValue() && Prestige.o.d_boolean______(new Object[0])) {
            Renderer2D var10000 = Prestige.o;
            int var10001 = this.af;
            Object[] var10004 = new Object[]{null, var8};
            var10004[0] = var10001;
            var10000.i(var10004);
            RenderUtil6.a_void____________________________(new Object[]{this, var4});
            this.af = -1;
        }

        this.w(new Object[]{var12});
        this.y(new Object[]{var6});
        Prestige.o.f(new Object[]{this, var10});
    }

    @Override
    public int a() {
        return 30;
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.y(new Object[]{61393938998120L});
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (this.S != CrystalState.IDLE) {
            if (!((String)this.f.getValue()).equals("Manual")
                || this.S != CrystalState.BREAK_CRYSTAL
                || !(b.crosshairTarget instanceof EntityHitResult var5 && var5.getEntity() instanceof EndCrystalEntity)) {
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(TickEvent10 var1) {
        if (this.S != CrystalState.IDLE) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.S != CrystalState.IDLE) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE && b.player != null) {
            this.ak = b.player.getEyePos();
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.i.getValue() && b.player != null && b.world != null) {
            float var23 = b_float__________(new Object[]{136649849765492L});
            boolean var24 = (Boolean)this.J.getValue() && this.Z != null;
            if (var24) {
                this.aB = this.Z.getBlockPos();
            }

            RenderHelper5 var10000 = this.ax;
            Object[] var10004 = new Object[]{null, null, 26288240986585L};
            var10004[1] = var24;
            var10004[0] = var10000;
            float var25 = c(var10004);
            if ((Boolean)this.J.getValue() && var25 > 0.01F && this.aB != null) {
                Color var44 = (Color)this.P.getValue();
                Object[] var10005 = new Object[]{null, null, null, 108485859613149L};
                var10005[2] = var23;
                var10005[1] = var25;
                var10005[0] = var44;
                Color var26 = a_Color___(var10005);
                if (var26.getAlpha() > 0) {
                    this.j(new Object[]{var1, this.aB, var26, 33469782502931L});
                    this.j(new Object[]{var1, this.aB.up(), var26, 33469782502931L});
                }
            }

            boolean var36 = (Boolean)this.K.getValue() && this.aa != null;
            if (var36) {
                this.aC = this.aa;
            }

            var10000 = this.ay;
            var10004 = new Object[]{null, null, 26288240986585L};
            var10004[1] = var36;
            var10004[0] = var10000;
            float var27 = c(var10004);
            if ((Boolean)this.K.getValue() && var27 > 0.01F && this.aC != null) {
                boolean var28 = WorldUtil2.b(new Object[]{this.aC, Blocks.OBSIDIAN, 91415361137757L})
                    || WorldUtil2.b(new Object[]{this.aC, Blocks.BEDROCK, 91415361137757L});
                BlockPos var10002 = this.aC;
                Color var10003 = (Color)this.N.getValue();
                Object[] var10008 = new Object[]{null, null, null, 108485859613149L};
                var10008[2] = var23;
                var10008[1] = var27;
                var10008[0] = var10003;
                var10003 = a_Color___(var10008);
                boolean var51 = !var28;
                var10008 = new Object[]{null, null, null, null, null, 40399733295326L};
                var10008[4] = var23;
                var10008[3] = var51;
                var10008[2] = var10003;
                var10008[1] = var10002;
                var10008[0] = var1;
                this.k(var10008);
                var10002 = this.aC.up();
                var10003 = (Color)this.O.getValue();
                var10008 = new Object[]{null, null, null, 108485859613149L};
                var10008[2] = var23;
                var10008[1] = var27;
                var10008[0] = var10003;
                var10003 = a_Color___(var10008);
                var10008 = new Object[]{null, null, null, null, null, 40399733295326L};
                var10008[4] = var23;
                var10008[3] = true;
                var10008[2] = var10003;
                var10008[1] = var10002;
                var10008[0] = var1;
                this.k(var10008);
            }

            this.aA.clear();
            boolean var37 = (Boolean)this.L.getValue();
            if (var37 && this.S == CrystalState.IDLE && !this.aj.isEmpty()) {
                int var29 = Math.min(this.aj.size(), (Integer)this.M.getValue());
                Color var30 = (Color)this.Q.getValue();

                for (int var31 = 0; var31 < var29; var31++) {
                    BlockPos var32 = ((AutoObbyCrystalHelper)this.aj.get(var31)).a.up();
                    this.aA.add(var32);
                    RenderHelper5 var33 = (RenderHelper5)this.az.computeIfAbsent(var32, lpL247_0 -> new RenderHelper5(180.0F, false, Easing.SINE_IN_OUT, 88790582046503L));
                    if (!var33.a(new Object[0])) {
                        var10004 = new Object[]{null, 134514778598418L};
                        var10004[0] = true;
                        var33.a_void_______________________(var10004);
                    }

                    int var34 = Math.max(30, var30.getAlpha() - var30.getAlpha() * var31 / Math.max(1, var29));
                    float var10001 = var33.a_float___________________(new Object[]{86379377865377L});
                    Object[] var54 = new Object[]{null, null, null, 98172499446587L};
                    var54[2] = var23;
                    var54[1] = var10001;
                    var54[0] = var34;
                    int var35 = a_int_________(var54);
                    if (var35 > 0) {
                        this.j(new Object[]{var1, var32, new Color(var30.getRed(), var30.getGreen(), var30.getBlue(), var35), 33469782502931L});
                    }
                }
            }

            Iterator var38 = this.az.entrySet().iterator();

            while (var38.hasNext()) {
                Entry var39 = (Entry)var38.next();
                if (!this.aA.contains(var39.getKey())) {
                    RenderHelper5 var40 = (RenderHelper5)var39.getValue();
                    if (var40.a(new Object[0])) {
                        var10004 = new Object[]{null, 134514778598418L};
                        var10004[0] = false;
                        var40.a_void_______________________(var10004);
                    }

                    float var41 = var40.a_float___________________(new Object[]{86379377865377L});
                    if (var41 < 0.01F) {
                        var38.remove();
                    } else if (var37) {
                        Color var42 = (Color)this.Q.getValue();
                        Object[] var55 = new Object[]{null, null, null, 98172499446587L};
                        var55[2] = var23;
                        var55[1] = var41;
                        var55[0] = 30;
                        int var43 = a_int_________(var55);
                        if (var43 > 0) {
                            this.j(
                                new Object[]{
                                    var1, (BlockPos)var39.getKey(), new Color(var42.getRed(), var42.getGreen(), var42.getBlue(), var43), 33469782502931L
                                }
                            );
                        }
                    }
                }
            }
        }
    }

    private static float b_float__________(Object[] var0) {
        return 0.5F + 0.5F * (float)Math.sin(System.currentTimeMillis() / 900.0 * (Math.PI * 2));
    }

    private static float c(Object[] var0) {
        RenderHelper5 var4 = (RenderHelper5)var0[0];
        boolean var3 = (Boolean)var0[1];
        long var1 = (Long)var0[2];
        var1 = 45124953315131L ^ var1;
        long var5 = var1 ^ 75548005790448L;
        long var7 = var1 ^ 123613616680003L;
        if (var4.a(new Object[0]) != var3) {
            Object[] var10004 = new Object[]{null, var5};
            var10004[0] = var3;
            var4.a_void_______________________(var10004);
        }

        return var4.a_float___________________(new Object[]{var7});
    }

    private static int a_int_________(Object[] var0) {
        int var1 = (Integer)var0[0];
        float var5 = (Float)var0[1];
        float var2 = (Float)var0[2];
        int var6 = Math.round(var1 * var5 * (0.6F + 0.4F * var2));
        return Math.max(0, Math.min(255, var6));
    }

    private static Color a_Color___(Object[] var0) {
        Color var3 = (Color)var0[0];
        float var1 = (Float)var0[1];
        float var2 = (Float)var0[2];
        long var4 = (Long)var0[3];
        var4 = 45124953315131L ^ var4;
        long var6 = var4 ^ 20792960402909L;
        int var10002 = var3.getRed();
        int var10003 = var3.getGreen();
        int var10004 = var3.getBlue();
        int var10005 = var3.getAlpha();
        Object[] var10010 = new Object[]{null, null, null, var6};
        var10010[2] = var2;
        var10010[1] = var1;
        var10010[0] = var10005;
        return new Color(var10002, var10003, var10004, a_int_________(var10010));
    }

    private void j(Object[] var1) {
        TickEvent27 var4 = (TickEvent27)var1[0];
        BlockPos var2 = (BlockPos)var1[1];
        Color var3 = (Color)var1[2];
        long var5 = (Long)var1[3];
        var5 = 45124953315131L ^ var5;
        long var7 = var5 ^ 100348188181670L;
        MatrixCameraData var10000 = var4.b;
        RenderHelper8 var10001 = var4.a;
        float var10002 = var2.getX();
        float var10003 = var2.getY();
        float var10004 = var2.getZ();
        float var10005 = var2.getX() + 1.0F;
        float var10006 = var2.getY() + 1.0F;
        float var10007 = var2.getZ() + 1.0F;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var3, var7};
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

    private void k(Object[] var1) {
        TickEvent27 var8 = (TickEvent27)var1[0];
        BlockPos var7 = (BlockPos)var1[1];
        Color var2 = (Color)var1[2];
        boolean var3 = (Boolean)var1[3];
        float var6 = (Float)var1[4];
        long var4 = (Long)var1[5];
        var4 = 45124953315131L ^ var4;
        long var9 = var4 ^ 122185494010108L;
        long var11 = var4 ^ 21736719364598L;
        if (var3) {
            Color var14 = new Color(var2.getRed(), var2.getGreen(), var2.getBlue(), Math.max(1, var2.getAlpha() / 4));
            float var15 = 0.01F * var6;
            MatrixCameraData var10000 = var8.b;
            RenderHelper8 var10001 = var8.a;
            float var10002 = var7.getX() - var15;
            float var10003 = var7.getY() - var15;
            float var10004 = var7.getZ() - var15;
            float var10005 = var7.getX() + 1.0F + var15;
            float var10006 = var7.getY() + 1.0F + var15;
            float var10007 = var7.getZ() + 1.0F + var15;
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var14, var9};
            var10011[7] = var10007;
            var10011[6] = var10006;
            var10011[5] = var10005;
            var10011[4] = var10004;
            var10011[3] = var10003;
            var10011[2] = var10002;
            var10011[1] = var10001;
            var10011[0] = var10000;
            RenderState.h(var10011);
        }

        this.j(new Object[]{var8, var7, var2, var11});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player == null || b.world == null || b.currentScreen != null || !b.isWindowFocused() || b.player.isUsingItem()) {
            R = false;
        } else if (AutoHitCrystal.a) {
            if (this.S != CrystalState.IDLE) {
                this.v(new Object[]{"AutoHitCrystal enabled", 52124688770449L});
            }

            this.w(new Object[]{32549319504484L});
            R = false;
        } else {
            this.l(new Object[]{this.Z != null ? this.Z : CombatUtil.a_class_1657(new Object[]{16945855905304L}), 11132492658288L});
            if (this.S != CrystalState.IDLE && !this.m_boolean(new Object[]{109563178542307L})) {
                this.v(new Object[]{"invalid target", 52124688770449L});
            }

            if (this.S != CrystalState.IDLE && this.n_boolean(new Object[]{79048407270062L})) {
                this.v(new Object[]{"targetMoved d=" + this.aD, 52124688770449L});
            }

            if (this.S != CrystalState.IDLE) {
                RenderHelper27 var10000 = this.V;
                float var10001 = this.d_float_(new Object[]{28418962328853L});
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = var10001;
                if (var10000.a(var10004)) {
                    if (this.S == CrystalState.BREAK_CRYSTAL && !this.ag) {
                        this.ag = true;
                        this.S = CrystalState.PLACE_CRYSTAL;
                        this.V.a_void_____________________(new Object[]{93540861878173L});
                    } else {
                        this.v(new Object[]{"stageTimeout " + (int)this.d_float_(new Object[]{28418962328853L}) + "ms", 52124688770449L});
                    }
                }
            }

            if (this.S == CrystalState.IDLE) {
                this.w(new Object[]{32549319504484L});
                this.t(new Object[]{31110594063981L});
            } else if (this.S == CrystalState.BREAK_CRYSTAL && ((String)this.f.getValue()).equals("Manual")) {
                if (this.aa != null && !this.g(new Object[]{this.aa.up(), 102069325938248L}) && this.U.b(new Object[]{this.m, 89714527855213L})) {
                    this.S = CrystalState.PLACE_CRYSTAL;
                    this.V.a_void_____________________(new Object[]{93540861878173L});
                }
            } else if (!(Boolean)this.g.getValue()) {
                if (Prestige.o.d_boolean______(new Object[0])) {
                    return;
                }

                this.m(new Object[]{67308089662998L});
            }

            R = this.S != CrystalState.IDLE;
        }
    }

    private void l(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 45124953315131L ^ var2;
        long var5 = var2 ^ 33598012771367L;
        Event.b();
        long var8 = b.world.getTime();
        if (var4 == null) {
            this.al = null;
            this.am = null;
            this.an = Vec3d.ZERO;
            this.ao = var8;
        } else {
            Vec3d var10 = PositionUtil.a(new Object[]{var4, var5});
            if (var4 == this.al && this.am != null && var8 - this.ao == 1L) {
                this.an = this.an.multiply(0.5).add(var10.subtract(this.am).multiply(0.5));
                this.am = var10;
                this.ao = var8;
            } else {
                this.al = var4;
                this.am = var10;
                this.ao = var8;
                this.an = Vec3d.ZERO;
            }
        }
    }

    private Vec3d a_class_243_______(Object[] var1) {
        return this.Z != null && this.al == this.Z ? this.an : Vec3d.ZERO;
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 46474961780868L;
        Event.b();
        Vec3d var7 = this.a_class_243_______(new Object[]{var4});
        return var7.x * var7.x + var7.z * var7.z > 4.0E-4;
    }

    private double a_double___(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        Vec3d var2 = (Vec3d)var1[1];
        BlockPos var3 = (BlockPos)var1[2];
        long var7 = (Long)var1[4];
        var7 = 45124953315131L ^ var7;
        long var9 = var7 ^ 112391609462524L;
        return Prestige.f.d(new Object[]{this.Z, var4, var2, var3, var9});
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 7477342050734L;
        long var6 = var2 ^ 8737832352967L;
        long var8 = var2 ^ 106526848059944L;
        long var10 = var2 ^ 51598816251400L;
        long var12 = var2 ^ 46249515484311L;
        long var14 = var2 ^ 101816702374277L;
        long var16 = var2 ^ 79748765915392L;
        long var18 = var2 ^ 46342348534105L;
        if (!(Boolean)this.g.getValue()) {
            return null;
        } else if (b.player == null || b.world == null || b.currentScreen != null || !b.isWindowFocused() || b.player.isUsingItem()) {
            return null;
        } else if (this.S == CrystalState.IDLE || this.aa == null || this.Z == null) {
            return null;
        } else if (AutoHitCrystal.a) {
            return null;
        } else if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else if (!this.e(new Object[]{var18})) {
            return null;
        } else {
            Vec3d var21 = this.b_class_243__(new Object[]{var6});
            if (this.a_boolean______________(new Object[]{var21, var16})) {
                this.v(new Object[]{"unreachable", var8});
                return null;
            } else if (var21 == null) {
                return null;
            } else {
                RenderBatch var22 = Prestige.o.a_RenderBatch(new Object[]{var21, var14});
                var22 = WorldUtil.d_RenderBatch(new Object[]{var22, var21, var4});
                float var10000 = var22.a(new Object[0]) - b.player.getYaw();
                Object[] var10003 = new Object[]{null, var10};
                var10003[0] = var10000;
                if (Math.abs(WorldUtil.b_float_____________________(var10003)) > (Float)this.s.getValue()) {
                    this.v(new Object[]{"silentAimFov", var8});
                    return null;
                } else if (var22.a_boolean________________________________________(new Object[0])) {
                    return var22;
                } else {
                    this.n(new Object[]{var22, var12});
                    return this.S == CrystalState.IDLE ? null : var22;
                }
            }
        }
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 101455617595987L;
        long var6 = var2 ^ 65236855467196L;
        long var8 = var2 ^ 124965707424259L;
        long var10 = var2 ^ 19537971689346L;
        long var12 = var2 ^ 59697553399042L;
        long var14 = var2 ^ 21098518658964L;
        long var16 = var2 ^ 124873998358477L;
        if (this.e(new Object[]{var16})) {
            Vec3d var19 = this.b_class_243__(new Object[]{var4});
            if (this.a_boolean______________(new Object[]{var19, var14})) {
                this.v(new Object[]{"unreachable", var6});
            } else if (var19 != null) {
                RenderBatch var20 = WorldUtil.a_RenderBatch_(new Object[]{var19, var10});
                WorldUtil.a_void________________________________(new Object[]{var20, var12});
                this.n(new Object[]{var20, var8});
            }
        }
    }

    private boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 32149273471926L;
        if (!this.X) {
            return this.S == CrystalState.BREAK_CRYSTAL && ((String)this.f.getValue()).equals("Manual")
                ? false
                : this.S != CrystalState.IDLE && this.W.b(new Object[]{this.n, var4});
        } else {
            return switch (this.S) {
                case PLACE_OBSIDIAN -> true;
                case PLACE_CRYSTAL -> this.Y || this.T.b(new Object[]{this.l, var4});
                case BREAK_CRYSTAL -> !((String)this.f.getValue()).equals("Manual") && this.U.b(new Object[]{this.m, var4});
                default -> false;
            };
        }
    }

    private Vec3d b_class_243__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 6165857182196L;
        long var6 = var2 ^ 8546064216336L;
        long var8 = var2 ^ 52627988679278L;
        switch (this.S) {
            case PLACE_OBSIDIAN:
                return this.ad;
            case PLACE_CRYSTAL:
                return RaycastUtil.a(new Object[]{this.aa, var4});
            case BREAK_CRYSTAL:
                EndCrystalEntity var11 = this.a_class_1511(new Object[]{var6});
                return var11 == null ? null : this.c_class_243(new Object[]{var11, var8});
            default:
                return null;
        }
    }

    private boolean a_boolean______________(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        if (this.S == CrystalState.BREAK_CRYSTAL) {
            return this.ah;
        } else {
            return var2 == null ? false : b.player.getEyePos().distanceTo(var2) > 4.4 || this.ak != null && this.ak.distanceTo(var2) > 4.4;
        }
    }

    private void n(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 45124953315131L ^ var3;
        long var5 = var3 ^ 106168605865956L;
        long var7 = var3 ^ 37748955130043L;
        long var9 = var3 ^ 90980105776041L;
        switch (this.S) {
            case PLACE_OBSIDIAN:
                this.o(new Object[]{var2, var5});
                break;
            case PLACE_CRYSTAL:
                this.p(new Object[]{var2, var7});
                break;
            case BREAK_CRYSTAL:
                this.q(new Object[]{var2, var9});
        }
    }

    private Vec3d c_class_243(Object[] var1) {
        EndCrystalEntity var4 = (EndCrystalEntity)var1[0];
        Vec3d var6 = b.player.getEyePos();
        Event.b();
        Box var7 = var4.getBoundingBox();
        Vec3d var8 = new Vec3d(
            MathHelper.clamp(var6.x, var7.minX, var7.maxX), MathHelper.clamp(var6.y, var7.minY, var7.maxY), MathHelper.clamp(var6.z, var7.minZ, var7.maxZ)
        );
        Vec3d var9 = var7.getCenter().subtract(var8);
        return var9.lengthSquared() > 1.0E-6 ? var8.add(var9.normalize().multiply(0.15)) : var8;
    }

    private boolean f(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        var3 = 45124953315131L ^ var3;
        long var5 = var3 ^ 107529132927439L;
        long var7 = var3 ^ 25138631126053L;
        long var9 = var3 ^ 86798592053750L;
        if (InventoryUtil.a_int_____(new Object[]{var9}) == var2) {
            return true;
        } else {
            Object[] var10003 = new Object[]{null, var7};
            var10003[0] = var2;
            InventoryUtil.a_void________(var10003);
            if ((Boolean)this.r.getValue()) {
                return true;
            } else {
                Prestige.o.g(new Object[]{true});
                this.V.a_void_____________________(new Object[]{var5});
                return false;
            }
        }
    }

    private void o(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 45124953315131L ^ var2;
        long var5 = var2 ^ 73268923291484L;
        long var7 = var2 ^ 35906716478960L;
        long var9 = var2 ^ 87989407318871L;
        long var11 = var2 ^ 103812236113712L;
        long var13 = var2 ^ 69928029564380L;
        long var15 = var2 ^ 46719396542811L;
        long var17 = var2 ^ 94771685535895L;
        long var19 = var2 ^ 28498724937012L;
        long var21 = var2 ^ 109741077556419L;
        long var23 = var2 ^ 27216306732451L;
        long var25 = var2 ^ 100779821467807L;
        if (!WorldUtil2.b(new Object[]{this.aa, Blocks.OBSIDIAN, var17}) && !WorldUtil2.b(new Object[]{this.aa, Blocks.BEDROCK, var17})) {
            if (!WorldUtil2.e(new Object[]{this.aa, var11})) {
                BlockPos var10001 = this.aa;
                Object[] var10005 = new Object[]{null, null, var19};
                var10005[1] = 1;
                var10005[0] = var10001;
                if (!this.h(var10005)) {
                    Integer var28 = InventoryUtil.a_Integer(new Object[]{Items.OBSIDIAN, var13});
                    if (var28 == null) {
                        this.u(new Object[]{"No obsidian in hotbar.", var5});
                        return;
                    }

                    if (WorldUtil.a_class_239_(new Object[]{var4, var7}) instanceof BlockHitResult var30
                        && var30.getBlockPos().equals(this.ab)
                        && var30.getBlockPos().offset(var30.getSide()).equals(this.aa)) {
                        if (this.af == -1) {
                            this.af = RenderUtil6.a(new Object[]{this, var21});
                        }

                        int var32 = var28;
                        Object[] var10004 = new Object[]{null, var23};
                        var10004[0] = var32;
                        if (!this.f(var10004)) {
                            return;
                        }

                        this.r(new Object[]{var30, var25});
                        this.Y = true;
                        this.S = CrystalState.PLACE_CRYSTAL;
                        this.V.a_void_____________________(new Object[]{var9});
                        return;
                    }

                    return;
                }
            }

            this.v(new Object[]{"base blocked", var15});
        } else {
            this.S = CrystalState.PLACE_CRYSTAL;
            this.V.a_void_____________________(new Object[]{var9});
        }
    }

    private void p(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 45124953315131L ^ var3;
        long var5 = var3 ^ 520012105731L;
        long var7 = var3 ^ 29802818600413L;
        long var9 = var3 ^ 20740167982088L;
        long var11 = var3 ^ 137727527547523L;
        long var13 = var3 ^ 115070328546820L;
        long var15 = var3 ^ 23002026667976L;
        long var17 = var3 ^ 95554552046236L;
        long var19 = var3 ^ 127703996697972L;
        long var21 = var3 ^ 100266873848427L;
        long var23 = var3 ^ 42873406710726L;
        long var25 = var3 ^ 99070287618812L;
        long var27 = var3 ^ 108243847410351L;
        long var29 = var3 ^ 7849217716794L;
        long var31 = var3 ^ 59580166537323L;
        long var33 = var3 ^ 36375268668316L;
        long var35 = var3 ^ 27981484644288L;
        if (WorldUtil2.b(new Object[]{this.aa, Blocks.OBSIDIAN, var15}) || WorldUtil2.b(new Object[]{this.aa, Blocks.BEDROCK, var15})) {
            BlockPos var38 = this.aa.up();
            if (this.g(new Object[]{var38, var7})) {
                this.S = CrystalState.BREAK_CRYSTAL;
                this.V.a_void_____________________(new Object[]{var9});
            } else {
                if (b.world.getBlockState(var38).isAir()) {
                    Object[] var10005 = new Object[]{null, null, var21};
                    var10005[1] = 2;
                    var10005[0] = var38;
                    if (!this.h(var10005)) {
                        Vec3d var39 = new Vec3d(this.aa.getX() + 0.5, this.aa.getY() + 1.0, this.aa.getZ() + 0.5);
                        DamageUtil var10000 = Prestige.f;
                        PlayerEntity var10001 = this.Z;
                        BlockPos var10004 = this.aa;
                        Object[] var10008 = new Object[]{null, null, null, null, null, var23};
                        var10008[4] = false;
                        var10008[3] = var10004;
                        var10008[2] = false;
                        var10008[1] = var39;
                        var10008[0] = var10001;
                        double var40 = var10000.b(var10008);
                        double var50;
                        if ((Boolean)this.B.getValue() && this.d(new Object[]{var31})) {
                            Vec3d var10003 = this.d_class_243(new Object[]{var17});
                            var10004 = this.aa;
                            var10008 = new Object[]{null, null, null, null, var29};
                            var10008[3] = var40;
                            var10008[2] = var10004;
                            var10008[1] = var10003;
                            var10008[0] = var39;
                            var50 = Math.max(var40, this.a_double___(var10008));
                        } else {
                            var50 = var40;
                        }

                        double var42 = var50;
                        var10000 = Prestige.f;
                        ClientPlayerEntity var52 = b.player;
                        var10004 = this.aa;
                        var10008 = new Object[]{null, null, null, null, null, var23};
                        var10008[4] = false;
                        var10008[3] = var10004;
                        var10008[2] = false;
                        var10008[1] = var39;
                        var10008[0] = var52;
                        double var44 = var10000.b(var10008);
                        if (var42 < (Float)this.c.getValue() * (Float)this.p.getValue()) {
                            this.v(new Object[]{"re-gate targetDamage " + String.format("%.1f", var42), var13});
                            return;
                        }

                        if (!(var44 > ((Float)this.d.getValue()).floatValue())
                            && (!(Boolean)this.e.getValue() || !(var44 >= b.player.getHealth() + b.player.getAbsorptionAmount() - 1.0F))) {
                            Integer var46 = InventoryUtil.a_Integer(new Object[]{Items.END_CRYSTAL, var11});
                            if (var46 == null) {
                                this.u(new Object[]{"No end crystals in hotbar.", var5});
                                return;
                            }

                            if (WorldUtil.a_class_239_(new Object[]{var2, var27}) instanceof BlockHitResult var48 && var48.getBlockPos().equals(this.aa)) {
                                if (this.af == -1) {
                                    this.af = RenderUtil6.a(new Object[]{this, var33});
                                }

                                int var53 = var46;
                                Object[] var56 = new Object[]{null, var25};
                                var56[0] = var53;
                                if (!this.f(var56)) {
                                    return;
                                }

                                this.r(new Object[]{var48, var35});
                                this.U.a_void_____________________(new Object[]{var9});
                                this.m.reroll(new Object[]{var19});
                                this.Y = false;
                                this.S = CrystalState.BREAK_CRYSTAL;
                                this.V.a_void_____________________(new Object[]{var9});
                                return;
                            }

                            return;
                        }

                        this.v(new Object[]{"re-gate selfDamage " + String.format("%.1f", var44), var13});
                        return;
                    }
                }

                this.v(new Object[]{"crystal spawn blocked", var13});
            }
        }
    }

    private void q(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 45124953315131L ^ var2;
        long var5 = var2 ^ 129842414531422L;
        long var7 = var2 ^ 107988827578138L;
        long var9 = var2 ^ 89693542184402L;
        long var11 = var2 ^ 23253009423088L;
        long var13 = var2 ^ 5285203758694L;
        long var15 = var2 ^ 94651813994708L;
        long var17 = var2 ^ 84706809833251L;
        long var19 = var2 ^ 17183915777967L;
        Event.b();
        EndCrystalEntity var22 = this.a_class_1511(new Object[]{var9});
        if (var22 != null) {
            Box var23 = var22.getBoundingBox();
            if (this.l_boolean(new Object[]{var23, b.player.getEyePos(), var4, var5})
                && (this.ak == null || this.l_boolean(new Object[]{var23, this.ak, var4, var5}))) {
                Vec3d var24 = new Vec3d(this.aa.getX() + 0.5, this.aa.getY() + 1.0, this.aa.getZ() + 0.5);
                DamageUtil var10000 = Prestige.f;
                ClientPlayerEntity var10001 = b.player;
                BlockPos var10004 = this.aa;
                Object[] var10008 = new Object[]{null, null, null, null, null, var15};
                var10008[4] = false;
                var10008[3] = var10004;
                var10008[2] = false;
                var10008[1] = var24;
                var10008[0] = var10001;
                double var25 = var10000.b(var10008);
                if (!(var25 > ((Float)this.d.getValue()).floatValue())
                    && (!(Boolean)this.e.getValue() || !(var25 >= b.player.getHealth() + b.player.getAbsorptionAmount() - 1.0F))) {
                    if (!AutoCrystal.a || !(b.crosshairTarget instanceof EntityHitResult var27 && var27.getEntity() == var22)) {
                        if (((String)this.f.getValue()).equals("SwapAttack") && this.af != -1 && InventoryUtil.a_int_____(new Object[]{var17}) != this.af) {
                            int var30 = this.af;
                            Object[] var10003 = new Object[]{null, var11};
                            var10003[0] = var30;
                            InventoryUtil.a_void________(var10003);
                            Prestige.o.g(new Object[]{true});
                            this.V.a_void_____________________(new Object[]{var7});
                        } else {
                            this.s(new Object[]{var22, var19});
                            this.ai = var22.getId();
                            this.T.a_void_____________________(new Object[]{var7});
                            this.l.reroll(new Object[]{var13});
                            this.ag = false;
                            this.S = CrystalState.PLACE_CRYSTAL;
                            this.V.a_void_____________________(new Object[]{var7});
                        }
                    }
                }
            }
        }
    }

    private void r(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        boolean var5 = b.interactionManager.interactBlock(b.player, Hand.MAIN_HAND, var2).isAccepted();
        Prestige.o.g(new Object[]{true});
        this.X = true;
        if (var5) {
            b.player.swingHand(Hand.MAIN_HAND);
        }
    }

    private void s(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        b.getNetworkHandler().sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        b.interactionManager.attackEntity(b.player, var2);
        b.player.swingHand(Hand.MAIN_HAND);
        b.getNetworkHandler().sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        Prestige.o.g(new Object[]{true});
        this.X = true;
    }

    private boolean g(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        if (this.ai != -1 && b.world.getEntityById(this.ai) == null) {
            this.ai = -1;
        }

        Box var6 = new Box(var4.getX(), var4.getY(), var4.getZ(), var4.getX() + 1.0, var4.getY() + 1.0, var4.getZ() + 1.0);
        return !b.world.getOtherEntities(null, var6, lpL246_0 -> ((net.minecraft.entity.Entity)lpL246_0) instanceof EndCrystalEntity && ((net.minecraft.entity.Entity)lpL246_0).getId() != this.ai).isEmpty();
    }

    private boolean h(Object[] var1) {
        BlockPos var3 = (BlockPos)var1[0];
        int var2 = (Integer)var1[1];
        Event.b();
        Box var7 = new Box(var3.getX(), var3.getY(), var3.getZ(), var3.getX() + 1.0, var3.getY() + var2, var3.getZ() + 1.0);
        return !b.world
            .getOtherEntities(
                null, var7, lpL245_0 -> ((net.minecraft.entity.Entity)lpL245_0) instanceof ItemEntity ? false : !(((net.minecraft.entity.Entity)lpL245_0) instanceof EndCrystalEntity) || ((net.minecraft.entity.Entity)lpL245_0).getId() != this.ai
            )
            .isEmpty();
    }

    private boolean i(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        int var3 = (Integer)var1[1];
        long var4 = (Long)var1[2];
        var4 = 45124953315131L ^ var4;
        long var6 = var4 ^ 23829242195720L;
        long var8 = var4 ^ 136218025999031L;
        Object[] var10005 = new Object[]{null, null, var6};
        var10005[1] = var3;
        var10005[0] = var2;
        if (this.h(var10005)) {
            return true;
        } else {
            Box var11 = new Box(var2.getX(), var2.getY(), var2.getZ(), var2.getX() + 1.0, var2.getY() + var3, var2.getZ() + 1.0);
            int var12 = (Integer)this.A.getValue();
            if (var12 <= 0) {
                return false;
            } else {
                if (this.Z != null) {
                    Vec3d var13 = this.a_class_243_______(new Object[]{var8});
                    if (var13.lengthSquared() > 1.0E-6) {
                        Box var14 = this.Z.getBoundingBox();

                        for (int var15 = 1; var15 <= var12; var15++) {
                            if (var14.offset(var13.multiply(var15)).intersects(var11)) {
                                return true;
                            }
                        }
                    }
                }

                Vec3d var17 = b.player.getVelocity();
                if (var17.lengthSquared() > 1.0E-6) {
                    Box var18 = b.player.getBoundingBox();

                    for (int var19 = 1; var19 <= var12; var19++) {
                        if (var18.offset(var17.multiply(var19)).intersects(var11)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    private void t(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 105906022934219L;
        long var6 = var2 ^ 81798887209688L;
        long var8 = var2 ^ 7334728578999L;
        long var10 = var2 ^ 63945696134990L;
        Event.b();
        PlayerEntity var13 = CombatUtil.a_class_1657(new Object[]{var10});
        if (var13 != null && var13.isAlive() && !(b.player.distanceTo(var13) > (Float)this.a.getValue())) {
            this.Z = var13;
            AutoObbyCrystalHelper var14 = this.a_AutoObbyCrystalHelper(new Object[]{var6});
            if (var14 == null) {
                this.Z = null;
            } else {
                this.aa = var14.a;
                this.ab = var14.b;
                this.ac = var14.c;
                this.ad = var14.d;
                this.ae = this.Z.getBlockPos();
                this.ag = false;
                this.S = var14.f ? CrystalState.BREAK_CRYSTAL : (var14.e ? CrystalState.PLACE_OBSIDIAN : CrystalState.PLACE_CRYSTAL);
                this.V.a_void_____________________(new Object[]{var4});
                this.X = false;
                this.Y = false;
                this.W.a_void_____________________(new Object[]{var4});
                this.n.reroll(new Object[]{var8});
                this.T.a_void_____________________(new Object[]{var4});
                this.U.a_void_____________________(new Object[]{var4});
                this.l.reroll(new Object[]{var8});
                this.m.reroll(new Object[]{var8});
            }
        }
    }

    private Vec3d d_class_243(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 6281414384478L;
        long var6 = var2 ^ 82037534794355L;
        Event.b();
        Vec3d var9 = PositionUtil.a(new Object[]{this.Z, var4});
        if (!(Boolean)this.B.getValue()) {
            return var9;
        } else {
            Vec3d var10 = this.a_class_243_______(new Object[]{var6});
            if (var10.lengthSquared() < 1.0E-6) {
                return var9;
            } else {
                int var11 = 0;
                PlayerListEntry var12 = b.player.networkHandler.getPlayerListEntry(this.Z.getUuid());
                if (var12 != null) {
                    var11 = Math.max(0, var12.getLatency());
                }

                float var13 = MathHelper.clamp((Float)this.C.getValue() + var11 / 50.0F * (Float)this.D.getValue(), 0.0F, 20.0F);
                Vec3d var14 = new Vec3d(var10.x * var13, 0.0, var10.z * var13);
                double var15 = ((Float)this.E.getValue()).floatValue();
                if (var14.lengthSquared() > var15 * var15) {
                    var14 = var14.lengthSquared() > 1.0E-6 ? var14.normalize().multiply(var15) : Vec3d.ZERO;
                }

                return var9.add(var14);
            }
        }
    }

    private Vec3d e_class_243(Object[] var1) {
        Event.b();
        Vec3d var5 = b.player.getEyePos();
        if (!(Boolean)this.F.getValue()) {
            return var5;
        } else {
            Vec3d var6 = b.player.getVelocity();
            if (var6.lengthSquared() < 1.0E-6) {
                return var5;
            } else {
                Vec3d var7 = new Vec3d(var6.x * ((Float)this.G.getValue()).floatValue(), 0.0, var6.z * ((Float)this.G.getValue()).floatValue());
                double var8 = ((Float)this.H.getValue()).floatValue();
                if (var7.lengthSquared() > var8 * var8) {
                    var7 = var7.lengthSquared() > 1.0E-6 ? var7.normalize().multiply(var8) : Vec3d.ZERO;
                }

                return var5.add(var7);
            }
        }
    }

    private double b_double___(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        long var2 = (Long)var1[1];
        var2 = 45124953315131L ^ var2;
        long var5 = var2 ^ 4591434114501L;
        long var7 = var2 ^ 130132202306120L;
        Event.b();
        RenderBatch var10 = Prestige.o.a_RenderBatch(new Object[]{var4, var7});
        if (var10 == null) {
            return -1.0;
        } else {
            float var10000 = var10.a(new Object[0]) - b.player.getYaw();
            Object[] var10003 = new Object[]{null, var5};
            var10003[0] = var10000;
            return Math.abs(WorldUtil.b_float_____________________(var10003));
        }
    }

    private AutoObbyCrystalHelper a_AutoObbyCrystalHelper(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 12072811178749L;
        long var6 = var2 ^ 55831202984196L;
        long var8 = var2 ^ 81357791168497L;
        long var10 = var2 ^ 78107987211708L;
        long var12 = var2 ^ 18288312931347L;
        long var14 = var2 ^ 5542560250088L;
        long var16 = var2 ^ 130251590430072L;
        long var18 = var2 ^ 57452831495421L;
        long var20 = var2 ^ 60609904784614L;
        long var22 = var2 ^ 25024092185882L;
        long var24 = var2 ^ 122208771214737L;
        long var26 = var2 ^ 43782885922635L;
        long var28 = var2 ^ 14641837637455L;
        long var30 = var2 ^ 29059984279546L;
        long var32 = var2 ^ 60788552692996L;
        long var34 = var2 ^ 76135634004617L;
        Event.b();
        BlockPos var37 = this.Z.getBlockPos();
        Vec3d var38 = this.d_class_243(new Object[]{var10});
        Vec3d var39 = b.player.getEyePos();
        Vec3d var40 = this.e_class_243(new Object[]{var12});
        Vec3d var41 = this.f_class_243(new Object[]{var38, var18});
        float var42 = b.player.getHealth() + b.player.getAbsorptionAmount();
        boolean var43 = (Boolean)this.B.getValue() && this.d(new Object[]{var26});
        short var10000 = 3116;
        AutoObbyCrystalHelper var44 = null;
        long var10001 = 6079205640999221001L ^ var2;
        this.aj.clear();
        Mutable var45 = new Mutable();

        for (int var46 = -4; var46 <= 4; var46++) {
            for (int var47 = -4; var47 <= 4; var47++) {
                for (int var48 = -2; var48 <= 2; var48++) {
                    var45.set(var37.getX() + var46, var37.getY() + var48, var37.getZ() + var47);
                    double var49 = var45.getX() + 0.5;
                    double var51 = var45.getY() + 0.5;
                    double var53 = var45.getZ() + 0.5;
                    if (!(var39.squaredDistanceTo(var49, var51, var53) > 27.5625) || !(var40.squaredDistanceTo(var49, var51, var53) > 27.5625)) {
                        BlockPos var55 = var45.up();
                        boolean var56 = WorldUtil2.b(new Object[]{var45, Blocks.OBSIDIAN, var14}) || WorldUtil2.b(new Object[]{var45, Blocks.BEDROCK, var14});
                        boolean var57 = var56 && this.g(new Object[]{var55, var4});
                        if (!var57) {
                            if (!b.world.getBlockState(var55).isAir()) {
                                continue;
                            }

                            Object[] var10005 = new Object[]{null, null, var16};
                            var10005[1] = 2;
                            var10005[0] = var55;
                            if (this.i(var10005)) {
                                continue;
                            }
                        }

                        BlockPos var58 = var45.toImmutable();
                        BlockPos var59 = null;
                        Direction var60 = null;
                        Vec3d var61;
                        if (var56) {
                            if (var57) {
                                Vec3d var62 = new Vec3d(var58.getX() + 0.5, var58.getY() + 1.5, var58.getZ() + 0.5);
                                if (var39.distanceTo(var62) > ((Float)this.I.getValue()).floatValue() + 0.5
                                    && var40.distanceTo(var62) > ((Float)this.I.getValue()).floatValue() + 0.5) {
                                    continue;
                                }

                                if ((Boolean)this.g.getValue()) {
                                    RenderBatch var63 = Prestige.o.a_RenderBatch(new Object[]{var62, var34});
                                    if (var63 == null) {
                                        continue;
                                    }

                                    float var85 = var63.a(new Object[0]) - b.player.getYaw();
                                    Object[] var10003 = new Object[]{null, var32};
                                    var10003[0] = var85;
                                    if (Math.abs(WorldUtil.b_float_____________________(var10003)) > (Float)this.s.getValue()) {
                                        continue;
                                    }
                                }

                                var61 = var62;
                            } else {
                                var61 = RaycastUtil.a(new Object[]{var58, var6});
                                if (var61 == null || (Boolean)this.g.getValue() && !this.j_boolean__(new Object[]{var61, var58, var8})) {
                                    continue;
                                }
                            }
                        } else {
                            if ((Boolean)this.z.getValue() || WorldUtil2.e(new Object[]{var58, var28})) {
                                continue;
                            }

                            Object[] var90 = new Object[]{null, null, var16};
                            var90[1] = 1;
                            var90[0] = var58;
                            if (this.i(var90)) {
                                continue;
                            }

                            var61 = null;
                            double var74 = Double.MAX_VALUE;

                            for (Direction var67 : Direction.values()) {
                                BlockPos var68 = var58.offset(var67);
                                if (!b.world.getBlockState(var68).isReplaceable()) {
                                    Direction var69 = var67.getOpposite();
                                    Vec3d var70 = var68.toCenterPos().add(var69.getOffsetX() * 0.5, var69.getOffsetY() * 0.5, var69.getOffsetZ() * 0.5);
                                    double var71 = var39.distanceTo(var70);
                                    if ((!(var71 > 4.25) || !(var40.distanceTo(var70) > 4.25))
                                        && (!(Boolean)this.g.getValue() || this.j_boolean__(new Object[]{var70, var68, var8}))
                                        && var71 < var74) {
                                        var74 = var71;
                                        var59 = var68;
                                        var60 = var69;
                                        var61 = var70;
                                    }
                                }
                            }

                            if (var59 == null
                                || var39.distanceTo(var58.toCenterPos().add(0.0, 0.5, 0.0)) > 4.25
                                    && var40.distanceTo(var58.toCenterPos().add(0.0, 0.5, 0.0)) > 4.25) {
                                continue;
                            }
                        }

                        Vec3d var75 = new Vec3d(var58.getX() + 0.5, var58.getY() + 1.0, var58.getZ() + 0.5);
                        DamageUtil var86 = Prestige.f;
                        PlayerEntity var88 = this.Z;
                        Object[] var10008 = new Object[]{null, null, null, null, null, var20};
                        var10008[4] = false;
                        var10008[3] = var58;
                        var10008[2] = false;
                        var10008[1] = var75;
                        var10008[0] = var88;
                        double var76 = var86.b(var10008);
                        double var77 = var76;
                        if (var43 && var76 < ((Float)this.c.getValue()).floatValue()) {
                            var10008 = new Object[]{null, null, null, null, var22};
                            var10008[3] = var76;
                            var10008[2] = var58;
                            var10008[1] = var38;
                            var10008[0] = var75;
                            var77 = Math.max(var76, this.a_double___(var10008));
                        }

                        if (!(var77 < ((Float)this.c.getValue()).floatValue())) {
                            DamageUtil var87 = Prestige.f;
                            ClientPlayerEntity var89 = b.player;
                            var10008 = new Object[]{null, null, null, null, null, var20};
                            var10008[4] = false;
                            var10008[3] = var58;
                            var10008[2] = false;
                            var10008[1] = var75;
                            var10008[0] = var89;
                            double var78 = var87.b(var10008);
                            if (!(var78 > ((Float)this.d.getValue()).floatValue()) && (!(Boolean)this.e.getValue() || !(var78 >= var42 - 1.0F))) {
                                double var79 = var77;
                                if (var77 >= this.Z.getHealth() + this.Z.getAbsorptionAmount()) {
                                    var79 = var77 + 50.0;
                                }

                                if (var41 != null) {
                                    Vec3d var81 = var38.subtract(var75);
                                    var81 = new Vec3d(var81.x, 0.0, var81.z);
                                    if (var81.lengthSquared() > 1.0E-4) {
                                        var79 += ((Float)this.u.getValue()).floatValue() * 8.0 * Math.max(0.0, var81.normalize().dotProduct(var41));
                                    }
                                }

                                if ((Boolean)this.x.getValue() && this.k_boolean_(new Object[]{var58, var37, var24})) {
                                    var79 += 3.0;
                                }

                                if (var56 && (Boolean)this.y.getValue()) {
                                    var79 += var57 ? 8.0 : 4.0;
                                }

                                if ((Float)this.v.getValue() > 0.0F) {
                                    double var83 = this.b_double___(new Object[]{var61, var30});
                                    if (var83 >= 0.0) {
                                        var79 -= ((Float)this.v.getValue()).floatValue() * Math.min(var83, (double)((Float)this.w.getValue()).floatValue());
                                    }
                                }

                                var79 -= 0.2 * var39.distanceTo(var61);
                                AutoObbyCrystalHelper var84 = new AutoObbyCrystalHelper(var58, var59, var60, var61, !var56, var57, var79);
                                this.aj.add(var84);
                                if (var44 == null || var79 > var44.g) {
                                    var44 = var84;
                                }
                            }
                        }
                    }
                }
            }
        }

        this.aj.sort(Comparator.<AutoObbyCrystalHelper>comparingDouble(lpL244_0 -> ((dev.zprestige.prestige.math.AutoObbyCrystalHelper)lpL244_0).g).reversed());
        return var44;
    }

    private boolean j_boolean__(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        BlockPos var3 = (BlockPos)var1[1];
        long var4 = (Long)var1[2];
        var4 = 45124953315131L ^ var4;
        long var6 = var4 ^ 21335166793029L;
        long var8 = var4 ^ 96471927547342L;
        long var10 = var4 ^ 41551049095747L;
        Event.b();
        RenderBatch var13 = Prestige.o.a_RenderBatch(new Object[]{var2, var10});
        if (var13 == null) {
            return false;
        } else {
            float var10000 = var13.a(new Object[0]) - b.player.getYaw();
            Object[] var10003 = new Object[]{null, var8};
            var10003[0] = var10000;
            if (Math.abs(WorldUtil.b_float_____________________(var10003)) > (Float)this.s.getValue()) {
                return false;
            } else {
                return var13.a_boolean________________________________________(new Object[0])
                    ? true
                    : WorldUtil.a_class_239_(new Object[]{var13, var6}) instanceof BlockHitResult var15 && var15.getBlockPos().equals(var3);
            }
        }
    }

    private Vec3d f_class_243(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        long var2 = (Long)var1[1];
        var2 = 45124953315131L ^ var2;
        long var5 = var2 ^ 49157113108411L;
        long var7 = var2 ^ 36211313154318L;
        Event.b();
        String var10 = (String)this.t.getValue();
        if (var10.equals("None")) {
            return null;
        } else {
            switch (var10) {
                case "Away From Self":
                    Vec3d var17 = var4.subtract(PositionUtil.b(new Object[]{var7}));
                    var17 = new Vec3d(var17.x, 0.0, var17.z);
                    return var17.lengthSquared() > 1.0E-4 ? var17.normalize() : null;
                case "Toward Center":
                    Vec3d var16 = new Vec3d(-var4.x, 0.0, -var4.z);
                    return var16.lengthSquared() > 1.0E-4 ? var16.normalize() : null;
                case "Toward Hole":
                    BlockPos var13 = this.a_class_2338__(new Object[]{var5});
                    if (var13 == null) {
                        return null;
                    }

                    Vec3d var14 = var13.toCenterPos().subtract(var4);
                    var14 = new Vec3d(var14.x, 0.0, var14.z);
                    return var14.lengthSquared() > 1.0E-4 ? var14.normalize() : null;
                default:
                    return null;
            }
        }
    }

    private BlockPos a_class_2338__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 23796775451657L;
        Event.b();
        BlockPos var7 = this.Z.getBlockPos();
        BlockPos var8 = null;
        double var9 = Double.MAX_VALUE;
        Mutable var11 = new Mutable();

        for (int var12 = -6; var12 <= 6; var12++) {
            for (int var13 = -6; var13 <= 6; var13++) {
                var11.set(var7.getX() + var12, var7.getY(), var7.getZ() + var13);
                if (b.world.getBlockState(var11).isAir() && b.world.getBlockState(var11.up()).isAir() && WorldUtil2.e(new Object[]{var11.down(), var4})) {
                    boolean var14 = true;

                    for (Direction var18 : Direction.values()) {
                        if (var18.getOffsetY() == 0 && !WorldUtil2.e(new Object[]{var11.offset(var18), var4})) {
                            var14 = false;
                            break;
                        }
                    }

                    if (var14) {
                        double var20 = var12 * var12 + var13 * var13;
                        if (var20 < var9) {
                            var9 = var20;
                            var8 = var11.toImmutable();
                        }
                    }
                }
            }
        }

        return var8;
    }

    private boolean k_boolean_(Object[] var1) {
        BlockPos var5 = (BlockPos)var1[0];
        BlockPos var2 = (BlockPos)var1[1];
        return var5.getY() == var2.getY() && Math.max(Math.abs(var5.getX() - var2.getX()), Math.abs(var5.getZ() - var2.getZ())) == 1;
    }

    private EndCrystalEntity a_class_1511(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 21345801821222L;
        long var6 = var2 ^ 125507669430018L;
        long var8 = var2 ^ 55690499677084L;
        Event.b();
        this.ah = false;
        if (this.aa != null && this.g(new Object[]{this.aa.up(), var4})) {
            Vec3d var11 = this.aa.up().toCenterPos();
            Vec3d var12 = b.player.getEyePos();
            Box var13 = new Box(this.aa.up()).expand(1.5);

            for (Entity var15 : b.world.getOtherEntities(null, var13, lpL243_0 -> ((net.minecraft.entity.Entity)lpL243_0) instanceof EndCrystalEntity)) {
                if (var15 instanceof EndCrystalEntity var16
                    && var16.isAlive()
                    && var16.getId() != this.ai
                    && var16.age >= 2
                    && !(PositionUtil.a(new Object[]{var16, var6}).distanceTo(var11) > 1.0)) {
                    Box var17 = var16.getBoundingBox();
                    double var18 = c_double___(new Object[]{var17, var12, var8});
                    if (this.ak != null) {
                        var18 = Math.max(var18, c_double___(new Object[]{var17, this.ak, var8}));
                    }

                    if (!(var18 > ((Float)this.I.getValue()).floatValue())) {
                        return var16;
                    }

                    this.ah = true;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private static double c_double___(Object[] var0) {
        Box var4 = (Box)var0[0];
        Vec3d var3 = (Vec3d)var0[1];
        double var5 = Math.max(var4.minX - var3.x, Math.max(0.0, var3.x - var4.maxX));
        double var7 = Math.max(var4.minY - var3.y, Math.max(0.0, var3.y - var4.maxY));
        double var9 = Math.max(var4.minZ - var3.z, Math.max(0.0, var3.z - var4.maxZ));
        return Math.sqrt(var5 * var5 + var7 * var7 + var9 * var9);
    }

    private boolean l_boolean(Object[] var1) {
        Box var6 = (Box)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        RenderBatch var4 = (RenderBatch)var1[2];
        long var2 = (Long)var1[3];
        var2 = 45124953315131L ^ var2;
        long var7 = var2 ^ 118867956410404L;
        Vec3d var9 = var5.add(WorldUtil.a_class_243________________(new Object[]{var4, var7}).multiply(6.0));
        return var6.raycast(var5, var9).map(lpL242_0 -> var5.distanceTo(((net.minecraft.util.math.Vec3d)lpL242_0)) <= ((Float)this.I.getValue()).floatValue()).orElse(false);
    }

    private boolean m_boolean(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 136194790328295L;
        return this.Z != null
            && this.Z.isAlive()
            && b.player.distanceTo(this.Z) <= (Float)this.k.getValue()
            && Prestige.i.a_boolean_(new Object[]{this.Z, var4});
    }

    private boolean n_boolean(Object[] var1) {
        if (this.ae != null && this.Z != null) {
            BlockPos var5 = this.Z.getBlockPos();
            int var6 = Math.abs(var5.getX() - this.ae.getX());
            int var7 = Math.abs(var5.getY() - this.ae.getY());
            int var8 = Math.abs(var5.getZ() - this.ae.getZ());
            this.aD = Math.max(var6, Math.max(var7, var8));
            return this.aD > (this.S == CrystalState.PLACE_OBSIDIAN ? 2 : (Integer)this.o.getValue());
        } else {
            return false;
        }
    }

    private void u(Object[] var1) {
        String var4 = (String)var1[0];
        long var2 = (Long)var1[1];
        var2 = 45124953315131L ^ var2;
        long var5 = var2 ^ 12846848591529L;
        long var7 = var2 ^ 65625383453488L;
        long var9 = var2 ^ 119828529314721L;
        long var11 = var2 ^ 72510782199301L;
        RenderHelper27 var10000 = this.aE;
        Object[] var10004 = new Object[]{null, var9};
        var10004[0] = 3000.0F;
        if (var10000.a(var10004)) {
            ModuleNotificationsElement.s(new Object[]{this, var4, NotificationType.ERROR, var5});
            this.aE.a_void_____________________(new Object[]{var7});
        }

        this.x(new Object[]{var11});
    }

    private void v(Object[] var1) {
        String var4 = (String)var1[0];
        long var2 = (Long)var1[1];
        var2 = 45124953315131L ^ var2;
        long var5 = var2 ^ 109340588861614L;
        long var7 = var2 ^ 45270268639234L;
        if ((Boolean)this.q.getValue() && this.S != CrystalState.IDLE) {
            ModuleNotificationsElement.s(new Object[]{this, "abort: " + this.S + ", " + var4, NotificationType.INFO, var5});
        }

        this.x(new Object[]{var7});
    }

    private float d_float_(Object[] var1) {
        int var4 = 0;
        PlayerListEntry var5 = b.player.networkHandler.getPlayerListEntry(b.player.getUuid());
        if (var5 != null) {
            var4 = Math.max(0, var5.getLatency());
        }

        return this.S == CrystalState.BREAK_CRYSTAL
            ? Math.min(1500.0F, Math.max(500.0F, 300.0F + var4 * 1.5F))
            : Math.min(600.0F, Math.max(200.0F, 120.0F + var4 * 0.8F));
    }

    private void w(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 67436639361498L;
        long var6 = var2 ^ 24817248026408L;
        if (this.af != -1) {
            if (b.player == null) {
                RenderUtil6.a_void____________________________(new Object[]{this, var4});
                this.af = -1;
            } else if (!RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                this.af = -1;
            } else if (!Prestige.o.d_boolean______(new Object[0])) {
                if ((Boolean)this.h.getValue()) {
                    int var10000 = this.af;
                    Object[] var10003 = new Object[]{null, var6};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                    Prestige.o.g(new Object[]{true});
                }

                RenderUtil6.a_void____________________________(new Object[]{this, var4});
                this.af = -1;
            }
        }
    }

    private void x(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 30640521703415L;
        this.w(new Object[]{var4});
        this.S = CrystalState.IDLE;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.ag = false;
        this.Y = false;
        this.aj.clear();
        R = false;
    }

    private void y(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 45124953315131L ^ var2;
        long var4 = var2 ^ 24958852567137L;
        this.S = CrystalState.IDLE;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = -1;
        this.ag = false;
        this.Y = false;
        this.X = false;
        this.ai = -1;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = Vec3d.ZERO;
        this.ao = Long.MIN_VALUE;
        this.aj.clear();
        R = false;
        this.ax.b(new Object[]{var4});
        this.ay.b(new Object[]{var4});
        this.az.clear();
        this.aA.clear();
        this.aB = null;
        this.aC = null;
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
