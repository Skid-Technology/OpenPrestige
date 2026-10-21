package dev.zprestige.prestige.render;

public class RenderBatch {
    private float a;
    private float b;
    private boolean c;

    public RenderBatch(float var1, float var2) {
        this.a = var1;
        this.b = var2;
    }

    public float a(Object[] var1) {
        return this.a;
    }

    public void a_void___________________(Object[] var1) {
        float var2 = (Float)var1[0];
        this.a = var2;
    }

    public float b(Object[] var1) {
        return this.b;
    }

    public void b_void________(Object[] var1) {
        float var2 = (Float)var1[0];
        this.b = var2;
    }

    public boolean a_boolean________________________________________(Object[] var1) {
        return this.c;
    }

    public void c(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        this.c = var2;
    }
}
