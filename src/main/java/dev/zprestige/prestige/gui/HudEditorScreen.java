package dev.zprestige.prestige.gui;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.hud.CartHelperElement;
import dev.zprestige.prestige.hud.CooldownIndicatorElement;
import dev.zprestige.prestige.hud.CrosshairElement;
import dev.zprestige.prestige.hud.FPSElement;
import dev.zprestige.prestige.hud.HudElement;
import dev.zprestige.prestige.hud.KeybindsElement;
import dev.zprestige.prestige.hud.LatencyElement;
import dev.zprestige.prestige.hud.ModuleListElement;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.hud.OffscreenArrowElement;
import dev.zprestige.prestige.hud.PotionDisplayElement;
import dev.zprestige.prestige.hud.ResourcesElement;
import dev.zprestige.prestige.hud.TPSElement;
import dev.zprestige.prestige.hud.TargetElement;
import dev.zprestige.prestige.hud.TimeElement;
import dev.zprestige.prestige.hud.WatermarkElement;
import dev.zprestige.prestige.hud.WebsiteElement;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.module.visual.HudEditor;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderContract5;
import dev.zprestige.prestige.render.RenderContract6;
import dev.zprestige.prestige.render.RenderHelper21;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import dev.zprestige.prestige.type.Axis;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

public class HudEditorScreen extends BaseScreen {
    private final HudElement[] a;
    private final ArrayList b;
    private boolean c;
    private float d;
    private float e;
    private float f;
    private float g;
    private int h;
    private boolean i;
    private HudElement j;
    private static final int[] k = new int[]{263, 262, 265, 264, 82};
    private final long[] l;
    private final boolean[] m;
    private float n;
    private long o;
    private static final float p = 140.0F;
    private static final float q = 24.0F;
    private static final float r = 20.0F;

    public static float a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 31417140128405L ^ var1;
        long var3 = var1 ^ 136619947291882L;
        return HudEditor.b_float______________(new Object[]{var3}) / 100.0F;
    }

    public static float b(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 31417140128405L ^ var1;
        long var3 = var1 ^ 86449951420142L;
        return 2.0F * a(new Object[]{var3});
    }

    public HudEditorScreen(long var1) {
        super();
        var1 = 0L;
        var1 = 31417140128405L ^ var1;
        long var3 = var1 ^ 82258139621509L;
        long var5 = var1 ^ 45469756952905L;
        long var7 = var1 ^ 62489419464663L;
        long var9 = var1 ^ 112858639643060L;
        long var11 = var1 ^ 15800096486225L;
        long var13 = var1 ^ 88196451292706L;
        long var15 = var1 ^ 55164932215004L;
        long var17 = var1 ^ 84511101675290L;
        long var19 = var1 ^ 116826991623579L;
        long var21 = var1 ^ 140392464330147L;
        long var23 = var1 ^ 84882787092113L;
        long var25 = var1 ^ 13389506241451L;
        long var27 = var1 ^ 114328870863646L;
        long var29 = var1 ^ 86933154160008L;
        long var31 = var1 ^ 69908061708237L;
        long var33 = var1 ^ 113259016269802L;
        this.a = new HudElement[]{
            new CrosshairElement(var23),
            new CooldownIndicatorElement(var13),
            new OffscreenArrowElement(var17),
            new ModuleNotificationsElement(var25),
            new TargetElement(var11),
            new FPSElement(var19),
            new TPSElement(var7),
            new KeybindsElement(var27),
            new ModuleListElement(var15),
            new LatencyElement(var29),
            new TimeElement(var33),
            new PotionDisplayElement(var5),
            new ResourcesElement(var3),
            new CartHelperElement(var9),
            new WatermarkElement(var21),
            new WebsiteElement(var31)
        };
        this.b = new ArrayList();
        this.c = false;
        this.d = 0.0F;
        this.e = 0.0F;
        this.f = -1.0F;
        this.g = -1.0F;
        this.h = 0;
        this.i = false;
        this.j = null;
        this.l = new long[k.length];
        this.m = new boolean[k.length];
        this.n = 0.0F;
        this.o = System.currentTimeMillis();
        RenderContract5.a.register((RenderContract6)(lpL399_0, lpL399_1) -> {
            if (RenderContract2.b.currentScreen == this) {
                Mouse var10 = RenderContract2.b.mouse;
                int var10003 = (int)Math.round(var10.getX() / RenderContract2.b.getWindow().getScaleFactor());
                int var10004 = (int)Math.round(var10.getY() / RenderContract2.b.getWindow().getScaleFactor());
                float var10005 = MatrixStack.a_float_______________(new Object[]{601921721859L});
                Object[] var10008 = new Object[]{null, null, null, null, null, 91519599449274L};
                var10008[4] = var10005;
                var10008[3] = var10004;
                var10008[2] = var10003;
                var10008[1] = ((dev.zprestige.prestige.data.MatrixCameraData)lpL399_1);
                var10008[0] = ((dev.zprestige.prestige.render.RenderHelper8)lpL399_0);
                this.a_void______(var10008);
            }
        });
    }

    @Override
    public void init() {
        Event.b();
        super.init();
        if (this.f == -1.0F && this.g == -1.0F) {
            float var8 = RenderContract2.b.getWindow().getFramebufferWidth() / b(new Object[]{99410855139333L});
            float var9 = RenderContract2.b.getWindow().getFramebufferHeight() / b(new Object[]{99410855139333L});
            this.f = var8 / 2.0F - 70.0F;
            this.g = var9 / 2.0F - this.c(new Object[0]) / 2.0F;
        }

        if (!this.i) {
            for (HudElement var11 : this.a) {
                var11.m(new Object[]{69450933719321L});
            }

            this.i = true;
        }

        if (RenderContract2.b.getWindow() != null) {
            long var13 = RenderContract2.b.getWindow().getHandle();

            for (int var15 = 0; var15 < k.length; var15++) {
                this.m[var15] = GLFW.glfwGetKey(var13, k[var15]) == 1;
                this.l[var15] = Long.MAX_VALUE;
            }
        }

        this.j = null;
        this.n = 0.0F;
        this.o = System.currentTimeMillis();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
    }

    public void a_void______(Object[] var1) {
        RenderHelper8 var2 = (RenderHelper8)var1[0];
        MatrixCameraData var7 = (MatrixCameraData)var1[1];
        int var5 = (Integer)var1[2];
        int var8 = (Integer)var1[3];
        long var3 = (Long)var1[5];
        var3 = 31417140128405L ^ var3;
        long var9 = var3 ^ 23947311064618L;
        long var11 = var3 ^ 35207067994649L;
        long var13 = var3 ^ 123126351294285L;
        long var15 = var3 ^ 96751337190027L;
        long var17 = var3 ^ 137940707372054L;
        long var19 = var3 ^ 87009244325932L;
        long var21 = var3 ^ 9256271514585L;
        long var23 = var3 ^ 90680546219022L;
        long var25 = var3 ^ 118761745651847L;
        long var27 = var3 ^ 21657020793807L;
        long var29 = var3 ^ 22429669519231L;
        long var31 = var3 ^ 71715086263030L;
        long var33 = var3 ^ 19745812531703L;
        long var35 = var3 ^ 110128994658108L;
        long var37 = var3 ^ 66208582980034L;
        long var39 = var3 ^ 86525055912814L;
        long var41 = var3 ^ 73003193376672L;
        long var43 = var3 ^ 121627841972431L;
        MatrixStack.g = var2;
        MatrixStack.h = var7;
        float var46 = RenderContract2.b.getWindow().getScaleFactor();
        float var47 = b(new Object[]{var9}) / var46;
        Event.b();
        float var48 = RenderContract2.b.getWindow().getFramebufferWidth() / b(new Object[]{var9});
        float var49 = RenderContract2.b.getWindow().getFramebufferHeight() / b(new Object[]{var9});
        float var50 = var5 * var46 / b(new Object[]{var9});
        float var51 = var8 * var46 / b(new Object[]{var9});
        if (this.f == -1.0F && this.g == -1.0F) {
            this.f = var48 / 2.0F - 70.0F;
            this.g = var49 / 2.0F - this.c(new Object[0]) / 2.0F;
        }

        float var52 = (float)(System.currentTimeMillis() - this.o) * 0.005F;
        this.o = System.currentTimeMillis();
        float var10001 = this.n;
        Object[] var10006 = new Object[]{null, null, null, var37};
        var10006[2] = var52;
        var10006[1] = 1.0F;
        var10006[0] = var10001;
        this.n = MathHelper4.b_float______(var10006);
        if (this.c) {
            var10001 = this.f;
            float var10002 = var50 + this.d;
            float var10003 = MatrixStack.a_float_______________(new Object[]{var19}) / 10.0F;
            var10006 = new Object[]{null, null, null, var37};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = var10001;
            this.f = MathHelper4.b_float______(var10006);
            var10001 = this.g;
            var10002 = var51 + this.e;
            var10003 = MatrixStack.a_float_______________(new Object[]{var19}) / 10.0F;
            var10006 = new Object[]{null, null, null, var37};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = var10001;
            this.g = MathHelper4.b_float______(var10006);
        }

        this.b.clear();
        this.b.add(new RenderHelper21(Axis.Y, 3));
        this.b.add(new RenderHelper21(Axis.X, 2));
        this.b.add(new RenderHelper21(Axis.Y, (int)(var49 - 3.0F)));
        this.b.add(new RenderHelper21(Axis.X, (int)(var48 - 3.0F)));
        this.b.add(new RenderHelper21(Axis.X, (int)(var48 / 2.0F)));
        this.b.add(new RenderHelper21(Axis.Y, (int)(var49 / 2.0F)));

        for (HudElement var56 : this.a) {
            if (var56.e_boolean(new Object[]{var41}) && !var56.a_boolean_____(new Object[0])) {
                var56.e_void(new Object[]{var33});
            }
        }

        for (HudElement var91 : this.a) {
            if (var91.e_boolean(new Object[]{var41}) && !var91.a_boolean_____(new Object[0]) && !(var91.g <= 0.0F)) {
                this.b.add(new RenderHelper21(Axis.X, (int)var91.e));
                this.b.add(new RenderHelper21(Axis.X, (int)(var91.e + var91.g)));
                this.b.add(new RenderHelper21(Axis.X, (int)(var91.e + var91.g / 2.0F)));
                this.b.add(new RenderHelper21(Axis.Y, (int)var91.f));
                this.b.add(new RenderHelper21(Axis.Y, (int)(var91.f + var91.h)));
                this.b.add(new RenderHelper21(Axis.Y, (int)(var91.f + var91.h / 2.0F)));
            }
        }

        this.b_void(new Object[]{var25});
        Matrix4f var86 = new Matrix4f().scale(var47, var47, 1.0F);
        float var88 = this.c(new Object[0]);
        Color var90 = MathHelper4.d(new Object[]{var11});
        float var92 = Prestige.c.a(new Object[]{var39}).c_float___(new Object[]{var21});
        Object[] var10008 = new Object[]{null, null, null, null, null, new Color(0, 0, 0, (int)(70.0F * this.n)), var27};
        var10008[4] = var49;
        var10008[3] = var48;
        var10008[2] = 0.0F;
        var10008[1] = 0.0F;
        var10008[0] = var86;
        MatrixStack.r(var10008);
        Color var58 = new Color(255, 255, 255, (int)(6.0F * this.n));

        for (float var59 = 20.0F; var59 < var48; var59 += 20.0F) {
            var10001 = var59 - 0.25F;
            float var131 = var59 + 0.25F;
            var10008 = new Object[]{null, null, null, null, null, var58, var27};
            var10008[4] = var49;
            var10008[3] = var131;
            var10008[2] = 0.0F;
            var10008[1] = var10001;
            var10008[0] = var86;
            MatrixStack.r(var10008);
        }

        for (float var93 = 20.0F; var93 < var49; var93 += 20.0F) {
            float var126 = var93 - 0.25F;
            float var10004 = var93 + 0.25F;
            var10008 = new Object[]{null, null, null, null, null, var58, var27};
            var10008[4] = var10004;
            var10008[3] = var48;
            var10008[2] = var126;
            var10008[1] = 0.0F;
            var10008[0] = var86;
            MatrixStack.r(var10008);
        }

        Color var94 = new Color(var90.getRed(), var90.getGreen(), var90.getBlue(), (int)(30.0F * this.n));
        short var10000 = 27662;
        float var127 = var48 / 2.0F - 0.25F;
        float var139 = var48 / 2.0F + 0.25F;
        Object[] var10009 = new Object[]{null, null, null, null, null, var94, var27};
        var10009[4] = var49;
        var10009[3] = var139;
        var10009[2] = 0.0F;
        var10009[1] = var127;
        var10009[0] = var86;
        MatrixStack.r(var10009);
        long var123 = 1003592114613291339L ^ var3;
        var139 = var49 / 2.0F - 0.25F;
        float var155 = var49 / 2.0F + 0.25F;
        Object[] var10010 = new Object[]{null, null, null, null, null, var94, var27};
        var10010[4] = var155;
        var10010[3] = var48;
        var10010[2] = var139;
        var10010[1] = 0.0F;
        var10010[0] = var86;
        MatrixStack.r(var10010);
        float var10005 = var48 - 3.0F;
        float var156 = var49 - 3.0F;
        Color var10007 = new Color(255, 255, 255, (int)(14.0F * this.n));
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, var15};
        var10011[6] = 0.0F;
        var10011[5] = var10007;
        var10011[4] = var156;
        var10011[3] = var10005;
        var10011[2] = 3.0F;
        var10011[1] = 2.0F;
        var10011[0] = var86;
        MatrixStack.l(var10011);
        var10005 = this.f;
        float var157 = this.g;
        Object[] var10013 = new Object[]{null, null, null, null, null, null, null, null, null, var13};
        var10013[8] = 4.0F;
        var10013[7] = 5;
        var10013[6] = var88;
        var10013[5] = 140.0F;
        var10013[4] = var157;
        var10013[3] = var10005;
        var10013[2] = var86;
        var10013[1] = var7;
        var10013[0] = var2;
        RenderTarget.e(var10013);
        var10005 = this.f;
        float var158 = this.g;
        Color var173 = new Color(NameTagsUtil.r.getRed(), NameTagsUtil.r.getGreen(), NameTagsUtil.r.getBlue(), (int)(NameTagsUtil.r.getAlpha() * this.n));
        var10013 = new Object[]{null, null, null, null, null, null, null, null, null, var31};
        var10013[8] = 4.0F;
        var10013[7] = var173;
        var10013[6] = var88;
        var10013[5] = 140.0F;
        var10013[4] = var158;
        var10013[3] = var10005;
        var10013[2] = var86;
        var10013[1] = var7;
        var10013[0] = var2;
        RoundedPosColorShader.c(var10013);
        float var132 = this.f;
        var139 = this.g;
        var10005 = this.f + 140.0F;
        float var159 = this.g + 24.0F;
        var10011 = new Object[]{null, null, null, null, null, new Color(24, 24, 30, (int)(235.0F * this.n)), new Vector4f(4.0F, 4.0F, 0.0F, 0.0F), var23};
        var10011[4] = var159;
        var10011[3] = var10005;
        var10011[2] = var139;
        var10011[1] = var132;
        var10011[0] = var86;
        RenderState.u(var10011);
        float var60 = (float)(Math.sin(System.currentTimeMillis() / 600.0) * 0.5 + 0.5);
        float var61 = 1.0F;
        var132 = this.f + 4.0F;
        var139 = this.g + 24.0F - var61;
        var10005 = this.f + 140.0F - 4.0F;
        float var160 = this.g + 24.0F;
        var10010 = new Object[]{
            null, null, null, null, null, new Color(var90.getRed(), var90.getGreen(), var90.getBlue(), (int)((150.0F + 90.0F * var60) * this.n)), var27
        };
        var10010[4] = var160;
        var10010[3] = var10005;
        var10010[2] = var139;
        var10010[1] = var132;
        var10010[0] = var86;
        MatrixStack.r(var10010);
        var132 = this.f;
        var139 = this.g;
        var10005 = this.f + 140.0F;
        float var161 = this.g + var88;
        var10007 = new Color(var90.getRed(), var90.getGreen(), var90.getBlue(), (int)(70.0F * this.n));
        var10011 = new Object[]{null, null, null, null, null, null, null, var15};
        var10011[6] = 4.0F;
        var10011[5] = var10007;
        var10011[4] = var161;
        var10011[3] = var10005;
        var10011[2] = var139;
        var10011[1] = var132;
        var10011[0] = var86;
        MatrixStack.l(var10011);
        String var62 = "HUD Editor";
        float var63 = Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var62, var35});
        Font var116 = Prestige.c.a(new Object[]{var39});
        var132 = this.f + 70.0F - var63 / 2.0F;
        var139 = this.g + 12.0F - var92 / 2.0F;
        var10008 = new Object[]{null, null, null, null, new Color(255, 255, 255, (int)(255.0F * this.n)), var43};
        var10008[3] = var139;
        var10008[2] = var132;
        var10008[1] = var62;
        var10008[0] = var86;
        var116.v(var10008);
        int var64 = 0;

        for (HudElement var68 : this.a) {
            if (var68.e_boolean(new Object[]{var41})) {
                var64++;
            }
        }

        String var95 = var64 + "/" + this.a.length;
        float var96 = Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var95, var35});
        Font var117 = Prestige.c.a(new Object[]{var39});
        var132 = this.f + 140.0F - 5.5F - var96;
        var139 = this.g + 12.0F - var92 / 2.0F;
        var10008 = new Object[]{
            null,
            null,
            null,
            null,
            new Color(NameTagsUtil.u.getRed(), NameTagsUtil.u.getGreen(), NameTagsUtil.u.getBlue(), (int)(NameTagsUtil.u.getAlpha() * this.n)),
            var43
        };
        var10008[3] = var139;
        var10008[2] = var132;
        var10008[1] = var95;
        var10008[0] = var86;
        var117.v(var10008);
        float var97 = this.f + 5.5F;
        float var98 = this.g + 24.0F + 3.0F;

        for (HudElement var72 : this.a) {
            int var162 = (int)var50;
            int var164 = (int)var51;
            int var170 = this.h;
            Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, this.b, var29};
            var10012[7] = var170;
            var10012[6] = var164;
            var10012[5] = var162;
            var10012[4] = 17.0F;
            var10012[3] = 129.0F;
            var10012[2] = var98;
            var10012[1] = var97;
            var10012[0] = var86;
            var72.n(var10012);
            var98 += 20.0F;
        }

        for (HudElement var105 : this.a) {
            if (var105.e_boolean(new Object[]{var41})) {
                var105.q(new Object[]{var86, var17});
            }
        }

        if (this.j != null && this.j.e_boolean(new Object[]{var41})) {
            var10001 = this.j.e - 1.5F;
            var127 = this.j.f - 1.5F;
            var132 = this.j.e + this.j.g + 1.5F;
            var139 = this.j.f + this.j.h + 1.5F;
            Color var152 = new Color(var90.getRed(), var90.getGreen(), var90.getBlue(), (int)(200.0F * this.n));
            var10009 = new Object[]{null, null, null, null, null, null, null, var15};
            var10009[6] = 0.0F;
            var10009[5] = var152;
            var10009[4] = var139;
            var10009[3] = var132;
            var10009[2] = var127;
            var10009[1] = var10001;
            var10009[0] = var86;
            MatrixStack.l(var10009);
        }

        String[][] var100 = new String[][]{
            {"Click", "toggle"}, {"Drag", "move"}, {"Shift", "free move / big nudge"}, {"Arrows", "nudge"}, {"R", "reset layout"}
        };
        String var102 = "   ";
        float var104 = Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var102, var35});
        float var106 = var104 * (var100.length - 1);

        for (String[] var76 : var100) {
            var106 += Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var76[0], var35})
                + Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{" = " + var76[1], var35});
        }

        if (var106 + 16.0F > var48 - 8.0F) {
            var100 = new String[][]{{"Drag", "move"}, {"Arrows", "nudge"}, {"R", "reset"}};
            var106 = var104 * (var100.length - 1);

            for (String[] var113 : var100) {
                var106 += Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var113[0], var35})
                    + Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{" = " + var113[1], var35});
            }
        }

        float var108 = 8.0F;
        Object[] var138 = new Object[]{null, 1.0F};
        var138[0] = var92;
        float var110 = NameTagsUtil.a(var138);
        float var112 = var106 + var108 * 2.0F;
        float var114 = Math.max(4.0F, var48 / 2.0F - var112 / 2.0F);
        float var77 = var49 - var110 - 8.0F;
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var13};
        var10011[8] = 4.0F;
        var10011[7] = 5;
        var10011[6] = var110;
        var10011[5] = var112;
        var10011[4] = var77;
        var10011[3] = var114;
        var10011[2] = var86;
        var10011[1] = var7;
        var10011[0] = var2;
        RenderTarget.e(var10011);
        var10007 = new Color(NameTagsUtil.r.getRed(), NameTagsUtil.r.getGreen(), NameTagsUtil.r.getBlue(), (int)(NameTagsUtil.r.getAlpha() * this.n));
        var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var31};
        var10011[8] = 4.0F;
        var10011[7] = var10007;
        var10011[6] = var110;
        var10011[5] = var112;
        var10011[4] = var77;
        var10011[3] = var114;
        var10011[2] = var86;
        var10011[1] = var7;
        var10011[0] = var2;
        RoundedPosColorShader.c(var10011);
        Color var78 = new Color(var90.getRed(), var90.getGreen(), var90.getBlue(), (int)(255.0F * this.n));
        Color var79 = new Color(NameTagsUtil.t.getRed(), NameTagsUtil.t.getGreen(), NameTagsUtil.t.getBlue(), (int)(230.0F * this.n));
        float var80 = var114 + var108;
        float var81 = var77 + (var110 - var92) / 2.0F;

        for (int var82 = 0; var82 < var100.length; var82++) {
            Font var118 = Prestige.c.a(new Object[]{var39});
            String var129 = var100[var82][0];
            var10008 = new Object[]{null, null, null, null, var78, var43};
            var10008[3] = var81;
            var10008[2] = var80;
            var10008[1] = var129;
            var10008[0] = var86;
            var118.v(var10008);
            var80 += Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var100[var82][0], var35});
            String var83 = " = " + var100[var82][1];
            Font var119 = Prestige.c.a(new Object[]{var39});
            var10008 = new Object[]{null, null, null, null, var79, var43};
            var10008[3] = var81;
            var10008[2] = var80;
            var10008[1] = var83;
            var10008[0] = var86;
            var119.v(var10008);
            var80 += Prestige.c.a(new Object[]{var39}).a_float______________(new Object[]{var83, var35});
            if (var82 < var100.length - 1) {
                var80 += var104;
            }
        }

        this.h = 0;
    }

    private float c(Object[] var1) {
        return 24.0F + this.a.length * 20.0F + 6.0F;
    }

    private void b_void(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 31417140128405L ^ var2;
        long var4 = var2 ^ 62807894497494L;
        long var6 = var2 ^ 56027340549554L;
        long var8 = var2 ^ 111554402923866L;
        Event.b();
        long var11 = RenderContract2.b.getWindow().getHandle();
        long var13 = System.currentTimeMillis();
        boolean var15 = GLFW.glfwGetKey(var11, 340) == 1 || GLFW.glfwGetKey(var11, 344) == 1;

        for (int var16 = 0; var16 < k.length; var16++) {
            int var17 = k[var16];
            boolean var18 = GLFW.glfwGetKey(var11, var17) == 1;
            boolean var19 = var18 && !this.m[var16];
            boolean var20 = false;
            if (var19) {
                var20 = true;
                this.l[var16] = var13 + 300L;
            } else if (var18 && var13 >= this.l[var16]) {
                var20 = true;
                this.l[var16] = var13 + 45L;
            }

            this.m[var16] = var18;
            if (var20) {
                if (var17 == 82) {
                    if (var19) {
                        this.c_void(new Object[]{var4});
                    }
                } else if (this.j != null && this.j.e_boolean(new Object[]{var6})) {
                    float var21 = var15 ? 10.0F : 1.0F;
                    switch (var17) {
                        case 262:
                            HudElement var25 = this.j;
                            Object[] var28 = new Object[]{null, null, var8};
                            var28[1] = 0.0F;
                            var28[0] = var21;
                            var25.h(var28);
                            break;
                        case 263:
                            HudElement var24 = this.j;
                            float var10001 = -var21;
                            Object[] var27 = new Object[]{null, null, var8};
                            var27[1] = 0.0F;
                            var27[0] = var10001;
                            var24.h(var27);
                            break;
                        case 264:
                            HudElement var23 = this.j;
                            Object[] var26 = new Object[]{null, null, var8};
                            var26[1] = var21;
                            var26[0] = 0.0F;
                            var23.h(var26);
                            break;
                        case 265:
                            HudElement var10000 = this.j;
                            float var10002 = -var21;
                            Object[] var10005 = new Object[]{null, null, var8};
                            var10005[1] = var10002;
                            var10005[0] = 0.0F;
                            var10000.h(var10005);
                    }
                }
            }
        }
    }

    private void c_void(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 31417140128405L ^ var2;
        long var4 = var2 ^ 41338085240679L;
        Event.b();

        for (HudElement var10 : this.a) {
            var10.m(new Object[]{var4});
        }

        this.j = null;
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        double var14 = click.x();
        Event.b();
        double var16 = click.y();
        int var18 = click.button();
        if (this.f == -1.0F && this.g == -1.0F) {
            this.init();
        }

        double var19 = RenderContract2.b.getWindow().getScaleFactor();
        double var21 = var14 * var19 / b(new Object[]{99410855139333L});
        double var23 = var16 * var19 / b(new Object[]{99410855139333L});
        if (var18 == 0) {
            int var10001 = (int)var21;
            int var10002 = (int)var23;
            Object[] var10005 = new Object[]{null, null, 57321394020433L};
            var10005[1] = var10002;
            var10005[0] = var10001;
            if (this.a_boolean____(var10005)) {
                this.d = (float)(this.f - var21);
                this.e = (float)(this.g - var23);
                this.c = true;
            }

            boolean var25 = var21 >= this.f && var21 <= this.f + 140.0F && var23 >= this.g && var23 <= this.g + this.c(new Object[0]);
            if (!var25) {
                for (HudElement var29 : this.a) {
                    if (var29.e_boolean(new Object[]{15166320517519L})) {
                        var10001 = (int)var21;
                        var10002 = (int)var23;
                        Object[] var10006 = new Object[]{null, null, null, 45913355491120L};
                        var10006[2] = false;
                        var10006[1] = var10002;
                        var10006[0] = var10001;
                        var29.o(var10006);
                    }
                }

                for (HudElement var33 : this.a) {
                    if (var33.e_boolean(new Object[]{15166320517519L}) && var33.a_boolean_____(new Object[0])) {
                        this.j = var33;
                    }
                }
            }

            this.h = 1;
        }

        return false;
    }

    @Override
    public boolean mouseReleased(Click click) {
        Event.b();
        double var11 = click.x();
        double var13 = click.y();
        int var15 = click.button();
        double var16 = RenderContract2.b.getWindow().getScaleFactor();
        double var18 = var11 * var16 / b(new Object[]{99410855139333L});
        double var20 = var13 * var16 / b(new Object[]{99410855139333L});
        if (var15 == 0) {
            this.c = false;

            for (HudElement var25 : this.a) {
                if (var25.e_boolean(new Object[]{15166320517519L})) {
                    int var10001 = (int)var18;
                    int var10002 = (int)var20;
                    Object[] var10006 = new Object[]{null, null, null, 45913355491120L};
                    var10006[2] = true;
                    var10006[1] = var10002;
                    var10006[0] = var10001;
                    var25.o(var10006);
                }
            }
        }

        return false;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        RenderContract2.b.setScreen(Prestige.k);
        Prestige.k.a(new Object[0]);
        return false;
    }

    private boolean a_boolean____(Object[] var1) {
        int var5 = (Integer)var1[0];
        int var4 = (Integer)var1[1];
        return var5 >= this.f && var5 <= this.f + 140.0F && var4 >= this.g && var4 <= this.g + 24.0F;
    }

    public boolean b_boolean___(Object[] var1) {
        RenderHelper8 var3 = (RenderHelper8)var1[0];
        MatrixCameraData var2 = (MatrixCameraData)var1[1];
        long var4 = (Long)var1[2];
        var4 = 31417140128405L ^ var4;
        long var6 = var4 ^ 50935960504888L;
        long var8 = var4 ^ 3143769832011L;
        long var10 = var4 ^ 46599171040741L;
        long var12 = var4 ^ 132294499628091L;
        long var14 = var4 ^ 82535738229540L;
        long var16 = var4 ^ 134079085654962L;
        if (RenderContract2.b.currentScreen != null
            && !(RenderContract2.b.currentScreen instanceof HudEditorScreen)
            && !(RenderContract2.b.currentScreen instanceof ChatScreen)) {
            return false;
        } else if (!this.d(new Object[]{var12})) {
            return false;
        } else {
            float var19 = RenderContract2.b.getWindow().getScaleFactor();
            float var20 = b(new Object[]{var6}) / var19;
            Matrix4f var21 = new Matrix4f().scale(var20, var20, 1.0F);
            if (!this.i) {
                for (HudElement var25 : this.a) {
                    var25.m(new Object[]{var14});
                }

                this.i = true;
            }

            boolean var28 = false;

            for (HudElement var26 : this.a) {
                if (var26.e_boolean(new Object[]{var16})) {
                    if (!var26.a_boolean_____(new Object[0])) {
                        var26.e_void(new Object[]{var10});
                    }

                    var26.l(new Object[]{var3, var2, var21, var8});
                    var28 = true;
                }
            }

            return var28;
        }
    }

    public boolean c_boolean_(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 31417140128405L ^ var2;
        long var4 = var2 ^ 99372171748780L;
        if (RenderContract2.b.currentScreen == this) {
            return true;
        } else {
            return RenderContract2.b.currentScreen != null && !(RenderContract2.b.currentScreen instanceof ChatScreen) ? false : this.d(new Object[]{var4});
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 31417140128405L ^ var2;
        long var4 = var2 ^ 32097659546908L;
        HudElement[] var7 = this.a;
        Event.b();

        for (HudElement var10 : var7) {
            if (var10.e_boolean(new Object[]{var4})) {
                return true;
            }
        }

        return false;
    }

    public HudElement[] a_HudElementArr(Object[] var1) {
        return this.a;
    }

    private static Field a(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field b(Class var0, String var1, Class var2) {
        Field var3 = a(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = b(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = a(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = b(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
