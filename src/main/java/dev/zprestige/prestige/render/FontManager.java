package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FontManager {
    private static volatile FontManager a;
    private final Map b = new HashMap();
    private Font c;

    public Font a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 3528379088226L ^ var2;
        long var4 = var2 ^ 6915856496627L;
        Event.b();
        Font var7 = this.c;
        if (var7 == null) {
            var7 = this.b(new Object[]{"inter", var4});
            this.c = var7;
        }

        return var7;
    }

    public Font b(Object[] var1) {
        String var4 = (String)var1[0];
        return (Font)this.b.computeIfAbsent(var4, lpL40_0 -> {
            try {
                return new Font(var4, 97080015228313L);
            } catch (IOException var7) {
                throw new RuntimeException("Failed to load MSDF font: " + var4, var7);
            }
        });
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

    private static RuntimeException a(RuntimeException var0) {
        return var0;
    }
}
