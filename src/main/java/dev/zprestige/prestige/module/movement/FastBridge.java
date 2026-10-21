package dev.zprestige.prestige.module.movement;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.world.PositionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;

public class FastBridge extends Module {
    public FastBridge() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private boolean i = false;

    @Subscribe
    public void a(TickEvent19 var1) {
        boolean var7 = !(b.player.getMainHandStack().getItem() instanceof BlockItem) && !(b.player.getOffHandStack().getItem() instanceof BlockItem);
        if (b.player.getPitch() < 70.0F) {
            var7 = true;
        }

        BlockPos var8 = BlockPos.ofFloored(PositionUtil.b(new Object[]{118247215910525L})).down();
        if (!b.world.getBlockState(var8).isReplaceable()
            || !b.world.getBlockState(var8.down()).isReplaceable()
            || !b.world.getBlockState(var8.down().down()).isReplaceable()) {
            var7 = true;
        }

        if (var7) {
            if (this.i) {
                b.options.sneakKey.setPressed(false);
                this.i = false;
            }
        } else {
            b.options.sneakKey.setPressed(true);
            this.i = true;
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
