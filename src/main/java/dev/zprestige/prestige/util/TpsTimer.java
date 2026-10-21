package dev.zprestige.prestige.util;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderHelper23;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.type.NotificationType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import org.joml.Matrix4f;

public class TpsTimer implements RenderContract2 {
    private final ArrayList a;
    public static float d = 0.0F;

    public TpsTimer(long var1) {
        super();
        var1 = 0L;
        var1 = 10332698973130L ^ var1;
        long var3 = var1 ^ 102853721104670L;
        this.a = new ArrayList();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        Event.b();
        d = (float)(System.currentTimeMillis() - 0L) * 0.005F;
        System.currentTimeMillis();
        if (!this.a.isEmpty()) {
            float var7 = b.getWindow().getScaleFactor();
            float var8 = 2.0F / var7;
            Matrix4f var9 = new Matrix4f().scale(var8, var8, 1.0F);
            float var10 = b.getWindow().getFramebufferWidth() / 2.0F;
            float var11 = b.getWindow().getFramebufferHeight() / 2.0F;
            float var12 = 150.0F;
            float var13 = 30.0F;
            float var14 = var10 - var12 - 10.0F;
            float var15 = var11 - 50.0F;
            Iterator var16 = this.a.iterator();

            while (var16.hasNext()) {
                RenderHelper23 var17 = (RenderHelper23)var16.next();
                if (var17.h) {
                    var16.remove();
                } else {
                    boolean var18 = var15 > var11 / 4.0F;
                    RenderHelper8 var10003 = var1.a;
                    MatrixCameraData var10004 = var1.b;
                    Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, 79831589046473L};
                    var10012[7] = var18;
                    var10012[6] = var13;
                    var10012[5] = var12;
                    var10012[4] = var15;
                    var10012[3] = var14;
                    var10012[2] = var10004;
                    var10012[1] = var10003;
                    var10012[0] = var9;
                    var15 -= var17.a(var10012);
                }
            }
        }
    }

    public void a(Object[] var1) {
        String var3 = (String)var1[0];
        String var2 = (String)var1[1];
        long var4 = (Long)var1[2];
        NotificationType var8 = (NotificationType)var1[3];
        long var6 = (Long)var1[4];
        var6 = 10332698973130L ^ var6;
        long var9 = var6 ^ 125851865862184L;
        Event.b();

        for (RenderHelper23 var13 : (Iterable<RenderHelper23>)(new ArrayList(this.a))) {
            if (var13.a.equals(var3) && var13.b.equals(var2)) {
                var13.g = true;
                var13.f = false;
            }
        }

        this.a.add(new RenderHelper23(var3, var2, var4, var8, var9));
    }

    public boolean a_boolean______________________________________________(Object[] var1) {
        return !this.a.isEmpty();
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
