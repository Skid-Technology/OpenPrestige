package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent5;
import dev.zprestige.prestige.event.IntEvent3;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.inventory.InventoryHelper;
import dev.zprestige.prestige.inventory.InventoryUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class Animations extends Module {
    public Animations() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private IntSetting a;
    private BoolSetting d;
    private static final long l = 5714003492503289866L;

    @Subscribe
    public void a(TickEvent27 var1) {
        Event.b();
        InventoryHelper var17 = new InventoryHelper(b.getEntityRenderDispatcher().getHeldItemRenderer());
        if (var17.a_float________(new Object[]{30464984678200L}) <= 1.0F) {
            Object[] var10004 = new Object[]{null, 82139320385297L};
            var10004[0] = 1.0F;
            var17.a(var10004);
            var17.c(new Object[]{b.player.getMainHandStack(), 74818249733785L});
        }

        if (var17.b_float____(new Object[]{98176128633121L}) <= 1.0F) {
            Object[] var18 = new Object[]{null, 7053432785255L};
            var18[0] = 1.0F;
            var17.b(var18);
            var17.d(new Object[]{b.player.getOffHandStack(), 57109304734105L});
        }
    }

    @Subscribe
    public void a(IntEvent3 var1) {
        if ((Boolean)this.d.getValue()) {
            var1.a = (int)l;
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(FloatEvent5 var1) {
        Event.b();
        MatrixStack var9 = var1.a(new Object[0]);
        Arm var10 = var1.a_class_1306(new Object[0]);
        float var11 = var1.a_float_____(new Object[0]);
        int var12 = var10 == Arm.RIGHT ? 1 : -1;
        switch (this.a.getValue()) {
            case 1:
                Object[] var20 = new Object[]{null, null, null, 64557177915653L};
                var20[2] = 0.0F;
                var20[1] = var10;
                var20[0] = var9;
                this.j(var20);
                float var17 = MathHelper.sin(var11 * var11 * (float) Math.PI);
                var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * (45.0F + var17 * -20.0F)));
                float var18 = MathHelper.sin(MathHelper.sqrt(var11) * (float) Math.PI);
                var9.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(var12 * var18 * -10.0F));
                var9.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var18 * -80.0F));
                var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * -45.0F));
                break;
            case 2:
                Object[] var19 = new Object[]{null, null, null, 64557177915653L};
                var19[2] = 0.0F;
                var19[1] = var10;
                var19[0] = var9;
                this.j(var19);
                var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * 45.0F));
                float var16 = MathHelper.sin(MathHelper.sqrt(var11) * (float) Math.PI);
                var9.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(var12 * var16 * -10.0F));
                var9.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var16 * -40.0F));
                var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * -45.0F));
                break;
            case 3:
                Object[] var10006 = new Object[]{null, null, null, 64557177915653L};
                var10006[2] = 0.0F;
                var10006[1] = var10;
                var10006[0] = var9;
                this.j(var10006);
                if (var10 == Arm.RIGHT && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                    var9.translate(0.0F, 0.1F, -0.2F);
                    var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * 65.0F));
                    var9.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var12 * -100.0F));
                    var9.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(var12 * 20.0F));
                    var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * -220.0F));
                    float var15 = MathHelper.sin(MathHelper.sqrt(var11) * (float) Math.PI);
                    var9.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(var12 * var15 * -40.0F));
                    var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * -45.0F));
                } else {
                    float var13 = MathHelper.sin(var11 * var11 * (float) Math.PI);
                    var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * (45.0F + var13 * -20.0F)));
                    float var14 = MathHelper.sin(MathHelper.sqrt(var11) * (float) Math.PI);
                    var9.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(var12 * var14 * -20.0F));
                    var9.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var14 * -80.0F));
                    var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var12 * -45.0F));
                }
        }

        var1.a_void_____(new Object[0]);
    }

    private void j(Object[] var1) {
        MatrixStack var2 = (MatrixStack)var1[0];
        Arm var3 = (Arm)var1[1];
        float var4 = (Float)var1[2];
        int var7 = var3 == Arm.RIGHT ? 1 : -1;
        var2.translate(var7 * 0.56F, -0.52F + var4 * -0.6F, -0.72F);
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
