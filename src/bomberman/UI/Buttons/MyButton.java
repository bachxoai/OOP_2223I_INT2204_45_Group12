package bomberman.UI.Buttons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class MyButton extends Button {
    public static final String EXIT = "/images/icons/exit.png";
    public static final String CONTINUE = "/images/icons/play.png";
    public static final String PAUSE = "/images/icons/pause.png";
    public static final String MUSIC = "/images/icons/music.png";
    public static final String PLAY = "/images/icons/play.png";
    public static final String MAP = "/images/icons/map1.png";
    public static final String BACK = "/images/icons/back.png";
    public static final String MUTE = "/images/icons/unmute.png";
    public static final int FONT_SIZE = 20;
    public  static final int BUTTON_WIDTH = 200;
    public static final int IMAGE_SIZE = 32;
    public static final int BUTTON_DISTANCE = 30;

    private final String path;

    public MyButton(String s, String path) {
        super(s);
        this.path = path;
        createFont();
        createImageButton();
        setPrefWidth(BUTTON_WIDTH);
        setOnMouseEntered(mouseEvent -> {
            setOpacity(0.75);
        });
        setOnMouseExited(mouseEvent -> {
            setOpacity(1);
        });
    }

    private void createFont() {
        setFont(Font.loadFont("file:res/fonts/font.ttf",FONT_SIZE));
        setStyle("-fx-background-color: #F9DB7B");
        setTextFill(Paint.valueOf("#F14C8F"));
    }

    private void createImageButton() {
        if (path == null || getClass().getResourceAsStream(path) == null) {
            return;
        }
        Image image = new Image(getClass().getResourceAsStream(path));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);
        setGraphic(imageView);
    }
}
