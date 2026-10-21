package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.DamageUtil;
import dev.zprestige.prestige.data.DoubleData;
import dev.zprestige.prestige.data.LongData2;
import dev.zprestige.prestige.data.VecDoubleData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class AutoSafeCart extends Module implements RenderContract3 {
    private EnumSetting a;
    private EnumSetting c;
    private FloatSetting d;
    private FloatSetting e;
    private MultiSetting f;
    private IntSetting g;
    private RangeSetting h;
    private EnumSetting i;
    private FloatSetting j;
    private FloatSetting k;
    private static final String[] l = new String[]{"Logs", "Planks", "Cobblestone", "Obsidian"};
    private Map m = new HashMap();
    private List n = new ArrayList();
    private BlockPos o;
    private long p;
    private boolean q;
    private RenderHelper27 r = new RenderHelper27(26293358099357L);

    public AutoSafeCart() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.k.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL287_0 -> ((String)this.a.getValue()).equals("Silent"), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Cart);
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.m.clear();
        this.n.clear();
        this.o = null;
        this.q = false;
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var7) {
            ItemStack var12 = var7.getHand().equals(Hand.MAIN_HAND) ? b.player.getMainHandStack() : b.player.getOffHandStack();
            if (var12.getItem() == Items.TNT_MINECART) {
                BlockHitResult var9 = var7.getBlockHitResult();
                long var10 = System.currentTimeMillis();
                this.n.add(new LongData2(var9.getBlockPos(), var10));
                this.n.add(new LongData2(WorldUtil2.a_class_2338______(new Object[]{var9, 110292667014705L}), var10));
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        this.k(new Object[]{24129908539005L});
        if (((String)this.a.getValue()).equals("Legit") && b.player != null && b.world != null) {
            if (!b.player.isUsingItem() && !Prestige.o.d_boolean______(new Object[0])) {
                Block var29 = this.a_class_2248(new Object[]{73622188516302L});
                if (var29 != null) {
                    List var30 = this.c(new Object[]{104766875730671L});
                    this.j(new Object[]{var30, 30230479421378L});
                    if (!var30.isEmpty() && this.r.b(new Object[]{this.h, 89714527855213L})) {
                        if (b.crosshairTarget instanceof BlockHitResult var31) {
                            BlockPos var38 = !WorldUtil2.b(new Object[]{var31.getBlockPos(), Blocks.FIRE, 91415361137757L})
                                    && !WorldUtil2.b(
                                        new Object[]{var31.getBlockPos(), WorldUtil2.a_class_2248_(new Object[]{12315318725950L}), 91415361137757L}
                                    )
                                ? WorldUtil2.a_class_2338______(new Object[]{var31, 110292667014705L})
                                : var31.getBlockPos();
                            if (b.world.getBlockState(var38).isReplaceable() && !WorldUtil2.g(new Object[]{var38, 87119771691358L})) {
                                if (this.o == null || !this.o.equals(var38)) {
                                    BlockPos var33 = this.a_class_2338___(new Object[]{115862723372271L});

                                    for (VecDoubleData var35 : (Iterable<VecDoubleData>)(var30)) {
                                        DamageUtil var10000 = Prestige.f;
                                        ClientPlayerEntity var10001 = b.player;
                                        Vec3d var10002 = var35.ap();
                                        double var10003 = var35.aq();
                                        Object[] var10009 = new Object[]{null, null, null, var33, var38, var29.getDefaultState(), 12057437191772L};
                                        var10009[2] = var10003;
                                        var10009[1] = var10002;
                                        var10009[0] = var10001;
                                        double var36 = var10000.h(var10009);
                                        if (!(var36 >= var35.ar() - 0.5)
                                            && this.a_boolean_______________(new Object[]{var29, new DoubleData(var38, var31, var36), var35, 118652811801300L})
                                            )
                                         {
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
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 116785209433731L;
        long var6 = var2 ^ 14839365373047L;
        long var8 = var2 ^ 93407410278523L;
        long var10 = var2 ^ 18427541941590L;
        long var12 = var2 ^ 39997114424685L;
        long var14 = var2 ^ 33065978990036L;
        long var16 = var2 ^ 4946510141917L;
        long var18 = var2 ^ 101816702374277L;
        if (((String)this.a.getValue()).equals("Silent") && b.player != null && b.world != null) {
            if (!b.player.isUsingItem() && !Prestige.o.d_boolean______(new Object[0])) {
                Block var21 = this.a_class_2248(new Object[]{var6});
                if (var21 == null) {
                    return null;
                } else {
                    List var22 = this.c(new Object[]{var10});
                    this.j(new Object[]{var22, var8});
                    if (!var22.isEmpty() && this.r.b(new Object[]{this.h, var14})) {
                        for (VecDoubleData var24 : (Iterable<VecDoubleData>)(var22)) {
                            DoubleData var25 = this.a_DoubleData(new Object[]{var24, var21, var16});
                            if (var25 != null) {
                                RenderBatch var26 = Prestige.o.a_RenderBatch(new Object[]{var25.am().getPos(), var18});
                                float var27 = MathHelper.wrapDegrees(var26.a(new Object[0]) - b.player.getYaw());
                                if (!(var27 > (Float)this.k.getValue()) && !(var27 < -(Float)this.k.getValue())) {
                                    if (var26.a_boolean________________________________________(new Object[0])) {
                                        return var26;
                                    }

                                    HitResult var28 = WorldUtil.a_class_239_(new Object[]{var26, var4});
                                    if (!(var28.getPos().distanceTo(var25.am().getPos()) > 0.5)
                                        && this.a_boolean_______________(new Object[]{var21, var25, var24, var12})) {
                                        return var26;
                                    }
                                }
                            }
                        }

                        return null;
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private List c(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 72247364469653L ^ var2;
        long var4 = var2 ^ 38924065942550L;
        long var6 = var2 ^ 22036425867046L;
        long var8 = var2 ^ 129149756477191L;
        long var10 = var2 ^ 131520260957589L;
        long var12 = var2 ^ 67848603377868L;
        ArrayList var15 = new ArrayList();
        Event.b();
        Vec3d var16 = PositionUtil.b(new Object[]{var8});
        BlockPos var17 = this.a_class_2338___(new Object[]{var10});

        for (Entity var19 : b.world.getEntities()) {
            if (var19 instanceof TntMinecartEntity var20) {
                Vec3d var21 = PositionUtil.a(new Object[]{var20, var4});
                if (!(var16.distanceTo(var21) > ((Float)this.j.getValue()).floatValue())
                    && this.d_boolean_(new Object[]{var20, var12})
                    && (Integer)this.m.getOrDefault(var20.getUuid(), 0) < (Integer)this.g.getValue()) {
                    double var22 = Math.hypot(var20.getVelocity().x, var20.getVelocity().z);
                    DamageUtil var10000 = Prestige.f;
                    ClientPlayerEntity var10001 = b.player;
                    Object[] var10009 = new Object[]{null, null, null, var17, null, null, var6};
                    var10009[2] = var22;
                    var10009[1] = var21;
                    var10009[0] = var10001;
                    double var24 = var10000.h(var10009);
                    if (!(var24 < ((Float)this.d.getValue()).floatValue()) && !(var24 <= ((Float)this.e.getValue()).floatValue())) {
                        var15.add(new VecDoubleData(var20, var21, var22, var24));
                    }
                }
            }
        }

        var15.sort((lpL286_0, lpL286_1) -> Double.compare(((dev.zprestige.prestige.data.VecDoubleData)lpL286_1).ar(), ((dev.zprestige.prestige.data.VecDoubleData)lpL286_0).ar()));
        return var15;
    }

    private List b_List__(Object[] var1) {
        TntMinecartEntity var2 = (TntMinecartEntity)var1[0];
        LinkedHashSet var6 = new LinkedHashSet();
        BlockPos var7 = b.player.getBlockPos();
        BlockPos var8 = var2.getBlockPos();
        BlockPos[] var9 = new BlockPos[]{var7, var7.up(), var8, var8.up()};
        BlockPos[] var10 = var9;
        int var11 = var9.length;
        Event.b();

        for (int var12 = 0; var12 < var11; var12++) {
            BlockPos var13 = var10[var12];

            for (int var14 = -1; var14 <= 1; var14++) {
                for (int var15 = -1; var15 <= 1; var15++) {
                    if (var14 != 0 || var15 != 0) {
                        var6.add(var13.add(var14, 0, var15));
                    }
                }
            }
        }

        return new ArrayList(var6);
    }

    private DoubleData a_DoubleData(Object[] var1) {
        VecDoubleData var2 = (VecDoubleData)var1[0];
        Block var3 = (Block)var1[1];
        long var4 = (Long)var1[2];
        var4 = 72247364469653L ^ var4;
        long var6 = var4 ^ 75557382509474L;
        long var8 = var4 ^ 786192419757L;
        long var10 = var4 ^ 108990155641118L;
        long var12 = var4 ^ 117666535715452L;
        BlockPos var15 = this.a_class_2338___(new Object[]{var10});
        Event.b();
        DoubleData var16 = null;

        for (BlockPos var18 : (Iterable<BlockPos>)(this.b_List__(new Object[]{var2.ao(), var12}))) {
            BlockHitResult var19 = this.a_class_3965_____(new Object[]{var18, var6});
            if (var19 != null) {
                DamageUtil var10000 = Prestige.f;
                ClientPlayerEntity var10001 = b.player;
                Vec3d var10002 = var2.ap();
                double var10003 = var2.aq();
                Object[] var10009 = new Object[]{null, null, null, var15, var18, var3.getDefaultState(), var8};
                var10009[2] = var10003;
                var10009[1] = var10002;
                var10009[0] = var10001;
                double var20 = var10000.h(var10009);
                if (!(var20 >= var2.ar() - 0.5) && (var16 == null || var20 < var16.an())) {
                    var16 = new DoubleData(var18, var19, var20);
                }
            }
        }

        return var16;
    }

    private BlockHitResult a_class_3965_____(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        long var3 = (Long)var1[1];
        var3 = 72247364469653L ^ var3;
        long var5 = var3 ^ 70854217993368L;
        long var7 = var3 ^ 33335450342208L;
        if (b.world.getBlockState(var2).isReplaceable() && !WorldUtil2.g(new Object[]{var2, var5})) {
            if (this.o != null && this.o.equals(var2)) {
                return null;
            } else {
                Vec3d var10 = b.player.getEyePos();
                short var10000 = 824;
                float var11 = InventoryUtil2.b_float_____(new Object[]{var7});
                Direction[] var12 = new Direction[]{Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP};

                for (Direction var16 : var12) {
                    BlockPos var17 = var2.offset(var16);
                    if (!b.world.getBlockState(var17).isReplaceable()) {
                        Direction var18 = var16.getOpposite();
                        Vec3d var19 = var17.toCenterPos().add(var18.getOffsetX() * 0.5, var18.getOffsetY() * 0.5, var18.getOffsetZ() * 0.5);
                        if (!(var10.distanceTo(var19) > var11)) {
                            BlockHitResult var20 = b.world.raycast(new RaycastContext(var10, var19, ShapeType.COLLIDER, FluidHandling.NONE, b.player));
                            if (var20 != null && !(var20.getPos().distanceTo(var19) > 0.1F)) {
                                return new BlockHitResult(var19, var18, var17, false);
                            }
                        }
                    }
                }

                return null;
            }
        } else {
            return null;
        }
    }

    private boolean a_boolean_______________(Object[] var1) {
        Block var3 = (Block)var1[0];
        DoubleData var6 = (DoubleData)var1[1];
        VecDoubleData var2 = (VecDoubleData)var1[2];
        long var4 = (Long)var1[3];
        var4 = 72247364469653L ^ var4;
        long var7 = var4 ^ 138090837969603L;
        long var9 = var4 ^ 139965620301532L;
        long var11 = var4 ^ 89428057324972L;
        long var13 = var4 ^ 28268871843744L;
        long var15 = var4 ^ 19536884003615L;
        boolean var18;
        if (((String)this.i.getValue()).equals("None")) {
            if (!InventoryUtil.l(new Object[]{var3.asItem(), var15})) {
                return false;
            }

            var18 = InventoryUtil2.a(new Object[]{var6.am(), var7});
        } else {
            Runnable var10001 = () -> InventoryUtil2.a(new Object[]{var6.am(), 96519249476482L});
            boolean var10002 = ((String)this.i.getValue()).equals("Silent");
            Object[] var10005 = new Object[]{null, null, null, var11};
            var10005[2] = var10002;
            var10005[1] = var10001;
            var10005[0] = var3;
            var18 = InventoryUtil.k(var10005);
        }

        if (var18) {
            this.m.merge(var2.ao().getUuid(), 1, (java.util.function.BiFunction)(a_, b_) -> Integer.sum((Integer)a_, (Integer)b_));
            this.o = var6.al();
            this.p = System.currentTimeMillis();
            this.r.a_void_____________________(new Object[]{var9});
            this.h.reroll(new Object[]{var13});
        }

        return var18;
    }

    private Block a_class_2248(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 72247364469653L ^ var2;
        long var4 = var2 ^ 102651330164453L;
        long var6 = var2 ^ 44830641924273L;
        long var8 = var2 ^ 19383162669128L;
        Event.b();
        boolean var11 = ((String)this.i.getValue()).equals("None");

        for (String var15 : l) {
            if (this.f.isSelected(new Object[]{var15, var6})) {
                Block var16 = switch (var15) {
                    case "Logs" -> {
                        TagKey var22 = ItemTags.LOGS;
                        Object[] var25 = new Object[]{null, null, var4};
                        var25[1] = var11;
                        var25[0] = var22;
                        yield this.c_class_2248(var25);
                    }
                    case "Planks" -> {
                        TagKey var21 = ItemTags.PLANKS;
                        Object[] var24 = new Object[]{null, null, var4};
                        var24[1] = var11;
                        var24[0] = var21;
                        yield this.c_class_2248(var24);
                    }
                    case "Cobblestone" -> {
                        Block var20 = Blocks.COBBLESTONE;
                        Object[] var23 = new Object[]{null, null, var8};
                        var23[1] = var11;
                        var23[0] = var20;
                        yield this.b_class_2248(var23);
                    }
                    case "Obsidian" -> {
                        Block var10001 = Blocks.OBSIDIAN;
                        Object[] var10005 = new Object[]{null, null, var8};
                        var10005[1] = var11;
                        var10005[0] = var10001;
                        yield this.b_class_2248(var10005);
                    }
                    default -> null;
                };
                if (var16 != null) {
                    return var16;
                }
            }
        }

        return null;
    }

    private Block b_class_2248(Object[] var1) {
        Block var4 = (Block)var1[0];
        boolean var5 = (Boolean)var1[1];
        long var2 = (Long)var1[2];
        var2 = 72247364469653L ^ var2;
        long var6 = var2 ^ 116383165242000L;
        long var8 = var2 ^ 115237515274712L;
        if (var5) {
            return InventoryUtil.l(new Object[]{var4.asItem(), var8}) ? var4 : null;
        } else {
            return InventoryUtil.a_Integer(new Object[]{var4.asItem(), var6}) != null ? var4 : null;
        }
    }

    private Block c_class_2248(Object[] var1) {
        TagKey var5 = (TagKey)var1[0];
        boolean var4 = (Boolean)var1[1];
        long var2 = (Long)var1[2];
        var2 = 72247364469653L ^ var2;
        long var6 = var2 ^ 126246368664270L;
        if (var4) {
            return this.d(new Object[]{b.player.getMainHandStack(), var5, var6});
        } else {
            for (int var9 = 0; var9 <= 8; var9++) {
                Block var10 = this.d(new Object[]{b.player.getInventory().getStack(var9), var5, var6});
                if (var10 != null) {
                    return var10;
                }
            }

            return null;
        }
    }

    private Block d(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        TagKey var5 = (TagKey)var1[1];
        return !var2.isEmpty() && var2.isIn(var5) && var2.getItem() instanceof BlockItem var7 ? var7.getBlock() : null;
    }

    private boolean d_boolean_(Object[] var1) {
        TntMinecartEntity var4 = (TntMinecartEntity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 72247364469653L ^ var2;
        long var5 = var2 ^ 41658254133611L;
        if (((String)this.c.getValue()).equals("Both")) {
            return true;
        } else {
            boolean var8 = this.e(new Object[]{var4, var5});
            return ((String)this.c.getValue()).equals("Only Self") ? var8 : !var8;
        }
    }

    private boolean e(Object[] var1) {
        TntMinecartEntity var2 = (TntMinecartEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 72247364469653L ^ var3;
        long var5 = var3 ^ 65081061148081L;
        Vec3d var8 = PositionUtil.a(new Object[]{var2, var5});
        Event.b();
        long var9 = System.currentTimeMillis();

        for (LongData2 var12 : (Iterable<LongData2>)(this.n)) {
            if (var9 - var12.ak() <= 5000L && var8.isInRange(var12.aj().toCenterPos(), 1.5)) {
                return true;
            }
        }

        return false;
    }

    private void j(Object[] var1) {
        List var2 = (List)var1[0];
        long var3 = (Long)var1[1];
        var3 = 72247364469653L ^ var3;
        long var5 = var3 ^ 17424134595530L;
        long var7 = var3 ^ 115591702524598L;
        boolean var10 = !var2.isEmpty();
        if (var10 && !this.q) {
            this.r.a_void_____________________(new Object[]{var5});
            this.h.reroll(new Object[]{var7});
        }

        this.q = var10;
    }

    private BlockPos a_class_2338___(Object[] var1) {
        if (this.o == null) {
            return null;
        } else if (System.currentTimeMillis() - this.p <= 1000L && b.world.getBlockState(this.o).isReplaceable()) {
            return this.o;
        } else {
            this.o = null;
            return null;
        }
    }

    private void k(Object[] var1) {
        if (b.world != null) {
            if (!this.m.isEmpty()) {
                HashSet var5 = new HashSet();

                for (Entity var7 : b.world.getEntities()) {
                    if (var7 instanceof TntMinecartEntity) {
                        var5.add(var7.getUuid());
                    }
                }

                this.m.keySet().removeIf(lpL284_0 -> !var5.contains(((java.util.UUID)lpL284_0)));
            }

            long var8 = System.currentTimeMillis();
            this.n.removeIf(lpL283_0 -> var8 - ((dev.zprestige.prestige.data.LongData2)lpL283_0).ak() > 5000L);
        }
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
        this.m.clear();
        this.n.clear();
        this.o = null;
        this.q = false;
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
