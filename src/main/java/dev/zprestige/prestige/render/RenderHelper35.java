package dev.zprestige.prestige.render;

import dev.zprestige.prestige.reflect.FieldAccessor;
import dev.zprestige.prestige.reflect.FieldValue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil.Key;

public class RenderHelper35 {
    private final KeyBinding a;
    private static final String c = "boundKey";

    public RenderHelper35(KeyBinding var1) {
        this.a = var1;
    }

    public Key a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 109166156949250L ^ var2;
        long var4 = var2 ^ 122513438798405L;
        long var6 = var2 ^ 29567259778995L;
        FieldValue var8 = FieldAccessor.b(new Object[]{this.a, c, KeyBinding.class, Key.class, var6});
        return (Key)var8.a(new Object[]{var4});
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
