package bomberman;

import bomberman.screen.levelscreen.LevelScreen;
import bomberman.screen.Menu;
import bomberman.screen.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class BombermanGame extends Application {
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public void start(Stage stage) throws IOException {
        Screen a = new Menu("Menu");
        new LevelScreen("Map1");
        stage.setWidth(500);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.setScene(a.getScene());
        stage.show();
    }
}
