package bomberman.entities.movingEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

import java.awt.*;
//Class Quái Oneal
public class Oneal extends MovingEntity {
    public Oneal(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.oneal_left1.getFxImage();
        solidArea = new Rectangle(0,0,32,32);
        super.map = map;
        Velocity = 2;
    }
}
