package dev.zprestige.prestige.math;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Setting;
import dev.zprestige.prestige.TextSetting;
import dev.zprestige.prestige.event.Event;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class MathUtil5 {
    private MathUtil5() {
    }

    public static void a(Object[] var0) {
        Setting var1 = (Setting)var0[0];
        Object var2 = var0[1];
        long var3 = (Long)var0[2];
        var3 = 13152399989566L ^ var3;
        long var5 = var3 ^ 53532658688614L;
        long var7 = var3 ^ 125782011785815L;
        long var9 = var3 ^ 122878328812744L;
        long var11 = var3 ^ 102927444203567L;
        long var13 = var3 ^ 78814096253474L;
        long var15 = var3 ^ 91988742845466L;
        if (var1 == null) {
            throw new IllegalArgumentException("setting is missing");
        } else if (var2 == null) {
            throw new IllegalArgumentException("value is null");
        } else {
            String var18 = var2.toString();
            if (var1 instanceof BoolSetting var35) {
                var35.setValue(a_boolean________(new Object[]{var18, var11}));
            } else if (var1 instanceof KeybindSetting var34) {
                String[] var40 = var18.split("\\.", -1);
                if (var40.length != 2) {
                    throw new IllegalArgumentException("expected key.hold");
                } else {
                    int var43 = a_int_______(new Object[]{var40[0], "key", var15});
                    boolean var46 = a_boolean________(new Object[]{var40[1], var11});
                    if (var43 >= -1 && (var43 <= 348 || var43 >= 9995 && var43 <= 9999)) {
                        var34.setValue(var43);
                        var34.setToggleMode(var46);
                    } else {
                        throw new IllegalArgumentException("key is outside the supported bind range");
                    }
                }
            } else if (var1 instanceof ColorSetting var33) {
                String[] var39 = var18.split("\\.", -1);
                if (var39.length != 4) {
                    throw new IllegalArgumentException("expected red.green.blue.alpha");
                } else {
                    int var42 = b(new Object[]{var39[0], "red", var13});
                    int var45 = b(new Object[]{var39[1], "green", var13});
                    int var47 = b(new Object[]{var39[2], "blue", var13});
                    int var48 = b(new Object[]{var39[3], "alpha", var13});
                    var33.setValue(new Color(var42, var45, var47, var48));
                }
            } else if (var1 instanceof FloatSetting var32) {
                float var38 = a_float_____________(new Object[]{var18, var7});
                var32.setValue(Math.clamp(var38, var32.getMin(), var32.getMax()));
            } else if (var1 instanceof IntSetting var31) {
                int var37 = a_int_______(new Object[]{var18, "integer", var15});
                var31.setValue(Math.clamp((long)var37, var31.getMin(), var31.getMax()));
            } else if (var1 instanceof EnumSetting var30) {
                if (Arrays.stream(var30.getOptions()).noneMatch(var18::equals)) {
                    Object[] var10006 = new Object[]{null, null, var9};
                    var10006[1] = 32;
                    var10006[0] = var18;
                    throw new IllegalArgumentException("unknown mode '" + a_String______(var10006) + "'");
                } else {
                    var30.setValue(var18);
                }
            } else if (!(var1 instanceof MultiSetting var19)) {
                if (var1 instanceof RangeSetting var29) {
                    String[] var36 = var18.split("\\|", -1);
                    if (var36.length != 2) {
                        throw new IllegalArgumentException("expected minimum|maximum");
                    } else {
                        float var41 = a_float_____________(new Object[]{var36[0], var7});
                        float var44 = a_float_____________(new Object[]{var36[1], var7});
                        float var23 = Math.clamp(var41, var29.getMin(), var29.getMax());
                        float var24 = Math.clamp(var44, var29.getMin(), var29.getMax());
                        if (var23 > var24) {
                            throw new IllegalArgumentException("minimum is greater than maximum");
                        } else {
                            float var25 = var29.getRolled(new Object[0]);
                            float var26 = Float.isFinite(var25) ? Math.clamp(var25, var23, var24) : var23;
                            var29.setLow(var23);
                            var29.setHigh(var24);
                            var29.setRolled(new Object[]{var26});
                        }
                    }
                } else if (var1 instanceof TextSetting var28) {
                    var28.setValue(var18);
                } else {
                    throw new IllegalArgumentException("unsupported setting type " + var1.getClass().getSimpleName());
                }
            } else {
                Map var20 = a_Map(new Object[]{var19, var18, var5});

                for (Entry var22 : (Iterable<Entry>)(var20.entrySet())) {
                    var19.setSelected((String)var22.getKey(), (Boolean)var22.getValue());
                }
            }
        }
    }

    public static boolean a_boolean________(Object[] var0) {
        Object var3 = var0[0];
        if (var3 == null) {
            throw new IllegalArgumentException("value is null");
        } else {
            String var5 = var3.toString().trim();
            if ("true".equalsIgnoreCase(var5)) {
                return true;
            } else if ("false".equalsIgnoreCase(var5)) {
                return false;
            } else {
                throw new IllegalArgumentException("expected true or false");
            }
        }
    }

    public static float a_float_____________(Object[] var0) {
        Object var1 = var0[0];
        if (var1 == null) {
            throw new IllegalArgumentException("value is null");
        } else {
            float var5;
            try {
                var5 = Float.parseFloat(var1.toString().trim());
            } catch (NumberFormatException var7) {
                throw new IllegalArgumentException("expected a number", var7);
            }

            if (!Float.isFinite(var5)) {
                throw new IllegalArgumentException("number must be finite");
            } else {
                return var5;
            }
        }
    }

    private static Map a_Map(Object[] var0) {
        MultiSetting var4 = (MultiSetting)var0[0];
        String var1 = (String)var0[1];
        long var2 = (Long)var0[2];
        var2 = 13152399989566L ^ var2;
        long var5 = var2 ^ 92941281938832L;
        long var7 = var2 ^ 113090423431543L;
        Event.b();
        LinkedHashMap var10 = new LinkedHashMap();
        if (var1.isEmpty()) {
            throw new IllegalArgumentException("mode list is empty");
        } else {
            for (String var14 : var1.split("&", -1)) {
                if (!var14.isEmpty()) {
                    int var15 = var14.lastIndexOf(46);
                    if (var15 <= 0 || var15 == var14.length() - 1) {
                        throw new IllegalArgumentException("expected option.true or option.false");
                    }

                    String var16 = var14.substring(0, var15);
                    boolean var17 = Arrays.stream(var4.getOptions()).anyMatch(var16::equals);
                    if (!var17) {
                        Object[] var23 = new Object[]{null, null, var5};
                        var23[1] = 32;
                        var23[0] = var16;
                        throw new IllegalArgumentException("unknown option '" + a_String______(var23) + "'");
                    }

                    if (var10.containsKey(var16)) {
                        Object[] var10006 = new Object[]{null, null, var5};
                        var10006[1] = 32;
                        var10006[0] = var16;
                        throw new IllegalArgumentException("duplicate option '" + a_String______(var10006) + "'");
                    }

                    var10.put(var16, a_boolean________(new Object[]{var14.substring(var15 + 1), var7}));
                }
            }

            for (String var22 : var4.getOptions()) {
                if (!var22.isEmpty() && !var10.containsKey(var22)) {
                    Object[] var24 = new Object[]{null, null, var5};
                    var24[1] = 32;
                    var24[0] = var22;
                    throw new IllegalArgumentException("missing option '" + a_String______(var24) + "'");
                }
            }

            return var10;
        }
    }

    private static int a_int_______(Object[] var0) {
        String var2 = (String)var0[0];
        String var1 = (String)var0[1];

        try {
            return Integer.parseInt(var2.trim());
        } catch (NumberFormatException var6) {
            throw new IllegalArgumentException("expected " + var1, var6);
        }
    }

    private static int b(Object[] var0) {
        String var4 = (String)var0[0];
        String var3 = (String)var0[1];
        long var1 = (Long)var0[2];
        var1 = 13152399989566L ^ var1;
        long var5 = var1 ^ 35123475238150L;
        Event.b();
        int var8 = a_int_______(new Object[]{var4, var3 + " channel", var5});
        if (var8 >= 0 && var8 <= 255) {
            return var8;
        } else {
            throw new IllegalArgumentException(var3 + " channel must be between 0 and 255");
        }
    }

    private static String a_String______(Object[] var0) {
        String var1 = (String)var0[0];
        int var2 = (Integer)var0[1];
        return var1.length() <= var2 ? var1 : var1.substring(0, var2 - 3) + "...";
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
