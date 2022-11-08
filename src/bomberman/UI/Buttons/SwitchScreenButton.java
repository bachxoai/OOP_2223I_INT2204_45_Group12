package bomberman.UI.Buttons;

import bomberman.managers.GenerateMap;
import bomberman.screen.Screen;
import javafx.stage.Stage;

public class SwitchScreenButton extends MyButton {
    String screenHoldThisButton;
    String screenToSwitchTo;
    public static final int WIDTH_SCREEN_MENU = 500;
    public static final int HEIGHT_SCREEN_MENU = 800;
    public static final int WIDTH_SCREEN_GAMEPLAY = 640;
    public static final int HEIGHT_SCREEN_GAMEPLAY = 640;

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
        s.setWidth(WIDTH_SCREEN_GAMEPLAY);
        s.setHeight(HEIGHT_SCREEN_GAMEPLAY);
    }
}
