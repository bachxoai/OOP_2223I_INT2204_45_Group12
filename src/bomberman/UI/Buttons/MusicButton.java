package bomberman.UI.Buttons;
import bomberman.screen.Menu;
import bomberman.sounds.SoundManager;

import java.net.URL;

public class MusicButton extends MyButton {
    public MusicButton(String s, String path, URL soundBackground) {
        super(s, path);
        setOnAction(actionEvent -> {
            SoundManager.music.stop();
            SoundManager.music.play(soundBackground);
        });
    }
}
