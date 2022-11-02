package bomberman.screen;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchPaneButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import bomberman.managers.Sound;
import bomberman.managers.SoundBackground;
import bomberman.managers.SoundEffect;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class Menu extends Screen {
    HashMap<String, Pane> optionPanes;
    Pane root;
    DropShadow dropShadow;

    public Menu(String currentScreen) throws IOException {
        super(currentScreen);
        dropShadow = new DropShadow();
        optionPanes = new HashMap<>();
        optionPanes.put("Menu", new VBox(30));
        optionPanes.put("ChooseMap", new VBox(30));
        optionPanes.put("ChooseMusic",new VBox(30));
        createScene();
    }

    public Scene createScene() throws IOException {
        root = new Pane();
        createChooseMapPane();
        createMenuPane();
        createChooseMusic();
        createRoot();

        scene = new Scene(root);

        //scene.getStylesheets().add(getClass().getResource("/background.css").toExternalForm());

        return scene;
    }

    public void createMenuPane() { //play,exit
        //optionPanes.replace("Menu", new VBox());

        SwitchPaneButton toChooseMap = new SwitchPaneButton("Play", optionPanes.get("Menu"), optionPanes.get("ChooseMap"), root);
        toChooseMap.setPrefWidth(200);
        createFont(toChooseMap);

        Image imagePlayButton = new Image(getClass().getResourceAsStream("/ImageButton/play.png"));
        ImageView imageViewPlayButton = new ImageView(imagePlayButton);
        imageViewPlayButton.setFitWidth(32);
        imageViewPlayButton.setFitHeight(32);
        toChooseMap.setGraphic(imageViewPlayButton);

        Button exit = new ExitButton();
        createFont(exit);

        Image imageExit = new Image(getClass().getResourceAsStream("/ImageButton/exit.png"));
        ImageView imageViewExit = new ImageView(imageExit);
        imageViewExit.setFitWidth(32);
        imageViewExit.setFitHeight(32);
        exit.setGraphic(imageViewExit);

        exit.setPrefWidth(200);
        SwitchPaneButton music = new SwitchPaneButton("Music",optionPanes.get("Menu"), optionPanes.get("ChooseMusic"), root);
        music.setPrefWidth(200);
        createFont(music);

        Image imageMusic = new Image(getClass().getResourceAsStream("/ImageButton/music.png"));
        ImageView imageViewMusic = new ImageView(imageMusic);
        imageViewMusic.setFitWidth(32);
        imageViewMusic.setFitHeight(32);
        music.setGraphic(imageViewMusic);

        //createImageButton("/ImageButton/music.png",music);
        optionPanes.get("Menu").relocate(400,50);
        optionPanes.get("Menu").getChildren().addAll(toChooseMap,music,exit);
    }

    public void createChooseMapPane() { //map1,back,exit
        //optionPanes.replace("ChooseMap", new VBox());
        Pane p = optionPanes.get("ChooseMap");
        p.relocate(400,50);
        SwitchScreenButton map1 = new SwitchScreenButton("Map 1", "Menu", "Map1",false);
        map1.setPrefWidth(200);
        createFont(map1);

        Image imageMap1 = new Image(getClass().getResourceAsStream("/ImageButton/map1.png"));
        ImageView imageViewMap1 = new ImageView(imageMap1);
        imageViewMap1.setFitWidth(32);
        imageViewMap1.setFitHeight(32);
        map1.setGraphic(imageViewMap1);

        SwitchPaneButton back = new SwitchPaneButton("Back", optionPanes.get("ChooseMap"), optionPanes.get("Menu"), root);
        back.setPrefWidth(200);
        createFont(back);

        Image imageBack = new Image(getClass().getResourceAsStream("/ImageButton/back.png"));
        ImageView imageViewBack = new ImageView(imageBack);
        imageViewBack.setFitWidth(32);
        imageViewBack.setFitHeight(32);
        back.setGraphic(imageViewBack);

        //createImageButton("ImageButton/back.png",back);
        Button exit = new ExitButton();
        createFont(exit);

        Image imageExit = new Image(getClass().getResourceAsStream("/ImageButton/exit.png"));
        ImageView imageViewExit = new ImageView(imageExit);
        imageViewExit.setFitWidth(32);
        imageViewExit.setFitHeight(32);
        exit.setGraphic(imageViewExit);

       // createImageButton("ImageButton/exit.png",exit);
        exit.setPrefWidth(200);
        p.getChildren().addAll(map1,back,exit);
    }


    public void createChooseMusic() {
        Pane chooseMusic = optionPanes.get("ChooseMusic");
        chooseMusic.relocate(400,50);
        Button noMusic = new Button("Unmute");
        noMusic.setPrefWidth(200);
        createFont(noMusic);
        noMusic.setOnAction(actionEvent -> {
            SoundBackground.stopMusic();
            SoundEffect.hasSoundEffect = false;
        });
        SwitchPaneButton back = new SwitchPaneButton("Back", optionPanes.get("ChooseMusic"), optionPanes.get("ChooseMap"), root);
        back.setPrefWidth(200);
        createFont(back);

        Image imageBack = new Image(getClass().getResourceAsStream("/ImageButton/back.png"));
        ImageView imageViewBack = new ImageView(imageBack);
        imageViewBack.setFitWidth(32);
        imageViewBack.setFitHeight(32);
        back.setGraphic(imageViewBack);

        Button feed = new Button("Feed");
        createFont(feed);
        feed.setPrefWidth(200);
        feed.setOnAction(actionEvent -> {
            SoundEffect.hasSoundEffect = true;
            SoundBackground.stopMusic();
            SoundBackground.playMusic(SoundBackground.soundFeed);
        });
        Button sinnes = new Button("Sinnes");
        createFont(sinnes);
        sinnes.setPrefWidth(200);
        sinnes.setOnAction(actionEvent -> {
            SoundEffect.hasSoundEffect = true;
            SoundBackground.stopMusic();
            SoundBackground.playMusic(SoundBackground.soundSinnes);
        });
        chooseMusic.getChildren().addAll(noMusic,feed,sinnes,back);
    }
    public void createRoot()  {
        //root = new Pane();
        root.getChildren().add(optionPanes.get("Menu"));
        SoundBackground.playMusic(SoundBackground.soundMenu);
        /*Color color = Color.YELLOW;
        CornerRadii cornerRadii = new CornerRadii(5);
        Insets insets = new Insets(5, 2, 3, 3);
        BackgroundFill backgroundFill = new BackgroundFill(color, cornerRadii, insets);
        Background background = new Background(backgroundFill);*/

        /*Image image= new Image("/bbm.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
        );
        Background background = new Background(backgroundImage);
        root.setBackground(background);*/
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