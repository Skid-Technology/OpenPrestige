package dev.zprestige.prestige.event;

import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.render.RenderHelper8;

public class TickEvent24 extends Event {
    public final RenderHelper8 a;
    public final MatrixCameraData b;

    public TickEvent24(RenderHelper8 var1, MatrixCameraData var2) {
        this.a = var1;
        this.b = var2;
    }
}
