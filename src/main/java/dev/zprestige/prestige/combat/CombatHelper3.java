package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.reflect.FieldAccessor;
import dev.zprestige.prestige.reflect.FieldValue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.MinecraftClient;

public class CombatHelper3 {
    private final MinecraftClient a;

    public CombatHelper3(MinecraftClient var1) {
        this.a = var1;
    }

    public int a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 20571854547171L ^ var2;
        long var4 = var2 ^ 50573642575059L;
        long var6 = var2 ^ 97271680844069L;
        FieldValue var8 = FieldAccessor.b(new Object[]{this.a, "itemUseCooldown", MinecraftClient.class, int.class, var6});
        return (Integer)var8.a(new Object[]{var4});
    }

    public void a_void__(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        var2 = 20571854547171L ^ var2;
        long var5 = var2 ^ 36864578943679L;
        long var7 = var2 ^ 25124917358267L;
        FieldValue var9 = FieldAccessor.b(new Object[]{this.a, "itemUseCooldown", MinecraftClient.class, int.class, var7});
        var9.a_void_____________(new Object[]{var4, var5});
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
