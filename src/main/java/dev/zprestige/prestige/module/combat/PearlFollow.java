package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.PearlFollowHelper;
import dev.zprestige.prestige.data.FloatDoubleIntData;
import dev.zprestige.prestige.data.VecIntDoubleData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PearlFollow extends Module implements RenderContract3 {
    public PearlFollow() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    public static boolean a;
    private FloatSetting c;
    private FloatSetting d;
    private FloatSetting e;
    private FloatSetting f;
    private RangeSetting g;
    private RangeSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private Set k = new HashSet();
    private RenderHelper27 l = new RenderHelper27(26293358099357L);
    private RenderHelper27 m = new RenderHelper27(26293358099357L);
    private int n = -1;
    private Vec3d o = null;
    private static final int p = 400;
    private static final float q = 1.5F;
    private static final float r = -85.0F;
    private static final float s = 85.0F;
    private static final float t = 2.0F;
    private static final float u = 0.2F;
    private static final float v = 2.2F;
    private static final float w = 6.0F;
    private static final int x = 3;
    private static final double y = 0.25;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal, Gamemode.Sword, Gamemode.UHC, Gamemode.Mace);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
        this.k.clear();
        this.o = null;
        this.n = -1;
        a = false;
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        long var6 = var2 ^ 6195065515282L;
        Event.b();
        Prestige.o.f(new Object[]{this, var4});
        if (this.n != -1 && b.player != null) {
            int var10000 = this.n;
            Object[] var10003 = new Object[]{null, var6};
            var10003[0] = var10000;
            InventoryUtil.a_void________(var10003);
        }

        this.k.clear();
        this.o = null;
        this.n = -1;
        a = false;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            if (this.n != -1 && this.m.b(new Object[]{this.h, 89714527855213L})) {
                int var10000 = this.n;
                Object[] var10003 = new Object[]{null, 37392877673079L};
                var10003[0] = var10000;
                InventoryUtil.a_void________(var10003);
                this.n = -1;
            }

            a = this.n != -1 || this.o != null;
            HashSet var17 = new HashSet();

            for (Entity var19 : b.world.getEntities()) {
                if (var19 instanceof EnderPearlEntity var20) {
                    var17.add(var20.getId());
                    if (!this.k.contains(var20.getId())) {
                        this.k.add(var20.getId());
                        if (var20.getOwner() instanceof PlayerEntity var22
                            && !var22.equals(b.player)
                            && !Prestige.g.a(new Object[]{var22.getName().getString(), 24848952222090L})) {
                            if ((Boolean)this.j.getValue()) {
                                PlayerEntity var23 = Prestige.n.a(new Object[0]);
                                if (var23 == null || !var22.getUuid().equals(var23.getUuid())) {
                                    continue;
                                }
                            } else if (b.player.distanceTo(var22) > (Float)this.c.getValue()) {
                                continue;
                            }

                            Vec3d var26 = this.a_class_243___________(new Object[]{var20, 68894082660344L});
                            if (var26 != null) {
                                double var24 = b.player.getEyePos().distanceTo(var26);
                                if (!(var24 < ((Float)this.d.getValue()).floatValue()) && !(var24 > ((Float)this.e.getValue()).floatValue())) {
                                    this.o = var26;
                                    this.l.a_void_____________________(new Object[]{93540861878173L});
                                    this.g.reroll(new Object[]{57083216809697L});
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            this.k.retainAll(var17);
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 6686850878648L;
        long var6 = var2 ^ 29247161167908L;
        long var8 = var2 ^ 128670251638447L;
        long var10 = var2 ^ 33065978990036L;
        long var12 = var2 ^ 120434008667598L;
        long var14 = var2 ^ 102840094869659L;
        long var16 = var2 ^ 136752692109656L;
        long var18 = var2 ^ 58982489950237L;
        if (this.o == null) {
            return null;
        } else if (b.player == null || b.world == null) {
            return null;
        } else if (b.currentScreen != null || !b.isWindowFocused()) {
            return null;
        } else if ((Boolean)this.i.getValue() && !b.player.isOnGround()) {
            return null;
        } else if (!this.l.b(new Object[]{this.g, var10})) {
            return null;
        } else if (InventoryUtil.a_Integer(new Object[]{Items.ENDER_PEARL, var8}) == null) {
            this.o = null;
            return null;
        } else {
            RenderBatch var21 = this.b_RenderBatch___(new Object[]{b.player.getEyePos(), this.o, var4});
            if (var21 == null) {
                this.o = null;
                return null;
            } else {
                float var22 = var21.a(new Object[0]);
                float var23 = var21.b(new Object[0]);
                Integer var24 = InventoryUtil.a_Integer(new Object[]{Items.ENDER_PEARL, var8});
                if (var24 == null) {
                    this.o = null;
                    return null;
                } else {
                    int var25 = InventoryUtil.a_int_____(new Object[]{var18});
                    boolean var26 = var25 != var24;
                    if (var26) {
                        int var10000 = var24;
                        Object[] var10003 = new Object[]{null, var12};
                        var10003[0] = var10000;
                        InventoryUtil.a_void________(var10003);
                    }

                    float var27 = b.player.getYaw();
                    float var28 = b.player.getPitch();
                    b.player.setYaw(var22);
                    b.player.setPitch(var23);
                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var14});
                    b.player.setYaw(var27);
                    b.player.setPitch(var28);
                    if (var26 && this.n == -1) {
                        this.n = var25;
                    }

                    if (this.n != -1) {
                        this.m.a_void_____________________(new Object[]{var6});
                        this.h.reroll(new Object[]{var16});
                    }

                    this.o = null;
                    return new RenderBatch(var22, var23);
                }
            }
        }
    }

    private Vec3d a_class_243___________(Object[] var1) {
        EnderPearlEntity var2 = (EnderPearlEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 139836379310563L ^ var3;
        long var5 = var3 ^ 109236779591203L;
        PearlFollowHelper var8 = new PearlFollowHelper(b.world, b.player, Items.ENDER_PEARL.getDefaultStack(), var5);
        var8.setPosition(var2.getX(), var2.getY(), var2.getZ());
        Event.b();
        var8.setVelocity(var2.getVelocity());
        int var9 = 0;

        while (!var8.a && var9++ < 400) {
            var8.tick();
        }

        return var8.a ? new Vec3d(var8.getX(), var8.getY(), var8.getZ()) : null;
    }

    private RenderBatch b_RenderBatch___(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        long var2 = (Long)var1[2];
        var2 = 139836379310563L ^ var2;
        long var6 = var2 ^ 55011785276453L;
        long var8 = var2 ^ 77919603929415L;
        long var10 = var2 ^ 72423269842006L;
        long var12 = var2 ^ 28060685596332L;
        long var14 = var2 ^ 123673287515116L;
        long var16 = var2 ^ 78711687918216L;
        Event.b();
        double var19 = var5.x - var4.x;
        double var21 = var5.z - var4.z;
        float var23 = (float)Math.toDegrees(Math.atan2(-var19, var21));
        ArrayList var24 = new ArrayList();
        ArrayList var25 = new ArrayList();

        for (float var26 = -85.0F; var26 <= 85.0F; var26 += 2.0F) {
            Object[] var10007 = new Object[]{null, null, null, null, var14};
            var10007[3] = var26;
            var10007[2] = var23;
            var10007[1] = var5;
            var10007[0] = var4;
            FloatDoubleIntData var27 = this.a_FloatDoubleIntData(var10007);
            var24.add(var27);
            if (var27 != null) {
                var25.add(var27);
            }
        }

        if (var25.isEmpty()) {
            return null;
        } else {
            double var33 = ((Float)this.f.getValue()).floatValue();
            List var28 = this.b_List____(new Object[]{var24, var6});
            Object[] var10005 = new Object[]{null, null, var10};
            var10005[1] = var33;
            var10005[0] = var25;
            FloatDoubleIntData var29 = this.c(var10005);

            for (FloatDoubleIntData var31 : (Iterable<FloatDoubleIntData>)(var28)) {
                Object[] var10008 = new Object[]{null, null, null, var31, var25, var12};
                var10008[2] = var23;
                var10008[1] = var5;
                var10008[0] = var4;
                this.j(var10008);
            }

            if (var29 != null && !this.d_boolean___(new Object[]{var28, var29, var16})) {
                Object[] var36 = new Object[]{null, null, null, var29, var25, var12};
                var36[2] = var23;
                var36[1] = var5;
                var36[0] = var4;
                this.j(var36);
            }

            var10005 = new Object[]{null, null, var8};
            var10005[1] = var33;
            var10005[0] = var25;
            FloatDoubleIntData var34 = this.b_FloatDoubleIntData(var10005);
            return var34 == null ? null : new RenderBatch(var23, var34.bt());
        }
    }

    private List b_List____(Object[] var1) {
        List var4 = (List)var1[0];
        long var2 = (Long)var1[1];
        var2 = 139836379310563L ^ var2;
        long var5 = var2 ^ 96929552012370L;
        Event.b();
        ArrayList var8 = new ArrayList();

        for (int var9 = 0; var9 < var4.size(); var9++) {
            FloatDoubleIntData var10 = (FloatDoubleIntData)var4.get(var9);
            if (var10 != null) {
                double var11 = var9 > 0 && var4.get(var9 - 1) != null ? ((FloatDoubleIntData)var4.get(var9 - 1)).bu() : Double.POSITIVE_INFINITY;
                double var13 = var9 + 1 < var4.size() && var4.get(var9 + 1) != null ? ((FloatDoubleIntData)var4.get(var9 + 1)).bu() : Double.POSITIVE_INFINITY;
                if (var10.bu() <= var11 && var10.bu() <= var13) {
                    var8.add(var10);
                }
            }
        }

        ArrayList var16 = new ArrayList();

        while (var16.size() < 3) {
            FloatDoubleIntData var17 = null;

            for (FloatDoubleIntData var12 : (Iterable<FloatDoubleIntData>)(var8)) {
                if (!this.a_boolean________________________(new Object[]{var16, var12, var5}) && (var17 == null || var12.bu() < var17.bu())) {
                    var17 = var12;
                }
            }

            if (var17 == null) {
                break;
            }

            var16.add(var17);
        }

        return var16;
    }

    private void j(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        Vec3d var5 = (Vec3d)var1[1];
        float var6 = (Float)var1[2];
        FloatDoubleIntData var3 = (FloatDoubleIntData)var1[3];
        List var4 = (List)var1[4];
        long var7 = (Long)var1[5];
        var7 = 139836379310563L ^ var7;
        long var9 = var7 ^ 25090072478883L;
        Event.b();
        int var12 = (int)Math.ceil(11.0);

        for (int var13 = -var12; var13 <= var12; var13++) {
            float var14 = var3.bt() + var13 * 0.2F;
            if (!(var14 < -85.0F) && !(var14 > 85.0F)) {
                Object[] var10007 = new Object[]{null, null, null, null, var9};
                var10007[3] = var14;
                var10007[2] = var6;
                var10007[1] = var5;
                var10007[0] = var2;
                FloatDoubleIntData var15 = this.a_FloatDoubleIntData(var10007);
                if (var15 != null) {
                    var4.add(var15);
                }
            }
        }
    }

    private FloatDoubleIntData a_FloatDoubleIntData(Object[] var1) {
        Vec3d var7 = (Vec3d)var1[0];
        Vec3d var6 = (Vec3d)var1[1];
        float var4 = (Float)var1[2];
        float var5 = (Float)var1[3];
        long var2 = (Long)var1[4];
        var2 = 139836379310563L ^ var2;
        long var8 = var2 ^ 94050514695118L;
        Object[] var10006 = new Object[]{null, null, null, var8};
        var10006[2] = var5;
        var10006[1] = var4;
        var10006[0] = var7;
        VecIntDoubleData var10 = this.a_VecIntDoubleData(var10006);
        return var10 == null ? null : new FloatDoubleIntData(var5, var10.bq().distanceTo(var6), var10.br(), var10.bs());
    }

    private FloatDoubleIntData b_FloatDoubleIntData(Object[] var1) {
        List var6 = (List)var1[0];
        double var2 = (Double)var1[1];
        long var4 = (Long)var1[2];
        var4 = 139836379310563L ^ var4;
        long var7 = var4 ^ 83751684597940L;
        Event.b();
        double var10 = Double.POSITIVE_INFINITY;

        for (FloatDoubleIntData var13 : (Iterable<FloatDoubleIntData>)(var6)) {
            if (var13.bu() <= var2) {
                var10 = Math.min(var10, var13.bu());
            }
        }

        if (!Double.isFinite(var10)) {
            return null;
        } else {
            double var18 = Math.min(var2, var10 + 0.25);
            FloatDoubleIntData var14 = null;

            for (FloatDoubleIntData var16 : (Iterable<FloatDoubleIntData>)(var6)) {
                if (var16.bu() <= var18) {
                    var14 = this.d(new Object[]{var14, var16, var7});
                }
            }

            return var14;
        }
    }

    private FloatDoubleIntData c(Object[] var1) {
        List var6 = (List)var1[0];
        double var2 = (Double)var1[1];
        long var4 = (Long)var1[2];
        var4 = 139836379310563L ^ var4;
        long var7 = var4 ^ 82651218011557L;
        Event.b();
        FloatDoubleIntData var10 = null;

        for (FloatDoubleIntData var12 : (Iterable<FloatDoubleIntData>)(var6)) {
            if (var12.bu() <= var2) {
                var10 = this.d(new Object[]{var10, var12, var7});
            }
        }

        return var10;
    }

    private FloatDoubleIntData d(Object[] var1) {
        FloatDoubleIntData var5 = (FloatDoubleIntData)var1[0];
        FloatDoubleIntData var4 = (FloatDoubleIntData)var1[1];
        if (var5 == null) {
            return var4;
        } else {
            int var7 = Integer.compare(var4.bv(), var5.bv());
            if (var7 != 0) {
                return var7 < 0 ? var4 : var5;
            } else {
                int var8 = Double.compare(var4.bw(), var5.bw());
                if (var8 != 0) {
                    return var8 < 0 ? var4 : var5;
                } else {
                    int var9 = Double.compare(var4.bu(), var5.bu());
                    if (var9 != 0) {
                        return var9 < 0 ? var4 : var5;
                    } else {
                        return var4.bt() > var5.bt() ? var4 : var5;
                    }
                }
            }
        }
    }

    private boolean a_boolean________________________(Object[] var1) {
        List var5 = (List)var1[0];
        FloatDoubleIntData var2 = (FloatDoubleIntData)var1[1];
        long var3 = (Long)var1[2];
        var3 = 139836379310563L ^ var3;
        long var6 = var3 ^ 11517251334472L;
        Event.b();

        for (FloatDoubleIntData var10 : (Iterable<FloatDoubleIntData>)(var5)) {
            if (this.e(new Object[]{var10, var2, var6})) {
                return true;
            }
        }

        return false;
    }

    private boolean d_boolean___(Object[] var1) {
        List var5 = (List)var1[0];
        FloatDoubleIntData var2 = (FloatDoubleIntData)var1[1];
        Event.b();

        for (FloatDoubleIntData var8 : (Iterable<FloatDoubleIntData>)(var5)) {
            if (Math.abs(var8.bt() - var2.bt()) < 0.1F) {
                return true;
            }
        }

        return false;
    }

    private boolean e(Object[] var1) {
        FloatDoubleIntData var5 = (FloatDoubleIntData)var1[0];
        FloatDoubleIntData var4 = (FloatDoubleIntData)var1[1];
        return var5 != null && var4 != null && Math.abs(var5.bt() - var4.bt()) < 6.0F;
    }

    private VecIntDoubleData a_VecIntDoubleData(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        float var5 = (Float)var1[1];
        float var6 = (Float)var1[2];
        long var2 = (Long)var1[3];
        var2 = 139836379310563L ^ var2;
        long var7 = var2 ^ 54547659135736L;
        Event.b();
        float var10 = var5 * (float) (Math.PI / 180.0);
        float var11 = var6 * (float) (Math.PI / 180.0);
        double var12 = -MathHelper.sin(var10) * MathHelper.cos(var11);
        double var14 = -MathHelper.sin(var11);
        double var16 = MathHelper.cos(var10) * MathHelper.cos(var11);
        Vec3d var18 = new Vec3d(var12, var14, var16).normalize().multiply(1.5);
        PearlFollowHelper var19 = new PearlFollowHelper(b.world, b.player, Items.ENDER_PEARL.getDefaultStack(), var7);
        var19.setPosition(var4.x, var4.y, var4.z);
        var19.setVelocity(var18);
        int var20 = 0;

        double var21;
        for (var21 = var4.y; !var19.a && var20 < 400; var21 = Math.max(var21, var19.getY())) {
            var19.tick();
            var20++;
        }

        return var19.a ? new VecIntDoubleData(new Vec3d(var19.getX(), var19.getY(), var19.getZ()), var20, var21) : null;
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
