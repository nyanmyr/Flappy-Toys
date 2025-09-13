package flappybird.objects;

// turn into abstract eventually
public class Pipe {

    public javax.swing.JLabel top = new javax.swing.JLabel();
    public javax.swing.JLabel bottom = new javax.swing.JLabel();

    public Pipe(int gap, int offset) {
        Create(gap, offset);
    }

    private void Create(int gap, int offset) {
        top.setBackground(new java.awt.Color(0, 153, 0));
        top.setOpaque(true);
        // 0 gap
        // -425 upper bound
        // -175 lower bound
        top.setBounds(785, -(offset + gap), 90, 600);

        bottom.setBackground(new java.awt.Color(0, 153, 0));
        bottom.setOpaque(true);
        bottom.setBounds(785, (top.getY() + top.getHeight()) + gap, 90, 600);
    }
}