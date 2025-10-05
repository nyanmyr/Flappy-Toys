package toys;

import abilities.Ability;
import utility.DynamicSprite;
import utility.Moveable;

public abstract class Toy implements Moveable {

    protected DynamicSprite sprite;
    protected Ability ability;
    protected int charges = 3;

    private int score = 0;
    private boolean hasShield = false;

    public Toy(Ability ability) {
        this.ability = ability;
    }

    abstract public void LoadSprite();

    public boolean useAbility() {
        ability.useAbility(this);
        if (charges > 0) {
            charges--;
            return true;
        }
        return false;
    }

    public DynamicSprite getSprite() {
        return sprite;
    }

    public int getCharges() {
        return charges;
    }

    public int getScore() {
        return score;
    }

    public boolean hasShield() {
        return hasShield;
    }

    public void setShield(boolean status) {
        this.hasShield = status;
    }
    
    public void incrementScore() {
        score++;
    }
    
    public void resetScore() {
        score = 0;
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
