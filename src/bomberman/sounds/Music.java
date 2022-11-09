package bomberman.sounds;

import java.net.URL;

public class Music extends Sound{

    public static URL defaultMusic = Music.class.getResource("/sounds/musics/along.wav");
    public static URL feed = Music.class.getResource("/sounds/musics/feed.wav");
    public static URL sines = Music.class.getResource("/sounds/musics/sines.wav");

    @Override
    public void play(URL url) {
        if (SoundManager.hasSound) {
            setFile(url);
            clip.start();
            loop();
        }
    }
}
