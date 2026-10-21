package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.event.TickEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

@CTransformer({InGameOverlayRenderer.class})
public class InGameOverlayRendererMixin {
    @CInject(
        method = {"renderFireOverlay"},
        target = {@CTarget(
            value = "INVOKE",
            target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V"
        )},
        cancellable = true
    )
    private static void a(MatrixStack matrices, VertexConsumerProvider vertexConsumers, InjectionCallback ci) {
        TickEvent event = ClientUtil.cp();
        if (ClientUtil.cq(event)) {
            ci.cancel();
        }
    }
}
