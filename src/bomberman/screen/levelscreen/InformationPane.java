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

import java.util.Objects;

public class InformationPane extends HBox {
    Button pause;
    LevelScreen containedLevelScreen;
    public static final int SPEED = 0;
    public static final int BOMB_NO = 1;
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
    final int DISTANCE_OF_HBOX = 5;

    public InformationPane(LevelScreen containedLevelScreen) {
        super(25);
        this.containedLevelScreen = containedLevelScreen;

        bom = Sprite.bomb.getFxImage();
        bomView = new ImageView(bom);
        bomView.setFitWidth(MyButton.IMAGE_SIZE);
        bomView.setFitHeight(MyButton.IMAGE_SIZE);

        flameRangeBox = new HBox(DISTANCE_OF_HBOX);
        flameRangeBox.getChildren().addAll(createImageView("/images/icons/flame.png"),createLabel("x"),FixLabel(flameRange));

        speedBox = new HBox(DISTANCE_OF_HBOX);
        speedBox.getChildren().addAll(createImageView("/images/icons/boot.png"),createLabel("x"),FixLabel(speedNumber));

        clockBox = new HBox(DISTANCE_OF_HBOX);
        clockBox.getChildren().addAll(createImageView("/images/icons/clock.png"),FixLabel(timeLeft));

        bomBox = new HBox(DISTANCE_OF_HBOX);
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
        setStat(BOMB_NO, containedLevelScreen.getMapManager().getBomberman().getBombNums());
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
            case BOMB_NO: {
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
        a.setFont(Font.loadFont("file:res/fonts/font.ttf",MyButton.FONT_SIZE + 5));
        a.setTextFill(Paint.valueOf("#F14C8F"));
        return a;
    }

    Label FixLabel(Label a) {
        a.setFont(Font.loadFont("file:res/fonts/font.ttf",MyButton.FONT_SIZE + 5));
        a.setTextFill(Paint.valueOf("#F14C8F"));
        return a;
    }

    ImageView createImageView(String path) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(MyButton.IMAGE_SIZE);
        imageView.setFitWidth(MyButton.IMAGE_SIZE);
        return imageView;
    }
}
