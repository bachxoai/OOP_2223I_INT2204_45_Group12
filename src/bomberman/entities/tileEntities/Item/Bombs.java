package bomberman.entities.tileEntities.Item;

import bomberman.ScreenController.Map1;
import bomberman.graphics.Sprite;

public class Bombs extends Item{
    public Bombs(int xUnit, int yUnit, Map1 map) {
        super(xUnit, yUnit, map);
        img = Sprite.powerup_bombs.getFxImage();
        collision = 2;
    }
}
