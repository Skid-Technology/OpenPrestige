package dev.zprestige.prestige;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

public class ListenerEntry {
    private final Method method;
    private final Object target;
    private final Class eventType;
    private final MethodHandle handle;

    public ListenerEntry(Method var1, Object var2, Class var3) {
        this(var1, var2, var3, null);
    }

    public ListenerEntry(Method var1, Object var2, Class var3, MethodHandle var4) {
        this.method = var1;
        this.target = var2;
        this.eventType = var3;
        this.handle = var4;
    }

    public Method a(Object[] var1) {
        return this.method;
    }

    public Object a_Object(Object[] var1) {
        return this.target;
    }

    public Class a_Class(Object[] var1) {
        return this.eventType;
    }

    public MethodHandle a_MethodHandle(Object[] var1) {
        return this.handle;
    }
}
