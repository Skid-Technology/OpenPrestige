package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.combat.CombatUtil2;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.gui.GuiHelper;
import dev.zprestige.prestige.gui.InventoryScreenOverride;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.network.NetworkUtil;
import dev.zprestige.prestige.network.PacketQueue;
import dev.zprestige.prestige.reflect.FieldAccessor;
import dev.zprestige.prestige.render.AutoTotemHelper;
import dev.zprestige.prestige.render.RenderHelper26;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.type.ArmorState;
import dev.zprestige.prestige.type.BlockBreakType;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.type.PacketResult;
import dev.zprestige.prestige.util.TpsTimer;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.BundlePacket;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.registry.entry.RegistryEntry.Type;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoTotem extends Module {
    private EnumSetting c;
    private RangeSetting d;
    private BoolSetting e;
    private MultiSetting f;
    private MultiSetting g;
    private IntSetting h;
    private BoolSetting i;
    private RangeSetting j;
    private BoolSetting k;
    private RangeSetting l;
    private RangeSetting m;
    private MultiSetting n;
    private FloatSetting o;
    private BoolSetting p;
    private BoolSetting q;
    private RenderHelper27 r = new RenderHelper27(26293358099357L);
    private RenderHelper27 s = new RenderHelper27(26293358099357L);
    private boolean t = false;
    private boolean u = false;
    private InventoryScreen v;
    private int w = -1;
    private int x = -1;
    private ArmorState y = ArmorState.NONE;
    private int z = -1;
    private int A = 0;
    private long B = 0L;
    private float C = Float.NaN;
    private Deque D = new ArrayDeque();
    private float F = Float.NaN;
    private Robot G;
    private Random H = new Random();
    private Slot I;
    private RenderHelper27 J = new RenderHelper27(26293358099357L);
    private List K = new ArrayList();
    private int L = 0;
    private Point M = null;
    private RenderHelper27 N = new RenderHelper27(26293358099357L);
    private volatile long O = 0L;

    public AutoTotem() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.g.setVisibility_MultiSetting(new Object[]{(Predicate<String>)lpL310_0 -> ((String)this.c.getValue()).equals("Crystal"), 18435058659860L});
        this.m
            .setVisibility_RangeSetting(
                new Object[]{(Predicate<Float>)lpL304_0 -> ((String)this.c.getValue()).equals("Crystal") && (Boolean)this.k.getValue(), 37043306203287L}
            );
        this.o.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL302_0 -> ((String)this.c.getValue()).equals("SMP"), 35046756052036L});
        this.q.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL300_0 -> ((String)this.c.getValue()).equals("SMP"), 73196168581295L});
        this.f
            .setVisibility_MultiSetting(
                new Object[]{(Predicate<String>)lpL311_0 -> ((String)this.c.getValue()).equals("Crystal") && (Boolean)this.e.getValue(), 18435058659860L}
            );
        this.i
            .setVisibility_BoolSetting(
                new Object[]{
                    (Predicate<Boolean>)lpL308_0 -> ((String)this.c.getValue()).equals("Crystal") && this.g.isSelected(new Object[]{"Switch", 47850572908778L}),
                    73196168581295L
                }
            );
        this.k.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL306_0 -> ((String)this.c.getValue()).equals("Crystal"), 73196168581295L});
        this.n.setVisibility_MultiSetting(new Object[]{(Predicate<String>)lpL303_0 -> ((String)this.c.getValue()).equals("Crystal"), 18435058659860L});
        this.h.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL309_0 -> ((String)this.c.getValue()).equals("Crystal"), 98265949679296L});
        this.j
            .setVisibility_RangeSetting(
                new Object[]{
                    (Predicate<Float>)lpL307_0 -> ((String)this.c.getValue()).equals("Crystal")
                        && (Boolean)this.i.getValue()
                        && this.g.isSelected(new Object[]{"Switch", 47850572908778L}),
                    37043306203287L
                }
            );
        this.l
            .setVisibility_RangeSetting(
                new Object[]{(Predicate<Float>)lpL305_0 -> ((String)this.c.getValue()).equals("Crystal") && (Boolean)this.k.getValue(), 37043306203287L}
            );
        this.p.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL301_0 -> ((String)this.c.getValue()).equals("SMP"), 73196168581295L});
        this.e.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL312_0 -> ((String)this.c.getValue()).equals("Crystal"), 73196168581295L});
    }

    @Override
    public void onEnable(Object[] var1) {
        this.f();
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 122422495623210L;
        this.j(new Object[]{var4});
        this.f();
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.f();
    }

    private void f() {
        if (Prestige.u != null) {
            Prestige.u.a_int___________________(new Object[]{this, 49283324427169L});
        }

        this.t = false;
        this.u = false;
        this.O = 0L;
        this.w = -1;
        this.x = -1;
        this.m(new Object[]{27529539044667L});
        this.D.clear();
        this.F = Float.NaN;
        this.C = Float.NaN;
        this.v = null;
        this.I = null;
        this.K.clear();
        this.L = 0;
        this.M = null;
        this.d.reroll(new Object[]{57083216809697L});
        this.j.reroll(new Object[]{57083216809697L});
        this.m.reroll(new Object[]{57083216809697L});
        this.r.a_void_____________________(new Object[]{93540861878173L});
        this.s.a_void_____________________(new Object[]{93540861878173L});
        this.J.a_void_____________________(new Object[]{93540861878173L});
        this.N.a_void_____________________(new Object[]{93540861878173L});
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 128814246915471L;
        Event.b();
        InventoryScreen var7 = this.v;
        this.v = null;
        if (var7 != null && b.currentScreen == var7) {
            if (b.getNetworkHandler() != null) {
                NetworkUtil.a(new Object[]{new CloseHandledScreenC2SPacket(var7.getScreenHandler().syncId), var4});
            }

            b.setScreen(null);
        }
    }

    private void k(Object[] var1) {
        HandledScreen var2 = (HandledScreen)var1[0];
        long var3 = (Long)var1[1];
        var3 = 14313756215246L ^ var3;
        long var5 = var3 ^ 28860982368821L;
        Event.b();
        NetworkUtil.a(new Object[]{new CloseHandledScreenC2SPacket(var2.getScreenHandler().syncId), var5});
        b.setScreen(null);
        if (this.v == var2) {
            this.v = null;
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 28352705930964L;
        if (!this.u) {
            return false;
        } else if (Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{var4})) {
            return true;
        } else {
            this.u = false;
            return false;
        }
    }

    private boolean a(Object[] var1) {
        int var4 = (Integer)var1[0];
        int var5 = (Integer)var1[1];
        long var2 = (Long)var1[2];
        var2 = 14313756215246L ^ var2;
        long var6 = var2 ^ 24779892326681L;
        long var8 = var2 ^ 39846721046167L;
        if (Prestige.u == null) {
            Object[] var10004 = new Object[]{null, null, var6};
            var10004[1] = var5;
            var10004[0] = var4;
            return InventoryUtil.f(var10004);
        } else {
            PacketQueue var10000 = Prestige.u;
            Object[] var10006 = new Object[]{null, null, null, var8};
            var10006[2] = var5;
            var10006[1] = var4;
            var10006[0] = this;
            PacketResult var11 = var10000.c(var10006);
            this.u = var11 == PacketResult.QUEUED;
            return var11 != PacketResult.REJECTED;
        }
    }

    private boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 66331746231645L;
        long var6 = var2 ^ 128591650490244L;
        if (this.y == ArmorState.NONE) {
            return false;
        } else if (Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{var4})) {
            this.A = 0;
            return true;
        } else if (this.A++ == 0) {
            return true;
        } else {
            this.m(new Object[]{var6});
            return false;
        }
    }

    private boolean f(Object[] var1) {
        int var5 = (Integer)var1[0];
        ArmorState var4 = (ArmorState)var1[1];
        long var2 = (Long)var1[2];
        var2 = 14313756215246L ^ var2;
        long var6 = var2 ^ 93961424471427L;
        long var8 = var2 ^ 98340789407323L;
        long var10 = var2 ^ 122696113199470L;
        long var12 = var2 ^ 17353904664138L;
        if (this.y != ArmorState.NONE) {
            return false;
        } else {
            long var15 = ++this.B;
            this.y = var4;
            this.z = var5;
            this.A = 0;
            if (Prestige.u == null) {
                Object[] var10004 = new Object[]{null, null, var12};
                var10004[1] = 40;
                var10004[0] = var5;
                InventoryUtil.f(var10004);
                Object[] var19 = new Object[]{null, null, null, var8};
                var19[2] = var5;
                var19[1] = var4;
                var19[0] = var15;
                this.l(var19);
                return true;
            } else {
                PacketQueue var10000 = Prestige.u;
                Object[] var10007 = new Object[]{null, null, null, (Runnable)() -> {
                    Object[] var10006x = new Object[]{null, null, null, 52701086519822L};
                    var10006x[2] = var5;
                    var10006x[1] = var4;
                    var10006x[0] = var15;
                    this.l(var10006x);
                }, var6};
                var10007[2] = 40;
                var10007[1] = var5;
                var10007[0] = this;
                PacketResult var17 = var10000.d(var10007);
                if (var17 == PacketResult.IMMEDIATE) {
                    Object[] var10006 = new Object[]{null, null, null, var8};
                    var10006[2] = var5;
                    var10006[1] = var4;
                    var10006[0] = var15;
                    this.l(var10006);
                    return true;
                } else if (var17 == PacketResult.QUEUED) {
                    return true;
                } else {
                    this.m(new Object[]{var10});
                    return false;
                }
            }
        }
    }

    private void l(Object[] var1) {
        long var6 = (Long)var1[0];
        ArmorState var2 = (ArmorState)var1[1];
        int var3 = (Integer)var1[2];
        long var4 = (Long)var1[3];
        var4 = 14313756215246L ^ var4;
        long var8 = var4 ^ 65848219078907L;
        if (this.isEnabled() && var6 == this.B && this.y == var2 && this.z == var3) {
            if (var2 == ArmorState.EQUIP) {
                this.w = var3;
            } else if (var2 == ArmorState.RESTORE) {
                this.w = -1;
            }

            this.m(new Object[]{var8});
        }
    }

    private void m(Object[] var1) {
        this.y = ArmorState.NONE;
        this.z = -1;
        this.A = 0;
        this.B++;
    }

    private boolean g(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 18795505335480L;
        Event.b();
        long var7 = this.O;
        this.O = 0L;
        return this.n.isSelected(new Object[]{"Anti Totem Guard Test", var4}) && var7 != 0L && System.currentTimeMillis() <= var7;
    }

    private float b_float___________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 89958660340586L;
        long var6 = var2 ^ 81607964457590L;
        long var8 = var2 ^ 1656969740956L;
        long var10 = var2 ^ 41151638402158L;
        long var12 = var2 ^ 113056885772394L;
        Event.b();
        PlayerEntity var15 = CombatUtil.a_class_1657(new Object[]{var8});
        if (var15 != null && !(b.player.distanceTo(var15) > 20.0F)) {
            float var16 = b.player.getHealth() + b.player.getAbsorptionAmount();
            float var17 = b.player.distanceTo(var15);
            float var18 = (float)Math.max(0.0, Math.min(1.0, (b.player.getY() - var15.getY()) / 100.0));
            float var19 = 0.0F;
            int var20 = 0;
            if (this.f.isSelected(new Object[]{"Health", var10})) {
                var19 += Math.max(0.0F, Math.min(1.0F, (20.0F - var16) / 18.0F));
                var20++;
            }

            if (this.f.isSelected(new Object[]{"Distance", var10})) {
                var19 += Math.max(0.0F, Math.min(1.0F, (10.0F - var17) / 6.0F));
                var20++;
            }

            if (this.f.isSelected(new Object[]{"Y Level", var10})) {
                var19 += var18;
                var20++;
            }

            if (this.f.isSelected(new Object[]{"Nearby Threats", var10})) {
                RenderHelper26 var21 = CombatUtil2.b(
                    new Object[]{var15, EnumSet.of(BlockBreakType.CRYSTAL, BlockBreakType.ANCHOR, BlockBreakType.OBSIDIAN), var4}
                );
                float var22 = 0.0F;
                if (var21.a(new Object[0])) {
                    Object[] var10004 = new Object[]{null, var12};
                    var10004[0] = var16;
                    if (var21.b(var10004)) {
                        var22 = 1.0F;
                    } else {
                        var10004 = new Object[]{null, var6};
                        var10004[0] = var16;
                        if (var21.a_float_________________(var10004) < 6.0F) {
                            var22 = 0.6F;
                        }
                    }
                }

                var19 += var22;
                var20++;
            }

            if (this.f.isSelected(new Object[]{"Recent Damage Rate", var10})) {
                long var27 = System.currentTimeMillis();
                this.D.removeIf(lpL298_0 -> var27 - ((dev.zprestige.prestige.render.AutoTotemHelper)lpL298_0).a > 5000L);
                float var23 = 0.0F;

                for (AutoTotemHelper var25 : (Iterable<AutoTotemHelper>)(this.D)) {
                    var23 += var25.b;
                }

                var19 += Math.min(1.0F, var23 / 20.0F);
                var20++;
            }

            return var20 > 0 ? var19 / var20 * 100.0F : 0.0F;
        } else {
            return 0.0F;
        }
    }

    private List b_List___(Object[] var1) {
        Point var2 = (Point)var1[0];
        Point var3 = (Point)var1[1];
        long var4 = (Long)var1[2];
        var4 = 14313756215246L ^ var4;
        long var6 = var4 ^ 19128065120188L;
        Event.b();
        ArrayList var9 = new ArrayList();
        double var10 = var2.distance(var3);
        float var12 = this.l.randomInRange(new Object[]{var6});
        int var13 = (int)Math.max(8.0, Math.min(60.0, var10 / var12 * 3.5));
        double var14 = Math.atan2(var3.y - var2.y, var3.x - var2.x);
        double var16 = var14 + (Math.PI / 2);
        double var18 = Math.min(var10 * 0.18, 22.0);
        double var20 = (this.H.nextGaussian() * 0.4 + (this.H.nextBoolean() ? 1 : -1)) * var18 * (0.4 + this.H.nextDouble() * 0.6);
        double var22 = this.H.nextGaussian() * 0.3 * var18 * 0.5;
        double var24 = var2.x + (var3.x - var2.x) * (0.25 + this.H.nextDouble() * 0.15) + Math.cos(var16) * var20;
        double var26 = var2.y + (var3.y - var2.y) * (0.25 + this.H.nextDouble() * 0.15) + Math.sin(var16) * var20;
        double var28 = var2.x + (var3.x - var2.x) * (0.6 + this.H.nextDouble() * 0.15) + Math.cos(var16) * var22;
        double var30 = var2.y + (var3.y - var2.y) * (0.6 + this.H.nextDouble() * 0.15) + Math.sin(var16) * var22;
        boolean var32 = this.H.nextDouble() < 0.25 && var10 > 30.0;
        double var33 = var32 ? 3.0 + this.H.nextDouble() * 5.0 : 0.0;
        double var35 = var14 + this.H.nextGaussian() * 0.15;
        Point var37 = var32 ? new Point((int)(var3.x + Math.cos(var35) * var33), (int)(var3.y + Math.sin(var35) * var33)) : var3;

        for (int var38 = 0; var38 <= var13; var38++) {
            double var39 = (double)var38 / var13;
            double var41 = var39 < 0.5 ? 4.0 * var39 * var39 * var39 : 1.0 - Math.pow(-2.0 * var39 + 2.0, 3.0) / 2.0;
            double var43 = 1.0 - var41;
            int var45 = (int)(
                var43 * var43 * var43 * var2.x + 3.0 * var43 * var43 * var41 * var24 + 3.0 * var43 * var41 * var41 * var28 + var41 * var41 * var41 * var37.x
            );
            int var46 = (int)(
                var43 * var43 * var43 * var2.y + 3.0 * var43 * var43 * var41 * var26 + 3.0 * var43 * var41 * var41 * var30 + var41 * var41 * var41 * var37.y
            );
            var9.add(new Point(var45, var46));
        }

        if (var32) {
            int var48 = 4 + this.H.nextInt(4);

            for (int var49 = 1; var49 <= var48; var49++) {
                double var40 = (double)var49 / var48;
                double var42 = var40 * var40 * (3.0 - 2.0 * var40);
                int var44 = (int)(var37.x + (var3.x - var37.x) * var42);
                int var50 = (int)(var37.y + (var3.y - var37.y) * var42);
                var9.add(new Point(var44, var50));
            }
        }

        var9.add(var3);
        return var9;
    }

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.isWindowFocused()) {
            if (!((String)this.c.getValue()).equals("SMP")) {
                if (this.t && !this.h(new Object[]{94918739196044L})) {
                    if ((Boolean)this.k.getValue() && this.G == null) {
                        this.n(new Object[]{2037288542756L});
                    }

                    boolean var49 = InventoryUtil.l(new Object[]{Items.SHIELD, 65539821781598L}) && b.player.isUsingItem();
                    if (!var49) {
                        Integer var50 = InventoryUtil.a_Integer(new Object[]{Items.TOTEM_OF_UNDYING, 64376993919254L});
                        if (var50 != null
                            && b.currentScreen == null
                            && this.g.isSelected(new Object[]{"Double Hand", 47850572908778L})
                            && !InventoryUtil.l(new Object[]{Items.TOTEM_OF_UNDYING, 65539821781598L})) {
                            int var10000 = var50;
                            Object[] var10003 = new Object[]{null, 37392877673079L};
                            var10003[0] = var10000;
                            InventoryUtil.a_void________(var10003);
                            if (this.i(new Object[]{39191347047416L})) {
                                return;
                            }
                        }

                        Item var86 = Items.TOTEM_OF_UNDYING;
                        Object[] var10004 = new Object[]{null, null, 77736144221765L};
                        var10004[1] = true;
                        var10004[0] = var86;
                        List var51 = InventoryUtil.a_List(var10004);
                        if (var51.isEmpty()) {
                            if (b.currentScreen instanceof InventoryScreen && this.g.isSelected(new Object[]{"Close Inventory", 47850572908778L})) {
                                this.k(new Object[]{(HandledScreen)b.currentScreen, 43910558120181L});
                            }

                            this.t = false;
                        } else {
                            boolean var52 = var50 == null && this.g.isSelected(new Object[]{"Refill Hotbar Totem", 47850572908778L});
                            boolean var53 = b.currentScreen == null && (var52 || !InventoryUtil.m(new Object[]{Items.TOTEM_OF_UNDYING, 6830336961985L}));
                            if (this.n.isSelected(new Object[]{"Only Inventory on Both Pops", 47850572908778L})) {
                                var53 = b.currentScreen == null && var52 && !InventoryUtil.m(new Object[]{Items.TOTEM_OF_UNDYING, 6830336961985L});
                                if (!var53
                                    && b.currentScreen == null
                                    && (var52 || !InventoryUtil.m(new Object[]{Items.TOTEM_OF_UNDYING, 6830336961985L}))
                                    && (!var52 || InventoryUtil.m(new Object[]{Items.TOTEM_OF_UNDYING, 6830336961985L}))
                                    && this.i(new Object[]{39191347047416L})) {
                                    return;
                                }
                            }

                            if (var53) {
                                this.J.a_void_____________________(new Object[]{93540861878173L});
                                this.m.reroll(new Object[]{57083216809697L});
                                this.K = new ArrayList();
                                this.L = 0;
                                this.M = null;
                                this.I = null;
                                if (!this.g.isSelected(new Object[]{"Open Inventory", 47850572908778L})) {
                                    return;
                                }

                                Object var54 = this.g.isSelected(new Object[]{"Switch", 47850572908778L})
                                    ? new InventoryScreenOverride(b.player)
                                    : new InventoryScreen(b.player);
                                b.setScreen((Screen)var54);
                                if (b.currentScreen == var54) {
                                    this.v = (InventoryScreen)var54;
                                }

                                if (this.i(new Object[]{39191347047416L})) {
                                    return;
                                }
                            }

                            if (b.currentScreen instanceof InventoryScreen || b.currentScreen instanceof InventoryScreenOverride) {
                                if (this.d(new Object[]{76297766520568L})) {
                                    return;
                                }

                                if ((Boolean)this.k.getValue() && this.J.b(new Object[]{this.m, 89714527855213L})) {
                                    GuiHelper var75 = new GuiHelper((HandledScreen)b.currentScreen);
                                    if (var75.a(new Object[]{37619827302565L}) == null
                                        || var75.a(new Object[]{37619827302565L}).getStack().getItem() != Items.TOTEM_OF_UNDYING) {
                                        if (this.I != null && this.I.getStack().getItem() != Items.TOTEM_OF_UNDYING) {
                                            this.I = null;
                                        }

                                        if (this.I == null) {
                                            ArrayList var55 = new ArrayList();

                                            for (Slot var57 : ((HandledScreen)b.currentScreen).getScreenHandler().slots) {
                                                if (var57.id != 45
                                                    && var57.id != 36 + ((Integer)this.h.getValue() - 1)
                                                    && var57.getStack().getItem() == Items.TOTEM_OF_UNDYING) {
                                                    var55.add(var57);
                                                }
                                            }

                                            if (!var55.isEmpty()) {
                                                this.I = (Slot)var55.get(this.H.nextInt(var55.size()));
                                            }
                                        }

                                        if (this.I != null) {
                                            double var77 = b.getWindow().getScaleFactor();
                                            int var84 = b.getWindow().getX();
                                            int var58 = b.getWindow().getY();
                                            int var59 = (Integer)FieldAccessor.a(new Object[]{HandledScreen.class, "x", 9152214377937L})
                                                .a_Object_(new Object[]{(InventoryScreen)b.currentScreen, 107155396285200L});
                                            int var60 = (Integer)FieldAccessor.a(new Object[]{HandledScreen.class, "y", 9152214377937L})
                                                .a_Object_(new Object[]{(InventoryScreen)b.currentScreen, 107155396285200L});
                                            int var61 = var59 + this.I.x + 8;
                                            int var62 = var60 + this.I.y + 8;
                                            int var63 = var84 + (int)(var61 * var77);
                                            int var64 = var58 + (int)(var62 * var77);
                                            Point var65 = new Point(var63, var64);
                                            Point var66 = MouseInfo.getPointerInfo().getLocation();
                                            if (this.M == null || !this.M.equals(var65) || this.L >= this.K.size()) {
                                                this.M = var65;
                                                this.K = this.b_List___(new Object[]{var66, var65, 31531047457231L});
                                                this.L = 0;
                                            }

                                            float var67 = (21.0F - this.l.randomInRange(new Object[]{877569900989L})) * 2.0F;
                                            if (this.L < this.K.size()) {
                                                RenderHelper27 var87 = this.N;
                                                var10004 = new Object[]{null, 2505922520844L};
                                                var10004[0] = var67;
                                                if (var87.a(var10004)) {
                                                    Point var68 = (Point)this.K.get(this.L);
                                                    double var69 = (double)this.L / this.K.size();
                                                    double var71 = Math.max(0.0, 1.0 - var69 * 2.5);
                                                    int var73 = (int)(this.H.nextGaussian() * 0.8 * var71);
                                                    int var74 = (int)(this.H.nextGaussian() * 0.8 * var71);
                                                    this.G.mouseMove(var68.x + var73, var68.y + var74);
                                                    this.L++;
                                                    this.N.a_void_____________________(new Object[]{93540861878173L});
                                                }
                                            }
                                        }
                                    }
                                }

                                boolean var76 = InventoryUtil.m(new Object[]{Items.TOTEM_OF_UNDYING, 6830336961985L});
                                if (!var76 && this.g.isSelected(new Object[]{"Switch", 47850572908778L})) {
                                    if (!(Boolean)this.i.getValue() && !(Boolean)this.k.getValue()) {
                                        var86 = Items.TOTEM_OF_UNDYING;
                                        var10004 = new Object[]{null, null, 77736144221765L};
                                        var10004[1] = false;
                                        var10004[0] = var86;
                                        List var79 = InventoryUtil.a_List(var10004);
                                        List var81 = var79.isEmpty() ? var51 : var79;
                                        int var10002 = var81.size() - 1;
                                        Object[] var100 = new Object[]{null, null, 97028099236409L};
                                        var100[1] = var10002;
                                        var100[0] = 0;
                                        int var85 = (Integer)var81.get(RenderUtil5.a_int__________________________(var100));
                                        int var91 = var79.isEmpty() ? 36 + var85 : var85;
                                        var100 = new Object[]{null, null, 108694851105992L};
                                        var100[1] = 40;
                                        var100[0] = var91;
                                        if (this.a(var100) && this.i(new Object[]{39191347047416L})) {
                                            return;
                                        }
                                    } else {
                                        GuiHelper var78 = new GuiHelper((HandledScreen)b.currentScreen);
                                        if (var78.a(new Object[]{37619827302565L}) == null
                                            || var78.a(new Object[]{37619827302565L}).getStack().getItem() != Items.TOTEM_OF_UNDYING) {
                                            this.s.a_void_____________________(new Object[]{93540861878173L});
                                            this.j.reroll(new Object[]{57083216809697L});
                                            return;
                                        }

                                        if (this.s.b(new Object[]{this.j, 89714527855213L})) {
                                            int var10001 = var78.a(new Object[]{37619827302565L}).id;
                                            Object[] var10005 = new Object[]{null, null, 108694851105992L};
                                            var10005[1] = 40;
                                            var10005[0] = var10001;
                                            if (this.a(var10005) && this.i(new Object[]{39191347047416L})) {
                                                return;
                                            }
                                        }
                                    }
                                }

                                if (var52 && this.g.isSelected(new Object[]{"Switch", 47850572908778L})) {
                                    var86 = Items.TOTEM_OF_UNDYING;
                                    var10004 = new Object[]{null, null, 77736144221765L};
                                    var10004[1] = false;
                                    var10004[0] = var86;
                                    List var80 = InventoryUtil.a_List(var10004);
                                    if (var80.size() > 0) {
                                        if (!(Boolean)this.i.getValue() && !(Boolean)this.k.getValue()) {
                                            int var94 = var80.size() - 1;
                                            Object[] var103 = new Object[]{null, null, 97028099236409L};
                                            var103[1] = var94;
                                            var103[0] = 0;
                                            int var83 = (Integer)var80.get(RenderUtil5.a_int__________________________(var103));
                                            var94 = (Integer)this.h.getValue() - 1;
                                            var103 = new Object[]{null, null, 108694851105992L};
                                            var103[1] = var94;
                                            var103[0] = var83;
                                            if (this.a(var103) && this.i(new Object[]{39191347047416L})) {
                                                return;
                                            }
                                        } else {
                                            GuiHelper var82 = new GuiHelper((HandledScreen)b.currentScreen);
                                            if (var82.a(new Object[]{37619827302565L}) == null
                                                || var82.a(new Object[]{37619827302565L}).getStack().getItem() != Items.TOTEM_OF_UNDYING) {
                                                this.s.a_void_____________________(new Object[]{93540861878173L});
                                                this.j.reroll(new Object[]{57083216809697L});
                                                return;
                                            }

                                            if (this.s.b(new Object[]{this.j, 89714527855213L})) {
                                                int var92 = var82.a(new Object[]{37619827302565L}).id;
                                                int var93 = (Integer)this.h.getValue() - 1;
                                                Object[] var102 = new Object[]{null, null, 108694851105992L};
                                                var102[1] = var93;
                                                var102[0] = var92;
                                                if (this.a(var102) && this.i(new Object[]{39191347047416L})) {
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }

                                if (!this.g.isSelected(new Object[]{"Close Inventory", 47850572908778L})) {
                                    return;
                                }

                                if (var52 || !var76) {
                                    return;
                                }

                                this.k(new Object[]{(HandledScreen)b.currentScreen, 43910558120181L});
                                if (this.i(new Object[]{39191347047416L})) {
                                    return;
                                }
                            }

                            RenderHelper27 var90 = this.r;
                            var10004 = new Object[]{null, 2505922520844L};
                            var10004[0] = 150.0F;
                            if (var90.a(var10004)) {
                                this.t = false;
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if (var1.a(new Object[0]) instanceof PlayerRespawnS2CPacket) {
            b.execute(this::f);
        } else if (b.world != null && b.player != null) {
            if (this.n.isSelected(new Object[]{"Anti Totem Guard Test", 47850572908778L})
                && var1.a(new Object[0]) instanceof EntityDamageS2CPacket var13
                && var13.entityId() == b.player.getId()) {
                DamageType var20 = var13.sourceType().value();
                if (var20.msgId().equals("generic")
                    && var20.exhaustion() == 0.0F
                    && var20.effects().equals(DamageEffects.HURT)
                    && var13.sourceCauseId() == -1
                    && var13.sourceType().getType().equals(Type.REFERENCE)) {
                    ModuleNotificationsElement.s(new Object[]{this, "Detected a manual totem check, ignoring pop.", NotificationType.WARNING, 111134823389700L});
                    b.world.playSoundFromEntity(b.player, b.player, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.AMBIENT, 13.0F, 5.0F);
                    this.O = System.currentTimeMillis() + 500L;
                }
            }

            if (var1.a(new Object[0]) instanceof EntityStatusS2CPacket var18) {
                Entity var22 = var18.getEntity(b.world);
                if (var22 == b.player && var18.getStatus() == 35) {
                    if (this.g(new Object[]{61132707951516L})) {
                        return;
                    }

                    this.i(new Object[]{39191347047416L});
                    this.t = true;
                }
            }

            if (var1.a(new Object[0]) instanceof BundlePacket var19) {
                for (Packet var15 : (Iterable<Packet>)(var19.getPackets())) {
                    if (var15 instanceof EntityStatusS2CPacket var16) {
                        Entity var17 = var16.getEntity(b.world);
                        if (var17 == b.player && var16.getStatus() == 35) {
                            if (this.g(new Object[]{61132707951516L})) {
                                return;
                            }

                            this.i(new Object[]{39191347047416L});
                            this.t = true;
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if (((String)this.c.getValue()).equals("Crystal") && this.n.isSelected(new Object[]{"Pause Interactions", 47850572908778L})) {
            if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket || var1.a(new Object[0]) instanceof PlayerInteractItemC2SPacket) {
                if (this.v != null && b.currentScreen != this.v) {
                    this.v = null;
                }

                if (this.v != null || Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{89789443909602L})) {
                    var1.a_void_____(new Object[0]);
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if ((Boolean)this.e.getValue()) {
            float var33 = b.player.getHealth() + b.player.getAbsorptionAmount();
            if (!Float.isNaN(this.F)) {
                float var34 = Math.max(0.0F, this.F - var33);
                if (var34 > 0.0F) {
                    this.D.addLast(new AutoTotemHelper(System.currentTimeMillis(), var34));
                }
            }

            this.F = var33;
        }

        if (((String)this.c.getValue()).equals("SMP")) {
            if (b.isWindowFocused()) {
                if (!this.e(new Object[]{106622657070449L})) {
                    boolean var38 = false;
                    if ((Boolean)this.q.getValue() && !InventoryUtil2.d(new Object[]{89063551742447L})) {
                        PlayerEntity var39 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
                        if (var39 != null) {
                            if (!Float.isNaN(this.C) && var39.getY() - this.C >= 0.4F && b.player.getY() < var39.getY()) {
                                Vec3d var35 = new Vec3d(var39.getX(), b.player.getY(), var39.getZ());
                                if (Math.sqrt(b.player.squaredDistanceTo(var35)) < 8.0) {
                                    var38 = true;
                                    TpsTimer var42 = Prestige.v;
                                    Object[] var10007 = new Object[]{null, null, null, NotificationType.WARNING, 104881728073391L};
                                    var10007[2] = 3000L;
                                    var10007[1] = "Detected mace look, switching to totem!";
                                    var10007[0] = "Auto Totem";
                                    var42.a(var10007);
                                }
                            }

                            this.C = (float)var39.getY();
                        }
                    }

                    if (!this.h(new Object[]{94918739196044L}) || var38) {
                        if (this.x != -1) {
                            int var46 = this.x;
                            Object[] var50 = new Object[]{null, 37392877673079L};
                            var50[0] = var46;
                            InventoryUtil.a_void________(var50);
                            this.x = -1;
                        } else if (!(Boolean)this.p.getValue() || !InventoryUtil2.d(new Object[]{89063551742447L})) {
                            if (b.player.getHealth() + b.player.getAbsorptionAmount() > (Float)this.o.getValue() && !var38) {
                                if (this.w != -1 && !b.player.isUsingItem()) {
                                    if (this.w < 9) {
                                        this.x = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                        if (InventoryUtil.a_int_____(new Object[]{134339616728996L}) != this.w) {
                                            int var45 = this.w;
                                            Object[] var49 = new Object[]{null, 37392877673079L};
                                            var49[0] = var45;
                                            InventoryUtil.a_void________(var49);
                                            NetworkUtil.a(
                                                new Object[]{
                                                    new UpdateSelectedSlotC2SPacket(InventoryUtil.a_int_____(new Object[]{134339616728996L})), 53690043409678L
                                                }
                                            );
                                        }

                                        NetworkUtil.a(
                                            new Object[]{
                                                new PlayerActionC2SPacket(Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN), 53690043409678L
                                            }
                                        );
                                        this.w = -1;
                                    } else {
                                        int var47 = this.w;
                                        Object[] var51 = new Object[]{null, ArmorState.RESTORE, 135903435660187L};
                                        var51[0] = var47;
                                        this.f(var51);
                                    }
                                }

                                this.i(new Object[]{39191347047416L});
                            } else if (!InventoryUtil.m(new Object[]{Items.TOTEM_OF_UNDYING, 6830336961985L}) && this.w == -1) {
                                Item var43 = Items.TOTEM_OF_UNDYING;
                                Object[] var10004 = new Object[]{null, null, 77736144221765L};
                                var10004[1] = true;
                                var10004[0] = var43;
                                List var40 = InventoryUtil.a_List(var10004);
                                Iterator var41 = var40.iterator();
                                if (var41.hasNext()) {
                                    Integer var36 = (Integer)var41.next();
                                    if (var36 < 9) {
                                        this.x = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                        if (InventoryUtil.a_int_____(new Object[]{134339616728996L}) != var36) {
                                            int var44 = var36;
                                            Object[] var48 = new Object[]{null, 37392877673079L};
                                            var48[0] = var44;
                                            InventoryUtil.a_void________(var48);
                                            NetworkUtil.a(
                                                new Object[]{
                                                    new UpdateSelectedSlotC2SPacket(InventoryUtil.a_int_____(new Object[]{134339616728996L})), 53690043409678L
                                                }
                                            );
                                        }

                                        NetworkUtil.a(
                                            new Object[]{
                                                new PlayerActionC2SPacket(Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN), 53690043409678L
                                            }
                                        );
                                        this.w = var36;
                                    } else {
                                        int var10001 = var36;
                                        Object[] var10005 = new Object[]{null, ArmorState.EQUIP, 135903435660187L};
                                        var10005[0] = var10001;
                                        this.f(var10005);
                                    }
                                }

                                this.i(new Object[]{39191347047416L});
                            } else {
                                this.i(new Object[]{39191347047416L});
                            }
                        }
                    }
                }
            }
        } else {
            if (this.n.isSelected(new Object[]{"Double Hand On GUI", 47850572908778L}) && b.currentScreen != null) {
                Integer var37 = InventoryUtil.a_Integer(new Object[]{Items.TOTEM_OF_UNDYING, 64376993919254L});
                if (var37 != null) {
                    int var10000 = var37;
                    Object[] var10003 = new Object[]{null, 37392877673079L};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                }
            }
        }
    }

    private boolean h(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 11884601400623L;
        return !this.r.b(new Object[]{this.d, var4});
    }

    private boolean i(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 49807578027900L;
        long var6 = var2 ^ 51041646505355L;
        long var8 = var2 ^ 136010639045547L;
        long var10 = var2 ^ 32207504089815L;
        Event.b();
        this.d.reroll(new Object[]{var10});
        if ((Boolean)this.e.getValue() && ((String)this.c.getValue()).equals("Crystal")) {
            float var13 = this.b_float___________(new Object[]{var4});
            float var14 = this.d.randomInRange(new Object[]{var6});
            float var15 = (var14 - this.d.getMin()) * (float)Math.pow(var13 / 100.0F, 2.0);
            float var16 = Math.max(this.d.getMin(), var14 - var15);
            this.d.setRolled(new Object[]{var16});
        }

        this.r.a_void_____________________(new Object[]{var8});
        return true;
    }

    private void n(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14313756215246L ^ var2;
        long var4 = var2 ^ 79538084803541L;
        long var6 = var2 ^ 4999677100091L;

        try {
            FieldAccessor.a(new Object[]{GraphicsEnvironment.class, "headless", var6}).c(new Object[]{false, var4});
            this.G = new Robot();
        } catch (Exception var8) {
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
