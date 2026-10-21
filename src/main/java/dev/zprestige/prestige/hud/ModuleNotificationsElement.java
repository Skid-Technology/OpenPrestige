package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.RenderHelper18;
import dev.zprestige.prestige.render.RenderHelper20;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import dev.zprestige.prestige.render.RoundedTextureShader;
import dev.zprestige.prestige.render.TextureUtil;
import dev.zprestige.prestige.type.NotificationType;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.joml.Matrix4f;

public class ModuleNotificationsElement extends HudElement {
    public static ModuleNotificationsElement a = new ModuleNotificationsElement(0L);
    private static final int d = 5;
    private static final float m = 10.0F;
    private static final float i = 90.0F;
    private static final float j = 12.0F;
    private final ArrayList k;
    private final Map l;
    private long n;
    private float o;
    private float p;

    public ModuleNotificationsElement(long var1) {
        super("Module Notifications", "Draws module notifications when toggled.", (90461679030534L));
        var1 = 0L;
        var1 = 53258369047921L ^ var1;
        long var3 = var1 ^ 107985914662007L;
        this.k = new ArrayList();
        this.l = new HashMap();
        this.n = 0L;
        this.o = 0.0F;
        this.p = 0.0F;
        a = this;
    }

    public static void r(Object[] var0) {
        Module var3 = (Module)var0[0];
        long var1 = (Long)var0[1];
        var1 = 53258369047921L ^ var1;
        long var4 = var1 ^ 128612267696415L;
        long var6 = var1 ^ 110479586714630L;
        if (a.e_boolean(new Object[]{var4})) {
            if (var3.isEnabled()) {
                a.t(new Object[]{var3.getName(), "Has been enabled.", NotificationType.SUCCESS, var6});
            } else {
                a.t(new Object[]{var3.getName(), "Has been disabled.", NotificationType.ERROR, var6});
            }
        }
    }

    public static void s(Object[] var0) {
        Module var1 = (Module)var0[0];
        String var5 = (String)var0[1];
        NotificationType var4 = (NotificationType)var0[2];
        long var2 = (Long)var0[3];
        var2 = 53258369047921L ^ var2;
        long var6 = var2 ^ 97481105155834L;
        long var8 = var2 ^ 79351645725667L;
        if (a.e_boolean(new Object[]{var6})) {
            a.t(new Object[]{var1.getName(), var5, var4, var8});
        }
    }

    private void t(Object[] var1) {
        String var4 = (String)var1[0];
        String var3 = (String)var1[1];
        NotificationType var2 = (NotificationType)var1[2];
        long var5 = (Long)var1[3];
        var5 = 53258369047921L ^ var5;
        long var7 = var5 ^ 5962095113102L;
        Event.b();

        for (RenderHelper20 var11 : (Iterable<RenderHelper20>)(this.k)) {
            if (var11.a.equals(var4) && !var11.f) {
                var11.f = true;
            }
        }

        this.k.add(new RenderHelper20(var4, var3, var2, var7));
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 111341298698524L;
        long var6 = var2 ^ 115520954206759L;
        if (b.getWindow() != null) {
            float var9 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var4});
            float var10 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var4});
            float var10001 = var9 - 90.0F - 4.0F;
            float var10002 = var10 - 70.0F;
            Object[] var10005 = new Object[]{null, null, var6};
            var10005[1] = var10002;
            var10005[0] = var10001;
            this.a_void_______(var10005);
        } else {
            Object[] var11 = new Object[]{null, null, var6};
            var11[1] = 150.0F;
            var11[0] = 200.0F;
            this.a_void_______(var11);
        }
    }

    @Override
    public void q(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        long var3 = (Long)var1[1];
        if (this.g <= 0.0F || this.h <= 0.0F) {
            return;
        }

        super.q(new Object[]{var2, var3});
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var5 = (RenderHelper8)var1[0];
        MatrixCameraData var2 = (MatrixCameraData)var1[1];
        Matrix4f var6 = (Matrix4f)var1[2];
        long var3 = (Long)var1[3];
        long var7 = var3 ^ 48964852101235L;
        long var9 = var3 ^ 95334181894420L;
        long var11 = var3 ^ 54030867045760L;
        long var13 = var3 ^ 104134194455071L;
        long var15 = var3 ^ 33668147527298L;
        long var17 = var3 ^ 93148276620004L;
        long var19 = var3 ^ 132449650111663L;
        long var21 = var3 ^ 78957407708937L;
        long var23 = var3 ^ 115271282653595L;
        long var25 = var3 ^ 8572711703332L;
        long var27 = var3 ^ 89135395443612L;
        long var29 = var3 ^ 102693909475685L;
        long var31 = var3 ^ 6014253680539L;
        long var33 = var3 ^ 131934975778103L;
        long var35 = var3 ^ 19313467402120L;
        long var37 = var3 ^ 101037798966850L;
        long var39 = var3 ^ 26124314106062L;
        Event.b();
        long var42 = System.currentTimeMillis();
        float var44 = this.n == 0L ? 0.0F : (float)(var42 - this.n) * 0.005F;
        this.n = var42;
        boolean var45 = b.currentScreen instanceof HudEditorScreen;
        Iterator var46 = this.k.iterator();

        while (var46.hasNext()) {
            RenderHelper20 var47 = (RenderHelper20)var46.next();
            if (!var47.f && var42 - var47.d >= 2000L) {
                var47.f = true;
            }

            float var10001 = var47.e;
            float var10002 = var47.f ? 0.0F : 1.0F;
            float var10003 = var47.f ? var44 * 2.0F : var44;
            Object[] var10006 = new Object[]{null, null, null, var31};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var47.e = MathHelper4.b_float______(var10006);
            if (var47.f && var47.e <= 0.01F) {
                var46.remove();
            }
        }

        int var79 = 0;

        for (RenderHelper20 var49 : (Iterable<RenderHelper20>)(this.k)) {
            if (!var49.f) {
                var79++;
            }
        }

        for (RenderHelper20 var82 : (Iterable<RenderHelper20>)(this.k)) {
            if (var79 <= 5) {
                break;
            }

            if (!var82.f) {
                var82.f = true;
                var79--;
            }
        }

        ArrayList var81 = new ArrayList(this.k);
        if (var45 && var81.isEmpty()) {
            RenderHelper20 var83 = new RenderHelper20("Module Notifications", "Toasts appear here.", NotificationType.SUCCESS, var13);
            var83.e = 1.0F;
            var81.add(var83);
        }

        if (var81.isEmpty()) {
            Object[] var114 = new Object[]{null, 0.0F};
            var114[0] = 0.0F;
            this.i(var114);
            this.o = 0.0F;
            this.p = 0.0F;
        } else {
            float var10000 = b.getWindow().getFramebufferWidth();
            HudEditorScreen.b(new Object[]{var7});
            var10000 = b.getWindow().getFramebufferHeight();
            HudEditorScreen.b(new Object[]{var7});
            boolean var51 = this.c_boolean__(new Object[0]);
            boolean var52 = this.d_boolean(new Object[0]);
            float var53 = Prestige.c.a(new Object[]{var33}).c_float___(new Object[]{var11});
            float var54 = var53 * 1.65F + 6.0F;
            float var55 = 0.0F;
            float var56 = 0.0F;

            for (RenderHelper20 var58 : (Iterable<RenderHelper20>)(var81)) {
                var55 = Math.max(var55, this.c(new Object[]{var58, var23}));
                var56 += (var54 + 3.0F) * var58.e;
            }

            if (!var45) {
                if (var52 && this.o > 0.0F) {
                    this.f = this.f - (var56 - this.o);
                }

                if (var51 && this.p > 0.0F) {
                    this.e = this.e - (var55 - this.p);
                }
            }

            this.o = var56;
            this.p = var55;
            Object[] var10004 = new Object[]{null, var56};
            var10004[0] = var55;
            this.i(var10004);
            float var84 = var52 ? this.f + var56 - var54 : this.f;

            for (int var85 = var81.size() - 1; var85 >= 0; var85--) {
                RenderHelper20 var59 = (RenderHelper20)var81.get(var85);
                float var60 = var59.e;
                float var61 = this.c(new Object[]{var59, var23});
                Color var62 = a_Color(new Object[]{var59.c, var39});
                float var63 = (1.0F - var60) * 12.0F;
                float var64 = var51 ? this.e + var55 - var61 + var63 : this.e - var63;
                float var65 = var64 + var61;
                int var96 = NameTagsUtil.r.getRed();
                int var102 = NameTagsUtil.r.getGreen();
                int var106 = NameTagsUtil.r.getBlue();
                int var10005 = (int)(NameTagsUtil.r.getAlpha() * var60);
                Object[] var10008 = new Object[]{null, var15};
                var10008[0] = var10005;
                Color var66 = new Color(var96, var102, var106, a_int____(var10008));
                int var92 = (int)(230.0F * var60);
                var10004 = new Object[]{null, null, var25};
                var10004[1] = var92;
                var10004[0] = var62;
                Color var67 = b(var10004);
                var10005 = (int)(255.0F * var60);
                var10008 = new Object[]{null, var15};
                var10008[0] = var10005;
                Color var68 = new Color(255, 255, 255, a_int____(var10008));
                var96 = NameTagsUtil.u.getRed();
                var102 = NameTagsUtil.u.getGreen();
                int var108 = NameTagsUtil.u.getBlue();
                var10005 = (int)(255.0F * var60);
                var10008 = new Object[]{null, var15};
                var10008[0] = var10005;
                Color var69 = new Color(var96, var102, var108, a_int____(var10008));
                if (var60 > 0.7F) {
                    Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
                    var10011[8] = 4.0F;
                    var10011[7] = 5;
                    var10011[6] = var54;
                    var10011[5] = var61;
                    var10011[4] = var84;
                    var10011[3] = var64;
                    var10011[2] = var6;
                    var10011[1] = var2;
                    var10011[0] = var5;
                    RenderTarget.e(var10011);
                }

                Object[] var124 = new Object[]{null, null, null, null, null, null, null, null, null, var19};
                var124[8] = 4.0F;
                var124[7] = var66;
                var124[6] = var54;
                var124[5] = var61;
                var124[4] = var84;
                var124[3] = var64;
                var124[2] = var6;
                var124[1] = var2;
                var124[0] = var5;
                RoundedPosColorShader.c(var124);
                FontRenderer var70 = this.a(new Object[]{var59.c, var21});
                float var71 = var51 ? var65 - 5.5F - 10.0F : var64 + 5.5F;
                float var117 = var84 + (var54 - 10.0F) / 2.0F;
                var124 = new Object[]{null, null, null, null, null, null, null, null, var67, var35};
                var124[7] = 10.0F;
                var124[6] = 10.0F;
                var124[5] = var117;
                var124[4] = var71;
                var124[3] = var70;
                var124[2] = var6;
                var124[1] = var2;
                var124[0] = var5;
                RoundedTextureShader.c(var124);
                float var72 = Prestige.c.a(new Object[]{var33}).a_float______________(new Object[]{var59.a, var29}) * 0.9F;
                float var73 = Prestige.c.a(new Object[]{var33}).a_float______________(new Object[]{var59.b, var29}) * 0.75F;
                float var74 = var51 ? var71 - 3.0F - var72 : var71 + 10.0F + 3.0F;
                float var75 = var51 ? var71 - 3.0F - var73 : var71 + 10.0F + 3.0F;
                Font var88 = Prestige.c.a(new Object[]{var33});
                String var98 = var59.a;
                float var109 = var84 + 3.0F;
                Object[] var10009 = new Object[]{null, null, null, null, null, var68, var37};
                var10009[4] = 0.9F;
                var10009[3] = var109;
                var10009[2] = var74;
                var10009[1] = var98;
                var10009[0] = var6;
                var88.w(var10009);
                Font var89 = Prestige.c.a(new Object[]{var33});
                String var99 = var59.b;
                float var110 = var84 + 3.0F + var53 * 0.9F;
                var10009 = new Object[]{null, null, null, null, null, var69, var37};
                var10009[4] = 0.75F;
                var10009[3] = var110;
                var10009[2] = var75;
                var10009[1] = var99;
                var10009[0] = var6;
                var89.w(var10009);
                if (var59.f) {
                    var10000 = 0.0F;
                } else {
                    var10000 = 1.0F - (float)(var42 - var59.d) / 2000.0F;
                    Object[] var118 = new Object[]{null, null, null, var17};
                    var118[2] = 1.0F;
                    var118[1] = 0.0F;
                    var118[0] = var10000;
                    var10000 = MathHelper4.a(var118);
                }

                float var76 = var10000;
                float var77 = (var61 - 11.0F) * var76;
                if (var77 > 0.5F) {
                    var92 = (int)(150.0F * var60);
                    var10004 = new Object[]{null, null, var25};
                    var10004[1] = var92;
                    var10004[0] = var62;
                    Color var78 = b(var10004);
                    if (var51) {
                        float var94 = var65 - 5.5F - var77;
                        float var100 = var84 + var54 - 3.0F;
                        float var104 = var65 - 5.5F;
                        float var112 = var84 + var54 - 2.0F;
                        var10008 = new Object[]{null, null, null, null, null, var78, var27};
                        var10008[4] = var112;
                        var10008[3] = var104;
                        var10008[2] = var100;
                        var10008[1] = var94;
                        var10008[0] = var6;
                        RenderState.l(var10008);
                    } else {
                        float var95 = var64 + 5.5F;
                        float var101 = var84 + var54 - 3.0F;
                        float var105 = var64 + 5.5F + var77;
                        float var113 = var84 + var54 - 2.0F;
                        var10008 = new Object[]{null, null, null, null, null, var78, var27};
                        var10008[4] = var113;
                        var10008[3] = var105;
                        var10008[2] = var101;
                        var10008[1] = var95;
                        var10008[0] = var6;
                        RenderState.l(var10008);
                    }
                }

                float var86 = (var54 + 3.0F) * var60;
                if (var52) {
                    var84 -= var86;
                } else {
                    var84 += var86;
                }
            }
        }
    }

    private float c(Object[] var1) {
        RenderHelper20 var4 = (RenderHelper20)var1[0];
        long var2 = (Long)var1[1];
        var2 = 53258369047921L ^ var2;
        long var5 = var2 ^ 6325548307855L;
        long var7 = var2 ^ 52055014947293L;
        float var9 = Prestige.c.a(new Object[]{var7}).a_float______________(new Object[]{var4.a, var5}) * 0.9F;
        float var10 = Prestige.c.a(new Object[]{var7}).a_float______________(new Object[]{var4.b, var5}) * 0.75F;
        float var11 = Math.max(var9, var10);
        return Math.max(90.0F, 18.5F + var11 + 5.5F);
    }

    private FontRenderer a(Object[] var1) {
        NotificationType var4 = (NotificationType)var1[0];
        long var2 = (Long)var1[1];
        var2 = 53258369047921L ^ var2;
        long var5 = var2 ^ 75086570088505L;
        Event.b();
        FontRenderer var8 = (FontRenderer)this.l.get(var4);
        if (var8 == null) {
            var8 = TextureUtil.a(new Object[]{"assets/prestige/icons/notifications/" + var4.name().toLowerCase() + ".png", var5});
            this.l.put(var4, var8);
        }

        return var8;
    }

    private static Color a_Color(Object[] var0) {
        NotificationType var1 = (NotificationType)var0[0];
        switch (RenderHelper18.a[var1.ordinal()]) {
            case 1:
                return new Color(76, 175, 80);
            case 2:
                return new Color(244, 67, 54);
            case 3:
                return new Color(255, 152, 0);
            case 4:
                return new Color(33, 150, 243);
            default:
                return new Color(128, 128, 128);
        }
    }

    private static Color b(Object[] var0) {
        Color var1 = (Color)var0[0];
        int var2 = (Integer)var0[1];
        long var3 = (Long)var0[2];
        var3 = 53258369047921L ^ var3;
        long var5 = var3 ^ 45239607603415L;
        int var10002 = var1.getRed();
        int var10003 = var1.getGreen();
        int var10004 = var1.getBlue();
        Object[] var10008 = new Object[]{null, var5};
        var10008[0] = var2;
        return new Color(var10002, var10003, var10004, a_int____(var10008));
    }

    private static int a_int____(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 < 0 ? 0 : Math.min(var3, 255);
    }

    private static Field e(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field f(Class var0, String var1, Class var2) {
        Field var3 = e(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = f(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method e(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method f(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = e(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = f(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
