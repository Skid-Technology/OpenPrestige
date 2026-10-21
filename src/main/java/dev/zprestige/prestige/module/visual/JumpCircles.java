package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.JumpCirclesHelper;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class JumpCircles extends Module {
    private BoolSetting d;
    private FloatSetting a;
    private IntSetting c;
    private FloatSetting e;
    private FloatSetting f;
    private BoolSetting g;
    private ColorSetting h;
    private Map i = new HashMap();
    private Map j = new HashMap();
    private List k = new ArrayList();
    private static final long m = 3161348516882678015L;

    public JumpCircles() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.h.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL157_0 -> !(Boolean)this.g.getValue(), 119980880492450L});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null) {
            HashSet var9 = new HashSet();

            for (PlayerEntity var11 : b.world.getPlayers()) {
                int var12 = var11.getId();
                var9.add(var12);
                boolean var13 = (boolean)this.i.getOrDefault(var12, false);
                boolean var14 = var11.isOnGround();
                Vec3d var15 = this.a(new Object[]{var11, 114333519071826L});
                double var16 = var15.y;
                double var18 = (double)this.j.getOrDefault(var12, var16);
                double var20 = var16 - var18;
                if ((!(Boolean)this.d.getValue() || var11 == b.player) && var13 && !var14 && var20 > 0.05) {
                    this.k.add(new JumpCirclesHelper(new Vec3d(var15.x, var18, var15.z), (Integer)this.c.getValue(), 74345798570754L));
                }

                this.i.put(var12, var14);
                this.j.put(var12, var16);
            }

            this.i.keySet().retainAll(var9);
            this.j.keySet().retainAll(var9);
        }
    }

    private Vec3d a(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        if (var4 == b.player) {
            return new Vec3d(var4.getX(), var4.getY(), var4.getZ());
        } else {
            try {
                return var4.getTrackedPosition().withDelta(0L, 0L, 0L);
            } catch (Throwable var5) {
                return new Vec3d(var4.getX(), var4.getY(), var4.getZ());
            }
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        long var11 = System.currentTimeMillis();
        Event.b();
        this.k.removeIf(lpL156_0 -> var11 - ((dev.zprestige.prestige.math.JumpCirclesHelper)lpL156_0).b >= ((dev.zprestige.prestige.math.JumpCirclesHelper)lpL156_0).c);
        if (!this.k.isEmpty()) {
            Color var13 = this.g.getValue() ? MathHelper4.d(new Object[]{122780339483702L}) : (Color)this.h.getValue();
            Color var14 = var13.brighter();
            float var15 = (Float)this.e.getValue();
            float var16 = (Float)this.f.getValue();
            float var17 = (Float)this.a.getValue();

            for (JumpCirclesHelper var19 : (Iterable<JumpCirclesHelper>)(this.k)) {
                float var20 = (float)(var11 - var19.b) / var19.c;
                if (!(var20 < 0.0F) && !(var20 > 1.0F)) {
                    float var21 = 0.1F + (var17 - 0.1F) * b_float_______________(new Object[]{var20});
                    float var22;
                    if (var20 < 0.2F) {
                        var22 = var20 / 0.2F;
                    } else {
                        var22 = 1.0F - (var20 - 0.2F) / 0.8F;
                    }

                    var22 = Math.max(0.0F, Math.min(1.0F, var22));
                    Object[] var10004 = new Object[]{null, null, 130394026981283L};
                    var10004[1] = var22;
                    var10004[0] = var14;
                    Color var23 = a_Color____(var10004);
                    var10004 = new Object[]{null, null, 130394026981283L};
                    var10004[1] = var22;
                    var10004[0] = var13;
                    Color var24 = a_Color____(var10004);
                    MatrixCameraData var10000 = var1.b;
                    RenderHelper8 var10001 = var1.a;
                    float var10002 = (float)var19.a.x;
                    float var10003 = (float)var19.a.y;
                    float var27 = (float)var19.a.z;
                    Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, var23, var24, 84681243398314L};
                    var10012[7] = var16;
                    var10012[6] = var15;
                    var10012[5] = var21;
                    var10012[4] = var27;
                    var10012[3] = var10003;
                    var10012[2] = var10002;
                    var10012[1] = var10001;
                    var10012[0] = var10000;
                    RenderState.f(var10012);
                }
            }
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        this.k.clear();
        this.i.clear();
        this.j.clear();
    }

    private static float b_float_______________(Object[] var0) {
        float var1 = (Float)var0[0];
        return 1.0F - (1.0F - var1) * (1.0F - var1);
    }

    private static Color a_Color____(Object[] var0) {
        Color var1 = (Color)var0[0];
        float var2 = (Float)var0[1];
        int var5 = Math.max(0, Math.min((int)m, (int)(var1.getAlpha() * var2)));
        return new Color(var1.getRed(), var1.getGreen(), var1.getBlue(), var5);
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
