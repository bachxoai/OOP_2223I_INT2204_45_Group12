package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import bomberman.managers.SoundBackground;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PausePane extends VBox {
    LevelScreen containedLevelScreen;

    public PausePane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        Button exit = new ExitButton();
        exit.setPrefWidth(300);
        createFont(exit);

        Image imageExit = new Image(getClass().getResourceAsStream("/ImageButton/exit.png"));
        ImageView imageViewExit = new ImageView(imageExit);
        imageViewExit.setFitWidth(32);
        imageViewExit.setFitHeight(32);
        exit.setGraphic(imageViewExit);

        relocate(400,100);
        Button cont = new Button("Continue");
        createFont(cont);

        Image imageCon = new Image(getClass().getResourceAsStream("/ImageButton/continue.png"));
        ImageView imageViewCon = new ImageView(imageCon);
        imageViewCon.setFitWidth(32);
        imageViewCon.setFitHeight(32);
        cont.setGraphic(imageViewCon);

        //createImageButton("ImageButton/continue.png",cont);
        cont.setPrefWidth(300);
        cont.setOnAction(actionEvent -> {
            containedLevelScreen.getRoot().getChildren().remove(this);
            containedLevelScreen.getPlayingPane().setOpacity(1);
            containedLevelScreen.startTimer();
        });
        SwitchScreenButton BackToMenu = new SwitchScreenButton("Back to menu", containedLevelScreen.getCurrentScreen(), "Menu",false);
        createFont(BackToMenu);

        Image imageBack = new Image(getClass().getResourceAsStream("/ImageButton/back.png"));
        ImageView imageViewBack = new ImageView(imageBack);
        imageViewBack.setFitWidth(32);
        imageViewBack.setFitHeight(32);
        BackToMenu.setGraphic(imageViewBack);

        //createImageButton("ImageButton/back.png",BackToMenu);
        BackToMenu.setPrefWidth(300);
        getChildren().addAll(cont,BackToMenu,exit);
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
