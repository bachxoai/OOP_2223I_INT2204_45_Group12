package bomberman.UI.buttons;

import bomberman.screen.levelscreen.LevelScreen;
import bomberman.screen.Screen;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SwitchScreenButton extends Button {
    String screenHoldThisButton;
    String screenToSwitchTo;

    public SwitchScreenButton(String name, String screenHoldThisButton, String screenToSwitchTo) {
        super(name);
        this.screenHoldThisButton = screenHoldThisButton;
        this.screenToSwitchTo = screenToSwitchTo;
        setOnAction(actionEvent -> {switchScreen();});
    }

    public void switchScreen() {
        Stage s = (Stage) this.getScene().getWindow();
        Screen scr = Screen.allScreens.get(screenToSwitchTo);
        s.setScene(scr.getScene());
        if (scr instanceof LevelScreen) {
            ((LevelScreen) scr).startTimer();
            ((LevelScreen) scr).setBomberStats();
        }
    }
}
