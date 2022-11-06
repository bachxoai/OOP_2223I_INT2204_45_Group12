package bomberman.UI.buttons;

import bomberman.managers.SoundBackground;
import bomberman.screen.Screen;
import bomberman.screen.levelscreen.LevelScreen;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BackToMenuFromLevel extends SwitchScreenButton {
    Pane containedPane;

    LevelScreen levelScreen;

    public BackToMenuFromLevel(String name, String screenHoldThisButton, String screenToSwitchTo,
                               Pane containedPane, LevelScreen levelScreen) {
        super(name, screenHoldThisButton, screenToSwitchTo);
        this.containedPane = containedPane;
        this.levelScreen = levelScreen;
    }

    @Override
    public void switchScreen(String nameScreen) {
        Stage s = (Stage) this.getScene().getWindow();
        Screen scr = Screen.allScreens.get(screenToSwitchTo);
        s.setScene(scr.getScene());
        s.setWidth(500);
        s.setHeight(800);
        levelScreen.getRoot().getChildren().remove(containedPane);
        SoundBackground.clip.stop();
        SoundBackground.clip.start();
    }
}
