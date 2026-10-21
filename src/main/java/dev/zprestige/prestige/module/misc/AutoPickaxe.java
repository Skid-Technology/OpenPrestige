package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderHelper27;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

public class AutoPickaxe extends Module {
    public AutoPickaxe() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;
    private RangeSetting c;
    private RenderHelper27 e = new RenderHelper27(26293358099357L);
    private static final Item[] f = new Item[]{
        Items.NETHERITE_PICKAXE, Items.DIAMOND_PICKAXE, Items.IRON_PICKAXE, Items.STONE_PICKAXE, Items.GOLDEN_PICKAXE, Items.WOODEN_PICKAXE
    };
    private int g = -1;
    private int h = -1;

    @Subscribe
    public void a(TickEvent24 var1) {
        if (b.currentScreen == null && b.isWindowFocused()) {
            BlockPos var21 = b.player.getBlockPos().down();
            boolean var22 = (Boolean)this.d.getValue() && GLFW.glfwGetMouseButton(b.getWindow().getHandle(), 0) != 1;
            HitResult var23 = b.crosshairTarget;
            if (!(var23 instanceof BlockHitResult) || !((BlockHitResult)var23).getBlockPos().equals(var21) || var22) {
                if (!this.d(new Object[]{123127378908948L})) {
                    this.j(new Object[]{36108270646903L});
                }
            } else if (!b.world.getBlockState(var21).isReplaceable() && b.world.getBlockState(var21).getBlock() != Blocks.BEDROCK) {
                if (InventoryUtil.c(new Object[]{b.player.getMainHandStack(), 100639324877220L})) {
                    this.j(new Object[]{36108270646903L});
                } else {
                    RenderHelper27 var10000 = this.e;
                    float var10001 = (Float)this.c.getValue();
                    Object[] var10004 = new Object[]{null, 2505922520844L};
                    var10004[0] = var10001;
                    if (var10000.a(var10004)) {
                        Object[] var10005 = new Object[]{null, null, 120051785458614L};
                        var10005[1] = 9;
                        var10005[0] = 0;
                        Integer var24 = this.a(var10005);
                        if (var24 != null) {
                            this.g = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                            this.h = -1;
                            int var28 = var24;
                            Object[] var10003 = new Object[]{null, 37392877673079L};
                            var10003[0] = var28;
                            InventoryUtil.a_void________(var10003);
                            this.j(new Object[]{36108270646903L});
                        } else if ((Boolean)this.a.getValue()) {
                            var10005 = new Object[]{null, null, 120051785458614L};
                            var10005[1] = 36;
                            var10005[0] = 9;
                            Integer var25 = this.a(var10005);
                            if (var25 != null) {
                                int var26 = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                                this.g = var26;
                                this.h = var25;
                                int var27 = var25;
                                var10004 = new Object[]{null, null, 133411734417951L};
                                var10004[1] = var26;
                                var10004[0] = var27;
                                InventoryUtil.f(var10004);
                                this.j(new Object[]{36108270646903L});
                            }
                        }
                    }
                }
            } else {
                if (!this.d(new Object[]{123127378908948L})) {
                    this.j(new Object[]{36108270646903L});
                }
            }
        }
    }

    private Integer a(Object[] var1) {
        int var5 = (Integer)var1[0];
        int var4 = (Integer)var1[1];
        Event.b();

        for (Item var10 : f) {
            for (int var11 = var5; var11 < var4; var11++) {
                if (b.player.getInventory().getStack(var11).getItem() == var10) {
                    return var11;
                }
            }
        }

        return null;
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90208737997070L ^ var2;
        long var4 = var2 ^ 119197203317879L;
        long var6 = var2 ^ 35127419141229L;
        long var8 = var2 ^ 32055836741741L;
        long var10 = var2 ^ 75483503907845L;
        if (this.g == -1) {
            return false;
        } else if (!this.e.b(new Object[]{this.c, var4})) {
            return true;
        } else {
            if (this.h != -1) {
                int var10000 = this.h;
                int var10001 = this.g;
                Object[] var10004 = new Object[]{null, null, var10};
                var10004[1] = var10001;
                var10004[0] = var10000;
                InventoryUtil.f(var10004);
            } else {
                int var13 = this.g;
                Object[] var10003 = new Object[]{null, var6};
                var10003[0] = var13;
                InventoryUtil.a_void________(var10003);
            }

            this.g = -1;
            this.h = -1;
            this.j(new Object[]{var8});
            return true;
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90208737997070L ^ var2;
        long var4 = var2 ^ 43771801999588L;
        long var6 = var2 ^ 71704473880984L;
        this.e.a_void_____________________(new Object[]{var4});
        this.c.reroll(new Object[]{var6});
    }

    @Override
    public void onDisable(Object[] var1) {
        this.g = -1;
        this.h = -1;
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
