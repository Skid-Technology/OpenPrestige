package dev.zprestige.prestige.module.visual;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.FloatSetting;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.render.ESPHelper;
import dev.zprestige.prestige.render.ESPUtil;
import dev.zprestige.prestige.render.PostShader;
import dev.zprestige.prestige.render.RenderContract5;
import dev.zprestige.prestige.render.RenderContract6;
import dev.zprestige.prestige.type.ShaderMode;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;
import net.minecraft.entity.EntityType;

public class ESP extends Module {
    public ESP() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private static final String a = "Normal";
    private static final String b = "Chams";
    private static final String c = "Aura";
    private EnumSetting d;
    private MultiSetting e;
    private BoolSetting f;
    private ColorSetting g;
    private IntSetting h;
    private IntSetting i;
    private BoolSetting j;
    private FloatSetting k;
    private FloatSetting l;
    private FloatSetting m;
    private FloatSetting n;
    private FloatSetting o;
    private BoolSetting p;
    private boolean[] q;
    private String r;
    private boolean s = false;
    private Consumer t;
    public static final ThreadLocal u = new ThreadLocal();

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();
        boolean[] var11 = this.e.getSelected();
        if (this.q == null || !Arrays.equals(var11, this.q)) {
            this.k(new Object[]{108749526380606L});
            this.q = Arrays.copyOf(var11, var11.length);
        }

        String var12 = (String)this.d.getValue();
        if (!var12.equals(this.r)) {
            ESPUtil.a(new Object[]{a(new Object[]{var12, 66274787328340L}), 41226367802457L});
            this.r = var12;
        }

        ESPUtil.f = (Boolean)this.p.getValue();
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 70806628412498L;
        long var6 = var2 ^ 98260224389471L;
        Event.b();
        ESPUtil.a(new Object[]{a(new Object[]{(String)this.d.getValue(), var4}), var6});
        this.r = (String)this.d.getValue();
        if (!this.s) {
            RenderContract5.c.register((RenderContract6)(lpL188_0, lpL188_1) -> {
                if (this.isEnabled()) {
                    PostShader var6x = ESPUtil.a_PostShader(new Object[0]);
                    if (var6x != null) {
                        var6x.d(new Object[]{this.t});
                    }
                }
            });
            this.s = true;
        }

        ESPUtil.e = true;
        ESPUtil.f = (Boolean)this.p.getValue();
    }

    @Override
    public void onDisable(Object[] var1) {
        Event.b();
        ESPUtil.e = false;
        ESPUtil.f = false;
        PostShader var5 = ESPUtil.a_PostShader(new Object[0]);
        if (var5 != null) {
            var5.d(new Object[]{null});
        }
    }

    private static ShaderMode a(Object[] var0) {
        String var1 = (String)var0[0];
        Event.b();

        return switch (var1) {
            case "Chams" -> ShaderMode.CHAMS;
            case "Aura" -> ShaderMode.AURA;
            default -> ShaderMode.NORMAL;
        };
    }

    private void k(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 124228018486362L ^ var2;
        long var4 = var2 ^ 2913177793225L;
        long var6 = var2 ^ 118793070602394L;
        long var8 = var2 ^ 63295913231502L;
        Event.b();
        ESPHelper var11 = new ESPHelper(new EntityType[0], var4);
        if (this.e.isSelected(new Object[]{"Players", var8})) {
            var11.a(new Object[]{EntityType.PLAYER, var6});
        }

        if (this.e.isSelected(new Object[]{"Crystals", var8})) {
            var11.a(new Object[]{EntityType.END_CRYSTAL, var6});
        }

        if (this.e.isSelected(new Object[]{"Items", var8})) {
            var11.a(new Object[]{EntityType.ITEM, var6});
        }

        if (this.e.isSelected(new Object[]{"Monsters", var8})) {
            var11.a(new Object[]{EntityType.ZOMBIE, var6});
            var11.a(new Object[]{EntityType.SKELETON, var6});
            var11.a(new Object[]{EntityType.CREEPER, var6});
            var11.a(new Object[]{EntityType.SPIDER, var6});
            var11.a(new Object[]{EntityType.CAVE_SPIDER, var6});
            var11.a(new Object[]{EntityType.ZOMBIE_VILLAGER, var6});
            var11.a(new Object[]{EntityType.HUSK, var6});
            var11.a(new Object[]{EntityType.DROWNED, var6});
            var11.a(new Object[]{EntityType.STRAY, var6});
            var11.a(new Object[]{EntityType.WITCH, var6});
            var11.a(new Object[]{EntityType.SLIME, var6});
            var11.a(new Object[]{EntityType.SILVERFISH, var6});
            var11.a(new Object[]{EntityType.PHANTOM, var6});
            var11.a(new Object[]{EntityType.VEX, var6});
            var11.a(new Object[]{EntityType.VINDICATOR, var6});
            var11.a(new Object[]{EntityType.EVOKER, var6});
            var11.a(new Object[]{EntityType.PILLAGER, var6});
            var11.a(new Object[]{EntityType.RAVAGER, var6});
        }

        if (this.e.isSelected(new Object[]{"Ambient", var8})) {
            var11.a(new Object[]{EntityType.BAT, var6});
        }

        if (this.e.isSelected(new Object[]{"Friendly", var8})) {
            var11.a(new Object[]{EntityType.COW, var6});
            var11.a(new Object[]{EntityType.SHEEP, var6});
            var11.a(new Object[]{EntityType.PIG, var6});
            var11.a(new Object[]{EntityType.CHICKEN, var6});
            var11.a(new Object[]{EntityType.RABBIT, var6});
            var11.a(new Object[]{EntityType.OCELOT, var6});
            var11.a(new Object[]{EntityType.VILLAGER, var6});
            var11.a(new Object[]{EntityType.IRON_GOLEM, var6});
            var11.a(new Object[]{EntityType.SNOW_GOLEM, var6});
            var11.a(new Object[]{EntityType.MOOSHROOM, var6});
            var11.a(new Object[]{EntityType.GOAT, var6});
            var11.a(new Object[]{EntityType.PANDA, var6});
            var11.a(new Object[]{EntityType.ALLAY, var6});
            var11.a(new Object[]{EntityType.SNIFFER, var6});
            var11.a(new Object[]{EntityType.ARMADILLO, var6});
        }

        if (this.e.isSelected(new Object[]{"Aquatic", var8})) {
            var11.a(new Object[]{EntityType.GUARDIAN, var6});
            var11.a(new Object[]{EntityType.ELDER_GUARDIAN, var6});
            var11.a(new Object[]{EntityType.DOLPHIN, var6});
            var11.a(new Object[]{EntityType.COD, var6});
            var11.a(new Object[]{EntityType.SALMON, var6});
            var11.a(new Object[]{EntityType.PUFFERFISH, var6});
            var11.a(new Object[]{EntityType.TROPICAL_FISH, var6});
            var11.a(new Object[]{EntityType.SQUID, var6});
            var11.a(new Object[]{EntityType.GLOW_SQUID, var6});
            var11.a(new Object[]{EntityType.AXOLOTL, var6});
            var11.a(new Object[]{EntityType.TURTLE, var6});
            var11.a(new Object[]{EntityType.TADPOLE, var6});
        }

        if (this.e.isSelected(new Object[]{"Nether", var8})) {
            var11.a(new Object[]{EntityType.BLAZE, var6});
            var11.a(new Object[]{EntityType.GHAST, var6});
            var11.a(new Object[]{EntityType.MAGMA_CUBE, var6});
            var11.a(new Object[]{EntityType.PIGLIN, var6});
            var11.a(new Object[]{EntityType.PIGLIN_BRUTE, var6});
            var11.a(new Object[]{EntityType.HOGLIN, var6});
            var11.a(new Object[]{EntityType.ZOGLIN, var6});
            var11.a(new Object[]{EntityType.STRIDER, var6});
            var11.a(new Object[]{EntityType.WITHER_SKELETON, var6});
        }

        if (this.e.isSelected(new Object[]{"End", var8})) {
            var11.a(new Object[]{EntityType.ENDERMAN, var6});
            var11.a(new Object[]{EntityType.ENDERMITE, var6});
            var11.a(new Object[]{EntityType.SHULKER, var6});
        }

        if (this.e.isSelected(new Object[]{"Bosses", var8})) {
            var11.a(new Object[]{EntityType.WITHER, var6});
            var11.a(new Object[]{EntityType.ENDER_DRAGON, var6});
            var11.a(new Object[]{EntityType.WARDEN, var6});
        }

        if (this.e.isSelected(new Object[]{"Tameable", var8})) {
            var11.a(new Object[]{EntityType.WOLF, var6});
            var11.a(new Object[]{EntityType.CAT, var6});
            var11.a(new Object[]{EntityType.PARROT, var6});
            var11.a(new Object[]{EntityType.HORSE, var6});
            var11.a(new Object[]{EntityType.DONKEY, var6});
            var11.a(new Object[]{EntityType.MULE, var6});
            var11.a(new Object[]{EntityType.LLAMA, var6});
            var11.a(new Object[]{EntityType.FOX, var6});
            var11.a(new Object[]{EntityType.BEE, var6});
            var11.a(new Object[]{EntityType.POLAR_BEAR, var6});
            var11.a(new Object[]{EntityType.FROG, var6});
            var11.a(new Object[]{EntityType.CAMEL, var6});
        }

        if (this.e.isSelected(new Object[]{"Projectiles", var8})) {
            var11.a(new Object[]{EntityType.ARROW, var6});
            var11.a(new Object[]{EntityType.SPECTRAL_ARROW, var6});
            var11.a(new Object[]{EntityType.TRIDENT, var6});
            var11.a(new Object[]{EntityType.FIREBALL, var6});
            var11.a(new Object[]{EntityType.SMALL_FIREBALL, var6});
            var11.a(new Object[]{EntityType.DRAGON_FIREBALL, var6});
            var11.a(new Object[]{EntityType.WITHER_SKULL, var6});
            var11.a(new Object[]{EntityType.SNOWBALL, var6});
            var11.a(new Object[]{EntityType.EGG, var6});
            var11.a(new Object[]{EntityType.ENDER_PEARL, var6});
            var11.a(new Object[]{EntityType.EXPERIENCE_BOTTLE, var6});
        }

        if (this.e.isSelected(new Object[]{"Vehicles", var8})) {
            var11.a(new Object[]{EntityType.MINECART, var6});
            var11.a(new Object[]{EntityType.CHEST_MINECART, var6});
            var11.a(new Object[]{EntityType.FURNACE_MINECART, var6});
            var11.a(new Object[]{EntityType.TNT_MINECART, var6});
            var11.a(new Object[]{EntityType.HOPPER_MINECART, var6});
            var11.a(new Object[]{EntityType.SPAWNER_MINECART, var6});
        }

        if (this.e.isSelected(new Object[]{"Decorations", var8})) {
            var11.a(new Object[]{EntityType.ITEM_FRAME, var6});
            var11.a(new Object[]{EntityType.GLOW_ITEM_FRAME, var6});
            var11.a(new Object[]{EntityType.ARMOR_STAND, var6});
            var11.a(new Object[]{EntityType.PAINTING, var6});
        }

        ESPUtil.c.clear();
        ESPUtil.d = var11;
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
