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
import utility.sprites.StaticSprite;

public class Login extends javax.swing.JFrame {

    private StaticSprite background;

    public Login() {
        initComponents();

        LoadSprite();
        panel_Background.add(background);
        label_Warning.setVisible(false);
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
        label_Username = new javax.swing.JLabel();
        textField_Username = new javax.swing.JTextField();
        label_Password = new javax.swing.JLabel();
        passwordField_Password = new javax.swing.JPasswordField();
        button_Login = new javax.swing.JButton();
        button_CreateAccount = new javax.swing.JButton();
        button_Return = new javax.swing.JButton();
        label_Warning = new javax.swing.JLabel();

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
        label_Title.setText("LOGIN");
        panel_Background.add(label_Title);
        label_Title.setBounds(50, 50, 290, 80);

        label_Username.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Username.setText("USERNAME");
        panel_Background.add(label_Username);
        label_Username.setBounds(270, 170, 260, 30);

        textField_Username.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        panel_Background.add(textField_Username);
        textField_Username.setBounds(270, 210, 260, 30);

        label_Password.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Password.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Password.setText("PASSWORD");
        panel_Background.add(label_Password);
        label_Password.setBounds(270, 250, 260, 30);

        passwordField_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField_PasswordActionPerformed(evt);
            }
        });
        panel_Background.add(passwordField_Password);
        passwordField_Password.setBounds(270, 290, 260, 30);

        button_Login.setBackground(new java.awt.Color(0, 74, 173));
        button_Login.setForeground(new java.awt.Color(255, 255, 255));
        button_Login.setText("Login");
        button_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_LoginMouseEntered(evt);
            }
        });
        button_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_LoginActionPerformed(evt);
            }
        });
        panel_Background.add(button_Login);
        button_Login.setBounds(290, 380, 220, 50);

        button_CreateAccount.setBackground(new java.awt.Color(0, 74, 173));
        button_CreateAccount.setForeground(new java.awt.Color(255, 255, 255));
        button_CreateAccount.setText("Create Account");
        button_CreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_CreateAccountMouseEntered(evt);
            }
        });
        button_CreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CreateAccountActionPerformed(evt);
            }
        });
        panel_Background.add(button_CreateAccount);
        button_CreateAccount.setBounds(290, 440, 220, 30);

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
        button_Return.setBounds(290, 480, 220, 30);

        label_Warning.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        label_Warning.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Warning.setText("Warning");
        label_Warning.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Background.add(label_Warning);
        label_Warning.setBounds(90, 140, 160, 110);

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

    private void button_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_LoginActionPerformed
        // implement login system here
        SoundPlayer.playSound(SoundFile.CLICK);

        String typedUsername = textField_Username.getText();
        String typedPassword = new String(passwordField_Password.getPassword());

        label_Warning.setVisible(true);

        // add message
        if (typedUsername.isEmpty()) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Enter a username.");
            return;
        } else if (typedPassword.isEmpty()) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Enter a password.");
            return;
        }

        if (DatabaseConnection.authenticate(typedUsername, typedPassword)) {
            System.out.println("Username: " + typedUsername);
            System.out.println("Password: " + typedPassword);

            dispose();
            java.awt.EventQueue.invokeLater(() -> new Account(typedUsername, DatabaseConnection.getPfp(typedUsername)).setVisible(true));
        } else {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Incorrect username or password.");
        }
    }//GEN-LAST:event_button_LoginActionPerformed

    private void button_CreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CreateAccountActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Register().setVisible(true));
    }//GEN-LAST:event_button_CreateAccountActionPerformed

    private void passwordField_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField_PasswordActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_passwordField_PasswordActionPerformed

    private void button_LoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_LoginMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_LoginMouseEntered

    private void button_CreateAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_CreateAccountMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_CreateAccountMouseEntered

    private void button_ReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ReturnMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ReturnMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_CreateAccount;
    private javax.swing.JButton button_Login;
    private javax.swing.JButton button_Return;
    private javax.swing.JLabel label_Password;
    private javax.swing.JLabel label_Title;
    private javax.swing.JLabel label_Username;
    private javax.swing.JLabel label_Warning;
    private javax.swing.JPanel panel_Background;
    private javax.swing.JPasswordField passwordField_Password;
    private javax.swing.JTextField textField_Username;
    // End of variables declaration//GEN-END:variables
}
