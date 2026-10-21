package dev.zprestige.prestige.inventory;

import dev.zprestige.prestige.reflect.FieldAccessor;
import dev.zprestige.prestige.reflect.FieldValue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;

public class InventoryHelper {
    private final HeldItemRenderer a;

    public InventoryHelper(HeldItemRenderer var1) {
        this.a = var1;
    }

    public void a(Object[] var1) {
        float var2 = (Float)var1[0];
        long var3 = (Long)var1[1];
        var3 = 43351946238974L ^ var3;
        long var5 = var3 ^ 103970603468878L;
        long var7 = var3 ^ 116348097453130L;
        FieldValue var9 = FieldAccessor.b(new Object[]{this.a, "equipProgressMainHand", HeldItemRenderer.class, float.class, var7});
        var9.a_void_____________(new Object[]{var2, var5});
    }

    public void b(Object[] var1) {
        float var2 = (Float)var1[0];
        long var3 = (Long)var1[1];
        var3 = 43351946238974L ^ var3;
        long var5 = var3 ^ 20140288343608L;
        long var7 = var3 ^ 40749229713980L;
        FieldValue var9 = FieldAccessor.b(new Object[]{this.a, "equipProgressOffHand", HeldItemRenderer.class, float.class, var7});
        var9.a_void_____________(new Object[]{var2, var5});
    }

    public float a_float________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 43351946238974L ^ var2;
        long var4 = var2 ^ 85040611111317L;
        long var6 = var2 ^ 62469302213731L;
        FieldValue var8 = FieldAccessor.b(new Object[]{this.a, "equipProgressMainHand", HeldItemRenderer.class, float.class, var6});
        return (Float)var8.a(new Object[]{var4});
    }

    public float b_float____(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 43351946238974L ^ var2;
        long var4 = var2 ^ 17211154073484L;
        long var6 = var2 ^ 134344484219514L;
        FieldValue var8 = FieldAccessor.b(new Object[]{this.a, "equipProgressOffHand", HeldItemRenderer.class, float.class, var6});
        return (Float)var8.a(new Object[]{var4});
    }

    public void c(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        long var3 = (Long)var1[1];
        var3 = 43351946238974L ^ var3;
        long var5 = var3 ^ 88171330322886L;
        long var7 = var3 ^ 113722432755138L;
        FieldValue var9 = FieldAccessor.b(new Object[]{this.a, "mainHand", HeldItemRenderer.class, ItemStack.class, var7});
        var9.a_void_____________(new Object[]{var2, var5});
    }

    public void d(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        long var3 = (Long)var1[1];
        var3 = 43351946238974L ^ var3;
        long var5 = var3 ^ 43756257674950L;
        long var7 = var3 ^ 18232703832770L;
        FieldValue var9 = FieldAccessor.b(new Object[]{this.a, "offHand", HeldItemRenderer.class, ItemStack.class, var7});
        var9.a_void_____________(new Object[]{var2, var5});
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

    private static RuntimeException a(RuntimeException var0) {
        return var0;
    }
}
