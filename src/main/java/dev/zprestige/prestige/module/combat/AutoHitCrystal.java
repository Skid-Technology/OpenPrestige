package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.inventory.InventoryUtil3;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class AutoHitCrystal extends Module {
    public static boolean a = false;
    private BoolSetting d;
    private BoolSetting c;
    private BoolSetting e;
    private RangeSetting f;
    private BoolSetting g;
    private EnumSetting h;
    private RangeSetting i;
    private BoolSetting j;
    private RenderHelper27 k = new RenderHelper27(26293358099357L);
    private int l = 0;

    public AutoHitCrystal() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.i
            .setVisibility_RangeSetting(
                new Object[]{
                    (Predicate<Float>)lpL231_0 -> ((String)this.h.getValue()).equals("Double Tap") || ((String)this.h.getValue()).equals("Infinite"),
                    37043306203287L
                }
            );
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public boolean b(Object[] var1) {
        return "Infinite".equals(this.h.getValue());
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 22594282158762L;
        long var6 = var2 ^ 131900892945784L;
        long var8 = var2 ^ 127375248225769L;
        if (this.getKey(new Object[]{var6}) == 9998 && InventoryUtil2.e(new Object[]{var8})) {
            this.toggle(new Object[]{var4});
        } else {
            if (!((String)this.h.getValue()).equals("None")) {
                a = true;
            }
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        a = false;
        this.l = 0;
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.getKey(new Object[]{13012393635966L}) == 9998
            && (b.player.getMainHandStack().getItem().equals(Items.END_CRYSTAL) || b.player.getMainHandStack().getItem().equals(Blocks.OBSIDIAN.asItem()))) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(FloatEvent2 var1) {
        if (b.currentScreen == null
            && b.isWindowFocused()
            && this.k.b(new Object[]{this.f, 89714527855213L})
            && (!(Boolean)this.d.getValue() || b.player.isOnGround())
            && !b.player.isUsingItem()) {
            if (this.getKey(new Object[]{13012393635966L}) == 9998 && InventoryUtil2.e(new Object[]{17574514088175L})) {
                a = false;
            } else if (!InventoryUtil.l(new Object[]{Items.NETHERITE_SPEAR, 65539821781598L})) {
                if (!Prestige.o.d_boolean______(new Object[0])) {
                    PlayerEntity var43 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
                    if (var43 != null && var43.distanceTo(b.player) > 10.0F) {
                        var43 = null;
                    }

                    if (this.l == 0 && (Boolean)this.c.getValue() && !InventoryUtil.a(new Object[]{b.player.getMainHandStack(), 127680976181311L})) {
                        this.toggle(new Object[]{115103427876780L});
                    } else {
                        HitResult var44 = b.crosshairTarget;
                        if (var44 != null) {
                            if (!((String)this.h.getValue()).equals("None")) {
                                a = true;
                            }

                            switch (this.l) {
                                case 0:
                                    if (var44 instanceof EntityHitResult var47 && var47.getEntity() instanceof EndCrystalEntity) {
                                        if (((String)this.h.getValue()).equals("None")) {
                                            a = false;
                                            return;
                                        }

                                        if ((Boolean)this.e.getValue() && InventoryUtil3.a(new Object[]{var47.getEntity().getBlockPos(), 64853648109405L})) {
                                            a = false;
                                            return;
                                        }

                                        if ((Boolean)this.g.getValue() && var43 != null && var43.hurtTime != 0) {
                                            return;
                                        }

                                        InventoryUtil2.a_void_________(new Object[]{var47.getEntity(), 31382773185830L});
                                        this.f.reroll(new Object[]{57083216809697L});
                                        this.k.a_void_____________________(new Object[]{93540861878173L});
                                        a = false;
                                        return;
                                    }

                                    if (!(var44 instanceof BlockHitResult) || !WorldUtil2.c(new Object[]{var44, 112619528716805L})) {
                                        a = false;
                                        return;
                                    }

                                    if (b.world.getBlockState(((BlockHitResult)var44).getBlockPos()).getBlock() == Blocks.OBSIDIAN) {
                                        this.l = 2;
                                        return;
                                    }

                                    if (!InventoryUtil.i(new Object[]{Blocks.OBSIDIAN, 33415164545344L})) {
                                        a = false;
                                        return;
                                    }

                                    this.k(new Object[]{94042533868345L});
                                    break;
                                case 1:
                                    if (!(var44 instanceof BlockHitResult)) {
                                        a = false;
                                        return;
                                    }

                                    if (!WorldUtil2.c(new Object[]{var44, 112619528716805L})) {
                                        a = false;
                                        return;
                                    }

                                    BlockPos var46 = ((BlockHitResult)var44).getBlockPos().offset(((BlockHitResult)var44).getSide());
                                    if (WorldUtil2.g(new Object[]{var46, 87119771691358L})) {
                                        a = false;
                                        return;
                                    }

                                    if (b.world.getBlockState(((BlockHitResult)var44).getBlockPos()).getBlock() == Blocks.OBSIDIAN) {
                                        a = false;
                                        this.k(new Object[]{94042533868345L});
                                        return;
                                    }

                                    if (WorldUtil2.g(new Object[]{((BlockHitResult)var44).getBlockPos(), 87119771691358L})) {
                                        a = false;
                                        return;
                                    }

                                    Block var48 = Blocks.OBSIDIAN;
                                    Runnable var49 = () -> InventoryUtil2.a(new Object[]{(BlockHitResult)var44, 96519249476482L});
                                    Object[] var50 = new Object[]{null, null, null, 135286962703597L};
                                    var50[2] = false;
                                    var50[1] = var49;
                                    var50[0] = var48;
                                    if (!InventoryUtil.k(var50)) {
                                        return;
                                    }

                                    this.k(new Object[]{94042533868345L});
                                    break;
                                case 2:
                                    if (!InventoryUtil.h(new Object[]{Items.END_CRYSTAL, 55391972360703L})) {
                                        return;
                                    }

                                    this.k(new Object[]{94042533868345L});
                                    break;
                                case 3:
                                case 5:
                                    if (((String)this.h.getValue()).equals("None")) {
                                        this.toggle(new Object[]{115103427876780L});
                                        return;
                                    }

                                    if (this.l == 5 && !this.k.b(new Object[]{this.i, 89714527855213L})) {
                                        return;
                                    }

                                    if (!(var44 instanceof BlockHitResult)) {
                                        return;
                                    }

                                    BlockPos var45 = ((BlockHitResult)var44).getBlockPos();
                                    if (b.world.getBlockState(var45).getBlock() != Blocks.OBSIDIAN) {
                                        return;
                                    }

                                    if (WorldUtil2.g(new Object[]{var45, 87119771691358L})) {
                                        return;
                                    }

                                    Item var10000 = Items.END_CRYSTAL;
                                    Runnable var10001 = () -> InventoryUtil2.a(new Object[]{(BlockHitResult)var44, 96519249476482L});
                                    Object[] var10005 = new Object[]{null, null, null, 114857082072684L};
                                    var10005[2] = false;
                                    var10005[1] = var10001;
                                    var10005[0] = var10000;
                                    if (!InventoryUtil.j(var10005)) {
                                        return;
                                    }

                                    this.k(new Object[]{94042533868345L});
                                    break;
                                case 4:
                                    if ((Boolean)this.g.getValue() && var43 != null && var43.hurtTime != 0) {
                                        return;
                                    }

                                    if (b.crosshairTarget.getType() == Type.ENTITY && ((EntityHitResult)var44).getEntity() instanceof EndCrystalEntity) {
                                        if ((Boolean)this.e.getValue()
                                            && InventoryUtil3.a(new Object[]{((EntityHitResult)var44).getEntity().getBlockPos(), 64853648109405L})) {
                                            return;
                                        }

                                        InventoryUtil2.a_void_________(new Object[]{((EntityHitResult)var44).getEntity(), 31382773185830L});
                                        if (!((String)this.h.getValue()).equals("Double Tap") && !((String)this.h.getValue()).equals("Infinite")) {
                                            this.toggle(new Object[]{115103427876780L});
                                        } else {
                                            this.k(new Object[]{94042533868345L});
                                        }

                                        return;
                                    }

                                    if ((Boolean)this.j.getValue()) {
                                        this.j(new Object[]{var1, 125788999008501L});
                                    }
                                    break;
                                case 6:
                                    if (!this.k.b(new Object[]{this.i, 89714527855213L})) {
                                        return;
                                    }

                                    if ((Boolean)this.g.getValue() && var43 != null && var43.hurtTime != 0) {
                                        return;
                                    }

                                    if (b.crosshairTarget.getType() == Type.ENTITY && ((EntityHitResult)var44).getEntity() instanceof EndCrystalEntity) {
                                        if ((Boolean)this.e.getValue()
                                            && InventoryUtil3.a(new Object[]{((EntityHitResult)var44).getEntity().getBlockPos(), 64853648109405L})) {
                                            return;
                                        }

                                        InventoryUtil2.a_void_________(new Object[]{((EntityHitResult)var44).getEntity(), 31382773185830L});
                                        if (((String)this.h.getValue()).equals("Infinite")) {
                                            this.l = 5;
                                            this.f.reroll(new Object[]{57083216809697L});
                                            this.i.reroll(new Object[]{57083216809697L});
                                            this.k.a_void_____________________(new Object[]{93540861878173L});
                                        } else {
                                            this.toggle(new Object[]{115103427876780L});
                                        }

                                        return;
                                    }

                                    if ((Boolean)this.j.getValue()) {
                                        this.j(new Object[]{var1, 125788999008501L});
                                    }
                            }
                        }
                    }
                }
            }
        }
    }

    private void j(Object[] var1) {
        FloatEvent2 var4 = (FloatEvent2)var1[0];
        long var2 = (Long)var1[1];
        var2 = 64315854250383L ^ var2;
        long var5 = var2 ^ 35968770995926L;
        long var7 = var2 ^ 92994165480540L;
        long var9 = var2 ^ 31929171132135L;
        long var11 = var2 ^ 129201646062614L;
        long var13 = var2 ^ 136285819989915L;
        long var15 = var2 ^ 84492936578115L;
        long var17 = var2 ^ 32567678734915L;
        long var19 = var2 ^ 126318504529447L;
        Event.b();
        BlockPos var22 = null;
        if (b.crosshairTarget.getType() == Type.BLOCK) {
            var22 = ((BlockHitResult)b.crosshairTarget).getBlockPos();
        }

        if (var22 != null && b.world.getBlockState(var22).getBlock() == Blocks.OBSIDIAN) {
            for (Entity var24 : b.world.getEntities()) {
                if (var24 instanceof EndCrystalEntity) {
                    Vec3d var25 = PositionUtil.a(new Object[]{var24, var11});
                    if (var25.distanceTo(var22.up().toCenterPos()) < 1.0) {
                        Box var26 = var24.getBoundingBox();
                        Vec3d var27 = b.player.getEyePos();
                        double var28 = Math.max(var26.minX - var27.x, Math.max(0.0, var27.x - var26.maxX));
                        double var30 = Math.max(var26.minY - var27.y, Math.max(0.0, var27.y - var26.maxY));
                        double var32 = Math.max(var26.minZ - var27.z, Math.max(0.0, var27.z - var26.maxZ));
                        double var34 = Math.sqrt(var28 * var28 + var30 * var30 + var32 * var32);
                        if (var34 <= 2.7F && (!(Boolean)this.e.getValue() || !InventoryUtil3.a(new Object[]{var24.getBlockPos(), var19}))) {
                            float var10000 = (float)var25.x;
                            float var10001 = (float)(var25.y + 0.45F);
                            float var10002 = (float)var25.z;
                            Object[] var10005 = new Object[]{null, null, null, var15};
                            var10005[2] = var10002;
                            var10005[1] = var10001;
                            var10005[0] = var10000;
                            RenderBatch var36 = WorldUtil.b_RenderBatch_____________(var10005);
                            var4.c(new Object[]{var36.b(new Object[0])});
                            InventoryUtil2.a_void_________(new Object[]{var24, var7});
                            if (((String)this.h.getValue()).equals("Infinite")) {
                                this.l = 5;
                                this.f.reroll(new Object[]{var13});
                                this.k.a_void_____________________(new Object[]{var9});
                                return;
                            } else {
                                if (this.l != 6 && ((String)this.h.getValue()).equals("Double Tap")) {
                                    this.k(new Object[]{var17});
                                } else {
                                    this.toggle(new Object[]{var5});
                                }

                                return;
                            }
                        }
                    }
                }
            }
        } else {
            this.toggle(new Object[]{var5});
        }
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 64315854250383L ^ var2;
        long var4 = var2 ^ 64778402680107L;
        long var6 = var2 ^ 101240711814231L;
        this.i.reroll(new Object[]{var6});
        this.f.reroll(new Object[]{var6});
        this.l++;
        this.k.a_void_____________________(new Object[]{var4});
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
