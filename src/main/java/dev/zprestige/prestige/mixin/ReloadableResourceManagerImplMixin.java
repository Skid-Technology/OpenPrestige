package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.util.ClientUtil;
import java.util.Optional;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.util.Identifier;

@CTransformer({ReloadableResourceManagerImpl.class})
public class ReloadableResourceManagerImplMixin {
    @CInject(
        method = {"getResource"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void a(Identifier identifier, InjectionCallback callbackInfoReturnable) {
        if (identifier.getNamespace().equals("prestige")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dI(identifier)));
        }

        if (identifier.getPath().equals("shaders/post/glow.json")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dJ("assets/prestige/shaders/post/glow.json")));
        }

        if (identifier.getPath().equals("shaders/program/glow.json")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dK("assets/prestige/shaders/program/glow.json")));
        }

        if (identifier.getPath().equals("shaders/program/glow.fsh")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dL("assets/prestige/shaders/program/glow.fsh")));
        }

        if (identifier.getPath().equals("shaders/core/blur.json")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dM("assets/minecraft/shaders/core/blur.json")));
        }

        if (identifier.getPath().equals("shaders/core/blur.fsh")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dN("assets/minecraft/shaders/core/blur.fsh")));
        }

        if (identifier.getPath().equals("shaders/core/rounded_rect.json")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dO("assets/minecraft/shaders/core/rounded_rect.json")));
        }

        if (identifier.getPath().equals("shaders/core/rounded_rect.fsh")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dP("assets/minecraft/shaders/core/rounded_rect.fsh")));
        }

        if (identifier.getPath().equals("shaders/core/rounded_rect_glow.json")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dQ("assets/minecraft/shaders/core/rounded_rect_glow.json")));
        }

        if (identifier.getPath().equals("shaders/core/rounded_rect_glow.fsh")) {
            callbackInfoReturnable.setReturnValue(Optional.of(ClientUtil.dR("assets/minecraft/shaders/core/rounded_rect_glow.fsh")));
        }
    }
}
