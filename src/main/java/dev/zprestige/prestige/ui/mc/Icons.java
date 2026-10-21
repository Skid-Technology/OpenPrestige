package dev.zprestige.prestige.ui.mc;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL21;

/** Loads the PNG icons into GL textures (ids are passed straight to ImGui as ImTextureID). */
final class Icons {
    private Icons() {}

    static final String[] FILES = {
        "logo.png", "categories/combat.png", "categories/mace.png", "categories/misc.png", "categories/movement.png",
        "categories/spear.png", "categories/visual.png", "other/menu.png", "other/themes.png", "other/configs.png",
        "other/social.png", "other/keyboard.png"};

    static long[] load() throws Exception {
        long[] ids = new long[FILES.length];
        int prevUnit = GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        int prev = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
        int prevPbo = GL11.glGetInteger(GL21.GL_PIXEL_UNPACK_BUFFER_BINDING);
        int prevRow = GL11.glGetInteger(GL11.GL_UNPACK_ROW_LENGTH), prevSkipP = GL11.glGetInteger(GL11.GL_UNPACK_SKIP_PIXELS),
            prevSkipR = GL11.glGetInteger(GL11.GL_UNPACK_SKIP_ROWS), prevAlign = GL11.glGetInteger(GL11.GL_UNPACK_ALIGNMENT);
        GL15.glBindBuffer(GL21.GL_PIXEL_UNPACK_BUFFER, 0);
        GL11.glPixelStorei(GL11.GL_UNPACK_ROW_LENGTH, 0);
        GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_PIXELS, 0);
        GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_ROWS, 0);
        for (int i = 0; i < FILES.length; i++) {
            BufferedImage img;
            try (InputStream in = Icons.class.getResourceAsStream("/prestige_ui/icons/" + FILES[i])) {
                img = ImageIO.read(in);
            }
            int w = img.getWidth(), h = img.getHeight();
            int[] px = img.getRGB(0, 0, w, h, null, 0, w);
            ByteBuffer buf = BufferUtils.createByteBuffer(w * h * 4);
            for (int p : px) {
                buf.put((byte) ((p >> 16) & 255)).put((byte) ((p >> 8) & 255)).put((byte) (p & 255)).put((byte) ((p >>> 24) & 255));
            }
            buf.flip();
            int tex = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, w, h, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
            ids[i] = tex;
        }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, prev);
        GL15.glBindBuffer(GL21.GL_PIXEL_UNPACK_BUFFER, prevPbo);
        GL11.glPixelStorei(GL11.GL_UNPACK_ROW_LENGTH, prevRow);
        GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_PIXELS, prevSkipP);
        GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_ROWS, prevSkipR);
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, prevAlign);
        GL13.glActiveTexture(prevUnit);
        return ids;
    }
}
