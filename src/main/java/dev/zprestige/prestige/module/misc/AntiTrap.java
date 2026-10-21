package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.Category;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.TickEvent19;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.SignEditorOpenS2CPacket;

public class AntiTrap extends Module {
    private static final String a = "Armor Stands";
    private static final String c = "Minecarts";
    private static final String d = "Item Frames";
    private static final String e = "Boats";
    private static final String f = "End Crystals";
    private MultiSetting g;
    private FloatSetting h;
    private BoolSetting i;
    private BoolSetting j;
    private BoolSetting k;

    public AntiTrap() {
        super("AntiTrap", "Hides entities commonly used to bait trap-TPAs (armor stands, minecarts, etc).", Category.Misc.ordinal());
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    @Subscribe
    public void a(PacketEvent var1) {
        if ((Boolean)this.k.getValue() && var1.a(new Object[0]) instanceof SignEditorOpenS2CPacket) {
            var1.a_void_____(new Object[0]);
        } else if ((Boolean)this.i.getValue()) {
            if (var1.a(new Object[0]) instanceof EntitySpawnS2CPacket var7) {
                if (b.player != null) {
                    if (this.d(new Object[]{var7.getEntityType(), 82216725291622L})) {
                        double var15 = var7.getX() - b.player.getX();
                        double var10 = var7.getY() - b.player.getY();
                        double var12 = var7.getZ() - b.player.getZ();
                        float var14 = (Float)this.h.getValue();
                        if (!(var15 * var15 + var10 * var10 + var12 * var12 > var14 * var14)) {
                            var1.a_void_____(new Object[0]);
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if ((Boolean)this.k.getValue() && b.currentScreen instanceof AbstractSignEditScreen) {
            b.setScreen(null);
        }

        if ((Boolean)this.j.getValue()) {
            if (b.world != null && b.player != null) {
                float var7 = (Float)this.h.getValue();
                double var8 = var7 * var7;
                double var10 = b.player.getX();
                double var12 = b.player.getY();
                double var14 = b.player.getZ();

                for (Entity var17 : b.world.getEntities()) {
                    if (this.a(new Object[]{var17, 103263670303874L})) {
                        double var18 = var17.getX() - var10;
                        double var20 = var17.getY() - var12;
                        double var22 = var17.getZ() - var14;
                        if (!(var18 * var18 + var20 * var20 + var22 * var22 > var8)) {
                            var17.discard();
                        }
                    }
                }
            }
        }
    }

    private boolean a(Object[] var1) {
        Entity var4 = (Entity)var1[0];
        long var2 = (Long)var1[1];
        var2 = 109320834739014L ^ var2;
        long var5 = var2 ^ 23102016790318L;
        if (this.g.isSelected(new Object[]{"Armor Stands", var5}) && var4 instanceof ArmorStandEntity) {
            return true;
        } else if (this.g.isSelected(new Object[]{"Minecarts", var5}) && var4 instanceof AbstractMinecartEntity) {
            return true;
        } else if (this.g.isSelected(new Object[]{"Item Frames", var5}) && var4 instanceof ItemFrameEntity) {
            return true;
        } else {
            return this.g.isSelected(new Object[]{"Boats", var5}) && var4 instanceof AbstractBoatEntity
                ? true
                : this.g.isSelected(new Object[]{"End Crystals", var5}) && var4 instanceof EndCrystalEntity;
        }
    }

    private boolean d(Object[] var1) {
        EntityType var4 = (EntityType)var1[0];
        long var2 = (Long)var1[1];
        var2 = 109320834739014L ^ var2;
        long var5 = var2 ^ 64384170474283L;
        long var7 = var2 ^ 74121589242938L;
        long var9 = var2 ^ 2399734809034L;
        if (this.g.isSelected(new Object[]{"Armor Stands", var9}) && var4 == EntityType.ARMOR_STAND) {
            return true;
        } else if (this.g.isSelected(new Object[]{"Minecarts", var9}) && this.e(new Object[]{var4, var5})) {
            return true;
        } else if (!this.g.isSelected(new Object[]{"Item Frames", var9}) || var4 != EntityType.ITEM_FRAME && var4 != EntityType.GLOW_ITEM_FRAME) {
            return this.g.isSelected(new Object[]{"Boats", var9}) && this.f(new Object[]{var4, var7})
                ? true
                : this.g.isSelected(new Object[]{"End Crystals", var9}) && var4 == EntityType.END_CRYSTAL;
        } else {
            return true;
        }
    }

    private boolean e(Object[] var1) {
        EntityType var4 = (EntityType)var1[0];
        return var4 == EntityType.MINECART
            || var4 == EntityType.CHEST_MINECART
            || var4 == EntityType.COMMAND_BLOCK_MINECART
            || var4 == EntityType.FURNACE_MINECART
            || var4 == EntityType.HOPPER_MINECART
            || var4 == EntityType.SPAWNER_MINECART
            || var4 == EntityType.TNT_MINECART;
    }

    private boolean f(Object[] var1) {
        EntityType var4 = (EntityType)var1[0];
        return var4 == EntityType.OAK_BOAT
            || var4 == EntityType.SPRUCE_BOAT
            || var4 == EntityType.BIRCH_BOAT
            || var4 == EntityType.JUNGLE_BOAT
            || var4 == EntityType.ACACIA_BOAT
            || var4 == EntityType.DARK_OAK_BOAT
            || var4 == EntityType.MANGROVE_BOAT
            || var4 == EntityType.CHERRY_BOAT
            || var4 == EntityType.PALE_OAK_BOAT
            || var4 == EntityType.PALE_OAK_CHEST_BOAT
            || var4 == EntityType.BAMBOO_RAFT
            || var4 == EntityType.OAK_CHEST_BOAT
            || var4 == EntityType.SPRUCE_CHEST_BOAT
            || var4 == EntityType.BIRCH_CHEST_BOAT
            || var4 == EntityType.JUNGLE_CHEST_BOAT
            || var4 == EntityType.ACACIA_CHEST_BOAT
            || var4 == EntityType.DARK_OAK_CHEST_BOAT
            || var4 == EntityType.MANGROVE_CHEST_BOAT
            || var4 == EntityType.CHERRY_CHEST_BOAT
            || var4 == EntityType.BAMBOO_CHEST_RAFT;
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
