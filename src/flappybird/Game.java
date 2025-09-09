package flappybird;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends javax.swing.JFrame {

    boolean gameOver = false;
    Random randomizer = new Random();
    int score = 0;

    public Game() {
        initComponents();

        GameOver.setVisible(false);

        int num = randomizer.nextInt(350 - 0 + 1) + 0;
        Pipe1.setBounds(
                800,
                -480 + num,
                Pipe1.getWidth(),
                Pipe1.getHeight()
        );
        Pipe.setBounds(
                800,
                200 + num,
                Pipe.getWidth(),
                Pipe.getHeight()
        );

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                Bird.setBounds(
                        Bird.getX(),
                        Bird.getY() + 10,
                        Bird.getWidth(),
                        Bird.getHeight());

                // move bird down
                if (Bird.getY() < 0) {
                    gameOver = true;
                    System.out.println("Touched the sky");
                }
                if (Bird.getY() > 450) {
                    gameOver = true;
                    System.out.println("Touched the ground");
                }

                // move pipe left
                Pipe1.setBounds(
                        Pipe1.getX() - 10,
                        Pipe1.getY(),
                        Pipe1.getWidth(),
                        Pipe1.getHeight());
                Pipe.setBounds(
                        Pipe.getX() - 10,
                        Pipe.getY(),
                        Pipe.getWidth(),
                        Pipe.getHeight());

                if (((Bird.getX() < Pipe1.getX() + Pipe1.getWidth()
                        && Bird.getX() + Bird.getWidth() > Pipe1.getX()
                        && Bird.getY() < Pipe1.getY() + Pipe1.getHeight()
                        && Bird.getY() + Bird.getHeight() > Pipe1.getY()))
                        || ((Bird.getX() < Pipe.getX() + Pipe.getWidth()
                        && Bird.getX() + Bird.getWidth() > Pipe.getX()
                        && Bird.getY() < Pipe.getY() + Pipe.getHeight()
                        && Bird.getY() + Bird.getHeight() > Pipe.getY()))) {
                    gameOver = true;
                    System.out.println("Collision.");
                }

                if (Pipe1.getX() < 0 - Pipe1.getWidth()) {
                    int num = randomizer.nextInt(350 - 0 + 1) + 0;
                    Pipe1.setBounds(
                            800,
                            -480 + num,
                            Pipe1.getWidth(),
                            Pipe1.getHeight());
                    Pipe.setBounds(
                            800,
                            200 + num,
                            Pipe.getWidth(),
                            Pipe.getHeight());
                }

                score++;
                String text = "Score: " + score;
                Score.setText(text);
                
                if (gameOver) {
                    timer.cancel();
                    GameOver.setVisible(true);
                }
                                    repaint();

            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 60);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        Score = new javax.swing.JLabel();
        GameOver = new javax.swing.JLabel();
        Ground = new javax.swing.JLabel();
        Bird = new javax.swing.JLabel();
        Jump = new javax.swing.JButton();
        Pipe = new javax.swing.JLabel();
        Pipe1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        Background.setBackground(new java.awt.Color(51, 102, 255));
        Background.setMaximumSize(new java.awt.Dimension(800, 600));
        Background.setMinimumSize(new java.awt.Dimension(800, 600));
        Background.setLayout(null);

        Score.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Score.setText("Score");
        Background.add(Score);
        Score.setBounds(10, 470, 240, 70);

        GameOver.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        GameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GameOver.setText("GAME OVER");
        Background.add(GameOver);
        GameOver.setBounds(260, 200, 290, 100);

        Ground.setBackground(new java.awt.Color(153, 255, 153));
        Ground.setMaximumSize(new java.awt.Dimension(50, 50));
        Ground.setMinimumSize(new java.awt.Dimension(50, 50));
        Ground.setOpaque(true);
        Ground.setPreferredSize(new java.awt.Dimension(50, 50));
        Background.add(Ground);
        Ground.setBounds(0, 460, 800, 140);

        Bird.setBackground(new java.awt.Color(255, 255, 204));
        Bird.setMaximumSize(new java.awt.Dimension(50, 50));
        Bird.setMinimumSize(new java.awt.Dimension(50, 50));
        Bird.setOpaque(true);
        Bird.setPreferredSize(new java.awt.Dimension(50, 50));
        Background.add(Bird);
        Bird.setBounds(400, 0, 50, 50);

        Jump.setBackground(new java.awt.Color(255, 255, 251, 0));
        Jump.setAutoscrolls(true);
        Jump.setBorder(null);
        Jump.setBorderPainted(false);
        Jump.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumpActionPerformed(evt);
            }
        });
        Background.add(Jump);
        Jump.setBounds(325, 513, 200, 80);

        Pipe.setBackground(new java.awt.Color(0, 153, 0));
        Pipe.setMaximumSize(new java.awt.Dimension(50, 50));
        Pipe.setMinimumSize(new java.awt.Dimension(50, 50));
        Pipe.setOpaque(true);
        Pipe.setPreferredSize(new java.awt.Dimension(50, 50));
        Background.add(Pipe);
        Pipe.setBounds(590, -420, 100, 500);

        Pipe1.setBackground(new java.awt.Color(0, 153, 0));
        Pipe1.setMaximumSize(new java.awt.Dimension(50, 50));
        Pipe1.setMinimumSize(new java.awt.Dimension(50, 50));
        Pipe1.setOpaque(true);
        Pipe1.setPreferredSize(new java.awt.Dimension(50, 50));
        Background.add(Pipe1);
        Pipe1.setBounds(600, 260, 100, 500);

        getContentPane().add(Background);
        Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JumpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumpActionPerformed

        if (!gameOver) {
            Bird.setBounds(
                    Bird.getX(),
                    Bird.getY() - 40,
                    Bird.getWidth(),
                    Bird.getHeight());
        }


    }//GEN-LAST:event_JumpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel Bird;
    private javax.swing.JLabel GameOver;
    private javax.swing.JLabel Ground;
    private javax.swing.JButton Jump;
    private javax.swing.JLabel Pipe;
    private javax.swing.JLabel Pipe1;
    private javax.swing.JLabel Score;
    // End of variables declaration//GEN-END:variables
}
