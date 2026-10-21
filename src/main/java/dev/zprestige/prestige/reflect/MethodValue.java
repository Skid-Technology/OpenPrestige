package dev.zprestige.prestige.reflect;

import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class MethodValue {
    private final boolean a;
    private final long b;
    private final Object c;
    private final boolean d;
    private final boolean e;
    private final VarHandle f;
    private final Field g;
    private final Class h;
    private final String i;
    private final Class j;

    public MethodValue(FieldSpec var1, long var2) {
        this.a = true;
        this.b = var1.d;
        this.c = var1.c;
        this.d = var1.b;
        this.f = var1.e;
        this.g = var1.a;
        this.e = ReflectionBase.a != null && var1.d != -1L;
        this.h = null;
        this.i = null;
        this.j = null;
    }

    public MethodValue(Class var1, String var2, Class var3, long var4) {
        this.a = false;
        this.b = -1L;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = var1;
        this.i = var2;
        this.j = var3;
    }

    private Object a(Object[] var1) {
        Object var4 = var1[0];
        return this.d ? this.c : var4;
    }

    public void a_void______________(Object[] var1) {
        Object var2 = var1[0];
        double var3 = (Double)var1[1];
        long var5 = (Long)var1[2];
        var5 = 52553368039867L ^ var5;
        long var7 = var5 ^ 65880657052537L;
        long var9 = var5 ^ 64742562627896L;
        long var11 = var5 ^ 60314311264557L;
        if (!this.a) {
            this.g(new Object[]{var2, var3, var11});
        } else {
            if (this.e) {
                ReflectionBase.a.putDouble(this.a(new Object[]{var2, var7}), this.b, var3);
            } else if (this.f != null) {
                if (this.d) {
                    this.f.set((double)var3);
                } else {
                    this.f.set((Object)var2, (double)var3);
                }
            } else {
                this.h(new Object[]{var2, var3, var9});
            }
        }
    }

    public void b(Object[] var1) {
        Object var4 = var1[0];
        float var5 = (Float)var1[1];
        long var2 = (Long)var1[2];
        var2 = 52553368039867L ^ var2;
        long var6 = var2 ^ 108899763810423L;
        long var8 = var2 ^ 107761667355190L;
        long var10 = var2 ^ 121200748313123L;
        if (!this.a) {
            this.g(new Object[]{var4, var5, var10});
        } else {
            if (this.e) {
                ReflectionBase.a.putFloat(this.a(new Object[]{var4, var6}), this.b, var5);
            } else if (this.f != null) {
                if (this.d) {
                    this.f.set((float)var5);
                } else {
                    this.f.set((Object)var4, (float)var5);
                }
            } else {
                this.h(new Object[]{var4, var5, var8});
            }
        }
    }

    public void f(Object[] var1) {
        Object var5 = var1[0];
        Object var4 = var1[1];
        long var2 = (Long)var1[2];
        var2 = 52553368039867L ^ var2;
        long var6 = var2 ^ 54268476751541L;
        long var8 = var2 ^ 53130240507124L;
        long var10 = var2 ^ 66431625934049L;
        if (!this.a) {
            this.g(new Object[]{var5, var4, var10});
        } else {
            if (this.e) {
                ReflectionBase.a.putObject(this.a(new Object[]{var5, var6}), this.b, var4);
            } else if (this.f != null) {
                if (this.d) {
                    this.f.set((Object)var4);
                } else {
                    this.f.set((Object)var5, (Object)var4);
                }
            } else {
                this.h(new Object[]{var5, var4, var8});
            }
        }
    }

    public Object b_Object(Object[] var1) {
        Object var4 = var1[0];
        long var2 = (Long)var1[1];
        var2 = 52553368039867L ^ var2;
        long var5 = var2 ^ 91599922475202L;
        long var7 = var2 ^ 37377794584031L;
        long var9 = var2 ^ 18105537663605L;
        if (!this.a) {
            return this.c(new Object[]{var4, var7});
        } else if (this.e) {
            return ReflectionBase.a.getObject(this.a(new Object[]{var4, var9}), this.b);
        } else if (this.f != null) {
            return this.d ? (Object)this.f.get() : (Object)this.f.get((Object)var4);
        } else {
            return this.d(new Object[]{var4, var5});
        }
    }

    private void g(Object[] var1) {
        Object var3 = var1[0];
        Object var2 = var1[1];
        long var4 = (Long)var1[2];
        var4 = 52553368039867L ^ var4;
        long var6 = var4 ^ 119917736554176L;
        long var8 = var4 ^ 99292864920260L;
        FieldAccessor.b(new Object[]{var3, this.i, this.h, this.j, var8}).a_void_____________(new Object[]{var2, var6});
    }

    private Object c(Object[] var1) {
        Object var2 = var1[0];
        long var3 = (Long)var1[1];
        var3 = 52553368039867L ^ var3;
        long var5 = var3 ^ 21438735426252L;
        long var7 = var3 ^ 113212448984890L;
        return FieldAccessor.b(new Object[]{var2, this.i, this.h, this.j, var7}).a(new Object[]{var5});
    }

    private void h(Object[] var1) {
        Object var4 = var1[0];
        Object var5 = var1[1];
        long var2 = (Long)var1[2];
        var2 = 52553368039867L ^ var2;
        long var6 = var2 ^ 51423854113786L;

        try {
            this.g.set(this.a(new Object[]{var4, var6}), var5);
        } catch (IllegalAccessException var9) {
            throw new RuntimeException(var9);
        }
    }

    private Object d(Object[] var1) {
        Object var2 = var1[0];
        long var3 = (Long)var1[1];
        var3 = 52553368039867L ^ var3;
        long var5 = var3 ^ 119798619329292L;

        try {
            return this.g.get(this.a(new Object[]{var2, var5}));
        } catch (IllegalAccessException var8) {
            throw new RuntimeException(var8);
        }
    }

    private static Field a(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field b(Class var0, String var1, Class var2) {
        Field var3 = a(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = b(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
        label33:
        for (Method var8 : var0.getDeclaredMethods()) {
            if (var8.getName().equals(var1) && var8.getReturnType() == var2) {
                Class[] var9 = var8.getParameterTypes();
                if (var9.length == var3) {
                    for (int var10 = 0; var10 < var3; var10++) {
                        if (var9[var10] != var4[var10]) {
                            continue label33;
                        }
                    }

                    return var8;
                }
            }
        }

        return null;
    }

    private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = a(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = b(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }

    private static RuntimeException a(RuntimeException var0) {
        return var0;
    }
}
