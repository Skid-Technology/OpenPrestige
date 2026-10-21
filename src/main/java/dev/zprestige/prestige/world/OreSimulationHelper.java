package dev.zprestige.prestige.world;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import net.minecraft.world.chunk.Chunk;

public class OreSimulationHelper implements Iterator, RenderContract2 {
    private final WorldHelper4 a;
    private final boolean c;
    private int d;
    private Chunk e;

    public OreSimulationHelper(boolean var1, long var2) {
        super();
        var2 = 0L;
        var2 = 115329937482544L ^ var2;
        long var4 = var2 ^ 118726270478236L;
        long var6 = var2 ^ 106313485447367L;
        this.a = new WorldHelper4(new WorldHelper3(b.world.getChunkManager()).a(new Object[]{var6}));
        this.d = 0;
        this.c = var1;
        this.a(new Object[]{var4});
    }

    private Chunk a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 115329937482544L ^ var2;
        long var4 = var2 ^ 92299072971527L;
        long var6 = var2 ^ 110664725166101L;
        Chunk var9 = this.e;
        Event.b();
        this.e = null;

        while (this.d < this.a.a(new Object[]{var4}).length()) {
            this.e = (Chunk)this.a.a(new Object[]{var4}).get(this.d++);
            if (this.e != null && (!this.c || this.a_boolean_______________________________________________(new Object[]{this.e, var6}))) {
                break;
            }
        }

        return var9;
    }

    private boolean a_boolean_______________________________________________(Object[] var1) {
        Chunk var4 = (Chunk)var1[0];
        int var6 = var4.getPos().x;
        Event.b();
        int var7 = var4.getPos().z;
        return b.world.getChunkManager().isChunkLoaded(var6 + 1, var7)
            && b.world.getChunkManager().isChunkLoaded(var6 - 1, var7)
            && b.world.getChunkManager().isChunkLoaded(var6, var7 + 1)
            && b.world.getChunkManager().isChunkLoaded(var6, var7 - 1);
    }

    @Override
    public boolean hasNext() {
        return this.e != null;
    }

    public Chunk b(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 115329937482544L ^ var2;
        long var4 = var2 ^ 61144889762510L;
        return this.a(new Object[]{var4});
    }

    @Override
    public Object next() {
        return this.b(new Object[]{79290930077513L});
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
