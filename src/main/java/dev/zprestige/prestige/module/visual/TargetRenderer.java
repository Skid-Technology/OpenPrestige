package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderUtil;
import dev.zprestige.prestige.render.RoundedTextureShader;
import dev.zprestige.prestige.render.TextureUtil;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class TargetRenderer extends Module {
    public TargetRenderer() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private FontRenderer b;
    private FontRenderer c;
    private static final float d = 850.0F;
    private static final float e = 1100.0F;
    private static final float f = 14.0F;
    private float g;
    private Vec3d h;
    private float i;
    private float j;
    private float k = 0.0F;
    private long l = System.currentTimeMillis();
    private long m = 0L;
    private long n = 0L;
    private boolean o = false;
    private float p = 0.0F;

    @Subscribe
    public void a(TickEvent27 var1) {
        Event.b();
        long var17 = System.currentTimeMillis();
        float var19 = this.m == 0L ? 0.0F : Math.min(0.1F, (float)(var17 - this.m) / 1000.0F);
        this.m = var17;
        this.k = (float)(var17 % 3600L) * 0.1F;
        PlayerEntity var20 = Prestige.n.a(new Object[0]);
        boolean var21 = var20 != null;
        if (var21 != this.o) {
            this.n = var17;
            this.p = this.g;
            this.o = var21;
        }

        long var22 = var17 - this.n;
        if (var21) {
            float var24 = Math.min(1.0F, (float)var22 / 850.0F);
            Object[] var10003 = new Object[]{null, 130206190942635L};
            var10003[0] = var24;
            float var25 = b_float________________(var10003);
            this.g = this.p + (1.0F - this.p) * var25;
        } else {
            float var28 = Math.min(1.0F, (float)var22 / 1100.0F);
            Object[] var34 = new Object[]{null, 130206190942635L};
            var34[0] = var28;
            float var31 = b_float________________(var34);
            this.g = this.p * (1.0F - var31);
        }

        float var10001 = this.g;
        Object[] var10004 = new Object[]{null, 23951640555617L};
        var10004[0] = var10001;
        this.g = c(var10004);
        if (var20 != null) {
            this.j = var20.getHeight();
            this.i = var20.getWidth();
            Vec3d var29 = PositionUtil.c(new Object[]{var20, 91425777529078L});
            float var32 = MatrixStack.a_float_______________(new Object[]{601921721859L});
            float var35 = (float)var29.x;
            float var37 = (float)var20.getX();
            Object[] var10007 = new Object[]{null, null, var32};
            var10007[1] = var37;
            var10007[0] = var35;
            double var10002 = this.e(var10007);
            float var38 = (float)var29.y;
            float var10005 = (float)var20.getY();
            Object[] var10008 = new Object[]{null, null, var32};
            var10008[1] = var10005;
            var10008[0] = var38;
            double var36 = this.e(var10008) + this.j / 2.0F;
            var10005 = (float)var29.z;
            float var10006 = (float)var20.getZ();
            Object[] var10009 = new Object[]{null, null, var32};
            var10009[1] = var10006;
            var10009[0] = var10005;
            Vec3d var26 = new Vec3d(var10002, var36, this.e(var10009));
            if (this.h != null && !(this.h.distanceTo(var26) > 50.0)) {
                float var27 = 1.0F - (float)Math.exp(-var19 * 14.0F);
                this.h = new Vec3d(this.h.x + (var26.x - this.h.x) * var27, this.h.y + (var26.y - this.h.y) * var27, this.h.z + (var26.z - this.h.z) * var27);
            } else {
                this.h = var26;
            }
        }

        if (!(this.g < 0.01F) && this.h != null && this.j != -1.0F) {
            String var30 = (String)this.a.getValue();
            switch (var30) {
                case "Ghost":
                    this.j(new Object[]{var1, 73191366651491L});
                    break;
                case "Square":
                    this.k(new Object[]{var1, 127351246602272L});
            }
        } else {
            this.k = 0.0F;
        }
    }

    private static float b_float________________(Object[] var0) {
        float var3 = (Float)var0[0];
        long var1 = (Long)var0[1];
        var1 = 32576321123276L ^ var1;
        long var4 = var1 ^ 138553846184454L;
        Object[] var10003 = new Object[]{null, var4};
        var10003[0] = var3;
        var3 = c(var10003);
        return var3 * var3 * var3 * (var3 * (var3 * 6.0F - 15.0F) + 10.0F);
    }

    private static float c(Object[] var0) {
        float var3 = (Float)var0[0];
        return var3 < 0.0F ? 0.0F : (var3 > 1.0F ? 1.0F : var3);
    }

    public void j(Object[] var1) {
        TickEvent27 var2 = (TickEvent27)var1[0];
        long var3 = (Long)var1[1];
        var3 = 32576321123276L ^ var3;
        long var5 = var3 ^ 28748280522648L;
        long var7 = var3 ^ 54509508021424L;
        long var9 = var3 ^ 111761811171581L;
        if (this.h != null) {
            if (this.c == null) {
                this.c = TextureUtil.a(new Object[]{"assets/prestige/particles/glow.png", var5});
            }

            MatrixCameraData var10000 = var2.b;
            double var10001 = this.h.x;
            double var10002 = this.h.y;
            double var10003 = this.h.z;
            Object[] var10006 = new Object[]{null, null, null, null, var7};
            var10006[3] = var10003;
            var10006[2] = var10002;
            var10006[1] = var10001;
            var10006[0] = var10000;
            Vector3d var12 = RenderUtil.a_Vector3d(var10006);
            Matrix4f var13 = new Matrix4f();
            var13.translate((float)var12.x, (float)var12.y, (float)var12.z);
            Quaternionf var14 = new Quaternionf().identity();
            var14.rotateY(-var2.b.bC().getYaw() * (float) Math.PI / 180.0F);
            var14.rotateX(var2.b.bC().getPitch() * (float) Math.PI / 180.0F);
            var13.rotate(var14);
            float var15 = (float)(
                (float)(System.currentTimeMillis() - this.l) / 3000.0F + Math.sin((float)(System.currentTimeMillis() - this.l) / 2500.0F) / 10.0
            );
            float var16 = -0.05F;
            float var17 = this.j / 4.0F;
            boolean var18 = true;
            Color var19 = (Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue();
            int var20 = var19.getRed();
            int var21 = var19.getGreen();
            int var22 = var19.getBlue();

            for (int var23 = 0; var23 < 4; var23++) {
                float var24 = var15 * 360.0F;
                float var25 = var24 + 150.0F;

                for (float var26 = var24; var26 < var24 + 100.0F; var26++) {
                    float var60 = var24 - 90.0F;
                    Object[] var10004 = new Object[]{null, null, var25};
                    var10004[1] = var60;
                    var10004[0] = var26;
                    float var27 = d(var10004);
                    float var28 = this.i * this.g;
                    double var29 = Math.toRadians(var26);
                    double var31 = Math.sin(var29 * 1.5);
                    double var33 = Math.cos(var29);
                    double var35 = Math.sin(var29);
                    double var37 = var17 + var31 * 0.25;
                    float var39 = 0.15F * (Math.max(0.15F, var18 ? var27 : (1.0F - var27) * 1.5F) + 0.45F);
                    float var40 = var39 * (1.7F + (0.5F - var16) * 2.0F);
                    if (var18) {
                        var27 = 1.0F - var27;
                    }

                    float var41 = 5.0F;
                    float var42 = 1.0F - (float)Math.pow(var27, var41);
                    float var43 = var40 * var42 * this.g;
                    float var44 = 0.5F;
                    float var45 = 1.0F - (float)Math.pow(var27, var44);
                    float var46 = var40 * var45 * this.g;
                    float var47 = (float)(var33 * var28);
                    float var48 = (float)((var37 - 0.9F) * this.g);
                    float var49 = (float)(var35 * var28);
                    float var50 = Math.min(var46 * 2.5F, 1.0F);
                    int var51 = (int)(var20 * (1.0F - var50) + 255.0F * var50);
                    int var52 = (int)(var21 * (1.0F - var50) + 255.0F * var50);
                    int var53 = (int)(var22 * (1.0F - var50) + 255.0F * var50);
                    float var54 = var51 / 255.0F;
                    float var55 = var52 / 255.0F;
                    float var56 = var53 / 255.0F;
                    Color var57 = new Color(var54, var55, var56, var46);
                    RenderHelper8 var59 = var2.a;
                    MatrixCameraData var61 = var2.b;
                    FontRenderer var62 = this.c;
                    float var63 = var47 - var43 / 2.0F;
                    float var10005 = var48 - var43 / 2.0F;
                    float var10007 = var47 + var43 / 2.0F;
                    float var10008 = var48 + var43 / 2.0F;
                    Object[] var10018 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, var9};
                    var10018[15] = true;
                    var10018[14] = 0.0F;
                    var10018[13] = var57;
                    var10018[12] = var57;
                    var10018[11] = var57;
                    var10018[10] = var57;
                    var10018[9] = var49;
                    var10018[8] = var10008;
                    var10018[7] = var10007;
                    var10018[6] = var49;
                    var10018[5] = var10005;
                    var10018[4] = var63;
                    var10018[3] = var62;
                    var10018[2] = var13;
                    var10018[1] = var61;
                    var10018[0] = var59;
                    RoundedTextureShader.o(var10018);
                }

                var15 *= -1.35F;
                var18 = !var18;
                var17 += this.j / 4.0F;
            }
        }
    }

    public void k(Object[] var1) {
        TickEvent27 var2 = (TickEvent27)var1[0];
        long var3 = (Long)var1[1];
        var3 = 32576321123276L ^ var3;
        long var5 = var3 ^ 47721645099483L;
        long var7 = var3 ^ 897906434803L;
        long var9 = var3 ^ 93355001628350L;
        long var11 = var3 ^ 60121678075077L;
        if (this.b == null) {
            this.b = TextureUtil.a(new Object[]{"assets/prestige/icons/target.png", var5});
        }

        MatrixCameraData var10000 = var2.b;
        double var10001 = this.h.x;
        double var10002 = this.h.y;
        double var10003 = this.h.z;
        Object[] var10006 = new Object[]{null, null, null, null, var7};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var10000;
        Vector3d var14 = RenderUtil.a_Vector3d(var10006);
        Matrix4f var15 = new Matrix4f();
        var15.translate((float)var14.x, (float)var14.y, (float)var14.z);
        Quaternionf var16 = new Quaternionf().identity();
        var16.rotateY(-var2.b.bC().getYaw() * (float) Math.PI / 180.0F);
        var16.rotateX((float)(var2.b.bC().getPitch() * (float) Math.PI / 180.0F + Math.sin(this.k / 50.0F) / 4.0));
        var16.rotateZ((float)Math.toRadians(this.k));
        var15.rotate(var16);
        float var17 = (this.j / 2.0F + 0.5F) * 80.0F / 100.0F * this.g * 0.85F;
        float var18 = var17 / 2.0F;
        int var25 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).brighter().getRGB();
        int var30 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).getRGB();
        float var34 = (float)(Math.sin(System.currentTimeMillis() * 0.001) * 0.5 + 0.5);
        var10006 = new Object[]{null, null, null, var11};
        var10006[2] = var34;
        var10006[1] = var30;
        var10006[0] = var25;
        Color var19 = this.a(var10006);
        int var26 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).brighter().getRGB();
        int var31 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).getRGB();
        float var35 = (float)(Math.sin(System.currentTimeMillis() * 0.001 + 1.0) * 0.5 + 0.5);
        var10006 = new Object[]{null, null, null, var11};
        var10006[2] = var35;
        var10006[1] = var31;
        var10006[0] = var26;
        Color var20 = this.a(var10006);
        int var27 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).brighter().getRGB();
        int var32 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).getRGB();
        float var36 = (float)(Math.sin(System.currentTimeMillis() * 0.001 + 2.0) * 0.5 + 0.5);
        var10006 = new Object[]{null, null, null, var11};
        var10006[2] = var36;
        var10006[1] = var32;
        var10006[0] = var27;
        Color var21 = this.a(var10006);
        int var28 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).brighter().getRGB();
        int var33 = ((Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()).getRGB();
        float var37 = (float)(Math.sin(System.currentTimeMillis() * 0.001 + 3.0) * 0.5 + 0.5);
        var10006 = new Object[]{null, null, null, var11};
        var10006[2] = var37;
        var10006[1] = var33;
        var10006[0] = var28;
        Color var22 = this.a(var10006);
        RenderHelper8 var24 = var2.a;
        MatrixCameraData var29 = var2.b;
        FontRenderer var38 = this.b;
        float var10004 = -var18;
        float var10005 = -var18;
        Object[] var10018 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, var9};
        var10018[15] = false;
        var10018[14] = 0.0F;
        var10018[13] = var22;
        var10018[12] = var21;
        var10018[11] = var20;
        var10018[10] = var19;
        var10018[9] = 0.0F;
        var10018[8] = var18;
        var10018[7] = var18;
        var10018[6] = 0.0F;
        var10018[5] = var10005;
        var10018[4] = var10004;
        var10018[3] = var38;
        var10018[2] = var15;
        var10018[1] = var29;
        var10018[0] = var24;
        RoundedTextureShader.o(var10018);
    }

    public static float d(Object[] var0) {
        float var2 = (Float)var0[0];
        float var1 = (Float)var0[1];
        float var3 = (Float)var0[2];
        return (var2 - var1) / (var3 - var1);
    }

    private float e(Object[] var1) {
        float var3 = (Float)var1[0];
        float var4 = (Float)var1[1];
        float var2 = (Float)var1[2];
        return var3 + var2 * (var4 - var3);
    }

    private Color a(Object[] var1) {
        int var2 = (Integer)var1[0];
        int var3 = (Integer)var1[1];
        float var6 = (Float)var1[2];
        int var7 = (int)((var2 >> 16 & 0xFF) * (1.0F - var6) + (var3 >> 16 & 0xFF) * var6);
        int var8 = (int)((var2 >> 8 & 0xFF) * (1.0F - var6) + (var3 >> 8 & 0xFF) * var6);
        int var9 = (int)((var2 & 0xFF) * (1.0F - var6) + (var3 & 0xFF) * var6);
        return new Color(var7 << 16 | var8 << 8 | var9);
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
