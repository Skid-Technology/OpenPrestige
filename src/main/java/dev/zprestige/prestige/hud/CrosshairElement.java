package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent14;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.MatrixStack;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.joml.Matrix4f;

public class CrosshairElement extends HudElement {
    private long a;
    private float g;
    private static final long H = 0L;

    public CrosshairElement(long var1) {
        super("Crosshair", "Modifies the crosshair to a custom one", (21270500652092L));
        var1 = 0L;
        var1 = 71572427898317L ^ var1;
        long var3 = var1 ^ 90435594832369L;
        this.a = H;
        this.g = 0.0F;
    }

    @Override
    public void m(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 115520954206759L;
        float var10001 = b.getWindow().getScaledWidth() / 2.0F;
        float var10002 = b.getWindow().getScaledHeight() / 2.0F;
        Object[] var10005 = new Object[]{null, null, var4};
        var10005[1] = var10002;
        var10005[0] = var10001;
        this.a_void_______(var10005);
    }

    @Override
    public void l(Object[] var1) {
        Matrix4f var6 = (Matrix4f)var1[2];
        long var2 = (Long)var1[3];
        long var7 = var2 ^ 36129254667080L;
        long var9 = var2 ^ 27807340928064L;
        long var11 = var2 ^ 6014253680539L;
        long var13 = var2 ^ 40687861210047L;
        long var15 = var2 ^ 28434091708354L;
        Object[] var10004 = new Object[]{null, 0.0F};
        var10004[0] = 0.0F;
        this.i(var10004);
        Event.b();
        float var18 = (float)Math.sqrt(var6.m00() * var6.m00() + var6.m01() * var6.m01() + var6.m02() * var6.m02());
        float var10002 = b.getWindow().getScaledWidth() / 2.0F / var18;
        float var10003 = b.getWindow().getScaledHeight() / 2.0F / var18;
        Object[] var10006 = new Object[]{null, null, var7};
        var10006[1] = var10003;
        var10006[0] = var10002;
        this.a_void_______(var10006);
        float var19 = (float)(System.currentTimeMillis() - this.a) * 0.005F;
        this.a = System.currentTimeMillis();
        boolean var20 = b.crosshairTarget instanceof EntityHitResult var22 && var22.getEntity() instanceof LivingEntity var21 && var21.hurtTime > 0;
        float var10001 = this.g;
        var10002 = var20 ? 1.0F : 0.0F;
        var10003 = var20 ? var19 * 3.0F : var19;
        var10006 = new Object[]{null, null, null, var11};
        var10006[2] = var10003;
        var10006[1] = var10002;
        var10006[0] = var10001;
        this.g = MathHelper4.b_float______(var10006);
        float var28 = ((float)Math.sin(System.currentTimeMillis() * 0.003) + 1.0F) * 0.5F;
        Color var29 = MathHelper4.d(new Object[]{var9});
        Color var31 = new Color(
            var29.getRed() + (int)((255 - var29.getRed()) * this.g),
            var29.getGreen() + (int)((255 - var29.getGreen()) * this.g),
            var29.getBlue() + (int)((255 - var29.getBlue()) * this.g)
        );
        short var32 = 27785;
        float var24 = this.e - 0.5F;
        long var33 = 3325764817997220311L ^ var2;
        float var25 = this.f - 0.5F;
        float var26 = 1.5F + var28 * 0.3F + this.g * 1.25F;
        int var27 = Math.min(255, 190 + (int)(65.0F * Math.max(var28 * 0.5F, this.g)));
        Object[] var10007 = new Object[]{null, null, null, null, new Color(0, 0, 0, 90), var15};
        var10007[3] = 0.9F;
        var10007[2] = var25;
        var10007[1] = var24;
        var10007[0] = var6;
        MatrixStack.c_void_(var10007);
        var10007 = new Object[]{null, null, null, null, var31, var15};
        var10007[3] = 0.5F;
        var10007[2] = var25;
        var10007[1] = var24;
        var10007[0] = var6;
        MatrixStack.c_void_(var10007);
        var10003 = var26 - 0.5F;
        var10007 = new Object[]{null, null, null, null, new Color(0, 0, 0, 60), var13};
        var10007[3] = var10003;
        var10007[2] = var25;
        var10007[1] = var24;
        var10007[0] = var6;
        MatrixStack.e(var10007);
        var10003 = var26 + 0.5F;
        var10007 = new Object[]{null, null, null, null, new Color(0, 0, 0, 60), var13};
        var10007[3] = var10003;
        var10007[2] = var25;
        var10007[1] = var24;
        var10007[0] = var6;
        MatrixStack.e(var10007);
        var10007 = new Object[]{null, null, null, null, new Color(var31.getRed(), var31.getGreen(), var31.getBlue(), var27), var13};
        var10007[3] = var26;
        var10007[2] = var25;
        var10007[1] = var24;
        var10007[0] = var6;
        MatrixStack.e(var10007);
    }

    @Subscribe
    public void a(TickEvent14 var1) {
        var1.a_void_____(new Object[0]);
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
