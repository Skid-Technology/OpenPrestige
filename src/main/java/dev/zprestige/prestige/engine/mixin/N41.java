package dev.zprestige.prestige.engine.mixin;

import org.spongepowered.asm.mixin.Mixin;

/** Engine anchor for net.minecraft.world.gen.HeightContext: gives Mixin a target so EnginePlugin.preApply runs the ClassTransform hooks on it. */
@Mixin(targets = "net.minecraft.world.gen.HeightContext", remap = false, priority = 2000)
public abstract class N41 {}
