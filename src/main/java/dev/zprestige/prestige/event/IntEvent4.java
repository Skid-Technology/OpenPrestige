package dev.zprestige.prestige.event;

public class IntEvent4 extends Event {
    private final int a;
    private final int b;

    public IntEvent4(int var1, int var2) {
        this.a = var1;
        this.b = var2;
    }

    public int a(Object[] var1) {
        return this.a;
    }

    public int b(Object[] var1) {
        return this.b;
    }
}
