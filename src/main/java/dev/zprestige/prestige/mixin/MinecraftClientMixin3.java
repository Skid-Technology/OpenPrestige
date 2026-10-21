package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.ModuleManager;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;

@CTransformer({MinecraftClient.class})
public class MinecraftClientMixin3 {
    @CInject(
        method = {"tick"},
        target = {@CTarget(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;tick(Z)V",
            shift = CTarget.Shift.AFTER
        )},
        cancellable = true
    )
    public void a(InjectionCallback ci) {
        if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null) {
            ModuleManager.f = false;
            TickEvent19 event = ClientUtil.da();
            if (ClientUtil.db(event)) {
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"tick"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void b(InjectionCallback ci) {
        if (ModuleManager.f) {
            if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null) {
                TickEvent19 event = ClientUtil.dc();
                if (ClientUtil.dd(event)) {
                    ci.cancel();
                }
            }
        }
    }

    @CInject(
        method = {"tick"},
        target = {@CTarget("HEAD")}
    )
    public void c(InjectionCallback ci) {
        ClientUtil.df(ClientUtil.de());
    }

    @CInject(
        method = {"onResolutionChanged"},
        target = {@CTarget("TAIL")}
    )
    public void d(InjectionCallback ci) {
        ClientUtil.dh(ClientUtil.dg(MinecraftClient.getInstance().getWindow()));
    }

    @CInject(
        method = {"doItemUse"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void e(InjectionCallback ci) {
        if (ClientUtil.dj(ClientUtil.di())) {
            ci.cancel();
        }
    }

    @CInject(
        method = {"doAttack"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void f(InjectionCallback cir) {
        if (ClientUtil.dl(ClientUtil.dk())) {
            cir.setReturnValue(false);
        }
    }

    @CInject(
        method = {"handleBlockBreaking"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    public void g(boolean bl, InjectionCallback ci) {
        if (ClientUtil.dn(ClientUtil.dm())) {
            ci.cancel();
        }
    }
}
