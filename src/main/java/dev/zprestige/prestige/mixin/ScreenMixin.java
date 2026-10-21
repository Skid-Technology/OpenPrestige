package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.event.TickEvent8;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;

@CTransformer({Screen.class})
public class ScreenMixin {
    @CInject(
        method = {"renderBackground"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(DrawContext drawContext, int i, int j, float f, InjectionCallback ci) {
        TickEvent8 event = ClientUtil.dS();
        if (ClientUtil.dT(event)) {
            ci.cancel();
        }
    }

    @CInject(
        method = {"renderBackgroundTexture"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private static void b(DrawContext drawContext, Identifier identifier, int i, int j, float f, float g, int k, int l, InjectionCallback ci) {
        TickEvent8 event = ClientUtil.dU();
        if (ClientUtil.dV(event)) {
            ci.cancel();
        }
    }
}
