package bomberman.UI.Buttons;

import bomberman.managers.SoundBackground;
import bomberman.managers.SoundEffect;
import bomberman.screen.Menu;

import java.net.URL;

public class MusicButton extends MyButton {
    URL soundBackground;
    public MusicButton(String s, String path, URL soundBackground) {
        super(s, path);
        this.soundBackground = soundBackground;
        setOnAction(actionEvent -> {
            Menu.isUnmute = false;
            SoundEffect.hasSoundEffect = true;
            SoundBackground.stopMusic();
            SoundBackground.playMusic(soundBackground);
        });
    }
}
