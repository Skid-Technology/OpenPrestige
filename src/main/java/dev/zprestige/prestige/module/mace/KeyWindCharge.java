package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class KeyWindCharge extends Module {
    public KeyWindCharge() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private RangeSetting c;
    private RenderHelper27 d = new RenderHelper27(26293358099357L);
    private int e = 0;
    private boolean i = false;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 108480545928934L;
        this.i = false;
        this.j(new Object[]{var4});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            String var23 = (String)this.a.getValue();
            switch (var23) {
                case "Normal":
                    RenderHelper27 var26 = this.d;
                    float var28 = (Float)this.c.getValue();
                    Object[] var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = var28;
                    if (!var26.a(var10004)) {
                        return;
                    }

                    if (this.i) {
                        int var27 = this.e;
                        Object[] var29 = new Object[]{null, 37392877673079L};
                        var29[0] = var27;
                        InventoryUtil.a_void________(var29);
                        this.toggle(new Object[]{115103427876780L});
                        return;
                    }

                    if (!a_boolean_______________________________(new Object[]{b.player.getMainHandStack(), 34044435795471L})) {
                        int var25 = this.a(new Object[]{120121965301062L});
                        if (var25 == -1) {
                            this.toggle(new Object[]{115103427876780L});
                            return;
                        }

                        this.e = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                        Object[] var10003 = new Object[]{null, 37392877673079L};
                        var10003[0] = var25;
                        InventoryUtil.a_void________(var10003);
                        this.j(new Object[]{33580849863648L});
                        return;
                    }

                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                    this.i = true;
                    this.j(new Object[]{33580849863648L});
                    break;
                case "Silent":
                    int var10000 = this.a(new Object[]{120121965301062L});
                    Runnable var10001 = () -> InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                    Object[] var10005 = new Object[]{null, null, null, 53805837885570L};
                    var10005[2] = true;
                    var10005[1] = var10001;
                    var10005[0] = var10000;
                    InventoryUtil.b_void__(var10005);
                    this.toggle(new Object[]{115103427876780L});
            }
        }
    }

    private int a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 70374441990552L ^ var2;
        long var4 = var2 ^ 56864663370449L;
        Event.b();

        for (int var7 = 0; var7 <= 8; var7++) {
            if (a_boolean_______________________________(new Object[]{b.player.getInventory().getStack(var7), var4})) {
                return var7;
            }
        }

        return -1;
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 70374441990552L ^ var2;
        long var4 = var2 ^ 12751101836773L;
        long var6 = var2 ^ 120264685874329L;
        this.c.reroll(new Object[]{var6});
        this.d.a_void_____________________(new Object[]{var4});
    }

    public static boolean a_boolean_______________________________(Object[] var0) {
        ItemStack var1 = (ItemStack)var0[0];
        return var1.getItem().equals(Items.WIND_CHARGE);
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
