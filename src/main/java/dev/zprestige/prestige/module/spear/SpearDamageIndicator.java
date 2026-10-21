package dev.zprestige.prestige.module.spear;

import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper5;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import dev.zprestige.prestige.type.Easing;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.component.type.KineticWeaponComponent;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class SpearDamageIndicator extends Module {
    public SpearDamageIndicator() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private static final float a = 20.0F;
    private static final float c = 4.0F;
    private RenderHelper5 d = new RenderHelper5(500.0F, false, Easing.BACK_IN_OUT, 88790582046503L);
    private float e = 0.0F;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Spear);
    }

    @Subscribe
    public void a(TickEvent25 var1) {
        boolean var29 = InventoryUtil.l(new Object[]{Items.NETHERITE_SPEAR, 65539821781598L}) && b.player.isUsingItem();
        PlayerEntity var30 = var29 ? CombatUtil.a_class_1657(new Object[]{16945855905304L}) : null;
        RenderHelper5 var10000 = this.d;
        boolean var10001 = var30 != null;
        Object[] var10004 = new Object[]{null, 134514778598418L};
        var10004[0] = var10001;
        var10000.a_void_______________________(var10004);
        float var31 = this.d.a_float___________________(new Object[]{86379377865377L});
        if (!(var31 <= 0.01F)) {
            Vec3d var32 = b.player.getRotationVector();
            double var33 = var32.dotProduct(KineticWeaponComponent.getAmplifiedMovement(b.player));
            double var35 = b.player.getAttributeBaseValue(EntityAttributes.ATTACK_DAMAGE);
            double var37 = var30 == null ? 0.0 : var32.dotProduct(KineticWeaponComponent.getAmplifiedMovement(var30));
            double var39 = Math.max(0.0, var33 - var37);
            float var41 = (float)var35 + MathHelper.floor(var39 / 2.0);
            float var63 = this.e;
            float var10003 = MatrixStack.a_float_______________(new Object[]{601921721859L}) / 5.0F;
            Object[] var10006 = new Object[]{null, null, null, 127101728255981L};
            var10006[2] = var10003;
            var10006[1] = var41;
            var10006[0] = var63;
            this.e = MathHelper4.b_float______(var10006);
            Matrix4f var42 = new Matrix4f();
            float var43 = 64.0F;
            float var44 = 20.0F;
            float var45 = b.getWindow().getScaledWidth() / 2.0F - var43 / 2.0F;
            float var46 = b.getWindow().getScaledHeight() / 2.0F + 30.0F;
            var42.translate(var45 + var43 / 2.0F, var46 + var44 / 2.0F, 0.0F);
            var42.scale(var31, var31, var31);
            var42.translate(-var45 - var43 / 2.0F, -var46 - var44 / 2.0F, 0.0F);
            RenderHelper8 var58 = var1.a;
            MatrixCameraData var64 = var1.b;
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 35553070502242L};
            var10011[8] = 5.0F;
            var10011[7] = 5;
            var10011[6] = var44;
            var10011[5] = var43;
            var10011[4] = var46;
            var10011[3] = var45;
            var10011[2] = var42;
            var10011[1] = var64;
            var10011[0] = var58;
            RenderTarget.e(var10011);
            RenderHelper8 var59 = var1.a;
            MatrixCameraData var65 = var1.b;
            Color var10007 = new Color(14, 14, 14, 150);
            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
            var10011[8] = 5.0F;
            var10011[7] = var10007;
            var10011[6] = var44;
            var10011[5] = var43;
            var10011[4] = var46;
            var10011[3] = var45;
            var10011[2] = var42;
            var10011[1] = var65;
            var10011[0] = var59;
            RoundedPosColorShader.c(var10011);
            String var47 = this.e >= 20.0F ? "LETHAL" : "Damage: " + (int)this.e + "HP";
            float var48 = Prestige.c.a(new Object[]{1236438218049L}).a_float______________(new Object[]{var47, 47859522476307L});
            float var49 = 7.0F;
            float var50 = MathHelper.clamp(this.e / 20.0F, 0.0F, 1.0F);
            float var51 = var45 + var49;
            float var52 = var46 + var44 - 4.0F - 3.0F;
            float var53 = var43 - var49 * 2.0F;
            float var54 = var53 * var50;
            Color var55 = new Color(0, 0, 0, 90);
            Color var56 = new Color((int)((1.0F - var50) * 255.0F), (int)(var50 * 255.0F), 0, 220);
            RenderHelper8 var60 = var1.a;
            MatrixCameraData var66 = var1.b;
            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
            var10011[8] = 2.0F;
            var10011[7] = var55;
            var10011[6] = 4.0F;
            var10011[5] = var53;
            var10011[4] = var52;
            var10011[3] = var51;
            var10011[2] = var42;
            var10011[1] = var66;
            var10011[0] = var60;
            RoundedPosColorShader.c(var10011);
            if (var54 > 0.0F) {
                RenderHelper8 var61 = var1.a;
                MatrixCameraData var67 = var1.b;
                var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
                var10011[8] = 2.0F;
                var10011[7] = var56;
                var10011[6] = 4.0F;
                var10011[5] = var54;
                var10011[4] = var52;
                var10011[3] = var51;
                var10011[2] = var42;
                var10011[1] = var67;
                var10011[0] = var61;
                RoundedPosColorShader.c(var10011);
            }

            float var57 = var45 + (var43 - var48) / 2.0F;
            Font var62 = Prestige.c.a(new Object[]{1236438218049L});
            float var68 = var46 + 3.0F;
            Object[] var10008 = new Object[]{null, null, null, null, MathHelper4.d(new Object[]{122780339483702L}), 36494305523424L};
            var10008[3] = var68;
            var10008[2] = var57;
            var10008[1] = var47;
            var10008[0] = var42;
            var62.v(var10008);
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
