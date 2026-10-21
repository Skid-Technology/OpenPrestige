package dev.zprestige.prestige.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.MultiSetting;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Setting;
import dev.zprestige.prestige.annotation.Chainable;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.data.ModuleStringBoolData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.hud.HudElement;
import dev.zprestige.prestige.math.MathUtil5;
import dev.zprestige.prestige.render.RenderHelper11;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.type.SettingFlag;
import dev.zprestige.prestige.type.TrajectoryType;
import dev.zprestige.prestige.util.TpsTimer;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.MinecraftClient;

public class ConfigManager {
    private static final Gson a = new Gson();
    private static final int b = 100000;
    private static final int c = 64;
    private String d;
    private String e;
    private static final long m = 8000L;

    @Chainable
    public String a() {
        Event.b();
        RenderHelper11 var16 = new RenderHelper11("save");
        JsonObject var17 = new JsonObject();

        try {
            if (Prestige.moduleManager == null) {
                var16.b(new Object[]{"Config", "module manager is not ready", null});
            } else {
                HashSet var18 = new HashSet();
                var18.add("Menu");
                JsonObject var19 = new JsonObject();

                for (Setting var21 : (Iterable<Setting>)(Prestige.moduleManager.getMenu(new Object[0]).settings)) {
                    this.g(new Object[]{var19, var21, "Menu", var16, 128387161211156L});
                }

                var17.add("Menu", var19);

                for (Module var32 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
                    String var22 = var32.getName();
                    if (var22 == null || var22.isBlank()) {
                        var16.b(new Object[]{"Unnamed module", "module has no config name", null});
                    } else if (!var18.add(var22)) {
                        var16.b(new Object[]{var22, "duplicate config section name", null});
                    } else {
                        JsonObject var23 = new JsonObject();

                        for (Setting var25 : (Iterable<Setting>)(var32.getCoreSettings(new Object[0]))) {
                            this.g(new Object[]{var23, var25, var22, var16, 128387161211156L});
                        }

                        for (Setting var38 : (Iterable<Setting>)(var32.settings)) {
                            this.g(new Object[]{var23, var38, var22, var16, 128387161211156L});
                        }

                        var17.add(var22, var23);
                    }
                }

                if (Prestige.l == null) {
                    var16.b(new Object[]{"HUD", "HUD editor is not ready", null});
                } else {
                    for (HudElement var35 : Prestige.l.a_HudElementArr(new Object[0])) {
                        String var37 = var35.a_String____(new Object[0]);
                        if (var37 == null || var37.isBlank()) {
                            var16.b(new Object[]{"HUD", "component has no config name", null});
                        } else if (!var18.add(var37)) {
                            var16.b(new Object[]{"HUD > " + var37, "duplicate config section name", null});
                        } else {
                            try {
                                JsonObject var39 = new JsonObject();
                                var39.addProperty("Enabled", var35.e_boolean(new Object[]{15166320517519L}));
                                var39.addProperty("X", var35.a_float_______(new Object[0]));
                                var39.addProperty("Y", var35.b_float___(new Object[0]));
                                if (var35.b_boolean____(new Object[0])) {
                                    var16.c(new Object[]{3});
                                } else {
                                    var39.addProperty("RuleX", var35.i.name());
                                    var39.addProperty("RuleY", var35.j.name());
                                    var39.addProperty("OffX", var35.k);
                                    var39.addProperty("OffY", var35.l);
                                    var16.c(new Object[]{7});
                                }

                                var17.add(var37, var39);
                            } catch (Throwable var27) {
                                j(new Object[]{var27, 77239967061762L});
                                var16.b(new Object[]{"HUD > " + var37, d_String(new Object[]{var27, 126091222217669L}), var27});
                            }
                        }
                    }
                }
            }
        } catch (Throwable var28) {
            j(new Object[]{var28, 77239967061762L});
            var16.b(new Object[]{"Config", "unexpected save failure: " + d_String(new Object[]{var28, 126091222217669L}), var28});
        }

        String var29;
        try {
            var29 = var17.toString();
        } catch (Throwable var26) {
            j(new Object[]{var26, 77239967061762L});
            var16.b(new Object[]{"Config", "could not serialize snapshot: " + d_String(new Object[]{var26, 126091222217669L}), var26});
            var29 = "{}";
        }

        if (var16.a_boolean__________________________________________(new Object[]{17026319865315L})) {
            if (this.d != null) {
                var16.a(new Object[]{"Config", "save was aborted; the previous complete snapshot was kept", null});
                var29 = this.d;
            } else {
                var16.b(new Object[]{"Config", "save was aborted; no complete fallback snapshot exists", null});
                var29 = "{}";
            }
        } else {
            this.d = var29;
        }

        i(new Object[]{var16, 29277096836346L});
        return var29;
    }

    @Chainable
    public void a(String var1) {
        Event.b();
        RenderHelper11 var25 = new RenderHelper11("load");

        try {
            JsonObject var26 = this.a_JsonObject(new Object[]{var1, var25, 50406667818198L});
            if (var26 == null) {
                return;
            }

            if (Prestige.moduleManager != null) {
                HashSet var27 = new HashSet();
                var27.add("Menu");
                ArrayList var28 = new ArrayList();
                Set var29 = Collections.newSetFromMap(new IdentityHashMap());
                this.a(new Object[]{var26, var29, var25, 33673993163928L});
                this.b(new Object[]{var26, var25, 86545728593565L});

                for (Module var31 : (Iterable<Module>)(Prestige.moduleManager.getModules())) {
                    String var32 = var31.getName();
                    if (var32 != null && !var32.isBlank()) {
                        if (!var27.add(var32)) {
                            var25.b(new Object[]{var32, "duplicate module config name; section was skipped", null});
                        } else {
                            this.c(new Object[]{var26, var31, var32, var29, var28, var25, 63085766558568L});
                        }
                    }
                }

                if (Prestige.l == null) {
                    var25.a(new Object[]{"HUD", "HUD editor is not ready; HUD layout was skipped", null});
                } else {
                    for (HudElement var33 : Prestige.l.a_HudElementArr(new Object[0])) {
                        String var34 = var33.a_String____(new Object[0]);
                        if (var34 == null || var34.isBlank()) {
                            var25.b(new Object[]{"HUD", "component has no config name", null});
                        } else if (!var27.add(var34)) {
                            var25.b(new Object[]{"HUD > " + var34, "duplicate config section name; section was skipped", null});
                        } else {
                            this.e(new Object[]{var26, var33, var34, var25, 5466957899849L});
                        }
                    }
                }

                this.h(new Object[]{var26, var27, "", "module or HUD section", var25, 9248152575382L});

                for (ModuleStringBoolData var43 : (Iterable<ModuleStringBoolData>)(var28)) {
                    JsonElement var10001 = var43.aE();
                    Module var10002 = var43.aC();
                    String var10003 = var43.aD();
                    boolean var10004 = var43.aF();
                    Object[] var10008 = new Object[]{null, null, null, null, var25, 81130270711953L};
                    var10008[3] = var10004;
                    var10008[2] = var10003;
                    var10008[1] = var10002;
                    var10008[0] = var10001;
                    this.d(var10008);
                }

                if (var25.c == 0) {
                    var25.b(new Object[]{"Config", "no recognized config sections were found", null});
                }

                return;
            }

            var25.b(new Object[]{"Config", "module manager is not ready", null});
        } catch (Throwable var38) {
            j(new Object[]{var38, 77239967061762L});
            var25.b(new Object[]{"Config", "unexpected load failure: " + d_String(new Object[]{var38, 126091222217669L}), var38});
            return;
        } finally {
            this.e = var25.a_String_______(new Object[0]);
            i(new Object[]{var25, 29277096836346L});
        }
    }

    @Chainable
    public String b() {
        String var1 = this.e;
        this.e = null;
        return var1;
    }

    private void a(Object[] var1) {
        JsonObject var4 = (JsonObject)var1[0];
        Set var3 = (Set)var1[1];
        RenderHelper11 var2 = (RenderHelper11)var1[2];
        long var5 = (Long)var1[3];
        var5 = 96496729806863L ^ var5;
        long var7 = var5 ^ 37162905344827L;
        long var9 = var5 ^ 132314134132321L;
        long var11 = var5 ^ 103654541799618L;
        long var13 = var5 ^ 107987087481170L;
        long var15 = var5 ^ 16891702107029L;
        long var17 = var5 ^ 65759533973330L;
        Event.b();
        HashSet var20 = new HashSet();
        var20.add("Menu");
        Iterator var21 = Prestige.moduleManager.getModules().iterator();

        while (true) {
            Module var22;
            String var23;
            boolean var27;
            while (true) {
                boolean var26;
                while (true) {
                    if (!var21.hasNext()) {
                        return;
                    }

                    var22 = (Module)var21.next();
                    var23 = var22.getName();
                    if (var23 != null && !var23.isBlank() && var20.add(var23)) {
                        JsonElement var24 = var4.get(var23);
                        if (var24 != null && var24.isJsonObject()) {
                            JsonObject var25 = var24.getAsJsonObject();
                            if (var25.has("Enabled")) {
                                try {
                                    var26 = MathUtil5.a_boolean________(new Object[]{this.a_String_(new Object[]{var25.get("Enabled"), var11}), var9});
                                    break;
                                } catch (Throwable var32) {
                                    j(new Object[]{var32, var15});
                                }
                            }
                        }
                    }
                }

                if (!var26) {
                    try {
                        var27 = var22.isEnabled();
                        break;
                    } catch (Throwable var33) {
                        j(new Object[]{var33, var15});
                        var2.b(new Object[]{var23 + " > Enabled", "could not inspect current module state: " + d_String(new Object[]{var33, var17}), var33});
                    }
                }
            }

            if (var27) {
                var3.add(var22);
                String var28 = var23 + " > Enabled";

                try {
                    var22.toggle(new Object[]{var7});
                    if (var22.isEnabled()) {
                        var22.toggle(new Object[]{var7});
                    }

                    if (!var22.isEnabled()) {
                        var2.c(new Object[]{1});
                    } else {
                        var2.a(new Object[]{var28, "module changed its own state while being disabled", null});
                    }
                } catch (Throwable var34) {
                    j(new Object[]{var34, var15});
                    Object[] var10006 = new Object[]{null, null, var34, var13};
                    var10006[1] = true;
                    var10006[0] = var22;
                    boolean var30 = this.a_boolean__(var10006);
                    String var31 = var30 ? "disable lifecycle failed; previous state was restored" : "disable lifecycle failed; state may be inconsistent";
                    var2.b(new Object[]{var28, var31, var34});
                }
            }
        }
    }

    private void b(Object[] var1) {
        JsonObject var2 = (JsonObject)var1[0];
        RenderHelper11 var3 = (RenderHelper11)var1[1];
        long var4 = (Long)var1[2];
        var4 = 96496729806863L ^ var4;
        long var6 = var4 ^ 8706910453811L;
        long var8 = var4 ^ 18813483277060L;
        long var10 = var4 ^ 79939548172944L;
        if (var2.has("Menu")) {
            var3.c++;
            JsonObject var13 = this.b_JsonObject(new Object[]{var2.get("Menu"), "Menu", var3, var6});
            if (var13 != null) {
                HashSet var14 = new HashSet();

                for (Setting var16 : (Iterable<Setting>)(Prestige.moduleManager.getMenu(new Object[0]).settings)) {
                    this.f(new Object[]{var13, var16, "Menu", var14, var3, var10});
                }

                this.h(new Object[]{var13, var14, "Menu", "setting", var3, var8});
            }
        }
    }

    private void c(Object[] var1) {
        JsonObject var7 = (JsonObject)var1[0];
        Module var2 = (Module)var1[1];
        String var3 = (String)var1[2];
        Set var8 = (Set)var1[3];
        List var9 = (List)var1[4];
        RenderHelper11 var6 = (RenderHelper11)var1[5];
        long var4 = (Long)var1[6];
        var4 = 96496729806863L ^ var4;
        long var10 = var4 ^ 123407556550086L;
        long var12 = var4 ^ 113019533182705L;
        long var14 = var4 ^ 69691540459365L;
        if (var7.has(var3)) {
            var6.c++;
            JsonObject var17 = this.b_JsonObject(new Object[]{var7.get(var3), var3, var6, var10});
            if (var17 != null) {
                HashSet var18 = new HashSet();
                boolean var19 = false;

                for (Setting var21 : (Iterable<Setting>)(var2.getCoreSettings(new Object[0]))) {
                    if ("Enabled".equals(var21.getName())) {
                        var19 = true;
                        var18.add("Enabled");
                    } else {
                        this.f(new Object[]{var17, var21, var3, var18, var6, var14});
                    }
                }

                for (Setting var24 : (Iterable<Setting>)(var2.settings)) {
                    this.f(new Object[]{var17, var24, var3, var18, var6, var14});
                }

                this.h(new Object[]{var17, var18, var3, "setting", var6, var12});
                if (var19 && var17.has("Enabled")) {
                    var9.add(new ModuleStringBoolData(var2, var3, var17.get("Enabled"), var8.contains(var2)));
                }
            }
        }
    }

    private void d(Object[] var1) {
        JsonElement var8 = (JsonElement)var1[0];
        Module var2 = (Module)var1[1];
        String var4 = (String)var1[2];
        boolean var3 = (Boolean)var1[3];
        RenderHelper11 var5 = (RenderHelper11)var1[4];
        long var6 = (Long)var1[5];
        var6 = 96496729806863L ^ var6;
        long var9 = var6 ^ 130452708159282L;
        long var11 = var6 ^ 51947669766760L;
        long var13 = var6 ^ 10085635278027L;
        long var15 = var6 ^ 58684175330651L;
        long var17 = var6 ^ 96985419263900L;
        long var19 = var6 ^ 119465039121243L;
        long var21 = var6 ^ 121114916499154L;
        Event.b();
        String var24 = var4 + " > Enabled";
        if (!var3) {
            try {
                boolean var25 = MathUtil5.a_boolean________(new Object[]{this.a_String_(new Object[]{var8, var13}), var11});
                boolean var26 = var2.isEnabled();
                if (var25 == var26) {
                    var5.c(new Object[]{1});
                    return;
                }

                if (var25 && !var2.b(new Object[]{var21})) {
                    var5.a(new Object[]{var24, "activation is blocked for downloaded configs; settings were still loaded", null});
                    return;
                }

                try {
                    var2.toggle(new Object[]{var9});
                    if (var2.isEnabled() == var25) {
                        var5.c(new Object[]{1});
                    } else {
                        var5.a(new Object[]{var24, "module changed its own state during activation", null});
                    }
                } catch (Throwable var31) {
                    j(new Object[]{var31, var17});
                    Object[] var10006 = new Object[]{null, null, var31, var15};
                    var10006[1] = var26;
                    var10006[0] = var2;
                    boolean var29 = this.a_boolean__(var10006);
                    String var30 = var29 ? "module lifecycle failed; previous state was restored" : "module lifecycle failed; state may be inconsistent";
                    var5.b(new Object[]{var24, var30, var31});
                }
            } catch (Throwable var32) {
                j(new Object[]{var32, var17});
                var5.b(new Object[]{var24, d_String(new Object[]{var32, var19}), var32});
            }
        }
    }

    private boolean a_boolean__(Object[] var1) {
        Module var4 = (Module)var1[0];
        boolean var3 = (Boolean)var1[1];
        Throwable var2 = (Throwable)var1[2];
        long var5 = (Long)var1[3];
        var5 = 96496729806863L ^ var5;
        long var7 = var5 ^ 22237184105062L;
        long var9 = var5 ^ 64498351692488L;

        try {
            if (var4.isEnabled() != var3) {
                var4.toggle(new Object[]{var7});
            }

            return var4.isEnabled() == var3;
        } catch (Throwable var13) {
            j(new Object[]{var13, var9});
            var2.addSuppressed(var13);
            return false;
        }
    }

    private void e(Object[] var1) {
        JsonObject var7 = (JsonObject)var1[0];
        HudElement var2 = (HudElement)var1[1];
        String var4 = (String)var1[2];
        RenderHelper11 var3 = (RenderHelper11)var1[3];
        long var5 = (Long)var1[4];
        var5 = 96496729806863L ^ var5;
        long var8 = var5 ^ 100411059039696L;
        long var10 = var5 ^ 5502290586488L;
        long var12 = var5 ^ 58875693054694L;
        long var14 = var5 ^ 101797762110170L;
        long var16 = var5 ^ 23108292749124L;
        long var18 = var5 ^ 36929804059523L;
        long var20 = var5 ^ 85373803017959L;
        long var22 = var5 ^ 87860093076389L;
        long var24 = var5 ^ 85724311003336L;
        long var26 = var5 ^ 55444573469968L;
        long var28 = var5 ^ 107814391728816L;
        long var30 = var5 ^ 119585972925391L;
        long var32 = var5 ^ 74892457757715L;
        long var34 = var5 ^ 104387745518025L;
        long var36 = var5 ^ 59274824819998L;
        if (var7.has(var4)) {
            var3.c++;
            String var39 = "HUD > " + var4;
            JsonObject var40 = this.b_JsonObject(new Object[]{var7.get(var4), var39, var3, var20});
            if (var40 != null) {
                Set var41 = Set.of("Enabled", "X", "Y", "Anchor", "RuleX", "RuleY", "OffX", "OffY");
                this.h(new Object[]{var40, var41, var39, "setting", var3, var8});
                boolean var42 = var40.has("RuleX") && var40.has("RuleY") && var40.has("OffX") && var40.has("OffY");
                boolean var43 = var40.has("X");
                boolean var44 = var40.has("Y");
                if (var42) {
                    try {
                        SettingFlag var45 = SettingFlag.valueOf(this.a_String_(new Object[]{var40.get("RuleX"), var32}));
                        SettingFlag var46 = SettingFlag.valueOf(this.a_String_(new Object[]{var40.get("RuleY"), var32}));
                        float var47 = MathUtil5.a_float_____________(new Object[]{this.a_String_(new Object[]{var40.get("OffX"), var32}), var24});
                        float var48 = MathUtil5.a_float_____________(new Object[]{this.a_String_(new Object[]{var40.get("OffY"), var32}), var24});
                        Object[] var10007 = new Object[]{null, null, null, null, var26};
                        var10007[3] = var48;
                        var10007[2] = var47;
                        var10007[1] = var46;
                        var10007[0] = var45;
                        var2.f_void(var10007);
                        var3.c(new Object[]{4});
                    } catch (IllegalArgumentException var52) {
                        var3.b(new Object[]{var39 + " > Position", "unknown positioning rule; position was skipped", null});
                    } catch (Throwable var53) {
                        j(new Object[]{var53, var16});
                        var3.b(new Object[]{var39 + " > Position", d_String(new Object[]{var53, var18}), var53});
                    }
                } else if (var43 != var44) {
                    var3.b(new Object[]{var39 + " > Position", "X and Y must both be present; position was skipped", null});
                } else if (var43) {
                    try {
                        label128: {
                            float var58 = MathUtil5.a_float_____________(new Object[]{this.a_String_(new Object[]{var40.get("X"), var32}), var24});
                            float var60 = MathUtil5.a_float_____________(new Object[]{this.a_String_(new Object[]{var40.get("Y"), var32}), var24});

                            label129: {
                                {
                                    if (var40.has("Anchor")) {
                                        try {
                                            TrajectoryType var10001 = TrajectoryType.valueOf(this.a_String_(new Object[]{var40.get("Anchor"), var32}));
                                            Object[] var10006 = new Object[]{null, null, null, var12};
                                            var10006[2] = var60;
                                            var10006[1] = var58;
                                            var10006[0] = var10001;
                                            var2.g(var10006);
                                            var3.c(new Object[]{3});
                                            break label128;
                                        } catch (IllegalArgumentException var54) {
                                            break label129;
                                        }
                                    }
                                }

                                Object[] var10005 = new Object[]{null, null, var10};
                                var10005[1] = var60;
                                var10005[0] = var58;
                                var2.a_void_______(var10005);
                                var3.c(new Object[]{2});
                                break label128;
                            }

                            var3.b(new Object[]{var39 + " > Anchor", "unknown anchor; position was skipped", null});
                        }
                    } catch (Throwable var56) {
                        j(new Object[]{var56, var16});
                        var3.b(new Object[]{var39 + " > Position", d_String(new Object[]{var56, var18}), var56});
                    }
                }

                if (var40.has("Enabled")) {
                    try {
                        boolean var59 = MathUtil5.a_boolean________(new Object[]{this.a_String_(new Object[]{var40.get("Enabled"), var32}), var28});
                        if (var59 != var2.e_boolean(new Object[]{var34})) {
                            boolean var61 = var2.e_boolean(new Object[]{var34});

                            try {
                                var2.j(new Object[]{var30});
                            } catch (Throwable var50) {
                                j(new Object[]{var50, var16});
                                Object[] var63 = new Object[]{null, null, var50, var14};
                                var63[1] = var61;
                                var63[0] = var2;
                                boolean var62 = this.b_boolean_(var63);
                                String var49 = var62 ? "HUD lifecycle failed; previous state was restored" : "HUD lifecycle failed; state may be inconsistent";
                                var3.b(new Object[]{var39 + " > Enabled", var49, var50});
                                return;
                            }
                        }

                        var3.c(new Object[]{1});
                    } catch (Throwable var51) {
                        j(new Object[]{var51, var16});
                        var3.b(new Object[]{var39 + " > Enabled", d_String(new Object[]{var51, var18}), var51});
                    }
                }
            }
        }
    }

    private boolean b_boolean_(Object[] var1) {
        HudElement var5 = (HudElement)var1[0];
        boolean var4 = (Boolean)var1[1];
        Throwable var6 = (Throwable)var1[2];
        long var2 = (Long)var1[3];
        var2 = 96496729806863L ^ var2;
        long var7 = var2 ^ 113891817421082L;
        long var9 = var2 ^ 94173269318428L;
        long var11 = var2 ^ 33340080713105L;

        try {
            if (var5.e_boolean(new Object[]{var9}) != var4) {
                var5.j(new Object[]{var7});
            }

            return var5.e_boolean(new Object[]{var9}) == var4;
        } catch (Throwable var15) {
            j(new Object[]{var15, var11});
            var6.addSuppressed(var15);
            return false;
        }
    }

    private void f(Object[] var1) {
        JsonObject var5 = (JsonObject)var1[0];
        Setting var2 = (Setting)var1[1];
        String var8 = (String)var1[2];
        Set var7 = (Set)var1[3];
        RenderHelper11 var6 = (RenderHelper11)var1[4];
        long var3 = (Long)var1[5];
        var3 = 96496729806863L ^ var3;
        long var9 = var3 ^ 107052151354858L;
        long var11 = var3 ^ 18849944105048L;
        long var13 = var3 ^ 70634159414031L;
        long var15 = var3 ^ 128299157553096L;
        if (var2 != null && var2.getName() != null && !var2.getName().isBlank()) {
            String var18 = var2.getName();
            if (!var7.add(var18)) {
                var6.b(new Object[]{var8 + " > " + var18, "duplicate registered setting name", null});
            } else if (var5.has(var18)) {
                String var19 = var8 + " > " + var18;

                try {
                    MathUtil5.a(new Object[]{var2, this.a_String_(new Object[]{var5.get(var18), var11}), var9});
                    var6.c(new Object[]{1});
                } catch (Throwable var21) {
                    j(new Object[]{var21, var13});
                    var6.b(new Object[]{var19, d_String(new Object[]{var21, var15}), var21});
                }
            }
        } else {
            var6.a(new Object[]{var8, "an unnamed setting was skipped", null});
        }
    }

    private void g(Object[] var1) {
        JsonObject var2 = (JsonObject)var1[0];
        Setting var7 = (Setting)var1[1];
        String var5 = (String)var1[2];
        RenderHelper11 var6 = (RenderHelper11)var1[3];
        long var3 = (Long)var1[4];
        var3 = 96496729806863L ^ var3;
        long var8 = var3 ^ 121466085324506L;
        long var10 = var3 ^ 111294525115929L;
        long var12 = var3 ^ 89794695424734L;
        if (var7 != null && var7.getName() != null && !var7.getName().isBlank()) {
            String var15 = var5 + " > " + var7.getName();
            if (var2.has(var7.getName())) {
                var6.b(new Object[]{var15, "duplicate registered setting name", null});
            } else {
                try {
                    var2.addProperty(var7.getName(), this.b_String(new Object[]{var7, var8}));
                    var6.c(new Object[]{1});
                } catch (Throwable var17) {
                    j(new Object[]{var17, var10});
                    var6.b(new Object[]{var15, d_String(new Object[]{var17, var12}), var17});
                }
            }
        } else {
            var6.a(new Object[]{var5, "an unnamed setting was skipped", null});
        }
    }

    private JsonObject a_JsonObject(Object[] var1) {
        String var2 = (String)var1[0];
        RenderHelper11 var5 = (RenderHelper11)var1[1];
        long var3 = (Long)var1[2];
        var3 = 96496729806863L ^ var3;
        long var6 = var3 ^ 44758310302231L;
        if (var2 != null && !var2.isBlank()) {
            if (var2.length() > 100000) {
                var5.b(new Object[]{"Config", "downloaded config is too large", null});
                return null;
            } else if (this.c_boolean(new Object[]{var2, var6})) {
                var5.b(new Object[]{"Config", "downloaded config is nested too deeply", null});
                return null;
            } else {
                JsonObject var9;
                try {
                    var9 = a.fromJson(var2, JsonObject.class);
                } catch (Exception var8) {
                    var5.b(new Object[]{"Config", "downloaded config is not valid JSON", var8});
                    return null;
                }

                if (var9 == null) {
                    var5.b(new Object[]{"Config", "downloaded config contained JSON null", null});
                    return null;
                } else {
                    return var9;
                }
            }
        } else {
            var5.b(new Object[]{"Config", "downloaded config was empty", null});
            return null;
        }
    }

    private boolean c_boolean(Object[] var1) {
        String var4 = (String)var1[0];
        Event.b();
        int var6 = 0;
        boolean var7 = false;
        boolean var8 = false;

        for (int var9 = 0; var9 < var4.length(); var9++) {
            char var10 = var4.charAt(var9);
            if (var7) {
                if (var8) {
                    var8 = false;
                } else if (var10 == '\\') {
                    var8 = true;
                } else if (var10 == '"') {
                    var7 = false;
                }
            } else if (var10 == '"') {
                var7 = true;
            } else if (var10 == '{' || var10 == '[') {
                if (++var6 > 64) {
                    return true;
                }
            } else if (var10 == '}' || var10 == ']') {
                var6--;
            }
        }

        return false;
    }

    private JsonObject b_JsonObject(Object[] var1) {
        JsonElement var2 = (JsonElement)var1[0];
        String var6 = (String)var1[1];
        RenderHelper11 var3 = (RenderHelper11)var1[2];
        if (var2 == null || var2.isJsonNull()) {
            var3.b(new Object[]{var6, "section is null", null});
            return null;
        } else if (!var2.isJsonObject()) {
            var3.b(new Object[]{var6, "section must be a JSON object", null});
            return null;
        } else {
            return var2.getAsJsonObject();
        }
    }

    private String a_String_(Object[] var1) {
        JsonElement var2 = (JsonElement)var1[0];
        if (var2 == null || var2.isJsonNull()) {
            throw new IllegalArgumentException("value is null");
        } else if (!var2.isJsonPrimitive()) {
            throw new IllegalArgumentException("expected a single value");
        } else {
            return var2.getAsString();
        }
    }

    private void h(Object[] var1) {
        JsonObject var3 = (JsonObject)var1[0];
        Set var2 = (Set)var1[1];
        String var4 = (String)var1[2];
        String var7 = (String)var1[3];
        RenderHelper11 var8 = (RenderHelper11)var1[4];
        Event.b();

        for (Entry var11 : var3.entrySet()) {
            if (!var2.contains(var11.getKey())) {
                String var12 = var4.isEmpty() ? (String)var11.getKey() : var4 + " > " + (String)var11.getKey();
                var8.a(new Object[]{var12, var7 + " is not available in this version; skipped", null});
            }
        }
    }

    private String b_String(Object[] var1) {
        Setting var2 = (Setting)var1[0];
        long var3 = (Long)var1[1];
        var3 = 96496729806863L ^ var3;
        long var5 = var3 ^ 54118292651812L;
        if (var2 instanceof ColorSetting var18) {
            Color var19 = (Color)var18.getValue();
            if (var19 == null) {
                throw new IllegalStateException("color is null");
            } else {
                return var19.getRed() + "." + var19.getGreen() + "." + var19.getBlue() + "." + var19.getAlpha();
            }
        } else if (var2 instanceof KeybindSetting var17) {
            if (var17.getValue() == null) {
                throw new IllegalStateException("bind is null");
            } else {
                return var17.getValue() + "." + var17.isToggleMode();
            }
        } else if (var2 instanceof MultiSetting var16) {
            StringBuilder var9 = new StringBuilder();

            for (String var13 : var16.getOptions()) {
                if (!var13.isEmpty()) {
                    var9.append(var13).append(".").append(var16.isSelected(new Object[]{var13, var5})).append("&");
                }
            }

            return var9.toString();
        } else if (var2 instanceof RangeSetting var15) {
            return var15.getLow() + "|" + var15.getHigh();
        } else {
            Object var8 = var2.getValue();
            if (var8 == null) {
                throw new IllegalStateException("value is null");
            } else {
                return var8.toString();
            }
        }
    }

    private static void i(Object[] var0) {
        RenderHelper11 var1 = (RenderHelper11)var0[0];
        long var2 = (Long)var0[1];
        var2 = 96496729806863L ^ var2;
        long var4 = var2 ^ 72704133099798L;
        long var6 = var2 ^ 12490493998071L;
        if (!"load".equals(var1.a) && (var1.a_boolean__________________________________________(new Object[]{var4}) || var1.e != 0)) {
            Runnable var9 = () -> {
                if (Prestige.v != null) {
                    String var8 = "load".equals(var1.a) ? "loaded" : "saved";
                    TpsTimer var10000 = Prestige.v;
                    String var10001 = "Config " + var8;
                    int var10002 = var1.d;
                    int var10003 = var1.e;
                    int var10004 = var1.b;
                    Object[] var10008 = new Object[]{null, null, null, var8, 114751876588817L};
                    var10008[2] = var10004;
                    var10008[1] = var10003;
                    var10008[0] = var10002;
                    String var9x = c_String(var10008);
                    long var10x = m;
                    Object[] var10007 = new Object[]{
                        null,
                        null,
                        null,
                        var1.d > 0 ? NotificationType.ERROR : (var1.e > 0 ? NotificationType.WARNING : NotificationType.SUCCESS),
                        104881728073391L
                    };
                    var10007[2] = var10x;
                    var10007[1] = var9x;
                    var10007[0] = var10001;
                    var10000.a(var10007);
                }
            };

            try {
                MinecraftClient var10 = MinecraftClient.getInstance();
                if (var10 != null && !var10.isOnThread()) {
                    var10.execute(var9);
                } else {
                    var9.run();
                }
            } catch (Throwable var11) {
                j(new Object[]{var11, var6});
            }
        }
    }

    public static String c_String(Object[] var0) {
        int var2 = (Integer)var0[0];
        int var6 = (Integer)var0[1];
        int var1 = (Integer)var0[2];
        String var5 = (String)var0[3];
        return var2 + " errors, " + var6 + " skipped, " + var1 + " " + var5;
    }

    private static String d_String(Object[] var0) {
        Throwable var3 = (Throwable)var0[0];
        Event.b();
        String var5 = var3.getMessage();
        return var5 != null && !var5.isBlank() ? var5 : var3.getClass().getSimpleName();
    }

    private static void j(Object[] var0) {
        Throwable var1 = (Throwable)var0[0];
        if (var1 instanceof ThreadDeath var6) {
            throw var6;
        } else if (var1 instanceof VirtualMachineError var5) {
            throw var5;
        } else {
            if (var1 instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
