package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper35;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.type.BowMode;
import dev.zprestige.prestige.type.RailState;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import org.lwjgl.glfw.GLFW;

public class AutoCart extends Module implements RenderContract3 {
    private IntSetting a;
    private IntSetting c;
    private IntSetting d;
    private IntSetting e;
    private KeybindSetting f;
    private IntSetting g;
    private KeybindSetting h;
    private RangeSetting i;
    private BoolSetting j;
    private EnumSetting k;
    private BoolSetting l;
    private BoolSetting m;
    private FloatSetting n;
    private IntSetting o;
    private BoolSetting p;
    private BoolSetting q;
    private BoolSetting r;
    private FloatSetting s;
    private BoolSetting t;
    private ColorSetting u;
    private static final Item[] v = new Item[]{Items.RAIL, Items.POWERED_RAIL, Items.DETECTOR_RAIL, Items.ACTIVATOR_RAIL};
    private static final Block[] w = new Block[]{Blocks.RAIL, Blocks.POWERED_RAIL, Blocks.DETECTOR_RAIL, Blocks.ACTIVATOR_RAIL};
    private static final Set x = Set.of(
        Items.OBSIDIAN, Items.CRYING_OBSIDIAN, Items.NETHERITE_BLOCK, Items.DEEPSLATE, Items.COBBLED_DEEPSLATE, Items.ENDER_CHEST, Items.RESPAWN_ANCHOR
    );
    private RailState y = RailState.IDLE;
    private BowMode z = BowMode.PASSIVE;
    private int A;
    private int B;
    private int C;
    private BlockPos D;
    private boolean E;
    private int F = -1;
    private int G = 0;
    private int H = 0;
    private boolean I = false;
    private boolean J = false;
    private boolean K = false;
    private boolean L = false;
    private int M = 0;
    private boolean N = false;
    private RenderHelper27 O = new RenderHelper27(26293358099357L);
    private int P = 0;
    private boolean Q = false;
    private BlockPos R = null;
    private boolean S = false;
    private TntMinecartEntity T;
    private boolean U = false;
    private RenderBatch V;
    private boolean W = false;
    private int X = -1;

    public AutoCart() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.h.setVisibility_KeybindSetting(new Object[]{(Predicate<Integer>)lpL115_0 -> (Integer)this.g.getValue() > 1, 112715924896781L});
        this.j.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL113_0 -> (Integer)this.g.getValue() > 1, 73196168581295L});
        this.i.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL114_0 -> (Integer)this.g.getValue() > 1, 37043306203287L});
        this.r.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL110_0 -> ((String)this.k.getValue()).equals("Trajectory"), 73196168581295L});
        this.s
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL109_0 -> ((String)this.k.getValue()).equals("Trajectory") && (Boolean)this.r.getValue(), 35046756052036L}
            );
        this.t.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL108_0 -> ((String)this.k.getValue()).equals("Trajectory"), 73196168581295L});
        this.n.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL112_0 -> (Boolean)this.m.getValue(), 35046756052036L});
        this.o.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL111_0 -> (Boolean)this.m.getValue(), 98265949679296L});
        this.u
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL107_0 -> ((String)this.k.getValue()).equals("Trajectory") && (Boolean)this.t.getValue(), 119980880492450L}
            );
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Cart);
    }

    private boolean a_boolean___________(Object[] var1) {
        Item var4 = (Item)var1[0];
        Item[] var6 = v;
        Event.b();

        for (Item var9 : var6) {
            if (var4 == var9) {
                return true;
            }
        }

        return false;
    }

    private boolean d(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        Block var6 = b.world.getBlockState(var4).getBlock();
        Event.b();

        for (Block var10 : w) {
            if (var6 == var10) {
                return true;
            }
        }

        return false;
    }

    private int d_int__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 2151103034484L ^ var2;
        long var4 = var2 ^ 75533614966463L;
        Event.b();

        for (int var7 = 0; var7 < 9; var7++) {
            if (this.a_boolean___________(new Object[]{b.player.getInventory().getStack(var7).getItem(), var4})) {
                return var7;
            }
        }

        return -1;
    }

    private int e(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (x.contains(var6.getItem())) {
                return var5;
            }
        }

        return -1;
    }

    private Integer a_Integer_(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 2151103034484L ^ var2;
        long var4 = var2 ^ 113928845082917L;
        long var6 = var2 ^ 43141582264215L;
        long var8 = var2 ^ 13999251216337L;
        long var10 = var2 ^ 39877035164204L;
        Event.b();
        Integer var13 = InventoryUtil.a_Integer(new Object[]{Items.TNT_MINECART, var4});
        if (var13 != null) {
            this.X = -1;
            return var13;
        } else {
            int var14 = -1;

            for (int var15 = 9; var15 < 36; var15++) {
                if (b.player.getInventory().getStack(var15).getItem() == Items.TNT_MINECART) {
                    var14 = var15;
                    break;
                }
            }

            if (var14 == -1) {
                return null;
            } else {
                int var19 = InventoryUtil.a_int_____(new Object[]{var6});
                int var16 = -1;

                for (int var17 = 0; var17 < 9; var17++) {
                    if (var17 != var19 && b.player.getInventory().getStack(var17).isEmpty()) {
                        var16 = var17;
                        break;
                    }
                }

                if (var16 == -1) {
                    return null;
                } else {
                    if (this.X != -1) {
                        if (Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{var8})) {
                            return this.X;
                        }

                        this.X = -1;
                    }

                    Object[] var10004 = new Object[]{null, null, var10};
                    var10004[1] = var16;
                    var10004[0] = var14;
                    InventoryUtil.f(var10004);
                    this.X = var16;
                    return var16;
                }
            }
        }
    }

    private int f(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (var6.getItem() == Items.CROSSBOW && var6.get(DataComponentTypes.CHARGED_PROJECTILES) != null) {
                return var5;
            }
        }

        return -1;
    }

    private boolean e_boolean_(Object[] var1) {
        ItemStack var4 = (ItemStack)var1[0];
        return var4.getItem() == Items.CROSSBOW && var4.get(DataComponentTypes.CHARGED_PROJECTILES) != null;
    }

    private void j(Object[] var1) {
        this.y = RailState.IDLE;
        this.z = BowMode.PASSIVE;
        this.F = -1;
        this.G = 0;
        this.H = 0;
        this.I = false;
        this.D = null;
        this.E = false;
        this.V = null;
        this.L = false;
        this.M = 0;
        this.N = false;
        this.P = 0;
        this.Q = false;
        this.R = null;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 112256863134219L;
        long var6 = var2 ^ 91082959635357L;
        Event.b();
        this.j(new Object[0]);
        this.J = (Integer)this.f.getValue() != -1 && this.f.isPressed(new Object[]{var4});
        this.K = (Integer)this.h.getValue() != -1 && this.h.isPressed(new Object[]{var4});
        this.T = null;
        this.U = false;
        this.S = false;
        this.W = false;
        Prestige.o.e(new Object[]{this, var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        long var6 = var2 ^ 120046844036687L;
        this.k(new Object[]{var6});
        this.j(new Object[0]);
        this.T = null;
        this.U = false;
        Prestige.o.f(new Object[]{this, var4});
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 2151103034484L ^ var2;
        long var4 = var2 ^ 60332513002826L;
        Event.b();
        int var7 = new RenderHelper35(b.options.useKey).a(new Object[]{var4}).getCode();
        boolean var8;
        if (var7 < 8) {
            var8 = GLFW.glfwGetMouseButton(b.getWindow().getHandle(), var7) == 1;
        } else {
            var8 = GLFW.glfwGetKey(b.getWindow().getHandle(), var7) == 1;
        }

        b.options.useKey.setPressed(var8);
    }

    private void l(Object[] var1) {
        Event.b();
        this.N = false;
        if (this.z == BowMode.BOW && !this.Q) {
            this.G = (Integer)this.a.getValue();
            this.y = RailState.SWITCH_WEAPON;
        } else {
            this.G = (Integer)this.c.getValue();
            this.y = RailState.WAIT_BACK;
        }
    }

    private void m(Object[] var1) {
        float var2 = (Float)var1[0];
        float var6 = (Float)var1[1];
        float var3 = (Float)var1[2];
        long var4 = (Long)var1[3];
        var4 = 2151103034484L ^ var4;
        long var7 = var4 ^ 137025550456991L;
        long var9 = var4 ^ 94787562211903L;
        long var11 = var4 ^ 58116189383383L;
        Event.b();
        Object[] var10007 = new Object[]{null, null, null, var9};
        var10007[2] = var2;
        var10007[1] = var3;
        var10007[0] = var6;
        BlockPos var14 = this.a_class_2338_(var10007);
        if (var14 != null
            && !this.h(new Object[]{var14.up(), var11})
            && !this.h(new Object[]{var14, var11})
            && b.world.getBlockState(var14.up()).isReplaceable()
            && this.f_boolean(new Object[]{var14, var7})) {
            this.D = var14;
            this.E = true;
            this.y = RailState.SWITCH_RAIL;
            this.G = 0;
        } else {
            this.j(new Object[0]);
        }
    }

    private void n(Object[] var1) {
        float var2 = (Float)var1[0];
        long var3 = (Long)var1[1];
        var3 = 2151103034484L ^ var3;
        long var5 = var3 ^ 23871919113384L;
        Event.b();
        RenderBatch var8 = Prestige.o.b_RenderBatch____________(new Object[0]);
        float var10002 = var8 != null ? var8.a(new Object[0]) : b.player.getYaw();
        float var10003 = var8 != null ? var8.b(new Object[0]) : b.player.getPitch();
        Object[] var10006 = new Object[]{null, null, null, var5};
        var10006[2] = var10003;
        var10006[1] = var10002;
        var10006[0] = var2;
        this.m(var10006);
    }

    private BlockPos a_class_2338_(Object[] var1) {
        float var4 = (Float)var1[0];
        float var2 = (Float)var1[1];
        float var3 = (Float)var1[2];
        if (b.player != null && b.world != null) {
            Vec3d var8 = b.player.getEyePos();
            double var9 = var8.x;
            double var11 = var8.y - 0.1;
            double var13 = var8.z;
            double var15 = Math.toRadians(var4);
            double var17 = Math.toRadians(var2);
            double var19 = -MathHelper.sin((float)var15) * MathHelper.cos((float)var17);
            double var21 = -MathHelper.sin((float)var17);
            double var23 = MathHelper.cos((float)var15) * MathHelper.cos((float)var17);
            double var25 = Math.sqrt(var19 * var19 + var21 * var21 + var23 * var23);
            if (var25 == 0.0) {
                return null;
            } else {
                var19 /= var25;
                var21 /= var25;
                var23 /= var25;
                float var27 = MathHelper.clamp(var3, 0.0F, 1.05F) * 3.0F;
                var19 *= var27;
                var21 *= var27;
                var23 *= var27;
                var19 += b.player.getVelocity().x;
                var23 += b.player.getVelocity().z;
                if (!b.player.isOnGround()) {
                    var21 += b.player.getVelocity().y;
                }

                for (int var28 = 0; var28 < 300; var28++) {
                    Vec3d var29 = new Vec3d(var9, var11, var13);
                    var9 += var19;
                    var11 += var21;
                    var13 += var23;
                    var19 *= 0.99;
                    var21 *= 0.99;
                    var23 *= 0.99;
                    var21 -= 0.05;
                    Vec3d var30 = new Vec3d(var9, var11, var13);
                    BlockHitResult var31 = b.world.raycast(new RaycastContext(var29, var30, ShapeType.OUTLINE, FluidHandling.NONE, b.player));
                    if (var31 != null && var31.getType() == Type.BLOCK) {
                        return var31.getBlockPos();
                    }

                    if (var11 < -65.0) {
                        break;
                    }
                }

                return null;
            }
        } else {
            return null;
        }
    }

    private RenderBatch b_RenderBatch(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        float var3 = (Float)var1[1];
        long var4 = (Long)var1[2];
        var4 = 2151103034484L ^ var4;
        long var6 = var4 ^ 23701897999716L;
        long var8 = var4 ^ 94611187447668L;
        Event.b();
        Vec3d var11 = b.player.getEyePos();
        double var12 = var2.x - var11.x;
        double var14 = var2.z - var11.z;
        float var16 = (float)Math.toDegrees(Math.atan2(var14, var12)) - 90.0F;
        ArrayList var17 = new ArrayList();
        double var18 = Double.MAX_VALUE;

        for (float var20 = -89.0F; var20 <= 60.0F; var20 += 0.5F) {
            Object[] var10006 = new Object[]{null, null, null, var8};
            var10006[2] = var3;
            var10006[1] = var20;
            var10006[0] = var16;
            BlockPos var21 = this.a_class_2338_(var10006);
            if (var21 != null) {
                double var22 = var21.toCenterPos().squaredDistanceTo(var2);
                var17.add(new float[]{var20, (float)var22});
                if (var22 < var18) {
                    var18 = var22;
                }
            }
        }

        if (var18 > 4.0) {
            return null;
        } else {
            double var29 = var18 + 0.25;
            var17.removeIf(lpL106_0 -> ((float[])lpL106_0)[1] > var29);
            int var30 = Math.min((int)Math.round(((Integer)this.e.getValue()).intValue() / 100.0 * (var17.size() - 1)), var17.size() - 1);
            float var23 = ((float[])var17.get(var30))[0];

            for (float var24 = var23 - 0.5F; var24 <= var23 + 0.5F; var24 += 0.05F) {
                Object[] var33 = new Object[]{null, null, null, var8};
                var33[2] = var3;
                var33[1] = var24;
                var33[0] = var16;
                BlockPos var25 = this.a_class_2338_(var33);
                if (var25 != null) {
                    double var26 = var25.toCenterPos().squaredDistanceTo(var2);
                    if (var26 < var18) {
                        var18 = var26;
                        var23 = var24;
                    }
                }
            }

            RenderBatch var31 = Prestige.o.a_RenderBatch(new Object[]{var2, var6});
            if (var31 == null) {
                return new RenderBatch(var16, var23);
            } else {
                RenderBatch var32 = new RenderBatch(var31.a(new Object[0]), var23);
                var32.c(new Object[]{var31.a_boolean________________________________________(new Object[0])});
                return var32;
            }
        }
    }

    private void o(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        if (b.interactionManager.interactBlock(b.player, Hand.MAIN_HAND, var2).isAccepted()) {
            b.player.swingHand(Hand.MAIN_HAND);
            Prestige.o.g(new Object[]{true});
        }
    }

    private boolean f_boolean(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        long var3 = (Long)var1[1];
        var3 = 2151103034484L ^ var3;
        long var5 = var3 ^ 117445088717950L;
        Vec3d var8 = b.player.getEyePos();
        Event.b();
        double var9 = InventoryUtil2.b_float_____(new Object[]{var5});
        double var11 = var2.getX() + 0.5 - var8.x;
        double var13 = var2.getY() + 1.0 - var8.y;
        double var15 = var2.getZ() + 0.5 - var8.z;
        return var11 * var11 + var13 * var13 + var15 * var15 <= var9 * var9;
    }

    private boolean g(Object[] var1) {
        double var2 = (Double)var1[0];
        double var4 = (Double)var1[1];
        double var6 = (Double)var1[2];
        long var8 = (Long)var1[3];
        var8 = 2151103034484L ^ var8;
        long var10 = var8 ^ 124137653274303L;
        Event.b();
        Vec3d var13 = b.player.getEyePos();
        double var14 = InventoryUtil2.b_float_____(new Object[]{var10});
        double var16 = var2 - var13.x;
        double var18 = var4 - var13.y;
        double var20 = var6 - var13.z;
        return var16 * var16 + var18 * var18 + var20 * var20 <= var14 * var14;
    }

    private boolean h(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        if (b.world == null) {
            return false;
        } else {
            for (Entity var7 : b.world.getOtherEntities(null, new Box(var4))) {
                if (var7 instanceof TntMinecartEntity) {
                    return true;
                }
            }

            return false;
        }
    }

    private TntMinecartEntity a_class_1701(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 2151103034484L ^ var2;
        long var4 = var2 ^ 126914937642381L;
        if (b.world != null && b.player != null) {
            Vec3d var7 = b.player.getEyePos();
            float var8 = (Float)this.n.getValue();
            TntMinecartEntity var9 = null;
            double var10 = Double.MAX_VALUE;
            Vec3d var12 = b.player.getRotationVec(1.0F);

            for (Entity var14 : b.world.getEntities()) {
                if (var14 instanceof TntMinecartEntity var15) {
                    double var16 = var7.distanceTo(PositionUtil.a(new Object[]{var15, var4}));
                    if (!(var16 > var8)) {
                        Vec3d var18 = PositionUtil.a(new Object[]{var15, var4}).subtract(var7);
                        double var19 = Math.sqrt(var18.x * var18.x + var18.z * var18.z);
                        if (!(var19 < 1.0E-6)) {
                            Vec3d var21 = var18.normalize();
                            double var22 = var12.dotProduct(var21);
                            double var24 = Math.acos(MathHelper.clamp((float)var22, -1.0F, 1.0F));
                            double var26 = var24 * 8.0 + var16 * 0.05;
                            if (var26 < var10) {
                                var10 = var26;
                                var9 = var15;
                            }
                        }
                    }
                }
            }

            return var9;
        } else {
            return null;
        }
    }

    private boolean i(Object[] var1) {
        if (b.player == null) {
            return false;
        } else {
            return !b.player.isUsingItem() ? false : b.player.getActiveItem().getItem() == Items.BOW;
        }
    }

    private int g_int(Object[] var1) {
        return Math.max((Integer)this.d.getValue(), 3);
    }

    private boolean j_boolean_(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 2151103034484L ^ var2;
        long var4 = var2 ^ 116821631026682L;
        return (Integer)this.g.getValue() > 1 && (Integer)this.h.getValue() != -1 && this.h.isPressed(new Object[]{var4});
    }

    private float b_float_________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 2151103034484L ^ var2;
        long var4 = var2 ^ 16964792086561L;
        long var6 = var2 ^ 66307203495936L;
        long var8 = var2 ^ 102275328517213L;
        if (this.f(new Object[]{var6}) != -1) {
            return 1.0500001F;
        } else {
            return InventoryUtil.a_Integer(new Object[]{Items.BOW, var4}) != null ? BowItem.getPullProgress(this.g_int(new Object[]{var8})) : -1.0F;
        }
    }

    private boolean k_boolean(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 2151103034484L ^ var2;
        long var4 = var2 ^ 687027227435L;
        if (b.player == null) {
            return false;
        } else {
            ItemStack var7 = b.player.getMainHandStack();
            return var7.getItem() == Items.BOW ? true : this.e_boolean_(new Object[]{var7, var4});
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if (this.S) {
            this.S = false;
        } else if ((Boolean)this.l.getValue()) {
            if (this.y == RailState.IDLE) {
                if (!this.W) {
                    if (b.player != null && b.world != null) {
                        ItemStack var21 = b.player.getMainHandStack();
                        Item var22 = var21.getItem();
                        boolean var23 = false;
                        float var24 = 1.0F;
                        if (var1.a(new Object[0]) instanceof PlayerActionC2SPacket var25 && var25.getAction() == Action.RELEASE_USE_ITEM && var22 == Items.BOW) {
                            int var28 = b.player.getItemUseTime();
                            if (var28 < 4) {
                                return;
                            }

                            var23 = true;
                            var24 = BowItem.getPullProgress(var28);
                        } else if (var1.a(new Object[0]) instanceof PlayerInteractItemC2SPacket && this.e_boolean_(new Object[]{var21, 7371155366939L})) {
                            var23 = true;
                            var24 = 1.0500001F;
                        }

                        if (var23) {
                            if (this.d_int__(new Object[]{43758232836049L}) != -1) {
                                if (this.a_Integer_(new Object[]{102143318459463L}) != null) {
                                    BlockPos var27 = null;
                                    if (((String)this.k.getValue()).equals("Trajectory")) {
                                        float var10001 = b.player.getYaw();
                                        float var10002 = b.player.getPitch();
                                        Object[] var10006 = new Object[]{null, null, null, 88589708016684L};
                                        var10006[2] = var24;
                                        var10006[1] = var10002;
                                        var10006[0] = var10001;
                                        var27 = this.a_class_2338_(var10006);
                                        if (var27 == null) {
                                            return;
                                        }

                                        if (this.h(new Object[]{var27.up(), 55508932811972L}) || this.h(new Object[]{var27, 55508932811972L})) {
                                            return;
                                        }

                                        if (!b.world.getBlockState(var27.up()).isReplaceable()) {
                                            return;
                                        }

                                        if (!this.f_boolean(new Object[]{var27, 134383816715916L})) {
                                            return;
                                        }
                                    }

                                    this.F = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                    this.z = BowMode.PASSIVE;
                                    if (var27 != null) {
                                        this.D = var27;
                                        this.E = true;
                                    }

                                    this.L = this.j_boolean_(new Object[]{125278170840707L});
                                    this.M = 0;
                                    this.y = RailState.SWITCH_RAIL;
                                    this.G = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            boolean var57 = (Integer)this.f.getValue() != -1 && this.f.isPressed(new Object[]{28844609523469L});
            boolean var58 = var57 && !this.J;
            this.J = var57;
            boolean var59 = (Integer)this.h.getValue() != -1 && this.h.isPressed(new Object[]{28844609523469L});
            boolean var60 = var59 && !this.K;
            this.K = var59;
            if (!b.options.useKey.isPressed()) {
                this.W = false;
            }

            if (this.y == RailState.IDLE && (var58 || var60)) {
                boolean var61 = this.j_boolean_(new Object[]{125278170840707L});
                boolean var62 = this.f(new Object[]{10648749750583L}) != -1 || InventoryUtil.a_Integer(new Object[]{Items.BOW, 64376993919254L}) != null;
                if (this.d_int__(new Object[]{43758232836049L}) != -1 && this.a_Integer_(new Object[]{102143318459463L}) != null && (!var61 || var62)) {
                    this.F = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                    this.z = BowMode.BOW;
                    this.Q = var61;
                    this.R = var61 && b.crosshairTarget instanceof BlockHitResult var63 ? var63.getBlockPos() : null;
                    this.D = null;
                    this.E = false;
                    this.W = true;
                    this.L = this.j_boolean_(new Object[]{125278170840707L});
                    this.M = 0;
                    this.y = var61 ? RailState.SWITCH_WEAPON : RailState.SWITCH_RAIL;
                    this.G = 0;
                }
            }

            if (this.U && this.y == RailState.IDLE && this.i(new Object[]{131561801410531L})) {
                this.S = true;
                b.options.useKey.setPressed(false);
                if (b.player.isUsingItem()) {
                    b.interactionManager.stopUsingItem(b.player);
                }

                this.U = false;
                this.T = null;
            } else {
                if ((Boolean)this.m.getValue() && this.y == RailState.IDLE && this.i(new Object[]{131561801410531L})) {
                    TntMinecartEntity var71 = this.a_class_1701(new Object[]{86871509419157L});
                    this.T = var71;
                    if (var71 != null && b.player.getItemUseTime() >= (Integer)this.o.getValue()) {
                        RenderBatch var77 = Prestige.o.c(new Object[0]);
                        if (var77 != null && !var77.a_boolean________________________________________(new Object[0])) {
                            this.U = true;
                        }
                    }
                } else {
                    this.T = null;
                    this.U = false;
                }

                if (this.y != RailState.IDLE) {
                    switch (this.y) {
                        case SWITCH_RAIL:
                            int var76 = this.d_int__(new Object[]{43758232836049L});
                            if (var76 == -1) {
                                this.j(new Object[0]);
                                return;
                            }

                            BlockHitResult var82 = this.a_class_3965___(new Object[]{74719101328524L});
                            if (var82 == null) {
                                if (++this.H > 10) {
                                    this.j(new Object[0]);
                                }

                                return;
                            }

                            double var96 = this.A + 0.5;
                            double var98 = this.B + 0.5;
                            double var105 = this.C + 0.5;
                            Object[] var109 = new Object[]{null, null, null, 105619551519821L};
                            var109[2] = var105;
                            var109[1] = var98;
                            var109[0] = var96;
                            if (!this.g(var109)) {
                                this.j(new Object[0]);
                                return;
                            }

                            Object[] var106 = new Object[]{null, 37392877673079L};
                            var106[0] = var76;
                            InventoryUtil.a_void________(var106);
                            this.o(new Object[]{var82, 133610651008650L});
                            this.G = (Integer)this.a.getValue();
                            this.y = RailState.SWITCH_CART;
                            break;
                        case SWITCH_CART:
                            if (this.G > 0) {
                                this.G--;
                                return;
                            }

                            if (this.N) {
                                if (!this.O.b(new Object[]{this.i, 89714527855213L})) {
                                    return;
                                }

                                this.N = false;
                            }

                            Integer var75 = this.j.getValue() && this.M > 0
                                ? InventoryUtil.a_Integer(new Object[]{Items.TNT_MINECART, 64376993919254L})
                                : this.a_Integer_(new Object[]{102143318459463L});
                            if (var75 == null) {
                                if (this.M > 0) {
                                    this.l(new Object[]{136470461279916L});
                                } else {
                                    this.j(new Object[0]);
                                }

                                return;
                            }

                            if (b.player.getInventory().getStack(var75).getItem() != Items.TNT_MINECART) {
                                return;
                            }

                            double var94 = this.A + 0.5;
                            double var97 = this.B + 0.5;
                            double var103 = this.C + 0.5;
                            Object[] var108 = new Object[]{null, null, null, 105619551519821L};
                            var108[2] = var103;
                            var108[1] = var97;
                            var108[0] = var94;
                            if (!this.g(var108)) {
                                this.j(new Object[0]);
                                return;
                            }

                            BlockHitResult var81 = new BlockHitResult(
                                new Vec3d(this.A + 0.5, this.B + 0.125, this.C + 0.5), Direction.UP, new BlockPos(this.A, this.B, this.C), false
                            );
                            if (this.M >= 1) {
                                int var91 = var75;
                                Runnable var95 = () -> this.o(new Object[]{var81, 133610651008650L});
                                Object[] var10005 = new Object[]{null, null, null, 53805837885570L};
                                var10005[2] = true;
                                var10005[1] = var95;
                                var10005[0] = var91;
                                InventoryUtil.b_void__(var10005);
                            } else {
                                int var92 = var75;
                                Object[] var104 = new Object[]{null, 37392877673079L};
                                var104[0] = var92;
                                InventoryUtil.a_void________(var104);
                                InventoryUtil2.a(new Object[]{var81, 96519249476482L});
                            }

                            this.M++;
                            if ((this.L || this.j_boolean_(new Object[]{125278170840707L})) && this.M < (Integer)this.g.getValue()) {
                                this.O.a_void_____________________(new Object[]{93540861878173L});
                                this.i.reroll(new Object[]{57083216809697L});
                                this.N = true;
                                return;
                            }

                            if ((Boolean)this.q.getValue()) {
                                this.y = RailState.SWITCH_SAFE;
                                this.G = (Integer)this.c.getValue();
                            } else {
                                this.l(new Object[]{136470461279916L});
                            }
                            break;
                        case SWITCH_SAFE:
                            if (this.G > 0) {
                                this.G--;
                                return;
                            }

                            int var74 = this.e(new Object[]{103960202223449L});
                            if (var74 == -1) {
                                this.l(new Object[]{136470461279916L});
                                return;
                            }

                            if (b.crosshairTarget instanceof BlockHitResult var80 && var80.getSide() == Direction.UP) {
                                BlockPos var85 = var80.getBlockPos();
                                Vec3d var87 = PositionUtil.b(new Object[]{118247215910525L});
                                Vec3d var88 = new Vec3d(this.A + 0.5, this.B + 0.5, this.C + 0.5);
                                Vec3d var66 = new Vec3d(var85.getX() + 0.5, var85.getY() + 0.5, var85.getZ() + 0.5);
                                double var67 = var87.distanceTo(var88);
                                double var69 = var87.distanceTo(var66);
                                if (var85.getY() <= (int)Math.floor(var87.y)
                                    && var69 < var67
                                    && !this.d(new Object[]{var85, 1171863229319L})
                                    && !b.world.getBlockState(var85).isReplaceable()) {
                                    double var93 = var85.getX() + 0.5;
                                    double var10002 = var85.getY() + 1.0;
                                    double var101 = var85.getZ() + 0.5;
                                    Object[] var107 = new Object[]{null, null, null, 105619551519821L};
                                    var107[2] = var101;
                                    var107[1] = var10002;
                                    var107[0] = var93;
                                    if (this.g(var107)) {
                                        Object[] var102 = new Object[]{null, 37392877673079L};
                                        var102[0] = var74;
                                        InventoryUtil.a_void________(var102);
                                        this.o(new Object[]{var80, 133610651008650L});
                                    }
                                }
                            }

                            this.l(new Object[]{136470461279916L});
                            break;
                        case WAIT_BACK:
                            if (!(Boolean)this.p.getValue()) {
                                this.j(new Object[0]);
                                return;
                            }

                            if (this.G > 0) {
                                this.G--;
                                return;
                            }

                            if (this.F != -1) {
                                int var90 = this.F;
                                Object[] var100 = new Object[]{null, 37392877673079L};
                                var100[0] = var90;
                                InventoryUtil.a_void________(var100);
                            }

                            this.j(new Object[0]);
                            break;
                        case SWITCH_WEAPON:
                            if (this.G > 0) {
                                this.G--;
                                return;
                            }

                            int var73 = this.f(new Object[]{10648749750583L});
                            Integer var79 = InventoryUtil.a_Integer(new Object[]{Items.BOW, 64376993919254L});
                            if (var73 != -1) {
                                Object[] var10003 = new Object[]{null, 37392877673079L};
                                var10003[0] = var73;
                                InventoryUtil.a_void________(var10003);
                                this.I = true;
                            } else {
                                if (var79 == null) {
                                    this.j(new Object[0]);
                                    return;
                                }

                                int var89 = var79;
                                Object[] var99 = new Object[]{null, 37392877673079L};
                                var99[0] = var89;
                                InventoryUtil.a_void________(var99);
                                this.I = false;
                            }

                            this.y = RailState.USE_WEAPON;
                            break;
                        case USE_WEAPON:
                            if (this.I) {
                                this.W = true;
                                b.interactionManager.interactItem(b.player, Hand.MAIN_HAND);
                                if (this.Q) {
                                    Object[] var10004 = new Object[]{null, 20963897406139L};
                                    var10004[0] = 1.0500001F;
                                    this.n(var10004);
                                } else {
                                    this.j(new Object[0]);
                                }
                            } else {
                                b.options.useKey.setPressed(true);
                                b.interactionManager.interactItem(b.player, Hand.MAIN_HAND);
                                this.G = (Integer)this.d.getValue();
                                this.y = RailState.CHARGE_BOW;
                            }
                            break;
                        case CHARGE_BOW:
                            if (!b.options.useKey.isPressed()) {
                                b.options.useKey.setPressed(true);
                            }

                            if (b.player.getItemUseTime() < this.g_int(new Object[]{115336365827434L})) {
                                return;
                            }

                            this.P = 0;
                            this.y = RailState.RELEASE_BOW;
                            break;
                        case RELEASE_BOW:
                            RenderBatch var72 = Prestige.o.c(new Object[0]);
                            if (this.V != null && var72 != null && var72.a_boolean________________________________________(new Object[0]) && this.P++ < 10) {
                                if (!b.options.useKey.isPressed()) {
                                    b.options.useKey.setPressed(true);
                                }

                                return;
                            }

                            float var78 = BowItem.getPullProgress(b.player.getItemUseTime());
                            RenderBatch var83 = Prestige.o.b_RenderBatch____________(new Object[0]);
                            float var86 = var83 != null ? var83.a(new Object[0]) : b.player.getYaw();
                            float var65 = var83 != null ? var83.b(new Object[0]) : b.player.getPitch();
                            this.k(new Object[]{81977082638634L});
                            if (b.player.isUsingItem()) {
                                this.W = true;
                                b.interactionManager.stopUsingItem(b.player);
                            }

                            if (this.Q) {
                                Object[] var10006 = new Object[]{null, null, null, 8056340562535L};
                                var10006[2] = var65;
                                var10006[1] = var86;
                                var10006[0] = var78;
                                this.m(var10006);
                            } else {
                                this.j(new Object[0]);
                            }
                    }
                }
            }
        } else {
            this.j(new Object[0]);
        }
    }

    private BlockHitResult a_class_3965___(Object[] var1) {
        if (this.E && this.D != null) {
            this.A = this.D.getX();
            this.B = this.D.getY() + 1;
            this.C = this.D.getZ();
            return new BlockHitResult(new Vec3d(this.A + 0.5, this.D.getY() + 1.0, this.C + 0.5), Direction.UP, this.D, false);
        } else if (b.crosshairTarget instanceof BlockHitResult var5) {
            if (var5.getSide() != Direction.UP) {
                return null;
            } else {
                BlockPos var7 = var5.getBlockPos();
                this.A = var7.getX();
                this.B = var7.getY() + 1;
                this.C = var7.getZ();
                return var5;
            }
        } else {
            return null;
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 587706608428L;
        long var6 = var2 ^ 125420266980053L;
        long var8 = var2 ^ 51598816251400L;
        long var10 = var2 ^ 61766210848858L;
        long var12 = var2 ^ 135924364663546L;
        long var14 = var2 ^ 101816702374277L;
        long var16 = var2 ^ 80128468979861L;
        if (b.player != null && b.world != null) {
            if ((Boolean)this.m.getValue() && this.y == RailState.IDLE && this.i(new Object[]{var10})) {
                TntMinecartEntity var19 = this.T != null ? this.T : this.a_class_1701(new Object[]{var4});
                if (var19 != null) {
                    Vec3d var20 = PositionUtil.a(new Object[]{var19, var6}).add(0.0, 0.25, 0.0);
                    Object[] var10005 = new Object[]{null, null, var16};
                    var10005[1] = 1.0F;
                    var10005[0] = var20;
                    RenderBatch var21 = this.b_RenderBatch(var10005);
                    if (var21 != null) {
                        float var10000 = var21.a(new Object[0]) - b.player.getYaw();
                        Object[] var10003 = new Object[]{null, var8};
                        var10003[0] = var10000;
                        float var22 = Math.abs(WorldUtil.b_float_____________________(var10003));
                        if (var22 <= (Float)this.s.getValue()) {
                            return var21;
                        }
                    }
                }
            }

            if (this.z == BowMode.BOW
                && this.Q
                && this.R != null
                && (this.y == RailState.USE_WEAPON || this.y == RailState.CHARGE_BOW || this.y == RailState.RELEASE_BOW)) {
                float var23 = this.b_float_________(new Object[]{var12});
                RenderBatch var34;
                if (var23 > 0.0F) {
                    Vec3d var10001 = this.R.toCenterPos();
                    Object[] var42 = new Object[]{null, null, var16};
                    var42[1] = var23;
                    var42[0] = var10001;
                    var34 = this.b_RenderBatch(var42);
                } else {
                    var34 = null;
                }

                RenderBatch var26 = var34;
                this.V = var26;
                if (var26 != null) {
                    float var35 = var26.a(new Object[0]) - b.player.getYaw();
                    Object[] var39 = new Object[]{null, var8};
                    var39[0] = var35;
                    float var29 = Math.abs(WorldUtil.b_float_____________________(var39));
                    if (var29 <= (Float)this.s.getValue()) {
                        return var26;
                    }
                }
            }

            if (this.z == BowMode.BOW
                && !this.Q
                && (
                    this.y == RailState.SWITCH_SAFE
                        || this.y == RailState.SWITCH_WEAPON
                        || this.y == RailState.USE_WEAPON
                        || this.y == RailState.CHARGE_BOW
                        || this.y == RailState.RELEASE_BOW
                )) {
                Vec3d var24 = new Vec3d(this.A + 0.5, this.B + 0.5, this.C + 0.5);
                float var27 = this.b_float_________(new Object[]{var12});
                RenderBatch var36;
                if (var27 > 0.0F) {
                    Object[] var43 = new Object[]{null, null, var16};
                    var43[1] = var27;
                    var43[0] = var24;
                    var36 = this.b_RenderBatch(var43);
                } else {
                    var36 = null;
                }

                RenderBatch var30 = var36;
                this.V = var30;
                if (var30 != null) {
                    float var37 = var30.a(new Object[0]) - b.player.getYaw();
                    Object[] var40 = new Object[]{null, var8};
                    var40[0] = var37;
                    float var32 = Math.abs(WorldUtil.b_float_____________________(var40));
                    if (var32 <= (Float)this.s.getValue()) {
                        return var30;
                    }
                }
            }

            if ((Boolean)this.r.getValue()
                && ((String)this.k.getValue()).equals("Trajectory")
                && this.E
                && this.D != null
                && (this.y == RailState.SWITCH_RAIL || this.y == RailState.SWITCH_CART)) {
                BlockPos var25 = this.D.up();
                Vec3d var28 = new Vec3d(var25.getX() + 0.5, var25.getY() + 0.5, var25.getZ() + 0.5);
                RenderBatch var31 = Prestige.o.a_RenderBatch(new Object[]{var28, var14});
                if (var31 != null) {
                    float var38 = var31.a(new Object[0]) - b.player.getYaw();
                    Object[] var41 = new Object[]{null, var8};
                    var41[0] = var38;
                    float var33 = Math.abs(WorldUtil.b_float_____________________(var41));
                    if (var33 <= (Float)this.s.getValue()) {
                        return var31;
                    }
                }
            }

            return null;
        } else {
            return null;
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.t.getValue()) {
            if (((String)this.k.getValue()).equals("Trajectory")) {
                if (b.player != null && b.world != null) {
                    if (this.k_boolean(new Object[]{8654805801796L})) {
                        RenderBatch var15 = Prestige.o.b_RenderBatch____________(new Object[0]);
                        float var16 = var15 != null ? var15.a(new Object[0]) : b.player.getYaw();
                        float var17 = var15 != null ? var15.b(new Object[0]) : b.player.getPitch();
                        ItemStack var19 = b.player.getMainHandStack();
                        float var18;
                        if (var19.getItem() == Items.BOW) {
                            if (!b.player.isUsingItem()) {
                                return;
                            }

                            var18 = BowItem.getPullProgress(b.player.getItemUseTime());
                            if (var18 < 0.05F) {
                                return;
                            }
                        } else {
                            if (!this.e_boolean_(new Object[]{var19, 7371155366939L})) {
                                return;
                            }

                            var18 = 1.0500001F;
                        }

                        Object[] var10006 = new Object[]{null, null, null, 88589708016684L};
                        var10006[2] = var18;
                        var10006[1] = var17;
                        var10006[0] = var16;
                        BlockPos var20 = this.a_class_2338_(var10006);
                        if (var20 != null) {
                            BlockPos var21 = var20.up();
                            float var22 = var21.getX();
                            float var23 = var21.getY();
                            float var24 = var21.getZ();
                            float var25 = var22 + 1.0F;
                            float var26 = var23 + 1.0F;
                            float var27 = var24 + 1.0F;
                            Color var28 = (Color)this.u.getValue();
                            Color var29 = new Color(var28.getRed(), var28.getGreen(), var28.getBlue(), Math.min(255, var28.getAlpha() + 100));
                            MatrixCameraData var10000 = var1.b;
                            RenderHelper8 var10001 = var1.a;
                            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var28, 108390846498585L};
                            var10011[7] = var27;
                            var10011[6] = var26;
                            var10011[5] = var25;
                            var10011[4] = var24;
                            var10011[3] = var23;
                            var10011[2] = var22;
                            var10011[1] = var10001;
                            var10011[0] = var10000;
                            RenderState.h(var10011);
                            var10000 = var1.b;
                            var10001 = var1.a;
                            var10011 = new Object[]{null, null, null, null, null, null, null, null, var29, 119015971454350L};
                            var10011[7] = var27;
                            var10011[6] = var26;
                            var10011[5] = var25;
                            var10011[4] = var24;
                            var10011[3] = var23;
                            var10011[2] = var22;
                            var10011[1] = var10001;
                            var10011[0] = var10000;
                            RenderState.g(var10011);
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
