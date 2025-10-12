package sfx.sounds;

import java.net.URL;

public enum SoundFile {

    ABILITY("/resources/sounds/ability.wav"),
    HURT("/resources/sounds/hurt.wav"),
    TEDDYCOPTER_JUMP("/resources/sounds/teddycopter_jump.wav"),
    TEDDYCOPTER_JUMPBOOST("/resources/sounds/teddycopter_jumpboost.wav"),
    ROCKETRON_JUMP("/resources/sounds/rocketron_jump.wav"),
    ROCKETRON_SHIELD("/resources/sounds/rocketron_shield.wav"),
    FOLDY_JUMP("/resources/sounds/foldy_jump.wav"),
    FOLDY_DASH("/resources/sounds/foldy_dash.wav"),
    SELECT("/resources/sounds/select.wav"), // not fully implemented
    CLICK("/resources/sounds/click.wav"), // not fully implemented
    INCORRECT("/resources/sounds/incorrect.wav"), // unused
    CHARGE("/resources/sounds/charge.wav"),
    TOKEN("/resources/sounds/token.wav"),
    NO_CHARGES("/resources/sounds/nocharges.wav");
    
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
