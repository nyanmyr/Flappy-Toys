package levels.parallaxes;

import utility.Moveable;
import utility.Sprite;

public abstract class Parallax implements Moveable {

    protected Sprite sprite;
    protected final int offsetX;
    protected final ParallaxLevel level;

    public Parallax(int offsetX, ParallaxLevel level){
        this.offsetX = offsetX;
        this.level = level;
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
