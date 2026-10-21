package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.world.PositionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class ElytraWindCharge extends Module implements RenderContract3 {
    public ElytraWindCharge() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private boolean i;
    private int a = 0;
    private RenderHelper27 c = new RenderHelper27(26293358099357L);
    private RenderHelper27 d = new RenderHelper27(26293358099357L);
    private boolean e;
    private static final long l = -1612205855334203384L;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (!this.i) {
            if (InventoryUtil.l(new Object[]{Items.NETHERITE_CHESTPLATE, 65539821781598L})
                || InventoryUtil.l(new Object[]{Items.DIAMOND_CHESTPLATE, 65539821781598L})) {
                this.toggle(new Object[]{115103427876780L});
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140072483835707L;
        long var6 = var2 ^ 29247161167908L;
        long var8 = var2 ^ 128670251638447L;
        long var10 = var2 ^ 114949303762359L;
        long var12 = var2 ^ 120434008667598L;
        long var14 = var2 ^ 102840094869659L;
        long var16 = var2 ^ 38214632243944L;
        long var18 = var2 ^ 85547089512629L;
        long var20 = var2 ^ 4277139717926L;
        long var22 = var2 ^ 58982489950237L;
        long var24 = var2 ^ 127616945565159L;
        long var26 = var2 ^ 46949571541433L;
        if (b.currentScreen == null && b.isWindowFocused()) {
            this.i = false;
            if (this.a != -1) {
                int var44 = this.a;
                Object[] var45 = new Object[]{null, var12};
                var45[0] = var44;
                InventoryUtil.a_void________(var45);
                this.a = -1;
                return null;
            } else if (b.player.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
                if (InventoryUtil.l(new Object[]{Items.ELYTRA, var24})) {
                    this.i = true;
                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var14});
                } else {
                    Integer var41 = InventoryUtil.a_Integer(new Object[]{Items.ELYTRA, var8});
                    if (var41 != null) {
                        int var43 = var41;
                        Object[] var10003 = new Object[]{null, var12};
                        var10003[0] = var43;
                        InventoryUtil.a_void________(var10003);
                    }
                }

                return null;
            } else if (b.player.isOnGround()) {
                b.options.jumpKey.setPressed(true);
                return null;
            } else {
                b.options.jumpKey.setPressed(false);
                if (!PositionUtil.a_boolean________________________________________________(new Object[]{var16})) {
                    if (!b.player.isOnGround()) {
                        RenderHelper27 var42 = this.c;
                        Object[] var46 = new Object[]{null, var18};
                        var46[0] = 400.0F;
                        if (var42.a(var46)) {
                            InventoryUtil2.b_void___(new Object[]{var10});
                            b.player.networkHandler.sendPacket(new ClientCommandC2SPacket(b.player, Mode.START_FALL_FLYING));
                            b.options.jumpKey.setPressed(true);
                            this.c.a_void_____________________(new Object[]{var6});
                        }
                    }

                    return null;
                } else {
                    RenderHelper27 var10000 = this.d;
                    Object[] var10004 = new Object[]{null, var18};
                    var10004[0] = 400.0F;
                    if (!var10000.a(var10004)) {
                        return null;
                    } else {
                        Vec3d var29 = b.player.getVelocity();
                        float var30 = b.player.getYaw();
                        double var31 = Math.toRadians(var30);
                        Vec3d var33 = new Vec3d(-Math.sin(var31), 0.0, Math.cos(var31)).normalize();
                        float var34 = (float)(var29.x * var33.x + var29.z * var33.z);
                        float var35 = 0.4F;
                        float var36 = 1.2F;
                        float var37 = Math.max(0.0F, Math.min(1.0F, var34));
                        float var38 = var36 - (var36 - var35) * var37;
                        if (PositionUtil.a_double___________(new Object[]{b.player, var26}) > var38) {
                            return null;
                        } else {
                            float var39 = 87.0F + RenderUtil5.a.nextFloat() * 3.0F;
                            int var40 = this.d(new Object[]{var20});
                            if (var40 == -1) {
                                return null;
                            } else {
                                this.a = InventoryUtil.a_int_____(new Object[]{var22});
                                Runnable var10001 = () -> {
                                    float var5 = b.player.getPitch();
                                    b.player.setPitch(var39);
                                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                                    b.player.setPitch(var5);
                                };
                                Object[] var10005 = new Object[]{null, null, null, var4};
                                var10005[2] = false;
                                var10005[1] = var10001;
                                var10005[0] = var40;
                                InventoryUtil.b_void__(var10005);
                                this.d.a_void_____________________(new Object[]{var6});
                                return new RenderBatch(b.player.getYaw(), var39);
                            }
                        }
                    }
                }
            }
        } else {
            return null;
        }
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
        Event.b();
        Prestige.o.f(new Object[]{this, var4});
        if (!InputUtil.isKeyPressed(b.getWindow(), b.options.jumpKey.getDefaultKey().getCode())) {
            b.options.jumpKey.setPressed(false);
        }
    }

    private int d(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= (int)l; var5++) {
            ItemStack var6 = b.player.getInventory().getStack(var5);
            if (var6.getItem().equals(Items.WIND_CHARGE)) {
                return var5;
            }
        }

        return -1;
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
