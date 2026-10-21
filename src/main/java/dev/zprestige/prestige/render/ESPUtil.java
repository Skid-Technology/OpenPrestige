package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.type.ShaderMode;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class ESPUtil implements RenderContract2 {
    private static PostShader a = ShaderMode.NORMAL.a(new Object[0]);
    public static final Map c = new ConcurrentHashMap();
    public static ESPHelper d = new ESPHelper(new EntityType[0], 0L);
    public static boolean e = false;
    public static boolean f = false;
    private static final Map g = new ConcurrentHashMap();
    private static final Class h = Void.class;

    public static void a(Object[] var0) {
        ShaderMode var1 = (ShaderMode)var0[0];
        if (var1 != null && var1 != ShaderMode.NONE && var1.a(new Object[0]) != null) {
            a = var1.a(new Object[0]);
        }
    }

    public static PostShader a_PostShader(Object[] var0) {
        return a;
    }

    public static boolean a_boolean______________________________________(Object[] var0) {
        EntityModel var3 = (EntityModel)var0[0];
        ESPHelper var4 = (ESPHelper)var0[1];
        long var1 = (Long)var0[2];
        var1 = 129352636611362L ^ var1;
        long var5 = var1 ^ 36945991568267L;
        long var7 = var1 ^ 26166079508179L;
        Event.b();
        Class var10 = var3.getClass();
        Boolean var11 = (Boolean)c.get(var10);
        if (var11 != null) {
            return var11;
        } else if (!var4.f(new Object[]{var7}) && b.world != null) {
            Iterator var12 = var4.iterator();

            while (true) {
                Entity var14;
                boolean var15;
                while (true) {
                    if (!var12.hasNext()) {
                        c.put(var10, false);
                        return false;
                    }

                    EntityType var13 = (EntityType)var12.next();
                    if (var13 == EntityType.PLAYER) {
                        var14 = b.player;
                        var15 = false;
                        if (var14 == null) {
                            continue;
                        }
                        break;
                    } else {
                        var14 = var13.create(b.world, null);
                        if (var14 != null) {
                            var15 = true;
                            break;
                        }
                    }
                }

                try {
                    EntityRenderer var16 = b.getEntityRenderDispatcher().getRenderer((Entity)var14);
                    Class var17 = a_Class__(new Object[]{var16, var5});
                    if (var17 != null && var17 == var10) {
                        c.put(var10, true);
                        return true;
                    }
                } finally {
                    if (var15) {
                        var14.discard();
                    }
                }
            }
        } else {
            return false;
        }
    }

    private static Class a_Class__(Object[] var0) {
        EntityRenderer var3 = (EntityRenderer)var0[0];
        Event.b();
        Class var5 = var3.getClass();
        Class var6 = (Class)g.get(var5);
        if (var6 != null) {
            return var6 == h ? null : var6;
        } else {
            Class var7 = null;
            if (var3 instanceof LivingEntityRenderer var8) {
                var7 = var8.getModel().getClass();
            } else {
                for (Field var12 : var5.getDeclaredFields()) {
                    if (EntityModel.class.isAssignableFrom(var12.getType())) {
                        var12.setAccessible(true);

                        try {
                            EntityModel var13 = (EntityModel)var12.get(var3);
                            if (var13 != null) {
                                var7 = var13.getClass();
                                break;
                            }
                        } catch (IllegalAccessException var14) {
                        }
                    }
                }
            }

            g.put(var5, var7 == null ? h : var7);
            return var7;
        }
    }

    public static boolean b(Object[] var0) {
        EntityModel var1 = (EntityModel)var0[0];
        long var2 = (Long)var0[1];
        var2 = 129352636611362L ^ var2;
        long var4 = var2 ^ 76965806763794L;
        long var6 = var2 ^ 127172186057290L;
        Event.b();
        Class var9 = var1.getClass();
        Boolean var10 = (Boolean)c.get(var9);
        if (var10 != null) {
            return var10;
        } else if (!d.f(new Object[]{var6}) && b.world != null) {
            Iterator var11 = d.iterator();

            while (true) {
                Entity var13;
                boolean var14;
                while (true) {
                    if (!var11.hasNext()) {
                        c.put(var9, false);
                        return false;
                    }

                    EntityType var12 = (EntityType)var11.next();
                    if (var12 == EntityType.PLAYER) {
                        var13 = b.player;
                        var14 = false;
                        if (var13 == null) {
                            continue;
                        }
                        break;
                    } else {
                        var13 = var12.create(b.world, null);
                        if (var13 != null) {
                            var14 = true;
                            break;
                        }
                    }
                }

                try {
                    EntityRenderer var15 = b.getEntityRenderDispatcher().getRenderer((Entity)var13);
                    Class var16 = a_Class__(new Object[]{var15, var4});
                    if (var16 != null && var16 == var9) {
                        c.put(var9, true);
                        return true;
                    }
                } finally {
                    if (var14) {
                        var13.discard();
                    }
                }
            }
        } else {
            return false;
        }
    }

    public static boolean c(Object[] var0) {
        EntityModel var3 = (EntityModel)var0[0];
        long var1 = (Long)var0[1];
        var1 = 129352636611362L ^ var1;
        long var4 = var1 ^ 127477464040019L;
        return a_boolean______________________________________(new Object[]{var3, d, var4});
    }

    static {
        RenderContract5.c.register((RenderContract6)(lpL4_0, lpL4_1) -> {
            if (a != null && e) {
                a.b_void______(new Object[]{((dev.zprestige.prestige.render.RenderHelper8)lpL4_0), ((dev.zprestige.prestige.data.MatrixCameraData)lpL4_1), 90284759324056L});
            }
        });
        RenderContract5.b.register((RenderContract6)(lpL3_0, lpL3_1) -> {
            if (a != null && e) {
                a.a_void_______________(new Object[]{((dev.zprestige.prestige.render.RenderHelper8)lpL3_0), ((dev.zprestige.prestige.data.MatrixCameraData)lpL3_1), 93702078595239L});
            }
        });
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

    private static Exception a(Exception var0) {
        return var0;
    }
}
