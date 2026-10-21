package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.LongData4;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class CartAura extends Module implements RenderContract3 {
    private RangeSetting a;
    private FloatSetting c;
    private BoolSetting d;
    private FloatSetting e;
    private RangeSetting f;
    private FloatSetting g;
    private BoolSetting h;
    private RenderHelper27 i = new RenderHelper27(26293358099357L);
    private RenderHelper27 j = new RenderHelper27(26293358099357L);
    private RenderHelper27 k = new RenderHelper27(26293358099357L);
    private RenderHelper27 l = new RenderHelper27(26293358099357L);
    private List m = new ArrayList();
    private TntMinecartEntity n;
    private int o = -1;
    private int p = 0;

    public CartAura() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.e.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL316_0 -> (Boolean)this.d.getValue(), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Cart);
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var7) {
            ItemStack var12 = var7.getHand().equals(Hand.MAIN_HAND) ? b.player.getMainHandStack() : b.player.getOffHandStack();
            if (var12.getItem() == Items.TNT_MINECART) {
                BlockHitResult var9 = var7.getBlockHitResult();
                long var10 = System.currentTimeMillis();
                this.m.add(new LongData4(var9.getBlockPos(), var10));
                this.m.add(new LongData4(WorldUtil2.a_class_2338______(new Object[]{var9, 110292667014705L}), var10));
            }
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.m.clear();
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 65603549542278L;
        long var6 = var2 ^ 29247161167908L;
        long var8 = var2 ^ 94148018906117L;
        long var10 = var2 ^ 18556217440230L;
        long var12 = var2 ^ 120434008667598L;
        long var14 = var2 ^ 136752692109656L;
        long var16 = var2 ^ 24717810023087L;
        long var18 = var2 ^ 58982489950237L;
        long var20 = var2 ^ 7477342050734L;
        long var22 = var2 ^ 8794929828675L;
        long var24 = var2 ^ 91260814807711L;
        long var26 = var2 ^ 40152458499673L;
        long var28 = var2 ^ 33065978990036L;
        long var30 = var2 ^ 85547089512629L;
        long var32 = var2 ^ 101816702374277L;
        long var34 = var2 ^ 96868503357288L;
        long var36 = var2 ^ 64921433391096L;
        if (b.player == null || b.world == null) {
            this.k(new Object[]{var36});
            return null;
        } else if (this.p == 2) {
            RenderHelper27 var43 = this.k;
            float var44 = (Float)this.e.getValue();
            Object[] var45 = new Object[]{null, var30};
            var45[0] = var44;
            if (var43.a(var45)) {
                this.j(new Object[]{var10});
            }

            return null;
        } else if (b.player.isUsingItem()) {
            return null;
        } else if (AutoObbyCrystal.R) {
            return null;
        } else if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else {
            if (this.n != null) {
                Vec3d var39 = this.a_class_243________(new Object[]{this.n, var8});
                if (!this.n.isAlive()
                    || this.a_boolean__________________(new Object[]{this.n, var16})
                    || Math.sqrt(b.player.squaredDistanceTo(var39.x, var39.y, var39.z)) > 5.0) {
                    this.n = null;
                }
            }

            if (this.n == null) {
                float var10002 = (Float)this.g.getValue();
                Object[] var10005 = new Object[]{null, var26};
                var10005[0] = var10002;
                this.n = this.a_class_1701_(var10005);
                if (this.n != null) {
                    this.a.reroll(new Object[]{var14});
                    this.i.a_void_____________________(new Object[]{var6});
                }
            }

            if (this.n == null) {
                if (this.p == 1) {
                    this.j(new Object[]{var10});
                }

                return null;
            } else {
                Vec3d var42 = this.a_class_243________(new Object[]{this.n, var8});
                RenderBatch var40 = Prestige.o.a_RenderBatch(new Object[]{var42, var32});
                if ((Boolean)this.h.getValue() && !var40.a_boolean________________________________________(new Object[0])) {
                    var40 = WorldUtil.d_RenderBatch(new Object[]{var40, var42, var20});
                }

                if (!b.player.canSee(this.n)) {
                    return null;
                } else if (this.a_double_____(new Object[]{this.n, var34}) > 3.0) {
                    return null;
                } else if (!this.i.b(new Object[]{this.a, var28})) {
                    return var40;
                } else {
                    if (this.p == 1) {
                        RenderHelper27 var10000 = this.j;
                        float var10001 = (Float)this.c.getValue();
                        Object[] var10004 = new Object[]{null, var30};
                        var10004[0] = var10001;
                        if (!var10000.a(var10004)) {
                            return var40;
                        }

                        if (!InventoryUtil.a(new Object[]{b.player.getMainHandStack(), var4})) {
                            this.j(new Object[]{var10});
                            return var40;
                        }
                    } else if (!InventoryUtil.a(new Object[]{b.player.getMainHandStack(), var4})) {
                        int var41 = this.d(new Object[]{var22});
                        if (var41 == -1) {
                            return null;
                        }

                        this.o = InventoryUtil.a_int_____(new Object[]{var18});
                        Object[] var10003 = new Object[]{null, var12};
                        var10003[0] = var41;
                        InventoryUtil.a_void________(var10003);
                        this.p = 1;
                        this.j.a_void_____________________(new Object[]{var6});
                        return var40;
                    }

                    if (!this.l.b(new Object[]{this.f, var28})) {
                        return var40;
                    } else if (var40.a_boolean________________________________________(new Object[0])) {
                        return var40;
                    } else {
                        InventoryUtil2.a_void_________(new Object[]{this.n, var24});
                        this.l.a_void_____________________(new Object[]{var6});
                        this.f.reroll(new Object[]{var14});
                        if (this.p == 1) {
                            if ((Boolean)this.d.getValue()) {
                                this.p = 2;
                                this.k.a_void_____________________(new Object[]{var6});
                            } else {
                                this.o = -1;
                                this.p = 0;
                            }
                        }

                        return var40;
                    }
                }
            }
        }
    }

    private TntMinecartEntity a_class_1701_(Object[] var1) {
        float var4 = (Float)var1[0];
        long var2 = (Long)var1[1];
        var2 = 28326220671996L ^ var2;
        long var5 = var2 ^ 115341421025696L;
        long var7 = var2 ^ 129805134438857L;
        long var9 = var2 ^ 47539367544586L;
        long var11 = var2 ^ 129796871681829L;
        if (b.world == null) {
            return null;
        } else {
            long var14 = System.currentTimeMillis();
            this.m.removeIf(lpL315_0 -> var14 - ((dev.zprestige.prestige.data.LongData4)lpL315_0).az() > 5000L);
            TntMinecartEntity var16 = null;
            double var17 = Double.MAX_VALUE;
            RenderBatch var19 = new RenderBatch(b.player.getYaw(), b.player.getPitch());

            for (Entity var21 : b.world.getEntities()) {
                if (var21 instanceof TntMinecartEntity var22 && var22.isAlive() && !this.a_boolean__________________(new Object[]{var22, var9})) {
                    Vec3d var23 = this.a_class_243________(new Object[]{var22, var5});
                    double var24 = b.player.squaredDistanceTo(var23.x, var23.y, var23.z);
                    if (!(Math.sqrt(var24) > 5.0)) {
                        if (var4 < 360.0F) {
                            float var10000 = (float)var23.x;
                            float var10001 = (float)var23.y;
                            float var10002 = (float)var23.z;
                            Object[] var10005 = new Object[]{null, null, null, var11};
                            var10005[2] = var10002;
                            var10005[1] = var10001;
                            var10005[0] = var10000;
                            RenderBatch var26 = WorldUtil.b_RenderBatch_____________(var10005);
                            var10005 = new Object[]{null, null, null, var7};
                            var10005[2] = var4;
                            var10005[1] = var26;
                            var10005[0] = var19;
                            if (!WorldUtil.f(var10005)) {
                                continue;
                            }
                        }

                        if (var24 < var17) {
                            var16 = var22;
                            var17 = var24;
                        }
                    }
                }
            }

            return var16;
        }
    }

    private boolean a_boolean__________________(Object[] var1) {
        TntMinecartEntity var2 = (TntMinecartEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 28326220671996L ^ var3;
        long var5 = var3 ^ 138167091794822L;
        Event.b();
        Vec3d var8 = PositionUtil.a(new Object[]{var2, var5});
        long var9 = System.currentTimeMillis();

        for (LongData4 var12 : (Iterable<LongData4>)(this.m)) {
            if (var9 - var12.az() <= 5000L && var8.isInRange(var12.ay().toCenterPos(), 1.5)) {
                return true;
            }
        }

        return false;
    }

    private int d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 28326220671996L ^ var2;
        long var4 = var2 ^ 41326321930041L;
        Event.b();

        for (int var7 = 0; var7 <= 8; var7++) {
            if (InventoryUtil.a(new Object[]{b.player.getInventory().getStack(var7), var4})) {
                return var7;
            }
        }

        return -1;
    }

    private Vec3d a_class_243________(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        return var2.getBoundingBox().getCenter();
    }

    private double a_double_____(Object[] var1) {
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

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 28326220671996L ^ var2;
        long var4 = var2 ^ 46650497383476L;
        if (this.o != -1) {
            Renderer2D var10000 = Prestige.o;
            int var10001 = this.o;
            Object[] var10004 = new Object[]{null, var4};
            var10004[0] = var10001;
            var10000.i(var10004);
            this.o = -1;
        }

        this.p = 0;
    }

    private void k(Object[] var1) {
        this.n = null;
        this.m.clear();
        this.o = -1;
        this.p = 0;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 9491932282183L;
        long var6 = var2 ^ 91082959635357L;
        this.k(new Object[]{var4});
        Prestige.o.e(new Object[]{this, var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        long var6 = var2 ^ 132811803356986L;
        Prestige.o.f(new Object[]{this, var4});
        this.j(new Object[]{var6});
        this.n = null;
        this.m.clear();
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
