package obstacles.grounds;

import obstacles.Obstacle;
import utility.interfaces.Moveable;
import utility.interfaces.OutOfBoundsDetection;
import utility.sprites.StaticSprite;

public abstract class Ground extends Obstacle implements Moveable, OutOfBoundsDetection {

    protected StaticSprite sprite;
    protected final int offsetX;

    public Ground(int offsetX){
        this.offsetX = offsetX;
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

    @Override
    public boolean outOfBoundsDetection() {
        return sprite.getX() + sprite.getWidth() < 0;
    }
    
    public StaticSprite getSprite() {
        return sprite;
    }
}
