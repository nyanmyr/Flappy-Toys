package levels;

import collectibles.BrickToken;
import levels.backgrounds.BricksBackground;
import levels.parallaxes.BricksParallax;
import levels.parallaxes.ParallaxLevel;
import obstacles.columns.BricksColumn;
import obstacles.grounds.BricksGround;
import sfx.music.MusicFile;

public class Bricks extends AbstractLevel {

    @Override
    final public void generateColumn(int gap, int columnOffset, int lifeTime) {
        column = new BricksColumn(gap, columnOffset, lifeTime);
    }

    @Override
    final public void generateLeftGround(int offsetX) {
        ground_left = new BricksGround(offsetX);
    }

    @Override
    final public void generateRightGround(int offsetX) {
        ground_right = new BricksGround(offsetX);
    }

    @Override
    final public void generateBackground() {
        background = new BricksBackground();
    }

    @Override
    final public void generateLeftParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_left = new BricksParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_left = new BricksParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    final public void generateRightParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_right = new BricksParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_right = new BricksParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    public void generateToken(int lifeTime) {
        token = new BrickToken(lifeTime);
    }

    @Override
    public void generateMusic() {
        MUSIC_FILE = MusicFile.BRICKS;
    }
}
