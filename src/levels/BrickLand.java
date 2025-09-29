package levels;

import levels.backgrounds.BrickLandBackground;
import levels.parallaxes.BrickParallax;
import levels.parallaxes.ParallaxLevel;
import obstacles.columns.BrickColumn;
import obstacles.grounds.BrickGround;

public class BrickLand extends Level {

    @Override
    final public void generateColumn(int gap, int columnOffset, int lifeTime) {
        column = new BrickColumn(gap, columnOffset, lifeTime);
    }

    @Override
    final public void generateLeftGround(int offsetX) {
        ground_left = new BrickGround(offsetX);
    }

    @Override
    final public void generateRightGround(int offsetX) {
        ground_right = new BrickGround(offsetX);
    }

    @Override
    final public void generateBackground() {
        background = new BrickLandBackground();
    }

    @Override
    final public void generateLeftParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_left = new BrickParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_left = new BrickParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    final public void generateRightParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_right = new BrickParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_right = new BrickParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }
}
