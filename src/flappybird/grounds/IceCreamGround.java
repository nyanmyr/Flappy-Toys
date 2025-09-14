package flappybird.grounds;

import flappybird.objects.Sprite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class IceCreamGround extends Ground {

    public IceCreamGround() {
        LoadSprite();
    }

    @Override
    public void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/flappybird/resources/icecreamland_ground.png");
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

                ground_left = new Sprite(buffered);
                ground_middle = new Sprite(buffered);
                ground_right = new Sprite(buffered);

                ground_left.setBounds(0, 450, 800, 150);
                ground_middle.setBounds(ground_left.getX() + ground_left.getWidth(), 450, 800, 150);
                ground_right.setBounds(ground_left.getX() + ground_left.getWidth(), 450, 800, 150);
            } else {
                throw new RuntimeException("Image resource not found: /icecreamland_ground.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

}
