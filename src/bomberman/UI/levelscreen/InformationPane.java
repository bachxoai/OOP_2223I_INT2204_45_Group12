package bomberman.UI.levelscreen;

import bomberman.ScreenController.LevelScreen;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class InformationPane extends HBox {
    Button pause;
    LevelScreen containedLevelScreen;

    public InformationPane(LevelScreen containedLevelScreen) {
        this.containedLevelScreen = containedLevelScreen;
        pause = new Button("Pause");
        pause.setOnAction(actionEvent -> {
            handlePause();
        });
        getChildren().add(pause);
    }

    private void handlePause() {
        containedLevelScreen.stopTimer();
        containedLevelScreen.getPlayingPane().setOpacity(.5);
        try {
            containedLevelScreen.getRoot().getChildren().add(containedLevelScreen.getPausePane());
        } catch (IllegalArgumentException e) {
            System.out.println("Pausing!");
        }
    }

//    public InformationPane() {
//        pause = new Button("Pause");
//        getChildren().add(pause);
//    }
}
