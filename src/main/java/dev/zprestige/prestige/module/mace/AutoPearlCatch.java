package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
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
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class AutoPearlCatch extends Module implements RenderContract3 {
    private static final float a = 1500.0F;
    private FloatSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private FloatSetting f;
    private boolean i;
    private boolean g;
    private RenderHelper27 h = new RenderHelper27(26293358099357L);
    private boolean j;
    private boolean k;
    private float l;
    private RenderBatch m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private Object s;
    private Object t;
    private int u = Integer.MIN_VALUE;

    public AutoPearlCatch() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.f.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL281_0 -> (Boolean)this.e.getValue(), 35046756052036L});
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
        if (this.t != b.world) {
            this.l(new Object[]{37059407413720L});
        }

        if ((Boolean)this.e.getValue()) {
            if (this.e(new Object[]{99450130826774L})) {
                this.j(new Object[]{102974567466914L});
            } else {
                if (!this.i) {
                    RenderBatch var10000 = new RenderBatch(b.player.getYaw(), -90.0F);
                    float var10001 = (Float)this.f.getValue() / 2.0F;
                    Object[] var10006 = new Object[]{null, null, null, null, 8666705888841L};
                    var10006[3] = 0.2F;
                    var10006[2] = 0.0F;
                    var10006[1] = var10001;
                    var10006[0] = var10000;
                    if (WorldUtil.d(var10006)) {
                        this.j = true;
                    }
                } else {
                    boolean var17 = b.player.getVelocity().x != 0.0 || b.player.getVelocity().z != 0.0;
                    RenderHelper27 var18 = this.h;
                    float var20 = var17 ? (Float)this.d.getValue() : (Float)this.c.getValue();
                    Object[] var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = var20;
                    if (!var18.a(var10004)) {
                        return;
                    }

                    if (this.g) {
                        if (this.p) {
                            return;
                        }

                        RenderBatch var19 = new RenderBatch(b.player.getYaw(), this.l);
                        var20 = (Float)this.f.getValue() / 2.0F;
                        Object[] var22 = new Object[]{null, null, null, null, 8666705888841L};
                        var22[3] = 0.2F;
                        var22[2] = 0.0F;
                        var22[1] = var20;
                        var22[0] = var19;
                        if (WorldUtil.d(var22)) {
                            this.toggle(new Object[]{115103427876780L});
                        }
                    } else {
                        this.k = true;
                    }
                }
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 43040229079061L;
        long var6 = var2 ^ 29247161167908L;
        long var8 = var2 ^ 24161452190127L;
        long var10 = var2 ^ 25270889892669L;
        long var12 = var2 ^ 121213098552929L;
        long var14 = var2 ^ 5698422053585L;
        long var16 = var2 ^ 77504906702022L;
        long var18 = var2 ^ 104047174863691L;
        long var20 = var2 ^ 19985254393883L;
        long var22 = var2 ^ 98738783120770L;
        long var24 = var2 ^ 85547089512629L;
        long var26 = var2 ^ 111078947484959L;
        long var28 = var2 ^ 55397201460281L;
        if (this.t != b.world) {
            this.l(new Object[]{var12});
        }

        boolean var31 = this.e(new Object[]{var8});
        if (!Prestige.o.c_boolean____(new Object[]{var16})) {
            this.l(new Object[]{var12});
            return null;
        } else if (var31) {
            this.j(new Object[]{var20});
            return !this.p && !this.q ? null : this.m;
        } else if (this.m == null || !this.p && !this.q) {
            if (b.isWindowFocused() && b.currentScreen == null) {
                if ((Boolean)this.e.getValue()) {
                    int var44 = this.i ? -1 : this.d_int______(new Object[]{var22});
                    int var45 = this.e_int____(new Object[]{var18});
                    if ((this.i || var44 != -1) && var45 != -1) {
                        if (this.j) {
                            RenderBatch var47 = this.b_RenderBatch_______(new Object[]{new RenderBatch(b.player.getYaw(), -90.0F), var14});
                            Object[] var70 = new Object[]{null, var10};
                            var70[0] = var44;
                            if (this.a_boolean____________________________(var70)) {
                                this.i = true;
                                this.j = false;
                                this.h.a_void_____________________(new Object[]{var6});
                            }

                            return var47;
                        } else {
                            if (this.k) {
                                EnderPearlEntity var46 = null;
                                int var49 = Integer.MAX_VALUE;

                                for (Entity var55 : b.world.getEntities()) {
                                    if (var55 instanceof EnderPearlEntity var60 && var60.getOwner() == b.player && var60.age < var49) {
                                        var49 = var60.age;
                                        var46 = var60;
                                    }
                                }

                                if (var46 == null) {
                                    for (Entity var56 : b.world.getEntities()) {
                                        if (var56 instanceof EnderPearlEntity var61) {
                                            if (var61.getOwner() == b.player) {
                                                var46 = var61;
                                            }
                                            break;
                                        }
                                    }
                                }

                                if (var46 != null) {
                                    Vec3d var52 = b.player.getVelocity();
                                    float var57 = b.player.getYaw();
                                    double var62 = Math.toRadians(var57);
                                    Vec3d var40 = new Vec3d(-Math.sin(var62), 0.0, Math.cos(var62)).normalize();
                                    float var65 = (float)(var52.x * var40.x + var52.z * var40.z);
                                    RenderBatch var66 = this.b_RenderBatch_______(
                                        new Object[]{new RenderBatch(b.player.getYaw(), -90.0F + var65 * 1.6F), var14}
                                    );
                                    Object[] var69 = new Object[]{null, var10};
                                    var69[0] = var45;
                                    if (this.a_boolean____________________________(var69)) {
                                        this.g = true;
                                        this.k = false;
                                    }

                                    return new RenderBatch(var66.a(new Object[0]), var66.b(new Object[0]));
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
                } else {
                    boolean var32 = b.player.getVelocity().x != 0.0 || b.player.getVelocity().z != 0.0;
                    int var33 = this.i ? -1 : this.d_int______(new Object[]{var22});
                    int var34 = this.e_int____(new Object[]{var18});
                    if ((this.i || var33 != -1) && var34 != -1) {
                        if (!this.i) {
                            RenderBatch var48 = this.b_RenderBatch_______(new Object[]{new RenderBatch(b.player.getYaw(), -90.0F), var14});
                            Object[] var68 = new Object[]{null, var10};
                            var68[0] = var33;
                            if (this.a_boolean____________________________(var68)) {
                                this.i = true;
                                this.h.a_void_____________________(new Object[]{var6});
                            }

                            return var48;
                        } else {
                            RenderHelper27 var10000 = this.h;
                            float var10001 = var32 ? (Float)this.d.getValue() : (Float)this.c.getValue();
                            Object[] var10004 = new Object[]{null, var24};
                            var10004[0] = var10001;
                            if (!var10000.a(var10004)) {
                                return new RenderBatch(b.player.getYaw(), -90.0F);
                            } else {
                                EnderPearlEntity var35 = null;
                                int var36 = Integer.MAX_VALUE;

                                for (Entity var38 : b.world.getEntities()) {
                                    if (var38 instanceof EnderPearlEntity var39 && var39.getOwner() == b.player && var39.age < var36) {
                                        var36 = var39.age;
                                        var35 = var39;
                                    }
                                }

                                if (var35 == null) {
                                    for (Entity var58 : b.world.getEntities()) {
                                        if (var58 instanceof EnderPearlEntity var63) {
                                            if (var63.getOwner() == b.player) {
                                                var35 = var63;
                                            }
                                            break;
                                        }
                                    }
                                }

                                if (var35 != null) {
                                    Vec3d var54 = b.player.getVelocity();
                                    float var59 = b.player.getYaw();
                                    double var64 = Math.toRadians(var59);
                                    Vec3d var41 = new Vec3d(-Math.sin(var64), 0.0, Math.cos(var64)).normalize();
                                    float var42 = (float)(var54.x * var41.x + var54.z * var41.z);
                                    RenderBatch var43 = this.b_RenderBatch_______(
                                        new Object[]{new RenderBatch(b.player.getYaw(), -90.0F + var42 * 1.6F), var14}
                                    );
                                    var10004 = new Object[]{null, var10};
                                    var10004[0] = var34;
                                    if (this.a_boolean____________________________(var10004)) {
                                        this.r = true;
                                    }

                                    return new RenderBatch(var43.a(new Object[0]), var43.b(new Object[0]));
                                } else {
                                    if (this.d(new Object[]{var26})) {
                                        this.toggle(new Object[]{var4});
                                    }

                                    return null;
                                }
                            }
                        }
                    } else {
                        this.toggle(new Object[]{var4});
                        return null;
                    }
                }
            } else {
                if (!this.p && !this.q) {
                    this.k(new Object[]{var28});
                }

                return null;
            }
        } else {
            return this.m;
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 10306347614701L ^ var2;
        long var4 = var2 ^ 36922976468039L;
        float var6 = Math.max((Float)this.c.getValue(), (Float)this.d.getValue());
        RenderHelper27 var10000 = this.h;
        float var10001 = var6 + 1500.0F;
        Object[] var10004 = new Object[]{null, var4};
        var10004[0] = var10001;
        return var10000.a(var10004);
    }

    private boolean e(Object[] var1) {
        Event.b();
        int var5 = b.player.age;
        if (b.player.isUsingItem() || b.options.useKey.isPressed()) {
            this.u = var5;
            return true;
        } else if (this.u == Integer.MIN_VALUE) {
            return false;
        } else {
            int var6 = var5 - this.u;
            if (var6 >= 0 && var6 <= 1) {
                return true;
            } else {
                this.u = Integer.MIN_VALUE;
                return false;
            }
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 10306347614701L ^ var2;
        long var4 = var2 ^ 45151469622735L;
        if (!this.p && !this.q) {
            this.k(new Object[]{var4});
        }

        this.j = false;
        this.k = false;
    }

    private RenderBatch b_RenderBatch_______(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 10306347614701L ^ var2;
        long var5 = var2 ^ 114490896329760L;
        if (this.m == null) {
            this.m = new RenderBatch(var4.a(new Object[0]), var4.b(new Object[0]));
            this.n = false;
            this.o = false;
            this.p = false;
            this.s = b.world;
        }

        Prestige.o.a_boolean_____________________________________________(new Object[]{this, this.m, var5});
        return this.m;
    }

    private boolean a_boolean____________________________(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        var2 = 10306347614701L ^ var2;
        long var5 = var2 ^ 125536761560862L;
        if (this.m != null && this.n && !this.p && !Prestige.o.d_boolean______(new Object[0])) {
            Object[] var10003 = new Object[]{null, var5};
            var10003[0] = var4;
            InventoryUtil.a_void________(var10003);
            float var8 = b.player.getYaw();
            float var9 = b.player.getPitch();

            boolean var10;
            try {
                b.player.setYaw(this.m.a(new Object[0]));
                b.player.setPitch(this.m.b(new Object[0]));
                var10 = b.interactionManager.interactItem(b.player, Hand.MAIN_HAND).isAccepted();
                Prestige.o.g(new Object[]{true});
                this.p = true;
                if (var10) {
                    b.player.swingHand(Hand.MAIN_HAND);
                }
            } finally {
                b.player.setYaw(var8);
                b.player.setPitch(var9);
            }

            return var10;
        } else {
            return false;
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (this.m != null) {
            if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
                if (!Prestige.o.a_boolean_____________________________________________(new Object[]{this, this.m, 48219934824613L})) {
                    this.o = false;
                } else {
                    var1.b(new Object[]{this.m.a(new Object[0])});
                    var1.c(new Object[]{this.m.b(new Object[0])});
                    this.o = true;
                }
            } else if (var1.a_EventPhase(new Object[0]) == EventPhase.POST && this.o) {
                this.o = false;
                if (Prestige.o.b_boolean_______(new Object[]{this, 14074758807540L})) {
                    if (this.p) {
                        this.p = false;
                        this.q = true;
                    } else if (this.q) {
                        this.q = false;
                        boolean var13 = this.r;
                        this.k(new Object[]{138438170727296L});
                        if (var13 && this.isEnabled()) {
                            this.toggle(new Object[]{115103427876780L});
                        }
                    } else {
                        this.n = true;
                    }
                }
            }
        }
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 10306347614701L ^ var2;
        long var4 = var2 ^ 108193674557785L;
        Prestige.o.c_void___(new Object[]{this, var4});
        this.m = null;
        this.s = null;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 10306347614701L ^ var2;
        long var4 = var2 ^ 138504629182376L;
        long var6 = var2 ^ 93473908560821L;
        boolean var9 = this.t != b.world;
        this.k(new Object[]{var6});
        this.i = false;
        this.g = false;
        this.j = false;
        this.k = false;
        this.h.a_void_____________________(new Object[]{var4});
        this.t = b.world;
        if (var9) {
            this.u = Integer.MIN_VALUE;
        }

        if (b.player != null) {
            this.l = b.player.getPitch();
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.u = Integer.MIN_VALUE;
        this.l(new Object[]{37059407413720L});
    }

    private int d_int______(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 < 9; var5++) {
            if (!b.player.getInventory().getStack(var5).isEmpty() && b.player.getInventory().getStack(var5).getItem() == Items.ENDER_PEARL) {
                return var5;
            }
        }

        return -1;
    }

    private int e_int____(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 10306347614701L ^ var2;
        long var4 = var2 ^ 32528850572091L;
        Event.b();

        for (int var7 = 0; var7 < 9; var7++) {
            if (!b.player.getInventory().getStack(var7).isEmpty() && f(new Object[]{b.player.getInventory().getStack(var7), var4})) {
                return var7;
            }
        }

        return -1;
    }

    public static boolean f(Object[] var0) {
        ItemStack var1 = (ItemStack)var0[0];
        return var1.getItem().equals(Items.WIND_CHARGE);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        long var6 = var2 ^ 102906042232030L;
        Prestige.o.e(new Object[]{this, var4});
        this.l(new Object[]{var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        long var6 = var2 ^ 99269417657573L;
        this.k(new Object[]{var6});
        this.t = null;
        this.u = Integer.MIN_VALUE;
        Prestige.o.f(new Object[]{this, var4});
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
