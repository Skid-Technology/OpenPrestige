package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.data.IntData3;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.render.DrawMode;
import dev.zprestige.prestige.render.RenderContract5;
import dev.zprestige.prestige.render.RenderContract6;
import dev.zprestige.prestige.render.RenderUtil;
import dev.zprestige.prestige.render.StorageESPHelper;
import dev.zprestige.prestige.render.TextRenderer;
import dev.zprestige.prestige.type.GlPrimitive;
import dev.zprestige.prestige.world.WorldHelper3;
import dev.zprestige.prestige.world.WorldHelper4;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Predicate;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.block.entity.DropperBlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.entity.TrappedChestBlockEntity;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;
import org.joml.Matrix4f;
import org.joml.Vector3d;

public class StorageESP extends Module {
    private MultiSetting a;
    private BoolSetting d;
    private ColorSetting c;
    private ColorSetting e;
    private ColorSetting f;
    private ColorSetting g;
    private ColorSetting h;
    private ColorSetting i;
    private ColorSetting j;
    private ColorSetting k;
    private ColorSetting l;
    private ColorSetting m;
    private static final int n = 2;
    private static final float o = 15.0F;
    private static StorageESPHelper p;
    private boolean q = false;
    private List r = new ArrayList();

    public StorageESP() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.i
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL325_0 -> this.a.isSelected(new Object[]{"Hoppers", 47850572908778L}), 119980880492450L}
            );
        this.c
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL330_0 -> this.a.isSelected(new Object[]{"Chests", 47850572908778L}), 119980880492450L}
            );
        this.h
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL326_0 -> this.a.isSelected(new Object[]{"Barrels", 47850572908778L}), 119980880492450L}
            );
        this.m
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL321_0 -> this.a.isSelected(new Object[]{"Brewing Stands", 47850572908778L}), 119980880492450L}
            );
        this.k
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL323_0 -> this.a.isSelected(new Object[]{"Droppers", 47850572908778L}), 119980880492450L}
            );
        this.j
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL324_0 -> this.a.isSelected(new Object[]{"Dispensers", 47850572908778L}), 119980880492450L}
            );
        this.l
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL322_0 -> this.a.isSelected(new Object[]{"Furnaces", 47850572908778L}), 119980880492450L}
            );
        this.g
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL327_0 -> this.a.isSelected(new Object[]{"Shulker Boxes", 47850572908778L}), 119980880492450L}
            );
        this.e
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL329_0 -> this.a.isSelected(new Object[]{"Trapped Chests", 47850572908778L}), 119980880492450L}
            );
        this.f
            .setVisibility_ColorSetting(
                new Object[]{(Predicate<Color>)lpL328_0 -> this.a.isSelected(new Object[]{"Ender Chests", 47850572908778L}), 119980880492450L}
            );
    }

    @Override
    public void onDisable(Object[] var1) {
        this.r.clear();
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null && b.player != null) {
            this.r.clear();
            ClientChunkManager var11 = b.world.getChunkManager();
            AtomicReferenceArray var12 = new WorldHelper4(new WorldHelper3(var11).a(new Object[]{30990328564204L})).a(new Object[]{48873265602176L});

            for (int var13 = 0; var13 < var12.length(); var13++) {
                WorldChunk var14 = (WorldChunk)var12.get(var13);
                if (var14 != null) {
                    for (Entry var16 : var14.getBlockEntities().entrySet()) {
                        BlockEntity var17 = (BlockEntity)var16.getValue();
                        if (var17 != null) {
                            Color var18 = this.a(new Object[]{var17, 125353077803274L});
                            if (var18 != null) {
                                BlockPos var19 = (BlockPos)var16.getKey();
                                if (var19 != null) {
                                    int var20 = var18.getRGB();
                                    int var21 = var20 >> 16 & 0xFF;
                                    int var22 = var20 >> 8 & 0xFF;
                                    int var23 = var20 & 0xFF;
                                    int var24 = 0xFF000000 | var23 << 16 | var22 << 8 | var21;
                                    this.r.add(new IntData3(var19.getX(), var19.getY(), var19.getZ(), var24));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEnable(Object[] var1) {
        if (!this.q) {
            RenderContract5.c.register((RenderContract6)(lpL320_0, lpL320_1) -> {
                if (this.isEnabled()) {
                    if (this.d(new Object[]{122305991367488L})) {
                        StorageESPHelper var12 = a_StorageESPHelper(new Object[]{122641494347933L});
                        float var10003 = this.d.getValue() ? 0.3F : 0.0F;
                        Object[] var10006 = new Object[]{null, null, null, 46077004212060L};
                        var10006[2] = var10003;
                        var10006[1] = 15.0F;
                        var10006[0] = 2;
                        var12.b(var10006);
                    }
                }
            });
            this.q = true;
        }
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if (b.world != null && b.player != null) {
            if (this.d(new Object[]{122305991367488L})) {
                StorageESPHelper var23 = a_StorageESPHelper(new Object[]{122641494347933L});
                DrawMode var24 = var23.a_DrawMode_(new Object[]{94040189489481L});
                TextRenderer var25 = var1.a.a(new Object[]{var24, 66081470011557L});
                Matrix4f var26 = new Matrix4f();
                boolean var27 = false;
                var25.k(new Object[]{111161505170643L});

                for (IntData3 var29 : (Iterable<IntData3>)(this.r)) {
                    int var30 = var29.b8();
                    MatrixCameraData var10000 = var1.b;
                    double var10001 = var29.b5();
                    double var10002 = var29.b6();
                    double var10003 = var29.b7();
                    Object[] var10006 = new Object[]{null, null, null, null, 121644458201375L};
                    var10006[3] = var10003;
                    var10006[2] = var10002;
                    var10006[1] = var10001;
                    var10006[0] = var10000;
                    Vector3d var31 = RenderUtil.a_Vector3d(var10006);
                    var10000 = var1.b;
                    var10001 = var29.b5() + 1.0F;
                    var10002 = var29.b6() + 1.0F;
                    var10003 = var29.b7() + 1.0F;
                    var10006 = new Object[]{null, null, null, null, 121644458201375L};
                    var10006[3] = var10003;
                    var10006[2] = var10002;
                    var10006[1] = var10001;
                    var10006[0] = var10000;
                    Vector3d var32 = RenderUtil.a_Vector3d(var10006);
                    float var33 = (float)var31.x;
                    float var34 = (float)var31.y;
                    float var35 = (float)var31.z;
                    float var36 = (float)var32.x;
                    float var37 = (float)var32.y;
                    float var38 = (float)var32.z;
                    Object[] var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var37;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var40 = var25.d_TextRenderer(var10007);
                    Object[] var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var41 = var40.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var37;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var42 = var41.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var43 = var42.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var37;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var44 = var43.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var45 = var44.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var37;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var46 = var45.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var47 = var46.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var34;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var48 = var47.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var49 = var48.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var34;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var50 = var49.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var51 = var50.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var34;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var52 = var51.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var53 = var52.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var34;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var54 = var53.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var55 = var54.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var34;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var56 = var55.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var57 = var56.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var34;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var58 = var57.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var59 = var58.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var37;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var60 = var59.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var61 = var60.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var37;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var62 = var61.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var63 = var62.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var34;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var64 = var63.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var65 = var64.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var37;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var66 = var65.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var67 = var66.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var37;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var68 = var67.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var69 = var68.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var34;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var70 = var69.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var71 = var70.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var34;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var72 = var71.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var73 = var72.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var34;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var74 = var73.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var75 = var74.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var37;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var76 = var75.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var77 = var76.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var37;
                    var10007[1] = var33;
                    var10007[0] = var26;
                    TextRenderer var78 = var77.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var79 = var78.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var34;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var80 = var79.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var81 = var80.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var35;
                    var10007[2] = var37;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var82 = var81.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var83 = var82.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var37;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var84 = var83.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    TextRenderer var85 = var84.e_TextRenderer(var10004);
                    var10007 = new Object[]{null, null, null, null, 24356912433638L};
                    var10007[3] = var38;
                    var10007[2] = var34;
                    var10007[1] = var36;
                    var10007[0] = var26;
                    TextRenderer var86 = var85.d_TextRenderer(var10007);
                    var10004 = new Object[]{null, 75189690902744L};
                    var10004[0] = var30;
                    var86.e_TextRenderer(var10004);
                    var27 = true;
                }

                if (var27) {
                    ((TextRenderer)var25.i_WorldContract(new Object[]{GlPrimitive.QUADS, 3098493320255L})).h_TextRenderer(new Object[0]);
                } else {
                    var25.h_TextRenderer(new Object[0]);
                }
            }
        }
    }

    private boolean d(Object[] var1) {
        return b.currentScreen == null || b.currentScreen instanceof ChatScreen;
    }

    private Color a(Object[] var1) {
        BlockEntity var4 = (BlockEntity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 5434553841031L ^ var2;
        long var5 = var2 ^ 102762619384935L;
        if (var4 instanceof TrappedChestBlockEntity) {
            return this.a.isSelected(new Object[]{"Trapped Chests", var5}) ? (Color)this.e.getValue() : null;
        } else if (var4 instanceof ChestBlockEntity) {
            return this.a.isSelected(new Object[]{"Chests", var5}) ? (Color)this.c.getValue() : null;
        } else if (var4 instanceof EnderChestBlockEntity) {
            return this.a.isSelected(new Object[]{"Ender Chests", var5}) ? (Color)this.f.getValue() : null;
        } else if (var4 instanceof ShulkerBoxBlockEntity) {
            return this.a.isSelected(new Object[]{"Shulker Boxes", var5}) ? (Color)this.g.getValue() : null;
        } else if (var4 instanceof BarrelBlockEntity) {
            return this.a.isSelected(new Object[]{"Barrels", var5}) ? (Color)this.h.getValue() : null;
        } else if (var4 instanceof HopperBlockEntity) {
            return this.a.isSelected(new Object[]{"Hoppers", var5}) ? (Color)this.i.getValue() : null;
        } else if (var4 instanceof DropperBlockEntity) {
            return this.a.isSelected(new Object[]{"Droppers", var5}) ? (Color)this.k.getValue() : null;
        } else if (var4 instanceof DispenserBlockEntity) {
            return this.a.isSelected(new Object[]{"Dispensers", var5}) ? (Color)this.j.getValue() : null;
        } else if (var4 instanceof AbstractFurnaceBlockEntity) {
            return this.a.isSelected(new Object[]{"Furnaces", var5}) ? (Color)this.l.getValue() : null;
        } else if (var4 instanceof BrewingStandBlockEntity) {
            return this.a.isSelected(new Object[]{"Brewing Stands", var5}) ? (Color)this.m.getValue() : null;
        } else {
            return null;
        }
    }

    private static synchronized StorageESPHelper a_StorageESPHelper(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 5434553841031L ^ var1;
        long var3 = var1 ^ 72456632945731L;
        if (p == null) {
            p = new StorageESPHelper(var3);
        }

        return p;
    }

    private static Field g(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field h(Class var0, String var1, Class var2) {
        Field var3 = g(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = h(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method g(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method h(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = g(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = h(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
