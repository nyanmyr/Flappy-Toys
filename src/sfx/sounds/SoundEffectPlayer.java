package sfx.sounds;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffectPlayer {

    public static void playSound(SoundFile soundFile) {
        
        URL filePath = soundFile.getFilePath();
        
        try {
            if (filePath == null) {
                System.err.println("Sound URL is null.");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Optional cleanup listener
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start(); // Play asynchronously
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound.");
        }
    }

}
