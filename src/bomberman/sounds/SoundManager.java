package bomberman.sounds;

import java.net.URL;

public class SoundManager {
    public static boolean hasSoundEffect = true;
    public static boolean hasMusic = true;

    public static Sound music = new Music();
    public static Sound soundEffect = new SoundEffect();
}
