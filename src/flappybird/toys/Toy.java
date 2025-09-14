package flappybird.toys;

import flappybird.abilities.Ability;
import flappybird.objects.Moveable;
import flappybird.objects.Sprite;

public abstract class Toy implements Moveable {

    public Sprite sprite;
    public Ability ability;
    public int charges = 3;

    abstract public void LoadSprite();

    public boolean useAbility() {
        if (charges > 0) {
            charges--;
            return true;
        }
        return false;
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
