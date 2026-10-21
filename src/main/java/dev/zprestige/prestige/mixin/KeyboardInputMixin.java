package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent16;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.util.math.Vec2f;

@CTransformer({KeyboardInput.class})
public class KeyboardInputMixin extends Input {
    @CInject(
        method = {"tick"},
        target = {@CTarget("HEAD")}
    )
    private void a(InjectionCallback ci) {
        TickEvent16 event = ClientUtil.cz(EventPhase.PRE);
        ClientUtil.cA(event);
    }

    @CInject(
        method = {"tick"},
        target = {@CTarget("RETURN")}
    )
    private void b(InjectionCallback ci) {
        TickEvent16 keyboardTickEvent = ClientUtil.cB(EventPhase.POST);
        ClientUtil.cC(keyboardTickEvent);
    }

    @CInject(
        method = {"tick"},
        target = {@CTarget("RETURN")}
    )
    private void c(InjectionCallback ci) {
        if (Prestige.u != null) {
            if (ClientUtil.cD(Prestige.u)) {
                this.playerInput = ClientUtil.cE(false, false, false, false, false, false, false);
                this.movementVector = Vec2f.ZERO;
            } else if (ClientUtil.cF(Prestige.u) && this.hasForwardMovement()) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client.player != null && !client.player.isSprinting()) {
                    client.player.setSprinting(true);
                }
            }
        }
    }
}
