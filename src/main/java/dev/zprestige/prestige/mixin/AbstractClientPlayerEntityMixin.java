package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.StringEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.SkinTextures;

@CTransformer({AbstractClientPlayerEntity.class})
public class AbstractClientPlayerEntityMixin {
    @CInject(
        method = {"getSkin"},
        target = {@CTarget("RETURN")},
        cancellable = true
    )
    private void a(InjectionCallback ci) {
        if (ClientUtil.d(Prestige.eventBus, StringEvent.class)) {
            StringEvent event = ClientUtil.e((SkinTextures)ci.getReturnValue(), ((AbstractClientPlayerEntity)(Object)this).getName().getString());
            if (ClientUtil.f(event)) {
                ci.setReturnValue(ClientUtil.g(event));
            }
        }
    }
}
