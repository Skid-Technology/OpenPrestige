package dev.zprestige.prestige.event;

import dev.zprestige.prestige.type.EventPhase;
import net.minecraft.entity.Entity;

public class EntityEvent4 extends Event {
    public final Entity a;

    public EntityEvent4(Entity var1, EventPhase var2) {
        super(var2);
        this.a = var1;
    }
}
