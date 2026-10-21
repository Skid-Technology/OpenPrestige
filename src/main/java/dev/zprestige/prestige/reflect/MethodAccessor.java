package dev.zprestige.prestige.reflect;

import dev.zprestige.prestige.data.StringData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderHelper16;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import net.lenni0451.classtransform.TransformerManager;
import net.lenni0451.classtransform.utils.ASMUtils;

public class MethodAccessor extends ReflectionBase {
    private final Method a;
    private final MethodHandle b;
    private final boolean c;
    private final Object d;
    private volatile MethodHandle e;
    private volatile boolean f;
    private static final Object[] g = new Object[0];
    private static final ConcurrentHashMap h = new ConcurrentHashMap();

    private MethodAccessor(Method var1, MethodHandle var2, boolean var3, Object var4) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
    }

    private MethodAccessor(MethodSpec var1, Object var2) {
        this(var1.a, var1.b, var1.c, var2);
    }

    public static MethodAccessor a(Object[] var0) {
        Class var2 = (Class)var0[0];
        String var1 = (String)var0[1];
        Class[] var5 = (Class[])var0[2];
        long var3 = (Long)var0[3];
        var3 = 68817367897026L ^ var3;
        long var6 = var3 ^ 128125055887225L;
        MethodSpec var8 = ReflectionBase.a_MethodSpec(new Object[]{var2, var1, var5, var6});
        return new MethodAccessor(var8, null);
    }

    public static MethodAccessor c(Object[] var0) {
        Object var2 = var0[0];
        String var3 = (String)var0[1];
        Class[] var1 = (Class[])var0[2];
        long var4 = (Long)var0[3];
        var4 = 68817367897026L ^ var4;
        long var6 = var4 ^ 12317463153975L;
        if (var2 == null) {
            throw new IllegalArgumentException("instance cannot be null");
        } else {
            MethodSpec var9 = ReflectionBase.a_MethodSpec(new Object[]{var2.getClass(), var3, var1, var6});
            return new MethodAccessor(var9, var2);
        }
    }

    public static RenderHelper16 a_RenderHelper16(Object[] var0) {
        Object var4 = var0[0];
        String var5 = (String)var0[1];
        Class var1 = (Class)var0[2];
        Class var2 = (Class)var0[3];
        Class[] var3 = (Class[])var0[4];
        long var6 = (Long)var0[5];
        var6 = 68817367897026L ^ var6;
        long var8 = var6 ^ 49993015910790L;
        long var10 = var6 ^ 103413622132717L;
        StringData var13 = new StringData(var1, var5, var2, var3, var4.getClass());
        Event.b();
        MethodAccessor var14 = (MethodAccessor)h.get(var13);
        if (var14 != null) {
            return var14.c_RenderHelper16(new Object[]{var4, var10});
        } else {
            String var15 = TransformerManager.getRemapper().getRemapper().reverse().map(var1.getName().replace('.', '/'));
            String var16 = TransformerManager.getRemapper().getRemapper().reverse().mapMethodDesc(ASMUtils.toDescriptor(var2, var3));
            String var17 = TransformerManager.getRemapper().getRemapper().mapMethodName(var15, var5, var16);
            MethodAccessor var18 = c(new Object[]{var4, var17, var3, var8});
            MethodAccessor var19 = (MethodAccessor)h.putIfAbsent(var13, var18);
            return (var19 != null ? var19 : var18).c_RenderHelper16(new Object[]{var4, var10});
        }
    }

    public static MethodAccessor e(Object[] var0) {
        Class var6 = (Class)var0[0];
        String var3 = (String)var0[1];
        Class var1 = (Class)var0[2];
        Class[] var2 = (Class[])var0[3];
        long var4 = (Long)var0[4];
        var4 = 68817367897026L ^ var4;
        long var7 = var4 ^ 85630767655394L;
        StringData var9 = new StringData(var6, var3, var1, var2, var6);
        MethodAccessor var10 = (MethodAccessor)h.get(var9);
        if (var10 != null) {
            return var10;
        } else {
            String var11 = TransformerManager.getRemapper().getRemapper().reverse().map(var6.getName().replace('.', '/'));
            String var12 = TransformerManager.getRemapper().getRemapper().reverse().mapMethodDesc(ASMUtils.toDescriptor(var1, var2));
            String var13 = TransformerManager.getRemapper().getRemapper().mapMethodName(var11, var3, var12);
            MethodAccessor var14 = a(new Object[]{var6, var13, var2, var7});
            MethodAccessor var15 = (MethodAccessor)h.putIfAbsent(var9, var14);
            return var15 != null ? var15 : var14;
        }
    }

    private MethodHandle a_MethodHandle_(Object[] var1) {
        if (this.f) {
            return this.e;
        } else {
            MethodHandle var5 = null;

            try {
                int var6 = this.b.type().parameterCount();
                var5 = this.b.asType(this.b.type().generic()).asSpreader(Object[].class, var6);
            } catch (Throwable var13) {
            }

            this.e = var5;
            this.f = true;
            return var5;
        }
    }

    public Method a_Method(Object[] var1) {
        return this.a;
    }

    public Object a_Object__(Object[] var1) throws Throwable {
        Object var5 = var1[0];
        Object[] var2 = (Object[])var1[1];
        long var3 = (Long)var1[2];
        var3 = 68817367897026L ^ var3;
        long var6 = var3 ^ 35452219203092L;
        return this.b(new Object[]{var5, var2, var6});
    }

    public Object b(Object[] var1) throws Throwable {
        Object var4 = var1[0];
        Object[] var5 = (Object[])var1[1];
        long var2 = (Long)var1[2];
        var2 = 68817367897026L ^ var2;
        long var6 = var2 ^ 57593098132257L;
        Object[] var9;
        if (this.c) {
            var9 = var5 == null ? g : var5;
        } else {
            if (var4 == null) {
                throw new IllegalArgumentException("Target instance required for non-static method");
            }

            int var10 = var5 == null ? 0 : var5.length;
            var9 = new Object[var10 + 1];
            var9[0] = var4;
            if (var10 > 0) {
                System.arraycopy(var5, 0, var9, 1, var10);
            }
        }

        MethodHandle var13 = this.a_MethodHandle_(new Object[]{var6});
        if (var13 != null) {
            return (Object)var13.invoke(var9);
        } else {
            return this.b.invokeWithArguments(var9);
        }
    }

    public RenderHelper16 c_RenderHelper16(Object[] var1) {
        Object var2 = var1[0];
        if (this.c) {
            throw new IllegalStateException("Cannot bind static method to instance");
        } else if (var2 == null) {
            throw new IllegalArgumentException("Target instance required for non-static method");
        } else {
            return new RenderHelper16(this, var2);
        }
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

    private static Object[] d() {
        return new Object[0];
    }

    private static Object[] a(Object var0, int var1) {
        return new Object[]{var0, var1};
    }

    private static Object[] b(Object var0) {
        return new Object[]{var0};
    }

    private static Throwable b(Throwable var0) {
        return var0;
    }
}
