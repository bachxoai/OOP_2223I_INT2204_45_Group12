package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.MyButton;
import bomberman.graphics.Sprite;
import bomberman.managers.GamePlay;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class InformationPane extends HBox {
    Button pause;
    LevelScreen containedLevelScreen;
    public static final int SPEED = 0;
    public static final int BOMBNO = 1;
    public static final int FLAME_RANGE = 2;
    public static final int TIME_LEFT = 4;
    int maxPlayTime = 200;
    Label speedNumber = new Label();
    Label bombNo = new Label();
    Label flameRange = new Label();
    Label lives = new Label();
    Label timeLeft = new Label();
    Image bom;
    ImageView bomView;
    HeartPane heartPane;
    HBox speedBox;
    HBox bomBox;
    HBox flameRangeBox;
    HBox clockBox;

    public InformationPane(LevelScreen containedLevelScreen) {
        super(25);
        this.containedLevelScreen = containedLevelScreen;

        bom = Sprite.bomb.getFxImage();
        bomView = new ImageView(bom);
        bomView.setFitWidth(32);
        bomView.setFitHeight(32);

        flameRangeBox = new HBox(5);
        flameRangeBox.getChildren().addAll(createImageView("/flameRange.png"),createLabel("x"),FixLabel(flameRange));

        speedBox = new HBox(5);
        speedBox.getChildren().addAll(createImageView("/boot.png"),createLabel("x"),FixLabel(speedNumber));

        clockBox = new HBox(5);
        clockBox.getChildren().addAll(createImageView("/clock.png"),FixLabel(timeLeft));

        bomBox = new HBox(5);
        bomBox.getChildren().addAll(bomView,createLabel("x"),FixLabel(bombNo));

        heartPane = new HeartPane();

        pause = new MyButton("", MyButton.PAUSE);
        pause.setPrefWidth(20);
        pause.setOnAction(actionEvent -> {
            handlePause();
        });

        getChildren().addAll(pause,speedBox,bomBox,flameRangeBox,clockBox,heartPane);
        setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setSpeed(int v) {
        try {
            speedNumber.setText(String.valueOf(v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBomberStats() {
        setStat(SPEED, (int) containedLevelScreen.getMapManager().getBomberman().getVelocity());
        setStat(BOMBNO, containedLevelScreen.getMapManager().getBomberman().getBombNums());
        setStat(FLAME_RANGE, containedLevelScreen.getMapManager().getBomberman().getFlameRange());
        setStat(TIME_LEFT, (int) GamePlay.playedTime);
        containedLevelScreen.getHeartpane().getChildren().remove(HeartPane.hf3);
        containedLevelScreen.getHeartpane().getChildren().remove(HeartPane.hf2);
        containedLevelScreen.getHeartpane().getChildren().remove(HeartPane.hf1);
        containedLevelScreen.getHeartpane().getChildren().add(HeartPane.hf1);
        containedLevelScreen.getHeartpane().getChildren().add(HeartPane.hf2);
        containedLevelScreen.getHeartpane().getChildren().add(HeartPane.hf3);
    }

    public Pane getHeartPane() {
        return heartPane;
    }

    public void setStat(int type, double valueD) {
        int value = (int) valueD;

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

    Label createLabel(String s) {
        Label a = new Label(s);
        a.setFont(Font.loadFont("file:res/font/font.ttf",25));
        a.setTextFill(Paint.valueOf("#FFFAD7"));
        return a;
    }

    Label FixLabel(Label a) {
        a.setFont(Font.loadFont("file:res/font/font.ttf",25));
        a.setTextFill(Paint.valueOf("#FFFAD7"));
        return a;
    }

    ImageView createImageView(String path) {
        Image image = new Image(getClass().getResourceAsStream(path));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);
        return imageView;
    }
}
