package dev.zprestige.prestige.event;

import dev.zprestige.prestige.type.EventPhase;

public class BoolEvent extends Event {
    private boolean b;

    public BoolEvent(EventPhase var1) {
        super(var1);
    }

    public boolean c(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        this.b = var2;
    }
}
