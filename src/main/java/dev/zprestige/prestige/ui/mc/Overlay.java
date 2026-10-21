package dev.zprestige.prestige.ui.mc;

import imgui.ImFont;
import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import dev.zprestige.prestige.ui.Data;
import dev.zprestige.prestige.ui.Input;
import dev.zprestige.prestige.ui.Lib;
import dev.zprestige.prestige.ui.Theme;
import dev.zprestige.prestige.ui.Ui;

/** Draws the ported ClickGUI over the finished frame (called from the RenderSystem.flipFrame mixin). */
public final class Overlay {
    private Overlay() {}

    public static volatile boolean visible;
    /** Mouse position in framebuffer pixels (polled from GLFW while visible; dev commands may override it). */
    public static volatile double mousePxX = -1e4, mousePxY = -1e4;

    // ---- window placement: fractions of the free space (0 = left/top edge, 1 = right/bottom edge), persisted
    static float fracX = 0.5f, fracY = 0.5f;
    static boolean posLoaded, prevDragging;
    /** config "allowUpscale": true lets the UI scale above 1 when the window is larger than 1100x700. */
    static boolean allowUpscale;
    static double dragLastX, dragLastY;
    static boolean wasGrabbed;
    static double lastRawX = Double.NaN, lastRawY = Double.NaN;
    public static volatile long lastCloseNanos;

    static Path posFile() {
        try { return net.fabricmc.loader.api.FabricLoader.getInstance().getConfigDir().resolve("prestige_ui.json"); } catch (Throwable t) { return null; }
    }
    static void loadPos() {
        if (posLoaded) return;
        posLoaded = true;
        try {
            Path f = posFile();
            if (f == null || !Files.exists(f)) return;
            String t = Files.readString(f);
            java.util.regex.Matcher mx = java.util.regex.Pattern.compile("\"x\"\\s*:\\s*(-?[0-9.]+)").matcher(t);
            java.util.regex.Matcher my = java.util.regex.Pattern.compile("\"y\"\\s*:\\s*(-?[0-9.]+)").matcher(t);
            if (mx.find()) fracX = Math.max(0f, Math.min(1f, Float.parseFloat(mx.group(1))));
            allowUpscale = java.util.regex.Pattern.compile("\"allowUpscale\"\\s*:\\s*true").matcher(t).find();
            if (my.find()) fracY = Math.max(0f, Math.min(1f, Float.parseFloat(my.group(1))));
        } catch (Throwable t) { System.out.println("[prestige-ui] loadPos " + t); }
    }
    static void savePos() {
        try {
            Path f = posFile();
            if (f != null) Files.writeString(f, "{\"x\": " + fracX + ", \"y\": " + fracY + ", \"allowUpscale\": " + allowUpscale + "}\n");
        } catch (Throwable t) { System.out.println("[prestige-ui] savePos " + t); }
    }

    // ---- open / close (no Screen involved: the game keeps rendering and updating underneath)
    public static void open() {
        if (visible) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        loadPos();
        wasGrabbed = mc.mouse.isCursorLocked();
        if (wasGrabbed) mc.mouse.unlockCursor();      // MouseHandler.releaseMouse
        net.minecraft.client.option.KeyBinding.unpressAll();              // KeyMapping.releaseAll: no stuck movement/attack keys
        pendingReset = true;     // Input state is reset in render() once the UI entries exist
        Input.ctrlDown = false;
        Input.clipboard = () -> { try { return MinecraftClient.getInstance().keyboard.getClipboard(); } catch (Throwable t) { return null; } };
        lastRawX = Double.NaN; lastRawY = Double.NaN; prevDragging = false;
        visible = true;
        pollCursor();
    }

    public static void close() {
        if (!visible) return;
        visible = false;
        lastCloseNanos = System.nanoTime();
        Input.close = false; Input.windowDragging = false; Input.mouseUp(0);
        MinecraftClient mc = MinecraftClient.getInstance();
        if (wasGrabbed && mc.currentScreen == null && !mc.mouse.isCursorLocked()) mc.mouse.lockCursor();   // grabMouse
        wasGrabbed = false;
    }

    /** Called every client tick: keep the cursor free while the UI is open. */
    public static void tick(MinecraftClient mc) {
        devOpenPoll();
        if (visible && mc.mouse.isCursorLocked()) { wasGrabbed = true; mc.mouse.unlockCursor(); }
    }

    /** Read the real cursor (window coords -> framebuffer pixels). */
    static void pollCursor() {
        try {
            Window w = MinecraftClient.getInstance().getWindow();
            double[] cx = new double[1], cy = new double[1];
            org.lwjgl.glfw.GLFW.glfwGetCursorPos(w.getHandle(), cx, cy);
            if (cx[0] == lastRawX && cy[0] == lastRawY) return;
            lastRawX = cx[0]; lastRawY = cy[0];
            mousePxX = cx[0] * w.getFramebufferWidth() / Math.max(1, w.getWidth());
            mousePxY = cy[0] * w.getFramebufferHeight() / Math.max(1, w.getHeight());
        } catch (Throwable t) { /* ignore */ }
    }

    static void syncMouse() {
        pollCursor();
        Input.mx = (float) ((mousePxX - Lib.ox) / Lib.scale);
        Input.my = (float) ((mousePxY - Lib.oy) / Lib.scale);
    }

    static void afterEvent() {
        if (Input.close) { Input.close = false; close(); }
    }

    // ---- events from the MouseHandler / KeyboardHandler mixins (all return true when consumed)
    public static boolean onMouseMove() { if (!visible) return false; pollCursor(); return true; }

    public static boolean onMouseButton(int button, int action) {
        if (!visible) return false;
        syncMouse();
        if (action == 1) Input.mouseDown(button); else if (action == 0) Input.mouseUp(button);
        afterEvent();
        return true;
    }

    public static boolean onScroll(double dy) {
        if (!visible) return false;
        syncMouse();
        Input.scroll(dy);
        return true;
    }

    public static boolean onKey(int key, int action) {
        if (!visible) return false;
        if (key == 341 || key == 345) Input.ctrlDown = action != 0;
        if (action == 1 || action == 2) { syncMouse(); Input.key(key); afterEvent(); }
        return true;
    }

    public static boolean onChar(int cp) {
        if (!visible) return false;
        Input.typed(cp);
        afterEvent();
        return true;
    }

    /** Dev aid: commands (move/down/up/scroll/esc/open/shot) are read from this file each frame, then it is deleted. */
    /** Dev command file; only active when -Dprestige.ui.cmd=<path> is set (off in release). */
    static final Path CMD = System.getProperty("prestige.ui.cmd") == null ? null : Path.of(System.getProperty("prestige.ui.cmd"));
    static String shotPath;
    static final java.util.ArrayList<String> QUEUE = new java.util.ArrayList<>();
    static int waitFrames;
    static boolean inited, failed, pendingReset;
    static ImGuiImplGl3 gl3;
    static float bakedScale = -1f;
    static long last = System.nanoTime();

    static final String[][] FONTS = {
        {"DMSans-Medium", "26"}, {"DMSans-Medium", "29"}, {"DMSans-Regular", "20"}, {"DMSans-Regular", "17"},
        {"DMSans-Regular", "14"}, {"DMSans-Medium", "18"}, {"DMSerifDisplay-Regular", "24"}, {"DMSerifDisplay-Italic", "24"},
        {"DMSerifDisplay-Regular", "34"}, {"DMSerifDisplay-Italic", "34"}, {"DMSans-Medium", "20"}};

    static byte[] res(String path) throws Exception {
        try (InputStream in = Overlay.class.getResourceAsStream(path)) {
            if (in == null) throw new IllegalStateException("missing resource " + path);
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            in.transferTo(o);
            return o.toByteArray();
        }
    }

    static void init() throws Exception {
        if (inited) return;
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);
        gl3 = new ImGuiImplGl3();
        gl3.init("#version 150");
        Data.load(Overlay.class.getResourceAsStream("/prestige_ui/modules.tsv"), Overlay.class.getResourceAsStream("/prestige_ui/settings.tsv"));
        long[] icons = Icons.load();
        Ui.initEntries(icons);
        Theme.active = 1;
        inited = true;
    }

    /** (Re)bake the fonts at FONT_SIZE * scale. */
    static void bakeFonts(float scale) throws Exception {
        ImFontAtlas atlas = ImGui.getIO().getFonts();
        atlas.clear();
        for (int i = 0; i < FONTS.length; i++) {
            float px = Math.max(7f, Math.round(Lib.FONT_SIZE[i] * scale));
            Lib.BAKED[i] = px;
            ImFontConfig cfg = new ImFontConfig();
            cfg.setPixelSnapH(true);
            Lib.FONT[i] = atlas.addFontFromMemoryTTF(res("/prestige_ui/fonts/" + FONTS[i][0] + ".ttf"), px, cfg);
            cfg.destroy();
        }
        atlas.build();
        gl3.destroyFontsTexture();
        gl3.createFontsTexture();
        bakedScale = scale;
    }

    /** Dev: a command file starting with "open" opens the overlay (the render hook only polls while visible). */
    static void devOpenPoll() {
        try {
            if (CMD == null || visible || !Files.exists(CMD)) return;
            java.util.List<String> ls = Files.readAllLines(CMD);
            if (ls.isEmpty() || !ls.get(0).trim().equals("open")) return;
            Files.delete(CMD);
            QUEUE.addAll(ls.subList(1, ls.size()));
            open();
        } catch (Throwable t) { System.out.println("[prestige-ui] devOpen " + t); }
    }

    static void pollCommands() {
        try {
            if (CMD == null) return;
            if (Files.exists(CMD)) { QUEUE.addAll(Files.readAllLines(CMD)); Files.delete(CMD); }
            if (QUEUE.isEmpty()) return;
            if (waitFrames > 0) { waitFrames--; return; }
            String l = QUEUE.remove(0);
            {
                String[] a = l.trim().split("\\s+");
                switch (a[0]) {
                    case "move": mousePxX = Lib.ox + Float.parseFloat(a[1]) * Lib.scale; mousePxY = Lib.oy + Float.parseFloat(a[2]) * Lib.scale;
                        Input.mx = Float.parseFloat(a[1]); Input.my = Float.parseFloat(a[2]); break;
                    case "down": Input.mouseDown(0); break;
                    case "up": Input.mouseUp(0); break;
                    case "scroll": Input.scroll(Double.parseDouble(a[1])); break;
                    case "esc": Input.escape(); afterEvent(); break;
                    case "key": Input.key(Integer.parseInt(a[1])); afterEvent(); break;
                    case "char": Input.typed(Integer.parseInt(a[1])); afterEvent(); break;
                    case "drag": mousePxX += Double.parseDouble(a[1]); mousePxY += Double.parseDouble(a[2]); break;
                    case "close": close(); break;
                    case "shot": shotPath = a[1]; break;
                    case "wait": waitFrames = Integer.parseInt(a[1]); break;
                    case "pointmod": {
                        float[] c = dev.zprestige.prestige.ui.DevCmd.toggleCenter(l.trim().substring(8).trim());
                        System.out.println("[prestige-ui] pointmod -> " + (c == null ? null : c[0] + "," + c[1]));
                        if (c != null) { mousePxX = Lib.ox + c[0] * Lib.scale; mousePxY = Lib.oy + c[1] * Lib.scale; Input.mx = c[0]; Input.my = c[1]; }
                        break;
                    }
                    case "state": System.out.println("[prestige-ui] STATE " + dev.zprestige.prestige.ui.DevCmd.state(l.trim().substring(5).trim())); break;
                    default: break;
                }
            }
        } catch (Throwable t) { System.out.println("[prestige-ui] cmd " + t); }
    }

    static void shoot(int w, int h) throws Exception {
        ByteBuffer buf = BufferUtils.createByteBuffer(w * h * 4);
        GL11.glReadBuffer(GL11.GL_BACK);
        GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
        GL11.glReadPixels(0, 0, w, h, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++) {
            int i = ((h - 1 - y) * w + x) * 4;
            img.setRGB(x, y, ((buf.get(i) & 255) << 16) | ((buf.get(i + 1) & 255) << 8) | (buf.get(i + 2) & 255));
        }
        javax.imageio.ImageIO.write(img, "png", new java.io.File(shotPath));
    }

    public static void render() {
        if (!visible || failed) return;
        try {
            init();
            Window w = MinecraftClient.getInstance().getWindow();
            int fbW = w.getFramebufferWidth(), fbH = w.getFramebufferHeight();
            if (fbW <= 0 || fbH <= 0) return;
            loadPos();
            float scale = Math.min((fbW - 24f) / 1100f, (fbH - 24f) / 700f);
            if (!(allowUpscale && fbW > 1100 && fbH > 700)) scale = Math.min(1f, scale);
            if (scale < 0.3f) scale = 0.3f;
            scale = Math.round(scale * 50f) / 50f;
            if (Math.abs(scale - bakedScale) > 0.001f) bakeFonts(scale);
            long now = System.nanoTime();
            float dt = Math.min(0.1f, (now - last) / 1e9f);
            last = now;
            ImGuiIO io = ImGui.getIO();
            io.setDisplaySize(fbW, fbH);
            io.setDeltaTime(Math.max(dt, 1e-4f));
            Lib.scale = scale;
            loadPos();
            pollCursor();
            pollCommands();
            float freeX = fbW - 1100f * scale, freeY = fbH - 700f * scale;
            float nx = freeX > 0 ? fracX * freeX : freeX * 0.5f, ny = freeY > 0 ? fracY * freeY : freeY * 0.5f;
            boolean drag = Input.windowDragging;
            if (drag && prevDragging) {
                nx += (float) (mousePxX - dragLastX); ny += (float) (mousePxY - dragLastY);
                if (freeX > 0) { nx = Math.max(0f, Math.min(freeX, nx)); fracX = nx / freeX; }
                if (freeY > 0) { ny = Math.max(0f, Math.min(freeY, ny)); fracY = ny / freeY; }
            }
            if (!drag && prevDragging) savePos();
            prevDragging = drag; dragLastX = mousePxX; dragLastY = mousePxY;
            Lib.ox = nx; Lib.oy = ny;
            Input.mx = (float) ((mousePxX - Lib.ox) / scale);
            Input.my = (float) ((mousePxY - Lib.oy) / scale);
            if (pendingReset) { pendingReset = false; Input.close = false; Input.windowDragging = false; Input.mouseUp(0); }
            Input.update(dt);
            gl3.newFrame();
            ImGui.newFrame();
            Lib.dl = ImGui.getForegroundDrawList();
            Ui.frame();
            ImGui.render();
            gl3.renderDrawData(ImGui.getDrawData());
            if (shotPath != null) { shoot(fbW, fbH); shotPath = null; }
            afterEvent();
        } catch (Throwable t) {
            failed = true;
            System.out.println("[prestige-ui] overlay failed: " + t);
            t.printStackTrace(System.out);
        }
    }
}
