package dev.zprestige.prestige.config;

import dev.zprestige.prestige.client.Prestige;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackInfo;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.ResourcePack.ResultConsumer;
import net.minecraft.resource.metadata.ResourceMetadataSerializer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ConfigUtil implements ResourcePack {

    @Nullable
    @Override
    public InputSupplier openRoot(String[] segments) {
        return null;
    }

    @Nullable
    @Override
    public InputSupplier open(ResourceType type, Identifier id) {
        return null;
    }

    @Override
    public void findResources(ResourceType type, String namespace, String prefix, ResultConsumer consumer) {
    }

    @Override
    public Set getNamespaces(ResourceType type) {
        return null;
    }

    @Nullable
    @Override
    public Object parseMetadata(ResourceMetadataSerializer metadataSerializer) throws IOException {
        return null;
    }

    @Override
    public String getId() {
        return "Prestige";
    }

    @Override
    public Optional getKnownPackInfo() {
        return ResourcePack.super.getKnownPackInfo();
    }

    @Override
    public ResourcePackInfo getInfo() {
        return null;
    }

    @Override
    public void close() {
    }

    public static Resource a(Object[] var0) {
        Identifier var1 = (Identifier)var0[0];
        return new Resource(new ConfigUtil(), () -> Prestige.class.getClassLoader().getResourceAsStream("assets/prestige/" + var1.getPath()));
    }

    public static Resource b(Object[] var0) {
        String var1 = (String)var0[0];
        return new Resource(new ConfigUtil(), () -> Prestige.class.getClassLoader().getResourceAsStream(var1));
    }

    public static InputSupplier a_class_7367(Object[] var0) {
        String var1 = (String)var0[0];
        return () -> Prestige.class.getClassLoader().getResourceAsStream(var1);
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
