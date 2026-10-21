package dev.zprestige.prestige.reflect;

import dev.zprestige.prestige.event.Event;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import net.lenni0451.classtransform.TransformerManager;
import net.lenni0451.classtransform.utils.ASMUtils;

public class FieldAccessor extends ReflectionBase {
    private final Field b;
    private final boolean c;
    private final Object d;
    private final long e;
    private final VarHandle f;
    private final Object g;
    private static final ConcurrentHashMap h = new ConcurrentHashMap();

    private FieldAccessor(Field var1, boolean var2, Object var3, long var4, VarHandle var6, Object var7) {
        this.b = var1;
        this.c = var2;
        this.d = var3;
        this.e = var4;
        this.f = var6;
        this.g = var7;
    }

    private FieldAccessor(FieldSpec var1, Object var2) {
        this(var1.a, var1.b, var1.c, var1.d, var1.e, var2);
    }

    public static FieldAccessor a(Object[] var0) {
        Class var1 = (Class)var0[0];
        String var4 = (String)var0[1];
        long var2 = (Long)var0[2];
        var2 = 81099604477280L ^ var2;
        long var5 = var2 ^ 10354602078213L;
        FieldSpec var7 = ReflectionBase.a_FieldSpec(new Object[]{var1, var4, var5});
        return new FieldAccessor(var7, null);
    }

    public static FieldValue b(Object[] var0) {
        Object var1 = var0[0];
        String var5 = (String)var0[1];
        Class var6 = (Class)var0[2];
        Class var2 = (Class)var0[3];
        long var3 = (Long)var0[4];
        var3 = 81099604477280L ^ var3;
        long var7 = var3 ^ 60490741841137L;
        long var9 = var3 ^ 123110825884294L;
        Event.b();
        Class var12 = var1.getClass();
        ReflectDescriptor var13 = new ReflectDescriptor(var6, var5, var2, var12);
        FieldAccessor var14 = (FieldAccessor)h.get(var13);
        if (var14 == null) {
            String var15 = ASMUtils.toFieldDescriptor(var2);
            String var16 = TransformerManager.getRemapper().getRemapper().reverse().map(var6.getName().replace('.', '/'));
            String var17 = TransformerManager.getRemapper().getRemapper().reverse().mapDesc(var15);
            String var18 = TransformerManager.getRemapper().getRemapper().mapFieldName(var16, var5, var17);
            FieldSpec var19 = ReflectionBase.b_FieldSpec(new Object[]{var12, var18, var15, var9});
            var14 = new FieldAccessor(var19, null);
            FieldAccessor var20 = (FieldAccessor)h.putIfAbsent(var13, var14);
            if (var20 != null) {
                var14 = var20;
            }
        }

        return var14.d_FieldValue(new Object[]{var1, var7});
    }

    public Field a_Field(Object[] var1) {
        return this.b;
    }

    public Object a_Object_(Object[] var1) {
        Object var4 = var1[0];
        long var2 = (Long)var1[1];
        var2 = 81099604477280L ^ var2;
        long var5 = var2 ^ 123908732068046L;

        try {
            if (a != null) {
                return this.c_Object(new Object[]{var4, var5});
            } else if (this.f != null) {
                return this.c ? (Object)this.f.get() : (Object)this.f.get((Object)var4);
            } else {
                return this.c ? this.b.get(null) : this.b.get(var4);
            }
        } catch (IllegalAccessException var9) {
            throw new RuntimeException(var9);
        }
    }

    public void b_void_____(Object[] var1) {
        Object var2 = var1[0];
        Object var5 = var1[1];
        long var3 = (Long)var1[2];
        var3 = 81099604477280L ^ var3;
        long var6 = var3 ^ 37363395543600L;
        if (this.c) {
            if (a != null) {
                this.d(new Object[]{null, var5, var6});
            } else if (this.f != null) {
                this.f.set((Object)var5);
            } else {
                try {
                    this.b.set(null, var5);
                } catch (IllegalAccessException var10) {
                    throw new RuntimeException(var10);
                }
            }
        } else if (a != null) {
            this.d(new Object[]{var2, var5, var6});
        } else if (this.f != null) {
            this.f.set((Object)var2, (Object)var5);
        } else {
            try {
                this.b.set(var2, var5);
            } catch (IllegalAccessException var11) {
                throw new RuntimeException(var11);
            }
        }
    }

    public void c(Object[] var1) {
        Object var2 = var1[0];
        long var3 = (Long)var1[1];
        var3 = 81099604477280L ^ var3;
        long var5 = var3 ^ 123497463895845L;
        if (!this.c) {
            if (this.g == null) {
                throw new IllegalStateException("set(value) without target only valid for static fields or when bound to an instance");
            } else {
                this.b_void_____(new Object[]{this.g, var2, var5});
            }
        } else {
            this.b_void_____(new Object[]{null, var2, var5});
        }
    }

    private Object c_Object(Object[] var1) {
        Object var2 = var1[0];
        Class var6 = this.b.getType();
        Event.b();
        boolean var7 = var6.isPrimitive();
        Object var8 = this.c ? this.d : var2;
        if (!var7) {
            return a.getObject(var8, this.e);
        } else if (var6 == int.class) {
            return a.getInt(var8, this.e);
        } else if (var6 == long.class) {
            return a.getLong(var8, this.e);
        } else if (var6 == boolean.class) {
            return a.getBoolean(var8, this.e);
        } else if (var6 == byte.class) {
            return a.getByte(var8, this.e);
        } else if (var6 == char.class) {
            return a.getChar(var8, this.e);
        } else if (var6 == short.class) {
            return a.getShort(var8, this.e);
        } else if (var6 == float.class) {
            return a.getFloat(var8, this.e);
        } else {
            return var6 == double.class ? a.getDouble(var8, this.e) : a.getObject(var8, this.e);
        }
    }

    private void d(Object[] var1) {
        Object var3 = var1[0];
        Object var2 = var1[1];
        Event.b();
        Class var7 = this.b.getType();
        Object var8 = this.c ? this.d : var3;
        if (!var7.isPrimitive()) {
            a.putObject(var8, this.e, var2);
        } else {
            if (var7 == int.class) {
                a.putInt(var8, this.e, ((Number)var2).intValue());
            } else if (var7 == long.class) {
                a.putLong(var8, this.e, ((Number)var2).longValue());
            } else if (var7 == boolean.class) {
                a.putBoolean(var8, this.e, (Boolean)var2);
            } else if (var7 == byte.class) {
                a.putByte(var8, this.e, ((Number)var2).byteValue());
            } else if (var7 == char.class) {
                a.putChar(var8, this.e, (Character)var2);
            } else if (var7 == short.class) {
                a.putShort(var8, this.e, ((Number)var2).shortValue());
            } else if (var7 == float.class) {
                a.putFloat(var8, this.e, ((Number)var2).floatValue());
            } else if (var7 == double.class) {
                a.putDouble(var8, this.e, ((Number)var2).doubleValue());
            }
        }
    }

    public FieldValue d_FieldValue(Object[] var1) {
        Object var4 = var1[0];
        if (var4 == null) {
            throw new IllegalArgumentException("target cannot be null for instance bind");
        } else if (this.c) {
            throw new IllegalStateException("Cannot bind static field to instance; access static field via get()/set() or FieldAccessor.of(Class,..)");
        } else {
            return new FieldValue(this, var4);
        }
    }

    public static MethodValue a_MethodValue(Object[] var0) {
        Class var4 = (Class)var0[0];
        String var5 = (String)var0[1];
        Class var3 = (Class)var0[2];
        long var1 = (Long)var0[3];
        var1 = 81099604477280L ^ var1;
        long var6 = var1 ^ 135772341581672L;
        long var8 = var1 ^ 79727553084788L;
        long var10 = var1 ^ 59566843252523L;

        try {
            String var12 = ASMUtils.toFieldDescriptor(var3);
            String var13 = TransformerManager.getRemapper().getRemapper().reverse().map(var4.getName().replace('.', '/'));
            String var14 = TransformerManager.getRemapper().getRemapper().reverse().mapDesc(var12);
            String var15 = TransformerManager.getRemapper().getRemapper().mapFieldName(var13, var5, var14);
            FieldSpec var16 = ReflectionBase.b_FieldSpec(new Object[]{var4, var15, var12, var8});
            return new MethodValue(var16, var6);
        } catch (Throwable var17) {
            return new MethodValue(var4, var5, var3, var10);
        }
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }

    private static Exception a(Exception var0) {
        return var0;
    }
}
