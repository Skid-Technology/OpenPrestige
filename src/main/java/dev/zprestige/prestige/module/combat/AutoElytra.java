package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.IntEvent2;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil2;
import dev.zprestige.prestige.world.PositionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;

public class AutoElytra extends Module {
    private BoolSetting d;
    private RangeSetting a;
    private BoolSetting c;
    private BoolSetting e;
    private FloatSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private RangeSetting j;
    private RenderHelper27 k = new RenderHelper27(26293358099357L);
    private RenderHelper27 l = new RenderHelper27(26293358099357L);
    private RenderHelper27 m = new RenderHelper27(26293358099357L);
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private int r = 0;
    private int s = -1;
    private int t = -1;
    public static boolean u;
    public static AutoElytra v;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal, Gamemode.Mace);
    }

    @Override
    public boolean b(Object[] var1) {
        return false;
    }

    public AutoElytra() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        v = this;
        this.f.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL228_0 -> (Boolean)this.e.getValue(), 35046756052036L});
        this.j.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL227_0 -> (Boolean)this.i.getValue(), 37043306203287L});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 86929222885577L;
        if (!(Boolean)this.e.getValue()) {
            if (this.o) {
                this.o = false;
                return;
            }

            this.o = true;
            this.toggle(new Object[]{var4});
        }

        if (!this.o && b.player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) {
            this.o = true;
        }

        u = false;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 45286455980699L;
        long var6 = var2 ^ 87726956962791L;
        if (!this.o) {
            this.n = false;
            this.r = 0;
            this.k.a_void_____________________(new Object[]{var4});
            this.a.reroll(new Object[]{var6});
            this.l.a_void_____________________(new Object[]{var4});
            this.s = -1;
            this.t = -1;
            this.p = false;
            u = true;
            this.q = false;
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.isWindowFocused() && (b.currentScreen == null || b.currentScreen instanceof InventoryScreen)) {
            if (b.player.isOnGround() && !this.o) {
                if ((Boolean)this.h.getValue() && this.r == 0) {
                    RenderUtil2.a(new Object[]{134150376721510L});
                }

                this.n = false;
                this.l.a_void_____________________(new Object[]{93540861878173L});
                this.p = true;
                if (this.r >= 3) {
                    return;
                }
            }

            if (this.k.b(new Object[]{this.a, 89714527855213L}) && (this.p || !(Boolean)this.d.getValue())) {
                Item var10000 = Items.ELYTRA;
                Object[] var10004 = new Object[]{null, null, 77736144221765L};
                var10004[1] = true;
                var10004[0] = var10000;
                List var27 = InventoryUtil.a_List(var10004);
                var10000 = Items.FIREWORK_ROCKET;
                var10004 = new Object[]{null, null, 77736144221765L};
                var10004[1] = true;
                var10004[0] = var10000;
                List var28 = InventoryUtil.a_List(var10004);
                boolean var29 = false;
                if (this.r == 0 && b.player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) {
                    this.r = 1;
                    var29 = true;
                }

                if (this.r < 2) {
                    if (!var29 && var27.isEmpty()) {
                        this.toggle(new Object[]{115103427876780L});
                        return;
                    }

                    if ((Boolean)this.c.getValue() && var28.isEmpty()) {
                        this.toggle(new Object[]{115103427876780L});
                        return;
                    }

                    int var30 = var29 ? 0 : (Integer)var27.getFirst();
                    int var31 = !this.c.getValue() ? 0 : (Integer)var28.getFirst();
                    this.q = (Boolean)this.g.getValue() || var30 < 9 && var31 < 9;
                }

                if (this.r == 8 && b.player.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
                    this.toggle(new Object[]{115103427876780L});
                } else {
                    switch (this.r) {
                        case 0:
                        case 5:
                            if (!this.q) {
                                b.setScreen(new InventoryScreen(b.player));
                            }

                            this.k.a_void_____________________(new Object[]{93540861878173L});
                            this.a.reroll(new Object[]{57083216809697L});
                            this.r++;
                            break;
                        case 1:
                            if (b.currentScreen == null && !this.q) {
                                this.r = 0;
                            } else {
                                int var36 = b.player.currentScreenHandler.syncId;
                                if (!var27.isEmpty()) {
                                    int var37 = (Integer)var27.getFirst();
                                    if (var37 < 9) {
                                        Object[] var49 = new Object[]{null, 37392877673079L};
                                        var49[0] = var37;
                                        InventoryUtil.a_void________(var49);
                                    } else {
                                        b.interactionManager.clickSlot(var36, var37, 0, SlotActionType.PICKUP, b.player);
                                        b.interactionManager.clickSlot(var36, 6, 0, SlotActionType.PICKUP, b.player);
                                        b.interactionManager.clickSlot(var36, var37, 0, SlotActionType.PICKUP, b.player);
                                    }

                                    this.t = var37;
                                } else {
                                    var10000 = Items.NETHERITE_CHESTPLATE;
                                    var10004 = new Object[]{null, null, 77736144221765L};
                                    var10004[1] = true;
                                    var10004[0] = var10000;
                                    List var38 = InventoryUtil.a_List(var10004);
                                    if (var38.isEmpty()) {
                                        var10000 = Items.DIAMOND_CHESTPLATE;
                                        var10004 = new Object[]{null, null, 77736144221765L};
                                        var10004[1] = true;
                                        var10004[0] = var10000;
                                        var38 = InventoryUtil.a_List(var10004);
                                    }

                                    if (!var38.isEmpty()) {
                                        this.t = (Integer)var38.getFirst();
                                    }
                                }

                                if ((Boolean)this.c.getValue()) {
                                    int var39 = (Integer)var28.getFirst();
                                    if (var39 < 9) {
                                        if (!var27.isEmpty() && (Integer)var27.getFirst() > 9) {
                                            Object[] var50 = new Object[]{null, 37392877673079L};
                                            var50[0] = var39;
                                            InventoryUtil.a_void________(var50);
                                        }
                                    } else {
                                        b.interactionManager
                                            .clickSlot(var36, var39, InventoryUtil.a_int_____(new Object[]{134339616728996L}), SlotActionType.SWAP, b.player);
                                    }

                                    this.s = var39;
                                }

                                this.r++;
                                this.k.a_void_____________________(new Object[]{93540861878173L});
                                this.a.reroll(new Object[]{57083216809697L});
                            }
                            break;
                        case 2:
                        case 7:
                            b.setScreen(null);
                            this.r++;
                            this.k.a_void_____________________(new Object[]{93540861878173L});
                            this.a.reroll(new Object[]{57083216809697L});
                            break;
                        case 3:
                            if (b.currentScreen != null) {
                                return;
                            }

                            if (b.player.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
                                if (b.player.getMainHandStack().isOf(Items.ELYTRA)) {
                                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                                }

                                this.k.a_void_____________________(new Object[]{93540861878173L});
                                this.a.reroll(new Object[]{57083216809697L});
                                return;
                            }

                            if (b.player.isOnGround()) {
                                return;
                            }

                            b.player.networkHandler.sendPacket(new ClientCommandC2SPacket(b.player, Mode.START_FALL_FLYING));
                            InventoryUtil2.b_void___(new Object[]{42886172512782L});
                            this.n = true;
                            this.l.a_void_____________________(new Object[]{93540861878173L});
                            this.r++;
                            this.k.a_void_____________________(new Object[]{93540861878173L});
                            this.a.reroll(new Object[]{57083216809697L});
                            break;
                        case 4:
                            if ((Boolean)this.c.getValue()) {
                                if (!b.player.getMainHandStack().isOf(Items.FIREWORK_ROCKET)) {
                                    if (var28.isEmpty()) {
                                        return;
                                    }

                                    int var35 = (Integer)var28.getFirst();
                                    Object[] var48 = new Object[]{null, 37392877673079L};
                                    var48[0] = var35;
                                    InventoryUtil.a_void________(var48);
                                    this.k.a_void_____________________(new Object[]{93540861878173L});
                                    this.a.reroll(new Object[]{57083216809697L});
                                    return;
                                }

                                InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                                this.k.a_void_____________________(new Object[]{93540861878173L});
                                this.a.reroll(new Object[]{57083216809697L});
                            }

                            this.r++;
                            break;
                        case 6:
                            if (b.currentScreen == null && !this.q) {
                                this.r = 5;
                            } else {
                                if ((Boolean)this.c.getValue()) {
                                    if (this.s < 9) {
                                        int var44 = this.s;
                                        Object[] var47 = new Object[]{null, 37392877673079L};
                                        var47[0] = var44;
                                        InventoryUtil.a_void________(var47);
                                    } else {
                                        int var34 = b.player.currentScreenHandler.syncId;
                                        b.interactionManager
                                            .clickSlot(var34, this.s, InventoryUtil.a_int_____(new Object[]{134339616728996L}), SlotActionType.SWAP, b.player);
                                    }

                                    this.k.a_void_____________________(new Object[]{93540861878173L});
                                    this.a.reroll(new Object[]{57083216809697L});
                                }

                                this.r++;
                            }
                            break;
                        case 8:
                            if ((Boolean)this.e.getValue()) {
                                RenderHelper27 var43 = this.l;
                                float var10001 = (Float)this.f.getValue();
                                var10004 = new Object[]{null, 2505922520844L};
                                var10004[0] = var10001;
                                if (!var43.a(var10004)) {
                                    return;
                                }
                            } else if (!this.o) {
                                return;
                            }

                            if (!this.q) {
                                b.setScreen(new InventoryScreen(b.player));
                            }

                            this.k.a_void_____________________(new Object[]{93540861878173L});
                            this.a.reroll(new Object[]{57083216809697L});
                            this.r++;
                            break;
                        case 9:
                            if (b.currentScreen == null && !this.q) {
                                this.r = 8;
                            } else {
                                if (this.t == -1) {
                                    var10000 = Items.AIR;
                                    var10004 = new Object[]{null, null, 77736144221765L};
                                    var10004[1] = false;
                                    var10004[0] = var10000;
                                    List var32 = InventoryUtil.a_List(var10004);
                                    if (var32.isEmpty()) {
                                        this.r++;
                                        this.k.a_void_____________________(new Object[]{93540861878173L});
                                        this.a.reroll(new Object[]{57083216809697L});
                                        break;
                                    }

                                    this.t = (Integer)var32.getFirst();
                                }

                                if (this.t < 9) {
                                    int var42 = this.t;
                                    Object[] var10003 = new Object[]{null, 37392877673079L};
                                    var10003[0] = var42;
                                    InventoryUtil.a_void________(var10003);
                                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                                } else {
                                    int var33 = b.player.currentScreenHandler.syncId;
                                    b.interactionManager.clickSlot(var33, this.t, 0, SlotActionType.PICKUP, b.player);
                                    b.interactionManager.clickSlot(var33, 6, 0, SlotActionType.PICKUP, b.player);
                                    b.interactionManager.clickSlot(var33, this.t, 0, SlotActionType.PICKUP, b.player);
                                }

                                this.r++;
                                this.k.a_void_____________________(new Object[]{93540861878173L});
                                this.a.reroll(new Object[]{57083216809697L});
                            }
                            break;
                        case 10:
                            b.setScreen(null);
                            if (b.currentScreen == null) {
                                this.toggle(new Object[]{115103427876780L});
                            }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if (this.n) {
            if (var1.a(new Object[0]) instanceof ClientCommandC2SPacket var5 && var5.getMode() == Mode.START_FALL_FLYING) {
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(IntEvent2 var1) {
        if ((Boolean)this.i.getValue()) {
            if (var1.b(new Object[0]) == 1) {
                if (var1.a(new Object[0]) == 32) {
                    if (b.player != null && b.world != null) {
                        if (b.currentScreen == null) {
                            if (b.isWindowFocused()) {
                                if (PositionUtil.a_boolean________________________________________________(new Object[]{120169006001489L})) {
                                    if (!u || this.r >= 4 && this.r <= 8) {
                                        if (this.m.b(new Object[]{this.j, 89714527855213L})) {
                                            if (!Prestige.o.d_boolean______(new Object[0])) {
                                                if (InventoryUtil.l(new Object[]{Items.FIREWORK_ROCKET, 65539821781598L})) {
                                                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                                                    Prestige.o.g(new Object[]{true});
                                                    this.m.a_void_____________________(new Object[]{93540861878173L});
                                                    this.j.reroll(new Object[]{57083216809697L});
                                                } else {
                                                    boolean[] var19 = new boolean[]{false};
                                                    Item var10000 = Items.FIREWORK_ROCKET;
                                                    Runnable var10001 = () -> {
                                                        if (b.player.getMainHandStack().isOf(Items.FIREWORK_ROCKET)) {
                                                            InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, 19803493115682L});
                                                            Prestige.o.g(new Object[]{true});
                                                            var19[0] = true;
                                                        }
                                                    };
                                                    Object[] var10005 = new Object[]{null, null, null, 114857082072684L};
                                                    var10005[2] = true;
                                                    var10005[1] = var10001;
                                                    var10005[0] = var10000;
                                                    InventoryUtil.j(var10005);
                                                    if (var19[0]) {
                                                        this.m.a_void_____________________(new Object[]{93540861878173L});
                                                        this.j.reroll(new Object[]{57083216809697L});
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
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
