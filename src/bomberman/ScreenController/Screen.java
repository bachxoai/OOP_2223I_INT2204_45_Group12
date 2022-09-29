package bomberman.ScreenController;

import javafx.scene.Scene;

import java.util.HashMap;

public abstract class Screen {
  Scene scene;
  String currentScreen;
  public static HashMap<String, Screen> allScreens = new HashMap<>();

  public Screen(String currentScreen) {
    this.currentScreen = currentScreen;
    allScreens.put(currentScreen, this);
  }

  public Scene getScene() {
    return this.scene;
  }

}
