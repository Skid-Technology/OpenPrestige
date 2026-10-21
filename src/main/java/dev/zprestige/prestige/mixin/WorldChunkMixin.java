package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.BlockPosBlockEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

@CTransformer({WorldChunk.class})
public class WorldChunkMixin {
    @CShadow("world")
    public World world;

    @CInject(
        method = {"setBlockState"},
        target = {@CTarget("TAIL")}
    )
    private void a(BlockPos pos, BlockState state, int flags, InjectionCallback cir) {
        if (ClientUtil.ex(Prestige.eventBus, BlockPosBlockEvent.class)) {
            if (this.world.isClient()) {
                BlockPosBlockEvent event = ClientUtil.ey(pos, state);
                ClientUtil.ez(event);
            }
        }
    }
}
