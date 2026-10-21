package dev.zprestige.prestige.event;

import dev.zprestige.prestige.type.EventPhase;

public class FloatEvent2 extends Event {
    private float a;
    private float b;
    public static float c;
    public static float d;

    public FloatEvent2(EventPhase var1, float var2, float var3) {
        super(var1);
        this.a = var2;
        this.b = var3;
    }

    public float a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        float var2 = (Float)var1[0];
        this.a = var2;
    }

    public float b_float__(Object[] var1) {
        return this.b;
    }

    public void c(Object[] var1) {
        float var2 = (Float)var1[0];
        this.b = var2;
    }
}
