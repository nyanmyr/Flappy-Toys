package toys;

import abilities.Ability;
import collectibles.Collectible;
import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import utility.sprites.DynamicSprite;
import utility.interfaces.Moveable;

public abstract class Toy implements Moveable {

    private final int FALL_SPEED;
    private final int MOVEMENT_SPEED;
    private final int JUMP_HEIGHT;

    protected DynamicSprite sprite;

    protected Ability ability;
    protected int charges;
    private final int ABILITY_COOLDOWN_RATE = 150;
    private int abilityCooldown = ABILITY_COOLDOWN_RATE;

    private int score;

    private int tokensCollected;
    private int chargesUsed;

    private boolean immune = false;
    private int immunityTimer = 0;

    private final SoundFile jumpSoundFile;

    public Toy(Ability ability, int FALL_SPEED, int MOVEMENT_SPEED, int JUMP_HEIGHT, SoundFile jumpSoundFile, ToyCharacter toyCharacter) {
        this.ability = ability;
        this.FALL_SPEED = FALL_SPEED;
        this.MOVEMENT_SPEED = MOVEMENT_SPEED;
        this.JUMP_HEIGHT = JUMP_HEIGHT;
        this.jumpSoundFile = jumpSoundFile;
        this.toyCharacter = toyCharacter;
    }

    private final ToyCharacter toyCharacter;

    abstract public void LoadSprite();

    // <editor-fold desc="stats methods">
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
    public void setAbilitySpriteToPlayerLocation() {
        ability.setAbilitySpriteToPlayerLocation(this);
    }

    public Ability getAbility() {
        return ability;
    }

    public boolean useAbility() {
        if (charges > 0
                && abilityCooldown <= 0
                && !immune) {
            abilityCooldown = ABILITY_COOLDOWN_RATE;

            charges--;
            chargesUsed++;

            ability.useAbility(this);
            return true;
        }
        SoundPlayer.playSound(SoundFile.NO_CHARGES);
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

    public void decrementAbilityCooldown() {
        if (abilityCooldown > 0) {
            abilityCooldown--;
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

    public int getTokensCollected() {
        return tokensCollected;
    }

    public int getChargesUsed() {
        return chargesUsed;
    }

    public SoundFile getJumpSoundFile() {
        return jumpSoundFile;
    }

    public ToyCharacter getToyCharacter() {
        return toyCharacter;
    }
    // </editor-fold>

    // <editor-fold desc="setters">
    public void setCharges(int charges) {
        this.charges = charges;
    }

    public void setTokensCollected(int tokensCollected) {
        this.tokensCollected = tokensCollected;
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
        tokensCollected++;

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
