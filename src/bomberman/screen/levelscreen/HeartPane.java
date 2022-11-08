package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.MyButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HeartPane extends HBox {
    Image HeartFull;
     public static ImageView hf1;
     public static ImageView hf2;
     public static ImageView hf3;

    public HeartPane() {
        HeartFull = new Image(HeartPane.class.getResourceAsStream("/images/icons/heart.png"));
        hf1 = new ImageView(HeartFull);
        hf1.setFitHeight(MyButton.IMAGE_SIZE);
        hf1.setFitWidth(MyButton.IMAGE_SIZE);
        hf2 = new ImageView(HeartFull);
        hf2.setFitHeight(MyButton.IMAGE_SIZE);
        hf2.setFitWidth(MyButton.IMAGE_SIZE);
        hf3 = new ImageView(HeartFull);
        hf3.setFitHeight(MyButton.IMAGE_SIZE);
        hf3.setFitWidth(MyButton.IMAGE_SIZE);
        getChildren().addAll(hf1,hf2,hf3);

    }

}
