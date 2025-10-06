package obstacles.columns;

// turn into abstract eventually
import obstacles.Obstacle;
import utility.interfaces.Moveable;
import utility.interfaces.Decays;
import utility.sprites.StaticSprite;

public abstract class Column extends Obstacle implements Moveable, Decays {

    public StaticSprite top;
    public StaticSprite bottom;

    protected int lifeTime;

    public Column(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public StaticSprite getTopSprite() {
        return top;
    }

    public StaticSprite getBottomSprite() {
        return bottom;
    }

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

    @Override
    public boolean decay() {
        if (lifeTime > 0) {
            lifeTime--;
            return false;
        }

        return true;
    }

    @Override
    public boolean isAlive() {
        return lifeTime > 0;
    }
    
    @Override
    public void kill() {
        lifeTime = 0;
    }
}
