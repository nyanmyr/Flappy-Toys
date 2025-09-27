package levels;

import levels.backgrounds.Background;
import obstacles.columns.Column;
import obstacles.grounds.Ground;
import utility.Sprite;

public abstract class Level {

    protected Column column;
    protected Ground ground_left;
    protected Ground ground_right;
    protected Background background;

    // add variables for holding loaded data here
    public abstract void generateColumn(int gap, int columnOffset, int lifeTime);

    public abstract void generateLeftGround(int offsetX);

    public abstract void generateRightGround(int offsetX);

    public abstract void generateBackground();

    // <editor-fold desc="get object methods">
    public Column getColumn() {
        return column;
    }

    public Ground getLeftGround() {
        return ground_left;
    }

    public Ground getRightGround() {
        return ground_right;
    }
    // </editor-fold>

    // <editor-fold desc="set object methods">
    public void setLeftGround(Ground ground_left) {
        this.ground_left = ground_left;
    }

    public void setRightGround(Ground ground_right) {
        this.ground_right = ground_right;
    }
    // </editor-fold>

    // <editor-fold desc="get sprite methods">
    public Sprite getTopColumn() {
        return column.getTopSprite();
    }

    public Sprite getBottomColumn() {
        return column.getBottomSprite();
    }

    public Sprite getLeftGroundSprite() {
        return ground_left.getSprite();
    }

    public Sprite getRightGroundSprite() {
        return ground_right.getSprite();
    }

    public Sprite getBackground() {
        return background.getSprite();
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
    // </editor-fold>

    // <editor-fold desc="out of bounds detection methods">
    public boolean isLeftGroundOutOfBounds() {
        return ground_left.outOfBoundsDetection();
    }

    public boolean isRightGroundOutOfBounds() {
        return ground_right.outOfBoundsDetection();
    }
    // </editor-fold>

}
