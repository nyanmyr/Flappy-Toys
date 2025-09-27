package levels;

import levels.backgrounds.BrickLandBackground;
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
        background = new BrickLandBackground();
    }
}
