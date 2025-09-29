package levels;

import levels.backgrounds.IceCreamLandBackground;
import levels.parallaxes.IceCreamParallax;
import levels.parallaxes.ParallaxLevel;
import obstacles.columns.IceCreamColumn;
import obstacles.grounds.IceCreamGround;

public class IceCreamLand extends Level {

    @Override
    final public void generateColumn(int gap, int columnOffset, int lifeTime) {
        column = new IceCreamColumn(gap, columnOffset, lifeTime);
    }

    @Override
    final public void generateLeftGround(int offsetX) {
        ground_left = new IceCreamGround(offsetX);
    }

    @Override
    final public void generateRightGround(int offsetX) {
        ground_right = new IceCreamGround(offsetX);
    }

    @Override
    final public void generateBackground() {
        background = new IceCreamLandBackground();
    }

    @Override
    final public void generateLeftParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_left = new IceCreamParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_left = new IceCreamParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    final public void generateRightParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_right = new IceCreamParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_right = new IceCreamParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }
}
