package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper5;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.type.Easing;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class TargetElement extends HudElement {
    private final RenderHelper5 d;
    private FontRenderer g;
    private Identifier h;
    private String i;
    private float k;
    private float l;
    private float o;
    private float p;
    private float t;
    private float u;
    private float j;
    private UUID m;
    private long n;
    private Iterable q;
    private ItemStack r;
    private ItemStack s;
    private long v;
    private long w;

    public TargetElement(long var1) {
        super("Target", "Displays information about your current target", (88190775886332L));
        var1 = 0L;
        var1 = 33354452652801L ^ var1;
        long var3 = var1 ^ 14856808117960L;
        long var5 = var1 ^ 86176162894589L;
        this.d = new RenderHelper5(500.0F, false, Easing.BACK_IN_OUT, var3);
        this.i = "";
        this.k = 0.0F;
        this.l = 0.0F;
        this.o = 0.0F;
        this.p = 0.0F;
        this.t = 0.0F;
        this.u = 0.0F;
        this.j = 0.0F;
        this.n = 0L;
        this.r = ItemStack.EMPTY;
        this.s = ItemStack.EMPTY;
        this.v = 0L;
        this.w = 0L;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 111341298698524L;
        long var6 = var2 ^ 115520954206759L;
        float var8 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var4});
        float var9 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var4});
        float var10001 = var8 / 2.0F - 65.0F;
        float var10002 = var9 / 2.0F + 80.0F;
        Object[] var10005 = new Object[]{null, null, var6};
        var10005[1] = var10002;
        var10005[0] = var10001;
        this.a_void_______(var10005);
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var2 = (RenderHelper8)var1[0];
        MatrixCameraData var4 = (MatrixCameraData)var1[1];
        Matrix4f var3 = (Matrix4f)var1[2];
        long var5 = (Long)var1[3];
        long var7 = var5 ^ 13977059902564L;
        long var9 = var5 ^ 62047849716439L;
        long var11 = var5 ^ 121708693431506L;
        long var13 = var5 ^ 117880139760215L;
        long var15 = var5 ^ 54030867045760L;
        long var17 = var5 ^ 91799118475102L;
        long var19 = var5 ^ 93148276620004L;
        long var21 = var5 ^ 27786335669325L;
        long var23 = var5 ^ 102693909475685L;
        long var25 = var5 ^ 67219672616543L;
        long var27 = var5 ^ 96836794377851L;
        long var29 = var5 ^ 131934975778103L;
        long var31 = var5 ^ 6014253680539L;
        long var33 = var5 ^ 51003133856837L;
        long var35 = var5 ^ 64450247846539L;
        long var37 = var5 ^ 64771341888446L;
        long var39 = var5 ^ 96557814079126L;
        long var41 = var5 ^ 101037798966850L;
        long var43 = var5 ^ 132551805609406L;
        long var45 = var5 ^ 26251524285181L;
        Event.b();
        this.w = System.currentTimeMillis() - this.v;
        this.v = System.currentTimeMillis();
        PlayerEntity var48 = b.currentScreen instanceof HudEditorScreen ? b.player : Prestige.n.a(new Object[0]);
        if (var48 != null && (var48.getUuid() == null || b.getNetworkHandler().getPlayerListEntry(var48.getUuid()) == null)) {
            var48 = null;
        }

        RenderHelper5 var10000 = this.d;
        boolean var10001 = !(b.currentScreen instanceof HudEditorScreen) && var48 != null;
        Object[] var10004 = new Object[]{null, var7};
        var10004[0] = var10001;
        var10000.a_void_______________________(var10004);
        float var49 = 130.0F;
        float var50 = 40.0F;
        float var51 = b.currentScreen instanceof HudEditorScreen ? 1.0F : this.d.a_float___________________(new Object[]{var9});
        if (var51 > 0.01F) {
            Matrix4f var52 = new Matrix4f();
            var52.translate(this.e + var49 / 2.0F, this.f + var50 / 2.0F, 0.0F);
            var52.scale(var51, var51, var51);
            var52.translate(-this.e - var49 / 2.0F, -this.f - var50 / 2.0F, 0.0F);
            var52 = var3.mul(var52, new Matrix4f());
            float var10003 = this.e;
            float var118 = this.f;
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, new Vector4f(4.0F), var25};
            var10011[7] = 5;
            var10011[6] = var50;
            var10011[5] = var49;
            var10011[4] = var118;
            var10011[3] = var10003;
            var10011[2] = var52;
            var10011[1] = var4;
            var10011[0] = var2;
            RenderTarget.d(var10011);
            float var83 = this.e;
            float var10002 = this.f;
            var10003 = this.e + var49;
            float var119 = this.f + var50;
            Object[] var10009 = new Object[]{null, null, null, null, null, NameTagsUtil.r, new Vector4f(4.0F), var13};
            var10009[4] = var119;
            var10009[3] = var10003;
            var10009[2] = var10002;
            var10009[1] = var83;
            var10009[0] = var52;
            RenderState.u(var10009);
            if (var48 != null) {
                Identifier var53 = b.getNetworkHandler().getPlayerListEntry(var48.getUuid()).getSkinTextures().body().texturePath();
                if (!var53.equals(this.h)) {
                    this.h = var53;
                    this.g = FontRenderer.d_FontRenderer(new Object[]{var53, var43});
                }

                this.i = var48.getName().getString();
                float var54 = var48.getHealth() + var48.getAbsorptionAmount();
                this.k = (float)Math.ceil(var54);
                this.o = this.c(new Object[]{var48, var45});
                this.j = b.player != null ? var48.distanceTo(b.player) : 0.0F;
                if (this.m == null || !this.m.equals(var48.getUuid())) {
                    this.m = var48.getUuid();
                    this.l = this.k;
                    this.p = this.o;
                    this.t = this.o;
                    this.n = 0L;
                } else if (var54 < this.u - 0.05F) {
                    this.n = System.currentTimeMillis();
                }

                this.u = var54;
                ArrayList var55 = new ArrayList();

                for (EquipmentSlot var57 : AttributeModifierSlot.ARMOR) {
                    if (!var57.getName().equals("body")) {
                        var55.add(var48.getEquippedStack(var57));
                    }
                }

                Collections.reverse(var55);
                this.q = var55;
                this.r = var48.getMainHandStack();
                this.s = var48.getOffHandStack();
            }

            long var68 = System.currentTimeMillis() - this.n;
            if (this.n != 0L && var68 < 350L) {
                float var69 = 1.0F - (float)var68 / 350.0F;
                float var74 = 55.0F * var69;
                Object[] var10006 = new Object[]{null, null, null, var19};
                var10006[2] = 1.0F;
                var10006[1] = 0.0F;
                var10006[0] = var51;
                int var71 = (int)(var74 * MathHelper4.a(var10006));
                float var84 = this.e;
                var10002 = this.f;
                var10003 = this.e + var49;
                float var120 = this.f + var50;
                var10009 = new Object[]{null, null, null, null, null, new Color(235, 70, 70, var71), new Vector4f(4.0F), var13};
                var10009[4] = var120;
                var10009[3] = var10003;
                var10009[2] = var10002;
                var10009[1] = var84;
                var10009[0] = var52;
                RenderState.u(var10009);
            }

            if (this.g != null) {
                float var75 = this.e + 5.0F;
                float var85 = this.f + 5.0F;
                FontRenderer var10010 = this.g;
                Color var143 = Color.WHITE;
                Object[] var10017 = new Object[]{null, null, null, var19};
                var10017[2] = 1.0F;
                var10017[1] = 0.0F;
                var10017[0] = var51;
                float var10012 = MathHelper4.a(var10017);
                Object[] var10015 = new Object[]{null, null, var37};
                var10015[1] = var10012;
                var10015[0] = var143;
                Object[] var10014 = new Object[]{
                    null, null, null, null, null, null, null, null, null, var52, var10010, RenderState.a_Color________(var10015), var21
                };
                var10014[8] = 0.0F;
                var10014[7] = 3.75F;
                var10014[6] = 3.75F;
                var10014[5] = 22.5F;
                var10014[4] = 3.75F;
                var10014[3] = 30.0F;
                var10014[2] = 30.0F;
                var10014[1] = var85;
                var10014[0] = var75;
                MatrixStack.x(var10014);
                float var86 = this.e + 5.0F;
                var10002 = this.f + 5.0F;
                var10003 = this.e + 35.0F;
                float var121 = this.f + 35.0F;
                Color var10005 = new Color(14, 14, 14, 140);
                var10009 = new Object[]{null, null, null, null, null, null, null, var11};
                var10009[6] = 0.0F;
                var10009[5] = var10005;
                var10009[4] = var121;
                var10009[3] = var10003;
                var10009[2] = var10002;
                var10009[1] = var86;
                var10009[0] = var52;
                MatrixStack.l(var10009);
            }

            Font var76 = Prestige.c.a(new Object[]{var29});
            String var96 = this.i;
            var10003 = this.e + 40.0F;
            float var122 = this.f + 3.0F;
            Object[] var10008 = new Object[]{null, null, null, null, NameTagsUtil.s, var39};
            var10008[3] = var122;
            var10008[2] = var10003;
            var10008[1] = var96;
            var10008[0] = var52;
            var76.v(var10008);
            float var77 = this.j;
            var10004 = new Object[]{null, null, var27};
            var10004[1] = 1;
            var10004[0] = var77;
            String var70 = String.valueOf(MathHelper4.c(var10004)).replace(".0", "") + "m";
            float var72 = Prestige.c.a(new Object[]{var29}).a_float______________(new Object[]{var70, var23}) * 0.75F;
            float var73 = Prestige.c.a(new Object[]{var29}).c_float___(new Object[]{var15});
            Font var78 = Prestige.c.a(new Object[]{var29});
            var10003 = this.e + var49 - 5.5F - var72;
            float var124 = this.f + 3.0F + (var73 - var73 * 0.75F) * 0.5F;
            var10009 = new Object[]{null, null, null, null, null, NameTagsUtil.u, var41};
            var10009[4] = 0.75F;
            var10009[3] = var124;
            var10009[2] = var10003;
            var10009[1] = var70;
            var10009[0] = var52;
            var78.w(var10009);
            float var87 = this.p;
            var10002 = this.o;
            var10003 = (float)this.w * 0.008F;
            Object[] var132 = new Object[]{null, null, null, var31};
            var132[2] = var10003;
            var132[1] = var10002;
            var132[0] = var87;
            this.p = MathHelper4.b_float______(var132);
            float var88 = this.l;
            var10002 = this.k;
            var10003 = (float)this.w * 0.01F;
            var132 = new Object[]{null, null, null, var31};
            var132[2] = var10003;
            var132[1] = var10002;
            var132[0] = var88;
            this.l = MathHelper4.b_float______(var132);
            if (this.o >= this.t) {
                this.t = this.o;
            } else if (System.currentTimeMillis() - this.n > 400L) {
                float var89 = this.t;
                var10002 = this.o;
                var10003 = (float)this.w * 0.003F;
                var132 = new Object[]{null, null, null, var31};
                var132[2] = var10003;
                var132[1] = var10002;
                var132[0] = var89;
                this.t = MathHelper4.b_float______(var132);
            }

            float var58 = this.e + 40.0F;
            float var59 = 65.0F;
            var10002 = this.f + 28.0F;
            var10003 = var58 + var59;
            float var125 = this.f + 35.0F;
            var10009 = new Object[]{null, null, null, null, null, new Color(255, 255, 255, 22), new Vector4f(1.5F), var13};
            var10009[4] = var125;
            var10009[3] = var10003;
            var10009[2] = var10002;
            var10009[1] = var58;
            var10009[0] = var52;
            RenderState.u(var10009);
            if (this.t > this.p + 0.005F) {
                var10002 = this.f + 28.0F;
                var10003 = var58 + var59 * this.t;
                float var126 = this.f + 35.0F;
                var10009 = new Object[]{null, null, null, null, null, new Color(235, 85, 85, 130), new Vector4f(1.5F), var13};
                var10009[4] = var126;
                var10009[3] = var10003;
                var10009[2] = var10002;
                var10009[1] = var58;
                var10009[0] = var52;
                RenderState.u(var10009);
            }

            float var90 = this.p;
            var10004 = new Object[]{null, var33};
            var10004[0] = var90;
            Color var60 = this.a(var10004);
            float var61 = Math.max(var59 * this.p, 2.5F);
            var10002 = this.f + 28.0F;
            var10003 = var58 + var61;
            float var128 = this.f + 35.0F;
            Object[] var144 = new Object[]{null, null, null, null, null, null, var60.darker(), var60.brighter(), var60.darker(), var60.brighter(), var17};
            var144[5] = 1.5F;
            var144[4] = var128;
            var144[3] = var10003;
            var144[2] = var10002;
            var144[1] = var58;
            var144[0] = var52;
            MatrixStack.p(var144);
            float var79 = this.l;
            var10004 = new Object[]{null, null, var27};
            var10004[1] = 1;
            var10004[0] = var79;
            String var62 = String.valueOf(MathHelper4.c(var10004)).replace(".0", "");
            Color var63 = this.p > 0.6F ? NameTagsUtil.s : var60;
            Font var80 = Prestige.c.a(new Object[]{var29});
            var10003 = this.e + var49 - Prestige.c.a(new Object[]{var29}).a_float______________(new Object[]{var62, var23}) - 5.5F;
            float var130 = this.f + 26.0F;
            var10008 = new Object[]{null, null, null, null, var63, var39};
            var10008[3] = var130;
            var10008[2] = var10003;
            var10008[1] = var62;
            var10008[0] = var52;
            var80.v(var10008);
            if (this.q != null) {
                float var64 = -2.5F;

                for (ItemStack var66 : (Iterable<ItemStack>)(this.q)) {
                    if (!var66.isEmpty()) {
                        float var91 = this.e + 40.5F + var64;
                        var10002 = this.f + 13.0F;
                        Object[] var10007 = new Object[]{null, null, null, null, var52, var35};
                        var10007[3] = 0.8F;
                        var10007[2] = var10002;
                        var10007[1] = var91;
                        var10007[0] = var66;
                        MatrixStack.a_boolean_______________________________________(var10007);
                    }

                    var64 += 15.0F;
                }

                if (!this.r.isEmpty()) {
                    ItemStack var81 = this.r;
                    float var92 = this.e + 40.5F + var64;
                    var10002 = this.f + 13.0F;
                    Object[] var135 = new Object[]{null, null, null, null, var52, var35};
                    var135[3] = 0.8F;
                    var135[2] = var10002;
                    var135[1] = var92;
                    var135[0] = var81;
                    if (MatrixStack.a_boolean_______________________________________(var135)) {
                        var64 += 15.0F;
                    }
                }

                if (!this.s.isEmpty()) {
                    ItemStack var82 = this.s;
                    float var93 = this.e + 40.5F + var64;
                    var10002 = this.f + 13.0F;
                    Object[] var136 = new Object[]{null, null, null, null, var52, var35};
                    var136[3] = 0.8F;
                    var136[2] = var10002;
                    var136[1] = var93;
                    var136[0] = var82;
                    MatrixStack.a_boolean_______________________________________(var136);
                }
            }
        }

        var10004 = new Object[]{null, var50};
        var10004[0] = var49;
        this.i(var10004);
    }

    private float c(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 33354452652801L ^ var2;
        long var5 = var2 ^ 102263665888024L;
        float var10000 = (var4.getHealth() + var4.getAbsorptionAmount()) / 20.0F;
        Object[] var10005 = new Object[]{null, null, null, var5};
        var10005[2] = 1.0F;
        var10005[1] = 0.0F;
        var10005[0] = var10000;
        return MathHelper4.a(var10005);
    }

    private Color a(Object[] var1) {
        float var4 = (Float)var1[0];
        long var2 = (Long)var1[1];
        var2 = 33354452652801L ^ var2;
        long var5 = var2 ^ 45614722040580L;
        if (var4 > 0.6F) {
            return MathHelper4.d(new Object[]{var5});
        } else {
            return var4 > 0.3F ? new Color(240, 170, 60) : new Color(235, 75, 75);
        }
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
}
