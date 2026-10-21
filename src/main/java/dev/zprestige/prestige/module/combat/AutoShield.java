package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.LivingEvent;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper35;
import dev.zprestige.prestige.world.PositionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class AutoShield extends Module {
    private FloatSetting a;
    private FloatSetting c;
    private FloatSetting d;
    private FloatSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private RenderHelper27 h = new RenderHelper27(26293358099357L);
    private RenderHelper27 i = new RenderHelper27(26293358099357L);
    private RenderHelper27 j = new RenderHelper27(26293358099357L);
    private boolean k = false;
    public static boolean l = false;

    public AutoShield() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.g.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL288_0 -> (Boolean)this.f.getValue(), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (this.k) {
            RenderHelper27 var10000 = this.j;
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = 5000.0F;
            if (!var10000.a(var10004)) {
                this.l(new Object[]{89153285792718L});
                return;
            }

            this.k = false;
            l = false;
        }

        float var27 = (Float)this.c.getValue();
        Object[] var10003 = new Object[]{null, 109615191536188L};
        var10003[0] = var27;
        PlayerEntity var25 = CombatUtil.b(var10003);
        RenderHelper27 var28 = this.i;
        Object[] var30 = new Object[]{null, 2505922520844L};
        var30[0] = 100.0F;
        if (var28.a(var30)) {
            if (var25 == null) {
                this.l(new Object[]{89153285792718L});
            } else if (b.player.distanceTo(var25) > (Float)this.a.getValue()) {
                this.l(new Object[]{89153285792718L});
            } else if (b.player.getMainHandStack().getItem() == Items.SHIELD
                || b.player.getOffHandStack().getItem() == Items.SHIELD && !InventoryUtil2.e(new Object[]{17574514088175L})) {
                if (PositionUtil.a_boolean________________________________________________(new Object[]{120169006001489L})) {
                    this.l(new Object[]{89153285792718L});
                } else if (var25.getMainHandStack().getItem() instanceof AxeItem) {
                    this.k(new Object[]{15123390343570L});
                } else if (!InventoryUtil.a(new Object[]{var25.getMainHandStack(), 127680976181311L})
                    && !InventoryUtil.o(new Object[]{var25.getMainHandStack(), 133561865349436L})) {
                    this.k(new Object[]{15123390343570L});
                } else {
                    RenderHelper27 var29 = this.h;
                    float var10001 = 625.0F * (0.94F - (Float)this.e.getValue() / 100.0F);
                    var30 = new Object[]{null, 2505922520844L};
                    var30[0] = var10001;
                    boolean var26 = var29.a(var30);
                    if (!b.player.isOnGround() && b.player.fallDistance > 0.0 && b.player.getAttackCooldownProgress(0.0F) > 0.94F) {
                        this.k(new Object[]{15123390343570L});
                    } else if (!var26 || !this.a(new Object[]{var25, 105503317375266L}) && !(b.player.distanceTo(var25) <= 3.0F)) {
                        if (!b.player.isOnGround() && b.player.fallDistance <= 0.0) {
                            this.j(new Object[]{130027305938123L});
                        } else {
                            this.k(new Object[]{15123390343570L});
                        }
                    } else {
                        this.j(new Object[]{130027305938123L});
                    }
                }
            } else {
                this.l(new Object[]{89153285792718L});
            }
        }
    }

    @Subscribe
    public void a(LivingEvent var1) {
        if ((Boolean)this.f.getValue()) {
            if (var1.a == b.player) {
                this.j.a_void_____________________(new Object[]{93540861878173L});
                this.k = true;
                l = true;
                this.l(new Object[]{89153285792718L});
                if ((Boolean)this.g.getValue()) {
                    for (int var13 = 0; var13 < 9; var13++) {
                        if (InventoryUtil.a(new Object[]{b.player.getInventory().getStack(var13), 127680976181311L})) {
                            Object[] var10003 = new Object[]{null, 37392877673079L};
                            var10003[0] = var13;
                            InventoryUtil.a_void________(var10003);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (b.world != null) {
            if (var1.a(new Object[0]) instanceof PlayerRespawnS2CPacket) {
                this.k = false;
                l = false;
            } else {
                if (var1.a(new Object[0]) instanceof EntityAnimationS2CPacket) {
                    int var9 = ((EntityAnimationS2CPacket)var1.a(new Object[0])).getEntityId();
                    Entity var10 = b.world.getEntityById(var9);
                    if (var10 instanceof PlayerEntity) {
                        float var10000 = (Float)this.c.getValue();
                        Object[] var10003 = new Object[]{null, 109615191536188L};
                        var10003[0] = var10000;
                        PlayerEntity var11 = CombatUtil.b(var10003);
                        if (var11 == null) {
                            return;
                        }

                        if (var11.equals(var10)) {
                            this.h.a_void_____________________(new Object[]{93540861878173L});
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(EntityEvent var1) {
        if (b.player.isUsingItem()) {
            var1.a_void_____(new Object[0]);
        }

        this.i.a_void_____________________(new Object[]{93540861878173L});
    }

    private boolean a(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 75187027837503L ^ var3;
        long var5 = var3 ^ 41844236867185L;
        long var7 = var3 ^ 123274899748192L;
        Event.b();
        Vec3d var10 = new Vec3d(var2.getX() - var2.lastX, var2.getY() - var2.lastY, var2.getZ() - var2.lastZ);
        double var11 = var10.length();
        if (var11 < (Float)this.d.getValue() / 10.0F) {
            return false;
        } else {
            Vec3d var13 = PositionUtil.b(new Object[]{var7}).subtract(PositionUtil.a(new Object[]{var2, var5})).normalize();
            Vec3d var14 = var10.normalize();
            double var15 = var14.dotProduct(var13);
            return var15 > 0.0;
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 130523224806571L;
        this.k = false;
        l = false;
        this.l(new Object[]{var4});
    }

    private void j(Object[] var1) {
        b.options.useKey.setPressed(true);
    }

    private void k(Object[] var1) {
        b.options.useKey.setPressed(false);
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 75187027837503L ^ var2;
        long var4 = var2 ^ 115247237112293L;
        Event.b();
        int var7 = new RenderHelper35(b.options.useKey).a(new Object[]{var4}).getCode();
        boolean var8;
        if (var7 < 8) {
            var8 = GLFW.glfwGetMouseButton(b.getWindow().getHandle(), var7) == 1;
        } else {
            var8 = GLFW.glfwGetKey(b.getWindow().getHandle(), var7) == 1;
        }

        b.options.useKey.setPressed(var8);
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
