package dev.zprestige.prestige.ui.mixin;

import dev.zprestige.prestige.ui.mc.Overlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Draws the overlay at HEAD of RenderSystem.flipFrame (Mojang-named class, never remapped). */
@Mixin(targets = "com.mojang.blaze3d.systems.RenderSystem", remap = false)
public abstract class RenderSystemMixin {
    @Inject(method = "flipFrame", at = @At("HEAD"), remap = false)
    private static void prestigeui$overlay(CallbackInfo ci) {
        Overlay.render();
    }
}
