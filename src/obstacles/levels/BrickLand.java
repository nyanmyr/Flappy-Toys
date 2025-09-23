package obstacles.levels;

import obstacles.columns.BrickColumn;
import obstacles.grounds.BrickGround;

public class BrickLand extends Level {

    public BrickLand(int gap, int columnOffset, int lifeTime, int groundOffset) {
        loadColumn(gap, columnOffset, lifeTime);
        loadGround(groundOffset);
    }
    
    @Override
    final public void loadColumn(int gap, int columnOffset, int lifeTime) {
        column = new BrickColumn(gap, columnOffset, lifeTime);
    }

    @Override
    final public void loadGround(int groundOffset) {
        ground_left = new BrickGround(groundOffset);
        ground_right = new BrickGround(groundOffset);
    }
    
}
