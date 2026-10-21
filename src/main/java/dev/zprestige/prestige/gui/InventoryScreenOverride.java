package dev.zprestige.prestige.gui;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.entity.player.PlayerEntity;

public class InventoryScreenOverride extends InventoryScreen {
    public InventoryScreenOverride(PlayerEntity var1) {
        super(var1);
    }

    @Override
    public boolean handleHotbarKeyPressed(KeyInput var1) {
        return false;
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        return false;
    }
}
