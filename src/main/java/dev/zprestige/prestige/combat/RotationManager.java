package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.EntityEvent4;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.type.EventPhase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.util.math.MathHelper;

public class RotationManager implements RenderContract2 {
    private static final float a = 0.4F;
    private static final float c = 0.5F;
    private boolean d;
    private float e;
    private float f;
    private float g;
    private float h;
    private boolean i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;

    public RotationManager(long var1) {
        super();
        var1 = 0L;
        var1 = 60632727332545L ^ var1;
        long var3 = var1 ^ 14681560044837L;
        this.d = false;
        this.i = false;
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player == null) {
            this.d = false;
        } else if (!this.d) {
            this.e = b.player.getYaw();
            this.f = b.player.getPitch();
            this.g = this.e;
            this.h = this.f;
            this.d = true;
        } else {
            RenderBatch var5 = Prestige.o.b_RenderBatch____________(new Object[0]);
            if (var5 == null) {
                this.e = b.player.getYaw();
                this.f = b.player.getPitch();
                this.g = this.e;
                this.h = this.f;
            } else {
                this.g = this.e;
                this.h = this.f;
                float var6 = MathHelper.wrapDegrees(var5.a(new Object[0]) - this.e);
                float var7 = var5.b(new Object[0]) - this.f;
                this.e = MathHelper.wrapDegrees(this.e + var6 * 0.4F);
                this.f = MathHelper.clamp(this.f + var7 * 0.4F, -90.0F, 90.0F);
            }
        }
    }

    @Subscribe
    public void a(EntityEvent4 var1) {
        if (var1.a == b.player && this.d) {
            if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
                if (this.i) {
                    return;
                }

                if (Prestige.o.b_RenderBatch____________(new Object[0]) == null) {
                    return;
                }

                float var5 = Math.abs(MathHelper.wrapDegrees(this.e - b.player.getYaw()));
                float var6 = Math.abs(this.f - b.player.getPitch());
                if (var5 < 0.5F && var6 < 0.5F) {
                    return;
                }

                this.j = b.player.getYaw();
                this.k = b.player.getPitch();
                this.l = b.player.bodyYaw;
                this.m = b.player.headYaw;
                this.n = b.player.lastYaw;
                this.o = b.player.lastPitch;
                this.p = b.player.lastBodyYaw;
                this.q = b.player.lastHeadYaw;
                b.player.setYaw(this.e);
                b.player.setPitch(this.f);
                b.player.bodyYaw = this.e;
                b.player.headYaw = this.e;
                b.player.lastYaw = this.g;
                b.player.lastPitch = this.h;
                b.player.lastBodyYaw = this.g;
                b.player.lastHeadYaw = this.g;
                this.i = true;
            } else if (var1.a_EventPhase(new Object[0]) == EventPhase.POST && this.i) {
                b.player.setYaw(this.j);
                b.player.setPitch(this.k);
                b.player.bodyYaw = this.l;
                b.player.headYaw = this.m;
                b.player.lastYaw = this.n;
                b.player.lastPitch = this.o;
                b.player.lastBodyYaw = this.p;
                b.player.lastHeadYaw = this.q;
                this.i = false;
            }
        }
    }

    public boolean a(Object[] var1) {
        return this.i;
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
