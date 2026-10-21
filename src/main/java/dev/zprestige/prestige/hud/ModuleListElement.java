package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.joml.Matrix4f;

public class ModuleListElement extends HudElement {
    private final Map a;
    private final Map c;
    private final Map d;
    private static float m = 0.0F;
    private static final float j = 500.0F;
    private static final float k = 1.0F;
    private float l;
    private float o;

    public ModuleListElement(long var1) {
        super("Module List", "List of currently enabled modules", (119052916597361L));
        var1 = 0L;
        var1 = 46696203253556L ^ var1;
        long var3 = var1 ^ 77238080296261L;
        this.a = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        this.l = 0.0F;
        this.o = 0.0F;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = 135.0F;
        var10005[0] = 2.0F;
        this.a_void_______(var10005);
    }

    @Override
    public void q(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        long var3 = (Long)var1[1];
        if (this.g <= 0.0F || this.h <= 0.0F) {
            return;
        }

        super.q(new Object[]{var2, var3});
    }

    @Override
    public void l(Object[] var1) {
        RenderHelper8 var3 = (RenderHelper8)var1[0];
        MatrixCameraData var2 = (MatrixCameraData)var1[1];
        Matrix4f var4 = (Matrix4f)var1[2];
        long var5 = (Long)var1[3];
        long var7 = var5 ^ 132449650111663L;
        long var9 = var5 ^ 27807340928064L;
        long var11 = var5 ^ 95334181894420L;
        long var13 = var5 ^ 102693909475685L;
        long var15 = var5 ^ 52207587184489L;
        long var17 = var5 ^ 18373113372313L;
        long var19 = var5 ^ 6014253680539L;
        long var21 = var5 ^ 131934975778103L;
        long var23 = var5 ^ 45947767099148L;
        long var25 = var5 ^ 54030867045760L;
        long var27 = var5 ^ 96557814079126L;
        long var30 = System.currentTimeMillis();
        m = (float)(var30 - 0L) * 0.005F;
        Event.b();

        for (Module var33 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
            boolean var34 = var33.isEnabled();
            Boolean var35 = (Boolean)this.c.put(var33, var34);
            if (var34 && Boolean.FALSE.equals(var35)) {
                this.d.put(var33, var30);
            }

            float var36 = var34 ? 1.0F : 0.0F;
            float var37 = (float)this.a.getOrDefault(var33, 0.0F);
            float var10002 = m;
            Object[] var10005 = new Object[]{null, null, null, var19};
            var10005[2] = var10002;
            var10005[1] = var36;
            var10005[0] = var37;
            var37 = MathHelper4.b_float______(var10005);
            this.a.put(var33, var37);
        }

        ArrayList var62 = new ArrayList();

        for (Entry var65 : (Iterable<Entry>)(this.a.entrySet())) {
            if ((Float)var65.getValue() > 0.005F) {
                var62.add(var65);
            }
        }

        if (var62.isEmpty()) {
            Object[] var88 = new Object[]{null, 0.0F};
            var88[0] = 0.0F;
            this.i(var88);
            this.l = 0.0F;
            this.o = 0.0F;
        } else {
            var62.sort(
                (lpL41_0, lpL41_1) -> Float.compare(
                    Prestige.c.a(new Object[]{1236438218049L}).a_float______________(new Object[]{((Module)((java.util.Map.Entry)lpL41_1).getKey()).getName(), 47859522476307L}),
                    Prestige.c.a(new Object[]{1236438218049L}).a_float______________(new Object[]{((Module)((java.util.Map.Entry)lpL41_0).getKey()).getName(), 47859522476307L})
                )
            );
            float var64 = Prestige.c.a(new Object[]{var21}).c_float___(new Object[]{var25});
            Object[] var10003 = new Object[]{null, 1.0F};
            var10003[0] = var64;
            float var66 = NameTagsUtil.a(var10003);
            float var67 = 0.0F;
            float var68 = 0.0F;

            for (Entry var38 : (Iterable<Entry>)(var62)) {
                float var39 = Prestige.c.a(new Object[]{var21}).a_float______________(new Object[]{((Module)var38.getKey()).getName(), var13}) + 11.0F;
                if (var39 > var67) {
                    var67 = var39;
                }

                var68 += (var66 + 1.0F) * (Float)var38.getValue();
            }

            b.getWindow().getScaledWidth();
            b.getWindow().getScaledHeight();
            boolean var71 = this.d_boolean(new Object[0]);
            boolean var40 = this.c_boolean__(new Object[0]);
            boolean var41 = b.currentScreen instanceof HudEditorScreen;
            if (!var41) {
                if (var71 && this.l > 0.0F) {
                    this.f = this.f - (var68 - this.l);
                }

                if (var40 && this.o > 0.0F) {
                    this.e = this.e - (var67 - this.o);
                }
            }

            this.l = var68;
            this.o = var67;
            Object[] var10004 = new Object[]{null, var68};
            var10004[0] = var67;
            this.i(var10004);
            Color var42 = MathHelper4.d(new Object[]{var9});
            int var43 = var62.size();
            float var44 = var71 ? this.f + var68 - var66 : this.f;

            for (int var45 = 0; var45 < var62.size(); var45++) {
                Entry var46 = (Entry)var62.get(var45);
                Module var47 = (Module)var46.getKey();
                float var48 = (Float)var46.getValue();
                String var49 = var47.getName();
                float var50 = Prestige.c.a(new Object[]{var21}).a_float______________(new Object[]{var49, var13}) + 11.0F;
                float var51 = (1.0F - var48) * 6.0F;
                int var52 = var40 ? var43 - 1 - var45 : var45;
                int var74 = 10 + var52;
                Object[] var89 = new Object[]{null, null, null, var23};
                var89[2] = var74;
                var89[1] = 10;
                var89[0] = var42;
                Color var53 = MatrixStack.b_Color_____(var89);
                float var54;
                if (var40) {
                    var54 = this.e + var67 - var50 - var51;
                } else {
                    var54 = this.e + var51;
                }

                long var55 = (long)this.d.getOrDefault(var47, 0L);
                float var57 = var55 == 0L ? 0.0F : Math.max(0.0F, 1.0F - (float)(var30 - var55) / 500.0F);
                var57 *= var57;
                int var10000 = (int)(NameTagsUtil.r.getAlpha() * var48);
                var10003 = new Object[]{null, var15};
                var10003[0] = var10000;
                int var58 = a(var10003);
                float var59 = var57 * 0.35F;
                var74 = NameTagsUtil.r.getRed();
                int var79 = var53.getRed();
                Object[] var10007 = new Object[]{null, null, null, var17};
                var10007[2] = var59;
                var10007[1] = var79;
                var10007[0] = var74;
                var74 = b(var10007);
                int var80 = NameTagsUtil.r.getGreen();
                int var84 = var53.getGreen();
                Object[] var10008 = new Object[]{null, null, null, var17};
                var10008[2] = var59;
                var10008[1] = var84;
                var10008[0] = var80;
                int var81 = b(var10008);
                int var85 = NameTagsUtil.r.getBlue();
                int var90 = var53.getBlue();
                Object[] var10009 = new Object[]{null, null, null, var17};
                var10009[2] = var59;
                var10009[1] = var90;
                var10009[0] = var85;
                Color var60 = new Color(var74, var81, b(var10009), var58);
                var74 = var53.getRed();
                int var82 = var53.getGreen();
                int var86 = var53.getBlue();
                int var91 = (int)(255.0F * var48);
                var10008 = new Object[]{null, var15};
                var10008[0] = var91;
                Color var61 = new Color(var74, var82, var86, a(var10008));
                if (var48 > 0.7F) {
                    Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, var11};
                    var10011[8] = 4.0F;
                    var10011[7] = 5;
                    var10011[6] = var66;
                    var10011[5] = var50;
                    var10011[4] = var44;
                    var10011[3] = var54;
                    var10011[2] = var4;
                    var10011[1] = var2;
                    var10011[0] = var3;
                    RenderTarget.e(var10011);
                }

                Object[] var94 = new Object[]{null, null, null, null, null, null, null, null, null, var7};
                var94[8] = 4.0F;
                var94[7] = var60;
                var94[6] = var66;
                var94[5] = var50;
                var94[4] = var44;
                var94[3] = var54;
                var94[2] = var4;
                var94[1] = var2;
                var94[0] = var3;
                RoundedPosColorShader.c(var94);
                Font var73 = Prestige.c.a(new Object[]{var21});
                float var83 = var54 + 5.5F;
                float var87 = var44 + 3.0F;
                var10008 = new Object[]{null, null, null, null, var61, var27};
                var10008[3] = var87;
                var10008[2] = var83;
                var10008[1] = var49;
                var10008[0] = var4;
                var73.v(var10008);
                if (var71) {
                    var44 -= (var66 + 1.0F) * var48;
                } else {
                    var44 += (var66 + 1.0F) * var48;
                }
            }
        }
    }

    private static int a(Object[] var0) {
        int var3 = (Integer)var0[0];
        return var3 < 0 ? 0 : Math.min(var3, 255);
    }

    private static int b(Object[] var0) {
        int var3 = (Integer)var0[0];
        int var2 = (Integer)var0[1];
        float var1 = (Float)var0[2];
        long var4 = (Long)var0[3];
        var4 = 46696203253556L ^ var4;
        long var6 = var4 ^ 23871877965508L;
        int var10000 = (int)(var3 + (var2 - var3) * var1);
        Object[] var10003 = new Object[]{null, var6};
        var10003[0] = var10000;
        return a(var10003);
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
