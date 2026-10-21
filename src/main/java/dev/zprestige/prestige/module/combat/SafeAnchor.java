package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.inventory.ArmorUtil;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.math.MathUtil3;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.SafeAnchorHelper;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.RaycastUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class SafeAnchor extends Module implements RenderContract3 {
    private EnumSetting a;
    private BoolSetting d;
    private FloatSetting c;
    private FloatSetting e;
    private EnumSetting f;
    private RangeSetting g;
    private EnumSetting h;
    private EnumSetting i;
    private IntSetting j;
    private FloatSetting k;
    private HashMap l = new HashMap();
    private HashMap m = new HashMap();
    private BlockPos n;
    private int o;
    private ArrayList p = new ArrayList();
    private ArrayList q = new ArrayList();
    private RenderHelper27 r = new RenderHelper27(26293358099357L);
    private static boolean s;

    public SafeAnchor() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.f.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL386_0 -> ((String)this.a.getValue()).equals("Legit"), 90319707064974L});
        this.k.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL385_0 -> ((String)this.a.getValue()).equals("Blatant"), 35046756052036L});
        this.d.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL387_0 -> ((String)this.a.getValue()).equals("Blatant"), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    public static boolean d(Object[] var0) {
        return s;
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.l.clear();
        this.m.clear();
        this.p.clear();
        this.q.clear();
        this.n = null;
        this.o = 0;
        s = false;
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 119956318131507L ^ var2;
        long var4 = var2 ^ 107042677122919L;
        long var6 = var2 ^ 134344802069301L;
        long var8 = var2 ^ 111951408962199L;
        long var10 = var2 ^ 113720823272615L;
        long var12 = var2 ^ 41048373857916L;
        long var14 = var2 ^ 8397830927899L;
        long var16 = var2 ^ 59436072296438L;
        long var18 = var2 ^ 105288676554375L;
        long var20 = var2 ^ 17395971078820L;
        long var22 = var2 ^ 96835133630541L;
        long var24 = var2 ^ 31428524456361L;
        Event.b();
        s = false;
        if (((String)this.a.getValue()).equalsIgnoreCase("Legit")) {
            if (this.n == null) {
                return;
            }

            if (!WorldUtil2.b(new Object[]{this.n, Blocks.RESPAWN_ANCHOR, var10})
                || PositionUtil.b(new Object[]{var18}).distanceTo(this.n.toCenterPos()) > InventoryUtil2.b_float_____(new Object[]{var12})
                || b.player.getY() < this.n.getY()
                || (Integer)this.l.getOrDefault(this.n, 0) >= (Integer)this.j.getValue()) {
                this.o = 0;
                return;
            }

            int var27 = WorldUtil2.a_int______________________________(new Object[]{this.n, var6});
            if (var27 > 0 && this.o == 0) {
                this.r.a_void_____________________(new Object[]{var4});
                this.g.reroll(new Object[]{var14});
            }

            this.o = var27;
            if (!((String)this.f.getValue()).equals("Before Charge") && var27 == 0) {
                return;
            }

            RenderHelper27 var10000 = this.r;
            float var10001 = this.g.getRolled(new Object[0]) + 250.0F;
            Object[] var10004 = new Object[]{null, var16};
            var10004[0] = var10001;
            if (var10000.a(var10004)) {
                return;
            }

            if (((String)this.h.getValue()).equals("None")
                && !InventoryUtil.l(new Object[]{(((String)this.i.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE : Blocks.OBSIDIAN).asItem(), var20})) {
                return;
            }

            s = Prestige.f.k(new Object[]{b.player, this.n.toCenterPos(), var22}) >= ((Float)this.c.getValue()).floatValue();
        } else {
            for (BlockPos var28 : (Iterable<BlockPos>)(this.m.keySet())) {
                if (WorldUtil2.b(new Object[]{var28, Blocks.RESPAWN_ANCHOR, var10})
                    && (!(Boolean)this.d.getValue() || this.q.contains(var28))
                    && (Integer)this.l.getOrDefault(var28, 0) < (Integer)this.j.getValue()) {
                    SafeAnchorHelper var29 = (SafeAnchorHelper)this.m.get(var28);
                    boolean var30 = var29 != null && !var29.a(new Object[]{var24}) || !this.r.b(new Object[]{this.g, var8});
                    if (var30 && Prestige.f.k(new Object[]{b.player, var28.toCenterPos(), var22}) >= ((Float)this.c.getValue()).floatValue()) {
                        s = true;
                        return;
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        this.j(new Object[]{98202227174857L});
        if ((Boolean)this.d.getValue()) {
            this.q.removeIf(lpL384_0 -> b.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL384_0)).getBlock() != Blocks.RESPAWN_ANCHOR);
        }

        this.l.keySet().removeIf(lpL383_0 -> !WorldUtil2.b(new Object[]{((net.minecraft.util.math.BlockPos)lpL383_0), Blocks.RESPAWN_ANCHOR, 91415361137757L}));
        this.m.keySet().removeIf(lpL382_0 -> !WorldUtil2.b(new Object[]{((net.minecraft.util.math.BlockPos)lpL382_0), Blocks.RESPAWN_ANCHOR, 91415361137757L}));
        if (((String)this.a.getValue()).equalsIgnoreCase("Legit") && b.currentScreen == null && !b.player.isUsingItem() && b.player.isOnGround()) {
            if (!Prestige.o.d_boolean______(new Object[0])) {
                if (this.n != null) {
                    if (WorldUtil2.b(new Object[]{this.n, Blocks.RESPAWN_ANCHOR, 91415361137757L})
                        && !(
                            PositionUtil.b(new Object[]{118247215910525L}).distanceTo(this.n.toCenterPos())
                                > InventoryUtil2.b_float_____(new Object[]{18814856656518L})
                        )) {
                        if (this.r.b(new Object[]{this.g, 89714527855213L})) {
                            BlockPos var29 = this.n;
                            if (!(b.player.getY() < var29.getY())) {
                                Block var30 = ((String)this.i.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE : Blocks.OBSIDIAN;
                                if (!((String)this.h.getValue()).equals("None") || InventoryUtil.l(new Object[]{var30.asItem(), 65539821781598L})) {
                                    if (b.crosshairTarget instanceof BlockHitResult var31) {
                                        if (!MathUtil3.a_boolean_______(new Object[]{var31.getBlockPos(), 44262730353436L})
                                            && !MathUtil3.b_boolean_____(new Object[]{var31.getBlockPos(), 57098141355368L})) {
                                            BlockPos var37 = var31.getBlockPos();
                                            if (!var29.equals(var37)
                                                && (Integer)this.l.getOrDefault(var29, 0) < (Integer)this.j.getValue()
                                                && (
                                                    !((String)this.f.getValue()).equals("After Charge")
                                                        || WorldUtil2.a_int______________________________(new Object[]{var29, 86201794204623L}) != 0
                                                )) {
                                                if (!(
                                                    Prestige.f.k(new Object[]{b.player, var29.toCenterPos(), 119141635395767L})
                                                        < ((Float)this.c.getValue()).floatValue()
                                                )) {
                                                    if ((
                                                            !WorldUtil2.b(new Object[]{var31.getBlockPos(), Blocks.FIRE, 91415361137757L})
                                                                    && !WorldUtil2.b(
                                                                        new Object[]{
                                                                            var31.getBlockPos(),
                                                                            WorldUtil2.a_class_2248_(new Object[]{12315318725950L}),
                                                                            91415361137757L
                                                                        }
                                                                    )
                                                                ? var37.getY() < var29.getY()
                                                                : var37.getY() <= var29.getY()
                                                        )
                                                        && var37.getY() >= var29.getY() - 1) {
                                                        if (!(
                                                            b.player.squaredDistanceTo(var29.toCenterPos()) > b.player.squaredDistanceTo(this.n.toCenterPos())
                                                        )) {
                                                            if (!(
                                                                var29.getSquaredDistance(this.n.toCenterPos())
                                                                    > b.player.squaredDistanceTo(this.n.toCenterPos())
                                                            )) {
                                                                BlockPos var33 = !WorldUtil2.b(new Object[]{var37, Blocks.FIRE, 91415361137757L})
                                                                        && !WorldUtil2.b(
                                                                            new Object[]{
                                                                                var37, WorldUtil2.a_class_2248_(new Object[]{12315318725950L}), 91415361137757L
                                                                            }
                                                                        )
                                                                    ? var37.add(
                                                                        var31.getSide().getOffsetX(),
                                                                        var31.getSide().getOffsetY(),
                                                                        var31.getSide().getOffsetZ()
                                                                    )
                                                                    : var37;
                                                                BlockState var34 = b.world.getBlockState(var33);
                                                                b.world.setBlockState(var33, var30.getDefaultState());
                                                                double var35 = Prestige.f.k(new Object[]{b.player, var29.toCenterPos(), 119141635395767L});
                                                                b.world.setBlockState(var33, var34);
                                                                if (!(var35 > ((Float)this.e.getValue()).floatValue())) {
                                                                    this.l.merge(var29, 1, (java.util.function.BiFunction)(a_, b_) -> Integer.sum((Integer)a_, (Integer)b_));
                                                                    Prestige.o.b(new Object[0]);
                                                                    Runnable var10001 = () -> InventoryUtil2.a(new Object[]{var31, 96519249476482L});
                                                                    boolean var10002 = ((String)this.h.getValue()).equals("Silent");
                                                                    Object[] var10005 = new Object[]{null, null, null, 135286962703597L};
                                                                    var10005[2] = var10002;
                                                                    var10005[1] = var10001;
                                                                    var10005[0] = var30;
                                                                    InventoryUtil.k(var10005);
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
                        this.n = null;
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (ArmorUtil.a(new Object[]{12659099461385L})) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var13) {
            ItemStack var18 = var13.getHand().equals(Hand.MAIN_HAND) ? b.player.getMainHandStack() : b.player.getOffHandStack();
            if (var18.getItem() == Items.RESPAWN_ANCHOR) {
                BlockHitResult var15 = var13.getBlockHitResult();
                BlockPos var16 = var15.getBlockPos().add(var15.getSide().getOffsetX(), var15.getSide().getOffsetY(), var15.getSide().getOffsetZ());
                if (!(Boolean)this.d.getValue() || this.q.contains(var16)) {
                    return;
                }

                this.q
                    .add(
                        !WorldUtil2.b(new Object[]{var15.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L})
                                && !WorldUtil2.b(new Object[]{var15.getBlockPos(), Blocks.FIRE, 91415361137757L})
                                && !WorldUtil2.b(new Object[]{var15.getBlockPos(), WorldUtil2.a_class_2248_(new Object[]{12315318725950L}), 91415361137757L})
                            ? var16
                            : var15.getBlockPos()
                    );
            }
        }

        if (((String)this.a.getValue()).equalsIgnoreCase("Legit")) {
            if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var17) {
                ItemStack var20 = var17.getHand().equals(Hand.MAIN_HAND) ? b.player.getMainHandStack() : b.player.getOffHandStack();
                if (var20.getItem() == Items.RESPAWN_ANCHOR) {
                    BlockHitResult var21 = var17.getBlockHitResult();
                    BlockPos var22 = var21.getBlockPos().add(var21.getSide().getOffsetX(), var21.getSide().getOffsetY(), var21.getSide().getOffsetZ());
                    this.n = !WorldUtil2.b(new Object[]{var21.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L})
                            && !WorldUtil2.b(new Object[]{var21.getBlockPos(), Blocks.FIRE, 91415361137757L})
                            && !WorldUtil2.b(new Object[]{var21.getBlockPos(), WorldUtil2.a_class_2248_(new Object[]{12315318725950L}), 91415361137757L})
                        ? var22
                        : var21.getBlockPos();
                    this.o = 0;
                    this.r.a_void_____________________(new Object[]{93540861878173L});
                    this.g.reroll(new Object[]{57083216809697L});
                }
            }
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
        Prestige.o.f(new Object[]{this, var4});
        this.l.clear();
        this.m.clear();
        this.p.clear();
        this.n = null;
        this.o = 0;
        s = false;
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 85123560351320L;
        long var6 = var2 ^ 29247161167908L;
        long var8 = var2 ^ 136752692109656L;
        long var10 = var2 ^ 39587186614724L;
        long var12 = var2 ^ 127616945565159L;
        long var14 = var2 ^ 39382126296846L;
        long var16 = var2 ^ 116785209433731L;
        long var18 = var2 ^ 23045327291459L;
        long var20 = var2 ^ 784510322407L;
        long var22 = var2 ^ 75440226304702L;
        long var24 = var2 ^ 33065978990036L;
        long var26 = var2 ^ 114122501858469L;
        long var28 = var2 ^ 136772110525137L;
        long var30 = var2 ^ 103998936193343L;
        long var32 = var2 ^ 101816702374277L;
        long var34 = var2 ^ 57726443201364L;
        long var36 = var2 ^ 113623159615210L;
        long var38 = var2 ^ 33191619103831L;
        if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else if (!((String)this.a.getValue()).equalsIgnoreCase("Blatant")) {
            return null;
        } else if (b.currentScreen == null && !b.player.isUsingItem()) {
            if (!this.r.b(new Object[]{this.g, var24})) {
                return null;
            } else if (((String)this.h.getValue()).equals("None")
                && !InventoryUtil.l(new Object[]{((String)this.i.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE.asItem() : Blocks.OBSIDIAN.asItem(), var12})
                )
             {
                return null;
            } else {
                Block var41 = ((String)this.i.getValue()).equals("Glowstone") ? Blocks.GLOWSTONE : Blocks.OBSIDIAN;
                short var10000 = 18321;
                this.p.clear();
                Object[] var10004 = new Object[]{
                    null, (Predicate<BlockPos>)lpL380_0 -> WorldUtil2.b(new Object[]{((net.minecraft.util.math.BlockPos)lpL380_0), Blocks.RESPAWN_ANCHOR, 91415361137757L}), var22
                };
                var10004[0] = 10;

                for (BlockPos var43 : (Iterable<BlockPos>)(WorldUtil2.a_List___(var10004))) {
                    if (PositionUtil.b(new Object[]{var10}).distanceTo(var43.toCenterPos()) <= 10.0) {
                        this.p.add(var43);
                        this.m.computeIfAbsent(var43, lpL379_0 -> new SafeAnchorHelper(this.g.randomInRange(new Object[]{877569900989L}), 74389771242761L));
                    }
                }

                HitResult var60 = null;
                RenderBatch var61 = null;
                BlockPos var44 = null;
                double var45 = Double.MAX_VALUE;

                for (BlockPos var48 : (Iterable<BlockPos>)(this.p)) {
                    if (this.q.contains(var48) || !(Boolean)this.d.getValue()) {
                        SafeAnchorHelper var49 = (SafeAnchorHelper)this.m.get(var48);
                        if (var49 != null
                            && var49.a(new Object[]{var36})
                            && !(Prestige.f.k(new Object[]{b.player, var48.toCenterPos(), var14}) < ((Float)this.c.getValue()).floatValue())
                            && (Integer)this.l.getOrDefault(var48, 0) < (Integer)this.j.getValue()) {
                            for (int var50 = -5; var50 <= 5; var50++) {
                                for (int var51 = -5; var51 <= 5; var51++) {
                                    if (!(
                                        PositionUtil.b(new Object[]{var10}).distanceTo(var48.toCenterPos()) > InventoryUtil2.b_float_____(new Object[]{var30})
                                    )) {
                                        BlockPos var52 = var48.add(var50, -1, var51);
                                        if (WorldUtil2.e(new Object[]{var52, var18}) && !WorldUtil2.g(new Object[]{var52.up(), var20})) {
                                            BlockPos var53 = var48.add(var50, 0, var51);
                                            double var54 = Prestige.f.l(new Object[]{b.player, var48.toCenterPos(), var53, var41.getDefaultState(), var38});
                                            if (!(var54 > ((Float)this.e.getValue()).floatValue())) {
                                                Vec3d var56 = RaycastUtil.b(new Object[]{var52, var4});
                                                if (var56 != null) {
                                                    RenderBatch var57 = Prestige.o.a_RenderBatch(new Object[]{var56, var32});
                                                    float var58 = MathHelper.wrapDegrees(var57.a(new Object[0]) - b.player.getYaw());
                                                    if (!(var58 > (Float)this.k.getValue()) && !(var58 < -(Float)this.k.getValue())) {
                                                        HitResult var59 = WorldUtil.a_class_239_(new Object[]{var57, var16});
                                                        if (!(var59.getPos().distanceTo(var56) > 0.5) && (var60 == null || var54 < var45)) {
                                                            var60 = var59;
                                                            var45 = var54;
                                                            var61 = var57;
                                                            var44 = var48;
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

                if (var60 == null) {
                    return null;
                } else if (var61.a_boolean________________________________________(new Object[0])) {
                    return var61;
                } else {
                    HitResult var62 = var60;
                    if (!(
                        var62 instanceof BlockHitResult var63
                            && (
                                MathUtil3.a_boolean_______(new Object[]{var63.getBlockPos(), var26})
                                    || MathUtil3.b_boolean_____(new Object[]{var63.getBlockPos(), var28})
                            )
                    )) {
                        Runnable var10001 = () -> InventoryUtil2.a(new Object[]{(BlockHitResult)var62, 96519249476482L});
                        boolean var10002 = ((String)this.h.getValue()).equals("Silent");
                        Object[] var10005 = new Object[]{null, null, null, var34};
                        var10005[2] = var10002;
                        var10005[1] = var10001;
                        var10005[0] = var41;
                        InventoryUtil.k(var10005);
                        this.l.merge(var44, 1, (java.util.function.BiFunction)(a_, b_) -> Integer.sum((Integer)a_, (Integer)b_));
                        this.r.a_void_____________________(new Object[]{var6});
                        this.g.reroll(new Object[]{var8});
                        return var61;
                    } else {
                        return var61;
                    }
                }
            }
        } else {
            return null;
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof BlockUpdateS2CPacket var5) {
            if (var5.getState() == null || var5.getState().getBlock() == Blocks.RESPAWN_ANCHOR) {
                return;
            }

            this.q.remove(var5.getPos());
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
