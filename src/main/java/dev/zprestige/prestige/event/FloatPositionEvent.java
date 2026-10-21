package dev.zprestige.prestige.event;

import net.minecraft.util.math.Vec3d;

public class FloatPositionEvent extends Event {
    private float a;
    private Vec3d b;

    public FloatPositionEvent(float var1) {
        this.a = var1;
    }

    public FloatPositionEvent(float var1, Vec3d var2) {
        this.a = var1;
        this.b = var2;
    }

    public float a(Object[] var1) {
        return this.a;
    }

    public void b(Object[] var1) {
        float var2 = (Float)var1[0];
        this.a = var2;
    }

    public Vec3d a_class_243(Object[] var1) {
        return this.b;
    }

    public void c(Object[] var1) {
        Vec3d var2 = (Vec3d)var1[0];
        this.b = var2;
    }
}
