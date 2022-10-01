package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.Map1;
import bomberman.graphics.Sprite;

public class Detonator extends Item{
    public Detonator(int xUnit, int yUnit, Map1 map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_detonator.getFxImage();
//        collision = 6;
    }
}
