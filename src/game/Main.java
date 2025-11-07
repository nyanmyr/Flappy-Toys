package game;

import database.DatabaseConnection;
import java.sql.SQLException;
import sfx.music.MusicFile;
import sfx.music.MusicPlayer;

public class Main {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    static public boolean databaseConnected = false;

    public static void main(String args[]) {

        MusicPlayer.playSound(MusicFile.MENU);

        try {
            databaseConnected = DatabaseConnection.update();
        } catch (SQLException e) {
        }

        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }
}
