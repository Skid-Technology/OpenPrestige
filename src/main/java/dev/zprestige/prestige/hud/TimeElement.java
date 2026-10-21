package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.Setting;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import org.joml.Matrix4f;

public class TimeElement extends HudElement {
    private FontRenderer a;
    private static final long H = 1000L;

    public TimeElement(long var1) {
        super("Time", "Show the client's time", (63123529882951L));
        var1 = 0L;
        var1 = 112739940464942L ^ var1;
        long var3 = var1 ^ 105416877483113L;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 3.0F;
        var10005[0] = 85.0F;
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
        long var11 = var2 ^ 45076250098524L;
        long var13 = var2 ^ 95334181894420L;
        long var15 = var2 ^ 27807340928064L;
        long var17 = var2 ^ 89135395443612L;
        long var19 = var2 ^ 102693909475685L;
        long var21 = var2 ^ 131934975778103L;
        long var23 = var2 ^ 54030867045760L;
        long var25 = var2 ^ 19313467402120L;
        long var27 = var2 ^ 101037798966850L;
        if (this.a == null) {
            this.a = TextureUtil.a(new Object[]{"assets/prestige/icons/hud/clock.png", var7});
        }

        Font var30 = Prestige.c.a(new Object[]{var21});
        short var10000 = 6373;
        short var10001 = 14418;
        LocalTime var31 = LocalTime.now();
        String var32 = String.format("%02d", var31.getHour());
        long var79 = 6139618554127606610L ^ var2;
        String var33 = String.format("%02d", var31.getMinute());
        String var34 = String.format("%02d", var31.getSecond());
        String var35 = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String var36 = var35.substring(0, 1).toUpperCase(Locale.ENGLISH) + var35.substring(1).toLowerCase(Locale.ENGLISH);
        float var37 = 0.0F;

        for (char var38 = '0'; var38 <= '9'; var38++) {
            var37 = Math.max(var37, var30.a_float______________(new Object[]{String.valueOf(var38), var19}));
        }

        float var76 = var37 * 1.0F;
        float var39 = var37 * 0.65F;
        float var40 = var30.a_float______________(new Object[]{":", var19}) * 1.0F + 1.6F;
        float var41 = var30.c_float___(new Object[]{var23});
        float var42 = var41 * 0.65F;
        float var43 = var41 * 1.0F;
        float var44 = var41 * 0.65F;
        float var45 = var76 * 2.0F + var40 + var76 * 2.0F + 3.0F + var39 * 2.0F;
        float var46 = var30.a_float______________(new Object[]{var36, var19}) * 0.65F;
        float var47 = Math.max(var45, var46);
        float var48 = 7.5F;
        float var49 = 5.5F + var48 + 5.5F;
        float var50 = var49 + 0.5F + 5.5F;
        float var51 = var50 + var47 + 5.5F;
        float var52 = 3.0F + var42 + 1.0F + var43 + 1.0F + 4.0F + 3.0F;
        float var53 = this.e;
        float var54 = this.f;
        boolean var55 = this.c_boolean__(new Object[0]);
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var13};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var52;
        var10011[5] = var51;
        var10011[4] = var54;
        var10011[3] = var53;
        var10011[2] = var6;
        var10011[1] = var5;
        var10011[0] = var4;
        RenderTarget.e(var10011);
        Color var10007 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var52;
        var10011[5] = var51;
        var10011[4] = var54;
        var10011[3] = var53;
        var10011[2] = var6;
        var10011[1] = var5;
        var10011[0] = var4;
        RoundedPosColorShader.c(var10011);
        Color var56 = MathHelper4.d(new Object[]{var15});
        float var57 = var55 ? var53 + var51 - 5.5F - var48 : var53 + 5.5F;
        float var58 = var55 ? var53 + var51 - var49 - 0.5F : var53 + var49;
        FontRenderer var10003 = this.a;
        float var10005 = var54 + var52 / 2.0F - var48 / 2.0F;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, var56, var25};
        var10011[7] = var48;
        var10011[6] = var48;
        var10011[5] = var10005;
        var10011[4] = var57;
        var10011[3] = var10003;
        var10011[2] = var6;
        var10011[1] = var5;
        var10011[0] = var4;
        RoundedTextureShader.c(var10011);
        float var10002 = var54 + 3.0F;
        float var80 = var58 + 0.5F;
        float var10004 = var54 + var52 - 3.0F;
        Object[] var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var17};
        var10008[4] = var10004;
        var10008[3] = var80;
        var10008[2] = var10002;
        var10008[1] = var58;
        var10008[0] = var6;
        RenderState.l(var10008);
        float var59 = var54 + 3.0F;
        float var60 = var55 ? var53 + var51 - var50 - var46 : var53 + var50;
        Object[] var10009 = new Object[]{null, null, null, null, null, var56, var27};
        var10009[4] = 0.65F;
        var10009[3] = var59;
        var10009[2] = var60;
        var10009[1] = var36;
        var10009[0] = var6;
        var30.w(var10009);
        float var61 = var59 + var42 + 1.0F;
        float var62 = var61 + (var43 - var44) - 0.5F;
        float var63 = var55 ? var53 + var51 - var50 - var45 : var53 + var50;
        float var64 = (float)(System.currentTimeMillis() % H) / 1000.0F;
        float var65 = 0.5F + 0.5F * (float)Math.sin(var64 * (float) (Math.PI * 2));
        Color var66 = new Color(255, 255, 255, 70 + (int)(170.0F * var65));
        var10004 = var76 * 2.0F;
        Object[] var10010 = new Object[]{null, null, null, null, null, null, null, NameTagsUtil.s, var11};
        var10010[6] = 1.0F;
        var10010[5] = var61;
        var10010[4] = var10004;
        var10010[3] = var63;
        var10010[2] = var32;
        var10010[1] = var6;
        var10010[0] = var30;
        r(var10010);
        float var67 = var63 + var76 * 2.0F;
        var10010 = new Object[]{null, null, null, null, null, null, null, var66, var11};
        var10010[6] = 1.0F;
        var10010[5] = var61;
        var10010[4] = var40;
        var10010[3] = var67;
        var10010[2] = ":";
        var10010[1] = var6;
        var10010[0] = var30;
        r(var10010);
        var67 += var40;
        var10004 = var76 * 2.0F;
        var10010 = new Object[]{null, null, null, null, null, null, null, NameTagsUtil.s, var11};
        var10010[6] = 1.0F;
        var10010[5] = var61;
        var10010[4] = var10004;
        var10010[3] = var67;
        var10010[2] = var33;
        var10010[1] = var6;
        var10010[0] = var30;
        r(var10010);
        var67 += var76 * 2.0F + 3.0F;
        var10004 = var39 * 2.0F;
        var10010 = new Object[]{null, null, null, null, null, null, null, NameTagsUtil.u, var11};
        var10010[6] = 0.65F;
        var10010[5] = var62;
        var10010[4] = var10004;
        var10010[3] = var67;
        var10010[2] = var34;
        var10010[1] = var6;
        var10010[0] = var30;
        r(var10010);
        float var68 = var61 + var43 + 1.0F;
        float var70 = var68 + 1.5F;
        float var71 = var55 ? var53 + 5.5F : var53 + var50;
        float var72 = var55 ? var53 + var51 - var50 : var53 + var51 - 5.5F;
        var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var17};
        var10008[4] = var70;
        var10008[3] = var72;
        var10008[2] = var68;
        var10008[1] = var71;
        var10008[0] = var6;
        RenderState.l(var10008);
        float var73 = (var31.getSecond() + var31.getNano() / 1.0E9F) / 60.0F;
        float var74 = (float)(Math.sin(System.currentTimeMillis() * 0.0031415926535897933) * 0.5 + 0.5);
        Color var75 = new Color(var56.getRed(), var56.getGreen(), var56.getBlue(), 150 + (int)(70.0F * var74));
        float var81 = var71 + (var72 - var71) * var73;
        var10008 = new Object[]{null, null, null, null, null, var75, var17};
        var10008[4] = var70;
        var10008[3] = var81;
        var10008[2] = var68;
        var10008[1] = var71;
        var10008[0] = var6;
        RenderState.l(var10008);
        Object[] var85 = new Object[]{null, var52};
        var85[0] = var51;
        this.i(var85);
        Setting.b();
    }

    private static void r(Object[] var0) {
        Font var4 = (Font)var0[0];
        Matrix4f var5 = (Matrix4f)var0[1];
        String var8 = (String)var0[2];
        float var2 = (Float)var0[3];
        float var7 = (Float)var0[4];
        float var3 = (Float)var0[5];
        float var6 = (Float)var0[6];
        Color var1 = (Color)var0[7];
        long var9 = (Long)var0[8];
        var9 = 112739940464942L ^ var9;
        long var11 = var9 ^ 20960383808279L;
        long var13 = var9 ^ 23720095138864L;
        float var15 = var4.a_float______________(new Object[]{var8, var11}) * var6;
        float var10003 = var2 + (var7 - var15) / 2.0F;
        Object[] var10009 = new Object[]{null, null, null, null, null, var1, var13};
        var10009[4] = var6;
        var10009[3] = var3;
        var10009[2] = var10003;
        var10009[1] = var8;
        var10009[0] = var5;
        var4.w(var10009);
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
