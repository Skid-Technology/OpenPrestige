package dev.zprestige.prestige.event;

import net.minecraft.entity.player.SkinTextures;

public class StringEvent extends Event {
    private SkinTextures a;
    private final String b;

    public StringEvent(SkinTextures var1, String var2) {
        this.a = var1;
        this.b = var2;
    }

    public SkinTextures a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        SkinTextures var2 = (SkinTextures)var1[0];
        this.a = var2;
    }

    public String a_String___(Object[] var1) {
        return this.b;
    }
}
