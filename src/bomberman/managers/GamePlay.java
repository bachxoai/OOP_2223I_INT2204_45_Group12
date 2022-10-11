package bomberman.managers;

import bomberman.ScreenController.LevelScreen;
import bomberman.managers.CollisionChecker;
import bomberman.entities.tileEntities.Item.Item;
import bomberman.managers.TileEntityManager;
import bomberman.graphics.Sprite;
import bomberman.entities.movingEntities.Bomber;
import bomberman.entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

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
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> stillObjects = new ArrayList<>();

    private Item items[] = new Item[10];

    //Quản lý các đối tượng trong map + đọc map
    private TileEntityManager tileEntityManager;// = new TileEntityManager(this);

    //Kiểm tra va chạm
    private CollisionChecker collisionChecker;// = new CollisionChecker(this);

    public GamePlay(LevelScreen containedLevelScreen) {//throws IOException {
        this.containedLevelScreen = containedLevelScreen;
        tileEntityManager = new TileEntityManager(this);
        collisionChecker = new CollisionChecker(this);
        tileEntityManager.loadMap("res/levels/Level1.txt");
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

    public ArrayList<Entity> getStillObjects() {
        return stillObjects;
    }

    public TileEntityManager getTileEntityManager() {
        return tileEntityManager;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public Item[] getItems() {
        return items;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Bomber getBomberman() {
        return bomberman;
    }

    public void setBomberman(Bomber bomberman) {
        this.bomberman = bomberman;
    }
    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
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