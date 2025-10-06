
package collectibles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utility.sprites.DynamicSprite;
import utility.sprites.SpriteUtils;

public class PopsicleToken extends Collectible {

    public PopsicleToken(int lifeTime) {
        super(lifeTime);
        loadSprite();
    }
    
    @Override
    public CollectibleType giveReward() {
        return CollectibleType.SCORE;
    }

    @Override
    public final void loadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/animations/collectibles/popsicle_token_anim.png");
            if (resource == null) {
                throw new RuntimeException("Image resource not found: /popsicle_token_anim.png");
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
            setSize(30, 30);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }
}

