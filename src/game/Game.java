package game;

// misc.
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
// toys
import toys.Toy;
// levels
import obstacles.columns.Column;
import obstacles.grounds.GroundSetting;
import levels.Level;
import levels.BrickLand;
import levels.IceCreamLand;
import levels.backgrounds.Background;
import levels.parallaxes.ParallaxLevel;
import utility.OrderLayer;
// utilities
import utility.KeyInputHandler;
import utility.MouseInputHandler;

public class Game extends javax.swing.JFrame {

    // probably reorganize these variables
    boolean gameOver = false;
    Random randomizer = new Random();

    // if 15, then 150 is 1 second
    // if 0, then 1 is 1 second
    final int MILISECOND_DELAY = 15;
    KeyInputHandler playerKeyInput = new KeyInputHandler();
    MouseInputHandler playerMouseInput = new MouseInputHandler();

    final int MIMIMUM_GAP = 100;
    final int STARTING_GAP = 150;
    final int GAP_DECREASE = 5;

    int num = randomizer.nextInt(175, 425);
    int gap = 150;
    int spawnTime;

    final int GROUND_HEIGHT = 420;

    final int MOVEMENT_SPEED = 3;
    final int JUMP_HEIGHT = 50;
    final int GRAVITY = 2;

    final int STARTING_SPEED = 3;
    float speed;

    Toy toy;
    final int CHARACTER_SIZE = 40;

    // values set after constructor call
    private final int WINDOW_HEIGHT;
    private final int WINDOW_WIDTH;

    private final int RESIZED_WIDTH;

    int countdown;
    boolean immunity;

    Level level;

    ArrayList<Column> columnsList = new ArrayList();
    int toAdd = 0;

    int transitionTimer = 0;

    final int LEVEL_CHANGE_TIME = 1500;
    final int LEVEL_SPEEDUP_CHECKPOINT = LEVEL_CHANGE_TIME + 125;
    final int TRANSITION_TIME = LEVEL_SPEEDUP_CHECKPOINT - LEVEL_CHANGE_TIME + 125;

    public Game(int WINDOW_WIDTH, int WINDOW_HEIGHT, Toy toy) {
        this.WINDOW_WIDTH = WINDOW_WIDTH;
        this.WINDOW_HEIGHT = WINDOW_HEIGHT;

        initComponents();

        Dimension screenSize = panel_Background.getSize();
        RESIZED_WIDTH = screenSize.width;

        this.toy = toy;

        label_Charges.setText("Charges: " + toy.getCharges());

        startGame();
    }

    Background temporaryBackground;

    private void startGame() {

        hideButtons();

        // could make all these methods
        countdown = 0;
        immunity = false;

        // give controls to player
        playerKeyInput.reset(); // makes sure to reset the controls
        playerMouseInput.reset(); // makes sure to reset the controls
        this.addKeyListener(playerKeyInput);
        this.addMouseListener(playerMouseInput);
        this.addMouseMotionListener(playerMouseInput);

        label_Charges.setVisible(true);
        panel_Background.setComponentZOrder(label_Charges, OrderLayer.UI.layer);

        level = new BrickLand();

        // initialize objects here
        level.generateLeftGround(GroundSetting.NORMAL.value);
        level.generateRightGround(GroundSetting.OFFSET.value);

        panel_Background.add(level.getLeftGroundSprite());
        panel_Background.setComponentZOrder(level.getLeftGroundSprite(), OrderLayer.MIDDLEGROUND.layer);
        panel_Background.add(level.getRightGroundSprite());
        panel_Background.setComponentZOrder(level.getRightGroundSprite(), OrderLayer.MIDDLEGROUND.layer);

        // generate first layer of parallax
        level.generateLeftParallax(GroundSetting.NORMAL.value, ParallaxLevel.LEVEL_1);
        level.generateRightParallax(GroundSetting.OFFSET.value, ParallaxLevel.LEVEL_1);

        panel_Background.add(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_1));
        panel_Background.setComponentZOrder(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_1), OrderLayer.PARALLAX_1.layer);

        panel_Background.add(level.getRightParallaxSprite(ParallaxLevel.LEVEL_1));
        panel_Background.setComponentZOrder(level.getRightParallaxSprite(ParallaxLevel.LEVEL_1), OrderLayer.PARALLAX_1.layer);

        // generate second layer of parallax
        level.generateLeftParallax(GroundSetting.NORMAL.value, ParallaxLevel.LEVEL_2);
        level.generateRightParallax(GroundSetting.OFFSET.value, ParallaxLevel.LEVEL_2);

        panel_Background.add(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_2));
        panel_Background.setComponentZOrder(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_2), OrderLayer.PARALLAX_2.layer);

        panel_Background.add(level.getRightParallaxSprite(ParallaxLevel.LEVEL_2));
        panel_Background.setComponentZOrder(level.getRightParallaxSprite(ParallaxLevel.LEVEL_2), OrderLayer.PARALLAX_2.layer);

        // add player
        panel_Background.add(toy.getSprite());
        panel_Background.setComponentZOrder(toy.getSprite(), OrderLayer.FOREGROUND.layer);
        // make toy to small
        toy.setSize(CHARACTER_SIZE, CHARACTER_SIZE);
        // place toy into center
        toy.setLocation((RESIZED_WIDTH / 2) - (toy.getSprite().getWidth() / 2), 0);

        // initialize the background
        level.generateBackground();
        panel_Background.add(level.getBackgroundSprite());
        panel_Background.setComponentZOrder(level.getBackgroundSprite(), panel_Background.getComponentCount() - 1);

        // game loop is here
        ActionListener update = (ActionEvent evt) -> {

            // ALPHA feature in the making
            // calculate speed over time
            // make constants for both starting speed (2)
            // and speed time increase (500)
            if (toy.getScore() % LEVEL_SPEEDUP_CHECKPOINT == 0) {
                speed = (STARTING_SPEED + toy.getScore() / LEVEL_SPEEDUP_CHECKPOINT);

                // calculate lifetime of the column using
                // Time = Distance / Speed formula
                spawnTime = (int) ((785 + 90) / speed);
            }

            if (toy.getScore() > 0 && toy.getScore() % LEVEL_SPEEDUP_CHECKPOINT == 0) {
                transitionTimer = TRANSITION_TIME;

                Level oldLevel = level;
                if (level instanceof BrickLand) {
                    System.out.println("Icecreamland");
                    level = new IceCreamLand();
                } else {
                    System.out.println("Brickland");
                    level = new BrickLand();
                }

                temporaryBackground = oldLevel.getBackground();
                panel_Background.setComponentZOrder(temporaryBackground.getSprite(), panel_Background.getComponentCount() - 2);

                level.setLeftGround(oldLevel.getLeftGround());
                level.setRightGround(oldLevel.getRightGround());

                level.setLeftParallax(oldLevel.getLeftParallax(ParallaxLevel.LEVEL_1), ParallaxLevel.LEVEL_1);
                level.setRightParallax(oldLevel.getRightParallax(ParallaxLevel.LEVEL_1), ParallaxLevel.LEVEL_1);

                level.setLeftParallax(oldLevel.getLeftParallax(ParallaxLevel.LEVEL_2), ParallaxLevel.LEVEL_2);
                level.setRightParallax(oldLevel.getRightParallax(ParallaxLevel.LEVEL_2), ParallaxLevel.LEVEL_2);

                // initialize the background
                level.generateBackground();
                panel_Background.add(level.getBackgroundSprite());
                panel_Background.setComponentZOrder(level.getBackgroundSprite(), panel_Background.getComponentCount() - 1);
            }

            if (temporaryBackground != null
                    && temporaryBackground.getOpacity() > 0) {
                temporaryBackground.decrementOpacity(speed);
            }

            if (transitionTimer > 0) {
                transitionTimer--;
            }

            // handle player input
            if (!gameOver) {
                toy.getSprite().update();

                playerMouseInput.updateMovement();
                playerMouseInput.givePlayerX(toy.getSprite().getCenterX());

                if (playerMouseInput.jumped) {
                    // turn this into method
                    toy.move(0, -JUMP_HEIGHT);
                    playerMouseInput.jumped = false;
                } else {
                    toy.move(0, GRAVITY);
                }

                if (playerMouseInput.moveLeft) {
                    toy.move(-MOVEMENT_SPEED, 0);
                    playerMouseInput.moveLeft = false;
                }
                if (playerMouseInput.moveRight) {
                    toy.move(MOVEMENT_SPEED, 0);
                    playerMouseInput.moveRight = false;
                }

                if (playerKeyInput.abilityUsed) {
                    if (toy.useAbility()) {
                        label_Charges.setText("Charges: " + toy.getCharges());
                    }
                    playerKeyInput.abilityUsed = false;

                    // if shield is activated then give player 5 sec immunity
                    if (toy.hasShield()) {
                        countdown = (MILISECOND_DELAY * 10) * 5;
                        immunity = true;
                    }
                }
            } else {
                this.removeKeyListener(playerKeyInput);
                this.removeMouseListener(playerMouseInput);
                this.removeMouseMotionListener(playerMouseInput);
            }

            // sky and ground_left collision detection
            if (toy.getSprite().getY() < 0) {
                gameOver = true;
                System.out.println("Touched the sky");
            }
            if (toy.getSprite().getY() > GROUND_HEIGHT) {
                gameOver = true;
                System.out.println(level.groundKillEffect());
            }

            // move ground to the left
            level.moveLeftGround((int) -speed, 0);
            level.moveRightGround((int) -speed, 0);

            // move parallax to the left
            level.moveLeftParallax((int) -(Math.max(1, speed - 1)), 0, ParallaxLevel.LEVEL_1);
            level.moveRightParallax((int) -(Math.max(1, speed - 1)), 0, ParallaxLevel.LEVEL_1);

            level.moveLeftParallax((int) -(Math.max(1, speed - 2)), 0, ParallaxLevel.LEVEL_2);
            level.moveRightParallax((int) -(Math.max(1, speed - 2)), 0, ParallaxLevel.LEVEL_2);

            // could generate first gound here instead of outside
            // detect if ground has gone out of bounds to delete it and spawn a new one
            checkGroundOutOfBounds();
            checkParallaxOutOfBounds();

            if (!columnsList.isEmpty()) {
                // randomize where the column spawns
                num = randomizer.nextInt(175, 425);
                // could add constants here
                // reduces the gap between the columns by
                // 5 spaces in the y axis
                // every 1 speed added
                // with a max of 100
                gap = (int) Math.max(MIMIMUM_GAP, STARTING_GAP - ((speed - STARTING_SPEED) * GAP_DECREASE));

                ArrayList<Column> toRemove = new ArrayList();

                // iterate through every spawned column
                for (Column col : columnsList) {
                    // move column to the sprite
                    col.move((int) -speed, 0);

                    // column collision detection
                    if ((((toy.getSprite().getX() < col.bottom.getX() + col.bottom.getWidth()
                            && toy.getSprite().getX() + toy.getSprite().getWidth() > col.bottom.getX()
                            && toy.getSprite().getY() < col.bottom.getY() + col.bottom.getHeight()
                            && toy.getSprite().getY() + toy.getSprite().getHeight() > col.bottom.getY()))
                            || ((toy.getSprite().getX() < col.top.getX() + col.top.getWidth()
                            && toy.getSprite().getX() + toy.getSprite().getWidth() > col.top.getX()
                            && toy.getSprite().getY() < col.top.getY() + col.top.getHeight()
                            && toy.getSprite().getY() + toy.getSprite().getHeight() > col.top.getY())))
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

                        if (transitionTimer <= 0) {
                            toRemove.add(col);
                        }
                    }
                }

                // set the amoutn of columns to spawn to the amount deleted before
                toAdd = toRemove.size();

                // delete all the decayed columns
                columnsList.removeAll(toRemove);
            }

            // adds a new column to replace the decayed columns
            if ((toAdd > 0
                    || columnsList.isEmpty())
                    && transitionTimer <= 0) {
                level.generateColumn(gap, num, spawnTime);

                panel_Background.add(level.getBottomColumn());
                panel_Background.setComponentZOrder(level.getBottomColumn(), OrderLayer.COLUMNS.layer);
                panel_Background.add(level.getTopColumn());
                panel_Background.setComponentZOrder(level.getTopColumn(), OrderLayer.COLUMNS.layer);

                columnsList.add(level.getColumn());
            }

            // increment the score every frame
            toy.incrementScore();
            String text = "Score: " + toy.getScore();
            label_Score.setText(text);

            // decrement the countdown
            if (countdown > 0) {
                countdown--;
            } else {
                toy.setShield(false);
                immunity = false;
            }

            // handle game over
            if (gameOver) {
                Timer localTimer = (Timer) evt.getSource();
                localTimer.stop();

                label_GameOver.setVisible(true);
                panel_Background.setComponentZOrder(label_GameOver, OrderLayer.UI.layer);
                button_ChooseCharacter.setVisible(true);
                panel_Background.setComponentZOrder(button_ChooseCharacter, OrderLayer.UI.layer);
                button_PlayAgain.setVisible(true);
                panel_Background.setComponentZOrder(button_PlayAgain, OrderLayer.UI.layer);
            }
            repaint();
        };

        Timer timer = new Timer(MILISECOND_DELAY, update);
        timer.start();
    }

    private void checkGroundOutOfBounds() {
        int offsetX = GroundSetting.OFFSET.value;

        if (level.isLeftGroundOutOfBounds()) {
            level.generateLeftGround(offsetX);
            panel_Background.add(level.getLeftGroundSprite());
            panel_Background.setComponentZOrder(level.getLeftGroundSprite(), OrderLayer.MIDDLEGROUND.layer);
        }
        if (level.isRightGroundOutOfBounds()) {
            level.generateRightGround(offsetX);
            panel_Background.add(level.getRightGroundSprite());
            panel_Background.setComponentZOrder(level.getRightGroundSprite(), OrderLayer.MIDDLEGROUND.layer);
        }
    }

    private void checkParallaxOutOfBounds() {
        int offsetX = GroundSetting.OFFSET.value;
        int orderLayer;

        for (ParallaxLevel parallaxLevel : ParallaxLevel.values()) {
            switch (parallaxLevel) {
                case LEVEL_1 -> {
                    orderLayer = OrderLayer.PARALLAX_1.layer;
                }
                case LEVEL_2 -> {
                    orderLayer = OrderLayer.PARALLAX_2.layer;
                }
                default ->
                    throw new AssertionError(parallaxLevel.name());
            }

            if (level.isLeftParallaxOutOfBounds(parallaxLevel)) {
                level.generateLeftParallax(offsetX, parallaxLevel);
                panel_Background.add(level.getLeftParallaxSprite(parallaxLevel));
                panel_Background.setComponentZOrder(level.getLeftParallaxSprite(parallaxLevel), orderLayer);
            }
            if (level.isRightParallaxOutOfBounds(parallaxLevel)) {
                level.generateRightParallax(offsetX, parallaxLevel);
                panel_Background.add(level.getRightParallaxSprite(parallaxLevel));
                panel_Background.setComponentZOrder(level.getRightParallaxSprite(parallaxLevel), orderLayer);
            }
        }
    }

    private void hideButtons() {
        button_PlayAgain.setVisible(false);
        button_ChooseCharacter.setVisible(false);
        label_GameOver.setVisible(false);
    }

    private void resetGame() {

        panel_Background.remove(level.getBackgroundSprite());

        panel_Background.remove(toy.getSprite());

        // rework background removal handling
        panel_Background.remove(level.getLeftGroundSprite());
        panel_Background.remove(level.getRightGroundSprite());

        // add removal for parallaxes here
        for (ParallaxLevel parallaxLevel : ParallaxLevel.values()) {
            panel_Background.remove(level.getLeftParallaxSprite(parallaxLevel));
            panel_Background.remove(level.getRightParallaxSprite(parallaxLevel));
        }

        // remove every spawned column from the screen
        for (Column col : columnsList) {
            panel_Background.remove(col.bottom);
            panel_Background.remove(col.top);
        }
        columnsList.clear();

        // disable controls
        this.removeKeyListener(playerKeyInput);
        this.removeMouseListener(playerMouseInput);
        toy.resetScore();

        gameOver = false;

        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Charges = new javax.swing.JLabel();
        label_Score = new javax.swing.JLabel();
        label_GameOver = new javax.swing.JLabel();
        button_PlayAgain = new javax.swing.JButton();
        button_ChooseCharacter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
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

        label_Score.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Score.setText("Score");
        panel_Background.add(label_Score);
        label_Score.setBounds(10, 470, 780, 70);

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

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_ChooseCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChooseCharacterActionPerformed
        dispose();
        new CharacterSelection(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true);
    }//GEN-LAST:event_button_ChooseCharacterActionPerformed

    private void button_PlayAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_PlayAgainActionPerformed
        resetGame();
        startGame();
    }//GEN-LAST:event_button_PlayAgainActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ChooseCharacter;
    private javax.swing.JButton button_PlayAgain;
    private javax.swing.JLabel label_Charges;
    private javax.swing.JLabel label_GameOver;
    private javax.swing.JLabel label_Score;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
