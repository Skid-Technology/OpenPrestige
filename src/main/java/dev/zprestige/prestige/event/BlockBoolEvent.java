package dev.zprestige.prestige.event;

import net.minecraft.block.Block;

public class BlockBoolEvent extends Event {
    private final Block a;
    private boolean b = false;

    public BlockBoolEvent(Block var1) {
        this.a = var1;
    }

    public Block a(Object[] var1) {
        return this.a;
    }

    public boolean c(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        this.b = var2;
    }
}
