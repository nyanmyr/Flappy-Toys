package toys;

import abilities.Ability;
import utility.Moveable;
import utility.Sprite;

public abstract class Toy implements Moveable {

    protected Sprite sprite;
    public Ability ability;
    public int charges = 3;
    
    public int score = 0;
    public boolean shield = false;

    abstract public void LoadSprite();

    public boolean useAbility() {
        ability.useAbility(this);
        if (charges > 0) {
            charges--;
            return true;
        }
        return false;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
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
}
