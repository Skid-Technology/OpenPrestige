package dev.zprestige.prestige.network;

import dev.zprestige.prestige.reflect.FieldAccessor;
import dev.zprestige.prestige.reflect.MethodValue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;

public class NetworkHelper2 {
    private static final MethodValue a = FieldAccessor.a_MethodValue(new Object[]{PlayerInteractItemC2SPacket.class, "yaw", float.class, 0L});
    private static final MethodValue b = FieldAccessor.a_MethodValue(new Object[]{PlayerInteractItemC2SPacket.class, "pitch", float.class, 0L});
    private final PlayerInteractItemC2SPacket c;

    public NetworkHelper2(PlayerInteractItemC2SPacket var1) {
        this.c = var1;
    }

    public void a(Object[] var1) {
        float var4 = (Float)var1[0];
        long var2 = (Long)var1[1];
        var2 = 102937265826405L ^ var2;
        long var5 = var2 ^ 74219396763671L;
        MethodValue var10000 = a;
        PlayerInteractItemC2SPacket var10001 = this.c;
        Object[] var10005 = new Object[]{null, null, var5};
        var10005[1] = var4;
        var10005[0] = var10001;
        var10000.b(var10005);
    }

    public void b(Object[] var1) {
        float var4 = (Float)var1[0];
        long var2 = (Long)var1[1];
        var2 = 102937265826405L ^ var2;
        long var5 = var2 ^ 62337086345182L;
        MethodValue var10000 = b;
        PlayerInteractItemC2SPacket var10001 = this.c;
        Object[] var10005 = new Object[]{null, null, var5};
        var10005[1] = var4;
        var10005[0] = var10001;
        var10000.b(var10005);
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
}
