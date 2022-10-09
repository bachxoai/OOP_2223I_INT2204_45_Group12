package bomberman.ScreenController;

import bomberman.managers.CollisionChecker;
import bomberman.entities.tileEntities.Item.Item;
import bomberman.managers.TileEntityManager;
import bomberman.graphics.Sprite;
import bomberman.entities.movingEntities.Bomber;
import bomberman.entities.Entity;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

import java.io.IOException;
import java.util.ArrayList;

public class ClassicMap extends Map {
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

    public ClassicMap(String currentScreen) {
        super(currentScreen);
        tileEntityManager = new TileEntityManager(this);
        collisionChecker = new CollisionChecker(this);
        try {
            tileEntityManager.loadMap("res/levels/Level1.txt");
        } catch (Exception e) {
            System.err.println("IOException at loadmap() from Map Constructor");
        }

        createScene();
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

    @Override
    public Group createGamePlay() {
        canvas = new Canvas(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        gc = canvas.getGraphicsContext2D();
        Group gamePlay = new Group();
        gamePlay.getChildren().add(canvas);
        return gamePlay;
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
        scene.setOnKeyPressed(keyEvent -> {
            bomberman.handleEvent(keyEvent);
        });

        scene.setOnKeyReleased(keyEvent -> {
            bomberman.handleReleasedEvent(keyEvent);
        });
    }


}