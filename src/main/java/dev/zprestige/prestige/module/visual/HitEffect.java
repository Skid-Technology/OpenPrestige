package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.HitEffectHelper;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.module.misc.CrystalOptimizer;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderUtil;
import dev.zprestige.prestige.render.RoundedTextureShader;
import dev.zprestige.prestige.render.TextureUtil;
import dev.zprestige.prestige.type.AnimationMode;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3d;

public class HitEffect extends Module {
    public HitEffect() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private IntSetting a;
    private IntSetting c;
    private BoolSetting d;
    private BoolSetting e;
    private static FontRenderer f;
    private static FontRenderer g;
    private ArrayList i = new ArrayList();
    private RenderHelper27 j = new RenderHelper27(26293358099357L);

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 22594282158762L;
        Event.b();

        for (Module var8 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
            if (var8.getClass().equals(CrystalOptimizer.class) && var8.isEnabled()) {
                var8.toggle(new Object[]{var4});
                var8.toggle(new Object[]{var4});
            }
        }
    }

    @Subscribe
    public void a(EntityEvent var1) {
        Event.b();
        Entity var9 = var1.a(new Object[0]);

        for (int var10 = 0; var10 < this.a.getValue(); var10++) {
            if (this.i.size() <= (Integer)this.c.getValue() * 20) {
                this.i
                    .add(
                        new HitEffectHelper(
                            PositionUtil.a(new Object[]{var9, 67736483458412L}),
                            new Vec3d(
                                var9.getX() + (Math.random() - 0.5) * 0.5, var9.getY() + Math.random() * 1.0 + 0.5, var9.getZ() + (Math.random() - 0.5) * 0.5
                            ),
                            AnimationMode.LEGACY,
                            71682537649551L
                        )
                    );
            }
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if (this.i.isEmpty()) {
            this.j.a_void_____________________(new Object[]{93540861878173L});
        } else {
            boolean var15 = (Boolean)this.d.getValue();
            short var10000 = 30169;
            long var16 = (long)Math.min(this.j.a_float__________________(new Object[]{45633363943154L}), 20.0F);

            for (long var18 = 0L; var18 <= var16; var18++) {
                for (HitEffectHelper var21 : (Iterable<HitEffectHelper>)(this.i)) {
                    Object[] var10004 = new Object[]{null, 48619495990225L};
                    var10004[0] = var15;
                    var21.a_void__________(var10004);
                }
            }

            this.i.removeIf(lpL209_0 -> {
                if (!(Math.sqrt(b.player.squaredDistanceTo(((dev.zprestige.prestige.math.HitEffectHelper)lpL209_0).b(new Object[0]))) > 20.0)) {
                    RenderHelper27 var10000x = ((dev.zprestige.prestige.math.HitEffectHelper)lpL209_0).a_RenderHelper27(new Object[0]);
                    float var10001 = (Integer)this.c.getValue() * 1000;
                    Object[] var10004x = new Object[]{null, 2505922520844L};
                    var10004x[0] = var10001;
                    if (!var10000x.a(var10004x)) {
                        return false;
                    }
                }

                return true;
            });
            this.j.a_void_____________________(new Object[]{93540861878173L});
            this.j(
                new Object[]{
                    var1,
                    this.i,
                    MathHelper4.d(new Object[]{122780339483702L}).brighter(),
                    MathHelper4.d(new Object[]{122780339483702L}).darker(),
                    13255623397683L
                }
            );
        }
    }

    public void j(Object[] var1) {
        TickEvent27 var2 = (TickEvent27)var1[0];
        ArrayList var7 = (ArrayList)var1[1];
        Color var6 = (Color)var1[2];
        Color var5 = (Color)var1[3];
        long var3 = (Long)var1[4];
        var3 = 113418132108689L ^ var3;
        long var8 = var3 ^ 57308816830803L;
        long var10 = var3 ^ 138184950432182L;
        long var12 = var3 ^ 11867991891386L;
        long var14 = var3 ^ 122583201262707L;
        long var16 = var3 ^ 699887528671L;
        Event.b();
        b.getEntityRenderDispatcher();
        MatrixStack.c(new Object[]{var8});
        int var21 = 0;

        for (HitEffectHelper var23 : (Iterable<HitEffectHelper>)(new ArrayList(var7))) {
            var21++;
            Vec3d var24 = var23.b(new Object[0]);
            double var25 = PositionUtil.b(new Object[]{var16}).distanceTo(var24);
            if (b.world.getBlockState(BlockPos.ofFloored(var24)).isAir() && (var21 % 10 == 0 || !(var25 > 25.0)) && (var21 % 3 != 0 || !(var25 > 15.0))) {
                float var10001 = ((Integer)this.c.getValue()).intValue() * 1000.0F;
                Object[] var10004 = new Object[]{null, var10};
                var10004[0] = var10001;
                float var27 = var23.a_float__________(var10004);
                int var28 = (int)(255.0F * (1.0F - var27));
                var28 = MathHelper.clamp(var28, 0, 255);
                float var10000 = var21 / 5.0F;
                Object[] var10006 = new Object[]{null, null, var6, var5, var14};
                var10006[1] = 0.5F;
                var10006[0] = var10000;
                Color var29 = a(var10006);
                Color var30 = new Color(var29.getRed(), var29.getGreen(), var29.getBlue(), var28);
                float var10003 = var23.g.equals(AnimationMode.POP) ? 7.0F : 4.5F;
                var10006 = new Object[]{null, null, null, null, var12};
                var10006[3] = var10003;
                var10006[2] = var30;
                var10006[1] = var23;
                var10006[0] = var2;
                k(var10006);
            }
        }
    }

    public static void k(Object[] var0) {
        TickEvent27 var4 = (TickEvent27)var0[0];
        HitEffectHelper var2 = (HitEffectHelper)var0[1];
        Color var3 = (Color)var0[2];
        float var1 = (Float)var0[3];
        long var5 = (Long)var0[4];
        var5 = 113418132108689L ^ var5;
        long var7 = var5 ^ 74568779790014L;
        long var9 = var5 ^ 16146997084625L;
        long var11 = var5 ^ 114795440364950L;
        if (f == null) {
            f = TextureUtil.a(new Object[]{"assets/prestige/particles/glow.png", var7});
        }

        if (g == null) {
            g = TextureUtil.a(new Object[]{"assets/prestige/particles/pop.png", var7});
        }

        MatrixCameraData var10000 = var4.b;
        double var10001 = var2.c.x;
        double var10002 = var2.c.y;
        double var10003 = var2.c.z;
        Object[] var10006 = new Object[]{null, null, null, null, var11};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var10000;
        Vector3d var14 = RenderUtil.a_Vector3d(var10006);
        FontRenderer var15 = var2.g.equals(AnimationMode.POP) ? g : f;
        Matrix4f var16 = new Matrix4f();
        var16.translate((float)var14.x, (float)var14.y, (float)var14.z);
        var16.scale(0.04F);
        var16.rotateY((float)Math.toRadians(-var4.b.bC().getYaw()));
        var16.rotateX((float)Math.toRadians(var4.b.bC().getPitch()));
        RenderHelper8 var18 = var4.a;
        MatrixCameraData var19 = var4.b;
        Object[] var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var9};
        var10014[11] = 0.0F;
        var10014[10] = var3;
        var10014[9] = 0.0F;
        var10014[8] = var1;
        var10014[7] = var1;
        var10014[6] = 0.0F;
        var10014[5] = 0.0F;
        var10014[4] = 0.0F;
        var10014[3] = var15;
        var10014[2] = var16;
        var10014[1] = var19;
        var10014[0] = var18;
        RoundedTextureShader.m(var10014);
    }

    public static Color a(Object[] var0) {
        float var6 = (Float)var0[0];
        float var5 = (Float)var0[1];
        Color var2 = (Color)var0[2];
        Color var1 = (Color)var0[3];
        long var3 = (Long)var0[4];
        var3 = 113418132108689L ^ var3;
        long var7 = var3 ^ 34840795952166L;
        Event.b();
        Color var10 = Color.BLACK;
        if (var2 != null && var1 != null) {
            double var10002 = (Math.sin(System.currentTimeMillis() / 1.0E8 * var5 * 400000.0 + var6 * 0.55F) + 1.0) * 0.5;
            Object[] var10005 = new Object[]{null, null, null, var7};
            var10005[2] = var10002;
            var10005[1] = var1;
            var10005[0] = var2;
            return b_Color__(var10005);
        } else {
            return var10;
        }
    }

    public static Color b_Color__(Object[] var0) {
        Color var2 = (Color)var0[0];
        Color var1 = (Color)var0[1];
        double var3 = (Double)var0[2];
        Event.b();
        double var8 = 1.0 - var3;
        int var10 = (int)(var2.getRed() * var3 + var1.getRed() * var8);
        int var11 = (int)(var2.getGreen() * var3 + var1.getGreen() * var8);
        int var12 = (int)(var2.getBlue() * var3 + var1.getBlue() * var8);
        if (var10 < 0 || var10 > 255) {
            var10 = Math.max(0, Math.min(255, var10));
        }

        if (var11 < 0 || var11 > 255) {
            var11 = Math.max(0, Math.min(255, var11));
        }

        if (var12 < 0 || var12 > 255) {
            var12 = Math.max(0, Math.min(255, var12));
        }

        return new Color(var10, var11, var12);
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if ((Boolean)this.e.getValue()) {
            if (var1.a(new Object[0]) instanceof EntityStatusS2CPacket var9 && var9.getStatus() == 35 && var9.getEntity(b.world) instanceof PlayerEntity var12) {
                for (int var13 = 0; var13 < this.a.getValue() * 2; var13++) {
                    if (this.i.size() <= (Integer)this.c.getValue() * 20) {
                        this.i
                            .add(
                                new HitEffectHelper(
                                    PositionUtil.a(new Object[]{var12, 67736483458412L}),
                                    new Vec3d(
                                        var12.getX() + (Math.random() - 0.5) * 0.5,
                                        var12.getY() + Math.random() * 1.0 + 0.5,
                                        var12.getZ() + (Math.random() - 0.5) * 0.5
                                    ),
                                    AnimationMode.POP,
                                    71682537649551L
                                )
                            );
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
