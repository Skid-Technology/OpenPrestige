package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
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
import dev.zprestige.prestige.world.RaycastUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AnchorPlacer extends Module implements RenderContract3 {
    private EnumSetting a;
    private RangeSetting c;
    private EnumSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private FloatSetting g;
    public ArrayList h = new ArrayList();
    private RenderHelper27 i = new RenderHelper27(26293358099357L);
    private int j = -1;
    public static boolean k;
    private int l = 0;

    public AnchorPlacer() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.g.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL52_0 -> (Boolean)this.f.getValue(), 35046756052036L});
        this.e.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL53_0 -> ((String)this.d.getValue()).equals("Swap"), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public boolean b(Object[] var1) {
        return "Persistent".equals(this.a.getValue());
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.h.clear();
    }

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof BlockUpdateS2CPacket var5 && var5.getState() != null && var5.getState().getBlock() != Blocks.RESPAWN_ANCHOR) {
            this.h.remove(var5.getPos());
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (ArmorUtil.a(new Object[]{12659099461385L})) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        this.j = -1;
        k = false;
        this.l = 0;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        this.h.clear();
        this.j = -1;
        k = false;
        this.l = 0;
        Prestige.o.f(new Object[]{this, var4});
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 109216027809139L ^ var2;
        long var4 = var2 ^ 19005928290183L;
        if (((String)this.a.getValue()).equals("One-Shot")) {
            this.toggle(new Object[]{var4});
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        k = b.player != null && b.player.age <= this.l;
        if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
            if (!Prestige.o.d_boolean______(new Object[0])) {
                if (!AutoObbyCrystal.R && !AnchorPearlCatch.C) {
                    if (!SafeAnchor.d(new Object[0])) {
                        if (this.i.b(new Object[]{this.c, 89714527855213L})) {
                            if (this.j != -1) {
                                this.l = b.player.age + 2;
                                k = true;
                                int var10000 = this.j;
                                Object[] var10003 = new Object[]{null, 37392877673079L};
                                var10003[0] = var10000;
                                InventoryUtil.a_void________(var10003);
                                this.j = -1;
                                this.j(new Object[]{29353517675864L});
                            } else if (!(Boolean)this.f.getValue()
                                || b.crosshairTarget instanceof BlockHitResult var29
                                    && (
                                        WorldUtil2.d(new Object[]{var29, 85348799407646L})
                                                && !WorldUtil2.g(
                                                    new Object[]{WorldUtil2.a_class_2338______(new Object[]{var29, 110292667014705L}), 87119771691358L}
                                                )
                                            || WorldUtil2.b(new Object[]{var29.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L})
                                    )) {
                                if (!(b.crosshairTarget instanceof BlockHitResult var32)) {
                                    this.j(new Object[]{29353517675864L});
                                } else if (!MathUtil3.a_boolean_______(new Object[]{var32.getBlockPos(), 44262730353436L})
                                    && !MathUtil3.b_boolean_____(new Object[]{var32.getBlockPos(), 57098141355368L})) {
                                    boolean var34 = WorldUtil2.b(new Object[]{var32.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L})
                                        && WorldUtil2.a_int______________________________(new Object[]{var32.getBlockPos(), 86201794204623L}) == 0;
                                    boolean var31 = WorldUtil2.b(new Object[]{var32.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L})
                                        && WorldUtil2.a_int______________________________(new Object[]{var32.getBlockPos(), 86201794204623L}) > 0;
                                    if (var31) {
                                        this.l = b.player.age + 2;
                                        k = true;
                                        this.l(new Object[]{var32, 25123173776940L});
                                    } else if (!WorldUtil2.d(new Object[]{var32, 85348799407646L}) && !var34) {
                                        this.j(new Object[]{29353517675864L});
                                    } else {
                                        this.l = b.player.age + 2;
                                        k = true;
                                        Object[] var10005 = new Object[]{null, null, 7206424965116L};
                                        var10005[1] = var34;
                                        var10005[0] = var32;
                                        this.m(var10005);
                                    }
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
        long var6 = var2 ^ 29247161167908L;
        long var8 = var2 ^ 48125314331016L;
        long var10 = var2 ^ 31536927622116L;
        long var12 = var2 ^ 136752692109656L;
        long var14 = var2 ^ 81647464664376L;
        long var16 = var2 ^ 116785209433731L;
        long var18 = var2 ^ 7477342050734L;
        long var20 = var2 ^ 784510322407L;
        long var22 = var2 ^ 2290821892519L;
        long var24 = var2 ^ 75440226304702L;
        long var26 = var2 ^ 33065978990036L;
        long var28 = var2 ^ 114122501858469L;
        long var30 = var2 ^ 136772110525137L;
        long var32 = var2 ^ 101816702374277L;
        if (!(Boolean)this.f.getValue()) {
            return null;
        } else if (b.player.isUsingItem()) {
            return null;
        } else if (!this.i.b(new Object[]{this.c, var26})) {
            return null;
        } else if (Prestige.o.d_boolean______(new Object[0])) {
            return null;
        } else if (SafeAnchor.d(new Object[0])) {
            return null;
        } else if (AnchorPearlCatch.C) {
            return null;
        } else if (!(
            b.crosshairTarget instanceof BlockHitResult var35
                && (
                    WorldUtil2.d(new Object[]{var35, var22}) && !WorldUtil2.g(new Object[]{WorldUtil2.a_class_2338______(new Object[]{var35, var8}), var20})
                        || WorldUtil2.b(new Object[]{var35.getBlockPos(), Blocks.RESPAWN_ANCHOR, var10})
                )
        )) {
            RenderBatch var48 = null;
            double var49 = Double.MAX_VALUE;
            Object[] var10004 = new Object[]{null, (Predicate<BlockPos>)lpL51_0 -> WorldUtil2.e(new Object[]{((net.minecraft.util.math.BlockPos)lpL51_0), 100601689205754L}), var24};
            var10004[0] = 5;

            for (BlockPos var39 : (Iterable<BlockPos>)(WorldUtil2.a_List___(var10004))) {
                if (!WorldUtil2.g(new Object[]{var39.up(), var20})) {
                    Vec3d var40 = RaycastUtil.b(new Object[]{var39, var4});
                    if (var40 != null && !(b.player.getEyePos().distanceTo(var40) > 4.4)) {
                        RenderBatch var41 = Prestige.o.a_RenderBatch(new Object[]{var40, var32});
                        var41 = WorldUtil.d_RenderBatch(new Object[]{var41, var40, var18});
                        float var42 = var41.a(new Object[0]) - b.player.getYaw();
                        float var43 = var41.b(new Object[0]) - b.player.getPitch();
                        if (!(Math.abs(var42) > (Float)this.g.getValue())
                            && WorldUtil.a_class_239_(new Object[]{var41, var16}) instanceof BlockHitResult var45
                            && (var45.getBlockPos().equals(var39) || var45.getBlockPos().up().equals(var39))) {
                            double var46 = Math.sqrt(var42 * var42 + var43 * var43);
                            if (var46 < var49) {
                                var49 = var46;
                                var48 = var41;
                            }
                        }
                    }
                }
            }

            if (var48 == null) {
                return null;
            } else if (var48.a_boolean________________________________________(new Object[0])) {
                return var48;
            } else if (!(WorldUtil.a_class_239_(new Object[]{var48, var16}) instanceof BlockHitResult var51)) {
                return var48;
            } else if (!MathUtil3.a_boolean_______(new Object[]{var51.getBlockPos(), var28})
                && !MathUtil3.b_boolean_____(new Object[]{var51.getBlockPos(), var30})) {
                this.k(new Object[]{var51, var14});
                this.i.a_void_____________________(new Object[]{var6});
                this.c.reroll(new Object[]{var12});
                return var48;
            } else {
                return var48;
            }
        } else {
            return null;
        }
    }

    private void k(Object[] var1) {
        BlockHitResult var2 = (BlockHitResult)var1[0];
        long var3 = (Long)var1[1];
        var3 = 109216027809139L ^ var3;
        long var5 = var3 ^ 123442192812510L;
        long var7 = var3 ^ 47294049833452L;
        long var9 = var3 ^ 105629111998478L;
        long var11 = var3 ^ 45055188804669L;
        long var13 = var3 ^ 59064892791727L;
        boolean var16 = WorldUtil2.b(new Object[]{var2.getBlockPos(), Blocks.RESPAWN_ANCHOR, var13})
            && WorldUtil2.a_int______________________________(new Object[]{var2.getBlockPos(), var11}) == 0;
        boolean var17 = WorldUtil2.b(new Object[]{var2.getBlockPos(), Blocks.RESPAWN_ANCHOR, var13})
            && WorldUtil2.a_int______________________________(new Object[]{var2.getBlockPos(), var11}) > 0;
        if (var17) {
            this.l(new Object[]{var2, var5});
        } else if (WorldUtil2.d(new Object[]{var2, var7}) || var16) {
            Object[] var10005 = new Object[]{null, null, var9};
            var10005[1] = var16;
            var10005[0] = var2;
            this.m(var10005);
        }
    }

    private void l(Object[] var1) {
        BlockHitResult var4 = (BlockHitResult)var1[0];
        long var2 = (Long)var1[1];
        var2 = 109216027809139L ^ var2;
        long var5 = var2 ^ 37679828275421L;
        long var7 = var2 ^ 86866930506313L;
        long var9 = var2 ^ 11880186185971L;
        long var11 = var2 ^ 96274147930408L;
        long var13 = var2 ^ 17196083432699L;
        long var15 = var2 ^ 122319639797255L;
        long var17 = var2 ^ 85856574007553L;
        Prestige.o.b(new Object[0]);
        String var20 = (String)this.d.getValue();
        Event.b();
        switch (var20) {
            case "Swap":
                Integer var22 = InventoryUtil.a_Integer(new Object[]{Items.TOTEM_OF_UNDYING, var7});
                if (var22 != null) {
                    if ((Boolean)this.e.getValue()) {
                        this.j = InventoryUtil.a_int_____(new Object[]{var13});
                    }

                    int var10000 = var22;
                    Object[] var10003 = new Object[]{null, var11};
                    var10003[0] = var10000;
                    InventoryUtil.a_void________(var10003);
                } else if (InventoryUtil.l(new Object[]{Items.GLOWSTONE, var17})) {
                    Integer var23 = InventoryUtil.b_Integer(new Object[]{Items.GLOWSTONE, var9});
                    if (var23 == null) {
                        return;
                    }

                    if ((Boolean)this.e.getValue()) {
                        this.j = InventoryUtil.a_int_____(new Object[]{var13});
                    }

                    int var25 = var23;
                    Object[] var26 = new Object[]{null, var11};
                    var26[0] = var25;
                    InventoryUtil.a_void________(var26);
                }

                InventoryUtil2.a(new Object[]{var4, var5});
                if (this.j == -1) {
                    this.j(new Object[]{var15});
                }
                break;
            case "None":
                if (!InventoryUtil.l(new Object[]{Items.GLOWSTONE, var17})) {
                    InventoryUtil2.a(new Object[]{var4, var5});
                    this.j(new Object[]{var15});
                }
        }
    }

    private void m(Object[] var1) {
        BlockHitResult var4 = (BlockHitResult)var1[0];
        boolean var5 = (Boolean)var1[1];
        long var2 = (Long)var1[2];
        var2 = 109216027809139L ^ var2;
        long var6 = var2 ^ 55051103551757L;
        long var8 = var2 ^ 53644597978386L;
        long var10 = var2 ^ 60462314837714L;
        long var12 = var2 ^ 121952725260209L;
        long var14 = var2 ^ 94779428877422L;
        long var16 = var2 ^ 35147190541611L;
        long var18 = var2 ^ 140098931953623L;
        long var20 = var2 ^ 103640163891409L;
        long var22 = var2 ^ 136052315501519L;
        Event.b();
        String var25 = (String)this.d.getValue();
        switch (var25) {
            case "None":
                if (var5) {
                    if (!InventoryUtil.l(new Object[]{Items.GLOWSTONE, var20})) {
                        return;
                    }
                } else if (!InventoryUtil.l(new Object[]{Items.RESPAWN_ANCHOR, var20})) {
                    return;
                }
                break;
            case "Swap":
                if ((Boolean)this.e.getValue()) {
                    this.j = InventoryUtil.a_int_____(new Object[]{var16});
                }

                if (var5) {
                    if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var22})) {
                        this.j(new Object[]{var18});
                        return;
                    }
                } else if (!InventoryUtil.i(new Object[]{Blocks.RESPAWN_ANCHOR, var22})) {
                    this.j(new Object[]{var18});
                    return;
                }
        }

        BlockPos var28 = !WorldUtil2.b(new Object[]{var4.getBlockPos(), Blocks.FIRE, var10})
                && !WorldUtil2.b(new Object[]{var4.getBlockPos(), WorldUtil2.a_class_2248_(new Object[]{var12}), var10})
            ? var4.getBlockPos().add(var4.getSide().getOffsetX(), var4.getSide().getOffsetY(), var4.getSide().getOffsetZ())
            : var4.getBlockPos();
        if (!var5) {
            this.h.add(var28);
        }

        Prestige.o.b(new Object[0]);
        InventoryUtil2.a(new Object[]{var4, var6});
        this.i.a_void_____________________(new Object[]{var8});
        this.c.reroll(new Object[]{var14});
        if (this.j == -1) {
            this.j(new Object[]{var18});
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
