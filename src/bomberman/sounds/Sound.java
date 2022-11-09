package bomberman.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public abstract class Sound {
    protected Clip clip;
    public abstract void play(URL url);
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
            clip.stop();
        }
    public void continuePlaying() {clip.start();}
    void setFile(URL url) {
        try {
          AudioInputStream ais = AudioSystem.getAudioInputStream(url);
          clip = AudioSystem.getClip();
          clip.open(ais);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
