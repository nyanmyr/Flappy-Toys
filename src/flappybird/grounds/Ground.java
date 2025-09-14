package flappybird.grounds;

import flappybird.objects.Moveable;
import flappybird.objects.Sprite;

public abstract class Ground implements Moveable {

    public Sprite ground_left;
    public Sprite ground_middle;
    public Sprite ground_right;

    abstract public void LoadSprite();

    @Override
    public void move(int x, int y) {
        ground_left.setLocation(ground_left.getX() + x, ground_left.getY() + y);
        ground_middle.setLocation(ground_middle.getX() + x, ground_middle.getY() + y);
        ground_right.setLocation(ground_right.getX() + x, ground_right.getY() + y);

        outOfBoundsDetection();
    }

    @Override
    public void setLocation(int x, int y) {
        ground_left.setLocation(x, y);
        ground_middle.setLocation(x, y);
        ground_right.setLocation(x, y);

        outOfBoundsDetection();
    }

    @Override
    public void setSize(int x, int y) {
        ground_left.setSize(x, y);
        ground_middle.setSize(x, y);
        ground_right.setSize(x, y);
    }

    private void outOfBoundsDetection() {
        if (ground_left.getX() + ground_left.getWidth() < 0) {
            // displace 2 segments back
            ground_left.setLocation(ground_left.getX() + ground_left.getWidth() + (ground_left.getX() + ground_left.getWidth() * 2), 450);
        }
        if (ground_middle.getX() + ground_middle.getWidth() < 0) {
            ground_middle.setLocation(ground_middle.getX() + ground_middle.getWidth() + (ground_middle.getX() + ground_middle.getWidth() * 2), 450);
        }
        if (ground_right.getX() + ground_right.getWidth() < 0) {
            ground_right.setLocation(ground_right.getX() + ground_right.getWidth() + (ground_right.getX() + ground_right.getWidth() * 2), 450);
        }
    }
}
