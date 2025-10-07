package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import utility.sprites.StaticSprite;

public class Credits extends javax.swing.JFrame {

    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    private StaticSprite background;

    public Credits(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        this.WINDOW_HEIGHT = SCREEN_HEIGHT;
        this.WINDOW_WIDTH = SCREEN_WIDTH;

        initComponents();

        LoadSprite();
        panel_Background.add(background);
    }

    private void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/backgrounds/brickland_bg.jpg");
            if (resource != null) {
                Image img = ImageIO.read(resource);
                java.awt.image.BufferedImage buffered
                        = new java.awt.image.BufferedImage(
                                img.getWidth(null),
                                img.getHeight(null),
                                java.awt.image.BufferedImage.TYPE_INT_ARGB
                        );
                Graphics2D g2d = buffered.createGraphics();
                g2d.drawImage(img, 0, 0, null);
                g2d.dispose();

                background = new StaticSprite(buffered);

                background.setBounds(0, 0, WINDOW_HEIGHT, WINDOW_WIDTH);
            } else {
                throw new RuntimeException("Image resource not found: brickland_bg.jpg");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Title = new javax.swing.JLabel();
        button_Return = new javax.swing.JButton();
        label_Tutorial = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Title.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("CREDITS");
        panel_Background.add(label_Title);
        label_Title.setBounds(50, 50, 290, 80);

        button_Return.setBackground(new java.awt.Color(0, 74, 173));
        button_Return.setForeground(new java.awt.Color(255, 255, 255));
        button_Return.setText("Return");
        button_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReturnActionPerformed(evt);
            }
        });
        panel_Background.add(button_Return);
        button_Return.setBounds(290, 460, 220, 50);

        label_Tutorial.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Tutorial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Tutorial.setText("<html><body style=\"text-align: center\">DEVELOPED IN COMPLIANCE FOR OBJECT ORIENTED COURSE:<br><b>CC009.23_1ST_SEM_SY-25-26</b><br><br>BACKEND PROGRAMMER:<br><b>BIEN NIÑO ENRIC N. ILIGAN</b><br><br>PROJECT MANAGER/ GAME DESIGNER/ PROGRAMMER:<br><b>NINMAR H. OLPENADO</b><br><br>DOCUMENTER:<br><b>MARK VINCENT C. PALENCIA</b></body></html>");
        panel_Background.add(label_Tutorial);
        label_Tutorial.setBounds(110, 140, 580, 310);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Menu(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true));
    }//GEN-LAST:event_button_ReturnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Return;
    private javax.swing.JLabel label_Title;
    private javax.swing.JLabel label_Tutorial;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
