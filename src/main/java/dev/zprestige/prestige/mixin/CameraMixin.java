package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.DoubleEvent;
import dev.zprestige.prestige.event.FloatEvent;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.render.Camera;

@CTransformer({Camera.class})
public class CameraMixin {
    @CInject(
        method = {"setPos"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void a(double x, double y, double z, InjectionCallback ci) {
        if (!DoubleEvent.a && ClientUtil.D(Prestige.eventBus, DoubleEvent.class)) {
            DoubleEvent event = ClientUtil.E(x, y, z);
            if (ClientUtil.F(event)) {
                DoubleEvent.a = true;
                ClientUtil.K(ClientUtil.G((Camera)(Object)this), ClientUtil.H(event), ClientUtil.I(event), ClientUtil.J(event));
                DoubleEvent.a = false;
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"setRotation"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void b(float yaw, float pitch, InjectionCallback ci) {
        if (!FloatEvent.a) {
            FloatEvent event = ClientUtil.L(yaw, pitch);
            if (ClientUtil.M(event)) {
                FloatEvent.a = true;
                ClientUtil.Q(ClientUtil.N((Camera)(Object)this), ClientUtil.O(event), ClientUtil.P(event));
                FloatEvent.a = false;
                ci.cancel();
            }
        }
    }
}
