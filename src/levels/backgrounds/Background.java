package levels.backgrounds;

import utility.Sprite;

public abstract class Background {

    protected Sprite sprite;

    abstract public void LoadSprite();

    public Sprite getSprite() {
        return sprite;
    }

    public float getOpacity() {
        return sprite.getOpacity();
    }

    public void decrementOpacity(float speed) {
        sprite.setOpacity(sprite.getOpacity() - ((speed - 2) * 0.125f));
    }
}
