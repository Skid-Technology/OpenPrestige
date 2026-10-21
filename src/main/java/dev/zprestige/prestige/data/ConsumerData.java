package dev.zprestige.prestige.data;

import dev.zprestige.prestige.world.WorldContract;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.render.VertexConsumer;

public record ConsumerData(WorldContract bp) implements VertexConsumer {
    @Override
    public VertexConsumer vertex(float x, float y, float z) {
        WorldContract var10000 = this.bp;
        Object[] var10006 = new Object[]{null, null, null, 65484385065975L};
        var10006[2] = z;
        var10006[1] = y;
        var10006[0] = x;
        var10000.c_WorldContract(var10006);
        return this;
    }

    @Override
    public VertexConsumer color(int red, int green, int blue, int alpha) {
        WorldContract var10000 = this.bp;
        Object[] var10004 = new Object[]{null, 106573078251021L};
        var10004[0] = -16776961;
        var10000.e(var10004);
        return this;
    }

    @Override
    public VertexConsumer color(int argb) {
        WorldContract var10000 = this.bp;
        Object[] var10004 = new Object[]{null, 106573078251021L};
        var10004[0] = -16776961;
        var10000.e(var10004);
        return this;
    }

    @Override
    public VertexConsumer lineWidth(float width) {
        return null;
    }

    @Override
    public VertexConsumer texture(float u, float v) {
        this.bp.b_WorldContract(new Object[]{new float[]{u, v}, 118034685045795L});
        return this;
    }

    @Override
    public VertexConsumer overlay(int u, int v) {
        return this;
    }

    @Override
    public VertexConsumer light(int u, int v) {
        return this;
    }

    @Override
    public VertexConsumer normal(float x, float y, float z) {
        return this;
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
