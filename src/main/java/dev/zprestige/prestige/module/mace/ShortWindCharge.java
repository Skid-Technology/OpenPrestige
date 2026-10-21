package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil2;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class ShortWindCharge extends Module implements RenderContract3 {
    public ShortWindCharge() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private RangeSetting c;
    private BoolSetting d;
    private BoolSetting e;
    private FloatSetting f;
    private FloatSetting g;
    private RenderHelper27 h = new RenderHelper27(26293358099357L);
    private float i = 0.0F;
    private int j = 0;
    private int k = 0;
    private boolean l;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 43040229079061L;
        long var6 = var2 ^ 101476654358216L;
        long var8 = var2 ^ 140072483835707L;
        long var10 = var2 ^ 58861972349919L;
        long var12 = var2 ^ 102840094869659L;
        long var14 = var2 ^ 120434008667598L;
        long var16 = var2 ^ 58982489950237L;
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (this.l) {
                if (b.player.isOnGround() && (Boolean)this.e.getValue()) {
                    RenderUtil2.a(new Object[]{var10});
                }

                InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var12});
                this.l = false;
            }

            if (!((String)this.a.getValue()).equals("Silent")) {
                return null;
            } else if (this.k != -1) {
                int var10000 = this.k;
                Object[] var10003 = new Object[]{null, var14};
                var10003[0] = var10000;
                InventoryUtil.a_void________(var10003);
                this.k = -1;
                this.toggle(new Object[]{var4});
                return null;
            } else {
                float var19 = (Float)this.f.getValue() - 1.0F + RenderUtil5.a.nextFloat() * 2.0F;
                int var20 = this.d(new Object[]{var6});
                if (var20 == -1) {
                    return null;
                } else {
                    this.k = InventoryUtil.a_int_____(new Object[]{var16});
                    if (b.player.isOnGround() && (Boolean)this.e.getValue()) {
                        RenderUtil2.a(new Object[]{var10});
                    }

                    Runnable var10001 = () -> {
                        float var5 = b.player.getPitch();
                        b.player.setPitch(var19);
                        InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                        b.player.setPitch(var5);
                    };
                    Object[] var10005 = new Object[]{null, null, null, var8};
                    var10005[2] = false;
                    var10005[1] = var10001;
                    var10005[0] = var20;
                    InventoryUtil.b_void__(var10005);
                    return new RenderBatch(b.player.getYaw(), var19);
                }
            }
        } else {
            return null;
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (((String)this.a.getValue()).equals("Legit")) {
                RenderHelper27 var10000 = this.h;
                float var10001 = (Float)this.c.getValue();
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = var10001;
                if (var10000.a(var10004)) {
                    switch (this.j) {
                        case 0:
                            if (a_boolean_________________________________(new Object[]{b.player.getMainHandStack(), 62546557267709L})) {
                                this.j(new Object[]{34429932222732L});
                                return;
                            }

                            int var24 = this.d(new Object[]{21716706357617L});
                            if (var24 == -1) {
                                this.toggle(new Object[]{115103427876780L});
                                return;
                            }

                            this.k = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                            Object[] var30 = new Object[]{null, 37392877673079L};
                            var30[0] = var24;
                            InventoryUtil.a_void________(var30);
                            this.j(new Object[]{34429932222732L});
                            break;
                        case 1:
                            float var23 = (Float)this.f.getValue() - 1.0F + RenderUtil5.a.nextFloat() * 2.0F;
                            RenderBatch var27 = new RenderBatch(0.0F, var23);
                            var10001 = (Float)this.g.getValue() / 2.0F;
                            Object[] var31 = new Object[]{null, null, null, null, 40447786406815L};
                            var31[3] = 0.5F;
                            var31[2] = 0.0F;
                            var31[1] = var10001;
                            var31[0] = var27;
                            if (WorldUtil.c(var31)) {
                                this.l = true;
                                this.j(new Object[]{34429932222732L});
                            }
                            break;
                        case 2:
                            if (this.l) {
                                return;
                            }

                            RenderBatch var26 = new RenderBatch(0.0F, this.i);
                            var10001 = (Float)this.g.getValue() / 2.0F;
                            Object[] var10006 = new Object[]{null, null, null, null, 8666705888841L};
                            var10006[3] = 0.5F;
                            var10006[2] = 0.0F;
                            var10006[1] = var10001;
                            var10006[0] = var26;
                            if (WorldUtil.d(var10006)) {
                                if (!(Boolean)this.d.getValue()) {
                                    this.toggle(new Object[]{115103427876780L});
                                } else {
                                    this.j(new Object[]{34429932222732L});
                                }
                            }
                            break;
                        case 3:
                            if ((Boolean)this.d.getValue()) {
                                int var25 = this.k;
                                Object[] var10003 = new Object[]{null, 37392877673079L};
                                var10003[0] = var25;
                                InventoryUtil.a_void________(var10003);
                                this.toggle(new Object[]{115103427876780L});
                            }
                    }
                }
            }
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 45286455980699L;
        long var6 = var2 ^ 87726956962791L;
        long var8 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var8});
        this.h.a_void_____________________(new Object[]{var4});
        this.i = b.player.getPitch();
        this.j = 0;
        this.h.a_void_____________________(new Object[]{var4});
        this.c.reroll(new Object[]{var6});
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 66991506590364L ^ var2;
        long var4 = var2 ^ 130493300157453L;
        long var6 = var2 ^ 17966985477489L;
        this.h.a_void_____________________(new Object[]{var4});
        this.c.reroll(new Object[]{var6});
        this.j++;
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
    }

    private int d(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (var6.getItem().equals(Items.WIND_CHARGE)) {
                return var5;
            }
        }

        return -1;
    }

    public static boolean a_boolean_________________________________(Object[] var0) {
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
