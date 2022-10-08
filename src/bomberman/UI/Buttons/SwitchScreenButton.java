package bomberman.UI.Buttons;

import bomberman.ScreenController.ClassicMap;
import bomberman.ScreenController.Screen;
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
        System.out.println("cc");
        Stage s = (Stage) this.getScene().getWindow();
        s.setScene(Screen.allScreens.get(screenToSwitchTo).getScene());
        if (Screen.allScreens.get(screenToSwitchTo) instanceof ClassicMap) {
            ((ClassicMap) Screen.allScreens.get(screenToSwitchTo)).startTimer();
        }
    }
}