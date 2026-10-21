package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.BlockBoolEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

@CTransformer({Block.class})
public class BlockMixin {
    @CInject(
        method = {"shouldDrawSide"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public static void a(BlockState state, BlockState otherState, Direction side, InjectionCallback cir) {
        if (ClientUtil.z(Prestige.eventBus, BlockBoolEvent.class)) {
            BlockBoolEvent event = ClientUtil.A(state.getBlock());
            if (ClientUtil.B(event)) {
                cir.setReturnValue(ClientUtil.C(event));
            }
        }
    }
}
