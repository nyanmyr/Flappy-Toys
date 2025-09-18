package obstacles.columns;

// turn into abstract eventually
import obstacles.Obstacle;
import utility.Moveable;
import utility.Sprite;

public abstract class Column extends Obstacle implements Moveable {

    public Sprite top;
    public Sprite bottom;
    
    public abstract void LoadSprite(int gap, int offset);
    
    @Override
    public void move(int x, int y) {
        top.setLocation(top.getX() + x, top.getY() + y);
        bottom.setLocation(bottom.getX() + x, bottom.getY() + y);
    }

    @Override
    public void setLocation(int x, int y) {
        top.setLocation(x, y);
        bottom.setLocation(x, y);
    }

    @Override
    public void setSize(int x, int y) {
        top.setSize(x, y);
        bottom.setSize(x, y);
    }
}
