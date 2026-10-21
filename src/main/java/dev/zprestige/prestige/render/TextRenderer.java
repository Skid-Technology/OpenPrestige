package dev.zprestige.prestige.render;

import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.world.WorldContract;
import java.lang.ref.Cleaner.Cleanable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public class TextRenderer implements WorldContract, AutoCloseable {
    public VertexFormat a;
    private final GpuBuffer b;
    private final GpuBuffer c;
    private final Cleanable d;
    private ByteBuffer e;
    private ByteBuffer f;
    private final Vector4f g;
    private int h;

    public TextRenderer(VertexFormat var1, GpuBuffer var2, GpuBuffer var3, long var4) {
        super();
        var4 = 0L;
        var4 = 134641318060110L ^ var4;
        long var6 = var4 ^ 59548942414325L;
        this.g = new Vector4f();
        this.h = 0;
        this.a = var1;
        this.b = var2;
        this.c = var3;
        long var10003 = var2.a_AtomicLong(new Object[0]).get();
        Object[] var10007 = new Object[]{null, null, null, var6};
        var10007[2] = 10;
        var10007[1] = var10003;
        var10007[0] = 0L;
        this.e = var2.a_ByteBuffer(var10007);
        var10003 = var3.a_AtomicLong(new Object[0]).get();
        var10007 = new Object[]{null, null, null, var6};
        var10007[2] = 10;
        var10007[1] = var10003;
        var10007[0] = 0L;
        this.f = var3.a_ByteBuffer(var10007);
        this.d = RenderUtil.a.register(this, () -> RenderUtil.f(new Object[]{(Runnable)() -> {
            var2.d(new Object[]{9738575996231L});
            var3.d(new Object[]{9738575996231L});
        }, 31795785076293L}));
    }

    @Override
    public int c(Object[] var1) {
        return this.h;
    }

    @Override
    public void b(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        long var5 = var2 ^ 108363549350785L;
        long var7 = var2 ^ 69987509623684L;
        long var9 = var2 ^ 16701581658951L;
        if (this.e.remaining() < var4) {
            int var11 = this.e.limit();
            int var12 = Math.max(var11 << 1, var11 + (var4 - this.e.remaining()));
            int var13 = this.e.position();
            ByteBuffer var14 = MemoryUtil.memAlloc(var13);
            this.e.flip();
            MemoryUtil.memCopy(this.e, var14);
            this.b.d(new Object[]{var9});
            GpuBuffer var10000 = this.b;
            int var10001 = this.b.b(new Object[0]);
            long var10002 = var12;
            Object[] var10005 = new Object[]{null, null, var7};
            var10005[1] = var10002;
            var10005[0] = var10001;
            var10000.a(var10005);
            GpuBuffer var15 = this.b;
            long var10003 = var12;
            Object[] var10007 = new Object[]{null, null, null, var5};
            var10007[2] = 10;
            var10007[1] = var10003;
            var10007[0] = 0L;
            this.e = var15.a_ByteBuffer(var10007);
            MemoryUtil.memCopy(var14, this.e);
            this.e.position(var13);
            MemoryUtil.memFree(var14);
        }
    }

    @Override
    public void a(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        long var5 = var2 ^ 8860316669930L;
        long var7 = var2 ^ 93628449740783L;
        long var9 = var2 ^ 111806807607084L;
        if (this.f.remaining() < var4) {
            int var12 = this.f.limit();
            int var13 = Math.max(var12 << 1, var12 + (var4 - this.f.remaining()));
            int var14 = this.f.position();
            ByteBuffer var15 = MemoryUtil.memAlloc(var14);
            this.f.flip();
            MemoryUtil.memCopy(this.f, var15);
            this.c.d(new Object[]{var9});
            GpuBuffer var10000 = this.c;
            int var10001 = this.c.b(new Object[0]);
            long var10002 = var13;
            Object[] var10005 = new Object[]{null, null, var7};
            var10005[1] = var10002;
            var10005[0] = var10001;
            var10000.a(var10005);
            GpuBuffer var16 = this.c;
            long var10003 = var13;
            Object[] var10007 = new Object[]{null, null, null, var5};
            var10007[2] = 10;
            var10007[1] = var10003;
            var10007[0] = 0L;
            this.f = var16.a_ByteBuffer(var10007);
            MemoryUtil.memCopy(var15, this.f);
            this.f.position(var14);
            MemoryUtil.memFree(var15);
        }
    }

    public TextRenderer b_TextRenderer(Object[] var1) {
        float[] var2 = (float[])var1[0];
        long var3 = (Long)var1[1];
        var3 = 134641318060110L ^ var3;
        long var5 = var3 ^ 21032764215160L;
        int var10001 = 4 * var2.length;
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var10001;
        this.b(var10004);

        for (float var10 : var2) {
            this.e.putFloat(var10);
        }

        return this;
    }

    public TextRenderer c_TextRenderer(Object[] var1) {
        float var4 = (Float)var1[0];
        float var2 = (Float)var1[1];
        float var3 = (Float)var1[2];
        long var5 = (Long)var1[3];
        var5 = 134641318060110L ^ var5;
        long var7 = var5 ^ 87616543274028L;
        Object[] var10004 = new Object[]{null, var7};
        var10004[0] = 12;
        this.b(var10004);
        this.e.putFloat(var4);
        this.e.putFloat(var2);
        this.e.putFloat(var3);
        return this;
    }

    public TextRenderer d_TextRenderer(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        float var7 = (Float)var1[1];
        float var4 = (Float)var1[2];
        float var3 = (Float)var1[3];
        long var5 = (Long)var1[4];
        var5 = 134641318060110L ^ var5;
        long var8 = var5 ^ 118439183714216L;
        var2.transform(var7, var4, var3, 1.0F, this.g);
        Object[] var10004 = new Object[]{null, var8};
        var10004[0] = 12;
        this.b(var10004);
        this.e.putFloat(this.g.x);
        this.e.putFloat(this.g.y);
        this.e.putFloat(this.g.z);
        return this;
    }

    public TextRenderer e_TextRenderer(Object[] var1) {
        int var4 = (Integer)var1[0];
        long var2 = (Long)var1[1];
        var2 = 134641318060110L ^ var2;
        long var5 = var2 ^ 63759774459542L;
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = 4;
        this.b(var10004);
        this.e.putInt(var4);
        return this;
    }

    public TextRenderer g_TextRenderer(Object[] var1) {
        int[] var4 = (int[])var1[0];
        long var2 = (Long)var1[1];
        var2 = 134641318060110L ^ var2;
        long var5 = var2 ^ 77264373819354L;
        Event.b();
        int var10002 = 4 * var4.length;
        Object[] var10005 = new Object[]{null, var5};
        var10005[0] = var10002;
        this.a(var10005);

        for (int var11 : var4) {
            this.f.putInt(this.h + var11);
        }

        return this;
    }

    @Override
    public int a_int_____________________________(Object[] var1) {
        return this.e.position() / this.a.b;
    }

    @Override
    public int b_int__(Object[] var1) {
        return this.f.position() / 4;
    }

    public TextRenderer h_TextRenderer(Object[] var1) {
        this.h = 0;
        return this;
    }

    public TextRenderer i(Object[] var1) {
        int var2 = (Integer)var1[0];
        this.h = var2;
        return this;
    }

    @Override
    public void close() {
        this.d.clean();
    }

    public void d_void__(Object[] var1) {
        VertexFormat var2 = (VertexFormat)var1[0];
        this.a = var2;
    }

    @Override
    public WorldContract a_WorldContract(Object[] var1) {
        throw new UnsupportedOperationException("removed by Strip (unresolved ZKM reference)");
    }

    @Override
    public WorldContract f(Object[] var1) {
        throw new UnsupportedOperationException("removed by Strip (unresolved ZKM reference)");
    }

    @Override
    public WorldContract j(Object[] var1) {
        int var4 = (Integer)var1[0];
        return this.i(new Object[]{var4});
    }

    @Override
    public WorldContract h(Object[] var1) {
        return this.h_TextRenderer(new Object[0]);
    }

    @Override
    public WorldContract g(Object[] var1) {
        int[] var4 = (int[])var1[0];
        long var2 = (Long)var1[1];
        long var5 = var2 ^ 2722474518840L;
        return this.g_TextRenderer(new Object[]{var4, var5});
    }

    @Override
    public WorldContract e(Object[] var1) {
        int var2 = (Integer)var1[0];
        long var3 = (Long)var1[1];
        long var5 = var3 ^ 40196811386581L;
        Object[] var10004 = new Object[]{null, var5};
        var10004[0] = var2;
        return this.e_TextRenderer(var10004);
    }

    @Override
    public WorldContract d(Object[] var1) {
        Matrix4f var5 = (Matrix4f)var1[0];
        float var2 = (Float)var1[1];
        float var3 = (Float)var1[2];
        float var4 = (Float)var1[3];
        long var6 = (Long)var1[4];
        long var8 = var6 ^ 136834706969906L;
        Object[] var10007 = new Object[]{null, null, null, null, var8};
        var10007[3] = var4;
        var10007[2] = var3;
        var10007[1] = var2;
        var10007[0] = var5;
        return this.d_TextRenderer(var10007);
    }

    @Override
    public WorldContract c_WorldContract(Object[] var1) {
        float var4 = (Float)var1[0];
        float var6 = (Float)var1[1];
        float var5 = (Float)var1[2];
        long var2 = (Long)var1[3];
        long var7 = var2 ^ 10713143091605L;
        Object[] var10006 = new Object[]{null, null, null, var7};
        var10006[2] = var5;
        var10006[1] = var6;
        var10006[0] = var4;
        return this.c_TextRenderer(var10006);
    }

    @Override
    public WorldContract b_WorldContract(Object[] var1) {
        float[] var4 = (float[])var1[0];
        long var2 = (Long)var1[1];
        long var5 = var2 ^ 6478042606869L;
        return this.b_TextRenderer(new Object[]{var4, var5});
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

    private static MatchException b(MatchException var0) {
        return var0;
    }
}
