package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.module.visual.ESP;
import dev.zprestige.prestige.render.ESPUtil;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.util.ClientUtil;
import java.util.Optional;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.lenni0451.classtransform.annotations.injection.CRedirect;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider.Immediate;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl.ModelCommand;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

@CTransformer({ModelCommandRenderer.class})
public class ModelCommandRendererMixin {
    @CInject(
        method = {"render(Lnet/minecraft/client/render/command/OrderedRenderCommandQueueImpl$ModelCommand;Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/render/OutlineVertexConsumerProvider;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V"},
        target = {@CTarget("HEAD")}
    )
    private void a(
        ModelCommand<?> model,
        RenderLayer renderLayer,
        VertexConsumer vertexConsumer,
        OutlineVertexConsumerProvider outlineVertexConsumers,
        Immediate crumblingOverlayVertexConsumers
    ) {
        if (ESPUtil.e) {
            ESP.u.set(renderLayer);
        }
    }

    @CInject(
        method = {"render(Lnet/minecraft/client/render/command/OrderedRenderCommandQueueImpl$ModelCommand;Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/render/OutlineVertexConsumerProvider;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V"},
        target = {@CTarget("RETURN")}
    )
    private void b() {
        if (ESPUtil.e) {
            ESP.u.remove();
        }
    }

    @CRedirect(
        method = {"render(Lnet/minecraft/client/render/command/OrderedRenderCommandQueueImpl$ModelCommand;Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/render/OutlineVertexConsumerProvider;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V"},
        target = @CTarget(
            value = "INVOKE",
            target = "Lnet/minecraft/client/model/Model;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V",
            ordinal = 0
        )
    )
    private void c(Model model, MatrixStack matrices, VertexConsumer originalConsumer, int light, int overlay, int color) {
        VertexConsumer consumer = originalConsumer;
        RenderLayer layer = (RenderLayer)ESP.u.get();
        if (ESPUtil.e
            && layer != null
            && model instanceof EntityModel<?> entityModel
            && (layer.isOutline() || layer.getAffectedOutline().isPresent())
            && ClientUtil.do_() != null
            && ClientUtil.dp(entityModel)
            && (RenderContract2.b.currentScreen == null || RenderContract2.b.currentScreen instanceof ChatScreen)) {
            Optional<Integer> textureId = ClientUtil.dq(layer);
            Integer id = textureId.orElse(null);
            if (id != null) {
                consumer = ClientUtil.dr(originalConsumer, id);
            }
        }

        model.render(matrices, consumer, light, overlay, color);
    }
}
