package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.combat.CombatUtil2;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper26;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.type.BlockBreakType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;

public class PredictDoubleHand extends Module {
    public PredictDoubleHand() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private MultiSetting a;
    private MultiSetting c;
    private RangeSetting d;
    private FloatSetting e;
    private RenderHelper27 f = new RenderHelper27(26293358099357L);
    private long g = Long.MIN_VALUE;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if ((!this.c.isSelected(new Object[]{"Auto Crystal", 47850572908778L}) || !AutoCrystal.a)
            && (!this.c.isSelected(new Object[]{"Auto Hit Crystal", 47850572908778L}) || !AutoHitCrystal.a)) {
            if (this.a.isSelected(new Object[]{"Pearl", 47850572908778L})
                && var1.a(new Object[0]) instanceof PlayerInteractItemC2SPacket
                && b.player.getMainHandStack().getItem() == Items.ENDER_PEARL
                && !InventoryUtil.l(new Object[]{Items.TOTEM_OF_UNDYING, 65539821781598L})) {
                Integer var13 = InventoryUtil.a_Integer(new Object[]{Items.TOTEM_OF_UNDYING, 64376993919254L});
                if (var13 != null) {
                    int var10000 = var13;
                    Object[] var10003 = new Object[]{null, 37392877673079L};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if ((!this.c.isSelected(new Object[]{"Auto Crystal", 47850572908778L}) || !AutoCrystal.a)
            && (!this.c.isSelected(new Object[]{"Auto Hit Crystal", 47850572908778L}) || !AutoHitCrystal.a)) {
            if (this.f.b(new Object[]{this.d, 89714527855213L}) && b.currentScreen == null && b.isWindowFocused()) {
                long var23 = b.world.getTime();
                if (var23 != this.g) {
                    this.g = var23;
                    if (!InventoryUtil.l(new Object[]{Items.TOTEM_OF_UNDYING, 65539821781598L})) {
                        Integer var25 = InventoryUtil.a_Integer(new Object[]{Items.TOTEM_OF_UNDYING, 64376993919254L});
                        if (var25 != null) {
                            PlayerEntity var26 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
                            if (var26 != null) {
                                float var27 = b.player.getHealth() + b.player.getAbsorptionAmount();
                                if (!(b.player.distanceTo(var26) > 10.0F)) {
                                    if (!this.c.isSelected(new Object[]{"Shield", 47850572908778L})
                                        || !InventoryUtil.l(new Object[]{Items.SHIELD, 65539821781598L})
                                        || !b.player.isUsingItem()) {
                                        EnumSet var28 = EnumSet.noneOf(BlockBreakType.class);
                                        if (this.a.isSelected(new Object[]{"Anchors", 47850572908778L})) {
                                            var28.add(BlockBreakType.ANCHOR);
                                        }

                                        if (this.a.isSelected(new Object[]{"Crystals", 47850572908778L})) {
                                            var28.add(BlockBreakType.CRYSTAL);
                                        }

                                        if (this.a.isSelected(new Object[]{"Obsidian", 47850572908778L})) {
                                            var28.add(BlockBreakType.OBSIDIAN);
                                        }

                                        if (this.a.isSelected(new Object[]{"Mace", 47850572908778L})) {
                                            var28.add(BlockBreakType.MACE);
                                        }

                                        if (!var28.isEmpty()) {
                                            float var10002 = (Float)this.e.getValue();
                                            Object[] var10005 = new Object[]{null, null, null, 71151030853651L};
                                            var10005[2] = var10002;
                                            var10005[1] = var28;
                                            var10005[0] = var26;
                                            RenderHelper26 var29 = CombatUtil2.a(var10005);
                                            if (var29.a(new Object[0])) {
                                                Object[] var10004 = new Object[]{null, 114604007714030L};
                                                var10004[0] = var27;
                                                if (var29.b(var10004)) {
                                                    int var30 = var25;
                                                    var10004 = new Object[]{null, 24777133508117L};
                                                    var10004[0] = var30;
                                                    this.j(var10004);
                                                    return;
                                                }
                                            }
                                        }

                                        if (this.a.isSelected(new Object[]{"Sword Pop", 47850572908778L})
                                            && (
                                                InventoryUtil.a(new Object[]{var26.getMainHandStack(), 127680976181311L})
                                                    || var26.getMainHandStack().getItem() instanceof AxeItem
                                            )
                                            && var27 < 1.0F) {
                                            int var10001 = var25;
                                            Object[] var31 = new Object[]{null, 24777133508117L};
                                            var31[0] = var10001;
                                            this.j(var31);
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

    private void j(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        var3 = 3180947340199L ^ var3;
        long var5 = var3 ^ 72015106646575L;
        long var7 = var3 ^ 59847319054277L;
        long var9 = var3 ^ 43459832312659L;
        if (!b.player.isUsingItem()) {
            Object[] var10003 = new Object[]{null, var7};
            var10003[0] = var2;
            InventoryUtil.a_void________(var10003);
            this.d.reroll(new Object[]{var9});
            this.f.a_void_____________________(new Object[]{var5});
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
