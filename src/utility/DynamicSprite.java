package utility;

import java.awt.image.BufferedImage;

public class DynamicSprite extends StaticSprite {

    private BufferedImage[] frames;
    private int currentFrame = 0;
    private int frameDelay = 12; // how many ticks per frame
    private int tickCounter = 0;

    public DynamicSprite(BufferedImage[] frames) {
        super(frames[0]);
        this.frames = frames;
    }

    /**
     * Called every game tick.
     * Switches frames according to frameDelay.
     */
    public void update() {
        tickCounter++;
        if (tickCounter >= frameDelay) {
            tickCounter = 0;
            currentFrame = (currentFrame + 1) % frames.length;
            image = frames[currentFrame];
            repaint();
        }
    }

    public void setFrameDelay(int delay) {
        this.frameDelay = Math.max(1, delay);
    }

    public void resetAnimation() {
        currentFrame = 0;
        tickCounter = 0;
        image = frames[0];
        repaint();
    }
}
