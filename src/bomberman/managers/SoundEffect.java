package bomberman.managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundEffect extends Sound{
    static Clip clipSE;
    public static boolean hasSoundEffect = true;
    public static URL bombCountDown = SoundEffect.class.getResource("/MusicEffect/BombCountDown.wav");
    public static URL bombExplosion = SoundEffect.class.getResource("/MusicEffect/BombExplosion.wav");
    public static URL gameOver = SoundEffect.class.getResource("/MusicEffect/GameOver.wav");
    public static URL enemyDeath = SoundEffect.class.getResource("/MusicEffect/EnemyDeath.wav");
    public static URL collectingItem = SoundEffect.class.getResource("/MusicEffect/CollectingItem.wav");
    public static URL bomberDeath = SoundEffect.class.getResource("/MusicEffect/BomberDeath.wav");

    public static URL plantingBomb = SoundEffect.class.getResource("/MusicEffect/PlantingBomb.wav");
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
        if (hasSoundEffect) {
            setFile(url);
            play();
        }
    }
}
