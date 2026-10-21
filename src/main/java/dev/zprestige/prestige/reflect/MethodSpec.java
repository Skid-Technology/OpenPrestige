package dev.zprestige.prestige.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

public class MethodSpec {
    public final Method a;
    public final MethodHandle b;
    public final boolean c;

    public MethodSpec(Method var1, MethodHandle var2, boolean var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }
}
