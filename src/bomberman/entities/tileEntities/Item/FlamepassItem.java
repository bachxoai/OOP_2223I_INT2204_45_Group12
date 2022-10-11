package bomberman.entities.tileEntities.Item;

import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class FlamepassItem extends Item{
    public FlamepassItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_flamepass.getFxImage();
//        collision = 7;
    }
}
