package dev.zprestige.prestige;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SettingHolder {
    public final ArrayList settings = new ArrayList();

    public KeybindSetting addKeybind(Object[] var1) {
        String var2 = (String)var1[0];
        int var5 = (Integer)var1[1];
        KeybindSetting var6 = new KeybindSetting(var2, var5);
        this.settings.add(var6);
        return var6;
    }

    public BoolSetting addBool(Object[] var1) {
        String var2 = (String)var1[0];
        boolean var3 = (Boolean)var1[1];
        BoolSetting var6 = new BoolSetting(var2, var3);
        this.settings.add(var6);
        return var6;
    }

    public EnumSetting addEnum(Object[] var1) {
        String var4 = (String)var1[0];
        String var2 = (String)var1[1];
        String[] var3 = (String[])var1[2];
        EnumSetting var7 = new EnumSetting(var4, var2, var3);
        this.settings.add(var7);
        return var7;
    }

    public ColorSetting addColor(Object[] var1) {
        String var4 = (String)var1[0];
        Color var5 = (Color)var1[1];
        ColorSetting var6 = new ColorSetting(var4, var5);
        this.settings.add(var6);
        return var6;
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
