package levels.backgrounds;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import utility.sprites.StaticSprite;

public class BrickLandBackground extends Background {

    public BrickLandBackground() {
        LoadSprite();
    }

    @Override
    public final void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/backgrounds/brickland_bg.jpg");
            if (resource != null) {
                Image img = ImageIO.read(resource);
                java.awt.image.BufferedImage buffered
                        = new java.awt.image.BufferedImage(
                                img.getWidth(null),
                                img.getHeight(null),
                                java.awt.image.BufferedImage.TYPE_INT_ARGB
                        );
                Graphics2D g2d = buffered.createGraphics();
                g2d.drawImage(img, 0, 0, null);
                g2d.dispose();

                sprite = new StaticSprite(buffered);
                sprite.setOpaque(false);

                sprite.setBounds(0, 0, 800, 600);
            } else {
                throw new RuntimeException("Image resource not found: /brickland_bg.jpg");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }
    
}
