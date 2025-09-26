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
    final public void generateLeftGround(int offsetX, int offsetY) {
        ground_left = new IceCreamGround(offsetX, offsetY);
    }
    
    @Override
    final public void generateRightGround(int offsetX, int offsetY) {
        ground_right = new IceCreamGround(offsetX, offsetY);
    }

    @Override
    final public void generateBackground() {
        background = new BrickLandBackground();
    }
}
