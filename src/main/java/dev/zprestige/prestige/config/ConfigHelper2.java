package dev.zprestige.prestige.config;

import com.google.gson.JsonObject;
import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ConfigHelper2 {
    private int a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;

    public static ConfigHelper2 a(Object[] var0) {
        JsonObject var1 = (JsonObject)var0[0];
        Event.b();
        ConfigHelper2 var5 = new ConfigHelper2();
        var5.a = var1.get("unicode").getAsInt();
        var5.b = var1.get("advance").getAsFloat();
        if (var1.has("planeBounds")) {
            JsonObject var6 = var1.get("planeBounds").getAsJsonObject();
            var5.c = var6.get("left").getAsFloat();
            var5.d = var6.get("bottom").getAsFloat();
            var5.e = var6.get("right").getAsFloat();
            var5.f = var6.get("top").getAsFloat();
        }

        if (var1.has("atlasBounds")) {
            JsonObject var7 = var1.get("atlasBounds").getAsJsonObject();
            var5.g = var7.get("left").getAsFloat();
            var5.h = var7.get("bottom").getAsFloat();
            var5.i = var7.get("right").getAsFloat();
            var5.j = var7.get("top").getAsFloat();
        }

        return var5;
    }

    public int a_int__(Object[] var1) {
        return this.a;
    }

    public float a_float__(Object[] var1) {
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

    public float f(Object[] var1) {
        return this.g;
    }

    public float g(Object[] var1) {
        return this.h;
    }

    public float h(Object[] var1) {
        return this.i;
    }

    public float i(Object[] var1) {
        return this.j;
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
