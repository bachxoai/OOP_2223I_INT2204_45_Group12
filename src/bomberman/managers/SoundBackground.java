package bomberman.managers;

import java.net.URL;

public class SoundBackground extends Sound{
    public static boolean pause = false;
    public static URL soundMenu = SoundBackground.class.getResource("/MusicBg/along.wav");
    public static URL soundFeed = SoundBackground.class.getResource("/MusicBg/feed.wav");
    public static URL soundSinnes = SoundBackground.class.getResource("/MusicBg/sinnes.wav");
    public static void playMusic(URL url) {
        setFile(url);
        play();
        loop();
    }
}
