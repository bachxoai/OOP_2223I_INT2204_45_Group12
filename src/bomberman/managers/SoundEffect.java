package bomberman.managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundEffect extends Sound{
    static Clip clipSE;
    public static boolean hasSoundEffect = true;
    public static URL soundBomb = SoundEffect.class.getResource("/MusicEffect/bomb_out_out.wav");
    public static URL soundBombExplosion = SoundEffect.class.getResource("/MusicEffect/bomb_explosion.wav");
    public static URL soundBombermanDie = SoundEffect.class.getResource("/MusicEffect/bbmDie.wav");
    public static URL soundEnemyDie = SoundEffect.class.getResource("/MusicEffect/BalloomDie.wav");
    public static URL soundBombsItem = SoundEffect.class.getResource("/MusicEffect/bombsItem.wav");
    public static URL soundFlamesItem = SoundEffect.class.getResource("/MusicEffect/FlamesItem.wav");
    public static URL soundSpeedItem = SoundEffect.class.getResource("/MusicEffect/SpeedItem.wav");
    public static URL soundYAD = SoundEffect.class.getResource("/MusicEffect/YAD.wav");
    public static void play() {
        clipSE.start();
    }
    public static void loop () {
        clipSE.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stop() {
        clipSE.stop();
    }
    static void setFile(URL url) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clipSE = AudioSystem.getClip();
            clipSE.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void playSE(URL url) {
        setFile(url);
        play();
    }
}
