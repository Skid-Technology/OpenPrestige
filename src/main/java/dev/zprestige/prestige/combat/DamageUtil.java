package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.DoubleBoolData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.math.MathHelper3;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldHelper;
import dev.zprestige.prestige.world.WorldHelper10;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameMode;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import net.minecraft.world.explosion.Explosion;

public class DamageUtil implements RenderContract2 {
    private final Vec3d a;
    private Explosion c;
    private RaycastContext d;
    private volatile long e;
    private final ConcurrentHashMap f;
    private static final ThreadLocal g = ThreadLocal.withInitial(Object2IntOpenHashMap::new);
    private static final long i = Long.MIN_VALUE;

    public DamageUtil(long var1) {
        super();
        var1 = 0L;
        var1 = 18350856574938L ^ var1;
        long var3 = var1 ^ 91927560072961L;
        this.a = new Vec3d(0.0, 0.0, 0.0);
        this.e = i;
        this.f = new ConcurrentHashMap();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.d = new RaycastContext(null, null, ShapeType.COLLIDER, FluidHandling.ANY, b.player);
    }

    public double a(Object[] var1) {
        LivingEntity var5 = (LivingEntity)var1[0];
        Vec3d var4 = (Vec3d)var1[1];
        long var2 = (Long)var1[2];
        var2 = 18350856574938L ^ var2;
        long var6 = var2 ^ 62045040412105L;
        Object[] var10008 = new Object[]{null, null, null, null, null, var6};
        var10008[4] = false;
        var10008[3] = null;
        var10008[2] = false;
        var10008[1] = var4;
        var10008[0] = var5;
        return this.b(var10008);
    }

    public double b(Object[] var1) {
        LivingEntity var2 = (LivingEntity)var1[0];
        Vec3d var4 = (Vec3d)var1[1];
        boolean var7 = (Boolean)var1[2];
        BlockPos var3 = (BlockPos)var1[3];
        boolean var8 = (Boolean)var1[4];
        long var5 = (Long)var1[5];
        var5 = 18350856574938L ^ var5;
        long var9 = var5 ^ 60394376658852L;
        Event.b();
        long var12 = b.world.getTime();
        if (var12 != this.e) {
            this.f.clear();
            this.e = var12;
        }

        DoubleBoolData var14 = new DoubleBoolData(var2, var4.x, var4.y, var4.z, var7, var3, var8);
        Double var15 = (Double)this.f.get(var14);
        if (var15 != null) {
            return var15;
        } else {
            Object[] var10008 = new Object[]{null, null, null, null, null, var9};
            var10008[4] = var8;
            var10008[3] = var3;
            var10008[2] = var7;
            var10008[1] = var4;
            var10008[0] = var2;
            double var16 = this.c(var10008);
            this.f.put(var14, var16);
            return var16;
        }
    }

    private double c(Object[] var1) {
        LivingEntity var2 = (LivingEntity)var1[0];
        Vec3d var7 = (Vec3d)var1[1];
        boolean var3 = (Boolean)var1[2];
        BlockPos var4 = (BlockPos)var1[3];
        boolean var8 = (Boolean)var1[4];
        long var5 = (Long)var1[5];
        var5 = 18350856574938L ^ var5;
        long var9 = var5 ^ 70663714774065L;
        long var11 = var5 ^ 116854953095835L;
        long var13 = var5 ^ 140262527072875L;
        long var15 = var5 ^ 45612110305298L;
        long var17 = var5 ^ 23558358614720L;
        long var19 = var5 ^ 125910066054471L;
        if (var2 instanceof PlayerEntity
            && b.getNetworkHandler().getPlayerListEntry(var2.getUuid()) != null
            && b.getNetworkHandler().getPlayerListEntry(var2.getUuid()).getGameMode() == GameMode.CREATIVE) {
            return 0.0;
        } else {
            Vec3d var22 = PositionUtil.a(new Object[]{var2, var11});
            MathHelper3 var10000 = new MathHelper3(this.a);
            double var10001 = var22.x;
            double var10002 = var22.y;
            double var10003 = var22.z;
            Object[] var10006 = new Object[]{null, null, null, var15};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var10000.d(var10006);
            if (var3) {
                var10000 = new MathHelper3(this.a);
                var10001 = this.a.x + var2.getVelocity().x;
                var10002 = this.a.y + var2.getVelocity().y;
                var10003 = this.a.z + var2.getVelocity().z;
                var10006 = new Object[]{null, null, null, var15};
                var10006[2] = var10003;
                var10006[1] = var10002;
                var10006[0] = var10001;
                var10000.d(var10006);
            }

            double var23 = Math.sqrt(this.a.squaredDistanceTo(var7));
            if (var23 > 12.0) {
                return 0.0;
            } else {
                RaycastContext var10004 = this.d;
                Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, var9};
                var10012[5] = var8;
                var10012[4] = var4;
                var10012[3] = var10004;
                var10012[2] = var3;
                var10012[1] = var2;
                var10012[0] = var7;
                double var25 = this.o(var10012);
                double var27 = (1.0 - var23 / 12.0) * var25;
                double var29 = (var27 * var27 + var27) / 2.0 * 7.0 * 12.0 + 1.0;
                Object[] var40 = new Object[]{null, var17};
                var40[0] = var29;
                var29 = this.m(var40);
                var29 = net.minecraft.entity.DamageUtil.getDamageLeft(
                    var2,
                    (float)var29,
                    b.world.getDamageSources().explosion(null),
                    var2.getArmor(),
                    (float)var2.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).getValue()
                );
                Object[] var10005 = new Object[]{null, null, var19};
                var10005[1] = var29;
                var10005[0] = var2;
                var29 = this.n(var10005);
                var10005 = new Object[]{null, null, b.world.getDamageSources().explosion(null), var13};
                var10005[1] = var29;
                var10005[0] = var2;
                var29 = j(var10005);
                return var29 < 0.0 ? 0.0 : var29;
            }
        }
    }

    public double d(Object[] var1) {
        LivingEntity var3 = (LivingEntity)var1[0];
        Vec3d var7 = (Vec3d)var1[1];
        Vec3d var6 = (Vec3d)var1[2];
        BlockPos var2 = (BlockPos)var1[3];
        long var4 = (Long)var1[4];
        var4 = 18350856574938L ^ var4;
        long var8 = var4 ^ 140583619040745L;
        long var10 = var4 ^ 38438316372702L;
        long var12 = var4 ^ 60626111085102L;
        long var14 = var4 ^ 107516225165399L;
        long var16 = var4 ^ 103216323854981L;
        long var18 = var4 ^ 63983453963522L;
        if (var3 instanceof PlayerEntity
            && b.getNetworkHandler().getPlayerListEntry(var3.getUuid()) != null
            && b.getNetworkHandler().getPlayerListEntry(var3.getUuid()).getGameMode() == GameMode.CREATIVE) {
            return 0.0;
        } else {
            MathHelper3 var10000 = new MathHelper3(this.a);
            double var10001 = var6.x;
            double var10002 = var6.y;
            double var10003 = var6.z;
            Object[] var10006 = new Object[]{null, null, null, var14};
            var10006[2] = var10003;
            var10006[1] = var10002;
            var10006[0] = var10001;
            var10000.d(var10006);
            double var21 = Math.sqrt(this.a.squaredDistanceTo(var7));
            if (var21 > 12.0) {
                return 0.0;
            } else {
                Vec3d var23 = var6.subtract(PositionUtil.a(new Object[]{var3, var10}));
                RaycastContext var10004 = this.d;
                Object[] var10012 = new Object[]{null, null, null, null, null, null, null, null, null, var8};
                var10012[5] = false;
                var10012[4] = var2;
                var10012[3] = var10004;
                var10012[2] = var23;
                var10012[1] = var3;
                var10012[0] = var7;
                double var24 = this.p(var10012);
                double var26 = (1.0 - var21 / 12.0) * var24;
                double var28 = (var26 * var26 + var26) / 2.0 * 7.0 * 12.0 + 1.0;
                Object[] var35 = new Object[]{null, var16};
                var35[0] = var28;
                var28 = this.m(var35);
                var28 = net.minecraft.entity.DamageUtil.getDamageLeft(
                    var3,
                    (float)var28,
                    b.world.getDamageSources().explosion(null),
                    var3.getArmor(),
                    (float)var3.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).getValue()
                );
                Object[] var10005 = new Object[]{null, null, var18};
                var10005[1] = var28;
                var10005[0] = var3;
                var28 = this.n(var10005);
                var10005 = new Object[]{null, null, b.world.getDamageSources().explosion(null), var12};
                var10005[1] = var28;
                var10005[0] = var3;
                var28 = j(var10005);
                return var28 < 0.0 ? 0.0 : var28;
            }
        }
    }

    private double f(Object[] var1) {
        LivingEntity var2 = (LivingEntity)var1[0];
        Vec3d var4 = (Vec3d)var1[1];
        BlockPos var5 = (BlockPos)var1[2];
        BlockPos var3 = (BlockPos)var1[3];
        BlockState var8 = (BlockState)var1[4];
        long var6 = (Long)var1[5];
        var6 = 18350856574938L ^ var6;
        long var9 = var6 ^ 137608125675640L;
        long var11 = var6 ^ 73609917046306L;
        long var13 = var6 ^ 44044175812233L;
        long var15 = var6 ^ 87825474003214L;
        if (var2 instanceof PlayerEntity && ((PlayerEntity)var2).getAbilities().creativeMode) {
            return 0.0;
        } else {
            double var18 = Math.sqrt(var2.squaredDistanceTo(var4));
            if (var18 > 10.0) {
                return 0.0;
            } else {
                RaycastContext var10004 = this.d;
                Object[] var10012 = new Object[]{null, null, null, null, null, null, var5, var3, var8, var9};
                var10012[5] = false;
                var10012[4] = null;
                var10012[3] = var10004;
                var10012[2] = false;
                var10012[1] = var2;
                var10012[0] = var4;
                double var20 = this.o(var10012);
                double var22 = (1.0 - var18 / 10.0) * var20;
                double var24 = (var22 * var22 + var22) / 2.0 * 7.0 * 10.0 + 1.0;
                Object[] var31 = new Object[]{null, var13};
                var31[0] = var24;
                var24 = this.m(var31);
                Object[] var10005 = new Object[]{null, null, var15};
                var10005[1] = var24;
                var10005[0] = var2;
                var24 = this.n(var10005);
                var24 = net.minecraft.entity.DamageUtil.getDamageLeft(
                    var2,
                    (float)var24,
                    b.world.getDamageSources().explosion(null),
                    var2.getArmor(),
                    (float)var2.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).getValue()
                );
                var10005 = new Object[]{null, null, b.world.getDamageSources().explosion(null), var11};
                var10005[1] = var24;
                var10005[0] = var2;
                var24 = j(var10005);
                return var24 < 0.0 ? 0.0 : var24;
            }
        }
    }

    public double g(Object[] var1) {
        LivingEntity var2 = (LivingEntity)var1[0];
        Vec3d var7 = (Vec3d)var1[1];
        double var3 = (Double)var1[2];
        long var5 = (Long)var1[3];
        var5 = 18350856574938L ^ var5;
        long var8 = var5 ^ 50002521674027L;
        Object[] var10009 = new Object[]{null, null, null, null, null, null, var8};
        var10009[2] = var3;
        var10009[1] = var7;
        var10009[0] = var2;
        return this.i(var10009);
    }

    public double h(Object[] var1) {
        LivingEntity var5 = (LivingEntity)var1[0];
        Vec3d var3 = (Vec3d)var1[1];
        double var7 = (Double)var1[2];
        BlockPos var6 = (BlockPos)var1[3];
        BlockPos var2 = (BlockPos)var1[4];
        BlockState var4 = (BlockState)var1[5];
        long var9 = (Long)var1[6];
        var9 = 18350856574938L ^ var9;
        long var11 = var9 ^ 93789556771378L;
        Object[] var10009 = new Object[]{null, null, null, var6, var2, var4, var11};
        var10009[2] = var7;
        var10009[1] = var3;
        var10009[0] = var5;
        return this.i(var10009);
    }

    private double i(Object[] var1) {
        LivingEntity var10 = (LivingEntity)var1[0];
        Vec3d var8 = (Vec3d)var1[1];
        double var6 = (Double)var1[2];
        BlockPos var4 = (BlockPos)var1[3];
        BlockPos var9 = (BlockPos)var1[4];
        BlockState var5 = (BlockState)var1[5];
        long var2 = (Long)var1[6];
        var2 = 18350856574938L ^ var2;
        long var11 = var2 ^ 79313669683112L;
        long var13 = var2 ^ 131904361501170L;
        long var15 = var2 ^ 31933369002329L;
        long var17 = var2 ^ 135126957141726L;
        if (var10 instanceof PlayerEntity && ((PlayerEntity)var10).getAbilities().creativeMode) {
            return 0.0;
        } else {
            double var20 = 4.0 + 1.5 * Math.min(var6, 5.0);
            double var22 = Math.sqrt(var10.squaredDistanceTo(var8));
            if (var22 > var20 * 2.0) {
                return 0.0;
            } else {
                RaycastContext var10004 = this.d;
                Object[] var10012 = new Object[]{null, null, null, null, null, null, null, var9, var5, var11};
                var10012[5] = false;
                var10012[4] = var4;
                var10012[3] = var10004;
                var10012[2] = false;
                var10012[1] = var10;
                var10012[0] = var8;
                double var24 = this.o(var10012);
                double var26 = (1.0 - var22 / (var20 * 2.0)) * var24;
                double var28 = (var26 * var26 + var26) / 2.0 * 7.0 * (var20 * 2.0) + 1.0;
                Object[] var35 = new Object[]{null, var15};
                var35[0] = var28;
                var28 = this.m(var35);
                Object[] var10005 = new Object[]{null, null, var17};
                var10005[1] = var28;
                var10005[0] = var10;
                var28 = this.n(var10005);
                var28 = net.minecraft.entity.DamageUtil.getDamageLeft(
                    var10,
                    (float)var28,
                    b.world.getDamageSources().explosion(null),
                    var10.getArmor(),
                    (float)var10.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS).getValue()
                );
                var10005 = new Object[]{null, null, b.world.getDamageSources().explosion(null), var13};
                var10005[1] = var28;
                var10005[0] = var10;
                var28 = j(var10005);
                return var28 < 0.0 ? 0.0 : var28;
            }
        }
    }

    private static double j(Object[] var0) {
        LivingEntity var3 = (LivingEntity)var0[0];
        double var1 = (Double)var0[1];
        DamageSource var6 = (DamageSource)var0[2];
        long var4 = (Long)var0[3];
        var4 = 18350856574938L ^ var4;
        long var7 = var4 ^ 63254382195539L;
        long var9 = var4 ^ 137971209152305L;
        if (var6.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return var1;
        } else {
            int var12 = 0;
            Object2IntMap var13 = (Object2IntMap)g.get();

            for (EquipmentSlot var15 : AttributeModifierSlot.ARMOR) {
                ItemStack var16 = var3.getEquippedStack(var15);
                a_void___(new Object[]{var16, var13, var7});
                int var17 = a_int(new Object[]{var13, Enchantments.PROTECTION, var9});
                if (var17 > 0) {
                    var12 += var17;
                }

                int var18 = a_int(new Object[]{var13, Enchantments.FIRE_PROTECTION, var9});
                if (var18 > 0 && var6.isIn(DamageTypeTags.IS_FIRE)) {
                    var12 += 2 * var18;
                }

                int var19 = a_int(new Object[]{var13, Enchantments.BLAST_PROTECTION, var9});
                if (var19 > 0 && var6.isIn(DamageTypeTags.IS_EXPLOSION)) {
                    var12 += 2 * var19;
                }

                int var20 = a_int(new Object[]{var13, Enchantments.PROJECTILE_PROTECTION, var9});
                if (var20 > 0 && var6.isIn(DamageTypeTags.IS_PROJECTILE)) {
                    var12 += 2 * var20;
                }

                int var21 = a_int(new Object[]{var13, Enchantments.FEATHER_FALLING, var9});
                if (var21 > 0 && var6.isIn(DamageTypeTags.IS_FALL)) {
                    var12 += 3 * var21;
                }
            }

            return net.minecraft.entity.DamageUtil.getInflictedDamage((float)var1, var12);
        }
    }

    public static void a_void___(Object[] var0) {
        ItemStack var4 = (ItemStack)var0[0];
        Object2IntMap var1 = (Object2IntMap)var0[1];
        Event.b();
        var1.clear();
        if (!var4.isEmpty()) {
            for (Entry var8 : var4.getItem() == Items.ENCHANTED_BOOK
                ? var4.getOrDefault(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT).getEnchantmentEntries()
                : var4.getEnchantments().getEnchantmentEntries()) {
                var1.put((RegistryEntry)var8.getKey(), var8.getIntValue());
            }
        }
    }

    public static int a_int(Object[] var0) {
        Object2IntMap var4 = (Object2IntMap)var0[0];
        RegistryKey var1 = (RegistryKey)var0[1];
        Event.b();

        for (Entry var7 : (Iterable<Entry>)(Object2IntMaps.fastIterable(var4))) {
            if (((RegistryEntry)var7.getKey()).matchesKey(var1)) {
                return var7.getIntValue();
            }
        }

        return 0;
    }

    public double k(Object[] var1) {
        LivingEntity var2 = (LivingEntity)var1[0];
        Vec3d var3 = (Vec3d)var1[1];
        long var4 = (Long)var1[2];
        var4 = 18350856574938L ^ var4;
        long var6 = var4 ^ 7577744648969L;
        return this.f(new Object[]{var2, var3, BlockPos.ofFloored(var3), null, null, var6});
    }

    public double l(Object[] var1) {
        LivingEntity var6 = (LivingEntity)var1[0];
        Vec3d var4 = (Vec3d)var1[1];
        BlockPos var5 = (BlockPos)var1[2];
        BlockState var7 = (BlockState)var1[3];
        long var2 = (Long)var1[4];
        var2 = 18350856574938L ^ var2;
        long var8 = var2 ^ 64892925663312L;
        return this.f(new Object[]{var6, var4, BlockPos.ofFloored(var4), var5, var7, var8});
    }

    private double m(Object[] var1) {
        double var2 = (Double)var1[0];

        return switch (WorldHelper.a[b.world.getDifficulty().ordinal()]) {
            case 1 -> 0.0;
            case 2 -> Math.min(var2 / 2.0 + 1.0, var2);
            case 3 -> var2 * 3.0 / 2.0;
            default -> var2;
        };
    }

    private double n(Object[] var1) {
        LivingEntity var2 = (LivingEntity)var1[0];
        double var3 = (Double)var1[1];
        Event.b();
        double var8 = var3;
        if (var2.hasStatusEffect(StatusEffects.RESISTANCE)) {
            int var10 = var2.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1;
            var8 = var3 * (1.0 - var10 * 0.2);
        }

        return var8 < 0.0 ? 0.0 : var8;
    }

    private double o(Object[] var1) {
        Vec3d var5 = (Vec3d)var1[0];
        Entity var8 = (Entity)var1[1];
        boolean var9 = (Boolean)var1[2];
        RaycastContext var10 = (RaycastContext)var1[3];
        BlockPos var6 = (BlockPos)var1[4];
        boolean var3 = (Boolean)var1[5];
        BlockPos var4 = (BlockPos)var1[6];
        BlockPos var7 = (BlockPos)var1[7];
        BlockState var2 = (BlockState)var1[8];
        long var11 = (Long)var1[9];
        var11 = 18350856574938L ^ var11;
        long var13 = var11 ^ 113914657529415L;
        Vec3d var10003 = var9 ? var8.getVelocity() : null;
        Object[] var10012 = new Object[]{null, null, null, null, null, null, var4, var7, var2, var13};
        var10012[5] = var3;
        var10012[4] = var6;
        var10012[3] = var10;
        var10012[2] = var10003;
        var10012[1] = var8;
        var10012[0] = var5;
        return this.p(var10012);
    }

    private double p(Object[] var1) {
        Vec3d var3 = (Vec3d)var1[0];
        Entity var8 = (Entity)var1[1];
        Vec3d var4 = (Vec3d)var1[2];
        RaycastContext var10 = (RaycastContext)var1[3];
        BlockPos var5 = (BlockPos)var1[4];
        boolean var2 = (Boolean)var1[5];
        BlockPos var9 = (BlockPos)var1[6];
        BlockPos var11 = (BlockPos)var1[7];
        BlockState var12 = (BlockState)var1[8];
        long var6 = (Long)var1[9];
        var6 = 18350856574938L ^ var6;
        long var13 = var6 ^ 54174853893221L;
        long var15 = var6 ^ 53612852969307L;
        long var17 = var6 ^ 16104425364068L;
        Event.b();
        Box var20 = var8.getBoundingBox();
        if (var4 != null) {
            var20 = var20.offset(var4.x, var4.y, var4.z);
        }

        double var21 = 1.0 / ((var20.maxX - var20.minX) * 2.0 + 1.0);
        double var23 = 1.0 / ((var20.maxY - var20.minY) * 2.0 + 1.0);
        double var25 = 1.0 / ((var20.maxZ - var20.minZ) * 2.0 + 1.0);
        double var27 = (1.0 - Math.floor(1.0 / var21) * var21) / 2.0;
        double var29 = (1.0 - Math.floor(1.0 / var25) * var25) / 2.0;
        if (var21 >= 0.0 && var23 >= 0.0 && var25 >= 0.0) {
            int var31 = 0;
            int var32 = 0;
            MathHelper3 var33 = new MathHelper3(this.a);
            WorldHelper10 var34 = new WorldHelper10(var10);

            for (double var35 = 0.0; var35 <= 1.0; var35 += var21) {
                for (double var37 = 0.0; var37 <= 1.0; var37 += var23) {
                    for (double var39 = 0.0; var39 <= 1.0; var39 += var25) {
                        double var41 = MathHelper.lerp(var35, var20.minX, var20.maxX);
                        double var43 = MathHelper.lerp(var37, var20.minY, var20.maxY);
                        double var45 = MathHelper.lerp(var39, var20.minZ, var20.maxZ);
                        double var10001 = var41 + var27;
                        double var10003 = var45 + var29;
                        Object[] var10006 = new Object[]{null, null, null, var17};
                        var10006[2] = var10003;
                        var10006[1] = var43;
                        var10006[0] = var10001;
                        var33.d(var10006);
                        var34.f(new Object[]{this.a, var3, ShapeType.COLLIDER, FluidHandling.NONE, var8, var15});
                        Object[] var10009 = new Object[]{null, null, null, var9, var11, var12, var13};
                        var10009[2] = var2;
                        var10009[1] = var5;
                        var10009[0] = var10;
                        if (this.a_class_3965(var10009).getType() == Type.MISS) {
                            var31++;
                        }

                        var32++;
                    }
                }
            }

            return (double)var31 / var32;
        } else {
            return 0.0;
        }
    }

    private BlockHitResult a_class_3965(Object[] var1) {
        RaycastContext var7 = (RaycastContext)var1[0];
        BlockPos var8 = (BlockPos)var1[1];
        boolean var9 = (Boolean)var1[2];
        BlockPos var5 = (BlockPos)var1[3];
        BlockPos var2 = (BlockPos)var1[4];
        BlockState var6 = (BlockState)var1[5];
        return BlockView.raycast(var7.getStart(), var7.getEnd(), var7, (lpL1_0, lpL1_1) -> {
            BlockState var10;
            if (var5 != null && ((net.minecraft.util.math.BlockPos)lpL1_1).equals(var5)) {
                var10 = Blocks.AIR.getDefaultState();
            } else if (var2 != null && ((net.minecraft.util.math.BlockPos)lpL1_1).equals(var2)) {
                var10 = var6;
            } else if (((net.minecraft.util.math.BlockPos)lpL1_1).equals(var8)) {
                var10 = Blocks.OBSIDIAN.getDefaultState();
            } else {
                var10 = b.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL1_1));
                if (var10.getBlock().getBlastResistance() < 600.0F && var9) {
                    var10 = Blocks.AIR.getDefaultState();
                }
            }

            Vec3d var11 = ((net.minecraft.world.RaycastContext)lpL1_0).getStart();
            Vec3d var12 = ((net.minecraft.world.RaycastContext)lpL1_0).getEnd();
            VoxelShape var13 = ((net.minecraft.world.RaycastContext)lpL1_0).getBlockShape(var10, b.world, ((net.minecraft.util.math.BlockPos)lpL1_1));
            BlockHitResult var14 = b.world.raycastBlock(var11, var12, ((net.minecraft.util.math.BlockPos)lpL1_1), var13, var10);
            VoxelShape var15 = VoxelShapes.empty();
            BlockHitResult var16 = var15.raycast(var11, var12, ((net.minecraft.util.math.BlockPos)lpL1_1));
            double var17 = var14 == null ? Double.MAX_VALUE : ((net.minecraft.world.RaycastContext)lpL1_0).getStart().squaredDistanceTo(var14.getPos());
            double var19 = var16 == null ? Double.MAX_VALUE : ((net.minecraft.world.RaycastContext)lpL1_0).getStart().squaredDistanceTo(var16.getPos());
            return var17 <= var19 ? var14 : var16;
        }, lpL0_0 -> {
            Vec3d var3 = ((net.minecraft.world.RaycastContext)lpL0_0).getStart().subtract(((net.minecraft.world.RaycastContext)lpL0_0).getEnd());
            return BlockHitResult.createMissed(((net.minecraft.world.RaycastContext)lpL0_0).getEnd(), Direction.getFacing(var3.x, var3.y, var3.z), BlockPos.ofFloored(((net.minecraft.world.RaycastContext)lpL0_0).getEnd()));
        });
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
