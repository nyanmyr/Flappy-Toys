package utility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInputHandler implements KeyListener {

    public boolean jumped, moveLeft, moveRight, abilityUsed = false;

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
        if (code == KeyEvent.VK_W
                || code == KeyEvent.VK_UP) {
            jumped = true;
        }
    }
    
    public void reset() {
        jumped = moveLeft = moveRight = abilityUsed = false;
    }
}
