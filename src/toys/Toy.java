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

    private int collected;

    private boolean immune = false;
    private int immunityTimer = 0;

    private final int FALL_SPEED;
    private final int MOVEMENT_SPEED;
    private final int JUMP_HEIGHT;

    public Toy(Ability ability, int FALL_SPEED, int MOVEMENT_SPEED, int JUMP_HEIGHT) {
        this.ability = ability;
        this.FALL_SPEED = FALL_SPEED;
        this.MOVEMENT_SPEED = MOVEMENT_SPEED;
        this.JUMP_HEIGHT = JUMP_HEIGHT;
    }

    abstract public void LoadSprite();

    // <editor-fold desc="ability methods">
    public int getFallSpeed() {
        return FALL_SPEED;
    }
    
    public int getMovementSpeed() {
        return MOVEMENT_SPEED;
    }
    
    public int getJumpHeight() {
        return JUMP_HEIGHT;
    }
    // </editor-fold>
    
    // <editor-fold desc="ability methods">
    public boolean useAbility() {
        if (charges > 0) {
            charges--;
            ability.useAbility(this);
            return true;
        }
        return false;
    }

    public void setImmunity(int immunity) {
        immune = true;
        immunityTimer = immunity;
    }

    public boolean isImmune() {
        return immune;
    }

    public void decrementImmunity() {
        immunityTimer--;

        if (immunityTimer <= 0) {
            immune = false;
        }
    }
    // </editor-fold>

    // <editor-fold desc="getters">
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
    // </editor-fold>

    // <editor-fold desc="setters">
    public void setCharges(int charges) {
        this.charges = charges;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }
    // </editor-fold>

    // <editor-fold desc="score methods">
    public void incrementScore() {
        score++;
    }

    public void resetScore() {
        score = 0;
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
    // </editor-fold>

    // <editor-fold desc="moveable methods">
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
    // </editor-fold>
}
