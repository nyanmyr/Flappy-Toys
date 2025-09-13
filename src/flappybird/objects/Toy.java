package flappybird.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Toy {

    public Sprite sprite;

    public Toy() {
        try {
            java.net.URL resource = getClass().getResource("/flappybird/resources/teddycopter_idle.png");
            if (resource != null) {
                Image img = ImageIO.read(resource);
                java.awt.image.BufferedImage buffered =
                        new java.awt.image.BufferedImage(
                                img.getWidth(null),
                                img.getHeight(null),
                                java.awt.image.BufferedImage.TYPE_INT_ARGB
                        );
                Graphics2D g2d = buffered.createGraphics();
                g2d.drawImage(img, 0, 0, null);
                g2d.dispose();

                sprite = new Sprite(buffered);
                sprite.setBounds(400, 0, 50, 50);
            } else {
                throw new RuntimeException("Image resource not found: /bird.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }
}
