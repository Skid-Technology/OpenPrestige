package dev.zprestige.prestige.event;

public final class DoubleEvent2 extends Event {
    private final double a;
    private final double b;

    public DoubleEvent2(double var1, double var3) {
        this.a = var1;
        this.b = var3;
    }

    public double a(Object[] var1) {
        return this.a;
    }

    public double b(Object[] var1) {
        return this.b;
    }
}
