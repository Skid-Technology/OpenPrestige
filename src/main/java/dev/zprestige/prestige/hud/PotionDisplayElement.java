package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatHelper;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class PotionDisplayElement extends HudElement {
    private static final float m = 0.65F;
    private final Map a;
    private FontRenderer c;
    private float g;
    private float h;
    private float k;

    public PotionDisplayElement(long var1) {
        super("Potion Display", "Show active potion effects with duration and level", (131056569934820L));
        var1 = 0L;
        var1 = 104440006182506L ^ var1;
        long var3 = var1 ^ 45961136301454L;
        this.a = new LinkedHashMap();
        this.g = 70.0F;
        this.h = 0.0F;
        this.k = 0.0F;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 100.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    public String b(Object[] var1) {
        int var4 = (Integer)var1[0];

        return switch (var4) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> var4 > 0 ? String.valueOf(var4) : "I";
        };
    }

    public String c(Object[] var1) {
        int var2 = (Integer)var1[0];
        int var5 = var2 / 20;
        int var6 = var5 / 60;
        int var7 = var5 % 60;
        return var6 > 0 ? String.format("%d:%02d", var6, var7) : String.format("0:%02d", var7);
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var2 = (RenderHelper8)var1[0];
        MatrixCameraData var6 = (MatrixCameraData)var1[1];
        Matrix4f var3 = (Matrix4f)var1[2];
        long var4 = (Long)var1[3];
        long var7 = var4 ^ 95334181894420L;
        long var9 = var4 ^ 27807340928064L;
        long var11 = var4 ^ 96488322041274L;
        long var13 = var4 ^ 45947767099148L;
        long var15 = var4 ^ 54030867045760L;
        long var17 = var4 ^ 92326280031513L;
        long var19 = var4 ^ 117880139760215L;
        long var21 = var4 ^ 93148276620004L;
        long var23 = var4 ^ 57131357196865L;
        long var25 = var4 ^ 132449650111663L;
        long var27 = var4 ^ 33779468439083L;
        long var29 = var4 ^ 89135395443612L;
        long var31 = var4 ^ 102693909475685L;
        long var33 = var4 ^ 63614044077481L;
        long var35 = var4 ^ 53426174796383L;
        long var37 = var4 ^ 6014253680539L;
        long var39 = var4 ^ 131934975778103L;
        long var41 = var4 ^ 19313467402120L;
        long var43 = var4 ^ 96557814079126L;
        long var45 = var4 ^ 101037798966850L;
        if (this.c == null) {
            this.c = TextureUtil.a(new Object[]{"assets/prestige/icons/hud/potion.png", var23});
        }

        float var48 = (float)(System.currentTimeMillis() - 0L) * 0.005F;
        System.currentTimeMillis();
        HashSet var49 = new HashSet();
        if (b.player != null) {
            for (Entry var51 : b.player.getActiveStatusEffects().entrySet()) {
                StatusEffect var52 = (StatusEffect)((RegistryEntry)var51.getKey()).value();
                StatusEffectInstance var53 = (StatusEffectInstance)var51.getValue();
                if (var52 != null && var53 != null && (var53.isInfinite() || var53.getDuration() > 0)) {
                    var49.add(var52);
                    CombatHelper var54 = (CombatHelper)this.a.computeIfAbsent(var52, lpL42_0 -> new CombatHelper(87719101051954L));
                    var54.a = var52.getName().getString();
                    String var10001;
                    if (var53.getAmplifier() > 0) {
                        int var10002 = var53.getAmplifier() + 1;
                        Object[] var10005 = new Object[]{null, var27};
                        var10005[0] = var10002;
                        var10001 = this.b(var10005);
                    } else {
                        var10001 = null;
                    }

                    var54.b = var10001;
                    var54.c = b_Color_(new Object[]{new Color(var52.getColor()), var35});
                    var54.f = var53.isInfinite();
                    var54.d = var53.getDuration();
                    if (var54.d > var54.e) {
                        var54.e = var54.d;
                    }
                }
            }
        }

        Iterator var86 = this.a.entrySet().iterator();

        while (var86.hasNext()) {
            Entry var87 = (Entry)var86.next();
            CombatHelper var89 = (CombatHelper)var87.getValue();
            float var91 = var49.contains(var87.getKey()) ? 1.0F : 0.0F;
            float var120 = var89.g;
            Object[] var10006 = new Object[]{null, null, null, var37};
            var10006[2] = var48;
            var10006[1] = var91;
            var10006[0] = var120;
            var89.g = MathHelper4.b_float______(var10006);
            if (var91 == 0.0F && var89.g < 0.02F) {
                var86.remove();
            }
        }

        float var88 = Prestige.c.a(new Object[]{var39}).c_float___(new Object[]{var15});
        Object[] var10003 = new Object[]{null, 1.0F};
        var10003[0] = var88;
        float var90 = NameTagsUtil.a(var10003);
        var10003 = new Object[]{null, 0.9F};
        var10003[0] = var88;
        float var92 = NameTagsUtil.a(var10003);
        float var93 = 23.0F;
        float var55 = var93 + Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{"Potions", var31}) + 5.5F;
        float var56 = Math.max(70.0F, var55);
        float var57 = var90;

        for (CombatHelper var59 : (Iterable<CombatHelper>)(this.a.values())) {
            if (!(var59.g < 0.02F)) {
                float var60 = 5.5F
                    + Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var59.a, var31}) * 0.9F
                    + (var59.b != null ? 3.0F + this.c_float(new Object[]{var59.b, var11}) : 0.0F)
                    + 8.0F
                    + Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{this.d(new Object[]{var59, var17}), var31}) * 0.9F
                    + 5.5F;
                if (var60 > var56) {
                    var56 = var60;
                }

                var57 += (var92 + 3.0F) * var59.g;
            }
        }

        float var121 = this.g;
        float var135 = var48 * 2.0F;
        Object[] var158 = new Object[]{null, null, null, var37};
        var158[2] = var135;
        var158[1] = var56;
        var158[0] = var121;
        this.g = MathHelper4.b_float______(var158);
        boolean var94 = this.d_boolean(new Object[0]);
        boolean var95 = this.c_boolean__(new Object[0]);
        boolean var96 = b.currentScreen instanceof HudEditorScreen;
        if (!var96) {
            if (var94 && this.h > 0.0F) {
                this.f = this.f - (var57 - this.h);
            }

            if (var95 && this.k > 0.0F) {
                this.e = this.e - (this.g - this.k);
            }
        }

        this.h = var57;
        this.k = this.g;
        var121 = this.g;
        Object[] var10004 = new Object[]{null, var57};
        var10004[0] = var121;
        this.i(var10004);
        float var61 = this.e;
        float var62 = var94 ? this.f + var57 - var90 : this.f;
        short var10000 = 24821;
        float var159 = this.g;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, var7};
        var10012[8] = 4.0F;
        var10012[7] = 5;
        var10012[6] = var90;
        var10012[5] = var159;
        var10012[4] = var62;
        var10012[3] = var61;
        var10012[2] = var3;
        var10012[1] = var6;
        var10012[0] = var2;
        RenderTarget.e(var10012);
        long var123 = 3027978994649654946L ^ var4;
        float var10007 = this.g;
        Color var10009 = NameTagsUtil.r;
        Object[] var10013 = new Object[]{null, null, null, null, null, null, null, null, null, var25};
        var10013[8] = 4.0F;
        var10013[7] = var10009;
        var10013[6] = var90;
        var10013[5] = var10007;
        var10013[4] = var62;
        var10013[3] = var61;
        var10013[2] = var3;
        var10013[1] = var6;
        var10013[0] = var2;
        RoundedPosColorShader.c(var10013);
        String var63 = "Potions";
        float var64 = var62 + (var90 - 7.5F) / 2.0F;
        float var65 = var62 + 3.0F;
        if (var95) {
            FontRenderer var136 = this.c;
            float var144 = var61 + this.g - 5.5F - 7.5F;
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.d(new Object[]{var9}), var41};
            var10011[7] = 7.5F;
            var10011[6] = 7.5F;
            var10011[5] = var64;
            var10011[4] = var144;
            var10011[3] = var136;
            var10011[2] = var3;
            var10011[1] = var6;
            var10011[0] = var2;
            RoundedTextureShader.c(var10011);
            float var66 = var61 + this.g - 18.0F;
            var121 = var66 - 0.5F;
            float var130 = var62 + 3.0F;
            float var145 = var62 + var90 - 3.0F;
            Object[] var10008 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var29};
            var10008[4] = var145;
            var10008[3] = var66;
            var10008[2] = var130;
            var10008[1] = var121;
            var10008[0] = var3;
            RenderState.l(var10008);
            float var67 = var61 + this.g - var93 - Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var63, var31});

            for (int var68 = 0; var68 < var63.length(); var68++) {
                String var69 = String.valueOf(var63.charAt(var68));
                Font var110 = Prestige.c.a(new Object[]{var39});
                Color var152 = MathHelper4.d(new Object[]{var9});
                int var163 = 10 + (var63.length() - 1 - var68);
                Object[] var10010 = new Object[]{null, null, null, var13};
                var10010[2] = var163;
                var10010[1] = 10;
                var10010[0] = var152;
                var10008 = new Object[]{null, null, null, null, MatrixStack.b_Color_____(var10010), var43};
                var10008[3] = var65;
                var10008[2] = var67;
                var10008[1] = var69;
                var10008[0] = var3;
                var110.v(var10008);
                var67 += Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var69, var31});
            }
        } else {
            FontRenderer var137 = this.c;
            float var146 = var61 + 5.5F;
            Object[] var182 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.d(new Object[]{var9}), var41};
            var182[7] = 7.5F;
            var182[6] = 7.5F;
            var182[5] = var64;
            var182[4] = var146;
            var182[3] = var137;
            var182[2] = var3;
            var182[1] = var6;
            var182[0] = var2;
            RoundedTextureShader.c(var182);
            float var97 = var61 + 5.5F + 7.5F + 5.0F;
            float var131 = var62 + 3.0F;
            float var138 = var97 + 0.5F;
            float var147 = var62 + var90 - 3.0F;
            Object[] var168 = new Object[]{null, null, null, null, null, NameTagsUtil.v, var29};
            var168[4] = var147;
            var168[3] = var138;
            var168[2] = var131;
            var168[1] = var97;
            var168[0] = var3;
            RenderState.l(var168);
            float var99 = var61 + var93;

            for (int var101 = 0; var101 < var63.length(); var101++) {
                String var103 = String.valueOf(var63.charAt(var101));
                Font var111 = Prestige.c.a(new Object[]{var39});
                Color var153 = MathHelper4.d(new Object[]{var9});
                int var164 = 10 + var101;
                Object[] var179 = new Object[]{null, null, null, var13};
                var179[2] = var164;
                var179[1] = 10;
                var179[0] = var153;
                var168 = new Object[]{null, null, null, null, MatrixStack.b_Color_____(var179), var43};
                var168[3] = var65;
                var168[2] = var99;
                var168[1] = var103;
                var168[0] = var3;
                var111.v(var168);
                var99 += Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var103, var31});
            }
        }

        float var98 = var94 ? var62 - 3.0F - var92 : var62 + var90 + 3.0F;

        for (CombatHelper var102 : (Iterable<CombatHelper>)(this.a.values())) {
            if (!(var102.g < 0.02F)) {
                float var104 = var102.g;
                int var70 = (int)(255.0F * var104);
                float var72 = (1.0F - var104) * 6.0F;
                if (var104 > 0.7F) {
                    float var154 = this.g;
                    Object[] var183 = new Object[]{null, null, null, null, null, null, null, null, null, var7};
                    var183[8] = 4.0F;
                    var183[7] = 5;
                    var183[6] = var92;
                    var183[5] = var154;
                    var183[4] = var98;
                    var183[3] = var61;
                    var183[2] = var3;
                    var183[1] = var6;
                    var183[0] = var2;
                    RenderTarget.e(var183);
                }

                float var155 = this.g;
                Color var165 = NameTagsUtil.r;
                int var170 = (int)(NameTagsUtil.r.getAlpha() * var104);
                Object[] var184 = new Object[]{null, null, var33};
                var184[1] = var170;
                var184[0] = var165;
                Color var166 = a(var184);
                var184 = new Object[]{null, null, null, null, null, null, null, null, null, var25};
                var184[8] = 4.0F;
                var184[7] = var166;
                var184[6] = var92;
                var184[5] = var155;
                var184[4] = var98;
                var184[3] = var61;
                var184[2] = var3;
                var184[1] = var6;
                var184[0] = var2;
                RoundedPosColorShader.c(var184);
                String var73 = this.d(new Object[]{var102, var17});
                float var74 = Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var102.a, var31}) * 0.9F;
                float var75 = Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var73, var31}) * 0.9F;
                float var76 = var98 + (var92 - 2.0F - var88 * 0.9F) / 2.0F;
                float var77;
                float var78;
                if (var95) {
                    var77 = var61 + this.g - 5.5F - var74 - var72;
                    var78 = var61 + 5.5F;
                } else {
                    var77 = var61 + 5.5F + var72;
                    var78 = var61 + this.g - 5.5F - var75;
                }

                Font var112 = Prestige.c.a(new Object[]{var39});
                String var132 = var102.a;
                Color var160 = NameTagsUtil.s;
                Object[] var180 = new Object[]{null, null, var33};
                var180[1] = var70;
                var180[0] = var160;
                Object[] var174 = new Object[]{null, null, null, null, null, a(var180), var45};
                var174[4] = 0.9F;
                var174[3] = var76;
                var174[2] = var77;
                var174[1] = var132;
                var174[0] = var3;
                var112.w(var174);
                if (var102.b != null) {
                    float var79 = this.c_float(new Object[]{var102.b, var11});
                    float var80 = var88 * 0.65F + 1.5F;
                    float var81 = var95 ? var77 - 3.0F - var79 : var77 + var74 + 3.0F;
                    float var82 = var98 + (var92 - 2.0F - var80) / 2.0F;
                    float var139 = var81 + var79;
                    float var148 = var82 + var80;
                    Color var156 = var102.c;
                    int var161 = (int)(70.0F * var104);
                    Object[] var175 = new Object[]{null, null, var33};
                    var175[1] = var161;
                    var175[0] = var156;
                    Object[] var176 = new Object[]{null, null, null, null, null, a(var175), new Vector4f(3.0F), var19};
                    var176[4] = var148;
                    var176[3] = var139;
                    var176[2] = var82;
                    var176[1] = var81;
                    var176[0] = var3;
                    RenderState.u(var176);
                    Font var113 = Prestige.c.a(new Object[]{var39});
                    var132 = var102.b;
                    float var140 = var81 + 2.0F;
                    float var149 = var82 + 0.75F;
                    Color var162 = var102.c;
                    var180 = new Object[]{null, null, var33};
                    var180[1] = var70;
                    var180[0] = var162;
                    Object[] var177 = new Object[]{null, null, null, null, null, a(var180), var45};
                    var177[4] = 0.65F;
                    var177[3] = var149;
                    var177[2] = var140;
                    var177[1] = var132;
                    var177[0] = var3;
                    var113.w(var177);
                }

                int var105 = var102.f ? Integer.MAX_VALUE : var102.d / 20;
                Color var106;
                if (var105 <= 5) {
                    int var107 = (int)(170.0 + 85.0 * Math.sin(System.currentTimeMillis() * 0.012));
                    var106 = new Color(235, 64, 64, (int)(Math.min(var107, 255) * var104));
                } else if (var105 <= 10) {
                    var106 = new Color(255, 190, 80, var70);
                } else {
                    var106 = new Color(235, 235, 235, var70);
                }

                Font var114 = Prestige.c.a(new Object[]{var39});
                Object[] var178 = new Object[]{null, null, null, null, null, var106, var45};
                var178[4] = 0.9F;
                var178[3] = var76;
                var178[2] = var78;
                var178[1] = var73;
                var178[0] = var3;
                var114.w(var178);
                float var115;
                if (var102.f) {
                    var115 = 1.0F;
                } else if (var102.e > 0) {
                    float var116 = (float)var102.d / var102.e;
                    Object[] var157 = new Object[]{null, null, null, var21};
                    var157[2] = 1.0F;
                    var157[1] = 0.0F;
                    var157[0] = var116;
                    var115 = MathHelper4.a(var157);
                } else {
                    var115 = 0.0F;
                }

                float var108 = var115;
                float var109 = var98 + var92 - 1.9F;
                float var83 = var98 + var92 - 1.15F;
                float var84 = (this.g - 4.0F) * var108;
                Color var118;
                if (var105 <= 5) {
                    Color var117 = new Color(235, 64, 64);
                    int var125 = (int)(200.0F * var104);
                    var10004 = new Object[]{null, null, var33};
                    var10004[1] = var125;
                    var10004[0] = var117;
                    var118 = a(var10004);
                } else {
                    Color var119 = var102.c;
                    int var126 = (int)(160.0F * var104);
                    var10004 = new Object[]{null, null, var33};
                    var10004[1] = var126;
                    var10004[0] = var119;
                    var118 = a(var10004);
                }

                Color var85 = var118;
                var121 = var61 + 2.0F;
                float var141 = var61 + this.g - 2.0F;
                Object[] var171 = new Object[]{null, null, null, null, null, new Color(255, 255, 255, (int)(18.0F * var104)), var29};
                var171[4] = var83;
                var171[3] = var141;
                var171[2] = var109;
                var171[1] = var121;
                var171[0] = var3;
                RenderState.l(var171);
                if (var95) {
                    var121 = var61 + this.g - 2.0F - var84;
                    float var142 = var61 + this.g - 2.0F;
                    Object[] var172 = new Object[]{null, null, null, null, null, var85, var29};
                    var172[4] = var83;
                    var172[3] = var142;
                    var172[2] = var109;
                    var172[1] = var121;
                    var172[0] = var3;
                    RenderState.l(var172);
                } else {
                    var121 = var61 + 2.0F;
                    float var143 = var61 + 2.0F + var84;
                    Object[] var173 = new Object[]{null, null, null, null, null, var85, var29};
                    var173[4] = var83;
                    var173[3] = var143;
                    var173[2] = var109;
                    var173[1] = var121;
                    var173[0] = var3;
                    RenderState.l(var173);
                }

                var98 += (var94 ? -1.0F : 1.0F) * (var92 + 3.0F) * var104;
            }
        }
    }

    private float c_float(Object[] var1) {
        String var2 = (String)var1[0];
        long var3 = (Long)var1[1];
        var3 = 104440006182506L ^ var3;
        long var5 = var3 ^ 92753789694645L;
        long var7 = var3 ^ 139376588512999L;
        return Prestige.c.a(new Object[]{var7}).a_float______________(new Object[]{var2, var5}) * 0.65F + 4.0F;
    }

    private String d(Object[] var1) {
        CombatHelper var4 = (CombatHelper)var1[0];
        long var2 = (Long)var1[1];
        var2 = 104440006182506L ^ var2;
        long var5 = var2 ^ 135932649284719L;
        String var10000;
        if (var4.f) {
            var10000 = "INF";
        } else {
            int var10001 = var4.d;
            Object[] var10004 = new Object[]{null, var5};
            var10004[0] = var10001;
            var10000 = this.c(var10004);
        }

        return var10000;
    }

    private static Color a(Object[] var0) {
        Color var1 = (Color)var0[0];
        int var2 = (Integer)var0[1];
        return new Color(var1.getRed(), var1.getGreen(), var1.getBlue(), Math.max(0, Math.min(var2, 255)));
    }

    private static Color b_Color_(Object[] var0) {
        Color var1 = (Color)var0[0];
        Event.b();
        int var5 = var1.getRed();
        int var6 = var1.getGreen();
        int var7 = var1.getBlue();
        int var8 = var5 * 3 + var6 * 4 + var7 >> 3;
        if (var8 < 90) {
            float var9 = (90 - var8) / 90.0F * 0.6F;
            var5 += (int)((255 - var5) * var9);
            var6 += (int)((255 - var6) * var9);
            var7 += (int)((255 - var7) * var9);
        }

        return new Color(var5, var6, var7);
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
