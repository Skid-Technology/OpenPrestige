package dev.zprestige.prestige.event;

import net.minecraft.entity.Entity;

public class EntityEvent extends Event {
    private final Entity a;

    public EntityEvent(Entity var1) {
        this.a = var1;
    }

    public Entity a(Object[] var1) {
        return this.a;
    }
}
