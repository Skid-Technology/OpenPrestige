package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.FloatEvent5;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;

@CTransformer({HeldItemRenderer.class})
public abstract class HeldItemRendererMixin {
    @CInject(
        method = {"swingArm"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(float swingProgress, MatrixStack matrixStack, int i, Arm arm, InjectionCallback ci) {
        if (ClientUtil.cb(Prestige.eventBus, FloatEvent5.class)) {
            FloatEvent5 event = ClientUtil.cc(matrixStack, arm, swingProgress, 1.0F);
            if (ClientUtil.cd(event)) {
                matrixStack.scale(2.0F, 2.0F, 2.0F);
                ci.cancel();
            }
        }
    }
}
