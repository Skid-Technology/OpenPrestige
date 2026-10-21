package dev.zprestige.prestige.module.misc;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.TextSetting;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.EntityEvent5;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.StringEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.render.RenderUtil5;
import dev.zprestige.prestige.world.PositionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class StreamerMode extends Module {
    public StreamerMode() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
    }

    private BoolSetting d;
    private TextSetting a;
    private EnumSetting c;
    private BoolSetting e;
    private Map f = new LinkedHashMap();
    private AtomicInteger g = new AtomicInteger(0);

    @Override
    public void onEnable(Object[] var1) {
        this.f.clear();
        this.g.set(0);
    }

    @Override
    public void onDisable(Object[] var1) {
        this.f.clear();
        this.g.set(0);
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.world != null) {
            for (PlayerEntity var12 : b.world.getPlayers()) {
                String var13 = var12.getName().getString();
                if (!this.a(new Object[]{var13, 116606740002112L}) && var13.length() >= 3) {
                    this.b_String___(new Object[]{var13, 110686765949525L});
                }
            }

            if (b.getNetworkHandler() != null) {
                for (PlayerListEntry var15 : b.getNetworkHandler().getPlayerList()) {
                    if (var15 != null && var15.getProfile() != null) {
                        String var16 = PositionUtil.a_String________(new Object[]{var15.getProfile(), 3044946865408L});
                        if (var16 != null && !this.a(new Object[]{var16, 116606740002112L})) {
                            this.b_String___(new Object[]{var16, 110686765949525L});
                        }
                    }
                }
            }
        }
    }

    @Subscribe
    public void a(EntityEvent5 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerEntity var9) {
            String var12 = var9.getName().getString();
            if (!this.a(new Object[]{var12, 116606740002112L})) {
                String var11 = this.b_String___(new Object[]{var12, 110686765949525L});
                var1.b(new Object[]{Text.literal(var11)});
            }
        }
    }

    @Subscribe
    public void a(StringEvent2 var1) {
        Event.b();
        String var5 = var1.b_String_(new Object[0]);
        if (var5 != null && !var5.isEmpty() && !this.f.isEmpty()) {
            String var6 = var5;

            for (Entry var8 : (Iterable<Entry>)(this.f.entrySet())) {
                String var9 = (String)var8.getKey();
                if (!var9.isEmpty() && var6.contains(var9)) {
                    var6 = var6.replace(var9, (CharSequence)var8.getValue());
                }
            }

            if (!var6.equals(var5)) {
                var1.b(new Object[]{var5});
                var1.c(new Object[]{var6});
                var1.a_void_____(new Object[0]);
            }
        }
    }

    private boolean a(Object[] var1) {
        String var2 = (String)var1[0];
        long var3 = (Long)var1[1];
        var3 = 110986303050412L ^ var3;
        long var5 = var3 ^ 26822146492006L;
        if (var2 == null || var2.isEmpty()) {
            return true;
        } else {
            return !this.e.getValue() && b.player != null && var2.equals(b.player.getName().getString())
                ? true
                : (Boolean)this.d.getValue() && Prestige.g.a(new Object[]{var2, var5});
        }
    }

    private String b_String___(Object[] var1) {
        String var4 = (String)var1[0];
        long var2 = (Long)var1[1];
        var2 = 110986303050412L ^ var2;
        long var5 = var2 ^ 103636841655147L;
        Event.b();
        String var8 = (String)this.f.get(var4);
        if (var8 != null) {
            return var8;
        } else {
            String var9 = this.d(new Object[]{var5});
            this.f.put(var4, var9);
            return var9;
        }
    }

    private String d(Object[] var1) {
        Event.b();
        String var5 = ((String)this.a.getValue()).toString();
        if (!((String)this.c.getValue()).equals("Random")) {
            return var5 + this.g.incrementAndGet();
        } else {
            StringBuilder var6 = new StringBuilder();
            String var7 = "abcdefghijklmnopqrstuvwxyz0123456789";

            for (int var8 = 0; var8 < 4; var8++) {
                var6.append(var7.charAt(RenderUtil5.a.nextInt(var7.length())));
            }

            return var5 + var6;
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
