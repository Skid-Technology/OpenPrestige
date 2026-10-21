package dev.zprestige.prestige.event;

import net.minecraft.entity.LivingEntity;

public class LivingEvent extends Event {
    public final LivingEntity a;

    public LivingEvent(LivingEntity var1) {
        this.a = var1;
    }
}
