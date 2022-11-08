package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.BackToMenuFromLevel;
import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.MyButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PausePane extends VBox {
    LevelScreen containedLevelScreen;
    final int X_OF_PAUSE = 200;
    final int Y_OF_PAUSE = 200;
    public PausePane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        relocate(X_OF_PAUSE,Y_OF_PAUSE);

        Button exit = new ExitButton(MyButton.EXIT);
        exit.setPrefWidth(MyButton.BUTTON_WIDTH);

        Button cont = new MyButton("Continue", MyButton.CONTINUE);
        cont.setPrefWidth(MyButton.BUTTON_WIDTH);
        cont.setOnAction(actionEvent -> {
            containedLevelScreen.getRoot().getChildren().remove(this);
            containedLevelScreen.getPlayingPane().setOpacity(1);
            containedLevelScreen.startTimer();
        });

        SwitchScreenButton BackToMenu = new BackToMenuFromLevel("Back", MyButton.BACK, containedLevelScreen.getCurrentScreen(), "Menu", this, containedLevelScreen);
        BackToMenu.setPrefWidth(MyButton.BUTTON_WIDTH);

        getChildren().addAll(cont,BackToMenu,exit);
    }
}
