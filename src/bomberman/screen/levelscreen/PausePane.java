package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.BackToMenuFromLevel;
import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.MyButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PausePane extends VBox {
    LevelScreen containedLevelScreen;

    public PausePane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        relocate(200,200);

        Button exit = new ExitButton(MyButton.EXIT);
        exit.setPrefWidth(200);

        Button cont = new MyButton("Continue", MyButton.CONTINUE);
        cont.setPrefWidth(200);
        cont.setOnAction(actionEvent -> {
            containedLevelScreen.getRoot().getChildren().remove(this);
            containedLevelScreen.getPlayingPane().setOpacity(1);
            containedLevelScreen.startTimer();
        });

        SwitchScreenButton BackToMenu = new BackToMenuFromLevel("Back", MyButton.BACK, containedLevelScreen.getCurrentScreen(), "Menu", this, containedLevelScreen);
        BackToMenu.setPrefWidth(200);

        getChildren().addAll(cont,BackToMenu,exit);
    }
}
