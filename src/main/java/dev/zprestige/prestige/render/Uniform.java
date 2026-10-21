package dev.zprestige.prestige.render;

import dev.zprestige.prestige.data.BoolData;
import dev.zprestige.prestige.data.BoolData2;
import dev.zprestige.prestige.data.BoolIntData;
import dev.zprestige.prestige.data.BoolIntData2;
import dev.zprestige.prestige.data.ConsumerData2;
import dev.zprestige.prestige.data.IntBoolData2;
import dev.zprestige.prestige.data.IntData2;
import dev.zprestige.prestige.data.IntSupplierData;
import dev.zprestige.prestige.data.ShaderData;
import java.util.function.Consumer;

public interface Uniform {
    IntSupplierData a = new IntSupplierData(RenderUtil::a);

    default void a(DrawContext var1) {
    }

    default void a(RenderPass var1) {
    }

    default void a() {
    }

    default boolean a_boolean() {
        return false;
    }

    static BoolData a(Object[] var0) {
        boolean var1 = (Boolean)var0[0];
        boolean var2 = (Boolean)var0[1];
        return new BoolData(var1, var2);
    }

    static BoolData b(Object[] var0) {
        boolean var1 = (Boolean)var0[0];
        return new BoolData(var1, var1);
    }

    static IntBoolData2 a_IntBoolData2(Object[] var0) {
        int var1 = (Integer)var0[0];
        boolean var2 = (Boolean)var0[1];
        return new IntBoolData2(var1, var2);
    }

    static IntBoolData2 b_IntBoolData2(Object[] var0) {
        int var1 = (Integer)var0[0];
        boolean var2 = (Boolean)var0[1];
        boolean var3 = (Boolean)var0[2];
        return new IntBoolData2(var1, var2, var3);
    }

    static ShaderData a_ShaderData(Object[] var0) {
        Shader var1 = (Shader)var0[0];
        return new ShaderData(var1);
    }

    static IntSupplierData a_IntSupplierData(Object[] var0) {
        int var1 = (Integer)var0[0];
        return new IntSupplierData(var1);
    }

    static IntData2 a_IntData2(Object[] var0) {
        int var2 = (Integer)var0[0];
        int var1 = (Integer)var0[1];
        return new IntData2(var2, var1);
    }

    static BoolData2 a_BoolData2(Object[] var0) {
        boolean var1 = (Boolean)var0[0];
        boolean var2 = (Boolean)var0[1];
        return new BoolData2(var1, var2);
    }

    static BoolIntData a_BoolIntData(Object[] var0) {
        boolean var3 = (Boolean)var0[0];
        boolean var6 = (Boolean)var0[1];
        int var1 = (Integer)var0[2];
        int var4 = (Integer)var0[3];
        int var5 = (Integer)var0[4];
        int var2 = (Integer)var0[5];
        return new BoolIntData(var3, var6, var1, var4, var5, var2);
    }

    static BoolIntData2 a_BoolIntData2(Object[] var0) {
        boolean var1 = (Boolean)var0[0];
        boolean var2 = (Boolean)var0[1];
        int var5 = (Integer)var0[2];
        int var3 = (Integer)var0[3];
        int var6 = (Integer)var0[4];
        int var4 = (Integer)var0[5];
        return new BoolIntData2(var1, var2, var5, var3, var6, var4);
    }

    static ConsumerData2 a_ConsumerData2(Object[] var0) {
        Consumer var1 = (Consumer)var0[0];
        return new ConsumerData2(var1);
    }

    static Uniform a_Uniform(Object[] var0) {
        Consumer var1 = (Consumer)var0[0];
        return new RenderHelper28(var1);
    }

    static Uniform b_Uniform(Object[] var0) {
        Consumer var1 = (Consumer)var0[0];
        return new RenderHelper29(var1);
    }
}
