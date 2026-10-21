package dev.zprestige.prestige.module.misc;

import com.mojang.authlib.GameProfile;
import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.TextSetting;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.EntityEvent5;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.StringEvent;
import dev.zprestige.prestige.event.StringEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.social.AltManager;
import dev.zprestige.prestige.world.PositionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;

public class EnemyFaker extends Module {
    public EnemyFaker() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private TextSetting a;
    private TextSetting c;
    private EnumSetting d;
    private BoolSetting e;
    private SkinTextures f = null;
    private PlayerEntity g;

    @Subscribe
    public void a(StringEvent var1) {
        if (this.g != null
            && this.f != null
            && var1.a_String___(new Object[0]) != null
            && var1.a(new Object[0]) != null
            && !((String)this.a.getValue()).isEmpty()
            && b.getNetworkHandler().getPlayerListEntry(this.g.getUuid()) != null
            && b.getNetworkHandler().getPlayerListEntry(this.g.getUuid()).getProfile() != null) {
            if (var1.a_String___(new Object[0])
                .equals(PositionUtil.a_String________(new Object[]{b.getNetworkHandler().getPlayerListEntry(this.g.getUuid()).getProfile(), 3044946865408L}))) {
                var1.b(new Object[]{this.f});
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(EntityEvent5 var1) {
        if (this.g != null) {
            if (var1.a(new Object[0]).equals(this.g)) {
                MutableText var7 = Text.literal(String.valueOf(this.a_class_3545(new Object[]{43791854069900L}).getLeft()));
                short var10000 = 30710;
                MutableText var8 = Text.literal(((String)this.d.getValue()).toString())
                    .styled(lpL194_0 -> ((net.minecraft.text.Style)lpL194_0).withColor(a_int______________(new Object[]{((String)this.d.getValue()).toString(), 12129676112239L})));
                MutableText var9 = Text.literal(" | ").styled(lpL193_0 -> ((net.minecraft.text.Style)lpL193_0).withColor(Formatting.GRAY));
                MutableText var10 = Text.literal(((String)this.a.getValue()).toString()).styled(lpL192_0 -> ((net.minecraft.text.Style)lpL192_0).withFormatting(Formatting.WHITE));
                if (((String)this.a.getValue()).isEmpty()) {
                    var10 = Text.literal(var1.a(new Object[0]).getName().getString()).styled(lpL191_0 -> ((net.minecraft.text.Style)lpL191_0).withFormatting(Formatting.WHITE));
                }

                MutableText var11 = (((String)this.d.getValue()).equals("None") ? Text.empty() : var7.append(var8).append(var9)).append(var10);
                var1.b(new Object[]{var11});
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(StringEvent2 var1) {
        if (this.g != null && !((String)this.a.getValue()).isEmpty()) {
            if (var1.b_String_(new Object[0]).contains(this.g.getName().getString())) {
                var1.b(new Object[]{this.g.getName().getString()});
                var1.c(new Object[]{((String)this.a.getValue()).toString()});
                var1.a_void_____(new Object[0]);
            }
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        Event.b();

        for (PlayerEntity var12 : b.world.getPlayers()) {
            if (!var12.equals(b.player) && (var12.isDead() || !var12.isAlive())) {
                b.world.removeEntity(var12.getId(), RemovalReason.DISCARDED);
            }
        }

        this.g = a(new Object[]{11921155283737L});
        if ((Boolean)this.e.getValue()) {
            this.e.setValue(false);
            if (!((String)this.a.getValue()).isEmpty()) {
                UUID var14 = AltManager.a(new Object[]{((String)this.a.getValue()).toString(), 101923350145499L});
                if (var14 != null) {
                    GameProfile var15 = AltManager.a_GameProfile(new Object[]{var14, 76452615521574L});
                    CompletableFuture var13 = b.getSkinProvider().fetchSkinTextures(var15);
                    var13.thenAccept(lpL190_0 -> {
                        if (((java.util.Optional)lpL190_0).isPresent()) {
                            this.f = (SkinTextures)((java.util.Optional)lpL190_0).get();
                            b.player.sendMessage(Text.of("Fake skin updated to: " + (String)this.a.getValue()), false);
                        } else {
                            b.player.sendMessage(Text.of("Failed to fetch skin for: " + (String)this.a.getValue()), false);
                        }
                    });
                }
            }
        }
    }

    public static PlayerEntity a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 18758766630033L ^ var1;
        long var3 = var1 ^ 14575397154306L;
        long var5 = var1 ^ 46907505884087L;
        Event.b();
        PlayerEntity var8 = null;

        for (PlayerEntity var10 : new ArrayList<>(b.world.getPlayers())) {
            if (!var10.equals(b.player)
                && !Prestige.g.a(new Object[]{var10.getName().getString(), var3})
                && var10.getName().getString().length() >= 3
                && Prestige.i.a_boolean_(new Object[]{var10, var5})
                && (var8 == null || b.player.squaredDistanceTo(var10) < b.player.squaredDistanceTo(var8))) {
                var8 = var10;
            }
        }

        return var8;
    }

    private static int a_int______________(Object[] var0) {
        String var3 = (String)var0[0];
        Event.b();

        return switch (var3) {
            case "HT1" -> 15252026;
            case "LT1" -> 14005077;
            case "HT2" -> 12899303;
            case "LT2" -> 10528690;
            case "HT3" -> 16293722;
            case "LT3" -> 13007682;
            case "HT4" -> 8483994;
            case "LT4" -> 6642553;
            case "HT5" -> 9405096;
            case "LT5" -> 6642553;
            default -> 13882323;
        };
    }

    private Pair a_class_3545(Object[] var1) {
        String var5 = ((String)this.c.getValue()).toLowerCase();
        Event.b();

        return switch (var5) {
            case "axe" -> new Pair('\ue701', TextColor.fromFormatting(Formatting.GREEN));
            case "mace" -> new Pair('\ue702', TextColor.fromFormatting(Formatting.GRAY));
            case "nethop", "neth_pot" -> new Pair('\ue703', TextColor.fromRgb(8211008));
            case "pot" -> new Pair('\ue704', TextColor.fromRgb(16711680));
            case "smp" -> new Pair('\ue705', TextColor.fromRgb(15518533));
            case "sword" -> new Pair('\ue706', TextColor.fromRgb(10812912));
            case "uhc" -> new Pair('\ue707', TextColor.fromFormatting(Formatting.RED));
            case "vanilla" -> new Pair('\ue708', TextColor.fromFormatting(Formatting.LIGHT_PURPLE));
            case "bed" -> new Pair('\ue801', TextColor.fromRgb(16711680));
            case "bow" -> new Pair('\ue802', TextColor.fromRgb(6700304));
            case "creeper" -> new Pair('\ue803', TextColor.fromFormatting(Formatting.GREEN));
            case "debuff" -> new Pair('\ue804', TextColor.fromFormatting(Formatting.DARK_GRAY));
            case "dia_crystal" -> new Pair('\ue805', TextColor.fromFormatting(Formatting.AQUA));
            case "dia_smp" -> new Pair('\ue806', TextColor.fromRgb(9201291));
            case "elytra" -> new Pair('\ue807', TextColor.fromRgb(9276849));
            case "manhunt" -> new Pair('\ue808', TextColor.fromFormatting(Formatting.RED));
            case "minecart" -> new Pair('\ue809', TextColor.fromFormatting(Formatting.GRAY));
            case "og_vanilla" -> new Pair('\ue810', TextColor.fromFormatting(Formatting.GOLD));
            case "speed" -> new Pair('\ue811', TextColor.fromRgb(4434385));
            case "trident" -> new Pair('\ue812', TextColor.fromRgb(5741452));
            default -> new Pair('•', TextColor.fromFormatting(Formatting.WHITE));
        };
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
