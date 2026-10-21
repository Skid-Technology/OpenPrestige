package dev.zprestige.prestige.event;

public class IntEvent extends Event {
    private int a;

    public IntEvent(int var1) {
        this.a = var1;
    }

    public int a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        int var2 = (Integer)var1[0];
        this.a = var2;
    }
}
