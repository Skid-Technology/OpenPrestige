package dev.zprestige.prestige;

import dev.zprestige.prestige.annotation.Chainable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class KeybindSetting extends Setting {
    // covariant getValue (added by fix_typed_values.py)
    @Override
    public Integer getValue() {
        return (Integer)super.getValue();
    }

    private boolean toggleMode;

    public KeybindSetting(String var1, int var2) {
        super(var1, var2);
    }

    @Chainable
    public boolean isToggleMode() {
        return this.toggleMode;
    }

    @Chainable
    public void setToggleMode(boolean var1) {
        this.toggleMode = var1;
    }

    public KeybindSetting setVisibility_KeybindSetting(Object[] var1) {
        Predicate var4 = (Predicate)var1[0];
        long var2 = (Long)var1[1];
        var2 = 99049240658274L ^ var2;
        long var5 = var2 ^ 41186392833473L;
        super.setVisibility(new Object[]{var4, var5});
        return this;
    }

    @Chainable
    public KeybindSetting setDescription_KeybindSetting(String var1) {
        super.setDescription(var1);
        return this;
    }

    public boolean isPressed(Object[] var1) {
        return this.getValue() != -1 && this.getValue() > 9994
            ? GLFW.glfwGetMouseButton(MinecraftClient.getInstance().getWindow().getHandle(), 9999 - (Integer)this.getValue()) == 1
            : GLFW.glfwGetKey(MinecraftClient.getInstance().getWindow().getHandle(), (Integer)this.getValue()) == 1;
    }

    @Override
    public Setting setVisibility(Object[] var1) {
        Predicate var4 = (Predicate)var1[0];
        long var2 = (Long)var1[1];
        long var5 = var2 ^ 140053625863331L;
        return this.setVisibility_KeybindSetting(new Object[]{var4, var5});
    }

    @Chainable
    @Override
    public Setting setDescription(String var1) {
        return this.setDescription_KeybindSetting(var1);
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

    private static MatchException b(MatchException var0) {
        return var0;
    }
}
