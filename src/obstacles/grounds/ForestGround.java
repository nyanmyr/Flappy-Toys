package obstacles.grounds;

import utility.sprites.StaticSprite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ForestGround extends Ground {

    public ForestGround(int offsetX){
        super(offsetX);
        LoadSprite();
    }

    @Override
    public final void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/grounds/forest_ground.png");
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

                sprite.setBounds(0 + offsetX, 450, 800, 200);
            } else {
                throw new RuntimeException("Image resource not found: /forest_ground.png");
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
