package flappybird.objects;

public class Floor {

    public javax.swing.JLabel floor = new javax.swing.JLabel();

    public Floor() {
        Create();
    }

    private void Create() {
        floor.setBackground(new java.awt.Color(153, 255, 153));
        floor.setOpaque(true);
        floor.setBounds(0, 450, 800, 150);
    }
}
