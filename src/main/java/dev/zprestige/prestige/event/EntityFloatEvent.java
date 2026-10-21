package dev.zprestige.prestige.event;

import net.minecraft.entity.Entity;

public class EntityFloatEvent extends Event {
    private final Entity a;
    private float b;

    public EntityFloatEvent(Entity var1, float var2) {
        this.a = var1;
        this.b = var2;
    }

    public Entity a(Object[] var1) {
        return this.a;
    }

    public float a_float___(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        float var2 = (Float)var1[0];
        this.b = var2;
    }
}
