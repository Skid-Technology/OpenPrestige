package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.DrawFloatData;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.VectorData;
import dev.zprestige.prestige.data.VertexIntData;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public class RenderHelper30 {
    public GpuBuffer a;
    public GpuBuffer b;
    public RenderHelper31 c;
    private boolean d = false;
    private final List e = new ArrayList();
    public float f = 0.0F;

    private void a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 72893699494923L ^ var2;
        long var4 = var2 ^ 65446728695281L;
        long var6 = var2 ^ 6077124533425L;
        long var8 = var2 ^ 105324996582245L;
        if (!this.d) {
            this.a = (GpuBuffer)RenderUtil.a_Object___(new Object[]{(java.util.function.Supplier)GpuBuffer::a, var4});
            this.b = (GpuBuffer)RenderUtil.a_Object___(new Object[]{(java.util.function.Supplier)GpuBuffer::a, var4});
            this.c = RenderHelper31.b(new Object[]{null, var6});
            GpuBuffer var10000 = this.a;
            Object[] var10005 = new Object[]{null, null, var8};
            var10005[1] = 65536L;
            var10005[0] = 35048;
            var10000.a(var10005);
            var10000 = this.b;
            var10005 = new Object[]{null, null, var8};
            var10005[1] = 16384L;
            var10005[0] = 35048;
            var10000.a(var10005);
            this.d = true;
        }
    }

    public void b(Object[] var1) {
        RenderContract4 var2 = (RenderContract4)var1[0];
        this.e.add(new DrawFloatData(var2, this.f));
        this.f = Math.nextUp(this.f);
    }

    public void c(Object[] var1) {
        MatrixCameraData var2 = (MatrixCameraData)var1[0];
        long var3 = (Long)var1[1];
        var3 = 72893699494923L ^ var3;
        long var5 = var3 ^ 86230103069194L;
        long var7 = var3 ^ 109013346395613L;
        long var9 = var3 ^ 79949687281912L;
        long var11 = var3 ^ 122717394461073L;
        long var13 = var3 ^ 32349811078470L;
        long var15 = var3 ^ 109300319960897L;
        long var17 = var3 ^ 20733713030849L;
        long var19 = var3 ^ 59677040195411L;
        long var21 = var3 ^ 6108043939455L;
        long var23 = var3 ^ 12051683328807L;
        long var25 = var3 ^ 45908158025975L;
        if (this.e.isEmpty()) {
            this.f = 0.0F;
        } else {
            this.a(new Object[]{var15});
            int var28 = 0;

            while (var28 < this.e.size()) {
                VectorData var29 = ((DrawFloatData)this.e.get(var28)).ca().a_VectorData_(new Object[0]);
                int var31 = var28 + 1;

                while (var31 < this.e.size() && ((DrawFloatData)this.e.get(var31)).ca().a_VectorData_(new Object[0]).equals(var29)) {
                    var31++;
                }

                List var32 = this.e.subList(var28, var31);
                Vector4f var33 = var29.bD();
                if (var33 != null) {
                    GL11.glEnable(3089);
                    int var34 = RenderUtil.c(new Object[]{var21});
                    float var35 = RenderUtil.a(new Object[]{var23});
                    GL11.glScissor((int)(var33.x * var35), (int)(var34 - var33.y * var35 - var33.w * var35), (int)(var33.z * var35), (int)(var33.w * var35));
                }

                DrawMode var43 = var29.bE();
                this.c
                    .a_void______________________(new Object[]{RenderHelper31.a_List_(new Object[]{this.a, var43.a_VertexFormat(new Object[0]), var5}), var25});
                TextRenderer var37 = new TextRenderer(var43.a_VertexFormat(new Object[0]), this.a, this.b, var11);

                int var36;
                int var44;
                try {
                    for (DrawFloatData var39 : (Iterable<DrawFloatData>)(var32)) {
                        RenderContract4 var10000 = var39.ca();
                        float var10002 = var39.cb();
                        Object[] var10005 = new Object[]{null, null, var19};
                        var10005[1] = var10002;
                        var10005[0] = var37;
                        var10000.a(var10005);
                    }

                    var44 = var37.a_int_____________________________(new Object[]{var13});
                    var36 = var37.b_int__(new Object[]{var17});
                } catch (Throwable var41) {
                    try {
                        var37.close();
                    } catch (Throwable var40) {
                        var41.addSuppressed(var40);
                    }

                    throw var41;
                }

                var37.close();
                var43.a_void________________(new Object[]{var2, this.c, this.b, new VertexIntData(var43.a_VertexFormat(new Object[0]), var44, var36), var7});
                if (var33 != null) {
                    GL11.glDisable(3089);
                }

                var28 = var31;
            }

            this.d(new Object[]{var9});
        }
    }

    public void d(Object[] var1) {
        this.f = 0.0F;
        this.e.clear();
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
