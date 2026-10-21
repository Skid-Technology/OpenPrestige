package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.IntBoolData;

public class VertexAttribute {
    public static final VertexFormat a;
    public static final VertexFormat b;
    public static final VertexFormat c;
    public static final VertexFormat d;
    public static final VertexFormat e;
    public static final VertexFormat f;
    public static final VertexFormat g;

    static {
        long[] var0 = new long[]{5126L, 5126L, 5121L, 5121L};
        a = new VertexFormat(new IntBoolData[]{new IntBoolData(3, (int)var0[0], 4, false)}, 0L);
        b = new VertexFormat(
            new IntBoolData[]{new IntBoolData(3, (int)var0[1], 4, false), new IntBoolData(4, (int)var0[3], 1, true)}, 0L
        );
        c = new VertexFormat(
            new IntBoolData[]{
                new IntBoolData(3, (int)var0[1], 4, false),
                new IntBoolData(4, (int)var0[2], 1, true),
                new IntBoolData(2, (int)var0[1], 4, false)
            },
            0L
        );
        d = new VertexFormat(
            new IntBoolData[]{
                new IntBoolData(3, (int)var0[1], 4, false),
                new IntBoolData(3, (int)var0[1], 4, false),
                new IntBoolData(4, (int)var0[2], 1, true),
                new IntBoolData(2, (int)var0[1], 4, false)
            },
            0L
        );
        e = new VertexFormat(
            new IntBoolData[]{
                new IntBoolData(3, (int)var0[1], 4, false),
                new IntBoolData(2, (int)var0[1], 4, false),
                new IntBoolData(2, (int)var0[1], 4, false),
                new IntBoolData(4, (int)var0[2], 1, true)
            },
            0L
        );
        f = new VertexFormat(
            new IntBoolData[]{new IntBoolData(3, (int)var0[1], 4, false), new IntBoolData(2, (int)var0[1], 4, false)}, 0L
        );
        g = new VertexFormat(
            new IntBoolData[]{
                new IntBoolData(3, (int)var0[1], 4, false),
                new IntBoolData(2, (int)var0[1], 4, false),
                new IntBoolData(2, (int)var0[1], 4, false)
            },
            0L
        );
    }
}
