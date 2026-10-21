package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.type.CartState;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class XbowCart extends Module implements RenderContract3 {
    private IntSetting a;
    private BoolSetting d;
    private FloatSetting c;
    private static final Item[] e = new Item[]{Items.RAIL, Items.POWERED_RAIL, Items.DETECTOR_RAIL, Items.ACTIVATOR_RAIL};
    private CartState f = CartState.IDLE;
    private int g;
    private int h;
    private int i;
    private BlockPos j;
    private int k = -1;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = -1;
    private RenderBatch q;

    public XbowCart() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.c.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL358_0 -> (Boolean)this.d.getValue(), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Cart);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 136621126230743L;
        long var6 = var2 ^ 47128962125542L;
        long var8 = var2 ^ 93334552156450L;
        long var10 = var2 ^ 6654513203874L;
        long var12 = var2 ^ 32457309120265L;
        long var14 = var2 ^ 91082959635357L;
        long var16 = var2 ^ 84081701449888L;
        Event.b();
        this.j(new Object[0]);
        this.k = InventoryUtil.a_int_____(new Object[]{var10});
        if (this.f(new Object[]{var12}) == -1) {
            this.k(new Object[]{var6});
        } else if (this.d_int____(new Object[]{var8}) == -1) {
            this.k(new Object[]{var6});
        } else if (this.a_Integer___(new Object[]{var16}) == null) {
            this.k(new Object[]{var6});
        } else if (this.e(new Object[]{var4}) == -1) {
            this.k(new Object[]{var6});
        } else {
            this.f = CartState.SWITCH_RAIL;
            Prestige.o.e(new Object[]{this, var14});
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
        this.j(new Object[0]);
    }

    private void j(Object[] var1) {
        this.f = CartState.IDLE;
        this.j = null;
        this.k = -1;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = -1;
        this.q = null;
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 53810106211380L ^ var2;
        long var4 = var2 ^ 88056286525842L;
        this.m(new Object[]{var4});
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 53810106211380L ^ var2;
        long var4 = var2 ^ 132265930987886L;
        long var6 = var2 ^ 119095194322271L;
        if (this.k != -1) {
            int var10000 = this.k;
            Object[] var10003 = new Object[]{null, var6};
            var10003[0] = var10000;
            InventoryUtil.a_void________(var10003);
        }

        this.m(new Object[]{var4});
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 53810106211380L ^ var2;
        long var4 = var2 ^ 121253479255518L;
        if (this.isEnabled()) {
            this.toggle(new Object[]{var4});
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            if (this.f != CartState.IDLE) {
                if (++this.n > 80) {
                    this.k(new Object[]{95649723638752L});
                } else {
                    switch (this.f) {
                        case SWITCH_RAIL:
                            int var36 = this.d_int____(new Object[]{44805536396324L});
                            if (var36 == -1) {
                                this.k(new Object[]{95649723638752L});
                                return;
                            }

                            if (b.crosshairTarget instanceof BlockHitResult var37 && var37.getSide() == Direction.UP) {
                                BlockPos var38 = var37.getBlockPos();
                                this.g = var38.getX();
                                this.h = var38.getY() + 1;
                                this.i = var38.getZ();
                                if (!b.world.getBlockState(var38.up()).isReplaceable()) {
                                    this.k(new Object[]{95649723638752L});
                                    return;
                                }

                                double var40 = this.g + 0.5;
                                double var41 = this.h + 0.5;
                                double var46 = this.i + 0.5;
                                Object[] var48 = new Object[]{null, null, null, 12947405432978L};
                                var48[2] = var46;
                                var48[1] = var41;
                                var48[0] = var40;
                                if (!this.d(var48)) {
                                    this.k(new Object[]{95649723638752L});
                                    return;
                                }

                                Object[] var47 = new Object[]{null, 37392877673079L};
                                var47[0] = var36;
                                InventoryUtil.a_void________(var47);
                                this.n(
                                    new Object[]{
                                        new BlockHitResult(new Vec3d(this.g + 0.5, var38.getY() + 1.0, this.i + 0.5), Direction.UP, var38, false),
                                        31783843004644L
                                    }
                                );
                                this.l = (Integer)this.a.getValue();
                                this.f = CartState.SWITCH_CART;
                                break;
                            }

                            if (++this.m > 10) {
                                this.k(new Object[]{95649723638752L});
                            }

                            return;
                        case SWITCH_CART:
                            if (this.l > 0) {
                                this.l--;
                                return;
                            }

                            Integer var35 = this.a_Integer___(new Object[]{53171514072486L});
                            if (var35 == null) {
                                this.k(new Object[]{95649723638752L});
                                return;
                            }

                            if (b.player.getInventory().getStack(var35).getItem() != Items.TNT_MINECART) {
                                return;
                            }

                            double var10001 = this.g + 0.5;
                            double var10002 = this.h + 0.5;
                            double var44 = this.i + 0.5;
                            Object[] var10006 = new Object[]{null, null, null, 12947405432978L};
                            var10006[2] = var44;
                            var10006[1] = var10002;
                            var10006[0] = var10001;
                            if (!this.d(var10006)) {
                                this.k(new Object[]{95649723638752L});
                                return;
                            }

                            int var39 = var35;
                            Object[] var45 = new Object[]{null, 37392877673079L};
                            var45[0] = var39;
                            InventoryUtil.a_void________(var45);
                            InventoryUtil2.a(
                                new Object[]{
                                    new BlockHitResult(
                                        new Vec3d(this.g + 0.5, this.h + 0.125, this.i + 0.5), Direction.UP, new BlockPos(this.g, this.h, this.i), false
                                    ),
                                    96519249476482L
                                }
                            );
                            this.l = (Integer)this.a.getValue();
                            this.f = CartState.SWITCH_FIRE;
                            break;
                        case SWITCH_FIRE:
                            if (this.l > 0) {
                                this.l--;
                                return;
                            }

                            if (this.j == null) {
                                this.j = this.a_class_2338_____(new Object[]{10211909878244L});
                                if (this.j == null) {
                                    this.k(new Object[]{95649723638752L});
                                    return;
                                }
                            }

                            int var34 = this.e(new Object[]{423783403473L});
                            if (var34 == -1) {
                                this.k(new Object[]{95649723638752L});
                                return;
                            }

                            BlockPos var30 = this.j.down();
                            Object[] var43 = new Object[]{null, 37392877673079L};
                            var43[0] = var34;
                            InventoryUtil.a_void________(var43);
                            this.n(
                                new Object[]{
                                    new BlockHitResult(new Vec3d(var30.getX() + 0.5, var30.getY() + 1.0, var30.getZ() + 0.5), Direction.UP, var30, false),
                                    31783843004644L
                                }
                            );
                            this.l = (Integer)this.a.getValue();
                            this.f = CartState.SWITCH_XBOW;
                            break;
                        case SWITCH_XBOW:
                            if (this.l > 0) {
                                this.l--;
                                return;
                            }

                            int var33 = this.f(new Object[]{107366408669711L});
                            if (var33 == -1) {
                                this.k(new Object[]{95649723638752L});
                                return;
                            }

                            Object[] var42 = new Object[]{null, 37392877673079L};
                            var42[0] = var33;
                            InventoryUtil.a_void________(var42);
                            this.o = 0;
                            this.f = CartState.FIRE;
                            break;
                        case FIRE:
                            if (this.q == null) {
                                this.k(new Object[]{95649723638752L});
                                return;
                            }

                            if (InventoryUtil.l(new Object[]{Items.CROSSBOW, 65539821781598L})) {
                                RenderBatch var29 = Prestige.o.c(new Object[0]);
                                if (var29 != null && var29.a_boolean________________________________________(new Object[0]) && this.o++ < 5) {
                                    return;
                                }

                                b.interactionManager.interactItem(b.player, Hand.MAIN_HAND);
                                Prestige.o.g(new Object[]{true});
                                this.l(new Object[]{139241022833436L});
                            } else {
                                int var32 = this.f(new Object[]{107366408669711L});
                                if (var32 == -1) {
                                    this.k(new Object[]{95649723638752L});
                                    return;
                                }

                                Object[] var10003 = new Object[]{null, 37392877673079L};
                                var10003[0] = var32;
                                InventoryUtil.a_void________(var10003);
                            }
                    }
                }
            }
        }
    }

    private Vec3d a_class_243_____________(Object[] var1) {
        Vec3d var4 = b.player.getEyePos();
        Vec3d var5 = new Vec3d(this.g + 0.5, this.h + 0.45, this.i + 0.5);
        double var6 = var4.distanceTo(var5);
        int var8 = (int)Math.ceil(var6 / 3.15);
        double var9 = 0.05 * var8 * (var8 + 1) / 2.0;
        return var5.add(0.0, var9, 0.0);
    }

    private RenderBatch b_RenderBatch_____(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        Event.b();
        Vec3d var6 = b.player.getEyePos();
        double var7 = var4.x - var6.x;
        double var9 = var4.y - (var6.y - 0.1);
        double var11 = var4.z - var6.z;
        double var13 = Math.sqrt(var7 * var7 + var9 * var9 + var11 * var11);
        if (var13 < 1.0E-6) {
            return null;
        } else {
            float var15 = (float)(Math.toDegrees(Math.atan2(var11, var7)) - 90.0);
            float var16 = (float)(-Math.toDegrees(Math.asin(var9 / var13)));
            return new RenderBatch(var15, var16);
        }
    }

    private boolean a_boolean___________________________(Object[] var1) {
        float var5 = (Float)var1[0];
        float var6 = (Float)var1[1];
        BlockPos var2 = (BlockPos)var1[2];
        Vec3d var8 = b.player.getEyePos();
        double var9 = var8.x;
        double var11 = var8.y - 0.1;
        double var13 = var8.z;
        Event.b();
        double var15 = Math.toRadians(var5);
        double var17 = Math.toRadians(var6);
        double var19 = -Math.sin(var15) * Math.cos(var17);
        double var21 = -Math.sin(var17);
        double var23 = Math.cos(var15) * Math.cos(var17);
        double var25 = Math.sqrt(var19 * var19 + var21 * var21 + var23 * var23);
        var19 = var19 / var25 * 3.15;
        var21 = var21 / var25 * 3.15;
        var23 = var23 / var25 * 3.15;
        var19 += b.player.getVelocity().x;
        var23 += b.player.getVelocity().z;
        if (!b.player.isOnGround()) {
            var21 += b.player.getVelocity().y;
        }

        Box var27 = new Box(this.g + 0.01, this.h + 0.0625, this.i + 0.01, this.g + 0.99, this.h + 0.7625, this.i + 0.99);
        BlockPos var28 = new BlockPos(this.g, this.h, this.i);
        boolean var29 = false;

        for (int var30 = 0; var30 < 60; var30++) {
            Vec3d var31 = new Vec3d(var9, var11, var13);
            double var32 = Math.sqrt(var19 * var19 + var21 * var21 + var23 * var23);
            int var34 = Math.max(1, (int)Math.ceil(var32 / 0.25));

            for (int var35 = 0; var35 < var34; var35++) {
                var9 += var19 / var34;
                var11 += var21 / var34;
                var13 += var23 / var34;
                if (!var29 && var2 != null && new Box(var2).contains(var9, var11, var13)) {
                    var29 = true;
                }

                if (var27.contains(var9, var11, var13)) {
                    return var29;
                }
            }

            Vec3d var42 = new Vec3d(var9, var11, var13);
            BlockHitResult var36 = b.world.raycast(new RaycastContext(var31, var42, ShapeType.OUTLINE, FluidHandling.NONE, b.player));
            if (var36 != null && var36.getType() == Type.BLOCK && !var36.getBlockPos().equals(var28)) {
                return false;
            }

            if (var11 < this.h - 3) {
                return false;
            }

            var19 *= 0.99;
            var23 *= 0.99;
            var21 = var21 * 0.99 - 0.05;
        }

        return false;
    }

    private BlockPos a_class_2338_____(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 53810106211380L ^ var2;
        long var4 = var2 ^ 41734967734239L;
        long var6 = var2 ^ 131231891155085L;
        long var8 = var2 ^ 55523399873858L;
        Event.b();
        RenderBatch var11 = this.b_RenderBatch_____(new Object[]{this.a_class_243_____________(new Object[]{var6}), var4});
        if (var11 == null) {
            return null;
        } else {
            Vec3d var12 = b.player.getEyePos();
            double var13 = var12.x;
            double var15 = var12.y - 0.1;
            double var17 = var12.z;
            double var19 = Math.toRadians(var11.a(new Object[0]));
            double var21 = Math.toRadians(var11.b(new Object[0]));
            double var23 = -Math.sin(var19) * Math.cos(var21);
            double var25 = -Math.sin(var21);
            double var27 = Math.cos(var19) * Math.cos(var21);
            double var29 = Math.sqrt(var23 * var23 + var25 * var25 + var27 * var27);
            var23 = var23 / var29 * 3.15;
            var25 = var25 / var29 * 3.15;
            var27 = var27 / var29 * 3.15;
            var23 += b.player.getVelocity().x;
            var27 += b.player.getVelocity().z;
            if (!b.player.isOnGround()) {
                var25 += b.player.getVelocity().y;
            }

            BlockPos var31 = new BlockPos(this.g, this.h, this.i);
            Box var32 = b.player.getBoundingBox().expand(0.3);

            for (int var33 = 0; var33 < 60; var33++) {
                double var34 = Math.sqrt(var23 * var23 + var25 * var25 + var27 * var27);
                int var36 = Math.max(1, (int)Math.ceil(var34 / 0.25));

                for (int var37 = 0; var37 < var36; var37++) {
                    var13 += var23 / var36;
                    var15 += var25 / var36;
                    var17 += var27 / var36;
                    BlockPos var38 = BlockPos.ofFloored(var13, var15, var17);
                    if (var38.equals(var31) || var38.equals(var31.up())) {
                        return null;
                    }

                    if (!var32.intersects(new Box(var38))) {
                        double var10001 = var38.getX() + 0.5;
                        double var10002 = var38.getY();
                        double var10003 = var38.getZ() + 0.5;
                        Object[] var10006 = new Object[]{null, null, null, var8};
                        var10006[2] = var10003;
                        var10006[1] = var10002;
                        var10006[0] = var10001;
                        if (this.d(var10006)
                            && b.world.getBlockState(var38).isReplaceable()
                            && b.world.getBlockState(var38.down()).isSideSolidFullSquare(b.world, var38.down(), Direction.UP)) {
                            return var38;
                        }
                    }
                }

                var23 *= 0.99;
                var27 *= 0.99;
                var25 = var25 * 0.99 - 0.05;
            }

            return null;
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 92112613088694L;
        long var6 = var2 ^ 51598816251400L;
        long var8 = var2 ^ 1551560694500L;
        long var10 = var2 ^ 101816702374277L;
        long var12 = var2 ^ 112432525177874L;
        if (b.player == null || b.world == null || !(Boolean)this.d.getValue()) {
            return null;
        } else if (this.f != CartState.SWITCH_XBOW && this.f != CartState.FIRE) {
            Vec3d var18 = null;
            if (this.f == CartState.SWITCH_CART) {
                var18 = new Vec3d(this.g + 0.5, this.h + 0.5, this.i + 0.5);
            } else if (this.f == CartState.SWITCH_FIRE && this.j != null) {
                var18 = this.j.toCenterPos();
            }

            if (var18 != null) {
                RenderBatch var19 = Prestige.o.a_RenderBatch(new Object[]{var18, var10});
                if (var19 != null) {
                    float var20 = var19.a(new Object[0]) - b.player.getYaw();
                    Object[] var21 = new Object[]{null, var6};
                    var21[0] = var20;
                    float var17 = Math.abs(WorldUtil.b_float_____________________(var21));
                    if (var17 <= (Float)this.c.getValue()) {
                        return var19;
                    }
                }
            }

            return null;
        } else {
            RenderBatch var15 = this.b_RenderBatch_____(new Object[]{this.a_class_243_____________(new Object[]{var8}), var4});
            if (var15 != null) {
                float var10001 = var15.a(new Object[0]);
                float var10002 = var15.b(new Object[0]);
                Object[] var10006 = new Object[]{null, null, this.j, var12};
                var10006[1] = var10002;
                var10006[0] = var10001;
                if (this.a_boolean___________________________(var10006)) {
                    this.q = var15;
                    float var10000 = var15.a(new Object[0]) - b.player.getYaw();
                    Object[] var10003 = new Object[]{null, var6};
                    var10003[0] = var10000;
                    float var16 = Math.abs(WorldUtil.b_float_____________________(var10003));
                    if (var16 <= (Float)this.c.getValue()) {
                        return var15;
                    }
                }
            }

            return null;
        }
    }

    private void n(Object[] var1) {
        BlockHitResult var4 = (BlockHitResult)var1[0];
        if (b.interactionManager.interactBlock(b.player, Hand.MAIN_HAND, var4).isAccepted()) {
            b.player.swingHand(Hand.MAIN_HAND);
            Prestige.o.g(new Object[]{true});
        }
    }

    private boolean d(Object[] var1) {
        double var2 = (Double)var1[0];
        double var4 = (Double)var1[1];
        double var6 = (Double)var1[2];
        long var8 = (Long)var1[3];
        var8 = 53810106211380L ^ var8;
        long var10 = var8 ^ 46362110231072L;
        Event.b();
        Vec3d var13 = b.player.getEyePos();
        double var14 = InventoryUtil2.b_float_____(new Object[]{var10});
        double var16 = var2 - var13.x;
        double var18 = var4 - var13.y;
        double var20 = var6 - var13.z;
        return var16 * var16 + var18 * var18 + var20 * var20 <= var14 * var14;
    }

    private int d_int____(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            Item var6 = b.player.getInventory().getStack(var5).getItem();

            for (Item var10 : e) {
                if (var6 == var10) {
                    return var5;
                }
            }
        }

        return -1;
    }

    private int e(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() == Items.FLINT_AND_STEEL) {
                return var5;
            }
        }

        return -1;
    }

    private int f(Object[] var1) {
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

    private Integer a_Integer___(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 53810106211380L ^ var2;
        long var4 = var2 ^ 63942377074820L;
        long var6 = var2 ^ 134712459994678L;
        long var8 = var2 ^ 89072967738992L;
        long var10 = var2 ^ 134127642260365L;
        Event.b();
        Integer var13 = InventoryUtil.a_Integer(new Object[]{Items.TNT_MINECART, var4});
        if (var13 != null) {
            this.p = -1;
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
                    if (this.p != -1) {
                        if (Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{var8})) {
                            return this.p;
                        }

                        this.p = -1;
                    }

                    Object[] var10004 = new Object[]{null, null, var10};
                    var10004[1] = var16;
                    var10004[0] = var14;
                    InventoryUtil.f(var10004);
                    this.p = var16;
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
