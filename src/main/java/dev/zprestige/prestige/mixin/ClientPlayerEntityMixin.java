package dev.zprestige.prestige.mixin;

import com.mojang.authlib.GameProfile;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Hand;

@CTransformer({ClientPlayerEntity.class})
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @CInject(
        method = {"swingHand"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(Hand hand, InjectionCallback ci) {
        if (Prestige.eventBus != null && ClientUtil.af(Prestige.eventBus, BoolEvent.class)) {
            BoolEvent event = ClientUtil.ag(EventPhase.PRE);
            if (ClientUtil.ah(event)) {
                ci.cancel();
            } else if (!ClientUtil.ai(event) && ClientUtil.ak(ClientUtil.aj(EventPhase.POST))) {
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"sendMovementPackets"},
        target = {@CTarget("HEAD")}
    )
    private void b() {
        FloatEvent2.c = this.getYaw();
        FloatEvent2.d = this.getPitch();
        FloatEvent2 event = ClientUtil.al(EventPhase.PRE, FloatEvent2.c, FloatEvent2.d);
        boolean changed = ClientUtil.am(event);
        RenderBatch movementOverride = Prestige.o == null ? null : ClientUtil.an(Prestige.o);
        if (movementOverride != null) {
            this.setYaw(ClientUtil.ao(movementOverride));
            this.setPitch(ClientUtil.ap(movementOverride));
        } else if (changed) {
            this.setYaw(ClientUtil.aq(event));
            this.setPitch(ClientUtil.ar(event));
        }
    }

    @CInject(
        method = {"sendMovementPackets"},
        target = {@CTarget("RETURN")}
    )
    private void c() {
        FloatEvent2 event = ClientUtil.as(EventPhase.POST, FloatEvent2.c, FloatEvent2.d);
        ClientUtil.at(event);
        this.setYaw(FloatEvent2.c);
        this.setPitch(FloatEvent2.d);
    }
}
