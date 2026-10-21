package dev.zprestige.prestige.event;

import net.minecraft.item.ItemStack;

public class ItemEvent extends Event {
    private ItemStack a;

    public ItemEvent(ItemStack var1) {
        this.a = var1;
    }

    public ItemStack a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        ItemStack var2 = (ItemStack)var1[0];
        this.a = var2;
    }
}
