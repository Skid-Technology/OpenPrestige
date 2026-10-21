package dev.zprestige.prestige.ui.mixin;

import dev.zprestige.prestige.engine.EngineState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Counts the events posted on the client EventBus per event type (EngineState.events); read with the "state engine" dev command. */
@Mixin(targets = "dev.zprestige.prestige.EventBus", remap = false)
public abstract class EventBusStatsMixin {
    @Inject(method = "post([Ljava/lang/Object;)Z", at = @At("HEAD"), remap = false, require = 1)
    private void engine$count(Object[] args, CallbackInfoReturnable<Boolean> cir) {
        if (args != null && args.length > 0 && args[0] != null) EngineState.onEvent(args[0].getClass());
    }
}
