package dev.zprestige.prestige.world;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class WorldUtil implements RenderContract2 {
    public static boolean c(Object[] var0) {
        RenderBatch var3 = (RenderBatch)var0[0];
        float var4 = (Float)var0[1];
        float var1 = (Float)var0[2];
        float var2 = (Float)var0[3];
        long var5 = (Long)var0[4];
        var5 = 115517686541580L ^ var5;
        long var7 = var5 ^ 95671329056734L;
        long var9 = var5 ^ 122797480979132L;
        long var11 = var5 ^ 57549318998263L;
        float var10000 = b.player.getYaw();
        float var10001 = var3.a(new Object[0]) + var1;
        float var10002 = Prestige.h.a(new Object[0]) * (1.1F - var4) * 7.0F / var2;
        Object[] var10007 = new Object[]{null, null, var7};
        var10007[1] = 1.0F;
        var10007[0] = 0.5F;
        var10002 *= RenderUtil5.a(var10007);
        Object[] var10005 = new Object[]{null, null, null, var11};
        var10005[2] = var10002;
        var10005[1] = var10001;
        var10005[0] = var10000;
        float var14 = c_float____(var10005);
        Event.b();
        var10001 = b.player.getPitch();
        var10002 = var3.b(new Object[0]) + var1 / 2.0F;
        float var10003 = Prestige.h.a(new Object[0]) * (1.1F - var4) * 7.0F / var2;
        Object[] var10008 = new Object[]{null, null, var7};
        var10008[1] = 1.0F;
        var10008[0] = 0.5F;
        var10003 *= RenderUtil5.a(var10008);
        Object[] var10006 = new Object[]{null, null, null, var11};
        var10006[2] = var10003;
        var10006[1] = var10002;
        var10006[0] = var10001;
        float var15 = c_float____(var10006);
        a_void________________________________(new Object[]{new RenderBatch(var14, var15), var9});
        return b.player.getYaw() - 5.0F < var3.a(new Object[0])
            && b.player.getYaw() + 5.0F > var3.a(new Object[0])
            && b.player.getPitch() - 5.0F < var3.b(new Object[0])
            && b.player.getPitch() + 5.0F > var3.b(new Object[0]);
    }

    public static boolean d(Object[] var0) {
        RenderBatch var6 = (RenderBatch)var0[0];
        float var1 = (Float)var0[1];
        float var4 = (Float)var0[2];
        float var5 = (Float)var0[3];
        long var2 = (Long)var0[4];
        var2 = 115517686541580L ^ var2;
        long var7 = var2 ^ 127730507624968L;
        long var9 = var2 ^ 84145391173482L;
        long var11 = var2 ^ 25836823158049L;
        Event.b();
        float var10001 = b.player.getPitch();
        float var10002 = var6.b(new Object[0]) + var4 / 2.0F;
        float var10003 = Prestige.h.a(new Object[0]) * (1.1F - var1) * 7.0F / var5;
        Object[] var10008 = new Object[]{null, null, var7};
        var10008[1] = 1.0F;
        var10008[0] = 0.5F;
        var10003 *= RenderUtil5.a(var10008);
        Object[] var10006 = new Object[]{null, null, null, var11};
        var10006[2] = var10003;
        var10006[1] = var10002;
        var10006[0] = var10001;
        float var14 = c_float____(var10006);
        a_void________________________________(new Object[]{new RenderBatch(b.player.getYaw(), var14), var9});
        return b.player.getPitch() - 1.0F < var6.b(new Object[0]) && b.player.getPitch() + 1.0F > var6.b(new Object[0]);
    }

    public static boolean f(Object[] var0) {
        RenderBatch var1 = (RenderBatch)var0[0];
        RenderBatch var5 = (RenderBatch)var0[1];
        float var2 = (Float)var0[2];
        Event.b();
        float var7 = Math.abs(Math.abs(var5.a(new Object[0])) - Math.abs(var1.a(new Object[0])));
        float var8 = Math.abs(var5.b(new Object[0]) - Math.abs(var1.b(new Object[0])));
        float var9 = (float)Math.sqrt(var7 * var7 + var8 * var8);
        return var9 <= var2;
    }

    public static float a(Object[] var0) {
        RenderBatch var1 = (RenderBatch)var0[0];
        float var4 = Math.abs(Math.abs(var1.a(new Object[0])) - Math.abs(b.player.getYaw()));
        float var5 = Math.abs(var1.b(new Object[0]) - Math.abs(b.player.getPitch()));
        return (float)Math.sqrt(var4 * var4 + var5 * var5);
    }

    public static HitResult a_class_239_(Object[] var0) {
        RenderBatch var3 = (RenderBatch)var0[0];
        long var1 = (Long)var0[1];
        var1 = 115517686541580L ^ var1;
        long var4 = var1 ^ 79751958745328L;
        return b.world
            .raycast(
                new RaycastContext(
                    b.player.getEyePos(),
                    a_class_243________________(new Object[]{var3, var4}).multiply(6.0).add(b.player.getEyePos()),
                    ShapeType.OUTLINE,
                    FluidHandling.NONE,
                    b.player
                )
            );
    }

    public static HitResult b(Object[] var0) {
        RenderBatch var2 = (RenderBatch)var0[0];
        float var1 = (Float)var0[1];
        long var3 = (Long)var0[2];
        var3 = 115517686541580L ^ var3;
        long var5 = var3 ^ 135189588091527L;
        return b.world
            .raycast(
                new RaycastContext(
                    b.player.getEyePos(),
                    a_class_243________________(new Object[]{var2, var5}).multiply(var1).add(b.player.getEyePos()),
                    ShapeType.OUTLINE,
                    FluidHandling.NONE,
                    b.player
                )
            );
    }

    public static Vec3d a_class_243________________(Object[] var0) {
        RenderBatch var3 = (RenderBatch)var0[0];
        float var4 = (float) (Math.PI / 180.0);
        float var5 = (float) Math.PI;
        float var6 = MathHelper.cos(-var3.a(new Object[0]) * var4 - var5);
        float var7 = MathHelper.sin(-var3.a(new Object[0]) * var4 - var5);
        float var8 = -MathHelper.cos(-var3.b(new Object[0]) * var4);
        float var9 = MathHelper.sin(-var3.b(new Object[0]) * var4);
        return new Vec3d(var7 * var8, var9, var6 * var8).normalize();
    }

    public static void a_void________________________________(Object[] var0) {
        RenderBatch var1 = (RenderBatch)var0[0];
        b.player.setYaw(var1.a(new Object[0]));
        b.player.setPitch(var1.b(new Object[0]));
    }

    public static RenderBatch a_RenderBatch_(Object[] var0) {
        Vec3d var1 = (Vec3d)var0[0];
        long var2 = (Long)var0[1];
        var2 = 115517686541580L ^ var2;
        long var4 = var2 ^ 114528207943322L;
        float var10000 = (float)var1.x;
        float var10001 = (float)var1.y;
        float var10002 = (float)var1.z;
        Object[] var10005 = new Object[]{null, null, null, var4};
        var10005[2] = var10002;
        var10005[1] = var10001;
        var10005[0] = var10000;
        return b_RenderBatch_____________(var10005);
    }

    public static RenderBatch b_RenderBatch_____________(Object[] var0) {
        float var2 = (Float)var0[0];
        float var1 = (Float)var0[1];
        float var5 = (Float)var0[2];
        long var3 = (Long)var0[3];
        var3 = 115517686541580L ^ var3;
        long var6 = var3 ^ 13979993745796L;
        Vec3d var8 = b.player.getEyePos();
        double var9 = var2 - var8.x;
        double var11 = var1 - var8.y;
        double var13 = var5 - var8.z;
        double var15 = Math.sqrt(var9 * var9 + var13 * var13);
        float var17 = (float)Math.toDegrees(Math.atan2(var13, var9)) - 90.0F;
        float var18 = (float)(-Math.toDegrees(Math.atan2(var11, var15)));
        float[] var19 = new float[]{b.player.getYaw(), b.player.getPitch()};
        float var10002 = var19[0];
        float var10003 = var17 - var19[0];
        Object[] var10006 = new Object[]{null, var6};
        var10006[0] = var10003;
        var10002 += b_float_____________________(var10006);
        var10003 = var19[1];
        float var10004 = var18 - var19[1];
        Object[] var10007 = new Object[]{null, var6};
        var10007[0] = var10004;
        return new RenderBatch(var10002, var10003 + b_float_____________________(var10007));
    }

    public static RenderBatch c_RenderBatch(Object[] var0) {
        RenderBatch var1 = (RenderBatch)var0[0];
        Vec3d var5 = (Vec3d)var0[1];
        float var4 = (Float)var0[2];
        long var2 = (Long)var0[3];
        var2 = 115517686541580L ^ var2;
        long var6 = var2 ^ 110052110534005L;
        float var10000 = var1.a(new Object[0]);
        float var10001 = var1.b(new Object[0]);
        float var10002 = (float)var5.x;
        float var10003 = (float)var5.y;
        float var10004 = (float)var5.z;
        Object[] var10008 = new Object[]{null, null, null, null, null, null, var6};
        var10008[5] = var4;
        var10008[4] = var10004;
        var10008[3] = var10003;
        var10008[2] = var10002;
        var10008[1] = var10001;
        var10008[0] = var10000;
        return e(var10008);
    }

    public static RenderBatch d_RenderBatch(Object[] var0) {
        RenderBatch var4 = (RenderBatch)var0[0];
        Vec3d var3 = (Vec3d)var0[1];
        long var1 = (Long)var0[2];
        var1 = 115517686541580L ^ var1;
        long var5 = var1 ^ 64373897445974L;
        double var7 = b.player.getEyePos().distanceTo(var3);
        float var9 = (float)MathHelper.clamp(0.18 / Math.max(var7, 1.0), 0.005, 0.15);
        float var10000 = -var9;
        Object[] var10004 = new Object[]{null, null, var5};
        var10004[1] = var9;
        var10004[0] = var10000;
        float var10 = RenderUtil5.a(var10004);
        var10000 = -var9;
        var10004 = new Object[]{null, null, var5};
        var10004[1] = var9;
        var10004[0] = var10000;
        float var11 = RenderUtil5.a(var10004);
        float var12 = MathHelper.clamp(var4.b(new Object[0]) + var11, -89.9F, 89.9F);
        RenderBatch var13 = new RenderBatch(var4.a(new Object[0]) + var10, var12);
        var13.c(new Object[]{var4.a_boolean________________________________________(new Object[0])});
        return var13;
    }

    public static RenderBatch e(Object[] var0) {
        float var5 = (Float)var0[0];
        float var2 = (Float)var0[1];
        float var6 = (Float)var0[2];
        float var1 = (Float)var0[3];
        float var4 = (Float)var0[4];
        float var3 = (Float)var0[5];
        long var7 = (Long)var0[6];
        var7 = 115517686541580L ^ var7;
        long var9 = var7 ^ 91919126308211L;
        Vec3d var12 = b.player.getEyePos();
        Event.b();
        double var13 = var6 - var12.x;
        double var15 = var1 - var12.y;
        double var17 = var4 - var12.z;
        double var19 = Math.sqrt(var13 * var13 + var17 * var17);
        float var21 = (float)Math.toDegrees(Math.atan2(var17, var13)) - 90.0F;
        float var22 = (float)(-Math.toDegrees(Math.atan2(var15, var19)));
        float var10000 = var21 - var5;
        Object[] var10003 = new Object[]{null, var9};
        var10003[0] = var10000;
        float var23 = b_float_____________________(var10003);
        boolean var24 = Math.abs(var23) > var3;
        var23 = MathHelper.clamp(var23, -var3, var3);
        float var25 = var5 + var23;
        float var10004 = var22 - var2;
        Object[] var10007 = new Object[]{null, var9};
        var10007[0] = var10004;
        RenderBatch var26 = new RenderBatch(var25, var2 + b_float_____________________(var10007));
        var26.c(new Object[]{var24});
        return var26;
    }

    public static RenderBatch f_RenderBatch(Object[] var0) {
        Entity var2 = (Entity)var0[0];
        Vec3d var1 = (Vec3d)var0[1];
        long var3 = (Long)var0[2];
        var3 = 115517686541580L ^ var3;
        long var5 = var3 ^ 69451085454382L;
        float var10001 = (float)var1.x;
        float var10002 = (float)var1.y;
        float var10003 = (float)var1.z;
        Object[] var10006 = new Object[]{null, null, null, null, var5};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var2;
        return g(var10006);
    }

    public static RenderBatch g(Object[] var0) {
        Entity var1 = (Entity)var0[0];
        float var5 = (Float)var0[1];
        float var2 = (Float)var0[2];
        float var6 = (Float)var0[3];
        long var3 = (Long)var0[4];
        var3 = 115517686541580L ^ var3;
        long var7 = var3 ^ 70033678902934L;
        Vec3d var9 = var1.getEyePos();
        double var10 = var5 - var9.x;
        double var12 = var2 - var9.y;
        double var14 = var6 - var9.z;
        double var16 = Math.sqrt(var10 * var10 + var14 * var14);
        float var18 = (float)Math.toDegrees(Math.atan2(var14, var10)) - 90.0F;
        float var19 = (float)(-Math.toDegrees(Math.atan2(var12, var16)));
        float[] var20 = new float[]{var1.getYaw(), var1.getPitch()};
        float var10002 = var20[0];
        float var10003 = var18 - var20[0];
        Object[] var10006 = new Object[]{null, var7};
        var10006[0] = var10003;
        var10002 += b_float_____________________(var10006);
        var10003 = var20[1];
        float var10004 = var19 - var20[1];
        Object[] var10007 = new Object[]{null, var7};
        var10007[0] = var10004;
        return new RenderBatch(var10002, var10003 + b_float_____________________(var10007));
    }

    public static float b_float_____________________(Object[] var0) {
        float var1 = (Float)var0[0];
        Event.b();
        float var5 = var1 % 360.0F;
        if (var5 >= 180.0F) {
            var5 -= 360.0F;
        }

        if (var5 < -180.0F) {
            var5 += 360.0F;
        }

        return var5;
    }

    public static float c_float____(Object[] var0) {
        float var4 = (Float)var0[0];
        float var5 = (Float)var0[1];
        float var3 = (Float)var0[2];
        long var1 = (Long)var0[3];
        var1 = 115517686541580L ^ var1;
        long var6 = var1 ^ 56689163446474L;
        float var10001 = var4 - var5;
        Object[] var10007 = new Object[]{null, null, null, var6};
        var10007[2] = 1.0F;
        var10007[1] = 0.0F;
        var10007[0] = var3;
        return var4 - var10001 * d_float___(var10007);
    }

    private static float d_float___(Object[] var0) {
        float var4 = (Float)var0[0];
        float var3 = (Float)var0[1];
        float var5 = (Float)var0[2];
        return var4 < var3 ? var3 : Math.min(var4, var5);
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
