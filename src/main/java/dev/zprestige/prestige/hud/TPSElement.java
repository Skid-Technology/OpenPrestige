package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent3;
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
import java.util.Locale;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.joml.Matrix4f;

public class TPSElement extends HudElement {
    private static final int a = 24;
    private static final float m = 20.0F;
    private FontRenderer d;
    private final float[] g;
    private int h;
    private long i;
    private float k;
    private long j;
    private float l;
    private float o;
    private float p;
    private float t;
    private float u;

    public TPSElement(long var1) {
        super("TPS", "Show the server's tick rate. Measured off the interval between WorldTimeUpdate packets.", (112967561235834L));
        var1 = 0L;
        var1 = 16729889130533L ^ var1;
        long var3 = var1 ^ 116039081952607L;
        this.g = new float[24];
        this.h = 0;
        this.i = 0L;
        this.k = -1.0F;
        this.j = 0L;
        this.l = -1.0F;
        this.o = -1.0F;
        this.u = 0.0F;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 45.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (var1.a(new Object[0]) instanceof WorldTimeUpdateS2CPacket) {
            long var5 = System.currentTimeMillis();
            if (this.i > 0L) {
                long var7 = var5 - this.i;
                if (var7 >= 200L) {
                    float var9 = Math.min(20000.0F / (float)var7, 20.0F);
                    this.k = var9;
                    this.g[this.h] = var9;
                    this.h = (this.h + 1) % 24;
                }
            }

            this.i = var5;
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        Event.b();

        for (int var5 = 0; var5 < 24; var5++) {
            this.g[var5] = 0.0F;
        }

        this.h = 0;
        this.i = 0L;
        this.k = -1.0F;
        this.l = -1.0F;
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var6 = (RenderHelper8)var1[0];
        MatrixCameraData var5 = (MatrixCameraData)var1[1];
        Matrix4f var4 = (Matrix4f)var1[2];
        long var2 = (Long)var1[3];
        long var7 = var2 ^ 57131357196865L;
        long var9 = var2 ^ 132449650111663L;
        long var11 = var2 ^ 77544806110454L;
        long var13 = var2 ^ 95334181894420L;
        long var15 = var2 ^ 27807340928064L;
        long var17 = var2 ^ 89135395443612L;
        long var19 = var2 ^ 102693909475685L;
        long var21 = var2 ^ 6014253680539L;
        long var23 = var2 ^ 131934975778103L;
        long var25 = var2 ^ 54030867045760L;
        long var27 = var2 ^ 19313467402120L;
        long var29 = var2 ^ 101037798966850L;
        if (this.d == null) {
            this.d = TextureUtil.a(new Object[]{"assets/prestige/icons/hud/clock.png", var7});
        }

        long var32 = System.currentTimeMillis();
        float var34 = (float)(var32 - this.j) * 0.005F;
        this.j = var32;
        float var35;
        String var36;
        if (this.k < 0.0F) {
            var35 = 0.0F;
            var36 = "-.-";
        } else {
            boolean var37 = this.i > 0L && var32 - this.i > 3000L;
            var35 = var37 ? 0.0F : this.k;
            var36 = String.format(Locale.ROOT, "%.1f", var35);
        }

        float var10001;
        if (this.l < 0.0F) {
            var10001 = var35;
        } else {
            var10001 = this.l;
            float var10003 = var34 * 0.5F;
            Object[] var10006 = new Object[]{null, null, null, var21};
            var10006[2] = var10003;
            var10006[1] = var35;
            var10006[0] = var10001;
            var10001 = MathHelper4.b_float______(var10006);
        }

        this.l = var10001;
        float var10000 = this.l;
        Object[] var83 = new Object[]{null, var11};
        var83[0] = var10000;
        Color var70 = a(var83);
        if (this.o < 0.0F) {
            this.o = var70.getRed();
            this.p = var70.getGreen();
            this.t = var70.getBlue();
        } else {
            var10001 = this.o;
            float var10002 = var70.getRed();
            Object[] var90 = new Object[]{null, null, null, var21};
            var90[2] = var34;
            var90[1] = var10002;
            var90[0] = var10001;
            this.o = MathHelper4.b_float______(var90);
            var10001 = this.p;
            var10002 = var70.getGreen();
            var90 = new Object[]{null, null, null, var21};
            var90[2] = var34;
            var90[1] = var10002;
            var90[0] = var10001;
            this.p = MathHelper4.b_float______(var90);
            var10001 = this.t;
            var10002 = var70.getBlue();
            var90 = new Object[]{null, null, null, var21};
            var90[2] = var34;
            var90[1] = var10002;
            var90[0] = var10001;
            this.t = MathHelper4.b_float______(var90);
        }

        Color var38 = new Color((int)this.o, (int)this.p, (int)this.t);
        float var39 = Prestige.c.a(new Object[]{var23}).c_float___(new Object[]{var25});
        float var40 = Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var36, var19}) * 1.0F;
        float var41 = Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{"tps", var19}) * 0.65F;
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
        if (var50 && this.u > 0.0F && !var51) {
            this.e = this.e - (var48 - this.u);
        }

        this.u = var48;
        float var52 = this.e;
        float var53 = this.f;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var13};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var49;
        var10011[5] = var48;
        var10011[4] = var53;
        var10011[3] = var52;
        var10011[2] = var4;
        var10011[1] = var5;
        var10011[0] = var6;
        RenderTarget.e(var10011);
        Color var10007 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var49;
        var10011[5] = var48;
        var10011[4] = var53;
        var10011[3] = var52;
        var10011[2] = var4;
        var10011[1] = var5;
        var10011[0] = var6;
        RoundedPosColorShader.c(var10011);
        Color var54 = MathHelper4.d(new Object[]{var15});
        float var55 = var50 ? var52 + var48 - 5.5F - var44 : var52 + 5.5F;
        float var56 = var50 ? var52 + var48 - var45 - 0.5F : var52 + var45;
        FontRenderer var84 = this.d;
        float var10005 = var53 + var49 / 2.0F - var44 / 2.0F;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, var54, var27};
        var10011[7] = var44;
        var10011[6] = var44;
        var10011[5] = var10005;
        var10011[4] = var55;
        var10011[3] = var84;
        var10011[2] = var4;
        var10011[1] = var5;
        var10011[0] = var6;
        RoundedTextureShader.c(var10011);
        float var81 = var53 + 3.0F;
        float var85 = var56 + 0.5F;
        float var10004 = var53 + var49 - 3.0F;
        Object[] var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var17};
        var10008[4] = var10004;
        var10008[3] = var85;
        var10008[2] = var81;
        var10008[1] = var56;
        var10008[0] = var4;
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

        Font var72 = Prestige.c.a(new Object[]{var23});
        Object[] var10009 = new Object[]{null, null, null, null, null, var38, var29};
        var10009[4] = 1.0F;
        var10009[3] = var57;
        var10009[2] = var58;
        var10009[1] = var36;
        var10009[0] = var4;
        var72.w(var10009);
        Font var73 = Prestige.c.a(new Object[]{var23});
        var10004 = var57 + (var42 - var43) - 0.5F;
        var10009 = new Object[]{null, null, null, null, null, NameTagsUtil.t, var29};
        var10009[4] = 0.65F;
        var10009[3] = var10004;
        var10009[2] = var59;
        var10009[1] = "tps";
        var10009[0] = var4;
        var73.w(var10009);
        float var71 = var57 + var42 + 1.0F + 4.0F;
        float var61 = var50 ? var52 + 5.5F : var52 + var46;
        float var62 = var50 ? var52 + var48 - var46 : var52 + var48 - 5.5F;
        float var63 = (var62 - var61) / 24.0F;

        for (int var64 = 0; var64 < 24; var64++) {
            float var65 = this.g[(this.h + var64) % 24];
            if (!(var65 <= 0.0F)) {
                float var66 = 0.5F + var65 / 20.0F * 3.5F;
                float var67 = var61 + var64 * var63;
                var83 = new Object[]{null, var11};
                var83[0] = var65;
                Color var68 = a(var83);
                int var69 = var64 == 23 ? 220 : 110;
                var10001 = var67 + 0.2F;
                var81 = var71 - var66;
                float var87 = var67 + var63 - 0.2F;
                var10008 = new Object[]{null, null, null, null, null, new Color(var68.getRed(), var68.getGreen(), var68.getBlue(), var69), var17};
                var10008[4] = var71;
                var10008[3] = var87;
                var10008[2] = var81;
                var10008[1] = var10001;
                var10008[0] = var4;
                RenderState.l(var10008);
            }
        }

        Object[] var89 = new Object[]{null, var49};
        var89[0] = var48;
        this.i(var89);
    }

    private static Color a(Object[] var0) {
        float var1 = (Float)var0[0];
        if (var1 >= 19.0F) {
            return new Color(96, 220, 130);
        } else if (var1 >= 15.0F) {
            return new Color(240, 200, 80);
        } else {
            return var1 >= 10.0F ? new Color(245, 145, 60) : new Color(235, 75, 70);
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
