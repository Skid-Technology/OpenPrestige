package dev.zprestige.prestige.world;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;

public class WorldUtil2 implements RenderContract2 {
    private static final MinecraftClient b = MinecraftClient.getInstance();

    public static boolean a(Object[] var0) {
        Entity var1 = (Entity)var0[0];
        Vec3d var5 = b.player.getEyePos();
        Event.b();
        Vec3d var6 = var1.getEyePos();
        Box var7 = var1.getBoundingBox();
        Vec3d var8 = var6.subtract(var5).normalize();
        double var9 = var5.distanceTo(var6);

        for (double var11 = 0.0; var11 < var9; var11 += 0.1) {
            Vec3d var13 = var5.add(var8.multiply(var11));
            if (var7 != null && var7.contains(var13)) {
                return false;
            }

            BlockPos var14 = BlockPos.ofFloored(var13.x, var13.y, var13.z);
            if (b.world.getBlockState(var14).getBlock() == Blocks.COBWEB) {
                return true;
            }
        }

        return false;
    }

    public static BlockPos a_class_2338______(Object[] var0) {
        BlockHitResult var3 = (BlockHitResult)var0[0];
        return var3.getBlockPos().add(var3.getSide().getOffsetX(), var3.getSide().getOffsetY(), var3.getSide().getOffsetZ());
    }

    public static boolean b(Object[] var0) {
        BlockPos var2 = (BlockPos)var0[0];
        Block var1 = (Block)var0[1];
        long var3 = (Long)var0[2];
        var3 = 66077792794307L ^ var3;
        long var5 = var3 ^ 37798456577286L;
        return a_class_2680_(new Object[]{var2, var5}).isOf(var1);
    }

    public static boolean c(Object[] var0) {
        HitResult var1 = (HitResult)var0[0];
        long var2 = (Long)var0[1];
        var2 = 66077792794307L ^ var2;
        long var4 = var2 ^ 1144871853884L;
        return e(new Object[]{((BlockHitResult)var1).getBlockPos(), var4});
    }

    public static boolean d(Object[] var0) {
        BlockHitResult var3 = (BlockHitResult)var0[0];
        long var1 = (Long)var0[1];
        var1 = 66077792794307L ^ var1;
        long var4 = var1 ^ 47245050379047L;
        return e(new Object[]{var3.getBlockPos(), var4});
    }

    public static boolean e(Object[] var0) {
        BlockPos var3 = (BlockPos)var0[0];
        long var1 = (Long)var0[1];
        var1 = 66077792794307L ^ var1;
        long var4 = var1 ^ 119110895513607L;
        return !b.world.getBlockState(var3).isReplaceable()
            || b.world.getBlockState(var3).isOf(Blocks.FIRE)
            || b.world.getBlockState(var3).isOf(a_class_2248_(new Object[]{var4}))
            || b.world.getBlockState(var3).isOf(Blocks.SNOW) && b.world.getBlockState(var3).get(Properties.LAYERS) == 1;
    }

    public static Block a_class_2248_(Object[] var0) {
        return Blocks.SHORT_GRASS;
    }

    public static boolean f(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        long var2 = (Long)var0[1];
        var2 = 66077792794307L ^ var2;
        long var4 = var2 ^ 94192405109382L;
        long var6 = var2 ^ 81031200972677L;
        return (b(new Object[]{var1, Blocks.OBSIDIAN, var6}) || b(new Object[]{var1, Blocks.BEDROCK, var6}))
            && b(new Object[]{var1.up(), Blocks.AIR, var6})
            && !g(new Object[]{var1.up(), var4});
    }

    public static boolean g(Object[] var0) {
        BlockPos var1 = (BlockPos)var0[0];
        Event.b();
        Box var5 = new Box(var1.getX(), var1.getY(), var1.getZ(), var1.getX() + 1.0, var1.getY() + 1.0, var1.getZ() + 1.0);
        return !b.world.getOtherEntities(null, var5, lpL10_0 -> !(((net.minecraft.entity.Entity)lpL10_0) instanceof ItemEntity)).isEmpty();
    }

    public static List a_List___(Object[] var0) {
        int var3 = (Integer)var0[0];
        Predicate var4 = (Predicate)var0[1];
        ArrayList var6 = new ArrayList();
        Event.b();
        BlockPos var7 = b.player.getBlockPos();
        int var8 = var7.getX();
        int var9 = var7.getY();
        int var10 = var7.getZ();
        Mutable var11 = new Mutable();

        for (int var12 = -var3; var12 <= var3; var12++) {
            for (int var13 = -var3; var13 <= var3; var13++) {
                for (int var14 = -var3; var14 <= var3; var14++) {
                    var11.set(var8 + var12, var9 + var13, var10 + var14);
                    if (var4.test(var11)) {
                        var6.add(var11.toImmutable());
                    }
                }
            }
        }

        return var6;
    }

    public static boolean k(Object[] var0) {
        BlockPos var3 = (BlockPos)var0[0];
        Block var4 = (Block)var0[1];
        return b.world.getBlockState(var3).getBlock() == var4;
    }

    public static BlockState a_class_2680_(Object[] var0) {
        BlockPos var3 = (BlockPos)var0[0];
        return b.world.getBlockState(var3);
    }

    public static int a_int______________________________(Object[] var0) {
        BlockPos var3 = (BlockPos)var0[0];
        long var1 = (Long)var0[1];
        var1 = 66077792794307L ^ var1;
        long var4 = var1 ^ 69417490838164L;
        return a_class_2680_(new Object[]{var3, var4}).get(Properties.CHARGES);
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
