package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.BlockBoolEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder;
import net.minecraft.util.math.BlockPos;

@CTransformer({ChunkOcclusionDataBuilder.class})
public class ChunkOcclusionDataBuilderMixin {
    @CInject(
        method = {"markClosed"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(BlockPos pos, InjectionCallback ci) {
        if (ClientUtil.R(Prestige.eventBus, BlockBoolEvent.class)) {
            if (ClientUtil.T(ClientUtil.S(null))) {
                ci.cancel();
            }
        }
    }
}
