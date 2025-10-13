package utility.inputhandling;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputHandler implements MouseListener, MouseMotionListener {

    public boolean moveLeft, moveRight, jumped, abilityUsed = false;

    int mouseX;

    int playerX;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1 -> {
                jumped = true;
            }
            case MouseEvent.BUTTON3 -> {
                abilityUsed = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
    }

    public void updateMovement() {
        if (mouseX > playerX + 10) {
            moveRight = true;
        } else if (mouseX < playerX - 10) {
            moveLeft = true;
        }
    }

    public void givePlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void reset() {
        moveLeft = moveRight = abilityUsed = jumped = false;
    }
}
