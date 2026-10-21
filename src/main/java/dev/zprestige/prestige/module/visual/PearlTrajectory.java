package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.math.PearlTrajectoryHelper;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class PearlTrajectory extends Module {
    private BoolSetting d;
    private BoolSetting a;
    private ColorSetting c;
    private BoolSetting e;
    private FloatSetting f;
    private BoolSetting g;
    private ColorSetting h;
    private static final int i = 400;
    private static final Identifier j = Identifier.of("minecraft", "textures/item/ender_pearl.png");
    private static FontRenderer k;
    private Map l = new HashMap();
    private Map m = new HashMap();
    private Map n = new HashMap();
    private static final float p = 1.0F;

    public PearlTrajectory() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.c
            .setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL376_0 -> (Boolean)this.d.getValue() && !(Boolean)this.a.getValue(), 119980880492450L});
        this.a.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL377_0 -> (Boolean)this.d.getValue(), 73196168581295L});
        this.g.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL374_0 -> (Boolean)this.e.getValue(), 73196168581295L});
        this.h
            .setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL373_0 -> (Boolean)this.e.getValue() && (Boolean)this.g.getValue(), 119980880492450L});
        this.f.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL375_0 -> (Boolean)this.e.getValue(), 35046756052036L});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            this.n.clear();

            for (Entity var8 : b.world.getEntities()) {
                if (var8 instanceof EnderPearlEntity var9) {
                    this.n.put(var9.getId(), this.a_ArrayList_(new Object[]{var9, 96388204327615L}));
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if (b.world != null && b.player != null) {
            this.l.clear();
            long var11 = System.currentTimeMillis();
            HashSet var13 = new HashSet();

            for (Entity var15 : b.world.getEntities()) {
                if (var15 instanceof EnderPearlEntity var16) {
                    int var17 = var16.getId();
                    var13.add(var17);
                    this.m.putIfAbsent(var17, var11);
                    ArrayList var18 = (ArrayList)this.n.get(var17);
                    if (var18 == null) {
                        var18 = this.a_ArrayList_(new Object[]{var16, 96388204327615L});
                        this.n.put(var17, var18);
                    }

                    if (var18.size() >= 2) {
                        float var19 = (var18.size() - 1) / 20.0F;
                        long var20 = var11 - (Long)this.m.get(var17);
                        float var22 = Math.min(1.0F, (float)var20 / 1000.0F);
                        float var23 = Math.min(1.0F, var19 / 1.0F);
                        float var24 = var22 * var23;
                        if ((Boolean)this.d.getValue() && var24 > 0.01F) {
                            Color var25 = this.a.getValue() ? MathHelper4.d(new Object[]{122780339483702L}) : (Color)this.c.getValue();
                            int var26 = Math.max(0, Math.min(255, (int)(var25.getAlpha() * var24)));
                            Color var27 = new Color(var25.getRed(), var25.getGreen(), var25.getBlue(), var26);
                            RenderState.c(new Object[]{var1.b, var1.a, var18, var27, 78587127157390L});
                        }

                        Vec3d var28 = (Vec3d)var18.get(var18.size() - 1);
                        this.l.put(var17, new PearlTrajectoryHelper(var28, var19, var24));
                    }
                }
            }

            this.m.keySet().retainAll(var13);
        }
    }

    @Subscribe
    public void a(TickEvent25 var1) {
        if ((Boolean)this.e.getValue()) {
            if (this.d(new Object[]{103087567018346L})) {
                if (!this.l.isEmpty()) {
                    boolean var29 = (Boolean)this.g.getValue();
                    Color var30 = var29 ? (Color)this.h.getValue() : null;
                    Font var31 = Prestige.c.a(new Object[]{1236438218049L});
                    float var32 = var31.c_float___(new Object[]{78913221670390L});
                    if (k == null) {
                        k = FontRenderer.d_FontRenderer(new Object[]{j, 15872041225672L});
                    }

                    float var33 = 1.0F;
                    float var34 = 16.0F * var33;
                    float var35 = 3.0F;
                    float var36 = 0.5F;
                    float var37 = 4.0F;
                    float var38 = 3.0F;
                    float var39 = 5.0F;
                    Color var40 = new Color(255, 255, 255, 50);

                    for (PearlTrajectoryHelper var42 : (Iterable<PearlTrajectoryHelper>)(this.l.values())) {
                        if (!(var42.c <= 0.01F)) {
                            Vec3d var43 = MatrixStack.a_class_243_______________(new Object[]{var42.a.add(0.0, 0.6, 0.0), 73023576373275L});
                            if (!(var43.z < 0.0) && !(var43.z >= 1.0)) {
                                String var44 = String.format("%.1fs", var42.b);
                                float var45 = (Float)this.f.getValue();
                                float var46 = var31.a_float______________(new Object[]{var44, 47859522476307L});
                                float var47 = Math.max(var34, var46);
                                float var48 = var34 + var35 + var36 + var35 + var32;
                                float var49 = var47 + 2.0F * var37;
                                float var50 = var48 + 2.0F * var38;
                                float var51 = (float)var43.x / var45;
                                float var52 = (float)var43.y / var45;
                                Matrix4f var53 = new Matrix4f().scale(var45, var45, var45);
                                float var54 = var51 - var49 / 2.0F;
                                float var55 = var52 - var50 / 2.0F;
                                float var56 = var42.c;
                                Color var10000;
                                if (var30 == null) {
                                    var10000 = null;
                                } else {
                                    Object[] var10004 = new Object[]{null, null, 125804553652472L};
                                    var10004[1] = var56;
                                    var10004[0] = var30;
                                    var10000 = a(var10004);
                                }

                                Color var57 = var10000;
                                Object[] var70 = new Object[]{null, null, 125804553652472L};
                                var70[1] = var56;
                                var70[0] = var40;
                                Color var58 = a(var70);
                                Color var59 = new Color(255, 255, 255, (int)(255.0F * var56));
                                if (var29) {
                                    if (var56 > 0.3F) {
                                        RenderHelper8 var67 = var1.a;
                                        MatrixCameraData var10001 = var1.b;
                                        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 35553070502242L};
                                        var10011[8] = var39;
                                        var10011[7] = 5;
                                        var10011[6] = var50;
                                        var10011[5] = var49;
                                        var10011[4] = var55;
                                        var10011[3] = var54;
                                        var10011[2] = var53;
                                        var10011[1] = var10001;
                                        var10011[0] = var67;
                                        RenderTarget.e(var10011);
                                    }

                                    RenderHelper8 var68 = var1.a;
                                    MatrixCameraData var69 = var1.b;
                                    Object[] var73 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
                                    var73[8] = var39;
                                    var73[7] = var57;
                                    var73[6] = var50;
                                    var73[5] = var49;
                                    var73[4] = var55;
                                    var73[3] = var54;
                                    var73[2] = var53;
                                    var73[1] = var69;
                                    var73[0] = var68;
                                    RoundedPosColorShader.c(var73);
                                }

                                float var60 = var51 - var34 / 2.0F;
                                float var61 = var55 + var38;
                                Object[] var10009 = new Object[]{null, null, null, null, var53, k, var59, 73467207830142L};
                                var10009[3] = var34;
                                var10009[2] = var34;
                                var10009[1] = var61;
                                var10009[0] = var60;
                                MatrixStack.v(var10009);
                                float var62 = var54 + var37;
                                float var63 = var54 + var49 - var37;
                                float var64 = var61 + var34 + var35;
                                float var71 = var64 + var36;
                                Object[] var10008 = new Object[]{null, null, null, null, null, var58, 43912076835818L};
                                var10008[4] = var71;
                                var10008[3] = var63;
                                var10008[2] = var64;
                                var10008[1] = var62;
                                var10008[0] = var53;
                                RenderState.l(var10008);
                                float var65 = var51 - var46 / 2.0F;
                                float var66 = var64 + var36 + var35;
                                var10008 = new Object[]{null, null, null, null, var59, 36494305523424L};
                                var10008[3] = var66;
                                var10008[2] = var65;
                                var10008[1] = var44;
                                var10008[0] = var53;
                                var31.v(var10008);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Color a(Object[] var0) {
        Color var3 = (Color)var0[0];
        float var4 = (Float)var0[1];
        int var5 = Math.max(0, Math.min(255, (int)(var3.getAlpha() * var4)));
        return new Color(var3.getRed(), var3.getGreen(), var3.getBlue(), var5);
    }

    private ArrayList a_ArrayList_(Object[] var1) {
        EnderPearlEntity var2 = (EnderPearlEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 85817529300934L ^ var3;
        long var5 = var3 ^ 110538259599995L;
        Event.b();
        ArrayList var8 = new ArrayList();
        dev.zprestige.prestige.combat.PearlTrajectoryHelper var9 = new dev.zprestige.prestige.combat.PearlTrajectoryHelper(
            b.world, b.player, Items.ENDER_PEARL.getDefaultStack(), var5
        );
        var9.setPosition(var2.getX(), var2.getY(), var2.getZ());
        var9.setVelocity(var2.getVelocity());
        int var10 = 0;

        while (!var9.a && var10++ < 400) {
            var8.add(new Vec3d(var9.getX(), var9.getY(), var9.getZ()));
            var9.tick();
        }

        if (var9.a) {
            var8.add(new Vec3d(var9.getX(), var9.getY(), var9.getZ()));
        }

        return var8;
    }

    private boolean d(Object[] var1) {
        return b.currentScreen == null || b.currentScreen instanceof ChatScreen;
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
