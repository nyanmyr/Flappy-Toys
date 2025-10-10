package game;

import sfx.music.MusicFile;
import sfx.music.MusicPlayer;

public class Main {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;

    public static void main(String args[]) {

        MusicPlayer.playSound(MusicFile.MENU);

        java.awt.EventQueue.invokeLater(() -> new Menu(SCREEN_WIDTH, SCREEN_HEIGHT).setVisible(true));
    }
}
