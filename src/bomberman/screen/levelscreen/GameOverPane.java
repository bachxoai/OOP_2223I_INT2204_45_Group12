package bomberman.screen.levelscreen;

import bomberman.UI.buttons.BackToMenuFromLevel;
import bomberman.UI.buttons.ExitButton;
import bomberman.UI.buttons.SwitchScreenButton;
import bomberman.managers.SoundBackground;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GameOverPane extends VBox {
    LevelScreen containedLevelScreen;
    Label gameOver;
    HBox buttonBox;

    public GameOverPane(LevelScreen containedLevelScreen) {
        super(40);
        this.containedLevelScreen = containedLevelScreen;
        relocate(80,200);

        buttonBox = new HBox(170);

        gameOver = new Label("Game Over");
        gameOver.setFont(Font.loadFont("file:res/font/font.ttf",70));
        gameOver.setTextFill(Paint.valueOf("#bf00b3"));
        getChildren().add(gameOver);

        Button exit = new ExitButton();
        createFont(exit);
        createImageButton("/ImageButton/exit.png",exit);
        exit.setPrefWidth(150);

        SwitchScreenButton p = new BackToMenuFromLevel("Back", containedLevelScreen.getCurrentScreen(), "Menu", containedLevelScreen.getGameOver(), containedLevelScreen);
        createFont(p);
        createImageButton("/ImageButton/back.png",p);
        p.setPrefWidth(150);

        buttonBox.getChildren().addAll(p,exit);
        getChildren().add(buttonBox);
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
