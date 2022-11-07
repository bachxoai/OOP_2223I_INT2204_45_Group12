package bomberman.UI.Buttons;

import javafx.scene.layout.Pane;

public class SwitchPaneButton extends MyButton {
    private Pane p1;
    private Pane p2;
    private Pane containedPane;

    public SwitchPaneButton(String s, String path, Pane p1, Pane p2, Pane containedPane) {
        super(s, path);
        this.p1 = p1;
        this.p2 = p2;
        this.containedPane = containedPane;
        this.setOnAction(actionEvent -> { switchPane(); });
    }

    public void switchPane() {
        containedPane.getChildren().remove(p1);
        containedPane.getChildren().add(p2);
    }
}
