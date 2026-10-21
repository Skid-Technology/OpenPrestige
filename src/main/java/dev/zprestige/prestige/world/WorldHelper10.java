package dev.zprestige.prestige.world;

import dev.zprestige.prestige.reflect.FieldAccessor;
import dev.zprestige.prestige.reflect.MethodValue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

public class WorldHelper10 {
    private static final MethodValue a = FieldAccessor.a_MethodValue(new Object[]{RaycastContext.class, "start", Vec3d.class, 0L});
    private static final MethodValue b = FieldAccessor.a_MethodValue(new Object[]{RaycastContext.class, "end", Vec3d.class, 0L});
    private static final MethodValue c = FieldAccessor.a_MethodValue(new Object[]{RaycastContext.class, "shapeType", ShapeType.class, 0L});
    private static final MethodValue d = FieldAccessor.a_MethodValue(new Object[]{RaycastContext.class, "fluid", FluidHandling.class, 0L});
    private static final MethodValue e = FieldAccessor.a_MethodValue(new Object[]{RaycastContext.class, "shapeContext", ShapeContext.class, 0L});
    private final RaycastContext f;

    public WorldHelper10(RaycastContext var1) {
        this.f = var1;
    }

    public void a(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        long var3 = (Long)var1[1];
        var3 = 21268067725915L ^ var3;
        long var5 = var3 ^ 58182339569528L;
        a.f(new Object[]{this.f, var2, var5});
    }

    public void b(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        long var2 = (Long)var1[1];
        var2 = 21268067725915L ^ var2;
        long var5 = var2 ^ 110818825862992L;
        b.f(new Object[]{this.f, var4, var5});
    }

    public void c(Object[] var1) {
        ShapeType var4 = (ShapeType)var1[0];
        long var2 = (Long)var1[1];
        var2 = 21268067725915L ^ var2;
        long var5 = var2 ^ 126506247994672L;
        c.f(new Object[]{this.f, var4, var5});
    }

    public void d(Object[] var1) {
        FluidHandling var2 = (FluidHandling)var1[0];
        long var3 = (Long)var1[1];
        var3 = 21268067725915L ^ var3;
        long var5 = var3 ^ 139014231716211L;
        d.f(new Object[]{this.f, var2, var5});
    }

    public void e(Object[] var1) {
        ShapeContext var2 = (ShapeContext)var1[0];
        long var3 = (Long)var1[1];
        var3 = 21268067725915L ^ var3;
        long var5 = var3 ^ 44372707042920L;
        e.f(new Object[]{this.f, var2, var5});
    }

    public void f(Object[] var1) {
        Vec3d var3 = (Vec3d)var1[0];
        Vec3d var2 = (Vec3d)var1[1];
        ShapeType var8 = (ShapeType)var1[2];
        FluidHandling var4 = (FluidHandling)var1[3];
        Entity var5 = (Entity)var1[4];
        long var6 = (Long)var1[5];
        var6 = 21268067725915L ^ var6;
        long var9 = var6 ^ 24220807918370L;
        long var11 = var6 ^ 11779660362290L;
        long var13 = var6 ^ 101718732711209L;
        long var15 = var6 ^ 90037529176426L;
        long var17 = var6 ^ 77137574341386L;
        this.a(new Object[]{var3, var9});
        this.b(new Object[]{var2, var17});
        this.c(new Object[]{var8, var15});
        this.d(new Object[]{var4, var13});
        this.e(new Object[]{ShapeContext.of(var5), var11});
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
