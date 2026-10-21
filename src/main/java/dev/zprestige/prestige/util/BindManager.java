package dev.zprestige.prestige.util;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent10;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderHelper35;
import dev.zprestige.prestige.render.RenderHelper36;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.lwjgl.glfw.GLFW;

public class BindManager implements RenderContract2 {
    private final HashMap a;
    private boolean c;
    private boolean d;
    public static int e = 0;
    public static int f = 1;
    private static final long h = -954273915637596152L;

    public BindManager(long var1) {
        super();
        var1 = 0L;
        var1 = 12009258263935L ^ var1;
        long var3 = var1 ^ 20580107122753L;
        this.a = new HashMap();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    public boolean a(Object[] var1) {
        int var2 = (Integer)var1[0];
        return this.a.containsKey(var2);
    }

    private void b(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        var3 = 12009258263935L ^ var3;
        long var5 = var3 ^ 130502617724988L;
        long var7 = var3 ^ 107793809825388L;
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var2;
        if (this.a(var10004)) {
            RenderHelper36 var10000 = new RenderHelper36(b.mouse);
            long var10001 = b.getWindow().getHandle();
            Object[] var10007 = new Object[]{null, null, null, null, var7};
            var10007[3] = 0;
            var10007[2] = 0;
            var10007[1] = var2;
            var10007[0] = var10001;
            var10000.a(var10007);
            this.a.remove(var2);
        }

        if (var2 == e) {
            this.c = false;
        }

        if (var2 == f) {
            this.d = false;
        }
    }

    private void c(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        var3 = 12009258263935L ^ var3;
        long var5 = var3 ^ 89669795328449L;
        long var7 = var3 ^ 50375637167234L;
        Object[] var10004 = new Object[]{null, var7};
        var10004[0] = var2;
        if (this.a(var10004)) {
            int var10 = (Integer)this.a.get(var2);
            if (var10 > 0) {
                this.a.replace(var2, var10 - 1);
            } else {
                var10004 = new Object[]{null, var5};
                var10004[0] = var2;
                this.b(var10004);
            }
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (!b.isWindowFocused()) {
            int var3 = e;
            Object[] var6 = new Object[]{null, 9764117872379L};
            var6[0] = var3;
            this.b(var6);
            var3 = f;
            var6 = new Object[]{null, 9764117872379L};
            var6[0] = var3;
            this.b(var6);
            this.c = false;
            this.d = false;
        } else {
            int var10001 = e;
            Object[] var10004 = new Object[]{null, 91811856542277L};
            var10004[0] = var10001;
            this.c(var10004);
            var10001 = f;
            var10004 = new Object[]{null, 91811856542277L};
            var10004[0] = var10001;
            this.c(var10004);
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.d) {
            int var11 = new RenderHelper35(b.options.useKey).a(new Object[]{138149021417492L}).getCode();
            Object[] var10004 = new Object[]{null, 136421046046674L};
            var10004[0] = var11;
            if (!this.b_boolean________(var10004)) {
                this.d = false;
                return;
            }

            var1.a_void_____(new Object[0]);
            int var10001 = f;
            var10004 = new Object[]{null, 128352941745080L};
            var10004[0] = var10001;
            if (!this.a(var10004)) {
                this.d = false;
            }
        }
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (this.c) {
            int var11 = new RenderHelper35(b.options.attackKey).a(new Object[]{138149021417492L}).getCode();
            Object[] var10004 = new Object[]{null, 136421046046674L};
            var10004[0] = var11;
            if (!this.b_boolean________(var10004)) {
                this.c = false;
                return;
            }

            var1.a_void_____(new Object[0]);
            int var10001 = e;
            var10004 = new Object[]{null, 128352941745080L};
            var10004[0] = var10001;
            if (!this.a(var10004)) {
                this.c = false;
            }
        }
    }

    @Subscribe
    public void a(TickEvent10 var1) {
        if (this.c) {
            int var11 = new RenderHelper35(b.options.attackKey).a(new Object[]{138149021417492L}).getCode();
            Object[] var10004 = new Object[]{null, 136421046046674L};
            var10004[0] = var11;
            if (!this.b_boolean________(var10004)) {
                this.c = false;
                return;
            }

            var1.a_void_____(new Object[0]);
            int var10001 = e;
            var10004 = new Object[]{null, 128352941745080L};
            var10004[0] = var10001;
            if (!this.a(var10004)) {
                this.c = false;
            }
        }
    }

    private boolean b_boolean________(Object[] var1) {
        int var2 = (Integer)var1[0];
        if (var2 < 0) {
            return true;
        } else {
            return var2 <= (int)h ? GLFW.glfwGetMouseButton(b.getWindow().getHandle(), var2) != 1 : GLFW.glfwGetKey(b.getWindow().getHandle(), var2) != 1;
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
}
