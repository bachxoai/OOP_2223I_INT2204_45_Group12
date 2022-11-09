package bomberman.sounds;

import java.net.URL;

public class SoundEffect extends Sound{

    public static URL bombCountDown = SoundEffect.class.getResource("/sounds/soundEffects/BombCountDown.wav");
    public static URL bombExplosion = SoundEffect.class.getResource("/sounds/soundEffects/BombExplosion.wav");
    public static URL gameOver = SoundEffect.class.getResource("/sounds/soundEffects/GameOver.wav");
    public static URL enemyDeath = SoundEffect.class.getResource("/sounds/soundEffects/EnemyDeath.wav");
    public static URL collectingItem = SoundEffect.class.getResource("/sounds/soundEffects/CollectingItem.wav");
    public static URL bomberDeath = SoundEffect.class.getResource("/sounds/soundEffects/BomberDeath.wav");
    public static URL plantingBomb = SoundEffect.class.getResource("/sounds/soundEffects/PlantingBomb.wav");

    @Override
    public void play(URL url) {
        if (SoundManager.hasSound) {
            setFile(url);
            clip.start();
        }
    }
}
