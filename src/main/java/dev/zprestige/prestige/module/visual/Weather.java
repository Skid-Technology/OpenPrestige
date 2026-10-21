package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.math.WeatherHelper;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderUtil;
import dev.zprestige.prestige.render.RoundedTextureShader;
import dev.zprestige.prestige.render.TextureUtil;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap.Type;
import org.joml.Matrix4f;
import org.joml.Vector3d;

public class Weather extends Module {
    private EnumSetting a;
    private IntSetting c;
    private IntSetting d;
    private FloatSetting e;
    private FloatSetting f;
    private BoolSetting g;
    private ColorSetting h;
    private static FontRenderer i;
    private ArrayList j = new ArrayList();
    private RenderHelper27 k = new RenderHelper27(26293358099357L);
    private Vec3d l = null;

    public Weather() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.h.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL357_0 -> !(Boolean)this.g.getValue(), 119980880492450L});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 126115243174136L;
        this.j.clear();
        this.k.a_void_____________________(new Object[]{var4});
        this.l = null;
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if (b.player != null && b.world != null) {
            float var17 = Math.min(this.k.a_float__________________(new Object[]{45633363943154L}) / 50.0F, 4.0F);
            this.k.a_void_____________________(new Object[]{93540861878173L});
            String var18 = (String)this.a.getValue();
            int var19 = (Integer)this.c.getValue();
            int var20 = (Integer)this.d.getValue();
            Vec3d var21 = PositionUtil.b(new Object[]{118247215910525L});
            boolean var22 = this.j.isEmpty() || this.l != null && this.l.squaredDistanceTo(var21) > 256.0;
            this.l = var21;
            if (var22) {
                int var23 = 0;

                for (int var24 = var19 * 6; this.j.size() < var19 && var23 < var24; var23++) {
                    Object[] var10006 = new Object[]{null, null, null, 7641560533586L};
                    var10006[2] = var20;
                    var10006[1] = var21;
                    var10006[0] = var18;
                    WeatherHelper var25 = this.a(var10006);
                    if (var25 != null) {
                        this.j.add(var25);
                    }
                }
            } else {
                int var26 = (int)Math.ceil(var17 * Math.max(1, var19 / 20));

                for (int var28 = 0; this.j.size() < var19 && var28 < var26; var28++) {
                    Object[] var32 = new Object[]{null, null, null, 7641560533586L};
                    var32[2] = var20;
                    var32[1] = var21;
                    var32[0] = var18;
                    WeatherHelper var30 = this.a(var32);
                    if (var30 != null) {
                        this.j.add(var30);
                    }
                }
            }

            Iterator var27 = this.j.iterator();

            while (var27.hasNext()) {
                WeatherHelper var29 = (WeatherHelper)var27.next();
                float var10001 = var17 * (Float)this.e.getValue();
                Object[] var10004 = new Object[]{null, 671520226978L};
                var10004[0] = var10001;
                var29.a(var10004);
                if (var29.c > var29.d) {
                    var27.remove();
                } else if (var29.a.squaredDistanceTo(var21) > var20 * 1.6 * (var20 * 1.6)) {
                    var27.remove();
                } else {
                    BlockState var31 = b.world.getBlockState(BlockPos.ofFloored(var29.a));
                    if (!var31.isAir() && !var31.isReplaceable() && var31.getFluidState().isEmpty()) {
                        var27.remove();
                    }
                }
            }

            this.j(new Object[]{var1, var18, 25400547519706L});
        }
    }

    private WeatherHelper a(Object[] var1) {
        String var4 = (String)var1[0];
        Vec3d var2 = (Vec3d)var1[1];
        int var3 = (Integer)var1[2];
        long var5 = (Long)var1[3];
        var5 = 121544606961880L ^ var5;
        long var7 = var5 ^ 66204901888124L;
        Event.b();
        double var10 = Math.random() * Math.PI * 2.0;
        double var12 = Math.sqrt(Math.random()) * var3;
        double var14 = var2.x + Math.cos(var10) * var12;
        double var16 = var2.z + Math.sin(var10) * var12;
        double var18;
        Vec3d var20;
        short var21;
        switch (var4) {
            case "Rain":
                var18 = var2.y - 4.0 + Math.random() * 28.0;
                short var30 = 21973;
                var20 = new Vec3d(0.0, -0.45, 0.0);
                var21 = 200;
                break;
            case "Snow":
                var18 = var2.y - 3.0 + Math.random() * 23.0;
                short var29 = 20256;
                var20 = new Vec3d((Math.random() - 0.5) * 0.02, -0.06, (Math.random() - 0.5) * 0.02);
                var21 = 600;
                break;
            case "Fireflies":
                var18 = var2.y - 1.0 + Math.random() * 6.0;
                short var28 = 31547;
                var20 = new Vec3d((Math.random() - 0.5) * 0.015, (Math.random() - 0.5) * 0.01, (Math.random() - 0.5) * 0.015);
                var21 = 400;
                break;
            case "Embers":
                var18 = var2.y - 4.0 + Math.random() * 12.0;
                short var10000 = 21621;
                var20 = new Vec3d((Math.random() - 0.5) * 0.01, 0.05 + Math.random() * 0.02, (Math.random() - 0.5) * 0.01);
                var21 = 300;
                break;
            default:
                return null;
        }

        Vec3d var22 = new Vec3d(var14, var18, var16);
        if (var4.equals("Rain") || var4.equals("Snow")) {
            int var26 = b.world.getTopY(Type.MOTION_BLOCKING, MathHelper.floor(var14), MathHelper.floor(var16));
            if (var18 < var26) {
                return null;
            }
        }

        BlockState var27 = b.world.getBlockState(BlockPos.ofFloored(var22));
        if (!var27.isAir() && !var27.isReplaceable() && var27.getFluidState().isEmpty()) {
            return null;
        } else {
            WeatherHelper var24 = new WeatherHelper(var22, var20, var21, var7);
            var24.c = (float)(Math.random() * var21 * 0.5);
            return var24;
        }
    }

    private void j(Object[] var1) {
        TickEvent27 var2 = (TickEvent27)var1[0];
        String var3 = (String)var1[1];
        long var4 = (Long)var1[2];
        var4 = 121544606961880L ^ var4;
        long var6 = var4 ^ 66545282385461L;
        long var8 = var4 ^ 125325640790362L;
        long var10 = var4 ^ 25511153347869L;
        long var12 = var4 ^ 24439689758772L;
        long var14 = var4 ^ 105713303031319L;
        long var16 = var4 ^ 19906549461631L;
        if (!this.j.isEmpty()) {
            if (i == null) {
                i = TextureUtil.a(new Object[]{"assets/prestige/particles/glow.png", var6});
            }

            boolean var19 = (Boolean)this.g.getValue();
            Color var20 = var19 ? MathHelper4.d(new Object[]{var12}).darker() : null;
            Color var21 = var19 ? MathHelper4.d(new Object[]{var12}).brighter() : null;
            Color var22 = var19 ? null : (Color)this.h.getValue();
            float var23 = (Float)this.f.getValue();
            boolean var26 = true;
            float var24;
            float var25;
            switch (var3) {
                case "Rain":
                    var24 = 0.5F;
                    var25 = 4.5F;
                    var26 = false;
                    break;
                case "Snow":
                    var24 = 1.6F;
                    var25 = 1.6F;
                    break;
                case "Fireflies":
                    var24 = 2.5F;
                    var25 = 2.5F;
                    break;
                case "Embers":
                    var24 = 1.4F;
                    var25 = 1.4F;
                    break;
                default:
                    var24 = 2.0F;
                    var25 = 2.0F;
            }

            var24 *= var23;
            var25 *= var23;
            Vec3d var27 = PositionUtil.b(new Object[]{var16});
            int var43 = (Integer)this.d.getValue();

            for (WeatherHelper var30 : (Iterable<WeatherHelper>)(new ArrayList(this.j))) {
                double var31 = var30.a.distanceTo(var27);
                if (!(var31 > var43 * 1.5)) {
                    float var33 = var30.c / var30.d;
                    float var34;
                    if (var33 < 0.1F) {
                        var34 = var33 / 0.1F;
                    } else if (var33 > 0.7F) {
                        var34 = (1.0F - var33) / 0.3F;
                    } else {
                        var34 = 1.0F;
                    }

                    if (var3.equals("Fireflies")) {
                        var34 *= 0.35F + 0.65F * (float)(Math.sin(var30.c * 0.12 + var30.e) * 0.5 + 0.5);
                    } else if (var3.equals("Embers")) {
                        var34 *= 0.6F + 0.4F * (float)(Math.sin(var30.c * 0.4 + var30.e) * 0.5 + 0.5);
                    }

                    int var35 = MathHelper.clamp((int)(255.0F * var34), 0, 255);
                    Color var10000;
                    if (var19) {
                        float var10002 = var30.f;
                        Object[] var10005 = new Object[]{null, null, null, var14};
                        var10005[2] = var10002;
                        var10005[1] = var21;
                        var10005[0] = var20;
                        var10000 = a_Color_______(var10005);
                    } else {
                        var10000 = var22;
                    }

                    Color var36 = var10000;
                    Color var37 = new Color(var36.getRed(), var36.getGreen(), var36.getBlue(), var35);
                    MatrixCameraData var44 = var2.b;
                    double var10001 = var30.a.x;
                    double var47 = var30.a.y;
                    double var10003 = var30.a.z;
                    Object[] var10006 = new Object[]{null, null, null, null, var10};
                    var10006[3] = var10003;
                    var10006[2] = var47;
                    var10006[1] = var10001;
                    var10006[0] = var44;
                    Vector3d var38 = RenderUtil.a_Vector3d(var10006);
                    Matrix4f var39 = new Matrix4f();
                    var39.translate((float)var38.x, (float)var38.y, (float)var38.z);
                    var39.scale(0.04F);
                    var39.rotateY((float)Math.toRadians(-var2.b.bC().getYaw()));
                    if (var26) {
                        var39.rotateX((float)Math.toRadians(var2.b.bC().getPitch()));
                    }

                    RenderHelper8 var45 = var2.a;
                    MatrixCameraData var46 = var2.b;
                    FontRenderer var48 = i;
                    Object[] var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var8};
                    var10014[11] = 0.0F;
                    var10014[10] = var37;
                    var10014[9] = 0.0F;
                    var10014[8] = var25;
                    var10014[7] = var24;
                    var10014[6] = 0.0F;
                    var10014[5] = 0.0F;
                    var10014[4] = 0.0F;
                    var10014[3] = var48;
                    var10014[2] = var39;
                    var10014[1] = var46;
                    var10014[0] = var45;
                    RoundedTextureShader.m(var10014);
                }
            }
        }
    }

    private static Color a_Color_______(Object[] var0) {
        Color var5 = (Color)var0[0];
        Color var4 = (Color)var0[1];
        float var1 = (Float)var0[2];
        var1 = MathHelper.clamp(var1, 0.0F, 1.0F);
        float var6 = 1.0F - var1;
        int var7 = (int)(var5.getRed() * var6 + var4.getRed() * var1);
        int var8 = (int)(var5.getGreen() * var6 + var4.getGreen() * var1);
        int var9 = (int)(var5.getBlue() * var6 + var4.getBlue() * var1);
        return new Color(MathHelper.clamp(var7, 0, 255), MathHelper.clamp(var8, 0, 255), MathHelper.clamp(var9, 0, 255));
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
