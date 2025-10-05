package toys;

import abilities.Ability;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utility.DynamicSprite;
import utility.SpriteUtils;

public class Rocketron extends Toy {

    public Rocketron(Ability ability) {
        super(ability);
        LoadSprite();
    }

    @Override
    public final void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/animations/rocketron_anim.png");
            if (resource == null) {
                throw new RuntimeException("Image resource not found: /resources/animations/rocketron_anim.png");
            }

            // Read and convert to BufferedImage
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

            // Split horizontally into frames
            BufferedImage[] frames = SpriteUtils.sliceHorizontalFrames(buffered, 12);

            // Use DynamicSprite for animation
            sprite = new DynamicSprite(frames);
            sprite.setBounds(100, 0, frames[0].getWidth(), frames[0].getHeight());

        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }
}
