package game;

import abilities.Dash;
import abilities.JumpBoost;
import abilities.Shield;
import static game.Game.MILISECOND_DELAY;
import static game.Main.SCREEN_HEIGHT;
import static game.Main.SCREEN_WIDTH;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import toys.Foldy;
import toys.Rocketron;
import toys.Teddycopter;
import toys.Toy;
import toys.ToyCharacter;
import toys.stats.FallSpeed;
import toys.stats.JumpHeight;
import toys.stats.MovementSpeed;
import utility.sprites.StaticSprite;

public class CharacterSelection extends javax.swing.JFrame {

    private final int RESIZED_WIDTH;

    private StaticSprite background;

    Toy toy;
    ToyCharacter selectedCharacter = ToyCharacter.TEDDYCOPTER;

    Timer timer;
    
    public CharacterSelection() {
        initComponents();

        Dimension screenSize = panel_Background.getSize();
        RESIZED_WIDTH = screenSize.width;

        setSelectedCharacter(ToyCharacter.TEDDYCOPTER);

        LoadSprite();
        panel_Background.add(background);

        ActionListener update = (ActionEvent evt) -> {
            toy.getSprite().update();
        };

        timer = new Timer(MILISECOND_DELAY, update);
        timer.start();
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

                background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            } else {
                throw new RuntimeException("Image resource not found: brickland_bg.jpg");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    private void setSelectedCharacter(ToyCharacter character) {

        if (toy != null) {
            panel_Background.remove(toy.getSprite());
        }

        selectedCharacter = character;

        switch (character) {
            case TEDDYCOPTER -> {
                toy = new Teddycopter(new JumpBoost());
                label_Name.setText("Teddycopter");
                label_Ability.setText("Jump Boost");
            }
            case ROCKETRON -> {
                toy = new Rocketron(new Shield());
                label_Name.setText("Rocketron");
                label_Ability.setText("Shield");
            }
            case FOLDY -> {
                toy = new Foldy(new Dash());
                label_Name.setText("Foldy");
                label_Ability.setText("Dash");
            }
            default ->
                throw new AssertionError(character.name());
        }

        bar_Falling.setValue(toy.getFallSpeed());
        bar_Moving.setValue(toy.getMovementSpeed());
        bar_Jumping.setValue(toy.getJumpHeight());

        toy.setSize(200, 200);
        toy.setLocation((RESIZED_WIDTH / 2) - (toy.getSprite().getWidth() / 2), 25);
        panel_Background.add(toy.getSprite());
        panel_Background.setComponentZOrder(toy.getSprite(), 0);
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Falling = new javax.swing.JLabel();
        label_Moving = new javax.swing.JLabel();
        label_Jumping = new javax.swing.JLabel();
        bar_Falling = new javax.swing.JProgressBar();
        bar_Moving = new javax.swing.JProgressBar();
        bar_Jumping = new javax.swing.JProgressBar();
        label_Objectives = new javax.swing.JLabel();
        label_Tutorial = new javax.swing.JLabel();
        label_Ability = new javax.swing.JLabel();
        label_Name = new javax.swing.JLabel();
        button_NextCharacter = new javax.swing.JButton();
        button_Start = new javax.swing.JButton();
        button_Return = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Falling.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Falling.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Falling.setText("Falling:");
        label_Falling.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Background.add(label_Falling);
        label_Falling.setBounds(500, 70, 140, 30);

        label_Moving.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Moving.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Moving.setText("Moving:");
        label_Moving.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Background.add(label_Moving);
        label_Moving.setBounds(500, 110, 140, 30);

        label_Jumping.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Jumping.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Jumping.setText("Jumping:");
        label_Jumping.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Background.add(label_Jumping);
        label_Jumping.setBounds(500, 150, 140, 30);

        bar_Falling.setForeground(new java.awt.Color(0, 74, 173));
        bar_Falling.setMaximum(FallSpeed.FAST.speed);
        bar_Falling.setValue(2);
        panel_Background.add(bar_Falling);
        bar_Falling.setBounds(650, 70, 120, 30);

        bar_Moving.setForeground(new java.awt.Color(0, 74, 173));
        bar_Moving.setMaximum(MovementSpeed.FAST.speed);
        bar_Moving.setMinimum(1);
        panel_Background.add(bar_Moving);
        bar_Moving.setBounds(650, 110, 120, 30);

        bar_Jumping.setForeground(new java.awt.Color(0, 74, 173));
        bar_Jumping.setMaximum(JumpHeight.HIGH.height);
        bar_Jumping.setMinimum(30);
        bar_Jumping.setToolTipText("");
        bar_Jumping.setValue(50);
        panel_Background.add(bar_Jumping);
        bar_Jumping.setBounds(650, 150, 120, 30);

        label_Objectives.setFont(new java.awt.Font("Comic Sans MS", 0, 19)); // NOI18N
        label_Objectives.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Objectives.setText("<html><p style=\"text-align: center;\"><b>Objectives:</b><br>Avoid Obstacles.<br>Survive for as long as you can.<br>Have fun!</p></html>");
        panel_Background.add(label_Objectives);
        label_Objectives.setBounds(520, 270, 260, 240);

        label_Tutorial.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Tutorial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Tutorial.setText("<html><body style=\"text-align: center\">Click <b>LEFT MOUSE CLICK</b> to jump <br /><b>MOVE</b> your mouse left and right to move left and right <br />Press <b>SPACE</b> to use your ability (if you have charges)</body></html>");
        panel_Background.add(label_Tutorial);
        label_Tutorial.setBounds(20, 270, 260, 240);

        label_Ability.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Ability.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Ability.setText("Teddycopter");
        label_Ability.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Background.add(label_Ability);
        label_Ability.setBounds(290, 280, 210, 30);

        label_Name.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        label_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Name.setText("Teddycopter");
        label_Name.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_Background.add(label_Name);
        label_Name.setBounds(290, 220, 210, 50);

        button_NextCharacter.setBackground(new java.awt.Color(0, 74, 173));
        button_NextCharacter.setForeground(new java.awt.Color(255, 255, 255));
        button_NextCharacter.setText("Next Character");
        button_NextCharacter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_NextCharacterMouseEntered(evt);
            }
        });
        button_NextCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_NextCharacterActionPerformed(evt);
            }
        });
        panel_Background.add(button_NextCharacter);
        button_NextCharacter.setBounds(290, 320, 220, 50);

        button_Start.setBackground(new java.awt.Color(0, 74, 173));
        button_Start.setForeground(new java.awt.Color(255, 255, 255));
        button_Start.setText("Start");
        button_Start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_StartMouseEntered(evt);
            }
        });
        button_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_StartActionPerformed(evt);
            }
        });
        panel_Background.add(button_Start);
        button_Start.setBounds(290, 390, 220, 50);

        button_Return.setBackground(new java.awt.Color(0, 74, 173));
        button_Return.setForeground(new java.awt.Color(255, 255, 255));
        button_Return.setText("Return");
        button_Return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_ReturnMouseEntered(evt);
            }
        });
        button_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReturnActionPerformed(evt);
            }
        });
        panel_Background.add(button_Return);
        button_Return.setBounds(290, 460, 220, 50);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_NextCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_NextCharacterActionPerformed
        selectedCharacter = selectedCharacter.nextCharacter();
        setSelectedCharacter(selectedCharacter);
        SoundPlayer.playSound(toy.getJumpSoundFile());
    }//GEN-LAST:event_button_NextCharacterActionPerformed

    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed
        timer.stop();
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }//GEN-LAST:event_button_ReturnActionPerformed

    private void button_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_StartActionPerformed
        timer.stop();
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Game(toy).setVisible(true));
    }//GEN-LAST:event_button_StartActionPerformed

    private void button_NextCharacterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_NextCharacterMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_NextCharacterMouseEntered

    private void button_StartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_StartMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_StartMouseEntered

    private void button_ReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ReturnMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ReturnMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar_Falling;
    private javax.swing.JProgressBar bar_Jumping;
    private javax.swing.JProgressBar bar_Moving;
    private javax.swing.JButton button_NextCharacter;
    private javax.swing.JButton button_Return;
    private javax.swing.JButton button_Start;
    private javax.swing.JLabel label_Ability;
    private javax.swing.JLabel label_Falling;
    private javax.swing.JLabel label_Jumping;
    private javax.swing.JLabel label_Moving;
    private javax.swing.JLabel label_Name;
    private javax.swing.JLabel label_Objectives;
    private javax.swing.JLabel label_Tutorial;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
