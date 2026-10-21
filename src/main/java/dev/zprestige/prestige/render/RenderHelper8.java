package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.BufferData;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.VertexIntData;
import dev.zprestige.prestige.event.Event;
import it.unimi.dsi.fastutil.Pair;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.lwjgl.opengl.ARBVertexArrayObject;

public class RenderHelper8 {
    private final WeakHashMap a = new WeakHashMap();
    private final Set b = new LinkedHashSet();
    public static final boolean c = !RenderHelper8.class.desiredAssertionStatus();
    private static final String e = "empty buffer but we have a record of it being requested. huh?";
    private static final long i = 4L;

    public TextRenderer a(Object[] var1) {
        DrawMode var4 = (DrawMode)var1[0];
        long var2 = (Long)var1[1];
        var2 = 28755365965556L ^ var2;
        long var5 = var2 ^ 4283583056413L;
        Object[] var10006 = new Object[]{null, null, null, var5};
        var10006[2] = 128;
        var10006[1] = 128;
        var10006[0] = var4;
        return this.b(var10006);
    }

    public TextRenderer b(Object[] var1) {
        DrawMode var4 = (DrawMode)var1[0];
        int var5 = (Integer)var1[1];
        int var6 = (Integer)var1[2];
        long var2 = (Long)var1[3];
        var2 = 28755365965556L ^ var2;
        long var7 = var2 ^ 109936647766440L;
        long var9 = var2 ^ 25036787234434L;
        long var11 = var2 ^ 86209165402986L;
        long var13 = var2 ^ 8454589949756L;
        Event.b();
        this.b.add(var4);
        BufferData var16 = (BufferData)this.a.get(var4);
        if (var16 != null) {
            if (var16.ax().get() != null) {
                return (TextRenderer)var16.ax().get();
            } else {
                TextRenderer var22 = new TextRenderer(var4.a, var16.au(), var16.av(), var9);
                var16.ax().set(var22);
                return var22;
            }
        } else {
            GpuBuffer var17 = (GpuBuffer)RenderUtil.a_Object___(new Object[]{(java.util.function.Supplier)GpuBuffer::a, var7});
            GpuBuffer var18 = (GpuBuffer)RenderUtil.a_Object___(new Object[]{(java.util.function.Supplier)GpuBuffer::a, var7});
            long var10002 = (long)var5 * var4.a.b;
            Object[] var10005 = new Object[]{null, null, var13};
            var10005[1] = var10002;
            var10005[0] = 35048;
            var17.a(var10005);
            var10002 = var6 * i;
            var10005 = new Object[]{null, null, var13};
            var10005[1] = var10002;
            var10005[0] = 35048;
            var18.a(var10005);
            TextRenderer var19 = new TextRenderer(var4.a, var17, var18, var9);
            RenderHelper31 var20 = RenderHelper31.a(new Object[]{var17, var4.a, var11});
            this.a.put(var4, new BufferData(var17, var18, var20, new AtomicReference<>(var19)));
            return var19;
        }
    }

    private void a_void_________________________(Object[] var1) {
        MatrixCameraData var2 = (MatrixCameraData)var1[0];
        DrawMode var3 = (DrawMode)var1[1];
        long var4 = (Long)var1[2];
        var4 = 28755365965556L ^ var4;
        long var6 = var4 ^ 120533942898075L;
        long var8 = var4 ^ 21811419897088L;
        long var10 = var4 ^ 31203651073671L;
        Event.b();
        BufferData var13 = (BufferData)this.a.get(var3);
        if (!c && var13 == null) {
            throw new AssertionError();
        } else {
            TextRenderer var14 = (TextRenderer)var13.ax().get();
            if (!c && var14 == null) {
                throw new AssertionError(e);
            } else {
                int var15 = var14.b_int__(new Object[]{var10});
                int var16 = var14.a_int_____________________________(new Object[]{var8});
                var14.close();
                var13.ax().set(null);
                ARBVertexArrayObject.glBindVertexArray(var13.aw().a_int________________________(new Object[0]));
                var3.a_void________________(new Object[]{var2, var13.aw(), var13.av(), new VertexIntData(var3.a, var16, var15), var6});
                ARBVertexArrayObject.glBindVertexArray(0);
            }
        }
    }

    public void b_void__________(Object[] var1) {
        MatrixCameraData var2 = (MatrixCameraData)var1[0];
        long var3 = (Long)var1[1];
        var3 = 28755365965556L ^ var3;
        long var5 = var3 ^ 71082937393543L;
        Event.b();

        for (DrawMode var9 : (Iterable<DrawMode>)(this.b)) {
            this.a_void_________________________(new Object[]{var2, var9, var5});
        }

        this.b.clear();
        this.a.size();
    }

    public Stream a_Stream(Object[] var1) {
        return this.b.stream().map(lpL13_0 -> Pair.of(((dev.zprestige.prestige.render.DrawMode)lpL13_0), (TextRenderer)((BufferData)this.a.get(((dev.zprestige.prestige.render.DrawMode)lpL13_0))).ax().get()));
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
