package dev.zprestige.prestige.event;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerStringEvent extends Event {
    private final PlayerEntity a;
    private String b;

    public PlayerStringEvent(PlayerEntity var1, String var2) {
        this.a = var1;
        this.b = var2;
    }

    public PlayerEntity a(Object[] var1) {
        return this.a;
    }

    public String a_String__(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        String var2 = (String)var1[0];
        this.b = var2;
    }
}
