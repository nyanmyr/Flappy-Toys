package game;

// misc.
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
// database
import database.PlayManager;
// collectibles.
import collectibles.BrickToken;
import collectibles.Charge;
import collectibles.Collectible;
import collectibles.PopsicleToken;
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
// sfx
import sfx.sounds.SoundPlayer;
import sfx.sounds.SoundFile;
import sfx.music.MusicFile;
import sfx.music.MusicPlayer;
// utilities
import utility.enums.OrderLayer;
import utility.inputhandling.KeyInputHandler;
import utility.inputhandling.MouseInputHandler;

public class Game extends javax.swing.JFrame {

    // eh. good enough
    boolean gameOver = false;
    Random randomizer = new Random();

    // if 15, then 150 is 1 second
    // if 0, then 1 is 1 second
    public static final int MILISECOND_DELAY = 15;

    KeyInputHandler playerKeyInput = new KeyInputHandler();
    MouseInputHandler playerMouseInput = new MouseInputHandler();

    final int MIMIMUM_GAP = 100;
    final int STARTING_GAP = 150;
    final int GAP_DECREASE = 5;

    final int GROUND_HEIGHT = 420;

//    final int MOVEMENT_SPEED = 3;
//    final int JUMP_HEIGHT = 50;
//    final int GRAVITY = 2;
    final int STARTING_SPEED = 3;
    int speed;

    Toy toy;
    final int CHARACTER_SIZE = 40;
//    int countdown;
//    boolean immunity;

    // values set after constructor call
    private final int WINDOW_HEIGHT;
    private final int WINDOW_WIDTH;
    private final int RESIZED_WIDTH;

    Level level;

    int transitionTimer;

    final int LEVEL_CHANGE_TIME = 1500;
    final int LEVEL_SPEEDUP_CHECKPOINT = LEVEL_CHANGE_TIME + 125;
    final int TRANSITION_TIME = LEVEL_SPEEDUP_CHECKPOINT - LEVEL_CHANGE_TIME + 125;

    int columnRandomY;
    int columnGap;
    int aliveTime;

    final int MIN_COLUMN_RESPAWN_GAP = 20;
    final int COLUMN_RESPAWN_GAP = 200;
    final int COLUMN_RESPAWN_DECREMENT = 15;
    int columnRespawnTimer;
    ArrayList<Column> columnsList = new ArrayList();

    int tokenRandomY;
    int tokenRespawnTimer;
    ArrayList<Collectible> tokenList = new ArrayList();

    final int CHARGE_COOLDOWN_START = 6;
    int chargeCooldown;
    ArrayList<Charge> chargeList = new ArrayList();

    public Game(int WINDOW_WIDTH, int WINDOW_HEIGHT, Toy toy) {
        this.WINDOW_WIDTH = WINDOW_WIDTH;
        this.WINDOW_HEIGHT = WINDOW_HEIGHT;

        initComponents();

        Dimension screenSize = panel_Background.getSize();
        RESIZED_WIDTH = screenSize.width;

        this.toy = toy;

        startGame();
    }

    Background temporaryBackground;

    Collectible screenCharge;
    Collectible screenToken;

    // <editor-fold desc="initialization methods">
    private void initializeVariables() {
        transitionTimer = 0;

        columnGap = 150;

        columnRespawnTimer = 0;
        columnRandomY = randomizer.nextInt(175, 425);

        tokenRespawnTimer = COLUMN_RESPAWN_GAP / 2;
        tokenRandomY = randomizer.nextInt(20, 400);

        chargeCooldown = CHARGE_COOLDOWN_START;
    }

    private void hideButtons() {
        button_PlayAgain.setVisible(false);
        button_ChooseCharacter.setVisible(false);
        label_GameOver.setVisible(false);
    }

    private void giveControls() {
        // give controls to player
        playerKeyInput.reset();
        playerMouseInput.reset();

        this.addKeyListener(playerKeyInput);
        this.addMouseListener(playerMouseInput);
        this.addMouseMotionListener(playerMouseInput);
    }

    private void resetCollectibles() {
        toy.setCharges(0);
        label_Charges.setText(String.valueOf(toy.getCharges()));
        label_Charges.setVisible(true);
        panel_Background.setComponentZOrder(label_Charges, getOrderLayer(OrderLayer.UI));

        toy.setTokensCollected(0);
        label_Collectibles.setText(String.valueOf(toy.getTokensCollected()));
        label_Collectibles.setVisible(true);
        panel_Background.setComponentZOrder(label_Collectibles, getOrderLayer(OrderLayer.UI));
    }

    private void generateLevel() {
        level = new BrickLand();

        // initialize objects here
        level.generateLeftGround(GroundSetting.NORMAL.value);
        level.generateRightGround(GroundSetting.OFFSET.value);

        panel_Background.add(level.getLeftGroundSprite());
        panel_Background.setComponentZOrder(level.getLeftGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));
        panel_Background.add(level.getRightGroundSprite());
        panel_Background.setComponentZOrder(level.getRightGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));

        // generate first layer of parallax
        level.generateLeftParallax(GroundSetting.NORMAL.value, ParallaxLevel.LEVEL_1);
        level.generateRightParallax(GroundSetting.OFFSET.value, ParallaxLevel.LEVEL_1);

        panel_Background.add(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_1));
        panel_Background.setComponentZOrder(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));

        panel_Background.add(level.getRightParallaxSprite(ParallaxLevel.LEVEL_1));
        panel_Background.setComponentZOrder(level.getRightParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));

        // generate second layer of parallax
        level.generateLeftParallax(GroundSetting.NORMAL.value, ParallaxLevel.LEVEL_2);
        level.generateRightParallax(GroundSetting.OFFSET.value, ParallaxLevel.LEVEL_2);

        panel_Background.add(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_2));
        panel_Background.setComponentZOrder(level.getLeftParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));

        panel_Background.add(level.getRightParallaxSprite(ParallaxLevel.LEVEL_2));
        panel_Background.setComponentZOrder(level.getRightParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));

        // initialize the background
        level.generateBackground();
        panel_Background.add(level.getBackgroundSprite());
        panel_Background.setComponentZOrder(level.getBackgroundSprite(), getOrderLayer(OrderLayer.BACKGROUND));
    }

    private void addPlayer() {
        // add player
        panel_Background.add(toy.getSprite());
        panel_Background.setComponentZOrder(toy.getSprite(), OrderLayer.FOREGROUND.layer);
        // make toy to small
        toy.setSize(CHARACTER_SIZE, CHARACTER_SIZE);
        // place toy into center`
        toy.setLocation((RESIZED_WIDTH / 2) - (toy.getSprite().getWidth() / 2), 0);
    }

    private void addChargeIcon() {
        screenCharge = new Charge(0);
        panel_Background.add(screenCharge.getSprite());
        screenCharge.setLocation(10, 10);
        screenCharge.setSize(50, 50);
        panel_Background.setComponentZOrder(screenCharge.getSprite(), getOrderLayer(OrderLayer.UI));
    }

    public void addTokenIcon(Collectible token) {
        // should probably make this change
        screenToken = token;
        panel_Background.add(screenToken.getSprite());
        screenToken.setLocation(10, 90);
        screenToken.setSize(50, 50);
        panel_Background.setComponentZOrder(screenToken.getSprite(), getOrderLayer(OrderLayer.UI));
    }

    private void setUIFront() {
        panel_Background.setComponentZOrder(label_Collectibles, getOrderLayer(OrderLayer.UI));
        panel_Background.setComponentZOrder(label_Charges, getOrderLayer(OrderLayer.UI));
        panel_Background.setComponentZOrder(label_Score, getOrderLayer(OrderLayer.UI));
    }
    // </editor-fold>

    private void startGame() {

        initializeVariables();

        hideButtons();

        giveControls();
        resetCollectibles();
        generateLevel();
        addPlayer();

        addChargeIcon();
        addTokenIcon(new BrickToken(0));

        setUIFront();

        MusicPlayer.crossfadeTo(MusicFile.BRICKLAND, 3000);

        // game loop is here
        ActionListener update = (ActionEvent evt) -> {

            updateDynamicSprites();

            // ALPHA feature in the making
            // calculate speed over time
            // make constants for both starting speed (2)
            // and speed time increase (500)
            if (toy.getScore() % LEVEL_SPEEDUP_CHECKPOINT == 0) {
                adjustSpeed();
            }

            // change level
            if (toy.getScore() > 0 && toy.getScore() % LEVEL_SPEEDUP_CHECKPOINT == 0) {
                changeLevel();
            }

            backgroundFade();

            // decrement transition timer
            if (transitionTimer > 0) {
                transitionTimer--;
            }

            // handle player input
            if (!gameOver) {
                handlePlayer();
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

            moveGround();
            moveParallax();

            if (!columnsList.isEmpty()) {
                handleColumns();
            }

            // adds a new column to replace the decayed columns
            if (columnRespawnTimer == 0
                    && transitionTimer <= 0) {
                addColumn();
            }

            if (!tokenList.isEmpty()) {
                handleTokens();
            }

            if (tokenRespawnTimer == 0
                    && transitionTimer <= 0) {
                addToken();
            }

            // decrement the column respawn timer
            if (transitionTimer <= 0) {
                columnRespawnTimer--;
                tokenRespawnTimer--;
            } else {
                // reset timer
                // is called while transitioning between levels
                columnRespawnTimer = getColumnRespawnTime();
                tokenRespawnTimer = getColumnRespawnTime() / 2;
            }

            // could generate first gound here instead of outside
            // detect if ground has gone out of bounds to delete it and spawn a new one
            checkGroundOutOfBounds();
            checkParallaxOutOfBounds();

            updateScore();

            // handle game over
            if (gameOver) {
                endGame(evt);
            }

            panel_Background.revalidate();
            panel_Background.repaint();
        };

        Timer timer = new Timer(MILISECOND_DELAY, update);
        timer.start();
    }

    // <editor-fold desc="game loop methods">
    private void updateDynamicSprites() {
        // update screen charge
        screenCharge.getSprite().update();
        screenToken.getSprite().update();
    }

    private void adjustSpeed() {
        speed = (STARTING_SPEED + toy.getScore() / LEVEL_SPEEDUP_CHECKPOINT);

        // calculate lifetime of the column using
        // Time = Distance / Speed formula
        aliveTime = (int) ((785 + 90) / speed);
    }

    private void changeLevel() {
        transitionTimer = TRANSITION_TIME;

        panel_Background.remove(screenToken.getSprite());

        Level oldLevel = level;
        MusicFile newMusic;
        if (level instanceof BrickLand) {
            addTokenIcon(new PopsicleToken(0));
            level = new IceCreamLand();
            newMusic = MusicFile.ICECREAM;
        } else {
            addTokenIcon(new BrickToken(0));
            level = new BrickLand();
            newMusic = MusicFile.BRICKLAND;
        }

        temporaryBackground = oldLevel.getBackground();

        level.setLeftGround(oldLevel.getLeftGround());
        level.setRightGround(oldLevel.getRightGround());

        level.setLeftParallax(oldLevel.getLeftParallax(ParallaxLevel.LEVEL_1), ParallaxLevel.LEVEL_1);
        level.setRightParallax(oldLevel.getRightParallax(ParallaxLevel.LEVEL_1), ParallaxLevel.LEVEL_1);

        level.setLeftParallax(oldLevel.getLeftParallax(ParallaxLevel.LEVEL_2), ParallaxLevel.LEVEL_2);
        level.setRightParallax(oldLevel.getRightParallax(ParallaxLevel.LEVEL_2), ParallaxLevel.LEVEL_2);

        // initialize the background
        level.generateBackground();
        panel_Background.add(level.getBackgroundSprite());

        panel_Background.setComponentZOrder(oldLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));
        panel_Background.setComponentZOrder(oldLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));

        panel_Background.setComponentZOrder(oldLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));
        panel_Background.setComponentZOrder(oldLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));

        panel_Background.setComponentZOrder(level.getBackgroundSprite(), getOrderLayer(OrderLayer.BACKGROUND));
        panel_Background.setComponentZOrder(temporaryBackground.getSprite(), getOrderLayer(OrderLayer.BACKGROUND) - 1);

        // transition music to new music
        MusicPlayer.crossfadeTo(newMusic, 6000);
    }

    private void backgroundFade() {
        if (temporaryBackground != null
                && temporaryBackground.getOpacity() > 0) {
            temporaryBackground.decrementOpacity(speed);
        }
    }

    private void handlePlayer() {
        toy.getSprite().update();
        toy.decrementAbilityCooldown();

        // decrement the countdown
        if (toy.isImmune()) {
            toy.decrementImmunity();
        }

        playerMouseInput.updateMovement();
        playerMouseInput.givePlayerX(toy.getSprite().getCenterX());

        if (playerMouseInput.jumped) {
            SoundPlayer.playSound(toy.getJumpSoundFile());
            // turn this into method
            toy.move(0, -toy.getJumpHeight());
            playerMouseInput.jumped = false;
        } else {
            toy.move(0, toy.getFallSpeed());
        }

        if (playerMouseInput.moveLeft) {
            toy.move(-toy.getMovementSpeed(), 0);
            playerMouseInput.moveLeft = false;
        }
        if (playerMouseInput.moveRight) {
            toy.move(toy.getMovementSpeed(), 0);
            playerMouseInput.moveRight = false;
        }

        if (playerKeyInput.abilityUsed) {
            if (toy.useAbility()) {
                SoundPlayer.playSound(SoundFile.ABILITY);
                label_Charges.setText(String.valueOf(toy.getCharges()));
            } else {
                SoundPlayer.playSound(SoundFile.NO_CHARGES);
            }
            playerKeyInput.abilityUsed = false;
        }
    }

    private void moveParallax() {
        for (ParallaxLevel parallaxLevel : ParallaxLevel.values()) {
            level.moveLeftParallax((int) -(Math.max(1, speed - 1)), 0, parallaxLevel);
            level.moveRightParallax((int) -(Math.max(1, speed - 1)), 0, parallaxLevel);
        }
    }

    private void moveGround() {
        // move ground to the left
        level.moveLeftGround((int) -speed, 0);
        level.moveRightGround((int) -speed, 0);
    }

    private void handleColumns() {
        // randomize where the column spawns
        columnRandomY = randomizer.nextInt(175, 425);

        // could add constants here
        // reduces the columnGap between the columns by
        // 5 spaces in the y axis
        // every 1 speed added
        // with a max of 100
        columnGap = (int) Math.max(MIMIMUM_GAP, STARTING_GAP - ((speed - STARTING_SPEED) * GAP_DECREASE));

        ArrayList<Column> columnsToRemove = new ArrayList();
        ArrayList<Collectible> chargesToRemove = new ArrayList();

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
                    && !toy.isImmune()
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
                    columnsToRemove.add(col);
                }
            }
        }

        // iterate through every spawned column
        for (Collectible charge : chargeList) {
            charge.getSprite().update();

            // move column to the sprite
            charge.move((int) -speed, 0);

            // charge collision detection
            if ((toy.getSprite().getX() < charge.getSprite().getX() + charge.getSprite().getWidth()
                    && toy.getSprite().getX() + toy.getSprite().getWidth() > charge.getSprite().getX()
                    && toy.getSprite().getY() < charge.getSprite().getY() + charge.getSprite().getHeight()
                    && toy.getSprite().getY() + toy.getSprite().getHeight() > charge.getSprite().getY())
                    && charge.isAlive()) {
                SoundPlayer.playSound(SoundFile.CHARGE);
                toy.receiveCollectible(charge);
                label_Charges.setText(String.valueOf(toy.getCharges()));
                charge.kill();
            }

            // delete the column after lifeTime expires
            if (charge.decay()) {
                // remove decayed column
                panel_Background.remove(charge.getSprite());

                if (transitionTimer <= 0) {
                    chargesToRemove.add(charge);
                }
            }
        }

        // delete all the decayed columns
        columnsList.removeAll(columnsToRemove);
        // including all the charges
        columnsList.removeAll(chargesToRemove);
    }

    private void addColumn() {
        columnRespawnTimer = getColumnRespawnTime();

        level.generateColumn(columnGap, columnRandomY, aliveTime);

        panel_Background.add(level.getBottomColumn());
        panel_Background.setComponentZOrder(level.getBottomColumn(), getOrderLayer(OrderLayer.COLUMNS));
        panel_Background.add(level.getTopColumn());
        panel_Background.setComponentZOrder(level.getTopColumn(), getOrderLayer(OrderLayer.COLUMNS));

        columnsList.add(level.getColumn());

        // only spawns the charge every 6th column
        if (chargeCooldown > 0) {
            chargeCooldown--;
        } else {
            chargeCooldown = CHARGE_COOLDOWN_START;
            Charge charge = new Charge(aliveTime);

            panel_Background.add(charge.getSprite());
            panel_Background.setComponentZOrder(charge.getSprite(), OrderLayer.UI.layer);
            // spawns the charge in between the gap
            charge.getSprite().setLocation(815,
                    level.getTopColumn().getY() + level.getTopColumn().getHeight() + (columnGap / 2) - 20);

            chargeList.add(charge);
        }
    }

    private void handleTokens() {
        ArrayList<Collectible> tokensToRemove = new ArrayList();

        tokenRandomY = randomizer.nextInt(20, 400);

        // iterate through every spawned column
        for (Collectible token : tokenList) {
            // animate token
            token.getSprite().update();

            // move column to the sprite
            token.move((int) -speed, 0);

            // column collision detection
            if ((toy.getSprite().getX() < token.getSprite().getX() + token.getSprite().getWidth()
                    && toy.getSprite().getX() + toy.getSprite().getWidth() > token.getSprite().getX()
                    && toy.getSprite().getY() < token.getSprite().getY() + token.getSprite().getHeight()
                    && toy.getSprite().getY() + toy.getSprite().getHeight() > token.getSprite().getY())
                    && token.isAlive()) {
                SoundPlayer.playSound(SoundFile.TOKEN);
                toy.receiveCollectible(token);
                label_Collectibles.setText(String.valueOf(toy.getTokensCollected()));
                token.kill();
            }

            // delete the token after lifeTime expires
            // or token is killed
            if (token.decay()) {
                // remove decayed column
                panel_Background.remove(token.getSprite());

                if (transitionTimer <= 0) {
                    tokensToRemove.add(token);
                }
            }
        }

        // delete all the decayed columns
        tokenList.removeAll(tokensToRemove);
    }

    private void addToken() {
        tokenRespawnTimer = getColumnRespawnTime();

        level.generateToken(aliveTime);

        panel_Background.add(level.getTokenSprite());
        // place the token to the offscreen right
        level.getTokenSprite().setLocation(815, tokenRandomY);
        panel_Background.setComponentZOrder(level.getTokenSprite(), getOrderLayer(OrderLayer.COLUMNS));

        tokenList.add(level.getToken());
    }

    private void checkGroundOutOfBounds() {
        int offsetX = GroundSetting.OFFSET.value;

        if (level.isLeftGroundOutOfBounds()) {
            level.generateLeftGround(offsetX);
            panel_Background.add(level.getLeftGroundSprite());
            panel_Background.setComponentZOrder(level.getLeftGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));
        }
        if (level.isRightGroundOutOfBounds()) {
            level.generateRightGround(offsetX);
            panel_Background.add(level.getRightGroundSprite());
            panel_Background.setComponentZOrder(level.getRightGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));
        }
    }

    private void checkParallaxOutOfBounds() {
        int offsetX = GroundSetting.OFFSET.value;
        int orderLayer;

        for (ParallaxLevel parallaxLevel : ParallaxLevel.values()) {
            switch (parallaxLevel) {
                case LEVEL_1 -> {
                    orderLayer = getOrderLayer(OrderLayer.PARALLAX_1);
                }
                case LEVEL_2 -> {
                    orderLayer = getOrderLayer(OrderLayer.PARALLAX_2);
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

    private void updateScore() {
        // increment the score every frame
        toy.incrementScore();
        String text = "Score: " + toy.getScore();
        label_Score.setText(text);
    }

    // calculates the columnGap between columns while taking speed into account
    private int getColumnRespawnTime() {
        int baseTime = (int) ((double) COLUMN_RESPAWN_GAP * STARTING_SPEED / speed);
        int decrement = (speed - STARTING_SPEED) * COLUMN_RESPAWN_DECREMENT;
        int adjustedTime = baseTime - decrement;
        return Math.max(adjustedTime, MIN_COLUMN_RESPAWN_GAP); // Prevents timer from becoming too small or negative
    }

    private void endGame(ActionEvent evt) {
        MusicPlayer.crossfadeTo(MusicFile.MENU, 3000);
        
        PlayManager.gameOver(toy);

        SoundPlayer.playSound(SoundFile.HURT);

        Timer localTimer = (Timer) evt.getSource();
        localTimer.stop();

        label_GameOver.setVisible(true);
        panel_Background.setComponentZOrder(label_GameOver, OrderLayer.UI.layer);
        button_ChooseCharacter.setVisible(true);
        panel_Background.setComponentZOrder(button_ChooseCharacter, OrderLayer.UI.layer);
        button_PlayAgain.setVisible(true);
        panel_Background.setComponentZOrder(button_PlayAgain, OrderLayer.UI.layer);
    }

    private void resetGame() {

        if (temporaryBackground != null) {
            panel_Background.remove(temporaryBackground.getSprite());
        }

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

        // remove every spawned charge from the screen
        for (Collectible charge : chargeList) {
            panel_Background.remove(charge.getSprite());
        }
        chargeList.clear();

        // remove every spawned token from the screen
        for (Collectible token : tokenList) {
            panel_Background.remove(token.getSprite());
        }
        tokenList.clear();

        // disable controls
        this.removeKeyListener(playerKeyInput);
        this.removeMouseListener(playerMouseInput);
        toy.resetScore();

        // remove screen icons
        panel_Background.remove(screenCharge.getSprite());
        panel_Background.remove(screenToken.getSprite());

        gameOver = false;

        panel_Background.revalidate();
        panel_Background.repaint();
    }
    // </editor-fold>

    private int getOrderLayer(OrderLayer layer) {
        switch (layer) {
            case UI -> {
                return 0;
            }
            case FOREGROUND -> {
                return 2;
            }
            case MIDDLEGROUND -> {
                return panel_Background.getComponentZOrder(toy.getSprite()) + 2;
            }
            case COLUMNS -> {
                return panel_Background.getComponentZOrder(level.getRightGroundSprite()) + 2;
            }
            case PARALLAX_1 -> {
                return panel_Background.getComponentCount() - 3;
            }
            case PARALLAX_2 -> {
                return panel_Background.getComponentCount() - 2;
            }
            case BACKGROUND -> {
                return panel_Background.getComponentCount() - 1;
            }
            default ->
                throw new AssertionError(layer.name());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Collectibles = new javax.swing.JLabel();
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

        label_Collectibles.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Collectibles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Collectibles.setText("Tokens");
        panel_Background.add(label_Collectibles);
        label_Collectibles.setBounds(70, 90, 170, 50);

        label_Charges.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Charges.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Charges.setText("Charges ");
        panel_Background.add(label_Charges);
        label_Charges.setBounds(70, 10, 170, 50);

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
    private javax.swing.JLabel label_Collectibles;
    private javax.swing.JLabel label_GameOver;
    private javax.swing.JLabel label_Score;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
