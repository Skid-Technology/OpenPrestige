package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper12;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import org.joml.Matrix4f;

public class CooldownIndicatorElement extends HudElement {
    private static final float m = 28.0F;
    private static final float a = 3.0F;
    private static final float c = 0.5F;
    private static final float d = 220.0F;
    private static final float g = 350.0F;
    private static final float h = 16.0F;
    private static final float i = 280.0F;
    private AttackIndicator j;
    private boolean n;
    private long k;
    private long l;
    private boolean q;
    private float o;
    private float p;
    private float t;
    private float u;
    private long r;

    public CooldownIndicatorElement(long var1) {
        super("Cooldown Indicator", "Themed cooldown bar - covers attack, projectiles, and bow/crossbow/trident charge", (15796535010447L));
        var1 = 0L;
        var1 = 9974884422292L ^ var1;
        long var3 = var1 ^ 8038562592283L;
        this.j = null;
        this.n = false;
        this.k = 0L;
        this.l = 0L;
        this.q = false;
        this.o = 0.0F;
        this.p = 0.0F;
        this.t = 0.0F;
        this.u = 0.0F;
        this.r = -100000L;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 111341298698524L;
        long var6 = var2 ^ 115520954206759L;
        if (b.getWindow() != null) {
            float var9 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var4});
            float var10 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var4});
            float var10001 = var9 / 2.0F - 14.0F;
            float var10002 = var10 / 2.0F + 14.0F;
            Object[] var10005 = new Object[]{null, null, var6};
            var10005[1] = var10002;
            var10005[0] = var10001;
            this.a_void_______(var10005);
        } else {
            Object[] var11 = new Object[]{null, null, var6};
            var11[1] = 110.0F;
            var11[0] = 150.0F;
            this.a_void_______(var11);
        }
    }

    @Override
    public void j(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 7095566904711L;
        long var6 = var2 ^ 5259218245497L;
        long var8 = var2 ^ 0L;
        long var10 = var2 ^ 55198602109446L;
        Event.b();
        super.j(new Object[]{var8});
        if (this.e_boolean(new Object[]{var10})) {
            this.r(new Object[]{var6});
        } else {
            this.s(new Object[]{var4});
        }
    }

    private void r(Object[] var1) {
        if (b.options != null && !this.n) {
            try {
                this.j = b.options.getAttackIndicator().getValue();
                b.options.getAttackIndicator().setValue(AttackIndicator.OFF);
                this.n = true;
            } catch (Throwable var2) {
            }
        }
    }

    private void s(Object[] var1) {
        if (b.options != null && this.n && this.j != null) {
            try {
                b.options.getAttackIndicator().setValue(this.j);
            } catch (Throwable var2) {
            }

            this.n = false;
            this.j = null;
        }
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var5 = (RenderHelper8)var1[0];
        MatrixCameraData var6 = (MatrixCameraData)var1[1];
        Matrix4f var2 = (Matrix4f)var1[2];
        long var3 = (Long)var1[3];
        long var7 = var3 ^ 48964852101235L;
        long var9 = var3 ^ 36129254667080L;
        long var11 = var3 ^ 85560644009094L;
        long var13 = var3 ^ 3632026424044L;
        long var15 = var3 ^ 5982008433205L;
        long var17 = var3 ^ 85350127870851L;
        long var19 = var3 ^ 53304382926758L;
        if (!this.n) {
            this.r(new Object[]{var11});
        }

        if (b.getWindow() != null) {
            float var22 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var7});
            float var23 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var7});
            float var10001 = var22 / 2.0F - 14.0F;
            float var10002 = var23 / 2.0F + 14.0F;
            Object[] var10005 = new Object[]{null, null, var9};
            var10005[1] = var10002;
            var10005[0] = var10001;
            this.a_void_______(var10005);
        } else {
            Object[] var44 = new Object[]{null, null, var9};
            var44[1] = 110.0F;
            var44[0] = 150.0F;
            this.a_void_______(var44);
        }

        Object[] var10004 = new Object[]{null, 3.0F};
        var10004[0] = 28.0F;
        this.i(var10004);
        boolean var31 = b.currentScreen instanceof HudEditorScreen;
        if (b.player != null && b.world != null) {
            long var32 = System.currentTimeMillis();
            float var25 = this.k == 0L ? 0.0F : Math.min(0.1F, (float)(var32 - this.k) / 1000.0F);
            this.k = var32;
            RenderHelper12 var26 = this.a_RenderHelper12(new Object[]{var15});
            boolean var27 = var26 != null && var26.a < 0.999F;
            if (var26 != null) {
                float var28 = 1.0F - (float)Math.exp(-var25 * 16.0F);
                this.t = this.t + (var26.a - this.t) * var28;
            }

            if (var27 != this.q) {
                this.o = this.p;
                this.l = var32;
                if (!var27 && this.u > 0.8F) {
                    this.r = var32;
                }

                this.q = var27;
            }

            if (var26 != null) {
                this.u = var26.a;
            }

            long var33 = var32 - this.l;
            if (var27) {
                float var30 = Math.min(1.0F, (float)var33 / 220.0F);
                float var36 = this.o;
                float var40 = 1.0F - this.o;
                Object[] var10006 = new Object[]{null, var19};
                var10006[0] = var30;
                this.p = var36 + var40 * d(var10006);
            } else {
                float var34 = Math.min(1.0F, (float)var33 / 350.0F);
                float var37 = this.o;
                Object[] var47 = new Object[]{null, var19};
                var47[0] = var34;
                this.p = var37 * (1.0F - d(var47));
            }

            float var38 = this.p;
            var10004 = new Object[]{null, var13};
            var10004[0] = var38;
            this.p = e(var10004);
            if (var31) {
                float var46 = Math.max(this.t, 0.5F);
                Object[] var49 = new Object[]{null, null, null, null, null, null, var17};
                var49[5] = 0.0F;
                var49[4] = var46;
                var49[3] = 1.0F;
                var49[2] = var2;
                var49[1] = var6;
                var49[0] = var5;
                this.t(var49);
            } else {
                var38 = (float)(var32 - this.r) / 280.0F;
                var10004 = new Object[]{null, var13};
                var10004[0] = var38;
                float var10000 = 1.0F - e(var10004);
                Object[] var10003 = new Object[]{null, var19};
                var10003[0] = var10000;
                float var35 = d(var10003);
                if (this.p > 0.01F) {
                    float var43 = this.p;
                    float var45 = this.t;
                    Object[] var48 = new Object[]{null, null, null, null, null, null, var17};
                    var48[5] = var35;
                    var48[4] = var45;
                    var48[3] = var43;
                    var48[2] = var2;
                    var48[1] = var6;
                    var48[0] = var5;
                    this.t(var48);
                }
            }
        } else {
            if (var31) {
                Object[] var10009 = new Object[]{null, null, null, null, null, null, var17};
                var10009[5] = 0.0F;
                var10009[4] = 0.5F;
                var10009[3] = 1.0F;
                var10009[2] = var2;
                var10009[1] = var6;
                var10009[0] = var5;
                this.t(var10009);
            }
        }
    }

    private void t(Object[] var1) {
        RenderHelper8 var7 = (RenderHelper8)var1[0];
        MatrixCameraData var2 = (MatrixCameraData)var1[1];
        Matrix4f var9 = (Matrix4f)var1[2];
        float var3 = (Float)var1[3];
        float var8 = (Float)var1[4];
        float var4 = (Float)var1[5];
        long var5 = (Long)var1[6];
        var5 = 9974884422292L ^ var5;
        long var10 = var5 ^ 66812563981752L;
        long var12 = var5 ^ 19820963104771L;
        long var14 = var5 ^ 40270976538512L;
        long var16 = var5 ^ 30523530588809L;
        long var18 = var5 ^ 19431057585679L;
        Event.b();
        float var21 = 1.5F;
        float var22 = 28.0F * var3;
        if (!(var22 < 0.5F)) {
            float var23 = this.e + (28.0F - var22) * 0.5F;
            Color var10000 = NameTagsUtil.r;
            Object[] var10004 = new Object[]{null, null, var16};
            var10004[1] = var3;
            var10004[0] = var10000;
            Color var24 = c_Color(var10004);
            if (var3 > 0.7F) {
                float var38 = this.f;
                Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var12};
                var10011[8] = var21;
                var10011[7] = 5;
                var10011[6] = 3.0F;
                var10011[5] = var22;
                var10011[4] = var38;
                var10011[3] = var23;
                var10011[2] = var9;
                var10011[1] = var2;
                var10011[0] = var7;
                RenderTarget.e(var10011);
            }

            float var39 = this.f;
            Object[] var45 = new Object[]{null, null, null, null, null, null, null, null, null, var10};
            var45[8] = var21;
            var45[7] = var24;
            var45[6] = 3.0F;
            var45[5] = var22;
            var45[4] = var39;
            var45[3] = var23;
            var45[2] = var9;
            var45[1] = var2;
            var45[0] = var7;
            RoundedPosColorShader.c(var45);
            float var25 = var22 - 1.0F;
            float var26 = 2.0F;
            float var27 = var26 * 0.5F;
            if (var8 > 0.001F && var25 > 1.0F) {
                float var28 = Math.min(1.0F, var8);
                Object[] var10003 = new Object[]{null, var14};
                var10003[0] = var28;
                Color var29 = a(var10003);
                float var30 = var25 * var28;
                if (var30 > var26) {
                    float var36 = var23 + 0.5F;
                    float var40 = this.f + 0.5F;
                    var45 = new Object[]{null, null, var16};
                    var45[1] = var3;
                    var45[0] = var29;
                    Color var10007 = c_Color(var45);
                    var45 = new Object[]{null, null, null, null, null, null, null, null, null, var10};
                    var45[8] = var27;
                    var45[7] = var10007;
                    var45[6] = var26;
                    var45[5] = var30;
                    var45[4] = var40;
                    var45[3] = var36;
                    var45[2] = var9;
                    var45[1] = var2;
                    var45[0] = var7;
                    RoundedPosColorShader.c(var45);
                    if (var28 < 0.995F && var30 > 3.0F) {
                        Color var10001 = Color.WHITE;
                        Object[] var10005 = new Object[]{null, null, null, var18};
                        var10005[2] = 0.75F;
                        var10005[1] = var10001;
                        var10005[0] = var29;
                        var10000 = b(var10005);
                        float var35 = var3 * 0.9F;
                        var10004 = new Object[]{null, null, var16};
                        var10004[1] = var35;
                        var10004[0] = var10000;
                        Color var31 = c_Color(var10004);
                        float var37 = var23 + 0.5F + var30 - 1.5F;
                        float var42 = this.f + 0.5F;
                        var45 = new Object[]{null, null, null, null, null, null, null, null, null, var10};
                        var45[8] = var27;
                        var45[7] = var31;
                        var45[6] = var26;
                        var45[5] = 1.5F;
                        var45[4] = var42;
                        var45[3] = var37;
                        var45[2] = var9;
                        var45[1] = var2;
                        var45[0] = var7;
                        RoundedPosColorShader.c(var45);
                    }
                }
            }

            if (var4 > 0.01F) {
                int var33 = Math.min(255, (int)(170.0F * var4 * var3));
                if (var33 > 2) {
                    float var43 = this.f;
                    Color var44 = new Color(255, 255, 255, var33);
                    var45 = new Object[]{null, null, null, null, null, null, null, null, null, var10};
                    var45[8] = var21;
                    var45[7] = var44;
                    var45[6] = 3.0F;
                    var45[5] = var22;
                    var45[4] = var43;
                    var45[3] = var23;
                    var45[2] = var9;
                    var45[1] = var2;
                    var45[0] = var7;
                    RoundedPosColorShader.c(var45);
                }
            }
        }
    }

    private static Color a(Object[] var0) {
        float var1 = (Float)var0[0];
        long var2 = (Long)var0[1];
        var2 = 9974884422292L ^ var2;
        long var4 = var2 ^ 123463530473555L;
        long var6 = var2 ^ 116882517059327L;
        long var8 = var2 ^ 98383029263285L;
        long var10 = var2 ^ 66099571454731L;
        Color var12 = MathHelper4.d(new Object[]{var4});
        float var10002 = var1 / 0.75F;
        Object[] var10005 = new Object[]{null, var6};
        var10005[0] = var10002;
        var10002 = e(var10005);
        var10005 = new Object[]{null, var8};
        var10005[0] = var10002;
        float var13 = 0.55F + 0.45F * d(var10005);
        Color var14 = new Color((int)(var12.getRed() * var13), (int)(var12.getGreen() * var13), (int)(var12.getBlue() * var13));
        float var10000 = (var1 - 0.88F) / 0.12F;
        Object[] var10003 = new Object[]{null, var6};
        var10003[0] = var10000;
        var10000 = e(var10003);
        var10003 = new Object[]{null, var8};
        var10003[0] = var10000;
        float var15 = d(var10003);
        Color var10001 = Color.WHITE;
        var10002 = var15 * 0.65F;
        var10005 = new Object[]{null, null, null, var10};
        var10005[2] = var10002;
        var10005[1] = var10001;
        var10005[0] = var14;
        return b(var10005);
    }

    private static Color b(Object[] var0) {
        Color var5 = (Color)var0[0];
        Color var2 = (Color)var0[1];
        float var1 = (Float)var0[2];
        long var3 = (Long)var0[3];
        var3 = 9974884422292L ^ var3;
        long var6 = var3 ^ 104735476565856L;
        Object[] var10003 = new Object[]{null, var6};
        var10003[0] = var1;
        var1 = e(var10003);
        return new Color(
            (int)(var5.getRed() + (var2.getRed() - var5.getRed()) * var1),
            (int)(var5.getGreen() + (var2.getGreen() - var5.getGreen()) * var1),
            (int)(var5.getBlue() + (var2.getBlue() - var5.getBlue()) * var1)
        );
    }

    private RenderHelper12 a_RenderHelper12(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 9974884422292L ^ var2;
        long var4 = var2 ^ 104144041804736L;
        long var6 = var2 ^ 134209061631700L;
        long var8 = var2 ^ 128150156218829L;
        Event.b();
        float var11 = MatrixStack.a_float_______________(new Object[]{var6});
        if (b.player.isUsingItem()) {
            ItemStack var12 = b.player.getActiveItem();
            if (var12 != null && !var12.isEmpty()) {
                if (var12.getItem() instanceof BowItem) {
                    RenderHelper12 var21 = new RenderHelper12();
                    var21.a = Math.min(1.0F, (b.player.getItemUseTime() + var11) / 20.0F);
                    return var21;
                }

                if (var12.getItem() instanceof CrossbowItem) {
                    RenderHelper12 var20 = new RenderHelper12();
                    int var22 = Math.max(1, CrossbowItem.getPullTime(var12, b.player));
                    var20.a = Math.min(1.0F, (b.player.getItemUseTime() + var11) / var22);
                    return var20;
                }

                if (var12.getItem() instanceof TridentItem) {
                    RenderHelper12 var19 = new RenderHelper12();
                    var19.a = Math.min(1.0F, (b.player.getItemUseTime() + var11) / 10.0F);
                    return var19;
                }
            }
        }

        ItemStack var10001 = b.player.getMainHandStack();
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = var11;
        var10005[0] = var10001;
        float var18 = this.c(var10005);
        var10001 = b.player.getOffHandStack();
        var10005 = new Object[]{null, null, var4};
        var10005[1] = var11;
        var10005[0] = var10001;
        float var13 = this.c(var10005);
        float var14 = Math.max(var18, var13);
        if (var14 > 0.001F) {
            RenderHelper12 var23 = new RenderHelper12();
            var23.a = 1.0F - var14;
            return var23;
        } else {
            if (this.f(new Object[]{b.player.getMainHandStack(), var8})) {
                float var15 = b.player.getAttackCooldownProgress(var11);
                if (var15 < 1.0F) {
                    RenderHelper12 var16 = new RenderHelper12();
                    var16.a = var15;
                    return var16;
                }
            }

            return null;
        }
    }

    private boolean f(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        long var3 = (Long)var1[1];
        var3 = 9974884422292L ^ var3;
        long var5 = var3 ^ 126705442536369L;
        long var7 = var3 ^ 106775891638243L;
        long var9 = var3 ^ 138975080893106L;
        return var2 != null && !var2.isEmpty()
            ? InventoryUtil.a(new Object[]{var2, var5})
                || InventoryUtil.b(new Object[]{var2, var7})
                || InventoryUtil.o(new Object[]{var2, var9})
                || var2.getItem() instanceof TridentItem
            : false;
    }

    private float c(Object[] var1) {
        ItemStack var5 = (ItemStack)var1[0];
        float var2 = (Float)var1[1];
        if (var5 != null && !var5.isEmpty()) {
            try {
                return b.player.getItemCooldownManager().getCooldownProgress(var5, var2);
            } catch (Throwable var6) {
                return 0.0F;
            }
        } else {
            return 0.0F;
        }
    }

    private static float d(Object[] var0) {
        float var3 = (Float)var0[0];
        if (var3 <= 0.0F) {
            return 0.0F;
        } else {
            return var3 >= 1.0F ? 1.0F : var3 * var3 * var3 * (var3 * (var3 * 6.0F - 15.0F) + 10.0F);
        }
    }

    private static float e(Object[] var0) {
        float var3 = (Float)var0[0];
        return var3 < 0.0F ? 0.0F : (var3 > 1.0F ? 1.0F : var3);
    }

    private static Color c_Color(Object[] var0) {
        Color var4 = (Color)var0[0];
        float var3 = (Float)var0[1];
        int var5 = Math.max(0, Math.min(255, (int)(var4.getAlpha() * var3)));
        return new Color(var4.getRed(), var4.getGreen(), var4.getBlue(), var5);
    }

    private static Field e(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field f(Class var0, String var1, Class var2) {
        Field var3 = e(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = f(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method e(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method f(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = e(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = f(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
