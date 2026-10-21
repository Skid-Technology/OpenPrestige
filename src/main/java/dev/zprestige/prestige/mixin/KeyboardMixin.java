package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.event.IntEvent2;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyInput;

@CTransformer({Keyboard.class})
public class KeyboardMixin {
    @CShadow("client")
    public MinecraftClient client;

    @CInject(
        method = {"onKey"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(long window, int action, KeyInput input, InjectionCallback ci) {
        int key = input.getKeycode();
        if (key != -1) {
            IntEvent2 event = ClientUtil.cx(key, action);
            if (ClientUtil.cy(event)) {
                ci.cancel();
            }
        }
    }
}
