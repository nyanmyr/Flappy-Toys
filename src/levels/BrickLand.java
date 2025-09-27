package levels;

import levels.backgrounds.BrickLandBackground;
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
}
