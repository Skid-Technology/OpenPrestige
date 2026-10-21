package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.player.PlayerEntity;

public class Teams extends Module {
    public Teams() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private static volatile Set a = new HashSet();

    public static boolean a(Object[] var0) {
        String var1 = (String)var0[0];
        Event.b();

        for (PlayerEntity var6 : (Iterable<PlayerEntity>)(a)) {
            if (var6.getName().getString().equals(var1)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        a = new HashSet();
    }

    @Override
    public void onDisable(Object[] var1) {
        a = new HashSet();
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        HashSet var7 = new HashSet();
        Event.b();

        for (PlayerEntity var9 : b.world.getPlayers()) {
            if (var9 != b.player && this.d(new Object[]{var9, 124554607975631L})) {
                var7.add(var9);
            }
        }

        a = var7;
    }

    private boolean d(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        boolean[] var5 = new boolean[]{false};
        var4.getDisplayName().asOrderedText().accept((lpL331_0, lpL331_1, lpL331_2) -> {
            b.player.getDisplayName().asOrderedText().accept((lpL332_0, lpL332_1, lpL332_2) -> {
                if (((net.minecraft.text.Style)lpL332_1) != null && ((net.minecraft.text.Style)lpL332_1).getColor() != null && ((net.minecraft.text.Style)lpL331_1).getColor() != null && ((net.minecraft.text.Style)lpL331_1).getColor().equals(((net.minecraft.text.Style)lpL332_1).getColor())) {
                    var5[0] = true;
                }

                return false;
            });
            return false;
        });
        return var5[0];
    }

    private static Field g(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field h(Class var0, String var1, Class var2) {
        Field var3 = g(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = h(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method g(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method h(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = g(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = h(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
