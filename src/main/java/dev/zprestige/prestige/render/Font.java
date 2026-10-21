package dev.zprestige.prestige.render;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.zprestige.prestige.config.ConfigHelper;
import dev.zprestige.prestige.config.ConfigHelper2;
import dev.zprestige.prestige.config.ConfigHelper3;
import dev.zprestige.prestige.data.FontFloatData;
import dev.zprestige.prestige.data.IntBoolData2;
import dev.zprestige.prestige.data.VectorData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.math.MathUtil4;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import net.minecraft.client.texture.NativeImage;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Font {
    private static final int[][] a = new int[32][3];
    private float b;
    private int c;
    private int d;
    private float e;
    private float f;
    private float g;
    private final Int2ObjectOpenHashMap h;
    private final Map i;
    private ConfigHelper j;
    private FontRenderer k;
    private BiConsumer l;
    private Function m;
    private DrawMode n;
    private VectorData o;

    public Font(String var1, long var2) throws IOException {
        super();
        var2 = 0L;
        var2 = 71232715412199L ^ var2;
        long var4 = var2 ^ 35896062585652L;
        long var6 = var2 ^ 527467001456L;
        long var8 = var2 ^ 50316809022329L;
        long var10 = var2 ^ 101922796707186L;
        this.e = 8.0F;
        Event.b();
        this.h = new Int2ObjectOpenHashMap();
        Object[] var10005 = new Object[]{null, var4};
        var10005[0] = 256;
        this.i = MathUtil4.a(var10005);
        this.m = lpL39_0 -> {
            RenderState.a(new Object[]{35451451245727L});
            return (RenderHelper30)RenderState.a.get();
        };
        ConfigHelper3 var13 = ConfigHelper3.a(new Object[]{"assets/prestige/font/" + var1 + ".json", var6});
        ConfigHelper3 var14 = ConfigHelper3.a(new Object[]{"assets/prestige/font/" + var1 + ".png", var6});

        try (
            InputStream var15 = var13.a_InputStream(new Object[]{var10});
            InputStream var16 = var14.a_InputStream(new Object[]{var10});
        ) {
            this.a(new Object[]{new InputStreamReader(var15), var16, var8});
        }
    }

    private void a(Object[] var1) throws IOException {
        Reader var5 = (Reader)var1[0];
        InputStream var4 = (InputStream)var1[1];
        long var2 = (Long)var1[2];
        var2 = 71232715412199L ^ var2;
        long var6 = var2 ^ 20704828053529L;
        long var8 = var2 ^ 69804292923111L;
        long var10 = var2 ^ 69040519261744L;
        long var12 = var2 ^ 138976230971845L;
        long var14 = var2 ^ 128913441663709L;
        long var16 = var2 ^ 67621779615786L;
        long var18 = var2 ^ 29961217953277L;
        Event.b();
        JsonObject var21 = JsonParser.parseReader(var5).getAsJsonObject();

        {
            label49: {
                {
                    if ("msdf".equals(var21.getAsJsonObject("atlas").get("type").getAsString())) {
                        break label49;
                    }
                }

                throw new RuntimeException("not msdf atlas");
            }
        }

        this.c = var21.getAsJsonObject("atlas").get("width").getAsInt();
        this.d = var21.getAsJsonObject("atlas").get("height").getAsInt();
        this.b = var21.getAsJsonObject("atlas").get("distanceRange").getAsFloat();
        this.j = ConfigHelper.a(new Object[]{var21.getAsJsonObject("metrics"), var8});
        this.f = 1.0F / this.c;
        this.g = 1.0F / this.d;
        NativeImage var22 = NativeImage.read(var4);
        Object[] var10004 = new Object[]{null, var14};
        var10004[0] = 3553;
        this.k = FontRenderer.a(var10004);
        FontRenderer var10000 = this.k;
        int var10002 = var22.getWidth();
        int var10003 = var22.getHeight();
        Object[] var10006 = new Object[]{null, null, null, var6};
        var10006[2] = var10003;
        var10006[1] = var10002;
        var10006[0] = 32856;
        var10000.c(var10006);
        int var23 = var22.getWidth();
        int var24 = var22.getHeight();
        ByteBuffer var25 = BufferUtils.createByteBuffer(var23 * var24 * 4);
        var25.order(ByteOrder.LITTLE_ENDIAN);

        for (int var26 = 0; var26 < var24; var26++) {
            int var27 = 0;

            while (var27 < var23) {
                int var28 = var22.getColorArgb(var27, var26);

                {
                    var25.putInt(var28);
                    var27++;
                }
            }
        }

        var25.flip();
        GL11.glPixelStorei(3317, 1);
        GL11.glPixelStorei(3314, 0);
        GL11.glPixelStorei(3316, 0);
        GL11.glPixelStorei(3315, 0);
        var10000 = this.k;
        Object[] var10010 = new Object[]{null, null, null, null, null, null, var25, var10};
        var10010[5] = 5121;
        var10010[4] = 6408;
        var10010[3] = var24;
        var10010[2] = var23;
        var10010[1] = 0;
        var10010[0] = 0;
        var10000.d(var10010);
        GL11.glPixelStorei(3317, 4);
        var10000 = this.k;
        var10004 = new Object[]{null, var12};
        var10004[0] = 9729;
        var10000.b_void_______(var10004);
        var10000 = this.k;
        var10004 = new Object[]{null, var18};
        var10004[0] = 33071;
        var10000.e(var10004);
        var22.close();

        for (JsonElement var35 : var21.getAsJsonArray("glyphs")) {
            JsonObject var36 = var35.getAsJsonObject();
            ConfigHelper2 var29 = ConfigHelper2.a(new Object[]{var36, var16});
            this.h.put(var29.a_int__(new Object[0]), var29);
        }
    }

    public void b(Object[] var1) {
        BiConsumer var2 = (BiConsumer)var1[0];
        this.l = var2;
    }

    public void c(Object[] var1) {
        Function var2 = (Function)var1[0];
        this.m = var2;
    }

    public void h(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        String var6 = (String)var1[1];
        float var9 = (Float)var1[2];
        float var7 = (Float)var1[3];
        float var8 = (Float)var1[4];
        int var5 = (Integer)var1[5];
        long var3 = (Long)var1[6];
        var3 = 71232715412199L ^ var3;
        long var10 = var3 ^ 71795448265388L;
        long var12 = var3 ^ 53398400318661L;
        RenderHelper30 var14 = (RenderHelper30)this.m.apply(this);
        VectorData var15 = this.a_VectorData(new Object[]{var10});
        Matrix4f var16 = new Matrix4f(var2);
        float var17 = this.j.b(new Object[0]) * var8;
        float[] var18 = new float[]{var9};
        this.x(new Object[]{var6, (RenderContract)(lpL38_0, lpL38_1) -> {
            float var10002 = var18[0];
            float var10008 = var18[0];
            Object[] var10015 = new Object[]{null, null, null, null, null, null, null, null, null, 53616589733604L};
            var10015[8] = var5;
            var10015[7] = var17;
            var10015[6] = var8;
            var10015[5] = var7;
            var10015[4] = var10008;
            var10015[3] = ((dev.zprestige.prestige.config.ConfigHelper2)lpL38_1);
            var10015[2] = var16;
            var10015[1] = var15;
            var10015[0] = var14;
            var18[0] = var10002 + this.g(var10015);
            return true;
        }, var12});
    }

    public void n(Object[] var1) {
        Matrix4f var9 = (Matrix4f)var1[0];
        String var6 = (String)var1[1];
        float var3 = (Float)var1[2];
        float var8 = (Float)var1[3];
        float var7 = (Float)var1[4];
        Color var2 = (Color)var1[5];
        long var4 = (Long)var1[6];
        var4 = 71232715412199L ^ var4;
        long var10 = var4 ^ 12858160589271L;
        int var12 = var2.getRGB();
        int var13 = (var12 >>> 24 & 0xFF) * 140 >> 8;
        int var14 = var13 << 24;
        float var10003 = var3 + 0.5F;
        float var10004 = var8 + 0.5F;
        Object[] var10009 = new Object[]{null, null, null, null, null, null, var10};
        var10009[5] = var14;
        var10009[4] = var7;
        var10009[3] = var10004;
        var10009[2] = var10003;
        var10009[1] = var6;
        var10009[0] = var9;
        this.h(var10009);
        var10009 = new Object[]{null, null, null, null, null, null, var10};
        var10009[5] = var12;
        var10009[4] = var7;
        var10009[3] = var8;
        var10009[2] = var3;
        var10009[1] = var6;
        var10009[0] = var9;
        this.h(var10009);
    }

    public void v(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        String var7 = (String)var1[1];
        float var4 = (Float)var1[2];
        float var8 = (Float)var1[3];
        Color var3 = (Color)var1[4];
        long var5 = (Long)var1[5];
        var5 = 71232715412199L ^ var5;
        long var9 = var5 ^ 133517182186723L;
        float var10005 = this.e;
        Object[] var10009 = new Object[]{null, null, null, null, null, var3, var9};
        var10009[4] = var10005;
        var10009[3] = var8;
        var10009[2] = var4;
        var10009[1] = var7;
        var10009[0] = var2;
        this.n(var10009);
    }

    public void w(Object[] var1) {
        Matrix4f var3 = (Matrix4f)var1[0];
        String var4 = (String)var1[1];
        float var6 = (Float)var1[2];
        float var7 = (Float)var1[3];
        float var5 = (Float)var1[4];
        Color var2 = (Color)var1[5];
        long var8 = (Long)var1[6];
        var8 = 71232715412199L ^ var8;
        long var10 = var8 ^ 129037059992631L;
        float var10005 = this.e * var5;
        Object[] var10009 = new Object[]{null, null, null, null, null, var2, var10};
        var10009[4] = var10005;
        var10009[3] = var7;
        var10009[2] = var6;
        var10009[1] = var4;
        var10009[0] = var3;
        this.n(var10009);
    }

    public float a_float______________(Object[] var1) {
        String var4 = (String)var1[0];
        long var2 = (Long)var1[1];
        var2 = 71232715412199L ^ var2;
        long var5 = var2 ^ 10224987624918L;
        float var10002 = this.e;
        Object[] var10005 = new Object[]{null, null, var5};
        var10005[1] = var10002;
        var10005[0] = var4;
        return this.b_float____________________(var10005);
    }

    public float b_float____________________(Object[] var1) {
        String var3 = (String)var1[0];
        float var2 = (Float)var1[1];
        long var4 = (Long)var1[2];
        var4 = 71232715412199L ^ var4;
        long var6 = var4 ^ 1639154567475L;
        if (var3.isEmpty()) {
            return 0.0F;
        } else {
            Float var9 = (Float)this.i.get(var3);
            float var10;
            if (var9 != null) {
                var10 = var9;
            } else {
                float[] var11 = new float[]{0.0F};
                this.x(new Object[]{var3, (RenderContract)(lpL37_0, lpL37_1) -> {
                    var11[0] += ((dev.zprestige.prestige.config.ConfigHelper2)lpL37_1).a_float__(new Object[0]);
                    return true;
                }, var6});
                var10 = var11[0];
                this.i.put(var3, var10);
            }

            return var2 * var10;
        }
    }

    public float c_float___(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 71232715412199L ^ var2;
        long var4 = var2 ^ 30350796624859L;
        float var10001 = this.e;
        Object[] var10004 = new Object[]{null, var4};
        var10004[0] = var10001;
        return this.d(var10004);
    }

    public float d(Object[] var1) {
        float var4 = (Float)var1[0];
        return this.j.a_float_(new Object[0]) * var4;
    }

    private void x(Object[] var1) {
        String var2 = (String)var1[0];
        RenderContract var5 = (RenderContract)var1[1];
        Event.b();
        int var7 = var2.length();
        int var8 = 0;

        while (var8 < var7) {
            int var9 = var2.codePointAt(var8);
            if (var9 == 167 && var8 + 1 < var7) {
                var8 += 2;
            } else {
                var8 += Character.charCount(var9);
                ConfigHelper2 var10 = (ConfigHelper2)this.h.get(var9);
                if (var10 != null && !var5.a(var9, var10)) {
                    return;
                }
            }
        }
    }

    public float e(Object[] var1) {
        return this.e;
    }

    public void y(Object[] var1) {
        float var2 = (Float)var1[0];
        this.e = var2;
    }

    private float f(Object[] var1) {
        RenderHelper30 var5 = (RenderHelper30)var1[0];
        VectorData var8 = (VectorData)var1[1];
        ConfigHelper2 var7 = (ConfigHelper2)var1[2];
        float var4 = (Float)var1[3];
        float var6 = (Float)var1[4];
        float var10 = (Float)var1[5];
        float var11 = (Float)var1[6];
        int var9 = (Integer)var1[7];
        long var2 = (Long)var1[8];
        var2 = 71232715412199L ^ var2;
        long var12 = var2 ^ 78559501613008L;
        long var14 = var2 ^ 23074457147922L;
        if (var7.d(new Object[0]) - var7.b(new Object[0]) != 0.0F) {
            float var17 = var4 + var7.b(new Object[0]) * var10;
            float var18 = var4 + var7.d(new Object[0]) * var10;
            float var19 = var6 + var11 - var7.e(new Object[0]) * var10;
            float var20 = var6 + var11 - var7.c(new Object[0]) * var10;
            float var21 = var7.f(new Object[0]) * this.f;
            float var22 = var7.h(new Object[0]) * this.f;
            float var23 = 1.0F - var7.i(new Object[0]) * this.g;
            float var24 = 1.0F - var7.g(new Object[0]) * this.g;
            var5.b(new Object[]{new RenderHelper6(var8, var17, var19, var18, var20, var9, var21, var23, var22, var24, null, var14), var12});
        }

        return var10 * var7.a_float__(new Object[0]);
    }

    private float g(Object[] var1) {
        RenderHelper30 var5 = (RenderHelper30)var1[0];
        VectorData var4 = (VectorData)var1[1];
        Matrix4f var12 = (Matrix4f)var1[2];
        ConfigHelper2 var7 = (ConfigHelper2)var1[3];
        float var9 = (Float)var1[4];
        float var6 = (Float)var1[5];
        float var3 = (Float)var1[6];
        float var2 = (Float)var1[7];
        int var8 = (Integer)var1[8];
        long var10 = (Long)var1[9];
        var10 = 71232715412199L ^ var10;
        long var13 = var10 ^ 115326712177119L;
        long var15 = var10 ^ 65338939788317L;
        if (var7.d(new Object[0]) - var7.b(new Object[0]) != 0.0F) {
            float var18 = var9 + var7.b(new Object[0]) * var3;
            float var19 = var9 + var7.d(new Object[0]) * var3;
            float var20 = var6 + var2 - var7.e(new Object[0]) * var3;
            float var21 = var6 + var2 - var7.c(new Object[0]) * var3;
            float var22 = var7.f(new Object[0]) * this.f;
            float var23 = var7.h(new Object[0]) * this.f;
            float var24 = 1.0F - var7.i(new Object[0]) * this.g;
            float var25 = 1.0F - var7.g(new Object[0]) * this.g;
            var5.b(new Object[]{new RenderHelper6(var4, var18, var20, var19, var21, var8, var22, var24, var23, var25, var12, var15), var13});
        }

        return var3 * var7.a_float__(new Object[0]);
    }

    public VectorData a_VectorData(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 71232715412199L ^ var2;
        long var4 = var2 ^ 118999110662734L;
        if (this.o == null) {
            this.o = new VectorData(null, this.a_DrawMode(new Object[]{var4}));
        }

        return this.o;
    }

    public DrawMode a_DrawMode(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 71232715412199L ^ var2;
        long var4 = var2 ^ 74579176709528L;
        if (this.n == null) {
            VertexFormat var10003 = VertexAttribute.c;
            Uniform[] var10006 = new Uniform[]{Uniform.a, Uniform.a_ShaderData(new Object[]{RenderHelper22.e}), null, null, null, null};
            Object[] var10013 = new Object[]{null, null, true};
            var10013[1] = false;
            var10013[0] = 2884;
            var10006[2] = Uniform.b_IntBoolData2(var10013);
            Object[] var10012 = new Object[]{null, true};
            var10012[0] = true;
            var10006[3] = Uniform.a_BoolData2(var10012);
            var10006[4] = new IntBoolData2(2929, false);
            var10006[5] = new FontFloatData(this.k, this.b / this.c, this.b / this.d);
            this.n = new DrawMode(var10003, 4, false, var10006, var4);
            if (this.l != null) {
                this.l.accept(this, this.n);
            }
        }

        return this.n;
    }

    public void a() {
        if (this.k != null) {
            this.k.close();
        }
    }

    static {
        for (int var22 = 0; var22 < 32; var22++) {
            int var23 = (var22 >> 3 & 1) * 85;
            int var24 = (var22 >> 2 & 1) * 170 + var23;
            int var25 = (var22 >> 1 & 1) * 170 + var23;
            int var26 = (var22 & 1) * 170 + var23;
            if (var22 == 6) {
                var24 += 85;
            }

            if (var22 >= 16) {
                var24 /= 4;
                var25 /= 4;
                var26 /= 4;
            }

            a[var22][0] = var24;
            a[var22][1] = var25;
            a[var22][2] = var26;
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
