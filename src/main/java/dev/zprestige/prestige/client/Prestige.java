package dev.zprestige.prestige.client;

import dev.zprestige.prestige.EventBus;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.ModuleManager;
import dev.zprestige.prestige.Native;
import dev.zprestige.prestige.Setting;
import dev.zprestige.prestige.combat.AimManager;
import dev.zprestige.prestige.combat.DamageUtil;
import dev.zprestige.prestige.combat.RotationManager;
import dev.zprestige.prestige.combat.TargetManager;
import dev.zprestige.prestige.combat.TargetTracker;
import dev.zprestige.prestige.config.ConfigManager;
import dev.zprestige.prestige.event.EventManager;
import dev.zprestige.prestige.event.TickEvent24;
import dev.zprestige.prestige.event.TickEvent25;
import dev.zprestige.prestige.event.TickEvent27;
import dev.zprestige.prestige.gui.ClickGuiScreen;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.input.MouseManager;
import dev.zprestige.prestige.network.PacketManager;
import dev.zprestige.prestige.network.PacketQueue;
import dev.zprestige.prestige.network.PingManager;
import dev.zprestige.prestige.render.ESPUtil;
import dev.zprestige.prestige.render.FontManager;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderContract5;
import dev.zprestige.prestige.render.RenderContract6;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.render.RenderUtil;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.social.FriendManager;
import dev.zprestige.prestige.util.BindManager;
import dev.zprestige.prestige.util.Timer;
import dev.zprestige.prestige.util.TpsTimer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;

public class Prestige implements ModInitializer {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    public static EventBus eventBus;
    public static FontManager c;
    public static ModuleManager moduleManager;
    public static ConfigManager e;
    public static DamageUtil f;
    public static FriendManager g;
    public static Timer h;
    public static TargetManager i;
    public static AimManager j;
    public static ClickGuiScreen k;
    public static HudEditorScreen l;
    public static EventManager m;
    public static TargetTracker n;
    public static Renderer2D o;
    public static RotationManager p;
    public static BindManager q;
    public static PingManager r;
    public static MouseManager s;
    public static PacketManager t;
    public static PacketQueue u;
    public static TpsTimer v;
    public static boolean w = false;
    private static Setting[] x;

    public void init() {
        b();
        boolean var34 = false;
        try {
        if (a.compareAndSet(false, true)) {
            eventBus = new EventBus();
            moduleManager = new ModuleManager(121395157283000L);
            Native.a(moduleManager.getModules());
            c = new FontManager();
            e = new ConfigManager();
            f = new DamageUtil(41691316515293L);
            g = new FriendManager();
            h = new Timer(111601052018604L);
            i = new TargetManager(137451367958631L);
            j = new AimManager();
            k = new ClickGuiScreen();
            var34 = true;
            l = new HudEditorScreen(90003821237034L);
            m = new EventManager(53157928465092L);
            n = new TargetTracker(139559845293095L);
            o = new Renderer2D(112473680974252L);
            p = new RotationManager(101976830556898L);
            q = new BindManager(139206864016440L);
            r = new PingManager(57309624251050L);
            t = new PacketManager(117193470404248L);
            u = new PacketQueue(87229227786379L);
            s = new MouseManager(82291833307369L);
            v = new TpsTimer(55175676787666L);
            RenderUtil.g(new Object[]{Thread.currentThread()});
            RenderContract5.a
                .register(
                    (RenderContract6)(lpL44_0, lpL44_1) -> {
                        b();
                        if (!w) {
                            if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null) {
                                if ((RenderContract2.b.currentScreen == null || RenderContract2.b.currentScreen instanceof ChatScreen)
                                    && eventBus.hasListeners(new Object[]{TickEvent25.class, 135978089436075L})) {
                                    new TickEvent25(((dev.zprestige.prestige.render.RenderHelper8)lpL44_0), ((dev.zprestige.prestige.data.MatrixCameraData)lpL44_1)).b_boolean__(new Object[]{122499962143880L});
                                }

                                new TickEvent24(((dev.zprestige.prestige.render.RenderHelper8)lpL44_0), ((dev.zprestige.prestige.data.MatrixCameraData)lpL44_1)).b_boolean__(new Object[]{122499962143880L});
                                RenderState.b(new Object[]{93665931432117L});
                            }
                        }
                    }
                );
            RenderContract5.b.register((RenderContract6)(lpL43_0, lpL43_1) -> {
                b();
                if (!w) {
                    if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().player != null) {
                        MatrixStack.a(new Object[]{14373979246969L});
                        new TickEvent27(((dev.zprestige.prestige.render.RenderHelper8)lpL43_0), ((dev.zprestige.prestige.data.MatrixCameraData)lpL43_1)).b_boolean__(new Object[]{122499962143880L});
                    }
                }
            });
        }
        } catch (Throwable var35) {
            if (!var34) {
                a.set(false);
            }

            var35.printStackTrace();
        }
    }

    public static boolean a(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 9499999619609L ^ var1;
        long var3 = var1 ^ 18103143018681L;
        b();
        return ESPUtil.e || eventBus != null && eventBus.hasListeners(new Object[]{TickEvent27.class, var3});
    }

    public static boolean b(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 9499999619609L ^ var1;
        long var3 = var1 ^ 11198839193506L;
        long var5 = var1 ^ 39355797438358L;
        long var7 = var1 ^ 65000825783606L;
        long var9 = var1 ^ 20328730886810L;
        long var11 = var1 ^ 20640679731384L;
        b();
        return eventBus == null
            ? false
            : a(new Object[]{var5})
                || eventBus.hasListeners(new Object[]{TickEvent25.class, var7})
                || eventBus.hasListenerOfType(new Object[]{TickEvent24.class, Module.class, var11})
                || l != null && l.c_boolean_(new Object[]{var3})
                || v != null && v.a_boolean______________________________________________(new Object[]{var9});
    }

    public static void a_void(Object[] var0) {
        long var1 = (Long)var0[0];
        var1 = 9499999619609L ^ var1;
        long var3 = var1 ^ 34804161661929L;
        b();
        if (!w && eventBus != null && RenderContract2.b.world != null && RenderContract2.b.player != null) {
            new TickEvent24(RenderUtil.b, null).b_boolean__(new Object[]{var3});
        }
    }

    static {
        b(null);
    }

    public static void b(Setting[] var0) {
        x = var0;
    }

    public static Setting[] b() {
        return x;
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

    @Override
    public void onInitialize() {
        this.init();
    }

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
