package dev.zprestige.prestige.module.movement;

import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.util.Hand;

public class AutoShortPearl extends Module implements RenderContract3 {
    public AutoShortPearl() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private RangeSetting a;
    private RenderHelper27 c = new RenderHelper27(26293358099357L);
    private boolean i = false;
    private static final String l = "player";

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof EntityDamageS2CPacket var9) {
            Entity var12 = b.world.getEntityById(var9.entityId());
            if (var12 == null || var12 != b.player) {
                return;
            }

            String var11 = var9.createDamageSource(b.world).getType().msgId();
            if (l.equals(var11)) {
                this.i = true;
                this.c.a_void_____________________(new Object[]{93540861878173L});
                this.a.reroll(new Object[]{57083216809697L});
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 43966253580757L;
        long var6 = var2 ^ 85547089512629L;
        if (this.i) {
            RenderHelper27 var10000 = this.c;
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, var6};
            var10004[0] = var10001;
            if (var10000.a(var10004) && b.isWindowFocused() && b.currentScreen == null) {
                float var9 = 89.0F + RenderUtil5.a.nextFloat() * 2.0F;
                Item var10 = Items.ENDER_PEARL;
                Runnable var11 = () -> {
                    float var5 = b.player.getPitch();
                    b.player.setPitch(var9);
                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                    b.player.setPitch(var5);
                };
                Object[] var10005 = new Object[]{null, null, null, var4};
                var10005[2] = true;
                var10005[1] = var11;
                var10005[0] = var10;
                InventoryUtil.j(var10005);
                this.i = false;
                return new RenderBatch(b.player.getYaw(), var9);
            }
        }

        return null;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
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
