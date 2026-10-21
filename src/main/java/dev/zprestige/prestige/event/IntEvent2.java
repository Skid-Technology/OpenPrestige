package dev.zprestige.prestige.event;

public class IntEvent2 extends Event {
    private final int a;
    private final int b;

    public IntEvent2(int var1, int var2) {
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
