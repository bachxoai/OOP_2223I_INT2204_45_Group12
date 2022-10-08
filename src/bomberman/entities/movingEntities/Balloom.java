package bomberman.entities.movingEntities;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

import java.awt.*;
//Class quái Balloom
public class Balloom extends MovingEntity {
    public Balloom(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.balloom_left1.getFxImage();
        solidArea = new Rectangle(0,0,32,32);
        super.map = map;
        Velocity = 2;
    }
}
