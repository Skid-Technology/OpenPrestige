package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.IntEvent4;
import dev.zprestige.prestige.event.TickEvent10;
import dev.zprestige.prestige.event.TickEvent12;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.util.BindManager;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class SilentAim extends Module implements RenderContract3 {
    private BoolSetting d;
    private BoolSetting a;
    private BoolSetting c;
    private FloatSetting e;
    private FloatSetting f;
    private MultiSetting g;
    private BoolSetting h;
    private FloatSetting i;
    private ColorSetting j;
    private boolean k = false;
    private boolean l = false;
    private Vec3d m = null;
    private Entity n = null;
    private static final String[] o = new String[]{"Head", "Neck", "Body", "Arms", "Thighs", "Legs", "Feet"};
    private static final double[] p = new double[]{0.95, 0.85, 0.7, 0.65, 0.35, 0.2, 0.05};

    public SilentAim() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.j.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL394_0 -> (Boolean)this.h.getValue(), 119980880492450L});
        this.i.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL395_0 -> (Boolean)this.h.getValue(), 35046756052036L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal, Gamemode.Mace);
    }

    private boolean d(Object[] var1) {
        return KillAura.a != null && KillAura.a.isEnabled();
    }

    private boolean a_boolean_________________________(Object[] var1) {
        RenderBatch var2 = (RenderBatch)var1[0];
        long var3 = (Long)var1[1];
        var3 = 22842548124311L ^ var3;
        long var5 = var3 ^ 59167800334406L;
        float var10000 = var2.a(new Object[0]) - b.player.getYaw();
        Object[] var10003 = new Object[]{null, var5};
        var10003[0] = var10000;
        return Math.abs(WorldUtil.b_float_____________________(var10003)) > (Float)this.e.getValue()
            || Math.abs(var2.b(new Object[0]) - b.player.getPitch()) > (Float)this.f.getValue();
    }

    private Entity a_class_1297__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 22842548124311L ^ var2;
        long var4 = var2 ^ 112637578291482L;
        long var6 = var2 ^ 71774879138066L;
        long var8 = var2 ^ 118837828270020L;
        Entity var11 = this.d.getValue() ? CombatUtil.a_class_1657(new Object[]{var6}) : CombatUtil.a_class_1297(new Object[]{var8});
        return (Entity)(var11 != null
                && var11.isAlive()
                && !(b.player.distanceTo((Entity)var11) > 3.0F)
                && b.player.canSee((Entity)var11)
                && !WorldUtil2.a(new Object[]{var11, var4})
            ? var11
            : null);
    }

    @Subscribe
    public void a(BoolEvent var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
                if (this.l) {
                    var1.b(new Object[]{true});
                } else if (!this.d(new Object[]{62715097032096L})) {
                    if (InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})
                        || b.player.getMainHandStack().getItem() instanceof AxeItem
                        || InventoryUtil.o(new Object[]{b.player.getMainHandStack(), 133561865349436L})
                        || !(Boolean)this.a.getValue()) {
                        HitResult var19 = b.crosshairTarget;
                        if (var19 != null
                            && (
                                var19.getType() == Type.MISS
                                    || var19.getType() == Type.BLOCK && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})
                            )) {
                            Entity var20 = this.a_class_1297__(new Object[]{99955001984413L});
                            if (var20 != null) {
                                RenderBatch var21 = WorldUtil.a_RenderBatch_(
                                    new Object[]{this.a_class_243____________(new Object[]{var20, 18962153507676L}), 6563042098863L}
                                );
                                if (!this.a_boolean_________________________(new Object[]{var21, 70854682346336L})) {
                                    this.n = var20;
                                    this.k = true;
                                    var1.a_void_____(new Object[0]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 136623400771732L;
        long var6 = var2 ^ 76064937642809L;
        long var8 = var2 ^ 23511072369188L;
        long var10 = var2 ^ 130306898807321L;
        long var12 = var2 ^ 104129184818405L;
        Event.b();
        Entity var15 = this.n;
        this.n = null;
        if (var15 != null && !var15.isAlive()) {
            var15 = null;
        }

        if (this.d(new Object[]{var10})) {
            this.k = false;
            this.m = null;
            return null;
        } else {
            if ((Boolean)this.c.getValue()) {
                if (var15 == null) {
                    var15 = this.a_class_1297__(new Object[]{var8});
                }

                RenderBatch var16 = var15 != null ? this.b_RenderBatch____(new Object[]{var15, var4}) : null;
                if (var16 != null) {
                    this.m = this.a_class_243____________(new Object[]{var15, var12});
                    if (this.k) {
                        this.k = false;
                        this.j(new Object[]{var15, var6});
                    }

                    return var16;
                }

                this.m = null;
            }

            if (!this.k) {
                return null;
            } else {
                this.k = false;
                if (var15 == null) {
                    return null;
                } else {
                    RenderBatch var17 = this.b_RenderBatch____(new Object[]{var15, var4});
                    if (var17 == null) {
                        return null;
                    } else {
                        this.m = this.a_class_243____________(new Object[]{var15, var12});
                        this.j(new Object[]{var15, var6});
                        return var17;
                    }
                }
            }
        }
    }

    private RenderBatch b_RenderBatch____(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 22842548124311L ^ var3;
        long var5 = var3 ^ 16842690077610L;
        long var7 = var3 ^ 91460942048645L;
        long var9 = var3 ^ 59580249833190L;
        long var11 = var3 ^ 38443984227093L;
        long var13 = var3 ^ 113798511916762L;
        long var15 = var3 ^ 103868017335430L;
        if (!InventoryUtil.a(new Object[]{b.player.getMainHandStack(), var7})
            && !(b.player.getMainHandStack().getItem() instanceof AxeItem)
            && !InventoryUtil.o(new Object[]{b.player.getMainHandStack(), var15})
            && (Boolean)this.a.getValue()) {
            return null;
        } else {
            HitResult var18 = b.crosshairTarget;
            if ((Boolean)this.c.getValue()
                || var18 != null
                    && (var18.getType() == Type.MISS || var18.getType() == Type.BLOCK && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), var7}))) {
                if (var2 != null && var2.isAlive() && !(b.player.distanceTo(var2) > 3.0F) && b.player.canSee(var2) && !WorldUtil2.a(new Object[]{var2, var5})) {
                    RenderBatch var19 = WorldUtil.a_RenderBatch_(new Object[]{this.a_class_243____________(new Object[]{var2, var9}), var11});
                    return this.a_boolean_________________________(new Object[]{var19, var13}) ? null : var19;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    private Vec3d a_class_243____________(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 22842548124311L ^ var2;
        long var5 = var2 ^ 51117056424225L;
        Event.b();
        Vec3d var8 = b.player.getEyePos();
        Box var9 = var4.getBoundingBox();
        double var10 = var9.maxY - var9.minY;
        if (var10 <= 0.0) {
            return var4.getEyePos();
        } else {
            double var12 = MathHelper.clamp(var8.x, var9.minX, var9.maxX);
            double var14 = MathHelper.clamp(var8.z, var9.minZ, var9.maxZ);
            double var16 = (var8.x - var12) * (var8.x - var12) + (var8.z - var14) * (var8.z - var14);
            double var18 = Double.POSITIVE_INFINITY;
            Vec3d var20 = null;

            for (int var21 = 0; var21 < o.length; var21++) {
                if (this.g.isSelected(new Object[]{o[var21], var5})) {
                    double var22 = var9.minY + p[var21] * var10;
                    double var24 = var8.y - var22;
                    double var26 = var16 + var24 * var24;
                    if (var26 < var18) {
                        var18 = var26;
                        var20 = new Vec3d(var12, var22, var14);
                    }
                }
            }

            return var20 != null ? var20 : var4.getEyePos();
        }
    }

    private void j(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 22842548124311L ^ var2;
        long var5 = var2 ^ 124294200138399L;
        long var7 = var2 ^ 3195960238897L;
        if (var4 != null) {
            new TickEvent12().b_boolean__(new Object[]{var5});
            this.l = true;
            InventoryUtil2.a_void_________(new Object[]{var4, var7});
            this.l = false;
        }
    }

    @Subscribe
    public void a(IntEvent4 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (!this.d(new Object[]{62715097032096L})) {
                if (var1.a(new Object[0]) == 0 && var1.b(new Object[0]) == 1) {
                    BindManager var10000 = Prestige.q;
                    int var10001 = var1.a(new Object[0]);
                    Object[] var10004 = new Object[]{null, 128352941745080L};
                    var10004[0] = var10001;
                    if (!var10000.a(var10004)) {
                        HitResult var19 = b.crosshairTarget;
                        if (var19 != null && var19.getType() == Type.BLOCK && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                            Entity var20 = this.a_class_1297__(new Object[]{99955001984413L});
                            if (var20 == null) {
                                return;
                            }

                            RenderBatch var21 = WorldUtil.a_RenderBatch_(
                                new Object[]{this.a_class_243____________(new Object[]{var20, 18962153507676L}), 6563042098863L}
                            );
                            if (this.a_boolean_________________________(new Object[]{var21, 70854682346336L})) {
                                return;
                            }

                            this.n = var20;
                            this.k = true;
                            var1.a_void_____(new Object[0]);
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent10 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            if (!this.d(new Object[]{62715097032096L})) {
                HitResult var17 = b.crosshairTarget;
                if (var17 != null && var17.getType() == Type.BLOCK && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                    Entity var18 = this.a_class_1297__(new Object[]{99955001984413L});
                    if (var18 == null) {
                        return;
                    }

                    RenderBatch var19 = WorldUtil.a_RenderBatch_(
                        new Object[]{this.a_class_243____________(new Object[]{var18, 18962153507676L}), 6563042098863L}
                    );
                    if (this.a_boolean_________________________(new Object[]{var19, 70854682346336L})) {
                        return;
                    }

                    var1.a_void_____(new Object[0]);
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.h.getValue()) {
            Vec3d var9 = this.m;
            if (var9 != null) {
                float var10 = (Float)this.i.getValue();
                float var11 = (float)(var9.x - var10);
                float var12 = (float)(var9.y - var10);
                float var13 = (float)(var9.z - var10);
                float var14 = (float)(var9.x + var10);
                float var15 = (float)(var9.y + var10);
                float var16 = (float)(var9.z + var10);
                Color var17 = (Color)this.j.getValue();
                Color var18 = new Color(var17.getRed(), var17.getGreen(), var17.getBlue(), Math.min(255, var17.getAlpha() + 55));
                MatrixCameraData var10000 = var1.b;
                RenderHelper8 var10001 = var1.a;
                Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var17, 108390846498585L};
                var10011[7] = var16;
                var10011[6] = var15;
                var10011[5] = var14;
                var10011[4] = var13;
                var10011[3] = var12;
                var10011[2] = var11;
                var10011[1] = var10001;
                var10011[0] = var10000;
                RenderState.h(var10011);
                var10000 = var1.b;
                var10001 = var1.a;
                var10011 = new Object[]{null, null, null, null, null, null, null, null, var18, 119015971454350L};
                var10011[7] = var16;
                var10011[6] = var15;
                var10011[5] = var14;
                var10011[4] = var13;
                var10011[3] = var12;
                var10011[2] = var11;
                var10011[1] = var10001;
                var10011[0] = var10000;
                RenderState.g(var10011);
            }
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        this.m = null;
        this.n = null;
        this.k = false;
        this.l = false;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        this.m = null;
        this.n = null;
        this.k = false;
        this.l = false;
        Prestige.o.f(new Object[]{this, var4});
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
