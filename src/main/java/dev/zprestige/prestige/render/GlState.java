package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.lwjgl.opengl.GL30C;

public record GlState(
    boolean aR,
    boolean aS,
    boolean aT,
    boolean aU,
    int aV,
    int aW,
    int aX,
    int aY,
    int aZ,
    int[] a0,
    int a1,
    int a2,
    int a3,
    int a4,
    int a5,
    int a6,
    int[] a7,
    int a8,
    int a9
) {
    private static final int a = 8;

    public static GlState a() {
        boolean var3 = GL30C.glIsEnabled(2929);
        boolean var4 = GL30C.glIsEnabled(2884);
        Event.b();
        boolean var5 = GL30C.glIsEnabled(3042);
        boolean var6 = GL30C.glIsEnabled(3089);
        int var7 = GL30C.glGetInteger(35725);
        int var8 = GL30C.glGetInteger(34229);
        int var9 = GL30C.glGetInteger(34964);
        int var10 = GL30C.glGetInteger(34965);
        int var11 = GL30C.glGetInteger(34016);
        int[] var12 = new int[8];

        for (int var13 = 0; var13 < 8; var13++) {
            GL30C.glActiveTexture(33984 + var13);
            var12[var13] = GL30C.glGetInteger(32873);
        }

        GL30C.glActiveTexture(var11);
        int var22 = GL30C.glGetInteger(32777);
        int var14 = GL30C.glGetInteger(34877);
        int var15 = GL30C.glGetInteger(32969);
        int var16 = GL30C.glGetInteger(32968);
        int var17 = GL30C.glGetInteger(32971);
        int var18 = GL30C.glGetInteger(32970);
        short var10000 = 8712;
        int[] var19 = new int[4];
        GL30C.glGetIntegerv(3088, var19);
        int var20 = GL30C.glGetInteger(36006);
        int var21 = GL30C.glGetInteger(36010);
        return new GlState(var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var22, var14, var15, var16, var17, var18, var19, var21, var20);
    }

    public void a_void() {
        if (this.aR) {
            GL30C.glEnable(2929);
        } else {
            GL30C.glDisable(2929);
        }

        if (this.aS) {
            GL30C.glEnable(2884);
        } else {
            GL30C.glDisable(2884);
        }

        if (this.aT) {
            GL30C.glEnable(3042);
        } else {
            GL30C.glDisable(3042);
        }

        if (this.aU) {
            GL30C.glEnable(3089);
        } else {
            GL30C.glDisable(3089);
        }

        GL30C.glUseProgram(this.aV);
        GL30C.glBindBuffer(34962, this.aX);
        GL30C.glBindBuffer(34963, this.aY);
        GL30C.glBindVertexArray(this.aW);

        for (int var4 = 0; var4 < this.a0.length; var4++) {
            GL30C.glActiveTexture(33984 + var4);
            GL30C.glBindTexture(3553, this.a0[var4]);
        }

        GL30C.glActiveTexture(this.aZ);
        short var10000 = 23794;
        GL30C.glBlendEquationSeparate(this.a1, this.a2);
        long var10001 = 3102456192241388704L;
        GL30C.glBlendFuncSeparate(this.a3, this.a4, this.a5, this.a6);
        GL30C.glScissor(this.a7[0], this.a7[1], this.a7[2], this.a7[3]);
        GL30C.glBindFramebuffer(36008, this.a8);
        GL30C.glBindFramebuffer(36009, this.a9);
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
