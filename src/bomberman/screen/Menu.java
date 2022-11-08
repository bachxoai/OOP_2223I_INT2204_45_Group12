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
    final int X_OF_VBOX_MENU = 150;
    final int Y_OF_VBOX_MENU = 322;

    public Menu(String currentScreen) throws IOException {
        super(currentScreen);
        optionPanes = new HashMap<>();
        optionPanes.put("Menu", new VBox(MyButton.BUTTON_DISTANCE));
        optionPanes.put("ChooseMap", new VBox(MyButton.BUTTON_DISTANCE));
        optionPanes.put("ChooseMusic",new VBox(MyButton.BUTTON_DISTANCE));
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

        optionPanes.get("Menu").relocate(X_OF_VBOX_MENU,Y_OF_VBOX_MENU);
        optionPanes.get("Menu").getChildren().addAll(toChooseMap,music,exit);
    }

    public void createChooseMapPane() {
        Pane p = optionPanes.get("ChooseMap");
        p.relocate(X_OF_VBOX_MENU,Y_OF_VBOX_MENU);

        SwitchScreenButton map1 = new SwitchToLevelScreenButton("Map 1", MyButton.MAP, "Menu", "Map1", "Level1");
        SwitchScreenButton map2 = new SwitchToLevelScreenButton("Map 2", MyButton.MAP, "Menu", "Map1", "Level2");
        SwitchScreenButton map3 = new SwitchToLevelScreenButton("Map 3", MyButton.MAP, "Menu", "Map1", "Level3");
        SwitchScreenButton mapRandom = new SwitchToLevelScreenButton("Random", MyButton.MAP, "Menu", "Map1", "RandomMap");
        SwitchPaneButton back = new SwitchPaneButton("Back", "/ImageButton/back.png", optionPanes.get("ChooseMap"), optionPanes.get("Menu"), root);

        p.getChildren().addAll(map1, map2, map3, mapRandom, back);
    }

    public void createChooseMusic() {
        Pane chooseMusic = optionPanes.get("ChooseMusic");
        chooseMusic.relocate(X_OF_VBOX_MENU,Y_OF_VBOX_MENU);

        SwitchPaneButton back = new SwitchPaneButton("Back", MyButton.BACK, optionPanes.get("ChooseMusic"), optionPanes.get("Menu"), root);

        Button feed = new MusicButton("Feed", MyButton.MUSIC, SoundBackground.soundFeed);

        Button sines = new MusicButton("Sines", MyButton.MUSIC, SoundBackground.soundSinnes);

        Button musicOn = new MyButton("Un Mute", MyButton.MUTE);
        Button musicOff = new MyButton("Mute", MyButton.MUTE);
        musicOff.setOnAction(actionEvent -> {
            chooseMusic.getChildren().removeAll(musicOff, feed, sines, back);
            chooseMusic.getChildren().addAll(musicOn,feed,sines,back);
            isUnmute = true;
            SoundBackground.stopMusic();
            SoundEffect.hasSoundEffect = false;
        });

        musicOn.setOnAction(actionEvent -> {
            chooseMusic.getChildren().removeAll(musicOn, feed, sines, back);
            chooseMusic.getChildren().addAll(musicOff,feed,sines,back);
            isUnmute = false;
            SoundBackground.clip.start();
            SoundEffect.hasSoundEffect = true;
        });

        chooseMusic.getChildren().addAll(musicOff,feed,sines,back);
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