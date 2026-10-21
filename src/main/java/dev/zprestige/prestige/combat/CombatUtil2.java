package dev.zprestige.prestige.combat;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper26;
import dev.zprestige.prestige.type.BlockBreakType;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;

public final class CombatUtil2 {
    private static final MinecraftClient a = MinecraftClient.getInstance();
    private static final int b = 5;
    private static final double c = 5.0;
    public static final RenderHelper26 d = new RenderHelper26(null, 0.0F, 0.0F);

    private CombatUtil2() {
    }

    public static RenderHelper26 a(Object[] var0) {
        PlayerEntity var1 = (PlayerEntity)var0[0];
        EnumSet var4 = (EnumSet)var0[1];
        float var5 = (Float)var0[2];
        long var2 = (Long)var0[3];
        var2 = 5849964192769L ^ var2;
        long var6 = var2 ^ 34097876804470L;
        long var8 = var2 ^ 86803075311893L;
        long var10 = var2 ^ 13231043016274L;
        long var12 = var2 ^ 33321315552121L;
        long var14 = var2 ^ 45904547140773L;
        if (a.player != null && a.world != null && var1 != null) {
            RenderHelper26 var17 = d;
            if (var4.contains(BlockBreakType.CRYSTAL)) {
                for (Entity var19 : a.world.getEntities()) {
                    if (var19 instanceof EndCrystalEntity) {
                        float var20 = (float)Prestige.f.a(new Object[]{a.player, var19.getBlockPos().toCenterPos(), var10});
                        BlockBreakType var10001 = BlockBreakType.CRYSTAL;
                        Object[] var10006 = new Object[]{null, null, null, null, var6};
                        var10006[3] = var5;
                        var10006[2] = var20;
                        var10006[1] = var10001;
                        var10006[0] = var17;
                        var17 = c(var10006);
                    }
                }
            }

            if (var4.contains(BlockBreakType.ANCHOR)) {
                Object[] var10004 = new Object[]{
                    null,
                    (Predicate<BlockPos>)lpL319_0 -> a_boolean(new Object[]{var1, ((net.minecraft.util.math.BlockPos)lpL319_0), 127524255025262L})
                        && a.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL319_0)).getBlock() == Blocks.RESPAWN_ANCHOR
                        && a.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL319_0)).get(Properties.CHARGES) != 0,
                    var8
                };
                var10004[0] = 5;

                for (BlockPos var28 : (Iterable<BlockPos>)(WorldUtil2.a_List___(var10004))) {
                    float var21 = (float)Prestige.f.k(new Object[]{a.player, var28.toCenterPos(), var14});
                    BlockBreakType var31 = BlockBreakType.ANCHOR;
                    Object[] var35 = new Object[]{null, null, null, null, var6};
                    var35[3] = var5;
                    var35[2] = var21;
                    var35[1] = var31;
                    var35[0] = var17;
                    var17 = c(var35);
                }
            }

            if (var4.contains(BlockBreakType.OBSIDIAN) && !a.player.isOnGround()) {
                Object[] var34 = new Object[]{
                    null,
                    (Predicate<BlockPos>)lpL318_0 -> ((net.minecraft.util.math.BlockPos)lpL318_0).getY() < a.player.getY()
                        && a_boolean(new Object[]{var1, ((net.minecraft.util.math.BlockPos)lpL318_0), 127524255025262L})
                        && a.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL318_0)).getBlock() == Blocks.OBSIDIAN
                        && a.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL318_0).up()).getBlock() == Blocks.AIR,
                    var8
                };
                var34[0] = 5;

                for (BlockPos var29 : (Iterable<BlockPos>)(WorldUtil2.a_List___(var34))) {
                    float var30 = (float)Prestige.f.a(new Object[]{a.player, var29.up().toCenterPos(), var10});
                    BlockBreakType var32 = BlockBreakType.OBSIDIAN;
                    Object[] var36 = new Object[]{null, null, null, null, var6};
                    var36[3] = var5;
                    var36[2] = var30;
                    var36[1] = var32;
                    var36[0] = var17;
                    var17 = c(var36);
                }
            }

            if (var4.contains(BlockBreakType.MACE)) {
                float var25 = a_float(new Object[]{var1, var12});
                if (var25 > 0.0F) {
                    BlockBreakType var33 = BlockBreakType.MACE;
                    Object[] var37 = new Object[]{null, null, null, null, var6};
                    var37[3] = var5;
                    var37[2] = var25;
                    var37[1] = var33;
                    var37[0] = var17;
                    var17 = c(var37);
                }
            }

            return var17;
        } else {
            return d;
        }
    }

    public static RenderHelper26 b(Object[] var0) {
        PlayerEntity var4 = (PlayerEntity)var0[0];
        EnumSet var1 = (EnumSet)var0[1];
        long var2 = (Long)var0[2];
        var2 = 5849964192769L ^ var2;
        long var5 = var2 ^ 29537693058044L;
        Object[] var10005 = new Object[]{null, null, null, var5};
        var10005[2] = 1.0F;
        var10005[1] = var1;
        var10005[0] = var4;
        return a(var10005);
    }

    public static float a_float(Object[] var0) {
        PlayerEntity var1 = (PlayerEntity)var0[0];
        long var2 = (Long)var0[1];
        var2 = 5849964192769L ^ var2;
        long var4 = var2 ^ 43436368200278L;
        return InventoryUtil.o(new Object[]{var1.getMainHandStack(), var4}) && !(var1.fallDistance <= 1.5) && !(var1.getY() <= a.player.getY())
            ? (float)(6.0 + var1.fallDistance * 5.0)
            : 0.0F;
    }

    private static boolean a_boolean(Object[] var0) {
        PlayerEntity var4 = (PlayerEntity)var0[0];
        BlockPos var3 = (BlockPos)var0[1];
        return var4.squaredDistanceTo(var3.getX() + 0.5, var3.getY() + 0.5, var3.getZ() + 0.5) < 25.0;
    }

    private static RenderHelper26 c(Object[] var0) {
        RenderHelper26 var6 = (RenderHelper26)var0[0];
        BlockBreakType var1 = (BlockBreakType)var0[1];
        float var4 = (Float)var0[2];
        float var5 = (Float)var0[3];
        if (var4 <= 0.0F) {
            return var6;
        } else {
            float var8 = var4 * var1.confidence * var5;
            return var8 <= var6.c ? var6 : new RenderHelper26(var1, var4, var8);
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
}
