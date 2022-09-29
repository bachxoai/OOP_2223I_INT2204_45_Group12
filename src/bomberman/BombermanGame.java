package bomberman;

import bomberman.ScreenController.Map1;
import bomberman.ScreenController.Menu;
import bomberman.ScreenController.Screen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import bomberman.entities.Bomber;
import bomberman.entities.Entity;
import bomberman.entities.Grass;
import bomberman.entities.Wall;
import bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public void start(Stage stage) {
        Screen a = new Menu("Menu");
        Screen b = new Map1("Map1");
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.setScene(a.getScene());
        stage.show();
    }
}
