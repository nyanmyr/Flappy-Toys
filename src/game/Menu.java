package game;

import static game.Game.MILISECOND_DELAY;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import utility.sprites.DynamicSprite;
import utility.sprites.SpriteUtils;
import utility.sprites.StaticSprite;

public class Menu extends javax.swing.JFrame {

    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    private StaticSprite background;

    private DynamicSprite accountIcon;
    private DynamicSprite optionsIcon;

    public Menu(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        this.WINDOW_HEIGHT = SCREEN_HEIGHT;
        this.WINDOW_WIDTH = SCREEN_WIDTH;

        initComponents();

        loadBackgroundSprite();
        loadOptionSprite();
        LoadAccountSprite();
        panel_Background.add(optionsIcon);
        panel_Background.add(accountIcon);
        panel_Background.add(background);

        ActionListener update = (ActionEvent evt) -> {
            accountIcon.update();
            optionsIcon.update();
        };

        Timer timer = new Timer(MILISECOND_DELAY, update);
        timer.start();
    }

    private void loadBackgroundSprite() {
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

            java.net.URL accountResource = getClass().getResource("/resources/animations/icons/account_anim.png");
            if (accountResource == null) {
                throw new RuntimeException("Image resource not found: /account_anim.png");
            }

            // Read and convert to BufferedImage
            Image img = ImageIO.read(accountResource);
            java.awt.image.BufferedImage buffered
                    = new java.awt.image.BufferedImage(
                            img.getWidth(null),
                            img.getHeight(null),
                            java.awt.image.BufferedImage.TYPE_INT_ARGB
                    );
            Graphics2D accountG2D = buffered.createGraphics();
            accountG2D.drawImage(img, 0, 0, null);
            accountG2D.dispose();

            // Split horizontally into frames
            BufferedImage[] accountFrames = SpriteUtils.sliceHorizontalFrames(buffered, 12);

            // Use DynamicSprite for animation
            accountIcon = new DynamicSprite(accountFrames);
            accountIcon.setBounds(0, 0, accountFrames[0].getWidth(), accountFrames[0].getHeight());
            accountIcon.setSize(60, 60);
            accountIcon.setLocation(590, 80);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    private void loadOptionSprite() {
        try {
            java.net.URL accountResource = getClass().getResource("/resources/animations/icons/account_anim.png");
            if (accountResource == null) {
                throw new RuntimeException("Image resource not found: /account_anim.png");
            }

            // Read and convert to BufferedImage
            Image img = ImageIO.read(accountResource);
            java.awt.image.BufferedImage buffered
                    = new java.awt.image.BufferedImage(
                            img.getWidth(null),
                            img.getHeight(null),
                            java.awt.image.BufferedImage.TYPE_INT_ARGB
                    );
            Graphics2D accountG2D = buffered.createGraphics();
            accountG2D.drawImage(img, 0, 0, null);
            accountG2D.dispose();

            // Split horizontally into frames
            BufferedImage[] accountFrames = SpriteUtils.sliceHorizontalFrames(buffered, 12);

            // Use DynamicSprite for animation
            accountIcon = new DynamicSprite(accountFrames);
            accountIcon.setBounds(0, 0, accountFrames[0].getWidth(), accountFrames[0].getHeight());
            accountIcon.setSize(60, 60);
            accountIcon.setLocation(690, 80);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    private void LoadAccountSprite() {
        try {
            java.net.URL settingsResource = getClass().getResource("/resources/animations/icons/options_anim.png");
            if (settingsResource == null) {
                throw new RuntimeException("Image resource not found: /options_anim.png");
            }

            // Read and convert to BufferedImage
            Image optionsIMG = ImageIO.read(settingsResource);
            java.awt.image.BufferedImage optionsBuffered
                    = new java.awt.image.BufferedImage(
                            optionsIMG.getWidth(null),
                            optionsIMG.getHeight(null),
                            java.awt.image.BufferedImage.TYPE_INT_ARGB
                    );
            Graphics2D optionsG2D = optionsBuffered.createGraphics();
            optionsG2D.drawImage(optionsIMG, 0, 0, null);
            optionsG2D.dispose();

            // Split horizontally into frames
            BufferedImage[] optionsFrames = SpriteUtils.sliceHorizontalFrames(optionsBuffered, 12);

            // Use DynamicSprite for animation
            optionsIcon = new DynamicSprite(optionsFrames);
            optionsIcon.setBounds(0, 0, optionsFrames[0].getWidth(), optionsFrames[0].getHeight());
            optionsIcon.setSize(60, 60);
            optionsIcon.setLocation(590, 80);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Title = new javax.swing.JLabel();
        button_Play = new javax.swing.JButton();
        button_Leaderboards = new javax.swing.JButton();
        button_Credits = new javax.swing.JButton();
        button_Exit = new javax.swing.JButton();
        button_Account = new javax.swing.JButton();
        button_Settings = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(WINDOW_HEIGHT, WINDOW_WIDTH));
        panel_Background.setMinimumSize(new java.awt.Dimension(WINDOW_HEIGHT, WINDOW_WIDTH));
        panel_Background.setLayout(null);

        label_Title.setFont(new java.awt.Font("Comic Sans MS", 0, 64)); // NOI18N
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("FLAPPY TOYS");
        panel_Background.add(label_Title);
        label_Title.setBounds(50, 50, 460, 110);

        button_Play.setBackground(new java.awt.Color(0, 74, 173));
        button_Play.setForeground(new java.awt.Color(255, 255, 255));
        button_Play.setText("Play");
        button_Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_PlayActionPerformed(evt);
            }
        });
        panel_Background.add(button_Play);
        button_Play.setBounds(50, 200, 210, 50);

        button_Leaderboards.setBackground(new java.awt.Color(0, 74, 173));
        button_Leaderboards.setForeground(new java.awt.Color(255, 255, 255));
        button_Leaderboards.setText("Leaderboards");
        button_Leaderboards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_LeaderboardsActionPerformed(evt);
            }
        });
        panel_Background.add(button_Leaderboards);
        button_Leaderboards.setBounds(50, 270, 210, 50);

        button_Credits.setBackground(new java.awt.Color(0, 74, 173));
        button_Credits.setForeground(new java.awt.Color(255, 255, 255));
        button_Credits.setText("Credits");
        button_Credits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CreditsActionPerformed(evt);
            }
        });
        panel_Background.add(button_Credits);
        button_Credits.setBounds(50, 340, 210, 50);

        button_Exit.setBackground(new java.awt.Color(0, 74, 173));
        button_Exit.setForeground(new java.awt.Color(255, 255, 255));
        button_Exit.setText("Exit");
        button_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ExitActionPerformed(evt);
            }
        });
        panel_Background.add(button_Exit);
        button_Exit.setBounds(50, 410, 210, 50);

        button_Account.setBackground(new java.awt.Color(0, 74, 173));
        button_Account.setForeground(new java.awt.Color(255, 255, 255));
        button_Account.setBorderPainted(false);
        button_Account.setContentAreaFilled(false);
        button_Account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AccountActionPerformed(evt);
            }
        });
        panel_Background.add(button_Account);
        button_Account.setBounds(680, 80, 60, 60);

        button_Settings.setBackground(new java.awt.Color(255, 255, 255, 0));
        button_Settings.setForeground(new java.awt.Color(255, 255, 255));
        button_Settings.setBorderPainted(false);
        button_Settings.setContentAreaFilled(false);
        button_Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SettingsActionPerformed(evt);
            }
        });
        panel_Background.add(button_Settings);
        button_Settings.setBounds(590, 80, 60, 60);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_button_ExitActionPerformed

    private void button_LeaderboardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_LeaderboardsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_LeaderboardsActionPerformed

    private void button_CreditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CreditsActionPerformed
        dispose();
        new Credits(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true);
    }//GEN-LAST:event_button_CreditsActionPerformed

    private void button_PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_PlayActionPerformed
        dispose();
        new CharacterSelection(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true);
    }//GEN-LAST:event_button_PlayActionPerformed

    private void button_SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SettingsActionPerformed
        dispose();
        new Options(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true);
    }//GEN-LAST:event_button_SettingsActionPerformed

    private void button_AccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AccountActionPerformed
        dispose();
        new Login(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true);
    }//GEN-LAST:event_button_AccountActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Account;
    private javax.swing.JButton button_Credits;
    private javax.swing.JButton button_Exit;
    private javax.swing.JButton button_Leaderboards;
    private javax.swing.JButton button_Play;
    private javax.swing.JButton button_Settings;
    private javax.swing.JLabel label_Title;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
