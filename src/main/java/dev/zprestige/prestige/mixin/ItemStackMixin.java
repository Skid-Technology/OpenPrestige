package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent11;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.item.ItemStack;

@CTransformer({ItemStack.class})
public class ItemStackMixin {
    @CInject(
        method = {"getBobbingAnimationTime"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(InjectionCallback info) {
        if (ClientUtil.cr(Prestige.eventBus, TickEvent11.class)) {
            if (ClientUtil.ct(ClientUtil.cs())) {
                info.setReturnValue(0);
            }
        }
    }

    @CInject(
        method = {"setBobbingAnimationTime"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void b(int bobbingAnimationTime, InjectionCallback ci) {
        if (ClientUtil.cu(Prestige.eventBus, TickEvent11.class)) {
            if (ClientUtil.cw(ClientUtil.cv())) {
                ci.cancel();
            }
        }
    }
}
