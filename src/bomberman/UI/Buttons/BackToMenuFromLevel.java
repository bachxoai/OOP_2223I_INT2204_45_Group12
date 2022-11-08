package bomberman.UI.Buttons;
import bomberman.screen.Menu;
import bomberman.screen.Screen;
import bomberman.screen.levelscreen.LevelScreen;
import bomberman.sounds.SoundManager;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BackToMenuFromLevel extends SwitchScreenButton {
    Pane containedPane;

    LevelScreen levelScreen;

    public BackToMenuFromLevel(String name, String path, String screenHoldThisButton, String screenToSwitchTo,
                               Pane containedPane, LevelScreen levelScreen) {
        super(name, path, screenHoldThisButton, screenToSwitchTo);
        this.containedPane = containedPane;
        this.levelScreen = levelScreen;
    }

    @Override
    public void switchScreen() {
        Stage s = (Stage) this.getScene().getWindow();
        Screen scr = Screen.allScreens.get(screenToSwitchTo);
        s.setScene(scr.getScene());
        s.setWidth(WIDTH_SCREEN_MENU);
        s.setHeight(HEIGHT_SCREEN_MENU);
        levelScreen.getRoot().getChildren().remove(containedPane);
        if (SoundManager.hasMusic) {
            SoundManager.music.continuePlaying();
        }
    }
}
