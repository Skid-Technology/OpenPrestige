package dev.zprestige.prestige.math;

import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.type.BlendMode;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.joml.Vector4f;

public class MathHelper6 {
    public final FontRenderer a;
    public final Vector4f b;
    public final BlendMode c;
    public final boolean d;

    public MathHelper6(FontRenderer var1, Vector4f var2, BlendMode var3, boolean var4) {
        this.a = var1;
        this.b = new Vector4f(var2);
        this.c = var3;
        this.d = var4;
    }

    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else {
            return !(var1 instanceof MathHelper6 var5) ? false : this.d == var5.d && this.c == var5.c && this.a.equals(var5.a) && this.b.equals(var5.b);
        }
    }

    @Override
    public int hashCode() {
        int var3 = this.a.hashCode();
        var3 = 31 * var3 + this.b.hashCode();
        var3 = 31 * var3 + this.c.hashCode();
        return 31 * var3 + Boolean.hashCode(this.d);
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
