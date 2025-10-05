package obstacles.columns;

import utility.StaticSprite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class IceCreamColumn extends Column {

    public IceCreamColumn(int gap, int offset, int lifeTime) {
        super(lifeTime);
        LoadSprite(gap, offset);
    }

    @Override
    public final void LoadSprite(int gap, int offset) {
        try {
            java.net.URL topSprite = getClass().getResource("/resources/columns/popsicle_top_column.png");
            java.net.URL bottomSprite = getClass().getResource("/resources/columns/popsicle_bottom_column.png");
            if (topSprite != null && bottomSprite != null) {
                Image topImage = ImageIO.read(topSprite);
                Image bottomImage = ImageIO.read(bottomSprite);

                java.awt.image.BufferedImage bufferedTop
                        = new java.awt.image.BufferedImage(
                                topImage.getWidth(null),
                                topImage.getHeight(null),
                                java.awt.image.BufferedImage.TYPE_INT_ARGB
                        );
                Graphics2D g2dTop = bufferedTop.createGraphics();
                g2dTop.drawImage(topImage, 0, 0, null);
                g2dTop.dispose();

                java.awt.image.BufferedImage bufferedBottom
                        = new java.awt.image.BufferedImage(
                                bottomImage.getWidth(null),
                                bottomImage.getHeight(null),
                                java.awt.image.BufferedImage.TYPE_INT_ARGB
                        );
                Graphics2D g2dBottom = bufferedBottom.createGraphics();
                g2dBottom.drawImage(bottomImage, 0, 0, null);
                g2dBottom.dispose();

                // 0 gap
                // -425 upper bound
                // -175 lower bound
                top = new StaticSprite(bufferedTop);
                bottom = new StaticSprite(bufferedBottom);

                // texture does not have enough bricks
                top.setBounds(785, -(offset + gap), 90, 600);
                bottom.setBounds(785, (top.getY() + top.getHeight()) + gap, 90, 600);
            } else {
                throw new RuntimeException("Image resource not found: popsicle_top_column.png or popsicle_bottom_column.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    @Override
    public String killEffect() {
        return "Killed by column";
    }
}
