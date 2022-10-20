package bomberman.UI.levelscreen;

import bomberman.ScreenController.LevelScreen;
import bomberman.entities.Entity;
import bomberman.entities.moving.MovingEntity;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        Label speedTitle = new Label("Speed: ");
        Label speedNumber = new Label();
        speedNumber.setText( String.valueOf( ((MovingEntity) containedLevelScreen.getMap().getBomberman()).getVelocity() ) );
        getChildren().addAll(speedTitle, speedNumber);
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
