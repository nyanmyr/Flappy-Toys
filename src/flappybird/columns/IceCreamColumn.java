package flappybird.columns;

import flappybird.objects.Sprite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class IceCreamColumn extends Column {
    
    public IceCreamColumn(int gap, int offset) {
        LoadSprite(gap, offset);
    }

    @Override
    public void LoadSprite(int gap, int offset) {
        try {
            java.net.URL resource = getClass().getResource("/flappybird/resources/popsicle_column.png");
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

                // 0 gap
                // -425 upper bound
                // -175 lower bound
                top = new Sprite(buffered);
                bottom = new Sprite(buffered);

                // texture does not have enough bricks
                top.setBounds(785, -(offset + gap), 90, 600);
                bottom.setBounds(785, (top.getY() + top.getHeight()) + gap, 90, 600);
            } else {
                throw new RuntimeException("Image resource not found: /popsicle_column.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }
    
}