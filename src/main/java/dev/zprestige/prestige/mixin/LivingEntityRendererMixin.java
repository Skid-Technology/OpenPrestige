package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.render.ESPUtil;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

@CTransformer({LivingEntityRenderer.class})
public class LivingEntityRendererMixin {
    @CShadow("model")
    private EntityModel<?> model;

    @CInject(
        method = {"isVisible"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(LivingEntityRenderState state, InjectionCallback ci) {
        if (ESPUtil.e && ESPUtil.f) {
            if (this.model != null) {
                if (ClientUtil.cZ(this.model)) {
                    ci.setReturnValue(true);
                }
            }
        }
    }
}
