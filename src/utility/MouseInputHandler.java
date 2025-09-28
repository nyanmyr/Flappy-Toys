package utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputHandler implements MouseListener {

    public boolean jumped;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        jumped = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
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

    public void reset() {
        jumped = false;
    }
}
