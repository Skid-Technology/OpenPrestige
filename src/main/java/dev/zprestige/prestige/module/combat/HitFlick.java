package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.type.KillAuraState;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class HitFlick extends Module implements RenderContract3 {
    public HitFlick() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private BoolSetting a;
    private FloatSetting c;
    private FloatSetting e;
    private EnumSetting f;
    private KillAuraState g = KillAuraState.IDLE;
    private Entity h;
    private float i;
    private boolean j;

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        Prestige.o.e(new Object[]{this, var4});
        this.g = KillAuraState.IDLE;
        this.h = null;
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 140372736859409L;
        Prestige.o.f(new Object[]{this, var4});
        this.g = KillAuraState.IDLE;
        this.h = null;
    }

    @Subscribe
    public void a(EntityEvent var1) {
        if (!this.j) {
            if (b.player != null && b.world != null) {
                if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
                    if (this.g == KillAuraState.IDLE) {
                        if (!(Boolean)this.a.getValue() || b.player.isSprinting()) {
                            Entity var9 = var1.a(new Object[0]);
                            if (var9 != null && var9 != b.player) {
                                if (!(Boolean)this.d.getValue() || var9 instanceof PlayerEntity) {
                                    if (!(var9 instanceof PlayerEntity var10 && Prestige.g.a(new Object[]{var10.getName().getString(), 24848952222090L}))) {
                                        if (Prestige.i.a_boolean_(new Object[]{var9, 54363558999103L})) {
                                            String var11 = (String)this.f.getValue();
                                            float var15;
                                            if (var11.equals("Left")) {
                                                var15 = -1.0F;
                                            } else if (var11.equals("Right")) {
                                                var15 = 1.0F;
                                            } else {
                                                var15 = Math.random() < 0.5 ? -1.0F : 1.0F;
                                            }

                                            float var12 = Math.min((Float)this.c.getValue(), (Float)this.e.getValue());
                                            float var13 = Math.max((Float)this.c.getValue(), (Float)this.e.getValue());
                                            float var14 = var12 + (float)(Math.random() * (var13 - var12));
                                            this.i = b.player.getYaw() + var15 * var14;
                                            this.h = var9;
                                            this.g = KillAuraState.FLICK;
                                            var1.a_void_____(new Object[0]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91260814807711L;
        long var6 = var2 ^ 81856115773718L;
        if (this.g == KillAuraState.IDLE) {
            return null;
        } else if (this.g == KillAuraState.FLICK) {
            float var13 = this.i + (float)(Math.random() * 3.0 - 1.5);
            float var14 = MathHelper.clamp(b.player.getPitch() + (float)(Math.random() * 2.0 - 1.0), -89.9F, 89.9F);
            this.g = KillAuraState.ATTACK;
            return new RenderBatch(var13, var14);
        } else {
            this.g = KillAuraState.IDLE;
            Entity var9 = this.h;
            this.h = null;
            if (var9 != null && var9.isAlive()) {
                RenderBatch var10 = WorldUtil.a_RenderBatch_(new Object[]{var9.getEyePos(), var6});
                float var11 = var10.a(new Object[0]) + (float)(Math.random() * 3.0 - 1.5);
                float var12 = MathHelper.clamp(var10.b(new Object[0]) + (float)(Math.random() * 2.0 - 1.0), -89.9F, 89.9F);
                this.j = true;
                InventoryUtil2.a_void_________(new Object[]{var9, var4});
                this.j = false;
                return new RenderBatch(var11, var12);
            } else {
                return null;
            }
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
