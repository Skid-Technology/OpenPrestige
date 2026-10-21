package dev.zprestige.prestige.event;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;

public class EntityEvent2 extends Event {
    private final Entity a;
    private Box b;

    public EntityEvent2(Entity var1, Box var2) {
        this.a = var1;
        this.b = var2;
    }

    public Entity a(Object[] var1) {
        return this.a;
    }

    public Box a_class_238(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        Box var2 = (Box)var1[0];
        this.b = var2;
    }
}
