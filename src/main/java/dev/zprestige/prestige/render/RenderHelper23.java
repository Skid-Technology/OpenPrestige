package dev.zprestige.prestige.render;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.util.TpsTimer;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.joml.Matrix4f;

public class RenderHelper23 {
    public final String a;
    public final String b;
    private final long c;
    public final long d;
    public float e;
    public boolean f;
    public boolean g;
    public boolean h;
    private FontRenderer i;
    private final String j;
    private final Color k;

    public RenderHelper23(String var1, String var2, long var3, NotificationType var5, long var6) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = System.currentTimeMillis();
        this.e = 0.0F;
        this.f = true;
        this.g = false;
        this.h = false;
        this.j = "assets/prestige/icons/notifications/" + var5.name().toLowerCase() + ".png";
        this.k = var5.a;
    }

    public float a(Object[] var1) {
        Matrix4f var5 = (Matrix4f)var1[0];
        RenderHelper8 var3 = (RenderHelper8)var1[1];
        MatrixCameraData var11 = (MatrixCameraData)var1[2];
        float var4 = (Float)var1[3];
        float var10 = (Float)var1[4];
        float var2 = (Float)var1[5];
        float var6 = (Float)var1[6];
        boolean var9 = (Boolean)var1[7];
        long var7 = (Long)var1[8];
        var7 = 93607983474883L ^ var7;
        long var12 = var7 ^ 97502157298237L;
        long var14 = var7 ^ 21091601579219L;
        long var16 = var7 ^ 68084546728296L;
        long var18 = var7 ^ 121087447224295L;
        long var20 = var7 ^ 31499599369547L;
        long var22 = var7 ^ 17429216574513L;
        long var24 = var7 ^ 53584568700478L;
        long var26 = var7 ^ 104902861318772L;
        long var28 = var7 ^ 94667550225589L;
        if (this.i == null) {
            this.i = TextureUtil.a(new Object[]{this.j, var12});
        }

        var4 += (var2 + 30.0F) * (1.0F - this.e);
        if (this.f) {
            float var10001 = this.e;
            float var10003 = TpsTimer.d;
            Object[] var10006 = new Object[]{null, null, null, var18};
            var10006[2] = var10003;
            var10006[1] = 1.0F;
            var10006[0] = var10001;
            this.e = MathHelper4.b_float______(var10006);
            if (this.e >= 0.99F) {
                this.e = 1.0F;
                this.f = false;
            }
        }

        if (this.g) {
            float var36 = this.e;
            float var41 = TpsTimer.d * 2.0F;
            Object[] var48 = new Object[]{null, null, null, var18};
            var48[2] = var41;
            var48[1] = 0.0F;
            var48[0] = var36;
            this.e = MathHelper4.b_float______(var48);
            if (this.e <= 0.01F) {
                this.e = 0.0F;
                this.h = true;
                return this.e * (var6 + 10.0F);
            }
        }

        if (System.currentTimeMillis() - this.d >= this.c && !this.g) {
            this.g = true;
        }

        if (!var9) {
            return this.e * (var6 + 10.0F);
        } else {
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var16};
            var10011[8] = 5.0F;
            var10011[7] = 5;
            var10011[6] = var6;
            var10011[5] = var2;
            var10011[4] = var10;
            var10011[3] = var4;
            var10011[2] = var5;
            var10011[1] = var11;
            var10011[0] = var3;
            RenderTarget.e(var10011);
            Color var10007 = new Color(0.05490196F, 0.05490196F, 0.05490196F, 150.0F * this.e / 255.0F);
            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var14};
            var10011[8] = 5.0F;
            var10011[7] = var10007;
            var10011[6] = var6;
            var10011[5] = var2;
            var10011[4] = var10;
            var10011[3] = var4;
            var10011[2] = var5;
            var10011[1] = var11;
            var10011[0] = var3;
            RoundedPosColorShader.c(var10011);
            float var10000 = var4 + 5.0F;
            float var37 = var10 + 6.0F;
            FontRenderer var10005 = this.i;
            Color var49 = this.k;
            float var52 = this.e;
            Object[] var10010 = new Object[]{null, null, var22};
            var10010[1] = var52;
            var10010[0] = var49;
            Object[] var10009 = new Object[]{null, null, null, null, var5, var10005, MathHelper4.b(var10010), var26};
            var10009[3] = 16.0F;
            var10009[2] = 16.0F;
            var10009[1] = var37;
            var10009[0] = var10000;
            MatrixStack.v(var10009);
            Font var34 = Prestige.c.a(new Object[]{var20});
            String var10002 = this.a;
            float var42 = var4 + 30.0F;
            float var10004 = var10 + 2.0F;
            var49 = Color.WHITE;
            float var53 = this.e;
            var10010 = new Object[]{null, null, var22};
            var10010[1] = var53;
            var10010[0] = var49;
            var10009 = new Object[]{null, null, null, null, null, MathHelper4.b(var10010), var24};
            var10009[4] = 0.9F;
            var10009[3] = var10004;
            var10009[2] = var42;
            var10009[1] = var10002;
            var10009[0] = var5;
            var34.w(var10009);
            Font var35 = Prestige.c.a(new Object[]{var20});
            var10002 = this.b;
            var42 = var4 + 30.0F;
            var10004 = var10 + 12.0F;
            var49 = new Color(150, 150, 150);
            float var54 = this.e;
            var10010 = new Object[]{null, null, var22};
            var10010[1] = var54;
            var10010[0] = var49;
            var10009 = new Object[]{null, null, null, null, null, MathHelper4.b(var10010), var24};
            var10009[4] = 0.8F;
            var10009[3] = var10004;
            var10009[2] = var42;
            var10009[1] = var10002;
            var10009[0] = var5;
            var35.w(var10009);
            float var31 = 1.0F - Math.max(0.0F, (float)(this.c - (System.currentTimeMillis() - this.d))) / (float)this.c;
            if (var31 < 1.0F) {
                var37 = var4 + 2.0F;
                float var40 = var10 + var6 - 6.0F;
                var42 = var4 + 8.0F + (var2 - 4.0F - (var2 - 4.0F) * var31 - 4.0F);
                var10004 = var10 + var6 - 1.0F;
                Color var47 = this.k;
                var10009 = new Object[]{null, null, null, null, null, null, null, var28};
                var10009[6] = 3.0F;
                var10009[5] = var47;
                var10009[4] = var10004;
                var10009[3] = var42;
                var10009[2] = var40;
                var10009[1] = var37;
                var10009[0] = var5;
                MatrixStack.n(var10009);
            }

            return this.e * (var6 + 10.0F);
        }
    }

    private static Field a(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field b(Class var0, String var1, Class var2) {
        Field var3 = a(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = b(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = a(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = b(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
