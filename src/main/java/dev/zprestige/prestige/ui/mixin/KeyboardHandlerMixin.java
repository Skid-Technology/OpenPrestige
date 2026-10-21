package dev.zprestige.prestige.ui.mixin;

import dev.zprestige.prestige.ui.mc.Overlay;
import net.minecraft.client.Keyboard;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Swallows keyboard input while the overlay is visible. */
@Mixin(Keyboard.class)
public abstract class KeyboardHandlerMixin {
    /** onKey(window, action, KeyInput) */
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void prestigeui$key(long window, int action, KeyInput ev, CallbackInfo ci) {
        if (Overlay.onKey(ev.key(), action)) ci.cancel();
    }

    /** onChar(window, CharInput) */
    @Inject(method = "onChar", at = @At("HEAD"), cancellable = true)
    private void prestigeui$char(long window, CharInput ev, CallbackInfo ci) {
        if (Overlay.onChar(ev.codepoint())) ci.cancel();
    }
}
