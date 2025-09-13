package flappybird;

import flappybird.objects.Pipe;
import flappybird.objects.Toy;
import flappybird.objects.Floor;
import flappybird.gameplay.PlayerInputHandler;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends javax.swing.JFrame {

    boolean gameOver = false;
    Random randomizer = new Random();
    int score = 0;

    final int FPS = 60;
    PlayerInputHandler playerInput = new PlayerInputHandler();

    int num = randomizer.nextInt(175, 425);
    int gap = 150;
    Pipe pipes;
    
    final int MIN_TOP = 175;
    final int MAX_TOP = 425;
    final int GROUND_HEIGHT = 420;
    
    final int MOVEMENT_SPEED = 10;
    final int JUMP_HEIGHT = 40;
    final int GRAVITY = 10;

    public Game() {
        initComponents();
        this.addKeyListener(playerInput);

        label_GameOver.setVisible(false);

        // initialize objects here
        Toy toy = new Toy();
        panel_Background.add(toy.sprite);

        Floor floor = new Floor();
        panel_Background.add(floor.floor);

        num = randomizer.nextInt(175, 425);
        gap = 150;
        pipes = new Pipe(gap, num);
        panel_Background.add(pipes.bottom);
        panel_Background.add(pipes.top);

        ActionListener update = (ActionEvent evt) -> {

            // handle player input
            if (!gameOver) {
                if (playerInput.jumped) {
                    // turn this into method
                    toy.sprite.setBounds(
                            toy.sprite.getX(),
                            toy.sprite.getY() - JUMP_HEIGHT,
                            toy.sprite.getWidth(),
                            toy.sprite.getHeight());
                    playerInput.jumped = false;
                } else {
                    toy.sprite.setBounds(
                            toy.sprite.getX(),
                                toy.sprite.getY() + GRAVITY,
                            toy.sprite.getWidth(),
                            toy.sprite.getHeight());
                }

                if (playerInput.moveLeft) {
                    toy.sprite.setBounds(toy.sprite.getX() - MOVEMENT_SPEED,
                            toy.sprite.getY(),
                            toy.sprite.getWidth(),
                            toy.sprite.getHeight());
                    playerInput.moveLeft = false;
                }
                if (playerInput.moveRight) {
                    toy.sprite.setBounds(toy.sprite.getX() + MOVEMENT_SPEED,
                            toy.sprite.getY(),
                            toy.sprite.getWidth(),
                            toy.sprite.getHeight());
                    playerInput.moveRight = false;
                }
            }

            // move bird down
            if (toy.sprite.getY() < 0) {
                gameOver = true;
                System.out.println("Touched the sky");
            }
            if (toy.sprite.getY() > GROUND_HEIGHT) {
                gameOver = true;
                System.out.println("Touched the ground");
            }

            // move pipe to the left
            pipes.bottom.setBounds(
                    pipes.bottom.getX() - 10,
                    pipes.bottom.getY(),
                    pipes.bottom.getWidth(),
                    pipes.bottom.getHeight());
            pipes.top.setBounds(
                    pipes.top.getX() - 10,
                    pipes.top.getY(),
                    pipes.top.getWidth(),
                    pipes.top.getHeight());

            // pipe collision detection
            if (((toy.sprite.getX() < pipes.bottom.getX() + pipes.bottom.getWidth()
                    && toy.sprite.getX() + toy.sprite.getWidth() > pipes.bottom.getX()
                    && toy.sprite.getY() < pipes.bottom.getY() + pipes.bottom.getHeight()
                    && toy.sprite.getY() + toy.sprite.getHeight() > pipes.bottom.getY()))
                    || ((toy.sprite.getX() < pipes.top.getX() + pipes.top.getWidth()
                    && toy.sprite.getX() + toy.sprite.getWidth() > pipes.top.getX()
                    && toy.sprite.getY() < pipes.top.getY() + pipes.top.getHeight()
                    && toy.sprite.getY() + toy.sprite.getHeight() > pipes.top.getY()))) {
                gameOver = true;
                System.out.println("Collision.");
            }
            
            // move pipe to left
            if (pipes.bottom.getX() < 0 - pipes.bottom.getWidth()) {
                num = randomizer.nextInt(MIN_TOP, MAX_TOP);
                // make this dynamic eventually
                // hint: arrays?
                gap = 150;
                pipes = new Pipe(gap, num);
                panel_Background.add(pipes.bottom);
                panel_Background.add(pipes.top);
            }
            score++;
            String text = "Score: " + score;
            label_Score.setText(text);
            if (gameOver) {
                Timer localTimer = (Timer) evt.getSource();
                localTimer.stop();
                label_GameOver.setVisible(true);
            }
        };

        Timer timer = new Timer(FPS, update);
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Score = new javax.swing.JLabel();
        label_GameOver = new javax.swing.JLabel();

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

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label_GameOver;
    private javax.swing.JLabel label_Score;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
