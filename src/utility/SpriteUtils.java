package utility;

import java.awt.image.BufferedImage;

public final class SpriteUtils {

    private SpriteUtils() {}

    public static BufferedImage[] sliceHorizontalFrames(BufferedImage sheet, int frameCount) {
        int frameWidth = sheet.getWidth() / frameCount;
        int frameHeight = sheet.getHeight();

        BufferedImage[] frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = sheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        return frames;
    }
}