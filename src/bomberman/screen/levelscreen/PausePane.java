package bomberman.screen.levelscreen;

import bomberman.UI.buttons.ExitButton;
import bomberman.UI.buttons.SwitchScreenButton;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PausePane extends VBox {
    LevelScreen containedLevelScreen;

    public PausePane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        //setPrefHeight(100);
        //setPrefWidth(200);
        //setLayoutX(400);
        //setLayoutY(200);
        Button exit = new ExitButton();
        exit.setPrefWidth(200);
        getChildren().add(exit);
        relocate(400,100);
        Button cont = new Button("Continue");
        cont.setPrefWidth(200);
        cont.setOnAction(actionEvent -> {
            containedLevelScreen.getRoot().getChildren().remove(this);
            containedLevelScreen.getPlayingPane().setOpacity(1);
            containedLevelScreen.startTimer();
        });
        getChildren().add(cont);
        SwitchScreenButton p = new SwitchScreenButton("Back to menu", containedLevelScreen.getCurrentScreen(), "Menu");
        p.setPrefWidth(200);
        getChildren().add(p);
    }
}
