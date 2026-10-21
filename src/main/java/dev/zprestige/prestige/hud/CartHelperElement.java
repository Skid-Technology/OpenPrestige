package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class CartHelperElement extends HudElement {
    private static final int a = 8;
    private static final float m = 0.6F;
    private static final float c = 9.6F;
    private static final float d = 4.0F;
    private static final float g = 30.0F;
    private static final float h = 3.0F;
    private static final Color i = new Color(80, 220, 120);
    private static final Color j = new Color(235, 64, 64);
    private static final Color k = new Color(255, 255, 255, 30);
    private static final Set l = Set.of(Items.RAIL, Items.POWERED_RAIL, Items.DETECTOR_RAIL, Items.ACTIVATOR_RAIL);
    private static final int n = 0;
    private static final int o = 1;
    private static final int p = 2;
    private static final int q = 3;
    private String r;
    private boolean s;
    private int t;
    private float u;
    private int v;
    private final ItemStack[] w;
    private boolean x;

    public CartHelperElement(long var1) {
        super("Cart Helper", "Cart PvP at a glance — trigger readiness, crossbow charge, and TNT cart stock.", (62465196802841L));
        var1 = 0L;
        var1 = 108227390598432L ^ var1;
        long var3 = var1 ^ 99649297198649L;
        this.r = "Ready";
        this.s = true;
        this.t = 0;
        this.u = 0.0F;
        this.v = 0;
        this.w = new ItemStack[2];
        this.x = false;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 340.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    private void r(Object[] var1) {
        if (!this.x) {
            this.w[0] = new ItemStack(Items.CROSSBOW);
            this.w[0].set(DataComponentTypes.ITEM_MODEL, Identifier.of("minecraft", "crossbow_standby"));
            this.w[1] = new ItemStack(Items.TNT_MINECART);
            this.x = true;
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player != null && b.world != null) {
            boolean var7 = false;
            boolean var8 = false;
            boolean var9 = false;
            boolean var10 = false;
            int var11 = 0;

            for (int var12 = 0; var12 <= 35; var12++) {
                ItemStack var13 = b.player.getInventory().getStack(var12);
                if (var13.getItem() == Items.TNT_MINECART) {
                    var11 += var13.getCount();
                }

                if (var12 <= 8) {
                    if (l.contains(var13.getItem())) {
                        var7 = true;
                    }

                    if (var13.getItem() == Items.BOW) {
                        var8 = true;
                    }

                    if (var13.getItem() == Items.CROSSBOW) {
                        var10 = true;
                        if (CrossbowItem.isCharged(var13)) {
                            var9 = true;
                        }
                    }
                }
            }

            ItemStack var16 = b.player.getOffHandStack();
            if (var16.getItem() == Items.TNT_MINECART) {
                var11 += var16.getCount();
            }

            this.v = var11;
            boolean var17 = var8 || var9;
            this.s = var7 && var11 > 0 && var17;
            this.r = !var7 ? "No Rail" : (var11 == 0 ? "No Carts" : (!var17 ? "No Weapon" : "Ready"));
            if (b.player.isUsingItem() && b.player.getActiveItem().getItem() instanceof CrossbowItem) {
                ItemStack var14 = b.player.getActiveItem();
                int var15 = Math.max(1, CrossbowItem.getPullTime(var14, b.player));
                this.t = 2;
                this.u = Math.min(1.0F, (b.player.getItemUseTime() + MatrixStack.a_float_______________(new Object[]{601921721859L})) / var15);
            } else if (var9) {
                this.t = 3;
                this.u = 1.0F;
            } else {
                this.t = var10 ? 1 : 0;
                this.u = 0.0F;
            }
        }
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var6 = (RenderHelper8)var1[0];
        MatrixCameraData var4 = (MatrixCameraData)var1[1];
        Matrix4f var5 = (Matrix4f)var1[2];
        long var2 = (Long)var1[3];
        long var7 = var2 ^ 95334181894420L;
        long var9 = var2 ^ 27807340928064L;
        long var11 = var2 ^ 45947767099148L;
        long var13 = var2 ^ 54030867045760L;
        long var15 = var2 ^ 45272506949373L;
        long var17 = var2 ^ 132449650111663L;
        long var19 = var2 ^ 89135395443612L;
        long var21 = var2 ^ 102693909475685L;
        long var23 = var2 ^ 131934975778103L;
        long var25 = var2 ^ 81986305771303L;
        long var27 = var2 ^ 64450247846539L;
        long var29 = var2 ^ 96557814079126L;
        long var31 = var2 ^ 101037798966850L;
        Event.b();
        this.r(new Object[]{var25});
        String var34 = "Cart Helper";
        float var35 = Prestige.c.a(new Object[]{var23}).c_float___(new Object[]{var13});
        Object[] var10004 = new Object[]{null, 1.0F};
        var10004[0] = var35;
        float var36 = NameTagsUtil.a(var10004);
        var10004 = new Object[]{null, 0.9F};
        var10004[0] = var35;
        float var37 = NameTagsUtil.a(var10004);
        float var38 = Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var34, var21});
        boolean var39 = b.player == null || b.world == null;
        String var40 = var39 ? "Ready" : this.r;
        boolean var41 = var39 || this.s;
        int var42 = var39 ? 2 : this.t;
        float var43 = var39 ? 0.65F : this.u;
        int var44 = var39 ? 24 : this.v;
        String var45 = var42 == 3 ? "Ready" : (var42 == 1 ? "Uncharged" : "None");
        String var46 = String.valueOf(var44);
        float var47 = 5.5F + var38 + 5.5F;
        float var48 = 12.5F + Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var40, var21}) * 0.9F + 5.5F;
        float var49 = 18.1F + (var42 == 2 ? 30.0F : Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var45, var21}) * 0.9F) + 5.5F;
        float var50 = 18.1F + Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var46, var21}) * 0.9F + 5.5F;
        var47 = Math.max(var47, Math.max(var48, Math.max(var49, var50)));
        float var51 = var36 + 3.0F + var37 * 3.0F + 3.0F;
        boolean var52 = this.c_boolean__(new Object[0]);
        var10004 = new Object[]{null, var51};
        var10004[0] = var47;
        this.i(var10004);
        float var53 = this.e;
        float var54 = this.f;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var7};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var51;
        var10011[5] = var47;
        var10011[4] = var54;
        var10011[3] = var53;
        var10011[2] = var5;
        var10011[1] = var4;
        var10011[0] = var6;
        RenderTarget.e(var10011);
        Color var10007 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var17};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var51;
        var10011[5] = var47;
        var10011[4] = var54;
        var10011[3] = var53;
        var10011[2] = var5;
        var10011[1] = var4;
        var10011[0] = var6;
        RoundedPosColorShader.c(var10011);
        float var55 = var54 + 3.0F;
        float var56 = var52 ? var53 + var47 - 5.5F - var38 : var53 + 5.5F;
        float var57 = var56;

        for (int var58 = 0; var58 < var34.length(); var58++) {
            String var59 = String.valueOf(var34.charAt(var58));
            int var60 = var52 ? var34.length() - 1 - var58 : var58;
            Font var10000 = Prestige.c.a(new Object[]{var23});
            Color var10005 = MathHelper4.d(new Object[]{var9});
            int var109 = 10 + var60;
            Object[] var10010 = new Object[]{null, null, null, var11};
            var10010[2] = var109;
            var10010[1] = 10;
            var10010[0] = var10005;
            Object[] var10008 = new Object[]{null, null, null, null, MatrixStack.b_Color_____(var10010), var29};
            var10008[3] = var55;
            var10008[2] = var57;
            var10008[1] = var59;
            var10008[0] = var5;
            var10000.v(var10008);
            var57 += Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var59, var21});
        }

        float var75 = var54 + var36 - 0.5F;
        float var10001 = var53 + 5.5F;
        float var10003 = var53 + var47 - 5.5F;
        float var106 = var75 + 0.5F;
        Object[] var114 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var19};
        var114[4] = var106;
        var114[3] = var10003;
        var114[2] = var75;
        var114[1] = var10001;
        var114[0] = var5;
        RenderState.l(var114);
        long var76 = System.currentTimeMillis();
        int var61 = (int)Math.min(255.0, 170.0 + 85.0 * Math.sin(var76 * 0.012));
        float var62 = var54 + var36 + 3.0F;
        float var63 = var62 + (var37 - var35 * 0.9F) / 2.0F;
        float var64 = var62 + (var37 - 4.0F) / 2.0F;
        Color var65 = var41 ? i : new Color(j.getRed(), j.getGreen(), j.getBlue(), var61);
        float var66 = Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var40, var21}) * 0.9F;
        if (var52) {
            float var67 = var53 + var47 - 5.5F - 4.0F;
            float var68 = var67 - 3.0F - var66;
            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var17};
            var10011[8] = 1.5F;
            var10011[7] = var65;
            var10011[6] = 4.0F;
            var10011[5] = 4.0F;
            var10011[4] = var64;
            var10011[3] = var67;
            var10011[2] = var5;
            var10011[1] = var4;
            var10011[0] = var6;
            RoundedPosColorShader.c(var10011);
            Font var89 = Prestige.c.a(new Object[]{var23});
            Object[] var10009 = new Object[]{null, null, null, null, null, NameTagsUtil.s, var31};
            var10009[4] = 0.9F;
            var10009[3] = var63;
            var10009[2] = var68;
            var10009[1] = var40;
            var10009[0] = var5;
            var89.w(var10009);
        } else {
            float var81 = var53 + 5.5F;
            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var17};
            var10011[8] = 1.5F;
            var10011[7] = var65;
            var10011[6] = 4.0F;
            var10011[5] = 4.0F;
            var10011[4] = var64;
            var10011[3] = var81;
            var10011[2] = var5;
            var10011[1] = var4;
            var10011[0] = var6;
            RoundedPosColorShader.c(var10011);
            Font var90 = Prestige.c.a(new Object[]{var23});
            var10003 = var81 + 4.0F + 3.0F;
            Object[] var115 = new Object[]{null, null, null, null, null, NameTagsUtil.s, var31};
            var115[4] = 0.9F;
            var115[3] = var63;
            var115[2] = var10003;
            var115[1] = var40;
            var115[0] = var5;
            var90.w(var115);
        }

        var62 += var37;
        var63 = var62 + (var37 - var35 * 0.9F) / 2.0F;
        float var82 = var62 + (var37 - 9.6F) / 2.0F;
        float var84 = var62 + (var37 - 3.0F) / 2.0F;
        Color var69 = var42 == 3 ? i : (var42 == 1 ? NameTagsUtil.t : NameTagsUtil.u);
        if (var52) {
            float var70 = var53 + var47 - 5.5F - 9.6F;
            ItemStack var91 = this.w[0];
            Object[] var110 = new Object[]{null, null, null, null, var5, var27};
            var110[3] = 0.6F;
            var110[2] = var82;
            var110[1] = var70;
            var110[0] = var91;
            MatrixStack.a_boolean_______________________________________(var110);
            if (var42 == 2) {
                float var107 = var70 - 3.0F - 30.0F;
                Object[] var116 = new Object[]{null, null, null, null, null, null, var15};
                var116[5] = var43;
                var116[4] = var84;
                var116[3] = var107;
                var116[2] = var5;
                var116[1] = var4;
                var116[0] = var6;
                this.s(var116);
            } else {
                float var71 = Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var45, var21}) * 0.9F;
                Font var92 = Prestige.c.a(new Object[]{var23});
                var10003 = var70 - 3.0F - var71;
                Object[] var117 = new Object[]{null, null, null, null, null, var69, var31};
                var117[4] = 0.9F;
                var117[3] = var63;
                var117[2] = var10003;
                var117[1] = var45;
                var117[0] = var5;
                var92.w(var117);
            }
        } else {
            float var85 = var53 + 5.5F;
            ItemStack var93 = this.w[0];
            Object[] var111 = new Object[]{null, null, null, null, var5, var27};
            var111[3] = 0.6F;
            var111[2] = var82;
            var111[1] = var85;
            var111[0] = var93;
            MatrixStack.a_boolean_______________________________________(var111);
            if (var42 == 2) {
                float var108 = var85 + 9.6F + 3.0F;
                Object[] var118 = new Object[]{null, null, null, null, null, null, var15};
                var118[5] = var43;
                var118[4] = var84;
                var118[3] = var108;
                var118[2] = var5;
                var118[1] = var4;
                var118[0] = var6;
                this.s(var118);
            } else {
                Font var94 = Prestige.c.a(new Object[]{var23});
                var10003 = var85 + 9.6F + 3.0F;
                Object[] var119 = new Object[]{null, null, null, null, null, var69, var31};
                var119[4] = 0.9F;
                var119[3] = var63;
                var119[2] = var10003;
                var119[1] = var45;
                var119[0] = var5;
                var94.w(var119);
            }
        }

        var62 += var37;
        var63 = var62 + (var37 - var35 * 0.9F) / 2.0F;
        var82 = var62 + (var37 - 9.6F) / 2.0F;
        boolean var86 = !var39 && var44 < 8;
        Color var87 = var86 ? new Color(j.getRed(), j.getGreen(), j.getBlue(), var61) : NameTagsUtil.s;
        float var72 = Prestige.c.a(new Object[]{var23}).a_float______________(new Object[]{var46, var21}) * 0.9F;
        if (var52) {
            float var73 = var53 + var47 - 5.5F - 9.6F;
            ItemStack var95 = this.w[1];
            Object[] var112 = new Object[]{null, null, null, null, var5, var27};
            var112[3] = 0.6F;
            var112[2] = var82;
            var112[1] = var73;
            var112[0] = var95;
            MatrixStack.a_boolean_______________________________________(var112);
            Font var96 = Prestige.c.a(new Object[]{var23});
            var10003 = var73 - 3.0F - var72;
            Object[] var120 = new Object[]{null, null, null, null, null, var87, var31};
            var120[4] = 0.9F;
            var120[3] = var63;
            var120[2] = var10003;
            var120[1] = var46;
            var120[0] = var5;
            var96.w(var120);
        } else {
            float var88 = var53 + 5.5F;
            ItemStack var97 = this.w[1];
            Object[] var113 = new Object[]{null, null, null, null, var5, var27};
            var113[3] = 0.6F;
            var113[2] = var82;
            var113[1] = var88;
            var113[0] = var97;
            MatrixStack.a_boolean_______________________________________(var113);
            Font var98 = Prestige.c.a(new Object[]{var23});
            var10003 = var88 + 9.6F + 3.0F;
            Object[] var121 = new Object[]{null, null, null, null, null, var87, var31};
            var121[4] = 0.9F;
            var121[3] = var63;
            var121[2] = var10003;
            var121[1] = var46;
            var121[0] = var5;
            var98.w(var121);
        }
    }

    private void s(Object[] var1) {
        RenderHelper8 var7 = (RenderHelper8)var1[0];
        MatrixCameraData var9 = (MatrixCameraData)var1[1];
        Matrix4f var5 = (Matrix4f)var1[2];
        float var6 = (Float)var1[3];
        float var8 = (Float)var1[4];
        float var2 = (Float)var1[5];
        long var3 = (Long)var1[6];
        var3 = 108227390598432L ^ var3;
        long var10 = var3 ^ 56299014473586L;
        long var12 = var3 ^ 90194618855325L;
        float var15 = 1.5F;
        Color var10007 = k;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var10};
        var10011[8] = var15;
        var10011[7] = var10007;
        var10011[6] = 3.0F;
        var10011[5] = 30.0F;
        var10011[4] = var8;
        var10011[3] = var6;
        var10011[2] = var5;
        var10011[1] = var9;
        var10011[0] = var7;
        RoundedPosColorShader.c(var10011);
        float var16 = Math.min(1.0F, Math.max(0.0F, var2));
        Event.b();
        float var17 = 30.0F * var16;
        if (var17 > 3.0F) {
            var10007 = MathHelper4.d(new Object[]{var12});
            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var10};
            var10011[8] = var15;
            var10011[7] = var10007;
            var10011[6] = 3.0F;
            var10011[5] = var17;
            var10011[4] = var8;
            var10011[3] = var6;
            var10011[2] = var5;
            var10011[1] = var9;
            var10011[0] = var7;
            RoundedPosColorShader.c(var10011);
            Color var18 = new Color(255, 255, 255, 200);
            float var10003 = var6 + var17 - 1.5F;
            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var10};
            var10011[8] = var15;
            var10011[7] = var18;
            var10011[6] = 3.0F;
            var10011[5] = 1.5F;
            var10011[4] = var8;
            var10011[3] = var10003;
            var10011[2] = var5;
            var10011[1] = var9;
            var10011[0] = var7;
            RoundedPosColorShader.c(var10011);
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
