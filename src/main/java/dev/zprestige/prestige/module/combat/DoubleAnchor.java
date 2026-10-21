package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.type.DoubleAnchorState;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.world.RaycastUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class DoubleAnchor extends Module {
    private FloatSetting a;
    private BoolSetting d;
    private RangeSetting c;
    private KeybindSetting f;
    private BoolSetting e;
    private RangeSetting g;
    private EnumSetting h;
    private BoolSetting i;
    private IntSetting j;
    private BoolSetting k;
    private FloatSetting l;
    private IntSetting m;
    private RenderHelper27 n = new RenderHelper27(26293358099357L);
    private RenderHelper27 o = new RenderHelper27(26293358099357L);
    private BlockPos p = null;
    private int q = 0;
    private boolean r = false;
    public static boolean s;
    private int t = 0;
    private RenderHelper27 u = new RenderHelper27(26293358099357L);
    private RenderHelper27 v = new RenderHelper27(26293358099357L);
    private DoubleAnchorState w = DoubleAnchorState.IDLE;
    private BlockHitResult x = null;
    private BlockPos y = null;
    private int z = -1;
    private boolean A = false;
    private boolean B = false;
    private boolean C = false;

    public DoubleAnchor() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.h.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL185_0 -> (Integer)this.f.getValue() != -1, 90319707064974L});
        this.g.setVisibility_RangeSetting(new Object[]{(Predicate<Float>)lpL186_0 -> (Integer)this.f.getValue() != -1, 37043306203287L});
        this.l
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL181_0 -> (Integer)this.f.getValue() != -1 && (Boolean)this.k.getValue(), 35046756052036L}
            );
        this.m
            .setVisibility_IntSetting(
                new Object[]{(Predicate<Integer>)lpL180_0 -> (Integer)this.f.getValue() != -1 && (Boolean)this.k.getValue(), 98265949679296L}
            );
        this.i
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL184_0 -> (Integer)this.f.getValue() != -1 && ((String)this.h.getValue()).equals("Swap"), 73196168581295L}
            );
        this.k.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL182_0 -> (Integer)this.f.getValue() != -1, 73196168581295L});
        this.e.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL187_0 -> (Integer)this.f.getValue() != -1, 73196168581295L});
        this.j
            .setVisibility_IntSetting(
                new Object[]{(Predicate<Integer>)lpL183_0 -> (Integer)this.f.getValue() != -1 && ((String)this.h.getValue()).equals("Swap"), 98265949679296L}
            );
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public void onEnable(Object[] var1) {
        this.j(new Object[0]);
        this.A = false;
    }

    @Override
    public void onDisable(Object[] var1) {
        this.j(new Object[0]);
        this.A = false;
        s = false;
        this.t = 0;
    }

    private void j(Object[] var1) {
        this.w = DoubleAnchorState.IDLE;
        this.x = null;
        this.y = null;
        this.z = -1;
        this.B = false;
        this.C = false;
    }

    private boolean a(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        return b.world.getBlockState(var2).isOf(Blocks.RESPAWN_ANCHOR) && b.world.getBlockState(var2).get(Properties.CHARGES) > 0;
    }

    private boolean d(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        return b.world.getBlockState(var2).isOf(Blocks.RESPAWN_ANCHOR);
    }

    private boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 79187590595000L ^ var2;
        long var4 = var2 ^ 96108774942226L;
        long var6 = var2 ^ 99264852426502L;
        if (!(Boolean)this.k.getValue()) {
            return false;
        } else {
            PlayerEntity var9 = CombatUtil.a_class_1657(new Object[]{var6});
            if (var9 == null) {
                return false;
            } else if (b.player.distanceTo(var9) >= (Float)this.l.getValue()) {
                return false;
            } else if (var9.hurtTime == 0) {
                return false;
            } else {
                RenderHelper27 var10000 = this.v;
                float var10001 = ((Integer)this.m.getValue()).intValue();
                Object[] var10004 = new Object[]{null, var4};
                var10004[0] = var10001;
                return !var10000.a(var10004);
            }
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        s = this.B || b.player != null && b.player.age <= this.t;
        if ((Boolean)this.d.getValue() && this.r) {
            RenderHelper27 var10000 = this.o;
            float var10001 = (Float)this.c.getValue();
            Object[] var10004 = new Object[]{null, 2505922520844L};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                if (!(b.crosshairTarget instanceof BlockHitResult var9)) {
                    this.r = false;
                    return;
                }

                if (b.world.getBlockState(var9.getBlockPos()).getBlock() == Blocks.RESPAWN_ANCHOR
                    && b.world.getBlockState(var9.getBlockPos()).get(Properties.CHARGES) == 0) {
                    this.r = false;
                    this.t = b.player.age + 2;
                    s = true;
                    Item var10 = Items.GLOWSTONE;
                    Runnable var11 = () -> InventoryUtil2.a(new Object[]{var9, 96519249476482L});
                    Object[] var10005 = new Object[]{null, null, null, 114857082072684L};
                    var10005[2] = false;
                    var10005[1] = var11;
                    var10005[0] = var10;
                    InventoryUtil.j(var10005);
                    return;
                }

                this.r = false;
                return;
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        if (!this.B) {
            if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket) {
                RenderHelper27 var10000 = this.n;
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = 200.0F;
                if (var10000.a(var10004)) {
                    if (!InventoryUtil.l(new Object[]{Items.RESPAWN_ANCHOR, 65539821781598L})) {
                        return;
                    }

                    BlockHitResult var17 = (BlockHitResult)b.crosshairTarget;
                    if (var17 != null
                        && b.world.getBlockState(var17.getBlockPos()).isOf(Blocks.RESPAWN_ANCHOR)
                        && b.world.getBlockState(var17.getBlockPos()).get(Properties.CHARGES) != 0) {
                        var10004 = new Object[]{null, null, 29524726012237L};
                        var10004[1] = 100.0F;
                        var10004[0] = 0.0F;
                        if (RenderUtil5.a(var10004) > (Float)this.a.getValue()) {
                            return;
                        }

                        if (var17.getBlockPos().equals(this.p)) {
                            if (this.q >= 1) {
                                return;
                            }
                        } else {
                            this.p = var17.getBlockPos();
                            this.q = 0;
                        }

                        this.n.a_void_____________________(new Object[]{93540861878173L});
                        this.t = b.player.age + 2;
                        s = true;
                        InventoryUtil2.a(new Object[]{var17, 96519249476482L});
                        this.r = true;
                        this.c.reroll(new Object[]{57083216809697L});
                        this.o.a_void_____________________(new Object[]{93540861878173L});
                        this.q++;
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if ((Integer)this.f.getValue() != -1) {
            if (b.player != null && b.world != null) {
                if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem() && !b.player.isSneaking()) {
                    if (this.w != DoubleAnchorState.IDLE) {
                        if (this.u.b(new Object[]{this.g, 89714527855213L})) {
                            this.k(new Object[]{35898293100276L});
                        }
                    } else {
                        boolean var23 = this.f.isPressed(new Object[]{28844609523469L});
                        if (!var23) {
                            this.A = false;
                        } else if (!this.A || (Boolean)this.e.getValue()) {
                            if (this.u.b(new Object[]{this.g, 89714527855213L})) {
                                if (b.crosshairTarget instanceof BlockHitResult var24 && var24.getType() == Type.BLOCK) {
                                    if (WorldUtil2.d(new Object[]{var24, 85348799407646L})) {
                                        if (!b.world.getBlockState(var24.getBlockPos()).isOf(Blocks.RESPAWN_ANCHOR)) {
                                            BlockPos var26 = b.world.getBlockState(var24.getBlockPos()).isReplaceable()
                                                ? var24.getBlockPos()
                                                : WorldUtil2.a_class_2338______(new Object[]{var24, 110292667014705L});
                                            if (!b.player.getBoundingBox().intersects(new Box(var26))) {
                                                this.A = true;
                                                this.x = var24;
                                                this.y = var26;
                                                this.z = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                                this.w = DoubleAnchorState.PLACE_1;
                                                this.u.a_void_____________________(new Object[]{93540861878173L});
                                                this.g.reroll(new Object[]{57083216809697L});
                                                this.m(new Object[]{"Macro: sequence started", 19751549127680L});
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

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 79187590595000L ^ var2;
        long var4 = var2 ^ 67827615613137L;
        long var6 = var2 ^ 110229631654360L;
        long var8 = var2 ^ 100372545474989L;
        long var10 = var2 ^ 124386046323710L;
        long var12 = var2 ^ 116426037201411L;
        long var14 = var2 ^ 108521349448208L;
        long var16 = var2 ^ 4840746324187L;
        long var18 = var2 ^ 129228536209386L;
        long var20 = var2 ^ 130730732197639L;
        long var22 = var2 ^ 66390967204072L;
        long var24 = var2 ^ 24988888660708L;
        long var26 = var2 ^ 133408951276364L;
        long var28 = var2 ^ 138016866472735L;
        Event.b();
        this.B = true;

        label329: {
            try {
                switch (this.w) {
                    case PLACE_1:
                        if (b.player.getBoundingBox().intersects(new Box(this.y))) {
                            this.l(new Object[]{"player blocking placement", var10});
                            return;
                        }

                        if (this.f(new Object[]{this.x, var6})) {
                            this.m(new Object[]{"Macro: placed anchor", var26});
                            this.w = DoubleAnchorState.GLOW_1;
                            break label329;
                        }

                        this.l(new Object[]{"no respawn anchor", var10});
                        return;
                    case GLOW_1:
                        if (!this.d(new Object[]{this.y, var20})) {
                            this.l(new Object[]{"anchor missing", var10});
                            return;
                        }

                        if (this.g(new Object[]{this.a_class_3965_______(new Object[]{this.y, var14}), var28})) {
                            this.m(new Object[]{"Macro: charged anchor", var26});
                            this.w = DoubleAnchorState.EXPLODE_DOUBLE_1;
                            break label329;
                        }

                        this.l(new Object[]{"no glowstone", var10});
                        return;
                    case EXPLODE_DOUBLE_1:
                        if (!this.a(new Object[]{this.y, var22})) {
                            this.l(new Object[]{"anchor not charged", var10});
                            return;
                        }

                        if (this.i(new Object[]{this.a_class_3965_______(new Object[]{this.y, var14}), var24})) {
                            this.m(new Object[]{"Macro: exploded + double-placed", var26});
                            this.w = DoubleAnchorState.GLOW_2;
                            break label329;
                        }

                        this.l(new Object[]{"explode failed", var10});
                        return;
                    case GLOW_2:
                        if (!this.d(new Object[]{this.y, var20})) {
                            this.l(new Object[]{"double failed", var10});
                            return;
                        }

                        if (this.g(new Object[]{this.a_class_3965_______(new Object[]{this.y, var14}), var28})) {
                            this.m(new Object[]{"Macro: charged again", var26});
                            this.w = DoubleAnchorState.EXPLODE_2;
                            this.v.a_void_____________________(new Object[]{var4});
                            this.C = false;
                            break label329;
                        }

                        this.l(new Object[]{"no glowstone (2)", var10});
                        return;
                    case EXPLODE_2:
                        if (!this.a(new Object[]{this.y, var22})) {
                            this.l(new Object[]{"second anchor not charged", var10});
                            return;
                        }

                        if (!this.e(new Object[]{var18})) {
                            this.C = false;
                            if (this.h(new Object[]{this.a_class_3965_______(new Object[]{this.y, var14}), var12})) {
                                this.m(new Object[]{"Macro: exploded again", var26});
                                this.w = DoubleAnchorState.RESTORE;
                                break label329;
                            }

                            this.l(new Object[]{"explode failed (2)", var10});
                            return;
                        }

                        if (!this.C) {
                            this.m(new Object[]{"Macro: waiting for damage tick", var26});
                            this.C = true;
                        }
                        break;
                    case RESTORE:
                        if ((Boolean)this.i.getValue() && "Swap".equals(this.h.getValue()) && this.z != -1) {
                            Renderer2D var10000 = Prestige.o;
                            int var10001 = this.z;
                            Object[] var10004 = new Object[]{null, var16};
                            var10004[0] = var10001;
                            var10000.i(var10004);
                        }

                        this.m(new Object[]{"Macro: done", var26});
                        this.j(new Object[0]);
                    default:
                        break label329;
                }
            } finally {
                this.B = false;
            }

            return;
        }

        this.u.a_void_____________________(new Object[]{var4});
        this.g.reroll(new Object[]{var8});
    }

    private void l(Object[] var1) {
        String var4 = (String)var1[0];
        long var2 = (Long)var1[1];
        var2 = 79187590595000L ^ var2;
        long var5 = var2 ^ 67356845505181L;
        long var7 = var2 ^ 5042136492695L;
        long var9 = var2 ^ 108217669876715L;
        long var11 = var2 ^ 70850301196554L;
        if ("Swap".equals(this.h.getValue()) && this.z != -1) {
            Renderer2D var10000 = Prestige.o;
            int var10001 = this.z;
            Object[] var10004 = new Object[]{null, var5};
            var10004[0] = var10001;
            var10000.i(var10004);
        }

        this.m(new Object[]{"Macro aborted: " + var4, var11});
        this.j(new Object[0]);
        this.u.a_void_____________________(new Object[]{var7});
        this.g.reroll(new Object[]{var9});
    }

    private void m(Object[] var1) {
        String var2 = (String)var1[0];
        long var3 = (Long)var1[1];
        var3 = 79187590595000L ^ var3;
        long var5 = var3 ^ 66933299812284L;
        ModuleNotificationsElement.s(new Object[]{this, var2, NotificationType.INFO, var5});
    }

    private BlockHitResult a_class_3965_______(Object[] var1) {
        BlockPos var4 = (BlockPos)var1[0];
        long var2 = (Long)var1[1];
        var2 = 79187590595000L ^ var2;
        long var5 = var2 ^ 71420769162501L;
        Event.b();
        Vec3d var8 = RaycastUtil.b(new Object[]{var4, var5});
        if (var8 == null) {
            var8 = new Vec3d(var4.getX() + 0.5, var4.getY() + 1.0, var4.getZ() + 0.5);
        }

        return new BlockHitResult(var8, Direction.UP, var4, false);
    }

    private boolean f(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        long var3 = (Long)var1[1];
        var3 = 79187590595000L ^ var3;
        long var5 = var3 ^ 21089574976686L;
        long var7 = var3 ^ 140175967520114L;
        long var9 = var3 ^ 99518620667500L;
        if ("None".equals(this.h.getValue())) {
            if (!InventoryUtil.l(new Object[]{Items.RESPAWN_ANCHOR, var7})) {
                return false;
            }
        } else if (!InventoryUtil.i(new Object[]{Blocks.RESPAWN_ANCHOR, var9})) {
            return false;
        }

        InventoryUtil2.a(new Object[]{var2, var5});
        return true;
    }

    private boolean g(Object[] var1) {
        BlockHitResult var4 = (BlockHitResult)var1[0];
        long var2 = (Long)var1[1];
        var2 = 79187590595000L ^ var2;
        long var5 = var2 ^ 11991898568297L;
        long var7 = var2 ^ 112953789861813L;
        long var9 = var2 ^ 73962093397163L;
        if ("None".equals(this.h.getValue())) {
            if (!InventoryUtil.l(new Object[]{Items.GLOWSTONE, var7})) {
                return false;
            }
        } else if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var9})) {
            return false;
        }

        InventoryUtil2.a(new Object[]{var4, var5});
        return true;
    }

    private boolean h(Object[] var1) {
        BlockHitResult var4 = (BlockHitResult)var1[0];
        long var2 = (Long)var1[1];
        var2 = 79187590595000L ^ var2;
        long var5 = var2 ^ 33591308398453L;
        long var7 = var2 ^ 117954845200000L;
        long var9 = var2 ^ 126298217174697L;
        if ("None".equals(this.h.getValue())) {
            if (InventoryUtil.l(new Object[]{Items.GLOWSTONE, var9})) {
                return false;
            }
        } else {
            int var10000 = (Integer)this.j.getValue() - 1;
            Object[] var10003 = new Object[]{null, var7};
            var10003[0] = var10000;
            InventoryUtil.a_void________(var10003);
        }

        InventoryUtil2.a(new Object[]{var4, var5});
        return true;
    }

    private boolean i(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        long var3 = (Long)var1[1];
        var3 = 79187590595000L ^ var3;
        long var5 = var3 ^ 107566235574162L;
        long var7 = var3 ^ 60872260497759L;
        long var9 = var3 ^ 14876577441358L;
        long var11 = var3 ^ 44495992630608L;
        if (!this.h(new Object[]{var2, var7})) {
            return false;
        } else {
            if ("None".equals(this.h.getValue())) {
                if (InventoryUtil.l(new Object[]{Items.RESPAWN_ANCHOR, var9})) {
                    InventoryUtil2.a(new Object[]{var2, var5});
                }
            } else if (InventoryUtil.i(new Object[]{Blocks.RESPAWN_ANCHOR, var11})) {
                InventoryUtil2.a(new Object[]{var2, var5});
            }

            return true;
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
