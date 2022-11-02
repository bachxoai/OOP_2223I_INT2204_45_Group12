package bomberman.screen;

import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

public abstract class Screen {
    protected Scene scene;
    protected String currentScreen;
    public static HashMap<String, Screen> allScreens = new HashMap<>();

    public Screen(String currentScreen) {
        this.currentScreen = currentScreen;
        allScreens.put(currentScreen, this);
    }

    public abstract Scene createScene() throws IOException;

    public Scene getScene() {
    return this.scene;
  }

    public String getCurrentScreen() {
        return currentScreen;
    }
}