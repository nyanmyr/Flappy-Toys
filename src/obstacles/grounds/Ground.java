package obstacles.grounds;

import obstacles.Obstacle;
import utility.Moveable;
import utility.Sprite;

public abstract class Ground extends Obstacle implements Moveable {

    public Sprite sprite;
//    public Sprite right;

    Ground(int lifetime) {
        super(lifetime);
    }

    abstract public void LoadSprite(int offset);

    @Override
    public void move(int x, int y) {
        sprite.setLocation(sprite.getX() + x, sprite.getY() + y);
//        right.setLocation(right.getX() + x, right.getY() + y);

//        outOfBoundsDetection();
    }

    @Override
    public void setLocation(int x, int y) {
        sprite.setLocation(x, y);
//        right.setLocation(x, y);

//        outOfBoundsDetection();
    }

    @Override
    public void setSize(int x, int y) {
        sprite.setSize(x, y);
//        right.setSize(x, y);
    }

//    private void outOfBoundsDetection() {
//        if (sprite.getX() + sprite.getWidth() < 0) {
//            // displace 2 segments back
//            sprite.setLocation(sprite.getX() + sprite.getWidth() + (sprite.getX() + sprite.getWidth() * 2), 450);
//        }
//        if (right.getX() + right.getWidth() < 0) {
//            right.setLocation(right.getX() + right.getWidth() + (right.getX() + right.getWidth() * 2), 450);
//        }
//    }
}
