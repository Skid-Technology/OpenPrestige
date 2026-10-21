package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
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
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.RaycastUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AnchorExploder extends Module implements RenderContract3 {
    private BoolSetting d;
    private RangeSetting a;
    private BoolSetting c;
    private EnumSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private EnumSetting h;
    private IntSetting i;
    private BoolSetting j;
    private BoolSetting k;
    private BoolSetting l;
    private MultiSetting m;
    private FloatSetting n;
    public static boolean o;
    private RenderHelper27 p = new RenderHelper27(26293358099357L);
    private ArrayList q = new ArrayList();
    private int r = -1;
    private int s = -1;

    public AnchorExploder() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.e.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL94_0 -> (Boolean)this.c.getValue(), 90319707064974L});
        this.j
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL90_0 -> (Boolean)this.g.getValue() && ((String)this.h.getValue()).equals("Swap"), 73196168581295L}
            );
        this.k.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL89_0 -> (Boolean)this.g.getValue(), 73196168581295L});
        this.i.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL91_0 -> (Boolean)this.g.getValue(), 98265949679296L});
        this.n
            .setVisibility_FloatSetting(
                new Object[]{
                    (Predicate<Float>)lpL88_0 -> this.m.isSelected(new Object[]{"Glowstone", 47850572908778L})
                        || this.m.isSelected(new Object[]{"Explode", 47850572908778L}),
                    35046756052036L
                }
            );
        this.f
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL93_0 -> (Boolean)this.c.getValue() && ((String)this.e.getValue()).equals("Swap"), 73196168581295L}
            );
        this.h.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL92_0 -> (Boolean)this.g.getValue(), 90319707064974L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public boolean b(Object[] var1) {
        return Boolean.FALSE.equals(this.l.getValue());
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.getKey(new Object[]{13012393635966L}) == 9998 && b.player.getMainHandStack().getItem().equals(Blocks.GLOWSTONE.asItem())) {
            var1.a_void_____(new Object[0]);
        } else {
            if (ArmorUtil.a(new Object[]{12659099461385L})) {
                var1.a_void_____(new Object[0]);
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
        this.q.clear();
        this.r = -1;
        this.s = -1;
        o = false;
        Prestige.o.f(new Object[]{this, var4});
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.q.clear();
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
            if (!Prestige.o.d_boolean______(new Object[0])) {
                if (!AutoObbyCrystal.R && !AnchorPearlCatch.C) {
                    if (!SafeAnchor.d(new Object[0])) {
                        if (this.p.b(new Object[]{this.a, 89714527855213L})) {
                            this.p.a_void_____________________(new Object[]{93540861878173L});
                            this.a.reroll(new Object[]{57083216809697L});
                            if ((Boolean)this.d.getValue()) {
                                this.q.removeIf(lpL87_0 -> b.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL87_0)).getBlock() != Blocks.RESPAWN_ANCHOR);
                            }

                            if (b.crosshairTarget instanceof BlockHitResult var21) {
                                boolean var23 = WorldUtil2.k(new Object[]{var21.getBlockPos(), Blocks.RESPAWN_ANCHOR, 137239425566158L});
                                if (var23) {
                                    o = true;
                                    if (MathUtil3.a_boolean_______(new Object[]{var21.getBlockPos(), 44262730353436L})
                                        || MathUtil3.b_boolean_____(new Object[]{var21.getBlockPos(), 57098141355368L})) {
                                        return;
                                    }

                                    if ((Boolean)this.c.getValue() && this.d(new Object[]{117171810338500L})) {
                                        return;
                                    }

                                    if ((Boolean)this.g.getValue()) {
                                        this.j(new Object[]{69363607833452L});
                                    }
                                } else {
                                    o = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 85123560351320L;
        long var6 = var2 ^ 56466007075447L;
        long var8 = var2 ^ 120434008667598L;
        long var10 = var2 ^ 110018189354835L;
        long var12 = var2 ^ 39587186614724L;
        long var14 = var2 ^ 58982489950237L;
        long var16 = var2 ^ 71343916077473L;
        long var18 = var2 ^ 127616945565159L;
        long var20 = var2 ^ 116785209433731L;
        long var22 = var2 ^ 7477342050734L;
        long var24 = var2 ^ 39291485316836L;
        long var26 = var2 ^ 26672500495419L;
        long var28 = var2 ^ 75440226304702L;
        long var30 = var2 ^ 2116952030326L;
        long var32 = var2 ^ 114122501858469L;
        long var34 = var2 ^ 136772110525137L;
        long var36 = var2 ^ 103998936193343L;
        long var38 = var2 ^ 101816702374277L;
        long var40 = var2 ^ 90085269546745L;
        if (!this.m.isSelected(new Object[]{"Glowstone", var10}) && !this.m.isSelected(new Object[]{"Explode", var10})) {
            return null;
        } else if (b.player.isUsingItem()) {
            return null;
        } else if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else {
            boolean var43 = this.m.isSelected(new Object[]{"Glowstone", var10}) && (Boolean)this.c.getValue();
            boolean var44 = this.m.isSelected(new Object[]{"Explode", var10}) && (Boolean)this.g.getValue();
            if (!var43 && !var44) {
                return null;
            } else if (SafeAnchor.d(new Object[0])) {
                return null;
            } else if (AnchorPearlCatch.C) {
                return null;
            } else if (b.crosshairTarget instanceof BlockHitResult var45 && WorldUtil2.k(new Object[]{var45.getBlockPos(), Blocks.RESPAWN_ANCHOR, var6})) {
                return null;
            } else {
                RenderBatch var61 = null;
                boolean var62 = false;
                double var47 = Double.MAX_VALUE;
                Object[] var10004 = new Object[]{
                    null, (Predicate<BlockPos>)lpL86_0 -> WorldUtil2.b(new Object[]{((net.minecraft.util.math.BlockPos)lpL86_0), Blocks.RESPAWN_ANCHOR, 91415361137757L}), var28
                };
                var10004[0] = 5;

                for (BlockPos var50 : (Iterable<BlockPos>)(WorldUtil2.a_List___(var10004))) {
                    if (!(PositionUtil.b(new Object[]{var12}).distanceTo(var50.toCenterPos()) > InventoryUtil2.b_float_____(new Object[]{var36}))
                        && (!(Boolean)this.d.getValue() || this.q.contains(var50))) {
                        int var51 = WorldUtil2.a_int______________________________(new Object[]{var50, var30});
                        boolean var52 = var43 && var51 == 0;
                        boolean var53 = var44 && var51 > 0;
                        if (var52 || var53) {
                            Vec3d var54 = RaycastUtil.b(new Object[]{var50, var4});
                            if (var54 == null) {
                                var54 = var50.toCenterPos();
                            }

                            RenderBatch var55 = Prestige.o.a_RenderBatch(new Object[]{var54, var38});
                            var55 = WorldUtil.d_RenderBatch(new Object[]{var55, var54, var22});
                            float var56 = var55.a(new Object[0]) - b.player.getYaw();
                            if (!(var56 > (Float)this.n.getValue())
                                && !(var56 < -(Float)this.n.getValue())
                                && WorldUtil.a_class_239_(new Object[]{var55, var20}) instanceof BlockHitResult var58
                                && var58.getBlockPos().equals(var50)) {
                                double var59 = PositionUtil.b(new Object[]{var12}).distanceTo(var50.toCenterPos());
                                if (var59 < var47) {
                                    var47 = var59;
                                    var61 = var55;
                                    var62 = var52;
                                }
                            }
                        }
                    }
                }

                if (var61 == null) {
                    return null;
                } else if (var61.a_boolean________________________________________(new Object[0])) {
                    return var61;
                } else if (WorldUtil.a_class_239_(new Object[]{var61, var20}) instanceof BlockHitResult var64) {
                    if (!MathUtil3.a_boolean_______(new Object[]{var64.getBlockPos(), var32})
                        && !MathUtil3.b_boolean_____(new Object[]{var64.getBlockPos(), var34})) {
                        if (var62) {
                            String var65 = (String)this.e.getValue();
                            switch (var65) {
                                case "None":
                                    if (!InventoryUtil.l(new Object[]{Items.GLOWSTONE, var18})) {
                                        return null;
                                    }
                                    break;
                                case "Swap":
                                    if ((Boolean)this.f.getValue()) {
                                        this.r = InventoryUtil.a_int_____(new Object[]{var14});
                                    }

                                    if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var40})) {
                                        return null;
                                    }
                            }

                            InventoryUtil2.a(new Object[]{var64, var26});
                        } else {
                            String var66 = (String)this.h.getValue();
                            switch (var66) {
                                case "None":
                                    if ((Integer)this.i.getValue() - 1 != InventoryUtil.a_int_____(new Object[]{var14})) {
                                        return null;
                                    }
                                    break;
                                case "Swap":
                                    if ((Boolean)this.j.getValue()) {
                                        this.s = InventoryUtil.a_int_____(new Object[]{var14});
                                    }

                                    int var10000 = (Integer)this.i.getValue() - 1;
                                    Object[] var10003 = new Object[]{null, var8};
                                    var10003[0] = var10000;
                                    InventoryUtil.a_void________(var10003);
                            }

                            if ((Boolean)this.k.getValue()) {
                                PlayerEntity var67 = CombatUtil.a_class_1657(new Object[]{var16});
                                if (var67 != null && b.player.distanceTo(var67) < 10.0F && var67.hurtTime != 0) {
                                    return null;
                                }
                            }

                            InventoryUtil2.a(new Object[]{var64, var26});
                            MathUtil3.b(new Object[]{var64.getBlockPos(), var24});
                        }

                        return var61;
                    } else {
                        return var61;
                    }
                } else {
                    return null;
                }
            }
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 69590137679535L ^ var2;
        long var4 = var2 ^ 2281245873129L;
        long var6 = var2 ^ 45123135630757L;
        long var8 = var2 ^ 30502524238756L;
        long var10 = var2 ^ 131776886174236L;
        long var12 = var2 ^ 52733179580367L;
        long var14 = var2 ^ 121221870414389L;
        long var16 = var2 ^ 83286199034155L;
        if (this.r != -1) {
            int var10000 = this.r;
            Object[] var10003 = new Object[]{null, var10};
            var10003[0] = var10000;
            InventoryUtil.a_void________(var10003);
            this.r = -1;
            return false;
        } else if (!(b.crosshairTarget instanceof BlockHitResult var19)) {
            return false;
        } else if (!b.player.isSneaking()
            && WorldUtil2.k(new Object[]{var19.getBlockPos(), Blocks.RESPAWN_ANCHOR, var6})
            && (!(Boolean)this.d.getValue() || this.q.contains(var19.getBlockPos()))
            && WorldUtil2.a_int______________________________(new Object[]{var19.getBlockPos(), var8}) == 0) {
            String var23 = (String)this.e.getValue();
            switch (var23) {
                case "None":
                    if (!InventoryUtil.l(new Object[]{Items.GLOWSTONE, var14})) {
                        return false;
                    }
                    break;
                case "Swap":
                    if ((Boolean)this.f.getValue()) {
                        this.r = InventoryUtil.a_int_____(new Object[]{var12});
                    }

                    if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var16})) {
                        return false;
                    }
            }

            Prestige.o.b(new Object[0]);
            InventoryUtil2.a(new Object[]{var19, var4});
            return true;
        } else {
            return false;
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 69590137679535L ^ var2;
        long var4 = var2 ^ 119200410741918L;
        long var6 = var2 ^ 115383570100847L;
        long var8 = var2 ^ 96310041452097L;
        long var10 = var2 ^ 136952639578125L;
        long var12 = var2 ^ 86007341495820L;
        long var14 = var2 ^ 37784897978292L;
        long var16 = var2 ^ 16728056896475L;
        long var18 = var2 ^ 134626546369127L;
        if ((Boolean)this.k.getValue()) {
            PlayerEntity var21 = CombatUtil.a_class_1657(new Object[]{var16});
            if (var21 != null && b.player.distanceTo(var21) < 10.0F && var21.hurtTime != 0) {
                return;
            }
        }

        if (this.s != -1) {
            int var27 = this.s;
            Object[] var28 = new Object[]{null, var14};
            var28[0] = var27;
            InventoryUtil.a_void________(var28);
            this.s = -1;
        } else if (b.crosshairTarget instanceof BlockHitResult var25) {
            if (!b.player.isSneaking()
                && WorldUtil2.k(new Object[]{var25.getBlockPos(), Blocks.RESPAWN_ANCHOR, var10})
                && (!(Boolean)this.d.getValue() || this.q.contains(var25.getBlockPos()))
                && WorldUtil2.a_int______________________________(new Object[]{var25.getBlockPos(), var12}) != 0) {
                String var26 = (String)this.h.getValue();
                switch (var26) {
                    case "None":
                        if ((Integer)this.i.getValue() - 1 != InventoryUtil.a_int_____(new Object[]{var18})) {
                            return;
                        }
                        break;
                    case "Swap":
                        if ((Boolean)this.j.getValue()) {
                            this.s = InventoryUtil.a_int_____(new Object[]{var18});
                        }

                        int var10000 = (Integer)this.i.getValue() - 1;
                        Object[] var10003 = new Object[]{null, var14};
                        var10003[0] = var10000;
                        InventoryUtil.a_void________(var10003);
                }

                Prestige.o.b(new Object[0]);
                InventoryUtil2.a(new Object[]{var25, var8});
                MathUtil3.b(new Object[]{var25.getBlockPos(), var4});
                if ((Boolean)this.l.getValue()) {
                    this.toggle(new Object[]{var6});
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var9) {
            ItemStack var13 = var9.getHand().equals(Hand.MAIN_HAND) ? b.player.getMainHandStack() : b.player.getOffHandStack();
            if (var13.getItem() == Items.RESPAWN_ANCHOR) {
                BlockHitResult var11 = var9.getBlockHitResult();
                BlockPos var12 = var11.getBlockPos().add(var11.getSide().getOffsetX(), var11.getSide().getOffsetY(), var11.getSide().getOffsetZ());
                if ((Boolean)this.d.getValue() && !this.q.contains(var12)) {
                    this.q
                        .add(
                            !WorldUtil2.b(new Object[]{var11.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L})
                                    && !WorldUtil2.b(new Object[]{var11.getBlockPos(), Blocks.FIRE, 91415361137757L})
                                    && !WorldUtil2.b(
                                        new Object[]{var11.getBlockPos(), WorldUtil2.a_class_2248_(new Object[]{12315318725950L}), 91415361137757L}
                                    )
                                ? var12
                                : var11.getBlockPos()
                        );
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof BlockUpdateS2CPacket var5) {
            if (var5.getState() != null && var5.getState().getBlock() != Blocks.RESPAWN_ANCHOR) {
                this.q.remove(var5.getPos());
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
