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
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.network.PacketQueue;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderUtil6;
import dev.zprestige.prestige.type.PacketResult;
import dev.zprestige.prestige.type.PlaceState;
import dev.zprestige.prestige.world.CobwebPlacerHelper;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class CobwebPlacer extends Module implements RenderContract3 {
    private static final double a = 0.08;
    private static final double c = 0.91;
    private static final double d = 0.98;
    private static final double e = 0.999;
    private EnumSetting f;
    private FloatSetting g;
    private RangeSetting h;
    private IntSetting i;
    private BoolSetting j;
    private IntSetting k;
    private BoolSetting l;
    private EnumSetting m;
    private IntSetting n;
    private EnumSetting o;
    private BoolSetting p;
    private BoolSetting q;
    private EnumSetting r;
    private IntSetting s;
    private BoolSetting t;
    private ColorSetting u;
    private RenderHelper27 v = new RenderHelper27(26293358099357L);
    private int w = 0;
    private Integer x = null;
    private Vec3d y = null;
    private UUID z = null;
    private int A = 0;
    private int B = -1;
    private int C = -1;
    private int D = -1;
    public boolean E = false;
    public boolean F = false;
    private Object G = null;

    public CobwebPlacer() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.l.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL176_0 -> ((String)this.f.getValue()).equals("Manual"), 73196168581295L});
        this.n
            .setVisibility_IntSetting(
                new Object[]{
                    (Predicate<Integer>)lpL174_0 -> ((String)this.f.getValue()).equals("Manual")
                        && !(Boolean)this.l.getValue()
                        && ((String)this.m.getValue()).equals("Slot"),
                    98265949679296L
                }
            );
        this.m
            .setVisibility_EnumSetting(
                new Object[]{(Predicate<String>)lpL175_0 -> ((String)this.f.getValue()).equals("Manual") && !(Boolean)this.l.getValue(), 90319707064974L}
            );
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public boolean b(Object[] var1) {
        return "Auto".equals(this.f.getValue());
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 39963010439486L;
        long var6 = var2 ^ 91082959635357L;
        this.w = 0;
        Event.b();
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = 0;
        if (!this.E) {
            this.p(new Object[]{var4});
        } else if (this.G != b.world) {
            this.p(new Object[]{var4});
        }

        Prestige.o.e(new Object[]{this, var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        long var6 = var2 ^ 56527593523101L;
        this.n(new Object[]{var6});
        this.w = 0;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = 0;
        Prestige.o.f(new Object[]{this, var4});
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (!((String)this.f.getValue()).equals("Auto")) {
            if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
                if (!Prestige.o.d_boolean______(new Object[0])) {
                    if (this.v.b(new Object[]{this.h, 89714527855213L})) {
                        if (b.crosshairTarget instanceof BlockHitResult var21) {
                            if (b.world.getBlockState(var21.getBlockPos()).isReplaceable()) {
                                this.toggle(new Object[]{115103427876780L});
                            } else {
                                if ((Boolean)this.l.getValue()) {
                                    Block var10000 = Blocks.COBWEB;
                                    Runnable var10001 = () -> InventoryUtil2.a(new Object[]{var21, 96519249476482L});
                                    boolean var10002 = (Boolean)this.l.getValue();
                                    Object[] var10005 = new Object[]{null, null, null, 135286962703597L};
                                    var10005[2] = var10002;
                                    var10005[1] = var10001;
                                    var10005[0] = var10000;
                                    if (InventoryUtil.k(var10005)) {
                                        this.toggle(new Object[]{115103427876780L});
                                    }
                                } else {
                                    switch (this.w) {
                                        case 0:
                                            this.x = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                            if (!InventoryUtil.i(new Object[]{Blocks.COBWEB, 33415164545344L})) {
                                                this.toggle(new Object[]{115103427876780L});
                                                return;
                                            }

                                            this.s(new Object[]{29220901753974L});
                                            break;
                                        case 1:
                                            InventoryUtil2.a(new Object[]{var21, 96519249476482L});
                                            this.s(new Object[]{29220901753974L});
                                            break;
                                        case 2:
                                            if (((String)this.m.getValue()).equals("Slot")) {
                                                int var23 = (Integer)this.n.getValue() - 1;
                                                Object[] var10003 = new Object[]{null, 37392877673079L};
                                                var10003[0] = var23;
                                                InventoryUtil.a_void________(var10003);
                                            } else if (((String)this.m.getValue()).equals("Original")) {
                                                int var24 = this.x;
                                                Object[] var25 = new Object[]{null, 37392877673079L};
                                                var25[0] = var24;
                                                InventoryUtil.a_void________(var25);
                                            }

                                            this.toggle(new Object[]{115103427876780L});
                                    }
                                }
                            }
                        } else {
                            this.toggle(new Object[]{115103427876780L});
                        }
                    }
                }
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 35169069234686L;
        long var6 = var2 ^ 92268617212551L;
        long var8 = var2 ^ 29247161167908L;
        long var10 = var2 ^ 89744056802368L;
        long var12 = var2 ^ 125420266980053L;
        long var14 = var2 ^ 29142186185547L;
        long var16 = var2 ^ 119646042442835L;
        long var18 = var2 ^ 17054052230717L;
        long var20 = var2 ^ 136752692109656L;
        long var22 = var2 ^ 114124981852069L;
        long var24 = var2 ^ 58982489950237L;
        long var26 = var2 ^ 71343916077473L;
        long var28 = var2 ^ 127616945565159L;
        long var30 = var2 ^ 46949571541433L;
        long var32 = var2 ^ 114164831697321L;
        long var34 = var2 ^ 116785209433731L;
        long var36 = var2 ^ 83235255851133L;
        long var38 = var2 ^ 26324604509057L;
        long var40 = var2 ^ 51598816251400L;
        long var42 = var2 ^ 33065978990036L;
        long var44 = var2 ^ 102249493874797L;
        long var46 = var2 ^ 103998936193343L;
        long var48 = var2 ^ 101816702374277L;
        long var50 = var2 ^ 78799625056005L;
        if (!((String)this.f.getValue()).equals("Auto")) {
            this.y = null;
            this.m(new Object[]{var16});
            this.d(new Object[]{var22});
            return null;
        } else if (b.player != null && b.world != null) {
            if (this.B == -1
                || this.G == b.world
                    && RenderUtil6.a_boolean____________________________________________(new Object[]{this})
                    && (this.C == -1 || InventoryUtil.a_int_____(new Object[]{var24}) == this.C)) {
                if (this.d(new Object[]{var22})) {
                    return null;
                } else if (b.currentScreen != null || !b.isWindowFocused() || b.player.isUsingItem()) {
                    return this.b_RenderBatch__(new Object[]{var44});
                } else if (Prestige.o.d_boolean______(new Object[0])) {
                    return null;
                } else if (!this.v.b(new Object[]{this.h, var42})) {
                    return null;
                } else {
                    PlayerEntity var53 = CombatUtil.a_class_1657(new Object[]{var26});
                    if (var53 == null || b.player.distanceTo(var53) > (Float)this.g.getValue()) {
                        this.y = null;
                        return this.b_RenderBatch__(new Object[]{var44});
                    } else if (WorldUtil2.a(new Object[]{var53, var32})) {
                        this.y = null;
                        return this.b_RenderBatch__(new Object[]{var44});
                    } else {
                        Integer var54 = InventoryUtil.d(new Object[]{Blocks.COBWEB, var50});
                        int var55 = -1;
                        if (var54 == null && (Boolean)this.p.getValue()) {
                            Item var10000 = Items.COBWEB;
                            Object[] var10004 = new Object[]{null, null, var4};
                            var10004[1] = false;
                            var10004[0] = var10000;
                            List var56 = InventoryUtil.b_List(var10004);
                            if (!var56.isEmpty()) {
                                var55 = (Integer)var56.get(0);
                            }
                        }

                        if (var54 == null && var55 == -1) {
                            this.y = null;
                            return this.b_RenderBatch__(new Object[]{var44});
                        } else {
                            boolean var92 = ((String)this.o.getValue()).equals("None");
                            if (var92 && !InventoryUtil.l(new Object[]{Items.COBWEB, var28}) && var55 == -1) {
                                this.y = null;
                                return this.b_RenderBatch__(new Object[]{var44});
                            } else {
                                Vec3d var57;
                                if (!(Boolean)this.j.getValue()) {
                                    var57 = this.a_class_243_________(new Object[]{var53, var18});
                                } else {
                                    if (this.z == null
                                        || !this.z.equals(var53.getUuid())
                                        || PositionUtil.a_double___________(new Object[]{var53, var30}) < 0.3F) {
                                        this.z = var53.getUuid();
                                        this.A = 0;
                                    }

                                    if (this.A >= (Integer)this.k.getValue()) {
                                        this.y = null;
                                        return this.b_RenderBatch__(new Object[]{var44});
                                    }

                                    var57 = this.b_class_243___(new Object[]{var53, var36});
                                    if (var57 == null || var57.y >= PositionUtil.a(new Object[]{var53, var12}).y - 0.5) {
                                        this.y = null;
                                        return this.b_RenderBatch__(new Object[]{var44});
                                    }
                                }

                                if (var57 == null) {
                                    return this.b_RenderBatch__(new Object[]{var44});
                                } else {
                                    this.y = var57;
                                    BlockPos var58 = BlockPos.ofFloored(var57.x, var57.y, var57.z);
                                    BlockPos[] var59 = this.q.getValue() ? new BlockPos[]{var58, var58.up()} : new BlockPos[]{var58};
                                    RenderBatch var60 = null;
                                    BlockHitResult var61 = null;
                                    double var62 = Double.MAX_VALUE;
                                    RenderBatch var64 = null;
                                    Vec3d var65 = b.player.getEyePos();
                                    double var66 = InventoryUtil2.b_float_____(new Object[]{var46});
                                    float var68 = b.player.getYaw();
                                    float var69 = b.player.getPitch();
                                    BlockPos var70 = b.player.getBlockPos();
                                    BlockPos var71 = var70.up();

                                    for (BlockPos var75 : var59) {
                                        if (!var75.equals(var70) && !var75.equals(var71)) {
                                            BlockState var76 = b.world.getBlockState(var75);
                                            if (var76.isReplaceable() && var76.getFluidState().isEmpty()) {
                                                for (Direction var80 : Direction.values()) {
                                                    BlockPos var81 = var75.offset(var80);
                                                    BlockState var82 = b.world.getBlockState(var81);
                                                    if (!var82.isAir() && !var82.isReplaceable() && var82.getFluidState().isEmpty()) {
                                                        Direction var83 = var80.getOpposite();
                                                        Vec3d var84 = new Vec3d(
                                                            var81.getX() + 0.5 + var83.getOffsetX() * 0.5,
                                                            var81.getY() + 0.5 + var83.getOffsetY() * 0.5,
                                                            var81.getZ() + 0.5 + var83.getOffsetZ() * 0.5
                                                        );
                                                        if (!(var65.distanceTo(var84) > var66)) {
                                                            RenderBatch var85 = Prestige.o.a_RenderBatch(new Object[]{var84, var48});
                                                            float var96 = var85.a(new Object[0]) - var68;
                                                            Object[] var10003 = new Object[]{null, var40};
                                                            var10003[0] = var96;
                                                            float var86 = WorldUtil.b_float_____________________(var10003);
                                                            if (var85.a_boolean________________________________________(new Object[0])) {
                                                                if (var64 == null) {
                                                                    var64 = var85;
                                                                }
                                                            } else if (WorldUtil.a_class_239_(new Object[]{var85, var34}) instanceof BlockHitResult var88
                                                                && var88.getBlockPos().equals(var81)
                                                                && var88.getSide() == var83
                                                                && !(var65.distanceTo(var88.getPos()) > var66)) {
                                                                float var89 = var85.b(new Object[0]) - var69;
                                                                double var90 = Math.sqrt(var86 * var86 + var89 * var89);
                                                                if (var90 < var62) {
                                                                    var62 = var90;
                                                                    var60 = var85;
                                                                    var61 = var88;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    if (var60 == null) {
                                        return var64 != null ? var64 : this.b_RenderBatch__(new Object[]{var44});
                                    } else if (var61 == null) {
                                        return var60;
                                    } else {
                                        boolean var93 = !InventoryUtil.l(new Object[]{Items.COBWEB, var28}) && this.B == -1 && !var92 && var54 != null;
                                        if (!var93) {
                                            BlockHitResult var94 = this.a_class_3965______(new Object[]{var61, var6});
                                            if (var94 == null) {
                                                return var60;
                                            }

                                            var61 = var94;
                                        }

                                        Object[] var10007 = new Object[]{null, null, null, null, var14};
                                        var10007[3] = var92;
                                        var10007[2] = var55;
                                        var10007[1] = var54;
                                        var10007[0] = var61;
                                        PlaceState var95 = this.a_PlaceState(var10007);
                                        if (var95 == PlaceState.PLACE_SENT) {
                                            this.j(new Object[]{var10});
                                        } else if (var95 == PlaceState.INVENTORY_QUEUED) {
                                            this.v.a_void_____________________(new Object[]{var8});
                                            this.h.reroll(new Object[]{var20});
                                        }

                                        return var60;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                this.p(new Object[]{var38});
                return null;
            }
        } else {
            this.p(new Object[]{var38});
            return null;
        }
    }

    private BlockHitResult a_class_3965______(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        long var3 = (Long)var1[1];
        var3 = 114642101026600L ^ var3;
        long var5 = var3 ^ 89721522766636L;
        long var7 = var3 ^ 111295021188240L;
        Event.b();
        RenderBatch var10 = Prestige.o.b_RenderBatch____________(new Object[0]);
        if (var10 == null) {
            return null;
        } else if (WorldUtil.a_class_239_(new Object[]{var10, var5}) instanceof BlockHitResult var12) {
            if (!var12.getBlockPos().equals(var2.getBlockPos())) {
                return null;
            } else if (var12.getSide() != var2.getSide()) {
                return null;
            } else {
                return b.player.getEyePos().distanceTo(var12.getPos()) > InventoryUtil2.b_float_____(new Object[]{var7}) ? null : var12;
            }
        } else {
            return null;
        }
    }

    private PlaceState a_PlaceState(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        Integer var7 = (Integer)var1[1];
        int var4 = (Integer)var1[2];
        boolean var3 = (Boolean)var1[3];
        long var5 = (Long)var1[4];
        var5 = 114642101026600L ^ var5;
        long var8 = var5 ^ 118253987659769L;
        long var10 = var5 ^ 57771033853791L;
        long var12 = var5 ^ 117128807594072L;
        long var14 = var5 ^ 115449276473541L;
        long var16 = var5 ^ 70942196657459L;
        long var18 = var5 ^ 33072385433648L;
        long var20 = var5 ^ 34418699590061L;
        long var22 = var5 ^ 78485935514750L;
        long var24 = var5 ^ 7508184252804L;
        long var26 = var5 ^ 100695879185363L;
        long var28 = var5 ^ 22735931462175L;
        Event.b();
        int var31 = InventoryUtil.a_int_____(new Object[]{var22});
        if (!InventoryUtil.l(new Object[]{Items.COBWEB, var24})) {
            if (this.B != -1) {
                if (RenderUtil6.a_boolean____________________________________________(new Object[]{this}) && var31 == this.C) {
                    this.m(new Object[]{var18});
                    return PlaceState.NONE;
                } else {
                    if (RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                        RenderUtil6.a_void____________________________(new Object[]{this, var10});
                    }

                    this.q(new Object[0]);
                    return PlaceState.NONE;
                }
            } else if (!var3 && var7 != null) {
                this.B = RenderUtil6.a(new Object[]{this, var26});
                this.C = var7;
                this.G = b.world;
                int var36 = var7;
                Object[] var10003 = new Object[]{null, var20};
                var10003[0] = var36;
                InventoryUtil.a_void________(var10003);
                Prestige.o.g(new Object[]{true});
                return PlaceState.HOTBAR_SWAPPED;
            } else if (var4 != -1) {
                if (Prestige.u != null) {
                    PacketQueue var10000 = Prestige.u;
                    Object[] var10006 = new Object[]{null, null, (Runnable)() -> {
                        Object[] var10006x = new Object[]{null, null, null, 138306721574633L};
                        var10006x[2] = var31;
                        var10006x[1] = var4;
                        var10006x[0] = var2;
                        if (this.a_boolean____________________(var10006x)) {
                            this.j(new Object[]{33078325982201L});
                        }
                    }, var8};
                    var10006[1] = var31;
                    var10006[0] = var4;
                    PacketResult var34 = var10000.b(var10006);
                    if (var34 == PacketResult.QUEUED) {
                        return PlaceState.INVENTORY_QUEUED;
                    }

                    if (var34 == PacketResult.REJECTED) {
                        return PlaceState.NONE;
                    }
                } else {
                    Object[] var10005 = new Object[]{null, null, SlotActionType.SWAP, var14};
                    var10005[1] = var31;
                    var10005[0] = var4;
                    InventoryUtil.e(var10005);
                }

                Object[] var37 = new Object[]{null, null, null, var16};
                var37[2] = var31;
                var37[1] = var4;
                var37[0] = var2;
                return this.a_boolean____________________(var37) ? PlaceState.PLACE_SENT : PlaceState.NONE;
            } else {
                return PlaceState.NONE;
            }
        } else if (this.B != -1 && (!RenderUtil6.a_boolean____________________________________________(new Object[]{this}) || var31 != this.C)) {
            if (RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                RenderUtil6.a_void____________________________(new Object[]{this, var10});
            }

            this.q(new Object[0]);
            return PlaceState.NONE;
        } else {
            InventoryUtil2.a(new Object[]{var2, var12});
            this.k(new Object[]{var28});
            return PlaceState.PLACE_SENT;
        }
    }

    private boolean a_boolean____________________(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        int var3 = (Integer)var1[1];
        int var6 = (Integer)var1[2];
        long var4 = (Long)var1[3];
        var4 = 114642101026600L ^ var4;
        long var7 = var4 ^ 72870984378947L;
        long var9 = var4 ^ 99175127842387L;
        long var11 = var4 ^ 135704699456659L;
        if (b.player != null && b.world != null) {
            InventoryUtil2.a(new Object[]{var2, var7});
            if (((String)this.r.getValue()).equals("Slot")) {
                int var10001 = (Integer)this.s.getValue() - 1;
                Object[] var10004 = new Object[]{null, var9};
                var10004[0] = var10001;
                this.l(var10004);
            }

            Prestige.o.h(new Object[]{(Runnable)() -> {
                if (b.player != null) {
                    Object[] var10004x = new Object[]{null, null, 133411734417951L};
                    var10004x[1] = var6;
                    var10004x[0] = var3;
                    InventoryUtil.f(var10004x);
                }
            }, var11});
            return true;
        } else {
            return false;
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 38769490367308L;
        long var6 = var2 ^ 76670211441200L;
        if ((Boolean)this.j.getValue()) {
            this.A++;
        }

        this.v.a_void_____________________(new Object[]{var4});
        this.h.reroll(new Object[]{var6});
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 28439160216277L;
        long var6 = var2 ^ 15509549728127L;
        Event.b();
        String var9 = (String)this.r.getValue();
        switch (var9) {
            case "Slot":
                int var12 = (Integer)this.s.getValue() - 1;
                Object[] var13 = new Object[]{null, var6};
                var13[0] = var12;
                this.l(var13);
                break;
            case "Original":
                if (this.B != -1) {
                    int var10001 = this.B;
                    Object[] var10004 = new Object[]{null, var6};
                    var10004[0] = var10001;
                    this.l(var10004);
                } else {
                    this.p(new Object[]{var4});
                }
                break;
            default:
                this.p(new Object[]{var4});
        }
    }

    private void l(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        var2 = 114642101026600L ^ var2;
        long var5 = var2 ^ 41209559543312L;
        long var7 = var2 ^ 140199183787138L;
        long var9 = var2 ^ 103179750733598L;
        long var11 = var2 ^ 71620999143603L;
        if (var4 >= 0 && var4 <= 8 && var4 != InventoryUtil.a_int_____(new Object[]{var9})) {
            if (this.B == -1) {
                this.B = RenderUtil6.a(new Object[]{this, var11});
                this.C = InventoryUtil.a_int_____(new Object[]{var9});
                this.G = b.world;
            }

            if (!RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                this.q(new Object[0]);
            } else {
                this.D = var4;
                this.E = true;
                this.o(new Object[]{var5});
            }
        } else {
            this.p(new Object[]{var7});
        }
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 21321809242362L;
        long var6 = var2 ^ 5097151312720L;
        long var8 = var2 ^ 54089143712614L;
        if (!this.E && this.B != -1) {
            if (b.player != null && b.world != null && this.G == b.world) {
                if (!RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                    this.q(new Object[0]);
                } else if (this.C != -1 && InventoryUtil.a_int_____(new Object[]{var8}) != this.C) {
                    this.p(new Object[]{var4});
                } else {
                    int var10001 = this.B;
                    Object[] var10004 = new Object[]{null, var6};
                    var10004[0] = var10001;
                    this.l(var10004);
                }
            } else {
                this.p(new Object[]{var4});
            }
        }
    }

    public boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 81089642396593L;
        long var6 = var2 ^ 26933035709196L;
        long var8 = var2 ^ 107783294541123L;
        long var10 = var2 ^ 63956708843664L;
        if (!this.E) {
            return false;
        } else if (b.player != null && b.world != null && this.G == b.world) {
            if (!RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
                this.q(new Object[0]);
                return true;
            } else if (this.C != -1 && InventoryUtil.a_int_____(new Object[]{var10}) != this.C) {
                this.p(new Object[]{var6});
                return true;
            } else if (Prestige.o.d_boolean______(new Object[0])) {
                return true;
            } else {
                if (InventoryUtil.a_int_____(new Object[]{var10}) != this.D) {
                    int var10000 = this.D;
                    Object[] var10003 = new Object[]{null, var8};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                    Prestige.o.g(new Object[]{true});
                }

                RenderUtil6.a_void____________________________(new Object[]{this, var4});
                this.q(new Object[0]);
                return true;
            }
        } else {
            this.p(new Object[]{var6});
            return true;
        }
    }

    private RenderBatch b_RenderBatch__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 97219313941270L;
        long var6 = var2 ^ 91745508860128L;
        this.m(new Object[]{var4});
        this.d(new Object[]{var6});
        return null;
    }

    private void n(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 138602510037370L;
        long var6 = var2 ^ 40641577122792L;
        long var8 = var2 ^ 105376849390650L;
        long var10 = var2 ^ 7298628692084L;
        long var12 = var2 ^ 93237255075788L;
        if (b.player != null && b.world != null && this.G == b.world) {
            this.m(new Object[]{var8});
            if (!this.E) {
                this.p(new Object[]{var6});
            } else if (RenderUtil6.a_boolean____________________________________________(new Object[]{this}) && b.player != null && b.world != null) {
                if (this.C != -1 && InventoryUtil.a_int_____(new Object[]{var10}) != this.C) {
                    this.p(new Object[]{var6});
                } else if (Prestige.o.d_boolean______(new Object[0])) {
                    this.o(new Object[]{var4});
                } else {
                    this.d(new Object[]{var12});
                }
            } else {
                this.p(new Object[]{var6});
            }
        } else {
            this.p(new Object[]{var6});
        }
    }

    public void o(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 4560937311440L;
        if (!this.F) {
            this.F = true;
            Prestige.o.h(new Object[]{new CobwebPlacerHelper(this), var4});
        }
    }

    private void p(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 63749141034901L;
        if (RenderUtil6.a_boolean____________________________________________(new Object[]{this})) {
            RenderUtil6.a_void____________________________(new Object[]{this, var4});
        }

        this.q(new Object[0]);
    }

    private void q(Object[] var1) {
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = false;
        this.G = null;
    }

    private Vec3d a_class_243_________(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 114642101026600L ^ var2;
        long var5 = var2 ^ 65621862799979L;
        long var7 = var2 ^ 63841328492754L;
        long var9 = var2 ^ 85168287391916L;
        boolean var12 = PositionUtil.a_double___________(new Object[]{var4, var9}) < 0.3F;
        return var12 ? this.d_class_243_(new Object[]{var4, var5}) : this.c(new Object[]{var4, var7});
    }

    private Vec3d b_class_243___(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 114642101026600L ^ var3;
        long var5 = var3 ^ 69663059769370L;
        long var7 = var3 ^ 10271476725475L;
        long var9 = var3 ^ 90048957031808L;
        Vec3d var12 = PositionUtil.a(new Object[]{var2, var9});
        Vec3d var13 = PositionUtil.c(new Object[]{var2, var5});
        Vec3d var14 = var2.getVelocity();
        double var15 = Math.min(var12.y - var13.y, var14.y);
        Vec3d var17 = new Vec3d(var12.x - var13.x, var15, var12.z - var13.z);
        Box var18 = var2.getBoundingBox();
        double var19 = Math.max((var18.maxX - var18.minX) / 2.0, 0.15);
        int var23 = b.world.getBottomY();
        double var24 = var12.x;
        Event.b();
        double var26 = var12.y;
        double var28 = var12.z;

        for (int var30 = 0; var30 < 80; var30++) {
            double var31 = var17.x / 4;
            double var33 = var17.y / 4;
            double var35 = var17.z / 4;

            for (int var37 = 0; var37 < 4; var37++) {
                double var38 = var24 + var31;
                double var40 = var26 + var33;
                double var42 = var28 + var35;
                if (var17.y <= 0.0) {
                    Object[] var10007 = new Object[]{null, null, null, null, var7};
                    var10007[3] = var19;
                    var10007[2] = var42;
                    var10007[1] = var40;
                    var10007[0] = var38;
                    if (this.e(var10007)) {
                        int var44 = MathHelper.floor(var40 - 1.0E-4);
                        return new Vec3d(var38, var44 + 1.0, var42);
                    }
                }

                var24 = var38;
                var26 = var40;
                var28 = var42;
            }

            if (var26 <= var23) {
                return null;
            }

            var17 = var17.subtract(0.0, 0.08, 0.0).multiply(0.91, 0.98, 0.91);
        }

        return null;
    }

    private boolean e(Object[] var1) {
        double var2 = (Double)var1[0];
        double var4 = (Double)var1[1];
        double var6 = (Double)var1[2];
        double var8 = (Double)var1[3];
        int var13 = MathHelper.floor(var2 - var8 + 0.001);
        Event.b();
        int var14 = MathHelper.floor(var2 + var8 - 0.001);
        int var15 = MathHelper.floor(var6 - var8 + 0.001);
        int var16 = MathHelper.floor(var6 + var8 - 0.001);
        int var17 = MathHelper.floor(var4 - 0.001);

        for (int var18 = var13; var18 <= var14; var18++) {
            for (int var19 = var15; var19 <= var16; var19++) {
                BlockPos var20 = new BlockPos(var18, var17, var19);
                BlockState var21 = b.world.getBlockState(var20);
                if (!var21.isAir() && !var21.getCollisionShape(b.world, var20).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    private Vec3d c(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 114642101026600L ^ var2;
        long var5 = var2 ^ 45349293885856L;
        long var7 = var2 ^ 78628729345082L;
        Vec3d var10 = PositionUtil.a(new Object[]{var4, var7});
        Vec3d var11 = PositionUtil.c(new Object[]{var4, var5});
        Vec3d var12 = var4.getVelocity();
        double var13 = Math.min(var10.y - var11.y, var12.y);
        Vec3d var15 = new Vec3d(var10.x - var11.x, var13, var10.z - var11.z);
        Event.b();
        Vec3d var16 = var10;
        int var17 = (Integer)this.i.getValue();
        int var18 = b.world.getBottomY();

        for (int var19 = 0; var19 < var17; var19++) {
            var16 = var10;
            var10 = var10.add(var15);
            BlockPos var20 = BlockPos.ofFloored(var10.x, var10.y - 0.1, var10.z);
            if (!b.world.getBlockState(var20).isAir()) {
                return new Vec3d(var10.x, var20.getY() + 1.0, var10.z);
            }

            if (var10.y <= var18) {
                return null;
            }

            var15 = var15.subtract(0.0, 0.08, 0.0).multiply(0.91, 0.98, 0.91);
        }

        return var16;
    }

    private Vec3d d_class_243_(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 114642101026600L ^ var3;
        long var5 = var3 ^ 44531377185561L;
        long var7 = var3 ^ 77231516679811L;
        Event.b();
        Vec3d var10 = PositionUtil.a(new Object[]{var2, var7});
        Vec3d var11 = PositionUtil.c(new Object[]{var2, var5});
        Vec3d var12 = new Vec3d(var10.x - var11.x, 0.0, var10.z - var11.z);
        int var13 = (Integer)this.i.getValue();

        for (int var14 = 0; var14 < var13; var14++) {
            var10 = var10.add(var12.x, 0.0, var12.z);
            BlockPos var15 = BlockPos.ofFloored(var10.x, var10.y - 0.1, var10.z);
            if (b.world.getBlockState(var15).isAir()) {
                break;
            }

            var12 = var12.multiply(0.999, 1.0, 0.999);
            if (Math.abs(var12.x) < 0.005 && Math.abs(var12.z) < 0.005) {
                break;
            }
        }

        return var10;
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if (((String)this.f.getValue()).equals("Auto") && (Boolean)this.t.getValue()) {
            Vec3d var7 = this.y;
            if (var7 != null) {
                BlockPos var8 = BlockPos.ofFloored(var7.x, var7.y, var7.z);
                this.r(new Object[]{var1, var8, (Color)this.u.getValue(), 8204085245145L});
                if ((Boolean)this.q.getValue()) {
                    this.r(new Object[]{var1, var8.up(), (Color)this.u.getValue(), 8204085245145L});
                }
            }
        }
    }

    private void r(Object[] var1) {
        TickEvent27 var5 = (TickEvent27)var1[0];
        BlockPos var4 = (BlockPos)var1[1];
        Color var6 = (Color)var1[2];
        long var2 = (Long)var1[3];
        var2 = 114642101026600L ^ var2;
        long var7 = var2 ^ 3352365659775L;
        MatrixCameraData var10000 = var5.b;
        RenderHelper8 var10001 = var5.a;
        float var10002 = var4.getX();
        float var10003 = var4.getY();
        float var10004 = var4.getZ();
        float var10005 = var4.getX() + 1.0F;
        float var10006 = var4.getY() + 1.0F;
        float var10007 = var4.getZ() + 1.0F;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var6, var7};
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

    private void s(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 114642101026600L ^ var2;
        long var4 = var2 ^ 43725335377091L;
        long var6 = var2 ^ 71730451277247L;
        this.v.a_void_____________________(new Object[]{var4});
        this.h.reroll(new Object[]{var6});
        this.w++;
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
