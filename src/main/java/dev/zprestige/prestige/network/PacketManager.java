package dev.zprestige.prestige.network;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;

public class PacketManager implements RenderContract2 {
    private int a;
    private boolean c;

    public PacketManager(long var1) {
        super();
        var1 = 0L;
        var1 = 102105027492144L ^ var1;
        long var3 = var1 ^ 88558921389742L;
        this.a = -1;
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.a != -1) {
            this.b(new Object[]{98450005855358L});
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if (this.a != -1) {
            if (var1.a(new Object[0]) instanceof UpdateSelectedSlotC2SPacket && !this.c) {
                var1.a_void_____(new Object[0]);
                return;
            }

            if (var1.a(new Object[0]) instanceof PlayerInteractItemC2SPacket
                || var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket
                || var1.a(new Object[0]) instanceof PlayerInteractEntityC2SPacket
                    && InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                this.b(new Object[]{98450005855358L});
            }
        }
    }

    public void a(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        var3 = 102105027492144L ^ var3;
        long var5 = var3 ^ 88166742632087L;
        this.a = var2;
        this.c = true;
        NetworkUtil.a(new Object[]{new UpdateSelectedSlotC2SPacket(var2), var5});
        this.c = false;
    }

    public void b(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 102105027492144L ^ var2;
        long var4 = var2 ^ 58839704735808L;
        long var6 = var2 ^ 140159301866218L;
        if (this.a != -1) {
            this.c = true;
            NetworkUtil.a(new Object[]{new UpdateSelectedSlotC2SPacket(InventoryUtil.a_int_____(new Object[]{var6})), var4});
            this.c = false;
            this.a = -1;
        }
    }

    public int a_int__________________(Object[] var1) {
        return this.a;
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
