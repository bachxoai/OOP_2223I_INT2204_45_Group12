package bomberman.managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

  public class Sound {
     public static Clip clip;
    static void setFile(URL url) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     static void play() {
        clip.start();
    }
     static void loop () {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
     static void stop() {
        clip.stop();
    }
    public static void stopMusic() {
        stop();
    }
    /*public static void pause() {
        long clipTime = clip.getMicrosecondPosition();
        clip.stop();
        clip.setMicrosecondPosition(clipTime);
        clip.start();
    }*/
}
