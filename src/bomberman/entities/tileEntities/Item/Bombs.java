package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.ClassicMap;
import bomberman.graphics.Sprite;

public class Bombs extends Item{
    public Bombs(int xUnit, int yUnit, ClassicMap map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_bombs.getFxImage();
        collision = 2;
    }
}
