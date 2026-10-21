package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.TrajectoriesHelper;
import dev.zprestige.prestige.data.BoolData3;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Trajectories extends Module {
    public Trajectories() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private static final float[] a = new float[]{0.0F, -10.0F, 10.0F};
    public EnumSetting c;
    public ColorSetting d;
    private ArrayList e = new ArrayList();
    private boolean i = false;
    private ItemStack f;
    private String h;
    private boolean g;
    private boolean j;
    private int k;
    private float l;
    private float m;
    private double n;
    private double o;
    private double p;
    private static final float q = 12.0F;
    private static final float r = 7.0F;
    private static final float s = 20.0F;
    private float t = 0.0F;
    private float u = 0.0F;
    private float v = 0.0F;
    private boolean w = false;
    private long x = 0L;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal, Gamemode.Mace);
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if (b.player != null) {
            ItemStack var15 = b.player.getMainHandStack();
            String var16 = ((String)this.c.getValue()).toString();
            Vec3d var17 = MatrixStack.b(new Object[]{b.player, 78245126833881L});
            boolean var18 = b.player.isUsingItem();
            int var19 = b.player.getItemUseTime();
            boolean var20 = var15.isOf(Items.CROSSBOW) && CrossbowItem.isCharged(var15);
            long var21 = System.currentTimeMillis();
            float var23 = this.x == 0L ? 0.016F : (float)(var21 - this.x) / 1000.0F;
            boolean var24 = this.x != 0L && var21 - this.x > 1000L;
            this.x = var21;
            var23 = Math.min(var23, 0.1F);
            float var25 = b.player.getPitch();
            float var26 = b.player.getYaw();
            if (this.w && !var24) {
                float var10001 = this.u;
                Object[] var10007 = new Object[]{null, null, null, null, 56959580732021L};
                var10007[3] = 20.0F;
                var10007[2] = var23;
                var10007[1] = var25;
                var10007[0] = var10001;
                this.u = b_float__________________(var10007);
                var10001 = this.v;
                float var10002 = this.v;
                Object[] var10005 = new Object[]{null, var26};
                var10005[0] = var10002;
                this.v = var10001 + c(var10005) * (1.0F - (float)Math.exp(-20.0F * var23));
            } else {
                this.u = var25;
                this.v = var26;
                this.w = true;
            }

            boolean var27 = this.a(new Object[]{var15, var16, 68228975196473L});
            float var32 = this.t;
            float var33 = var27 ? 1.0F : 0.0F;
            float var10004 = var27 ? 12.0F : 7.0F;
            Object[] var34 = new Object[]{null, null, null, null, 56959580732021L};
            var34[3] = var10004;
            var34[2] = var23;
            var34[1] = var33;
            var34[0] = var32;
            this.t = b_float__________________(var34);
            if (var27) {
                if (!this.i
                    || var15 != this.f
                    || !var16.equals(this.h)
                    || var18 != this.g
                    || var20 != this.j
                    || var19 != this.k
                    || this.u != this.l
                    || this.v != this.m
                    || var17.x != this.n
                    || var17.y != this.o
                    || var17.z != this.p) {
                    this.j(new Object[]{var15, var16, 44771390742457L});
                    this.f = var15;
                    this.h = var16;
                    this.g = var18;
                    this.j = var20;
                    this.k = var19;
                    this.l = this.u;
                    this.m = this.v;
                    this.n = var17.x;
                    this.o = var17.y;
                    this.p = var17.z;
                    this.i = true;
                }
            } else if (this.t < 0.005F && !this.e.isEmpty()) {
                this.e.clear();
                this.i = false;
            }

            if (!(this.t < 0.005F)) {
                for (BoolData3 var29 : (Iterable<BoolData3>)(this.e)) {
                    this.k(new Object[]{var1, var29, 49091042478923L});
                }
            }
        }
    }

    private boolean a(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        String var5 = (String)var1[1];
        boolean var7 = var5.equals("Bows") || var5.equals("Crossbow") || var5.equals("All");
        boolean var8 = var5.equals("Bows") || var5.equals("Bow") || var5.equals("All");
        boolean var9 = var5.equals("Pearl") || var5.equals("All");
        if (var2.isOf(Items.CROSSBOW) && var7) {
            return CrossbowItem.isCharged(var2);
        } else {
            return var2.isOf(Items.BOW) && b.player.isUsingItem() && var8 ? true : var2.isOf(Items.ENDER_PEARL) && var9;
        }
    }

    private static float b_float__________________(Object[] var0) {
        float var1 = (Float)var0[0];
        float var5 = (Float)var0[1];
        float var6 = (Float)var0[2];
        float var4 = (Float)var0[3];
        return var1 + (var5 - var1) * (1.0F - (float)Math.exp(-var4 * var6));
    }

    private static float c(Object[] var0) {
        float var2 = (Float)var0[0];
        float var1 = (Float)var0[1];
        return ((var1 - var2) % 360.0F + 540.0F) % 360.0F - 180.0F;
    }

    private void j(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        String var5 = (String)var1[1];
        long var3 = (Long)var1[2];
        var3 = 123598832424487L ^ var3;
        long var6 = var3 ^ 105193676660312L;
        long var8 = var3 ^ 71729562861207L;
        Event.b();
        this.e.clear();
        boolean var11 = var5.equals("Bows") || var5.equals("Crossbow") || var5.equals("All");
        boolean var12 = var5.equals("Bows") || var5.equals("Bow") || var5.equals("All");
        boolean var13 = var5.equals("Pearl") || var5.equals("All");
        if (var2.isOf(Items.CROSSBOW) && var11) {
            if (!CrossbowItem.isCharged(var2)) {
                return;
            }

            var2.getItem();
            boolean var15 = EnchantmentHelper.getLevel(
                    b.world.getRegistryManager().getOptional(Enchantments.MULTISHOT.getRegistryRef()).get().getEntry(Enchantments.MULTISHOT.getValue()).get(),
                    var2
                )
                > 0;
            if (var15) {
                ArrayList var17 = this.e;
                float var10002 = a[0];
                Object[] var23 = new Object[]{null, Items.ARROW.getDefaultStack(), var2, var8};
                var23[0] = var10002;
                var17.add(this.b_BoolData3(var23));
                var17 = this.e;
                var10002 = a[1];
                var23 = new Object[]{null, Items.ARROW.getDefaultStack(), var2, var8};
                var23[0] = var10002;
                var17.add(this.b_BoolData3(var23));
                var17 = this.e;
                var10002 = a[2];
                var23 = new Object[]{null, Items.ARROW.getDefaultStack(), var2, var8};
                var23[0] = var10002;
                var17.add(this.b_BoolData3(var23));
            } else {
                ArrayList var20 = this.e;
                Object[] var26 = new Object[]{null, Items.ARROW.getDefaultStack(), var2, var8};
                var26[0] = 0.0F;
                var20.add(this.b_BoolData3(var26));
            }
        } else if (var2.isOf(Items.BOW) && b.player.isUsingItem() && var12) {
            ArrayList var10000 = this.e;
            Object[] var10007 = new Object[]{null, Items.ARROW.getDefaultStack(), var2, var8};
            var10007[0] = 0.0F;
            var10000.add(this.b_BoolData3(var10007));
        } else if (var2.isOf(Items.ENDER_PEARL) && var13) {
            this.e.add(this.a_BoolData3(new Object[]{var6}));
        }
    }

    private void k(Object[] var1) {
        TickEvent27 var2 = (TickEvent27)var1[0];
        BoolData3 var3 = (BoolData3)var1[1];
        long var4 = (Long)var1[2];
        var4 = 123598832424487L ^ var4;
        long var6 = var4 ^ 8967109307519L;
        long var8 = var4 ^ 79717573006245L;
        Event.b();
        ArrayList var11 = var3.b9();
        if (var11.size() > 1) {
            Color var12 = (Color)this.d.getValue();
            long var13 = System.currentTimeMillis();
            Object[] var10007 = new Object[]{null, null, null, null, var6};
            var10007[3] = var13;
            var10007[2] = var12;
            var10007[1] = var11;
            var10007[0] = var2;
            this.l(var10007);
            Vec3d var10002 = (Vec3d)var11.get(var11.size() - 1);
            boolean var10004 = var3.b_();
            Object[] var10008 = new Object[]{null, null, null, null, null, var8};
            var10008[4] = var13;
            var10008[3] = var10004;
            var10008[2] = var12;
            var10008[1] = var10002;
            var10008[0] = var2;
            this.m(var10008);
        }
    }

    private void l(Object[] var1) {
        TickEvent27 var4 = (TickEvent27)var1[0];
        ArrayList var3 = (ArrayList)var1[1];
        Color var2 = (Color)var1[2];
        long var5 = (Long)var1[3];
        long var7 = (Long)var1[4];
        var7 = 123598832424487L ^ var7;
        long var9 = var7 ^ 109930941461946L;
        long var11 = var7 ^ 21820500128038L;
        long var13 = var7 ^ 39696662745329L;
        int var16 = var3.size() - 1;
        int var17 = Math.min(28, var16);
        Event.b();

        for (int var18 = 0; var18 < var17; var18++) {
            float var19 = var17 == 1 ? 0.0F : (float)var18 / (var17 - 1);
            int var20 = Math.round(255.0F - var19 * 175.0F);
            MatrixCameraData var10000 = var4.b;
            RenderHelper8 var10001 = var4.a;
            Object[] var10008 = new Object[]{null, null, null, null, var13};
            var10008[3] = var16;
            var10008[2] = var17;
            var10008[1] = var18;
            var10008[0] = var3;
            ArrayList var10002 = a_ArrayList__(var10008);
            var10008 = new Object[]{null, null, var11};
            var10008[1] = var20;
            var10008[0] = var2;
            RenderState.c(new Object[]{var10000, var10001, var10002, this.a_Color_____(var10008), var9});
        }

        float var23 = (float)(var5 % 1200L) / 1200.0F;
        int var24 = (int)(var23 * var17);

        for (int var25 = 0; var25 < 4; var25++) {
            int var21 = var24 - var25;
            if (var21 >= 0 && var21 < var17) {
                MatrixCameraData var26 = var4.b;
                RenderHelper8 var27 = var4.a;
                Object[] var30 = new Object[]{null, null, null, null, var13};
                var30[3] = var16;
                var30[2] = var17;
                var30[1] = var21;
                var30[0] = var3;
                ArrayList var28 = a_ArrayList__(var30);
                int var10005 = 255 - var25 * 55;
                var30 = new Object[]{null, null, var11};
                var30[1] = var10005;
                var30[0] = var2;
                RenderState.c(new Object[]{var26, var27, var28, this.a_Color_____(var30), var9});
            }
        }
    }

    private static ArrayList a_ArrayList__(Object[] var0) {
        ArrayList var3 = (ArrayList)var0[0];
        int var6 = (Integer)var0[1];
        int var5 = (Integer)var0[2];
        int var4 = (Integer)var0[3];
        Event.b();
        int var8 = var6 * var4 / var5;
        int var9 = (var6 + 1) * var4 / var5;
        ArrayList var10 = new ArrayList(var9 - var8 + 1);

        for (int var11 = var8; var11 <= var9; var11++) {
            var10.add((Vec3d)var3.get(var11));
        }

        return var10;
    }

    private void m(Object[] var1) {
        TickEvent27 var3 = (TickEvent27)var1[0];
        Vec3d var2 = (Vec3d)var1[1];
        Color var9 = (Color)var1[2];
        boolean var8 = (Boolean)var1[3];
        long var4 = (Long)var1[4];
        long var6 = (Long)var1[5];
        var6 = 123598832424487L ^ var6;
        long var10 = var6 ^ 26103282198177L;
        long var12 = var6 ^ 45222597199940L;
        long var14 = var6 ^ 91805394325244L;
        float var16 = (float)var2.x;
        float var17 = (float)var2.y;
        float var18 = (float)var2.z;
        long var19 = var8 ? 700L : 1500L;
        float var21 = (float)(var4 % var19) / (float)var19;
        float var22 = 0.5F * (1.0F + (float)Math.sin(var21 * (float) (Math.PI * 2)));
        float var23 = (var8 ? 0.45F : 0.25F) + 0.1F * var22;
        Object[] var10005 = new Object[]{null, null, var14};
        var10005[1] = 255;
        var10005[0] = var9;
        Color var24 = this.a_Color_____(var10005);
        MatrixCameraData var10000 = var3.b;
        RenderHelper8 var10001 = var3.a;
        Object[] var10012 = new Object[]{null, null, var14};
        var10012[1] = 0;
        var10012[0] = var9;
        Object[] var10010 = new Object[]{null, null, null, null, null, null, var24, this.a_Color_____(var10012), var10};
        var10010[5] = var23;
        var10010[4] = var18;
        var10010[3] = var17;
        var10010[2] = var16;
        var10010[1] = var10001;
        var10010[0] = var10000;
        RenderState.d(var10010);
        var10000 = var3.b;
        var10001 = var3.a;
        float var33 = var23 + 0.08F;
        Object[] var10014 = new Object[]{null, null, var14};
        var10014[1] = 60;
        var10014[0] = var9;
        var10012 = new Object[]{null, null, null, null, null, null, null, null, var24, this.a_Color_____(var10014), var12};
        var10012[7] = 0.06F;
        var10012[6] = 0.02F;
        var10012[5] = var33;
        var10012[4] = var18;
        var10012[3] = var17;
        var10012[2] = var16;
        var10012[1] = var10001;
        var10012[0] = var10000;
        RenderState.f(var10012);
        if (var8) {
            float var25 = (float)(var4 % 500L) / 500.0F;
            float var26 = 0.3F + var25 * 0.9F;
            float var27 = 1.0F - var25;
            var10000 = var3.b;
            var10001 = var3.a;
            int var34 = Math.round(255.0F * var27);
            Object[] var10013 = new Object[]{null, null, var14};
            var10013[1] = var34;
            var10013[0] = var9;
            Color var10008 = this.a_Color_____(var10013);
            int var10011 = Math.round(60.0F * var27);
            var10014 = new Object[]{null, null, var14};
            var10014[1] = var10011;
            var10014[0] = var9;
            var10012 = new Object[]{null, null, null, null, null, null, null, null, var10008, this.a_Color_____(var10014), var12};
            var10012[7] = 0.05F;
            var10012[6] = 0.02F;
            var10012[5] = var26;
            var10012[4] = var18;
            var10012[3] = var17;
            var10012[2] = var16;
            var10012[1] = var10001;
            var10012[0] = var10000;
            RenderState.f(var10012);
        }
    }

    private Color a_Color_____(Object[] var1) {
        Color var2 = (Color)var1[0];
        int var3 = (Integer)var1[1];
        return new Color(var2.getRed(), var2.getGreen(), var2.getBlue(), MathHelper.clamp(Math.round(var3 * this.t), 0, 255));
    }

    private BoolData3 a_BoolData3(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 123598832424487L ^ var2;
        long var4 = var2 ^ 81979255385229L;
        long var6 = var2 ^ 53041597253432L;
        long var8 = var2 ^ 31473421864860L;
        long var10 = var2 ^ 117515664896908L;
        ArrayList var13 = new ArrayList();
        ClientPlayerEntity var14 = b.player;
        Vec3d var15 = MatrixStack.b(new Object[]{b.player, var6});
        Vec3d var16 = PositionUtil.b(new Object[]{var8});
        Event.b();
        var14.setPosition(var15.x, var15.y, var15.z);
        ClientWorld var17 = b.world;
        TrajectoriesHelper var18 = new TrajectoriesHelper(var17, var14, Items.ENDER_PEARL.getDefaultStack(), var10);
        var18.setVelocity(var14, this.u, this.v, 0.0F, 1.5F, 1.0F);
        int var19 = 0;

        while (!var18.a && var19++ < 1000) {
            var13.add(PositionUtil.a(new Object[]{var18, var4}));
            var18.tick();
        }

        var14.setPosition(var16);

        for (int var20 = 0; var20 < var13.size() - 1; var20++) {
            Vec3d var21 = (Vec3d)var13.get(var20);
            var13.set(var20, new Vec3d(var21.x, var21.y - 0.1F, var21.z));
        }

        return new BoolData3(var13, true);
    }

    private BoolData3 b_BoolData3(Object[] var1) {
        float var2 = (Float)var1[0];
        ItemStack var4 = (ItemStack)var1[1];
        ItemStack var3 = (ItemStack)var1[2];
        long var5 = (Long)var1[3];
        var5 = 123598832424487L ^ var5;
        long var7 = var5 ^ 92495623130178L;
        long var9 = var5 ^ 51321322858487L;
        long var11 = var5 ^ 2265100758867L;
        ArrayList var14 = new ArrayList();
        Item var15 = var3.getItem();
        ClientPlayerEntity var16 = b.player;
        Vec3d var17 = MatrixStack.b(new Object[]{b.player, var9});
        Vec3d var18 = PositionUtil.b(new Object[]{var11});
        float var19 = var16.getPitch();
        float var20 = var16.getYaw();
        Event.b();
        var16.setPosition(var17.x, var17.y, var17.z);
        var16.setPitch(this.u);
        var16.setYaw(this.v);
        dev.zprestige.prestige.world.TrajectoriesHelper var21 = new dev.zprestige.prestige.world.TrajectoriesHelper(EntityType.ARROW, var16, b.world, var4);
        if (var15 instanceof CrossbowItem) {
            Vec3d var22 = var16.getOppositeRotationVector(1.0F);
            Quaternionf var23 = new Quaternionf().setAngleAxis((double)(var2 * (float) (Math.PI / 180.0)), var22.x, var22.y, var22.z);
            Vec3d var24 = var16.getRotationVec(1.0F);
            Vector3f var25 = var24.toVector3f().rotate(var23);
            var21.setVelocity(var25.x(), var25.y(), var25.z(), 3.15F, 1.0F);
        } else {
            var21.setVelocity(var16, this.u, this.v, 0.0F, BowItem.getPullProgress(b.player.getItemUseTime()) * 3.0F, 1.0F);
        }

        var21.b = true;
        int var27 = 0;

        while (var21.b && var27++ < 1000) {
            var14.add(PositionUtil.a(new Object[]{var21, var7}));
            var21.tick();
        }

        var16.setPosition(var18);
        var16.setPitch(var19);
        var16.setYaw(var20);

        for (int var28 = 0; var28 < var14.size() - 1; var28++) {
            Vec3d var29 = (Vec3d)var14.get(var28);
            var14.set(var28, new Vec3d(var29.x, var29.y - 0.1F, var29.z));
        }

        return new BoolData3(var14, var21.a);
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
