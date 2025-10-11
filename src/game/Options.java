package game;

import static game.Main.SCREEN_HEIGHT;
import static game.Main.SCREEN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import utility.sprites.StaticSprite;

public class Options extends javax.swing.JFrame {

    private StaticSprite background;

    public Options() {
        initComponents();

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Title = new javax.swing.JLabel();
        button_SoundEffects = new javax.swing.JButton();
        button_Music = new javax.swing.JButton();
        button_Language = new javax.swing.JButton();
        button_Tutorial = new javax.swing.JButton();
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

        button_Language.setBackground(new java.awt.Color(0, 74, 173));
        button_Language.setForeground(new java.awt.Color(255, 255, 255));
        button_Language.setText("LANGUAGE: ENGLISH");
        button_Language.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_LanguageMouseEntered(evt);
            }
        });
        button_Language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_LanguageActionPerformed(evt);
            }
        });
        panel_Background.add(button_Language);
        button_Language.setBounds(150, 340, 220, 50);

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
        button_Tutorial.setBounds(430, 340, 220, 50);

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
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_button_MusicActionPerformed

    private void button_TutorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TutorialActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_button_TutorialActionPerformed

    private void button_SoundEffectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SoundEffectsActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_button_SoundEffectsActionPerformed

    private void button_LanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_LanguageActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_button_LanguageActionPerformed

    private void button_ReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ReturnMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ReturnMouseEntered

    private void button_TutorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_TutorialMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_TutorialMouseEntered

    private void button_LanguageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_LanguageMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_LanguageMouseEntered

    private void button_MusicMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_MusicMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_MusicMouseEntered

    private void button_SoundEffectsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_SoundEffectsMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_SoundEffectsMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Language;
    private javax.swing.JButton button_Music;
    private javax.swing.JButton button_Return;
    private javax.swing.JButton button_SoundEffects;
    private javax.swing.JButton button_Tutorial;
    private javax.swing.JLabel label_Title;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
