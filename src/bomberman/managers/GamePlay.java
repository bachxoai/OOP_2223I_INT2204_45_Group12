package bomberman.managers;

import bomberman.screen.levelscreen.InformationPane;
import bomberman.screen.levelscreen.LevelScreen;
import bomberman.entities.DynamicEntity;
import bomberman.graphics.Sprite;
import bomberman.entities.moving.Bomber;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

public class GamePlay {
    AnimationTimer timer;
    public static int frameCount = 0;
    public static double playedTime = 0;
    LevelScreen containedLevelScreen;
    Group root;
    int maxCol;
    int maxRow;
    int mapWidth;
    int mapHeight;
    public static final int gameplayScreenWidth = 640;
    public static final int gameplayScreenHeight = 640;
    private GraphicsContext gc;
    private Canvas canvas;
    private MapManager mapManager;

    public GamePlay(LevelScreen containedLevelScreen) {
        this.containedLevelScreen = containedLevelScreen;
        mapManager = new MapManager(this);
        createGamePlay();
    }

    public Group createGamePlay() {
        canvas = new Canvas(gameplayScreenWidth, gameplayScreenHeight);
        gc = canvas.getGraphicsContext2D();
        root = new Group();
        root.getChildren().add(canvas);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                frameCount = (frameCount + 1) % 60;
                if (frameCount % 6 == 0) {
                    playedTime += 0.1;
                }
                if (playedTime >= 200) {
                    getContainedLevelScreen().defeat();
                }
                handleEvent();
                render();
                update();
                getContainedLevelScreen().setBomberStat(InformationPane.TIME_LEFT, (int) playedTime);
            }
        };
        return root;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void resetPlayTime() {
        playedTime = 0;
        frameCount = 0;
        root.setOpacity(1);
    }

    public void setMapWidth(int width) {
        this.mapWidth = width;
    }

    public void setMapHeight(int height) {
        this.mapHeight = height;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public void update() {
        // update tile entity
        for (int j = 0; j < mapManager.getRow(); j++) {
            for (int i = 0; i < mapManager.getCol(); i++) {
                for (int k = mapManager.getTilesAt(i, j).size() - 1; k >= 0; k--) {
                    if (mapManager.getTilesAt(i, j).get(k) instanceof DynamicEntity) {
                        ((DynamicEntity) mapManager.getTilesAt(i, j).get(k)).update();
                    }
                }
            }
        }

        // update moving entity
        for (int i = mapManager.getMovingEntities().size() - 1; i >= 0; i--) {
            mapManager.getMovingEntities().get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int j = 0; j < mapManager.getRow(); j++) {
            for (int i = 0; i < mapManager.getCol(); i++) {
                for (int k = 0; k < mapManager.getTilesAt(i, j).size(); k++) {
                        mapManager.getTilesAt(i, j).get(k).render(gc);
                }
            }
        }

        for (int i = mapManager.getMovingEntities().size() - 1; i >= 0; i--) {
            mapManager.getMovingEntities().get(i).render(gc);
        }
    }

    public void handleEvent() {
        containedLevelScreen.getScene().setOnKeyPressed(keyEvent -> {
            mapManager.getBomberman().handleEvent(keyEvent);
        });

        containedLevelScreen.getScene().setOnKeyReleased(keyEvent -> {
            mapManager.getBomberman().handleReleasedEvent(keyEvent);
        });
    }

    public void loadMap(String path) {
        mapManager.loadMap(path);
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public LevelScreen getContainedLevelScreen() {
        return containedLevelScreen;
    }

    public void setContainedLevelScreen(LevelScreen containedLevelScreen) {
        this.containedLevelScreen = containedLevelScreen;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }
}