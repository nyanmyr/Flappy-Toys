package obstacles.grounds;

import utility.Sprite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BrickGround extends Ground {

    public BrickGround() {
        LoadSprite();
    }

    @Override
    public void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/brickland_ground.png");
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

                left = new Sprite(buffered);
                right = new Sprite(buffered);

                left.setBounds(0, 450, 800, 150);
                right.setBounds(left.getX() + left.getWidth(), 450, 800, 150);
            } else {
                throw new RuntimeException("Image resource not found: /brickland_ground.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    @Override
    public String killEffect() {
        return "Killed by ground";
    }
}
