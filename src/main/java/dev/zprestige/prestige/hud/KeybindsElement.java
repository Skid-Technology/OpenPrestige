package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.data.ModuleStringFloatData;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.FontRenderer;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import dev.zprestige.prestige.render.RoundedTextureShader;
import dev.zprestige.prestige.render.TextureUtil;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.joml.Matrix4f;

public class KeybindsElement extends HudElement {
    private static final float m = 1.5F;
    private static final float a = 8.0F;
    private static final float c = 7.5F;
    private static final float d = 70.0F;
    private final Map g;
    private FontRenderer h;
    private float k;
    private float l;
    private long i;
    private float o;
    private float p;
    private static final long H = 0L;

    public KeybindsElement(long var1) {
        super("Keybinds", "Show the client's keybinds", (63292773085619L));
        var1 = 0L;
        var1 = 80895934793056L ^ var1;
        long var3 = var1 ^ 123160103269587L;
        this.g = new HashMap();
        this.k = 70.0F;
        this.l = 0.0F;
        this.i = H;
        this.o = 0.0F;
        this.p = 0.0F;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 63.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var6 = (RenderHelper8)var1[0];
        MatrixCameraData var5 = (MatrixCameraData)var1[1];
        Matrix4f var4 = (Matrix4f)var1[2];
        long var2 = (Long)var1[3];
        long var7 = var2 ^ 73612951285412L;
        long var9 = var2 ^ 27807340928064L;
        long var11 = var2 ^ 95334181894420L;
        long var13 = var2 ^ 45947767099148L;
        long var15 = var2 ^ 54030867045760L;
        long var17 = var2 ^ 57131357196865L;
        long var19 = var2 ^ 132449650111663L;
        long var21 = var2 ^ 89135395443612L;
        long var23 = var2 ^ 102693909475685L;
        long var25 = var2 ^ 137665767872520L;
        long var27 = var2 ^ 131934975778103L;
        long var29 = var2 ^ 6014253680539L;
        long var31 = var2 ^ 19313467402120L;
        long var33 = var2 ^ 96557814079126L;
        long var35 = var2 ^ 101037798966850L;
        long var37 = var2 ^ 102686069302981L;
        if (this.h == null) {
            this.h = TextureUtil.a(new Object[]{"assets/prestige/icons/other/keyboard.png", var17});
        }

        float var40 = (float)(System.currentTimeMillis() - this.i) * 0.005F;
        this.i = System.currentTimeMillis();
        float var41 = Prestige.c.a(new Object[]{var27}).c_float___(new Object[]{var15});
        Object[] var10003 = new Object[]{null, 1.0F};
        var10003[0] = var41;
        float var42 = NameTagsUtil.a(var10003);
        var10003 = new Object[]{null, 0.75F};
        var10003[0] = var41;
        float var43 = NameTagsUtil.a(var10003);
        ArrayList var44 = new ArrayList();

        for (Module var46 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
            float var47 = var46.isEnabled() && var46.getKey(new Object[]{var25}) != -1 ? 1.0F : 0.0F;
            float var10000 = (float)this.g.getOrDefault(var46, 0.0F);
            Object[] var10005 = new Object[]{null, null, null, var29};
            var10005[2] = var40;
            var10005[1] = var47;
            var10005[0] = var10000;
            float var48 = MathHelper4.b_float______(var10005);
            this.g.put(var46, var48);
            if (!(var48 < 0.05F)) {
                String var96;
                if (KeyEvent.getKeyText(var46.getKey(new Object[]{var25})).contains("Unknown")) {
                    int var95 = var46.getKey(new Object[]{var25});
                    var10003 = new Object[]{null, var7};
                    var10003[0] = var95;
                    var96 = MathHelper4.a_String_____(var10003);
                } else {
                    var96 = KeyEvent.getKeyText(var46.getKey(new Object[]{var25}));
                }

                String var49 = var96;
                if (var49 != null && !var49.isEmpty()) {
                    var44.add(new ModuleStringFloatData(var46, var49, var48));
                }
            }
        }

        float var74 = 0.0F;
        float var75 = 70.0F;

        for (ModuleStringFloatData var78 : (Iterable<ModuleStringFloatData>)(var44)) {
            float var81 = Prestige.c.a(new Object[]{var27}).a_float______________(new Object[]{var78.ba(), var23}) * 0.75F + 5.5F;
            float var50 = Prestige.c.a(new Object[]{var27}).a_float______________(new Object[]{var78.a_().getName(), var23}) * 0.75F;
            var74 = Math.max(var74, var81 * var78.bb());
            var75 = Math.max(var75, (5.5F + var81 + 3.0F + var50 + 5.5F) * var78.bb());
        }

        float var10001 = this.k;
        float var111 = var40 * 2.0F;
        Object[] var10006 = new Object[]{null, null, null, var29};
        var10006[2] = var111;
        var10006[1] = var75;
        var10006[0] = var10001;
        this.k = MathHelper4.b_float______(var10006);
        var10001 = this.l;
        float var112 = var40 * 2.0F;
        var10006 = new Object[]{null, null, null, var29};
        var10006[2] = var112;
        var10006[1] = var74;
        var10006[0] = var10001;
        this.l = MathHelper4.b_float______(var10006);
        float var77 = var42;

        for (ModuleStringFloatData var82 : (Iterable<ModuleStringFloatData>)(var44)) {
            var77 += (var43 + 1.5F) * var82.bb();
        }

        boolean var80 = this.c_boolean__(new Object[0]);
        boolean var83 = this.d_boolean(new Object[0]);
        boolean var84 = b.currentScreen instanceof HudEditorScreen;
        if (!var84) {
            if (var83 && this.o > 0.0F) {
                this.f = this.f - (var77 - this.o);
            }

            if (var80 && this.p > 0.0F) {
                this.e = this.e - (this.k - this.p);
            }
        }

        this.o = var77;
        this.p = this.k;
        float var51 = this.e;
        float var52 = this.f;
        var10001 = this.k;
        Object[] var10004 = new Object[]{null, var77};
        var10004[0] = var10001;
        this.i(var10004);
        Color var53 = MathHelper4.d(new Object[]{var9});
        float var54 = var83 ? var52 + var77 - var42 : var52;
        int var97 = 1773;
        float var127 = this.k;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, var11};
        var10012[8] = 4.0F;
        var10012[7] = 5;
        var10012[6] = var42;
        var10012[5] = var127;
        var10012[4] = var54;
        var10012[3] = var51;
        var10012[2] = var4;
        var10012[1] = var5;
        var10012[0] = var6;
        RenderTarget.e(var10012);
        long var105 = 6767746664560558356L ^ var2;
        float var10007 = this.k;
        Color var10009 = NameTagsUtil.r;
        Object[] var10013 = new Object[]{null, null, null, null, null, null, null, null, null, var19};
        var10013[8] = 4.0F;
        var10013[7] = var10009;
        var10013[6] = var42;
        var10013[5] = var10007;
        var10013[4] = var54;
        var10013[3] = var51;
        var10013[2] = var4;
        var10013[1] = var5;
        var10013[0] = var6;
        RoundedPosColorShader.c(var10013);
        String var55 = "Keybinds";
        float var56 = (var42 - 7.5F) / 2.0F;
        float var57 = var54 + (var42 - var41) / 2.0F;
        if (var80) {
            float var58 = var51 + this.k - 5.5F - 7.5F;
            FontRenderer var113 = this.h;
            float var120 = var54 + var56;
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var53, var31};
            var10011[7] = 7.5F;
            var10011[6] = 7.5F;
            var10011[5] = var120;
            var10011[4] = var58;
            var10011[3] = var113;
            var10011[2] = var4;
            var10011[1] = var5;
            var10011[0] = var6;
            RoundedTextureShader.c(var10011);
            float var59 = var58 - 3.0F - 0.5F;
            float var10002 = var54 + var56;
            float var114 = var59 + 0.5F;
            float var118 = var54 + var42 - var56;
            Object[] var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var21};
            var10008[4] = var118;
            var10008[3] = var114;
            var10008[2] = var10002;
            var10008[1] = var59;
            var10008[0] = var4;
            RenderState.l(var10008);
            float var60 = var59 - 3.0F - Prestige.c.a(new Object[]{var27}).a_float______________(new Object[]{var55, var23});

            for (int var61 = 0; var61 < var55.length(); var61++) {
                String var62 = String.valueOf(var55.charAt(var61));
                Font var98 = Prestige.c.a(new Object[]{var27});
                int var128 = 10 + (var55.length() - 1 - var61);
                Object[] var10010 = new Object[]{null, null, null, var13};
                var10010[2] = var128;
                var10010[1] = 10;
                var10010[0] = var53;
                var10008 = new Object[]{null, null, null, null, MatrixStack.b_Color_____(var10010), var33};
                var10008[3] = var57;
                var10008[2] = var60;
                var10008[1] = var62;
                var10008[0] = var4;
                var98.v(var10008);
                var60 += Prestige.c.a(new Object[]{var27}).a_float______________(new Object[]{var62, var23});
            }
        } else {
            float var85 = var51 + 5.5F;
            FontRenderer var115 = this.h;
            float var121 = var54 + var56;
            Object[] var140 = new Object[]{null, null, null, null, null, null, null, null, var53, var31};
            var140[7] = 7.5F;
            var140[6] = 7.5F;
            var140[5] = var121;
            var140[4] = var85;
            var140[3] = var115;
            var140[2] = var4;
            var140[1] = var5;
            var140[0] = var6;
            RoundedTextureShader.c(var140);
            float var87 = var85 + 7.5F + 3.0F;
            float var106 = var54 + var56;
            float var116 = var87 + 0.5F;
            float var119 = var54 + var42 - var56;
            Object[] var133 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var21};
            var133[4] = var119;
            var133[3] = var116;
            var133[2] = var106;
            var133[1] = var87;
            var133[0] = var4;
            RenderState.l(var133);
            float var89 = var87 + 0.5F + 3.0F;

            for (int var91 = 0; var91 < var55.length(); var91++) {
                String var93 = String.valueOf(var55.charAt(var91));
                Font var99 = Prestige.c.a(new Object[]{var27});
                int var129 = 10 + var91;
                Object[] var138 = new Object[]{null, null, null, var13};
                var138[2] = var129;
                var138[1] = 10;
                var138[0] = var53;
                var133 = new Object[]{null, null, null, null, MatrixStack.b_Color_____(var138), var33};
                var133[3] = var57;
                var133[2] = var89;
                var133[1] = var93;
                var133[0] = var4;
                var99.v(var133);
                var89 += Prestige.c.a(new Object[]{var27}).a_float______________(new Object[]{var93, var23});
            }
        }

        float var86 = var83 ? var54 - 1.5F - var43 : var52 + var42 + 1.5F;

        for (int var88 = 0; var88 < var44.size(); var88++) {
            ModuleStringFloatData var90 = (ModuleStringFloatData)var44.get(var88);
            float var92 = var90.bb();
            var97 = (int)(255.0F * var92);
            var10003 = new Object[]{null, var37};
            var10003[0] = var97;
            int var94 = a(var10003);
            if (var92 > 0.7F) {
                float var122 = this.k;
                Object[] var141 = new Object[]{null, null, null, null, null, null, null, null, null, var11};
                var141[8] = 4.0F;
                var141[7] = 5;
                var141[6] = var43;
                var141[5] = var122;
                var141[4] = var86;
                var141[3] = var51;
                var141[2] = var4;
                var141[1] = var5;
                var141[0] = var6;
                RenderTarget.e(var141);
            }

            float var123 = this.k;
            int var135 = NameTagsUtil.r.getRed();
            int var139 = NameTagsUtil.r.getGreen();
            int var142 = NameTagsUtil.r.getBlue();
            int var146 = (int)(NameTagsUtil.r.getAlpha() * var92);
            Object[] var10015 = new Object[]{null, var37};
            var10015[0] = var146;
            Color var130 = new Color(var135, var139, var142, a(var10015));
            Object[] var143 = new Object[]{null, null, null, null, null, null, null, null, null, var19};
            var143[8] = 4.0F;
            var143[7] = var130;
            var143[6] = var43;
            var143[5] = var123;
            var143[4] = var86;
            var143[3] = var51;
            var143[2] = var4;
            var143[1] = var5;
            var143[0] = var6;
            RoundedPosColorShader.c(var143);
            float var63 = var80 ? var51 + this.k - 5.5F - this.l : var51 + 5.5F;
            float var64 = var86 + (var43 - 8.0F) / 2.0F;
            var123 = this.l;
            int var147 = (int)(25.0F * var92);
            var10015 = new Object[]{null, var37};
            var10015[0] = var147;
            Color var131 = new Color(255, 255, 255, a(var10015));
            Object[] var144 = new Object[]{null, null, null, null, null, null, null, null, null, var19};
            var144[8] = 3.0F;
            var144[7] = var131;
            var144[6] = 8.0F;
            var144[5] = var123;
            var144[4] = var64;
            var144[3] = var63;
            var144[2] = var4;
            var144[1] = var5;
            var144[0] = var6;
            RoundedPosColorShader.c(var144);
            int var107 = 10 + var88;
            Object[] var125 = new Object[]{null, null, null, var13};
            var125[2] = var107;
            var125[1] = 10;
            var125[0] = var53;
            Color var65 = MatrixStack.b_Color_____(var125);
            Color var66 = new Color(var65.getRed(), var65.getGreen(), var65.getBlue(), var94);
            float var67 = Prestige.c.a(new Object[]{var27}).a_float______________(new Object[]{var90.ba(), var23}) * 0.75F;
            float var68 = var63 + (this.l - var67) / 2.0F;
            float var69 = var64 + (8.0F - var41 * 0.75F) / 2.0F;
            Font var101 = Prestige.c.a(new Object[]{var27});
            String var108 = var90.ba();
            Object[] var136 = new Object[]{null, null, null, null, null, var66, var35};
            var136[4] = 0.75F;
            var136[3] = var69;
            var136[2] = var68;
            var136[1] = var108;
            var136[0] = var4;
            var101.w(var136);
            String var70 = var90.a_().getName();
            float var71 = Prestige.c.a(new Object[]{var27}).a_float______________(new Object[]{var70, var23}) * 0.75F;
            float var72 = var80 ? var63 - 3.0F - var71 : var63 + this.l + 3.0F;
            float var73 = var86 + (var43 - var41 * 0.75F) / 2.0F;
            Font var102 = Prestige.c.a(new Object[]{var27});
            var142 = (int)(220.0F * var92);
            Object[] var10014 = new Object[]{null, var37};
            var10014[0] = var142;
            Object[] var137 = new Object[]{null, null, null, null, null, new Color(255, 255, 255, a(var10014)), var35};
            var137[4] = 0.75F;
            var137[3] = var73;
            var137[2] = var72;
            var137[1] = var70;
            var137[0] = var4;
            var102.w(var137);
            if (var83) {
                var86 -= (var43 + 1.5F) * var92;
            } else {
                var86 += (var43 + 1.5F) * var92;
            }
        }
    }

    private static int a(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 < 0 ? 0 : Math.min(var3, 255);
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
