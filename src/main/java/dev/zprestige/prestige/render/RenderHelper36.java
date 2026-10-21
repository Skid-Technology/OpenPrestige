package dev.zprestige.prestige.render;

import dev.zprestige.prestige.reflect.MethodAccessor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;

public class RenderHelper36 {
    private final Mouse a;
    private static final String c = "onMouseButton";

    public RenderHelper36(Mouse var1) {
        this.a = var1;
    }

    public void a(Object[] var1) {
        long var7 = (Long)var1[0];
        int var4 = (Integer)var1[1];
        int var3 = (Integer)var1[2];
        int var2 = (Integer)var1[3];
        long var5 = (Long)var1[4];
        var5 = 96267497211421L ^ var5;
        long var9 = var5 ^ 73977705220219L;
        long var11 = var5 ^ 12261113047470L;
        RenderHelper16 var13 = MethodAccessor.a_RenderHelper16(
            new Object[]{this.a, c, Mouse.class, void.class, new Class[]{long.class, MouseInput.class, int.class}, var11}
        );
        var13.a(new Object[]{new Object[]{var7, new MouseInput(var4, var2), var3}, var9});
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
