package bomberman.UI.Buttons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class MyButton extends Button {
    public static final String EXIT = "/ImageButton/exit.png";
    public static final String CONTINUE = "/ImageButton/continue.png";
    public static final String PAUSE = "/ImageButton/pause.png";
    public static final String MUSIC = "/ImageButton/music.png";
    public static final String PLAY = "/ImageButton/play.png";
    public static final String MAP = "/ImageButton/map1.png";
    public static final String BACK = "/ImageButton/back.png";
    public static final String MUTE = "/ImageButton/unmute.png";
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
        setFont(Font.loadFont("file:res/font/font.ttf",FONT_SIZE));
        setStyle("-fx-background-color: #FF9F9F");
        setTextFill(Paint.valueOf("#FFFAD7"));
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
