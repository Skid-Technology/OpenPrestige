package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.type.GlPrimitive;
import dev.zprestige.prestige.world.WorldContract;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3d;
import org.joml.Vector4f;

public class RenderState {
    public static AtomicReference a = new AtomicReference();
    private static final ThreadLocal b = ThreadLocal.withInitial(Matrix4f::new);
    private static final Matrix4f c = new Matrix4f();
    private static final ThreadLocal d = ThreadLocal.withInitial(Vector3d::new);

    public static void a(Object[] var0) {
        if (a.get() == null) {
            a.compareAndSet(null, new RenderHelper30());
        }
    }

    public static RenderHelper30 a_RenderHelper30(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 18046302082221L ^ var1;
        long var3 = var1 ^ 92530132840722L;
        Event.b();
        RenderHelper30 var6 = (RenderHelper30)a.get();
        if (var6 == null) {
            a(new Object[]{var3});
            var6 = (RenderHelper30)a.get();
        }

        return var6;
    }

    public static void b(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 18046302082221L ^ var1;
        long var3 = var1 ^ 36463098488120L;
        long var5 = var1 ^ 72256607524280L;
        a_RenderHelper30(new Object[]{var3}).c(new Object[]{MatrixStack.h, var5});
    }

    public static void c(Object[] var0) {
        MatrixCameraData var5 = (MatrixCameraData)var0[0];
        RenderHelper8 var6 = (RenderHelper8)var0[1];
        ArrayList var1 = (ArrayList)var0[2];
        Color var2 = (Color)var0[3];
        long var3 = (Long)var0[4];
        var3 = 18046302082221L ^ var3;
        long var7 = var3 ^ 120665052813699L;
        long var9 = var3 ^ 55014381306608L;
        long var11 = var3 ^ 21383307439867L;
        long var13 = var3 ^ 71707964313541L;
        long var15 = var3 ^ 94290272811548L;
        long var17 = var3 ^ 79348456535108L;
        long var19 = var3 ^ 117686603046534L;
        if (!var1.isEmpty()) {
            Matrix4f var22 = ((Matrix4f)b.get()).identity();
            TextRenderer var23 = var6.a(new Object[]{UniformCache.e, var19});
            int var24 = a_int_________________________(new Object[]{var2, var7});
            var23.k(new Object[]{var9});
            Vector3d var25 = (Vector3d)d.get();

            for (Vec3d var27 : (Iterable<Vec3d>)(var1)) {
                double var10001 = var27.x;
                double var10002 = var27.y;
                double var10003 = var27.z;
                Object[] var10007 = new Object[]{null, null, null, null, var25, var17};
                var10007[3] = var10003;
                var10007[2] = var10002;
                var10007[1] = var10001;
                var10007[0] = var5;
                RenderUtil.b_Vector3d(var10007);
                float var29 = (float)var25.x;
                float var30 = (float)var25.y;
                float var10004 = (float)var25.z;
                var10007 = new Object[]{null, null, null, null, var13};
                var10007[3] = var10004;
                var10007[2] = var30;
                var10007[1] = var29;
                var10007[0] = var22;
                TextRenderer var10000 = var23.d_TextRenderer(var10007);
                Object[] var31 = new Object[]{null, var11};
                var31[0] = var24;
                var10000.e_TextRenderer(var31);
            }

            ((TextRenderer)var23.i_WorldContract(new Object[]{GlPrimitive.DEBUG_LINE_STRIP, var15})).h_TextRenderer(new Object[0]);
        }
    }

    public static void d(Object[] var0) {
        MatrixCameraData var10 = (MatrixCameraData)var0[0];
        RenderHelper8 var7 = (RenderHelper8)var0[1];
        float var3 = (Float)var0[2];
        float var6 = (Float)var0[3];
        float var4 = (Float)var0[4];
        float var5 = (Float)var0[5];
        Color var1 = (Color)var0[6];
        Color var2 = (Color)var0[7];
        long var8 = (Long)var0[8];
        var8 = 18046302082221L ^ var8;
        long var11 = var8 ^ 7964302088389L;
        Matrix4f var10002 = ((Matrix4f)b.get()).identity();
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, var1, var2, var11};
        var10011[6] = var5;
        var10011[5] = var4;
        var10011[4] = var6;
        var10011[3] = var3;
        var10011[2] = var10002;
        var10011[1] = var7;
        var10011[0] = var10;
        e(var10011);
    }

    public static void e(Object[] var0) {
        MatrixCameraData var10 = (MatrixCameraData)var0[0];
        RenderHelper8 var5 = (RenderHelper8)var0[1];
        Matrix4f var4 = (Matrix4f)var0[2];
        float var7 = (Float)var0[3];
        float var1 = (Float)var0[4];
        float var6 = (Float)var0[5];
        float var2 = (Float)var0[6];
        Color var3 = (Color)var0[7];
        Color var11 = (Color)var0[8];
        long var8 = (Long)var0[9];
        var8 = 18046302082221L ^ var8;
        long var12 = var8 ^ 28605165352853L;
        long var14 = var8 ^ 85800112683306L;
        long var16 = var8 ^ 19513781723737L;
        long var18 = var8 ^ 53621875101266L;
        long var20 = var8 ^ 108309128245100L;
        long var22 = var8 ^ 130256872118965L;
        long var24 = var8 ^ 79986997050927L;
        double var10001 = var7;
        double var10002 = var1;
        double var10003 = var6;
        Object[] var10006 = new Object[]{null, null, null, null, var12};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var10;
        Vector3d var27 = RenderUtil.a_Vector3d(var10006);
        TextRenderer var28 = var5.a(new Object[]{UniformCache.b, var24});
        Event.b();
        float var29 = (float)var27.x;
        float var30 = (float)var27.y;
        float var31 = (float)var27.z;
        int var32 = a_int_________________________(new Object[]{var3, var14});
        int var33 = a_int_________________________(new Object[]{var11, var14});
        var28.k(new Object[]{var16});
        Object[] var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var31;
        var10007[2] = var30;
        var10007[1] = var29;
        var10007[0] = var4;
        TextRenderer var10000 = var28.d_TextRenderer(var10007);
        Object[] var10004 = new Object[]{null, var18};
        var10004[0] = var32;
        var10000.e_TextRenderer(var10004);

        for (int var34 = 0; var34 <= 100; var34++) {
            float var35 = (float)((Math.PI * 2) * var34 / 100.0);
            float var36 = (float)Math.cos(var35);
            float var37 = (float)Math.sin(var35);
            float var38 = var29 + var2 * var36;
            float var39 = var31 + var2 * var37;
            var10007 = new Object[]{null, null, null, null, var20};
            var10007[3] = var39;
            var10007[2] = var30;
            var10007[1] = var38;
            var10007[0] = var4;
            var10000 = var28.d_TextRenderer(var10007);
            var10004 = new Object[]{null, var18};
            var10004[0] = var33;
            var10000.e_TextRenderer(var10004);
        }

        ((TextRenderer)var28.i_WorldContract(new Object[]{GlPrimitive.TRIANGLES_FAN, var22})).h_TextRenderer(new Object[0]);
    }

    public static void f(Object[] var0) {
        MatrixCameraData var3 = (MatrixCameraData)var0[0];
        RenderHelper8 var4 = (RenderHelper8)var0[1];
        float var8 = (Float)var0[2];
        float var2 = (Float)var0[3];
        float var5 = (Float)var0[4];
        float var7 = (Float)var0[5];
        float var1 = (Float)var0[6];
        float var6 = (Float)var0[7];
        Color var10 = (Color)var0[8];
        Color var9 = (Color)var0[9];
        long var11 = (Long)var0[10];
        var11 = 18046302082221L ^ var11;
        long var13 = var11 ^ 56965606282520L;
        long var15 = var11 ^ 114089820871591L;
        long var17 = var11 ^ 62071236788436L;
        long var19 = var11 ^ 27555148295391L;
        long var21 = var11 ^ 82784708152801L;
        long var23 = var11 ^ 52805101659756L;
        long var25 = var11 ^ 13433469854200L;
        long var27 = var11 ^ 107151155109026L;
        double var10001 = var8;
        double var10002 = var2;
        double var10003 = var5;
        Object[] var10006 = new Object[]{null, null, null, null, var13};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var3;
        Vector3d var30 = RenderUtil.a_Vector3d(var10006);
        Matrix4f var31 = ((Matrix4f)b.get()).identity();
        TextRenderer var32 = var4.a(new Object[]{UniformCache.b, var27});
        Event.b();
        float var33 = (float)var30.x;
        float var34 = (float)var30.y;
        float var35 = (float)var30.z;
        int var36 = a_int_________________________(new Object[]{var10, var15});
        int var37 = a_int_________________________(new Object[]{var9, var15}) & 16777215;
        var32.k(new Object[]{var17});

        for (int var38 = 0; var38 <= 100; var38++) {
            float var39 = (float)((Math.PI * 2) * var38 / 100.0);
            float var40 = (float)Math.cos(var39);
            float var41 = (float)Math.sin(var39);
            float var42 = var33 + (var7 + var1 / 2.0F + var6) * var40;
            float var43 = var35 + (var7 + var1 / 2.0F + var6) * var41;
            Object[] var10007 = new Object[]{null, null, null, null, var21};
            var10007[3] = var43;
            var10007[2] = var34;
            var10007[1] = var42;
            var10007[0] = var31;
            TextRenderer var10000 = var32.d_TextRenderer(var10007);
            Object[] var10004 = new Object[]{null, var19};
            var10004[0] = var37;
            var10000.e_TextRenderer(var10004);
            float var44 = var33 + (var7 + var1 / 2.0F) * var40;
            float var45 = var35 + (var7 + var1 / 2.0F) * var41;
            var10007 = new Object[]{null, null, null, null, var21};
            var10007[3] = var45;
            var10007[2] = var34;
            var10007[1] = var44;
            var10007[0] = var31;
            var10000 = var32.d_TextRenderer(var10007);
            var10004 = new Object[]{null, var19};
            var10004[0] = var36;
            var10000.e_TextRenderer(var10004);
            float var46 = var33 + (var7 - var1 / 2.0F) * var40;
            float var47 = var35 + (var7 - var1 / 2.0F) * var41;
            var10007 = new Object[]{null, null, null, null, var21};
            var10007[3] = var47;
            var10007[2] = var34;
            var10007[1] = var46;
            var10007[0] = var31;
            var10000 = var32.d_TextRenderer(var10007);
            var10004 = new Object[]{null, var19};
            var10004[0] = var36;
            var10000.e_TextRenderer(var10004);
            float var48 = var33 + (var7 - var1 / 2.0F - var6) * var40;
            float var49 = var35 + (var7 - var1 / 2.0F - var6) * var41;
            var10007 = new Object[]{null, null, null, null, var21};
            var10007[3] = var49;
            var10007[2] = var34;
            var10007[1] = var48;
            var10007[0] = var31;
            var10000 = var32.d_TextRenderer(var10007);
            var10004 = new Object[]{null, var19};
            var10004[0] = var37;
            var10000.e_TextRenderer(var10004);
        }

        Object[] var60 = new Object[]{null, var23};
        var60[0] = 7200;
        var32.a(var60);

        for (int var51 = 0; var51 < 100; var51++) {
            int var52 = var51 * 4;
            int var53 = (var51 + 1) % 101 * 4;
            var32.g_TextRenderer(new Object[]{new int[]{var52, var52 + 1, var53 + 1, var53 + 1, var53, var52}, var25});
            var32.g_TextRenderer(new Object[]{new int[]{var52 + 1, var52 + 2, var53 + 2, var53 + 2, var53 + 1, var52 + 1}, var25});
            var32.g_TextRenderer(new Object[]{new int[]{var52 + 2, var52 + 3, var53 + 3, var53 + 3, var53 + 2, var52 + 2}, var25});
        }

        var32.h_TextRenderer(new Object[0]);
    }

    public static void g(Object[] var0) {
        MatrixCameraData var4 = (MatrixCameraData)var0[0];
        RenderHelper8 var3 = (RenderHelper8)var0[1];
        float var1 = (Float)var0[2];
        float var9 = (Float)var0[3];
        float var8 = (Float)var0[4];
        float var10 = (Float)var0[5];
        float var5 = (Float)var0[6];
        float var11 = (Float)var0[7];
        Color var2 = (Color)var0[8];
        long var6 = (Long)var0[9];
        var6 = 18046302082221L ^ var6;
        long var12 = var6 ^ 20846251341884L;
        long var14 = var6 ^ 78037020522115L;
        long var16 = var6 ^ 27826654342640L;
        long var18 = var6 ^ 61801845654011L;
        long var20 = var6 ^ 117029852061893L;
        long var22 = var6 ^ 139115186640156L;
        long var24 = var6 ^ 70707532034438L;
        double var10001 = var1;
        double var10002 = var9;
        double var10003 = var8;
        Object[] var10006 = new Object[]{null, null, null, null, var12};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var4;
        Vector3d var26 = RenderUtil.a_Vector3d(var10006);
        var10001 = var10;
        var10002 = var5;
        var10003 = var11;
        var10006 = new Object[]{null, null, null, null, var12};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var4;
        Vector3d var27 = RenderUtil.a_Vector3d(var10006);
        Matrix4f var28 = ((Matrix4f)b.get()).identity();
        TextRenderer var29 = var3.a(new Object[]{UniformCache.e, var24});
        int var30 = a_int_________________________(new Object[]{var2, var14});
        float var31 = (float)var26.x;
        float var32 = (float)var26.y;
        float var33 = (float)var26.z;
        float var34 = (float)var27.x;
        float var35 = (float)var27.y;
        float var36 = (float)var27.z;
        TextRenderer var10000 = (TextRenderer)var29.k(new Object[]{var16});
        Object[] var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        Object[] var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        ((TextRenderer)var10000.e_TextRenderer(var10004).i_WorldContract(new Object[]{GlPrimitive.DEBUG_LINE_STRIP, var22})).h_TextRenderer(new Object[0]);
    }

    public static void h(Object[] var0) {
        MatrixCameraData var11 = (MatrixCameraData)var0[0];
        RenderHelper8 var3 = (RenderHelper8)var0[1];
        float var1 = (Float)var0[2];
        float var5 = (Float)var0[3];
        float var7 = (Float)var0[4];
        float var2 = (Float)var0[5];
        float var6 = (Float)var0[6];
        float var4 = (Float)var0[7];
        Color var8 = (Color)var0[8];
        long var9 = (Long)var0[9];
        var9 = 18046302082221L ^ var9;
        long var12 = var9 ^ 31196498330283L;
        long var14 = var9 ^ 79522623343636L;
        long var16 = var9 ^ 26271250421607L;
        long var18 = var9 ^ 60058546928492L;
        long var20 = var9 ^ 110887592974930L;
        long var22 = var9 ^ 123335926886283L;
        long var24 = var9 ^ 86744447847185L;
        double var10001 = var1;
        double var10002 = var5;
        double var10003 = var7;
        Object[] var10006 = new Object[]{null, null, null, null, var12};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var11;
        Vector3d var26 = RenderUtil.a_Vector3d(var10006);
        var10001 = var2;
        var10002 = var6;
        var10003 = var4;
        var10006 = new Object[]{null, null, null, null, var12};
        var10006[3] = var10003;
        var10006[2] = var10002;
        var10006[1] = var10001;
        var10006[0] = var11;
        Vector3d var27 = RenderUtil.a_Vector3d(var10006);
        Matrix4f var28 = ((Matrix4f)b.get()).identity();
        TextRenderer var29 = var3.a(new Object[]{UniformCache.b, var24});
        int var30 = a_int_________________________(new Object[]{var8, var14});
        float var31 = (float)var26.x;
        float var32 = (float)var26.y;
        float var33 = (float)var26.z;
        float var34 = (float)var27.x;
        float var35 = (float)var27.y;
        float var36 = (float)var27.z;
        TextRenderer var10000 = (TextRenderer)var29.k(new Object[]{var16});
        Object[] var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        Object[] var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var31;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var33;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var35;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        var10000 = var10000.e_TextRenderer(var10004);
        var10007 = new Object[]{null, null, null, null, var20};
        var10007[3] = var36;
        var10007[2] = var32;
        var10007[1] = var34;
        var10007[0] = var28;
        var10000 = var10000.d_TextRenderer(var10007);
        var10004 = new Object[]{null, var18};
        var10004[0] = var30;
        ((TextRenderer)var10000.e_TextRenderer(var10004).i_WorldContract(new Object[]{GlPrimitive.QUADS, var22})).h_TextRenderer(new Object[0]);
    }

    public static void k(Object[] var0) {
        Matrix4f var5 = (Matrix4f)var0[0];
        float var1 = (Float)var0[1];
        float var4 = (Float)var0[2];
        float var7 = (Float)var0[3];
        float var9 = (Float)var0[4];
        float var8 = (Float)var0[5];
        float var6 = (Float)var0[6];
        int var10 = (Integer)var0[7];
        long var2 = (Long)var0[8];
        var2 = 18046302082221L ^ var2;
        long var11 = var2 ^ 26689699278352L;
        long var13 = var2 ^ 110910168466156L;
        a_RenderHelper30(new Object[]{var11}).b(new Object[]{new RenderHelper13(UniformCache.c, (lpL70_0, lpL70_1) -> {
            Object[] var10003 = new Object[]{null, 91022737183023L};
            var10003[0] = var10;
            int var24 = b_int_(var10003);
            WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL70_1).k(new Object[]{111161505170643L});
            float var10004 = ((java.lang.Float)lpL70_0);
            Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
            var10007[3] = var10004;
            var10007[2] = var4;
            var10007[1] = var1;
            var10007[0] = var5;
            var10000 = var10000.d(var10007);
            Object[] var30 = new Object[]{null, 106573078251021L};
            var30[0] = var24;
            var10000 = var10000.e(var30);
            var10004 = ((java.lang.Float)lpL70_0);
            var10007 = new Object[]{null, null, null, null, 116910503435476L};
            var10007[3] = var10004;
            var10007[2] = var9;
            var10007[1] = var7;
            var10007[0] = var5;
            var10000 = var10000.d(var10007);
            Object[] var32 = new Object[]{null, 106573078251021L};
            var32[0] = var24;
            var10000 = var10000.e(var32);
            var10004 = ((java.lang.Float)lpL70_0);
            var10007 = new Object[]{null, null, null, null, 116910503435476L};
            var10007[3] = var10004;
            var10007[2] = var6;
            var10007[1] = var8;
            var10007[0] = var5;
            var10000 = var10000.d(var10007);
            Object[] var34 = new Object[]{null, 106573078251021L};
            var34[0] = var24;
            var10000.e(var34).i_WorldContract(new Object[]{GlPrimitive.TRIANGLES, 3098493320255L}).h(new Object[]{7414847813752L});
        }), var13});
    }

    public static void l(Object[] var0) {
        Matrix4f var1 = (Matrix4f)var0[0];
        float var4 = (Float)var0[1];
        float var7 = (Float)var0[2];
        float var2 = (Float)var0[3];
        float var3 = (Float)var0[4];
        Color var8 = (Color)var0[5];
        long var5 = (Long)var0[6];
        var5 = 18046302082221L ^ var5;
        long var9 = var5 ^ 77113810277705L;
        int var10005 = var8.getRGB();
        Object[] var10008 = new Object[]{null, null, null, null, null, null, var9};
        var10008[5] = var10005;
        var10008[4] = var3;
        var10008[3] = var2;
        var10008[2] = var7;
        var10008[1] = var4;
        var10008[0] = var1;
        m(var10008);
    }

    public static void m(Object[] var0) {
        Matrix4f var7 = (Matrix4f)var0[0];
        float var6 = (Float)var0[1];
        float var2 = (Float)var0[2];
        float var5 = (Float)var0[3];
        float var1 = (Float)var0[4];
        int var8 = (Integer)var0[5];
        long var3 = (Long)var0[6];
        var3 = 18046302082221L ^ var3;
        long var9 = var3 ^ 6194065297283L;
        long var11 = var3 ^ 133295588582271L;
        a_RenderHelper30(new Object[]{var9}).b(new Object[]{new RenderHelper13(UniformCache.c, (lpL69_0, lpL69_1) -> {
            Object[] var10003 = new Object[]{null, 91022737183023L};
            var10003[0] = var8;
            int var22 = b_int_(var10003);
            WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL69_1).k(new Object[]{111161505170643L});
            float var10004 = ((java.lang.Float)lpL69_0);
            Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
            var10007[3] = var10004;
            var10007[2] = var1;
            var10007[1] = var6;
            var10007[0] = var7;
            var10000 = var10000.d(var10007);
            Object[] var30 = new Object[]{null, 106573078251021L};
            var30[0] = var22;
            var10000 = var10000.e(var30);
            var10004 = ((java.lang.Float)lpL69_0);
            var10007 = new Object[]{null, null, null, null, 116910503435476L};
            var10007[3] = var10004;
            var10007[2] = var1;
            var10007[1] = var5;
            var10007[0] = var7;
            var10000 = var10000.d(var10007);
            Object[] var32 = new Object[]{null, 106573078251021L};
            var32[0] = var22;
            var10000 = var10000.e(var32);
            var10004 = ((java.lang.Float)lpL69_0);
            var10007 = new Object[]{null, null, null, null, 116910503435476L};
            var10007[3] = var10004;
            var10007[2] = var2;
            var10007[1] = var5;
            var10007[0] = var7;
            var10000 = var10000.d(var10007);
            Object[] var34 = new Object[]{null, 106573078251021L};
            var34[0] = var22;
            var10000 = var10000.e(var34);
            var10004 = ((java.lang.Float)lpL69_0);
            var10007 = new Object[]{null, null, null, null, 116910503435476L};
            var10007[3] = var10004;
            var10007[2] = var2;
            var10007[1] = var6;
            var10007[0] = var7;
            var10000 = var10000.d(var10007);
            Object[] var36 = new Object[]{null, 106573078251021L};
            var36[0] = var22;
            var10000.e(var36).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}).h(new Object[]{7414847813752L});
        }), var11});
    }

    public static void u(Object[] var0) {
        Matrix4f var5 = (Matrix4f)var0[0];
        float var1 = (Float)var0[1];
        float var4 = (Float)var0[2];
        float var3 = (Float)var0[3];
        float var2 = (Float)var0[4];
        Color var6 = (Color)var0[5];
        Vector4f var9 = (Vector4f)var0[6];
        long var7 = (Long)var0[7];
        var7 = 18046302082221L ^ var7;
        long var10 = var7 ^ 116335856689068L;
        long var12 = var7 ^ 73997186512419L;
        long var14 = var7 ^ 23467337675600L;
        a_RenderHelper30(new Object[]{var10})
            .b(
                new Object[]{
                    new RenderHelper13(
                        UniformCache.a(new Object[]{var9, var12}),
                        (lpL68_0, lpL68_1) -> {
                            int var10000 = var6.getRGB();
                            Object[] var10003 = new Object[]{null, 91022737183023L};
                            var10003[0] = var10000;
                            int var24 = b_int_(var10003);
                            float var25 = var3 - var1;
                            float var26 = var2 - var4;
                            WorldContract var27 = ((dev.zprestige.prestige.world.WorldContract)lpL68_1).k(new Object[]{111161505170643L});
                            float var10004 = ((java.lang.Float)lpL68_0);
                            Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var2;
                            var10007[1] = var1;
                            var10007[0] = var5;
                            WorldContract var28 = var27.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var25, var26}, 118034685045795L});
                            Object[] var35 = new Object[]{null, 106573078251021L};
                            var35[0] = var24;
                            WorldContract var29 = var28.e(var35);
                            var10004 = ((java.lang.Float)lpL68_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var2;
                            var10007[1] = var3;
                            var10007[0] = var5;
                            WorldContract var30 = var29.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var25, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var25, var26}, 118034685045795L});
                            Object[] var37 = new Object[]{null, 106573078251021L};
                            var37[0] = var24;
                            WorldContract var31 = var30.e(var37);
                            var10004 = ((java.lang.Float)lpL68_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var4;
                            var10007[1] = var3;
                            var10007[0] = var5;
                            WorldContract var32 = var31.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var25, var26}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var25, var26}, 118034685045795L});
                            Object[] var39 = new Object[]{null, 106573078251021L};
                            var39[0] = var24;
                            WorldContract var33 = var32.e(var39);
                            var10004 = ((java.lang.Float)lpL68_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var4;
                            var10007[1] = var1;
                            var10007[0] = var5;
                            WorldContract var34 = var33.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, var26}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var25, var26}, 118034685045795L});
                            Object[] var41 = new Object[]{null, 106573078251021L};
                            var41[0] = var24;
                            var34.e(var41).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}).h(new Object[]{7414847813752L});
                        }
                    ),
                    var14
                }
            );
    }

    public static void w(Object[] var0) {
        Matrix4f var7 = (Matrix4f)var0[0];
        float var12 = (Float)var0[1];
        float var8 = (Float)var0[2];
        float var3 = (Float)var0[3];
        float var11 = (Float)var0[4];
        Color var2 = (Color)var0[5];
        Color var4 = (Color)var0[6];
        Color var9 = (Color)var0[7];
        Color var1 = (Color)var0[8];
        Vector4f var10 = (Vector4f)var0[9];
        long var5 = (Long)var0[10];
        var5 = 18046302082221L ^ var5;
        long var13 = var5 ^ 11828101370340L;
        long var15 = var5 ^ 35475031750763L;
        long var17 = var5 ^ 130122862378264L;
        a_RenderHelper30(new Object[]{var13})
            .b(
                new Object[]{
                    new RenderHelper13(
                        UniformCache.a(new Object[]{var10, var15}),
                        (lpL67_0, lpL67_1) -> {
                            int var27 = a_int_________________________(new Object[]{var2, 64520126767008L});
                            int var28 = a_int_________________________(new Object[]{var4, 64520126767008L});
                            int var29 = a_int_________________________(new Object[]{var9, 64520126767008L});
                            int var30 = a_int_________________________(new Object[]{var1, 64520126767008L});
                            float var31 = var3 - var12;
                            float var32 = var11 - var8;
                            WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL67_1).k(new Object[]{111161505170643L});
                            float var10004 = ((java.lang.Float)lpL67_0);
                            Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var11;
                            var10007[1] = var12;
                            var10007[0] = var7;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var31, var32}, 118034685045795L});
                            Object[] var40 = new Object[]{null, 106573078251021L};
                            var40[0] = var27;
                            var10000 = var10000.e(var40);
                            var10004 = ((java.lang.Float)lpL67_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var11;
                            var10007[1] = var3;
                            var10007[0] = var7;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var31, 0.0F}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var31, var32}, 118034685045795L});
                            Object[] var42 = new Object[]{null, 106573078251021L};
                            var42[0] = var28;
                            var10000 = var10000.e(var42);
                            var10004 = ((java.lang.Float)lpL67_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var8;
                            var10007[1] = var3;
                            var10007[0] = var7;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{var31, var32}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var31, var32}, 118034685045795L});
                            Object[] var44 = new Object[]{null, 106573078251021L};
                            var44[0] = var29;
                            var10000 = var10000.e(var44);
                            var10004 = ((java.lang.Float)lpL67_0);
                            var10007 = new Object[]{null, null, null, null, 116910503435476L};
                            var10007[3] = var10004;
                            var10007[2] = var8;
                            var10007[1] = var12;
                            var10007[0] = var7;
                            var10000 = var10000.d(var10007)
                                .b_WorldContract(new Object[]{new float[]{0.0F, var32}, 118034685045795L})
                                .b_WorldContract(new Object[]{new float[]{var31, var32}, 118034685045795L});
                            Object[] var46 = new Object[]{null, 106573078251021L};
                            var46[0] = var30;
                            var10000.e(var46).i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L}).h(new Object[]{7414847813752L});
                        }
                    ),
                    var17
                }
            );
    }

    public static void y(Object[] var0) {
        Matrix4f var4 = (Matrix4f)var0[0];
        float var1 = (Float)var0[1];
        float var3 = (Float)var0[2];
        float var7 = (Float)var0[3];
        int var2 = (Integer)var0[4];
        long var5 = (Long)var0[5];
        var5 = 18046302082221L ^ var5;
        long var8 = var5 ^ 78498517662214L;
        long var10 = var5 ^ 65960957990650L;
        a_RenderHelper30(new Object[]{var8}).b(new Object[]{new RenderHelper13(UniformCache.c, (lpL66_0, lpL66_1) -> {
            Object[] var10003 = new Object[]{null, 91022737183023L};
            var10003[0] = var2;
            int var22 = b_int_(var10003);
            ((dev.zprestige.prestige.world.WorldContract)lpL66_1).k(new Object[]{111161505170643L});
            Event.b();
            float var10005 = ((java.lang.Float)lpL66_0);
            Object[] var10008 = new Object[]{null, null, null, null, 116910503435476L};
            var10008[3] = var10005;
            var10008[2] = var3;
            var10008[1] = var1;
            var10008[0] = var4;
            WorldContract var10001 = ((dev.zprestige.prestige.world.WorldContract)lpL66_1).d(var10008);
            Object[] var28 = new Object[]{null, 106573078251021L};
            var28[0] = var22;
            var10001.e(var28);
            int var23 = Math.max(12, (int)(var7 * 2.0F));

            for (int var24 = 0; var24 < var23; var24++) {
                float var25 = (float)((Math.PI * 2) * var24 / var23);
                float var10002 = (float)(var1 + Math.sin(var25) * var7);
                float var26 = (float)(var3 + Math.cos(var25) * var7);
                float var10004 = ((java.lang.Float)lpL66_0);
                Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
                var10007[3] = var10004;
                var10007[2] = var26;
                var10007[1] = var10002;
                var10007[0] = var4;
                WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL66_1).d(var10007);
                Object[] var27 = new Object[]{null, 106573078251021L};
                var27[0] = var22;
                var10000.e(var27);
            }

            ((dev.zprestige.prestige.world.WorldContract)lpL66_1).i_WorldContract(new Object[]{GlPrimitive.TRIANGLES_FAN, 3098493320255L}).h(new Object[]{7414847813752L});
        }), var10});
    }

    public static void A(Object[] var0) {
        Matrix4f var5 = (Matrix4f)var0[0];
        float var3 = (Float)var0[1];
        float var1 = (Float)var0[2];
        float var2 = (Float)var0[3];
        float var8 = (Float)var0[4];
        int var4 = (Integer)var0[5];
        long var6 = (Long)var0[6];
        var6 = 18046302082221L ^ var6;
        long var9 = var6 ^ 131489645526610L;
        long var11 = var6 ^ 12162116752046L;
        a_RenderHelper30(new Object[]{var9}).b(new Object[]{new RenderHelper13(UniformCache.c, (lpL65_0, lpL65_1) -> {
            Event.b();
            Object[] var10004 = new Object[]{null, 91022737183023L};
            var10004[0] = var4;
            int var23 = b_int_(var10004);
            float var25 = var8 / 2.0F;
            float var26 = var2 + var25;
            float var27 = Math.max(0.0F, var2 - var25);
            ((dev.zprestige.prestige.world.WorldContract)lpL65_1).k(new Object[]{111161505170643L});

            for (int var28 = 0; var28 <= 100; var28++) {
                float var29 = (float)((Math.PI * 2) * var28 / 100);
                float var30 = (float)Math.cos(var29);
                float var31 = (float)Math.sin(var29);
                float var32 = var3 + var26 * var30;
                float var33 = var1 + var26 * var31;
                float var34 = var3 + var27 * var30;
                float var35 = var1 + var27 * var31;
                float var37 = ((java.lang.Float)lpL65_0);
                Object[] var10007 = new Object[]{null, null, null, null, 116910503435476L};
                var10007[3] = var37;
                var10007[2] = var33;
                var10007[1] = var32;
                var10007[0] = var5;
                WorldContract var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL65_1).d(var10007);
                var10004 = new Object[]{null, 106573078251021L};
                var10004[0] = var23;
                var10000.e(var10004);
                float var39 = ((java.lang.Float)lpL65_0);
                var10007 = new Object[]{null, null, null, null, 116910503435476L};
                var10007[3] = var39;
                var10007[2] = var35;
                var10007[1] = var34;
                var10007[0] = var5;
                var10000 = ((dev.zprestige.prestige.world.WorldContract)lpL65_1).d(var10007);
                var10004 = new Object[]{null, 106573078251021L};
                var10004[0] = var23;
                var10000.e(var10004);
            }

            ((dev.zprestige.prestige.world.WorldContract)lpL65_1).i_WorldContract(new Object[]{GlPrimitive.QUAD_STRIP, 3098493320255L}).h(new Object[]{7414847813752L});
        }), var11});
    }

    public static void C(Object[] var0) {
        RenderHelper8 var8 = (RenderHelper8)var0[0];
        MatrixCameraData var9 = (MatrixCameraData)var0[1];
        Matrix4f var12 = (Matrix4f)var0[2];
        float var3 = (Float)var0[3];
        float var6 = (Float)var0[4];
        float var7 = (Float)var0[5];
        float var4 = (Float)var0[6];
        Color var2 = (Color)var0[7];
        float var5 = (Float)var0[8];
        float var1 = (Float)var0[9];
        long var10 = (Long)var0[10];
        var10 = 18046302082221L ^ var10;
        long var13 = var10 ^ 128467875067440L;
        float var10005 = var7 - var3;
        float var10006 = var4 - var6;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, null, var13};
        var10012[9] = var1;
        var10012[8] = var5;
        var10012[7] = var2;
        var10012[6] = var10006;
        var10012[5] = var10005;
        var10012[4] = var6;
        var10012[3] = var3;
        var10012[2] = var12;
        var10012[1] = var9;
        var10012[0] = var8;
        RoundedOutlinePosColorShader.c(var10012);
    }

    public static Color a_Color________(Object[] var0) {
        Color var1 = (Color)var0[0];
        float var4 = (Float)var0[1];
        float var5 = var1.getRed() / 255.0F;
        float var6 = var1.getGreen() / 255.0F;
        float var7 = var1.getBlue() / 255.0F;
        return new Color(var5, var6, var7, var4);
    }

    public static int a_int_________________________(Object[] var0) {
        Color var1 = (Color)var0[0];
        long var2 = (Long)var0[1];
        var2 = 18046302082221L ^ var2;
        long var4 = var2 ^ 132006251518498L;
        int var10000 = var1.getRGB();
        Object[] var10003 = new Object[]{null, var4};
        var10003[0] = var10000;
        return b_int_(var10003);
    }

    public static int b_int_(Object[] var0) {
        int var3 = (Integer)var0[0];
        int var4 = var3 >> 24 & 0xFF;
        int var5 = var3 >> 16 & 0xFF;
        int var6 = var3 >> 8 & 0xFF;
        int var7 = var3 & 0xFF;
        return var4 << 24 | var7 << 16 | var6 << 8 | var5;
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
