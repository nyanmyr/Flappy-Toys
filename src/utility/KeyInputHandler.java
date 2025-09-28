package utility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener {

    public boolean moveLeft, moveRight, abilityUsed = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A
                || code == KeyEvent.VK_LEFT) {
            moveLeft = true;
        }
        if (code == KeyEvent.VK_D
                || code == KeyEvent.VK_RIGHT) {
            moveRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            abilityUsed = true;
        }
    }

    public void reset() {
        moveLeft = moveRight = abilityUsed = false;
    }
}
