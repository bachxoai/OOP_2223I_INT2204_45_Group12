package bomberman.UI.buttons;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class ExitButton extends Button {
    public ExitButton() {
        super("Exit");
        this.setOnAction(actionEvent -> {
            Platform.exit();
        });
    }
}
