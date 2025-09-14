package flappybird;

// ground
import flappybird.grounds.Ground;
import flappybird.grounds.BrickGround;
import flappybird.grounds.IceCreamGround;
// toys
import flappybird.toys.Toy;
import flappybird.toys.Teddycopter;
import flappybird.toys.Rocketron;
import flappybird.toys.Foldy;
import flappybird.toys.ToyCharacter;
// column
import flappybird.columns.Column;
import flappybird.columns.BrickColumn;
import flappybird.columns.IceCreamColumn;
// utility
import flappybird.utility.PlayerInputHandler;
// misc.
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends javax.swing.JFrame {

    boolean gameOver = false;
    Random randomizer = new Random();
    int score = 0;

    // if 10, then 100 is 1 second
    final int MILISECOND_DELAY = 10;
    PlayerInputHandler playerInput = new PlayerInputHandler();

    int num = randomizer.nextInt(175, 425);
    int gap = 150;
    Column columns;

    final int MIN_TOP = 175;
    final int MAX_TOP = 425;
    final int GROUND_HEIGHT = 420;

    final int MOVEMENT_SPEED = 10;
    final int JUMP_HEIGHT = 50;
    final int GRAVITY = 2;
    final int PIPE_SPEED = 2;

    ToyCharacter selectedCharacter = ToyCharacter.TEDDYCOPTER;
    Toy toy = new Teddycopter();
    final int CHARACTER_SIZE = 40;

    Ground ground;

    public Game() {
        initComponents();

        // menu screen
        label_GameOver.setVisible(false);
        label_Score.setVisible(false);
        label_Charges.setVisible(false);

        setSelectedCharacter(selectedCharacter);
    }

    private void startGame() {

        // initialize objects here
        ground = new BrickGround();
        panel_Background.add(ground.ground_left);
        panel_Background.add(ground.ground_middle);
        panel_Background.add(ground.ground_right);

        num = randomizer.nextInt(175, 425);
        gap = 150;
        columns = new BrickColumn(gap, num);
        panel_Background.add(columns.bottom);
        panel_Background.add(columns.top);

        ActionListener update = (ActionEvent evt) -> {

            // handle player input
            if (!gameOver) {
                if (playerInput.jumped) {
                    // turn this into method
                    toy.move(0, -JUMP_HEIGHT);
                    playerInput.jumped = false;
                } else {
                    toy.move(0, GRAVITY);
                }

                if (playerInput.moveLeft) {
                    toy.move(-MOVEMENT_SPEED, 0);
                    playerInput.moveLeft = false;
                }
                if (playerInput.moveRight) {
                    toy.move(MOVEMENT_SPEED, 0);
                    playerInput.moveRight = false;
                }

                if (playerInput.abilityUsed) {
                    if (toy.useAbility()) {
                        label_Charges.setText("Charges: " + toy.charges);
                    }
                    playerInput.abilityUsed = false;
                }
            }

            // sky and ground collision detection
            if (toy.sprite.getY() < 0) {
                gameOver = true;
                System.out.println("Touched the sky");
            }
            if (toy.sprite.getY() > GROUND_HEIGHT) {
                gameOver = true;
                System.out.println("Touched the ground");
            }

            // move column to the left
            // turn this into a method too
            columns.move(-PIPE_SPEED, 0);
            ground.move(-PIPE_SPEED, 0);

            // column collision detection
            if (((toy.sprite.getX() < columns.bottom.getX() + columns.bottom.getWidth()
                    && toy.sprite.getX() + toy.sprite.getWidth() > columns.bottom.getX()
                    && toy.sprite.getY() < columns.bottom.getY() + columns.bottom.getHeight()
                    && toy.sprite.getY() + toy.sprite.getHeight() > columns.bottom.getY()))
                    || ((toy.sprite.getX() < columns.top.getX() + columns.top.getWidth()
                    && toy.sprite.getX() + toy.sprite.getWidth() > columns.top.getX()
                    && toy.sprite.getY() < columns.top.getY() + columns.top.getHeight()
                    && toy.sprite.getY() + toy.sprite.getHeight() > columns.top.getY()))) {
                gameOver = true;
                System.out.println("Collision.");
            }

            // detect if column has disappeared to the left
            if (columns.bottom.getX() < 0 - columns.bottom.getWidth()) {
                num = randomizer.nextInt(MIN_TOP, MAX_TOP);
                // make this dynamic eventually
                // hint: arrays?
                gap = 150;
                columns = new IceCreamColumn(gap, num);
                panel_Background.add(columns.bottom);
                panel_Background.add(columns.top);
            }

            // increment the score every frame
            score++;
            String text = "Score: " + score;

            label_Score.setText(text);
            if (gameOver) {

                Timer localTimer = (Timer) evt.getSource();
                localTimer.stop();

                // disable the movement controls
                this.removeKeyListener(playerInput);

                label_GameOver.setVisible(true);
            }
            repaint();
        };

        Timer timer = new Timer(MILISECOND_DELAY, update);
        timer.start();
    }

    private void setSelectedCharacter(ToyCharacter character) {
        panel_Background.remove(toy.sprite);

        switch (character) {
            case TEDDYCOPTER -> {
                toy = new Teddycopter();
                label_Name.setText("Teddycopter");
            }
            case ROCKETRON -> {
                toy = new Rocketron();
                label_Name.setText("Rocketron");
            }
            case FOLDY -> {
                toy = new Foldy();
                label_Name.setText("Foldy");
            }
            default ->
                throw new AssertionError(character.name());
        }

        panel_Background.add(toy.sprite);
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Charges = new javax.swing.JLabel();
        label_Name = new javax.swing.JLabel();
        label_Score = new javax.swing.JLabel();
        label_Title = new javax.swing.JLabel();
        label_GameOver = new javax.swing.JLabel();
        button_Start = new javax.swing.JButton();
        button_NextCharacter = new javax.swing.JButton();

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

        label_Charges.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Charges.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Charges.setText("Charges: ");
        panel_Background.add(label_Charges);
        label_Charges.setBounds(10, 10, 170, 50);

        label_Name.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Name.setText("Teddycopter");
        panel_Background.add(label_Name);
        label_Name.setBounds(10, 210, 170, 50);

        label_Score.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Score.setText("Score");
        panel_Background.add(label_Score);
        label_Score.setBounds(10, 470, 780, 70);

        label_Title.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("FLAPPY TOYBOX");
        panel_Background.add(label_Title);
        label_Title.setBounds(190, 240, 460, 110);

        label_GameOver.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_GameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameOver.setText("GAME OVER");
        panel_Background.add(label_GameOver);
        label_GameOver.setBounds(260, 200, 290, 100);

        button_Start.setText("Start");
        button_Start.setFocusable(false);
        button_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_StartActionPerformed(evt);
            }
        });
        panel_Background.add(button_Start);
        button_Start.setBounds(10, 330, 170, 50);

        button_NextCharacter.setText("Next");
        button_NextCharacter.setFocusable(false);
        button_NextCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_NextCharacterActionPerformed(evt);
            }
        });
        panel_Background.add(button_NextCharacter);
        button_NextCharacter.setBounds(10, 270, 170, 50);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_NextCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_NextCharacterActionPerformed

        selectedCharacter = selectedCharacter.nextCharacter();
        setSelectedCharacter(selectedCharacter);
    }//GEN-LAST:event_button_NextCharacterActionPerformed

    private void button_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_StartActionPerformed
        toy.setSize(CHARACTER_SIZE, CHARACTER_SIZE);
        // image center taken into account
        toy.setLocation(400 - (CHARACTER_SIZE / 2), 0);

        button_NextCharacter.setVisible(false);
        button_Start.setVisible(false);
        label_Title.setVisible(false);
        label_Name.setVisible(false);
        label_Score.setVisible(true);
        label_Charges.setVisible(true);

        label_Charges.setText("Charges: " + toy.charges);

        this.addKeyListener(playerInput);

        repaint();
        startGame();
    }//GEN-LAST:event_button_StartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_NextCharacter;
    private javax.swing.JButton button_Start;
    private javax.swing.JLabel label_Charges;
    private javax.swing.JLabel label_GameOver;
    private javax.swing.JLabel label_Name;
    private javax.swing.JLabel label_Score;
    private javax.swing.JLabel label_Title;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
