package bomberman.entities.movingEntities;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

import java.awt.*;
//Class Quái Oneal
public class Oneal extends MovingEntity {
    public Oneal(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.oneal_left1.getFxImage();
        solidArea = new Rectangle(0,0,32,32);
        super.gamePlay = gamePlay;
        Velocity = 2;
    }
}
