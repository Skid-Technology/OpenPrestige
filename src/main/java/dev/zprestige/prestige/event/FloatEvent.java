package dev.zprestige.prestige.event;

public class FloatEvent extends Event {
    public static boolean a = false;
    private float b;
    private float c;

    public FloatEvent(float var1, float var2) {
        this.b = var1;
        this.c = var2;
    }

    public float a(Object[] var1) {
        return this.b;
    }

    public void b(Object[] var1) {
        float var2 = (Float)var1[0];
        this.b = var2;
    }

    public float b_float_(Object[] var1) {
        return this.c;
    }

    public void c(Object[] var1) {
        float var2 = (Float)var1[0];
        this.c = var2;
    }
}
