package levels.parallaxes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import utility.sprites.StaticSprite;

public class IceCreamParallax extends Parallax {

    public IceCreamParallax(int offsetX, ParallaxLevel level) {
        super(offsetX, level);
        LoadSprite();
    }

    @Override
    public final void LoadSprite() {
        try {

            int offsetY = 0;
            
            java.net.URL resource;
            switch (level) {
                case LEVEL_1 -> {
                    resource = getClass().getResource("/resources/parallaxes/icecream_parallax1.png");
                }
                case LEVEL_2 -> {
                    resource = getClass().getResource("/resources/parallaxes/icecream_parallax2.png");
                    offsetY = 100;
                }
                default ->
                    throw new AssertionError(level.name());
            }

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

                // adjust
                sprite.setBounds(0 + offsetX, 400 - offsetY, 800, 150);
            } else {
                throw new RuntimeException("Image resource not found: icecream_parallax1 or icecream_parallax2.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

}
