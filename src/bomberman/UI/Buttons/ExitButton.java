package bomberman.UI.Buttons;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class ExitButton extends MyButton {
    public ExitButton(String path) {
        super("Exit", path);
        this.setOnAction(actionEvent -> {
            Platform.exit();
        });
    }
}
