package obstacles.grounds;

import obstacles.Obstacle;
import utility.Moveable;
import utility.Sprite;

public abstract class Ground extends Obstacle implements Moveable {

    protected Sprite sprite;
    protected final int offset;

    public Ground(int offset){
        this.offset = offset;
    }
    
    abstract public void LoadSprite();

    @Override
    public void move(int x, int y) {
        sprite.setLocation(sprite.getX() + x, sprite.getY() + y);

        outOfBoundsDetection();
    }

    @Override
    public void setLocation(int x, int y) {
        sprite.setLocation(x, y);

        outOfBoundsDetection();
    }

    @Override
    public void setSize(int x, int y) {
        sprite.setSize(x, y);
    }

    public boolean outOfBoundsDetection() {
        return sprite.getX() + sprite.getWidth() < 0;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
}
