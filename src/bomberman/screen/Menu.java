package bomberman.screen;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchPaneButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import bomberman.UI.Buttons.SwitchToLevelScreenButton;
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
    public static boolean isUnmute = false;


    String chosenMapPath;

    String chosenMap;

    public Menu(String currentScreen) throws IOException {
        super(currentScreen);
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

        return scene;
    }

    public void createMenuPane() {
        SwitchPaneButton toChooseMap = new SwitchPaneButton("Play", optionPanes.get("Menu"), optionPanes.get("ChooseMap"), root);
        toChooseMap.setPrefWidth(150);
        createFont(toChooseMap);
        createImageButton("/ImageButton/play.png",toChooseMap);

        Button exit = new ExitButton();
        exit.setPrefWidth(150);
        createFont(exit);
        createImageButton("/ImageButton/exit.png",exit);

        SwitchPaneButton music = new SwitchPaneButton("Music",optionPanes.get("Menu"), optionPanes.get("ChooseMusic"), root);
        music.setPrefWidth(150);
        createFont(music);
        createImageButton("/ImageButton/music.png",music);

        optionPanes.get("Menu").relocate(175,322);
        optionPanes.get("Menu").getChildren().addAll(toChooseMap,music,exit);
    }

    public void createChooseMapPane() {
        Pane p = optionPanes.get("ChooseMap");
        p.relocate(175,322);

        SwitchScreenButton map1 = new SwitchToLevelScreenButton("Map 1", "Menu", "Map1", 1);
        map1.setPrefWidth(150);
        createFont(map1);
        createImageButton("/ImageButton/map1.png",map1);

        SwitchScreenButton map2 = new SwitchToLevelScreenButton("Map 2", "Menu", "Map1", 2);
        map2.setPrefWidth(150);
        createFont(map2);
        createImageButton("/ImageButton/map1.png",map2);

        SwitchScreenButton map3 = new SwitchToLevelScreenButton("Map 3", "Menu", "Map1", 3);
        map3.setPrefWidth(150);
        createFont(map3);
        createImageButton("/ImageButton/map1.png",map3);

        SwitchPaneButton back = new SwitchPaneButton("Back", optionPanes.get("ChooseMap"), optionPanes.get("Menu"), root);
        back.setPrefWidth(150);
        createFont(back);
        createImageButton("/ImageButton/back.png",back);

//        Button exit = new ExitButton();
//        exit.setPrefWidth(150);
//        createFont(exit);
//        createImageButton("/ImageButton/exit.png",exit);

//        p.getChildren().addAll(map1,back,exit);
        p.getChildren().addAll(map1, map2, map3, back);
    }

    public void createChooseMusic() {
        Pane chooseMusic = optionPanes.get("ChooseMusic");
        chooseMusic.relocate(175,290);

        SwitchPaneButton back = new SwitchPaneButton("Back", optionPanes.get("ChooseMusic"), optionPanes.get("Menu"), root);
        back.setPrefWidth(160);
        createFont(back);
        createImageButton("/ImageButton/back.png",back);

        Button feed = new Button("Feed");
        createFont(feed);
        createImageButton("/ImageButton/music.png",feed);
        feed.setPrefWidth(160);
        feed.setOnAction(actionEvent -> {
            isUnmute = false;
            SoundEffect.hasSoundEffect = true;
            SoundBackground.stopMusic();
            SoundBackground.playMusic(SoundBackground.soundFeed);
        });

        Button sinnes = new Button("Sinnes");
        createFont(sinnes);
        createImageButton("/ImageButton/music.png",sinnes);
        sinnes.setPrefWidth(160);
        sinnes.setOnAction(actionEvent -> {
            isUnmute = false;
            SoundEffect.hasSoundEffect = true;
            SoundBackground.stopMusic();
            SoundBackground.playMusic(SoundBackground.soundSinnes);
        });


        Button musicOn = new Button("Un Mute");
        Button musicOff = new Button("Mute");
        musicOff.setPrefWidth(160);
        createFont(musicOff);
        createImageButton("/ImageButton/umnute.png",musicOff);
        musicOff.setOnAction(actionEvent -> {
            chooseMusic.getChildren().removeAll(musicOff, feed, sinnes, back);
            chooseMusic.getChildren().addAll(musicOn,feed,sinnes,back);
            isUnmute = true;
            SoundBackground.stopMusic();
            SoundEffect.hasSoundEffect = false;
        });

        musicOn.setPrefWidth(160);
        createFont(musicOn);
        createImageButton("/ImageButton/umnute.png",musicOn);
        musicOn.setOnAction(actionEvent -> {
            chooseMusic.getChildren().removeAll(musicOn, feed, sinnes, back);
            chooseMusic.getChildren().addAll(musicOff,feed,sinnes,back);
            isUnmute = false;
            SoundBackground.clip.start();
            SoundEffect.hasSoundEffect = true;
        });


        chooseMusic.getChildren().addAll(musicOff,feed,sinnes,back);
    }
    public void createRoot()  {
        //root = new Pane();
        root.getChildren().add(optionPanes.get("Menu"));
        SoundBackground.playMusic(SoundBackground.soundMenu);

        Image image= new Image(getClass().getResourceAsStream("/textures/MenuBackground.png"));
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
        );
        Background background = new Background(backgroundImage);
        root.setBackground(background);
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

    public String getChosenMapPath() {
        return chosenMapPath;
    }

    public void setChosenMapPath(String chosenMapPath) {
        this.chosenMapPath = chosenMapPath;
    }
}