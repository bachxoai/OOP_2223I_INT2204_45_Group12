package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import bomberman.managers.SoundBackground;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverPane extends VBox {
    LevelScreen containedLevelScreen;

    public GameOverPane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        getChildren().add(new Label("Game over!"));
        Button exit = new ExitButton();
        createFont(exit);

        Image imageExit = new Image(getClass().getResourceAsStream("/ImageButton/exit.png"));
        ImageView imageViewExit = new ImageView(imageExit);
        imageViewExit.setFitWidth(32);
        imageViewExit.setFitHeight(32);
        exit.setGraphic(imageViewExit);

        //createImageButton("ImageButton/exit.png",exit);
        exit.setPrefWidth(250);
        relocate(400,100);
        SwitchScreenButton p = new SwitchScreenButton("Back to menu", containedLevelScreen.getCurrentScreen(), "Menu",true);
        createFont(p);

        Image imageBack = new Image(getClass().getResourceAsStream("/ImageButton/back.png"));
        ImageView imageViewBack = new ImageView(imageBack);
        imageViewBack.setFitWidth(32);
        imageViewBack.setFitHeight(32);
        p.setGraphic(imageViewBack);

        //createImageButton("ImageButton/back.png",p);
        p.setPrefWidth(250);
        getChildren().addAll(p,exit);
    }
    void createFont(Button a) {
        a.setFont(Font.loadFont("file:res/font/font.ttf",20));
        a.setStyle("-fx-background-color: #bdb76b");
        a.setTextFill(Color.WHITE);
    }
    /*void createImageButton(String path, Button a) {
        Image image = new Image(getClass().getResourceAsStream(path));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        a.setGraphic(imageView);
    }*/
}
