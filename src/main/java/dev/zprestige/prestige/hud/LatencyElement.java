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
import net.minecraft.client.network.PlayerListEntry;
import org.joml.Matrix4f;

public class LatencyElement extends HudElement {
    private static final int a = 24;
    private static final float m = 100.0F;
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

    public LatencyElement(long var1) {
        super("Latency", "Show the client's latency", (19222381473573L));
        var1 = 0L;
        var1 = 44097720316040L ^ var1;
        long var3 = var1 ^ 63087764594605L;
        this.g = new float[24];
        this.h = 0;
        this.i = 0L;
        this.j = 0L;
        this.k = -1.0F;
        this.l = 96.0F;
        this.o = 220.0F;
        this.p = 130.0F;
        this.t = 0.0F;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 23.0F;
        var10005[0] = 65.0F;
        this.a_void_______(var10005);
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var2 = (RenderHelper8)var1[0];
        MatrixCameraData var4 = (MatrixCameraData)var1[1];
        Matrix4f var3 = (Matrix4f)var1[2];
        long var5 = (Long)var1[3];
        long var7 = var5 ^ 95334181894420L;
        long var9 = var5 ^ 27807340928064L;
        long var11 = var5 ^ 54030867045760L;
        long var13 = var5 ^ 57131357196865L;
        long var15 = var5 ^ 132449650111663L;
        long var17 = var5 ^ 109546980108248L;
        long var19 = var5 ^ 89135395443612L;
        long var21 = var5 ^ 102693909475685L;
        long var23 = var5 ^ 6014253680539L;
        long var25 = var5 ^ 131934975778103L;
        long var27 = var5 ^ 19313467402120L;
        long var29 = var5 ^ 108665228979358L;
        long var31 = var5 ^ 101037798966850L;
        if (this.d == null) {
            this.d = TextureUtil.a(new Object[]{"assets/prestige/icons/hud/latency.png", var13});
        }

        long var34 = System.currentTimeMillis();
        float var36 = this.j == 0L ? 1.0F : (float)(var34 - this.j) * 0.005F;
        this.j = var34;
        int var37 = 0;
        if (b.getNetworkHandler() != null && b.player != null) {
            PlayerListEntry var38 = b.getNetworkHandler().getPlayerListEntry(b.player.getUuid());
            if (var38 != null) {
                var37 = var38.getLatency();
            }
        }

        if (var34 - this.i >= 100L) {
            this.g[this.h] = var37;
            this.h = (this.h + 1) % 24;
            this.i = var34;
        }

        float var10001;
        if (this.k < 0.0F) {
            var10001 = var37;
        } else {
            var10001 = this.k;
            float var10002 = var37;
            Object[] var10006 = new Object[]{null, null, null, var23};
            var10006[2] = var36;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var10001 = MathHelper4.b_float______(var10006);
        }

        this.k = var10001;
        int var73 = Math.max(0, Math.round(this.k));
        float var10000 = var73;
        Object[] var10003 = new Object[]{null, var17};
        var10003[0] = var10000;
        Color var39 = a(var10003);
        var10001 = this.l;
        float var86 = var39.getRed();
        Object[] var103 = new Object[]{null, null, null, var23};
        var103[2] = var36;
        var103[1] = var86;
        var103[0] = var10001;
        this.l = MathHelper4.b_float______(var103);
        var10001 = this.o;
        var86 = var39.getGreen();
        var103 = new Object[]{null, null, null, var23};
        var103[2] = var36;
        var103[1] = var86;
        var103[0] = var10001;
        this.o = MathHelper4.b_float______(var103);
        var10001 = this.p;
        var86 = var39.getBlue();
        var103 = new Object[]{null, null, null, var23};
        var103[2] = var36;
        var103[1] = var86;
        var103[0] = var10001;
        this.p = MathHelper4.b_float______(var103);
        var86 = this.l;
        Object[] var10005 = new Object[]{null, var29};
        var10005[0] = var86;
        int var90 = a_int___(var10005);
        float var93 = this.o;
        var103 = new Object[]{null, var29};
        var103[0] = var93;
        int var94 = a_int___(var103);
        float var10004 = this.p;
        Object[] var10007 = new Object[]{null, var29};
        var10007[0] = var10004;
        Color var40 = new Color(var90, var94, a_int___(var10007));
        String var41 = String.valueOf(var73);
        float var42 = Prestige.c.a(new Object[]{var25}).c_float___(new Object[]{var11});
        float var43 = Prestige.c.a(new Object[]{var25}).a_float______________(new Object[]{var41, var21}) * 1.0F;
        float var44 = Prestige.c.a(new Object[]{var25}).a_float______________(new Object[]{"ms", var21}) * 0.65F;
        float var45 = var42 * 1.0F;
        float var46 = var42 * 0.65F;
        float var47 = 7.5F;
        float var48 = 5.5F + var47 + 5.5F;
        float var49 = var48 + 0.5F + 5.5F;
        float var50 = Math.max(var43 + 3.0F + var44, 28.0F);
        float var51 = var49 + var50 + 5.5F;
        float var52 = NameTagsUtil.b(new Object[]{var42});
        boolean var53 = this.c_boolean__(new Object[0]);
        boolean var54 = b.currentScreen instanceof HudEditorScreen;
        if (var53 && this.t > 0.0F && !var54) {
            this.e = this.e - (var51 - this.t);
        }

        this.t = var51;
        float var55 = this.e;
        float var56 = this.f;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var7};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var52;
        var10011[5] = var51;
        var10011[4] = var56;
        var10011[3] = var55;
        var10011[2] = var3;
        var10011[1] = var4;
        var10011[0] = var2;
        RenderTarget.e(var10011);
        Color var107 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var15};
        var10011[8] = 4.0F;
        var10011[7] = var107;
        var10011[6] = var52;
        var10011[5] = var51;
        var10011[4] = var56;
        var10011[3] = var55;
        var10011[2] = var3;
        var10011[1] = var4;
        var10011[0] = var2;
        RoundedPosColorShader.c(var10011);
        float var57 = var53 ? var55 + var51 - 5.5F - var47 : var55 + 5.5F;
        float var58 = var53 ? var55 + var51 - var48 - 0.5F : var55 + var48;
        FontRenderer var95 = this.d;
        float var102 = var56 + var52 / 2.0F - var47 / 2.0F;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.d(new Object[]{var9}), var27};
        var10011[7] = var47;
        var10011[6] = var47;
        var10011[5] = var102;
        var10011[4] = var57;
        var10011[3] = var95;
        var10011[2] = var3;
        var10011[1] = var4;
        var10011[0] = var2;
        RoundedTextureShader.c(var10011);
        var86 = var56 + 3.0F;
        float var96 = var58 + 0.5F;
        var10004 = var56 + var52 - 3.0F;
        Object[] var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var19};
        var10008[4] = var10004;
        var10008[3] = var96;
        var10008[2] = var86;
        var10008[1] = var58;
        var10008[0] = var3;
        RenderState.l(var10008);
        float var59 = var56 + 3.0F;
        float var60;
        float var61;
        if (var53) {
            float var62 = var55 + var51 - var49;
            var61 = var62 - var44;
            var60 = var61 - 3.0F - var43;
        } else {
            var60 = var55 + var49;
            var61 = var60 + var43 + 3.0F;
        }

        Font var79 = Prestige.c.a(new Object[]{var25});
        Object[] var10009 = new Object[]{null, null, null, null, null, var40, var31};
        var10009[4] = 1.0F;
        var10009[3] = var59;
        var10009[2] = var60;
        var10009[1] = var41;
        var10009[0] = var3;
        var79.w(var10009);
        Font var80 = Prestige.c.a(new Object[]{var25});
        var10004 = var59 + (var45 - var46) - 0.5F;
        var10009 = new Object[]{null, null, null, null, null, NameTagsUtil.u, var31};
        var10009[4] = 0.65F;
        var10009[3] = var10004;
        var10009[2] = var61;
        var10009[1] = "ms";
        var10009[0] = var3;
        var80.w(var10009);
        float var74 = var59 + var45 + 1.0F + 4.0F;
        float var63 = var53 ? var55 + 5.5F : var55 + var49;
        float var64 = var53 ? var55 + var51 - var49 : var55 + var51 - 5.5F;
        float var65 = (var64 - var63) / 24.0F;
        float var66 = 100.0F;

        for (float var70 : this.g) {
            if (var70 > var66) {
                var66 = var70;
            }
        }

        for (int var75 = 0; var75 < 24; var75++) {
            float var76 = this.g[(this.h + var75) % 24];
            if (!(var76 <= 0.0F)) {
                float var77 = 0.5F + var76 / var66 * 3.5F;
                float var78 = var63 + var75 * var65;
                var10003 = new Object[]{null, var17};
                var10003[0] = var76;
                Color var71 = a(var10003);
                int var72 = var75 == 23 ? 220 : 110;
                var10001 = var78 + 0.2F;
                var86 = var74 - var77;
                float var98 = var78 + var65 - 0.2F;
                var10008 = new Object[]{null, null, null, null, null, new Color(var71.getRed(), var71.getGreen(), var71.getBlue(), var72), var19};
                var10008[4] = var74;
                var10008[3] = var98;
                var10008[2] = var86;
                var10008[1] = var10001;
                var10008[0] = var3;
                RenderState.l(var10008);
            }
        }

        Object[] var101 = new Object[]{null, var52};
        var101[0] = var51;
        this.i(var101);
    }

    private static Color a(Object[] var0) {
        float var3 = (Float)var0[0];
        if (var3 < 60.0F) {
            return new Color(96, 220, 130);
        } else if (var3 < 120.0F) {
            return new Color(245, 205, 90);
        } else {
            return var3 <= 200.0F ? new Color(245, 150, 70) : new Color(240, 85, 85);
        }
    }

    private static int a_int___(Object[] var0) {
        float var1 = (Float)var0[0];
        return var1 < 0.0F ? 0 : Math.min((int)var1, 255);
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
