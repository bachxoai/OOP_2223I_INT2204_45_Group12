package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.Map1;
import bomberman.graphics.Sprite;

public class Wallpass extends Item{
    public Wallpass(int xUnit, int yUnit, Map1 map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_wallpass.getFxImage();
    }
}
