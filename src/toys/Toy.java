package toys;

import abilities.Ability;
import collectibles.Collectible;
import utility.sprites.DynamicSprite;
import utility.interfaces.Moveable;

public abstract class Toy implements Moveable {

    protected DynamicSprite sprite;
    protected Ability ability;
    protected int charges;

    private int score;
    private boolean hasShield = false;

    private int collected;

    public Toy(Ability ability) {
        this.ability = ability;
    }

    abstract public void LoadSprite();

    public boolean useAbility() {
        if (charges > 0) {
            charges--;
            ability.useAbility(this);
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

    public int getCollected() {
        return collected;
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

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }

    public void receiveCollectible(Collectible collectible) {
        collected++;
        
        switch (collectible.giveReward()) {
            case CHARGE -> {
                charges++;
            }
            case SCORE -> {
//                score += 500;
            }
            default ->
                throw new AssertionError(collectible.giveReward().name());
        }
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
