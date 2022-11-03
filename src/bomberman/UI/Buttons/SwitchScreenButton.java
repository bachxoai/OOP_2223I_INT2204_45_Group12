package bomberman.UI.Buttons;

import bomberman.managers.Sound;
import bomberman.managers.SoundBackground;
import bomberman.managers.SoundEffect;
import bomberman.screen.levelscreen.LevelScreen;
import bomberman.screen.Screen;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class SwitchScreenButton extends Button {
    String screenHoldThisButton;
    String screenToSwitchTo;

    public SwitchScreenButton(String name, String screenHoldThisButton, String screenToSwitchTo, boolean isPaneGameOver ) {
        super(name);
        this.screenHoldThisButton = screenHoldThisButton;
        this.screenToSwitchTo = screenToSwitchTo;
        setOnAction(actionEvent -> {
            switchScreen(name);
            //tiep tuc phat nhac
            if(isPaneGameOver) {
                SoundBackground.clip.setMicrosecondPosition(SoundBackground.clip.getMicrosecondPosition());
                SoundBackground.clip.start();
            }
        });
    }
        public void switchScreen(String nameScreen) {
            Stage s = (Stage) this.getScene().getWindow();
            Screen scr = Screen.allScreens.get(screenToSwitchTo);
            s.setScene(scr.getScene());
            if(nameScreen == "Back") {
                s.setWidth(500);
                s.setHeight(800);
            } else {
                s.setWidth(640);
                s.setHeight(640);
            }
            if (scr instanceof LevelScreen) {
                ((LevelScreen) scr).startTimer();
                ((LevelScreen) scr).setBomberStats();
            }
        }
    }
