package dev.zprestige.prestige.network;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.math.MathUtil3;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public final class NetworkUtil2 implements RenderContract2 {
    @Subscribe
    public void a(PacketEvent var1) {
        long var9 = System.currentTimeMillis();
        Event.b();
        if (var1.a(new Object[0]) instanceof BlockUpdateS2CPacket var11) {
            if (var11.getState() != null && (var11.getState().isAir() || var11.getState().getBlock() == Blocks.FIRE)) {
                BlockPos var10000 = var11.getPos();
                Object[] var10004 = new Object[]{null, null, 2240376780754L};
                var10004[1] = var9;
                var10004[0] = var10000;
                MathUtil3.c(var10004);
            }
        } else if (var1.a(new Object[0]) instanceof ChunkDeltaUpdateS2CPacket var12) {
            var12.visitUpdates((lpL32_0, lpL32_1) -> {
                if (((net.minecraft.block.BlockState)lpL32_1).isAir() || ((net.minecraft.block.BlockState)lpL32_1).getBlock() == Blocks.FIRE) {
                    Object[] var10004x = new Object[]{null, null, 2240376780754L};
                    var10004x[1] = var9;
                    var10004x[0] = ((net.minecraft.util.math.BlockPos)lpL32_0);
                    MathUtil3.c(var10004x);
                }
            });
        } else if (var1.a(new Object[0]) instanceof ExplosionS2CPacket var13) {
            Vec3d var23 = var13.center();
            double var15 = var23.x;
            double var17 = var23.y;
            double var19 = var23.z;
            MathUtil3.g.put(BlockPos.ofFloored(var15, var17, var19), var9);
        }

        Object[] var10003 = new Object[]{null, 89605591752417L};
        var10003[0] = var9;
        a(var10003);
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var7) {
            if (b.player != null && b.world != null) {
                if (b.player.getStackInHand(var7.getHand()).getItem() instanceof BlockItem) {
                    BlockHitResult var12 = var7.getBlockHitResult();
                    BlockPos var9 = var12.getBlockPos();
                    if (b.world.getBlockState(var9).getBlock() != Blocks.RESPAWN_ANCHOR) {
                        long var10 = System.currentTimeMillis();
                        MathUtil3.h.put(var9.offset(var12.getSide()).toImmutable(), var10);
                        if (b.world.getBlockState(var9).isReplaceable()) {
                            MathUtil3.h.put(var9.toImmutable(), var10);
                        }

                        Object[] var10003 = new Object[]{null, 89605591752417L};
                        var10003[0] = var10;
                        a(var10003);
                    }
                }
            }
        }
    }

    private static void a(Object[] var0) {
        long var1 = (Long)var0[0];
        if (MathUtil3.f.size() > 256) {
            MathUtil3.f.entrySet().removeIf(lpL31_0 -> var1 - (Long)((java.util.Map.Entry)lpL31_0).getValue() > 2000L);
        }

        if (MathUtil3.g.size() > 64) {
            MathUtil3.g.entrySet().removeIf(lpL30_0 -> var1 - (Long)((java.util.Map.Entry)lpL30_0).getValue() > 2000L);
        }

        if (MathUtil3.h.size() > 256) {
            MathUtil3.h.entrySet().removeIf(lpL29_0 -> var1 - (Long)((java.util.Map.Entry)lpL29_0).getValue() > 2000L);
        }
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
