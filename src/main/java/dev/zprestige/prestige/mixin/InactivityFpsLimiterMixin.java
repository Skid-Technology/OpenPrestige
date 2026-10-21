package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.InactivityFpsLimiter;

@CTransformer({InactivityFpsLimiter.class})
public class InactivityFpsLimiterMixin {
    @CInject(
        method = {"update"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void a(InjectionCallback cir) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (Prestige.k != null && mc.currentScreen == Prestige.k) {
            int maxFps = mc.options.getMaxFps().getValue();
            cir.setReturnValue(maxFps);
        }
    }
}
