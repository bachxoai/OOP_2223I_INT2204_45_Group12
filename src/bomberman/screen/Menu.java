package bomberman.screen;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchPaneButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import bomberman.managers.Sound;
import bomberman.managers.SoundBackground;
import bomberman.managers.SoundEffect;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Menu extends Screen {
    HashMap<String, Pane> optionPanes;
    Pane root;

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

        scene.getStylesheets().add(getClass().getResource("/background.css").toExternalForm());

        return scene;
    }

    public void createMenuPane() { //play,exit
        //optionPanes.replace("Menu", new VBox());

        SwitchPaneButton toChooseMap = new SwitchPaneButton("Play", optionPanes.get("Menu"), optionPanes.get("ChooseMap"), root);
        toChooseMap.setPrefWidth(100);
        Button exit = new ExitButton();
        exit.relocate(30,30);
        exit.setPrefWidth(100);
        optionPanes.get("Menu").relocate(400,50);
        optionPanes.get("Menu").getChildren().addAll(toChooseMap,exit);

    }

    public void createChooseMapPane() { //map1,back,exit
        //optionPanes.replace("ChooseMap", new VBox());
        Pane p = optionPanes.get("ChooseMap");
        p.relocate(400,50);
        SwitchScreenButton map1 = new SwitchScreenButton("Map 1", "Menu", "Map1",false);
        map1.setPrefWidth(100);
        SwitchPaneButton back = new SwitchPaneButton("Back", optionPanes.get("ChooseMap"), optionPanes.get("Menu"), root);
        back.setPrefWidth(100);
        SwitchPaneButton music = new SwitchPaneButton("Music",optionPanes.get("ChooseMap"), optionPanes.get("ChooseMusic"), root);
        music.setPrefWidth(100);
        Button exit = new ExitButton();
        exit.setPrefWidth(100);
        p.getChildren().addAll(map1, back,music,exit);
    }


    public void createChooseMusic() {
        Pane chooseMusic = optionPanes.get("ChooseMusic");
        chooseMusic.relocate(400,50);
        Button noMusic = new Button("Unmute");
        noMusic.setPrefWidth(200);
        noMusic.setOnAction(actionEvent -> {
            SoundBackground.stopMusic();
            SoundEffect.hasSoundEffect = false;
        });
        SwitchPaneButton back = new SwitchPaneButton("Back", optionPanes.get("ChooseMusic"), optionPanes.get("ChooseMap"), root);
        back.setPrefWidth(200);
        Button feed = new Button("Feed");
        feed.setPrefWidth(200);
        feed.setOnAction(actionEvent -> {
            SoundEffect.hasSoundEffect = true;
            SoundBackground.stopMusic();
            SoundBackground.playMusic(SoundBackground.soundFeed);
        });
        Button sinnes = new Button("Sinnes");
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

        /*Image image= new Image("./bomberman/ScreenController/bbm.png");
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
}