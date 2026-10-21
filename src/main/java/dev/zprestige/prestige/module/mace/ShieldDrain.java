package dev.zprestige.prestige.module.mace;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;

public class ShieldDrain extends Module {
    private FloatSetting a;
    private FloatSetting c;
    private FloatSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private BoolSetting g;
    private IntSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private int k = -1;
    private static boolean l;

    public ShieldDrain() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.i.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL389_0 -> !(Boolean)this.f.getValue(), 73196168581295L});
        this.j
            .setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL388_0 -> !(Boolean)this.f.getValue() && (Boolean)this.i.getValue(), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Mace);
    }

    public static boolean d(Object[] var0) {
        return l;
    }

    @Override
    public void onEnable(Object[] var1) {
        this.k = -1;
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 131139254730552L;
        l = false;
        this.j(new Object[]{var4});
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        l = false;
        if (b.player != null && b.world != null && b.currentScreen == null && b.isWindowFocused()) {
            if (!b.player.isUsingItem()) {
                if ((Boolean)this.g.getValue() && b.player.isOnGround()) {
                    this.j(new Object[]{88946342922333L});
                } else if (b.player.fallDistance < ((Float)this.d.getValue()).floatValue()) {
                    this.j(new Object[]{88946342922333L});
                } else {
                    PlayerEntity var19 = this.a(new Object[]{16578663200379L});
                    if (var19 == null) {
                        this.j(new Object[]{88946342922333L});
                    } else if (b.player.distanceTo(var19) > (Float)this.c.getValue()) {
                        this.j(new Object[]{88946342922333L});
                    } else {
                        if (!InventoryUtil.o(new Object[]{b.player.getMainHandStack(), 133561865349436L})) {
                            if ((Boolean)this.f.getValue()) {
                                return;
                            }

                            if (!(Boolean)this.i.getValue()) {
                                return;
                            }

                            Integer var20 = InventoryUtil.c_Integer(new Object[]{25149592867800L});
                            if (var20 == null) {
                                return;
                            }

                            if ((Boolean)this.j.getValue() && this.k == -1) {
                                this.k = InventoryUtil.a_int_____(new Object[]{134339616728996L});
                            }

                            int var10000 = var20;
                            Object[] var10003 = new Object[]{null, 37392877673079L};
                            var10003[0] = var10000;
                            InventoryUtil.a_void________(var10003);
                        }

                        l = true;
                        int var22 = (Integer)this.h.getValue();

                        for (int var21 = 0; var21 < var22; var21++) {
                            InventoryUtil2.a_void_________(new Object[]{var19, 31382773185830L});
                        }
                    }
                }
            }
        }
    }

    private PlayerEntity a(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 125457291904568L ^ var2;
        long var4 = var2 ^ 118299790496201L;
        long var6 = var2 ^ 83592214154739L;
        long var8 = var2 ^ 22916526825587L;
        Event.b();
        if (b.crosshairTarget instanceof EntityHitResult var11
            && var11.getEntity() instanceof PlayerEntity var12
            && CombatUtil.b_boolean(new Object[]{var12, var6})
            && !Prestige.g.a(new Object[]{var12.getName().getString(), var4})) {
            return var12;
        } else if ((Boolean)this.e.getValue()) {
            return null;
        } else {
            float var10001 = (Float)this.a.getValue();
            Object[] var10004 = new Object[]{null, var8};
            var10004[0] = var10001;
            return this.b_class_1657(var10004);
        }
    }

    private PlayerEntity b_class_1657(Object[] var1) {
        float var2 = (Float)var1[0];
        long var3 = (Long)var1[1];
        var3 = 125457291904568L ^ var3;
        long var5 = var3 ^ 14681118619522L;
        long var7 = var3 ^ 47052207319992L;
        PlayerEntity var10 = null;
        double var11 = Double.MAX_VALUE;
        Event.b();

        for (PlayerEntity var14 : b.world.getPlayers()) {
            if (var14.isAlive()
                && !var14.equals(b.player)
                && !Prestige.g.a(new Object[]{var14.getName().getString(), var5})
                && CombatUtil.b_boolean(new Object[]{var14, var7})) {
                Vec3d var15 = new Vec3d(var14.getX(), b.player.getY(), var14.getZ());
                double var16 = b.player.squaredDistanceTo(var15);
                if (!(var16 > var2 * var2) && var16 < var11) {
                    var11 = var16;
                    var10 = var14;
                }
            }
        }

        return var10;
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 125457291904568L ^ var2;
        long var4 = var2 ^ 1087017702418L;
        if (this.k != -1) {
            int var10000 = this.k;
            Object[] var10003 = new Object[]{null, var4};
            var10003[0] = var10000;
            InventoryUtil.a_void________(var10003);
            this.k = -1;
        }
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
