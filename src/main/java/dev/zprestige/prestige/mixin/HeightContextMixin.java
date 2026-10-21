package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.util.ClientUtil;
import dev.zprestige.prestige.world.WorldHelper7;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@CTransformer({HeightContext.class})
public class HeightContextMixin {
    @CInject(
        method = {"<init>"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(ChunkGenerator generator, HeightLimitView world, InjectionCallback ci) {
        if (generator == null) {
            WorldHelper7 accessor = ClientUtil.bY(this);
            ClientUtil.bZ(accessor, -9999999);
            ClientUtil.ca(accessor, 100000000);
            ci.cancel();
        }
    }
}
