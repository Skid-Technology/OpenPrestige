package dev.zprestige.prestige.event;

public class StringEvent2 extends Event {
    private String a;
    private String b;

    public StringEvent2(String var1, String var2) {
        this.a = var1;
        this.b = var2;
    }

    public String a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        String var2 = (String)var1[0];
        this.a = var2;
    }

    public String b_String_(Object[] var1) {
        return this.b;
    }

    public void c(Object[] var1) {
        String var2 = (String)var1[0];
        this.b = var2;
    }
}
