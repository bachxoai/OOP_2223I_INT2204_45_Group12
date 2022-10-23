package bomberman.entities.tile.item;

import bomberman.managers.CollisionChecker;
import bomberman.managers.GamePlay;
import bomberman.graphics.Sprite;

public class FlamePassItem extends Item {
    public FlamePassItem(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        img = Sprite.powerup_flamepass.getFxImage();
        collision = CollisionChecker.FLAME_PASS_ITEM_COLLISION;
    }
}
