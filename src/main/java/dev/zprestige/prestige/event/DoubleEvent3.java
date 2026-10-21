package dev.zprestige.prestige.event;

public final class DoubleEvent3 extends Event {
    private double a;
    private double b;
    private double c;

    public DoubleEvent3(double var1, double var3, double var5) {
        this.a = var1;
        this.b = var3;
        this.c = var5;
    }

    public double a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        double var2 = (Double)var1[0];
        this.a = var2;
    }

    public double b_double_(Object[] var1) {
        return this.b;
    }

    public void c(Object[] var1) {
        double var2 = (Double)var1[0];
        this.b = var2;
    }

    public double c_double_(Object[] var1) {
        return this.c;
    }

    public void d(Object[] var1) {
        double var2 = (Double)var1[0];
        this.c = var2;
    }
}
