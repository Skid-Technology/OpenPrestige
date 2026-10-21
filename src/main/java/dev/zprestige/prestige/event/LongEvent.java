package dev.zprestige.prestige.event;

public class LongEvent extends Event {
    private long a;

    public LongEvent(long var1) {
        this.a = var1;
    }

    public long a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        long var2 = (Long)var1[0];
        this.a = var2;
    }
}
