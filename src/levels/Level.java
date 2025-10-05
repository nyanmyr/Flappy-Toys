package levels;

import levels.backgrounds.Background;
import levels.parallaxes.Parallax;
import levels.parallaxes.ParallaxLevel;
import obstacles.columns.Column;
import obstacles.grounds.Ground;
import utility.StaticSprite;

public abstract class Level {

    protected Column column;
    protected Ground ground_left;
    protected Ground ground_right;
    protected Background background;

    protected Parallax parallax1_left;
    protected Parallax parallax1_right;

    protected Parallax parallax2_left;
    protected Parallax parallax2_right;

    // add variables for holding loaded data here
    public abstract void generateColumn(int gap, int columnOffset, int lifeTime);

    public abstract void generateLeftGround(int offsetX);

    public abstract void generateRightGround(int offsetX);

    public abstract void generateBackground();

    public abstract void generateLeftParallax(int offsetX, ParallaxLevel level);

    public abstract void generateRightParallax(int offsetX, ParallaxLevel level);

    // <editor-fold desc="get object methods">
    public Background getBackground() {
        return background;
    }

    public Column getColumn() {
        return column;
    }

    public Ground getLeftGround() {
        return ground_left;
    }

    public Ground getRightGround() {
        return ground_right;
    }

    public Parallax getLeftParallax(ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                return parallax1_left;
            }
            case LEVEL_2 -> {
                return parallax2_left;
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    public Parallax getRightParallax(ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                return parallax1_right;
            }
            case LEVEL_2 -> {
                return parallax2_right;
            }
            default ->
                throw new AssertionError(level.name());
        }
    }
    // </editor-fold>

    // <editor-fold desc="set object methods">
    public void setBackground(Background background) {
        this.background = background;
    }

    public void setLeftGround(Ground ground_left) {
        this.ground_left = ground_left;
    }

    public void setRightGround(Ground ground_right) {
        this.ground_right = ground_right;
    }

    public void setLeftParallax(Parallax parallax_left, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                this.parallax1_left = parallax_left;
            }
            case LEVEL_2 -> {
                this.parallax2_left = parallax_left;
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    public void setRightParallax(Parallax parallax_right, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                this.parallax1_right = parallax_right;
            }
            case LEVEL_2 -> {
                this.parallax2_right = parallax_right;
            }
            default ->
                throw new AssertionError(level.name());
        }
    }
    // </editor-fold>

    // <editor-fold desc="get sprite methods">
    public StaticSprite getTopColumn() {
        return column.getTopSprite();
    }

    public StaticSprite getBottomColumn() {
        return column.getBottomSprite();
    }

    public StaticSprite getLeftGroundSprite() {
        return ground_left.getSprite();
    }

    public StaticSprite getRightGroundSprite() {
        return ground_right.getSprite();
    }

    public StaticSprite getBackgroundSprite() {
        return background.getSprite();
    }

    public StaticSprite getLeftParallaxSprite(ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                return parallax1_left.getSprite();
            }
            case LEVEL_2 -> {
                return parallax2_left.getSprite();
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    public StaticSprite getRightParallaxSprite(ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                return parallax1_right.getSprite();
            }
            case LEVEL_2 -> {
                return parallax2_right.getSprite();
            }
            default ->
                throw new AssertionError(level.name());
        }
    }
    // </editor-fold>

    // <editor-fold desc="get kill effect methods">
    public String groundKillEffect() {
        return ground_left.killEffect();
    }

    public String columnKillEffect() {
        return column.killEffect();
    }
    // </editor-fold>

    // <editor-fold desc="move sprite methods">
    public void moveLeftGround(int x, int y) {
        ground_left.move(x, y);
    }

    public void moveRightGround(int x, int y) {
        ground_right.move(x, y);
    }

    public void moveLeftParallax(int x, int y, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_left.move(x, y);
            }
            case LEVEL_2 -> {
                parallax2_left.move(x, y);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    public void moveRightParallax(int x, int y, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_right.move(x, y);
            }
            case LEVEL_2 -> {
                parallax2_right.move(x, y);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }
    // </editor-fold>

    // <editor-fold desc="out of bounds detection methods">
    public boolean isLeftGroundOutOfBounds() {
        return ground_left.outOfBoundsDetection();
    }

    public boolean isRightGroundOutOfBounds() {
        return ground_right.outOfBoundsDetection();
    }

    public boolean isLeftParallaxOutOfBounds(ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                return parallax1_left.outOfBoundsDetection();
            }
            case LEVEL_2 -> {
                return parallax2_left.outOfBoundsDetection();
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    public boolean isRightParallaxOutOfBounds(ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                return parallax1_right.outOfBoundsDetection();
            }
            case LEVEL_2 -> {
                return parallax1_right.outOfBoundsDetection();
            }
            default ->
                throw new AssertionError(level.name());
        }
    }
    // </editor-fold>
}
