package dev.zprestige.prestige.config;

import dev.zprestige.prestige.event.Event;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class ConfigHelper3 {
    private final String a;
    private static final String c = "resource not found: ";

    private ConfigHelper3(String var1) {
        this.a = var1;
    }

    public static ConfigHelper3 a(Object[] var0) {
        String var1 = (String)var0[0];
        String var5 = var1.startsWith("/") ? var1.substring(1) : var1;
        return new ConfigHelper3(var5);
    }

    public InputStream a_InputStream(Object[] var1) {
        Event.b();
        InputStream var5 = ConfigHelper3.class.getClassLoader().getResourceAsStream(this.a);
        if (var5 == null) {
            throw new IllegalArgumentException(c + this);
        } else {
            return var5;
        }
    }

    public String a_String(Object[] var1) {
        return this.a;
    }

    @Override
    public String toString() {
        return this.a;
    }

    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else {
            return !(var1 instanceof ConfigHelper3 var5) ? false : this.a.equals(var5.a);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.a);
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

    private static IllegalArgumentException a(IllegalArgumentException var0) {
        return var0;
    }
}
