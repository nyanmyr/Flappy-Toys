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
import collectibles.GearToken;
import collectibles.MushroomToken;
import collectibles.PopsicleToken;
import collectibles.ScarabToken;
import java.util.LinkedList;
import java.util.Queue;
// toys
import toys.Toy;
// levels
import obstacles.columns.Column;
import obstacles.grounds.GroundSetting;
import levels.AbstractLevel;
import levels.Bricks;
import levels.Desert;
import levels.Forest;
import levels.IceCream;
import levels.Level;
import levels.Steamworks;
import levels.backgrounds.Background;
import levels.parallaxes.ParallaxLevel;
// sfx
import sfx.sounds.SoundPlayer;
import sfx.sounds.SoundFile;
import sfx.music.MusicFile;
import sfx.music.MusicPlayer;
// utilities
import utility.enums.OrderLayer;
import utility.inputhandling.MouseInputHandler;

public class Game extends javax.swing.JFrame {

    // eh. good enough
    boolean gameOver = false;
    Random randomizer = new Random();

    // if 15, then 150 is 1 second
    // if 0, then 1 is 1 second
    public static final int MILISECOND_DELAY = 15;

    MouseInputHandler playerMouseInput = new MouseInputHandler();

    final int MIMIMUM_GAP = 100;
    final int STARTING_GAP = 150;
    final int GAP_DECREASE = 5;

    final int GROUND_HEIGHT = 420;

    final int STARTING_SPEED = 3;
    int speed;

    Toy toy;
    final int CHARACTER_SIZE = 40;
//    int countdown;
//    boolean immunity;

    // values set after constructor call
    private final int RESIZED_WIDTH;

    AbstractLevel gameLevel;

    int transitionTimer;

    // 750 for testing purposes
    final int LEVEL_CHANGE_SCORE = 1500;
    final int LEVEL_SPEEDUP_CHECKPOINT = LEVEL_CHANGE_SCORE + 125;
    final int TRANSITION_TIME = LEVEL_SPEEDUP_CHECKPOINT - LEVEL_CHANGE_SCORE + 125;

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

    public Game(Toy toy) {
        initComponents();

        Dimension screenSize = panel_Background.getSize();
        RESIZED_WIDTH = screenSize.width;

        this.toy = toy;

        startGame();
    }

    Background temporaryBackground;

    Collectible screenCharge;
    Collectible screenToken;

    Queue<Level> levelsQueue = new LinkedList();

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
        playerMouseInput.reset();

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

        levelsQueue.clear();

        gameLevel = generateRandomLevel(true);

        // initialize objects here
        gameLevel.generateLeftGround(GroundSetting.NORMAL.value);
        gameLevel.generateRightGround(GroundSetting.OFFSET.value);

        panel_Background.add(gameLevel.getLeftGroundSprite());
        panel_Background.setComponentZOrder(gameLevel.getLeftGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));
        panel_Background.add(gameLevel.getRightGroundSprite());
        panel_Background.setComponentZOrder(gameLevel.getRightGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));

        // generate first layer of parallax
        gameLevel.generateLeftParallax(GroundSetting.NORMAL.value, ParallaxLevel.LEVEL_1);
        gameLevel.generateRightParallax(GroundSetting.OFFSET.value, ParallaxLevel.LEVEL_1);

        panel_Background.add(gameLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_1));
        panel_Background.setComponentZOrder(gameLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));

        panel_Background.add(gameLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_1));
        panel_Background.setComponentZOrder(gameLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));

        // generate second layer of parallax
        gameLevel.generateLeftParallax(GroundSetting.NORMAL.value, ParallaxLevel.LEVEL_2);
        gameLevel.generateRightParallax(GroundSetting.OFFSET.value, ParallaxLevel.LEVEL_2);

        panel_Background.add(gameLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_2));
        panel_Background.setComponentZOrder(gameLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));

        panel_Background.add(gameLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_2));
        panel_Background.setComponentZOrder(gameLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));

        // initialize the background
        gameLevel.generateBackground();
        panel_Background.add(gameLevel.getBackgroundSprite());
        panel_Background.setComponentZOrder(gameLevel.getBackgroundSprite(), getOrderLayer(OrderLayer.BACKGROUND));

        // play the music of the levle
        gameLevel.generateMusic();
        MusicPlayer.crossfadeTo(gameLevel.getMusicFILE(), 3000);
    }

    private void addPlayer() {
        // add player
        panel_Background.add(toy.getSprite());
        panel_Background.setComponentZOrder(toy.getSprite(), OrderLayer.FOREGROUND.layer);
        // make toy to small
        toy.setSize(CHARACTER_SIZE, CHARACTER_SIZE);
        // place toy into center`
        toy.setLocation((RESIZED_WIDTH / 2) - (toy.getSprite().getWidth() / 2), 0);

        // adds the character ability sprite
        panel_Background.add(toy.getAbility().getSprite());
        panel_Background.setComponentZOrder(toy.getAbility().getSprite(), OrderLayer.FOREGROUND.layer + 1);

        // hidden away at start (only shows when ability is used)
        toy.getAbility().setSpriteVisiblity(false);
    }

    private void addChargeIcon() {
        screenCharge = new Charge(0);
        panel_Background.add(screenCharge.getSprite());
        screenCharge.setLocation(10, 10);
        screenCharge.setSize(50, 50);
        panel_Background.setComponentZOrder(screenCharge.getSprite(), getOrderLayer(OrderLayer.UI));
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
        addPlayer();

        generateLevel();

        addChargeIcon();

        setUIFront();

        // game loop is here
        ActionListener update = (ActionEvent evt) -> {

            // !!! find way to show that time ability is nearly over !!!
            if (!toy.getAbility().isTimerDone()) {
                toy.getAbility().decrementTimer();
                toy.setAbilitySpriteToPlayerLocation();

                boolean visibility = true;
                if (toy.getAbility().isNearlyDone()) {
                    visibility = toy.getAbility().isTimerDivisibleByFour();
                }

                toy.getAbility().setSpriteVisiblity(visibility);
            } else {
                toy.getAbility().setSpriteVisiblity(false);
            }

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
                System.out.println(gameLevel.groundKillEffect());
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

        // checks the current level, and the level before that
        // to determine the next level (without duplication)
        Level polledLevel = levelsQueue.poll();

        Level selectedLevel = polledLevel;

        while (selectedLevel == polledLevel && selectedLevel != levelsQueue.peek()) {
            selectedLevel = getRandomLevel();
        }
        levelsQueue.add(selectedLevel);

        AbstractLevel oldLevel = gameLevel;
        switch (selectedLevel) {
            case BRICKS -> {
                addTokenIcon(new BrickToken(0));
                gameLevel = new IceCream();
            }
            case ICECREAM -> {
                addTokenIcon(new PopsicleToken(0));
                gameLevel = new IceCream();
            }
            case DESERT -> {
                addTokenIcon(new ScarabToken(0));
                gameLevel = new Desert();
            }
            case FOREST -> {
                addTokenIcon(new MushroomToken(0));
                gameLevel = new Forest();
            }
            case STEAMWORKS -> {
                addTokenIcon(new GearToken(0));
                gameLevel = new Steamworks();
            }
            default ->
                throw new AssertionError(selectedLevel.name());
        }

        gameLevel.generateMusic();
        MusicPlayer.crossfadeTo(gameLevel.getMusicFILE(), 3000);

        temporaryBackground = oldLevel.getBackground();

        gameLevel.setLeftGround(oldLevel.getLeftGround());
        gameLevel.setRightGround(oldLevel.getRightGround());

        gameLevel.setLeftParallax(oldLevel.getLeftParallax(ParallaxLevel.LEVEL_1), ParallaxLevel.LEVEL_1);
        gameLevel.setRightParallax(oldLevel.getRightParallax(ParallaxLevel.LEVEL_1), ParallaxLevel.LEVEL_1);

        gameLevel.setLeftParallax(oldLevel.getLeftParallax(ParallaxLevel.LEVEL_2), ParallaxLevel.LEVEL_2);
        gameLevel.setRightParallax(oldLevel.getRightParallax(ParallaxLevel.LEVEL_2), ParallaxLevel.LEVEL_2);

        // initialize the background
        gameLevel.generateBackground();
        panel_Background.add(gameLevel.getBackgroundSprite());

        panel_Background.setComponentZOrder(oldLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));
        panel_Background.setComponentZOrder(oldLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_1), getOrderLayer(OrderLayer.PARALLAX_1));

        panel_Background.setComponentZOrder(oldLevel.getLeftParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));
        panel_Background.setComponentZOrder(oldLevel.getRightParallaxSprite(ParallaxLevel.LEVEL_2), getOrderLayer(OrderLayer.PARALLAX_2));

        panel_Background.setComponentZOrder(gameLevel.getBackgroundSprite(), getOrderLayer(OrderLayer.BACKGROUND));
        panel_Background.setComponentZOrder(temporaryBackground.getSprite(), getOrderLayer(OrderLayer.BACKGROUND) - 1);
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

        if (playerMouseInput.abilityUsed) {
            if (toy.useAbility()) {
                label_Charges.setText(String.valueOf(toy.getCharges()));
            }
            playerMouseInput.abilityUsed = false;
        }
    }

    private void moveParallax() {
        for (ParallaxLevel parallaxLevel : ParallaxLevel.values()) {
            int parallaxSpeed = parallaxLevel == ParallaxLevel.LEVEL_1 ? speed - 1 : speed - 2;
            gameLevel.moveLeftParallax((int) -(Math.max(1, parallaxSpeed)), 0, parallaxLevel);
            gameLevel.moveRightParallax((int) -(Math.max(1, parallaxSpeed)), 0, parallaxLevel);
        }
    }

    private void moveGround() {
        // move ground to the left
        gameLevel.moveLeftGround((int) -speed, 0);
        gameLevel.moveRightGround((int) -speed, 0);
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

        gameLevel.generateColumn(columnGap, columnRandomY, aliveTime);

        panel_Background.add(gameLevel.getBottomColumn());
        panel_Background.setComponentZOrder(gameLevel.getBottomColumn(), getOrderLayer(OrderLayer.COLUMNS));
        panel_Background.add(gameLevel.getTopColumn());
        panel_Background.setComponentZOrder(gameLevel.getTopColumn(), getOrderLayer(OrderLayer.COLUMNS));

        columnsList.add(gameLevel.getColumn());

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
                    gameLevel.getTopColumn().getY() + gameLevel.getTopColumn().getHeight() + (columnGap / 2) - 20);

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

        gameLevel.generateToken(aliveTime);

        panel_Background.add(gameLevel.getTokenSprite());
        // place the token to the offscreen right
        gameLevel.getTokenSprite().setLocation(815, tokenRandomY);
        panel_Background.setComponentZOrder(gameLevel.getTokenSprite(), getOrderLayer(OrderLayer.COLUMNS));

        tokenList.add(gameLevel.getToken());
    }

    private void checkGroundOutOfBounds() {
        int offsetX = GroundSetting.OFFSET.value;

        if (gameLevel.isLeftGroundOutOfBounds()) {
            gameLevel.generateLeftGround(offsetX);
            panel_Background.add(gameLevel.getLeftGroundSprite());
            panel_Background.setComponentZOrder(gameLevel.getLeftGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));
        }
        if (gameLevel.isRightGroundOutOfBounds()) {
            gameLevel.generateRightGround(offsetX);
            panel_Background.add(gameLevel.getRightGroundSprite());
            panel_Background.setComponentZOrder(gameLevel.getRightGroundSprite(), getOrderLayer(OrderLayer.MIDDLEGROUND));
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

            if (gameLevel.isLeftParallaxOutOfBounds(parallaxLevel)) {
                gameLevel.generateLeftParallax(offsetX, parallaxLevel);
                panel_Background.add(gameLevel.getLeftParallaxSprite(parallaxLevel));
                panel_Background.setComponentZOrder(gameLevel.getLeftParallaxSprite(parallaxLevel), orderLayer);
            }
            if (gameLevel.isRightParallaxOutOfBounds(parallaxLevel)) {
                gameLevel.generateRightParallax(offsetX, parallaxLevel);
                panel_Background.add(gameLevel.getRightParallaxSprite(parallaxLevel));
                panel_Background.setComponentZOrder(gameLevel.getRightParallaxSprite(parallaxLevel), orderLayer);
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

        panel_Background.remove(gameLevel.getBackgroundSprite());

        panel_Background.remove(toy.getSprite());

        // rework background removal handling
        panel_Background.remove(gameLevel.getLeftGroundSprite());
        panel_Background.remove(gameLevel.getRightGroundSprite());

        // add removal for parallaxes here
        for (ParallaxLevel parallaxLevel : ParallaxLevel.values()) {
            panel_Background.remove(gameLevel.getLeftParallaxSprite(parallaxLevel));
            panel_Background.remove(gameLevel.getRightParallaxSprite(parallaxLevel));
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

    // <editor-fold desc="utility methods">
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
                return panel_Background.getComponentZOrder(gameLevel.getRightGroundSprite()) + 2;
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

    private Level getRandomLevel() {
        int selectedLevel = randomizer.nextInt(1, 6);
        switch (selectedLevel) {
            case 1 -> {
                return Level.BRICKS;
            }
            case 2 -> {
                return Level.ICECREAM;
            }
            case 3 -> {
                return Level.DESERT;
            }
            case 4 -> {
                return Level.FOREST;
            }
            case 5 -> {
                return Level.STEAMWORKS;
            }
            default -> {
                throw new RuntimeException("Error: Level selection");
            }
        }
    }

    private AbstractLevel generateRandomLevel(boolean addToQueue) {
        int selectedLevel = randomizer.nextInt(1, 6);

        AbstractLevel generatedLevel;
        Level queueLevel;
        Collectible generatedToken;

        switch (selectedLevel) {
            case 1 -> {
                generatedLevel = new Bricks();
                queueLevel = Level.BRICKS;
                generatedToken = new BrickToken(0);
            }
            case 2 -> {
                generatedLevel = new IceCream();
                queueLevel = Level.ICECREAM;
                generatedToken = new PopsicleToken(0);
            }
            case 3 -> {
                generatedLevel = new Desert();
                queueLevel = Level.DESERT;
                generatedToken = new ScarabToken(0);
            }
            case 4 -> {
                generatedLevel = new Forest();
                queueLevel = Level.FOREST;
                generatedToken = new MushroomToken(0);
            }
            case 5 -> {
                generatedLevel = new Steamworks();
                queueLevel = Level.STEAMWORKS;
                generatedToken = new GearToken(0);
            }
            default -> {
                throw new RuntimeException("Error: Level selection");
            }
        }

        if (addToQueue) {
            levelsQueue.add(queueLevel);
        }

        addTokenIcon(generatedToken);

        return generatedLevel;
    }

    public void addTokenIcon(Collectible token) {
        if (screenToken != null) {
            panel_Background.remove(screenToken.getSprite());
        }
        screenToken = token;
        panel_Background.add(screenToken.getSprite());
        screenToken.setLocation(10, 90);
        screenToken.setSize(50, 50);
        panel_Background.setComponentZOrder(screenToken.getSprite(), getOrderLayer(OrderLayer.UI));
    }
    // </editor-fold>
    
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
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Collectibles.setBackground(new java.awt.Color(255, 255, 255, 32));
        label_Collectibles.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Collectibles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Collectibles.setText("Tokens");
        label_Collectibles.setOpaque(true);
        panel_Background.add(label_Collectibles);
        label_Collectibles.setBounds(70, 90, 170, 50);

        label_Charges.setBackground(new java.awt.Color(255, 255, 255, 32));
        label_Charges.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Charges.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Charges.setText("Charges ");
        label_Charges.setOpaque(true);
        panel_Background.add(label_Charges);
        label_Charges.setBounds(70, 10, 170, 50);

        label_Score.setBackground(new java.awt.Color(255, 255, 255, 32));
        label_Score.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Score.setText("Score");
        label_Score.setOpaque(true);
        panel_Background.add(label_Score);
        label_Score.setBounds(10, 470, 780, 70);

        label_GameOver.setBackground(new java.awt.Color(255, 255, 255, 32));
        label_GameOver.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_GameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameOver.setText("GAME OVER");
        label_GameOver.setOpaque(true);
        panel_Background.add(label_GameOver);
        label_GameOver.setBounds(260, 200, 290, 100);

        button_PlayAgain.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        button_PlayAgain.setText("PLAY AGAIN");
        button_PlayAgain.setFocusable(false);
        button_PlayAgain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_PlayAgainMouseEntered(evt);
            }
        });
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
        button_ChooseCharacter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_ChooseCharacterMouseEntered(evt);
            }
        });
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

    // <editor-fold desc="button methods">
    private void button_ChooseCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChooseCharacterActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        new CharacterSelection().setVisible(true);
    }//GEN-LAST:event_button_ChooseCharacterActionPerformed

    private void button_PlayAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_PlayAgainActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        resetGame();
        startGame();
    }//GEN-LAST:event_button_PlayAgainActionPerformed

    private void button_ChooseCharacterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ChooseCharacterMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ChooseCharacterMouseEntered

    private void button_PlayAgainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_PlayAgainMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_PlayAgainMouseEntered
    // </editor-fold>
    
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
