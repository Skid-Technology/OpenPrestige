package dev.zprestige.prestige.event;

public class DoubleEvent extends Event {
    public static boolean a = false;
    private double b;
    private double c;
    private double d;

    public DoubleEvent(double var1, double var3, double var5) {
        this.b = var1;
        this.c = var3;
        this.d = var5;
    }

    public final double a(Object[] var1) {
        return this.b;
    }

    public final void b(Object[] var1) {
        double var2 = (Double)var1[0];
        this.b = var2;
    }

    public final double b_double(Object[] var1) {
        return this.c;
    }

    public final void c(Object[] var1) {
        double var2 = (Double)var1[0];
        this.c = var2;
    }

    public final double c_double(Object[] var1) {
        return this.d;
    }

    public final void d(Object[] var1) {
        double var2 = (Double)var1[0];
        this.d = var2;
    }
}
