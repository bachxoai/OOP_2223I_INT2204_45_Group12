package bomberman.screen.levelscreen;

import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;
import bomberman.managers.SoundBackground;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InformationPane extends Pane {

    Button pause;
    LevelScreen containedLevelScreen;
    public static final int SPEED = 0;
    public static final int BOMBNO = 1;
    public static final int FLAME_RANGE = 2;
    //public static final int LIVES_LEFT = 3;
    public static final int TIME_LEFT = 4;
    int maxPlayTime = 200;
    Label speedNumber = new Label();
    Label bombNo = new Label();
    Label flameRange = new Label();
    Label lives = new Label();
    Label timeLeft = new Label();
    Image bom;
    ImageView bomView;

    Image boot;
    ImageView bootView;

    Image clock;
    ImageView clockView;
    HeartPane heartPane;
    Label dauXSpeed;
    Label dauXBom;
    Label dauXFlameRange;
    Label FlameRange;
    HBox speedBox;
    HBox bomBox;
    HBox flameRangeBox;
    HBox clockBox;
    HBox pro;


    public InformationPane(LevelScreen containedLevelScreen) {
        this.containedLevelScreen = containedLevelScreen;
        pro = new HBox(60);
        dauXSpeed = new Label(" x ");
        dauXSpeed.setFont(Font.loadFont("file:res/font/font.ttf",20));
        dauXBom = new Label(" x ");
        dauXBom.setFont(Font.loadFont("file:res/font/font.ttf",20));
        dauXFlameRange = new Label(" x ");
        dauXFlameRange.setFont(Font.loadFont("file:res/font/font.ttf",20));

        bom = Sprite.bomb.getFxImage();
        bomView = new ImageView(bom);
        bomView.setFitWidth(32);
        bomView.setFitHeight(32);

        boot = new Image(getClass().getResourceAsStream("/boot.png"));
        bootView = new ImageView(boot);
        bootView.setFitHeight(32);
        bootView.setFitWidth(32);

        clock = new Image(getClass().getResourceAsStream("/clock.png"));
        clockView = new ImageView(clock);
        clockView.setFitWidth(32);
        clockView.setFitHeight(32);

        FlameRange = new Label("Flame range");
        FlameRange.setFont(Font.loadFont("file:res/font/font.ttf",20));
        flameRangeBox = new HBox(5);
        flameRange.setFont(Font.loadFont("file:res/font/font.ttf",20));
        flameRangeBox.getChildren().addAll(FlameRange,dauXFlameRange,flameRange);

        speedBox = new HBox(5);
        speedNumber.setFont(Font.loadFont("file:res/font/font.ttf",20));
        speedBox.getChildren().addAll(bootView,dauXSpeed,speedNumber);

        clockBox = new HBox(5);
        timeLeft.setFont(Font.loadFont("file:res/font/font.ttf",20));
        clockBox.getChildren().addAll(clockView,timeLeft);

        bomBox = new HBox(5);
        bombNo.setFont(Font.loadFont("file:res/font/font.ttf",20));
        bomBox.getChildren().addAll(bomView,dauXBom,bombNo);

        heartPane = new HeartPane();

        pause = new Button();

        Image imagePause = new Image(getClass().getResourceAsStream("/ImageButton/pause.png"));
        ImageView imageViewPause = new ImageView(imagePause);
        imageViewPause.setFitWidth(32);
        imageViewPause.setFitHeight(32);
        pause.setGraphic(imageViewPause);

        pause.setStyle("-fx-background-color: #bdb76b");
        pause.setOnAction(actionEvent -> {
            handlePause();
        });

        pro.getChildren().addAll(pause,speedBox,bomBox,flameRangeBox,clockBox,heartPane);
        getChildren().add(pro);
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
        setStat(TIME_LEFT, (int) GamePlay.playedTime);
    }

    public Pane getHeartPane() {
        return heartPane;
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
