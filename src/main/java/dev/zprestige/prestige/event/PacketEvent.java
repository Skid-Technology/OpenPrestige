package dev.zprestige.prestige.event;

import net.minecraft.network.packet.Packet;

public class PacketEvent extends Event {
    private final Packet a;

    public PacketEvent(Packet var1) {
        this.a = var1;
    }

    public Packet a(Object[] var1) {
        return this.a;
    }
}
