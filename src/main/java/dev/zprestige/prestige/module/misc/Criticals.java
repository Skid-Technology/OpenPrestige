package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.network.NetworkUtil;
import dev.zprestige.prestige.render.RenderUtil2;
import dev.zprestige.prestige.type.EventPhase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.PositionAndOnGround;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Vec3d;

public class Criticals extends Module {
    public Criticals() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private EnumSetting a;
    private Entity c = null;
    private int d = 0;
    private boolean i = false;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    @Subscribe
    public void a(EntityEvent var1) {
        if (this.d(new Object[]{37888275107808L}) && !this.i) {
            Entity var11 = var1.a(new Object[0]);
            if (var11 != null) {
                if (((String)this.a.getValue()).equals("Silent")) {
                    Object[] var10004 = new Object[]{null, 54655750177859L};
                    var10004[0] = 0.0625F;
                    this.j(var10004);
                    var10004 = new Object[]{null, 54655750177859L};
                    var10004[0] = 0.0F;
                    this.j(var10004);
                } else {
                    this.c = var11;
                    if (((String)this.a.getValue()).equals("Legit")) {
                        RenderUtil2.a(new Object[]{134150376721510L});
                    } else {
                        b.player.setVelocity(new Vec3d(b.player.getVelocity().x, b.player.getVelocity().y + 0.15, b.player.getVelocity().z));
                    }

                    this.d = ((String)this.a.getValue()).equals("Legit") ? 6 : 1;
                    var1.a_void_____(new Object[0]);
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if (this.d(new Object[]{37888275107808L}) && !this.i) {
            if (var1.a(new Object[0]) instanceof HandSwingC2SPacket && !((String)this.a.getValue()).equals("Silent") && this.c != null) {
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (this.c != null) {
            if (this.d <= 0) {
                this.i = true;
                if (b.crosshairTarget.getType() == Type.ENTITY) {
                    InventoryUtil2.a_void_________(new Object[]{this.c, 31382773185830L});
                }

                this.i = false;
                this.c = null;
            } else {
                this.d--;
            }
        }
    }

    @Subscribe
    public void a(BoolEvent var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
            if (this.c != null && this.d > 0) {
                var1.a_void_____(new Object[0]);
            }
        }
    }

    private void j(Object[] var1) {
        float var4 = (Float)var1[0];
        long var2 = (Long)var1[1];
        var2 = 66180507535862L ^ var2;
        long var5 = var2 ^ 67422375684283L;
        PositionAndOnGround var7 = new PositionAndOnGround(b.player.getX(), b.player.getY() + var4, b.player.getZ(), false, b.player.horizontalCollision);
        NetworkUtil.a(new Object[]{var7, var5});
    }

    private boolean d(Object[] var1) {
        return b.player != null && b.player.isOnGround() && !b.player.isSubmergedInWater() && !b.player.isInLava() && !b.player.isClimbing();
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
