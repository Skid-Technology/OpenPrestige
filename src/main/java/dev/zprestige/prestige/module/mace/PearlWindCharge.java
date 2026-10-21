package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BundleS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.Hand;

public class PearlWindCharge extends Module implements RenderContract3 {
    public PearlWindCharge() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;
    private boolean i;
    private boolean c;
    private static final long l = 5771087529172271112L;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
        this.i = false;
        this.c = false;
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 43040229079061L;
        long var6 = var2 ^ 58861972349919L;
        long var8 = var2 ^ 128670251638447L;
        long var10 = var2 ^ 32895933853369L;
        long var12 = var2 ^ 120434008667598L;
        long var14 = var2 ^ 102840094869659L;
        long var16 = var2 ^ 127616945565159L;
        if (!this.i) {
            if ((Boolean)this.d.getValue() && !InventoryUtil.l(new Object[]{Items.ENDER_PEARL, var16})) {
                int var21 = InventoryUtil.a_Integer(new Object[]{Items.ENDER_PEARL, var8});
                if (var21 == -1) {
                    this.toggle(new Object[]{var4});
                    return null;
                } else {
                    Object[] var22 = new Object[]{null, var12};
                    var22[0] = var21;
                    InventoryUtil.a_void________(var22);
                    return null;
                }
            } else {
                if (InventoryUtil.l(new Object[]{Items.ENDER_PEARL, var16})) {
                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var14});
                    this.i = true;
                }

                return null;
            }
        } else {
            if (this.c) {
                int var19 = this.d(new Object[]{var10});
                if (var19 != -1) {
                    float var20 = b.player.getPitch();
                    b.player.setPitch(90.0F);
                    Object[] var10003 = new Object[]{null, var12};
                    var10003[0] = var19;
                    InventoryUtil.a_void________(var10003);
                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var14});
                    if ((Boolean)this.a.getValue() && b.player.isOnGround()) {
                        RenderUtil2.a(new Object[]{var6});
                    }

                    b.player.setPitch(var20);
                    this.toggle(new Object[]{var4});
                    return new RenderBatch(b.player.getYaw(), 90.0F);
                }

                this.toggle(new Object[]{var4});
            }

            return null;
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (var1.a(new Object[0]) instanceof PlayerPositionLookS2CPacket) {
            this.c = true;
        }

        if (var1.a(new Object[0]) instanceof BundleS2CPacket var5) {
            for (Packet var7 : var5.getPackets()) {
                if (var7 instanceof PlayerPositionLookS2CPacket) {
                    this.c = true;
                    break;
                }
            }
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
    }

    private int d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 13590517682888L ^ var2;
        long var4 = var2 ^ 18851560775470L;
        Event.b();

        for (int var7 = 0; var7 <= (int)l; var7++) {
            if (a_boolean________________________________(new Object[]{b.player.getInventory().getStack(var7), var4})) {
                return var7;
            }
        }

        return -1;
    }

    public static boolean a_boolean________________________________(Object[] var0) {
        ItemStack var1 = (ItemStack)var0[0];
        return var1.getItem().equals(Items.WIND_CHARGE);
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
