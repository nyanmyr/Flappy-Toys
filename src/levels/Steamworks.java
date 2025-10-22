
package levels;

import collectibles.GearToken;
import levels.backgrounds.SteamworksBackground;
import levels.parallaxes.ParallaxLevel;
import levels.parallaxes.SteamworksParallax;
import obstacles.columns.SteamworksColumn;
import obstacles.grounds.SteamworksGround;
import sfx.music.MusicFile;


public class Steamworks extends AbstractLevel {

    @Override
    final public void generateColumn(int gap, int columnOffset, int lifeTime) {
        column = new SteamworksColumn(gap, columnOffset, lifeTime);
    }

    @Override
    final public void generateLeftGround(int offsetX) {
        ground_left = new SteamworksGround(offsetX);
    }

    @Override
    final public void generateRightGround(int offsetX) {
        ground_right = new SteamworksGround(offsetX);
    }

    @Override
    final public void generateBackground() {
        background = new SteamworksBackground();
    }

    @Override
    final public void generateLeftParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_left = new SteamworksParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_left = new SteamworksParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    final public void generateRightParallax(int offsetX, ParallaxLevel level) {
        switch (level) {
            case LEVEL_1 -> {
                parallax1_right = new SteamworksParallax(offsetX, level);
            }
            case LEVEL_2 -> {
                parallax2_right = new SteamworksParallax(offsetX, level);
            }
            default ->
                throw new AssertionError(level.name());
        }
    }

    @Override
    public void generateToken(int lifeTime) {
        token = new GearToken(lifeTime);
    }

    @Override
    public void generateMusic() {
        MUSIC_FILE = MusicFile.STEAMWORKS;
    }
}

