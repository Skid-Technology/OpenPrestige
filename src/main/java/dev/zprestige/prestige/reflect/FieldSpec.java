package dev.zprestige.prestige.reflect;

import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

public class FieldSpec {
    public final Field a;
    public final boolean b;
    public final Object c;
    public final long d;
    public final VarHandle e;

    public FieldSpec(Field var1, boolean var2, Object var3, long var4, VarHandle var6) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.e = var6;
    }
}
