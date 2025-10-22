
package levels;

import collectibles.MushroomToken;
import levels.backgrounds.ForestBackground;
import levels.parallaxes.ForestParallax;
import levels.parallaxes.ParallaxLevel;
import obstacles.columns.ForestColumn;
import obstacles.grounds.ForestGround;
import sfx.music.MusicFile;


public class Forest extends AbstractLevel {

    @Override
    final public void generateColumn(int gap, int columnOffset, int lifeTime) {
        column = new ForestColumn(gap, columnOffset, lifeTime);
    }

    @Override
    final public void generateLeftGround(int offsetX) {
        ground_left = new ForestGround(offsetX);
    }

    @Override
    final public void generateRightGround(int offsetX) {
        ground_right = new ForestGround(offsetX);
    }

    @Override
    final public void generateBackground() {
        background = new ForestBackground();
    }

    @Override
    final public void generateLeftParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_left = new ForestParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_left = new ForestParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    final public void generateRightParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_right = new ForestParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_right = new ForestParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    public void generateToken(int lifeTime) {
        token = new MushroomToken(lifeTime);
    }

    @Override
    public void generateMusic() {
        MUSIC_FILE = MusicFile.FOREST;
    }
}

