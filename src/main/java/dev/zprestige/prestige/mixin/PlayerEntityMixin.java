package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.entity.player.PlayerEntity;

@CTransformer({PlayerEntity.class})
public class PlayerEntityMixin {
    @CInject(
        method = {"resetTicksSince"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(InjectionCallback ci) {
        if (ClientUtil.dC(ClientUtil.dB())) {
            ci.cancel();
        }
    }
}
