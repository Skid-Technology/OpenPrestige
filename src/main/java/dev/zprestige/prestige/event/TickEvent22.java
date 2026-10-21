package dev.zprestige.prestige.event;

import net.minecraft.client.render.Frustum;

public class TickEvent22 extends Event {
    private final Frustum a;

    public TickEvent22(Frustum var1) {
        this.a = var1;
    }

    public Frustum a(Object[] var1) {
        return this.a;
    }
}
