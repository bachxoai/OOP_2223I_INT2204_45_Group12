package bomberman.managers;

import bomberman.ScreenController.LevelScreen;
import bomberman.entities.DynamicEntity;
import bomberman.graphics.Sprite;
import bomberman.entities.movingEntities.Bomber;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

public class GamePlay {
    AnimationTimer timer;

    //Một biến đếm số frame của từng giây, sẽ sử dụng kết hợp với MovingEntity để tạo Animation
    public static int frameCount = 0;
    LevelScreen containedLevelScreen;
    Group root;
    int width;
    int height;
    Bomber bomberman;
    private GraphicsContext gc;
    private Canvas canvas;

    //Quản lý các đối tượng trong map + đọc map
    private MapManager mapManager;// = new TileEntityManager(this);

    //Kiểm tra va chạm
    private CollisionChecker collisionChecker;

    public GamePlay(LevelScreen containedLevelScreen) {
        this.containedLevelScreen = containedLevelScreen;
        mapManager = new MapManager(this);
        mapManager.loadMap("res/levels/Level1.txt");

        createGamePlay();
    }

    public Group createGamePlay() {
        canvas = new Canvas(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        gc = canvas.getGraphicsContext2D();
        root = new Group();
        root.getChildren().add(canvas);
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
        return root;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }


    public Bomber getBomberman() {
        return bomberman;
    }

    public void setBomberman(Bomber bomberman) {
        this.bomberman = bomberman;
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

    //Phương thức handleEvent cho người chơi.
    public void handleEvent() {
        containedLevelScreen.getScene().setOnKeyPressed(keyEvent -> {
            bomberman.handleEvent(keyEvent);
        });

        containedLevelScreen.getScene().setOnKeyReleased(keyEvent -> {
            bomberman.handleReleasedEvent(keyEvent);
        });
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }
}