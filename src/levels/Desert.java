
package levels;

import collectibles.ScarabToken;
import levels.backgrounds.DesertBackground;
import levels.parallaxes.DesertParallax;
import levels.parallaxes.ParallaxLevel;
import obstacles.columns.DesertColumn;
import obstacles.grounds.DesertGround;
import sfx.music.MusicFile;


public class Desert extends AbstractLevel {

    @Override
    final public void generateColumn(int gap, int columnOffset, int lifeTime) {
        column = new DesertColumn(gap, columnOffset, lifeTime);
    }

    @Override
    final public void generateLeftGround(int offsetX) {
        ground_left = new DesertGround(offsetX);
    }

    @Override
    final public void generateRightGround(int offsetX) {
        ground_right = new DesertGround(offsetX);
    }

    @Override
    final public void generateBackground() {
        background = new DesertBackground();
    }

    @Override
    final public void generateLeftParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_left = new DesertParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_left = new DesertParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    final public void generateRightParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_right = new DesertParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_right = new DesertParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    public void generateToken(int lifeTime) {
        token = new ScarabToken(lifeTime);
    }

    @Override
    public void generateMusic() {
        MUSIC_FILE = MusicFile.DESERT;
    }
}

