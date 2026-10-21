package dev.zprestige.prestige.event;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerEvent extends Event {
    private final PlayerEntity a;

    public PlayerEvent(PlayerEntity var1) {
        this.a = var1;
    }

    public PlayerEntity a(Object[] var1) {
        return this.a;
    }
}
