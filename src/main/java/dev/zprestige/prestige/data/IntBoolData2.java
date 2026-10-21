package dev.zprestige.prestige.data;

import dev.zprestige.prestige.render.RenderPass;
import dev.zprestige.prestige.render.Uniform;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.lwjgl.opengl.GL11;

public record IntBoolData2(int bR, boolean bS, boolean bT) implements Uniform {
    public IntBoolData2(int var1, boolean var2) {
        this(var1, var2, var2);
    }

    @Override
    public void a(RenderPass var1) {
        if (this.bS) {
            GL11.glEnable(this.bR);
        } else {
            GL11.glDisable(this.bR);
        }
    }

    @Override
    public void a() {
        if (this.bT != this.bS) {
            if (this.bT) {
                GL11.glEnable(this.bR);
            } else {
                GL11.glDisable(this.bR);
            }
        }
    }

    @Override
    public boolean a_boolean() {
        return true;
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
