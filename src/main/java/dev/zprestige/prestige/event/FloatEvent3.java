package dev.zprestige.prestige.event;

import dev.zprestige.prestige.type.EventPhase;

public class FloatEvent3 extends Event {
    public float a;
    public float b;
    public static float c;
    public static float d;

    public FloatEvent3(EventPhase var1, float var2, float var3) {
        super(var1);
        this.a = var2;
        this.b = var3;
    }
}
