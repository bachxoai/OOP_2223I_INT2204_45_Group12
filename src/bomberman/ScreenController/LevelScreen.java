package bomberman.ScreenController;

import bomberman.UI.levelscreen.InformationPane;
import bomberman.UI.levelscreen.PausePane;
import bomberman.managers.GamePlay;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class LevelScreen extends Screen {
//    AnimationTimer timer;
//
//    //Một biến đếm số frame của từng giây, sẽ sử dụng kết hợp với MovingEntity để tạo Animation
//    public static int frameCount = 0;

    Pane root;
    Pane pausePane;
    Pane playingPane;
    InformationPane informationPane;
    GamePlay gamePlay;

    public LevelScreen(String currentScreen) {
        super(currentScreen);
        pausePane = new PausePane(this);
        createScene();
    }
    public Pane createPlayingPane() {
        playingPane = new VBox();
        gamePlay = new GamePlay(this);
        informationPane = new InformationPane(this);
        System.out.println(gamePlay.getRoot());
        //playingPane.getChildren().addAll(informationPane, map.getRoot());
        playingPane.getChildren().addAll(informationPane, gamePlay.getRoot());
        return playingPane;
    }

    //public abstract Group createGamePlay();


    public Scene createScene() {
        root = new Pane();
        createPlayingPane();
        root.getChildren().addAll(playingPane);
        scene = new Scene(root);

//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                //Mỗi khung hình (frame) thì biến frameCount sẽ tăng lên 1 đơn vị
//                //Nếu frameCount >=60 thì frameCount sẽ được gán lại = 0.
//                //1s có 60 frame -> 60 lần gọi
//                frameCount = (frameCount+1)%60;
//                gamePlay.handleEvent();
//                gamePlay.render();
//                gamePlay.update();
//            }
//        };
//        timer.start();

        return scene;
    }

//    public void startTimer() {
//        timer.start();
//    }

    public void startTimer() {
        gamePlay.startTimer();
    }

    public void stopTimer() {
        gamePlay.stopTimer();
    }

    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Pane getPlayingPane() {
        return playingPane;
    }

    public void setPlayingPane(Pane playingPane) {
        this.playingPane = playingPane;
    }

    public Pane getPausePane() {
        return pausePane;
    }

    public void setPausePane(Pane pausePane) {
        this.pausePane = pausePane;
    }

    public InformationPane getInformationPane() {
        return informationPane;
    }

    public void setInformationPane(InformationPane informationPane) {
        this.informationPane = informationPane;
    }

    public GamePlay getMap() {
        return gamePlay;
    }

    public void setMap(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }
}