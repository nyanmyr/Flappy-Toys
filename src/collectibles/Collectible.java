package collectibles;

import utility.interfaces.Decays;
import utility.sprites.DynamicSprite;
import utility.interfaces.Moveable;

public abstract class Collectible implements Moveable, Decays {

    protected DynamicSprite sprite;

    protected int lifeTime;

    public Collectible(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public DynamicSprite getSprite() {
        return sprite;
    }

    public abstract void loadSprite();

    public abstract CollectibleType giveReward();

    @Override
    public void move(int x, int y) {
        sprite.setLocation(sprite.getX() + x, sprite.getY() + y);
    }

    @Override
    public void setLocation(int x, int y) {
        sprite.setLocation(x, y);
    }

    @Override
    public void setSize(int x, int y) {
        sprite.setSize(x, y);
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
