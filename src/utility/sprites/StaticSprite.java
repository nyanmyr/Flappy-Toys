package utility.sprites;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class StaticSprite extends JComponent {

    protected BufferedImage image;
    private float opacity = 100.f; // default fully opaque

    public StaticSprite(BufferedImage image) {
        this.image = image;
        setSize(image.getWidth(), image.getHeight());
        setOpaque(false); // transparent background
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            float alpha = opacity / 100.0f;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g2d.dispose();
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        setSize(image.getWidth(), image.getHeight());
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setOpacity(float opacity) {
        // clamp between 0 and 100
        this.opacity = Math.max(0.f, Math.min(100.f, opacity));
        repaint();
    }

    public float getOpacity() {
        return opacity;
    }

    public int getCenterX() {
        return getX() + (getWidth() / 2);
    }
}
