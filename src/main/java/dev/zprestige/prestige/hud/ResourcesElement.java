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
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.util.TpsTimer;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.joml.Matrix4f;

public class ResourcesElement extends HudElement {
    private static final Item[] a = new Item[]{Items.GOLDEN_APPLE, Items.TOTEM_OF_UNDYING, Items.END_CRYSTAL, Items.EXPERIENCE_BOTTLE};
    private static final String[] c = new String[]{"Gapples", "Totems", "Crystals", "Exp"};
    private static final int[] d = new int[]{5, 2, 16, 8};
    private static final int i = 40;
    private final int[] j;
    private final int[] k;
    private final long[] l;
    private final ItemStack[] m;
    private boolean n;
    private boolean q;
    private static final float o = 0.6F;
    private static final float p = 9.6F;

    public ResourcesElement(long var1) {
        super("Resources", "Tracks gapples, totems, crystals, and exp — warns when any drops below threshold.", (22793589414440L));
        var1 = 0L;
        var1 = 98820726575509L ^ var1;
        long var3 = var1 ^ 85055208754109L;
        this.j = new int[a.length];
        this.k = new int[a.length];
        this.l = new long[a.length];
        this.m = new ItemStack[a.length];
        this.n = false;
        this.q = false;
    }

    private void r(Object[] var1) {
        if (!this.q) {
            for (int var5 = 0; var5 < a.length; var5++) {
                this.m[var5] = new ItemStack(a[var5]);
            }

            this.q = true;
        }
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 220.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    private static int a(Object[] var0) {
        Item var1 = (Item)var0[0];
        Event.b();
        int var5 = 0;

        for (int var6 = 0; var6 <= 35; var6++) {
            ItemStack var7 = b.player.getInventory().getStack(var6);
            if (var7.getItem() == var1) {
                var5 += var7.getCount();
            }
        }

        ItemStack var8 = b.player.getOffHandStack();
        if (var8.getItem() == var1) {
            var5 += var8.getCount();
        }

        return var5;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player != null && b.world != null) {
            for (int var9 = 0; var9 < a.length; var9++) {
                this.j[var9] = a(new Object[]{a[var9], 126516893439353L});
            }

            if (b.player.age < 40) {
                System.arraycopy(this.j, 0, this.k, 0, this.j.length);
                this.n = false;
            } else if (!this.n) {
                System.arraycopy(this.j, 0, this.k, 0, this.j.length);
                this.n = true;
            } else {
                long var16 = System.currentTimeMillis();

                for (int var11 = 0; var11 < a.length; var11++) {
                    int var12 = d[var11];
                    boolean var13 = this.k[var11] >= var12 && this.j[var11] < var12;
                    if (var13 && var16 >= this.l[var11]) {
                        TpsTimer var10000 = Prestige.v;
                        String var10002 = c[var11] + " low: " + this.j[var11] + "/" + var12;
                        Object[] var10007 = new Object[]{null, null, null, NotificationType.WARNING, 104881728073391L};
                        var10007[2] = 4000L;
                        var10007[1] = var10002;
                        var10007[0] = "Resources";
                        var10000.a(var10007);
                        long var14 = 5000L + (long)(Math.random() * 5000.0);
                        this.l[var11] = var16 + var14;
                    }

                    this.k[var11] = this.j[var11];
                }
            }
        }
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var2 = (RenderHelper8)var1[0];
        MatrixCameraData var3 = (MatrixCameraData)var1[1];
        Matrix4f var6 = (Matrix4f)var1[2];
        long var4 = (Long)var1[3];
        long var7 = var4 ^ 132449650111663L;
        long var9 = var4 ^ 95334181894420L;
        long var11 = var4 ^ 27807340928064L;
        long var13 = var4 ^ 89135395443612L;
        long var15 = var4 ^ 102693909475685L;
        long var17 = var4 ^ 131934975778103L;
        long var19 = var4 ^ 45947767099148L;
        long var21 = var4 ^ 64450247846539L;
        long var23 = var4 ^ 25893121108317L;
        long var25 = var4 ^ 54030867045760L;
        long var27 = var4 ^ 96557814079126L;
        long var29 = var4 ^ 101037798966850L;
        this.r(new Object[]{var23});
        String var32 = "Resources";
        float var33 = Prestige.c.a(new Object[]{var17}).c_float___(new Object[]{var25});
        Object[] var10003 = new Object[]{null, 1.0F};
        var10003[0] = var33;
        float var34 = NameTagsUtil.a(var10003);
        Event.b();
        Object[] var10004 = new Object[]{null, 0.9F};
        var10004[0] = var33;
        float var35 = NameTagsUtil.a(var10004);
        float var36 = Prestige.c.a(new Object[]{var17}).a_float______________(new Object[]{var32, var15});
        float var37 = 5.5F + var36 + 5.5F;

        for (int var38 = 0; var38 < a.length; var38++) {
            float var39 = Prestige.c.a(new Object[]{var17}).a_float______________(new Object[]{String.valueOf(this.j[var38]), var15}) * 0.9F;
            float var40 = 18.1F + var39 + 5.5F;
            if (var40 > var37) {
                var37 = var40;
            }
        }

        float var60 = var34 + 3.0F + var35 * a.length + 3.0F;
        this.d_boolean(new Object[0]);
        boolean var61 = this.c_boolean__(new Object[0]);
        var10004 = new Object[]{null, var60};
        var10004[0] = var37;
        this.i(var10004);
        float var41 = this.e;
        float var42 = this.f;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var60;
        var10011[5] = var37;
        var10011[4] = var42;
        var10011[3] = var41;
        var10011[2] = var6;
        var10011[1] = var3;
        var10011[0] = var2;
        RenderTarget.e(var10011);
        Color var10007 = NameTagsUtil.r;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var7};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var60;
        var10011[5] = var37;
        var10011[4] = var42;
        var10011[3] = var41;
        var10011[2] = var6;
        var10011[1] = var3;
        var10011[0] = var2;
        RoundedPosColorShader.c(var10011);
        float var43 = var42 + 3.0F;
        float var44 = var61 ? var41 + var37 - 5.5F - var36 : var41 + 5.5F;
        float var45 = var44;

        for (int var46 = 0; var46 < var32.length(); var46++) {
            String var47 = String.valueOf(var32.charAt(var46));
            int var48 = var61 ? var32.length() - 1 - var46 : var46;
            Font var10000 = Prestige.c.a(new Object[]{var17});
            Color var10005 = MathHelper4.d(new Object[]{var11});
            int var69 = 10 + var48;
            Object[] var10010 = new Object[]{null, null, null, var19};
            var10010[2] = var69;
            var10010[1] = 10;
            var10010[0] = var10005;
            Object[] var10008 = new Object[]{null, null, null, null, MatrixStack.b_Color_____(var10010), var27};
            var10008[3] = var43;
            var10008[2] = var45;
            var10008[1] = var47;
            var10008[0] = var6;
            var10000.v(var10008);
            var45 += Prestige.c.a(new Object[]{var17}).a_float______________(new Object[]{var47, var15});
        }

        float var62 = var42 + var34 - 0.5F;
        float var10001 = var41 + 5.5F;
        float var66 = var41 + var37 - 5.5F;
        float var68 = var62 + 0.5F;
        Object[] var71 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var13};
        var71[4] = var68;
        var71[3] = var66;
        var71[2] = var62;
        var71[1] = var10001;
        var71[0] = var6;
        RenderState.l(var71);
        long var63 = System.currentTimeMillis();
        int var49 = (int)Math.min(255.0, 170.0 + 85.0 * Math.sin(var63 * 0.012));
        float var50 = var42 + var34 + 3.0F;

        for (int var51 = 0; var51 < a.length; var51++) {
            boolean var52 = this.n && this.j[var51] < d[var51];
            Color var53 = var52 ? new Color(235, 64, 64, var49) : NameTagsUtil.s;
            String var54 = String.valueOf(this.j[var51]);
            float var55 = Prestige.c.a(new Object[]{var17}).a_float______________(new Object[]{var54, var15}) * 0.9F;
            float var56 = var50 + (var35 - var33 * 0.9F) / 2.0F;
            float var57 = var50 + (var35 - 9.6F) / 2.0F;
            float var58;
            float var59;
            if (var61) {
                var58 = var41 + var37 - 5.5F - 9.6F;
                var59 = var58 - 3.0F - var55;
            } else {
                var58 = var41 + 5.5F;
                var59 = var58 + 9.6F + 3.0F;
            }

            ItemStack var64 = this.m[var51];
            Object[] var70 = new Object[]{null, null, null, null, var6, var21};
            var70[3] = 0.6F;
            var70[2] = var57;
            var70[1] = var58;
            var70[0] = var64;
            MatrixStack.a_boolean_______________________________________(var70);
            Font var65 = Prestige.c.a(new Object[]{var17});
            Object[] var10009 = new Object[]{null, null, null, null, null, var53, var29};
            var10009[4] = 0.9F;
            var10009[3] = var56;
            var10009[2] = var59;
            var10009[1] = var54;
            var10009[0] = var6;
            var65.w(var10009);
            var50 += var35;
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
