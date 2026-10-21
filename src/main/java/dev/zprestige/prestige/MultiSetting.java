package dev.zprestige.prestige;

import dev.zprestige.prestige.annotation.Chainable;
import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MultiSetting extends Setting {
    // covariant getValue (added by fix_typed_values.py)
    @Override
    public String getValue() {
        return (String)super.getValue();
    }

    private final String[] options;
    private final boolean[] selected;
    private final Map indexByOption;

    public MultiSetting(String var1, String[] var2, boolean[] var3) {
        super(var1, "");
        Event.b();
        this.options = var2;
        this.selected = var3;
        this.indexByOption = new HashMap(var2.length * 2);

        for (int var7 = 0; var7 < var2.length; var7++) {
            this.indexByOption.put(var2[var7], var7);
        }
    }

    @Chainable
    public String[] getOptions() {
        return this.options;
    }

    public boolean isSelected(Object[] var1) {
        String var2 = (String)var1[0];
        Integer var5 = (Integer)this.indexByOption.get(var2);
        if (var5 == null) {
            throw new IllegalArgumentException("Value not found: " + var2);
        } else {
            return this.selected[var5];
        }
    }

    @Chainable
    public void setSelected(String var1, boolean var2) {
        Event.b();
        Integer var6 = (Integer)this.indexByOption.get(var1);
        if (var6 == null) {
            throw new IllegalArgumentException("Value not found: " + var1);
        } else {
            this.selected[var6] = var2;
        }
    }

    @Chainable
    public boolean[] getSelected() {
        return this.selected;
    }

    public MultiSetting setVisibility_MultiSetting(Object[] var1) {
        Predicate var4 = (Predicate)var1[0];
        long var2 = (Long)var1[1];
        var2 = 89151985553059L ^ var2;
        long var5 = var2 ^ 96972061473817L;
        super.setVisibility(new Object[]{var4, var5});
        return this;
    }

    @Chainable
    public MultiSetting setDescription_MultiSetting(String var1) {
        super.setDescription(var1);
        return this;
    }

    @Override
    public Setting setVisibility(Object[] var1) {
        Predicate var4 = (Predicate)var1[0];
        long var2 = (Long)var1[1];
        long var5 = var2 ^ 10064201577146L;
        return this.setVisibility_MultiSetting(new Object[]{var4, var5});
    }

    @Chainable
    @Override
    public Setting setDescription(String var1) {
        return this.setDescription_MultiSetting(var1);
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

    private static IllegalArgumentException a(IllegalArgumentException var0) {
        return var0;
    }
}
