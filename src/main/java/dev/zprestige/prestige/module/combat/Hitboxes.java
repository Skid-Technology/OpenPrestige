package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.EntityEvent2;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper8;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.world.WorldUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Hitboxes extends Module implements RenderContract3 {
    private BoolSetting d;
    private FloatSetting a;
    private BoolSetting c;
    private FloatSetting e;
    private EnumSetting f;
    private BoolSetting g;
    private BoolSetting h;
    private boolean i = false;
    private boolean j = false;

    public Hitboxes() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.g.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL211_0 -> ((String)this.f.getValue()).equals("Grim/Vulcan"), 73196168581295L});
        this.h.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL210_0 -> ((String)this.f.getValue()).equals("Grim/Vulcan"), 73196168581295L});
    }

    @Subscribe
    public void a(TickEvent27 var1) {
        if ((Boolean)this.c.getValue()) {
            PlayerEntity var11 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
            float var12 = (Float)this.e.getValue();
            boolean var13 = ((String)this.f.getValue()).equals("Grim/Vulcan");
            double var14 = var13 ? ((Float)this.a.getValue()).floatValue() / 5.0 : 0.0;

            for (PlayerEntity var17 : b.world.getPlayers()) {
                float var18 = b.player.distanceTo(var17);
                if (!(var18 > var12) && Prestige.i.a_boolean_(new Object[]{var17, 54363558999103L})) {
                    Box var19 = var13 ? var17.getBoundingBox().expand(var14) : var17.getBoundingBox();
                    Color var20 = var18 <= 3.0F && var13 && var11 == var17 ? Color.GREEN : Color.RED;
                    MatrixCameraData var10000 = var1.b;
                    RenderHelper8 var10001 = var1.a;
                    float var10002 = (float)var19.minX;
                    float var10003 = (float)var19.minY;
                    float var10004 = (float)var19.minZ;
                    float var10005 = (float)var19.maxX;
                    float var10006 = (float)var19.maxY;
                    float var10007 = (float)var19.maxZ;
                    Object[] var10011 = new Object[]{null, null, null, null, null, null, null, null, var20, 119015971454350L};
                    var10011[7] = var10007;
                    var10011[6] = var10006;
                    var10011[5] = var10005;
                    var10011[4] = var10004;
                    var10011[3] = var10003;
                    var10011[2] = var10002;
                    var10011[1] = var10001;
                    var10011[0] = var10000;
                    RenderState.g(var10011);
                }
            }
        }
    }

    @Subscribe
    public void a(EntityEvent2 var1) {
        if (((String)this.f.getValue()).equals("None")) {
            if (!var1.a(new Object[0]).equals(b.player)) {
                if (!(Boolean)this.d.getValue() || var1.a(new Object[0]) instanceof PlayerEntity) {
                    if (!(var1.a(new Object[0]) instanceof PlayerEntity)
                        || Prestige.i.a_boolean_(new Object[]{(PlayerEntity)var1.a(new Object[0]), 54363558999103L})) {
                        if (!(b.player.distanceTo(var1.a(new Object[0])) > (Float)this.e.getValue())) {
                            var1.b(new Object[]{var1.a_class_238(new Object[0]).expand(((Float)this.a.getValue()).floatValue() / 5.0)});
                            var1.a_void_____(new Object[0]);
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(BoolEvent var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.PRE) {
            if (((String)this.f.getValue()).equals("Grim/Vulcan")) {
                if (this.j) {
                    var1.b(new Object[]{true});
                } else {
                    HitResult var9 = b.crosshairTarget;
                    if (var9 != null && var9.getType() == Type.MISS) {
                        PlayerEntity var10 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
                        if (var10 != null && !(b.player.distanceTo(var10) > 3.0F) && ((Boolean)this.g.getValue() || b.player.canSee(var10))) {
                            if (!(Boolean)this.h.getValue() || !this.a_boolean_______________________(new Object[]{var10, 41274878725551L})) {
                                Box var11 = var10.getBoundingBox().expand(((Float)this.a.getValue()).floatValue() / 5.0);
                                if (!var11.raycast(b.player.getEyePos(), var9.getPos()).isEmpty()) {
                                    this.i = true;
                                    var1.a_void_____(new Object[0]);
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
        long var4 = var2 ^ 7477342050734L;
        long var6 = var2 ^ 91260814807711L;
        long var8 = var2 ^ 116550396994070L;
        long var10 = var2 ^ 101816702374277L;
        long var12 = var2 ^ 71343916077473L;
        if (!((String)this.f.getValue()).equals("Grim/Vulcan")) {
            return null;
        } else if (!this.i) {
            return null;
        } else {
            HitResult var15 = b.crosshairTarget;
            if (var15 != null && var15.getType() == Type.MISS) {
                PlayerEntity var16 = CombatUtil.a_class_1657(new Object[]{var12});
                if (var16 != null && !(b.player.distanceTo(var16) > 3.0F) && ((Boolean)this.g.getValue() || b.player.canSee(var16))) {
                    if ((Boolean)this.h.getValue() && this.a_boolean_______________________(new Object[]{var16, var8})) {
                        this.i = false;
                        return null;
                    } else {
                        Box var17 = var16.getBoundingBox().expand(((Float)this.a.getValue()).floatValue() / 5.0);
                        Vec3d var18 = var15.getPos();
                        Vec3d var19 = new Vec3d(
                            MathHelper.clamp(var18.x, var17.minX, var17.maxX),
                            MathHelper.clamp(var18.y, var17.minY, var17.maxY),
                            MathHelper.clamp(var18.z, var17.minZ, var17.maxZ)
                        );
                        RenderBatch var20 = Prestige.o.a_RenderBatch(new Object[]{var19, var10});
                        var20 = WorldUtil.d_RenderBatch(new Object[]{var20, var19, var4});
                        if (var20.a_boolean________________________________________(new Object[0])) {
                            return var20;
                        } else {
                            this.i = false;
                            this.j = true;
                            InventoryUtil2.a_void_________(new Object[]{var16, var6});
                            this.j = false;
                            return var20;
                        }
                    }
                } else {
                    this.i = false;
                    return null;
                }
            } else {
                this.i = false;
                return null;
            }
        }
    }

    private boolean a_boolean_______________________(Object[] var1) {
        PlayerEntity var4 = (PlayerEntity)var1[0];
        return var4 != null && var4.isUsingItem() && var4.getActiveItem().isOf(Items.SHIELD);
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
        Prestige.o.f(new Object[]{this, var4});
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
