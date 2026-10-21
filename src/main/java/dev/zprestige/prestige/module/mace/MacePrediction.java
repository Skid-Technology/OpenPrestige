package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MacePrediction extends Module {
    private static final double a = 0.08;
    private static final double b = 0.98;
    private static final double c = 0.999;
    private EnumSetting d;
    private BoolSetting e;
    private IntSetting f;
    private ColorSetting g;
    private ColorSetting h;
    private BoolSetting i;
    private IntSetting j;
    private ColorSetting k;
    private BoolSetting l;
    private ColorSetting m;
    private ColorSetting n;
    private FloatSetting o;

    public MacePrediction() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.k.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL166_0 -> (Boolean)this.i.getValue(), 119980880492450L});
        this.m.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL165_0 -> (Boolean)this.l.getValue(), 119980880492450L});
        this.h.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL168_0 -> (Boolean)this.e.getValue(), 119980880492450L});
        this.f.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL170_0 -> (Boolean)this.e.getValue(), 98265949679296L});
        this.o.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL163_0 -> (Boolean)this.l.getValue(), 35046756052036L});
        this.g.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL169_0 -> (Boolean)this.e.getValue(), 119980880492450L});
        this.n.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL164_0 -> (Boolean)this.l.getValue(), 119980880492450L});
        this.j.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL167_0 -> (Boolean)this.i.getValue(), 98265949679296L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        Event.b();
        PlayerEntity var19 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
        if (var19 != null) {
            if (PositionUtil.a_double___________(new Object[]{var19, 111298775242240L}) < 0.3F) {
                if (!(Boolean)this.i.getValue()) {
                    return;
                }

                Vec3d var20 = PositionUtil.a(new Object[]{var19, 67736483458412L});
                if ((Boolean)this.l.getValue()) {
                    MatrixCameraData var10000 = var1.b;
                    RenderHelper8 var10001 = var1.a;
                    float var10002 = (float)var20.x;
                    float var10003 = (float)var20.y;
                    float var10004 = (float)var20.z;
                    Color var10006 = (Color)this.m.getValue();
                    Object[] var10010 = new Object[]{null, null, 20220661505083L};
                    var10010[1] = 0.8F;
                    var10010[0] = var10006;
                    var10006 = MathHelper4.b(var10010);
                    Color var10007 = (Color)this.n.getValue();
                    float var10008 = 0.2F * ((Float)this.o.getValue() / 255.0F);
                    Object[] var10011 = new Object[]{null, null, 20220661505083L};
                    var10011[1] = var10008;
                    var10011[0] = var10007;
                    var10010 = new Object[]{null, null, null, null, null, null, var10006, MathHelper4.b(var10011), 127100755770959L};
                    var10010[5] = 3.0F;
                    var10010[4] = var10004;
                    var10010[3] = var10003;
                    var10010[2] = var10002;
                    var10010[1] = var10001;
                    var10010[0] = var10000;
                    RenderState.d(var10010);
                }

                this.j(new Object[]{var19, var1, 116402847105576L});
            } else {
                if (!(Boolean)this.e.getValue()) {
                    return;
                }

                Vec3d var21 = this.a(new Object[]{var19, var1, 67797917859641L});
                if (var21 != null && (Boolean)this.l.getValue()) {
                    MatrixCameraData var22 = var1.b;
                    RenderHelper8 var23 = var1.a;
                    float var24 = (float)var21.x;
                    float var25 = (float)var21.y;
                    float var26 = (float)var21.z;
                    Color var28 = (Color)this.m.getValue();
                    Object[] var33 = new Object[]{null, null, 20220661505083L};
                    var33[1] = 0.8F;
                    var33[0] = var28;
                    var28 = MathHelper4.b(var33);
                    Color var30 = (Color)this.n.getValue();
                    float var31 = 0.2F * ((Float)this.o.getValue() / 255.0F);
                    Object[] var35 = new Object[]{null, null, 20220661505083L};
                    var35[1] = var31;
                    var35[0] = var30;
                    var33 = new Object[]{null, null, null, null, null, null, var28, MathHelper4.b(var35), 127100755770959L};
                    var33[5] = 3.0F;
                    var33[4] = var26;
                    var33[3] = var25;
                    var33[2] = var24;
                    var33[1] = var23;
                    var33[0] = var22;
                    RenderState.d(var33);
                }
            }
        }
    }

    private Vec3d a(Object[] var1) {
        Entity var5 = (Entity)var1[0];
        TickEvent27 var2 = (TickEvent27)var1[1];
        long var3 = (Long)var1[2];
        var3 = 33234515022557L ^ var3;
        long var6 = var3 ^ 33020995289224L;
        long var8 = var3 ^ 88973239075438L;
        Vec3d var11 = PositionUtil.a(new Object[]{var5, var6});
        Vec3d var12 = var5.getVelocity();
        Event.b();
        Vec3d var13 = var11;
        Vec3d var14 = null;

        for (int var15 = 0; var15 < this.f.getValue(); var15++) {
            var13 = var11;
            var11 = var11.add(var12);
            BlockPos var16 = new BlockPos((int)Math.floor(var11.x), (int)Math.floor(var11.y - 0.1), (int)Math.floor(var11.z));
            if (!var5.getEntityWorld().getBlockState(var16).isAir() || var11.y <= var5.getEntityWorld().getBottomY()) {
                this.l(new Object[]{var13, (Color)this.h.getValue(), var2, var8});
                return new Vec3d(var13.x, var16.getY() + 1, var13.z);
            }

            if (var14 == null || var14.squaredDistanceTo(var11) >= 0.1) {
                this.l(new Object[]{var11, (Color)this.g.getValue(), var2, var8});
                var14 = var11;
            }

            var12 = var12.subtract(0.0, 0.08, 0.0);
            var12 = var12.multiply(0.98, 0.98, 0.98);
        }

        return var13;
    }

    private void j(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        TickEvent27 var5 = (TickEvent27)var1[1];
        long var2 = (Long)var1[2];
        var2 = 33234515022557L ^ var2;
        long var6 = var2 ^ 40418290049027L;
        long var8 = var2 ^ 81909390835097L;
        long var10 = var2 ^ 75333267657599L;
        Vec3d var12 = PositionUtil.a(new Object[]{var4, var8});
        Vec3d var13 = PositionUtil.c(new Object[]{var4, var6});
        Vec3d var14 = new Vec3d(var12.x - var13.x, var12.y, var12.z - var13.z);
        this.k(new Object[]{var4, var12, var14, var5, var10});
    }

    private void k(Object[] var1) {
        PlayerEntity var7 = (PlayerEntity)var1[0];
        Vec3d var6 = (Vec3d)var1[1];
        Vec3d var2 = (Vec3d)var1[2];
        TickEvent27 var3 = (TickEvent27)var1[3];
        long var4 = (Long)var1[4];
        var4 = 33234515022557L ^ var4;
        long var8 = var4 ^ 103513493651165L;
        Event.b();
        Vec3d var11 = var6;
        Vec3d var12 = var2;
        Vec3d var13 = null;

        for (int var14 = 0; var14 < this.j.getValue(); var14++) {
            var11 = var11.add(var12.x, 0.0, var12.z);
            BlockPos var15 = new BlockPos((int)Math.floor(var11.x), (int)Math.floor(var11.y - 0.1), (int)Math.floor(var11.z));
            if (var7.getEntityWorld() != null && var7.getEntityWorld().getBlockState(var15).isAir()) {
                break;
            }

            var12 = var12.multiply(0.999, 1.0, 0.999);
            if (Math.abs(var12.x) < 0.005 && Math.abs(var12.z) < 0.005) {
                break;
            }

            if (var13 == null || var13.squaredDistanceTo(var11) >= 0.1) {
                this.l(new Object[]{var11, (Color)this.k.getValue(), var3, var8});
                var13 = var11;
            }
        }
    }

    public void l(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        Color var6 = (Color)var1[1];
        TickEvent27 var5 = (TickEvent27)var1[2];
        long var3 = (Long)var1[3];
        var3 = 33234515022557L ^ var3;
        long var7 = var3 ^ 17413565790798L;
        long var9 = var3 ^ 1633365999833L;
        long var11 = var3 ^ 139780278493548L;
        Event.b();
        String var14 = (String)this.d.getValue();
        switch (var14) {
            case "Box":
                MatrixCameraData var19 = var5.b;
                RenderHelper8 var22 = var5.a;
                float var25 = (float)var2.x - 0.25F;
                float var28 = (float)var2.y;
                float var31 = (float)var2.z - 0.25F;
                float var34 = (float)var2.x + 0.25F;
                float var37 = (float)var2.y + 2.0F;
                float var40 = (float)var2.z + 0.25F;
                Object[] var43 = new Object[]{null, null, null, null, null, null, null, null, var6, var7};
                var43[7] = var40;
                var43[6] = var37;
                var43[5] = var34;
                var43[4] = var31;
                var43[3] = var28;
                var43[2] = var25;
                var43[1] = var22;
                var43[0] = var19;
                RenderState.h(var43);
                break;
            case "Outline":
                MatrixCameraData var18 = var5.b;
                RenderHelper8 var21 = var5.a;
                float var24 = (float)var2.x - 0.25F;
                float var27 = (float)var2.y;
                float var30 = (float)var2.z - 0.25F;
                float var33 = (float)var2.x + 0.25F;
                float var36 = (float)var2.y + 2.0F;
                float var39 = (float)var2.z + 0.25F;
                Object[] var42 = new Object[]{null, null, null, null, null, null, null, null, var6, var9};
                var42[7] = var39;
                var42[6] = var36;
                var42[5] = var33;
                var42[4] = var30;
                var42[3] = var27;
                var42[2] = var24;
                var42[1] = var21;
                var42[0] = var18;
                RenderState.g(var42);
                break;
            case "Both":
                MatrixCameraData var10000 = var5.b;
                RenderHelper8 var10001 = var5.a;
                float var10002 = (float)var2.x - 0.25F;
                float var10003 = (float)var2.y;
                float var10004 = (float)var2.z - 0.25F;
                float var10005 = (float)var2.x + 0.25F;
                float var10006 = (float)var2.y + 2.0F;
                float var10007 = (float)var2.z + 0.25F;
                float var10009 = var6.getAlpha() / 255.0F * 0.5F;
                Object[] var10012 = new Object[]{null, null, var11};
                var10012[1] = var10009;
                var10012[0] = var6;
                Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.b(var10012), var7};
                var10011[7] = var10007;
                var10011[6] = var10006;
                var10011[5] = var10005;
                var10011[4] = var10004;
                var10011[3] = var10003;
                var10011[2] = var10002;
                var10011[1] = var10001;
                var10011[0] = var10000;
                RenderState.h(var10011);
                var10000 = var5.b;
                var10001 = var5.a;
                var10002 = (float)var2.x - 0.25F;
                var10003 = (float)var2.y;
                var10004 = (float)var2.z - 0.25F;
                var10005 = (float)var2.x + 0.25F;
                var10006 = (float)var2.y + 2.0F;
                var10007 = (float)var2.z + 0.25F;
                var10011 = new Object[]{null, null, null, null, null, null, null, null, var6, var9};
                var10011[7] = var10007;
                var10011[6] = var10006;
                var10011[5] = var10005;
                var10011[4] = var10004;
                var10011[3] = var10003;
                var10011[2] = var10002;
                var10011[1] = var10001;
                var10011[0] = var10000;
                RenderState.g(var10011);
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
