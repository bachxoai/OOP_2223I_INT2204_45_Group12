package bomberman.screen.levelscreen;

import bomberman.UI.buttons.ExitButton;
import bomberman.UI.buttons.SwitchScreenButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverPane extends VBox {
    LevelScreen containedLevelScreen;

    public GameOverPane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        getChildren().add(new Label("Game over!"));
        Button exit = new ExitButton();
        exit.setPrefWidth(200);
        getChildren().add(exit);
        relocate(400,100);
        SwitchScreenButton p = new SwitchScreenButton("Back to menu", containedLevelScreen.getCurrentScreen(), "Menu");
        p.setPrefWidth(200);
        getChildren().add(p);
    }
}
