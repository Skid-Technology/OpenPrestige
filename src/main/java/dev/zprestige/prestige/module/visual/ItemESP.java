package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent22;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.inventory.ItemESPHelper;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderTarget;
import dev.zprestige.prestige.render.RoundedPosColorShader;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.render.Frustum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class ItemESP extends Module {
    private FloatSetting a;
    private IntSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private ColorSetting f;
    private Frustum g;
    private Map h = new LinkedHashMap();
    private static final String l = " x";

    public ItemESP() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.f.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL155_0 -> (Boolean)this.e.getValue(), 119980880492450L});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            float var5 = (Float)this.d.getValue() * (Float)this.d.getValue();
            ArrayList var6 = new ArrayList();

            for (Entity var8 : b.world.getEntities()) {
                if (var8 instanceof ItemEntity var9 && !(var9.squaredDistanceTo(b.player) > var5)) {
                    ItemStack var10 = var9.getStack();
                    if (var10 != null && !var10.isEmpty()) {
                        ItemESPHelper var11 = new ItemESPHelper();
                        var11.b = var10;
                        String var12 = var10.getName().getString();
                        var11.a = var10.getCount() > 1 ? var12 + l + var10.getCount() : var12;
                        var6.add(Map.entry(var9, var11));
                    }
                }
            }

            var6.sort((lpL154_0, lpL154_1) -> Float.compare(b.player.distanceTo((Entity)((java.util.Map.Entry)lpL154_1).getKey()), b.player.distanceTo((Entity)((java.util.Map.Entry)lpL154_0).getKey())));
            this.h = new LinkedHashMap();

            for (Entry var14 : (Iterable<Entry>)(var6)) {
                this.h.put((ItemEntity)var14.getKey(), (ItemESPHelper)var14.getValue());
            }
        }
    }

    @Subscribe
    public void a(TickEvent25 var1) {
        if (b.world != null && b.player != null) {
            if (this.d(new Object[]{131824000084707L})) {
                float var23 = 10.0F;
                int var24 = (Integer)this.c.getValue();
                boolean var25 = (Boolean)this.e.getValue();
                Color var26 = var25 ? (Color)this.f.getValue() : null;
                Font var27 = Prestige.c.a(new Object[]{1236438218049L});
                float var28 = var27.c_float___(new Object[]{78913221670390L});
                int var29 = 0;

                for (Entry var31 : (Iterable<Entry>)(this.h.entrySet())) {
                    if (var29 >= var24) {
                        break;
                    }

                    ItemEntity var32 = (ItemEntity)var31.getKey();
                    ItemESPHelper var33 = (ItemESPHelper)var31.getValue();
                    if (!var32.isRemoved() && (this.g == null || this.g.isVisible(var32.getBoundingBox()))) {
                        float var34 = b.player.distanceTo(var32);
                        Vec3d var35 = MatrixStack.b(new Object[]{var32, 78245126833881L});
                        Vec3d var36 = MatrixStack.a_class_243_______________(
                            new Object[]{var35.add(0.0, var32.getHeight() + 0.4 * (1.0F + var34 / 12.0F), 0.0), 73023576373275L}
                        );
                        if (!(var36.z < 0.0) && !(var36.z >= 1.0)) {
                            var29++;
                            float var37 = (Float)this.a.getValue() - var34 / 500.0F;
                            if (var37 < 0.15F) {
                                var37 = 0.15F;
                            }

                            float var38 = var27.a_float______________(new Object[]{var33.a, 47859522476307L});
                            float var39 = 5.0F;
                            float var40 = 2.0F;
                            float var41 = 4.0F;
                            float var42 = (float)var36.x / var37;
                            float var43 = (float)var36.y / var37;
                            Matrix4f var44 = new Matrix4f().scale(var37, var37, var37);
                            float var45 = var38 + 2.0F * var39;
                            float var46 = var23 + 2.0F * var40;
                            float var47 = var42 - var45 / 2.0F;
                            float var48 = var43 - var40;
                            if (var25) {
                                RenderHelper8 var10000 = var1.a;
                                MatrixCameraData var10001 = var1.b;
                                Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 35553070502242L};
                                var10011[8] = var41;
                                var10011[7] = 5;
                                var10011[6] = var46;
                                var10011[5] = var45;
                                var10011[4] = var48;
                                var10011[3] = var47;
                                var10011[2] = var44;
                                var10011[1] = var10001;
                                var10011[0] = var10000;
                                RenderTarget.e(var10011);
                                var10000 = var1.a;
                                var10001 = var1.b;
                                var10011 = new Object[]{null, null, null, null, null, null, null, null, null, 16042616216793L};
                                var10011[8] = var41;
                                var10011[7] = var26;
                                var10011[6] = var46;
                                var10011[5] = var45;
                                var10011[4] = var48;
                                var10011[3] = var47;
                                var10011[2] = var44;
                                var10011[1] = var10001;
                                var10011[0] = var10000;
                                RoundedPosColorShader.c(var10011);
                            }

                            float var49 = var42 - var38 / 2.0F;
                            float var50 = var43 + (var23 - var28) / 2.0F;
                            String var10002 = var33.a;
                            Object[] var10008 = new Object[]{null, null, null, null, Color.WHITE, 36494305523424L};
                            var10008[3] = var50;
                            var10008[2] = var49;
                            var10008[1] = var10002;
                            var10008[0] = var44;
                            var27.v(var10008);
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent22 var1) {
        this.g = var1.a(new Object[0]);
    }

    private boolean d(Object[] var1) {
        return b.currentScreen == null || b.currentScreen instanceof ChatScreen;
    }

    private static String b(byte[] var0) {
        int var1 = 0;
        int var2;
        char[] var3 = new char[var2 = var0.length];

        for (int var4 = 0; var4 < var2; var4++) {
            int var5;
            if ((var5 = 255 & var0[var4]) < 192) {
                var3[var1++] = (char)var5;
            } else if (var5 < 224) {
                char var6 = (char)((char)(var5 & 31) << 6);
                byte var8 = var0[++var4];
                var6 = (char)(var6 | (char)(var8 & 63));
                var3[var1++] = var6;
            } else if (var4 < var2 - 2) {
                char var12 = (char)((char)(var5 & 15) << '\f');
                byte var9 = var0[++var4];
                var12 = (char)(var12 | (char)(var9 & 63) << 6);
                var9 = var0[++var4];
                var12 = (char)(var12 | (char)(var9 & 63));
                var3[var1++] = var12;
            }
        }

        return new String(var3, 0, var1);
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
