package dev.zprestige.prestige.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent2;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.profiler.Profilers;

@CTransformer({LightmapTextureManager.class})
public class LightmapTextureManagerMixin {
    @CShadow("glTexture")
    private GpuTexture glTexture;

    @CInject(
        method = {"update"},
        target = {@CTarget(
            value = "INVOKE",
            target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V",
            shift = CTarget.Shift.AFTER
        )},
        cancellable = true
    )
    private void a(float tickProgress, InjectionCallback ci) {
        if (ClientUtil.cG(Prestige.eventBus, TickEvent2.class)) {
            TickEvent2 event = ClientUtil.cH();
            if (ClientUtil.cI(event)) {
                RenderSystem.getDevice().createCommandEncoder().clearColorTexture(this.glTexture, ColorHelper.getArgb(255, 255, 255, 255));
                Profilers.get().pop();
                ci.cancel();
            }
        }
    }
}
