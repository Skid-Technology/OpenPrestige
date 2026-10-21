package dev.zprestige.prestige.ui.mc;

import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Skid extends JWindow implements ActionListener, MouseListener, MouseMotionListener {
    private static final int WIDTH = 440;
    private static final int HEIGHT = 270;
    private static final int DURATION = 4000;
    private static final String DISCORD_URL = "https://discord.gg/5FB5hqJacr";

    private final CountDownLatch latch;
    private final Timer timer;
    private final long startTime;
    private float angle = 0.0f;

    public Skid(CountDownLatch latch) {
        this.latch = latch;
        this.startTime = System.currentTimeMillis();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.timer = new Timer(16, this);
    }

    public void startSplash() {
        setVisible(true);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        // 1. Black Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        // 2. Dual-Ring Spinner (Opposing 90-degree arcs, pure white)
        float dia = 54.0f;
        float sx = (WIDTH - dia) / 2.0f;
        float sy = 55.0f;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        g2.drawArc((int) sx, (int) sy, (int) dia, (int) dia, (int) angle, 90);
        g2.drawArc((int) sx, (int) sy, (int) dia, (int) dia, (int) (angle + 180.0f), 90);

        // 3. Skid Solutions Text Fade-in
        long elapsed = System.currentTimeMillis() - startTime;
        float prog = Math.min(1.0f, elapsed / 600.0f);
        int alpha = (int) (prog * 255.0f);
        g2.setColor(new Color(255, 255, 255, alpha));
        g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        FontMetrics fm = g2.getFontMetrics();
        String title = "Skid Solutions";
        int tx = (WIDTH - fm.stringWidth(title)) / 2;
        int ty = (int) (sy + dia + 30.0f) + fm.getAscent();
        g2.drawString(title, tx, ty);

        // 4. Discord Link
        g2.setColor(new Color(180, 180, 180));
        g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        g2.drawString(DISCORD_URL, 14, HEIGHT - 14);

        g2.dispose();
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed >= DURATION) {
            timer.stop();
            dispose();
            latch.countDown();
        } else {
            angle = (angle + 6.5f) % 360.0f;
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() >= 14 && e.getX() <= 280 && e.getY() >= HEIGHT - 30) {
            try {
                Desktop.getDesktop().browse(new URI(DISCORD_URL));
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= 14 && e.getX() <= 280 && e.getY() >= HEIGHT - 30) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e) {}

    private static File getPrefFile() {
        String tmp = System.getProperty("java.io.tmpdir");
        return new File(tmp, "skid_splash_pref.txt");
    }

    private static String readPref() {
        File f = getPrefFile();
        if (!f.exists()) return "unset";
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            return (line != null) ? line.trim().toLowerCase() : "unset";
        } catch (Exception e) {
            return "unset";
        }
    }

    private static void savePref(String val) {
        try (FileWriter fw = new FileWriter(getPrefFile())) {
            fw.write(val);
        } catch (Exception ignored) {}
    }

    public static void run() {
        if (java.awt.GraphicsEnvironment.isHeadless()) return;
        String pref = readPref();
        if ("no".equals(pref)) {
            return;
        }

        final CountDownLatch latch = new CountDownLatch(1);
        Skid ui = new Skid(latch);
        ui.startSplash();

        try {
            latch.await(DURATION + 3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {}

        if ("unset".equals(pref)) {
            int res = JOptionPane.showConfirmDialog(
                null,
                "Do you want to see this splash screen again?",
                "Skid Solutions",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (res == JOptionPane.YES_OPTION) {
                savePref("yes");
            } else {
                savePref("no");
            }
        }
    }
}
