package bomberman.ScreenController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Screen {
  int k = 0;
  Label l;
  Button b;

  public Menu(String currentScreen) {
    super(currentScreen);
    createLable();
    createButton();
    createScene();
  }

  public void createLable() {
    l = new Label("Click to switch to Map1");
  }

  public void createButton() {
    b = new Button("Switch to map1");
    b.setOnAction(actionEvent -> {
      k += 1;
      System.out.println(currentScreen);
      System.out.println(k);
      currentScreen = "Map1";
      Stage s = (Stage) b.getScene().getWindow();
      s.setScene(allScreens.get(currentScreen).getScene());
    });
    b.setLayoutX(100);
    b.setLayoutY(100);
  }

  public void createScene() {
    VBox root = new VBox();
    root.getChildren().addAll(l, b);
    scene = new Scene(root);
    scene.setOnKeyPressed(keyEvent -> {
      switch (keyEvent.getCode()) {
        case A:
          System.out.println("Up");
      }
    });
  }
}
