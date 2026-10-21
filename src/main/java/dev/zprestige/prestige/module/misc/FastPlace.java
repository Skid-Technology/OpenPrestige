package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.CombatHelper3;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.item.BlockItem;

public class FastPlace extends Module {
    public FastPlace() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private IntSetting a;
    private IntSetting c;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (!(Boolean)this.d.getValue()
            || b.player.getMainHandStack().getItem() instanceof BlockItem
            || b.player.getOffHandStack().getItem() instanceof BlockItem) {
            if ((Integer)this.a.getValue() > (Integer)this.c.getValue()) {
                this.a.setValue((Integer)this.c.getValue());
            }

            CombatHelper3 var11 = new CombatHelper3(b);
            int var10000 = var11.a(new Object[]{86630877778275L});
            int var10001 = (Integer)this.a.getValue();
            int var10002 = (Integer)this.c.getValue();
            Object[] var10005 = new Object[]{null, null, 97028099236409L};
            var10005[1] = var10002;
            var10005[0] = var10001;
            int var12 = Math.min(var10000, RenderUtil5.a_int__________________________(var10005));
            Object[] var10004 = new Object[]{null, 430982545149L};
            var10004[0] = var12;
            var11.a_void__(var10004);
        }
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
