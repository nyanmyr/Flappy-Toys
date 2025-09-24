package levels.backgrounds;

import utility.Sprite;

public abstract class Background {
    
    protected Sprite sprite;
    
    abstract public void LoadSprite();
    
    public Sprite getSprite() {
        return sprite;
    }
}
