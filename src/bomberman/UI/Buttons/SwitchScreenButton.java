package bomberman.UI.Buttons;

import bomberman.managers.GenerateMap;
import bomberman.screen.Screen;
import javafx.stage.Stage;

public class SwitchScreenButton extends MyButton {
    String screenHoldThisButton;
    String screenToSwitchTo;

    public SwitchScreenButton(String name, String path, String screenHoldThisButton, String screenToSwitchTo) {
        super(name, path);
        this.screenHoldThisButton = screenHoldThisButton;
        this.screenToSwitchTo = screenToSwitchTo;
        setOnAction(actionEvent -> {
            if (name.equals("Random")) {
                GenerateMap.generate("RandomMap");
            }
            switchScreen();});
    }
    public void switchScreen() {
        Stage s = (Stage) this.getScene().getWindow();
        Screen scr = Screen.allScreens.get(screenToSwitchTo);
        s.setScene(scr.getScene());
        s.setWidth(640);
        s.setHeight(640);
    }
}
