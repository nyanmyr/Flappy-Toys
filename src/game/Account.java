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

public class Account extends javax.swing.JFrame {

    private StaticSprite background;

    public Account() {
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
        label_Username = new javax.swing.JLabel();
        label_TextHighestScore = new javax.swing.JLabel();
        label_TextTotalScore = new javax.swing.JLabel();
        label_TextHighestTokensCollected = new javax.swing.JLabel();
        label_TextTotalTokens = new javax.swing.JLabel();
        label_TextChargesUsed = new javax.swing.JLabel();
        label_TextMostUsedCharacter = new javax.swing.JLabel();
        label_HighestScore = new javax.swing.JLabel();
        label_TotalScore = new javax.swing.JLabel();
        label_HighestTokensCollected = new javax.swing.JLabel();
        label_TotalTokens = new javax.swing.JLabel();
        label_ChargesUsed = new javax.swing.JLabel();
        label_MostUsedCharacter = new javax.swing.JLabel();
        button_Return = new javax.swing.JButton();
        button_Logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Username.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Username.setText("USERNAME");
        panel_Background.add(label_Username);
        label_Username.setBounds(50, 50, 290, 80);

        label_TextHighestScore.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TextHighestScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TextHighestScore.setText("Highest Score:");
        panel_Background.add(label_TextHighestScore);
        label_TextHighestScore.setBounds(120, 150, 270, 40);

        label_TextTotalScore.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TextTotalScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TextTotalScore.setText("Total Score: ");
        panel_Background.add(label_TextTotalScore);
        label_TextTotalScore.setBounds(120, 200, 270, 40);

        label_TextHighestTokensCollected.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TextHighestTokensCollected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TextHighestTokensCollected.setText("Highest Tokens Collected: ");
        panel_Background.add(label_TextHighestTokensCollected);
        label_TextHighestTokensCollected.setBounds(120, 250, 270, 40);

        label_TextTotalTokens.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TextTotalTokens.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TextTotalTokens.setText("Total Tokens: ");
        panel_Background.add(label_TextTotalTokens);
        label_TextTotalTokens.setBounds(120, 300, 270, 40);

        label_TextChargesUsed.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TextChargesUsed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TextChargesUsed.setText("Total Charges Used:");
        panel_Background.add(label_TextChargesUsed);
        label_TextChargesUsed.setBounds(120, 350, 270, 40);

        label_TextMostUsedCharacter.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TextMostUsedCharacter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TextMostUsedCharacter.setText("Most Used Character:");
        panel_Background.add(label_TextMostUsedCharacter);
        label_TextMostUsedCharacter.setBounds(120, 400, 270, 40);

        label_HighestScore.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_HighestScore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_HighestScore.setText("0");
        panel_Background.add(label_HighestScore);
        label_HighestScore.setBounds(400, 150, 270, 40);

        label_TotalScore.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TotalScore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_TotalScore.setText("0");
        panel_Background.add(label_TotalScore);
        label_TotalScore.setBounds(400, 200, 270, 40);

        label_HighestTokensCollected.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_HighestTokensCollected.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_HighestTokensCollected.setText("0");
        panel_Background.add(label_HighestTokensCollected);
        label_HighestTokensCollected.setBounds(400, 250, 270, 40);

        label_TotalTokens.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_TotalTokens.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_TotalTokens.setText("0");
        panel_Background.add(label_TotalTokens);
        label_TotalTokens.setBounds(400, 300, 270, 40);

        label_ChargesUsed.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_ChargesUsed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_ChargesUsed.setText("0");
        panel_Background.add(label_ChargesUsed);
        label_ChargesUsed.setBounds(400, 350, 270, 40);

        label_MostUsedCharacter.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_MostUsedCharacter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_MostUsedCharacter.setText("0");
        panel_Background.add(label_MostUsedCharacter);
        label_MostUsedCharacter.setBounds(400, 400, 270, 40);

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
        button_Return.setBounds(170, 460, 220, 50);

        button_Logout.setBackground(new java.awt.Color(0, 74, 173));
        button_Logout.setForeground(new java.awt.Color(255, 255, 255));
        button_Logout.setText("Logout");
        button_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_LogoutMouseEntered(evt);
            }
        });
        button_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_LogoutActionPerformed(evt);
            }
        });
        panel_Background.add(button_Logout);
        button_Logout.setBounds(400, 460, 220, 50);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_LogoutActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_button_LogoutActionPerformed

    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }//GEN-LAST:event_button_ReturnActionPerformed

    private void button_ReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ReturnMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ReturnMouseEntered

    private void button_LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_LogoutMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_LogoutMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Logout;
    private javax.swing.JButton button_Return;
    private javax.swing.JLabel label_ChargesUsed;
    private javax.swing.JLabel label_HighestScore;
    private javax.swing.JLabel label_HighestTokensCollected;
    private javax.swing.JLabel label_MostUsedCharacter;
    private javax.swing.JLabel label_TextChargesUsed;
    private javax.swing.JLabel label_TextHighestScore;
    private javax.swing.JLabel label_TextHighestTokensCollected;
    private javax.swing.JLabel label_TextMostUsedCharacter;
    private javax.swing.JLabel label_TextTotalScore;
    private javax.swing.JLabel label_TextTotalTokens;
    private javax.swing.JLabel label_TotalScore;
    private javax.swing.JLabel label_TotalTokens;
    private javax.swing.JLabel label_Username;
    private javax.swing.JPanel panel_Background;
    // End of variables declaration//GEN-END:variables
}
