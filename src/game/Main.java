package game;

import sfx.music.MusicFile;
import sfx.music.MusicPlayer;

public class Main {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    public static void main(String args[]) {

        MusicPlayer.playSound(MusicFile.MENU);

        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }
}
