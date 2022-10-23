package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import bomberman.managers.SoundBackground;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PausePane extends VBox {
    LevelScreen containedLevelScreen;

    public PausePane(LevelScreen containedLevelScreen) {
        super(30);
        this.containedLevelScreen = containedLevelScreen;
        Button exit = new ExitButton();
        exit.setPrefWidth(200);
        getChildren().add(exit);
        relocate(400,100);
        Button cont = new Button("Continue");
        cont.setPrefWidth(200);
        cont.setOnAction(actionEvent -> {
            containedLevelScreen.getRoot().getChildren().remove(this);
            containedLevelScreen.getPlayingPane().setOpacity(1);
            containedLevelScreen.startTimer();
        });
        getChildren().add(cont);
        SwitchScreenButton BackToMenu = new SwitchScreenButton("Back to menu", containedLevelScreen.getCurrentScreen(), "Menu",false);
        BackToMenu.setPrefWidth(200);
        getChildren().add(BackToMenu);
    }
}
