package toys;

import utility.Sprite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rocketron extends Toy {

    public Rocketron() {
        LoadSprite();
    }

    @Override
    public void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/rocketron_idle.png");
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

                sprite = new Sprite(buffered);
                sprite.setBounds(0, 0, 200, 200);
            } else {
                throw new RuntimeException("Image resource not found: /rocketron_idle.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }
}
