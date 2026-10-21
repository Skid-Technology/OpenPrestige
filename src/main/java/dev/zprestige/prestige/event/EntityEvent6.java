package dev.zprestige.prestige.event;

import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

public class EntityEvent6 extends Event {
    private Text a;
    private Text b;
    private Entity c;

    public EntityEvent6(Text var1, Text var2) {
        this(var1, var2, null);
    }

    public EntityEvent6(Text var1, Text var2, Entity var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public Entity a(Object[] var1) {
        return this.c;
    }

    public void b(Object[] var1) {
        Entity var2 = (Entity)var1[0];
        this.c = var2;
    }

    public Text a_class_2561_(Object[] var1) {
        return this.a;
    }

    public void c(Object[] var1) {
        Text var2 = (Text)var1[0];
        this.a = var2;
    }

    public Text b_class_2561(Object[] var1) {
        return this.b;
    }

    public void d(Object[] var1) {
        Text var2 = (Text)var1[0];
        this.b = var2;
    }
}
