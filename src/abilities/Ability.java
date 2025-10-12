package abilities;

import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import toys.Toy;
import utility.sprites.StaticSprite;

public abstract class Ability {
    
    protected StaticSprite sprite;
    protected final int TIMER_START;
    protected int timer;
    
    protected SoundFile SOUND;
    
    public Ability(int TIMER_START, SoundFile sound) {
        this.TIMER_START = TIMER_START;
        this.SOUND = sound;
    }
    
    public abstract void useAbility(Toy toy);
    
    public abstract void loadSprite();
    
    public StaticSprite getSprite() {
        return sprite;
    }
    
    public void setSpriteVisiblity(boolean visibility) {
        sprite.setVisible(visibility);
    }
    
    public void setAbilitySpriteToPlayerLocation(Toy toy) {
        sprite.setLocation(toy.getSprite().getX() - 10, toy.getSprite().getY() - 10);
    }
    
    public boolean isTimerDivisibleByFour() {
        return timer % 4 == 0;
    }
    
    public boolean isNearlyDone() {
        return timer <= 75;
    }
    
    public boolean isTimerDone() {
        return timer <= 0;
    }
    
    public void startTimer() {
        timer = TIMER_START;
    }
    
    public void decrementTimer() {
        timer--;
    }
    
    protected void playSound() {
        SoundPlayer.playSound(SOUND);
    }
}
