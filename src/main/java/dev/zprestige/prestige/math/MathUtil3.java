package dev.zprestige.prestige.math;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.network.NetworkUtil2;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.math.BlockPos;

public final class MathUtil3 implements RenderContract2 {
    private static final double e = 6.5;
    public static final Map f = new HashMap();
    public static final Map g = new HashMap();
    public static final Map h = new HashMap();
    private static boolean i;

    private MathUtil3() {
    }

    private static void a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 7601911366469L ^ var1;
        long var3 = var1 ^ 106503665461141L;
        if (!i && Prestige.eventBus != null) {
            i = true;
            Prestige.eventBus.register(new Object[]{new NetworkUtil2(), var3});
        }
    }

    public static void b(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        long var2 = (Long)var0[1];
        var2 = 7601911366469L ^ var2;
        long var4 = var2 ^ 116735878948814L;
        a(new Object[]{var4});
        g.put(var1.toImmutable(), System.currentTimeMillis());
    }

    public static boolean a_boolean_______(Object[] var0) {
        BlockPos var3 = (BlockPos)var0[0];
        long var1 = (Long)var0[1];
        var1 = 7601911366469L ^ var1;
        long var4 = var1 ^ 50975971845519L;
        Event.b();
        a(new Object[]{var4});
        long var7 = System.currentTimeMillis();
        Long var9 = (Long)f.get(var3);
        if (var9 != null && var7 - var9 < 300L) {
            return true;
        } else {
            for (Entry var11 : (Iterable<Entry>)(g.entrySet())) {
                if (var7 - (Long)var11.getValue() <= 100L && ((BlockPos)var11.getKey()).getSquaredDistance(var3) <= 42.25) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean b_boolean_____(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        long var2 = (Long)var0[1];
        var2 = 7601911366469L ^ var2;
        long var4 = var2 ^ 59319111295995L;
        Event.b();
        a(new Object[]{var4});
        Long var7 = (Long)h.get(var1);
        return var7 != null && System.currentTimeMillis() - var7 < 120L;
    }

    public static void c(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        long var2 = (Long)var0[1];
        f.put(var1.toImmutable(), var2);
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
