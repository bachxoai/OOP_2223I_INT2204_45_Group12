package bomberman.screen.levelscreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class HeartPane extends HBox {
    Image HeartFull;
     public static ImageView hf1;
     public static ImageView hf2;
     public static ImageView hf3;

    public HeartPane() {
        HeartFull = new Image(HeartPane.class.getResourceAsStream("/HRed.png"));
        hf1 = new ImageView(HeartFull);
        hf1.setFitHeight(32);
        hf1.setFitWidth(32);
        hf2 = new ImageView(HeartFull);
        hf2.setFitHeight(32);
        hf2.setFitWidth(32);
        hf3 = new ImageView(HeartFull);
        hf3.setFitHeight(32);
        hf3.setFitWidth(32);
        getChildren().addAll(hf1,hf2,hf3);

    }

}
