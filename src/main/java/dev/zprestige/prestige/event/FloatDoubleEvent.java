package dev.zprestige.prestige.event;

public class FloatDoubleEvent extends Event {
    public float a;
    public double b;
    public double c;
    public double d;

    public FloatDoubleEvent(float var1, double var2, double var4, double var6) {
        this.a = var1;
        this.b = var2;
        this.c = var4;
        this.d = var6;
    }
}
