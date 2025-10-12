package abilities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import sfx.sounds.SoundFile;
import toys.Toy;
import utility.sprites.StaticSprite;

public class JumpBoost extends Ability {

    public JumpBoost() {
        super(AbilityDuration.SHORT.duration, SoundFile.TEDDYCOPTER_JUMPBOOST);
        loadSprite();
    }
    
    @Override
    public void useAbility(Toy toy) {
        playSound();
        toy.setImmunity(AbilityDuration.SHORT.duration);
        toy.move(0, -175);
        System.out.println("JumpBoost ability used.");
        startTimer();
    }

    @Override
    public final void loadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/abilities/jump.png");
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

                sprite.setBounds(0, 0, 60, 60);
            } else {
                throw new RuntimeException("Image resource not found: /jump.png");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }
}
