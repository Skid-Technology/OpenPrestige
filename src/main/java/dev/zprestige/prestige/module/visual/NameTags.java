package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.NameTagsUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.EntityEvent5;
import dev.zprestige.prestige.event.EntityEvent6;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent22;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.inventory.ItemLabel;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.client.render.Frustum;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class NameTags extends Module {
    private FloatSetting a;
    private IntSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private static final float k = 0.15F;
    private static final Color l = new Color(240, 170, 60);
    private static final Color m = new Color(235, 75, 75);
    private static final Color n = new Color(255, 255, 255, 22);
    private static final Color o = new Color(235, 85, 85, 130);
    private static final Color p = new Color(235, 75, 75);
    private static final Color q = new Color(120, 220, 240);
    private Frustum r;
    private Map s = new HashMap();
    private List t = new ArrayList();
    private Map u = new LinkedHashMap();

    public NameTags() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.j.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL367_0 -> (Boolean)this.i.getValue(), 73196168581295L});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            Font var19 = Prestige.c.a(new Object[]{1236438218049L});
            Color var20 = (Color)Prestige.moduleManager.getMenu(new Object[0]).getMenuColor(new Object[0]).getValue();
            this.s.clear();

            for (PlayerEntity var22 : b.world.getPlayers()) {
                if (var22 != b.player && Prestige.i.a_boolean_(new Object[]{var22, 54363558999103L})) {
                    dev.zprestige.prestige.combat.NameTags var24 = (dev.zprestige.prestige.combat.NameTags)this.u.get(var22);
                    dev.zprestige.prestige.combat.NameTags var23 = new dev.zprestige.prestige.combat.NameTags();
                    var23.a = var22.getName().getString();
                    var23.b = Prestige.g.a(new Object[]{var23.a, 24848952222090L}) ? q : NameTagsUtil.s;
                    float var25 = var22.getHealth() + var22.getAbsorptionAmount();
                    float var26 = var22.getMaxHealth() + var22.getAbsorptionAmount();
                    float var10001 = var25 / Math.max(1.0F, var26);
                    Object[] var10006 = new Object[]{null, null, null, 37755544982162L};
                    var10006[2] = 1.0F;
                    var10006[1] = 0.0F;
                    var10006[0] = var10001;
                    var23.e = MathHelper4.a(var10006);
                    float var27 = var24 == null ? var23.e : var24.f;
                    var23.f = Math.max(var23.e, var27 + (var23.e - var27) * 0.15F);
                    if (var23.e > var27) {
                        var23.f = var23.e;
                    }

                    if (var23.e > 0.6F) {
                        var23.g = var20;
                    } else if (var23.e > 0.3F) {
                        var23.g = l;
                    } else {
                        var23.g = m;
                    }

                    var23.c = Prestige.r.a(new Object[]{var22, 133768629865341L});
                    var23.d = this.g.getValue() && var23.c > 0 ? "-" + var23.c : "";
                    if ((Boolean)this.f.getValue()) {
                        var23.h = Math.round(var25) + "hp";
                    } else {
                        var23.h = "";
                    }

                    if ((Boolean)this.h.getValue()) {
                        if (!var22.getMainHandStack().isEmpty()) {
                            var23.l.add(var22.getMainHandStack());
                        }

                        if (!var22.getOffHandStack().isEmpty()) {
                            var23.l.add(var22.getOffHandStack());
                        }
                    }

                    if ((Boolean)this.i.getValue()) {
                        boolean var28 = (Boolean)this.j.getValue();

                        for (EquipmentSlot var30 : AttributeModifierSlot.ARMOR) {
                            ItemStack var31 = var22.getEquippedStack(var30);
                            if (!var31.isEmpty()) {
                                var23.l.add(var31);
                                if (var28) {
                                    var23.m.addAll(this.b_List______(new Object[]{var31, 106470340904503L}));
                                }
                            }
                        }
                    }

                    var23.i = var19.a_float______________(new Object[]{var23.a, 47859522476307L});
                    var23.j = var23.d.isEmpty() ? 0.0F : var19.a_float______________(new Object[]{var23.d, 47859522476307L}) * 0.75F;
                    var23.k = var23.h.isEmpty() ? 0.0F : var19.a_float______________(new Object[]{var23.h, 47859522476307L}) * 0.75F;
                    this.s.put(var22, var23);
                }
            }

            this.t.clear();
            this.t.addAll(this.s.entrySet());
            this.t.sort((lpL366_0, lpL366_1) -> Float.compare(b.player.distanceTo((Entity)((java.util.Map.Entry)lpL366_1).getKey()), b.player.distanceTo((Entity)((java.util.Map.Entry)lpL366_0).getKey())));
            this.u.clear();

            for (Entry var33 : (Iterable<Entry>)(this.t)) {
                this.u.put((PlayerEntity)var33.getKey(), (dev.zprestige.prestige.combat.NameTags)var33.getValue());
            }
        }
    }

    @Subscribe
    public void a(TickEvent25 var1) {
        if (b.player != null) {
            Font var31 = Prestige.c.a(new Object[]{1236438218049L});
            float var32 = var31.c_float___(new Object[]{78913221670390L});
            float var33 = NameTagsUtil.b(new Object[]{var32});
            float var34 = (Float)this.d.getValue();
            boolean var35 = (Boolean)this.e.getValue();
            boolean var36 = (Boolean)this.f.getValue();
            boolean var37 = (Boolean)this.h.getValue() || (Boolean)this.i.getValue();
            boolean var38 = (Boolean)this.j.getValue() && (Boolean)this.i.getValue();
            int var39 = (Integer)this.c.getValue();
            int var40 = 0;

            for (Entry var42 : (Iterable<Entry>)(this.u.entrySet())) {
                if (var40 >= var39) {
                    break;
                }

                PlayerEntity var43 = (PlayerEntity)var42.getKey();
                dev.zprestige.prestige.combat.NameTags var44 = (dev.zprestige.prestige.combat.NameTags)var42.getValue();
                if (this.r == null || this.r.isVisible(var43.getBoundingBox())) {
                    float var45 = b.player.distanceTo(var43);
                    if (!(var45 > var34)) {
                        Vec3d var46 = MatrixStack.b(new Object[]{var43, 78245126833881L});
                        Vec3d var47 = MatrixStack.a_class_243_______________(
                            new Object[]{var46.add(0.0, var43.getHeight() + 0.5 * (var45 / 10.0F), 0.0), 73023576373275L}
                        );
                        if (!(var47.z < 0.0) && !(var47.z >= 1.0)) {
                            var40++;
                            float var48 = (Float)this.a.getValue() * 0.8F - var45 / 300.0F;
                            if (var48 < 0.15F) {
                                var48 = 0.15F;
                            }

                            float var89;
                            if (var45 > var34 - 10.0F) {
                                var89 = (var34 - var45) / 10.0F;
                                Object[] var10005 = new Object[]{null, null, null, 37755544982162L};
                                var10005[2] = 1.0F;
                                var10005[1] = 0.0F;
                                var10005[0] = var89;
                                var89 = MathHelper4.a(var10005);
                            } else {
                                var89 = 1.0F;
                            }

                            float var49 = var89;
                            float var50 = (float)var47.x / var48;
                            float var51 = (float)var47.y / var48;
                            Matrix4f var52 = new Matrix4f().scale(var48, var48, var48);
                            String var53 = var35 ? Math.round(var45) + "m" : "";
                            float var54 = var53.isEmpty() ? 0.0F : var31.a_float______________(new Object[]{var53, 47859522476307L}) * 0.65F;
                            float var55 = var44.i + (var44.d.isEmpty() ? 0.0F : 3.0F + var44.j);
                            float var56 = 0.0F;
                            if (!var53.isEmpty()) {
                                var56 = var54;
                            }

                            float var57 = var55 + (var56 > 0.0F ? 6.0F + var56 : 0.0F);
                            float var58 = 60.0F;
                            float var59 = Math.max(var57, var58);
                            if (var36) {
                                var59 = Math.max(var59, var55 + 6.0F + var44.k);
                            }

                            float var60 = var59 + 11.0F;
                            float var61 = var50 - var60 / 2.0F;
                            float var62 = var51 - var33 / 2.0F;
                            RenderHelper8 var90 = var1.a;
                            MatrixCameraData var10001 = var1.b;
                            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 35553070502242L};
                            var10011[8] = 4.0F;
                            var10011[7] = 5;
                            var10011[6] = var33;
                            var10011[5] = var60;
                            var10011[4] = var62;
                            var10011[3] = var61;
                            var10011[2] = var52;
                            var10011[1] = var10001;
                            var10011[0] = var90;
                            RenderTarget.e(var10011);
                            RenderHelper8 var91 = var1.a;
                            var10001 = var1.b;
                            Color var10007 = NameTagsUtil.r;
                            var10011 = new Object[]{null, null, 85465053841486L};
                            var10011[1] = var49;
                            var10011[0] = var10007;
                            var10007 = a(var10011);
                            var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
                            var10011[8] = 4.0F;
                            var10011[7] = var10007;
                            var10011[6] = var33;
                            var10011[5] = var60;
                            var10011[4] = var62;
                            var10011[3] = var61;
                            var10011[2] = var52;
                            var10011[1] = var10001;
                            var10011[0] = var91;
                            RoundedPosColorShader.c(var10011);
                            float var63 = var62 + 3.0F;
                            float var64 = var61 + 5.5F;
                            float var65 = var61 + var60 - 5.5F;
                            String var10002 = var44.a;
                            Color var105 = var44.b;
                            Object[] var10009 = new Object[]{null, null, 85465053841486L};
                            var10009[1] = var49;
                            var10009[0] = var105;
                            Object[] var10008 = new Object[]{null, null, null, null, a(var10009), 36494305523424L};
                            var10008[3] = var63;
                            var10008[2] = var64;
                            var10008[1] = var10002;
                            var10008[0] = var52;
                            var31.v(var10008);
                            float var66 = var64 + var44.i + 3.0F;
                            if (!var44.d.isEmpty()) {
                                float var67 = var32 - var32 * 0.75F;
                                var10002 = var44.d;
                                float var10004 = var63 + var67;
                                Color var10006 = p;
                                Object[] var10010 = new Object[]{null, null, 85465053841486L};
                                var10010[1] = var49;
                                var10010[0] = var10006;
                                var10009 = new Object[]{null, null, null, null, null, a(var10010), 49503024803380L};
                                var10009[4] = 0.75F;
                                var10009[3] = var10004;
                                var10009[2] = var66;
                                var10009[1] = var10002;
                                var10009[0] = var52;
                                var31.w(var10009);
                            }

                            if (!var53.isEmpty()) {
                                float var85 = var32 - var32 * 0.65F;
                                float var10003 = var65 - var54;
                                float var100 = var63 + var85;
                                Color var109 = NameTagsUtil.u;
                                Object[] var124 = new Object[]{null, null, 85465053841486L};
                                var124[1] = var49;
                                var124[0] = var109;
                                var10009 = new Object[]{null, null, null, null, null, a(var124), 49503024803380L};
                                var10009[4] = 0.65F;
                                var10009[3] = var100;
                                var10009[2] = var10003;
                                var10009[1] = var53;
                                var10009[0] = var52;
                                var31.w(var10009);
                            }

                            float var86 = var63 + var32 + 1.0F;
                            float var69 = var65;
                            if (var36 && !var44.h.isEmpty()) {
                                var69 = var65 - (var44.k + 3.0F);
                            }

                            float var70 = var69 - var64;
                            Vector4f var71 = new Vector4f(1.5F, 1.5F, 1.5F, 1.5F);
                            float var98 = var64 + var70;
                            float var101 = var86 + 4.0F;
                            var105 = n;
                            var10009 = new Object[]{null, null, 85465053841486L};
                            var10009[1] = var49;
                            var10009[0] = var105;
                            var10009 = new Object[]{null, null, null, null, null, a(var10009), var71, 32811334756897L};
                            var10009[4] = var101;
                            var10009[3] = var98;
                            var10009[2] = var86;
                            var10009[1] = var64;
                            var10009[0] = var52;
                            RenderState.u(var10009);
                            float var72 = var64 + var70 * var44.f;
                            float var73 = var64 + var70 * var44.e;
                            if (var44.f > var44.e + 0.001F) {
                                var101 = var86 + 4.0F;
                                var105 = o;
                                var10009 = new Object[]{null, null, 85465053841486L};
                                var10009[1] = var49;
                                var10009[0] = var105;
                                var10009 = new Object[]{null, null, null, null, null, a(var10009), var71, 32811334756897L};
                                var10009[4] = var101;
                                var10009[3] = var72;
                                var10009[2] = var86;
                                var10009[1] = var73;
                                var10009[0] = var52;
                                RenderState.u(var10009);
                            }

                            if (var44.e > 0.001F) {
                                var101 = var86 + 4.0F;
                                var105 = var44.g;
                                var10009 = new Object[]{null, null, 85465053841486L};
                                var10009[1] = var49;
                                var10009[0] = var105;
                                var10009 = new Object[]{null, null, null, null, null, a(var10009), var71, 32811334756897L};
                                var10009[4] = var101;
                                var10009[3] = var73;
                                var10009[2] = var86;
                                var10009[1] = var64;
                                var10009[0] = var52;
                                RenderState.u(var10009);
                            }

                            if (var36 && !var44.h.isEmpty()) {
                                float var74 = var86 + 2.0F - var32 * 0.75F / 2.0F;
                                var10002 = var44.h;
                                var98 = var69 + 3.0F;
                                Color var110 = var44.g;
                                Object[] var125 = new Object[]{null, null, 85465053841486L};
                                var125[1] = var49;
                                var125[0] = var110;
                                var10009 = new Object[]{null, null, null, null, null, a(var125), 49503024803380L};
                                var10009[4] = 0.75F;
                                var10009[3] = var74;
                                var10009[2] = var98;
                                var10009[1] = var10002;
                                var10009[0] = var52;
                                var31.w(var10009);
                            }

                            if (var37 && !var44.l.isEmpty()) {
                                int var87 = var44.l.size();
                                float var75 = 9.6F;
                                float var76 = var62 - var75 - 2.0F;
                                float var77 = var50 - var87 * 12.0F / 2.0F + 1.0F;

                                for (int var78 = 0; var78 < var87; var78++) {
                                    ItemStack var92 = (ItemStack)var44.l.get(var78);
                                    float var94 = var77 + var78 * 12.0F;
                                    Object[] var113 = new Object[]{null, null, null, null, var52, 84098110997245L};
                                    var113[3] = 0.6F;
                                    var113[2] = var76;
                                    var113[1] = var94;
                                    var113[0] = var92;
                                    MatrixStack.a_boolean_______________________________________(var113);
                                }

                                if (var38 && !var44.m.isEmpty()) {
                                    int var88 = this.h.getValue()
                                        ? (var43.getMainHandStack().isEmpty() ? 0 : 1) + (var43.getOffHandStack().isEmpty() ? 0 : 1)
                                        : 0;

                                    for (int var79 = var88; var79 < var87; var79++) {
                                        float var80 = var77 + var79 * 12.0F;
                                        float var81 = 0.0F;
                                        ItemStack var82 = (ItemStack)var44.l.get(var79);

                                        for (ItemLabel var84 : (Iterable<ItemLabel>)(var44.m)) {
                                            if (var84.a == var82) {
                                                var10002 = var84.b;
                                                var101 = var76 - var32 * 0.5F - var81;
                                                Color var111 = NameTagsUtil.t;
                                                Object[] var126 = new Object[]{null, null, 85465053841486L};
                                                var126[1] = var49;
                                                var126[0] = var111;
                                                var10009 = new Object[]{null, null, null, null, null, a(var126), 49503024803380L};
                                                var10009[4] = 0.5F;
                                                var10009[3] = var101;
                                                var10009[2] = var80;
                                                var10009[1] = var10002;
                                                var10009[0] = var52;
                                                var31.w(var10009);
                                                var81 += var32 * 0.5F;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent22 var1) {
        this.r = var1.a(new Object[0]);
    }

    @Subscribe
    public void a(EntityEvent5 var1) {
        if (var1.a(new Object[0]) != null && var1.a(new Object[0]) != b.player && Prestige.i.a_boolean_(new Object[]{var1.a(new Object[0]), 54363558999103L})) {
            var1.b(new Object[]{Text.empty()});
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(EntityEvent6 var1) {
        Entity var6 = var1.a(new Object[0]);
        if (var6 != null) {
            if (Prestige.i.a_boolean_(new Object[]{var6, 54363558999103L})) {
                var1.a_void_____(new Object[0]);
            }
        }
    }

    private static Color a(Object[] var0) {
        Color var4 = (Color)var0[0];
        float var3 = (Float)var0[1];
        long var1 = (Long)var0[2];
        var1 = 101720657445394L ^ var1;
        long var5 = var1 ^ 56555732162766L;
        float var10000 = var4.getAlpha();
        Object[] var10006 = new Object[]{null, null, null, var5};
        var10006[2] = 1.0F;
        var10006[1] = 0.0F;
        var10006[0] = var3;
        int var7 = Math.round(var10000 * MathHelper4.a(var10006));
        return new Color(var4.getRed(), var4.getGreen(), var4.getBlue(), var7);
    }

    private List b_List______(Object[] var1) {
        ItemStack var4 = (ItemStack)var1[0];
        Event.b();
        ArrayList var6 = new ArrayList();
        ItemEnchantmentsComponent var7 = EnchantmentHelper.getEnchantments(var4);
        if (var7.isEmpty()) {
            return var6;
        } else {
            for (RegistryEntry var9 : var7.getEnchantments()) {
                if (!var9.getKey().isEmpty()) {
                    String var10 = (String)var9.getKey().map(lpL365_0 -> ((net.minecraft.registry.RegistryKey)lpL365_0).getValue().getPath()).orElse("Unknown");
                    var10 = var10.length() > 3 ? var10.substring(0, 3) : var10;
                    var10 = var10 + " " + var7.getLevel(var9);
                    var6.add(new ItemLabel(var4, var10));
                }
            }

            return var6;
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
