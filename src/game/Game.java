package game;

// misc.
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
// abilities
import abilities.Dash;
import abilities.JumpBoost;
import abilities.Shield;
// toys
import toys.Toy;
import toys.Teddycopter;
import toys.Rocketron;
import toys.Foldy;
import toys.ToyCharacter;
// column
import obstacles.columns.Column;
import obstacles.columns.BrickColumn;
// ground_left
import obstacles.grounds.Ground;
import obstacles.grounds.BrickGround;
import obstacles.grounds.GroundSetting;
// utility
import utility.PlayerInputHandler;

public class Game extends javax.swing.JFrame {

    // probably reorganize these variables
    boolean gameOver = false;
    Random randomizer = new Random();

    // if 10, then 100 is 1 second
    // if 0, then 1 is 1 second
    final int MILISECOND_DELAY = 10;
    PlayerInputHandler playerInput = new PlayerInputHandler();

    int num = randomizer.nextInt(175, 425);
    int gap = 150;
    int spawnTime;

    final int GROUND_HEIGHT = 420;

    final int MOVEMENT_SPEED = 10;
    final int JUMP_HEIGHT = 50;
    final int GRAVITY = 2;

    float speed = 2;

    ToyCharacter selectedCharacter = ToyCharacter.TEDDYCOPTER;
    Toy toy = new Teddycopter();
    final int CHARACTER_SIZE = 40;

    Ground ground_left;
    Ground ground_right;

    // values set after constructor call
    int WINDOW_WIDTH;

    int countdown;
    boolean immunity;

    ArrayList<Column> columnsList = new ArrayList();
    int toAdd = 0;

    public Game() {
        initComponents();

        // title screen
        hideGame();
        hideCharacterSelection();

        Dimension screenSize = panel_Background.getSize();
        WINDOW_WIDTH = screenSize.width;
    }

    private void startGame() {

        countdown = 0;
        immunity = false;

        // give controls to player
        playerInput.reset(); // makes sure to reset the controls
        this.addKeyListener(playerInput);

        label_Score.setVisible(true);
        label_Charges.setVisible(true);

        panel_Background.add(toy.sprite);
        // make toy to small
        toy.setSize(CHARACTER_SIZE, CHARACTER_SIZE);
        // place toy into center
        toy.setLocation((WINDOW_WIDTH / 2) - (toy.sprite.getWidth() / 2), 0);

        // initialize objects here
        ground_left = new BrickGround(GroundSetting.NORMAL.offset);
        panel_Background.add(ground_left.sprite);

        ground_right = new BrickGround(GroundSetting.OFFSET.offset);
        panel_Background.add(ground_right.sprite);

        ActionListener update = (ActionEvent evt) -> {

            // calculate speed over time
            // make constants for both starting speed (2)
            // and speed time increase (500)
            speed = (2 + toy.score / 500);

            // calculate lifetime of the column using
            // Time = Distance / Speed formula
            spawnTime = (int) ((785 + 90) / speed);

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

                    // if shield is activated then give player 5 sec immunity
                    if (toy.shield) {
                        countdown = 500;
                        immunity = true;
                    }
                }
            } else {
                this.removeKeyListener(playerInput);
            }

            // sky and ground_left collision detection
            if (toy.sprite.getY() < 0) {
                gameOver = true;
                System.out.println("Touched the sky");
            }
            if (toy.sprite.getY() > GROUND_HEIGHT) {
                gameOver = true;
                System.out.println(ground_left.killEffect());
            }

            // move the ground left
            ground_left.move((int) -speed, 0);
            ground_right.move((int) -speed, 0);

            // detect if ground has gone out of bounds to delete it and spawn a new one
            if (ground_left.outOfBoundsDetection()) {
                ground_left = new BrickGround(GroundSetting.OFFSET.offset);
                panel_Background.add(ground_left.sprite);
            }
            if (ground_right.outOfBoundsDetection()) {
                ground_right = new BrickGround(GroundSetting.OFFSET.offset);
                panel_Background.add(ground_right.sprite);

            }

            // randomize where the column spawns
            num = randomizer.nextInt(175, 425);
            // reduces the gap between the columns by
            // 5 spaces in the y axis
            // every 1 speed added
            // with a max of 100
            gap = (int) Math.max(100, 150 - ((speed - 2) * 5));

            ArrayList<Column> toRemove = new ArrayList();

            // iterate through every spawned column
            for (Column col : columnsList) {
                // move column to the sprite
                col.move((int) -speed, 0);

                // column collision detection
                if ((((toy.sprite.getX() < col.bottom.getX() + col.bottom.getWidth()
                        && toy.sprite.getX() + toy.sprite.getWidth() > col.bottom.getX()
                        && toy.sprite.getY() < col.bottom.getY() + col.bottom.getHeight()
                        && toy.sprite.getY() + toy.sprite.getHeight() > col.bottom.getY()))
                        || ((toy.sprite.getX() < col.top.getX() + col.top.getWidth()
                        && toy.sprite.getX() + toy.sprite.getWidth() > col.top.getX()
                        && toy.sprite.getY() < col.top.getY() + col.top.getHeight()
                        && toy.sprite.getY() + toy.sprite.getHeight() > col.top.getY())))
                        && !immunity
                        && col.isAlive()) {

                    gameOver = true;
                    System.out.println(col.killEffect());
                }

                // delete the column after lifeTime expires
                if (col.decay()) {
                    // remove decayed column
                    panel_Background.remove(col.bottom);
                    panel_Background.remove(col.top);

                    toRemove.add(col);
                }
            }

            // set the amoutn of columns to spawn to the amount deleted before
            toAdd = toRemove.size();

            // delete all the decayed columns
            columnsList.removeAll(toRemove);

            // adds a new column to replace the decayed columns
            if (toAdd > 0 || columnsList.isEmpty()) {
                Column newColumns = new BrickColumn(gap, num, spawnTime);
                panel_Background.add(newColumns.bottom);
                panel_Background.add(newColumns.top);
                columnsList.add(newColumns);
            }

            // increment the score every frame
            toy.score++;
            String text = "Score: " + toy.score;
            label_Score.setText(text);

            // decrement the countdown
            if (countdown > 0) {
                countdown--;
            } else {
                toy.shield = false;
                immunity = false;
            }

            // handle game over
            if (gameOver) {

                Timer localTimer = (Timer) evt.getSource();
                localTimer.stop();

                label_GameOver.setVisible(true);
                button_ChooseCharacter.setVisible(true);
                button_PlayAgain.setVisible(true);
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
                toy.setAbility(new JumpBoost());
                label_Name.setText("Teddycopter");
                label_Ability.setText("Jump Boost");
            }
            case ROCKETRON -> {
                toy = new Rocketron();
                toy.setAbility(new Shield());
                label_Name.setText("Rocketron");
                label_Ability.setText("Shield");
            }
            case FOLDY -> {
                toy = new Foldy();
                toy.setAbility(new Dash());
                label_Name.setText("Foldy");
                label_Ability.setText("Dash");
            }
            default ->
                throw new AssertionError(character.name());
        }

        toy.setLocation((WINDOW_WIDTH / 2) - (toy.sprite.getWidth() / 2), 50);
        panel_Background.add(toy.sprite);
        repaint();
    }

    private void hideGame() {
        label_GameOver.setVisible(false);
        label_Score.setVisible(false);
        label_Charges.setVisible(false);
        button_ChooseCharacter.setVisible(false);
        button_PlayAgain.setVisible(false);
    }

    private void hideCharacterSelection() {
        label_Name.setVisible(false);
        label_Ability.setVisible(false);
        label_Tutorial.setVisible(false);
        label_Objectives.setVisible(false);
        button_Start.setVisible(false);
        button_NextCharacter.setVisible(false);
    }

    private void openCharacterSelection() {
        hideGame();

        label_Name.setVisible(true);
        label_Ability.setVisible(true);
        button_Start.setVisible(true);
        button_NextCharacter.setVisible(true);
        label_Tutorial.setVisible(true);
        label_Objectives.setVisible(true);

        setSelectedCharacter(selectedCharacter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Objectives = new javax.swing.JLabel();
        label_Tutorial = new javax.swing.JLabel();
        label_Charges = new javax.swing.JLabel();
        label_Ability = new javax.swing.JLabel();
        label_Name = new javax.swing.JLabel();
        label_Score = new javax.swing.JLabel();
        label_Title = new javax.swing.JLabel();
        label_GameOver = new javax.swing.JLabel();
        button_PlayAgain = new javax.swing.JButton();
        button_ChooseCharacter = new javax.swing.JButton();
        button_Play = new javax.swing.JButton();
        button_Start = new javax.swing.JButton();
        button_NextCharacter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Objectives.setFont(new java.awt.Font("Comic Sans MS", 0, 19)); // NOI18N
        label_Objectives.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Objectives.setText("<html><p style=\"text-align: center;\"><b>Objectives:</b><br>Avoid Obstacles.<br>Survive for as long as you can.<br>Have fun!</p></html>");
        panel_Background.add(label_Objectives);
        label_Objectives.setBounds(490, 270, 290, 190);

        label_Tutorial.setFont(new java.awt.Font("Comic Sans MS", 0, 19)); // NOI18N
        label_Tutorial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Tutorial.setText("<html><p style=\"text-align: center;\">Press <b>W</b> to jump.<br> Press <b>A</b> to move left.<br> Press <b>D</b> to move right.<br> Press <b>SPACE</b> to use your ability<br>(if you have charges).</p></html>");
        panel_Background.add(label_Tutorial);
        label_Tutorial.setBounds(20, 270, 290, 190);

        label_Charges.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Charges.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Charges.setText("Charges: ");
        panel_Background.add(label_Charges);
        label_Charges.setBounds(10, 10, 170, 50);

        label_Ability.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Ability.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Ability.setText("Teddycopter");
        label_Ability.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Background.add(label_Ability);
        label_Ability.setBounds(315, 290, 170, 50);

        label_Name.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Name.setText("Teddycopter");
        label_Name.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_Background.add(label_Name);
        label_Name.setBounds(315, 230, 170, 50);

        label_Score.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Score.setText("Score");
        panel_Background.add(label_Score);
        label_Score.setBounds(10, 470, 780, 70);

        label_Title.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("FLAPPY TOYBOX");
        panel_Background.add(label_Title);
        label_Title.setBounds(170, 160, 460, 110);

        label_GameOver.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_GameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameOver.setText("GAME OVER");
        panel_Background.add(label_GameOver);
        label_GameOver.setBounds(260, 200, 290, 100);

        button_PlayAgain.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        button_PlayAgain.setText("PLAY AGAIN");
        button_PlayAgain.setFocusable(false);
        button_PlayAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_PlayAgainActionPerformed(evt);
            }
        });
        panel_Background.add(button_PlayAgain);
        button_PlayAgain.setBounds(315, 370, 170, 50);

        button_ChooseCharacter.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        button_ChooseCharacter.setText("CHOOSE A NEW CHARACTER");
        button_ChooseCharacter.setFocusable(false);
        button_ChooseCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChooseCharacterActionPerformed(evt);
            }
        });
        panel_Background.add(button_ChooseCharacter);
        button_ChooseCharacter.setBounds(290, 310, 210, 50);

        button_Play.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        button_Play.setText("PLAY");
        button_Play.setFocusable(false);
        button_Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_PlayActionPerformed(evt);
            }
        });
        panel_Background.add(button_Play);
        button_Play.setBounds(315, 275, 170, 50);

        button_Start.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        button_Start.setText("START");
        button_Start.setFocusable(false);
        button_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_StartActionPerformed(evt);
            }
        });
        panel_Background.add(button_Start);
        button_Start.setBounds(315, 410, 170, 50);

        button_NextCharacter.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        button_NextCharacter.setText("NEXT");
        button_NextCharacter.setFocusable(false);
        button_NextCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_NextCharacterActionPerformed(evt);
            }
        });
        panel_Background.add(button_NextCharacter);
        button_NextCharacter.setBounds(315, 350, 170, 50);

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
        hideCharacterSelection();

        label_Charges.setText("Charges: " + toy.charges);

        startGame();
    }//GEN-LAST:event_button_StartActionPerformed

    private void button_PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_PlayActionPerformed
        label_Title.setVisible(false);
        button_Play.setVisible(false);

        openCharacterSelection();
    }//GEN-LAST:event_button_PlayActionPerformed

    private void ResetGame() {
        // remove obstacles and player
        hideGame();

        panel_Background.remove(toy.sprite);

        // rework background removal handling
        panel_Background.remove(ground_left.sprite);
        panel_Background.remove(ground_right.sprite);

        // remove every spawned column from the screen
        for (Column col : columnsList) {
            panel_Background.remove(col.bottom);
            panel_Background.remove(col.top);

        }
        columnsList.clear();

        // disable controls
        this.removeKeyListener(playerInput);
        toy.score = 0;

        gameOver = false;

        repaint();
    }

    private void button_ChooseCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChooseCharacterActionPerformed
        ResetGame();
        openCharacterSelection();
    }//GEN-LAST:event_button_ChooseCharacterActionPerformed

    private void button_PlayAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_PlayAgainActionPerformed
        ResetGame();
        startGame();
    }//GEN-LAST:event_button_PlayAgainActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ChooseCharacter;
    private javax.swing.JButton button_NextCharacter;
    private javax.swing.JButton button_Play;
    private javax.swing.JButton button_PlayAgain;
    private javax.swing.JButton button_Start;
    private javax.swing.JLabel label_Ability;
    private javax.swing.JLabel label_Charges;
    private javax.swing.JLabel label_GameOver;
    private javax.swing.JLabel label_Name;
    private javax.swing.JLabel label_Objectives;
    private javax.swing.JLabel label_Score;
    private javax.swing.JLabel label_Title;
    private javax.swing.JLabel label_Tutorial;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
