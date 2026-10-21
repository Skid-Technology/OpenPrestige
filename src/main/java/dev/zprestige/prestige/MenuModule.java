package dev.zprestige.prestige;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MenuModule extends Module {
    private final KeybindSetting menuKey;
    private final EnumSetting descriptionMode;
    private final BoolSetting rounded;
    private final ColorSetting menuColor;

    public MenuModule() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        Object[] var10006 = new Object[]{null, null, 137970699205822L};
        var10006[1] = 161;
        var10006[0] = "Menu Key";
        this.menuKey = this.addKeybind(var10006);
        this.descriptionMode = this.addEnum(new Object[]{"Descriptions", "Mouse", new String[]{"Mouse", "Search Bar"}, 85198277421524L});
        var10006 = new Object[]{null, null, 136800689510088L};
        var10006[1] = false;
        var10006[0] = "Rounded";
        this.rounded = this.addBool(var10006);
        this.menuColor = this.addColor(new Object[]{"Menu Color", new Color(131, 118, 252), 17358566314135L});
    }

    public KeybindSetting getMenuKey(Object[] var1) {
        return this.menuKey;
    }

    public EnumSetting getDescriptionMode(Object[] var1) {
        return this.descriptionMode;
    }

    public BoolSetting getRounded(Object[] var1) {
        return this.rounded;
    }

    public ColorSetting getMenuColor(Object[] var1) {
        return this.menuColor;
    }

    private static Field g(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field h(Class var0, String var1, Class var2) {
        Field var3 = g(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = h(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method g(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method h(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = g(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = h(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
