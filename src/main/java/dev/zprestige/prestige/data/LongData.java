package dev.zprestige.prestige.data;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public record LongData(RegistryKey<World> bn, long bo) {
}
