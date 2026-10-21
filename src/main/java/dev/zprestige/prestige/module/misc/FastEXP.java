package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import org.lwjgl.glfw.GLFW;

public class FastEXP extends Module implements RenderContract3 {
    private EnumSetting a;
    private RangeSetting c;
    private BoolSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private RenderHelper27 g = new RenderHelper27(26293358099357L);
    private int h = -1;
    private int i = -1;

    public FastEXP() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.e.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL201_0 -> ((String)this.a.getValue()).equals("Automatic"), 73196168581295L});
        this.f.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL200_0 -> ((String)this.a.getValue()).equals("Automatic"), 73196168581295L});
        this.d.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL202_0 -> ((String)this.a.getValue()).equals("Manual"), 73196168581295L});
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.f(new Object[]{95897545603097L})) {
            var1.a_void_____(new Object[0]);
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 66268029083673L;
        long var6 = var2 ^ 18600503218156L;
        if (b.currentScreen == null && b.isWindowFocused()) {
            return ((String)this.a.getValue()).equals("Automatic") ? this.c(new Object[]{var6}) : this.b_RenderBatch___________(new Object[]{var4});
        } else {
            return null;
        }
    }

    private RenderBatch b_RenderBatch___________(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 4514749775132L ^ var2;
        long var4 = var2 ^ 35305615903038L;
        long var6 = var2 ^ 38241130376481L;
        long var8 = var2 ^ 42111411261649L;
        long var10 = var2 ^ 111970955679134L;
        long var12 = var2 ^ 36161376881317L;
        long var14 = var2 ^ 75033648675933L;
        if (this.f(new Object[]{var12}) && GLFW.glfwGetMouseButton(b.getWindow().getHandle(), 1) == 1) {
            if (this.g.b(new Object[]{this.c, var8})) {
                float var17 = b.player.getPitch();
                if ((Boolean)this.d.getValue()) {
                    b.player.setPitch(90.0F);
                }

                if (b.crosshairTarget != null && b.crosshairTarget.getType() == Type.BLOCK) {
                    InventoryUtil2.a(new Object[]{(BlockHitResult)b.crosshairTarget, var4});
                }

                InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var10});
                this.c.reroll(new Object[]{var14});
                this.g.a_void_____________________(new Object[]{var6});
                if ((Boolean)this.d.getValue()) {
                    b.player.setPitch(var17);
                }
            }

            return this.d.getValue() ? new RenderBatch(b.player.getYaw(), 90.0F) : null;
        } else {
            return null;
        }
    }

    private RenderBatch c(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 4514749775132L ^ var2;
        long var4 = var2 ^ 13966235903691L;
        long var6 = var2 ^ 15841094238932L;
        long var8 = var2 ^ 5047724095835L;
        long var10 = var2 ^ 110011219520984L;
        long var12 = var2 ^ 11970837479204L;
        long var14 = var2 ^ 80787181386347L;
        long var16 = var2 ^ 13522774811984L;
        long var18 = var2 ^ 114975734485928L;
        long var20 = var2 ^ 11900766861995L;
        long var22 = var2 ^ 28698410445038L;
        Event.b();
        boolean var25 = this.e_boolean__(new Object[]{var10});
        if (!var25) {
            this.j(new Object[]{var8});
            return null;
        } else if (!this.f(new Object[]{var16})) {
            if (this.h != -1 && Prestige.u != null && Prestige.u.a_boolean____________________________________(new Object[]{var20})) {
                return null;
            } else if (this.h != -1) {
                this.j(new Object[]{var8});
                return null;
            } else if (!this.g.b(new Object[]{this.c, var12})) {
                return null;
            } else {
                this.d(new Object[]{var22});
                this.g.a_void_____________________(new Object[]{var6});
                this.c.reroll(new Object[]{var18});
                return null;
            }
        } else if (!this.g.b(new Object[]{this.c, var12})) {
            return new RenderBatch(b.player.getYaw(), 90.0F);
        } else {
            float var26 = b.player.getPitch();
            b.player.setPitch(90.0F);
            if (b.crosshairTarget != null && b.crosshairTarget.getType() == Type.BLOCK) {
                InventoryUtil2.a(new Object[]{(BlockHitResult)b.crosshairTarget, var4});
            }

            InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var14});
            b.player.setPitch(var26);
            this.c.reroll(new Object[]{var18});
            this.g.a_void_____________________(new Object[]{var6});
            return new RenderBatch(b.player.getYaw(), 90.0F);
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 4514749775132L ^ var2;
        long var4 = var2 ^ 113780673630924L;
        long var6 = var2 ^ 4374636806522L;
        long var8 = var2 ^ 69646999940895L;
        long var10 = var2 ^ 32810518475270L;
        long var12 = var2 ^ 66175826098852L;
        Event.b();
        int var15 = this.d_int_________(new Object[]{var6});
        if (var15 != -1) {
            this.h = InventoryUtil.a_int_____(new Object[]{var8});
            this.i = -1;
            Object[] var10003 = new Object[]{null, var4};
            var10003[0] = var15;
            InventoryUtil.a_void________(var10003);
            return true;
        } else if (!(Boolean)this.e.getValue()) {
            return false;
        } else {
            int var16 = this.e(new Object[]{var10});
            if (var16 == -1) {
                return false;
            } else {
                int var17 = InventoryUtil.a_int_____(new Object[]{var8});
                this.h = var17;
                this.i = var16;
                Object[] var10004 = new Object[]{null, null, var12};
                var10004[1] = var17;
                var10004[0] = var16;
                InventoryUtil.f(var10004);
                return true;
            }
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 4514749775132L ^ var2;
        long var4 = var2 ^ 134094212039545L;
        long var6 = var2 ^ 38076493146897L;
        if (this.h != -1) {
            if ((Boolean)this.f.getValue()) {
                if (this.i != -1) {
                    int var10000 = this.i;
                    int var10001 = this.h;
                    Object[] var10004 = new Object[]{null, null, var6};
                    var10004[1] = var10001;
                    var10004[0] = var10000;
                    InventoryUtil.f(var10004);
                } else {
                    int var9 = this.h;
                    Object[] var10003 = new Object[]{null, var4};
                    var10003[0] = var9;
                    InventoryUtil.a_void________(var10003);
                }
            }

            this.h = -1;
            this.i = -1;
        }
    }

    private int d_int_________(Object[] var1) {
        Event.b();

        for (int var5 = 0; var5 <= 8; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() == Items.EXPERIENCE_BOTTLE) {
                return var5;
            }
        }

        return -1;
    }

    private int e(Object[] var1) {
        Event.b();

        for (int var5 = 9; var5 <= 35; var5++) {
            if (b.player.getInventory().getStack(var5).getItem() == Items.EXPERIENCE_BOTTLE) {
                return var5;
            }
        }

        return -1;
    }

    private boolean e_boolean__(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 4514749775132L ^ var2;
        long var4 = var2 ^ 118366489665207L;
        Event.b();

        for (EquipmentSlot var8 : AttributeModifierSlot.ARMOR) {
            ItemStack var9 = b.player.getEquippedStack(var8);
            if (a_boolean__________________________________(new Object[]{var9, var4})) {
                return true;
            }
        }

        return a_boolean__________________________________(new Object[]{b.player.getOffHandStack(), var4});
    }

    private static boolean a_boolean__________________________________(Object[] var0) {
        ItemStack var3 = (ItemStack)var0[0];
        if (!var3.isEmpty() && var3.isDamaged()) {
            for (Entry var6 : var3.getEnchantments().getEnchantmentEntries()) {
                if (((RegistryEntry)var6.getKey()).matchesKey(Enchantments.MENDING)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    private boolean f(Object[] var1) {
        return b.player != null && b.player.getMainHandStack().getItem() == Items.EXPERIENCE_BOTTLE;
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        long var6 = var2 ^ 132269813764983L;
        Event.b();
        Prestige.o.f(new Object[]{this, var4});
        if (this.h != -1 && b.player != null && b.world != null && b.currentScreen == null) {
            this.j(new Object[]{var6});
        }

        this.h = -1;
        this.i = -1;
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
