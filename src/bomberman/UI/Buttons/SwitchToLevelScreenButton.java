package bomberman.UI.buttons;

import bomberman.screen.Screen;
import bomberman.screen.levelscreen.LevelScreen;
import javafx.stage.Stage;

public class SwitchToLevelScreenButton extends SwitchScreenButton {
    int mapOfButton;

    public SwitchToLevelScreenButton(String name, String screenHoldThisButton, String screenToSwitchTo, int mapOfButton) {
        super(name, screenHoldThisButton, screenToSwitchTo);
        this.mapOfButton = mapOfButton;
    }

    @Override
    public void switchScreen(String nameScreen) {
        LevelScreen tmp = (LevelScreen) Screen.allScreens.get(screenToSwitchTo);
        tmp.getGamePlay().loadMap("res/levels/Level" + mapOfButton + ".txt");
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
