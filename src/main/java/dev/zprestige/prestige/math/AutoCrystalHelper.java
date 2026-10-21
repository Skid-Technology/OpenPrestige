package dev.zprestige.prestige.math;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.module.combat.AutoCrystal;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class AutoCrystalHelper {
    private AutoCrystal a;
    private BlockPos b;
    private Box c;
    private float d = 0.0F;
    public long e = 0L;
    public long f;

    public AutoCrystalHelper(AutoCrystal var1, long var2) {
        this.a = var1;
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.a.i.getValue()) {
            this.e = System.currentTimeMillis() - this.f;
            this.f = System.currentTimeMillis();
            float var11 = (float)this.e * 0.005F;
            if (!(var11 > 1.0F)) {
                if (this.b != null) {
                    if (this.a.isEnabled()
                        && RenderContract2.b.player.getMainHandStack().isOf(Items.END_CRYSTAL)
                        && RenderContract2.b.crosshairTarget != null
                        && RenderContract2.b.crosshairTarget.getType() == Type.BLOCK) {
                        float var15 = this.d;
                        float var10002 = ((Color)this.a.j.getValue()).getAlpha() / 255.0F;
                        Object[] var23 = new Object[]{null, null, null, 127101728255981L};
                        var23[2] = var11;
                        var23[1] = var10002;
                        var23[0] = var15;
                        this.d = MathHelper4.b_float______(var23);
                    } else {
                        float var10001 = this.d;
                        Object[] var10006 = new Object[]{null, null, null, 127101728255981L};
                        var10006[2] = var11;
                        var10006[1] = 0.0F;
                        var10006[0] = var10001;
                        this.d = MathHelper4.b_float______(var10006);
                    }

                    if (this.d <= 0.01F) {
                        this.b = null;
                        return;
                    }

                    if (this.c == null) {
                        this.c = new Box(this.b);
                    }

                    this.c = this.c.offset((this.b.getX() - this.c.minX) * 0.05F, (this.b.getY() - this.c.minY) * 0.05F, (this.b.getZ() - this.c.minZ) * 0.05F);
                    Color var12 = new Color(
                        ((Color)this.a.j.getValue()).getRed(),
                        ((Color)this.a.j.getValue()).getGreen(),
                        ((Color)this.a.j.getValue()).getBlue(),
                        (int)(this.d / 4.0F * 255.0F)
                    );
                    Color var13 = new Color(
                        ((Color)this.a.j.getValue()).getRed(),
                        ((Color)this.a.j.getValue()).getGreen(),
                        ((Color)this.a.j.getValue()).getBlue(),
                        (int)(this.d * 255.0F)
                    );
                    MatrixCameraData var10000 = var1.b;
                    RenderHelper8 var16 = var1.a;
                    float var18 = (float)this.c.minX;
                    float var10003 = (float)this.c.minY;
                    float var10004 = (float)this.c.minZ;
                    float var10005 = (float)this.c.maxX;
                    float var24 = (float)this.c.maxY;
                    float var10007 = (float)this.c.maxZ;
                    Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var12, 108390846498585L};
                    var10011[7] = var10007;
                    var10011[6] = var24;
                    var10011[5] = var10005;
                    var10011[4] = var10004;
                    var10011[3] = var10003;
                    var10011[2] = var18;
                    var10011[1] = var16;
                    var10011[0] = var10000;
                    RenderState.h(var10011);
                    var10000 = var1.b;
                    var16 = var1.a;
                    var18 = (float)this.c.minX;
                    var10003 = (float)this.c.minY;
                    var10004 = (float)this.c.minZ;
                    var10005 = (float)this.c.maxX;
                    var24 = (float)this.c.maxY;
                    var10007 = (float)this.c.maxZ;
                    var10011 = new Object[]{null, null, null, null, null, null, null, null, var13, 119015971454350L};
                    var10011[7] = var10007;
                    var10011[6] = var24;
                    var10011[5] = var10005;
                    var10011[4] = var10004;
                    var10011[3] = var10003;
                    var10011[2] = var18;
                    var10011[1] = var16;
                    var10011[0] = var10000;
                    RenderState.g(var10011);
                }
            }
        }
    }

    public void a(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        this.b = var2;
    }

    public BlockPos a_class_2338(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        this.b = null;
        this.c = null;
        this.d = 0.0F;
        this.e = 0L;
        this.f = System.currentTimeMillis();
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
