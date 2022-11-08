package bomberman.screen.levelscreen;

import bomberman.UI.Buttons.BackToMenuFromLevel;
import bomberman.UI.Buttons.ExitButton;
import bomberman.UI.Buttons.MyButton;
import bomberman.UI.Buttons.SwitchScreenButton;
import bomberman.screen.Menu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class GameOverPane extends VBox {
    LevelScreen containedLevelScreen;
    Label gameOver;
    String title;
    HBox buttonBox;
    final int X_OF_HBOX_GAMEOVER = 50;
    final int Y_OF_HBOX_GAMEOVER = 200;
    final int BUTTON_GAMEOVER_DISTANCE = 140;

    public GameOverPane(LevelScreen containedLevelScreen) {
        super(MyButton.BUTTON_DISTANCE);
        this.containedLevelScreen = containedLevelScreen;
        relocate(X_OF_HBOX_GAMEOVER,Y_OF_HBOX_GAMEOVER);

        buttonBox = new HBox(BUTTON_GAMEOVER_DISTANCE);

        gameOver = new Label(title);
        gameOver.setFont(Font.loadFont("file:res/font/font.ttf",MyButton.FONT_SIZE * 4));
        gameOver.setTextFill(Paint.valueOf("#FFFAD7"));
        getChildren().add(gameOver);

        Button exit = new ExitButton(MyButton.EXIT);

        SwitchScreenButton p = new BackToMenuFromLevel("Back", MyButton.BACK, containedLevelScreen.getCurrentScreen(), "Menu", this, containedLevelScreen);

        buttonBox.getChildren().addAll(p,exit);
        getChildren().add(buttonBox);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        gameOver.setText(title);
    }
}
