package dev.zprestige.prestige.engine.mixin;

import org.spongepowered.asm.mixin.Mixin;

/** Engine anchor for net.minecraft.client.gui.hud.InGameOverlayRenderer: gives Mixin a target so EnginePlugin.preApply runs the ClassTransform hooks on it. */
@Mixin(targets = "net.minecraft.client.gui.hud.InGameOverlayRenderer", remap = false, priority = 2000)
public abstract class N07 {}
