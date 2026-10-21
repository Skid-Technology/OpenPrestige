package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.network.NetworkUtil;
import dev.zprestige.prestige.reflect.MethodAccessor;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.type.PearlStage;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class FastPearlCatch extends Module implements RenderContract3 {
    private static final float a = 1500.0F;
    private EnumSetting c;
    private FloatSetting d;
    private FloatSetting e;
    private FloatSetting f;
    private FloatSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private FloatSetting j;
    private boolean k;
    private boolean l;
    private RenderHelper27 m = new RenderHelper27(26293358099357L);
    private boolean n;
    private boolean o;
    private float p;
    private float q;
    private RenderBatch r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private Object w;
    private Object x;
    private int y = Integer.MIN_VALUE;
    private PearlStage z = PearlStage.PREP;
    private int A;
    private int B = -1;

    public FastPearlCatch() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.d.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL208_0 -> ((String)this.c.getValue()).equals("Swap"), 35046756052036L});
        this.f.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL206_0 -> ((String)this.c.getValue()).equals("Offhand"), 35046756052036L});
        this.e.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL207_0 -> ((String)this.c.getValue()).equals("Offhand"), 35046756052036L});
        this.i.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL204_0 -> ((String)this.c.getValue()).equals("Swap"), 73196168581295L});
        this.j.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL203_0 -> (Boolean)this.i.getValue(), 35046756052036L});
        this.h.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL205_0 -> ((String)this.c.getValue()).equals("Swap"), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (this.x != b.world) {
            this.m(new Object[]{18978719144907L});
        }

        if ((Boolean)this.i.getValue() && ((String)this.c.getValue()).equals("Swap")) {
            if (this.e(new Object[]{138411142906974L})) {
                this.k(new Object[]{49387181425026L});
            } else {
                if (!this.k) {
                    RenderBatch var10000 = new RenderBatch(this.q, -(Float)this.d.getValue());
                    float var10001 = (Float)this.j.getValue() / 2.0F;
                    Object[] var10006 = new Object[]{null, null, null, null, 40447786406815L};
                    var10006[3] = 0.2F;
                    var10006[2] = 0.0F;
                    var10006[1] = var10001;
                    var10006[0] = var10000;
                    if (WorldUtil.c(var10006)) {
                        this.n = true;
                    }
                } else if (this.l) {
                    if (this.u) {
                        return;
                    }

                    RenderBatch var2 = new RenderBatch(this.q, this.p);
                    float var3 = (Float)this.j.getValue() / 2.0F;
                    Object[] var4 = new Object[]{null, null, null, null, 40447786406815L};
                    var4[3] = 0.2F;
                    var4[2] = 0.0F;
                    var4[1] = var3;
                    var4[0] = var2;
                    if (WorldUtil.c(var4)) {
                        this.toggle(new Object[]{115103427876780L});
                    }
                } else {
                    this.o = true;
                }
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 43040229079061L;
        long var6 = var2 ^ 52277468023920L;
        long var8 = var2 ^ 29247161167908L;
        long var10 = var2 ^ 77721887434032L;
        long var12 = var2 ^ 55425823108071L;
        long var14 = var2 ^ 120434008667598L;
        long var16 = var2 ^ 102640691698968L;
        long var18 = var2 ^ 109265412501051L;
        long var20 = var2 ^ 30056340190671L;
        long var22 = var2 ^ 13724982201792L;
        long var24 = var2 ^ 77504906702022L;
        long var26 = var2 ^ 106126499036750L;
        long var28 = var2 ^ 90200738560140L;
        long var30 = var2 ^ 104214641439858L;
        if (this.x != b.world) {
            this.m(new Object[]{var30});
        }

        boolean var33 = this.e(new Object[]{var12});
        if (!Prestige.o.c_boolean____(new Object[]{var24})) {
            this.m(new Object[]{var30});
            return null;
        } else if (var33) {
            this.k(new Object[]{var18});
            return this.u ? this.r : null;
        } else if (this.r != null && this.u) {
            return this.r;
        } else if (b.isWindowFocused() && b.currentScreen == null) {
            if ((Boolean)this.i.getValue() && ((String)this.c.getValue()).equals("Swap")) {
                int var45 = this.k ? -1 : this.d_int_______(new Object[]{var6});
                int var46 = this.e_int_____(new Object[]{var16});
                if ((this.k || var45 != -1) && var46 != -1) {
                    if (this.n) {
                        RenderBatch var49 = this.c(new Object[]{new RenderBatch(this.q, -(Float)this.d.getValue()), var20});
                        Object[] var73 = new Object[]{null, Hand.MAIN_HAND, var28};
                        var73[0] = var45;
                        if (this.a_boolean______________________________(var73)) {
                            this.k = true;
                            this.n = false;
                            this.m.a_void_____________________(new Object[]{var8});
                        }

                        return var49;
                    } else {
                        if (this.o) {
                            EnderPearlEntity var48 = null;
                            int var50 = Integer.MAX_VALUE;

                            for (Entity var58 : b.world.getEntities()) {
                                if (var58 instanceof EnderPearlEntity var63 && var63.getOwner() == b.player && var63.age < var50) {
                                    var50 = var63.age;
                                    var48 = var63;
                                }
                            }

                            if (var48 == null) {
                                for (Entity var59 : b.world.getEntities()) {
                                    if (var59 instanceof EnderPearlEntity var64) {
                                        if (var64.getOwner() == b.player) {
                                            var48 = var64;
                                        }
                                        break;
                                    }
                                }
                            }

                            if (var48 != null) {
                                Vec3d var55 = b.player.getVelocity();
                                float var60 = this.q;
                                double var65 = Math.toRadians(var60);
                                Vec3d var66 = new Vec3d(-Math.sin(var65), 0.0, Math.cos(var65)).normalize();
                                float var67 = (float)(var55.x * var66.x + var55.z * var66.z);
                                RenderBatch var68 = this.c(new Object[]{new RenderBatch(this.q, -(Float)this.g.getValue() + var67 * 1.6F), var20});
                                Object[] var72 = new Object[]{null, Hand.MAIN_HAND, var28};
                                var72[0] = var46;
                                if (this.a_boolean______________________________(var72)) {
                                    this.l = true;
                                    this.o = false;
                                    if ((Boolean)this.h.getValue() && this.A != var46) {
                                        int var69 = this.A;
                                        Object[] var70 = new Object[]{null, var14};
                                        var70[0] = var69;
                                        InventoryUtil.a_void________(var70);
                                    }
                                }

                                return new RenderBatch(var68.a(new Object[0]), var68.b(new Object[0]));
                            }

                            if (this.d(new Object[]{var26})) {
                                this.toggle(new Object[]{var4});
                            }
                        }

                        return null;
                    }
                } else {
                    this.toggle(new Object[]{var4});
                    return null;
                }
            } else if (((String)this.c.getValue()).equals("Offhand")) {
                return this.b_RenderBatch_________(new Object[]{var10});
            } else {
                int var34 = this.k ? -1 : this.d_int_______(new Object[]{var6});
                int var35 = this.e_int_____(new Object[]{var16});
                if ((this.k || var34 != -1) && var35 != -1) {
                    if (!this.k) {
                        RenderBatch var47 = this.c(new Object[]{new RenderBatch(this.q, -(Float)this.d.getValue()), var20});
                        Object[] var71 = new Object[]{null, Hand.MAIN_HAND, var28};
                        var71[0] = var34;
                        if (this.a_boolean______________________________(var71)) {
                            this.k = true;
                            this.m.a_void_____________________(new Object[]{var8});
                        }

                        return var47;
                    } else {
                        EnderPearlEntity var36 = null;
                        int var37 = Integer.MAX_VALUE;

                        for (Entity var39 : b.world.getEntities()) {
                            if (var39 instanceof EnderPearlEntity var40 && var40.getOwner() == b.player && var40.age < var37) {
                                var37 = var40.age;
                                var36 = var40;
                            }
                        }

                        if (var36 == null) {
                            for (Entity var56 : b.world.getEntities()) {
                                if (var56 instanceof EnderPearlEntity var61) {
                                    if (var61.getOwner() == b.player) {
                                        var36 = var61;
                                    }
                                    break;
                                }
                            }
                        }

                        if (var36 != null) {
                            Vec3d var52 = b.player.getVelocity();
                            float var57 = this.q;
                            double var62 = Math.toRadians(var57);
                            Vec3d var42 = new Vec3d(-Math.sin(var62), 0.0, Math.cos(var62)).normalize();
                            float var43 = (float)(var52.x * var42.x + var52.z * var42.z);
                            RenderBatch var44 = this.c(new Object[]{new RenderBatch(this.q, -(Float)this.g.getValue() + var43 * 1.6F), var20});
                            Object[] var10005 = new Object[]{null, Hand.MAIN_HAND, var28};
                            var10005[0] = var35;
                            if (this.a_boolean______________________________(var10005)) {
                                if ((Boolean)this.h.getValue() && this.A != var35) {
                                    int var10000 = this.A;
                                    Object[] var10003 = new Object[]{null, var14};
                                    var10003[0] = var10000;
                                    InventoryUtil.a_void________(var10003);
                                }

                                this.v = true;
                            }

                            return new RenderBatch(var44.a(new Object[0]), var44.b(new Object[0]));
                        } else {
                            if (this.d(new Object[]{var26})) {
                                this.toggle(new Object[]{var4});
                            }

                            return null;
                        }
                    }
                } else {
                    this.toggle(new Object[]{var4});
                    return null;
                }
            }
        } else {
            if (!this.u) {
                this.l(new Object[]{var22});
            }

            return null;
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11907200794228L ^ var2;
        long var4 = var2 ^ 43551637260431L;
        RenderHelper27 var10000 = this.m;
        Object[] var10004 = new Object[]{null, var4};
        var10004[0] = 1500.0F;
        return var10000.a(var10004);
    }

    private RenderBatch b_RenderBatch_________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11907200794228L ^ var2;
        long var4 = var2 ^ 117928000512849L;
        long var6 = var2 ^ 109880814704436L;
        long var8 = var2 ^ 95648781314912L;
        long var10 = var2 ^ 37301510663818L;
        long var12 = var2 ^ 18956659394140L;
        long var14 = var2 ^ 56326173299187L;
        long var16 = var2 ^ 11467018583072L;
        long var18 = var2 ^ 133868683809625L;
        long var20 = var2 ^ 95873811754635L;
        long var22 = var2 ^ 49348457161994L;
        long var24 = var2 ^ 33454238344136L;
        long var26 = var2 ^ 125964507441939L;
        long var28 = var2 ^ 6427782732092L;
        switch (this.z) {
            case PREP:
                if (Prestige.o.d_boolean______(new Object[0])) {
                    return null;
                } else {
                    int var40 = this.d_int_______(new Object[]{var6});
                    int var42 = this.e_int_____(new Object[]{var12});
                    boolean var43 = InventoryUtil.m(new Object[]{Items.WIND_CHARGE, var28});
                    if (var40 != -1 && (var43 || var42 != -1)) {
                        this.A = InventoryUtil.a_int_____(new Object[]{var18});
                        this.B = -1;
                        if (!var43) {
                            if (InventoryUtil.a_int_____(new Object[]{var18}) != var42) {
                                Object[] var46 = new Object[]{null, var10};
                                var46[0] = var42;
                                InventoryUtil.a_void________(var46);
                                this.j(new Object[]{var26});
                            }

                            NetworkUtil.a(new Object[]{new PlayerActionC2SPacket(Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN), var14});
                            this.B = var42;
                        }

                        if (InventoryUtil.a_int_____(new Object[]{var18}) != var40) {
                            Object[] var47 = new Object[]{null, var10};
                            var47[0] = var40;
                            InventoryUtil.a_void________(var47);
                            this.j(new Object[]{var26});
                        }

                        Prestige.o.g(new Object[]{true});
                        this.z = PearlStage.PEARL;
                        return this.c(new Object[]{new RenderBatch(this.q, -(Float)this.e.getValue()), var20});
                    }

                    this.toggle(new Object[]{var4});
                    return null;
                }
            case PEARL:
                int var39 = this.d_int_______(new Object[]{var6});
                if (var39 == -1) {
                    this.toggle(new Object[]{var4});
                    return null;
                }

                RenderBatch var41 = this.c(new Object[]{new RenderBatch(this.q, -(Float)this.e.getValue()), var20});
                Object[] var48 = new Object[]{null, Hand.MAIN_HAND, var24};
                var48[0] = var39;
                if (this.a_boolean______________________________(var48)) {
                    this.m.a_void_____________________(new Object[]{var8});
                    this.z = PearlStage.WIND;
                }

                return var41;
            case WIND:
                EnderPearlEntity var31 = this.a_class_1684__(new Object[]{var16});
                if (var31 == null) {
                    if (this.d(new Object[]{var22})) {
                        this.toggle(new Object[]{var4});
                    }

                    return null;
                } else {
                    Vec3d var32 = b.player.getVelocity();
                    double var33 = Math.toRadians(this.q);
                    Vec3d var35 = new Vec3d(-Math.sin(var33), 0.0, Math.cos(var33)).normalize();
                    float var36 = (float)(var32.x * var35.x + var32.z * var35.z);
                    RenderBatch var37 = this.c(new Object[]{new RenderBatch(this.q, -(Float)this.f.getValue() + var36 * 1.6F), var20});
                    if (!InventoryUtil.m(new Object[]{Items.WIND_CHARGE, var28})) {
                        if (this.d(new Object[]{var22})) {
                            this.toggle(new Object[]{var4});
                        }

                        return new RenderBatch(var37.a(new Object[0]), var37.b(new Object[0]));
                    }

                    Object[] var10005 = new Object[]{null, Hand.OFF_HAND, var24};
                    var10005[0] = -1;
                    if (this.a_boolean______________________________(var10005)) {
                        this.z = PearlStage.RESTORE;
                    }

                    return new RenderBatch(var37.a(new Object[0]), var37.b(new Object[0]));
                }
            case RESTORE:
                if (Prestige.o.d_boolean______(new Object[0])) {
                    return null;
                }

                if (this.B != -1) {
                    if (InventoryUtil.a_int_____(new Object[]{var18}) != this.B) {
                        int var10000 = this.B;
                        Object[] var10003 = new Object[]{null, var10};
                        var10003[0] = var10000;
                        InventoryUtil.a_void________(var10003);
                        this.j(new Object[]{var26});
                    }

                    NetworkUtil.a(new Object[]{new PlayerActionC2SPacket(Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN), var14});
                }

                if (InventoryUtil.a_int_____(new Object[]{var18}) != this.A) {
                    int var44 = this.A;
                    Object[] var45 = new Object[]{null, var10};
                    var45[0] = var44;
                    InventoryUtil.a_void________(var45);
                    this.j(new Object[]{var26});
                }

                Prestige.o.g(new Object[]{true});
                this.toggle(new Object[]{var4});
                return null;
            default:
                return null;
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11907200794228L ^ var2;
        long var4 = var2 ^ 93354449811845L;
        long var6 = var2 ^ 79720529788136L;

        try {
            MethodAccessor.c(new Object[]{b.interactionManager, "syncSelectedSlot", new Class[0], var4})
                .b(new Object[]{b.interactionManager, new Object[0], var6});
        } catch (Throwable var8) {
        }
    }

    private EnderPearlEntity a_class_1684__(Object[] var1) {
        Event.b();
        EnderPearlEntity var5 = null;
        int var6 = Integer.MAX_VALUE;

        for (Entity var8 : b.world.getEntities()) {
            if (var8 instanceof EnderPearlEntity var9 && var9.getOwner() == b.player && var9.age < var6) {
                var6 = var9.age;
                var5 = var9;
            }
        }

        if (var5 == null) {
            for (Entity var11 : b.world.getEntities()) {
                if (var11 instanceof EnderPearlEntity var12 && var12.getOwner() == b.player) {
                    var5 = var12;
                    break;
                }
            }
        }

        return var5;
    }

    private boolean e(Object[] var1) {
        Event.b();
        int var5 = b.player.age;
        if (b.player.isUsingItem() || b.options.useKey.isPressed()) {
            this.y = var5;
            return true;
        } else if (this.y == Integer.MIN_VALUE) {
            return false;
        } else {
            int var6 = var5 - this.y;
            if (var6 >= 0 && var6 <= 1) {
                return true;
            } else {
                this.y = Integer.MIN_VALUE;
                return false;
            }
        }
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11907200794228L ^ var2;
        long var4 = var2 ^ 111942195764623L;
        if (!this.u) {
            this.l(new Object[]{var4});
        }

        this.n = false;
        this.o = false;
    }

    private RenderBatch c(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 11907200794228L ^ var3;
        long var5 = var3 ^ 129552561683623L;
        if (this.r == null) {
            this.r = new RenderBatch(var2.a(new Object[0]), var2.b(new Object[0]));
            this.s = false;
            this.t = false;
            this.u = false;
            this.w = b.world;
        }

        Prestige.o.a_boolean_____________________________________________(new Object[]{this, this.r, var5});
        return this.r;
    }

    private boolean a_boolean______________________________(Object[] var1) {
        int var5 = (Integer)var1[0];
        Hand var4 = (Hand)var1[1];
        long var2 = (Long)var1[2];
        var2 = 11907200794228L ^ var2;
        long var6 = var2 ^ 58641752126262L;
        if (this.r != null && this.s && !this.u && !Prestige.o.d_boolean______(new Object[0])) {
            if (var5 >= 0) {
                Object[] var10003 = new Object[]{null, var6};
                var10003[0] = var5;
                InventoryUtil.a_void________(var10003);
            }

            float var9 = b.player.getYaw();
            float var10 = b.player.getPitch();

            boolean var11;
            try {
                b.player.setYaw(this.r.a(new Object[0]));
                b.player.setPitch(this.r.b(new Object[0]));
                var11 = b.interactionManager.interactItem(b.player, var4).isAccepted();
                Prestige.o.g(new Object[]{true});
                this.u = true;
                if (var11) {
                    b.player.swingHand(var4);
                }
            } finally {
                b.player.setYaw(var9);
                b.player.setPitch(var10);
            }

            return var11;
        } else {
            return false;
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (this.r != null) {
            if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
                if (!Prestige.o.a_boolean_____________________________________________(new Object[]{this, this.r, 48219934824613L})) {
                    this.t = false;
                } else {
                    var1.b(new Object[]{this.r.a(new Object[0])});
                    var1.c(new Object[]{this.r.b(new Object[0])});
                    this.t = true;
                }
            } else if (var1.a_EventPhase(new Object[0]) == EventPhase.POST && this.t) {
                this.t = false;
                if (Prestige.o.b_boolean_______(new Object[]{this, 14074758807540L})) {
                    if (this.u) {
                        boolean var13 = this.v;
                        this.l(new Object[]{74702765597305L});
                        if (var13 && this.isEnabled()) {
                            this.toggle(new Object[]{115103427876780L});
                        }
                    } else {
                        this.s = true;
                    }
                }
            }
        }
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11907200794228L ^ var2;
        long var4 = var2 ^ 105503756875577L;
        Prestige.o.c_void___(new Object[]{this, var4});
        this.r = null;
        this.w = null;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11907200794228L ^ var2;
        long var4 = var2 ^ 97201961797574L;
        long var6 = var2 ^ 86335862946338L;
        long var8 = var2 ^ 107446903980571L;
        boolean var11 = this.x != b.world;
        this.l(new Object[]{var4});
        this.k = false;
        this.l = false;
        this.n = false;
        this.o = false;
        this.z = PearlStage.PREP;
        this.B = -1;
        this.m.a_void_____________________(new Object[]{var6});
        this.x = b.world;
        if (var11) {
            this.y = Integer.MIN_VALUE;
        }

        if (b.player != null) {
            this.p = b.player.getPitch();
            this.q = b.player.getYaw();
            this.A = InventoryUtil.a_int_____(new Object[]{var8});
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.y = Integer.MIN_VALUE;
        this.m(new Object[]{18978719144907L});
    }

    private int d_int_______(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            if (!b.player.getInventory().getStack(var5).isEmpty() && b.player.getInventory().getStack(var5).getItem() == Items.ENDER_PEARL) {
                return var5;
            }
        }

        return -1;
    }

    private int e_int_____(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 11907200794228L ^ var2;
        long var4 = var2 ^ 19540157771984L;
        Event.b();

        for (int var7 = 0; var7 < 9; var7++) {
            if (!b.player.getInventory().getStack(var7).isEmpty() && f(new Object[]{b.player.getInventory().getStack(var7), var4})) {
                return var7;
            }
        }

        return -1;
    }

    public static boolean f(Object[] var0) {
        ItemStack var3 = (ItemStack)var0[0];
        return var3.getItem().equals(Items.WIND_CHARGE);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 120267356976845L;
        long var6 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var6});
        this.m(new Object[]{var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 110300201456924L;
        long var6 = var2 ^ 140372736859409L;
        this.l(new Object[]{var4});
        this.x = null;
        this.y = Integer.MIN_VALUE;
        Prestige.o.f(new Object[]{this, var6});
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
