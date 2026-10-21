package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.AimManager;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.item.AxeItem;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;

public class AutoClicker extends Module {
    private EnumSetting a;
    private EnumSetting c;
    private IntSetting d;
    private FloatSetting e;
    private FloatSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private FloatSetting i;
    private FloatSetting j;
    private RenderHelper27 k = new RenderHelper27(26293358099357L);
    private float l = 200.0F;
    private float m;
    private float n = -1.0F;
    private int o = -1;
    private long p;
    private float q;

    public AutoClicker() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.d.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL218_0 -> ((String)this.a.getValue()).equals("Interval"), 98265949679296L});
        this.c.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL219_0 -> ((String)this.a.getValue()).equals("Interval"), 90319707064974L});
        this.i.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL213_0 -> this.d(new Object[]{105783513896537L}), 35046756052036L});
        this.f.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL216_0 -> this.d(new Object[]{105783513896537L}), 35046756052036L});
        this.j
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL212_0 -> this.d(new Object[]{105783513896537L}) && (Float)this.i.getValue() > 0.0F, 35046756052036L}
            );
        this.h.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL214_0 -> this.d(new Object[]{105783513896537L}), 73196168581295L});
        this.g.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL215_0 -> this.d(new Object[]{105783513896537L}), 73196168581295L});
        this.e.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL217_0 -> this.d(new Object[]{105783513896537L}), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    private boolean d(Object[] var1) {
        return ((String)this.a.getValue()).equals("Ghost");
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 17075022824029L;
        long var6 = var2 ^ 113221713884235L;
        this.j(new Object[]{var4});
        this.o = -1;
        this.q = 0.0F;
        float var10002 = Math.max((Float)this.e.getValue(), 1.0F);
        float var10003 = (Float)this.f.getValue();
        Object[] var10006 = new Object[]{null, null, var6};
        var10006[1] = var10003;
        var10006[0] = var10002;
        this.l = 1000.0F / RenderUtil5.a(var10006);
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen == null && b.player != null && !Prestige.j.a(new Object[]{46247009548246L})) {
            if (((String)this.a.getValue()).equals("Interval")) {
                RenderHelper27 var23 = this.k;
                float var25 = ((Integer)this.d.getValue()).floatValue();
                Object[] var27 = new Object[]{null, 2505922520844L};
                var27[0] = var25;
                if (var23.a(var27)) {
                    AimManager var24 = Prestige.j;
                    int var26 = ((String)this.c.getValue()).equals("Left") ? 0 : 1;
                    Object[] var28 = new Object[]{null, null, 60507167928612L};
                    var28[1] = 0.0F;
                    var28[0] = var26;
                    var24.a_void_(var28);
                    this.k.a_void_____________________(new Object[]{93540861878173L});
                }
            } else if (!b.player.isUsingItem()) {
                if (!(Boolean)this.g.getValue() || !(b.crosshairTarget instanceof BlockHitResult)) {
                    if (!(Boolean)this.h.getValue()
                        || InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})
                        || b.player.getMainHandStack().getItem() instanceof AxeItem) {
                        if (b.crosshairTarget instanceof EntityHitResult var19) {
                            int var21 = var19.getEntity().getId();
                            if (var21 != this.o) {
                                this.o = var21;
                                this.p = System.currentTimeMillis();
                                this.q = this.b_float____________(new Object[]{97004612147087L});
                            }

                            if (System.currentTimeMillis() - this.p < (long)this.q) {
                                return;
                            }
                        } else {
                            this.o = -1;
                        }

                        RenderHelper27 var10000 = this.k;
                        float var10001 = this.l;
                        Object[] var10004 = new Object[]{null, 2505922520844L};
                        var10004[0] = var10001;
                        if (var10000.a(var10004)) {
                            AimManager var22 = Prestige.j;
                            Object[] var10005 = new Object[]{null, null, 60507167928612L};
                            var10005[1] = 0.0F;
                            var10005[0] = 0;
                            var22.a_void_(var10005);
                            float var10002 = Math.max((Float)this.e.getValue(), 1.0F);
                            float var10003 = (Float)this.f.getValue();
                            Object[] var10006 = new Object[]{null, null, 29524726012237L};
                            var10006[1] = var10003;
                            var10006[0] = var10002;
                            this.l = 1000.0F / RenderUtil5.a(var10006);
                            this.k.a_void_____________________(new Object[]{93540861878173L});
                        }
                    }
                }
            }
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 94643421949254L ^ var2;
        long var4 = var2 ^ 35233642579180L;
        long var6 = var2 ^ 83965501772691L;
        Event.b();
        this.n = (Float)this.i.getValue();
        if ((Float)this.i.getValue() <= 0.0F) {
            this.m = 0.0F;
        } else {
            float var10001 = (Float)this.i.getValue();
            Object[] var10005 = new Object[]{null, null, var4};
            var10005[1] = 15.0F;
            var10005[0] = var10001;
            var10001 = c(var10005);
            float var10002 = (Float)this.i.getValue() - 50.0F;
            float var10003 = (Float)this.i.getValue() + 50.0F;
            Object[] var10006 = new Object[]{null, null, null, var6};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = var10001;
            this.m = d_float__(var10006);
        }
    }

    private float b_float____________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 94643421949254L ^ var2;
        long var4 = var2 ^ 138052841624978L;
        long var6 = var2 ^ 12740850515000L;
        long var8 = var2 ^ 114077300321095L;
        if ((Float)this.i.getValue() != this.n) {
            this.j(new Object[]{var4});
        }

        if (this.m <= 0.0F) {
            return 0.0F;
        } else {
            float var11 = this.j.getValue() > 0.0F ? (Float)this.j.getValue() : 1.0F;
            float var10000 = this.m;
            Object[] var10004 = new Object[]{null, null, var6};
            var10004[1] = var11;
            var10004[0] = var10000;
            var10000 = c(var10004);
            Object[] var10005 = new Object[]{null, null, null, var8};
            var10005[2] = 500.0F;
            var10005[1] = 80.0F;
            var10005[0] = var10000;
            return d_float__(var10005);
        }
    }

    private static float c(Object[] var0) {
        float var1 = (Float)var0[0];
        float var4 = (Float)var0[1];
        long var2 = (Long)var0[2];
        var2 = 94643421949254L ^ var2;
        long var5 = var2 ^ 80769315959546L;
        Object[] var10004 = new Object[]{null, null, var5};
        var10004[1] = 1.0F;
        var10004[0] = 1.0E-6F;
        float var7 = RenderUtil5.a(var10004);
        var10004 = new Object[]{null, null, var5};
        var10004[1] = 1.0F;
        var10004[0] = 0.0F;
        float var8 = RenderUtil5.a(var10004);
        return var1 + var4 * (float)(Math.sqrt(-2.0 * Math.log(var7)) * Math.cos((Math.PI * 2) * var8));
    }

    private static float d_float__(Object[] var0) {
        float var3 = (Float)var0[0];
        float var5 = (Float)var0[1];
        float var4 = (Float)var0[2];
        return Math.max(var5, Math.min(var4, var3));
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
