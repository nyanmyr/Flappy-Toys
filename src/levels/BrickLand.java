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
    final public void generateLeftGround(int groundOffset) {
        ground_left = new BrickGround(groundOffset);
    }
    
    @Override
    final public void generateRightGround(int groundOffset) {
        ground_right = new BrickGround(groundOffset);
    }

    @Override
    final public void generateBackground() {
        background = new BrickLandBackground();
    }
}
