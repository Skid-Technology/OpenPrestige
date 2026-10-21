package dev.zprestige.prestige.mixin;

import com.mojang.blaze3d.buffers.Std140Builder;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.FloatColorEvent;
import dev.zprestige.prestige.util.ClientUtil;
import java.awt.Color;
import java.nio.ByteBuffer;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import org.joml.Vector4f;

@CTransformer({FogRenderer.class})
public class FogRendererMixin {
    @CInject(
        method = {"applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(
        ByteBuffer buffer,
        int bufPos,
        Vector4f fogColor,
        float environmentalStart,
        float environmentalEnd,
        float renderDistanceStart,
        float renderDistanceEnd,
        float skyEnd,
        float cloudEnd,
        InjectionCallback ci
    ) {
        if (ClientUtil.h(Prestige.eventBus, FloatColorEvent.class)) {
            FloatColorEvent event = ClientUtil.i(0.0F, 0.0F, Color.WHITE);
            if (ClientUtil.j(event)) {
                Vector4f color = ClientUtil.o(
                    ClientUtil.k(event).getRed() / 255.0F,
                    ClientUtil.l(event).getGreen() / 255.0F,
                    ClientUtil.m(event).getBlue() / 255.0F,
                    ClientUtil.n(event).getAlpha() / 255.0F
                );
                Float skyOv = ClientUtil.p(event);
                float effectiveSkyEnd = skyOv == null ? skyEnd : skyOv;
                buffer.position(bufPos);
                Std140Builder.intoBuffer(buffer)
                    .putVec4(color)
                    .putFloat(ClientUtil.q(event))
                    .putFloat(ClientUtil.r(event))
                    .putFloat(ClientUtil.s(event))
                    .putFloat(ClientUtil.t(event))
                    .putFloat(effectiveSkyEnd)
                    .putFloat(cloudEnd);
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;"},
        target = {@CTarget("TAIL")},
        cancellable = true
    )
    private void b(Camera camera, int viewDistance, RenderTickCounter tickCounter, float tickProgress, ClientWorld world, InjectionCallback ci) {
        if (ClientUtil.u(Prestige.eventBus, FloatColorEvent.class)) {
            FloatColorEvent event = ClientUtil.v(0.0F, 0.0F, Color.WHITE);
            if (ClientUtil.w(event)) {
                Color c = ClientUtil.x(event);
                ci.setReturnValue(ClientUtil.y(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F));
            }
        }
    }
}
