package bomberman.UI.levelscreen;

import bomberman.ScreenController.LevelScreen;
import bomberman.UI.buttons.ExitButton;
import bomberman.UI.buttons.SwitchScreenButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
        /*Color color = Color.YELLOW;
        CornerRadii cornerRadii = new CornerRadii(5);
        javafx.geometry.Insets insets = new Insets(5, 2, 3, 3);
        BackgroundFill backgroundFill = new BackgroundFill(color, cornerRadii, insets);
        Background background = new Background(backgroundFill);
        setBackground(background);*/
        //cont.setLayoutY(20);
        //cont.setLayoutX(20);
        SwitchScreenButton p = new SwitchScreenButton("Back to menu", containedLevelScreen.getCurrentScreen(), "Menu");
        p.setPrefWidth(200);
        getChildren().add(p);
    }
}
