package bomberman.ScreenController;

import bomberman.UI.buttons.ExitButton;
import bomberman.UI.buttons.SwitchPaneButton;
import bomberman.UI.buttons.SwitchScreenButton;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;

public class Menu extends Screen {
    HashMap<String, Pane> optionPanes;
    Pane root;

    public Menu(String currentScreen) throws IOException {
        super(currentScreen);
        optionPanes = new HashMap<>();
        optionPanes.put("Menu", new VBox(30));
        optionPanes.put("ChooseMap", new VBox(30));
        createScene();
    }

    public Scene createScene() throws IOException {
        root = new Pane();
        createChooseMapPane();
        createMenuPane();
        createRoot();

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("background.css").toExternalForm());
        return scene;
    }

    public void createMenuPane() {
        //optionPanes.replace("Menu", new VBox());

        SwitchPaneButton toChooseMap = new SwitchPaneButton("Play", optionPanes.get("Menu"), optionPanes.get("ChooseMap"), root);
        toChooseMap.setPrefWidth(100);
        System.out.println(toChooseMap);
        Button exit = new ExitButton();
        exit.relocate(30,30);
        exit.setPrefWidth(100);
        optionPanes.get("Menu").relocate(400,50);
        optionPanes.get("Menu").getChildren().addAll(toChooseMap,exit);

    }

    public void createChooseMapPane() {
        //optionPanes.replace("ChooseMap", new VBox());
        Pane p = optionPanes.get("ChooseMap");
        p.relocate(400,50);
        SwitchScreenButton map1 = new SwitchScreenButton("Map 1", "Menu", "Map1");
        map1.setPrefWidth(100);
        SwitchPaneButton p1 = new SwitchPaneButton("Back", optionPanes.get("ChooseMap"), optionPanes.get("Menu"), root);
        p1.setPrefWidth(100);
        Button exit = new ExitButton();
        exit.setPrefWidth(100);
        p.getChildren().addAll(map1, p1,exit );
    }

    public void createRoot()  {
        //root = new Pane();
        root.getChildren().add(optionPanes.get("Menu"));
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