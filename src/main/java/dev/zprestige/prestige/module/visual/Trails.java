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
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.math.TrailsHelper;
import dev.zprestige.prestige.math.TrailsHelper2;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderUtil;
import dev.zprestige.prestige.render.RoundedTextureShader;
import dev.zprestige.prestige.render.TextureUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3d;

public class Trails extends Module {
    private BoolSetting d;
    private EnumSetting a;
    private IntSetting c;
    private FloatSetting e;
    private BoolSetting f;
    private ColorSetting g;
    private static FontRenderer h;
    private static final int i = 4;
    private Map j = new HashMap();
    private List k = new ArrayList();

    public Trails() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.g.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL335_0 -> !(Boolean)this.f.getValue(), 119980880492450L});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            HashSet var7 = new HashSet();
            String var8 = (String)this.a.getValue();

            double var9 = switch (var8) {
                case "Particles" -> 0.25;
                case "Ring" -> 0.65;
                case "Footsteps" -> 0.75;
                default -> 0.5;
            };
            int var11 = (Integer)this.c.getValue();

            for (PlayerEntity var13 : b.world.getPlayers()) {
                int var14 = var13.getId();
                var7.add(var14);
                if ((!(Boolean)this.d.getValue() || var13 == b.player) && var13.isOnGround()) {
                    TrailsHelper var15 = (TrailsHelper)this.j.get(var14);
                    Vec3d var16 = new Vec3d(var13.getX(), var13.getY(), var13.getZ());
                    if (var15 == null) {
                        var15 = new TrailsHelper();
                        var15.a = var16;
                        this.j.put(var14, var15);
                    } else {
                        double var17 = var16.x - var15.a.x;
                        double var19 = var16.z - var15.a.z;
                        double var21 = Math.sqrt(var17 * var17 + var19 * var19);
                        if (!(var21 < var9)) {
                            float var23 = var13.getYaw();
                            switch (var8) {
                                case "Footsteps":
                                    boolean var38 = var15.b;
                                    double var39 = Math.toRadians(var23);
                                    double var40 = 0.18;
                                    double var41 = Math.cos(var39) * var40 * (var38 ? -1 : 1);
                                    double var33 = Math.sin(var39) * var40 * (var38 ? -1 : 1);
                                    Vec3d var35 = new Vec3d(var16.x + var41, var16.y, var16.z + var33);
                                    this.k.add(new TrailsHelper2(var35, var8, var11, var23, var38, null, 138117324316801L));
                                    var15.b = !var38;
                                    break;
                                case "Particles":
                                    for (int var26 = 0; var26 < 4; var26++) {
                                        double var27 = Math.random() * Math.PI * 2.0;
                                        double var29 = 0.025 + Math.random() * 0.05;
                                        Vec3d var31 = new Vec3d(Math.cos(var27) * var29, 0.05 + Math.random() * 0.05, Math.sin(var27) * var29);
                                        this.k.add(new TrailsHelper2(var16, var8, var11, var23, false, var31, 138117324316801L));
                                    }
                                    break;
                                default:
                                    this.k.add(new TrailsHelper2(var16, var8, var11, var23, false, null, 138117324316801L));
                            }

                            var15.a = var16;
                        }
                    }
                }
            }

            this.j.keySet().retainAll(var7);
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        long var19 = System.currentTimeMillis();
        Event.b();
        this.k.removeIf(lpL334_0 -> var19 - ((dev.zprestige.prestige.math.TrailsHelper2)lpL334_0).b >= ((dev.zprestige.prestige.math.TrailsHelper2)lpL334_0).c);
        if (!this.k.isEmpty()) {
            Color var21 = this.f.getValue() ? MathHelper4.d(new Object[]{122780339483702L}) : (Color)this.g.getValue();
            Color var22 = var21.darker();
            Color var23 = var21.brighter();
            float var24 = (Float)this.e.getValue();

            for (TrailsHelper2 var26 : (Iterable<TrailsHelper2>)(this.k)) {
                float var27 = (float)(var19 - var26.b) / var26.c;
                if (!(var27 < 0.0F) && !(var27 > 1.0F)) {
                    Object[] var10004 = new Object[]{null, 28503932810326L};
                    var10004[0] = var27;
                    float var28 = 1.0F - b_float_________________(var10004);
                    Color var29;
                    Color var30;
                    if (var26.d.equals("Footsteps")) {
                        var29 = var21;
                        var10004 = new Object[]{null, null, 49996634410410L};
                        var10004[1] = 0.3F;
                        var10004[0] = var21;
                        var30 = b_Color___(var10004);
                    } else {
                        float var10002 = var26.i;
                        Object[] var10005 = new Object[]{null, null, null, 89203639344671L};
                        var10005[2] = var10002;
                        var10005[1] = var23;
                        var10005[0] = var22;
                        var29 = c_Color_(var10005);
                        var10005 = new Object[]{null, null, null, 89203639344671L};
                        var10005[2] = 0.6F;
                        var10005[1] = var23;
                        var10005[0] = var29;
                        var30 = c_Color_(var10005);
                    }

                    String var31 = var26.d;
                    switch (var31) {
                        case "Footsteps":
                            Object[] var35 = new Object[]{null, null, null, null, null, null, 41713175379068L};
                            var35[5] = var24;
                            var35[4] = var28;
                            var35[3] = var30;
                            var35[2] = var29;
                            var35[1] = var26;
                            var35[0] = var1;
                            this.j(var35);
                            break;
                        case "Ring":
                            Object[] var10010 = new Object[]{null, null, null, null, null, null, null, 29073417628285L};
                            var10010[6] = var27;
                            var10010[5] = var24;
                            var10010[4] = var28;
                            var10010[3] = var30;
                            var10010[2] = var29;
                            var10010[1] = var26;
                            var10010[0] = var1;
                            this.k(var10010);
                            break;
                        case "Particles":
                            Object[] var10009 = new Object[]{null, null, null, null, null, null, 52348321139318L};
                            var10009[5] = var27;
                            var10009[4] = var24;
                            var10009[3] = var28;
                            var10009[2] = var29;
                            var10009[1] = var26;
                            var10009[0] = var1;
                            this.l(var10009);
                    }
                }
            }
        }
    }

    private void j(Object[] var1) {
        TickEvent27 var3 = (TickEvent27)var1[0];
        TrailsHelper2 var4 = (TrailsHelper2)var1[1];
        Color var2 = (Color)var1[2];
        Color var6 = (Color)var1[3];
        float var5 = (Float)var1[4];
        float var7 = (Float)var1[5];
        long var8 = (Long)var1[6];
        var8 = 3784339827904L ^ var8;
        long var10 = var8 ^ 78557536447428L;
        long var12 = var8 ^ 51590760504804L;
        long var14 = var8 ^ 79317468135843L;
        long var16 = var8 ^ 33038936377119L;
        n(new Object[]{var16});
        Vec3d var18 = var4.a.add(0.0, 0.02, 0.0);
        MatrixCameraData var10000 = var3.b;
        double var10001 = var18.x;
        double var10002 = var18.y;
        double var10003 = var18.z;
        Object[] var10006 = new Object[]{null, null, null, null, var14};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var10000;
        Vector3d var19 = RenderUtil.a_Vector3d(var10006);
        Matrix4f var20 = new Matrix4f();
        var20.translate((float)var19.x, (float)var19.y, (float)var19.z);
        var20.rotateY(-var4.e);
        var20.rotateX((float)Math.toRadians(90.0));
        var20.scale(0.04F);
        float var21 = 4.0F * var7;
        float var22 = 7.5F * var7;
        float var23 = var21 * 1.55F;
        float var24 = var22 * 1.4F;
        RenderHelper8 var26 = var3.a;
        MatrixCameraData var28 = var3.b;
        FontRenderer var30 = h;
        float var10004 = -var23;
        float var10005 = -var24;
        float var10011 = var5 * 0.45F;
        Object[] var10014 = new Object[]{null, null, var10};
        var10014[1] = var10011;
        var10014[0] = var2;
        Color var10010 = a(var10014);
        var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var12};
        var10014[11] = 0.0F;
        var10014[10] = var10010;
        var10014[9] = 0.0F;
        var10014[8] = var24;
        var10014[7] = var23;
        var10014[6] = 0.0F;
        var10014[5] = var10005;
        var10014[4] = var10004;
        var10014[3] = var30;
        var10014[2] = var20;
        var10014[1] = var28;
        var10014[0] = var26;
        RoundedTextureShader.m(var10014);
        RenderHelper8 var27 = var3.a;
        MatrixCameraData var29 = var3.b;
        FontRenderer var31 = h;
        var10004 = -var21;
        var10005 = -var22;
        var10014 = new Object[]{null, null, var10};
        var10014[1] = var5;
        var10014[0] = var6;
        var10010 = a(var10014);
        var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var12};
        var10014[11] = 0.0F;
        var10014[10] = var10010;
        var10014[9] = 0.0F;
        var10014[8] = var22;
        var10014[7] = var21;
        var10014[6] = 0.0F;
        var10014[5] = var10005;
        var10014[4] = var10004;
        var10014[3] = var31;
        var10014[2] = var20;
        var10014[1] = var29;
        var10014[0] = var27;
        RoundedTextureShader.m(var10014);
    }

    private void k(Object[] var1) {
        TickEvent27 var6 = (TickEvent27)var1[0];
        TrailsHelper2 var2 = (TrailsHelper2)var1[1];
        Color var7 = (Color)var1[2];
        Color var8 = (Color)var1[3];
        float var10 = (Float)var1[4];
        float var5 = (Float)var1[5];
        float var9 = (Float)var1[6];
        long var3 = (Long)var1[7];
        var3 = 3784339827904L ^ var3;
        long var11 = var3 ^ 132988020407749L;
        long var13 = var3 ^ 92377531242007L;
        float var15 = (0.05F + 0.55F * c(new Object[]{var9})) * var5;
        MatrixCameraData var10000 = var6.b;
        RenderHelper8 var10001 = var6.a;
        float var10002 = (float)var2.a.x;
        float var10003 = (float)var2.a.y + 0.01F;
        float var10004 = (float)var2.a.z;
        Object[] var10012 = new Object[]{null, null, var11};
        var10012[1] = var10;
        var10012[0] = var8;
        Color var10008 = a(var10012);
        Object[] var10013 = new Object[]{null, null, var11};
        var10013[1] = var10;
        var10013[0] = var7;
        var10012 = new Object[]{null, null, null, null, null, null, null, null, var10008, a(var10013), var13};
        var10012[7] = 0.12F;
        var10012[6] = 0.018F;
        var10012[5] = var15;
        var10012[4] = var10004;
        var10012[3] = var10003;
        var10012[2] = var10002;
        var10012[1] = var10001;
        var10012[0] = var10000;
        RenderState.f(var10012);
    }

    private void l(Object[] var1) {
        TickEvent27 var8 = (TickEvent27)var1[0];
        TrailsHelper2 var4 = (TrailsHelper2)var1[1];
        Color var2 = (Color)var1[2];
        float var3 = (Float)var1[3];
        float var7 = (Float)var1[4];
        float var9 = (Float)var1[5];
        long var5 = (Long)var1[6];
        var5 = 3784339827904L ^ var5;
        long var10 = var5 ^ 84794635181518L;
        long var12 = var5 ^ 49976521378908L;
        long var14 = var5 ^ 22403254530325L;
        n(new Object[]{var14});
        float var16 = var4.c * var9 / 50.0F;
        Vec3d var17 = var4.g.multiply(var16);
        double var18 = 9.0E-4 * var16 * var16;
        Vec3d var20 = var4.a.add(var17.x, var17.y - var18 + 0.05, var17.z);
        float var21 = (1.6F - 0.5F * var9) * 2.5F * var7;
        Object[] var10008 = new Object[]{null, null, var10};
        var10008[1] = var3;
        var10008[0] = var2;
        Object[] var10007 = new Object[]{null, null, null, a(var10008), var12};
        var10007[2] = var21;
        var10007[1] = var20;
        var10007[0] = var8;
        this.m(var10007);
    }

    private void m(Object[] var1) {
        TickEvent27 var4 = (TickEvent27)var1[0];
        Vec3d var2 = (Vec3d)var1[1];
        float var7 = (Float)var1[2];
        Color var3 = (Color)var1[3];
        long var5 = (Long)var1[4];
        var5 = 3784339827904L ^ var5;
        long var8 = var5 ^ 11556483287922L;
        long var10 = var5 ^ 119068663453493L;
        if (var3.getAlpha() > 0) {
            MatrixCameraData var10000 = var4.b;
            double var10001 = var2.x;
            double var10002 = var2.y;
            double var10003 = var2.z;
            Object[] var10006 = new Object[]{null, null, null, null, var10};
            var10006[3] = var10003;
            var10006[2] = var10002;
            var10006[1] = var10001;
            var10006[0] = var10000;
            Vector3d var12 = RenderUtil.a_Vector3d(var10006);
            Matrix4f var13 = new Matrix4f();
            var13.translate((float)var12.x, (float)var12.y, (float)var12.z);
            var13.scale(0.04F);
            var13.rotateY((float)Math.toRadians(-var4.b.bC().getYaw()));
            var13.rotateX((float)Math.toRadians(var4.b.bC().getPitch()));
            RenderHelper8 var15 = var4.a;
            MatrixCameraData var16 = var4.b;
            FontRenderer var17 = h;
            Object[] var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var8};
            var10014[11] = 0.0F;
            var10014[10] = var3;
            var10014[9] = 0.0F;
            var10014[8] = var7;
            var10014[7] = var7;
            var10014[6] = 0.0F;
            var10014[5] = 0.0F;
            var10014[4] = 0.0F;
            var10014[3] = var17;
            var10014[2] = var13;
            var10014[1] = var16;
            var10014[0] = var15;
            RoundedTextureShader.m(var10014);
        }
    }

    private static void n(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 3784339827904L ^ var1;
        long var3 = var1 ^ 139538915246420L;
        if (h == null) {
            h = TextureUtil.a(new Object[]{"assets/prestige/particles/glow.png", var3});
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        this.k.clear();
        this.j.clear();
    }

    private static float b_float_________________(Object[] var0) {
        float var1 = (Float)var0[0];
        if (var1 <= 0.0F) {
            return 0.0F;
        } else {
            return var1 >= 1.0F ? 1.0F : var1 * var1 * var1 * (var1 * (var1 * 6.0F - 15.0F) + 10.0F);
        }
    }

    private static float c(Object[] var0) {
        float var1 = (Float)var0[0];
        return 1.0F - (1.0F - var1) * (1.0F - var1);
    }

    private static Color a(Object[] var0) {
        Color var4 = (Color)var0[0];
        float var3 = (Float)var0[1];
        int var5 = Math.max(0, Math.min(255, (int)(var4.getAlpha() * var3)));
        return new Color(var4.getRed(), var4.getGreen(), var4.getBlue(), var5);
    }

    private static Color b_Color___(Object[] var0) {
        Color var4 = (Color)var0[0];
        float var3 = (Float)var0[1];
        long var1 = (Long)var0[2];
        var1 = 3784339827904L ^ var1;
        long var5 = var1 ^ 48320050385056L;
        if (var3 < 0.0F) {
            var3 = 0.0F;
        }

        if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        int var8 = (int)(var4.getRed() + (255 - var4.getRed()) * var3);
        int var9 = (int)(var4.getGreen() + (255 - var4.getGreen()) * var3);
        int var10 = (int)(var4.getBlue() + (255 - var4.getBlue()) * var3);
        Object[] var10005 = new Object[]{null, var5};
        var10005[0] = var8;
        int var10002 = a_int________________(var10005);
        Object[] var10006 = new Object[]{null, var5};
        var10006[0] = var9;
        int var10003 = a_int________________(var10006);
        Object[] var10007 = new Object[]{null, var5};
        var10007[0] = var10;
        return new Color(var10002, var10003, a_int________________(var10007), var4.getAlpha());
    }

    private static Color c_Color_(Object[] var0) {
        Color var1 = (Color)var0[0];
        Color var4 = (Color)var0[1];
        float var5 = (Float)var0[2];
        long var2 = (Long)var0[3];
        var2 = 3784339827904L ^ var2;
        long var6 = var2 ^ 96394298671893L;
        if (var5 < 0.0F) {
            var5 = 0.0F;
        }

        if (var5 > 1.0F) {
            var5 = 1.0F;
        }

        float var9 = 1.0F - var5;
        int var10 = (int)(var1.getRed() * var9 + var4.getRed() * var5);
        int var11 = (int)(var1.getGreen() * var9 + var4.getGreen() * var5);
        int var12 = (int)(var1.getBlue() * var9 + var4.getBlue() * var5);
        int var13 = (int)(var1.getAlpha() * var9 + var4.getAlpha() * var5);
        Object[] var10005 = new Object[]{null, var6};
        var10005[0] = var10;
        int var10002 = a_int________________(var10005);
        Object[] var10006 = new Object[]{null, var6};
        var10006[0] = var11;
        int var10003 = a_int________________(var10006);
        Object[] var10007 = new Object[]{null, var6};
        var10007[0] = var12;
        int var10004 = a_int________________(var10007);
        Object[] var10008 = new Object[]{null, var6};
        var10008[0] = var13;
        return new Color(var10002, var10003, var10004, a_int________________(var10008));
    }

    private static int a_int________________(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 < 0 ? 0 : (var3 > 255 ? 255 : var3);
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
