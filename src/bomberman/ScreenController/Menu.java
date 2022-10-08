package bomberman.ScreenController;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchPaneButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;

public class Menu extends Screen {
    HashMap<String, Pane> optionPanes;
    Pane root;

    public Menu(String currentScreen) {
        super(currentScreen);
        optionPanes = new HashMap<>();
        optionPanes.put("Menu", new VBox());
        optionPanes.put("ChooseMap", new VBox());
        createScene();
    }

    public Scene createScene() {
        root = new HBox();
        createChooseMapPane();
        createMenuPane();
        createRoot();

        scene = new Scene(root);
        return scene;
    }

    public void createMenuPane() {
        //optionPanes.replace("Menu", new VBox());
        SwitchPaneButton toChooseMap = new SwitchPaneButton("Play", optionPanes.get("Menu"), optionPanes.get("ChooseMap"), root);
        System.out.println(toChooseMap);
        Button exit = new ExitButton();
        optionPanes.get("Menu").getChildren().addAll(toChooseMap, exit);
    }

    public void createChooseMapPane() {
        //optionPanes.replace("ChooseMap", new VBox());
        Pane p = optionPanes.get("ChooseMap");
        SwitchScreenButton map1 = new SwitchScreenButton("Map1", "Menu", "Map1");
        p.getChildren().addAll(map1,
                new SwitchPaneButton("Back", optionPanes.get("ChooseMap"), optionPanes.get("Menu"), root),
                new ExitButton());
    }

    public void createRoot() {
        //root = new Pane();
        root.getChildren().add(optionPanes.get("Menu"));
        Color color = Color.YELLOW;
        CornerRadii cornerRadii = new CornerRadii(5);
        Insets insets = new Insets(5, 2, 3, 3);
        BackgroundFill backgroundFill = new BackgroundFill(color, cornerRadii, insets);
        Background background = new Background(backgroundFill);
        root.setBackground(background);
    }
}