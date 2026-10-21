package dev.zprestige.prestige.event;

import net.minecraft.client.render.Camera;
import org.joml.Matrix4f;

public class FloatMatrixEvent extends Event {
    private final Camera a;
    private final float b;
    private final Matrix4f c;

    public FloatMatrixEvent(Camera var1, float var2, Matrix4f var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public Camera a(Object[] var1) {
        return this.a;
    }

    public float a_float______(Object[] var1) {
        return this.b;
    }

    public Matrix4f a_Matrix4f(Object[] var1) {
        return this.c;
    }
}
