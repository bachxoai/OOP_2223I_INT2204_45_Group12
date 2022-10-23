package bomberman.screen.levelscreen;

import bomberman.screen.Screen;
import bomberman.managers.GamePlay;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class LevelScreen extends Screen {
//    AnimationTimer timer;
//
//    //Một biến đếm số frame của từng giây, sẽ sử dụng kết hợp với MovingEntity để tạo Animation
//    public static int frameCount = 0;

    Pane root;
    Pane pausePane; // 3 nut o man pause
    Pane playingPane;
    InformationPane informationPane;
    GamePlay gamePlay;
    GameOverPane gameOver;

    public LevelScreen(String currentScreen) {
        super(currentScreen);
        pausePane = new PausePane(this);
        gameOver = new GameOverPane(this);
        createScene();
    }
    public Pane createPlayingPane() {
        playingPane = new VBox();
        gamePlay = new GamePlay(this);
        informationPane = new InformationPane(this);
        playingPane.getChildren().addAll(informationPane, gamePlay.getRoot());
        return playingPane;
    }

    //public abstract Group createGamePlay();


    public Scene createScene() {
        root = new Pane();
        createPlayingPane();
        root.getChildren().addAll(playingPane);
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/background.css").toExternalForm());
        return scene;
    }

    public void startTimer() {
        gamePlay.startTimer();
    }

    public void stopTimer() {
        gamePlay.stopTimer();
    }

    public void gameOver() {
        root.getChildren().add(gameOver);
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

    public void setBomberStats() {
        getInformationPane().setBomberStats();
    }

    public void setBomberStat(int type, int value) {
        getInformationPane().setStat(type, value);
    }

    public void setBomberSpeed(int v) {
        informationPane.setSpeed(v);
    }
}