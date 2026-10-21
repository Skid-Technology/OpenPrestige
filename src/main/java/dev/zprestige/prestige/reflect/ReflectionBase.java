package dev.zprestige.prestige.reflect;

import dev.zprestige.prestige.event.Event;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import sun.misc.Unsafe;

public abstract class ReflectionBase {
    public static final Unsafe a;
    private static final Map b = new ConcurrentHashMap();
    private static final Map c = new ConcurrentHashMap();

    public static AccessibleObject a_AccessibleObject(Object[] var0) {
        AccessibleObject var3 = (AccessibleObject)var0[0];
        if (a == null) {
            var3.setAccessible(true);
            return var3;
        } else {
            try {
                a.putBoolean(var3, 12L, true);
                return var3;
            } catch (Throwable var5) {
                try {
                    var3.setAccessible(true);
                } catch (Throwable var4) {
                }

                return var3;
            }
        }
    }

    public static void a_void____________(Object[] var0) {
        Field var3 = (Field)var0[0];

        try {
            Field var4 = Field.class.getDeclaredField("modifiers");
            var4.setAccessible(true);
            int var5 = var3.getModifiers();
            if ((var5 & 16) != 0) {
                var4.setInt(var3, var5 & -17);
            }
        } catch (Throwable var6) {
        }
    }

    public static FieldSpec a_FieldSpec(Object[] var0) {
        Class var1 = (Class)var0[0];
        String var4 = (String)var0[1];
        long var2 = (Long)var0[2];
        var2 = 40376972710807L ^ var2;
        long var5 = var2 ^ 133172850120418L;
        long var7 = var2 ^ 131683977832592L;
        String var10 = var1.getName() + "#" + var4;
        Event.b();
        FieldSpec var11 = (FieldSpec)b.get(var10);
        if (var11 != null) {
            return var11;
        } else {
            try {
                Field var12 = var1.getDeclaredField(var4);
                a_AccessibleObject(new Object[]{var12, var5});
                a_void____________(new Object[]{var12, var7});
                boolean var13 = Modifier.isStatic(var12.getModifiers());
                VarHandle var14 = null;

                try {
                    Lookup var15 = MethodHandles.privateLookupIn(var1, MethodHandles.lookup());
                    if (var13) {
                        var14 = var15.findStaticVarHandle(var1, var4, var12.getType());
                    } else {
                        var14 = var15.findVarHandle(var1, var4, var12.getType());
                    }
                } catch (Throwable var22) {
                }

                if (a == null) {
                    var11 = new FieldSpec(var12, var13, null, -1L, var14);
                } else if (var13) {
                    Object var26 = a.staticFieldBase(var12);
                    long var16 = a.staticFieldOffset(var12);
                    var11 = new FieldSpec(var12, true, var26, var16, var14);
                } else {
                    long var27 = a.objectFieldOffset(var12);
                    var11 = new FieldSpec(var12, false, null, var27, var14);
                }

                b.put(var10, var11);
                return var11;
            } catch (NoSuchFieldException var23) {
                throw new RuntimeException("Field not found: " + var1 + "." + var4, var23);
            }
        }
    }

    public static FieldSpec b_FieldSpec(Object[] var0) {
        Class var5 = (Class)var0[0];
        String var1 = (String)var0[1];
        String var2 = (String)var0[2];
        long var3 = (Long)var0[3];
        var3 = 40376972710807L ^ var3;
        long var6 = var3 ^ 21823637373205L;
        long var8 = var3 ^ 31901388848999L;
        long var10 = var3 ^ 66489829358878L;
        String var12 = var5.getName() + "#" + var1 + ":" + var2;
        FieldSpec var13 = (FieldSpec)b.get(var12);
        if (var13 != null) {
            return var13;
        } else {
            Class var14 = var5;

            Class var15;
            try {
                var15 = a_Class_(new Object[]{var2, var5.getClassLoader(), var10});
            } catch (ClassNotFoundException var31) {
                throw new RuntimeException("Failed to parse field descriptor: " + var2, var31);
            }

            while (var14 != null) {
                try {
                    Field var16 = var14.getDeclaredField(var1);
                    if (!var16.getType().equals(var15)) {
                        String var51 = String.valueOf(var14);
                        String var10004 = String.valueOf(var15);
                        String var22 = String.valueOf(var16.getType());
                        String var23 = var10004;
                        String var25 = var51;
                        throw new RuntimeException("Field type mismatch for " + var25 + "." + var1 + ": expected " + var23 + " but found " + var22);
                    }

                    a_AccessibleObject(new Object[]{var16, var6});
                    a_void____________(new Object[]{var16, var8});
                    boolean var17 = Modifier.isStatic(var16.getModifiers());
                    VarHandle var18 = null;

                    try {
                        Lookup var19 = MethodHandles.privateLookupIn(var14, MethodHandles.lookup());
                        if (var17) {
                            var18 = var19.findStaticVarHandle(var14, var1, var16.getType());
                        } else {
                            var18 = var19.findVarHandle(var14, var1, var16.getType());
                        }
                    } catch (Throwable var30) {
                    }

                    if (a == null) {
                        var13 = new FieldSpec(var16, var17, null, -1L, var18);
                    } else if (var17) {
                        Object var35 = a.staticFieldBase(var16);
                        long var20 = a.staticFieldOffset(var16);
                        var13 = new FieldSpec(var16, true, var35, var20, var18);
                    } else {
                        long var36 = a.objectFieldOffset(var16);
                        var13 = new FieldSpec(var16, false, null, var36, var18);
                    }

                    b.put(var12, var13);
                    return var13;
                } catch (NoSuchFieldException var32) {
                    var14 = var14.getSuperclass();
                }
            }

            throw new RuntimeException("Field not found: " + var5 + "." + var1);
        }
    }

    public static MethodSpec a_MethodSpec(Object[] var0) {
        Class var1 = (Class)var0[0];
        String var3 = (String)var0[1];
        Class[] var2 = (Class[])var0[2];
        long var4 = (Long)var0[3];
        var4 = 40376972710807L ^ var4;
        long var6 = var4 ^ 47334713393340L;
        long var8 = var4 ^ 10958948693601L;
        String var10 = var1.getName() + "#" + var3 + ":" + a(new Object[]{var2, var8});
        MethodSpec var11 = (MethodSpec)c.get(var10);
        if (var11 != null) {
            return var11;
        } else {
            for (Class var12 = var1; var12 != null; var12 = var12.getSuperclass()) {
                try {
                    Method var36 = var12.getDeclaredMethod(var3, var2);
                    a_AccessibleObject(new Object[]{var36, var6});
                    Lookup var37 = MethodHandles.privateLookupIn(var12, MethodHandles.lookup());
                    MethodHandle var38 = var37.unreflect(var36);
                    boolean var39 = Modifier.isStatic(var36.getModifiers());
                    var11 = new MethodSpec(var36, var38, var39);
                    c.put(var10, var11);
                    return var11;
                } catch (IllegalAccessException | NoSuchMethodException var32) {
                    for (Method var16 : var12.getDeclaredMethods()) {
                        if (var16.getName().equals(var3) && var16.getParameterCount() == var2.length) {
                            boolean var17 = true;
                            Class[] var18 = var16.getParameterTypes();

                            for (int var19 = 0; var19 < var18.length; var19++) {
                                if (!var18[var19].isAssignableFrom(var2[var19])) {
                                    var17 = false;
                                    break;
                                }
                            }

                            if (var17) {
                                try {
                                    a_AccessibleObject(new Object[]{var16, var6});
                                    Lookup var40 = MethodHandles.privateLookupIn(var12, MethodHandles.lookup());
                                    MethodHandle var20 = var40.unreflect(var16);
                                    boolean var21 = Modifier.isStatic(var16.getModifiers());
                                    var11 = new MethodSpec(var16, var20, var21);
                                    c.put(var10, var11);
                                    return var11;
                                } catch (Throwable var31) {
                                }
                            }
                        }
                    }
                }
            }

            String var10002 = String.valueOf(var1);
            String var22 = Arrays.toString((Object[])var2);
            String var24 = var10002;
            throw new RuntimeException("Method not found: " + var24 + "." + var3 + var22);
        }
    }

    private static String a(Object[] var0) {
        Class[] var1 = (Class[])var0[0];
        if (var1 != null && var1.length != 0) {
            StringBuilder var4 = new StringBuilder();

            for (Class var8 : var1) {
                var4.append(var8 == null ? "null" : var8.getName()).append(',');
            }

            return var4.toString();
        } else {
            return "()";
        }
    }

    public static Class a_Class_(Object[] var0) throws ClassNotFoundException {
        String var1 = (String)var0[0];
        long var2 = (Long)var0[2];
        var2 = 40376972710807L ^ var2;
        long var5 = var2 ^ 45045835094261L;
        long var7 = var2 ^ 134547145911763L;
        if (var1 == null) {
            throw new IllegalArgumentException("Descriptor required");
        } else {
            int var9 = 0;

            int var10;
            for (var10 = 0; var9 < var1.length() && var1.charAt(var9) == '['; var9++) {
                var10++;
            }

            char var11 = var1.charAt(var9);
            if (var11 == 'L') {
                int var17 = var1.indexOf(59, var9);
                String var13 = var1.substring(var9 + 1, var17);
                String var14 = var13.replace('/', '.');
                Class var15 = Class.forName(var14);
                Object[] var18 = new Object[]{null, null, var7};
                var18[1] = var10;
                var18[0] = var15;
                return b(var18);
            } else {
                Object[] var10003 = new Object[]{null, var5};
                var10003[0] = Integer.valueOf(var11);
                Class var12 = c(var10003);
                Object[] var10004 = new Object[]{null, null, var7};
                var10004[1] = var10;
                var10004[0] = var12;
                return b(var10004);
            }
        }
    }

    private static Class b(Object[] var0) {
        Class var2 = (Class)var0[0];
        int var1 = (Integer)var0[1];
        if (var1 == 0) {
            return var2;
        } else {
            Class var5 = var2;

            for (int var6 = 0; var6 < var1; var6++) {
                var5 = Array.newInstance(var5, 0).getClass();
            }

            return var5;
        }
    }

    private static Class c(Object[] var0) {
        int var1 = (Integer)var0[0];
        switch (var1) {
            case 66:
                return byte.class;
            case 67:
                return char.class;
            case 68:
                return double.class;
            case 69:
            case 71:
            case 72:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 84:
            case 85:
            case 87:
            case 88:
            case 89:
            default:
                throw new IllegalArgumentException("Unknown primitive descriptor: " + var1);
            case 70:
                return float.class;
            case 73:
                return int.class;
            case 74:
                return long.class;
            case 83:
                return short.class;
            case 86:
                return void.class;
            case 90:
                return boolean.class;
        }
    }

    static {
        Unsafe var33 = null;

        try {
            Field var34 = Unsafe.class.getDeclaredField("theUnsafe");
            var34.setAccessible(true);
            var33 = (Unsafe)var34.get(null);
        } catch (Throwable var35) {
            var35.printStackTrace(System.err);
        }

        a = var33;
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

    private static Object[] b() {
        return new Object[0];
    }

    private static Object[] a(Object var0, Object var1, Object var2) {
        return new Object[]{var0, var1, var2};
    }

    private static Object[] a(Object var0, Object var1) {
        return new Object[]{var0, var1};
    }

    private static Object[] a(Object var0) {
        return new Object[]{var0};
    }

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
