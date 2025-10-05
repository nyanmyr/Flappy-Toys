package levels.backgrounds;

import utility.StaticSprite;

public abstract class Background {

    protected StaticSprite sprite;

    abstract public void LoadSprite();

    public StaticSprite getSprite() {
        return sprite;
    }

    public float getOpacity() {
        return sprite.getOpacity();
    }

    public void decrementOpacity(float speed) {
        sprite.setOpacity(sprite.getOpacity() - ((speed - 2) * 0.125f));
    }
}
