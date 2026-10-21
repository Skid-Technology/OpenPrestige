package dev.zprestige.prestige.event;

import net.minecraft.network.packet.Packet;

public class PacketEvent2 extends Event {
    private final Packet a;

    public PacketEvent2(Packet var1) {
        this.a = var1;
    }

    public Packet a(Object[] var1) {
        return this.a;
    }
}
