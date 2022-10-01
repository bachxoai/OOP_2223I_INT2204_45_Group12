package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.Map1;
import bomberman.graphics.Sprite;

public class Flames extends Item{
    public Flames(int xUnit, int yUnit, Map1 map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_flames.getFxImage();
        collision = 3;
    }
}
