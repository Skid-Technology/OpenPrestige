package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;

public class AntiAction extends Module {
    public AntiAction() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;
    private BoolSetting c;
    private BoolSetting e;
    private BoolSetting f;

    @Subscribe
    public void a(TickEvent5 var1) {
        if ((Boolean)this.e.getValue()
            && InventoryUtil.l(new Object[]{Items.FIREWORK_ROCKET, 65539821781598L})
            && !PositionUtil.a_boolean________________________________________________(new Object[]{120169006001489L})
            && !b.player.getOffHandStack().isOf(Items.CROSSBOW)) {
            var1.a_void_____(new Object[0]);
        } else if (b.crosshairTarget instanceof BlockHitResult var17) {
            if (var17.getBlockPos() != null) {
                boolean var21 = WorldUtil2.b(new Object[]{var17.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L});
                if (var21 && (Boolean)this.d.getValue() && !b.player.isSneaking() && InventoryUtil.l(new Object[]{Items.GLOWSTONE, 65539821781598L})) {
                    int var19 = WorldUtil2.a_int______________________________(new Object[]{var17.getBlockPos(), 86201794204623L});
                    if (var19 != 0) {
                        var1.a_void_____(new Object[0]);
                    }
                }

                boolean var22 = WorldUtil2.b(new Object[]{var17.getBlockPos(), Blocks.ENDER_CHEST, 91415361137757L});
                if (var22
                    && (Boolean)this.a.getValue()
                    && !b.player.isSneaking()
                    && (
                        this.a(new Object[]{b.player.getMainHandStack(), 123053009433525L})
                            || this.a(new Object[]{b.player.getOffHandStack(), 123053009433525L})
                    )) {
                    var1.a_void_____(new Object[0]);
                }

                boolean var20 = InventoryUtil.l(new Object[]{Items.GLOWSTONE, 65539821781598L});
                if (var20
                    && (!var21 || b.player.isSneaking())
                    && (Boolean)this.c.getValue()
                    && WorldUtil2.e(new Object[]{var17.getBlockPos(), 100601689205754L})) {
                    var1.a_void_____(new Object[0]);
                }
            }
        }
    }

    private boolean a(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        long var3 = (Long)var1[1];
        var3 = 46972721282546L ^ var3;
        long var5 = var3 ^ 54366661580408L;
        long var7 = var3 ^ 38836746770986L;
        long var9 = var3 ^ 66157415360379L;
        if (var2.isEmpty()) {
            return false;
        } else {
            return !InventoryUtil.a(new Object[]{var2, var5}) && !InventoryUtil.b(new Object[]{var2, var7}) && !InventoryUtil.o(new Object[]{var2, var9})
                ? var2.isOf(Items.END_CRYSTAL)
                    || var2.isOf(Items.OBSIDIAN)
                    || var2.isOf(Items.CRYING_OBSIDIAN)
                    || var2.isOf(Items.TOTEM_OF_UNDYING)
                    || var2.isOf(Items.EXPERIENCE_BOTTLE)
                    || var2.isOf(Items.GLOWSTONE)
                : true;
        }
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        Event.b();
        if (b.crosshairTarget instanceof EntityHitResult var7) {
            if (var7.getEntity() instanceof PlayerEntity var10) {
                if ((Boolean)this.f.getValue()
                    && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})
                    && var10.isUsingItem()
                    && var10.getActiveItem().isOf(Items.SHIELD)) {
                    var1.a_void_____(new Object[0]);
                }
            }
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
