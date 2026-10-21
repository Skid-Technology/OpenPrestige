package dev.zprestige.prestige.world;

import dev.zprestige.prestige.event.Event;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.Impl;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.WorldPresets;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.ScatteredOreFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer;
import net.minecraft.world.gen.feature.util.PlacedFeatureIndexer.IndexedFeatures;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;

public class OreColorUtil {
    public int a;
    public int b;
    public String c;
    public IntProvider d;
    public HeightProvider e;
    public HeightContext f;
    public float g;
    public float h;
    public int i;
    public List j;
    public Color k;
    public boolean l;

    public static Map a(Object[] var0) {
        String var1 = (String)var0[0];
        long var2 = (Long)var0[1];
        var2 = 6510610185200L ^ var2;
        long var4 = var2 ^ 130665062014404L;
        WrapperLookup var7 = BuiltinRegistries.createWrapperLookup();
        Impl var8 = var7.getOrThrow(RegistryKeys.PLACED_FEATURE);
        Map var9 = var7.getOrThrow(RegistryKeys.WORLD_PRESET).getOrThrow(WorldPresets.DEFAULT).value().createDimensionsRegistryHolder().dimensions();
        Event.b();

        DimensionOptions var10 = switch (var1) {
            case "the_nether" -> (DimensionOptions)var9.get(DimensionOptions.NETHER);
            case "the_end" -> (DimensionOptions)var9.get(DimensionOptions.END);
            default -> (DimensionOptions)var9.get(DimensionOptions.OVERWORLD);
        };
        Set var11 = var10.chunkGenerator().getBiomeSource().getBiomes();
        List var17 = var11.stream().toList();
        List var13 = PlacedFeatureIndexer.collectIndexedFeatures(var17, lpL27_0 -> ((Biome)((net.minecraft.registry.entry.RegistryEntry)lpL27_0).value()).getGenerationSettings().getFeatures(), true);
        HashMap var14 = new HashMap();
        RegistryKey var10003 = OrePlacedFeatures.ORE_COAL_LOWER;
        Object[] var10009 = new Object[]{null, null, null, null, null, "Coal", new Color(15, 15, 15), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_COAL_UPPER;
        var10009 = new Object[]{null, null, null, null, null, "Coal", new Color(15, 15, 15), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_IRON_MIDDLE;
        var10009 = new Object[]{null, null, null, null, null, "Iron", new Color(255, 255, 255), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_IRON_SMALL;
        var10009 = new Object[]{null, null, null, null, null, "Iron", new Color(255, 255, 255), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_IRON_UPPER;
        var10009 = new Object[]{null, null, null, null, null, "Iron", new Color(255, 255, 255), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_GOLD;
        var10009 = new Object[]{null, null, null, null, null, "Gold", new Color(255, 255, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_GOLD_LOWER;
        var10009 = new Object[]{null, null, null, null, null, "Gold", new Color(255, 255, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_GOLD_EXTRA;
        var10009 = new Object[]{null, null, null, null, null, "Gold", new Color(255, 255, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_GOLD_NETHER;
        var10009 = new Object[]{null, null, null, null, null, "Gold", new Color(255, 255, 0), var4};
        var10009[4] = 7;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_GOLD_DELTAS;
        var10009 = new Object[]{null, null, null, null, null, "Gold", new Color(255, 255, 0), var4};
        var10009[4] = 7;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_REDSTONE;
        var10009 = new Object[]{null, null, null, null, null, "Redstone", new Color(255, 0, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_REDSTONE_LOWER;
        var10009 = new Object[]{null, null, null, null, null, "Redstone", new Color(255, 0, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_DIAMOND;
        var10009 = new Object[]{null, null, null, null, null, "Diamond", new Color(0, 255, 255), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_DIAMOND_BURIED;
        var10009 = new Object[]{null, null, null, null, null, "Diamond", new Color(0, 255, 255), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_DIAMOND_LARGE;
        var10009 = new Object[]{null, null, null, null, null, "Diamond", new Color(0, 255, 255), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_DIAMOND_MEDIUM;
        var10009 = new Object[]{null, null, null, null, null, "Diamond", new Color(0, 255, 255), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_LAPIS;
        var10009 = new Object[]{null, null, null, null, null, "Lapis", new Color(0, 0, 200), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_LAPIS_BURIED;
        var10009 = new Object[]{null, null, null, null, null, "Lapis", new Color(0, 0, 200), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_COPPER;
        var10009 = new Object[]{null, null, null, null, null, "Copper", new Color(255, 120, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_COPPER_LARGE;
        var10009 = new Object[]{null, null, null, null, null, "Copper", new Color(255, 120, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_EMERALD;
        var10009 = new Object[]{null, null, null, null, null, "Emerald", new Color(0, 255, 0), var4};
        var10009[4] = 6;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_QUARTZ_NETHER;
        var10009 = new Object[]{null, null, null, null, null, "Quartz", new Color(200, 200, 200), var4};
        var10009[4] = 7;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_QUARTZ_DELTAS;
        var10009 = new Object[]{null, null, null, null, null, "Quartz", new Color(200, 200, 200), var4};
        var10009[4] = 7;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_DEBRIS_SMALL;
        var10009 = new Object[]{null, null, null, null, null, "Ancient Debris", new Color(200, 30, 255), var4};
        var10009[4] = 7;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        var10003 = OrePlacedFeatures.ORE_ANCIENT_DEBRIS_LARGE;
        var10009 = new Object[]{null, null, null, null, null, "Ancient Debris", new Color(200, 30, 255), var4};
        var10009[4] = 7;
        var10009[3] = var10003;
        var10009[2] = var8;
        var10009[1] = var13;
        var10009[0] = var14;
        a_void_______________________________(var10009);
        HashMap var15 = new HashMap();
        var17.forEach(
            lpL25_0 -> {
                var15.put((RegistryKey)((net.minecraft.registry.entry.RegistryEntry)lpL25_0).getKey().get(), new ArrayList());
                ((Biome)((net.minecraft.registry.entry.RegistryEntry)lpL25_0).value())
                    .getGenerationSettings()
                    .getFeatures()
                    .stream()
                    .flatMap(RegistryEntryList::stream)
                    .map(RegistryEntry::value)
                    .filter(var14::containsKey)
                    .forEach(lpL26_0 -> ((List)var15.get(((net.minecraft.registry.entry.RegistryEntry)lpL25_0).getKey().get())).add((OreColorUtil)var14.get(((net.minecraft.world.gen.feature.PlacedFeature)lpL26_0))));
            }
        );
        return var15;
    }

    private static void a_void_______________________________(Object[] var0) {
        Map var4 = (Map)var0[0];
        List var7 = (List)var0[1];
        Impl var8 = (Impl)var0[2];
        RegistryKey var2 = (RegistryKey)var0[3];
        int var1 = (Integer)var0[4];
        String var9 = (String)var0[5];
        Color var3 = (Color)var0[6];
        long var5 = (Long)var0[7];
        var5 = 6510610185200L ^ var5;
        long var10 = var5 ^ 5231273712340L;
        PlacedFeature var12 = (PlacedFeature)var8.getOrThrow(var2).value();
        int var13 = ((IndexedFeatures)var7.get(var1)).indexMapping().applyAsInt(var12);
        OreColorUtil var14 = new OreColorUtil(var12, var1, var13, var9, var3, var10);
        var4.put(var12, var14);
    }

    private OreColorUtil(PlacedFeature var1, int var2, int var3, String var4, Color var5, long var6) {
        super();
        var6 = 0L;
        var6 = 6510610185200L ^ var6;
        long var8 = var6 ^ 8468932554198L;
        long var10 = var6 ^ 109308055008521L;
        long var12 = var6 ^ 37975217773429L;
        Event.b();
        this.d = ConstantIntProvider.create(1);
        this.g = 1.0F;
        this.j = List.of();
        this.a = var2;
        this.b = var3;
        this.c = var4;
        this.k = var5;
        int var15 = MinecraftClient.getInstance().world.getBottomY();
        int var16 = MinecraftClient.getInstance().world.getDimension().logicalHeight();
        this.f = new HeightContext(null, HeightLimitView.create(var15, var16));

        for (PlacementModifier var18 : var1.placementModifiers()) {
            if (var18 instanceof CountPlacementModifier) {
                this.d = new WorldHelper5((CountPlacementModifier)var18).a(new Object[]{var10});
            } else if (var18 instanceof HeightRangePlacementModifier) {
                this.e = new WorldHelper8((HeightRangePlacementModifier)var18).a(new Object[]{var12});
            } else if (var18 instanceof RarityFilterPlacementModifier) {
                this.g = new WorldHelper9((RarityFilterPlacementModifier)var18).a(new Object[]{var8});
            }
        }

        if (var1.feature().value().config() instanceof OreFeatureConfig var22) {
            this.h = var22.discardOnAirChance;
            this.i = var22.size;
            this.j = List.copyOf(var22.targets);
            if (var1.feature().value().feature() instanceof ScatteredOreFeature) {
                this.l = true;
            }
        } else {
            throw new IllegalStateException("config for " + var1 + "is not OreFeatureConfig.class");
        }
    }

    private static Field a(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field b(Class var0, String var1, Class var2) {
        Field var3 = a(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = b(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = a(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = b(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }

    private static IllegalStateException a(IllegalStateException var0) {
        return var0;
    }
}
