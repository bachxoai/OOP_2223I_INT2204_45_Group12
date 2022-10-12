package bomberman.entities.tileEntities.Item;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class WallpassItem extends Item{
    public WallpassItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_wallpass.getFxImage();
    }
}