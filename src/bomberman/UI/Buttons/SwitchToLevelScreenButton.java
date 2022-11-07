package bomberman.UI.Buttons;

import bomberman.screen.Screen;
import bomberman.screen.levelscreen.LevelScreen;
import javafx.stage.Stage;

public class SwitchToLevelScreenButton extends SwitchScreenButton {
    String mapOfButton;

    public SwitchToLevelScreenButton(String name, String path, String screenHoldThisButton, String screenToSwitchTo, String mapOfButton) {
        super(name, path, screenHoldThisButton, screenToSwitchTo);
        this.mapOfButton = mapOfButton;
    }

    @Override
    public void switchScreen() {
        LevelScreen tmp = (LevelScreen) Screen.allScreens.get(screenToSwitchTo);
        tmp.getGamePlay().loadMap("res/levels/" + mapOfButton + ".txt");
        tmp.getGamePlay().resetPlayTime();
        tmp.startTimer();
        tmp.setBomberStats();
        tmp.getPlayingPane().setOpacity(1);

        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(tmp.getScene());
        s.setWidth(640);
        s.setHeight(640);
    }
}
