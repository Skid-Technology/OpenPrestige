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
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.math.MathHelper2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper3;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.type.PearlState;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class DiagonalPearlCatch extends Module implements RenderContract3 {
    public DiagonalPearlCatch() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private FloatSetting c;
    private BoolSetting d;
    private FloatSetting e;
    private static final double f = 0.4;
    private static final int g = 15;
    private static final int h = 30;
    private static final float i = 1.5F;
    private static final double j = 0.03;
    private static final double k = 0.99;
    private static final float l = 1.5F;
    private static final double m = 0.99;
    private static final double n = 0.0;
    private static final double o = 0.08;
    private static final double p = 0.98;
    private static final double q = 0.91;
    private PearlState r = PearlState.IDLE;
    private int s;
    private List t;
    private RenderHelper3 u;
    private RenderHelper27 v = new RenderHelper27(26293358099357L);
    private boolean w;
    private RenderBatch x;
    private boolean y;
    private boolean z;
    private boolean A;
    private boolean B;
    private Object C;
    private Object D;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 6523796846702L;
        long var6 = var2 ^ 91082959635357L;
        this.j(new Object[]{var4});
        Prestige.o.e(new Object[]{this, var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        long var6 = var2 ^ 103824891961869L;
        this.j(new Object[]{var6});
        this.D = null;
        Prestige.o.f(new Object[]{this, var4});
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 8283165051676L ^ var2;
        long var4 = var2 ^ 55084400404693L;
        long var6 = var2 ^ 47650255962601L;
        this.r = PearlState.IDLE;
        this.s = 0;
        this.t = null;
        this.u = null;
        this.w = false;
        this.v.a_void_____________________(new Object[]{var6});
        this.n(new Object[]{var4});
        this.D = b.world;
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.j(new Object[]{133924836660584L});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.D != b.world) {
            this.j(new Object[]{133924836660584L});
        }

        if (!Prestige.o.c_boolean____(new Object[]{10956647187327L})) {
            this.j(new Object[]{133924836660584L});
        } else if (b.player != null && b.world != null && b.isWindowFocused() && b.currentScreen == null) {
            int var19 = this.a_int____________(
                new Object[]{(Predicate<ItemStack>)lpL178_0 -> !((net.minecraft.item.ItemStack)lpL178_0).isEmpty() && InventoryUtil.p(new Object[]{((net.minecraft.item.ItemStack)lpL178_0), 96613578214009L}), 5535161176176L}
            );
            if (var19 == -1) {
                this.toggle(new Object[]{115103427876780L});
            } else {
                boolean var20 = ((String)this.a.getValue()).equals("Macro") ? !this.w : this.r == PearlState.IDLE || this.r == PearlState.THROW_PEARL;
                int var21 = -1;
                if (var20) {
                    var21 = this.a_int____________(
                        new Object[]{(Predicate<ItemStack>)lpL177_0 -> !((net.minecraft.item.ItemStack)lpL177_0).isEmpty() && ((net.minecraft.item.ItemStack)lpL177_0).getItem() == Items.ENDER_PEARL, 5535161176176L}
                    );
                    if (var21 == -1) {
                        this.toggle(new Object[]{115103427876780L});
                        return;
                    }
                }

                if (((String)this.a.getValue()).equals("Macro")) {
                    Object[] var10005 = new Object[]{null, null, 127774237691672L};
                    var10005[1] = var19;
                    var10005[0] = var21;
                    this.k(var10005);
                } else {
                    Object[] var22 = new Object[]{null, null, 47972324523133L};
                    var22[1] = var19;
                    var22[0] = var21;
                    this.l(var22);
                }
            }
        } else {
            if (!this.A) {
                this.n(new Object[]{83958210688673L});
            }
        }
    }

    private void k(Object[] var1) {
        int var5 = (Integer)var1[0];
        int var2 = (Integer)var1[1];
        long var3 = (Long)var1[2];
        var3 = 8283165051676L ^ var3;
        long var6 = var3 ^ 117878007009866L;
        long var8 = var3 ^ 42530859703193L;
        long var10 = var3 ^ 99418377255359L;
        long var12 = var3 ^ 125319185949448L;
        if (!this.w) {
            Object[] var15 = new Object[]{null, this.d(new Object[]{var10}), var6};
            var15[0] = var5;
            if (this.d_boolean____(var15)) {
                this.w = true;
                this.v.a_void_____________________(new Object[]{var8});
            }
        } else {
            RenderHelper27 var10000 = this.v;
            float var10001 = (Float)this.c.getValue();
            Object[] var10004 = new Object[]{null, var12};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                Object[] var10005 = new Object[]{null, this.d(new Object[]{var10}), var6};
                var10005[0] = var2;
                if (this.d_boolean____(var10005)) {
                    this.B = true;
                }
            }
        }
    }

    private void l(Object[] var1) {
        int var4 = (Integer)var1[0];
        int var5 = (Integer)var1[1];
        long var2 = (Long)var1[2];
        var2 = 8283165051676L ^ var2;
        long var6 = var2 ^ 75344005472461L;
        long var8 = var2 ^ 136401377843702L;
        long var10 = var2 ^ 57866965492015L;
        long var12 = var2 ^ 83079234818822L;
        long var14 = var2 ^ 6592948816602L;
        switch (this.r) {
            case IDLE:
                if (!this.a_boolean_____________________________(new Object[]{this.d(new Object[]{var14}), var12})) {
                    this.toggle(new Object[]{var6});
                    return;
                }

                this.r = PearlState.THROW_PEARL;
                break;
            case THROW_PEARL:
                if (this.y
                    && !this.A
                    && !Prestige.o.d_boolean______(new Object[0])
                    && !this.a_boolean_____________________________(new Object[]{this.x, var12})) {
                    this.toggle(new Object[]{var6});
                    return;
                }

                Object[] var10005 = new Object[]{null, this.d(new Object[]{var14}), var10};
                var10005[0] = var4;
                if (this.d_boolean____(var10005)) {
                    this.s = this.u.a;
                    this.r = this.s > 0 ? PearlState.WAIT_FOR_LAUNCH : PearlState.THROW_WIND_CHARGE;
                }
                break;
            case WAIT_FOR_LAUNCH:
                if (--this.s <= 0) {
                    this.r = PearlState.THROW_WIND_CHARGE;
                    Object[] var17 = new Object[]{null, var8};
                    var17[0] = var5;
                    this.m(var17);
                }
                break;
            case THROW_WIND_CHARGE:
                Object[] var10004 = new Object[]{null, var8};
                var10004[0] = var5;
                this.m(var10004);
        }
    }

    private void m(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        var2 = 8283165051676L ^ var2;
        long var5 = var2 ^ 69271851807271L;
        long var7 = var2 ^ 87032334136261L;
        long var9 = var2 ^ 6839964455270L;
        Event.b();
        Object[] var10006 = new Object[]{null, this.c(new Object[]{var9}), var7};
        var10006[0] = var4;
        boolean var12 = this.d_boolean____(var10006);
        if (!var12 && !this.A) {
            this.toggle(new Object[]{var5});
        } else {
            this.B = true;
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 77504906702022L;
        long var6 = var2 ^ 59679664126673L;
        long var8 = var2 ^ 138119825400010L;
        long var10 = var2 ^ 112547392999938L;
        long var12 = var2 ^ 5545949845198L;
        long var14 = var2 ^ 112194596139339L;
        long var16 = var2 ^ 33109972671316L;
        if (this.D != b.world) {
            this.j(new Object[]{var6});
        }

        if (!Prestige.o.c_boolean____(new Object[]{var4})) {
            this.j(new Object[]{var6});
            return null;
        } else if (this.x != null) {
            return this.x;
        } else if (!((String)this.a.getValue()).equals("Macro")) {
            if (this.r == PearlState.THROW_PEARL) {
                return this.e(new Object[]{this.d(new Object[]{var10}), var12});
            } else if (this.r == PearlState.WAIT_FOR_LAUNCH) {
                RenderBatch var20 = this.b_RenderBatch________(new Object[]{var8});
                return var20 == null && this.s > 2 ? null : this.e(new Object[]{var20 != null ? var20 : this.d(new Object[]{var10}), var12});
            } else {
                return this.r == PearlState.THROW_WIND_CHARGE ? this.e(new Object[]{this.c(new Object[]{var16}), var12}) : null;
            }
        } else {
            boolean var19 = !this.w || this.v.a_float__________________(new Object[]{var14}) + 55.0F >= (Float)this.c.getValue();
            return var19 ? this.e(new Object[]{this.d(new Object[]{var10}), var12}) : null;
        }
    }

    private RenderBatch b_RenderBatch________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 8283165051676L ^ var2;
        long var4 = var2 ^ 93440723412446L;
        if (!((String)this.a.getValue()).equals("Trajectory")) {
            return null;
        } else if (!(Boolean)this.d.getValue()) {
            return null;
        } else if (b.player == null) {
            return null;
        } else if (this.u == null) {
            return null;
        } else if (this.r != PearlState.WAIT_FOR_LAUNCH && this.r != PearlState.THROW_WIND_CHARGE) {
            return null;
        } else {
            float var10000 = this.u.b - b.player.getYaw();
            Object[] var10003 = new Object[]{null, var4};
            var10003[0] = var10000;
            float var7 = Math.abs(WorldUtil.b_float_____________________(var10003));
            return var7 > this.e.getValue() ? null : new RenderBatch(this.u.b, this.u.c);
        }
    }

    private RenderBatch c(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 8283165051676L ^ var2;
        long var4 = var2 ^ 109998677274754L;
        long var6 = var2 ^ 140505230197322L;
        Event.b();
        RenderBatch var9 = this.b_RenderBatch________(new Object[]{var4});
        return var9 != null ? var9 : this.d(new Object[]{var6});
    }

    private RenderBatch d(Object[] var1) {
        return new RenderBatch(b.player.getYaw(), b.player.getPitch());
    }

    private boolean a_boolean_____________________________(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 8283165051676L ^ var3;
        long var5 = var3 ^ 68799133388487L;
        long var7 = var3 ^ 125197213067628L;
        long var9 = var3 ^ 130960080118814L;
        Vec3d var11 = b.player.getEyePos();
        Vec3d var12 = b.player.getVelocity();
        boolean var13 = b.player.isOnGround();
        Object[] var10007 = new Object[]{null, null, null, null, var5};
        var10007[3] = 17;
        var10007[2] = var13;
        var10007[1] = var12;
        var10007[0] = var11;
        List var14 = this.b_List_____(var10007);
        MathHelper2 var15 = (MathHelper2)var14.get(0);
        float var10003 = var2.a(new Object[0]);
        float var10004 = var2.b(new Object[0]);
        var10007 = new Object[]{null, null, null, var7};
        var10007[2] = var10004;
        var10007[1] = var10003;
        var10007[0] = var15;
        this.t = this.c_List(var10007);
        this.u = this.a_RenderHelper3(new Object[]{var14, this.t, var9});
        return this.u != null;
    }

    private boolean d_boolean____(Object[] var1) {
        int var2 = (Integer)var1[0];
        RenderBatch var3 = (RenderBatch)var1[1];
        long var4 = (Long)var1[2];
        var4 = 8283165051676L ^ var4;
        long var6 = var4 ^ 67080602797861L;
        long var8 = var4 ^ 14304432405104L;
        long var10 = var4 ^ 94015048426533L;
        if (Prestige.o.d_boolean______(new Object[0])) {
            return false;
        } else {
            RenderBatch var13 = this.e(new Object[]{var3, var10});
            if (this.y && !this.A) {
                Object[] var10003 = new Object[]{null, var6};
                var10003[0] = var2;
                InventoryUtil.a_void________(var10003);
                float var14 = b.player.getYaw();
                float var15 = b.player.getPitch();

                boolean var17;
                try {
                    b.player.setYaw(var13.a(new Object[0]));
                    b.player.setPitch(var13.b(new Object[0]));
                    boolean var16 = InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var8});
                    this.A = true;
                    var17 = var16;
                } finally {
                    b.player.setYaw(var14);
                    b.player.setPitch(var15);
                }

                return var17;
            } else {
                return false;
            }
        }
    }

    private RenderBatch e(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 8283165051676L ^ var2;
        long var5 = var2 ^ 113053407013582L;
        long var7 = var2 ^ 48851467604954L;
        if (this.x == null) {
            float var10 = var4.a(new Object[0]);
            if (b.player != null) {
                float var10000 = b.player.getYaw();
                float var10001 = var10 - b.player.getYaw();
                Object[] var10004 = new Object[]{null, var7};
                var10004[0] = var10001;
                var10 = var10000 + WorldUtil.b_float_____________________(var10004);
            }

            this.x = new RenderBatch(var10, var4.b(new Object[0]));
            this.y = false;
            this.z = false;
            this.A = false;
            this.C = b.world;
        }

        Prestige.o.a_boolean_____________________________________________(new Object[]{this, this.x, var5});
        return this.x;
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (this.x != null) {
            if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
                if (!Prestige.o.a_boolean_____________________________________________(new Object[]{this, this.x, 48219934824613L})) {
                    this.z = false;
                } else {
                    var1.b(new Object[]{this.x.a(new Object[0])});
                    var1.c(new Object[]{this.x.b(new Object[0])});
                    this.z = true;
                }
            } else if (var1.a_EventPhase(new Object[0]) == EventPhase.POST && this.z) {
                this.z = false;
                if (Prestige.o.b_boolean_______(new Object[]{this, 14074758807540L})) {
                    if (this.A) {
                        boolean var13 = this.B;
                        this.n(new Object[]{83958210688673L});
                        if (var13 && this.isEnabled()) {
                            this.toggle(new Object[]{115103427876780L});
                        }
                    } else {
                        this.y = true;
                    }
                }
            }
        }
    }

    private void n(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 8283165051676L ^ var2;
        long var4 = var2 ^ 102277432628873L;
        Prestige.o.c_void___(new Object[]{this, var4});
        this.x = null;
        this.C = null;
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = false;
    }

    private int a_int____________(Object[] var1) {
        Predicate var2 = (Predicate)var1[0];
        Event.b();

        for (int var6 = 0; var6 < 9; var6++) {
            ItemStack var7 = b.player.getInventory().getStack(var6);
            if (var2.test(var7)) {
                return var6;
            }
        }

        return -1;
    }

    private static Vec3d a_class_243______________(Object[] var0) {
        Vec3d var3 = (Vec3d)var0[0];
        double var1 = (Double)var0[1];
        Vec3d var4 = (Vec3d)var0[2];
        boolean var7 = (Boolean)var0[3];
        return var3.multiply(var1).add(var4.x, var7 ? 0.0 : var4.y, var4.z);
    }

    private List b_List_____(Object[] var1) {
        Vec3d var3 = (Vec3d)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        boolean var4 = (Boolean)var1[2];
        int var2 = (Integer)var1[3];
        Event.b();
        ArrayList var9 = new ArrayList(var2 + 1);
        Vec3d var10 = var3;
        Vec3d var11 = var5;
        var9.add(new MathHelper2(var3, var5, var4));

        for (int var13 = 0; var13 < var2; var13++) {
            var10 = var10.add(var11);
            double var14 = (var11.y - 0.08) * 0.98;
            double var16 = var11.x * 0.91;
            double var18 = var11.z * 0.91;
            var11 = new Vec3d(var16, var14, var18);
            var9.add(new MathHelper2(var10, var11, false));
        }

        return var9;
    }

    private List c_List(Object[] var1) {
        MathHelper2 var2 = (MathHelper2)var1[0];
        float var3 = (Float)var1[1];
        float var6 = (Float)var1[2];
        long var4 = (Long)var1[3];
        var4 = 8283165051676L ^ var4;
        long var7 = var4 ^ 11475859869302L;
        long var9 = var4 ^ 105762616860040L;
        ArrayList var13 = new ArrayList(31);
        Object[] var10004 = new Object[]{null, null, var9};
        var10004[1] = var6;
        var10004[0] = var3;
        Vec3d var14 = b_class_243____(var10004);
        Vec3d var10002 = var2.b;
        boolean var10003 = var2.c;
        Object[] var10006 = new Object[]{null, null, null, null, var7};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = 1.5;
        var10006[0] = var14;
        Vec3d var15 = a_class_243______________(var10006);
        Vec3d var16 = var2.a;
        var13.add(var16);

        for (int var17 = 0; var17 < 30; var17++) {
            var16 = var16.add(var15);
            var15 = var15.multiply(0.99).subtract(0.0, 0.03, 0.0);
            var13.add(var16);
            if (var16.y < var2.a.y - 256.0) {
                break;
            }
        }

        return var13;
    }

    private List d_List(Object[] var1) {
        MathHelper2 var6 = (MathHelper2)var1[0];
        float var7 = (Float)var1[1];
        float var2 = (Float)var1[2];
        int var5 = (Integer)var1[3];
        long var3 = (Long)var1[4];
        var3 = 8283165051676L ^ var3;
        long var8 = var3 ^ 103555905484689L;
        long var10 = var3 ^ 57663514584175L;
        Event.b();
        ArrayList var13 = new ArrayList(var5 + 1);
        Object[] var10004 = new Object[]{null, null, var10};
        var10004[1] = var2;
        var10004[0] = var7;
        Vec3d var14 = b_class_243____(var10004);
        Vec3d var10002 = var6.b;
        boolean var10003 = var6.c;
        Object[] var10006 = new Object[]{null, null, null, null, var8};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = 1.5;
        var10006[0] = var14;
        Vec3d var15 = a_class_243______________(var10006);
        Vec3d var16 = var6.a;
        var13.add(var16);

        for (int var17 = 0; var17 < var5; var17++) {
            var16 = var16.add(var15);
            var15 = var15.multiply(0.99);
            var13.add(var16);
        }

        return var13;
    }

    private RenderHelper3 a_RenderHelper3(Object[] var1) {
        List var4 = (List)var1[0];
        List var5 = (List)var1[1];
        long var2 = (Long)var1[2];
        var2 = 8283165051676L ^ var2;
        long var6 = var2 ^ 76212563303480L;
        long var8 = var2 ^ 75153131281404L;
        RenderHelper3 var11 = null;
        Event.b();
        double var12 = Double.MAX_VALUE;
        int var15 = var5.size();
        int var16 = var4.size();

        for (int var17 = 2; var17 <= 15 && var17 < var15 - 1 && var17 < var16; var17++) {
            MathHelper2 var18 = (MathHelper2)var4.get(var17);

            for (int var19 = var17 + 1; var19 < var15; var19++) {
                Vec3d var20 = (Vec3d)var5.get(var19);
                int var21 = var19 - var17;
                Vec3d var22 = var20.subtract(var18.a);
                if (!(var22.lengthSquared() < 1.0E-6)) {
                    Vec3d var23 = var18.c ? new Vec3d(var18.b.x, 0.0, var18.b.z) : var18.b;
                    Vec3d var24 = var22.subtract(var23.multiply(var21));
                    float var25 = (float)Math.toDegrees(Math.atan2(-var24.x, var24.z));
                    double var26 = Math.sqrt(var24.x * var24.x + var24.z * var24.z);
                    float var28 = (float)Math.toDegrees(-Math.atan2(var24.y, var26));

                    for (float var29 = var28 - 5.0F; var29 <= var28 + 5.0F; var29 += 0.5F) {
                        for (float var30 = var25 - 5.0F; var30 <= var25 + 5.0F; var30 += 0.5F) {
                            Object[] var10009 = new Object[]{null, null, null, null, null, null, var8};
                            var10009[5] = var29;
                            var10009[4] = var30;
                            var10009[3] = var21;
                            var10009[2] = var17;
                            var10009[1] = var5;
                            var10009[0] = var18;
                            RenderHelper3 var31 = this.b_RenderHelper3(var10009);
                            if (var31 != null && var31.d < var12) {
                                var12 = var31.d;
                                var11 = var31;
                            }
                        }
                    }
                }
            }
        }

        if (var11 == null) {
            return null;
        } else {
            MathHelper2 var33 = (MathHelper2)var4.get(var11.a);

            for (float var34 = var11.c - 0.5F; var34 <= var11.c + 0.5F; var34 += 0.05F) {
                for (float var35 = var11.b - 1.0F; var35 <= var11.b + 1.0F; var35 += 0.1F) {
                    int var10003 = var11.a;
                    Object[] var10008 = new Object[]{null, null, null, null, null, var6};
                    var10008[4] = var34;
                    var10008[3] = var35;
                    var10008[2] = var10003;
                    var10008[1] = var5;
                    var10008[0] = var33;
                    RenderHelper3 var36 = this.c_RenderHelper3(var10008);
                    if (var36 != null && var36.d < var11.d) {
                        var11 = var36;
                    }
                }
            }

            return var11.d > 0.4 ? null : var11;
        }
    }

    private RenderHelper3 b_RenderHelper3(Object[] var1) {
        MathHelper2 var9 = (MathHelper2)var1[0];
        List var3 = (List)var1[1];
        int var2 = (Integer)var1[2];
        int var6 = (Integer)var1[3];
        float var4 = (Float)var1[4];
        float var5 = (Float)var1[5];
        long var7 = (Long)var1[6];
        var7 = 8283165051676L ^ var7;
        long var10 = var7 ^ 25146424176489L;
        Event.b();
        int var10005 = var6 + 4;
        Object[] var10008 = new Object[]{null, null, null, null, var10};
        var10008[3] = var10005;
        var10008[2] = var5;
        var10008[1] = var4;
        var10008[0] = var9;
        List var13 = this.d_List(var10008);
        double var14 = Double.MAX_VALUE;
        int var16 = var2;

        for (int var17 = 1; var17 < var13.size(); var17++) {
            int var18 = var2 + var17;
            if (var18 >= var3.size()) {
                break;
            }

            double var19 = ((Vec3d)var13.get(var17)).squaredDistanceTo((Vec3d)var3.get(var18));
            if (var19 < var14) {
                var14 = var19;
                var16 = var18;
            }
        }

        return var14 == Double.MAX_VALUE ? null : new RenderHelper3(var2, var4, var5, Math.sqrt(var14), var16);
    }

    private RenderHelper3 c_RenderHelper3(Object[] var1) {
        MathHelper2 var4 = (MathHelper2)var1[0];
        List var3 = (List)var1[1];
        int var8 = (Integer)var1[2];
        float var7 = (Float)var1[3];
        float var2 = (Float)var1[4];
        long var5 = (Long)var1[5];
        var5 = 8283165051676L ^ var5;
        long var9 = var5 ^ 26215253338285L;
        Event.b();
        int var12 = var3.size() - var8;
        if (var12 <= 1) {
            return null;
        } else {
            Object[] var10007 = new Object[]{null, null, null, null, var9};
            var10007[3] = var12;
            var10007[2] = var2;
            var10007[1] = var7;
            var10007[0] = var4;
            List var13 = this.d_List(var10007);
            double var14 = Double.MAX_VALUE;
            int var16 = var8;

            for (int var17 = 1; var17 < var13.size(); var17++) {
                int var18 = var8 + var17;
                if (var18 >= var3.size()) {
                    break;
                }

                double var19 = ((Vec3d)var13.get(var17)).squaredDistanceTo((Vec3d)var3.get(var18));
                if (var19 < var14) {
                    var14 = var19;
                    var16 = var18;
                }
            }

            return var14 == Double.MAX_VALUE ? null : new RenderHelper3(var8, var7, var2, Math.sqrt(var14), var16);
        }
    }

    private static Vec3d b_class_243____(Object[] var0) {
        float var1 = (Float)var0[0];
        float var4 = (Float)var0[1];
        float var5 = (float)Math.toRadians(var1);
        float var6 = (float)Math.toRadians(var4);
        double var7 = -MathHelper.sin(var5) * MathHelper.cos(var6);
        double var9 = -MathHelper.sin(var6);
        double var11 = MathHelper.cos(var5) * MathHelper.cos(var6);
        double var13 = Math.sqrt(var7 * var7 + var9 * var9 + var11 * var11);
        return var13 < 1.0E-9 ? new Vec3d(0.0, 0.0, 1.0) : new Vec3d(var7 / var13, var9 / var13, var11 / var13);
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
