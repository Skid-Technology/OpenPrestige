package dev.zprestige.prestige;

import dev.zprestige.prestige.annotation.Chainable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public abstract class Setting {
    private String name;
    private Object value;
    private String description;
    private Predicate visibility;
    private static int[] e;

    public Setting(String var1, Object var2) {
        this.name = var1;
        this.value = var2;
    }

    @Chainable
    public String getName() {
        return this.name;
    }

    public void setName(Object[] var1) {
        String var2 = (String)var1[0];
        this.name = var2;
    }

    @Chainable
    public Object getValue() {
        return this.value;
    }

    @Chainable
    public void setValue(Object var1) {
        this.value = var1;
    }

    @Chainable
    public String getDescription() {
        return this.description;
    }

    @Chainable
    public Setting setDescription(String var1) {
        this.description = var1;
        return this;
    }

    public Setting setVisibility(Object[] var1) {
        Predicate var2 = (Predicate)var1[0];
        this.visibility = var2;
        return this;
    }

    @Chainable
    public boolean isVisible() {
        return this.visibility == null || this.visibility.test(this.getValue());
    }

    public static void b(int[] var0) {
        e = var0;
    }

    public static int[] b() {
        return e;
    }

    static {
        b();
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
