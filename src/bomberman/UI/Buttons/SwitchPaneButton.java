package bomberman.UI.buttons;

import bomberman.managers.Sound;
import bomberman.managers.SoundBackground;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;

public class SwitchPaneButton extends Button {
    private Pane p1;
    private Pane p2;
    private Pane containedPane;

    public SwitchPaneButton(String s, Pane p1, Pane p2, Pane containedPane) {
        super(s);
        this.p1 = p1;
        this.p2 = p2;
        this.containedPane = containedPane;
        this.setOnAction(actionEvent -> { switchPane(); });
    }
    public SwitchPaneButton(Pane p1, Pane p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public void switchPane() {
        containedPane.getChildren().remove(p1);
        containedPane.getChildren().add(p2);
    }
}
