package obstacles.grounds;

import obstacles.Obstacle;
import utility.Moveable;
import utility.Sprite;

public abstract class Ground extends Obstacle implements Moveable {

    public Sprite left;
    public Sprite right;

    abstract public void LoadSprite();

    @Override
    public void move(int x, int y) {
        left.setLocation(left.getX() + x, left.getY() + y);
        right.setLocation(right.getX() + x, right.getY() + y);

        outOfBoundsDetection();
    }

    @Override
    public void setLocation(int x, int y) {
        left.setLocation(x, y);
        right.setLocation(x, y);

        outOfBoundsDetection();
    }

    @Override
    public void setSize(int x, int y) {
        left.setSize(x, y);
        right.setSize(x, y);
    }

    private void outOfBoundsDetection() {
        if (left.getX() + left.getWidth() < 0) {
            // displace 2 segments back
            left.setLocation(left.getX() + left.getWidth() + (left.getX() + left.getWidth() * 2), 450);
        }
        if (right.getX() + right.getWidth() < 0) {
            right.setLocation(right.getX() + right.getWidth() + (right.getX() + right.getWidth() * 2), 450);
        }
    }
}
