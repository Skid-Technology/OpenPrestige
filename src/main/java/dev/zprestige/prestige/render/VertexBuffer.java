package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.PassConsumerData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.type.GlPrimitive;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

public class VertexBuffer {
    private static final GpuBuffer a = (GpuBuffer)RenderUtil.a_Object___(new Object[]{(java.util.function.Supplier)GpuBuffer::a, 0L});
    private static final GpuBuffer b = (GpuBuffer)RenderUtil.a_Object___(new Object[]{(java.util.function.Supplier)GpuBuffer::a, 0L});
    private static final RenderHelper31 c = RenderHelper31.a(new Object[]{a, VertexAttribute.f, 0L});
    private final List d = new ArrayList();

    public VertexBuffer a(Object[] var1) {
        Shader var6 = (Shader)var1[0];
        Consumer var5 = (Consumer)var1[1];
        int var4 = (Integer)var1[2];
        long var2 = (Long)var1[3];
        var2 = 85728054144674L ^ var2;
        long var7 = var2 ^ 22580024169075L;
        return this.b(new Object[]{var6, var5, (IntSupplier)() -> var4, var7});
    }

    public VertexBuffer b(Object[] var1) {
        Shader var6 = (Shader)var1[0];
        Consumer var3 = (Consumer)var1[1];
        IntSupplier var2 = (IntSupplier)var1[2];
        long var4 = (Long)var1[3];
        var4 = 85728054144674L ^ var4;
        long var7 = var4 ^ 94493743747280L;
        long var9 = var4 ^ 66384678072345L;
        this.d.add(new PassConsumerData(RenderPass.a(new Object[]{var7}).b(new Object[]{var2}).e(new Object[]{var6}).a_RenderPass(new Object[]{var9}), var3));
        return this;
    }

    public void a_void______________________________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 85728054144674L ^ var2;
        long var4 = var2 ^ 124205971407095L;
        long var6 = var2 ^ 138790927232772L;
        Event.b();

        for (PassConsumerData var10 : (Iterable<PassConsumerData>)(this.d)) {
            RenderPass var11 = var10.bx();
            var11.a_void__________________________(new Object[]{var6});
            var10.by().accept(var11);
            RenderHelper31 var10001 = c;
            GpuBuffer var10002 = b;
            Object[] var10007 = new Object[]{null, null, null, null, var4};
            var10007[3] = 6;
            var10007[2] = 4;
            var10007[1] = var10002;
            var10007[0] = var10001;
            var11.d(var10007);
        }
    }

    static {
        long[] var0 = new long[]{4L, 24L};
        GpuBuffer var10000 = a;
        long var10002 = var0[0] * VertexAttribute.f.b;
        Object[] var10005 = new Object[]{null, null, 0L};
        var10005[1] = var10002;
        var10005[0] = 35044;
        var10000.a(var10005);
        var10000 = b;
        var10002 = var0[1];
        var10005 = new Object[]{null, null, 0L};
        var10005[1] = var10002;
        var10005[0] = 35044;
        var10000.a(var10005);

        try (TextRenderer var38 = new TextRenderer(VertexAttribute.f, a, b, 0L)) {
            Object[] var10006 = new Object[]{null, null, null, 0L};
            var10006[2] = 1.0F;
            var10006[1] = 1.0F;
            var10006[0] = -1.0F;
            TextRenderer var44 = var38.c_TextRenderer(var10006).b_TextRenderer(new Object[]{new float[]{0.0F, 1.0F}, 0L});
            var10006 = new Object[]{null, null, null, 0L};
            var10006[2] = 1.0F;
            var10006[1] = -1.0F;
            var10006[0] = -1.0F;
            TextRenderer var45 = var44.c_TextRenderer(var10006).b_TextRenderer(new Object[]{new float[]{0.0F, 0.0F}, 0L});
            var10006 = new Object[]{null, null, null, 0L};
            var10006[2] = 1.0F;
            var10006[1] = -1.0F;
            var10006[0] = 1.0F;
            TextRenderer var46 = var45.c_TextRenderer(var10006).b_TextRenderer(new Object[]{new float[]{1.0F, 0.0F}, 0L});
            var10006 = new Object[]{null, null, null, 0L};
            var10006[2] = 1.0F;
            var10006[1] = 1.0F;
            var10006[0] = 1.0F;
            var46.c_TextRenderer(var10006).b_TextRenderer(new Object[]{new float[]{1.0F, 1.0F}, 0L});
            var38.i_WorldContract(new Object[]{GlPrimitive.QUADS, 0L});
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
