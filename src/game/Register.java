package game;

import database.DatabaseConnection;
import static game.Main.SCREEN_HEIGHT;
import static game.Main.SCREEN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import utility.sprites.StaticSprite;

public class Register extends javax.swing.JFrame {

    private StaticSprite background;
    private StaticSprite pfp;
    static private int pfpSelected = 1;

    public Register() {
        initComponents();

        loadBackground();
        loadPfp(pfpSelected);

        panel_Background.add(background);
        label_Warning.setVisible(false);
    }

    private void loadBackground() {
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

    private void loadPfp(int pfpNum) {
        if (pfp != null) {
            panel_Background.remove(pfp);
            panel_Background.setComponentZOrder(background, panel_Background.getComponentCount() - 1);
        }

        try {

            java.net.URL resource;

            // load pfp
            switch (pfpNum) {
                case 1 -> {
                    resource = getClass().getResource("/resources/pfp/teddycopter_pfp.png");
                }
                case 2 -> {
                    resource = getClass().getResource("/resources/pfp/rocketron_pfp.png");
                }
                case 3 -> {
                    resource = getClass().getResource("/resources/pfp/foldy_pfp.png");
                }
                case 4 -> {
                    resource = getClass().getResource("/resources/pfp/rocketron_pfp.png");
//                    resource = getClass().getResource("/resources/pfp/dancerina_pfp.png");
                }
                case 5 -> {
                    resource = getClass().getResource("/resources/pfp/rocketron_pfp.png");
//                    resource = getClass().getResource("/resources/pfp/sergeant_pfp.png");
                }
                case 6 -> {
                    resource = getClass().getResource("/resources/pfp/rocketron_pfp.png");
//                    resource = getClass().getResource("/resources/pfp/allicorn_pfp.png");
                }
                default -> {
                    throw new AssertionError(pfpNum);
                }
            }

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

                pfp = new StaticSprite(buffered);

                pfp.setBounds(360, 50, 80, 80);
            } else {
                throw new RuntimeException("Pfp resource not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }

        panel_Background.add(pfp);
        panel_Background.setComponentZOrder(pfp, 0);
        panel_Background.revalidate();
        panel_Background.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Title = new javax.swing.JLabel();
        label_Pfp = new javax.swing.JLabel();
        label_Warning = new javax.swing.JLabel();
        label_Username = new javax.swing.JLabel();
        passwordField_ConfirmedPassword = new javax.swing.JPasswordField();
        label_Password1 = new javax.swing.JLabel();
        textField_Username = new javax.swing.JTextField();
        label_Password = new javax.swing.JLabel();
        passwordField_Password = new javax.swing.JPasswordField();
        button_CreateAccount = new javax.swing.JButton();
        button_Login = new javax.swing.JButton();
        button_Return = new javax.swing.JButton();
        button_Pfp = new javax.swing.JButton();

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
        label_Title.setText("Register");
        panel_Background.add(label_Title);
        label_Title.setBounds(50, 50, 290, 80);

        label_Pfp.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        label_Pfp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Pfp.setText("CHANGE PROFILE PICTURE");
        panel_Background.add(label_Pfp);
        label_Pfp.setBounds(270, 20, 260, 30);

        label_Warning.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        label_Warning.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Warning.setText("Warning");
        label_Warning.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_Background.add(label_Warning);
        label_Warning.setBounds(90, 140, 160, 110);

        label_Username.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Username.setText("USERNAME");
        panel_Background.add(label_Username);
        label_Username.setBounds(270, 140, 260, 30);

        passwordField_ConfirmedPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField_ConfirmedPasswordActionPerformed(evt);
            }
        });
        panel_Background.add(passwordField_ConfirmedPassword);
        passwordField_ConfirmedPassword.setBounds(270, 340, 260, 30);

        label_Password1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Password1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Password1.setText("CONFIRM PASSWORD");
        panel_Background.add(label_Password1);
        label_Password1.setBounds(270, 300, 260, 30);

        textField_Username.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        panel_Background.add(textField_Username);
        textField_Username.setBounds(270, 180, 260, 30);

        label_Password.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Password.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Password.setText("PASSWORD");
        panel_Background.add(label_Password);
        label_Password.setBounds(270, 220, 260, 30);

        passwordField_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField_PasswordActionPerformed(evt);
            }
        });
        panel_Background.add(passwordField_Password);
        passwordField_Password.setBounds(270, 260, 260, 30);

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
        button_CreateAccount.setBounds(290, 380, 220, 50);

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
        button_Login.setBounds(290, 440, 220, 30);

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

        button_Pfp.setBackground(new java.awt.Color(255, 255, 255, 0));
        button_Pfp.setForeground(new java.awt.Color(255, 255, 255));
        button_Pfp.setBorderPainted(false);
        button_Pfp.setContentAreaFilled(false);
        button_Pfp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_PfpMouseEntered(evt);
            }
        });
        button_Pfp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_PfpActionPerformed(evt);
            }
        });
        panel_Background.add(button_Pfp);
        button_Pfp.setBounds(360, 60, 80, 80);

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

    private void button_CreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CreateAccountActionPerformed

        String typedUsername = textField_Username.getText();
        String typedPassword = new String(passwordField_Password.getPassword());
        String typedConfirmedPassword = new String(passwordField_ConfirmedPassword.getPassword());

        label_Warning.setVisible(true);

        // add message
        if (typedUsername.isEmpty()) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Enter a username.");
            return;
        } else if (typedUsername.length() < 5) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Choose a longer username (5 characters long or more).");
            return;
        } else if (typedPassword.isEmpty()) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Enter a password.");
            return;
        } else if (typedPassword.length() < 8) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Choose a stronger password (8 characters long or more).");
            return;
        } else if (typedConfirmedPassword.isEmpty()) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Enter confirm password.");
            return;
        } else if (!typedPassword.equals(typedConfirmedPassword)) {
            SoundPlayer.playSound(SoundFile.INCORRECT);
            label_Warning.setText("<html>Passwords does not match.");
            return;
        }

        try {
            label_Warning.setVisible(false);
            SoundPlayer.playSound(SoundFile.CLICK);

            textField_Username.setText("");
            passwordField_Password.setText("");
            passwordField_ConfirmedPassword.setText("");

            if (DatabaseConnection.addAccount(typedUsername, typedPassword, pfpSelected)) {
                dispose();
                java.awt.EventQueue.invokeLater(() -> new Account(typedUsername, pfpSelected).setVisible(true));
            }
        } catch (SQLException e) {
            System.out.println("Database Error");
        }

        SoundPlayer.playSound(SoundFile.CLICK);

    }//GEN-LAST:event_button_CreateAccountActionPerformed

    private void button_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_LoginActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }//GEN-LAST:event_button_LoginActionPerformed

    private void passwordField_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField_PasswordActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_passwordField_PasswordActionPerformed

    private void passwordField_ConfirmedPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField_ConfirmedPasswordActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
    }//GEN-LAST:event_passwordField_ConfirmedPasswordActionPerformed

    private void button_CreateAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_CreateAccountMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_CreateAccountMouseEntered

    private void button_LoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_LoginMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_LoginMouseEntered

    private void button_ReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ReturnMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ReturnMouseEntered

    private void button_PfpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_PfpMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_PfpMouseEntered

    private void button_PfpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_PfpActionPerformed
        pfpSelected++;
        if (pfpSelected > 6) {
            pfpSelected = 1;
        }
//        System.out.println("pfpSelected: " + pfpSelected);
        loadPfp(pfpSelected);
    }//GEN-LAST:event_button_PfpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_CreateAccount;
    private javax.swing.JButton button_Login;
    private javax.swing.JButton button_Pfp;
    private javax.swing.JButton button_Return;
    private javax.swing.JLabel label_Password;
    private javax.swing.JLabel label_Password1;
    private javax.swing.JLabel label_Pfp;
    private javax.swing.JLabel label_Title;
    private javax.swing.JLabel label_Username;
    private javax.swing.JLabel label_Warning;
    private javax.swing.JPanel panel_Background;
    private javax.swing.JPasswordField passwordField_ConfirmedPassword;
    private javax.swing.JPasswordField passwordField_Password;
    private javax.swing.JTextField textField_Username;
    // End of variables declaration//GEN-END:variables
}
