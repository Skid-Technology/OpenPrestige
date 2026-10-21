package dev.zprestige.prestige.module.movement;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class ShortPearl extends Module implements RenderContract3 {
    public ShortPearl() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private RangeSetting c;
    private BoolSetting d;
    private FloatSetting e;
    private FloatSetting f;
    private RenderHelper27 g = new RenderHelper27(26293358099357L);
    private float h = 0.0F;
    private int i = 0;
    private int j = 0;
    private int k = -1;
    private int l = -1;
    private int m = -1;
    private float n;

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 43040229079061L;
        long var6 = var2 ^ 43966253580757L;
        if (!((String)this.a.getValue()).equals("Silent")) {
            return null;
        } else if (this.m != -1) {
            if (b.player.age <= this.m + 1) {
                return new RenderBatch(b.player.getYaw(), this.n);
            } else {
                this.m = -1;
                this.toggle(new Object[]{var4});
                return null;
            }
        } else {
            float var9 = (Float)this.e.getValue() - 1.0F + RenderUtil5.a.nextFloat() * 2.0F;
            Item var10000 = Items.ENDER_PEARL;
            Runnable var10001 = () -> {
                float var5 = b.player.getPitch();
                b.player.setPitch(var9);
                InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                b.player.setPitch(var5);
            };
            Object[] var10005 = new Object[]{null, null, null, var6};
            var10005[2] = true;
            var10005[1] = var10001;
            var10005[0] = var10000;
            if (!InventoryUtil.j(var10005)) {
                return null;
            } else {
                this.m = b.player.age;
                this.n = var9;
                return new RenderBatch(b.player.getYaw(), var9);
            }
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (((String)this.a.getValue()).equals("Legit")) {
            RenderHelper27 var10000 = this.g;
            float var10001 = (Float)this.c.getValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                switch (this.i) {
                    case 0:
                        if (b.player.getMainHandStack().getItem() == Items.ENDER_PEARL) {
                            this.j(new Object[]{3539038516859L});
                            return;
                        }

                        Integer var21 = InventoryUtil.a_Integer(new Object[]{Items.ENDER_PEARL, 64376993919254L});
                        if (var21 == null) {
                            this.toggle(new Object[]{115103427876780L});
                            return;
                        }

                        this.j = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                        int var25 = var21;
                        Object[] var28 = new Object[]{null, 37392877673079L};
                        var28[0] = var25;
                        InventoryUtil.a_void________(var28);
                        this.j(new Object[]{3539038516859L});
                        break;
                    case 1:
                        RenderBatch var24 = new RenderBatch(0.0F, (Float)this.e.getValue());
                        var10001 = (Float)this.f.getValue() / 2.0F;
                        Object[] var29 = new Object[]{null, null, null, null, 8666705888841L};
                        var29[3] = 0.5F;
                        var29[2] = 0.0F;
                        var29[1] = var10001;
                        var29[0] = var24;
                        if (WorldUtil.d(var29)) {
                            b.player.setPitch((Float)this.e.getValue());
                            this.k = b.player.age;
                            this.j(new Object[]{3539038516859L});
                        }
                        break;
                    case 2:
                        if (b.player.age >= this.k + 2) {
                            InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                            this.l = b.player.age;
                            this.j(new Object[]{3539038516859L});
                        }
                        break;
                    case 3:
                        if (b.player.age >= this.l + 2) {
                            this.j(new Object[]{3539038516859L});
                        }
                        break;
                    case 4:
                        RenderBatch var23 = new RenderBatch(0.0F, this.h);
                        var10001 = (Float)this.f.getValue() / 2.0F;
                        Object[] var10006 = new Object[]{null, null, null, null, 8666705888841L};
                        var10006[3] = 0.5F;
                        var10006[2] = 0.0F;
                        var10006[1] = var10001;
                        var10006[0] = var23;
                        if (WorldUtil.d(var10006)) {
                            if (!(Boolean)this.d.getValue()) {
                                this.toggle(new Object[]{115103427876780L});
                            } else {
                                this.j(new Object[]{3539038516859L});
                            }
                        }
                        break;
                    case 5:
                        if ((Boolean)this.d.getValue()) {
                            int var22 = this.j;
                            Object[] var10003 = new Object[]{null, 37392877673079L};
                            var10003[0] = var22;
                            InventoryUtil.a_void________(var10003);
                            this.toggle(new Object[]{115103427876780L});
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
        this.g.a_void_____________________(new Object[]{var4});
        this.h = b.player.getPitch();
        this.i = 0;
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.g.a_void_____________________(new Object[]{var4});
        this.c.reroll(new Object[]{var6});
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 40129861624705L ^ var2;
        long var4 = var2 ^ 125737933703783L;
        long var6 = var2 ^ 22686853749531L;
        this.g.a_void_____________________(new Object[]{var4});
        this.c.reroll(new Object[]{var6});
        this.i++;
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
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
