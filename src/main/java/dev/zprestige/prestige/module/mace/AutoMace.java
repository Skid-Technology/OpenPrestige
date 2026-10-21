package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.EntityEvent2;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;

public class AutoMace extends Module implements RenderContract3 {
    private FloatSetting a;
    private FloatSetting c;
    private BoolSetting d;
    private EnumSetting e;
    private KeybindSetting f;
    private FloatSetting g;
    private BoolSetting h;
    private FloatSetting i;
    private IntSetting j;
    private BoolSetting k;
    private BoolSetting l;
    private BoolSetting m;
    private FloatSetting n;
    private BoolSetting o;
    private BoolSetting p;
    private IntSetting q;
    private BoolSetting r;
    private FloatSetting s;
    private RenderHelper27 t = new RenderHelper27(26293358099357L);
    private RenderHelper27 u = new RenderHelper27(26293358099357L);
    private RenderHelper27 v = new RenderHelper27(26293358099357L);
    private RenderHelper27 w = new RenderHelper27(26293358099357L);
    private boolean x;
    private boolean y;
    private int z = -1;
    private boolean A;
    private boolean B;
    private PlayerEntity C;
    private RenderBatch D;
    private long E;
    private int F = -1;

    public AutoMace() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.g
            .setVisibility_FloatSetting(
                new Object[]{(Predicate<Float>)lpL239_0 -> (Boolean)this.d.getValue() && ((String)this.e.getValue()).equals("Chance"), 35046756052036L}
            );
        this.i.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL237_0 -> (Boolean)this.d.getValue(), 35046756052036L});
        this.e.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL241_0 -> (Boolean)this.d.getValue(), 90319707064974L});
        this.j.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL236_0 -> (Boolean)this.d.getValue(), 98265949679296L});
        this.m.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL235_0 -> (Boolean)this.l.getValue(), 73196168581295L});
        this.n
            .setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL234_0 -> (Boolean)this.l.getValue() && (Boolean)this.m.getValue(), 35046756052036L});
        this.s.setVisibility_FloatSetting(new Object[]{(Predicate<Float>)lpL232_0 -> (Boolean)this.r.getValue(), 35046756052036L});
        this.h
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL238_0 -> (Boolean)this.d.getValue() && ((String)this.e.getValue()).equals("Chance"), 73196168581295L}
            );
        this.f
            .setVisibility_KeybindSetting(
                new Object[]{(Predicate<Integer>)lpL240_0 -> (Boolean)this.d.getValue() && ((String)this.e.getValue()).equals("Hold Key"), 112715924896781L}
            );
        this.q.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL233_0 -> (Boolean)this.p.getValue(), 98265949679296L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140428399156587L;
        long var6 = var2 ^ 56416153325822L;
        long var8 = var2 ^ 29247161167908L;
        long var10 = var2 ^ 120434008667598L;
        long var12 = var2 ^ 83290274247234L;
        long var14 = var2 ^ 81856115773718L;
        long var16 = var2 ^ 38214632243944L;
        long var18 = var2 ^ 58982489950237L;
        long var20 = var2 ^ 96244338737809L;
        long var22 = var2 ^ 114164831697321L;
        long var24 = var2 ^ 126398332382536L;
        long var26 = var2 ^ 30935657995700L;
        long var28 = var2 ^ 91260814807711L;
        long var30 = var2 ^ 139097133863433L;
        long var32 = var2 ^ 73223711775706L;
        long var34 = var2 ^ 85547089512629L;
        long var36 = var2 ^ 17011823071369L;
        long var38 = var2 ^ 48633155378565L;
        long var40 = var2 ^ 108664097687836L;
        long var42 = var2 ^ 6262656534595L;
        Event.b();
        this.B = false;
        if (this.z != -1 && !this.A) {
            RenderHelper27 var10000 = this.w;
            Object[] var10004 = new Object[]{null, var34};
            var10004[0] = 2000.0F;
            if (var10000.a(var10004)) {
                this.m(new Object[0]);
            }
        }

        if (this.z != -1 && this.A && !this.x && this.C == null) {
            RenderHelper27 var60 = this.v;
            float var10001 = ((Integer)this.q.getValue()).intValue();
            Object[] var71 = new Object[]{null, var34};
            var71[0] = var10001;
            if (var60.a(var71)) {
                this.l(new Object[]{var36});
            }
        }

        if (this.C != null && !(Boolean)this.d.getValue()) {
            int var45 = this.p.getValue() && this.z != -1 ? this.z : this.F;
            this.j(new Object[]{var32});
            this.m(new Object[0]);
            if (var45 >= 0 && InventoryUtil.a_int_____(new Object[]{var18}) != var45) {
                Object[] var10003 = new Object[]{null, var10};
                var10003[0] = var45;
                InventoryUtil.a_void________(var10003);
            }
        }

        if (this.C != null) {
            return this.c(new Object[]{var24});
        } else if (this.f(new Object[]{var40})) {
            return null;
        } else {
            int var51 = this.e_int___(new Object[]{var20});
            if (var51 == -1) {
                this.x = false;
                return null;
            } else if (b.player.fallDistance <= ((Float)this.c.getValue()).floatValue()) {
                return null;
            } else if ((Boolean)this.d.getValue() && this.x) {
                int var52 = this.d_int_____(new Object[]{var42});
                if (var52 == -1) {
                    this.x = false;
                    return null;
                } else {
                    RenderBatch var57 = null;
                    PlayerEntity var53;
                    if (b.crosshairTarget instanceof EntityHitResult var58 && var58.getEntity() instanceof PlayerEntity) {
                        var53 = (PlayerEntity)var58.getEntity();
                    } else {
                        float var66 = (Float)this.n.getValue();
                        Object[] var70 = new Object[]{null, var38};
                        var70[0] = var66;
                        var53 = CombatUtil.b(var70);
                        if (var53 == null || b.player.distanceTo(var53) > 3.0F || !b.player.canSee(var53) || WorldUtil2.a(new Object[]{var53, var22})) {
                            this.x = false;
                            return null;
                        }

                        var57 = WorldUtil.a_RenderBatch_(new Object[]{var53.getEyePos(), var14});
                        if (var57.a(new Object[0]) - b.player.getYaw() > (Float)this.n.getValue()
                            || var57.a(new Object[0]) - b.player.getYaw() < -(Float)this.n.getValue()) {
                            this.x = false;
                            return null;
                        }
                    }

                    if (!this.e(new Object[]{var12})) {
                        this.x = false;
                        return null;
                    } else {
                        this.x = false;
                        Object[] var77 = new Object[]{null, null, null, null, var4};
                        var77[3] = var51;
                        var77[2] = var52;
                        var77[1] = var57;
                        var77[0] = var53;
                        return this.b_RenderBatch______(var77);
                    }
                }
            } else {
                if (b.crosshairTarget instanceof EntityHitResult var46 && var46.getEntity() instanceof PlayerEntity var47) {
                    if ((Boolean)this.l.getValue()) {
                        RenderHelper27 var64 = this.t;
                        Object[] var74 = new Object[]{null, var34};
                        var74[0] = 800.0F;
                        if (var64.a(var74)) {
                            if (CombatUtil.b_boolean(new Object[]{var47, var30}) && !ShieldDrain.d(new Object[0]) && (Boolean)this.d.getValue()) {
                                var64 = this.u;
                                var74 = new Object[]{null, var34};
                                var74[0] = 800.0F;
                                if (var64.a(var74) && this.d(new Object[]{var26}) && this.e(new Object[]{var12})) {
                                    int var56 = this.d_int_____(new Object[]{var42});
                                    if (var56 != -1) {
                                        Object[] var76 = new Object[]{null, null, null, null, var4};
                                        var76[3] = var51;
                                        var76[2] = var56;
                                        var76[1] = null;
                                        var76[0] = var47;
                                        return this.b_RenderBatch______(var76);
                                    }
                                }
                            }

                            if (PositionUtil.a_boolean________________________________________________(new Object[]{var16})) {
                                return null;
                            }

                            this.y = true;
                            if (InventoryUtil.a_int_____(new Object[]{var18}) != var51) {
                                this.k(new Object[]{var6});
                                Object[] var69 = new Object[]{null, var10};
                                var69[0] = var51;
                                InventoryUtil.a_void________(var69);
                            }

                            InventoryUtil2.a_void_________(new Object[]{var47, var28});
                            this.y = false;
                            this.t.a_void_____________________(new Object[]{var8});
                            if ((Boolean)this.p.getValue() && this.z != -1) {
                                this.v.a_void_____________________(new Object[]{var8});
                                this.A = true;
                            }
                        }
                    }
                } else {
                    if ((Boolean)this.l.getValue() && (Boolean)this.m.getValue()) {
                        RenderHelper27 var61 = this.t;
                        Object[] var72 = new Object[]{null, var34};
                        var72[0] = 800.0F;
                        if (var61.a(var72)) {
                            float var62 = (Float)this.n.getValue();
                            Object[] var67 = new Object[]{null, var38};
                            var67[0] = var62;
                            PlayerEntity var55 = CombatUtil.b(var67);
                            if (var55 != null && !(b.player.distanceTo(var55) > 3.0F) && b.player.canSee(var55) && !WorldUtil2.a(new Object[]{var55, var22})) {
                                RenderBatch var49 = WorldUtil.a_RenderBatch_(new Object[]{var55.getEyePos(), var14});
                                if (!(var49.a(new Object[0]) - b.player.getYaw() > (Float)this.n.getValue())
                                    && !(var49.a(new Object[0]) - b.player.getYaw() < -(Float)this.n.getValue())) {
                                    if (!this.x
                                        && CombatUtil.b_boolean(new Object[]{var55, var30})
                                        && !ShieldDrain.d(new Object[0])
                                        && (Boolean)this.d.getValue()) {
                                        var61 = this.u;
                                        var72 = new Object[]{null, var34};
                                        var72[0] = 800.0F;
                                        if (var61.a(var72) && this.d(new Object[]{var26}) && this.e(new Object[]{var12})) {
                                            int var50 = this.d_int_____(new Object[]{var42});
                                            if (var50 == -1) {
                                                this.x = false;
                                                return null;
                                            }

                                            Object[] var10007 = new Object[]{null, null, null, null, var4};
                                            var10007[3] = var51;
                                            var10007[2] = var50;
                                            var10007[1] = var49;
                                            var10007[0] = var55;
                                            return this.b_RenderBatch______(var10007);
                                        }
                                    }

                                    if (PositionUtil.a_boolean________________________________________________(new Object[]{var16})) {
                                        return null;
                                    }

                                    if (InventoryUtil.a_int_____(new Object[]{var18}) != var51) {
                                        this.k(new Object[]{var6});
                                        var67 = new Object[]{null, var10};
                                        var67[0] = var51;
                                        InventoryUtil.a_void________(var67);
                                    }

                                    InventoryUtil2.a_void_________(new Object[]{var55, var28});
                                    this.t.a_void_____________________(new Object[]{var8});
                                    if ((Boolean)this.p.getValue() && this.z != -1) {
                                        this.v.a_void_____________________(new Object[]{var8});
                                        this.A = true;
                                    }

                                    return var49;
                                }

                                return null;
                            }

                            return null;
                        }
                    }

                    this.x = false;
                }

                return null;
            }
        }
    }

    private RenderBatch b_RenderBatch______(Object[] var1) {
        PlayerEntity var3 = (PlayerEntity)var1[0];
        RenderBatch var5 = (RenderBatch)var1[1];
        int var4 = (Integer)var1[2];
        int var2 = (Integer)var1[3];
        long var6 = (Long)var1[4];
        var6 = 101230168494241L ^ var6;
        long var8 = var6 ^ 123871765208917L;
        long var10 = var6 ^ 18580500749620L;
        long var12 = var6 ^ 62881916009966L;
        long var14 = var6 ^ 85907108329476L;
        long var16 = var6 ^ 24247686546903L;
        if (InventoryUtil.a_int_____(new Object[]{var16}) != var4) {
            this.k(new Object[]{var10});
            Object[] var10003 = new Object[]{null, var14};
            var10003[0] = var4;
            InventoryUtil.a_void________(var10003);
        }

        int var19 = (Integer)this.j.getValue();
        if (var19 <= 0) {
            this.y = true;
            InventoryUtil2.a_void_________(new Object[]{var3, var8});
            this.y = false;
            this.u.a_void_____________________(new Object[]{var12});
            return var5;
        } else {
            this.C = var3;
            this.D = var5;
            this.F = var2;
            this.E = System.currentTimeMillis();
            return var5;
        }
    }

    private RenderBatch c(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 101230168494241L ^ var2;
        long var4 = var2 ^ 138419480735606L;
        long var6 = var2 ^ 57714582392269L;
        long var8 = var2 ^ 119282564081203L;
        long var10 = var2 ^ 74133849655335L;
        long var12 = var2 ^ 29965816231412L;
        if (this.C != null && this.C.isAlive() && b.player != null && b.world != null && !(b.player.distanceTo(this.C) > 6.0F)) {
            long var23 = System.currentTimeMillis() - this.E;
            long var17 = ((Integer)this.j.getValue()).intValue() * 50L;
            if (var23 < var17) {
                return this.D;
            } else {
                PlayerEntity var19 = this.C;
                RenderBatch var20 = this.D;
                int var21 = this.F;
                this.y = true;
                InventoryUtil2.a_void_________(new Object[]{var19, var4});
                this.y = false;
                this.u.a_void_____________________(new Object[]{var6});
                if (var21 >= 0 && InventoryUtil.a_int_____(new Object[]{var12}) != var21) {
                    Object[] var24 = new Object[]{null, var10};
                    var24[0] = var21;
                    InventoryUtil.a_void________(var24);
                }

                this.j(new Object[]{var8});
                return var20;
            }
        } else {
            int var15 = this.p.getValue() && this.z != -1 ? this.z : this.F;
            this.j(new Object[]{var8});
            this.m(new Object[0]);
            if (var15 >= 0 && InventoryUtil.a_int_____(new Object[]{var12}) != var15) {
                Object[] var10003 = new Object[]{null, var10};
                var10003[0] = var15;
                InventoryUtil.a_void________(var10003);
            }

            return null;
        }
    }

    private void j(Object[] var1) {
        this.C = null;
        this.D = null;
        this.E = 0L;
        this.F = -1;
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 101230168494241L ^ var2;
        long var4 = var2 ^ 129500618322043L;
        long var6 = var2 ^ 100033993115714L;
        if ((Boolean)this.p.getValue() && this.z == -1) {
            if (!this.B) {
                this.z = InventoryUtil.a_int_____(new Object[]{var6});
                this.w.a_void_____________________(new Object[]{var4});
            }
        }
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 101230168494241L ^ var2;
        long var4 = var2 ^ 69136522556902L;
        long var6 = var2 ^ 113031959578677L;
        Event.b();
        int var9 = this.z;
        this.m(new Object[0]);
        if (var9 >= 0 && InventoryUtil.a_int_____(new Object[]{var6}) != var9) {
            Object[] var10003 = new Object[]{null, var4};
            var10003[0] = var9;
            InventoryUtil.a_void________(var10003);
        }
    }

    private void m(Object[] var1) {
        this.z = -1;
        this.A = false;
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 101230168494241L ^ var2;
        long var4 = var2 ^ 23651832737185L;
        if (!(Boolean)this.d.getValue()) {
            return false;
        } else if (!((String)this.e.getValue()).equals("Hold Key")) {
            float var7 = (Float)this.g.getValue();
            if ((Boolean)this.h.getValue()) {
                float var8 = (float)b.player.fallDistance;
                float var9 = Math.max((Float)this.c.getValue(), (Float)this.i.getValue());
                float var10 = 10.0F;
                float var11 = Math.max(0.0F, Math.min(1.0F, (var8 - var9) / var10));
                var7 *= var11;
            }

            return Math.random() * 100.0 < var7;
        } else {
            return (Integer)this.f.getValue() != -1 && this.f.isPressed(new Object[]{var4});
        }
    }

    private boolean e(Object[] var1) {
        Event.b();
        float var5 = Math.max((Float)this.c.getValue(), (Float)this.i.getValue());
        return b.player.fallDistance >= var5;
    }

    @Subscribe
    public void a(EntityEvent2 var1) {
        if ((Boolean)this.r.getValue() && !(b.player.fallDistance <= ((Float)this.c.getValue()).floatValue())) {
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, 112864609174293L};
            var10004[0] = var10001;
            PlayerEntity var7 = this.a_class_1657__(var10004);
            if (var7 != null && var7.equals(var1.a(new Object[0]))) {
                var1.b(new Object[]{var1.a_class_238(new Object[0]).expand(((Float)this.s.getValue()).floatValue() / 5.0)});
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(TickEvent7 var1) {
        if (!this.y) {
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, 112864609174293L};
            var10004[0] = var10001;
            PlayerEntity var27 = this.a_class_1657__(var10004);
            if (!this.x
                && b.player.fallDistance > ((Float)this.c.getValue()).floatValue()
                && var27 != null
                && CombatUtil.b_boolean(new Object[]{var27, 53913050824112L})
                && b.player.getMainHandStack().getItem() instanceof AxeItem
                && (Boolean)this.d.getValue()) {
                RenderHelper27 var10000 = this.u;
                var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = 800.0F;
                if (var10000.a(var10004) && this.d(new Object[]{91982287942157L}) && this.e(new Object[]{4716013247995L})) {
                    int var28 = this.d_int_____(new Object[]{81607265740794L});
                    if (var28 != -1) {
                        this.x = true;
                        return;
                    }
                }
            }

            int var29 = this.e_int___(new Object[]{26397655543080L});
            if (var29 != -1
                && InventoryUtil.a_int_____(new Object[]{134339616728996L}) != var29
                && b.crosshairTarget instanceof EntityHitResult
                && !(b.player.fallDistance <= ((Float)this.c.getValue()).floatValue())) {
                this.k(new Object[]{137189438111559L});
                Object[] var10003 = new Object[]{null, 37392877673079L};
                var10003[0] = var29;
                InventoryUtil.a_void________(var10003);
                if ((Boolean)this.p.getValue() && this.z != -1) {
                    this.v.a_void_____________________(new Object[]{93540861878173L});
                    this.A = true;
                }
            }
        }
    }

    private PlayerEntity a_class_1657__(Object[] var1) {
        float var4 = (Float)var1[0];
        long var2 = (Long)var1[1];
        var2 = 101230168494241L ^ var2;
        long var5 = var2 ^ 48579069850174L;
        Event.b();
        float var8 = var4 * var4;
        PlayerEntity var9 = null;
        double var10 = Double.MAX_VALUE;

        for (PlayerEntity var13 : b.world.getPlayers()) {
            if (var13.isAlive() && !var13.equals(b.player) && !Prestige.g.a(new Object[]{var13.getName().getString(), var5})) {
                Vec3d var14 = new Vec3d(var13.getX(), b.player.getY(), var13.getZ());
                double var15 = b.player.squaredDistanceTo(var14);
                if (!(var15 > var8) && var15 < var10) {
                    var10 = var15;
                    var9 = var13;
                }
            }
        }

        return var9;
    }

    private boolean f(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 101230168494241L ^ var2;
        long var4 = var2 ^ 26293542614289L;
        long var6 = var2 ^ 70674685774772L;
        long var8 = var2 ^ 61559715348545L;
        long var10 = var2 ^ 40682094155035L;
        long var12 = var2 ^ 83302105925394L;
        long var14 = var2 ^ 91592635755635L;
        long var16 = var2 ^ 109184437211430L;
        long var18 = var2 ^ 30806212746069L;
        if (!(Boolean)this.o.getValue()) {
            return false;
        } else if (b.player.fallDistance < ((Float)this.c.getValue()).floatValue()) {
            return false;
        } else {
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, var4};
            var10004[0] = var10001;
            PlayerEntity var21 = this.a_class_1657__(var10004);
            if (var21 == null) {
                return false;
            } else if (!var21.isAlive() || b.player.distanceTo(var21) > (CombatUtil.b_boolean(new Object[]{var21, var6}) ? 8.0F : 6.0F)) {
                return false;
            } else if (!PositionUtil.a_boolean________________________________________________(new Object[]{var18})) {
                return false;
            } else if (b.player.getEquippedStack(EquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
                return false;
            } else {
                Integer var22 = InventoryUtil.a_Integer(new Object[]{Items.NETHERITE_CHESTPLATE, var12});
                if (var22 != null) {
                    int var33 = var22;
                    Object[] var34 = new Object[]{null, var14};
                    var34[0] = var33;
                    InventoryUtil.a_void________(var34);
                    InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var16});
                    this.B = true;
                    return false;
                } else {
                    var22 = InventoryUtil.a_Integer(new Object[]{Items.DIAMOND_CHESTPLATE, var12});
                    if (var22 != null) {
                        int var32 = var22;
                        Object[] var10003 = new Object[]{null, var14};
                        var10003[0] = var32;
                        InventoryUtil.a_void________(var10003);
                        InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var16});
                        this.B = true;
                        return false;
                    } else {
                        Item var10000 = Items.NETHERITE_CHESTPLATE;
                        var10004 = new Object[]{null, null, var8};
                        var10004[1] = false;
                        var10004[0] = var10000;
                        List var23 = InventoryUtil.a_List(var10004);
                        if (!var23.isEmpty()) {
                            int var28 = (Integer)var23.getFirst();
                            short var31 = 19837;
                            Object[] var40 = new Object[]{null, null, SlotActionType.PICKUP, var10};
                            var40[1] = 0;
                            var40[0] = var28;
                            InventoryUtil.e(var40);
                            Object[] var38 = new Object[]{null, null, SlotActionType.PICKUP, var10};
                            var38[1] = 0;
                            var38[0] = 6;
                            InventoryUtil.e(var38);
                            var38 = new Object[]{null, null, SlotActionType.PICKUP, var10};
                            var38[1] = 0;
                            var38[0] = var28;
                            InventoryUtil.e(var38);
                            return true;
                        } else {
                            var10000 = Items.DIAMOND_CHESTPLATE;
                            var10004 = new Object[]{null, null, var8};
                            var10004[1] = false;
                            var10004[0] = var10000;
                            var23 = InventoryUtil.a_List(var10004);
                            if (!var23.isEmpty()) {
                                int var24 = (Integer)var23.getFirst();
                                short var30 = 6361;
                                Object[] var10006 = new Object[]{null, null, SlotActionType.PICKUP, var10};
                                var10006[1] = 0;
                                var10006[0] = var24;
                                InventoryUtil.e(var10006);
                                Object[] var10005 = new Object[]{null, null, SlotActionType.PICKUP, var10};
                                var10005[1] = 0;
                                var10005[0] = 6;
                                InventoryUtil.e(var10005);
                                var10005 = new Object[]{null, null, SlotActionType.PICKUP, var10};
                                var10005[1] = 0;
                                var10005[0] = var24;
                                InventoryUtil.e(var10005);
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
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
        long var6 = var2 ^ 46945699070726L;
        Prestige.o.f(new Object[]{this, var4});
        this.j(new Object[]{var6});
        this.m(new Object[0]);
        this.B = false;
    }

    public int d_int_____(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() instanceof AxeItem) {
                return var5;
            }
        }

        return -1;
    }

    public int e_int___(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 101230168494241L ^ var2;
        long var4 = var2 ^ 118720742219636L;
        long var6 = var2 ^ 80160213370881L;
        long var8 = var2 ^ 67525900522677L;
        ArrayList var11 = new ArrayList();
        Event.b();

        for (int var12 = 0; var12 <= 8; var12++) {
            if (InventoryUtil.o(new Object[]{b.player.getInventory().getStack(var12), var8})) {
                var11.add(var12);
            }
        }

        int var18 = -1;
        int var13 = -1;

        for (int var15 : (Iterable<Integer>)(var11)) {
            int var10000;
            if ((Boolean)this.k.getValue() && b.player.fallDistance <= 7.0) {
                Object[] var19 = new Object[]{null, var4};
                var19[0] = var15;
                var10000 = this.c_int_(var19);
            } else {
                Object[] var10004 = new Object[]{null, var6};
                var10004[0] = var15;
                var10000 = this.a_int___________(var10004);
            }

            int var16 = var10000;
            if (var16 > var18) {
                var18 = var16;
                var13 = var15;
            }
        }

        return var13;
    }

    public int a_int___________(Object[] var1) {
        int var4 = (Integer)var1[0];
        Event.b();
        ItemStack var6 = b.player.getInventory().getStack(var4);
        if (var6 != null) {
            float var7 = EnchantmentHelper.getLevel(
                b.world.getRegistryManager().getOptional(Enchantments.DENSITY.getRegistryRef()).get().getEntry(Enchantments.DENSITY.getValue()).get(), var6
            );
            return (int)var7;
        } else {
            return 0;
        }
    }

    public int c_int_(Object[] var1) {
        int var4 = (Integer)var1[0];
        Event.b();
        ItemStack var6 = b.player.getInventory().getStack(var4);
        if (var6 != null) {
            float var7 = EnchantmentHelper.getLevel(
                b.world.getRegistryManager().getOptional(Enchantments.BREACH.getRegistryRef()).get().getEntry(Enchantments.BREACH.getValue()).get(), var6
            );
            return (int)var7;
        } else {
            return 0;
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
