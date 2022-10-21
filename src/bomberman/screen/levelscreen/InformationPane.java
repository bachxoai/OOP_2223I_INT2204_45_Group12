package bomberman.screen.levelscreen;

import bomberman.managers.GamePlay;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class InformationPane extends HBox {
    Button pause;
    LevelScreen containedLevelScreen;
    public static final int SPEED = 0;
    public static final int BOMBNO = 1;
    public static final int FLAME_RANGE = 2;
    public static final int LIVES_LEFT = 3;
    public static final int TIME_LEFT = 4;
    int maxPlayTime = 200;
    Label speedNumber = new Label();
    Label bombNo = new Label();
    Label flameRange = new Label();
    Label lives = new Label();
    Label timeLeft = new Label();

    public InformationPane(LevelScreen containedLevelScreen) {
        this.containedLevelScreen = containedLevelScreen;
        pause = new Button("Pause");
        pause.setOnAction(actionEvent -> {
            handlePause();
        });
        getChildren().add(pause);
        getChildren().addAll(new Label("Speed: "), speedNumber,
                new Label("  Bomb left: "), bombNo,
                new Label("  Flame range: "), flameRange,
                new Label("  Lives: "), lives,
                new Label("  Times: "), timeLeft);
    }

    public void setSpeed(int v) {
        try {
            speedNumber.setText(String.valueOf(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBomberStats() {
        setStat(SPEED, (int) containedLevelScreen.getMap().getBomberman().getVelocity());
        setStat(BOMBNO, (int) containedLevelScreen.getMap().getBomberman().getBombNums());
        setStat(FLAME_RANGE, (int) containedLevelScreen.getMap().getBomberman().getFlameRange());
        setStat(LIVES_LEFT, (int) containedLevelScreen.getMap().getBomberman().getLives());
        setStat(TIME_LEFT, (int) GamePlay.playedTime);
    }

    public void setStat(int type, int value) {
        switch (type) {
            case SPEED: {
                speedNumber.setText(String.valueOf(value));
                break;
            }
            case BOMBNO: {
                bombNo.setText(String.valueOf(value));
                break;
            }
            case FLAME_RANGE: {
                flameRange.setText(String.valueOf(value));
                break;
            }
            case LIVES_LEFT: {
                lives.setText(String.valueOf(value));
                break;
            }
            case TIME_LEFT: {
                timeLeft.setText(String.valueOf(maxPlayTime - value));
                break;
            }
        }
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
}
