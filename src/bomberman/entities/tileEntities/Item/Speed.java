package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

public class Speed extends Item{
    public Speed(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_speed.getFxImage();
        collision = 4;
    }
}
