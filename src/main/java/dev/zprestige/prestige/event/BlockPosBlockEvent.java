package dev.zprestige.prestige.event;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class BlockPosBlockEvent extends Event {
    private final BlockPos a;
    private final BlockState b;

    public BlockPosBlockEvent(BlockPos var1, BlockState var2) {
        this.a = var1;
        this.b = var2;
    }

    public BlockPos a(Object[] var1) {
        return this.a;
    }

    public BlockState a_class_2680(Object[] var1) {
        return this.b;
    }
}
