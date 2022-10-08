package bomberman.ScreenController;

import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class Map extends Screen {
    //Chiều rộng của map
    int width;

    //Chiều cao của map
    int height;
    AnimationTimer timer;

    //Một biến đếm số frame của từng giây, sẽ sử dụng kết hợp với MovingEntity để tạo Animation
    public static int frameCount = 0;

    Pane root;
    Pane playingPane;
    Pane pausePane;

    public Map(String currentScreen) {
        super(currentScreen);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Pane createPlayingPane() {
        playingPane = new VBox();
        playingPane.getChildren().addAll(informationPane(), createGamePlay());
        return playingPane;
    }

    public abstract Group createGamePlay();

    public Pane informationPane () {
        Pane p = new HBox();
        Button pause = new Button("Pause");
        pause.setOnAction(actionEvent -> {
            handlePause();
        });
        p.getChildren().addAll(pause, new ExitButton());
        return p;
    }

    public Scene createScene() {
        root = new Pane();
        root.getChildren().add(createPlayingPane());
        createPausePane();
        scene = new Scene(root);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //Mỗi khung hình (frame) thì biến frameCount sẽ tăng lên 1 đơn vị
                //Nếu frameCount >=60 thì frameCount sẽ được gán lại = 0.
                //1s có 60 frame -> 60 lần gọi
                frameCount = (frameCount+1)%60;
                handleEvent();
                render();
                update();
            }
        };
        timer.start();

        return scene;
    }

    public abstract void update();

    public abstract void render();

    public abstract void  handleEvent();

    public void startTimer() {
        timer.start();
    }

    public void createPausePane() {
        pausePane = new VBox();
        pausePane.setPrefHeight(100);
        pausePane.setPrefWidth(200);
        pausePane.setLayoutX(400);
        pausePane.setLayoutY(200);
        pausePane.getChildren().add(new ExitButton());
        Button cont = new Button("Continue");
        cont.setOnAction(actionEvent -> {
            root.getChildren().remove(pausePane);
            playingPane.setOpacity(1);
            timer.start();
        });
        pausePane.getChildren().add(cont);
        Color color = Color.YELLOW;
        CornerRadii cornerRadii = new CornerRadii(5);
        javafx.geometry.Insets insets = new Insets(5, 2, 3, 3);
        BackgroundFill backgroundFill = new BackgroundFill(color, cornerRadii, insets);
        Background background = new Background(backgroundFill);
        pausePane.setBackground(background);
        cont.setLayoutY(20);
        cont.setLayoutX(20);
        pausePane.getChildren().add(new SwitchScreenButton("Back to menu", currentScreen, "Menu"));
    }
    public void handlePause() {
        timer.stop();
        playingPane.setOpacity(.5);
        try {
            root.getChildren().add(pausePane);
        } catch (IllegalArgumentException e) {

        }
    }
}