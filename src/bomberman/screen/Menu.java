package bomberman.screen;

import bomberman.UI.Buttons.*;
import bomberman.managers.SoundBackground;
import bomberman.managers.SoundEffect;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.util.HashMap;

public class Menu extends Screen {
    HashMap<String, Pane> optionPanes;
    Pane root;
    public static boolean isUnmute = false;

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
        SwitchPaneButton toChooseMap = new SwitchPaneButton("Play", MyButton.PLAY, optionPanes.get("Menu"), optionPanes.get("ChooseMap"), root);
        Button exit = new ExitButton(MyButton.EXIT);
        SwitchPaneButton music = new SwitchPaneButton("Music", MyButton.MUSIC, optionPanes.get("Menu"), optionPanes.get("ChooseMusic"), root);

        optionPanes.get("Menu").relocate(150,322);
        optionPanes.get("Menu").getChildren().addAll(toChooseMap,music,exit);
    }

    public void createChooseMapPane() {
        Pane p = optionPanes.get("ChooseMap");
        p.relocate(150,322);

        SwitchScreenButton map1 = new SwitchToLevelScreenButton("Map 1", MyButton.MAP, "Menu", "Map1", "Level1");
        SwitchScreenButton map2 = new SwitchToLevelScreenButton("Map 2", MyButton.MAP, "Menu", "Map1", "Level2");
        SwitchScreenButton map3 = new SwitchToLevelScreenButton("Map 3", MyButton.MAP, "Menu", "Map1", "Level3");
        SwitchScreenButton mapRandom = new SwitchToLevelScreenButton("Random", MyButton.MAP, "Menu", "Map1", "RandomMap");
        SwitchPaneButton back = new SwitchPaneButton("Back", "/ImageButton/back.png", optionPanes.get("ChooseMap"), optionPanes.get("Menu"), root);

        p.getChildren().addAll(map1, map2, map3, mapRandom, back);
    }

    public void createChooseMusic() {
        Pane chooseMusic = optionPanes.get("ChooseMusic");
        chooseMusic.relocate(150,290);

        SwitchPaneButton back = new SwitchPaneButton("Back", MyButton.BACK, optionPanes.get("ChooseMusic"), optionPanes.get("Menu"), root);

        Button feed = new MusicButton("Feed", MyButton.MUSIC, SoundBackground.soundFeed);

        Button sinnes = new MusicButton("Sinnes", MyButton.MUSIC, SoundBackground.soundSinnes);

        Button musicOn = new MyButton("Un Mute", MyButton.MUTE);
        Button musicOff = new MyButton("Mute", MyButton.MUTE);
        musicOff.setOnAction(actionEvent -> {
            chooseMusic.getChildren().removeAll(musicOff, feed, sinnes, back);
            chooseMusic.getChildren().addAll(musicOn,feed,sinnes,back);
            isUnmute = true;
            SoundBackground.stopMusic();
            SoundEffect.hasSoundEffect = false;
        });

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
}