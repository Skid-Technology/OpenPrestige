package dev.zprestige.prestige.event;

import net.minecraft.entity.Entity;

public class EntityBoolEvent extends Event {
    public final Entity a;
    public boolean b;

    public EntityBoolEvent(Entity var1) {
        this.a = var1;
    }
}
