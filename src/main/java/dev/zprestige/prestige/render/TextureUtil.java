package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextureUtil {
    private static final Logger a = LoggerFactory.getLogger(TextureUtil.class);
    private static final boolean b = GL.getCapabilities().GL_EXT_texture_filter_anisotropic;
    private static final float c;

    public static FontRenderer a(Object[] var0) {
        String var1 = (String)var0[0];
        long var2 = (Long)var0[1];
        var2 = 115330956396883L ^ var2;
        long var4 = var2 ^ 39631506551911L;

        try {
            Object[] var10006 = new Object[]{null, null, null, null, var4};
            var10006[3] = true;
            var10006[2] = 33071;
            var10006[1] = 9729;
            var10006[0] = var1;
            return c(var10006);
        } catch (IOException var7) {
            throw new RuntimeException(var7);
        }
    }

    public static FontRenderer c(Object[] var0) throws IOException {
        String var2 = (String)var0[0];
        int var1 = (Integer)var0[1];
        int var5 = (Integer)var0[2];
        boolean var6 = (Boolean)var0[3];
        long var3 = (Long)var0[4];
        var3 = 115330956396883L ^ var3;
        long var7 = var3 ^ 6885571309737L;
        long var9 = var3 ^ 61797235049706L;
        long var11 = var3 ^ 46579528746624L;
        long var13 = var3 ^ 117589611311477L;
        long var15 = var3 ^ 107380155499117L;
        long var17 = var3 ^ 17216244491597L;
        ByteBuffer var20 = a_ByteBuffer_(new Object[]{var2, var9});
        Event.b();

        FontRenderer var39;
        try (MemoryStack var21 = MemoryStack.stackPush()) {
            IntBuffer var22 = var21.mallocInt(1);
            IntBuffer var23 = var21.mallocInt(1);
            IntBuffer var24 = var21.mallocInt(1);
            ByteBuffer var25 = STBImage.stbi_load_from_memory(var20, var22, var23, var24, 4);
            if (var25 == null) {
                throw new IOException("Failed to load image: " + STBImage.stbi_failure_reason());
            }

            try {
                int var26 = var22.get(0);
                int var27 = var23.get(0);
                int var28 = var26 * var27 * 4;
                if (var25.remaining() < var28) {
                    throw new IOException("Image buffer too small: " + var25.remaining() + " bytes, expected " + var28);
                }

                Object[] var10003 = new Object[]{null, var15};
                var10003[0] = 3553;
                FontRenderer var29 = FontRenderer.a(var10003);
                Object[] var10006 = new Object[]{null, null, null, var7};
                var10006[2] = var27;
                var10006[1] = var26;
                var10006[0] = 32856;
                var29.c(var10006);
                GL15.glBindBuffer(35052, 0);
                GL11.glPixelStorei(3314, 0);
                GL11.glPixelStorei(3316, 0);
                GL11.glPixelStorei(3315, 0);
                GL11.glPixelStorei(3317, 4);
                GL11.glBindTexture(3553, var29.a);
                Object[] var10010 = new Object[]{null, null, null, null, null, null, var25, var11};
                var10010[5] = 5121;
                var10010[4] = 6408;
                var10010[3] = var27;
                var10010[2] = var26;
                var10010[1] = 0;
                var10010[0] = 0;
                var29.d(var10010);
                if (var6) {
                    GL30.glGenerateMipmap(3553);
                    if (var1 == 9729) {
                        GL11.glTexParameteri(3553, 10241, 9987);
                        GL11.glTexParameteri(3553, 10240, 9729);
                    } else {
                        Object[] var10004 = new Object[]{null, var13};
                        var10004[0] = var1;
                        var29.b_void_______(var10004);
                    }
                } else {
                    Object[] var40 = new Object[]{null, var13};
                    var40[0] = var1;
                    var29.b_void_______(var40);
                }

                Object[] var41 = new Object[]{null, var17};
                var41[0] = var5;
                var29.e(var41);
                if (b && var6) {
                    float var30 = Math.min(64.0F, c);
                    GL11.glTexParameterf(3553, 34046, var30);
                }

                GL11.glBindTexture(3553, 0);
                var39 = var29;
            } finally {
                STBImage.stbi_image_free(var25);
            }
        }

        return var39;
    }

    private static ByteBuffer a_ByteBuffer_(Object[] var0) throws IOException {
        String var1 = (String)var0[0];
        String var5 = var1.startsWith("/") ? var1.substring(1) : var1;

        ByteBuffer var9;
        try (InputStream var6 = TextureUtil.class.getClassLoader().getResourceAsStream(var5)) {
            if (var6 == null) {
                throw new IOException("Resource not found: " + var5);
            }

            byte[] var7 = var6.readAllBytes();
            ByteBuffer var8 = ByteBuffer.allocateDirect(var7.length);
            var8.put(var7);
            var8.flip();
            var9 = var8;
        }

        return var9;
    }

    static {
        if (b) {
            c = GL11.glGetFloat(34047);
            a.info("Anisotropic filtering supported: Max {}x", c);
        } else {
            c = 1.0F;
            a.info("Anisotropic filtering not supported");
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
