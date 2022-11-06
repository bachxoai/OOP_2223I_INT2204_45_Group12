package bomberman.screen.levelscreen;

import bomberman.managers.MapManager;
import bomberman.screen.Screen;
import bomberman.managers.GamePlay;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    Image image;

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
        //scene.getStylesheets().add(getClass().getResource("/background.css").toExternalForm());

        image = new Image(getClass().getResourceAsStream("/textures/GamePlayBackground.png"));
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
        );
        Background background = new Background(backgroundImage);
        root.setBackground(background);
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
        stopTimer();
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

    public MapManager getMapManager() {
        return gamePlay.getMapManager();
    }

    public void setMap(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }
    public Pane getHeartpane(){
        return getInformationPane().getHeartPane();
    }

    public void setBomberStats() {
        getInformationPane().setBomberStats();
    }

    public void setBomberStat(int type, double value) {
        getInformationPane().setStat(type, value);
    }

    public void setBomberSpeed(int v) {
        informationPane.setSpeed(v);
    }

    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public GameOverPane getGameOver() {
        return gameOver;
    }

    public void setGameOver(GameOverPane gameOver) {
        this.gameOver = gameOver;
    }
}