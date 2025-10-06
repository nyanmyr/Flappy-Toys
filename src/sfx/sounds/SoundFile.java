package sfx.sounds;

import java.net.URL;

public enum SoundFile {

    ABILITY("/resources/sounds/ability.wav"),
    HURT("/resources/sounds/hurt.wav"),
    JUMP("/resources/sounds/jump.wav"),
    SELECT("/resources/sounds/select.wav"),
    CHARGE("/resources/sounds/charge.wav"),
    TOKEN("/resources/sounds/token.wav");
    
    private final URL FILE_PATH;
    
    SoundFile(String FILE_PATH) {
        this.FILE_PATH = SoundFile.class.getResource(FILE_PATH);
        if (this.FILE_PATH == null) {
            throw new IllegalArgumentException("Resource not found: " + FILE_PATH);
        }
    }
    
    public URL getFilePath() {
        return FILE_PATH;
    }
    
}
