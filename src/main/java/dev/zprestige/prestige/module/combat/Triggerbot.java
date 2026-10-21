package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent12;
import dev.zprestige.prestige.event.TickEvent15;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.inventory.InventoryUtil3;
import dev.zprestige.prestige.module.spear.ExtendedReach;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderHelper36;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.util.BindManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import org.lwjgl.glfw.GLFW;

public class Triggerbot extends Module {
    private MultiSetting a;
    private BoolSetting d;
    private FloatSetting c;
    private MultiSetting e;
    private MultiSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private RangeSetting k;
    private RangeSetting l;
    private BoolSetting m;
    private FloatSetting n;
    private RangeSetting o;
    private RenderHelper27 p = new RenderHelper27(26293358099357L);
    private RenderHelper27 q = new RenderHelper27(26293358099357L);
    private int r = -1;
    private int s = -1;
    private boolean t = false;
    private boolean u = false;
    private long v = System.nanoTime();
    private double w = 20.0;
    private int x = -1;
    private long y = 0L;

    public Triggerbot() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.l
            .setVisibility_RangeSetting(
                new Object[]{
                    (Predicate<Float>)lpL339_0 -> this.a.isSelected(new Object[]{"Any", 47850572908778L})
                        || this.a.isSelected(new Object[]{"Players", 47850572908778L}),
                    37043306203287L
                }
            );
        this.n
            .setVisibility_FloatSetting(
                new Object[]{
                    (Predicate<Float>)lpL337_0 -> this.a.isSelected(new Object[]{"Any", 47850572908778L})
                        || this.a.isSelected(new Object[]{"Players", 47850572908778L}),
                    35046756052036L
                }
            );
        this.f
            .setVisibility_MultiSetting(
                new Object[]{
                    (Predicate<String>)lpL340_0 -> this.a.isSelected(new Object[]{"Any", 47850572908778L})
                        || this.a.isSelected(new Object[]{"Crystals", 47850572908778L}),
                    18435058659860L
                }
            );
        this.m
            .setVisibility_BoolSetting(
                new Object[]{
                    (Predicate<Boolean>)lpL338_0 -> this.a.isSelected(new Object[]{"Any", 47850572908778L})
                        || this.a.isSelected(new Object[]{"Players", 47850572908778L}),
                    73196168581295L
                }
            );
        this.d
            .setVisibility_BoolSetting(
                new Object[]{
                    (Predicate<Boolean>)lpL343_0 -> this.a.isSelected(new Object[]{"Any", 47850572908778L})
                        || this.a.isSelected(new Object[]{"Players", 47850572908778L}),
                    73196168581295L
                }
            );
        this.c
            .setVisibility_FloatSetting(
                new Object[]{
                    (Predicate<Float>)lpL342_0 -> (Boolean)this.d.getValue()
                        && (this.a.isSelected(new Object[]{"Any", 47850572908778L}) || this.a.isSelected(new Object[]{"Players", 47850572908778L})),
                    35046756052036L
                }
            );
        this.o
            .setVisibility_RangeSetting(
                new Object[]{
                    (Predicate<Float>)lpL336_0 -> (
                            this.a.isSelected(new Object[]{"Any", 47850572908778L}) || this.a.isSelected(new Object[]{"Players", 47850572908778L})
                        )
                        && !this.e.isSelected(new Object[]{"Weapon Only", 47850572908778L}),
                    37043306203287L
                }
            );
        this.e
            .setVisibility_MultiSetting(
                new Object[]{
                    (Predicate<String>)lpL341_0 -> this.a.isSelected(new Object[]{"Any", 47850572908778L})
                        || this.a.isSelected(new Object[]{"Players", 47850572908778L}),
                    18435058659860L
                }
            );
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Sword, Gamemode.UHC);
    }

    public void j(Object[] var1) {
        long var5 = System.nanoTime();
        Event.b();
        long var7 = var5 - this.v;
        if (var7 > 0L) {
            double var9 = var7 / 1.0E9;
            this.w = Math.min(20.0, 1.0 / var9);
        }

        this.v = var5;
    }

    private double a(Object[] var1) {
        float var2 = (Float)var1[0];
        double var5 = Math.clamp(this.w, 10.0, 20.0);
        return var2 + (1.0F - var2) * ((20.0 - var5) / 10.0);
    }

    @Override
    public void onDisable(Object[] var1) {
        this.r = -1;
        this.k(new Object[0]);
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.r = -1;
        this.k(new Object[0]);
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (var1.a(new Object[0]) instanceof PlayerRespawnS2CPacket) {
            this.r = -1;
            this.k(new Object[0]);
        }
    }

    private void k(Object[] var1) {
        this.s = -1;
        this.t = false;
        this.u = false;
    }

    private void l(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        boolean var5 = (Boolean)var1[1];
        long var3 = (Long)var1[2];
        var3 = 103472504357851L ^ var3;
        long var6 = var3 ^ 8429549668212L;
        if (!CombatUtil.a(new Object[]{var2, var6})) {
            this.k(new Object[0]);
        } else {
            if (var2.getId() != this.s || var5 != this.u) {
                this.s = var2.getId();
                this.t = false;
                this.u = var5;
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        this.j(new Object[]{87087843766798L});
        if (b.player != null && b.world != null) {
            if (this.r != -1) {
                Entity var45 = b.world.getEntityById(this.r);
                if (var45 == null || !var45.isAlive() || b.player.distanceTo(var45) > (Float)this.c.getValue()) {
                    this.r = -1;
                }
            }

            if (b.crosshairTarget == null) {
                this.k(new Object[0]);
            } else if ((!(Boolean)this.h.getValue() || b.currentScreen == null) && b.isWindowFocused() && !b.player.isUsingItem()) {
                if (!InventoryUtil2.d(new Object[]{89063551742447L})) {
                    if ((Boolean)this.g.getValue() && GLFW.glfwGetMouseButton(b.getWindow().getHandle(), 0) != 1) {
                        this.k(new Object[0]);
                    } else {
                        Entity var53 = null;
                        if (this.t) {
                            if (this.u) {
                                var53 = ExtendedReach.a_class_1297____(new Object[]{30287626816250L});
                                if (var53 == null || var53.getId() != this.s) {
                                    this.k(new Object[0]);
                                    var53 = null;
                                }
                            } else if (!(b.crosshairTarget instanceof EntityHitResult var46 && var46.getEntity().getId() == this.s)) {
                                this.k(new Object[0]);
                            }
                        }

                        if (this.p.b(new Object[]{this.k, 89714527855213L})) {
                            Entity var54 = var53 != null ? var53 : ExtendedReach.a_class_1297____(new Object[]{30287626816250L});
                            HitResult var55 = b.crosshairTarget;
                            if ((var55 == null || !var55.getType().equals(Type.ENTITY)) && var54 == null) {
                                this.k(new Object[0]);
                                this.k.reroll(new Object[]{57083216809697L});
                                this.p.a_void_____________________(new Object[]{93540861878173L});
                            } else {
                                Entity var48 = var54 != null ? var54 : ((EntityHitResult)var55).getEntity();
                                int var10002 = var54 != null ? 1 : 0;
                                Object[] var10005 = new Object[]{null, null, 80328563351148L};
                                var10005[1] = Boolean.valueOf(var10002 != 0);
                                var10005[0] = var48;
                                this.l(var10005);
                                if (!this.a_boolean__________________________(new Object[]{var48, 14676090267098L})) {
                                    this.k(new Object[0]);
                                    if ((Boolean)this.j.getValue()) {
                                        this.m(new Object[]{var48, 82527965575973L});
                                    }
                                } else {
                                    boolean var49 = InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})
                                        || b.player.getMainHandStack().getItem() instanceof AxeItem
                                        || InventoryUtil.o(new Object[]{b.player.getMainHandStack(), 133561865349436L});
                                    if (!(var48 instanceof PlayerEntity)
                                            && (!this.a.isSelected(new Object[]{"Any", 47850572908778L}) || var48 instanceof EndCrystalEntity)
                                        || !this.e.isSelected(new Object[]{"Weapon Only", 47850572908778L})
                                        || var49) {
                                        if (!(var48 instanceof EndCrystalEntity)
                                            || !this.f.isSelected(new Object[]{"Crystal Only", 47850572908778L})
                                            || b.player.getMainHandStack().getItem() == Items.END_CRYSTAL) {
                                            float var50 = this.l.randomInRange(new Object[]{877569900989L}) / 100.0F;
                                            if ((Boolean)this.m.getValue()) {
                                                Object[] var10004 = new Object[]{null, 24963920917395L};
                                                var10004[0] = var50;
                                                var50 = (float)this.a(var10004);
                                            }

                                            if (!(var48 instanceof EndCrystalEntity)) {
                                                if (this.e.isSelected(new Object[]{"Weapon Only", 47850572908778L})) {
                                                    if (b.player.getAttackCooldownProgress(0.0F) < var50) {
                                                        return;
                                                    }
                                                } else if (!this.q.b(new Object[]{this.o, 89714527855213L})) {
                                                    return;
                                                }
                                            }

                                            if (!this.f.isSelected(new Object[]{"On Ground Only", 47850572908778L})
                                                || !(var48 instanceof EndCrystalEntity)
                                                || b.player.isOnGround()) {
                                                if (!(var48 instanceof PlayerEntity)
                                                    || !CombatUtil.b_boolean(new Object[]{(PlayerEntity)var48, 53913050824112L})
                                                    || b.player.getMainHandStack().getItem() instanceof AxeItem) {
                                                    if (var48 instanceof PlayerEntity
                                                        && var49
                                                        && !b.player.isOnGround()
                                                        && b.player.fallDistance > 0.0
                                                        && b.player.getVelocity().y <= 0.0) {
                                                        boolean var56 = true;
                                                    } else {
                                                        boolean var10000 = false;
                                                    }

                                                    if (!this.e.isSelected(new Object[]{"Criticals Timing", 47850572908778L})
                                                        || !(var48 instanceof PlayerEntity)
                                                        || !var49
                                                        || b.player.isOnGround()
                                                        || !(b.player.fallDistance <= 0.0) && !(b.player.getVelocity().y >= 0.0)) {
                                                        if (!this.e.isSelected(new Object[]{"Crit Only", 47850572908778L})
                                                            || !(var48 instanceof PlayerEntity)
                                                            || !var49
                                                            || !b.player.isOnGround() && !(b.player.fallDistance <= 0.0) && !(b.player.getVelocity().y >= 0.0)) {
                                                            if (!this.f.isSelected(new Object[]{"Pause On Kill", 47850572908778L})
                                                                || !(var48 instanceof EndCrystalEntity)
                                                                || !InventoryUtil3.a(new Object[]{var48.getBlockPos(), 64853648109405L})) {
                                                                if ((Boolean)this.d.getValue() && var48 instanceof PlayerEntity) {
                                                                    int var52 = var48.getId();
                                                                    if (this.r == -1) {
                                                                        this.r = var52;
                                                                    } else if (var52 != this.r) {
                                                                        return;
                                                                    }
                                                                }

                                                                if (var48 instanceof PlayerEntity) {
                                                                    if ((Float)this.n.getValue() <= 0.0F) {
                                                                        this.t = false;
                                                                    } else {
                                                                        if (this.t) {
                                                                            return;
                                                                        }

                                                                        Object[] var62 = new Object[]{null, null, 29524726012237L};
                                                                        var62[1] = 100.0F;
                                                                        var62[0] = 0.0F;
                                                                        if (RenderUtil5.a(var62) < (Float)this.n.getValue()) {
                                                                            this.t = true;
                                                                            this.k.reroll(new Object[]{57083216809697L});
                                                                            this.p.a_void_____________________(new Object[]{93540861878173L});
                                                                            return;
                                                                        }
                                                                    }
                                                                }

                                                                if (var48 instanceof PlayerEntity) {
                                                                    new TickEvent12().b_boolean__(new Object[]{122499962143880L});
                                                                }

                                                                if (var54 != null) {
                                                                    new TickEvent15().b_boolean__(new Object[]{122499962143880L});
                                                                    RenderHelper36 var57 = new RenderHelper36(b.mouse);
                                                                    long var10001 = b.getWindow().getHandle();
                                                                    var10002 = BindManager.e;
                                                                    Object[] var10007 = new Object[]{null, null, null, null, 105573767579112L};
                                                                    var10007[3] = 0;
                                                                    var10007[2] = 1;
                                                                    var10007[1] = var10002;
                                                                    var10007[0] = var10001;
                                                                    var57.a(var10007);
                                                                    var57 = new RenderHelper36(b.mouse);
                                                                    var10001 = b.getWindow().getHandle();
                                                                    var10002 = BindManager.e;
                                                                    var10007 = new Object[]{null, null, null, null, 105573767579112L};
                                                                    var10007[3] = 0;
                                                                    var10007[2] = 0;
                                                                    var10007[1] = var10002;
                                                                    var10007[0] = var10001;
                                                                    var57.a(var10007);
                                                                } else {
                                                                    InventoryUtil2.a_void_________(new Object[]{var48, 31382773185830L});
                                                                }

                                                                this.k.reroll(new Object[]{57083216809697L});
                                                                this.p.a_void_____________________(new Object[]{93540861878173L});
                                                                this.o.reroll(new Object[]{57083216809697L});
                                                                this.q.a_void_____________________(new Object[]{93540861878173L});
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
        } else {
            this.k(new Object[0]);
        }
    }

    private boolean a_boolean__________________________(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 103472504357851L ^ var3;
        long var5 = var3 ^ 76806933710731L;
        long var7 = var3 ^ 74755248897218L;
        long var9 = var3 ^ 132798486605547L;
        if ((Boolean)this.i.getValue() && var2 instanceof PlayerEntity var12 && Prestige.g.a(new Object[]{var12.getName().getString(), var5})) {
            return false;
        } else {
            return !(var2 instanceof PlayerEntity var14 && (var14.isSpectator() || var14.isDead() || var14.isRemoved() || var14.getAbilities().invulnerable))
                ? var2 != null
                    && var2 != b.player
                    && (this.e.isSelected(new Object[]{"Attack Invisibles", var9}) || !var2.isInvisible())
                    && (
                        this.a.isSelected(new Object[]{"Any", var9})
                            || this.a.isSelected(new Object[]{"Players", var9}) && CombatUtil.a(new Object[]{var2, var7})
                            || this.a.isSelected(new Object[]{"Crystals", var9}) && var2 instanceof EndCrystalEntity
                    )
                : false;
        }
    }

    private void m(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 103472504357851L ^ var2;
        long var5 = var2 ^ 3904179263860L;
        long var7 = var2 ^ 68794901409812L;
        Event.b();
        long var10 = System.currentTimeMillis();
        if (var4.getId() != this.x || var10 - this.y >= 3000L) {
            this.x = var4.getId();
            this.y = var10;
            String var12 = var4.getName().getString();
            String var13 = var4.getClass().getSimpleName();
            StringBuilder var14 = new StringBuilder();
            if (!(var4 instanceof PlayerEntity var15)) {
                var14.append("not-a-player-type ");
            } else {
                if ((Boolean)this.i.getValue() && Prestige.g.a(new Object[]{var12, var5})) {
                    var14.append("friend ");
                }

                if (var15.isSpectator()) {
                    var14.append("spectator ");
                }

                if (var15.isDead()) {
                    var14.append("dead ");
                }

                if (var15.isRemoved()) {
                    var14.append("removed ");
                }

                if (var15.getAbilities().invulnerable) {
                    var14.append("invulnerable ");
                }

                if (!this.a.isSelected(new Object[]{"Any", var7}) && !this.a.isSelected(new Object[]{"Players", var7})) {
                    var14.append("targets-filter ");
                }

                var14.append("uuid-v").append(var15.getUuid().version()).append(' ');
            }

            if (var4 instanceof EndCrystalEntity && !this.a.isSelected(new Object[]{"Crystals", var7}) && !this.a.isSelected(new Object[]{"Any", var7})) {
                var14.append("crystals-off ");
            }

            if (!this.e.isSelected(new Object[]{"Attack Invisibles", var7}) && var4.isInvisible()) {
                var14.append("invisible ");
            }

            if (var14.length() == 0) {
                var14.append("unknown");
            }

            ClientPlayerEntity var10000 = b.player;
            String var16 = String.valueOf(var14);
            var10000.sendMessage(Text.literal("§7[Triggerbot] §f" + var12 + " §8(" + var13 + ") §crejected:§7 " + var16), false);
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
