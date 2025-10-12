package abilities;

import static game.Game.MILISECOND_DELAY;

public enum AbilityDuration {
    SHORT((MILISECOND_DELAY * 10) * 1),
    NORMAL((MILISECOND_DELAY * 10) * 2),
    LONG((MILISECOND_DELAY * 10) * 3);
    
    public final int duration;
    
    private AbilityDuration(int duration) {
        this.duration = duration;
    }
    
}
