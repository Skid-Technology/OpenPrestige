package dev.zprestige.prestige.event;

import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

public class EntityEvent5 extends Event {
    private final Entity a;
    private Text b;

    public EntityEvent5(Entity var1, Text var2) {
        this.a = var1;
        this.b = var2;
    }

    public Entity a(Object[] var1) {
        return this.a;
    }

    public Text a_class_2561(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        Text var2 = (Text)var1[0];
        this.b = var2;
    }
}
