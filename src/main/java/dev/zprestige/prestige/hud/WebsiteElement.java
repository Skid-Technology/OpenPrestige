package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.joml.Matrix4f;

public class WebsiteElement extends HudElement {
    private static final String a = "prestigeclient.vip";

    public WebsiteElement(long var1) {
        super("Website", "Display client website", (107750022622560L));
        var1 = 0L;
        var1 = 108641807380605L ^ var1;
        long var3 = var1 ^ 3507470245149L;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 3.0F;
        var10005[0] = 150.0F;
        this.a_void_______(var10005);
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var3 = (RenderHelper8)var1[0];
        MatrixCameraData var6 = (MatrixCameraData)var1[1];
        Matrix4f var2 = (Matrix4f)var1[2];
        long var4 = (Long)var1[3];
        long var7 = var4 ^ 132449650111663L;
        long var9 = var4 ^ 95334181894420L;
        long var11 = var4 ^ 27807340928064L;
        long var13 = var4 ^ 102693909475685L;
        long var15 = var4 ^ 131934975778103L;
        long var17 = var4 ^ 54030867045760L;
        long var19 = var4 ^ 101037798966850L;
        float var21 = Prestige.c.a(new Object[]{var15}).c_float___(new Object[]{var17});
        float var22 = Prestige.c.a(new Object[]{var15}).a_float______________(new Object[]{"prestigeclient.vip", var13}) * 1.0F;
        float var23 = 5.5F + var22 + 5.5F;
        Object[] var10003 = new Object[]{null, 1.0F};
        var10003[0] = var21;
        float var24 = NameTagsUtil.a(var10003);
        float var25 = this.e;
        float var26 = this.f;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var24;
        var10011[5] = var23;
        var10011[4] = var26;
        var10011[3] = var25;
        var10011[2] = var2;
        var10011[1] = var6;
        var10011[0] = var3;
        RenderTarget.e(var10011);
        Color var10007 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var7};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var24;
        var10011[5] = var23;
        var10011[4] = var26;
        var10011[3] = var25;
        var10011[2] = var2;
        var10011[1] = var6;
        var10011[0] = var3;
        RoundedPosColorShader.c(var10011);
        Color var27 = MathHelper4.d(new Object[]{var11});
        float var28 = (float)(Math.sin(System.currentTimeMillis() * 0.0031415926535897933) * 0.5 + 0.5);
        Color var29 = new Color(var27.getRed(), var27.getGreen(), var27.getBlue(), 150 + (int)(105.0F * var28));
        float var30 = var25 + 5.5F;
        float var31 = var26 + (var24 - var21 * 1.0F) / 2.0F;
        Font var10000 = Prestige.c.a(new Object[]{var15});
        Object[] var10009 = new Object[]{null, null, null, null, null, var29, var19};
        var10009[4] = 1.0F;
        var10009[3] = var31;
        var10009[2] = var30;
        var10009[1] = "prestigeclient.vip";
        var10009[0] = var2;
        var10000.w(var10009);
        Object[] var10004 = new Object[]{null, var24};
        var10004[0] = var23;
        this.i(var10004);
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
