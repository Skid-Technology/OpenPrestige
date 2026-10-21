package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.DamageUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.VecIntData2;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import dev.zprestige.prestige.type.FireState;
import dev.zprestige.prestige.world.WorldUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import org.joml.Matrix4f;

public class CartPearlCatch extends Module implements RenderContract3 {
    private FloatSetting a;
    private FloatSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private IntSetting f;
    private BoolSetting g;
    private FloatSetting h;
    private BoolSetting i;
    private static final Item[] j = new Item[]{Items.RAIL, Items.POWERED_RAIL, Items.DETECTOR_RAIL, Items.ACTIVATOR_RAIL};
    private FireState k = FireState.IDLE;
    private UUID l;
    private Set m = new HashSet();
    private int n;
    private int o;
    private int p;
    private BlockPos q;
    private boolean r;
    private int s = -1;
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private int w = -1;
    private boolean x = false;
    private RenderBatch y;
    private int z = -1;
    private static final Identifier A = Identifier.of("minecraft", "textures/item/tnt_minecart.png");
    private static FontRenderer B;

    public CartPearlCatch() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.h.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL317_0 -> (Boolean)this.g.getValue(), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Cart);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        this.j(new Object[0]);
        this.m.clear();
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 83012844419172L;
        long var6 = var2 ^ 140372736859409L;
        this.m(new Object[]{var4});
        Prestige.o.f(new Object[]{this, var6});
        this.j(new Object[0]);
    }

    private void j(Object[] var1) {
        this.k = FireState.IDLE;
        this.l = null;
        this.q = null;
        this.s = -1;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = -1;
        this.y = null;
        this.z = -1;
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.m.clear();
    }

    private void k(Object[] var1) {
        String var2 = (String)var1[0];
        if (b.player != null) {
            b.player.sendMessage(Text.literal("§7[CartPearlCatch] §f" + var2), false);
        }
    }

    private void l(Object[] var1) {
        String var2 = (String)var1[0];
        long var3 = (Long)var1[1];
        var3 = 57555634897493L ^ var3;
        long var5 = var3 ^ 16090892033783L;
        long var7 = var3 ^ 92977459443455L;
        Event.b();
        this.k(new Object[]{"aborted: " + var2, var7});
        if (this.l != null) {
            this.m.add(this.l);
        }

        this.m(new Object[]{var5});
        this.j(new Object[0]);
    }

    private void m(Object[] var1) {
        if (this.x) {
            this.x = false;
            b.options.useKey.setPressed(false);
            if (b.player != null && b.player.isUsingItem()) {
                b.interactionManager.stopUsingItem(b.player);
            }
        }
    }

    private VecIntData2 a_VecIntData2(Object[] var1) {
        EnderPearlEntity var4 = (EnderPearlEntity)var1[0];
        double var6 = var4.getX();
        Event.b();
        double var8 = var4.getY();
        double var10 = var4.getZ();
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
                return new VecIntData2(var21.getPos(), var19);
            }

            if (var8 < -65.0) {
                break;
            }
        }

        return null;
    }

    private EnderPearlEntity a_class_1684_(Object[] var1) {
        if (this.l != null && b.world != null) {
            for (Entity var6 : b.world.getEntities()) {
                if (var6 instanceof EnderPearlEntity var7 && var6.getUuid().equals(this.l)) {
                    return var7;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private double a_double______(Object[] var1) {
        Vec3d var7 = (Vec3d)var1[0];
        Vec3d var6 = (Vec3d)var1[1];
        double var2 = (Double)var1[2];
        double var9 = var2 * 2.0;
        Event.b();
        double var11 = Math.sqrt(var7.squaredDistanceTo(var6));
        double var13 = var11 / var9;
        if (var13 > 1.0) {
            return 0.0;
        } else {
            int var15 = 0;

            for (double var19 : new double[]{0.2, 1.0, 1.7}) {
                Vec3d var21 = var7.add(0.0, var19, 0.0);
                BlockHitResult var22 = b.world.raycast(new RaycastContext(var6, var21, ShapeType.COLLIDER, FluidHandling.NONE, b.player));
                if (var22 == null || var22.getType() == Type.MISS) {
                    var15++;
                }
            }

            double var23 = (1.0 - var13) * (var15 / 3.0);
            return (var23 * var23 + var23) / 2.0 * 7.0 * var9 + 1.0;
        }
    }

    private BlockPos a_class_2338____(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        long var2 = (Long)var1[1];
        var2 = 57555634897493L ^ var2;
        long var5 = var2 ^ 55318463579930L;
        long var7 = var2 ^ 68011556437821L;
        long var9 = var2 ^ 133643832525828L;
        double var12 = this.r ? 6.4 : 6.25;
        double var14 = this.r ? 3.15 : 3.0;
        short var10000 = 25142;
        BlockPos var16 = BlockPos.ofFloored(var4);
        long var10001 = 3235896888934859824L ^ var2;
        BlockPos var17 = null;
        double var18 = ((Float)this.c.getValue()).floatValue();

        for (int var20 = -4; var20 <= 4; var20++) {
            for (int var21 = -1; var21 <= 2; var21++) {
                for (int var22 = -4; var22 <= 4; var22++) {
                    BlockPos var23 = var16.add(var20, var21, var22);
                    BlockPos var24 = var23.up();
                    if (!b.world.getBlockState(var23).isReplaceable() && b.world.getBlockState(var24).isReplaceable()) {
                        double var30 = var24.getX() + 0.5;
                        double var10002 = var24.getY() + 0.5;
                        double var10003 = var24.getZ() + 0.5;
                        Object[] var10006 = new Object[]{null, null, null, var7};
                        var10006[2] = var10003;
                        var10006[1] = var10002;
                        var10006[0] = var30;
                        if (this.f(var10006)) {
                            Vec3d var25 = new Vec3d(var24.getX() + 0.5, var24.getY() + 0.0625, var24.getZ() + 0.5);
                            DamageUtil var29 = Prestige.f;
                            ClientPlayerEntity var31 = b.player;
                            var10006 = new Object[]{null, null, null, var5};
                            var10006[2] = var14;
                            var10006[1] = var25;
                            var10006[0] = var31;
                            if (!(var29.g(var10006) > ((Float)this.d.getValue()).floatValue())) {
                                var10006 = new Object[]{null, null, null, var9};
                                var10006[2] = var12;
                                var10006[1] = var25;
                                var10006[0] = var4;
                                double var26 = this.a_double______(var10006);
                                if (var26 >= var18) {
                                    var18 = var26;
                                    var17 = var24;
                                }
                            }
                        }
                    }
                }
            }
        }

        return var17;
    }

    private int a_int__________(Object[] var1) {
        float var6 = (Float)var1[0];
        float var2 = (Float)var1[1];
        float var3 = (Float)var1[2];
        Event.b();
        Vec3d var8 = b.player.getEyePos();
        double var9 = var8.x;
        double var11 = var8.y - 0.1;
        double var13 = var8.z;
        double var15 = Math.toRadians(var6);
        double var17 = Math.toRadians(var2);
        double var19 = -Math.sin(var15) * Math.cos(var17);
        double var21 = -Math.sin(var17);
        double var23 = Math.cos(var15) * Math.cos(var17);
        double var25 = Math.sqrt(var19 * var19 + var21 * var21 + var23 * var23);
        double var27 = Math.min(var3, 1.05F) * 3.0;
        var19 = var19 / var25 * var27;
        var21 = var21 / var25 * var27;
        var23 = var23 / var25 * var27;
        var19 += b.player.getVelocity().x;
        var23 += b.player.getVelocity().z;
        if (!b.player.isOnGround()) {
            var21 += b.player.getVelocity().y;
        }

        Box var29 = new Box(this.n + 0.01, this.o + 0.0625, this.p + 0.01, this.n + 0.99, this.o + 0.7625, this.p + 0.99);
        BlockPos var30 = new BlockPos(this.n, this.o, this.p);

        for (int var31 = 1; var31 <= 60; var31++) {
            Vec3d var32 = new Vec3d(var9, var11, var13);
            double var33 = Math.sqrt(var19 * var19 + var21 * var21 + var23 * var23);
            int var35 = Math.max(1, (int)Math.ceil(var33 / 0.25));

            for (int var36 = 0; var36 < var35; var36++) {
                var9 += var19 / var35;
                var11 += var21 / var35;
                var13 += var23 / var35;
                if (var29.contains(var9, var11, var13)) {
                    return var31;
                }
            }

            Vec3d var43 = new Vec3d(var9, var11, var13);
            BlockHitResult var37 = b.world.raycast(new RaycastContext(var32, var43, ShapeType.OUTLINE, FluidHandling.NONE, b.player));
            if (var37 != null && var37.getType() == Type.BLOCK && !var37.getBlockPos().equals(var30)) {
                return -1;
            }

            if (var11 < this.o - 3) {
                return -1;
            }

            var19 *= 0.99;
            var23 *= 0.99;
            var21 = var21 * 0.99 - 0.05;
        }

        return -1;
    }

    private RenderBatch b_RenderBatch_(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        Vec3d var6 = b.player.getEyePos();
        Event.b();
        double var7 = var2.x - var6.x;
        double var9 = var2.y - (var6.y - 0.1);
        double var11 = var2.z - var6.z;
        double var13 = Math.sqrt(var7 * var7 + var9 * var9 + var11 * var11);
        if (var13 < 1.0E-6) {
            return null;
        } else {
            float var15 = (float)(Math.toDegrees(Math.atan2(var11, var7)) - 90.0);
            float var16 = (float)(-Math.toDegrees(Math.asin(var9 / var13)));
            return new RenderBatch(var15, var16);
        }
    }

    private boolean a_boolean___________________(Object[] var1) {
        float var4 = (Float)var1[0];
        long var2 = (Long)var1[1];
        var2 = 57555634897493L ^ var2;
        long var5 = var2 ^ 71076682851812L;
        long var7 = var2 ^ 120219321038427L;
        Vec3d var10 = new Vec3d(this.n + 0.5, this.o + 0.45, this.p + 0.5);
        double[] var11 = new double[]{0.0, 0.15, 0.3, 0.5, 0.8, 1.2, 1.8, 2.6};
        Event.b();

        for (double var14 : var11) {
            RenderBatch var16 = this.b_RenderBatch_(new Object[]{var10.add(0.0, var14, 0.0), var7});
            if (var16 != null) {
                float var10001 = var16.a(new Object[0]);
                float var10002 = var16.b(new Object[0]);
                Object[] var10006 = new Object[]{null, null, null, var5};
                var10006[2] = var4;
                var10006[1] = var10002;
                var10006[0] = var10001;
                int var17 = this.a_int__________(var10006);
                if (var17 != -1) {
                    this.y = var16;
                    this.z = var17;
                    return true;
                }
            }
        }

        return false;
    }

    private BlockPos b_class_2338(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        long var2 = (Long)var1[1];
        var2 = 57555634897493L ^ var2;
        long var5 = var2 ^ 111447218500322L;
        long var7 = var2 ^ 134189818566232L;
        Event.b();
        RenderBatch var10 = this.b_RenderBatch_(new Object[]{var4, var5});
        if (var10 == null) {
            return null;
        } else {
            Vec3d var11 = b.player.getEyePos();
            double var12 = var11.x;
            double var14 = var11.y - 0.1;
            double var16 = var11.z;
            double var18 = Math.toRadians(var10.a(new Object[0]));
            double var20 = Math.toRadians(var10.b(new Object[0]));
            double var22 = -Math.sin(var18) * Math.cos(var20);
            double var24 = -Math.sin(var20);
            double var26 = Math.cos(var18) * Math.cos(var20);
            double var28 = Math.sqrt(var22 * var22 + var24 * var24 + var26 * var26);
            var22 = var22 / var28 * 3.15;
            var24 = var24 / var28 * 3.15;
            var26 = var26 / var28 * 3.15;
            var22 += b.player.getVelocity().x;
            var26 += b.player.getVelocity().z;
            if (!b.player.isOnGround()) {
                var24 += b.player.getVelocity().y;
            }

            BlockPos var30 = new BlockPos(this.n, this.o, this.p);
            Box var31 = b.player.getBoundingBox().expand(0.3);

            for (int var32 = 0; var32 < 60; var32++) {
                double var33 = Math.sqrt(var22 * var22 + var24 * var24 + var26 * var26);
                int var35 = Math.max(1, (int)Math.ceil(var33 / 0.25));

                for (int var36 = 0; var36 < var35; var36++) {
                    var12 += var22 / var35;
                    var14 += var24 / var35;
                    var16 += var26 / var35;
                    BlockPos var37 = BlockPos.ofFloored(var12, var14, var16);
                    if (var37.equals(var30) || var37.equals(var30.up())) {
                        return null;
                    }

                    if (!var31.intersects(new Box(var37))) {
                        double var10001 = var37.getX() + 0.5;
                        double var10002 = var37.getY();
                        double var10003 = var37.getZ() + 0.5;
                        Object[] var10006 = new Object[]{null, null, null, var7};
                        var10006[2] = var10003;
                        var10006[1] = var10002;
                        var10006[0] = var10001;
                        if (this.f(var10006)
                            && b.world.getBlockState(var37).isReplaceable()
                            && b.world.getBlockState(var37.down()).isSideSolidFullSquare(b.world, var37.down(), Direction.UP)) {
                            return var37;
                        }
                    }
                }

                var22 *= 0.99;
                var26 *= 0.99;
                var24 = var24 * 0.99 - 0.05;
            }

            return null;
        }
    }

    private boolean d(Object[] var1) {
        float var2 = (Float)var1[0];
        float var5 = (Float)var1[1];
        Vec3d var7 = b.player.getEyePos();
        double var8 = var7.x;
        double var10 = var7.y - 0.1;
        double var12 = var7.z;
        double var14 = Math.toRadians(var2);
        Event.b();
        double var16 = Math.toRadians(var5);
        double var18 = -Math.sin(var14) * Math.cos(var16);
        double var20 = -Math.sin(var16);
        double var22 = Math.cos(var14) * Math.cos(var16);
        double var24 = Math.sqrt(var18 * var18 + var20 * var20 + var22 * var22);
        var18 = var18 / var24 * 3.15;
        var20 = var20 / var24 * 3.15;
        var22 = var22 / var24 * 3.15;
        var18 += b.player.getVelocity().x;
        var22 += b.player.getVelocity().z;
        if (!b.player.isOnGround()) {
            var20 += b.player.getVelocity().y;
        }

        Box var26 = new Box(this.n + 0.01, this.o + 0.0625, this.p + 0.01, this.n + 0.99, this.o + 0.7625, this.p + 0.99);
        BlockPos var27 = new BlockPos(this.n, this.o, this.p);
        Box var28 = this.q != null ? new Box(this.q) : null;
        boolean var29 = false;

        for (int var30 = 0; var30 < 60; var30++) {
            Vec3d var31 = new Vec3d(var8, var10, var12);
            double var32 = Math.sqrt(var18 * var18 + var20 * var20 + var22 * var22);
            int var34 = Math.max(1, (int)Math.ceil(var32 / 0.25));

            for (int var35 = 0; var35 < var34; var35++) {
                var8 += var18 / var34;
                var10 += var20 / var34;
                var12 += var22 / var34;
                if (!var29 && var28 != null && var28.contains(var8, var10, var12)) {
                    var29 = true;
                }

                if (var26.contains(var8, var10, var12)) {
                    return var29;
                }
            }

            Vec3d var42 = new Vec3d(var8, var10, var12);
            BlockHitResult var36 = b.world.raycast(new RaycastContext(var31, var42, ShapeType.OUTLINE, FluidHandling.NONE, b.player));
            if (var36 != null && var36.getType() == Type.BLOCK && !var36.getBlockPos().equals(var27)) {
                return false;
            }

            if (var10 < this.o - 3) {
                return false;
            }

            var18 *= 0.99;
            var22 *= 0.99;
            var20 = var20 * 0.99 - 0.05;
        }

        return false;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            if (this.k == FireState.IDLE) {
                this.o(new Object[]{29002719073789L});
            } else if (++this.u > 100) {
                this.l(new Object[]{"timed out", 94718932046755L});
            } else {
                switch (this.k) {
                    case ARMED:
                        EnderPearlEntity var58 = this.a_class_1684_(new Object[]{58875298170324L});
                        if (var58 == null) {
                            this.l(new Object[]{"too late — the pearl already landed", 94718932046755L});
                            return;
                        }

                        int var63 = this.c(new Object[]{var58, 140618963028407L});
                        if (this.r) {
                            Object[] var81 = new Object[]{null, 64929107332566L};
                            var81[0] = 1.05F;
                            this.a_boolean___________________(var81);
                            if (var63 <= this.d_int___(new Object[]{131259633732523L}) + Math.max(this.z, 0)) {
                                this.k = FireState.SWITCH_RAIL;
                            }
                        } else if (var63 <= this.d_int___(new Object[]{131259633732523L}) + 20) {
                            this.k = FireState.SWITCH_RAIL;
                        }
                        break;
                    case SWITCH_RAIL:
                        int var57 = this.e_int__(new Object[]{64280958659869L});
                        if (var57 == -1) {
                            this.l(new Object[]{"no rail in hotbar", 94718932046755L});
                            return;
                        }

                        double var70 = this.n + 0.5;
                        double var73 = this.o + 0.5;
                        double var79 = this.p + 0.5;
                        Object[] var83 = new Object[]{null, null, null, 137802499562850L};
                        var83[2] = var79;
                        var83[1] = var73;
                        var83[0] = var70;
                        if (!this.f(var83)) {
                            this.l(new Object[]{"placement out of reach", 94718932046755L});
                            return;
                        }

                        BlockPos var62 = new BlockPos(this.n, this.o - 1, this.p);
                        Object[] var80 = new Object[]{null, 37392877673079L};
                        var80[0] = var57;
                        InventoryUtil.a_void________(var80);
                        this.p(new Object[]{new BlockHitResult(new Vec3d(this.n + 0.5, this.o, this.p + 0.5), Direction.UP, var62, false), 44089689467500L});
                        this.t = (Integer)this.f.getValue();
                        this.k = FireState.SWITCH_CART;
                        break;
                    case SWITCH_CART:
                        if (this.t > 0) {
                            this.t--;
                            return;
                        }

                        Integer var56 = this.a_Integer__(new Object[]{106761166356357L});
                        if (var56 == null) {
                            this.l(new Object[]{"no TNT carts", 94718932046755L});
                            return;
                        }

                        if (b.player.getInventory().getStack(var56).getItem() != Items.TNT_MINECART) {
                            return;
                        }

                        double var69 = this.n + 0.5;
                        double var72 = this.o + 0.5;
                        double var77 = this.p + 0.5;
                        Object[] var82 = new Object[]{null, null, null, 137802499562850L};
                        var82[2] = var77;
                        var82[1] = var72;
                        var82[0] = var69;
                        if (!this.f(var82)) {
                            this.l(new Object[]{"cart out of reach", 94718932046755L});
                            return;
                        }

                        int var66 = var56;
                        Object[] var78 = new Object[]{null, 37392877673079L};
                        var78[0] = var66;
                        InventoryUtil.a_void________(var78);
                        InventoryUtil2.a(
                            new Object[]{
                                new BlockHitResult(
                                    new Vec3d(this.n + 0.5, this.o + 0.125, this.p + 0.5), Direction.UP, new BlockPos(this.n, this.o, this.p), false
                                ),
                                96519249476482L
                            }
                        );
                        if (this.r) {
                            this.t = (Integer)this.f.getValue();
                            this.k = FireState.SWITCH_FIRE;
                        } else {
                            this.k = FireState.SWITCH_BOW;
                        }
                        break;
                    case SWITCH_FIRE:
                        if (this.t > 0) {
                            this.t--;
                            return;
                        }

                        if (this.q == null) {
                            this.q = this.b_class_2338(new Object[]{new Vec3d(this.n + 0.5, this.o + 0.45, this.p + 0.5), 56107612670319L});
                            if (this.q == null) {
                                this.l(new Object[]{"no fire spot on the shot path", 94718932046755L});
                                return;
                            }
                        }

                        int var55 = this.f_int(new Object[]{75954783929944L});
                        if (var55 == -1) {
                            this.l(new Object[]{"lost the flint & steel", 94718932046755L});
                            return;
                        }

                        BlockPos var61 = this.q.down();
                        Object[] var76 = new Object[]{null, 37392877673079L};
                        var76[0] = var55;
                        InventoryUtil.a_void________(var76);
                        this.p(
                            new Object[]{
                                new BlockHitResult(new Vec3d(var61.getX() + 0.5, var61.getY() + 1.0, var61.getZ() + 0.5), Direction.UP, var61, false),
                                44089689467500L
                            }
                        );
                        this.t = (Integer)this.f.getValue();
                        this.k = FireState.SWITCH_XBOW;
                        break;
                    case SWITCH_XBOW:
                        if (this.t > 0) {
                            this.t--;
                            return;
                        }

                        int var54 = this.g(new Object[]{81774609946785L});
                        if (var54 == -1) {
                            this.l(new Object[]{"no charged crossbow", 94718932046755L});
                            return;
                        }

                        Object[] var75 = new Object[]{null, 37392877673079L};
                        var75[0] = var54;
                        InventoryUtil.a_void________(var75);
                        this.v = 0;
                        this.k = FireState.FIRE;
                        break;
                    case FIRE:
                        RenderBatch var53 = this.b_RenderBatch_(new Object[]{new Vec3d(this.n + 0.5, this.o + 0.45, this.p + 0.5), 107767014652376L});
                        if (var53 != null) {
                            float var67 = var53.a(new Object[0]);
                            float var10002 = var53.b(new Object[0]);
                            Object[] var10005 = new Object[]{null, null, 97740287203088L};
                            var10005[1] = var10002;
                            var10005[0] = var67;
                            if (this.d(var10005)) {
                                this.y = var53;
                                var67 = var53.a(new Object[0]);
                                var10002 = var53.b(new Object[0]);
                                Object[] var10006 = new Object[]{null, null, null, 87897028061799L};
                                var10006[2] = 1.05F;
                                var10006[1] = var10002;
                                var10006[0] = var67;
                                int var50 = this.a_int__________(var10006);
                                if (var50 != -1) {
                                    this.z = var50;
                                }
                            }
                        }

                        if (this.y == null) {
                            this.l(new Object[]{"no firing line through the fire", 94718932046755L});
                            return;
                        }

                        if (!InventoryUtil.l(new Object[]{Items.CROSSBOW, 65539821781598L})) {
                            int var60 = this.g(new Object[]{81774609946785L});
                            if (var60 == -1) {
                                this.l(new Object[]{"crossbow lost its charge", 94718932046755L});
                                return;
                            }

                            Object[] var74 = new Object[]{null, 37392877673079L};
                            var74[0] = var60;
                            InventoryUtil.a_void________(var74);
                            return;
                        }

                        if (!this.e(new Object[]{40873392269738L})) {
                            return;
                        }

                        RenderBatch var59 = Prestige.o.c(new Object[0]);
                        if (var59 != null && var59.a_boolean________________________________________(new Object[0]) && this.v++ < 5) {
                            return;
                        }

                        b.interactionManager.interactItem(b.player, Hand.MAIN_HAND);
                        Prestige.o.g(new Object[]{true});
                        this.n(new Object[]{25573606183585L});
                        break;
                    case SWITCH_BOW:
                        Integer var52 = InventoryUtil.a_Integer(new Object[]{Items.BOW, 64376993919254L});
                        if (var52 == null) {
                            this.l(new Object[]{"no bow in hotbar", 94718932046755L});
                            return;
                        }

                        int var64 = var52;
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var64;
                        InventoryUtil.a_void________(var10003);
                        this.k = FireState.USE_BOW;
                        break;
                    case USE_BOW:
                        b.options.useKey.setPressed(true);
                        b.interactionManager.interactItem(b.player, Hand.MAIN_HAND);
                        this.x = true;
                        this.k = FireState.CHARGE_WAIT;
                        break;
                    case CHARGE_WAIT:
                        if (!b.options.useKey.isPressed()) {
                            b.options.useKey.setPressed(true);
                        }

                        int var51 = b.player.getItemUseTime();
                        if (var51 >= 3) {
                            float var10001 = BowItem.getPullProgress(var51);
                            Object[] var10004 = new Object[]{null, 64929107332566L};
                            var10004[0] = var10001;
                            this.a_boolean___________________(var10004);
                        }

                        if (this.y == null && var51 >= 30) {
                            this.l(new Object[]{"no firing line at the cart", 94718932046755L});
                            return;
                        }

                        if (this.y != null && (this.e(new Object[]{40873392269738L}) || var51 >= 30)) {
                            this.v = 0;
                            this.k = FireState.RELEASE;
                        }
                        break;
                    case RELEASE:
                        if (this.y == null) {
                            this.l(new Object[]{"no firing line at the cart", 94718932046755L});
                            return;
                        }

                        if (!this.e(new Object[]{40873392269738L}) && b.player.getItemUseTime() < 30) {
                            if (!b.options.useKey.isPressed()) {
                                b.options.useKey.setPressed(true);
                            }

                            return;
                        }

                        RenderBatch var49 = Prestige.o.c(new Object[0]);
                        if (var49 != null && var49.a_boolean________________________________________(new Object[0]) && this.v++ < 5) {
                            if (!b.options.useKey.isPressed()) {
                                b.options.useKey.setPressed(true);
                            }

                            return;
                        }

                        this.x = false;
                        b.options.useKey.setPressed(false);
                        if (b.player.isUsingItem()) {
                            b.interactionManager.stopUsingItem(b.player);
                        }

                        this.n(new Object[]{25573606183585L});
                }
            }
        }
    }

    private int c(Object[] var1) {
        EnderPearlEntity var4 = (EnderPearlEntity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 57555634897493L ^ var2;
        long var5 = var2 ^ 21919088601415L;
        Event.b();
        VecIntData2 var8 = this.a_VecIntData2(new Object[]{var4, var5});
        return var8 != null ? var8.aB() : 0;
    }

    private int d_int___(Object[] var1) {
        Event.b();
        int var5 = (Integer)this.f.getValue();
        return this.r ? 3 * var5 + 4 : var5 + 2;
    }

    private boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 57555634897493L ^ var2;
        long var4 = var2 ^ 40681293835819L;
        long var6 = var2 ^ 80434472716634L;
        if (this.z < 0) {
            return false;
        } else {
            EnderPearlEntity var9 = this.a_class_1684_(new Object[]{var4});
            if (var9 == null) {
                return true;
            } else {
                VecIntData2 var10 = this.a_VecIntData2(new Object[]{var9, var6});
                return var10 == null || var10.aB() <= this.z + 1;
            }
        }
    }

    private void n(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 57555634897493L ^ var2;
        long var4 = var2 ^ 1206142028419L;
        if (this.l != null) {
            this.m.add(this.l);
        }

        if (this.s != -1) {
            int var10000 = this.s;
            Object[] var10003 = new Object[]{null, var4};
            var10003[0] = var10000;
            InventoryUtil.a_void________(var10003);
        }

        this.j(new Object[0]);
    }

    private void o(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 57555634897493L ^ var2;
        long var4 = var2 ^ 62266675344930L;
        long var6 = var2 ^ 34400800056215L;
        long var8 = var2 ^ 110396454932233L;
        long var10 = var2 ^ 22767088068286L;
        long var12 = var2 ^ 22327863294645L;
        long var14 = var2 ^ 130204902028557L;
        long var16 = var2 ^ 117839239483888L;
        long var18 = var2 ^ 87004403312685L;
        long var20 = var2 ^ 23319315749502L;
        long var22 = var2 ^ 92455324790796L;
        long var24 = var2 ^ 99983372384162L;
        if (this.e_int__(new Object[]{var12}) != -1) {
            for (Entity var28 : b.world.getEntities()) {
                if (var28 instanceof EnderPearlEntity var29 && !this.m.contains(var29.getUuid())) {
                    double var30 = ((Float)this.a.getValue()).floatValue();
                    Vec3d var32 = b.player.getEyePos();
                    double var33 = var29.getX() - var32.x;
                    double var35 = var29.getY() - var32.y;
                    double var37 = var29.getZ() - var32.z;
                    if (!(var33 * var33 + var35 * var35 + var37 * var37 > var30 * var30)
                        && var29.getOwner() instanceof PlayerEntity var39
                        && !var39.equals(b.player)
                        && Prestige.i.a_boolean_(new Object[]{var39, var6})
                        && !Prestige.g.a(new Object[]{var39.getName().getString(), var4})) {
                        VecIntData2 var44 = this.a_VecIntData2(new Object[]{var29, var14});
                        if (var44 != null && var44.aB() <= 100) {
                            boolean var41 = (Boolean)this.e.getValue() && this.g(new Object[]{var8}) != -1 && this.f_int(new Object[]{var16}) != -1;
                            if ((var41 || InventoryUtil.a_Integer(new Object[]{Items.BOW, var10}) != null) && this.a_Integer__(new Object[]{var18}) != null) {
                                this.r = var41;
                                BlockPos var42 = this.a_class_2338____(new Object[]{var44.aA(), var24});
                                if (var42 != null) {
                                    this.l = var29.getUuid();
                                    this.n = var42.getX();
                                    this.o = var42.getY();
                                    this.p = var42.getZ();
                                    float var10001 = var41 ? 1.05F : 1.0F;
                                    Object[] var10004 = new Object[]{null, var20};
                                    var10004[0] = var10001;
                                    if (this.a_boolean___________________(var10004)) {
                                        this.s = InventoryUtil.a_int_____(new Object[]{var22});
                                        this.u = 0;
                                        this.t = 0;
                                        this.k = FireState.ARMED;
                                        return;
                                    }

                                    this.l = null;
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
        long var4 = var2 ^ 51598816251400L;
        long var6 = var2 ^ 101816702374277L;
        if (b.player != null && b.world != null && (Boolean)this.g.getValue()) {
            if (this.y == null
                || this.k != FireState.FIRE
                    && this.k != FireState.RELEASE
                    && this.k != FireState.SWITCH_XBOW
                    && this.k != FireState.SWITCH_BOW
                    && this.k != FireState.USE_BOW
                    && this.k != FireState.CHARGE_WAIT) {
                Vec3d var12 = null;
                if (this.k == FireState.SWITCH_RAIL || this.k == FireState.SWITCH_CART) {
                    var12 = new Vec3d(this.n + 0.5, this.o + 0.5, this.p + 0.5);
                } else if (this.k == FireState.SWITCH_FIRE && this.q != null) {
                    var12 = this.q.toCenterPos();
                }

                if (var12 != null) {
                    RenderBatch var10 = Prestige.o.a_RenderBatch(new Object[]{var12, var6});
                    if (var10 != null) {
                        float var13 = var10.a(new Object[0]) - b.player.getYaw();
                        Object[] var14 = new Object[]{null, var4};
                        var14[0] = var13;
                        float var11 = Math.abs(WorldUtil.b_float_____________________(var14));
                        if (var11 <= (Float)this.h.getValue()) {
                            return var10;
                        }
                    }
                }

                return null;
            } else {
                float var10000 = this.y.a(new Object[0]) - b.player.getYaw();
                Object[] var10003 = new Object[]{null, var4};
                var10003[0] = var10000;
                float var9 = Math.abs(WorldUtil.b_float_____________________(var10003));
                return var9 <= this.h.getValue() ? this.y : null;
            }
        } else {
            return null;
        }
    }

    @Subscribe
    public void a(TickEvent25 var1) {
        if ((Boolean)this.i.getValue()) {
            if (b.currentScreen == null || b.currentScreen instanceof ChatScreen) {
                if (b.world != null && b.player != null) {
                    if (this.k != FireState.IDLE) {
                        if (B == null) {
                            B = FontRenderer.d_FontRenderer(new Object[]{A, 15872041225672L});
                        }

                        EnderPearlEntity var30 = this.a_class_1684_(new Object[]{58875298170324L});
                        String var29 = var30 == null ? "!" : String.format("%.1fs", this.c(new Object[]{var30, 140618963028407L}) / 20.0F);
                        Vec3d var31 = MatrixStack.a_class_243_______________(new Object[]{new Vec3d(this.n + 0.5, this.o + 0.8, this.p + 0.5), 73023576373275L});
                        if (!(var31.z < 0.0) && !(var31.z >= 1.0)) {
                            Font var32 = Prestige.c.a(new Object[]{1236438218049L});
                            float var33 = var32.c_float___(new Object[]{78913221670390L});
                            float var34 = 16.0F;
                            float var35 = 3.0F;
                            float var36 = 0.5F;
                            float var37 = 4.0F;
                            float var38 = 3.0F;
                            float var39 = 5.0F;
                            float var40 = var32.a_float______________(new Object[]{var29, 47859522476307L});
                            float var41 = Math.max(var34, var40);
                            float var42 = var34 + var35 + var36 + var35 + var33;
                            float var43 = var41 + 2.0F * var37;
                            float var44 = var42 + 2.0F * var38;
                            float var45 = (float)var31.x;
                            float var46 = (float)var31.y;
                            float var47 = var45 - var43 / 2.0F;
                            float var48 = var46 - var44 / 2.0F;
                            Matrix4f var49 = new Matrix4f();
                            Color var50 = new Color(14, 14, 14, 200);
                            Color var51 = new Color(255, 255, 255, 50);
                            Color var52 = new Color(255, 255, 255, 255);
                            RenderHelper8 var10000 = var1.a;
                            MatrixCameraData var10001 = var1.b;
                            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 35553070502242L};
                            var10011[8] = var39;
                            var10011[7] = 5;
                            var10011[6] = var44;
                            var10011[5] = var43;
                            var10011[4] = var48;
                            var10011[3] = var47;
                            var10011[2] = var49;
                            var10011[1] = var10001;
                            var10011[0] = var10000;
                            RenderTarget.e(var10011);
                            var10000 = var1.a;
                            var10001 = var1.b;
                            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
                            var10011[8] = var39;
                            var10011[7] = var50;
                            var10011[6] = var44;
                            var10011[5] = var43;
                            var10011[4] = var48;
                            var10011[3] = var47;
                            var10011[2] = var49;
                            var10011[1] = var10001;
                            var10011[0] = var10000;
                            RoundedPosColorShader.c(var10011);
                            float var53 = var45 - var34 / 2.0F;
                            float var54 = var48 + var38;
                            Object[] var10009 = new Object[]{null, null, null, null, var49, B, var52, 73467207830142L};
                            var10009[3] = var34;
                            var10009[2] = var34;
                            var10009[1] = var54;
                            var10009[0] = var53;
                            MatrixStack.v(var10009);
                            float var55 = var47 + var37;
                            float var56 = var47 + var43 - var37;
                            float var57 = var54 + var34 + var35;
                            float var10004 = var57 + var36;
                            Object[] var10008 = new Object[]{null, null, null, null, null, var51, 43912076835818L};
                            var10008[4] = var10004;
                            var10008[3] = var56;
                            var10008[2] = var57;
                            var10008[1] = var55;
                            var10008[0] = var49;
                            RenderState.l(var10008);
                            float var58 = var45 - var40 / 2.0F;
                            float var59 = var57 + var36 + var35;
                            var10008 = new Object[]{null, null, null, null, var52, 36494305523424L};
                            var10008[3] = var59;
                            var10008[2] = var58;
                            var10008[1] = var29;
                            var10008[0] = var49;
                            var32.v(var10008);
                        }
                    }
                }
            }
        }
    }

    private void p(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        if (b.interactionManager.interactBlock(b.player, Hand.MAIN_HAND, var2).isAccepted()) {
            b.player.swingHand(Hand.MAIN_HAND);
            Prestige.o.g(new Object[]{true});
        }
    }

    private boolean f(Object[] var1) {
        double var2 = (Double)var1[0];
        double var4 = (Double)var1[1];
        double var6 = (Double)var1[2];
        long var8 = (Long)var1[3];
        var8 = 57555634897493L ^ var8;
        long var10 = var8 ^ 96828947016113L;
        Vec3d var13 = b.player.getEyePos();
        Event.b();
        double var14 = InventoryUtil2.b_float_____(new Object[]{var10});
        double var16 = var2 - var13.x;
        double var18 = var4 - var13.y;
        double var20 = var6 - var13.z;
        return var16 * var16 + var18 * var18 + var20 * var20 <= var14 * var14;
    }

    private int e_int__(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            Item var6 = b.player.getInventory().getStack(var5).getItem();

            for (Item var10 : j) {
                if (var6 == var10) {
                    return var5;
                }
            }
        }

        return -1;
    }

    private int f_int(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() == Items.FLINT_AND_STEEL) {
                return var5;
            }
        }

        return -1;
    }

    private int g(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (var6.getItem() == Items.CROSSBOW) {
                ChargedProjectilesComponent var7 = var6.get(DataComponentTypes.CHARGED_PROJECTILES);
                if (var7 != null) {
                    for (ItemStack var9 : var7.getProjectiles()) {
                        if (var9.getItem() == Items.ARROW || var9.getItem() == Items.SPECTRAL_ARROW) {
                            return var5;
                        }
                    }
                }
            }
        }

        return -1;
    }

    private Integer a_Integer__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 57555634897493L ^ var2;
        long var4 = var2 ^ 122926755187910L;
        long var6 = var2 ^ 52156261161588L;
        long var8 = var2 ^ 5395714251314L;
        long var10 = var2 ^ 48480068358095L;
        Event.b();
        Integer var13 = InventoryUtil.a_Integer(new Object[]{Items.TNT_MINECART, var4});
        if (var13 != null) {
            this.w = -1;
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
                    if (this.w != -1) {
                        if (Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{var8})) {
                            return this.w;
                        }

                        this.w = -1;
                    }

                    Object[] var10004 = new Object[]{null, null, var10};
                    var10004[1] = var16;
                    var10004[0] = var14;
                    InventoryUtil.f(var10004);
                    this.w = var16;
                    return var16;
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
