package dev.zprestige.prestige.event;

import java.awt.Color;

public class ColorEvent extends Event {
    private Color a;
    private Color b;

    public ColorEvent(Color var1) {
        this.a = var1;
        this.b = null;
    }

    public ColorEvent(Color var1, Color var2) {
        this.a = var1;
        this.b = var2;
    }

    public Color a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        Color var2 = (Color)var1[0];
        this.a = var2;
    }

    public Color b_Color(Object[] var1) {
        return this.b;
    }

    public void c(Object[] var1) {
        Color var2 = (Color)var1[0];
        this.b = var2;
    }
}
