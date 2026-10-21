package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;

public class VertexBufferProvider implements VertexConsumerProvider {
    private final VertexConsumerProvider a;

    public VertexBufferProvider(VertexConsumerProvider var1) {
        this.a = var1;
    }

    @Override
    public VertexConsumer getBuffer(RenderLayer layer) {
        Event.b();
        VertexConsumer var7 = this.a.getBuffer(layer);
        if (!layer.isOutline() && layer.getAffectedOutline().isEmpty()) {
            return var7;
        } else {
            Optional var8 = RenderLayerUtil.a(new Object[]{layer, 48751301194998L});
            return (VertexConsumer)var8.<VertexConsumer>map(lpL28_0 -> {
                int var10001 = ((java.lang.Integer)lpL28_0);
                Object[] var10004 = new Object[]{null, null, 50226393134120L};
                var10004[1] = var10001;
                var10004[0] = var7;
                return RenderLayerUtil.a_class_4588(var10004);
            }).orElse(var7);
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
}
