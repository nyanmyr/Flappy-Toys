package game;

import database.DatabaseConnection;
import static game.Main.SCREEN_HEIGHT;
import static game.Main.SCREEN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import toys.ToyCharacter;
import utility.sprites.StaticSprite;

public class SaveRecord extends javax.swing.JFrame {

    public static boolean enabled = false;
    
    private static int savedScore;
    private static int savedTokensCollected;
    private static int savedChargesUsed;
    private static ToyCharacter savedToyCharacter;
    
    private StaticSprite background;

    public SaveRecord(int savedScore, int tokensCollected, int chargesUsed, ToyCharacter toyCharacter) {
        enabled = true;
        SaveRecord.savedScore = savedScore;
        SaveRecord.savedTokensCollected = tokensCollected;
        SaveRecord.savedChargesUsed = chargesUsed;
        SaveRecord.savedToyCharacter = toyCharacter;
        initComponents();

        LoadSprite();
        panel_Background.add(background);
    }

    private void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/backgrounds/bricks_bg.jpg");
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
                throw new RuntimeException("Image resource not found: bricks_bg.jpg");
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
        button_Accept = new javax.swing.JButton();
        button_Decline = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Title.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("SAVE TO RECORD?");
        panel_Background.add(label_Title);
        label_Title.setBounds(10, 10, 380, 70);

        button_Accept.setBackground(new java.awt.Color(0, 74, 173));
        button_Accept.setForeground(new java.awt.Color(255, 255, 255));
        button_Accept.setText("Accept");
        button_Accept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_AcceptMouseEntered(evt);
            }
        });
        button_Accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AcceptActionPerformed(evt);
            }
        });
        panel_Background.add(button_Accept);
        button_Accept.setBounds(80, 130, 220, 50);

        button_Decline.setBackground(new java.awt.Color(0, 74, 173));
        button_Decline.setForeground(new java.awt.Color(255, 255, 255));
        button_Decline.setText("Decline");
        button_Decline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_DeclineMouseEntered(evt);
            }
        });
        button_Decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DeclineActionPerformed(evt);
            }
        });
        panel_Background.add(button_Decline);
        button_Decline.setBounds(80, 190, 220, 50);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 400, 300);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_DeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DeclineActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        enabled = false;
        dispose();
    }//GEN-LAST:event_button_DeclineActionPerformed

    private void button_DeclineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_DeclineMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_DeclineMouseEntered

    private void button_AcceptMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_AcceptMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_AcceptMouseEntered

    private void button_AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AcceptActionPerformed
        enabled = false;

        if (button_Accept.getText().equals("Login")) {
            SoundPlayer.playSound(SoundFile.CLICK);
            dispose();
            // no login prompt
            return;
        }

        if (Account.logedIn) {
            SoundPlayer.playSound(SoundFile.CLICK);
            DatabaseConnection.saveRecord(savedScore, savedTokensCollected, savedChargesUsed, savedToyCharacter);
            dispose();
        } else {
            label_Title.setText("Account not logged in");
            button_Accept.setText("Login");
            SoundPlayer.playSound(SoundFile.INCORRECT);
        }
    }//GEN-LAST:event_button_AcceptActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Accept;
    private javax.swing.JButton button_Decline;
    private javax.swing.JLabel label_Title;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
