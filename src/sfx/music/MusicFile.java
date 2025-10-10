package sfx.music;

import java.net.URL;

public enum MusicFile {

    BRICKLAND("/resources/music/pou.wav"),
    ICECREAM("/resources/music/pou.wav"),
    MENU("/resources/music/menu.wav");
    
    private final URL FILE_PATH;
    
    MusicFile(String FILE_PATH) {
        this.FILE_PATH = MusicFile.class.getResource(FILE_PATH);
        if (this.FILE_PATH == null) {
            throw new IllegalArgumentException("Resource not found: " + FILE_PATH);
        }
    }
    
    public URL getFilePath() {
        return FILE_PATH;
    }
    
}
