package dev.zprestige.prestige.hud;

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
import org.joml.Matrix4f;

public class WatermarkElement extends HudElement {
    private static final String a = "Prestige";
    private static final String b = "Client";
    private static final float m = 12.0F;
    private FontRenderer c;

    public WatermarkElement(long var1) {
        super("Watermark", "Show the client's watermark", (37128214601486L));
        var1 = 0L;
        var1 = 75060585149526L ^ var1;
        long var3 = var1 ^ 111604482534232L;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 3.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var6 = (RenderHelper8)var1[0];
        MatrixCameraData var5 = (MatrixCameraData)var1[1];
        Matrix4f var2 = (Matrix4f)var1[2];
        long var3 = (Long)var1[3];
        long var7 = var3 ^ 57131357196865L;
        long var9 = var3 ^ 132449650111663L;
        long var11 = var3 ^ 95334181894420L;
        long var13 = var3 ^ 27807340928064L;
        long var15 = var3 ^ 89135395443612L;
        long var17 = var3 ^ 102693909475685L;
        long var19 = var3 ^ 131934975778103L;
        long var21 = var3 ^ 54030867045760L;
        long var23 = var3 ^ 19313467402120L;
        long var25 = var3 ^ 101037798966850L;
        if (this.c == null) {
            this.c = TextureUtil.a(new Object[]{"assets/prestige/icons/logo.png", var7});
        }

        float var28 = Prestige.c.a(new Object[]{var19}).c_float___(new Object[]{var21});
        float var29 = Prestige.c.a(new Object[]{var19}).a_float______________(new Object[]{"Prestige Client", var17}) * 1.0F;
        Object[] var10003 = new Object[]{null, 1.0F};
        var10003[0] = var28;
        float var30 = Math.max(NameTagsUtil.a(var10003), 18.0F);
        float var33 = 29.0F + var29 + 5.5F;
        float var34 = this.e;
        float var35 = this.f;
        boolean var36 = this.c_boolean__(new Object[0]);
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var11};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var30;
        var10011[5] = var33;
        var10011[4] = var35;
        var10011[3] = var34;
        var10011[2] = var2;
        var10011[1] = var5;
        var10011[0] = var6;
        RenderTarget.e(var10011);
        Color var10007 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var30;
        var10011[5] = var33;
        var10011[4] = var35;
        var10011[3] = var34;
        var10011[2] = var2;
        var10011[1] = var5;
        var10011[0] = var6;
        RoundedPosColorShader.c(var10011);
        Color var37 = MathHelper4.d(new Object[]{var13});
        float var38 = (float)(Math.sin(System.currentTimeMillis() * 0.0031415926535897933) * 0.5 + 0.5);
        int var39 = 170 + (int)(50.0F * var38);
        Color var40 = new Color(var37.getRed(), var37.getGreen(), var37.getBlue(), var39);
        float var41 = var36 ? var34 + var33 - 5.5F - 12.0F : var34 + 5.5F;
        float var42 = var36 ? var34 + var33 - 23.0F - 0.5F : var34 + 23.0F;
        float var43 = var35 + (var30 - 12.0F) / 2.0F;
        FontRenderer var48 = this.c;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, var37, var23};
        var10011[7] = 12.0F;
        var10011[6] = 12.0F;
        var10011[5] = var43;
        var10011[4] = var41;
        var10011[3] = var48;
        var10011[2] = var2;
        var10011[1] = var5;
        var10011[0] = var6;
        RoundedTextureShader.c(var10011);
        float var10002 = var35 + 3.0F;
        float var49 = var42 + 0.5F;
        float var10004 = var35 + var30 - 3.0F;
        Object[] var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var15};
        var10008[4] = var10004;
        var10008[3] = var49;
        var10008[2] = var10002;
        var10008[1] = var42;
        var10008[0] = var2;
        RenderState.l(var10008);
        float var44 = var36 ? var34 + var33 - 29.0F - var29 : var34 + 29.0F;
        float var45 = var35 + (var30 - var28 * 1.0F) / 2.0F;
        float var46 = Prestige.c.a(new Object[]{var19}).a_float______________(new Object[]{"Prestige ", var17}) * 1.0F;
        Font var10000 = Prestige.c.a(new Object[]{var19});
        Object[] var10009 = new Object[]{null, null, null, null, null, var40, var25};
        var10009[4] = 1.0F;
        var10009[3] = var45;
        var10009[2] = var44;
        var10009[1] = "Prestige";
        var10009[0] = var2;
        var10000.w(var10009);
        var10000 = Prestige.c.a(new Object[]{var19});
        float var50 = var44 + var46;
        var10009 = new Object[]{null, null, null, null, null, var40, var25};
        var10009[4] = 1.0F;
        var10009[3] = var45;
        var10009[2] = var50;
        var10009[1] = "Client";
        var10009[0] = var2;
        var10000.w(var10009);
        Object[] var51 = new Object[]{null, var30};
        var51[0] = var33;
        this.i(var51);
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
