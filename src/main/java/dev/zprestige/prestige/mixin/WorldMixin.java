package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.LongEvent;
import dev.zprestige.prestige.event.TickEvent23;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.world.World;

@CTransformer({World.class})
public class WorldMixin {
    @CInject(
        method = {"getRainGradient(F)F"},
        target = {@CTarget("RETURN")},
        cancellable = true
    )
    private void a(float delta, InjectionCallback cir) {
        if (ClientUtil.eq(Prestige.eventBus, TickEvent23.class)) {
            if (ClientUtil.es(ClientUtil.er())) {
                cir.setReturnValue(0.0F);
            }
        }
    }

    @CInject(
        method = {"getTimeOfDay"},
        target = {@CTarget("RETURN")},
        cancellable = true
    )
    private void b(InjectionCallback cir) {
        if (ClientUtil.et(Prestige.eventBus, LongEvent.class)) {
            LongEvent event = ClientUtil.eu((Long)cir.getReturnValue());
            if (ClientUtil.ev(event)) {
                cir.setReturnValue(ClientUtil.ew(event));
            }
        }
    }
}
