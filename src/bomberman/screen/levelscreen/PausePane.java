package bomberman.screen.levelscreen;

import bomberman.UI.buttons.BackToMenuFromLevel;
import bomberman.UI.buttons.ExitButton;
import bomberman.UI.buttons.SwitchScreenButton;
import bomberman.managers.SoundBackground;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class PausePane extends VBox {
    LevelScreen containedLevelScreen;

    public PausePane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        relocate(200,200);

        Button exit = new ExitButton();
        exit.setPrefWidth(200);
        createFont(exit);
        createImageButton("/ImageButton/exit.png",exit);

        Button cont = new Button("Continue");
        createFont(cont);
        createImageButton("/ImageButton/continue.png",cont);
        cont.setPrefWidth(200);
        cont.setOnAction(actionEvent -> {
            containedLevelScreen.getRoot().getChildren().remove(this);
            containedLevelScreen.getPlayingPane().setOpacity(1);
            containedLevelScreen.startTimer();
        });

        SwitchScreenButton BackToMenu = new BackToMenuFromLevel("Back", containedLevelScreen.getCurrentScreen(), "Menu", this, containedLevelScreen);
        createFont(BackToMenu);
        createImageButton("/ImageButton/back.png",BackToMenu);
        BackToMenu.setPrefWidth(200);

        getChildren().addAll(cont,BackToMenu,exit);
    }
    void createFont(Button a) {
        a.setFont(Font.loadFont("file:res/font/font.ttf",20));
        a.setStyle("-fx-background-color: #33ff36");
        a.setTextFill(Paint.valueOf("#bf00b3"));
    }
    void createImageButton(String path, Button a) {
        Image image = new Image(getClass().getResourceAsStream(path));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        a.setGraphic(imageView);
    }
}
