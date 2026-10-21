package dev.zprestige.prestige.data;

import dev.zprestige.prestige.render.GpuBuffer;
import dev.zprestige.prestige.render.RenderHelper31;
import java.util.concurrent.atomic.AtomicReference;

public record BufferData(GpuBuffer au, GpuBuffer av, RenderHelper31 aw, AtomicReference ax) {
}
