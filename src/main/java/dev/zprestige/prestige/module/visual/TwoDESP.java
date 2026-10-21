package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.TwoDESPHelper;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent22;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.math.Point3D;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.render.Frustum;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class TwoDESP extends Module {
    private BoolSetting d;
    private EnumSetting a;
    private FloatSetting c;
    private FloatSetting e;
    private MultiSetting f;
    private FloatSetting g;
    private FloatSetting h;
    private BoolSetting i;
    private FloatSetting j;
    private EnumSetting k;
    private BoolSetting l;
    private BoolSetting m;
    private FloatSetting n;
    private IntSetting o;
    private IntSetting p;
    private BoolSetting q;
    private ColorSetting r;
    private ColorSetting s;
    private ColorSetting t;
    private ColorSetting u;
    private ColorSetting v;
    private Frustum w;
    private long x = 0L;
    private long y = 0L;
    private Map z = new LinkedHashMap();
    private Map A = new HashMap();

    public TwoDESP() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.l
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL350_0 -> this.f.isSelected(new Object[]{"Health Bar", 47850572908778L}), 73196168581295L}
            );
        this.u
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL346_0 -> this.f.isSelected(new Object[]{"Health Bar", 47850572908778L}), 119980880492450L}
            );
        this.m
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL349_0 -> this.f.isSelected(new Object[]{"Health Bar", 47850572908778L}), 73196168581295L}
            );
        this.j
            .setVisibility_FloatSetting(
                new Object[]{
                    (Predicate<Float>)lpL352_0 -> this.f.isSelected(new Object[]{"Gradient", 47850572908778L}) && (Boolean)this.i.getValue(), 35046756052036L
                }
            );
        this.k
            .setVisibility_EnumSetting(
                new Object[]{(Predicate<String>)lpL351_0 -> this.f.isSelected(new Object[]{"Health Bar", 47850572908778L}), 90319707064974L}
            );
        this.g
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL355_0 -> this.f.isSelected(new Object[]{"Fill", 47850572908778L}), 35046756052036L});
        this.i
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL353_0 -> this.f.isSelected(new Object[]{"Gradient", 47850572908778L}), 73196168581295L}
            );
        this.r.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL348_0 -> !(Boolean)this.q.getValue(), 119980880492450L});
        this.s
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL347_0 -> this.f.isSelected(new Object[]{"Gradient", 47850572908778L}), 119980880492450L}
            );
        this.v
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL345_0 -> this.f.isSelected(new Object[]{"Health Bar", 47850572908778L}), 119980880492450L}
            );
        this.h
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL354_0 -> this.f.isSelected(new Object[]{"Glow", 47850572908778L}), 35046756052036L});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            this.z.clear();
            int var9 = (Integer)this.o.getValue();
            boolean var10 = (Boolean)this.d.getValue();
            ArrayList var11 = new ArrayList();

            for (PlayerEntity var13 : b.world.getPlayers()) {
                if (var13 != b.player && Prestige.i.a_boolean_(new Object[]{var13, 54363558999103L}) && !var13.isRemoved()) {
                    float var14 = b.player.distanceTo(var13);
                    if (!(var14 > var9) && (var10 || !var13.isInvisible())) {
                        TwoDESPHelper var15 = new TwoDESPHelper();
                        var15.a = var13;
                        var15.b = var13.getName().getString();
                        var15.c = var13.getHealth() + var13.getAbsorptionAmount();
                        var15.d = Math.max(1.0F, var13.getMaxHealth());
                        var15.e = var13.getWidth();
                        var15.f = var13.getHeight();
                        var15.g = var14;
                        var15.h = Prestige.g.a(new Object[]{var15.b, 24848952222090L});
                        var11.add(var15);
                    }
                }
            }

            var11.sort(Comparator.comparingDouble(lpL344_0 -> ((dev.zprestige.prestige.combat.TwoDESPHelper)lpL344_0).g));

            for (int var16 = 0; var16 < var11.size() && var16 < this.p.getValue(); var16++) {
                TwoDESPHelper var17 = (TwoDESPHelper)var11.get(var16);
                this.z.put(var17.a.getUuid(), var17);
            }
        }
    }

    @Subscribe
    public void a(TickEvent22 var1) {
        this.w = var1.a(new Object[0]);
    }

    @Subscribe
    public void a(TickEvent25 var1) {
        if (b.world != null && b.player != null) {
            if (b.currentScreen == null || b.currentScreen instanceof ChatScreen) {
                this.y++;
                long var19 = System.currentTimeMillis();
                float var21 = this.x == 0L ? 0.0F : Math.min(0.1F, (float)(var19 - this.x) / 1000.0F);
                this.x = var19;
                float var22 = 1.0F - (float)Math.exp(-var21 * (Float)this.n.getValue());
                int var23 = b.getWindow().getScaledWidth();
                int var24 = b.getWindow().getScaledHeight();
                Font var25 = Prestige.c.a(new Object[]{1236438218049L});
                Matrix4f var26 = new Matrix4f();
                Color var27 = this.q.getValue()
                    ? (Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue()
                    : (Color)this.r.getValue();
                Color var28 = (Color)this.s.getValue();
                float var29 = this.f.isSelected(new Object[]{"Gradient", 47850572908778L}) && this.i.getValue()
                    ? (float)((Math.sin(var19 / 1000.0 * ((Float)this.j.getValue()).floatValue()) + 1.0) * 0.5)
                    : 0.0F;

                for (Entry var31 : (Iterable<Entry>)(this.z.entrySet())) {
                    UUID var32 = (UUID)var31.getKey();
                    TwoDESPHelper var33 = (TwoDESPHelper)var31.getValue();
                    PlayerEntity var34 = var33.a;
                    if (var34 != null && !var34.isRemoved() && (this.w == null || this.w.isVisible(var34.getBoundingBox()))) {
                        Vec3d var35 = MatrixStack.b(new Object[]{var34, 78245126833881L});
                        float var10002 = var33.e;
                        float var10003 = var33.f;
                        Object[] var10006 = new Object[]{null, null, null, 134641612236124L};
                        var10006[2] = var10003;
                        var10006[1] = var10002;
                        var10006[0] = var35;
                        float[] var36 = this.a(var10006);
                        if (var36 != null) {
                            float var10000 = var36[0];
                            var10002 = var23 + 20;
                            Object[] var10005 = new Object[]{null, null, null, 90144406875187L};
                            var10005[2] = var10002;
                            var10005[1] = -20.0F;
                            var10005[0] = var10000;
                            float var37 = d(var10005);
                            var10000 = var36[1];
                            var10002 = var24 + 20;
                            var10005 = new Object[]{null, null, null, 90144406875187L};
                            var10005[2] = var10002;
                            var10005[1] = -20.0F;
                            var10005[0] = var10000;
                            float var38 = d(var10005);
                            var10000 = var36[2];
                            var10002 = var23 + 20;
                            var10005 = new Object[]{null, null, null, 90144406875187L};
                            var10005[2] = var10002;
                            var10005[1] = -20.0F;
                            var10005[0] = var10000;
                            float var39 = d(var10005);
                            var10000 = var36[3];
                            var10002 = var24 + 20;
                            var10005 = new Object[]{null, null, null, 90144406875187L};
                            var10005[2] = var10002;
                            var10005[1] = -20.0F;
                            var10005[0] = var10000;
                            float var40 = d(var10005);
                            if (!(var39 - var37 < 3.0F) && !(var40 - var38 < 3.0F)) {
                                Point3D var41 = (Point3D)this.A.get(var32);
                                if (var41 == null) {
                                    var41 = new Point3D();
                                    var41.e = 0.0F;
                                    this.A.put(var32, var41);
                                }

                                var41.a = var37;
                                var41.b = var38;
                                var41.c = var39;
                                var41.d = var40;
                                float var10001 = var41.e;
                                var10006 = new Object[]{null, null, null, 42082820314752L};
                                var10006[2] = var22;
                                var10006[1] = 1.0F;
                                var10006[0] = var10001;
                                var41.e = b_float___________________(var10006);
                                var41.f = this.y;
                                var41.g = var33;
                                RenderHelper8 var48 = var1.a;
                                MatrixCameraData var55 = var1.b;
                                Object[] var10013 = new Object[]{null, null, null, null, null, null, null, null, null, null, 78522822944826L};
                                var10013[9] = var19;
                                var10013[8] = var29;
                                var10013[7] = var28;
                                var10013[6] = var27;
                                var10013[5] = var41;
                                var10013[4] = var33;
                                var10013[3] = var25;
                                var10013[2] = var26;
                                var10013[1] = var55;
                                var10013[0] = var48;
                                this.j(var10013);
                            }
                        }
                    }
                }

                Iterator var42 = this.A.entrySet().iterator();

                while (var42.hasNext()) {
                    Entry var43 = (Entry)var42.next();
                    Point3D var44 = (Point3D)var43.getValue();
                    if (var44.f != this.y) {
                        float var49 = var44.e;
                        Object[] var62 = new Object[]{null, null, null, 42082820314752L};
                        var62[2] = var22;
                        var62[1] = 0.0F;
                        var62[0] = var49;
                        var44.e = b_float___________________(var62);
                        if (!(var44.e < 0.02F) && var44.g != null) {
                            RenderHelper8 var50 = var1.a;
                            MatrixCameraData var56 = var1.b;
                            TwoDESPHelper var60 = var44.g;
                            Object[] var63 = new Object[]{null, null, null, null, null, null, null, null, null, null, 78522822944826L};
                            var63[9] = var19;
                            var63[8] = var29;
                            var63[7] = var28;
                            var63[6] = var27;
                            var63[5] = var44;
                            var63[4] = var60;
                            var63[3] = var25;
                            var63[2] = var26;
                            var63[1] = var56;
                            var63[0] = var50;
                            this.j(var63);
                        } else {
                            var42.remove();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        this.A.clear();
        this.z.clear();
        this.x = 0L;
    }

    private void j(Object[] var1) {
        RenderHelper8 var3 = (RenderHelper8)var1[0];
        MatrixCameraData var14 = (MatrixCameraData)var1[1];
        Matrix4f var4 = (Matrix4f)var1[2];
        Font var5 = (Font)var1[3];
        TwoDESPHelper var13 = (TwoDESPHelper)var1[4];
        Point3D var2 = (Point3D)var1[5];
        Color var10 = (Color)var1[6];
        Color var12 = (Color)var1[7];
        float var11 = (Float)var1[8];
        long var6 = (Long)var1[9];
        long var8 = (Long)var1[10];
        var8 = 5437566087809L ^ var8;
        long var15 = var8 ^ 70499333495722L;
        long var17 = var8 ^ 57838912502400L;
        long var19 = var8 ^ 134877643836561L;
        long var21 = var8 ^ 49658717802141L;
        long var23 = var8 ^ 88513148419423L;
        long var25 = var8 ^ 71379644903388L;
        long var27 = var8 ^ 107093311527222L;
        long var29 = var8 ^ 102121887172249L;
        long var31 = var8 ^ 136938965861949L;
        long var33 = var8 ^ 114476324000337L;
        long var35 = var8 ^ 9040879832580L;
        long var37 = var8 ^ 130621728408040L;
        Event.b();
        float var10001 = var2.e;
        Object[] var10004 = new Object[]{null, var15};
        var10004[0] = var10001;
        float var40 = c_float__(var10004);
        if (!(var40 < 0.02F)) {
            float var41 = var2.a;
            float var42 = var2.b;
            float var43 = var2.c;
            float var44 = var2.d;
            float var45 = var43 - var41;
            float var46 = var44 - var42;
            Color var47 = var13.h ? (Color)this.t.getValue() : var10;
            boolean var48 = this.f.isSelected(new Object[]{"Gradient", var33});
            Color var49 = var47;
            Color var50 = var48 && !var13.h ? var12 : var47;
            if (var48 && (Boolean)this.i.getValue() && !var13.h) {
                Object[] var10005 = new Object[]{null, null, null, var27};
                var10005[2] = var11;
                var10005[1] = var12;
                var10005[0] = var47;
                Color var51 = b_Color____(var10005);
                var10005 = new Object[]{null, null, null, var27};
                var10005[2] = var11;
                var10005[1] = var47;
                var10005[0] = var12;
                Color var52 = b_Color____(var10005);
                var49 = var51;
                var50 = var52;
            }

            var10004 = new Object[]{null, null, var31};
            var10004[1] = var40;
            var10004[0] = var49;
            Color var58 = a_Color______(var10004);
            var10004 = new Object[]{null, null, var31};
            var10004[1] = var40;
            var10004[0] = var50;
            Color var59 = a_Color______(var10004);
            float var53 = (Float)this.e.getValue();
            float var54 = Math.min((Float)this.c.getValue(), Math.min(var45, var46) * 0.4F);
            if (var54 < 0.0F) {
                var54 = 0.0F;
            }

            if (this.f.isSelected(new Object[]{"Glow", var33})) {
                Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var17};
                var10011[7] = var54;
                var10011[6] = var40;
                var10011[5] = var47;
                var10011[4] = var44;
                var10011[3] = var43;
                var10011[2] = var42;
                var10011[1] = var41;
                var10011[0] = var4;
                this.o(var10011);
            }

            if (this.f.isSelected(new Object[]{"Fill", var33})) {
                var10001 = var40 * (Float)this.g.getValue();
                var10004 = new Object[]{null, null, var31};
                var10004[1] = var10001;
                var10004[0] = var47;
                Color var55 = a_Color______(var10004);
                Object[] var10009 = new Object[]{null, null, null, null, null, null, null, var35};
                var10009[6] = var54;
                var10009[5] = var55;
                var10009[4] = var44;
                var10009[3] = var43;
                var10009[2] = var42;
                var10009[1] = var41;
                var10009[0] = var4;
                MatrixStack.n(var10009);
            }

            String var60 = (String)this.a.getValue();
            switch (var60) {
                case "Box":
                    Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, var37};
                    var10012[8] = var54;
                    var10012[7] = var53;
                    var10012[6] = var59;
                    var10012[5] = var58;
                    var10012[4] = var44;
                    var10012[3] = var43;
                    var10012[2] = var42;
                    var10012[1] = var41;
                    var10012[0] = var4;
                    this.k(var10012);
                    break;
                case "Corners":
                    Object[] var69 = new Object[]{null, null, null, null, null, null, null, null, var21};
                    var69[7] = var53;
                    var69[6] = var59;
                    var69[5] = var58;
                    var69[4] = var44;
                    var69[3] = var43;
                    var69[2] = var42;
                    var69[1] = var41;
                    var69[0] = var4;
                    this.l(var69);
                    break;
                case "Brackets":
                    Object[] var68 = new Object[]{null, null, null, null, null, null, null, null, var19};
                    var68[7] = var53;
                    var68[6] = var59;
                    var68[5] = var58;
                    var68[4] = var44;
                    var68[3] = var43;
                    var68[2] = var42;
                    var68[1] = var41;
                    var68[0] = var4;
                    this.m(var68);
            }

            if (this.f.isSelected(new Object[]{"Health Bar", var33})) {
                float var10007 = var13.c;
                float var10008 = var13.d;
                Object[] var10013 = new Object[]{null, null, null, null, null, null, null, null, null, null, var25};
                var10013[9] = var6;
                var10013[8] = var40;
                var10013[7] = var10008;
                var10013[6] = var10007;
                var10013[5] = var44;
                var10013[4] = var43;
                var10013[3] = var42;
                var10013[2] = var41;
                var10013[1] = var5;
                var10013[0] = var4;
                this.q(var10013);
            }

            if (this.f.isSelected(new Object[]{"Name Tag", var33})) {
                String var66 = var13.b;
                Object[] var70 = new Object[]{null, null, null, null, null, null, null, null, null, var29};
                var70[8] = var40;
                var70[7] = var66;
                var70[6] = var43;
                var70[5] = var42;
                var70[4] = var41;
                var70[3] = var5;
                var70[2] = var4;
                var70[1] = var14;
                var70[0] = var3;
                this.r(var70);
            }

            if (this.f.isSelected(new Object[]{"Distance", var33})) {
                float var67 = var13.g;
                Object[] var71 = new Object[]{null, null, null, null, null, null, null, null, null, var23};
                var71[8] = var40;
                var71[7] = var67;
                var71[6] = var43;
                var71[5] = var44;
                var71[4] = var41;
                var71[3] = var5;
                var71[2] = var4;
                var71[1] = var14;
                var71[0] = var3;
                this.s(var71);
            }
        }
    }

    private void k(Object[] var1) {
        Matrix4f var9 = (Matrix4f)var1[0];
        float var2 = (Float)var1[1];
        float var3 = (Float)var1[2];
        float var5 = (Float)var1[3];
        float var4 = (Float)var1[4];
        Color var12 = (Color)var1[5];
        Color var11 = (Color)var1[6];
        float var10 = (Float)var1[7];
        float var6 = (Float)var1[8];
        long var7 = (Long)var1[9];
        var7 = 5437566087809L ^ var7;
        long var13 = var7 ^ 69984260505063L;
        long var15 = var7 ^ 21983777775710L;
        Color var17 = c(new Object[]{var12, var11, var13});
        Color var18 = new Color(0, 0, 0, Math.round(80.0F * (var17.getAlpha() / 255.0F)));
        float var10001 = var2 - 0.5F;
        float var10002 = var3 - 0.5F;
        float var10003 = var5 + 0.5F;
        float var10004 = var4 + 0.5F;
        float var10006 = var6 + 0.5F;
        Object[] var10010 = new Object[]{null, null, null, null, null, null, null, null, var15};
        var10010[7] = 1.0F;
        var10010[6] = var10006;
        var10010[5] = var18;
        var10010[4] = var10004;
        var10010[3] = var10003;
        var10010[2] = var10002;
        var10010[1] = var10001;
        var10010[0] = var9;
        p(var10010);
        var10010 = new Object[]{null, null, null, null, null, null, null, null, var15};
        var10010[7] = var10;
        var10010[6] = var6;
        var10010[5] = var17;
        var10010[4] = var4;
        var10010[3] = var5;
        var10010[2] = var3;
        var10010[1] = var2;
        var10010[0] = var9;
        p(var10010);
    }

    private void l(Object[] var1) {
        Matrix4f var9 = (Matrix4f)var1[0];
        float var2 = (Float)var1[1];
        float var7 = (Float)var1[2];
        float var11 = (Float)var1[3];
        float var3 = (Float)var1[4];
        Color var10 = (Color)var1[5];
        Color var8 = (Color)var1[6];
        float var6 = (Float)var1[7];
        long var4 = (Long)var1[8];
        var4 = 5437566087809L ^ var4;
        long var12 = var4 ^ 6866449549507L;
        long var14 = var4 ^ 65682793450132L;
        float var16 = var11 - var2;
        float var17 = var3 - var7;
        float var10000 = Math.min(var16, var17) * 0.28F;
        Object[] var10005 = new Object[]{null, null, null, var14};
        var10005[2] = 22.0F;
        var10005[1] = 4.0F;
        var10005[0] = var10000;
        float var18 = d(var10005);
        float var19 = var6 * 0.5F;
        Color var20 = new Color(0, 0, 0, Math.round(120.0F * (var10.getAlpha() / 255.0F)));
        float var10002 = var2 - var19;
        float var10003 = var7 - var19;
        float var10004 = var2 + var18;
        float var43 = var7 + var19;
        Object[] var10010 = new Object[]{null, null, null, null, null, var10, var20, var12};
        var10010[4] = var43;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
        var10002 = var2 - var19;
        var10003 = var7 - var19;
        var10004 = var2 + var19;
        float var44 = var7 + var18;
        var10010 = new Object[]{null, null, null, null, null, var10, var20, var12};
        var10010[4] = var44;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
        var10002 = var11 - var18;
        var10003 = var7 - var19;
        var10004 = var11 + var19;
        float var45 = var7 + var19;
        var10010 = new Object[]{null, null, null, null, null, var10, var20, var12};
        var10010[4] = var45;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
        var10002 = var11 - var19;
        var10003 = var7 - var19;
        var10004 = var11 + var19;
        float var46 = var7 + var18;
        var10010 = new Object[]{null, null, null, null, null, var10, var20, var12};
        var10010[4] = var46;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
        var10002 = var2 - var19;
        var10003 = var3 - var19;
        var10004 = var2 + var18;
        float var47 = var3 + var19;
        var10010 = new Object[]{null, null, null, null, null, var8, var20, var12};
        var10010[4] = var47;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
        var10002 = var2 - var19;
        var10003 = var3 - var18;
        var10004 = var2 + var19;
        float var48 = var3 + var19;
        var10010 = new Object[]{null, null, null, null, null, var8, var20, var12};
        var10010[4] = var48;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
        var10002 = var11 - var18;
        var10003 = var3 - var19;
        var10004 = var11 + var19;
        float var49 = var3 + var19;
        var10010 = new Object[]{null, null, null, null, null, var8, var20, var12};
        var10010[4] = var49;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
        var10002 = var11 - var19;
        var10003 = var3 - var18;
        var10004 = var11 + var19;
        float var50 = var3 + var19;
        var10010 = new Object[]{null, null, null, null, null, var8, var20, var12};
        var10010[4] = var50;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var9;
        this.n(var10010);
    }

    private void m(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        float var6 = (Float)var1[1];
        float var10 = (Float)var1[2];
        float var7 = (Float)var1[3];
        float var11 = (Float)var1[4];
        Color var3 = (Color)var1[5];
        Color var4 = (Color)var1[6];
        float var5 = (Float)var1[7];
        long var8 = (Long)var1[8];
        var8 = 5437566087809L ^ var8;
        long var12 = var8 ^ 56905653874846L;
        long var14 = var8 ^ 89880989911759L;
        long var16 = var8 ^ 119010383546520L;
        float var18 = var11 - var10;
        float var10000 = var18 * 0.35F;
        Object[] var10005 = new Object[]{null, null, null, var16};
        var10005[2] = 26.0F;
        var10005[1] = 5.0F;
        var10005[0] = var10000;
        float var19 = d(var10005);
        float var20 = var5 * 0.5F;
        float var21 = (var10 + var11) * 0.5F;
        Color var22 = new Color(0, 0, 0, Math.round(120.0F * (var3.getAlpha() / 255.0F)));
        float var10002 = var6 - var20;
        float var10003 = var10 - var20;
        float var10004 = var6 + var20;
        float var39 = var10 + var19;
        Object[] var10010 = new Object[]{null, null, null, null, null, var3, var22, var14};
        var10010[4] = var39;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var2;
        this.n(var10010);
        var10002 = var6 - var20;
        var10003 = var21 - var19 * 0.4F;
        var10004 = var6 + var20;
        float var40 = var21 + var19 * 0.4F;
        var10010 = new Object[]{null, null, null, null, null, c(new Object[]{var3, var4, var12}), var22, var14};
        var10010[4] = var40;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var2;
        this.n(var10010);
        var10002 = var6 - var20;
        var10003 = var11 - var19;
        var10004 = var6 + var20;
        float var41 = var11 + var20;
        var10010 = new Object[]{null, null, null, null, null, var4, var22, var14};
        var10010[4] = var41;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var2;
        this.n(var10010);
        var10002 = var7 - var20;
        var10003 = var10 - var20;
        var10004 = var7 + var20;
        float var42 = var10 + var19;
        var10010 = new Object[]{null, null, null, null, null, var3, var22, var14};
        var10010[4] = var42;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var2;
        this.n(var10010);
        var10002 = var7 - var20;
        var10003 = var21 - var19 * 0.4F;
        var10004 = var7 + var20;
        float var43 = var21 + var19 * 0.4F;
        var10010 = new Object[]{null, null, null, null, null, c(new Object[]{var3, var4, var12}), var22, var14};
        var10010[4] = var43;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var2;
        this.n(var10010);
        var10002 = var7 - var20;
        var10003 = var11 - var19;
        var10004 = var7 + var20;
        float var44 = var11 + var20;
        var10010 = new Object[]{null, null, null, null, null, var4, var22, var14};
        var10010[4] = var44;
        var10010[3] = var10004;
        var10010[2] = var10003;
        var10010[1] = var10002;
        var10010[0] = var2;
        this.n(var10010);
    }

    private void n(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        float var3 = (Float)var1[1];
        float var4 = (Float)var1[2];
        float var10 = (Float)var1[3];
        float var5 = (Float)var1[4];
        Color var7 = (Color)var1[5];
        Color var6 = (Color)var1[6];
        long var8 = (Long)var1[7];
        var8 = 5437566087809L ^ var8;
        long var11 = var8 ^ 57793146413317L;
        float var10001 = var3 - 0.5F;
        float var10002 = var4 - 0.5F;
        float var10003 = var10 + 0.5F;
        float var10004 = var5 + 0.5F;
        Object[] var10008 = new Object[]{null, null, null, null, null, var6, var11};
        var10008[4] = var10004;
        var10008[3] = var10003;
        var10008[2] = var10002;
        var10008[1] = var10001;
        var10008[0] = var2;
        MatrixStack.r(var10008);
        var10008 = new Object[]{null, null, null, null, null, var7, var11};
        var10008[4] = var5;
        var10008[3] = var10;
        var10008[2] = var4;
        var10008[1] = var3;
        var10008[0] = var2;
        MatrixStack.r(var10008);
    }

    private void o(Object[] var1) {
        Matrix4f var4 = (Matrix4f)var1[0];
        float var8 = (Float)var1[1];
        float var11 = (Float)var1[2];
        float var7 = (Float)var1[3];
        float var10 = (Float)var1[4];
        Color var9 = (Color)var1[5];
        float var6 = (Float)var1[6];
        float var5 = (Float)var1[7];
        long var2 = (Long)var1[8];
        var2 = 5437566087809L ^ var2;
        long var12 = var2 ^ 86001372118539L;
        long var14 = var2 ^ 61917817914885L;
        Event.b();
        float var17 = 4.0F;
        float var18 = (Float)this.h.getValue();

        for (int var19 = 1; var19 <= var17; var19++) {
            float var20 = var19 / var17;
            float var21 = var18 * var20;
            float var22 = var6 * 0.14F * (1.0F - var20);
            int var10002 = var9.getRed();
            int var10003 = var9.getGreen();
            int var10004 = var9.getBlue();
            Object[] var10008 = new Object[]{null, var12};
            var10008[0] = var22;
            Color var23 = new Color(var10002, var10003, var10004, a_int_________________(var10008));
            float var10001 = var8 - var21;
            float var25 = var11 - var21;
            float var26 = var7 + var21;
            float var27 = var10 + var21;
            float var10006 = var5 + var21;
            Object[] var10009 = new Object[]{null, null, null, null, null, null, null, var14};
            var10009[6] = var10006;
            var10009[5] = var23;
            var10009[4] = var27;
            var10009[3] = var26;
            var10009[2] = var25;
            var10009[1] = var10001;
            var10009[0] = var4;
            MatrixStack.n(var10009);
        }
    }

    private static void p(Object[] var0) {
        Matrix4f var4 = (Matrix4f)var0[0];
        float var3 = (Float)var0[1];
        float var8 = (Float)var0[2];
        float var10 = (Float)var0[3];
        float var1 = (Float)var0[4];
        Color var9 = (Color)var0[5];
        float var5 = (Float)var0[6];
        float var2 = (Float)var0[7];
        long var6 = (Long)var0[8];
        var6 = 5437566087809L ^ var6;
        long var11 = var6 ^ 36196417274046L;
        RenderHelper8 var10000 = MatrixStack.g;
        MatrixCameraData var10001 = MatrixStack.h;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, null, var11};
        var10012[9] = var2;
        var10012[8] = var5;
        var10012[7] = var9;
        var10012[6] = var1;
        var10012[5] = var10;
        var10012[4] = var8;
        var10012[3] = var3;
        var10012[2] = var4;
        var10012[1] = var10001;
        var10012[0] = var10000;
        RenderState.C(var10012);
    }

    private void q(Object[] var1) {
        Matrix4f var4 = (Matrix4f)var1[0];
        Font var2 = (Font)var1[1];
        float var14 = (Float)var1[2];
        float var6 = (Float)var1[3];
        float var5 = (Float)var1[4];
        float var11 = (Float)var1[5];
        float var3 = (Float)var1[6];
        float var13 = (Float)var1[7];
        float var12 = (Float)var1[8];
        long var7 = (Long)var1[9];
        long var9 = (Long)var1[10];
        var9 = 5437566087809L ^ var9;
        long var15 = var9 ^ 4428948817655L;
        long var17 = var9 ^ 48408362557173L;
        long var19 = var9 ^ 133426880095119L;
        long var21 = var9 ^ 64073203559255L;
        long var23 = var9 ^ 41230823818347L;
        long var25 = var9 ^ 62202941512544L;
        long var27 = var9 ^ 70663756624400L;
        long var29 = var9 ^ 46746568911314L;
        long var31 = var9 ^ 83707440354137L;
        long var33 = var9 ^ 100734159960582L;
        Event.b();
        float var10001 = var3 / var13;
        Object[] var10004 = new Object[]{null, var15};
        var10004[0] = var10001;
        float var36 = c_float__(var10004);
        Color var37 = (Color)this.u.getValue();
        Color var38 = (Color)this.v.getValue();
        Object[] var10005 = new Object[]{null, null, null, var23};
        var10005[2] = var36;
        var10005[1] = var37;
        var10005[0] = var38;
        Color var39 = b_Color____(var10005);
        float var83 = var12 * 0.63F;
        Object[] var10008 = new Object[]{null, var21};
        var10008[0] = var83;
        Color var41 = new Color(0, 0, 0, a_int_________________(var10008));
        String var42 = (String)this.k.getValue();
        float var43 = 2.6F;
        float var44 = 3.0F;
        float var45 = 1.6F;
        boolean var50 = var42.equals("Left") || var42.equals("Right");
        float var46;
        float var47;
        float var48;
        float var49;
        if (var50) {
            if (var42.equals("Left")) {
                var48 = var14 - var44;
                var46 = var48 - var43;
            } else {
                var46 = var5 + var44;
                var48 = var46 + var43;
            }

            var47 = var6;
            var49 = var11;
        } else {
            if (var42.equals("Top")) {
                var49 = var6 - var44;
                var47 = var49 - var43;
            } else {
                var47 = var11 + var44;
                var49 = var47 + var43;
            }

            var46 = var14;
            var48 = var5;
        }

        var10001 = var46 - 0.6F;
        float var10002 = var47 - 0.6F;
        float var10003 = var48 + 0.6F;
        float var79 = var49 + 0.6F;
        Object[] var10009 = new Object[]{null, null, null, null, null, null, null, var31};
        var10009[6] = var45;
        var10009[5] = var41;
        var10009[4] = var79;
        var10009[3] = var10003;
        var10009[2] = var10002;
        var10009[1] = var10001;
        var10009[0] = var4;
        MatrixStack.n(var10009);
        if (var50) {
            float var51 = (var49 - var47) * var36;
            float var52 = var49 - var51;
            if (var51 > 1.0F) {
                var10009 = new Object[]{null, null, var25};
                var10009[1] = var12;
                var10009[0] = var38;
                Color var84 = a_Color______(var10009);
                Object[] var10010 = new Object[]{null, null, var25};
                var10010[1] = var12;
                var10010[0] = var38;
                Color var10006 = a_Color______(var10010);
                Object[] var10011 = new Object[]{null, null, var25};
                var10011[1] = var12;
                var10011[0] = var39;
                Color var10007 = a_Color______(var10011);
                Object[] var10012 = new Object[]{null, null, var25};
                var10012[1] = var12;
                var10012[0] = var39;
                var10012 = new Object[]{null, null, null, null, null, var84, var10006, var10007, a_Color______(var10012), new Vector4f(var45), var19};
                var10012[4] = var49;
                var10012[3] = var48;
                var10012[2] = var52;
                var10012[1] = var46;
                var10012[0] = var4;
                RenderState.w(var10012);
            }

            if ((Boolean)this.m.getValue() && var51 > 4.0F) {
                float var53 = (float)(var7 % 1600L / 1600.0);
                float var54 = var52 + (var49 - var52) * var53;
                float var55 = Math.min(8.0F, var51 * 0.35F);
                float var85 = var12 * 0.28F * (1.0F - Math.abs(var53 - 0.5F) * 2.0F);
                var10008 = new Object[]{null, var21};
                var10008[0] = var85;
                Color var56 = new Color(255, 255, 255, a_int_________________(var10008));
                var10002 = var54 - var55 * 0.5F;
                float var80 = var54 + var55 * 0.5F;
                var10008 = new Object[]{null, null, null, null, null, var56, var33};
                var10008[4] = var80;
                var10008[3] = var48;
                var10008[2] = var10002;
                var10008[1] = var46;
                var10008[0] = var4;
                MatrixStack.r(var10008);
            }
        } else {
            float var60 = (var48 - var46) * var36;
            float var62 = var46 + var60;
            if (var60 > 1.0F) {
                var10009 = new Object[]{null, null, var25};
                var10009[1] = var12;
                var10009[0] = var38;
                Color var86 = a_Color______(var10009);
                Object[] var100 = new Object[]{null, null, var25};
                var100[1] = var12;
                var100[0] = var39;
                Color var89 = a_Color______(var100);
                Object[] var101 = new Object[]{null, null, var25};
                var101[1] = var12;
                var101[0] = var39;
                Color var90 = a_Color______(var101);
                Object[] var103 = new Object[]{null, null, var25};
                var103[1] = var12;
                var103[0] = var38;
                var103 = new Object[]{null, null, null, null, null, var86, var89, var90, a_Color______(var103), new Vector4f(var45), var19};
                var103[4] = var49;
                var103[3] = var62;
                var103[2] = var47;
                var103[1] = var46;
                var103[0] = var4;
                RenderState.w(var103);
            }

            if ((Boolean)this.m.getValue() && var60 > 4.0F) {
                float var64 = (float)(var7 % 1600L / 1600.0);
                float var66 = var46 + var60 * var64;
                float var68 = Math.min(8.0F, var60 * 0.35F);
                float var87 = var12 * 0.28F * (1.0F - Math.abs(var64 - 0.5F) * 2.0F);
                var10008 = new Object[]{null, var21};
                var10008[0] = var87;
                Color var70 = new Color(255, 255, 255, a_int_________________(var10008));
                var10001 = var66 - var68 * 0.5F;
                var10003 = var66 + var68 * 0.5F;
                var10008 = new Object[]{null, null, null, null, null, var70, var33};
                var10008[4] = var49;
                var10008[3] = var10003;
                var10008[2] = var47;
                var10008[1] = var10001;
                var10008[0] = var4;
                MatrixStack.r(var10008);
            }
        }

        if ((Boolean)this.l.getValue()) {
            String var61 = String.valueOf(Math.round(var3));
            float var88 = var12 * 0.55F;
            var10008 = new Object[]{null, var21};
            var10008[0] = var88;
            Color var63 = new Color(0, 0, 0, a_int_________________(var10008));
            float var65 = 0.5F;
            float var67 = var2.a_float______________(new Object[]{var61, var17}) * var65;
            float var69 = var2.c_float___(new Object[]{var27}) * var65;
            float var57;
            float var71;
            if (var50) {
                var71 = (var46 + var48) * 0.5F - var67 * 0.5F;
                var57 = var49 + 2.0F;
            } else {
                var71 = (var46 + var48) * 0.5F - var67 * 0.5F;
                var57 = var42.equals("Top") ? var47 - var69 - 2.0F : var49 + 2.0F;
            }

            var10001 = var71 - 2.0F;
            var10002 = var57 - 1.0F;
            var10003 = var71 + var67 + 2.0F;
            float var81 = var57 + var69 + 1.0F;
            var10009 = new Object[]{null, null, null, null, null, null, null, var31};
            var10009[6] = 1.5F;
            var10009[5] = var63;
            var10009[4] = var81;
            var10009[3] = var10003;
            var10009[2] = var10002;
            var10009[1] = var10001;
            var10009[0] = var4;
            MatrixStack.n(var10009);
            Color var10000 = new Color(240, 240, 245);
            var10004 = new Object[]{null, null, var25};
            var10004[1] = var12;
            var10004[0] = var10000;
            Color var58 = a_Color______(var10004);
            var10009 = new Object[]{null, null, null, null, null, var58, var29};
            var10009[4] = var65;
            var10009[3] = var57;
            var10009[2] = var71;
            var10009[1] = var61;
            var10009[0] = var4;
            var2.w(var10009);
        }
    }

    private void r(Object[] var1) {
        RenderHelper8 var10 = (RenderHelper8)var1[0];
        MatrixCameraData var7 = (MatrixCameraData)var1[1];
        Matrix4f var3 = (Matrix4f)var1[2];
        Font var5 = (Font)var1[3];
        float var4 = (Float)var1[4];
        float var2 = (Float)var1[5];
        float var11 = (Float)var1[6];
        String var12 = (String)var1[7];
        float var6 = (Float)var1[8];
        long var8 = (Long)var1[9];
        var8 = 5437566087809L ^ var8;
        long var13 = var8 ^ 52831183632304L;
        long var15 = var8 ^ 88966295022204L;
        long var17 = var8 ^ 101494182599509L;
        long var19 = var8 ^ 40237477949989L;
        long var21 = var8 ^ 64576446339139L;
        float var23 = var5.a_float______________(new Object[]{var12, var13});
        float var24 = var5.c_float___(new Object[]{var17});
        float var25 = 4.0F;
        float var26 = 1.5F;
        float var27 = var23 + var25 * 2.0F;
        float var28 = (var4 + var11) * 0.5F;
        float var29 = var28 - var27 * 0.5F;
        float var30 = var29 + var27;
        float var31 = var2 - 3.0F;
        float var32 = var31 - (var24 + var26 * 2.0F);
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var15};
        var10011[7] = var6;
        var10011[6] = var31;
        var10011[5] = var30;
        var10011[4] = var32;
        var10011[3] = var29;
        var10011[2] = var3;
        var10011[1] = var7;
        var10011[0] = var10;
        this.t(var10011);
        Color var10000 = new Color(240, 240, 250);
        Object[] var10004 = new Object[]{null, null, var19};
        var10004[1] = var6;
        var10004[0] = var10000;
        Color var33 = a_Color______(var10004);
        float var10003 = var29 + var25;
        float var35 = var32 + var26;
        Object[] var10008 = new Object[]{null, null, null, null, var33, var21};
        var10008[3] = var35;
        var10008[2] = var10003;
        var10008[1] = var12;
        var10008[0] = var3;
        var5.v(var10008);
    }

    private void s(Object[] var1) {
        RenderHelper8 var10 = (RenderHelper8)var1[0];
        MatrixCameraData var6 = (MatrixCameraData)var1[1];
        Matrix4f var8 = (Matrix4f)var1[2];
        Font var9 = (Font)var1[3];
        float var5 = (Float)var1[4];
        float var11 = (Float)var1[5];
        float var7 = (Float)var1[6];
        float var4 = (Float)var1[7];
        float var12 = (Float)var1[8];
        long var2 = (Long)var1[9];
        var2 = 5437566087809L ^ var2;
        long var13 = var2 ^ 66439921077366L;
        long var15 = var2 ^ 101754693915066L;
        long var17 = var2 ^ 88164886159507L;
        long var19 = var2 ^ 45050123749859L;
        long var21 = var2 ^ 64801460353873L;
        int var24 = (int)var4;
        Event.b();
        int var25 = (int)(var4 * 10.0F) - var24 * 10;
        if (var25 < 0) {
            var25 = -var25;
        }

        String var26 = var24 + "." + var25 + "m";
        float var27 = 0.75F;
        float var28 = var9.a_float______________(new Object[]{var26, var13}) * var27;
        float var29 = var9.c_float___(new Object[]{var17}) * var27;
        float var30 = 3.5F;
        float var31 = 1.5F;
        float var32 = var28 + var30 * 2.0F;
        float var33 = (var5 + var7) * 0.5F;
        float var34 = var33 - var32 * 0.5F;
        float var35 = var34 + var32;
        float var36 = var11 + 3.0F;
        float var37 = var36 + var29 + var31 * 2.0F;
        Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var15};
        var10011[7] = var12;
        var10011[6] = var37;
        var10011[5] = var35;
        var10011[4] = var36;
        var10011[3] = var34;
        var10011[2] = var8;
        var10011[1] = var6;
        var10011[0] = var10;
        this.t(var10011);
        Color var10000 = new Color(200, 200, 220);
        Object[] var10004 = new Object[]{null, null, var19};
        var10004[1] = var12;
        var10004[0] = var10000;
        Color var38 = a_Color______(var10004);
        float var10003 = var34 + var30;
        float var40 = var36 + var31;
        Object[] var10009 = new Object[]{null, null, null, null, null, var38, var21};
        var10009[4] = var27;
        var10009[3] = var40;
        var10009[2] = var10003;
        var10009[1] = var26;
        var10009[0] = var8;
        var9.w(var10009);
    }

    private void t(Object[] var1) {
        RenderHelper8 var7 = (RenderHelper8)var1[0];
        MatrixCameraData var2 = (MatrixCameraData)var1[1];
        Matrix4f var10 = (Matrix4f)var1[2];
        float var6 = (Float)var1[3];
        float var8 = (Float)var1[4];
        float var11 = (Float)var1[5];
        float var9 = (Float)var1[6];
        float var5 = (Float)var1[7];
        long var3 = (Long)var1[8];
        var3 = 5437566087809L ^ var3;
        long var12 = var3 ^ 71488456665735L;
        long var14 = var3 ^ 122897708558140L;
        long var16 = var3 ^ 125706923072239L;
        long var18 = var3 ^ 110048169098932L;
        float var20 = var11 - var6;
        float var21 = var9 - var8;
        if (this.f.isSelected(new Object[]{"Blur Labels", var18})) {
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var14};
            var10011[8] = 2.5F;
            var10011[7] = 5;
            var10011[6] = var21;
            var10011[5] = var20;
            var10011[4] = var8;
            var10011[3] = var6;
            var10011[2] = var10;
            var10011[1] = var2;
            var10011[0] = var7;
            RenderTarget.e(var10011);
        }

        float var10005 = var5 * 0.72F;
        Object[] var10008 = new Object[]{null, var16};
        var10008[0] = var10005;
        Color var22 = new Color(16, 16, 22, a_int_________________(var10008));
        Object[] var24 = new Object[]{null, null, null, null, null, null, null, null, null, var12};
        var24[8] = 2.5F;
        var24[7] = var22;
        var24[6] = var21;
        var24[5] = var20;
        var24[4] = var8;
        var24[3] = var6;
        var24[2] = var10;
        var24[1] = var2;
        var24[0] = var7;
        RoundedPosColorShader.c(var24);
    }

    private float[] a(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        float var3 = (Float)var1[1];
        float var6 = (Float)var1[2];
        long var4 = (Long)var1[3];
        var4 = 5437566087809L ^ var4;
        long var7 = var4 ^ 66986878808006L;
        Event.b();
        double var10 = var3 * 0.5;
        double var12 = var6;
        float var14 = Float.POSITIVE_INFINITY;
        float var15 = Float.POSITIVE_INFINITY;
        float var16 = Float.NEGATIVE_INFINITY;
        float var17 = Float.NEGATIVE_INFINITY;

        for (int var18 = 0; var18 < 8; var18++) {
            double var19 = var2.x + ((var18 & 1) == 0 ? -var10 : var10);
            double var21 = var2.y + ((var18 & 2) == 0 ? 0.0 : var12);
            double var23 = var2.z + ((var18 & 4) == 0 ? -var10 : var10);
            Vec3d var25 = MatrixStack.a_class_243_______________(new Object[]{new Vec3d(var19, var21, var23), var7});
            if (var25.z < 0.0 || var25.z >= 1.0) {
                return null;
            }

            float var26 = (float)var25.x;
            float var27 = (float)var25.y;
            if (var26 < var14) {
                var14 = var26;
            }

            if (var26 > var16) {
                var16 = var26;
            }

            if (var27 < var15) {
                var15 = var27;
            }

            if (var27 > var17) {
                var17 = var27;
            }
        }

        return new float[]{var14, var15, var16, var17};
    }

    private static Color a_Color______(Object[] var0) {
        Color var1 = (Color)var0[0];
        float var4 = (Float)var0[1];
        long var2 = (Long)var0[2];
        var2 = 5437566087809L ^ var2;
        long var5 = var2 ^ 62019225104150L;
        long var7 = var2 ^ 27069973455766L;
        float var10000 = var1.getAlpha();
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var4;
        int var9 = Math.round(var10000 * c_float__(var10004));
        int var10002 = var1.getRed();
        int var10003 = var1.getGreen();
        int var11 = var1.getBlue();
        Object[] var10008 = new Object[]{null, var7};
        var10008[0] = var9;
        return new Color(var10002, var10003, var11, c_int__(var10008));
    }

    private static Color b_Color____(Object[] var0) {
        Color var2 = (Color)var0[0];
        Color var3 = (Color)var0[1];
        float var1 = (Float)var0[2];
        long var4 = (Long)var0[3];
        var4 = 5437566087809L ^ var4;
        long var6 = var4 ^ 41278750437405L;
        long var8 = var4 ^ 5994508123293L;
        Object[] var10003 = new Object[]{null, var6};
        var10003[0] = var1;
        var1 = c_float__(var10003);
        int var10 = Math.round(var2.getRed() + (var3.getRed() - var2.getRed()) * var1);
        int var11 = Math.round(var2.getGreen() + (var3.getGreen() - var2.getGreen()) * var1);
        int var12 = Math.round(var2.getBlue() + (var3.getBlue() - var2.getBlue()) * var1);
        int var13 = Math.round(var2.getAlpha() + (var3.getAlpha() - var2.getAlpha()) * var1);
        Object[] var10005 = new Object[]{null, var8};
        var10005[0] = var10;
        int var10002 = c_int__(var10005);
        Object[] var10006 = new Object[]{null, var8};
        var10006[0] = var11;
        int var16 = c_int__(var10006);
        Object[] var10007 = new Object[]{null, var8};
        var10007[0] = var12;
        int var10004 = c_int__(var10007);
        Object[] var10008 = new Object[]{null, var8};
        var10008[0] = var13;
        return new Color(var10002, var16, var10004, c_int__(var10008));
    }

    private static Color c(Object[] var0) {
        Color var2 = (Color)var0[0];
        Color var1 = (Color)var0[1];
        return new Color(
            (var2.getRed() + var1.getRed()) / 2,
            (var2.getGreen() + var1.getGreen()) / 2,
            (var2.getBlue() + var1.getBlue()) / 2,
            (var2.getAlpha() + var1.getAlpha()) / 2
        );
    }

    private static float b_float___________________(Object[] var0) {
        float var3 = (Float)var0[0];
        float var2 = (Float)var0[1];
        float var1 = (Float)var0[2];
        long var4 = (Long)var0[3];
        var4 = 5437566087809L ^ var4;
        long var6 = var4 ^ 36502000330000L;
        float var10001 = var2 - var3;
        Object[] var10005 = new Object[]{null, var6};
        var10005[0] = var1;
        return var3 + var10001 * c_float__(var10005);
    }

    private static float c_float__(Object[] var0) {
        float var3 = (Float)var0[0];
        return var3 < 0.0F ? 0.0F : (var3 > 1.0F ? 1.0F : var3);
    }

    private static float d(Object[] var0) {
        float var1 = (Float)var0[0];
        float var5 = (Float)var0[1];
        float var2 = (Float)var0[2];
        return var1 < var5 ? var5 : (var1 > var2 ? var2 : var1);
    }

    private static int a_int_________________(Object[] var0) {
        float var1 = (Float)var0[0];
        Event.b();
        int var5 = Math.round(var1 * 255.0F);
        return var5 < 0 ? 0 : (var5 > 255 ? 255 : var5);
    }

    private static int c_int__(Object[] var0) {
        int var1 = (Integer)var0[0];
        return var1 < 0 ? 0 : (var1 > 255 ? 255 : var1);
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
