module bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens bomberman to javafx.fxml;
    exports bomberman;
    exports bomberman.managers;
    opens bomberman.managers to javafx.fxml;
    exports bomberman.sounds;
    opens bomberman.sounds to javafx.fxml;
}