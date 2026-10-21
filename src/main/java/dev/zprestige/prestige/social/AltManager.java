package dev.zprestige.prestige.social;

import com.google.common.collect.HashMultimap;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import dev.zprestige.prestige.event.Event;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class AltManager {
    public static UUID a(Object[] var0) {
        String var1 = (String)var0[0];

        try {
            HttpURLConnection var4 = (HttpURLConnection)new URL("https://api.mojang.com/users/profiles/minecraft/" + var1).openConnection();
            var4.setRequestMethod("GET");
            if (var4.getResponseCode() != 200) {
                return null;
            } else {
                UUID var8;
                try (BufferedReader var5 = new BufferedReader(new InputStreamReader(var4.getInputStream()))) {
                    JsonObject var6 = JsonParser.parseReader(var5).getAsJsonObject();
                    String var7 = var6.get("id").getAsString();
                    var8 = UUID.fromString(var7.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
                }

                return var8;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            return null;
        }
    }

    public static GameProfile a_GameProfile(Object[] var0) {
        UUID var1 = (UUID)var0[0];

        try {
            HttpURLConnection var4 = (HttpURLConnection)new URL(
                    "https://sessionserver.mojang.com/session/minecraft/profile/" + var1.toString() + "?unsigned=false"
                )
                .openConnection();
            var4.setRequestMethod("GET");
            if (var4.getResponseCode() != 200) {
                return null;
            } else {
                GameProfile var9;
                try (BufferedReader var5 = new BufferedReader(new InputStreamReader(var4.getInputStream()))) {
                    JsonObject var6 = JsonParser.parseReader(var5).getAsJsonObject();
                    HashMultimap var7 = HashMultimap.create();
                    var6.getAsJsonArray("properties").forEach(lpL2_0 -> {
                        Event.b();
                        JsonObject var5x = ((com.google.gson.JsonElement)lpL2_0).getAsJsonObject();
                        String var6x = var5x.get("name").getAsString();
                        String var7x = var5x.get("value").getAsString();
                        String var8x = var5x.has("signature") ? var5x.get("signature").getAsString() : null;
                        var7.put(var6x, new Property(var6x, var7x, var8x));
                    });
                    GameProfile var8 = new GameProfile(var1, var6.get("name").getAsString(), new PropertyMap(var7));
                    var9 = var8;
                }

                return var9;
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            return null;
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
