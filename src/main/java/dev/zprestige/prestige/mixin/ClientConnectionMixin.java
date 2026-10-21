package dev.zprestige.prestige.mixin;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.util.ClientUtil;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;

@CTransformer({ClientConnection.class})
public class ClientConnectionMixin {
    @CInject(
        method = {"handlePacket"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private static <T extends PacketListener> void a(Packet<T> packet, PacketListener listener, InjectionCallback ci) {
        if (ClientUtil.U(Prestige.eventBus, PacketEvent.class)) {
            PacketEvent event = ClientUtil.V(packet);
            if (ClientUtil.W(event)) {
                ci.cancel();
            }
        }
    }

    @CInject(
        method = {"send(Lnet/minecraft/network/packet/Packet;)V"},
        target = {@CTarget("HEAD")},
        cancellable = true
    )
    private void b(Packet<?> packet, InjectionCallback ci) {
        if (Prestige.eventBus != null && ClientUtil.X(Prestige.eventBus, PacketEvent2.class)) {
            PacketEvent2 event = ClientUtil.Y(packet);
            if (ClientUtil.Z(event)) {
                ci.cancel();
                return;
            }
        }

        if (Prestige.u != null) {
            ClientUtil.aa(Prestige.u, packet);
        }
    }
}
