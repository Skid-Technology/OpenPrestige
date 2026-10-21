package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.ConsumerData;
import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumers;
import net.minecraft.client.render.RenderSetup.Texture;
import net.minecraft.client.texture.GlTextureView;

public class RenderLayerUtil {
    public static Optional a(Object[] var0) {
        RenderLayer var1 = (RenderLayer)var0[0];
        long var2 = (Long)var0[1];
        var2 = 50887277444900L ^ var2;
        long var4 = var2 ^ 21565215403969L;
        Event.b();
        RenderSetup var7 = new RenderHelper41(var1).a(new Object[]{var4});
        if (var7 == null) {
            return Optional.empty();
        } else {
            Map var8 = var7.resolveTextures();
            ArrayList var9 = new ArrayList();

            for (Entry var11 : (Iterable<Entry>)(var8.entrySet())) {
                if (((Texture)var11.getValue()).textureView() instanceof GlTextureView var12) {
                    var9.add(var12.texture().getGlId());
                }
            }

            return var9.isEmpty() ? Optional.empty() : Optional.of((Integer)var9.get(0));
        }
    }

    public static VertexConsumer a_class_4588(Object[] var0) {
        VertexConsumer var3 = (VertexConsumer)var0[0];
        int var4 = (Integer)var0[1];
        long var1 = (Long)var0[2];
        var1 = 50887277444900L ^ var1;
        long var5 = var1 ^ 75356188662889L;
        PostShader var10003 = ESPUtil.a_PostShader(new Object[0]);
        RenderHelper8 var10004 = RenderUtil.b;
        Object[] var10008 = new Object[]{null, null, var5};
        var10008[1] = var4;
        var10008[0] = var10004;
        return VertexConsumers.union(var3, new ConsumerData(var10003.a_TextRenderer(var10008)));
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
