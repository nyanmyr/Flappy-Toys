package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import utility.sprites.StaticSprite;

public class Register extends javax.swing.JFrame {

    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    private StaticSprite background;

    public Register(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        this.WINDOW_HEIGHT = SCREEN_HEIGHT;
        this.WINDOW_WIDTH = SCREEN_WIDTH;

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

                background.setBounds(0, 0, WINDOW_HEIGHT, WINDOW_WIDTH);
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
        passwordField_Password1 = new javax.swing.JPasswordField();
        label_Password1 = new javax.swing.JLabel();
        textField_Username = new javax.swing.JTextField();
        label_Password = new javax.swing.JLabel();
        passwordField_Password = new javax.swing.JPasswordField();
        button_CreateAccount = new javax.swing.JButton();
        button_Login = new javax.swing.JButton();
        button_Return = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
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

        label_Username.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        label_Username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_Username.setText("USERNAME");
        panel_Background.add(label_Username);
        label_Username.setBounds(270, 140, 260, 30);

        passwordField_Password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField_Password1ActionPerformed(evt);
            }
        });
        panel_Background.add(passwordField_Password1);
        passwordField_Password1.setBounds(270, 340, 260, 30);

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
        button_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReturnActionPerformed(evt);
            }
        });
        panel_Background.add(button_Return);
        button_Return.setBounds(290, 480, 220, 30);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Menu(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true));
    }//GEN-LAST:event_button_ReturnActionPerformed

    private void button_CreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CreateAccountActionPerformed
        // implement login system here
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Account(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true));
    }//GEN-LAST:event_button_CreateAccountActionPerformed

    private void button_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_LoginActionPerformed
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Login(WINDOW_HEIGHT, WINDOW_WIDTH).setVisible(true));
    }//GEN-LAST:event_button_LoginActionPerformed

    private void passwordField_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordField_PasswordActionPerformed

    private void passwordField_Password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField_Password1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordField_Password1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_CreateAccount;
    private javax.swing.JButton button_Login;
    private javax.swing.JButton button_Return;
    private javax.swing.JLabel label_Password;
    private javax.swing.JLabel label_Password1;
    private javax.swing.JLabel label_Title;
    private javax.swing.JLabel label_Username;
    private javax.swing.JPanel panel_Background;
    private javax.swing.JPasswordField passwordField_Password;
    private javax.swing.JPasswordField passwordField_Password1;
    private javax.swing.JTextField textField_Username;
    // End of variables declaration//GEN-END:variables
}
