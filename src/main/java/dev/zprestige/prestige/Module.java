package dev.zprestige.prestige;

import dev.zprestige.prestige.annotation.Chainable;
import dev.zprestige.prestige.annotation.NativeId;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.render.RenderContract2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Module extends SettingHolder implements RenderContract2 {
    private final List coreSettings = new ArrayList();
    private final BoolSetting enabledSetting = new BoolSetting("Enabled", false);
    private String name;
    private final KeybindSetting keybind;
    private Category category;
    private String description;
    private boolean lastEnabled;
    private volatile List allSettingsCache;

    public Module() {
        this.keybind = new KeybindSetting("Bind", -1).setDescription_KeybindSetting("Keybind for " + this.name);
        this.coreSettings.add(this.enabledSetting);
        this.coreSettings.add(this.keybind);
    }

    public Module(String var1, String var2, int var3) {
        this();
        Event.b();
        this.name = var1;
        this.description = var2;

        for (Category var10 : Category.values()) {
            if (var10.ordinal() == var3) {
                this.category = var10;
                break;
            }
        }
    }

    @NativeId("")
    public void a(Object var1, String var2, String var3, int var4) {
        if (var1 != null) {
            this.settings.add((Setting)var1);
            this.allSettingsCache = null;
        } else {
            this.name = var2;
            this.description = var3;
            this.category = Arrays.stream(Category.values()).filter(lpL71_0 -> ((dev.zprestige.prestige.Category)lpL71_0).ordinal() == var4).findFirst().orElse(this.category);
        }
    }

    @Chainable
    public boolean isEnabled() {
        return (Boolean)this.enabledSetting.getValue();
    }

    public int getKey(Object[] var1) {
        return (Integer)this.keybind.getValue();
    }

    public KeybindSetting getKeybind(Object[] var1) {
        return this.keybind;
    }

    public void toggle(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 55958956532363L;
        long var6 = var2 ^ 15872977247914L;
        long var8 = var2 ^ 22594282158762L;
        long var10 = var2 ^ 37299566365261L;
        long var12 = var2 ^ 75993416544364L;
        long var14 = var2 ^ 86929222885577L;
        long var16 = var2 ^ 79146057582229L;
        this.enabledSetting.setValue(!this.isEnabled());
        this.setLastEnabled(new Object[]{this.isEnabled()});
        if (this.isEnabled()) {
            Prestige.eventBus.register(new Object[]{this, var6});
            if (b.world != null && b.player != null) {
                this.onEnable(new Object[]{var8});
            } else {
                Prestige.moduleManager.queueEnable(new Object[]{this, var12});
            }
        } else {
            Prestige.eventBus.unregister(new Object[]{this, var4});
            if (b.world != null && b.player != null) {
                this.onDisable(new Object[]{var14});
            } else {
                Prestige.moduleManager.queueDisable(new Object[]{this, var16});
            }
        }

        ModuleNotificationsElement.r(new Object[]{this, var10});
    }

    public void applyState(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 25781655103684L ^ var2;
        long var4 = var2 ^ 120644081699938L;
        long var6 = var2 ^ 89278968246339L;
        long var8 = var2 ^ 83365083665475L;
        long var10 = var2 ^ 139299437470884L;
        long var12 = var2 ^ 28865644895877L;
        long var14 = var2 ^ 17947047808544L;
        long var16 = var2 ^ 27105383311484L;
        if (this.isEnabled()) {
            Prestige.eventBus.register(new Object[]{this, var6});
            if (b.world != null && b.player != null) {
                this.onEnable(new Object[]{var8});
            } else {
                Prestige.moduleManager.queueEnable(new Object[]{this, var12});
            }
        } else {
            Prestige.eventBus.unregister(new Object[]{this, var4});
            if (b.world != null && b.player != null) {
                this.onDisable(new Object[]{var14});
            } else {
                Prestige.moduleManager.queueDisable(new Object[]{this, var16});
            }
        }

        ModuleNotificationsElement.r(new Object[]{this, var10});
    }

    public void dispose(Object[] var1) {
        this.name = null;
        Event.b();
        this.description = null;
        ArrayList var5 = new ArrayList();
        var5.addAll(this.settings);
        var5.addAll(this.coreSettings);

        for (Setting var7 : (Iterable<Setting>)(var5)) {
            var7.setName(new Object[]{null});
            var7.setDescription(null);
            var7.setValue(null);
        }
    }

    public void onEnable(Object[] var1) {
    }

    public void onDisable(Object[] var1) {
    }

    public boolean b(Object[] var1) {
        return true;
    }

    public String getInfo(Object[] var1) {
        return "";
    }

    public List getCoreSettings(Object[] var1) {
        return this.coreSettings;
    }

    @Chainable
    public String getName() {
        return this.name;
    }

    public void setName(Object[] var1) {
        String var2 = (String)var1[0];
        this.name = var2;
    }

    @Chainable
    public String getDescription() {
        return this.description;
    }

    public void setDescription(Object[] var1) {
        String var2 = (String)var1[0];
        this.description = var2;
    }

    @Chainable
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Object[] var1) {
        Category var2 = (Category)var1[0];
        this.category = var2;
    }

    public boolean getLastEnabled(Object[] var1) {
        return this.lastEnabled;
    }

    public void setLastEnabled(Object[] var1) {
        boolean var2 = (Boolean)var1[0];
        this.lastEnabled = var2;
    }

    @Chainable
    public List getSettings() {
        Event.b();
        List var4 = this.allSettingsCache;
        if (var4 == null) {
            ArrayList var5 = new ArrayList(this.coreSettings.size() + this.settings.size());
            var5.addAll(this.coreSettings);
            var5.addAll(this.settings);
            var4 = List.copyOf(var5);
            this.allSettingsCache = var4;
        }

        return var4;
    }

    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Any);
    }

    @Chainable
    public int getGamemodeMask() {
        Set var6 = this.getGamemodes(new Object[]{33917734280704L});
        Event.b();
        int var7 = 0;

        for (Gamemode var9 : (Iterable<Gamemode>)(var6)) {
            if (var9 != Gamemode.Any) {
                var7 |= 1 << var9.ordinal();
            }
        }

        return var7;
    }

    private static Field e(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field f(Class var0, String var1, Class var2) {
        Field var3 = e(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = f(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method e(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method f(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = e(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = f(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }

    private static MatchException b(MatchException var0) {
        return var0;
    }
}
