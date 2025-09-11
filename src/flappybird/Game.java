package flappybird;

import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Bird {

}

class Pipe {

}

class Background {

}

class Ground {

}

public class Game extends javax.swing.JFrame {

    boolean gameOver = false;
    Random randomizer = new Random();
    int score = 0;

    public Game() {
        initComponents();

        label_GameOver.setVisible(false);

        int num = randomizer.nextInt(350 - 0 + 1) + 0;
        label_BottomPipe.setBounds(
                800,
                -480 + num,
                label_BottomPipe.getWidth(),
                label_BottomPipe.getHeight()
        );
        label_TopPipe.setBounds(
                800,
                200 + num,
                label_TopPipe.getWidth(),
                label_TopPipe.getHeight()
        );

        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                label_Bird.setBounds(
                        label_Bird.getX(),
                        label_Bird.getY() + 10,
                        label_Bird.getWidth(),
                        label_Bird.getHeight());

                // move bird down
                if (label_Bird.getY() < 0) {
                    gameOver = true;
                    System.out.println("Touched the sky");
                }
                if (label_Bird.getY() > 450) {
                    gameOver = true;
                    System.out.println("Touched the ground");
                }

                // move pipe to the left
                label_BottomPipe.setBounds(
                        label_BottomPipe.getX() - 10,
                        label_BottomPipe.getY(),
                        label_BottomPipe.getWidth(),
                        label_BottomPipe.getHeight());
                label_TopPipe.setBounds(
                        label_TopPipe.getX() - 10,
                        label_TopPipe.getY(),
                        label_TopPipe.getWidth(),
                        label_TopPipe.getHeight());

                if (((label_Bird.getX() < label_BottomPipe.getX() + label_BottomPipe.getWidth()
                        && label_Bird.getX() + label_Bird.getWidth() > label_BottomPipe.getX()
                        && label_Bird.getY() < label_BottomPipe.getY() + label_BottomPipe.getHeight()
                        && label_Bird.getY() + label_Bird.getHeight() > label_BottomPipe.getY()))
                        || ((label_Bird.getX() < label_TopPipe.getX() + label_TopPipe.getWidth()
                        && label_Bird.getX() + label_Bird.getWidth() > label_TopPipe.getX()
                        && label_Bird.getY() < label_TopPipe.getY() + label_TopPipe.getHeight()
                        && label_Bird.getY() + label_Bird.getHeight() > label_TopPipe.getY()))) {
                    gameOver = true;
                    System.out.println("Collision.");
                }

                if (label_BottomPipe.getX() < 0 - label_BottomPipe.getWidth()) {
                    int num = randomizer.nextInt(350 - 0 + 1) + 0;
                    label_BottomPipe.setBounds(
                            800,
                            -480 + num,
                            label_BottomPipe.getWidth(),
                            label_BottomPipe.getHeight());
                    label_TopPipe.setBounds(
                            800,
                            200 + num,
                            label_TopPipe.getWidth(),
                            label_TopPipe.getHeight());
                }

                score++;
                String text = "Score: " + score;
                label_Score.setText(text);

                if (gameOver) {
                    Timer localTimer = (Timer) evt.getSource();
                    localTimer.stop();
                    label_GameOver.setVisible(true);
                }
                repaint();

            }
        };
        
        Timer timer = new Timer(60, taskPerformer);
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Score = new javax.swing.JLabel();
        label_GameOver = new javax.swing.JLabel();
        label_Ground = new javax.swing.JLabel();
        label_Bird = new javax.swing.JLabel();
        button_Jump = new javax.swing.JButton();
        label_TopPipe = new javax.swing.JLabel();
        label_BottomPipe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Score.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        label_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Score.setText("Score");
        panel_Background.add(label_Score);
        label_Score.setBounds(10, 470, 240, 70);

        label_GameOver.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        label_GameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameOver.setText("GAME OVER");
        panel_Background.add(label_GameOver);
        label_GameOver.setBounds(260, 200, 290, 100);

        label_Ground.setBackground(new java.awt.Color(153, 255, 153));
        label_Ground.setMaximumSize(new java.awt.Dimension(50, 50));
        label_Ground.setMinimumSize(new java.awt.Dimension(50, 50));
        label_Ground.setOpaque(true);
        label_Ground.setPreferredSize(new java.awt.Dimension(50, 50));
        panel_Background.add(label_Ground);
        label_Ground.setBounds(0, 460, 800, 140);

        label_Bird.setBackground(new java.awt.Color(255, 255, 204));
        label_Bird.setMaximumSize(new java.awt.Dimension(50, 50));
        label_Bird.setMinimumSize(new java.awt.Dimension(50, 50));
        label_Bird.setOpaque(true);
        label_Bird.setPreferredSize(new java.awt.Dimension(50, 50));
        panel_Background.add(label_Bird);
        label_Bird.setBounds(400, 0, 50, 50);

        button_Jump.setBackground(new java.awt.Color(255, 255, 251, 0));
        button_Jump.setAutoscrolls(true);
        button_Jump.setBorder(null);
        button_Jump.setBorderPainted(false);
        button_Jump.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_JumpActionPerformed(evt);
            }
        });
        panel_Background.add(button_Jump);
        button_Jump.setBounds(325, 513, 200, 80);

        label_TopPipe.setBackground(new java.awt.Color(0, 153, 0));
        label_TopPipe.setMaximumSize(new java.awt.Dimension(50, 50));
        label_TopPipe.setMinimumSize(new java.awt.Dimension(50, 50));
        label_TopPipe.setOpaque(true);
        label_TopPipe.setPreferredSize(new java.awt.Dimension(50, 50));
        panel_Background.add(label_TopPipe);
        label_TopPipe.setBounds(590, -420, 100, 500);

        label_BottomPipe.setBackground(new java.awt.Color(0, 153, 0));
        label_BottomPipe.setMaximumSize(new java.awt.Dimension(50, 50));
        label_BottomPipe.setMinimumSize(new java.awt.Dimension(50, 50));
        label_BottomPipe.setOpaque(true);
        label_BottomPipe.setPreferredSize(new java.awt.Dimension(50, 50));
        panel_Background.add(label_BottomPipe);
        label_BottomPipe.setBounds(600, 260, 100, 500);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_JumpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_JumpActionPerformed

        if (!gameOver) {
            label_Bird.setBounds(
                    label_Bird.getX(),
                    label_Bird.getY() - 40,
                    label_Bird.getWidth(),
                    label_Bird.getHeight());
        }

    }//GEN-LAST:event_button_JumpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Jump;
    private javax.swing.JLabel label_Bird;
    private javax.swing.JLabel label_BottomPipe;
    private javax.swing.JLabel label_GameOver;
    private javax.swing.JLabel label_Ground;
    private javax.swing.JLabel label_Score;
    private javax.swing.JLabel label_TopPipe;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
