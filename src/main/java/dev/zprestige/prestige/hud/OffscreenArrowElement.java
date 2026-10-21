package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.RenderState;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class OffscreenArrowElement extends HudElement {
    private static final float m = 16.0F;
    private static final float a = 9.0F;
    private static final float c = 5.0F;
    private static final float d = 4.5F;
    private static final float e = 45.0F;
    private static final float f = 45.0F;
    private static final float g = 5.0F;
    private static final float h = 10.0F;
    private static final float i = 28.0F;
    private static final float j = 0.18F;
    private float k;
    private float l;
    private boolean n;
    private long o;

    public OffscreenArrowElement(long var1) {
        super("Offscreen Arrow", "Points to the closest player when they're off-screen", (20581395994039L));
        var1 = 0L;
        var1 = 84397816325920L ^ var1;
        long var3 = var1 ^ 103859272856215L;
        this.k = 0.0F;
        this.l = 0.0F;
        this.n = false;
        this.o = 0L;
    }

    @Override
    public void l(Object[] var1) {
        Matrix4f var5 = (Matrix4f)var1[2];
        long var3 = (Long)var1[3];
        long var7 = var3 ^ 27807340928064L;
        long var9 = var3 ^ 23705772420734L;
        long var11 = var3 ^ 36001204938826L;
        long var13 = var3 ^ 133628840384110L;
        Event.b();
        Object[] var10005 = new Object[]{null, 0.0F};
        var10005[0] = 0.0F;
        this.i(var10005);
        if (b.player != null && b.world != null) {
            long var16 = System.currentTimeMillis();
            float var18 = this.o == 0L ? 0.016F : (float)(var16 - this.o) / 1000.0F;
            this.o = var16;
            var18 = Math.min(var18, 0.05F);
            PlayerEntity var19 = CombatUtil.a_class_1657(new Object[]{var13});
            boolean var20 = false;
            float var21 = 0.0F;
            if (var19 != null) {
                Vec3d var22 = var19.getEyePos();
                Vec3d var23 = b.player.getEyePos();
                double var24 = var22.x - var23.x;
                double var26 = var22.y - var23.y;
                double var28 = var22.z - var23.z;
                double var30 = Math.sqrt(var24 * var24 + var28 * var28);
                double var32 = Math.toDegrees(Math.atan2(-var24, var28));
                double var34 = Math.toDegrees(-Math.atan2(var26, var30));
                double var36 = MathHelper.wrapDegrees(var32 - b.player.getYaw());
                double var38 = var34 - b.player.getPitch();
                boolean var40 = Math.abs(var36) < 45.0 && Math.abs(var38) < 45.0;
                var20 = !var40;
                var21 = (float)var36;
            }

            if (!this.n && var20) {
                this.k = var21;
                this.n = true;
            }

            float var42 = 1.0F - (float)Math.exp(-5.0F * var18);
            float var43 = var20 ? 10.0F : 28.0F;
            float var44 = 1.0F - (float)Math.exp(-var43 * var18);
            float var25 = MathHelper.wrapDegrees(var21 - this.k);
            this.k = MathHelper.wrapDegrees(this.k + var25 * var42);
            float var45 = var20 ? 1.0F : 0.0F;
            this.l = this.l + (var45 - this.l) * var44;
            if (this.l < 0.01F) {
                this.n = false;
            } else {
                float var27 = (float)Math.sqrt(var5.m00() * var5.m00() + var5.m01() * var5.m01() + var5.m02() * var5.m02());
                float var46 = b.getWindow().getScaledWidth() / 2.0F / var27;
                float var29 = b.getWindow().getScaledHeight() / 2.0F / var27;
                double var47 = Math.toRadians(this.k);
                float var48 = (float)Math.sin(var47);
                float var33 = -((float)Math.cos(var47));
                float var49 = -var33;
                Color var50 = MathHelper4.d(new Object[]{var7});
                float var37 = 1.0F + 0.18F * (float)Math.sin(var16 * 0.012);
                float var10001 = 0.1F * this.l * var37;
                Object[] var10004 = new Object[]{null, null, var9};
                var10004[1] = var10001;
                var10004[0] = var50;
                int var51 = a(var10004);
                var10001 = 0.22F * this.l * var37;
                var10004 = new Object[]{null, null, var9};
                var10004[1] = var10001;
                var10004[0] = var50;
                int var39 = a(var10004);
                Object[] var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var11};
                var10014[11] = var51;
                var10014[10] = 6.9F;
                var10014[9] = 7.4F;
                var10014[8] = 13.8F;
                var10014[7] = 18.4F;
                var10014[6] = var48;
                var10014[5] = var49;
                var10014[4] = var33;
                var10014[3] = var48;
                var10014[2] = var29;
                var10014[1] = var46;
                var10014[0] = var5;
                r(var10014);
                var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var11};
                var10014[11] = var39;
                var10014[10] = 5.7F;
                var10014[9] = 6.2F;
                var10014[8] = 11.4F;
                var10014[7] = 17.2F;
                var10014[6] = var48;
                var10014[5] = var49;
                var10014[4] = var33;
                var10014[3] = var48;
                var10014[2] = var29;
                var10014[1] = var46;
                var10014[0] = var5;
                r(var10014);
                var10001 = this.l;
                var10004 = new Object[]{null, null, var9};
                var10004[1] = var10001;
                var10004[0] = var50;
                int var52 = a(var10004);
                var10014 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, var11};
                var10014[11] = var52;
                var10014[10] = 4.5F;
                var10014[9] = 5.0F;
                var10014[8] = 9.0F;
                var10014[7] = 16.0F;
                var10014[6] = var48;
                var10014[5] = var49;
                var10014[4] = var33;
                var10014[3] = var48;
                var10014[2] = var29;
                var10014[1] = var46;
                var10014[0] = var5;
                r(var10014);
            }
        }
    }

    private static void r(Object[] var0) {
        Matrix4f var4 = (Matrix4f)var0[0];
        float var6 = (Float)var0[1];
        float var5 = (Float)var0[2];
        float var10 = (Float)var0[3];
        float var12 = (Float)var0[4];
        float var3 = (Float)var0[5];
        float var11 = (Float)var0[6];
        float var13 = (Float)var0[7];
        float var2 = (Float)var0[8];
        float var1 = (Float)var0[9];
        float var14 = (Float)var0[10];
        int var7 = (Integer)var0[11];
        long var8 = (Long)var0[12];
        var8 = 84397816325920L ^ var8;
        long var15 = var8 ^ 130579662978177L;
        float var17 = var6 + var10 * var13;
        float var18 = var5 + var12 * var13;
        float var19 = -var10 * var2;
        float var20 = -var12 * var2;
        float var21 = var3 * var1;
        float var22 = var11 * var1;
        float var23 = var17 + var19 + var21;
        float var24 = var18 + var20 + var22;
        float var25 = var17 + var19 - var21;
        float var26 = var18 + var20 - var22;
        float var27 = var17 - var10 * var14;
        float var28 = var18 - var12 * var14;
        Object[] var10010 = new Object[]{null, null, null, null, null, null, null, null, var15};
        var10010[7] = var7;
        var10010[6] = var24;
        var10010[5] = var23;
        var10010[4] = var28;
        var10010[3] = var27;
        var10010[2] = var18;
        var10010[1] = var17;
        var10010[0] = var4;
        RenderState.k(var10010);
        var10010 = new Object[]{null, null, null, null, null, null, null, null, var15};
        var10010[7] = var7;
        var10010[6] = var28;
        var10010[5] = var27;
        var10010[4] = var26;
        var10010[3] = var25;
        var10010[2] = var18;
        var10010[1] = var17;
        var10010[0] = var4;
        RenderState.k(var10010);
    }

    private static int a(Object[] var0) {
        Color var2 = (Color)var0[0];
        float var1 = (Float)var0[1];
        long var3 = (Long)var0[2];
        var3 = 84397816325920L ^ var3;
        long var5 = var3 ^ 111892939938721L;
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var1;
        int var7 = Math.round(255.0F * c(var10004));
        return new Color(var2.getRed(), var2.getGreen(), var2.getBlue(), var7).getRGB();
    }

    private static float c(Object[] var0) {
        float var3 = (Float)var0[0];
        return var3 < 0.0F ? 0.0F : Math.min(var3, 1.0F);
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
