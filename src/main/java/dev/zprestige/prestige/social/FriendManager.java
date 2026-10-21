package dev.zprestige.prestige.social;

import dev.zprestige.prestige.annotation.Chainable;
import dev.zprestige.prestige.module.misc.Teams;
import dev.zprestige.prestige.world.PositionUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;

public class FriendManager {
    private final Set a = new LinkedHashSet();
    private final Set b = new LinkedHashSet();

    @Chainable
    public void a(String var1) {
        this.a.add(var1);
    }

    @Chainable
    public void b(String var1) {
        this.a.remove(var1);
    }

    public boolean a(Object[] var1) {
        String var4 = (String)var1[0];
        long var2 = (Long)var1[1];
        var2 = 102810091184591L ^ var2;
        long var5 = var2 ^ 110880351489726L;
        return Teams.a(new Object[]{var4, var5}) ? true : this.a.contains(var4);
    }

    @Chainable
    public List a() {
        return new ArrayList(this.a);
    }

    public List b(Object[] var1) {
        return new ArrayList(this.b);
    }

    @Chainable
    public List c() {
        if (MinecraftClient.getInstance().getNetworkHandler() == null) {
            return new ArrayList();
        } else {
            ArrayList var8 = new ArrayList();

            for (PlayerListEntry var10 : Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).getPlayerList()) {
                if (var10.getProfile() != null
                    && PositionUtil.a_String________(new Object[]{var10.getProfile(), 3044946865408L}) != null
                    && MinecraftClient.getInstance().player != null
                    && MinecraftClient.getInstance().player.getUuid() != null
                    && !var10.getProfile().id().equals(MinecraftClient.getInstance().player.getUuid())) {
                    String var11 = PositionUtil.a_String________(new Object[]{var10.getProfile(), 3044946865408L});
                    if (!this.a(new Object[]{var11, 24848952222090L})) {
                        var8.add(var11);
                    }
                }
            }

            return var8;
        }
    }

    private static Field a(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field b(Class var0, String var1, Class var2) {
        Field var3 = a(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = b(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = a(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = b(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
