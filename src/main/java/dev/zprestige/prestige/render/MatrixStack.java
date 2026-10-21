package dev.zprestige.prestige.render;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public class MatrixStack implements RenderContract2 {
    private static final MinecraftClient b = MinecraftClient.getInstance();
    public static net.minecraft.client.gui.DrawContext a;
    public static net.minecraft.client.util.math.MatrixStack c;
    public static Matrix4f d = new Matrix4f();
    public static Matrix4f e = new Matrix4f();
    public static Matrix4f f = new Matrix4f();
    public static RenderHelper8 g;
    public static MatrixCameraData h;
    private static final int[] i = new int[4];
    private static boolean j = false;
    private static final Matrix4f k = new Matrix4f();
    private static final Matrix4f l = new Matrix4f();
    private static final Vector3f m = new Vector3f();
    private static final Vector4f n = new Vector4f();
    private static long o = Long.MIN_VALUE;
    private static long p = 0L;
    private static final Matrix4f q = new Matrix4f();
    private static final float[] r = new float[3];
    private static int s = 1;
    private static final Vector4f t = new Vector4f();
    private static final Color u = new Color(0, 0, 0, 0);
    private static final Color v = new Color(0, 0, 0, 200);
    private static float w = Float.NaN;
    private static Color x = u;
    private static final Identifier y = SpriteAtlasTexture.ITEMS_ATLAS_TEXTURE;

    public static void a(Object[] var0) {
        GL11.glGetIntegerv(2978, i);
        j = true;
    }

    private static int[] a_intArr(Object[] var0) {
        if (!j) {
            GL11.glGetIntegerv(2978, i);
            j = true;
        }

        return i;
    }

    private static long a_long(Object[] var0) {
        Event.b();
        long var4 = RenderUtil.a_long__(new Object[0]);
        if (var4 != o) {
            o = var4;
            p = System.currentTimeMillis();
        }

        return p;
    }

    public static Vec3d a_class_243_______________(Object[] var0) {
        Vec3d var1 = (Vec3d)var0[0];
        long var2 = (Long)var0[1];
        var2 = 21468203664412L ^ var2;
        long var4 = var2 ^ 14330539846531L;
        Camera var6 = b.getEntityRenderDispatcher().camera;
        int var7 = b.getWindow().getHeight();
        int[] var8 = a_intArr(new Object[]{var4});
        Vector3f var9 = m;
        Vec3d var10 = var6.getCameraPos();
        double var11 = var1.x - var10.x;
        double var13 = var1.y - var10.y;
        double var15 = var1.z - var10.z;
        Vector4f var17 = n.set((float)var11, (float)var13, (float)var15, 1.0F).mul(f);
        Matrix4f var18 = k.set(d);
        Matrix4f var19 = l.set(e);
        var18.mul(var19).project(var17.x(), var17.y(), var17.z(), var8, var9);
        return new Vec3d(var9.x / b.getWindow().getScaleFactor(), (var7 - var9.y) / b.getWindow().getScaleFactor(), var9.z);
    }

    private static double a_double_________(Object[] var0) {
        double var1 = (Double)var0[0];
        double var3 = (Double)var0[1];
        long var5 = (Long)var0[2];
        var5 = 21468203664412L ^ var5;
        long var7 = var5 ^ 31231179932360L;
        return var3 + (var1 - var3) * a_float_______________(new Object[]{var7});
    }

    public static Vec3d b(Object[] var0) {
        Entity var3 = (Entity)var0[0];
        long var1 = (Long)var0[1];
        var1 = 21468203664412L ^ var1;
        long var4 = var1 ^ 8285271159347L;
        long var6 = var1 ^ 100890573525522L;
        Vec3d var8 = PositionUtil.c(new Object[]{var3, var4});
        double var10000 = var3.getX();
        double var10001 = var8.x;
        Object[] var10004 = new Object[]{null, null, var6};
        var10004[1] = var10001;
        var10004[0] = var10000;
        double var9 = a_double_________(var10004);
        var10000 = var3.getY();
        var10001 = var8.y;
        var10004 = new Object[]{null, null, var6};
        var10004[1] = var10001;
        var10004[0] = var10000;
        double var11 = a_double_________(var10004);
        var10000 = var3.getZ();
        var10001 = var8.z;
        var10004 = new Object[]{null, null, var6};
        var10004[1] = var10001;
        var10004[0] = var10000;
        double var13 = a_double_________(var10004);
        return new Vec3d(var9, var11, var13);
    }

    public static Color b_Color_____(Object[] var0) {
        Color var2 = (Color)var0[0];
        int var1 = (Integer)var0[1];
        int var3 = (Integer)var0[2];
        long var4 = (Long)var0[3];
        var4 = 21468203664412L ^ var4;
        long var6 = var4 ^ 26718476449320L;
        Event.b();
        int var9 = var2.getRGB();
        if (var9 != s) {
            Color.RGBtoHSB(var2.getRed(), var2.getGreen(), var2.getBlue(), r);
            s = var9;
        }

        float var10 = 0.5F + 0.5F * Math.abs(((float)(a_long(new Object[]{var6}) % 2000L) / 1000.0F + (float)var1 / var3 * 2.0F) % 2.0F - 1.0F);
        return new Color(Color.HSBtoRGB(r[0], r[1], Math.min(var10, 1.0F)));
    }

    public static Vec3d c(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 21468203664412L ^ var1;
        long var3 = var1 ^ 124429728496769L;
        return PositionUtil.a(new Object[]{b.getCameraEntity(), var3});
    }

    public static void c_void_(Object[] var0) {
        Matrix4f var4 = (Matrix4f)var0[0];
        float var3 = (Float)var0[1];
        float var2 = (Float)var0[2];
        float var7 = (Float)var0[3];
        Color var1 = (Color)var0[4];
        long var5 = (Long)var0[5];
        var5 = 21468203664412L ^ var5;
        long var8 = var5 ^ 87713662938147L;
        int var10004 = var1.getRGB();
        Object[] var10007 = new Object[]{null, null, null, null, null, var8};
        var10007[4] = var10004;
        var10007[3] = var7;
        var10007[2] = var2;
        var10007[1] = var3;
        var10007[0] = var4;
        RenderState.y(var10007);
    }

    public static void e(Object[] var0) {
        Matrix4f var2 = (Matrix4f)var0[0];
        float var3 = (Float)var0[1];
        float var7 = (Float)var0[2];
        float var1 = (Float)var0[3];
        Color var6 = (Color)var0[4];
        long var4 = (Long)var0[5];
        var4 = 21468203664412L ^ var4;
        long var8 = var4 ^ 74669382892554L;
        int var10005 = var6.getRGB();
        Object[] var10008 = new Object[]{null, null, null, null, null, null, var8};
        var10008[5] = var10005;
        var10008[4] = 1.0F;
        var10008[3] = var1;
        var10008[2] = var7;
        var10008[1] = var3;
        var10008[0] = var2;
        RenderState.A(var10008);
    }

    public static void l(Object[] var0) {
        Matrix4f var2 = (Matrix4f)var0[0];
        float var9 = (Float)var0[1];
        float var3 = (Float)var0[2];
        float var1 = (Float)var0[3];
        float var8 = (Float)var0[4];
        Color var7 = (Color)var0[5];
        float var6 = (Float)var0[6];
        long var4 = (Long)var0[7];
        var4 = 21468203664412L ^ var4;
        long var10 = var4 ^ 14944260780811L;
        RenderHelper8 var10000 = g;
        MatrixCameraData var10001 = h;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, null, var10};
        var10012[9] = 0.25F;
        var10012[8] = var6;
        var10012[7] = var7;
        var10012[6] = var8;
        var10012[5] = var1;
        var10012[4] = var3;
        var10012[3] = var9;
        var10012[2] = var2;
        var10012[1] = var10001;
        var10012[0] = var10000;
        RenderState.C(var10012);
    }

    public static void n(Object[] var0) {
        Matrix4f var2 = (Matrix4f)var0[0];
        float var7 = (Float)var0[1];
        float var6 = (Float)var0[2];
        float var1 = (Float)var0[3];
        float var9 = (Float)var0[4];
        Color var5 = (Color)var0[5];
        float var8 = (Float)var0[6];
        long var3 = (Long)var0[7];
        var3 = 21468203664412L ^ var3;
        long var10 = var3 ^ 76903503166082L;
        Object[] var10009 = new Object[]{null, null, null, null, null, var5, t.set(var8), var10};
        var10009[4] = var9;
        var10009[3] = var1;
        var10009[2] = var6;
        var10009[1] = var7;
        var10009[0] = var2;
        RenderState.u(var10009);
    }

    public static void p(Object[] var0) {
        Matrix4f var4 = (Matrix4f)var0[0];
        float var3 = (Float)var0[1];
        float var5 = (Float)var0[2];
        float var8 = (Float)var0[3];
        float var9 = (Float)var0[4];
        float var11 = (Float)var0[5];
        Color var7 = (Color)var0[6];
        Color var12 = (Color)var0[7];
        Color var6 = (Color)var0[8];
        Color var10 = (Color)var0[9];
        long var1 = (Long)var0[10];
        var1 = 21468203664412L ^ var1;
        long var13 = var1 ^ 80001115006813L;
        Object[] var10012 = new Object[]{null, null, null, null, null, var7, var12, var6, var10, t.set(var11), var13};
        var10012[4] = var9;
        var10012[3] = var8;
        var10012[2] = var5;
        var10012[1] = var3;
        var10012[0] = var4;
        RenderState.w(var10012);
    }

    public static void r(Object[] var0) {
        Matrix4f var3 = (Matrix4f)var0[0];
        float var6 = (Float)var0[1];
        float var4 = (Float)var0[2];
        float var5 = (Float)var0[3];
        float var2 = (Float)var0[4];
        Color var1 = (Color)var0[5];
        long var7 = (Long)var0[6];
        var7 = 21468203664412L ^ var7;
        long var9 = var7 ^ 114805589393942L;
        Object[] var10008 = new Object[]{null, null, null, null, null, var1, var9};
        var10008[4] = var2;
        var10008[3] = var5;
        var10008[2] = var4;
        var10008[1] = var6;
        var10008[0] = var3;
        RenderState.l(var10008);
    }

    public static void v(Object[] var0) {
        float var7 = (Float)var0[0];
        float var4 = (Float)var0[1];
        float var6 = (Float)var0[2];
        float var9 = (Float)var0[3];
        Matrix4f var5 = (Matrix4f)var0[4];
        FontRenderer var8 = (FontRenderer)var0[5];
        Color var1 = (Color)var0[6];
        long var2 = (Long)var0[7];
        var2 = 21468203664412L ^ var2;
        long var10 = var2 ^ 30766224687645L;
        RenderHelper8 var10000 = g;
        MatrixCameraData var10001 = h;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, var5, var1, var10};
        var10011[6] = var9;
        var10011[5] = var6;
        var10011[4] = var4;
        var10011[3] = var7;
        var10011[2] = var8;
        var10011[1] = var10001;
        var10011[0] = var10000;
        RoundedTextureShader.d(var10011);
    }

    public static void x(Object[] var0) {
        float var3 = (Float)var0[0];
        float var14 = (Float)var0[1];
        float var4 = (Float)var0[2];
        float var12 = (Float)var0[3];
        float var5 = (Float)var0[4];
        float var6 = (Float)var0[5];
        float var2 = (Float)var0[6];
        float var8 = (Float)var0[7];
        float var13 = (Float)var0[8];
        Matrix4f var9 = (Matrix4f)var0[9];
        FontRenderer var7 = (FontRenderer)var0[10];
        Color var1 = (Color)var0[11];
        long var10 = (Long)var0[12];
        var10 = 21468203664412L ^ var10;
        long var15 = var10 ^ 56482848374767L;
        RenderHelper8 var10000 = g;
        MatrixCameraData var10001 = h;
        Object[] var10016 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, new Vector4f(var13), var1, var15};
        var10016[11] = var8;
        var10016[10] = var2;
        var10016[9] = var6;
        var10016[8] = var5;
        var10016[7] = var12;
        var10016[6] = var4;
        var10016[5] = var14;
        var10016[4] = var3;
        var10016[3] = var7;
        var10016[2] = var9;
        var10016[1] = var10001;
        var10016[0] = var10000;
        RoundedTextureShader.E(var10016);
    }

    public static boolean a_boolean_______________________________________(Object[] var0) {
        ItemStack var4 = (ItemStack)var0[0];
        float var3 = (Float)var0[1];
        float var5 = (Float)var0[2];
        float var6 = (Float)var0[3];
        Matrix4f var7 = (Matrix4f)var0[4];
        long var1 = (Long)var0[5];
        var1 = 21468203664412L ^ var1;
        long var8 = var1 ^ 9522236869216L;
        long var10 = var1 ^ 128083499229170L;
        long var12 = var1 ^ 104337839420320L;
        long var14 = var1 ^ 26661011895063L;
        long var16 = var1 ^ 126440491870421L;
        long var18 = var1 ^ 89702905285417L;
        long var20 = var1 ^ 22379330228830L;
        long var22 = var1 ^ 53180422251226L;
        Event.b();
        Identifier var25 = var4.get(DataComponentTypes.ITEM_MODEL);
        if (var25 == null) {
            var25 = Registries.ITEM.getId(var4.getItem());
        }

        SpriteIdentifier var26 = new SpriteIdentifier(y, Identifier.of(var25.getNamespace(), "item/" + var25.getPath()));
        Sprite var27 = b.getAtlasManager().getSprite(var26);
        if (var27.getContents().getId().getPath().equals("missingno")) {
            return false;
        } else {
            Identifier var28 = var27.getAtlasId();
            float var29 = var27.getMinU();
            float var30 = var27.getMaxU();
            float var31 = var27.getMinV();
            float var32 = var27.getMaxV();
            float var33 = 16.0F * var6;
            float var10004 = var29 * var33;
            float var10005 = var33 - var32 * var33;
            float var10006 = (var30 - var29) * var33;
            float var10007 = (var32 - var31) * var33;
            Object[] var10014 = new Object[]{
                null, null, null, null, null, null, null, null, null, var7, FontRenderer.d_FontRenderer(new Object[]{var28, var18}), Color.WHITE, var22
            };
            var10014[8] = 0.0F;
            var10014[7] = var10007;
            var10014[6] = var10006;
            var10014[5] = var10005;
            var10014[4] = var10004;
            var10014[3] = var33;
            var10014[2] = var33;
            var10014[1] = var5;
            var10014[0] = var3;
            x(var10014);
            if (var4.getCount() > 1) {
                String var34 = String.valueOf(var4.getCount());
                Font var10000 = Prestige.c.a(new Object[]{var12});
                float var10003 = var3 + var33 - Prestige.c.a(new Object[]{var12}).a_float______________(new Object[]{var34, var10}) * var6;
                var10004 = var5 + var33 - Prestige.c.a(new Object[]{var12}).c_float___(new Object[]{var14}) * var6;
                Object[] var10009 = new Object[]{null, null, null, null, null, Color.WHITE, var16};
                var10009[4] = var6;
                var10009[3] = var10004;
                var10009[2] = var10003;
                var10009[1] = var34;
                var10009[0] = var7;
                var10000.w(var10009);
            }

            if (var4.isItemBarVisible()) {
                int var37 = (int)(var3 + 2.0F * var6);
                int var35 = (int)(var5 + 16.0F * var6);
                float var10001 = var37;
                float var10002 = var35;
                float var40 = var37 + 13.0F * var6;
                var10004 = var35 + var6;
                Color var45 = v;
                Object[] var48 = new Object[]{null, null, null, null, null, null, null, var20};
                var48[6] = 2.0F;
                var48[5] = var45;
                var48[4] = var10004;
                var48[3] = var40;
                var48[2] = var10002;
                var48[1] = var10001;
                var48[0] = var7;
                n(var48);
                var10001 = var37;
                var10002 = var35;
                var40 = var37 + var4.getItemBarStep() * var6;
                var10004 = var35 + var6;
                int var47 = var4.getItemBarColor();
                Object[] var10010 = new Object[]{null, var8};
                var10010[0] = var47;
                Color var46 = new Color(a_int______________________(var10010));
                var48 = new Object[]{null, null, null, null, null, null, null, var20};
                var48[6] = 2.0F;
                var48[5] = var46;
                var48[4] = var10004;
                var48[3] = var40;
                var48[2] = var10002;
                var48[1] = var10001;
                var48[0] = var7;
                n(var48);
            }

            return true;
        }
    }

    public static int a_int______________________(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 | 0xFF000000;
    }

    public static float a_float_______________(Object[] var0) {
        return b.getRenderTickCounter().getTickProgress(false);
    }

    static {
        short var10000 = 9696;
        long var10001 = 3676374455798618111L;
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
