package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.DoubleEvent2;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;

@CTransformer({Mouse.class})
public class MouseMixin {
    @CShadow("cursorDeltaX")
    public double cursorDeltaX;
    @CShadow("cursorDeltaY")
    public double cursorDeltaY;

    @CInject(
        method = {"updateMouse"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(double timeDelta, InjectionCallback ci) {
        if (ClientUtil.ds(Prestige.eventBus, DoubleEvent2.class)) {
            DoubleEvent2 event = ClientUtil.dt(this.cursorDeltaX, this.cursorDeltaY);
            if (ClientUtil.du(event)) {
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"onMouseButton"},
        target = {@CTarget("HEAD")}
    )
    private void b(long window, MouseInput input, int action, InjectionCallback ci) {
        int button = input.button();
        if (button != -1) {
            ClientUtil.dw(ClientUtil.dv(button, action));
        }
    }
}
