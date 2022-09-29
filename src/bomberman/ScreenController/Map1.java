package bomberman.ScreenController;

import bomberman.entities.Bomber;
import bomberman.entities.Entity;
import bomberman.entities.Grass;
import bomberman.entities.Wall;
import bomberman.graphics.Sprite;
import bomberman.entities.Bomber;
import bomberman.entities.Entity;
import bomberman.entities.Grass;
import bomberman.entities.Wall;
import bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;

public class Map1 extends Screen {
  public static final int WIDTH = 20;
  public static final int HEIGHT = 15;
  private GraphicsContext gc;
  private Canvas canvas;
  private ArrayList<Entity> entities = new ArrayList<>();
  private ArrayList<Entity> stillObjects = new ArrayList<>();
  Label l;
  Button b;

  public Map1(String currentScreen) {
    super(currentScreen);
    createScene();
  }

  public void createLabel() {
    l = new javafx.scene.control.Label("Click to switch to Menu");
  }

  public void createButton() {
    b = new javafx.scene.control.Button("Back to Menu");
    b.setOnAction(actionEvent -> {
      System.out.println(currentScreen);
      currentScreen = "Menu";
      Stage s = (Stage) b.getScene().getWindow();
      s.setScene(allScreens.get(currentScreen).getScene());
    });
    b.setLayoutX(800);
    b.setLayoutY(100);
  }

  public void createScene() {
    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    gc = canvas.getGraphicsContext2D();
    Group root = new Group();
    root.getChildren().add(canvas);
    createButton();
    root.getChildren().add(b);
    scene = new Scene(root);
    handleEvent();

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        render();
        update();
      }
    };
    timer.start();

    createMap();

    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    entities.add(bomberman);
  }
  public void createMap() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        Entity object;
        if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
          object = new Wall(i, j, Sprite.wall.getFxImage());
        }
        else {
          object = new Grass(i, j, Sprite.grass.getFxImage());
        }
        stillObjects.add(object);
      }
    }
  }

  public void update() {
    entities.forEach(Entity::update);
  }

  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }

  public void handleEvent() {
    scene.setOnKeyPressed(keyEvent -> {
      switch(keyEvent.getCode()) {
        case UP:
          System.out.println("Upp");
          break;
      }
    });
  }
}
