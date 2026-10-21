package dev.zprestige.prestige.ui.mc;

import dev.zprestige.prestige.ui.Input;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

/** Opens the ported ClickGUI with the configured menu key (Input.menuKey, default RIGHT SHIFT); closing is handled by Input via the key mixin. */
public final class Entry implements ClientModInitializer {
    static boolean prev;
    private static int cachedVk = -1, cachedGlfw;

    @Override public void onInitializeClient() {
        try { Skid.run(); } catch (Throwable t) { System.out.println("[prestige-ui] splash " + t); }
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
        System.out.println("[prestige-ui] loaded (menu key opens the menu)");
    }

    /** Menu key (Windows VK, as stored by Input) -> GLFW key, or -1 when unmapped. */
    static int menuGlfwKey() {
        int vk = Input.menuKey;
        if (vk != cachedVk) {
            cachedGlfw = -1;
            for (int g = 32; g <= 348 && cachedGlfw < 0; g++) if (Input.toVk(g) == vk) cachedGlfw = g;
            cachedVk = vk;
        }
        return cachedGlfw;
    }

    static boolean bridgeTried;

    void tick(MinecraftClient mc) {
        if (!bridgeTried) { bridgeTried = true; dev.zprestige.prestige.ui.bridge.Bridge.bind(); }
        try {
            long h = mc.getWindow().getHandle();
            int key = menuGlfwKey();
            boolean down = key >= 0 && GLFW.glfwGetKey(h, key) == GLFW.GLFW_PRESS;
            boolean justClosed = System.nanoTime() - Overlay.lastCloseNanos < 400_000_000L;
            if (down && !prev && !Overlay.visible && !justClosed) Overlay.open();
            prev = down;
            Overlay.tick(mc);
        } catch (Throwable t) {
            System.out.println("[prestige-ui] tick " + t);
        }
    }
}
