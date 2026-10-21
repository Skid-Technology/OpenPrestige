package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

@CTransformer({ClientPlayerInteractionManager.class})
public class ClientPlayerInteractionManagerMixin {
    @CInject(
        method = {"attackEntity"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(PlayerEntity player, Entity target, InjectionCallback ci) {
        if (MinecraftClient.getInstance().player != null) {
            if (player.equals(MinecraftClient.getInstance().player)) {
                EntityEvent event = ClientUtil.au(target);
                if (ClientUtil.av(event)) {
                    ci.cancel();
                }
            }
        }
    }
}
