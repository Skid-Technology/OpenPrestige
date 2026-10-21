package dev.zprestige.prestige.event;

import net.minecraft.world.chunk.WorldChunk;

public class TickEvent13 extends Event {
    private final WorldChunk a;

    public TickEvent13(WorldChunk var1) {
        this.a = var1;
    }

    public WorldChunk a(Object[] var1) {
        return this.a;
    }
}
