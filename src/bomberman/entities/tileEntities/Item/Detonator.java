package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

public class Detonator extends Item{
    public Detonator(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_detonator.getFxImage();
//        collision = 6;
    }
}
