package bomberman.managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    /*public static void pause() {
        long clipTime = clip.getMicrosecondPosition();
        clip.stop();
        pause = true;
        clip.setMicrosecondPosition(clipTime);
        clip.start();
    }*/
    public static void conti() {

    }
}
