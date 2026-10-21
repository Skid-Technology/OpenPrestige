package dev.zprestige.prestige.event;

import dev.zprestige.prestige.type.EventPhase;
import net.minecraft.entity.Entity;

public class EntityEvent3 extends Event {
    private final Entity a;

    public EntityEvent3(Entity var1, EventPhase var2) {
        super(var2);
        this.a = var1;
    }

    public Entity a(Object[] var1) {
        return this.a;
    }
}
