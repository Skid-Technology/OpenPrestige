package dev.zprestige.prestige.config;

import com.google.gson.JsonObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ConfigHelper {
    private int a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;

    public ConfigHelper() {
    }

    public ConfigHelper(int var1, float var2, float var3, float var4, float var5, float var6) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var5;
        this.f = var6;
    }

    public static ConfigHelper a(Object[] var0) {
        JsonObject var1 = (JsonObject)var0[0];
        ConfigHelper var4 = new ConfigHelper();
        var4.a = var1.get("emSize").getAsInt();
        var4.b = var1.get("lineHeight").getAsFloat();
        var4.c = var1.get("ascender").getAsFloat();
        var4.d = var1.get("descender").getAsFloat();
        var4.e = var1.get("underlineY").getAsFloat();
        var4.f = var1.get("underlineThickness").getAsFloat();
        return var4;
    }

    public int a_int_(Object[] var1) {
        return this.a;
    }

    public float a_float_(Object[] var1) {
        return this.b;
    }

    public float b(Object[] var1) {
        return this.c;
    }

    public float c(Object[] var1) {
        return this.d;
    }

    public float d(Object[] var1) {
        return this.e;
    }

    public float e(Object[] var1) {
        return this.f;
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
