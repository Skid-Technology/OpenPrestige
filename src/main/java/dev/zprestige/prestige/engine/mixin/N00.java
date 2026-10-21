package dev.zprestige.prestige.engine.mixin;

import org.spongepowered.asm.mixin.Mixin;

/** Engine anchor for net.minecraft.block.AbstractBlock$AbstractBlockState: gives Mixin a target so EnginePlugin.preApply runs the ClassTransform hooks on it. */
@Mixin(targets = "net.minecraft.block.AbstractBlock$AbstractBlockState", remap = false, priority = 2000)
public abstract class N00 {}
