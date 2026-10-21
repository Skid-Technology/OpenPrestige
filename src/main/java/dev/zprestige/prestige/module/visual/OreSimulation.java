package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.TextSetting;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.LongData;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.BlockPosBlockEvent;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent13;
import dev.zprestige.prestige.event.TickEvent22;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.util.TpsTimer;
import dev.zprestige.prestige.world.OreColorUtil;
import dev.zprestige.prestige.world.OreSimulationHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.ChunkRandom.RandomProvider;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.feature.OreFeatureConfig.Target;

public class OreSimulation extends Module {
    public OreSimulation() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private MultiSetting a;
    private TextSetting c;
    private IntSetting d;
    public Map e = new ConcurrentHashMap();
    private long f;
    private boolean i;
    private Map g;
    private RegistryKey h;
    private ClientWorld j;
    private Frustum k;

    @Subscribe
    public void a(TickEvent27 var1) {
        if (b.player != null && this.i && this.d(new Object[]{95949034677572L})) {
            int var9 = b.player.getChunkPos().x;
            int var10 = b.player.getChunkPos().z;
            int var11 = (Integer)this.d.getValue();

            for (int var12 = 0; var12 <= var11; var12++) {
                for (int var13 = -var12 + var9; var13 <= var12 + var9; var13++) {
                    int var10003 = var10 + var12 - var11;
                    Object[] var10006 = new Object[]{null, null, null, 2776106951599L};
                    var10006[2] = var10003;
                    var10006[1] = var13;
                    var10006[0] = var1;
                    this.j(var10006);
                }

                for (int var14 = -var12 + 1 + var9; var14 < var12 + var9; var14++) {
                    int var15 = var10 - var12 + var11 + 1;
                    Object[] var16 = new Object[]{null, null, null, 2776106951599L};
                    var16[2] = var15;
                    var16[1] = var14;
                    var16[0] = var1;
                    this.j(var16);
                }
            }
        }
    }

    private void j(Object[] var1) {
        TickEvent27 var5 = (TickEvent27)var1[0];
        int var2 = (Integer)var1[1];
        int var6 = (Integer)var1[2];
        long var3 = (Long)var1[3];
        var3 = 64714675753228L ^ var3;
        long var7 = var3 ^ 92785305685805L;
        long var9 = var3 ^ 21821788944969L;
        if (this.k == null || this.k.isVisible(new Box(var2 << 4, -64.0, var6 << 4, (var2 << 4) + 16, 320.0, (var6 << 4) + 16))) {
            ClientWorld var12 = b.world;
            if (var12 != null) {
                LongData var13 = new LongData(var12.getRegistryKey(), ChunkPos.toLong(var2, var6));
                if (this.e.containsKey(var13)) {
                    Map var14 = (Map)this.e.get(var13);

                    for (Entry var16 : (Iterable<Entry>)(var14.entrySet())) {
                        if (this.a.isSelected(new Object[]{((OreColorUtil)var16.getKey()).c, var9})) {
                            for (Vec3d var18 : (Iterable<Vec3d>)((Set)var16.getValue())) {
                                MatrixCameraData var10000 = var5.b;
                                RenderHelper8 var10001 = var5.a;
                                float var10002 = (float)var18.x;
                                float var10003 = (float)var18.y;
                                float var10004 = (float)var18.z;
                                float var10005 = (float)(var18.x + 1.0);
                                float var10006 = (float)(var18.y + 1.0);
                                float var10007 = (float)(var18.z + 1.0);
                                Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, ((OreColorUtil)var16.getKey()).k, var7};
                                var10011[7] = var10007;
                                var10011[6] = var10006;
                                var10011[5] = var10005;
                                var10011[4] = var10004;
                                var10011[3] = var10003;
                                var10011[2] = var10002;
                                var10011[1] = var10001;
                                var10011[0] = var10000;
                                RenderState.g(var10011);
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent22 var1) {
        this.k = var1.a(new Object[0]);
    }

    @Subscribe
    public void a(BlockPosBlockEvent var1) {
        if (!var1.a_class_2680(new Object[0]).isOpaque()) {
            if (b.world != null) {
                LongData var5 = new LongData(b.world.getRegistryKey(), ChunkPos.toLong(var1.a(new Object[0])));
                if (this.e.containsKey(var5)) {
                    Vec3d var6 = Vec3d.of(var1.a(new Object[0]));

                    for (Set var8 : (Iterable<Set>)(((Map)this.e.get(var5)).values())) {
                        var8.remove(var6);
                    }
                }
            }
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 22594282158762L;
        long var6 = var2 ^ 13444384898553L;
        if (((String)this.c.getValue()).isEmpty()) {
            b.player.sendMessage(Text.of("Please set a seed"), false);
            this.toggle(new Object[]{var4});
        } else {
            this.l(new Object[]{var6});
        }
    }

    @Override
    public void onDisable(Object[] var1) {
        this.e.clear();
        this.g = null;
        this.h = null;
        this.j = null;
        this.i = false;
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 64714675753228L ^ var2;
        long var4 = var2 ^ 128451297919236L;
        if (b.player != null) {
            for (Chunk var8 : (Iterable<Chunk>)(a(new Object[]{false}))) {
                this.m(new Object[]{var8, var4});
            }
        }
    }

    public static Iterable a(Object[] var0) {
        boolean var1 = (Boolean)var0[0];
        return () -> new OreSimulationHelper(var1, 22514788653595L);
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 64714675753228L ^ var2;
        long var4 = var2 ^ 23782784347996L;
        long var6 = var2 ^ 32440197080247L;

        long var8;
        try {
            var8 = Long.parseLong(((String)this.c.getValue()).toString());
        } catch (NumberFormatException var10) {
            this.i = false;
            this.e.clear();
            this.g = null;
            this.h = null;
            this.j = null;
            TpsTimer var10000 = Prestige.v;
            Object[] var10007 = new Object[]{null, null, null, NotificationType.ERROR, var4};
            var10007[2] = 6000L;
            var10007[1] = "Invalid seed!";
            var10007[0] = "Ore Simulation";
            var10000.a(var10007);
            return;
        }

        this.f = var8;
        this.i = true;
        this.h = null;
        this.j = null;
        this.d(new Object[]{var6});
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 64714675753228L ^ var2;
        long var4 = var2 ^ 38290697504492L;
        long var6 = var2 ^ 5737900942308L;
        long var8 = var2 ^ 56056860859111L;
        long var10 = var2 ^ 53382997508982L;
        Event.b();
        ClientWorld var13 = b.world;
        if (this.i && var13 != null) {
            RegistryKey var14 = var13.getRegistryKey();
            if (var13 == this.j && var14.equals(this.h) && this.g != null) {
                return true;
            } else {
                Map var15 = OreColorUtil.a(new Object[]{var14.getValue().getPath(), var4});
                if (var15 != null && !var15.isEmpty()) {
                    this.g = var15;
                    this.h = var14;
                    this.j = var13;
                    this.e.clear();
                    this.k(new Object[]{var10});
                    return true;
                } else {
                    TpsTimer var10000 = Prestige.v;
                    Object[] var10007 = new Object[]{null, null, null, NotificationType.ERROR, var8};
                    var10007[2] = 6000L;
                    var10007[1] = "No ores registered for this dimension";
                    var10007[0] = "Ore Simulation";
                    var10000.a(var10007);
                    this.toggle(new Object[]{var6});
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Subscribe
    public void a(TickEvent13 var1) {
        if (this.d(new Object[]{95949034677572L})) {
            this.m(new Object[]{var1.a(new Object[0]), 21018349512502L});
        }
    }

    private void m(Object[] var1) {
        Chunk var2 = (Chunk)var1[0];
        long var3 = (Long)var1[1];
        var3 = 64714675753228L ^ var3;
        long var5 = var3 ^ 48101512546857L;
        long var7 = var3 ^ 113981655001071L;
        long var9 = var3 ^ 78945745916203L;
        Event.b();
        ClientWorld var12 = b.world;
        if (var12 != null && var12 == this.j && this.i && this.g != null && var12.getRegistryKey().equals(this.h)) {
            ChunkPos var13 = var2.getPos();
            LongData var14 = new LongData(var12.getRegistryKey(), var13.toLong());
            if (!this.e.containsKey(var14)) {
                HashSet var15 = new HashSet();
                ChunkPos.stream(var13, 1).forEach(lpL370_0 -> {
                    Event.b();
                    Chunk var6 = var12.getChunk(((net.minecraft.util.math.ChunkPos)lpL370_0).x, ((net.minecraft.util.math.ChunkPos)lpL370_0).z, ChunkStatus.BIOMES, false);
                    if (var6 != null) {
                        for (ChunkSection var10 : var6.getSectionArray()) {
                            var10.getBiomeContainer().forEachValue(lpL371_0 -> var15.add(((net.minecraft.registry.entry.RegistryEntry)lpL371_0).getKey().get()));
                        }
                    }
                });
                Set var16 = (Set)var15.stream()
                    .flatMap(lpL369_0 -> this.b_List_______(new Object[]{((net.minecraft.registry.RegistryKey)lpL369_0), 120992536422161L}).stream())
                    .collect(Collectors.toSet());
                int var17 = var13.x << 4;
                int var18 = var13.z << 4;
                ChunkRandom var19 = new ChunkRandom(RandomProvider.XOROSHIRO.create(0L));
                long var20 = var19.setPopulationSeed(this.f, var17, var18);
                HashMap var22 = new HashMap();

                for (OreColorUtil var24 : (Iterable<OreColorUtil>)(var16)) {
                    HashSet var25 = new HashSet();
                    var19.setDecoratorSeed(var20, var24.b, var24.a);
                    int var26 = var24.d.get(var19);

                    for (int var27 = 0; var27 < var26; var27++) {
                        if (var24.g == 1.0F || !(var19.nextFloat() >= 1.0F / var24.g)) {
                            int var28 = var19.nextInt(16) + var17;
                            int var29 = var19.nextInt(16) + var18;
                            int var30 = var24.e.get(var19, var24.f);
                            BlockPos var31 = new BlockPos(var28, var30, var29);
                            RegistryKey var32 = var2.getBiomeForNoiseGen(
                                    BiomeCoords.fromBlock(var28), BiomeCoords.fromBlock(var30), BiomeCoords.fromBlock(var29)
                                )
                                .getKey()
                                .get();
                            if (this.b_List_______(new Object[]{var32, var9}).contains(var24)) {
                                if (var24.l) {
                                    var25.addAll(this.c(new Object[]{var12, var19, var31, var24, var7}));
                                } else {
                                    var25.addAll(this.a_ArrayList(new Object[]{var12, var19, var31, var24, var5}));
                                }
                            }
                        }
                    }

                    if (!var25.isEmpty()) {
                        var22.put(var24, var25);
                    }
                }

                this.e.put(var14, var22);
            }
        }
    }

    private List b_List_______(Object[] var1) {
        RegistryKey var4 = (RegistryKey)var1[0];
        return this.g == null ? Collections.emptyList() : (List)this.g.getOrDefault(var4, Collections.emptyList());
    }

    private ArrayList a_ArrayList(Object[] var1) {
        ClientWorld var7 = (ClientWorld)var1[0];
        ChunkRandom var3 = (ChunkRandom)var1[1];
        BlockPos var2 = (BlockPos)var1[2];
        OreColorUtil var4 = (OreColorUtil)var1[3];
        long var5 = (Long)var1[4];
        var5 = 64714675753228L ^ var5;
        long var8 = var5 ^ 6699754321079L;
        Event.b();
        int var11 = var4.i;
        float var12 = var3.nextFloat() * (float) Math.PI;
        float var13 = var11 / 8.0F;
        int var14 = MathHelper.ceil((var11 / 16.0F * 2.0F + 1.0F) / 2.0F);
        double var15 = var2.getX() + Math.sin(var12) * var13;
        double var17 = var2.getX() - Math.sin(var12) * var13;
        double var19 = var2.getZ() + Math.cos(var12) * var13;
        double var21 = var2.getZ() - Math.cos(var12) * var13;
        double var23 = var2.getY() + var3.nextInt(3) - 2;
        double var25 = var2.getY() + var3.nextInt(3) - 2;
        int var27 = var2.getX() - MathHelper.ceil(var13) - var14;
        int var28 = var2.getY() - 2 - var14;
        int var29 = var2.getZ() - MathHelper.ceil(var13) - var14;
        int var30 = 2 * (MathHelper.ceil(var13) + var14);
        int var31 = 2 * (2 + var14);

        for (int var32 = var27; var32 <= var27 + var30; var32++) {
            for (int var33 = var29; var33 <= var29 + var30; var33++) {
                if (var28 <= var7.getTopY(Type.MOTION_BLOCKING, var32, var33)) {
                    Object[] var10018 = new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, var4, var8};
                    var10018[13] = var31;
                    var10018[12] = var30;
                    var10018[11] = var29;
                    var10018[10] = var28;
                    var10018[9] = var27;
                    var10018[8] = var25;
                    var10018[7] = var23;
                    var10018[6] = var21;
                    var10018[5] = var19;
                    var10018[4] = var17;
                    var10018[3] = var15;
                    var10018[2] = var11;
                    var10018[1] = var3;
                    var10018[0] = var7;
                    return this.b_ArrayList(var10018);
                }
            }
        }

        return new ArrayList();
    }

    private ArrayList b_ArrayList(Object[] var1) {
        ClientWorld var4 = (ClientWorld)var1[0];
        ChunkRandom var2 = (ChunkRandom)var1[1];
        int var24 = (Integer)var1[2];
        double var9 = (Double)var1[3];
        double var11 = (Double)var1[4];
        double var13 = (Double)var1[5];
        double var15 = (Double)var1[6];
        double var17 = (Double)var1[7];
        double var19 = (Double)var1[8];
        int var21 = (Integer)var1[9];
        int var3 = (Integer)var1[10];
        int var6 = (Integer)var1[11];
        int var8 = (Integer)var1[12];
        int var5 = (Integer)var1[13];
        OreColorUtil var7 = (OreColorUtil)var1[14];
        long var22 = (Long)var1[15];
        var22 = 64714675753228L ^ var22;
        long var25 = var22 ^ 14942108637690L;
        BitSet var28 = new BitSet(var8 * var5 * var8);
        Mutable var29 = new Mutable();
        double[] var30 = new double[var24 * 4];
        Event.b();
        ArrayList var31 = new ArrayList();

        for (int var32 = 0; var32 < var24; var32++) {
            float var41 = (float)var32 / var24;
            double var33 = MathHelper.lerp((double)var41, var9, var11);
            double var35 = MathHelper.lerp((double)var41, var17, var19);
            double var37 = MathHelper.lerp((double)var41, var13, var15);
            double var39 = var2.nextDouble() * var24 / 16.0;
            double var42 = ((MathHelper.sin((float) Math.PI * var41) + 1.0F) * var39 + 1.0) / 2.0;
            var30[var32 * 4] = var33;
            var30[var32 * 4 + 1] = var35;
            var30[var32 * 4 + 2] = var37;
            var30[var32 * 4 + 3] = var42;
        }

        for (int var66 = 0; var66 < var24 - 1; var66++) {
            if (!(var30[var66 * 4 + 3] <= 0.0)) {
                for (int var72 = var66 + 1; var72 < var24; var72++) {
                    if (!(var30[var72 * 4 + 3] <= 0.0)) {
                        double var68 = var30[var66 * 4] - var30[var72 * 4];
                        double var69 = var30[var66 * 4 + 1] - var30[var72 * 4 + 1];
                        double var70 = var30[var66 * 4 + 2] - var30[var72 * 4 + 2];
                        double var71 = var30[var66 * 4 + 3] - var30[var72 * 4 + 3];
                        if (var71 * var71 > var68 * var68 + var69 * var69 + var70 * var70) {
                            if (var71 > 0.0) {
                                var30[var72 * 4 + 3] = -1.0;
                            } else {
                                var30[var66 * 4 + 3] = -1.0;
                            }
                        }
                    }
                }
            }
        }

        for (int var67 = 0; var67 < var24; var67++) {
            double var73 = var30[var67 * 4 + 3];
            if (!(var73 < 0.0)) {
                double var43 = var30[var67 * 4];
                double var45 = var30[var67 * 4 + 1];
                double var47 = var30[var67 * 4 + 2];
                int var49 = Math.max(MathHelper.floor(var43 - var73), var21);
                int var50 = Math.max(MathHelper.floor(var45 - var73), var3);
                int var51 = Math.max(MathHelper.floor(var47 - var73), var6);
                int var52 = Math.max(MathHelper.floor(var43 + var73), var49);
                int var53 = Math.max(MathHelper.floor(var45 + var73), var50);
                int var54 = Math.max(MathHelper.floor(var47 + var73), var51);

                for (int var55 = var49; var55 <= var52; var55++) {
                    double var56 = (var55 + 0.5 - var43) / var73;
                    if (var56 * var56 < 1.0) {
                        for (int var58 = var50; var58 <= var53; var58++) {
                            double var59 = (var58 + 0.5 - var45) / var73;
                            if (var56 * var56 + var59 * var59 < 1.0) {
                                for (int var61 = var51; var61 <= var54; var61++) {
                                    double var62 = (var61 + 0.5 - var47) / var73;
                                    if (var56 * var56 + var59 * var59 + var62 * var62 < 1.0) {
                                        int var64 = var55 - var21 + (var58 - var3) * var8 + (var61 - var6) * var8 * var5;
                                        if (!var28.get(var64)) {
                                            var28.set(var64);
                                            var29.set(var55, var58, var61);
                                            if (var58 >= -64
                                                && var58 < 320
                                                && this.a_boolean___________________________________(new Object[]{var4, var29, var7, var2, var25})) {
                                                var31.add(new Vec3d(var55, var58, var61));
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

        return var31;
    }

    private boolean a_boolean___________________________________(Object[] var1) {
        ClientWorld var2 = (ClientWorld)var1[0];
        BlockPos var5 = (BlockPos)var1[1];
        OreColorUtil var3 = (OreColorUtil)var1[2];
        ChunkRandom var4 = (ChunkRandom)var1[3];
        long var6 = (Long)var1[4];
        var6 = 64714675753228L ^ var6;
        long var8 = var6 ^ 65468701798799L;
        long var10 = var6 ^ 115423491876968L;
        Event.b();
        BlockState var13 = var2.getBlockState(var5);

        for (Target var15 : (Iterable<Target>)(var3.j)) {
            boolean var16 = var13.isOf(var15.state.getBlock()) || var15.target.test(var13, var4);
            if (var16) {
                float var10002 = var3.h;
                Object[] var10005 = new Object[]{null, null, var8};
                var10005[1] = var10002;
                var10005[0] = var4;
                if (this.e(var10005) || !this.f(new Object[]{var2, var5, var10})) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean e(Object[] var1) {
        ChunkRandom var2 = (ChunkRandom)var1[0];
        float var5 = (Float)var1[1];
        if (var5 <= 0.0F) {
            return true;
        } else {
            return var5 >= 1.0F ? false : var2.nextFloat() >= var5;
        }
    }

    private boolean f(Object[] var1) {
        ClientWorld var4 = (ClientWorld)var1[0];
        BlockPos var5 = (BlockPos)var1[1];
        Direction[] var7 = Direction.values();
        int var8 = var7.length;
        Event.b();

        for (int var9 = 0; var9 < var8; var9++) {
            Direction var10 = var7[var9];
            if (var4.getBlockState(var5.offset(var10)).isAir()) {
                return true;
            }
        }

        return false;
    }

    private ArrayList c(Object[] var1) {
        ClientWorld var6 = (ClientWorld)var1[0];
        ChunkRandom var4 = (ChunkRandom)var1[1];
        BlockPos var5 = (BlockPos)var1[2];
        OreColorUtil var7 = (OreColorUtil)var1[3];
        long var2 = (Long)var1[4];
        var2 = 64714675753228L ^ var2;
        long var8 = var2 ^ 81370436481930L;
        long var10 = var2 ^ 137780163744135L;
        Event.b();
        ArrayList var13 = new ArrayList();
        int var14 = var4.nextInt(var7.i + 1);

        for (int var15 = 0; var15 < var14; var15++) {
            int var16 = Math.min(var15, 7);
            Object[] var10005 = new Object[]{null, null, var8};
            var10005[1] = var16;
            var10005[0] = var4;
            int var17 = this.a_int_______________(var10005) + var5.getX();
            var10005 = new Object[]{null, null, var8};
            var10005[1] = var16;
            var10005[0] = var4;
            int var18 = this.a_int_______________(var10005) + var5.getY();
            var10005 = new Object[]{null, null, var8};
            var10005[1] = var16;
            var10005[0] = var4;
            int var19 = this.a_int_______________(var10005) + var5.getZ();
            BlockPos var20 = new BlockPos(var17, var18, var19);
            if (this.a_boolean___________________________________(new Object[]{var6, var20, var7, var4, var10})) {
                var13.add(new Vec3d(var17, var18, var19));
            }
        }

        return var13;
    }

    private int a_int_______________(Object[] var1) {
        ChunkRandom var2 = (ChunkRandom)var1[0];
        int var3 = (Integer)var1[1];
        return Math.round((var2.nextFloat() - var2.nextFloat()) * var3);
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

    private static NumberFormatException a(NumberFormatException var0) {
        return var0;
    }
}
