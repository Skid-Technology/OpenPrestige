package dev.zprestige.prestige.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import java.lang.ref.Cleaner;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.GlBackend;
import net.minecraft.client.texture.GlTexture;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

public class RenderUtil {
    public static final Cleaner a = Cleaner.create();
    public static final RenderHelper8 b = new RenderHelper8();
    private static final List c = new ArrayList();
    public static MatrixCameraData d = null;
    private static Thread e;
    private static long f = -9223372036854775807L;

    public static int a() {
        return ((GlTexture)Objects.requireNonNull(MinecraftClient.getInstance().getFramebuffer().getColorAttachment()))
            .getOrCreateFramebuffer(
                ((GlBackend)RenderSystem.getDevice()).getBufferManager(), MinecraftClient.getInstance().getFramebuffer().getDepthAttachment()
            );
    }

    public static int b(Object[] var0) {
        return ((GlTexture)Objects.requireNonNull(MinecraftClient.getInstance().getFramebuffer().getColorAttachment())).getGlId();
    }

    public static float a(Object[] var0) {
        return MinecraftClient.getInstance().getWindow().getScaleFactor();
    }

    public static int c(Object[] var0) {
        return MinecraftClient.getInstance().getWindow().getFramebufferHeight();
    }

    public static Vector3d a_Vector3d(Object[] var0) {
        MatrixCameraData var9 = (MatrixCameraData)var0[0];
        double var1 = (Double)var0[1];
        double var3 = (Double)var0[2];
        double var5 = (Double)var0[3];
        long var7 = (Long)var0[4];
        var7 = 52698670966595L ^ var7;
        long var10 = var7 ^ 103858031838267L;
        Object[] var10007 = new Object[]{null, null, null, null, new Vector3d(), var10};
        var10007[3] = var5;
        var10007[2] = var3;
        var10007[1] = var1;
        var10007[0] = var9;
        return b_Vector3d(var10007);
    }

    public static Vector3d b_Vector3d(Object[] var0) {
        MatrixCameraData var1 = (MatrixCameraData)var0[0];
        double var4 = (Double)var0[1];
        double var6 = (Double)var0[2];
        double var8 = (Double)var0[3];
        Vector3d var10 = (Vector3d)var0[4];
        Vec3d var11 = var1.bC().getCameraPos();
        return var10.set(var4 - var11.x, var6 - var11.y, var8 - var11.z);
    }

    public static void a_void___________________________(Object[] var0) {
        Runnable var1 = (Runnable)var0[0];
        synchronized (c) {
            c.add(var1);
        }
    }

    // $VF: Could not create synchronized statement, marking monitor enters and exits
    // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
    public static List a_List__(Object[] var0) {
        Event.b();
        List var4 = c;
        synchronized (c){} // $VF: monitorenter 

        try {
            if (c.isEmpty()) {
                List var10000 = List.of();
                // $VF: monitorexit
                return var10000;
            } else {
                ArrayList var5 = new ArrayList(c);
                c.clear();
                // $VF: monitorexit
                return var5;
            }
        } finally {
            // $VF: monitorexit
        }
    }

    public static void c_void__(Object[] var0) {
        List var1 = (List)var0[0];
        Event.b();

        for (Runnable var6 : (Iterable<Runnable>)(var1)) {
            var6.run();
        }
    }

    public static void d(Object[] var0) {
        MatrixCameraData var1 = (MatrixCameraData)var0[0];
        long var2 = (Long)var0[1];
        var2 = 52698670966595L ^ var2;
        long var4 = var2 ^ 31406291669022L;
        MatrixStack.h = var1;
        MatrixStack.g = b;
        d = var1;
        ((RenderContract6)RenderContract5.b.invoker()).a(b, var1);
        b.b_void__________(new Object[]{var1, var4});
    }

    public static void e(Object[] var0) {
        MatrixCameraData var3 = (MatrixCameraData)var0[0];
        long var1 = (Long)var0[1];
        var1 = 52698670966595L ^ var1;
        long var4 = var1 ^ 93262027522045L;
        long var6 = var1 ^ 88314557730749L;
        Event.b();
        h(new Object[]{var4});
        if (RenderContract2.b.world != null && RenderContract2.b.player != null) {
            MatrixStack.h = var3;
            MatrixStack.g = b;
            ((RenderContract6)RenderContract5.a.invoker()).a(b, var3);
            b.b_void__________(new Object[]{var3, var6});
            ((RenderContract6)RenderContract5.c.invoker()).a(b, var3);
            b.b_void__________(new Object[]{var3, var6});
        }
    }

    public static void f(Object[] var0) {
        Runnable var3 = (Runnable)var0[0];
        long var1 = (Long)var0[1];
        var1 = 52698670966595L ^ var1;
        long var4 = var1 ^ 120559174517494L;
        if (Thread.currentThread() == e) {
            var3.run();
        } else {
            a_void___________________________(new Object[]{var3, var4});
        }
    }

    public static Object a_Object___(Object[] var0) {
        Supplier var1 = (Supplier)var0[0];
        long var2 = (Long)var0[1];
        var2 = 52698670966595L ^ var2;
        long var4 = var2 ^ 49816714551203L;
        if (e != null && Thread.currentThread() != e) {
            CompletableFuture var6 = new CompletableFuture();
            a_void___________________________(new Object[]{(Runnable)() -> {
                try {
                    var6.complete(var1.get());
                } catch (Throwable var5) {
                    var6.completeExceptionally(var5);
                }
            }, var4});

            try {
                return var6.get(30L, TimeUnit.SECONDS);
            } catch (InterruptedException var9) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("interrupted waiting on render thread", var9);
            } catch (TimeoutException var10) {
                throw new RuntimeException("timed out waiting for render thread to execute supplier", var10);
            } catch (ExecutionException var11) {
                Object var8 = var11.getCause() == null ? var11 : var11.getCause();
                if (var8 instanceof RuntimeException) {
                    throw (RuntimeException)var8;
                } else {
                    throw new RuntimeException((Throwable)var8);
                }
            }
        } else {
            return var1.get();
        }
    }

    public static void g(Object[] var0) {
        Thread var1 = (Thread)var0[0];
        e = var1;
    }

    public static Thread a_Thread(Object[] var0) {
        return e;
    }

    public static long a_long__(Object[] var0) {
        return f;
    }

    public static void h(Object[] var0) {
        f++;
    }

    static {
        short var10000 = 2821;
        long var10001 = 5991801754864537605L;
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

    private static Exception a(Exception var0) {
        return var0;
    }
}
