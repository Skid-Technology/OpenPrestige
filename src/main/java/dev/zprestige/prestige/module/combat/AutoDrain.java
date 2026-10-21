package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class AutoDrain extends Module implements RenderContract3 {
    private EnumSetting a;
    private MultiSetting c;
    private RangeSetting d;
    private EnumSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private FloatSetting i;
    private ArrayList j = new ArrayList();
    private ArrayList k = new ArrayList();
    private int l = -1;
    private boolean m = false;
    private RenderHelper27 n = new RenderHelper27(26293358099357L);

    public AutoDrain() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.h
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL225_0 -> ((String)this.e.getValue()).equals("Normal") && (Boolean)this.g.getValue(), 73196168581295L}
            );
        this.i.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL224_0 -> ((String)this.a.getValue()).equals("Silent Aim"), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 45286455980699L;
        long var6 = var2 ^ 91082959635357L;
        this.l = -1;
        this.m = false;
        this.n.a_void_____________________(new Object[]{var4});
        Prestige.o.e(new Object[]{this, var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 82893747353842L;
        long var6 = var2 ^ 140372736859409L;
        long var8 = var2 ^ 6195065515282L;
        if ((Boolean)this.h.getValue() && this.l != -1) {
            if (Prestige.o.d_boolean______(new Object[0])) {
                Renderer2D var10000 = Prestige.o;
                int var10001 = this.l;
                Object[] var10004 = new Object[]{null, var4};
                var10004[0] = var10001;
                var10000.i(var10004);
            } else {
                int var10 = this.l;
                Object[] var10003 = new Object[]{null, var8};
                var10003[0] = var10;
                InventoryUtil.a_void________(var10003);
            }

            this.l = -1;
        }

        this.k.clear();
        this.m = false;
        Prestige.o.f(new Object[]{this, var6});
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.j.clear();
        this.k.clear();
        this.l = -1;
        this.m = false;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        this.j.removeIf(lpL223_0 -> !this.g(new Object[]{b.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL223_0)).getBlock(), 12889581833764L}));
        Event.b();
        this.k.removeIf(lpL222_0 -> !this.g(new Object[]{b.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL222_0)).getBlock(), 12889581833764L}));
        if (((String)this.a.getValue()).equals("Legit")) {
            if (!b.player.isUsingItem()) {
                if (!Prestige.o.d_boolean______(new Object[0])) {
                    if (this.m && this.l != -1) {
                        if (this.n.b(new Object[]{this.d, 89714527855213L})) {
                            if ((Boolean)this.h.getValue() && (Boolean)this.g.getValue()) {
                                int var10000 = this.l;
                                Object[] var10003 = new Object[]{null, 37392877673079L};
                                var10003[0] = var10000;
                                InventoryUtil.a_void________(var10003);
                            }

                            this.l = -1;
                            this.m = false;
                            this.n.a_void_____________________(new Object[]{93540861878173L});
                            this.d.reroll(new Object[]{57083216809697L});
                        }
                    } else if (this.n.b(new Object[]{this.d, 89714527855213L})) {
                        if (InventoryUtil.a_Integer(new Object[]{Items.BUCKET, 64376993919254L}) != null
                            || (Boolean)this.f.getValue() && InventoryUtil.a_Integer(new Object[]{Items.COBWEB, 64376993919254L}) != null) {
                            PlayerEntity var27 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
                            if (var27 != null
                                && !(PositionUtil.a(new Object[]{var27, 67736483458412L}).distanceTo(PositionUtil.b(new Object[]{118247215910525L})) > 10.0)) {
                                BlockHitResult var28 = this.a_class_3965____(new Object[]{25296333506079L});
                                if (var28 != null) {
                                    BlockPos var29 = var28.getBlockPos();
                                    if (this.e(new Object[]{var29, 3019936591966L})) {
                                        if (this.a_boolean_____________(new Object[]{var29, var28, 43510323930796L})) {
                                            this.n.a_void_____________________(new Object[]{93540861878173L});
                                            this.d.reroll(new Object[]{57083216809697L});
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
        long var6 = var2 ^ 114452390742293L;
        long var8 = var2 ^ 29247161167908L;
        long var10 = var2 ^ 128670251638447L;
        long var12 = var2 ^ 125420266980053L;
        long var14 = var2 ^ 33065978990036L;
        long var16 = var2 ^ 120434008667598L;
        long var18 = var2 ^ 136752692109656L;
        long var20 = var2 ^ 101816702374277L;
        long var22 = var2 ^ 102846228487495L;
        long var24 = var2 ^ 71343916077473L;
        long var26 = var2 ^ 39587186614724L;
        if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else if (!((String)this.a.getValue()).equalsIgnoreCase("Silent Aim")) {
            return null;
        } else if (b.player.isUsingItem()) {
            return null;
        } else if (this.m && this.l != -1) {
            if (!this.n.b(new Object[]{this.d, var14})) {
                return null;
            } else {
                if ((Boolean)this.h.getValue() && (Boolean)this.g.getValue()) {
                    int var10000 = this.l;
                    Object[] var10003 = new Object[]{null, var16};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                }

                this.l = -1;
                this.m = false;
                this.n.a_void_____________________(new Object[]{var8});
                this.d.reroll(new Object[]{var18});
                return null;
            }
        } else if (!this.n.b(new Object[]{this.d, var14})) {
            return null;
        } else if (InventoryUtil.a_Integer(new Object[]{Items.BUCKET, var10}) != null
            || (Boolean)this.f.getValue() && InventoryUtil.a_Integer(new Object[]{Items.COBWEB, var10}) != null) {
            PlayerEntity var29 = CombatUtil.a_class_1657(new Object[]{var24});
            if (var29 != null && !(PositionUtil.a(new Object[]{var29, var12}).distanceTo(PositionUtil.b(new Object[]{var26})) > 10.0)) {
                BlockHitResult var30 = this.b_class_3965(new Object[]{var22});
                if (var30 == null) {
                    return null;
                } else {
                    BlockPos var31 = var30.getBlockPos();
                    boolean var32 = InventoryUtil.a_Integer(new Object[]{Items.BUCKET, var10}) != null;
                    boolean var33 = !var32 && (Boolean)this.f.getValue();
                    RenderBatch var34;
                    if (var33) {
                        BlockPos var35 = var31.down();
                        Vec3d var36 = Vec3d.ofCenter(var35).add(0.0, 0.5, 0.0);
                        var34 = Prestige.o.a_RenderBatch(new Object[]{var36, var20});
                        HitResult var37 = WorldUtil.a_class_239_(new Object[]{var34, var4});
                        if (var37 == null || var37.getType() != Type.BLOCK || !(var37 instanceof BlockHitResult var38)) {
                            return null;
                        }

                        var30 = var38;
                    } else {
                        var34 = Prestige.o.a_RenderBatch(new Object[]{var30.getPos(), var20});
                    }

                    if (Math.abs(var34.a(new Object[0]) - b.player.getYaw()) > (Float)this.i.getValue()) {
                        return null;
                    } else if (var34.a_boolean________________________________________(new Object[0])) {
                        return var34;
                    } else {
                        Prestige.o.a(new Object[]{var34});
                        if (this.a_boolean_____________(new Object[]{var31, var30, var6})) {
                            this.n.a_void_____________________(new Object[]{var8});
                            this.d.reroll(new Object[]{var18});
                        }

                        return var34;
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var5) {
            ItemStack var8 = var5.getHand() == Hand.MAIN_HAND ? b.player.getMainHandStack() : b.player.getOffHandStack();
            if (var8.getItem() == Items.WATER_BUCKET || var8.getItem() == Items.LAVA_BUCKET) {
                BlockHitResult var7 = var5.getBlockHitResult();
                this.j.add(var7.getBlockPos().add(var7.getSide().getOffsetX(), var7.getSide().getOffsetY(), var7.getSide().getOffsetZ()));
            }
        }
    }

    private boolean a_boolean_____________(Object[] var1) {
        BlockPos var5 = (BlockPos)var1[0];
        BlockHitResult var2 = (BlockHitResult)var1[1];
        long var3 = (Long)var1[2];
        var3 = 63790212518096L ^ var3;
        long var6 = var3 ^ 42993533405034L;
        long var8 = var3 ^ 443127019328L;
        long var10 = var3 ^ 17128426033502L;
        long var12 = var3 ^ 114043070288344L;
        long var14 = var3 ^ 41839228367906L;
        long var16 = var3 ^ 52734334465923L;
        if (this.k.contains(var5)) {
            return false;
        } else {
            boolean var19 = InventoryUtil.a_Integer(new Object[]{Items.BUCKET, var6}) != null;
            if (var19) {
                if (!InventoryUtil.l(new Object[]{Items.BUCKET, var14})) {
                    if (((String)this.e.getValue()).equals("None")) {
                        return false;
                    } else {
                        if (this.l == -1) {
                            this.l = InventoryUtil.a_int_____(new Object[]{var12});
                        }

                        InventoryUtil.h(new Object[]{Items.BUCKET, var16});
                        return true;
                    }
                } else {
                    if ((Boolean)this.g.getValue()) {
                        InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var10});
                        this.m = true;
                    }

                    this.k.add(var5);
                    return true;
                }
            } else {
                return (Boolean)this.f.getValue() && this.d(new Object[]{var5, var2, var8});
            }
        }
    }

    private boolean d(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        BlockHitResult var3 = (BlockHitResult)var1[1];
        long var4 = (Long)var1[2];
        var4 = 63790212518096L ^ var4;
        long var6 = var4 ^ 123407406426734L;
        long var8 = var4 ^ 32406783516922L;
        long var10 = var4 ^ 103198470256200L;
        long var12 = var4 ^ 31258918818738L;
        long var14 = var4 ^ 23731168432147L;
        if (!(Boolean)this.g.getValue()) {
            return false;
        } else if (!InventoryUtil.l(new Object[]{Items.COBWEB, var12})) {
            if (((String)this.e.getValue()).equals("None")) {
                return false;
            } else if (InventoryUtil.a_Integer(new Object[]{Items.COBWEB, var8}) == null) {
                return false;
            } else {
                if (this.l == -1) {
                    this.l = InventoryUtil.a_int_____(new Object[]{var10});
                }

                InventoryUtil.h(new Object[]{Items.COBWEB, var14});
                return true;
            }
        } else {
            if (!((String)this.a.getValue()).equals("Silent Aim")) {
                if (!(b.crosshairTarget instanceof BlockHitResult)) {
                    return false;
                }

                var3 = (BlockHitResult)b.crosshairTarget;
            }

            InventoryUtil2.a(new Object[]{var3, var6});
            this.k.add(var2);
            this.m = this.l != -1;
            return true;
        }
    }

    private BlockHitResult a_class_3965____(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 63790212518096L ^ var2;
        long var4 = var2 ^ 66079125987401L;
        Vec3d var7 = b.player.getEyePos();
        Vec3d var8 = b.player.getRotationVec(1.0F);
        Event.b();
        BlockHitResult var9 = b.world
            .raycast(
                new RaycastContext(
                    var7, var7.add(var8.multiply(InventoryUtil2.b_float_____(new Object[]{var4}))), ShapeType.OUTLINE, FluidHandling.SOURCE_ONLY, b.player
                )
            );
        return var9 != null && var9.getType() == Type.BLOCK ? var9 : null;
    }

    private BlockHitResult b_class_3965(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 63790212518096L ^ var2;
        long var4 = var2 ^ 35669101079288L;
        long var6 = var2 ^ 62791142167720L;
        long var8 = var2 ^ 46970791973488L;
        long var10 = var2 ^ 74270926269523L;
        Event.b();
        Vec3d var13 = b.player.getEyePos();

        for (BlockPos var15 : BlockPos.iterateOutwards(b.player.getBlockPos(), 5, 5, 5)) {
            if (!(PositionUtil.b(new Object[]{var10}).distanceTo(var15.toCenterPos()) > InventoryUtil2.b_float_____(new Object[]{var6}))
                && this.e(new Object[]{var15, var8})) {
                Box var16 = new Box(var15.getX(), var15.getY(), var15.getZ(), var15.getX() + 1, var15.getY() + 1, var15.getZ() + 1);
                if (!(var13.distanceTo(this.a_class_243______(new Object[]{var13, var16, var4})) > InventoryUtil2.b_float_____(new Object[]{var6}))) {
                    BlockHitResult var17 = b.world
                        .raycast(new RaycastContext(var13, var16.getCenter(), ShapeType.OUTLINE, FluidHandling.SOURCE_ONLY, b.player));
                    if (var17.getType() != Type.MISS && var17.getBlockPos().getManhattanDistance(var15) == 0) {
                        return var17;
                    }
                }
            }
        }

        return null;
    }

    private boolean e(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        long var3 = (Long)var1[1];
        var3 = 63790212518096L ^ var3;
        long var5 = var3 ^ 37664249976644L;
        long var7 = var3 ^ 56085364354730L;
        if (!this.g(new Object[]{b.world.getBlockState(var2).getBlock(), var7})) {
            return false;
        } else if (!b.world.getFluidState(var2).isStill()) {
            return false;
        } else if (this.k.contains(var2)) {
            return false;
        } else {
            return this.j.contains(var2) ? false : this.f(new Object[]{b.world.getBlockState(var2).getBlock(), var2, var5});
        }
    }

    private boolean f(Object[] var1) {
        Block var2 = (Block)var1[0];
        BlockPos var5 = (BlockPos)var1[1];
        long var3 = (Long)var1[2];
        var3 = 63790212518096L ^ var3;
        long var6 = var3 ^ 12624291196912L;
        long var8 = var3 ^ 76361982620965L;
        if (this.c.isSelected(new Object[]{"Water", var6}) && var2 == Blocks.WATER) {
            return true;
        } else if (this.c.isSelected(new Object[]{"Lava", var6}) && var2 == Blocks.LAVA) {
            return true;
        } else {
            return this.c.isSelected(new Object[]{"Water On Web", var6}) && var2 == Blocks.WATER && this.h(new Object[]{var5, var8})
                ? true
                : this.c.isSelected(new Object[]{"Lava On Web", var6}) && var2 == Blocks.LAVA && this.h(new Object[]{var5, var8});
        }
    }

    private boolean g(Object[] var1) {
        Block var4 = (Block)var1[0];
        return var4 == Blocks.WATER || var4 == Blocks.LAVA;
    }

    private boolean h(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        Event.b();

        for (Direction var9 : Direction.values()) {
            if (b.world.getBlockState(var4.offset(var9)).getBlock() == Blocks.COBWEB) {
                return true;
            }
        }

        return false;
    }

    private Vec3d a_class_243______(Object[] var1) {
        Vec3d var5 = (Vec3d)var1[0];
        Box var4 = (Box)var1[1];
        return new Vec3d(
            Math.max(var4.minX, Math.min(var5.x, var4.maxX)),
            Math.max(var4.minY, Math.min(var5.y, var4.maxY)),
            Math.max(var4.minZ, Math.min(var5.z, var4.maxZ))
        );
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
