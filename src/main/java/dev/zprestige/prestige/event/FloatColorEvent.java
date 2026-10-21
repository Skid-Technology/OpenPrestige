package dev.zprestige.prestige.event;

import java.awt.Color;

public class FloatColorEvent extends Event {
    private float a;
    private float b;
    private Color c;
    private Float d;

    public FloatColorEvent(float var1, float var2, Color var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public Color a(Object[] var1) {
        return this.c;
    }

    public void b(Object[] var1) {
        Color var2 = (Color)var1[0];
        this.c = var2;
    }

    public float a_float____(Object[] var1) {
        return this.a;
    }

    public void c(Object[] var1) {
        float var2 = (Float)var1[0];
        this.a = var2;
    }

    public float b_float(Object[] var1) {
        return this.b;
    }

    public void d(Object[] var1) {
        float var2 = (Float)var1[0];
        this.b = var2;
    }

    public Float a_Float(Object[] var1) {
        return this.d;
    }

    public void e(Object[] var1) {
        Float var2 = (Float)var1[0];
        this.d = var2;
    }
}
