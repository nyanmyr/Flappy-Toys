package utility;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class Sprite extends JComponent {

    private BufferedImage image;

    public Sprite(BufferedImage image) {
        this.image = image;
        setSize(image.getWidth(), image.getHeight());
        setOpaque(false); // transparent background
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        setSize(image.getWidth(), image.getHeight());
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
