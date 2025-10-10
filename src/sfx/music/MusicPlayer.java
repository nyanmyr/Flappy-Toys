package sfx.music;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

    private static Clip currentClip;
    private static FloatControl currentVolumeControl;
    private static boolean isTransitioning = false;
    
    public static void playSound(MusicFile musicFile) {
        stopCurrentClip();

        URL filePath = musicFile.getFilePath();

        try {
            if (filePath == null) {
                System.err.println("Sound URL is null.");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Set up volume control
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(0f); // Default volume

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            currentClip = clip;
            currentVolumeControl = volumeControl;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + e.getMessage());
        }
    }

    public static void crossfadeTo(MusicFile nextMusicFile, int durationMs) {
        if (isTransitioning) return; // Prevent overlapping transitions
        isTransitioning = true;

        new Thread(() -> {
            try {
                // Prepare the next clip
                URL filePath = nextMusicFile.getFilePath();
                if (filePath == null) {
                    System.err.println("Next sound URL is null.");
                    isTransitioning = false;
                    return;
                }

                AudioInputStream audioIn = AudioSystem.getAudioInputStream(filePath);
                Clip nextClip = AudioSystem.getClip();
                nextClip.open(audioIn);

                FloatControl nextVolume = (FloatControl) nextClip.getControl(FloatControl.Type.MASTER_GAIN);
                nextVolume.setValue(-40f); // Start muted (quiet)

                nextClip.loop(Clip.LOOP_CONTINUOUSLY);
                nextClip.start();

                // Fade out current clip while fading in the next one
                if (currentClip != null && currentVolumeControl != null) {
                    int steps = 50;
                    int sleepTime = durationMs / steps;

                    float startVolCurrent = currentVolumeControl.getValue();
                    float endVolCurrent = -40f; // Quiet
                    float startVolNext = -40f;
                    float endVolNext = 0f; // Normal volume

                    for (int i = 0; i <= steps; i++) {
                        float progress = i / (float) steps;

                        // Linear fade
                        currentVolumeControl.setValue(startVolCurrent + (endVolCurrent - startVolCurrent) * progress);
                        nextVolume.setValue(startVolNext + (endVolNext - startVolNext) * progress);

                        Thread.sleep(sleepTime);
                    }

                    currentClip.stop();
                    currentClip.close();
                }

                // Set the new clip as current
                currentClip = nextClip;
                currentVolumeControl = nextVolume;

            } catch (Exception e) {
                System.out.println("Error during crossfade: " + e.getMessage());
            } finally {
                isTransitioning = false;
            }
        }).start();
    }

    public static void stopCurrentClip() {
        if (currentClip != null) {
            currentClip.stop();
            currentClip.close();
            currentClip = null;
            currentVolumeControl = null;
        }
    }

}
