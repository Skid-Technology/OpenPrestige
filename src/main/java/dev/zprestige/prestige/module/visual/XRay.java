package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.BlockBoolEvent;
import dev.zprestige.prestige.event.TickEvent2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class XRay extends Module {
    public XRay() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    public static List a;

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 137403884361407L;
        this.j(new Object[]{var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 43382272281820L;
        this.j(new Object[]{var4});
    }

    @Subscribe
    public void a(TickEvent2 var1) {
        if ((Boolean)this.d.getValue()) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Subscribe
    public void a(BlockBoolEvent var1) {
        if (var1.a(new Object[0]) != null && this.a(new Object[]{var1.a(new Object[0]), 55406673852401L})) {
            var1.b(new Object[]{true});
        }

        var1.a_void_____(new Object[0]);
    }

    public boolean a(Object[] var1) {
        Block var4 = (Block)var1[0];
        return a.contains(var4);
    }

    public void j(Object[] var1) {
        if (b.worldRenderer != null) {
            b.worldRenderer.reload();
            b.worldRenderer.scheduleTerrainUpdate();
        }
    }

    static {
        long[] var0 = new long[]{-965180847646834672L, -4560921742666629086L, 8926569193743056950L, -2692947904796033016L, -5341203359162433522L, -8772031253911175139L, 4452274398816108577L, 8145793621648474163L, -1652677113121079279L, 3146527104435224615L, -7982608722943803344L, -8189377747177439206L, 8467311858359992366L, -7691443587048800234L, 3707995835305295902L, 2996114386201870360L, 556036769766703136L, -1130760903583596533L, -3911967465734668275L, -9165016964744085479L, -4361975451606843350L, -3399299295743049681L, 405124933383880711L, -5938611710645501933L, -8795471080878768119L, 542933309877911579L, -9135026719944605668L, 5335918290235555855L, 1628068299134730275L, -65652401935220724L, 7789352679435665460L, -392972435088998351L, -1650343343856549870L, -1390015927117938667L, -7181382681249185755L, -1773966826351362038L, 645987347883622445L, -5139079277634912225L, -374112086935994315L, -7644537617848991706L, -5167832597622947817L, -8010885589324791758L, -6404928211326599132L, 4273689366590652460L, -3635677455148122069L, 5671192315058716712L, 2588953419276353542L, -8924114486789406679L, -4278980353982463980L};
        Block[] var10000 = new Block[]{
            Blocks.EMERALD_ORE,
            Blocks.EMERALD_BLOCK,
            Blocks.DIAMOND_ORE,
            Blocks.DIAMOND_BLOCK,
            Blocks.GOLD_ORE,
            Blocks.GOLD_BLOCK,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        };
        var10000[(int)var0[46]] = Blocks.IRON_ORE;
        var10000[(int)var0[22]] = Blocks.IRON_BLOCK;
        var10000[(int)var0[3]] = Blocks.COAL_ORE;
        var10000[(int)var0[24]] = Blocks.COAL_BLOCK;
        var10000[(int)var0[35]] = Blocks.REDSTONE_ORE;
        var10000[(int)var0[17]] = Blocks.REDSTONE_BLOCK;
        var10000[(int)var0[29]] = Blocks.LAPIS_ORE;
        var10000[(int)var0[18]] = Blocks.LAPIS_BLOCK;
        var10000[(int)var0[4]] = Blocks.NETHER_QUARTZ_ORE;
        var10000[(int)var0[27]] = Blocks.NETHER_GOLD_ORE;
        var10000[(int)var0[0]] = Blocks.DEEPSLATE_EMERALD_ORE;
        var10000[(int)var0[8]] = Blocks.DEEPSLATE_DIAMOND_ORE;
        var10000[(int)var0[32]] = Blocks.DEEPSLATE_GOLD_ORE;
        var10000[(int)var0[23]] = Blocks.DEEPSLATE_IRON_ORE;
        var10000[(int)var0[48]] = Blocks.DEEPSLATE_COAL_ORE;
        var10000[(int)var0[33]] = Blocks.ANCIENT_DEBRIS;
        var10000[(int)var0[13]] = Blocks.WATER;
        var10000[(int)var0[40]] = Blocks.LAVA;
        var10000[(int)var0[15]] = Blocks.OBSIDIAN;
        var10000[(int)var0[19]] = Blocks.CRYING_OBSIDIAN;
        var10000[(int)var0[11]] = Blocks.BEACON;
        var10000[(int)var0[25]] = Blocks.ENDER_CHEST;
        var10000[(int)var0[26]] = Blocks.CHEST;
        var10000[(int)var0[5]] = Blocks.TRAPPED_CHEST;
        var10000[(int)var0[14]] = Blocks.BARREL;
        var10000[(int)var0[37]] = Blocks.HOPPER;
        var10000[(int)var0[16]] = Blocks.FURNACE;
        var10000[(int)var0[6]] = Blocks.BLAST_FURNACE;
        var10000[(int)var0[1]] = Blocks.SMOKER;
        var10000[(int)var0[28]] = Blocks.ANVIL;
        var10000[(int)var0[42]] = Blocks.ENCHANTING_TABLE;
        var10000[(int)var0[34]] = Blocks.BREWING_STAND;
        var10000[(int)var0[39]] = Blocks.DISPENSER;
        var10000[(int)var0[9]] = Blocks.DROPPER;
        var10000[(int)var0[45]] = Blocks.GLOWSTONE;
        var10000[(int)var0[47]] = Blocks.SEA_LANTERN;
        var10000[(int)var0[20]] = Blocks.TORCH;
        var10000[(int)var0[44]] = Blocks.SOUL_TORCH;
        var10000[(int)var0[43]] = Blocks.CAMPFIRE;
        var10000[(int)var0[36]] = Blocks.SOUL_CAMPFIRE;
        var10000[(int)var0[12]] = Blocks.SPAWNER;
        var10000[(int)var0[21]] = Blocks.END_PORTAL_FRAME;
        var10000[(int)var0[10]] = Blocks.END_GATEWAY;
        var10000[(int)var0[31]] = Blocks.NETHER_PORTAL;
        var10000[(int)var0[41]] = Blocks.MOSSY_COBBLESTONE;
        var10000[(int)var0[7]] = Blocks.STONE_BRICKS;
        var10000[(int)var0[30]] = Blocks.OAK_PLANKS;
        var10000[(int)var0[38]] = Blocks.NOTE_BLOCK;
        a = Arrays.asList(var10000);
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
