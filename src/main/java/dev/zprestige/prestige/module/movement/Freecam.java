package dev.zprestige.prestige.module.movement;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.DoubleEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderHelper35;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.world.PositionUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Freecam extends Module {
    public Freecam() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private FloatSetting a;
    private BoolSetting d;
    private Vec3d c = Vec3d.ZERO;
    private Vec3d e = Vec3d.ZERO;
    private Vec3d f = Vec3d.ZERO;
    private float g = 0.0F;
    private float h = 0.0F;
    private Vec3d i = Vec3d.ZERO;

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 26013233570683L;
        if (b.player != null) {
            this.c = b.player.getEyePos();
            this.e = this.c;
            this.f = PositionUtil.b(new Object[]{var4});
            this.g = b.player.getYaw();
            this.h = b.player.getPitch();
            this.i = b.player.getVelocity();
            b.player.setVelocity(Vec3d.ZERO);
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        if (b.player != null) {
            b.player.setVelocity(this.i);
        }

        this.i = Vec3d.ZERO;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.currentScreen == null) {
            b.options.forwardKey.setPressed(false);
            b.options.backKey.setPressed(false);
            b.options.leftKey.setPressed(false);
            b.options.rightKey.setPressed(false);
            b.options.jumpKey.setPressed(false);
            b.options.sneakKey.setPressed(false);
            b.player.setSneaking(false);
            float var7 = (float) (Math.PI / 180.0);
            float var8 = (float) Math.PI;
            float var9 = MathHelper.cos(-b.player.getYaw() * var7 - var8);
            float var10 = MathHelper.sin(-b.player.getYaw() * var7 - var8);
            Vec3d var11 = new Vec3d(-var10, 0.0, -var9);
            Vec3d var12 = new Vec3d(0.0, 1.0, 0.0);
            Vec3d var13 = var12.crossProduct(var11);
            Vec3d var14 = var11.crossProduct(var12);
            Vec3d var15 = Vec3d.ZERO;
            if (GLFW.glfwGetKey(b.getWindow().getHandle(), new RenderHelper35(b.options.forwardKey).a(new Object[]{138149021417492L}).getCode()) == 1) {
                var15 = var15.add(var11);
            }

            if (GLFW.glfwGetKey(b.getWindow().getHandle(), new RenderHelper35(b.options.backKey).a(new Object[]{138149021417492L}).getCode()) == 1) {
                var15 = var15.subtract(var11);
            }

            if (GLFW.glfwGetKey(b.getWindow().getHandle(), new RenderHelper35(b.options.leftKey).a(new Object[]{138149021417492L}).getCode()) == 1) {
                var15 = var15.add(var13);
            }

            if (GLFW.glfwGetKey(b.getWindow().getHandle(), new RenderHelper35(b.options.rightKey).a(new Object[]{138149021417492L}).getCode()) == 1) {
                var15 = var15.add(var14);
            }

            if (GLFW.glfwGetKey(b.getWindow().getHandle(), new RenderHelper35(b.options.jumpKey).a(new Object[]{138149021417492L}).getCode()) == 1) {
                var15 = var15.add(0.0, ((Float)this.a.getValue()).floatValue(), 0.0);
            }

            if (GLFW.glfwGetKey(b.getWindow().getHandle(), new RenderHelper35(b.options.sneakKey).a(new Object[]{138149021417492L}).getCode()) == 1) {
                var15 = var15.add(0.0, -(Float)this.a.getValue(), 0.0);
            }

            var15 = var15.normalize()
                .multiply(
                    (Float)this.a.getValue()
                        * (
                            GLFW.glfwGetKey(b.getWindow().getHandle(), new RenderHelper35(b.options.sprintKey).a(new Object[]{138149021417492L}).getCode())
                                    == 1
                                ? 2.0F
                                : 1.0F
                        )
                );
            this.e = this.c;
            this.c = this.c.add(var15);
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
            if (b.player != null) {
                b.player.setPosition(this.f.x, this.f.y, this.f.z);
                b.player.setVelocity(Vec3d.ZERO);
                var1.b(new Object[]{this.g});
                var1.c(new Object[]{this.h});
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof ClientCommandC2SPacket var5) {
            Mode var8 = var5.getMode();
            boolean var7 = var8 == Mode.START_SPRINTING || var8 == Mode.STOP_SPRINTING;
            if (var7) {
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(DoubleEvent var1) {
        float var6 = MatrixStack.a_float_______________(new Object[]{601921721859L});
        var1.b(new Object[]{(double)MathHelper.lerp(var6, (float)this.e.x, (float)this.c.x)});
        var1.c(new Object[]{(double)MathHelper.lerp(var6, (float)this.e.y, (float)this.c.y)});
        var1.d(new Object[]{(double)MathHelper.lerp(var6, (float)this.e.z, (float)this.c.z)});
        var1.a_void_____(new Object[0]);
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.d.getValue()) {
            float var13 = (float)this.f.x;
            float var14 = (float)this.f.y;
            float var15 = (float)this.f.z;
            MatrixCameraData var10000 = var1.b;
            RenderHelper8 var10001 = var1.a;
            float var10002 = var13 - 0.3F;
            float var10004 = var15 - 0.3F;
            float var10005 = var13 + 0.3F;
            float var10006 = var14 + b.player.getHeight();
            float var10007 = var15 + 0.3F;
            Color var10008 = MathHelper4.d(new Object[]{122780339483702L});
            Object[] var10012 = new Object[]{null, null, 20220661505083L};
            var10012[1] = 0.1F;
            var10012[0] = var10008;
            Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.b(var10012), 108390846498585L};
            var10011[7] = var10007;
            var10011[6] = var10006;
            var10011[5] = var10005;
            var10011[4] = var10004;
            var10011[3] = var14;
            var10011[2] = var10002;
            var10011[1] = var10001;
            var10011[0] = var10000;
            RenderState.h(var10011);
            var10000 = var1.b;
            var10001 = var1.a;
            var10002 = var13 - 0.3F;
            var10004 = var15 - 0.3F;
            var10005 = var13 + 0.3F;
            var10006 = var14 + b.player.getHeight();
            var10007 = var15 + 0.3F;
            var10011 = new Object[]{null, null, null, null, null, null, null, null, MathHelper4.d(new Object[]{122780339483702L}), 119015971454350L};
            var10011[7] = var10007;
            var10011[6] = var10006;
            var10011[5] = var10005;
            var10011[4] = var10004;
            var10011[3] = var14;
            var10011[2] = var10002;
            var10011[1] = var10001;
            var10011[0] = var10000;
            RenderState.g(var10011);
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
