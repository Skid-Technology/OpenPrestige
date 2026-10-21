package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent21;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;

@CTransformer({ParticleManager.class})
public class ParticleManagerMixin {
    @CInject(
        method = {"addParticle"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(Particle particle, InjectionCallback ci) {
        if (ClientUtil.dy(Prestige.eventBus, TickEvent21.class)) {
            TickEvent21 event = ClientUtil.dz();
            if (ClientUtil.dA(event)) {
                ci.cancel();
            }
        }
    }
}
