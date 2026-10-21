package dev.zprestige.prestige.event;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;

public class FloatEvent5 extends Event {
    private final MatrixStack a;
    private final Arm b;
    private final float c;
    private final float d;

    public FloatEvent5(MatrixStack var1, Arm var2, float var3, float var4) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
    }

    public MatrixStack a(Object[] var1) {
        return this.a;
    }

    public Arm a_class_1306(Object[] var1) {
        return this.b;
    }

    public float a_float_____(Object[] var1) {
        return this.c;
    }

    public float b(Object[] var1) {
        return this.d;
    }
}
