package obstacles.levels;

import obstacles.columns.Column;
import obstacles.grounds.Ground;

public abstract class Level {

    Column column;
    Ground ground_left;
    Ground ground_right;
    
    
    public abstract void loadColumn(int gap, int columnOffset, int lifeTime);
    
    public abstract void loadGround(int groundOffset);
}
