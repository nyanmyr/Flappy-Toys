package game;

import static game.Main.SCREEN_HEIGHT;
import static game.Main.SCREEN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import sfx.music.MusicPlayer;
import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import utility.sprites.StaticSprite;

public class Options extends javax.swing.JFrame {

    private StaticSprite background;

    private boolean soundEffectsOptions;
    private boolean musicOptions;
    private boolean fullscreenOptions;
    private boolean tutorialOptions;

    public static boolean soundEffects = true;
    public static boolean music = true;
    public static boolean fullscreen = true;
    public static boolean tutorial = true;

    public Options() {
        initComponents();

        retrieveSettings();
        updateSettings();

        hideSaveDiscard();

        LoadSprite();
        panel_Background.add(background);
    }

    private void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/backgrounds/icecream_bg.jpg");
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
                throw new RuntimeException("Image resource not found: icecream_bg.jpg");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    private void retrieveSettings() {
        soundEffectsOptions = soundEffects;
        musicOptions = music;
        fullscreenOptions = fullscreen;
        tutorialOptions = tutorial;
    }

    private void updateSettings() {
        button_SoundEffects.setText("SOUND EFFECTS: " + (soundEffectsOptions ? "ON" : "OFF"));
        button_Music.setText("MUSIC: " + (musicOptions ? "ON" : "OFF"));
        button_Fullscreen.setText("FULLSCREEN: " + (fullscreenOptions ? "ON" : "OFF"));
        button_Tutorial.setText("TUTORIAL: " + (tutorialOptions ? "ON" : "OFF"));
    }

    // could fuse these two together
    private void showSaveDiscard() {
        button_Save.setVisible(true);
        button_Discard.setVisible(true);
    }

    private void hideSaveDiscard() {
        button_Save.setVisible(false);
        button_Discard.setVisible(false);
    }

    private void saveSettings() {
        soundEffects = soundEffectsOptions;
        music = musicOptions;
        fullscreen = fullscreenOptions;
        tutorial = tutorialOptions;
        
        if (!music) {
            MusicPlayer.stopCurrentClip();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Title = new javax.swing.JLabel();
        button_SoundEffects = new javax.swing.JButton();
        button_Music = new javax.swing.JButton();
        button_Fullscreen = new javax.swing.JButton();
        button_Tutorial = new javax.swing.JButton();
        button_Discard = new javax.swing.JButton();
        button_Save = new javax.swing.JButton();
        button_Return = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Title.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("OPTIONS");
        panel_Background.add(label_Title);
        label_Title.setBounds(50, 50, 290, 80);

        button_SoundEffects.setBackground(new java.awt.Color(0, 74, 173));
        button_SoundEffects.setForeground(new java.awt.Color(255, 255, 255));
        button_SoundEffects.setText("SOUND EFFECTS: ON");
        button_SoundEffects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_SoundEffectsMouseEntered(evt);
            }
        });
        button_SoundEffects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SoundEffectsActionPerformed(evt);
            }
        });
        panel_Background.add(button_SoundEffects);
        button_SoundEffects.setBounds(150, 200, 220, 50);

        button_Music.setBackground(new java.awt.Color(0, 74, 173));
        button_Music.setForeground(new java.awt.Color(255, 255, 255));
        button_Music.setText("MUSIC: ON");
        button_Music.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_MusicMouseEntered(evt);
            }
        });
        button_Music.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MusicActionPerformed(evt);
            }
        });
        panel_Background.add(button_Music);
        button_Music.setBounds(430, 200, 220, 50);

        button_Fullscreen.setBackground(new java.awt.Color(0, 74, 173));
        button_Fullscreen.setForeground(new java.awt.Color(255, 255, 255));
        button_Fullscreen.setText("FULLSCREEN");
        button_Fullscreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_FullscreenMouseEntered(evt);
            }
        });
        button_Fullscreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_FullscreenActionPerformed(evt);
            }
        });
        panel_Background.add(button_Fullscreen);
        button_Fullscreen.setBounds(150, 300, 220, 50);

        button_Tutorial.setBackground(new java.awt.Color(0, 74, 173));
        button_Tutorial.setForeground(new java.awt.Color(255, 255, 255));
        button_Tutorial.setText("TUTORIAL: ON");
        button_Tutorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_TutorialMouseEntered(evt);
            }
        });
        button_Tutorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TutorialActionPerformed(evt);
            }
        });
        panel_Background.add(button_Tutorial);
        button_Tutorial.setBounds(430, 300, 220, 50);

        button_Discard.setBackground(new java.awt.Color(0, 74, 173));
        button_Discard.setForeground(new java.awt.Color(255, 255, 255));
        button_Discard.setText("Discard Changes");
        button_Discard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_DiscardMouseEntered(evt);
            }
        });
        button_Discard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DiscardActionPerformed(evt);
            }
        });
        panel_Background.add(button_Discard);
        button_Discard.setBounds(430, 390, 220, 50);

        button_Save.setBackground(new java.awt.Color(0, 74, 173));
        button_Save.setForeground(new java.awt.Color(255, 255, 255));
        button_Save.setText("Save Changes");
        button_Save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_SaveMouseEntered(evt);
            }
        });
        button_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SaveActionPerformed(evt);
            }
        });
        panel_Background.add(button_Save);
        button_Save.setBounds(150, 390, 220, 50);

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

    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }//GEN-LAST:event_button_ReturnActionPerformed

    private void button_MusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_MusicActionPerformed
        musicOptions = !musicOptions;
        button_Music.setText("MUSIC: " + (musicOptions ? "ON" : "OFF"));
        SoundPlayer.playSound(SoundFile.CLICK);
        showSaveDiscard();
    }//GEN-LAST:event_button_MusicActionPerformed

    private void button_TutorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TutorialActionPerformed
        tutorialOptions = !tutorialOptions;
        button_Tutorial.setText("TUTORIAL: " + (tutorialOptions ? "ON" : "OFF"));
        SoundPlayer.playSound(SoundFile.CLICK);
        showSaveDiscard();
    }//GEN-LAST:event_button_TutorialActionPerformed

    private void button_SoundEffectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SoundEffectsActionPerformed
        soundEffectsOptions = !soundEffectsOptions;
        button_SoundEffects.setText("SOUND EFFECTS: " + (soundEffectsOptions ? "ON" : "OFF"));
        SoundPlayer.playSound(SoundFile.CLICK);
        showSaveDiscard();
    }//GEN-LAST:event_button_SoundEffectsActionPerformed

    private void button_FullscreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_FullscreenActionPerformed
        fullscreenOptions = !fullscreenOptions;
        button_Fullscreen.setText("FULLSCREEN: " + (fullscreenOptions ? "ON" : "OFF"));
        SoundPlayer.playSound(SoundFile.CLICK);
        showSaveDiscard();
    }//GEN-LAST:event_button_FullscreenActionPerformed

    private void button_ReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ReturnMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ReturnMouseEntered

    private void button_TutorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_TutorialMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_TutorialMouseEntered

    private void button_FullscreenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_FullscreenMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_FullscreenMouseEntered

    private void button_MusicMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_MusicMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_MusicMouseEntered

    private void button_SoundEffectsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_SoundEffectsMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_SoundEffectsMouseEntered

    private void button_SaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_SaveMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_SaveMouseEntered

    private void button_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SaveActionPerformed
        hideSaveDiscard();

        saveSettings();

        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_button_SaveActionPerformed

    private void button_DiscardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_DiscardMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_DiscardMouseEntered

    private void button_DiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DiscardActionPerformed
        hideSaveDiscard();

        retrieveSettings();
        updateSettings();

        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_button_DiscardActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Discard;
    private javax.swing.JButton button_Fullscreen;
    private javax.swing.JButton button_Music;
    private javax.swing.JButton button_Return;
    private javax.swing.JButton button_Save;
    private javax.swing.JButton button_SoundEffects;
    private javax.swing.JButton button_Tutorial;
    private javax.swing.JLabel label_Title;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables

}
