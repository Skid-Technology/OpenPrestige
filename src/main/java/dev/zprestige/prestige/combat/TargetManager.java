package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PlayerEvent;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent6;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class TargetManager implements RenderContract2 {
    private final Set a;
    private final Map c;

    public TargetManager(long var1) {
        super();
        var1 = 0L;
        var1 = 135428133275062L ^ var1;
        long var3 = var1 ^ 106554379797719L;
        this.a = new HashSet();
        this.c = new IdentityHashMap();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        this.c.clear();
        if (!this.a.isEmpty()) {
            this.a.removeIf(lpL9_0 -> !((net.minecraft.entity.Entity)lpL9_0).isAlive());
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.a.clear();
    }

    public void a(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        this.a.add(var2);
    }

    public Set a_Set(Object[] var1) {
        return this.a;
    }

    public boolean a_boolean_(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 135428133275062L ^ var3;
        long var5 = var3 ^ 5217238789553L;
        if (var2 == b.player) {
            return false;
        } else {
            Boolean var8 = (Boolean)this.c.get(var2);
            if (var8 != null) {
                return var8;
            } else {
                boolean var9 = this.b(new Object[]{var2, var5});
                this.c.put(var2, var9);
                return var9;
            }
        }
    }

    private boolean b(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 135428133275062L ^ var2;
        long var5 = var2 ^ 99671251868934L;
        long var7 = var2 ^ 86195271010853L;
        if (!(var4 instanceof PlayerEntity)) {
            return true;
        } else if (!Prestige.eventBus.hasListeners(new Object[]{TickEvent6.class, var7})) {
            return true;
        } else if (!new TickEvent6().b_boolean__(new Object[]{var5})) {
            return true;
        } else {
            return Prestige.eventBus.hasListeners(new Object[]{PlayerEvent.class, var7}) && new PlayerEvent((PlayerEntity)var4).b_boolean__(new Object[]{var5})
                ? false
                : this.a.contains(var4) && var4.isAlive();
        }
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
