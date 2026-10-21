package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.TickEvent14;
import dev.zprestige.prestige.event.TickEvent17;
import dev.zprestige.prestige.event.TickEvent26;
import dev.zprestige.prestige.event.TickEvent31;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;

@CTransformer({InGameHud.class})
public class InGameHudMixin {
    @CInject(
        method = {"renderStatusEffectOverlay"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(DrawContext context, RenderTickCounter tickCounter, InjectionCallback ci) {
        TickEvent17 event = ClientUtil.ce();
        if (ClientUtil.cf(event)) {
            ci.cancel();
        }
    }

    @CInject(
        method = {"renderCrosshair"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void b(DrawContext context, RenderTickCounter tickCounter, InjectionCallback ci) {
        if (ClientUtil.cg(Prestige.eventBus, TickEvent14.class)) {
            TickEvent14 event = ClientUtil.ch();
            if (ClientUtil.ci(event)) {
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"renderScoreboardSidebar"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void c(DrawContext context, RenderTickCounter counter, InjectionCallback ci) {
        if (ClientUtil.cj(Prestige.eventBus, TickEvent31.class)) {
            if (ClientUtil.cl(ClientUtil.ck())) {
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"renderOverlayMessage"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void d(DrawContext context, RenderTickCounter tickCounter, InjectionCallback ci) {
        if (ClientUtil.cm(Prestige.eventBus, TickEvent26.class)) {
            if (ClientUtil.co(ClientUtil.cn())) {
                ci.cancel();
            }
        }
    }
}
