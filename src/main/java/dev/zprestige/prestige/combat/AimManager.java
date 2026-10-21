package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderHelper10;
import dev.zprestige.prestige.render.RenderHelper36;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AimManager implements RenderContract2 {
    private RenderHelper10 a = null;

    public boolean a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 68553623989839L ^ var2;
        long var4 = var2 ^ 127895873690737L;
        if (this.a != null) {
            if ((float)(System.currentTimeMillis() - this.a.a_long_(new Object[0])) > this.a.a_float________________(new Object[0])
                && !this.a.a_boolean_________________________________________(new Object[0])) {
                RenderHelper36 var10000 = new RenderHelper36(b.mouse);
                long var10001 = b.getWindow().getHandle();
                int var10002 = this.a.a(new Object[0]);
                Object[] var10007 = new Object[]{null, null, null, null, var4};
                var10007[3] = 0;
                var10007[2] = 1;
                var10007[1] = var10002;
                var10007[0] = var10001;
                var10000.a(var10007);
                this.a.a_void____________________(new Object[]{true});
            }

            if ((float)(System.currentTimeMillis() - this.a.a_long_(new Object[0])) > this.a.a_float________________(new Object[0])
                && (float)(System.currentTimeMillis() - this.a.a_long_(new Object[0]))
                    > this.a.a_float________________(new Object[0]) + this.a.b(new Object[0])) {
                RenderHelper36 var7 = new RenderHelper36(b.mouse);
                long var8 = b.getWindow().getHandle();
                int var9 = this.a.a(new Object[0]);
                Object[] var10 = new Object[]{null, null, null, null, var4};
                var10[3] = 0;
                var10[2] = 0;
                var10[1] = var9;
                var10[0] = var8;
                var7.a(var10);
                this.a = null;
            }

            return true;
        } else {
            return false;
        }
    }

    public void a_void_(Object[] var1) {
        int var4 = (Integer)var1[0];
        float var5 = (Float)var1[1];
        long var2 = (Long)var1[2];
        var2 = 68553623989839L ^ var2;
        long var6 = var2 ^ 21461094467110L;
        long var10004 = System.currentTimeMillis();
        Object[] var10010 = new Object[]{null, null, var6};
        var10010[1] = 15.0F;
        var10010[0] = 5.0F;
        float var10006 = RenderUtil5.a(var10010);
        Object[] var10011 = new Object[]{null, null, var6};
        var10011[1] = 55.0F;
        var10011[0] = 35.0F;
        float var10007 = RenderUtil5.a(var10011);
        var10010 = new Object[]{null, null, var6};
        var10010[1] = var10007;
        var10010[0] = var10006;
        this.a = new RenderHelper10(var4, var10004, var5, RenderUtil5.a(var10010), false);
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
}
