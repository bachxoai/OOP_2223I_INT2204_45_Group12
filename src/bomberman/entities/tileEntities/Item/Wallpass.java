package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

public class Wallpass extends Item{
    public Wallpass(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_wallpass.getFxImage();
    }
}
