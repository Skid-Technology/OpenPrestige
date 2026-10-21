package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import dev.zprestige.prestige.render.RoundedTextureShader;
import dev.zprestige.prestige.render.TextureUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.joml.Matrix4f;

public class FPSElement extends HudElement {
    private static final int a = 24;
    private FontRenderer d;
    private final float[] g;
    private int h;
    private long i;
    private long j;
    private float k;
    private float l;
    private float o;
    private float p;
    private float t;

    public FPSElement(long var1) {
        super("FPS", "Show the client's fps", (57362973462326L));
        var1 = 0L;
        var1 = 127159346024597L ^ var1;
        long var3 = var1 ^ 78672191971235L;
        this.g = new float[24];
        this.h = 0;
        this.i = 0L;
        this.j = 0L;
        this.k = -1.0F;
        this.l = -1.0F;
        this.t = 0.0F;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 23.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var4 = (RenderHelper8)var1[0];
        MatrixCameraData var5 = (MatrixCameraData)var1[1];
        Matrix4f var6 = (Matrix4f)var1[2];
        long var2 = (Long)var1[3];
        long var7 = var2 ^ 57131357196865L;
        long var9 = var2 ^ 132449650111663L;
        long var11 = var2 ^ 95334181894420L;
        long var13 = var2 ^ 27807340928064L;
        long var15 = var2 ^ 89135395443612L;
        long var17 = var2 ^ 102693909475685L;
        long var19 = var2 ^ 6014253680539L;
        long var21 = var2 ^ 131934975778103L;
        long var23 = var2 ^ 54030867045760L;
        long var25 = var2 ^ 19313467402120L;
        long var27 = var2 ^ 18460198684232L;
        long var29 = var2 ^ 101037798966850L;
        if (this.d == null) {
            this.d = TextureUtil.a(new Object[]{"assets/prestige/icons/hud/fps.png", var7});
        }

        int var32 = b.getCurrentFps();
        long var33 = System.currentTimeMillis();
        float var35 = (float)(var33 - this.j) * 0.005F;
        this.j = var33;
        if (var33 - this.i >= 100L) {
            this.g[this.h] = var32;
            this.h = (this.h + 1) % 24;
            this.i = var33;
        }

        float var10001;
        if (this.k < 0.0F) {
            var10001 = var32;
        } else {
            var10001 = this.k;
            float var10002 = var32;
            float var10003 = var35 * 0.5F;
            Object[] var10006 = new Object[]{null, null, null, var19};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var10001 = MathHelper4.b_float______(var10006);
        }

        this.k = var10001;
        float var10000 = this.k;
        Object[] var88 = new Object[]{null, var27};
        var88[0] = var10000;
        Color var36 = a(var88);
        if (this.l < 0.0F) {
            this.l = var36.getRed();
            this.o = var36.getGreen();
            this.p = var36.getBlue();
        } else {
            var10001 = this.l;
            float var83 = var36.getRed();
            Object[] var95 = new Object[]{null, null, null, var19};
            var95[2] = var35;
            var95[1] = var83;
            var95[0] = var10001;
            this.l = MathHelper4.b_float______(var95);
            var10001 = this.o;
            var83 = var36.getGreen();
            var95 = new Object[]{null, null, null, var19};
            var95[2] = var35;
            var95[1] = var83;
            var95[0] = var10001;
            this.o = MathHelper4.b_float______(var95);
            var10001 = this.p;
            var83 = var36.getBlue();
            var95 = new Object[]{null, null, null, var19};
            var95[2] = var35;
            var95[1] = var83;
            var95[0] = var10001;
            this.p = MathHelper4.b_float______(var95);
        }

        Color var37 = new Color((int)this.l, (int)this.o, (int)this.p);
        String var38 = String.valueOf(var32);
        float var39 = Prestige.c.a(new Object[]{var21}).c_float___(new Object[]{var23});
        float var40 = Prestige.c.a(new Object[]{var21}).a_float______________(new Object[]{var38, var17}) * 1.0F;
        float var41 = Prestige.c.a(new Object[]{var21}).a_float______________(new Object[]{"fps", var17}) * 0.65F;
        float var42 = var39 * 1.0F;
        float var43 = var39 * 0.65F;
        float var44 = 7.5F;
        float var45 = 5.5F + var44 + 5.5F;
        float var46 = var45 + 0.5F + 5.5F;
        float var47 = Math.max(var40 + 3.0F + var41, 28.0F);
        float var48 = var46 + var47 + 5.5F;
        float var49 = NameTagsUtil.b(new Object[]{var39});
        boolean var50 = this.c_boolean__(new Object[0]);
        boolean var51 = b.currentScreen instanceof HudEditorScreen;
        if (var50 && this.t > 0.0F && !var51) {
            this.e = this.e - (var48 - this.t);
        }

        this.t = var48;
        float var52 = this.e;
        float var53 = this.f;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var11};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var49;
        var10011[5] = var48;
        var10011[4] = var53;
        var10011[3] = var52;
        var10011[2] = var6;
        var10011[1] = var5;
        var10011[0] = var4;
        RenderTarget.e(var10011);
        Color var10007 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var49;
        var10011[5] = var48;
        var10011[4] = var53;
        var10011[3] = var52;
        var10011[2] = var6;
        var10011[1] = var5;
        var10011[0] = var4;
        RoundedPosColorShader.c(var10011);
        Color var54 = MathHelper4.d(new Object[]{var13});
        float var55 = var50 ? var52 + var48 - 5.5F - var44 : var52 + 5.5F;
        float var56 = var50 ? var52 + var48 - var45 - 0.5F : var52 + var45;
        FontRenderer var89 = this.d;
        float var10005 = var53 + var49 / 2.0F - var44 / 2.0F;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, var54, var25};
        var10011[7] = var44;
        var10011[6] = var44;
        var10011[5] = var10005;
        var10011[4] = var55;
        var10011[3] = var89;
        var10011[2] = var6;
        var10011[1] = var5;
        var10011[0] = var4;
        RoundedTextureShader.c(var10011);
        float var86 = var53 + 3.0F;
        float var90 = var56 + 0.5F;
        float var10004 = var53 + var49 - 3.0F;
        Object[] var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var15};
        var10008[4] = var10004;
        var10008[3] = var90;
        var10008[2] = var86;
        var10008[1] = var56;
        var10008[0] = var6;
        RenderState.l(var10008);
        float var57 = var53 + 3.0F;
        float var58;
        float var59;
        if (var50) {
            float var60 = var52 + var48 - var46;
            var59 = var60 - var41;
            var58 = var59 - 3.0F - var40;
        } else {
            var58 = var52 + var46;
            var59 = var58 + var40 + 3.0F;
        }

        Font var76 = Prestige.c.a(new Object[]{var21});
        Object[] var10009 = new Object[]{null, null, null, null, null, var37, var29};
        var10009[4] = 1.0F;
        var10009[3] = var57;
        var10009[2] = var58;
        var10009[1] = var38;
        var10009[0] = var6;
        var76.w(var10009);
        Font var77 = Prestige.c.a(new Object[]{var21});
        var10004 = var57 + (var42 - var43) - 0.5F;
        var10009 = new Object[]{null, null, null, null, null, NameTagsUtil.t, var29};
        var10009[4] = 0.65F;
        var10009[3] = var10004;
        var10009[2] = var59;
        var10009[1] = "fps";
        var10009[0] = var6;
        var77.w(var10009);
        float var71 = var57 + var42 + 1.0F + 4.0F;
        float var61 = var50 ? var52 + 5.5F : var52 + var46;
        float var62 = var50 ? var52 + var48 - var46 : var52 + var48 - 5.5F;
        float var63 = (var62 - var61) / 24.0F;
        float var64 = 60.0F;

        for (float var68 : this.g) {
            if (var68 > var64) {
                var64 = var68;
            }
        }

        for (int var72 = 0; var72 < 24; var72++) {
            float var73 = this.g[(this.h + var72) % 24];
            if (!(var73 <= 0.0F)) {
                float var74 = 0.5F + var73 / var64 * 3.5F;
                float var75 = var61 + var72 * var63;
                var88 = new Object[]{null, var27};
                var88[0] = var73;
                Color var69 = a(var88);
                int var70 = var72 == 23 ? 220 : 110;
                var10001 = var75 + 0.2F;
                var86 = var71 - var74;
                float var92 = var75 + var63 - 0.2F;
                var10008 = new Object[]{null, null, null, null, null, new Color(var69.getRed(), var69.getGreen(), var69.getBlue(), var70), var15};
                var10008[4] = var71;
                var10008[3] = var92;
                var10008[2] = var86;
                var10008[1] = var10001;
                var10008[0] = var6;
                RenderState.l(var10008);
            }
        }

        Object[] var94 = new Object[]{null, var49};
        var94[0] = var48;
        this.i(var94);
    }

    private static Color a(Object[] var0) {
        float var3 = (Float)var0[0];
        if (var3 >= 120.0F) {
            return new Color(96, 220, 130);
        } else if (var3 >= 60.0F) {
            return new Color(240, 200, 80);
        } else {
            return var3 >= 30.0F ? new Color(245, 145, 60) : new Color(235, 75, 70);
        }
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
